/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;

/**
 *
 * @author Mirko
 */
public class Gruppi {
    
    private String Nome;
    private String UrlImgPro;
    private String argomento;
    private UtentiRegistrati creatore;
    private int Id;
    
 public Gruppi()
    {
        Nome = "";
        UrlImgPro = "";
        Id = 0;     
    }
 
     /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
 
    /**
     * @return the UrlImgPro
     */
    public String getUrlImgPro() {
        return UrlImgPro;
    }

    /**
     * @param UrlImgPro the UrlImgPro to set
     */
    public void setUrlImgPro(String UrlImgPro) {
        this.UrlImgPro = UrlImgPro;
    }
    
    /**
     * @return the creatore
     */
    public UtentiRegistrati getCreatore() {
        return creatore;
    }

    /**
     * @param creatore the founder to set
     */
    public void setCreatore(UtentiRegistrati creatore) {
        this.creatore = creatore;
    }
    
        /**
     * @return the argomento
     */
    public String getArgomento() {
        return argomento;
    }

    /**
     * @param argomento the argomento to set
     */
    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }
    
     /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }
    
    @Override
    public boolean equals(Object gruppo) {
        if(gruppo == null) return false;
        if (gruppo instanceof Gruppi) if (this.getId() == ((Gruppi)gruppo).getId()) return true;
        
        return false;
    }
}
