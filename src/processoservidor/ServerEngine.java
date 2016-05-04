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

/**
 *
 * @author rz
 */
public class ServerEngine extends UnicastRemoteObject implements InterfaceServidor{

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
    
}
