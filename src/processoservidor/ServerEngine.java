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
    
    
    public ServerEngine() throws RemoteException{
        super();
    }
    
    @Override
    public void compraPassagem() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compraHospedagem() throws RemoteException {
        System.out.println("Ok");
    }

    @Override
    public void registraInteresse(String cliente, String tipo_interesse) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }
    public void cadastraOfertaHospedagem(OfertaHospedagem novaOfertaDeHospedagem){
        listaDeHospedagens.add(novaOfertaDeHospedagem);
    }
}
