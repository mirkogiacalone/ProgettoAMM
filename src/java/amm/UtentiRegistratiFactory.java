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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mirko
 */
public class UtentiRegistratiFactory {
        private static UtentiRegistratiFactory singleton;
    
    public static UtentiRegistratiFactory getInstance()
    {
        if(singleton == null)
            singleton = new UtentiRegistratiFactory();
        return singleton;
    }
    
    private ArrayList<UtentiRegistrati> listaUtenti = new ArrayList<UtentiRegistrati>();
    
    private UtentiRegistratiFactory()
    {
    /*
    UtentiRegistrati Utente1 = new UtentiRegistrati();
    Utente1.setNome("UtenteUno");
    Utente1.setCognome("UtenteUno");
    Utente1.setUrlImgPro("immagini/iconautente.jpg");
    Utente1.setFrase("Frase utente1");
    Utente1.setData("02/10/1985");
    Utente1.setPassword("pswutente1");
    Utente1.setId(1);
    Utente1.setPotereUtente(UtentiRegistrati.Type.USER);
    
    UtentiRegistrati Utente2 = new UtentiRegistrati();
    Utente2.setNome("UtenteDue");
    Utente2.setCognome("UtenteDue");
    Utente2.setUrlImgPro("immagini/iconautente.jpg");
    Utente2.setFrase("Frase utente2");
    Utente2.setData("02/10/1985");
    Utente2.setPassword("pswutente2");
    Utente2.setId(2);
    Utente2.setPotereUtente(UtentiRegistrati.Type.USER);
    
    UtentiRegistrati Utente3 = new UtentiRegistrati();
    Utente3.setNome("UtenteTre");
    Utente3.setCognome("UtenteTre");
    Utente3.setUrlImgPro("immagini/iconautente.jpg");
    Utente3.setFrase("Frase utente3");
    Utente3.setData("02/10/1985");
    Utente3.setPassword("pswutente3");
    Utente3.setId(3);
    Utente3.setPotereUtente(UtentiRegistrati.Type.USER);
    
    UtentiRegistrati incompleto = new UtentiRegistrati();
    incompleto.setNome("Incompleto");    
    incompleto.setCognome("Incompleto");
    incompleto.setUrlImgPro("immagini/iconautente.jpg");
    incompleto.setFrase(null);
    incompleto.setData("02/10/1985");
    incompleto.setPassword("pswincompleto");
    incompleto.setId(4);
    incompleto.setPotereUtente(UtentiRegistrati.Type.USER);
    
    listaUtenti.add(Utente1);
    listaUtenti.add(Utente2);
    listaUtenti.add(Utente3);
    listaUtenti.add(incompleto);
    */
    }
    
    public UtentiRegistrati getUserById(int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String query = "SELECT * FROM Users WHERE Users.Id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();          
            if (set.next()) {
                UtentiRegistrati utente_ = new UtentiRegistrati();               
                utente_.setId(set.getInt("Id"));
                utente_.setNome(set.getString("nome"));
                utente_.setCognome(set.getString("cognome"));
                utente_.setPassword(set.getString("password"));
                utente_.setUrlImgPro(set.getString("UrlImgPro"));
                utente_.setData(set.getString("datan"));
                utente_.setPotereUtente(utentiTypeFromString(set.getString("PotereUtente")));
                utente_.setFrase(set.getString("frase"));
                stmt.close();
                conn.close();
                return utente_;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private UtentiRegistrati.Type utentiTypeFromString(String tipo){
        
        if(tipo.equals("SUPERUSER"))
            return UtentiRegistrati.Type.SUPERUSER;
        else 
            return UtentiRegistrati.Type.USER;
    }
    
    public UtentiRegistrati getUserByName(String n)
    {
           try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");           
            String sql = "SELECT * FROM Users WHERE Users.nome = ?";           
            PreparedStatement stmt = conn.prepareStatement(sql);           
            stmt.setString(1, n);          
            ResultSet set = stmt.executeQuery();          
            if (set.next()) {
                UtentiRegistrati utente_ = new UtentiRegistrati();
                utente_.setId(set.getInt("Id"));
                utente_.setNome(set.getString("nome"));
                utente_.setCognome(set.getString("cognome"));
                utente_.setPassword(set.getString("password"));
                utente_.setUrlImgPro(set.getString("UrlImgPro"));
                utente_.setData(set.getString("datan"));
                utente_.setPotereUtente(utentiTypeFromString(set.getString("PotereUtente")));
                utente_.setFrase(set.getString("frase"));
                stmt.close();
                conn.close();
                return utente_;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List getUserList()
    {
           List<UtentiRegistrati> l = new ArrayList<>();
        try {           
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");          
            String sql = "SELECT Users.Id, Users.nome, Users.UrlImgPro FROM Users ";            
            PreparedStatement stmt = conn.prepareStatement(sql);           
            ResultSet set = stmt.executeQuery();            
            while (set.next()) {
                UtentiRegistrati utente_ = new UtentiRegistrati();
               
                utente_.setId(set.getInt("Id"));
                utente_.setNome(set.getString("nome"));
                utente_.setUrlImgPro(set.getString("UrlImgPro"));
                l.add(utente_);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
    
   public void aggiornaNome(String nome, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET nome = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void aggiornaCognome(String cognome, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET cognome = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cognome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void aggiornaFrase(String frase, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET frase = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, frase);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void aggiornaData(String data, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET datan = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, data);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void aggiornaUrlImgPro(String url, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET UrlImgPro = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, url);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void aggiornaPassword(String psw, int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "UPDATE Users SET password = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, psw);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
  private boolean Elimina(UtentiRegistrati utente_)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Users_Groups WHERE Users_Groups.Id_u = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utente_.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Users_Groups WHERE Users_Groups.Id_g IN (SELECT Groups.Id FROM Groups WHERE creatore = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utente_.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Groups WHERE Groups.creatore = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utente_.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
            String sql = "DELETE FROM Users_Users WHERE Users_Users.Id_ua = ? OR Users_Users.Id_ub = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utente_.getId());
            stmt.setInt(2, utente_.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
    
      public boolean eliminaUtente(UtentiRegistrati utente_)
    {
        if(Elimina(utente_) == true)
        {
            try {
                Connection conn = DriverManager.getConnection(connectionString, "mirkogiacalone", "Mirko123");
                String sql = "DELETE FROM Users WHERE Users.Id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, utente_.getId());
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    } 
      
public boolean friends(int Id_1, int Id_2)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString,  "mirkogiacalone", "Mirko123");
            String sql = 
                      " SELECT * FROM Users_Users WHERE Users_Users.Id_ua = ? AND Users_Users.Id_ub = ? OR Users_Users.Id_ua = ? AND Users_Users.Id_ub = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id_1);
            stmt.setInt(2, Id_2);
            stmt.setInt(3, Id_2);
            stmt.setInt(4, Id_1);
            ResultSet set = stmt.executeQuery();
            boolean controllo = false;
            if(set.next())
                controllo = true;
            stmt.close();
            conn.close();
            return controllo;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddFriend(int Id_1, int Id_2)
    {
        try {           
            Connection conn = DriverManager.getConnection(connectionString,  "mirkogiacalone", "Mirko123");
            String sql = "INSERT INTO Users_Users(Id, Id_ua, Id_ub) VALUES (default, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id_1);
            stmt.setInt(2, Id_2);
            int set = stmt.executeUpdate();
            if(set == 1){
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
    
}
