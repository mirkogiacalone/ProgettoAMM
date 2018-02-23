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
public class Post {
    
    public enum Type {
        TEXT, IMAGE, URL
    };

    private UtentiRegistrati autore;
    private UtentiRegistrati destinatario;
    private int id;
    private Gruppi gruppo;
    private String content;
    private Type tipo;
    private String allegato;
    
 public Post()
    {
        autore = null;
        destinatario = null;
        id = 0;
        gruppo = null;
        content = "";
        tipo = Type.TEXT;
        
    }
 
 /**
     * @return autore
     */
    public UtentiRegistrati getAutore() {
        return autore;
    }
    
 /**
     * @param autore autore to set
     */
    public void setAutore(UtentiRegistrati autore) {
        this.autore = autore;
    }
    
        /**
     * @return the allegato
     */
    public String getAllegato() {
        return allegato;
    }

    /**
     * @param allegato the allegato to set
     */
    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }
    
    /**
     * @return destinatario
     */
    public UtentiRegistrati getDestinatario() {
        return destinatario;
    }
    
 /**
     * @param destinatario destinatario to set
     */
    public void setDestinatario(UtentiRegistrati destinatario) {
        this.destinatario = destinatario;
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
     * @return gruppo
     */
    public Gruppi getGruppo() {
        return gruppo;
    }

    /**
     * @param gruppo gruppo to set
     */
    public void setGruppo(Gruppi gruppo) {
        this.gruppo = gruppo;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the Type
     */
    public Type getTipo() {
        return tipo;
    }

    /**
     * @param tipo the Type to set
     */
    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }   
}
