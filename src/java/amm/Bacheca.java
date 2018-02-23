/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mirko
 */
@WebServlet(name = "Bacheca", urlPatterns = {"/Bacheca"})
public class Bacheca extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  
        HttpSession session = request.getSession();
        Object loggato = session.getAttribute("loggato");
              
        
        if(loggato != null)
        {
            boolean controllo = (boolean)loggato;          
            if(controllo)
            {
                loggato = session.getAttribute("user"); 
                request.setAttribute("NoAccesso", false);
                
                Object visualizzaBacheca = request.getParameter("bacheca_di");
                Object visualizzaGruppo = request.getParameter("pagina_gruppo");
                UtentiRegistrati user_;
                Gruppi group_;
                
               if(visualizzaGruppo != null)
               {                            
                   if(request.getParameter("eliminag") == null){
                        int k;
                        k = Integer.parseInt(visualizzaGruppo.toString());
                        group_ = GruppiFactory.getInstance().getGroupById(k);
                        if(group_ != null)
                        {
                            request.setAttribute("AttributoAut", group_);
                            if(((UtentiRegistrati)loggato).getId() == group_.getCreatore().getId())
                            {
                                request.setAttribute("iscritto",true);
                                List<Post> post_ = PostFactory.getInstance().getPostByGroup(group_);
                                if(post_ != null)
                                    request.setAttribute("post", post_);
                            }
                            else if(GruppiFactory.getInstance().joined((UtentiRegistrati)loggato,group_))
                            {
                                request.setAttribute("iscritto",true);                               
                                List<Post> post_ = PostFactory.getInstance().getPostByGroup(group_);
                                if(post_ != null)
                                    request.setAttribute("post", post_);
                            }
                            else
                            {
                                List<Post> post_ = new ArrayList<>();
                                request.setAttribute("iscritto",false);
                            }                       
                         }
                   }
                 
                 else
                 {                    
                     // Eiminazione Gruppo   
                        int var = Integer.parseInt(request.getParameter("eliminag"));
                        int id = Integer.parseInt(visualizzaGruppo.toString());
                        
                        if(var == id)
                        {
                            if(GruppiFactory.getInstance().eliminaGruppo(id) == true)                       
                            {
                                request.setAttribute("EliminaG",1);
                                List<Gruppi> lg = GruppiFactory.getInstance().getGroupList();
                                session.setAttribute("gruppi", lg);
                                visualizzaGruppo = visualizzaBacheca= null;
                                session = request.getSession();
                                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                                
                            }
                            else
                                request.setAttribute("EliminaG",0);
                         }                       
                 }
               } 
                
                
                
                
                
                //-------------------------------------Bacheca
                else if(visualizzaBacheca != null)
                {                 
                    request.setAttribute("AttributoUtn",true);
                    int k;
                    k = Integer.parseInt(visualizzaBacheca.toString());
                    user_ = UtentiRegistratiFactory.getInstance().getUserById(k);
                    request.setAttribute("AttributoAut", user_);
                    if(((UtentiRegistrati)loggato).getId() == user_.getId())
                    {
                        request.setAttribute("friend",true);
                        List<Post> post_ = PostFactory.getInstance().getPostByDest(user_);
                        if(post_ != null)
                            request.setAttribute("post",post_);
                    }
                    else if(UtentiRegistratiFactory.getInstance().friends(((UtentiRegistrati)loggato).getId(),user_.getId()))
                    {
                        request.setAttribute("friend",true);
                        List<Post> post_ = PostFactory.getInstance().getPostByDest(user_);
                        if(post_ != null)
                            request.setAttribute("post",post_);
                    }
                    else
                    {
                        List<Post> post_ = new ArrayList<>();
                        request.setAttribute("friend",false);
                    }
                }
                else
                //utente corrente (bacheca)
                {
                    request.setAttribute("AttributoUtn",true);
                    user_ = (UtentiRegistrati)loggato;
                    request.setAttribute("AttributoAut", user_);
                    List<Post> post_ = PostFactory.getInstance().getPostByDest(user_);
                    if(post_ != null)
                        request.setAttribute("post", post_);
                     request.setAttribute("friend",true);
                }                
               
                if(request.getParameter("aggiungi") != null)
                {
                    if(request.getParameter("aggiungi").equals("Aggiungi Amico"))
                    {
                        if(UtentiRegistratiFactory.getInstance().AddFriend(((UtentiRegistrati)request.getAttribute("AttributoAut")).getId(),((UtentiRegistrati)session.getAttribute("user")).getId()) == true)                           
                        {
                            request.setAttribute("ok_aggiungi",true);
                            request.setAttribute("friend",true);
                            request.setAttribute("post",PostFactory.getInstance().getPostByDest(UtentiRegistratiFactory.getInstance().getUserById(((UtentiRegistrati)request.getAttribute("AttributoAut")).getId())));
                        }
                        else
                            request.setAttribute("ok_aggiungi",false);
                    }
                }
                
                if(request.getParameter("iscriviti") != null)
                {
                    if(request.getParameter("iscriviti").equals("Iscriviti al gruppo"))
                    {
                        int Id_1 = ((UtentiRegistrati)session.getAttribute("user")).getId();
                        int Id_2 = ((Gruppi)request.getAttribute("AttributoAut")).getId();
                        if(GruppiFactory.getInstance().iscrizione(Id_1, Id_2) == true)                           
                        {
                            request.setAttribute("registrati",true);
                            request.setAttribute("iscritto",true);
                            request.setAttribute("post",PostFactory.getInstance().getPostByGroup(GruppiFactory.getInstance().getGroupById(Id_2)));
                        }
                        else
                            request.setAttribute("registrati",false);
                    }
                }
                
              //Elimina post
              
                if(request.getParameter("elimina_post") != null)
                {
                    int id = Integer.parseInt(request.getParameter("elimina_post"));
                   
                    Post appoggio1 = PostFactory.getInstance().getPostById(id);
                    int appoggio2 = 0, appoggio3 = 0;
                    if(appoggio1.getAutore() != null)
                        appoggio2 = appoggio1.getAutore().getId();
                    if(appoggio1.getGruppo() != null)
                        appoggio3 = appoggio1.getGruppo().getId();

                    if(PostFactory.getInstance().eliminaPost(id) == true)
                    {
                        request.setAttribute("elimina",true);
                        List<Post> p = null;
                        if(request.getParameter("pagina_gruppo") == null)
                            p = PostFactory.getInstance().getPostByDest((UtentiRegistrati)request.getAttribute("AttributoAut"));
                        else p = PostFactory.getInstance().getPostByGroup((Gruppi)request.getAttribute("AttributoAut"));
                        
                        if(p != null) request.setAttribute("post", p);
                    }
                   else
                        request.setAttribute("elimina",false);
                }
                
                
                
                if(request.getParameter("FrasePers") != null || request.getParameter("tipo") != null || request.getParameter("allegato") != null)
                {
                    String testo = request.getParameter("FrasePers");
                    String radio = request.getParameter("tipo");
                    String allegato = request.getParameter("link");                   
                    Post.Type tipo = null;
                    if(radio != null)
                    {
                        if(radio.equals("indirizzo_url"))
                        {                            
                            if(allegato == null)
                            {
                                request.setAttribute("erroretipo", true);
                            }
                                                        
                            else
                            {
                                if (!(allegato.equals("")))
                                {
                                    request.setAttribute("attachment", 2);
                                    tipo = Post.Type.URL;
                                    request.setAttribute("erroretipo", false);
                                    request.setAttribute("inserisci_post", true);
                                }
                                else
                                    request.setAttribute("erroretipo", true);
                                    
                            }

                        }                                                                                                                                            
                        
                        else if(radio.equals("immagine"))
                        {                            
                            if(allegato != null)
                            {                              
                                if (!(allegato.equals("")))
                                {
                                    request.setAttribute("attachment",1);
                                    tipo = Post.Type.IMAGE;
                                    request.setAttribute("erroretipo", false);
                                    request.setAttribute("inserisci_post", true);
                                }
                                else
                                    request.setAttribute("erroretipo", true);
                                    
                            }
                            else
                            {
                                request.setAttribute("erroretipo", true);
                            }
                        }                                                                                                                                          
                    }                    
                  
                    else if(!testo.equals(""))
                    {
                        if(!allegato.equals(""))
                        {
                            request.setAttribute("erroretipo", true);
                            request.setAttribute("inserisci_post", false);
                        }
                        else
                        {
                            request.setAttribute("inserisci_post", true);
                            request.setAttribute("erroretipo", false);
                            tipo = Post.Type.TEXT;
                        }
                    }
                    else if(!allegato.equals(""))
                    {
                        request.setAttribute("erroretipo", true);
                        request.setAttribute("inserisci_post", false);
                    }                    
                    if(request.getAttribute("erroretipo") != null)                       
                    {
                        if(!(boolean)request.getAttribute("erroretipo"))
                        {
                            request.setAttribute("inserisci_post", true);                            
                            Post nuovoPost = new Post();
                            nuovoPost.setAutore((UtentiRegistrati)session.getAttribute("user"));
                            if(visualizzaBacheca != null && Integer.parseInt(visualizzaBacheca.toString()) != ((UtentiRegistrati)session.getAttribute("user")).getId())
                                nuovoPost.setDestinatario(UtentiRegistratiFactory.getInstance().getUserById(Integer.parseInt(visualizzaBacheca.toString())));
                            else if(visualizzaGruppo == null)
                                nuovoPost.setDestinatario(null);
                            else 
                                nuovoPost.setGruppo((Gruppi)request.getAttribute("AttributoAut"));
                                nuovoPost.setTipo(tipo);
                            if(!testo.equals(""))
                                nuovoPost.setContent(request.getParameter("FrasePers"));
                            if(!allegato.equals(""))
                                nuovoPost.setAllegato(request.getParameter("link"));
                            request.setAttribute("n",nuovoPost);
                            PostFactory.getInstance().addNewPost(nuovoPost);
                        }
                    }
                }                               
                              
                if(request.getParameter("conferma_inserimento") != null)
                {
                    if((request.getParameter("conferma_inserimento").equals("true")))
                        request.setAttribute("conferma_inserimento", true);
                }
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
            
            else
            {
                request.setAttribute("NoAccesso",true);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
        }
     
        else
        {
            request.setAttribute("NoAccesso",true);
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
        }

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
