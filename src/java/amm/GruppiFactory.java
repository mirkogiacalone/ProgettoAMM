/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Mirko
 */
public class GruppiFactory {
    
    private static GruppiFactory singleton; 
    public static GruppiFactory getInstance()
    {
        if(singleton == null) singleton = new GruppiFactory();
        return singleton;
    }
   
    private ArrayList<Gruppi> listaGruppi = new ArrayList<Gruppi>();
    
    private GruppiFactory()
    {/*
        Gruppi Gruppo1 = new Gruppi();
        Gruppo1.setCreatore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteUno"));
        Gruppo1.setArgomento("Argomento 1");
        Gruppo1.setNome("GruppoUno");
        Gruppo1.setUrlImgPro("immagini/clock.png");
        Gruppo1.setId(1);
        
        Gruppi Gruppo2 = new Gruppi();
        Gruppo2.setCreatore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteDue"));
        Gruppo2.setArgomento("Argomento 2");
        Gruppo2.setNome("GruppoDue");
        Gruppo2.setUrlImgPro("immagini/clock.png");
        Gruppo2.setId(2);
        
        Gruppi Gruppo3 = new Gruppi();
        Gruppo3.setCreatore(UtentiRegistratiFactory.getInstance().getUserByName("UtenteTre"));
        Gruppo3.setArgomento("Argomento 3");
        Gruppo3.setNome("GruppoTre");
        Gruppo3.setUrlImgPro("immagini/clock.png");
        Gruppo3.setId(3);
       
        
        listaGruppi.add(Gruppo1);
        listaGruppi.add(Gruppo2);
        listaGruppi.add(Gruppo3);
      */
    }
    
    public Gruppi getGroupById(int id)
    {
         try {            
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");          
            String sql = "SELECT * FROM Groups WHERE Groups.Id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                Gruppi group = new Gruppi(); 
                group.setCreatore(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("creatore")));               
                group.setNome(set.getString("nome"));
                group.setArgomento(set.getString("argomento"));                
                group.setUrlImgPro(set.getString("UrlImgPro"));
                group.setId(set.getInt("Id"));
                stmt.close();
                conn.close();
                return group;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        
    public Gruppi getGroupByName(String n)
    {
        try {            
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");          
            String sql = "SELECT * FROM Groups WHERE Groups.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, n);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                Gruppi group = new Gruppi();
                group.setCreatore(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("creatore")));               
                group.setNome(set.getString("nome"));
                group.setArgomento(set.getString("argomento"));                
                group.setUrlImgPro(set.getString("UrlImgPro"));
                group.setId(set.getInt("Id"));
                stmt.close();
                conn.close();
                return group;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List getGroupList(){
            List<Gruppi> lista = new ArrayList<>();
        try {           
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");           
            String sql = "SELECT* FROM Groups ";           
            PreparedStatement stmt = conn.prepareStatement(sql);            
            ResultSet set = stmt.executeQuery();           
              while (set.next()) {
                Gruppi group = new Gruppi();
                group.setCreatore(UtentiRegistratiFactory.getInstance().getUserById(set.getInt("creatore")));               
                group.setNome(set.getString("nome"));
                group.setArgomento(set.getString("argomento"));                
                group.setUrlImgPro(set.getString("UrlImgPro"));
                group.setId(set.getInt("Id"));
                lista.add(group);
              }

              stmt.close();
              conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
              }
        return lista;
    }
   
        public boolean joined(UtentiRegistrati user_, Gruppi group_)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "SELECT * FROM Users_Groups WHERE Users_Groups.Id_u = ? AND Users_Groups.Id_g = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user_.getId());
            stmt.setInt(2, group_.getId());
            ResultSet set = stmt.executeQuery();
            boolean controllo = false;
            if(set.next()) controllo = true;
            stmt.close();
            conn.close();
            return controllo;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
        public boolean iscrizione(int Id_utente, int Id_group)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "INSERT INTO Users_Groups(Id, Id_u, Id_g) VALUES (default, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id_utente);
            stmt.setInt(2, Id_group);
            int set = stmt.executeUpdate();
            if(set == 1)
            {
                stmt.close();
                conn.close();
                return true;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
        
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
        }
    public String getConnectionString(){
            return this.connectionString;
        }

    public boolean eliminaGruppo(int id)
    {
      
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Post WHERE Post.gruppo = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Users_Groups WHERE Users_Groups.Id_g = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch(SQLException e){
                    e.printStackTrace();
                }
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql= "DELETE FROM Groups WHERE Groups.Id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
}
