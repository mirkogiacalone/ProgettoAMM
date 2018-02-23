/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mirko
 */

public class Profilo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        Object lg = session.getAttribute("loggato");
        
        if(lg == null){            
            request.setAttribute("NoAccesso",true);
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
        }
        
        if(lg != null)
        {
            boolean check = (boolean)lg;
            
            if(check == true)
            {
                UtentiRegistrati utente_ = (UtentiRegistrati)session.getAttribute("user");
                request.setAttribute("NoAccesso",false);
                
                if(request.getParameter("cancella") != null)
                {
                 if(request.getParameter("cancella").equals("Cancella profilo"))
                  if(PostFactory.getInstance().eliminaProfiloByUtente(utente_) == true)
                    if(UtentiRegistratiFactory.getInstance().eliminaUtente(utente_) == true)
                     {
                      session.invalidate();
                      session = request.getSession();
                      request.getRequestDispatcher("descrizione.html").forward(request, response);
                     }
                }
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String ImmagineProfilo = request.getParameter("ImmagineProfilo");
                String FrasePers = request.getParameter("FrasePers");
                String dataDiNascita = request.getParameter("dataDiNascita");                
                String password = request.getParameter("password");
                String confermaPsw = request.getParameter("confermaPsw");
               
                if(nome != null){if(!nome.equals("")){
                        UtentiRegistratiFactory.getInstance().aggiornaNome(nome, utente_.getId());
                        utente_.setNome(nome);
                        request.setAttribute("datierrati", false);
                        request.setAttribute("nome", true);
                    }
                }
                
                if(cognome != null){if(!cognome.equals("")){
                        UtentiRegistratiFactory.getInstance().aggiornaCognome(cognome, utente_.getId());
                        utente_.setCognome(cognome);
                        request.setAttribute("datierrati", false);
                        request.setAttribute("cognome", true);
                    }
                }
                
                if(ImmagineProfilo != null){ if(!ImmagineProfilo.equals("")){
                        UtentiRegistratiFactory.getInstance().aggiornaUrlImgPro(ImmagineProfilo, utente_.getId());
                        utente_.setUrlImgPro(ImmagineProfilo);
                        request.setAttribute("datierrati", false);
                        request.setAttribute("ImmagineProfilo", true);
                    }
                } 
                                
                if(FrasePers != null){if(!FrasePers.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().aggiornaFrase(FrasePers, utente_.getId());
                        utente_.setFrase(FrasePers);
                        request.setAttribute("datierrati", false);
                        request.setAttribute("FrasePers", true);
                    }
                }
                

                
                if(dataDiNascita != null){if(!dataDiNascita.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().aggiornaData(dataDiNascita, utente_.getId());
                        utente_.setData(dataDiNascita);
                        request.setAttribute("datierrati", false);
                        request.setAttribute("dataDiNascita", true);
                    }
                }

                if(password != null){if(confermaPsw != null){
                        if(confermaPsw.equals(password)){if (!(password.equals(""))){
                                UtentiRegistratiFactory.getInstance().aggiornaPassword(password, utente_.getId());
                                utente_.setPassword(password);
                                request.setAttribute("datierrati", false);
                            }
                        }
                        else request.setAttribute("datierrati", true);
                    }
                }
                else if (confermaPsw != null)
                request.setAttribute("datierrati", true);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
            
            if(check == false)
            {
                request.setAttribute("NoAccesso",true);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
        }     
    }
}
   

