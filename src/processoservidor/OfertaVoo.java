/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

import java.util.Date;

/**
 *
 * @author rz
 */
public class OfertaVoo {

    private String origem;
    private String destino;
    private Date data;
    private float preço;
    
    public OfertaVoo(String origem, String destino, Date data, float preço) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.preço = preço;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getPreço() {
        return preço;
    }

    //origem,destino,data,preço
    public void setPreço(float preço) {
        this.preço = preço;
    }
    
    
}
