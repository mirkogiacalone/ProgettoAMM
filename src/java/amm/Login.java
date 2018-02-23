/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

/**
 *
 * @author Mirko
 */

@WebServlet(name ="Login", urlPatterns = {"/login.html"}, loadOnStartup=0)
public class Login extends HttpServlet {
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
    @Override
    public void init() {
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtentiRegistratiFactory.getInstance().setConnectionString(dbConnection);
        PostFactory.getInstance().setConnectionString(dbConnection);
        GruppiFactory.getInstance().setConnectionString(dbConnection);
    }

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
        
        if(request.getParameter("logout") != null)
        {
            HttpSession sessione_collegamento = request.getSession(false);           
            if(request.getParameter("logout").equals("esci")) sessione_collegamento.invalidate();           
        }
               
        HttpSession sessione_collegamento = request.getSession();
        String utente = request.getParameter("user");
        String password = request.getParameter("password");

        if(utente != null && password != null)
        {
            UtentiRegistrati userL = UtentiRegistratiFactory.getInstance().getUserByName(utente);
            if(userL != null)
            {
                if(!userL.getNome().equals(utente) || !userL.getPassword().equals(password))
                {
                    request.setAttribute("erroreAccesso", true);
                    sessione_collegamento.setAttribute("loggato",false);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                
                else
                {
                    sessione_collegamento.setAttribute("loggato",true);
                    sessione_collegamento.setAttribute("user",userL);
                    sessione_collegamento.setAttribute("AttributoAut",userL);
                    List<UtentiRegistrati> listaUtenti = UtentiRegistratiFactory.getInstance().getUserList();
                    List<Gruppi> listaGruppi = GruppiFactory.getInstance().getGroupList();
                    sessione_collegamento.setAttribute("utenti", listaUtenti);
                    sessione_collegamento.setAttribute("gruppi", listaGruppi);

                    if(userL.getNome() == null || userL.getCognome() == null || userL.getUrlImgPro() == null  || userL.getFrase() == null) response.sendRedirect("profilo.html");
                    
                    else
                    {
                        List<Post> postsL = PostFactory.getInstance().getPostByUser(userL);
                        sessione_collegamento.setAttribute("post", postsL);
                        response.sendRedirect("bacheca.html");
                    }
                }

            }
            else
            {
                request.setAttribute("erroreAccesso", true);
                sessione_collegamento.setAttribute("loggato",false);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    
        
        if(password == null || utente == null)
        request.getRequestDispatcher("login.jsp").forward(request, response);
        
     
     }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
