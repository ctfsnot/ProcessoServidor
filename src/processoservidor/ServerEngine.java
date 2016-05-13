/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

import interfaces.InterfaceServidor;
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
    public boolean registraInteresse(String cliente, String origem, String destino, float preco) throws RemoteException {
        InteresseVoo int_voo = new InteresseVoo(cliente, origem, destino, preco);
        return listaDeinteressesVoo.add(int_voo);
    }
    @Override
    public boolean registraInteresse(String cliente, String local, int quartos, float preco) throws RemoteException {
        InteresseHospedagem int_hosp = new InteresseHospedagem(cliente, local, quartos, preco);
        return listaDeinteressesHospedagem.add(int_hosp);
    }

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
    
    public void cadastraOfertaVoo(OfertaVoo novaOfertaDeVoo){
        listaDeVoos.add(novaOfertaDeVoo);
        //for(int i = 0, i<listaDeinte){
        
        //}
    }
    public void cadastraOfertaHospedagem(OfertaHospedagem novaOfertaDeHospedagem){
        listaDeHospedagens.add(novaOfertaDeHospedagem);
    }

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
