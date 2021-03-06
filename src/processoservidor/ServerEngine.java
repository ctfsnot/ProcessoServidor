/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

import interfaces.InterfaceCliente;
import interfaces.InterfaceServidor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author rz
 */
public class ServerEngine extends UnicastRemoteObject implements InterfaceServidor{
    
    //array para guardar as ofertas de hospedagem disponíveis
    private ArrayList<OfertaHospedagem> listaDeHospedagens = new ArrayList();
    //array para guardar as ofertas de passagem disponíveis
    private ArrayList<OfertaVoo> listaDeVoos = new ArrayList();
    //array para guardar os interesses em passagens
    private ArrayList<InteresseVoo> listaDeinteressesVoo = new ArrayList();
    //array para guardar os interesses em hospedagens
    private ArrayList<InteresseHospedagem> listaDeinteressesHospedagem = new ArrayList();
    
    
    public ServerEngine() throws RemoteException{
        super();
    }
    //o método compra de passagem registra uma nova compra de passagem e remove ela da lista
    //de passagens disponíveis. Ele é synchronized para evitar o acesso concorrente
    @Override
    public synchronized boolean compraPassagem(int passagem) throws RemoteException {
        Object ret = listaDeVoos.remove(passagem);
        if(ret != null)
            return true;
        else return false;
    }
    //o método compra de hospedagem registra uma nova compra de hospedagem e remove ela da lista
    //de hospedagens disponíveis. Ele é synchronized para evitar o acesso concorrente
    @Override
    public synchronized boolean compraHospedagem(int hospedagem) throws RemoteException {
        Object ret = listaDeHospedagens.remove(hospedagem);
        if(ret != null)
            return true;
        else return false;
    }
    //o método registra interesse de passagem registra um novo interesse de passagem
    @Override
    public boolean registraInteresse(InterfaceCliente cliente, String origem, String destino, float preco) throws RemoteException {
        InteresseVoo int_voo = new InteresseVoo(cliente, origem, destino, preco);
        return listaDeinteressesVoo.add(int_voo);
    }
    //o método registra interesse de hospedagem registra um novo interesse de hospedagem
    @Override
    public boolean registraInteresse(InterfaceCliente cliente, String local, int quartos, float preco) throws RemoteException {
        InteresseHospedagem int_hosp = new InteresseHospedagem(cliente, local, quartos, preco);
        return listaDeinteressesHospedagem.add(int_hosp);
    }
    //método para listar todas as passagens disponíveis
    @Override
    public Object[] listaPassagens() throws RemoteException {
        ArrayList<String> results = new ArrayList();
        
        for(int i = 0; i < this.listaDeVoos.size(); i++){
            String r = "";
            r += "De "+ listaDeVoos.get(i).getOrigem();
            r += " Para "+ listaDeVoos.get(i).getDestino();
            r += " - R$: " + listaDeVoos.get(i).getPreço();
            
            results.add(r);
        }
        
        return results.toArray();
    }
    //método para cadastrar novas ofertas de passagens(de vôos) e para informar ao cliente quando
    //uma nova oferta mais econômica é cadastrada de acordo com o interesse
    public void cadastraOfertaVoo(OfertaVoo novaOfertaDeVoo) throws NotBoundException, MalformedURLException, RemoteException{
        listaDeVoos.add(novaOfertaDeVoo);
        //verifica se a nova oferta é melhor que alguma guardada no array de interesses
        //para cada item na lista de interesses de vôo
        for(int i = 0; i< this.listaDeinteressesVoo.size(); i++ ){
            //verifica se os parâmetros combinam com a novaOferta que adicionamos
            InteresseVoo interesse = listaDeinteressesVoo.get(i);
            if(interesse.getOrigem().equals(novaOfertaDeVoo.getOrigem())){
                if(interesse.getDestino().equals(novaOfertaDeVoo.getDestino())){
                    if(interesse.getPreco() > novaOfertaDeVoo.getPreço()){
                        //então, chama o método do objeto remoto CLIENTE, informando que há nova oferta
                        //de interesse.
                        //InterfaceCliente client = (InterfaceCliente) Naming.lookup("rmi://localhost:8888/"+interesse.getCliente());
                        String toSend = "";
                        toSend += "De "+ novaOfertaDeVoo.getOrigem();
                        toSend += " Para "+ novaOfertaDeVoo.getDestino();
                        toSend += " - R$: " + novaOfertaDeVoo.getPreço();
                        interesse.getCliente().notificaInteresse("\n\n[OFERTA]: " + toSend + "\n\n");
                    }
                }
            }
        }
    }
    //método para cadastrar novas ofertas de hospedagem e para informar ao cliente quando
    //uma nova oferta mais econômica é cadastrada de acordo com o interesse
    public void cadastraOfertaHospedagem(OfertaHospedagem novaOfertaDeHospedagem) throws RemoteException{
        listaDeHospedagens.add(novaOfertaDeHospedagem);
        //verifica se a nova oferta é melhor que alguma guardada no array de interesses
        //para cada item na lista de interesses de hospedagem
        for(int i = 0; i<this.listaDeinteressesHospedagem.size(); i++){
            //verifica se os parâmetros combinam com a novaOferta que adicionamos
            InteresseHospedagem interesseh = listaDeinteressesHospedagem.get(i);
            if(interesseh.getLocal().equals(novaOfertaDeHospedagem.getLocal())){
                if(interesseh.getQuartos()== novaOfertaDeHospedagem.getQuartos()){
                    if(interesseh.getPreco() > novaOfertaDeHospedagem.getPreço()){
                        //então, chama o método do objeto remoto CLIENTE, informando que há nova oferta
                        //de interesse.
                        //InterfaceCliente client = (InterfaceCliente) Naming.lookup("rmi://localhost:8888/"+interesse.getCliente());
                        String toSend = "";
                        toSend += "Em "+ novaOfertaDeHospedagem.getLocal();
                        toSend += ", com "+ novaOfertaDeHospedagem.getQuartos() + " quartos de casal";
                        toSend += " - R$: " + novaOfertaDeHospedagem.getPreço();
                        interesseh.getCliente().notificaInteresse("\n\n[OFERTA]: " + toSend + "\n\n");
                    }
                }
            }
        }
    }
    //método para listar as hospedagens disponíveis
    @Override
    public Object[] listaHospedagens() throws RemoteException {
        ArrayList<String> results = new ArrayList();
        
        for(int i = 0; i < this.listaDeHospedagens.size(); i++){
            String r = "";
            r += "Em "+ listaDeHospedagens.get(i).getLocal();
            r += ", com "+ listaDeHospedagens.get(i).getQuartos() + " quartos de casal";
            r += " - R$: " + listaDeHospedagens.get(i).getPreço();
            
            results.add(r);
        }
        
        return results.toArray();
    }
}
