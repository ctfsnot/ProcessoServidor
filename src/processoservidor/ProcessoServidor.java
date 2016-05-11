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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author a1144847
 */
public class ProcessoServidor {

    private static InterfaceServidor serverEngine;
    private static Registry servidorNomes;
    
    private static ArrayList<OfertaHospedagem> listaDeHospedagens;
    private static ArrayList<OfertaVoo> listaDeVoos;
    
    public static void main(String[] args) throws RemoteException, ParseException {
        serverEngine = new ServerEngine(); //objeto remoto do servidor
        
        servidorNomes = LocateRegistry.createRegistry(8888);
        servidorNomes.rebind("ServerEngine", serverEngine);
        
        menu();
   
    }
    public static void menu() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        
        while(true){
            String MENU1 = "### PROCESSO SERVIDOR ###\n\n";
            MENU1 += "1. Cadastrar nova oferta de Vôo\n";
            MENU1 += "2. Cadastrar nova oferta de Hospedagem\n";
            MENU1 += "3. Consultar ofertas de Vôo\n";
            MENU1 += "4. Consultar ofertas de Hospedagem\n\n";

            System.out.print(MENU1 + "Opção: ");
            option = scanner.nextLine();

            switch (option){
                case "1":
                    System.out.print("\nDigite a oferta de Vôo conforme o padrão a seguir: "
                            + "\nOrigem,Destino,Data/De/Ida,Preço\n\nOpção: ");
                    
                    String[] parts_voo = scanner.nextLine().split(",");
                    
                    OfertaVoo novaOfertaDeVoo = new OfertaVoo(
                            parts_voo[0], 
                            parts_voo[1], 
                            (new SimpleDateFormat("dd/MM/yyyy")).parse(parts_voo[2]), 
                            Float.parseFloat(parts_voo[3])
                    );
                    ProcessoServidor.listaDeVoos.add(novaOfertaDeVoo);
                    break;
                    
                case "2":
                    System.out.print("\nDigite a oferta de hospedagem conforme padrão a seguir:\n"
                            + "CidadeOuNomeDoHotel,NumQuartos,Preço\n\nOpção: ");
                    
                    String[] parts_hosp = scanner.nextLine().split(",");
                    
                    OfertaHospedagem novaOfertaHospedagem = new OfertaHospedagem(
                            parts_hosp[0], 
                            Integer.parseInt(parts_hosp[1]), 
                            Float.parseFloat(parts_hosp[2])
                    );
                    ProcessoServidor.listaDeHospedagens.add(novaOfertaHospedagem);
                    
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }      
    }
}
