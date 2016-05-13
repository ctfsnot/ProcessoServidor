/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

import interfaces.InterfaceServidor;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author a1144847
 */
public class ProcessoServidor {

    private static ServerEngine serverEngine ;
    private static Registry servidorNomes;
    
    public static void main(String[] args) throws RemoteException, ParseException, NotBoundException, MalformedURLException {
        serverEngine = new ServerEngine(); //objeto remoto do servidor        
        servidorNomes = LocateRegistry.createRegistry(8888);
        servidorNomes.rebind("ServerEngine", serverEngine);
        
        makeData();  //gera dados para teste
        menu();
   
    }
    public static void menu() throws ParseException, RemoteException, NotBoundException, MalformedURLException {
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
            
            float preco;
            
            switch (option){
                case "1":
                    System.out.print("\nDigite a origem: ");
                    String origem = "";
                    origem = scanner.nextLine();
                    System.out.print("\nDigite o destino: ");
                    String destino = "";
                    destino = scanner.nextLine();
                    System.out.print("\nDigite o preço: ");
                    preco = scanner.nextFloat();
                    scanner.nextLine();
                    
                    OfertaVoo novaOfertaDeVoo = new OfertaVoo(origem, destino, preco);
                    serverEngine.cadastraOfertaVoo(novaOfertaDeVoo);
                    
                    break;
                    
                case "2":
                    System.out.print("\nDigite o local: ");
                    String local = "";
                    origem = scanner.nextLine();
                    System.out.print("\nDigite o número de quartos: ");
                    int quartos;
                    quartos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("\nDigite o preço: ");
                    
                    preco = scanner.nextFloat();
                    scanner.nextLine();
                    
                    OfertaHospedagem novaOfertaHospedagem = new OfertaHospedagem(local, quartos, preco);
                    serverEngine.cadastraOfertaHospedagem(novaOfertaHospedagem);
                    break;
                case "3":
                    for (int i = 0; i < serverEngine.listaPassagens().length; i++){
                        String passagem = (String)serverEngine.listaPassagens()[i];
                        System.out.println((i+1) + ". " + passagem );
                    }
                    break;
                case "4":
                    for (int i = 0; i < serverEngine.listaHospedagens().length; i++){
                        String hospedagem = (String)serverEngine.listaHospedagens()[i];
                        System.out.println((i+1) + ". " + hospedagem );
                    }
                    break;
                default:
                    break;
            }
        }      
    }

    private static void makeData() throws NotBoundException, MalformedURLException, RemoteException {
        //cria cinco ofertas de vôo e cinco ofertas de hospedagem
        serverEngine.cadastraOfertaVoo(new OfertaVoo("Curitiba", "São Paulo", 100.00f));
        serverEngine.cadastraOfertaVoo(new OfertaVoo("Curitiba", "Minas Gerais", 115.00f));
        serverEngine.cadastraOfertaVoo(new OfertaVoo("Curitiba", "Florianopolis", 96.00f));
        serverEngine.cadastraOfertaVoo(new OfertaVoo("Curitiba", "Bahia", 350.00f));
        serverEngine.cadastraOfertaVoo(new OfertaVoo("Curitiba", "Rio Grande do Sul", 120.00f));
        
        serverEngine.cadastraOfertaHospedagem(new OfertaHospedagem("Curitiba", 3, 75.00f));
        serverEngine.cadastraOfertaHospedagem(new OfertaHospedagem("São Paulo", 2, 225.00f));
        serverEngine.cadastraOfertaHospedagem(new OfertaHospedagem("Florianopolis", 2, 215.00f));
        serverEngine.cadastraOfertaHospedagem(new OfertaHospedagem("Bahia", 3, 375.00f));
        serverEngine.cadastraOfertaHospedagem(new OfertaHospedagem("Rio Grande do Sul", 4, 175.00f));
        
    }
}
