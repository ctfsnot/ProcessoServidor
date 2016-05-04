/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

import interfaces.InterfaceServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author a1144847
 */
public class ProcessoServidor {

    private static void loadGUI() {
        String MENU1 = "### PROCESSO SERVIDOR ###\n\n";
        MENU1 += "1. Cadastrar nova oferta de Hospedagem\n";
        MENU1 += "2. Cadastrar nova oferta de Vôo\n";
        MENU1 += "3. Consultar ofertas de Hospedagem\n";
        MENU1 += "4. Consultar ofertas de Vôo\n\n";
        
        String MENU2 = "### CADASTRO DE OFERTAS ###\n\n";
    }

    ServerEngine serverEngine;
    static Registry servidorNomes;
    
    public static void main(String[] args) throws RemoteException {
        InterfaceServidor serverEngine = new ServerEngine(); //objeto remoto do servidor
        servidorNomes = LocateRegistry.createRegistry(8888);
        servidorNomes.rebind("ServerEngine", serverEngine);
        
        loadGUI();
        
    }

    
}
