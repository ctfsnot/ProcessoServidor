/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

/**
 *
 * @author Pepo
 */
public class InteresseHospedagem {

    private String local;
    private int quartos;
    private float preco;
    private String cliente;
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public InteresseHospedagem(String cliente, String local, int quartos, float preco) {
        this.cliente = cliente;
        this.local = local;
        this.quartos = quartos;
        this.preco = preco;
    }
    
    
}