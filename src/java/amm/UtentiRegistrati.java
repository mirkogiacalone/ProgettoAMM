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
public class UtentiRegistrati {
    private String nome;
    private String cognome;
    private String UrlImgPro;
    private String frase;
    private String data;
    private String password;
    private int id;
    private Type PotereUtente;
    
    public enum Type
    {
        USER, SUPERUSER
    };
    
    public UtentiRegistrati()
    {     
        nome = "";
        cognome = "";
        UrlImgPro = "";
        frase = null;
        data = "";
        password = "";
        id = 0;
        PotereUtente = Type.USER;
        
    }
    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
       /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
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
     * @return the frase
     */
    public String getFrase() {
        return frase;
    }

    /**
     * @param frase the frase to set
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }
   
        /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
        /**
     * @return the PotereUtente
     */
    public Type getPotereUtente() {
        return PotereUtente;
    }

    /**
     * @param PotereUtente the PotereUtente to set
     */
    public void setPotereUtente(Type PotereUtente) {
        this.PotereUtente = PotereUtente;
    }
    
    @Override
    public boolean equals(Object utente_) {
        if(utente_ == null) return false;
        if (utente_ instanceof UtentiRegistrati)
            if (this.getId() == ((UtentiRegistrati)utente_).getId()) return true;
        return false;
    }
}
