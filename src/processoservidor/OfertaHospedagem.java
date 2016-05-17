/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processoservidor;

/**
 *
 * @author rz
 * 
 * essa classe define oferta de hospedagem com seus atributos, setters e guetters
 * e seu construtor
 */
public class OfertaHospedagem {

    private String local;
    private int quartos;
    private float preço;
    //construtor
    public OfertaHospedagem(String local, int quartos, float preço) {
        this.local = local;
        this.quartos = quartos;
        this.preço = preço;
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

    public float getPreço() {
        return preço;
    }

    public void setPreço(float preço) {
        this.preço = preço;
    }
    
    
}
