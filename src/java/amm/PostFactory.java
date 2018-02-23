/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Mirko
 */
public class PostFactory {
        
    private static PostFactory singleton;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<Post>();
/*
    private PostFactory() {
    
        //Creazione Post
        Post post1 = new Post();
        post1.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteUno"));
        post1.setId(1);
        post1.setGruppo(null);
        post1.setContent("Contenuto Post 1");
        post1.setTipo(Post.Type.TEXT);
        
        Post post2 = new Post();
        post2.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteDue"));
        post2.setId(2);
        post2.setGruppo(null);
        post2.setAllegato("immagini/imglink.png");
        post2.setTipo(Post.Type.IMAGE);
        
        Post post3 = new Post();
        post3.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteTre"));
        post3.setId(3);
        post3.setGruppo(null);
        post3.setAllegato("http://www.html.it/");
        post3.setTipo(Post.Type.URL);
        
        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
    
    }
    */
    
        public Post getPostById(int id) {
    
        UtentiRegistratiFactory utn = UtentiRegistratiFactory.getInstance(); 
        try {            
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");          
            String sql = "SELECT * FROM Post WHERE Post.Id = ?";
          
            PreparedStatement stmt = conn.prepareStatement(sql);          
            stmt.setInt(1, id);            
            ResultSet set = stmt.executeQuery();
          
            if (set.next()) {
                Post post_ = new Post();       
                post_.setId(set.getInt("Id"));
                post_.setContent(set.getString("content"));
                post_.setTipo(this.postTypeFromString(set.getString("tipo")));
                UtentiRegistrati autore = UtentiRegistratiFactory.getInstance().getUserById(set.getInt("autore"));
                post_.setAutore(autore);
                post_.setAllegato(set.getString("allegato"));
                post_.setGruppo(GruppiFactory.getInstance().getGroupById(set.getInt("gruppo")));
                UtentiRegistrati destinatario = UtentiRegistratiFactory.getInstance().getUserById(set.getInt("destinatario"));
                post_.setDestinatario(destinatario);
                stmt.close();
                conn.close();
                return post_;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        
        private Post.Type postTypeFromString(String tipo){        
        if(tipo.equals("TEXT")) return Post.Type.TEXT;           
        else if (tipo.equals("URL")) return Post.Type.URL;
        else return Post.Type.IMAGE;
    }
        
     public List getPostByUser(UtentiRegistrati utn)
    {
        List<Post> lista = new ArrayList<Post>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "SELECT * FROM Post WHERE Post.autore = ? AND Post.destinatario IS NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utn.getId());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Post post_ = new Post();
                post_.setId(set.getInt("Id"));
                post_.setContent(set.getString("content"));
                post_.setTipo(this.postTypeFromString(set.getString("tipo")));
                post_.setAutore(utn);
                post_.setAllegato(set.getString("allegato"));
                post_.setGruppo(GruppiFactory.getInstance().getGroupById(set.getInt("gruppo")));
                UtentiRegistrati destinatario = UtentiRegistratiFactory.getInstance().getUserById(set.getInt("destinatario"));
                post_.setDestinatario(destinatario);
                lista.add(post_);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
       
    }
    public List getPostByDest(UtentiRegistrati utn)
    {
               List<Post> lista = getPostByUser(utn);
       try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "SELECT * FROM Post WHERE Post.destinatario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utn.getId());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Post post_ = new Post();
                post_.setId(set.getInt("Id"));
                post_.setContent(set.getString("content"));
                post_.setTipo(this.postTypeFromString(set.getString("tipo")));
                post_.setDestinatario(utn);
                post_.setAutore(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("autore")));
                post_.setAllegato(set.getString("allegato"));
                post_.setGruppo(GruppiFactory.getInstance().getGroupById(set.getInt("gruppo")));
                lista.add(post_);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;       
    }
    
    public List getPostByGroup(Gruppi grp)
    {
                 List<Post> lista = new ArrayList<Post>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "SELECT * FROM Post WHERE Post.gruppo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, grp.getId());
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Post post_ = new Post();
                post_.setId(set.getInt("Id"));
                post_.setContent(set.getString("content"));
                post_.setTipo(this.postTypeFromString(set.getString("tipo")));
                post_.setDestinatario(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("destinatario")));
                post_.setAutore(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("autore")));
                post_.setAllegato(set.getString("allegato"));
                post_.setGruppo(grp);
                lista.add(post_);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
    
    private String postTypeFromType(Post.Type tipo){
        if(tipo == Post.Type.TEXT)
                return "TEXT";
        else if (tipo == Post.Type.IMAGE)
                return "IMAGE";
        else    return "URL";
    }

  public void addNewPost(Post nuovo)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "INSERT INTO Post (Id, autore, destinatario, gruppo, content, tipo, allegato) VALUES (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nuovo.getAutore().getId());
           
            if(nuovo.getDestinatario() != null)
                stmt.setInt(2, nuovo.getDestinatario().getId());
            else
                stmt.setString(2, null);
            
            if(nuovo.getGruppo() != null)
                stmt.setInt(3, nuovo.getGruppo().getId());
            else
                stmt.setString(3, null);
            
            if(nuovo.getContent() != null)
                stmt.setString(4, nuovo.getContent());
            else
                stmt.setString(4, null);
            
            stmt.setString(5, this.postTypeFromType(nuovo.getTipo()));            
           
            if(nuovo.getAllegato() != null)
                stmt.setString(6, nuovo.getAllegato());
            else
                stmt.setString(6, null);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
        
  public boolean eliminaPost(int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = 
                      "DELETE FROM Post WHERE Post.Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            ps.close();
            conn.close();
            if(rs == 1)
                return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
  
   public boolean eliminaProfiloByUtente(UtentiRegistrati utn)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = 
                      "DELETE FROM Post WHERE Post.autore = ? OR Post.destinatario = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utn.getId());
            stmt.setInt(2, utn.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Post WHERE Post.gruppo IN (SELECT Groups.Id FROM Groups WHERE creatore = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utn.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
}
