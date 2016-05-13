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
public class InteresseVoo {

    private String cliente;
    private String origem;
    private String destino;
    private float preco;
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public InteresseVoo(String cliente, String origem, String destino, float preco) {
        this.cliente = cliente;
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
    }
    
    
    
}
