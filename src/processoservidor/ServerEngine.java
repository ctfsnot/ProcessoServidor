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
    
    
    private ArrayList<OfertaHospedagem> listaDeHospedagens = new ArrayList();
    private ArrayList<OfertaVoo> listaDeVoos = new ArrayList();
    private ArrayList<InteresseVoo> listaDeinteressesVoo = new ArrayList();
    private ArrayList<InteresseHospedagem> listaDeinteressesHospedagem = new ArrayList();
    
    
    public ServerEngine() throws RemoteException{
        super();
    }
    
    @Override
    public synchronized boolean compraPassagem(int passagem) throws RemoteException {
        Object ret = listaDeVoos.remove(passagem);
        if(ret != null)
            return true;
        else return false;
    }

    @Override
    public synchronized boolean compraHospedagem(int hospedagem) throws RemoteException {
        Object ret = listaDeHospedagens.remove(hospedagem);
        if(ret != null)
            return true;
        else return false;
    }

    @Override
    public synchronized boolean registraInteresse(String cliente, String origem, String destino, float preco) throws RemoteException {
        InteresseVoo int_voo = new InteresseVoo(cliente, origem, destino, preco);
        return listaDeinteressesVoo.add(int_voo);
    }
    @Override
    public synchronized boolean registraInteresse(String cliente, String local, int quartos, float preco) throws RemoteException {
        InteresseHospedagem int_hosp = new InteresseHospedagem(cliente, local, quartos, preco);
        return listaDeinteressesHospedagem.add(int_hosp);
    }

    @Override
    public synchronized Object[] listaPassagens() throws RemoteException {
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
    
    public synchronized void cadastraOfertaVoo(OfertaVoo novaOfertaDeVoo) throws NotBoundException, MalformedURLException, RemoteException{
        listaDeVoos.add(novaOfertaDeVoo);
        //para cada item na lista de interesses de vôo
        for(int i = 0; i< this.listaDeinteressesVoo.size(); i++ ){
            //verifica se os parâmetros combinam com a novaOferta que adicionamos
            InteresseVoo interesse = listaDeinteressesVoo.get(i);
            
            if(interesse.getOrigem().equals(novaOfertaDeVoo.getOrigem())){
                if(interesse.getDestino().equals(novaOfertaDeVoo.getDestino())){
                    if(interesse.getPreco() > novaOfertaDeVoo.getPreço()){
                        //então, chama o método do objeto remoto CLIENTE, informando que há nova oferta
                        //de interesse.
                        InterfaceCliente client = (InterfaceCliente) Naming.lookup("rmi://localhost:8888/"+interesse.getCliente());
                        String toSend = "";
                        toSend += "De "+ novaOfertaDeVoo.getOrigem();
                        toSend += " Para "+ novaOfertaDeVoo.getDestino();
                        toSend += " - R$: " + novaOfertaDeVoo.getPreço();
                        client.notificaInteresse("\n\n[OFERTA]: " + toSend + "\n\n");
                    }
                }
            }
        }
    }
    public synchronized void cadastraOfertaHospedagem(OfertaHospedagem novaOfertaDeHospedagem){
        listaDeHospedagens.add(novaOfertaDeHospedagem);
    }

    @Override
    public synchronized Object[] listaHospedagens() throws RemoteException {
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
