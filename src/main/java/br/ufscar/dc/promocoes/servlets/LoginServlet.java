package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.dao.AdministradorDAO;
import br.ufscar.dc.promocoes.dao.HotelDAO;
import br.ufscar.dc.promocoes.dao.SiteDAO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdministradorDAO usuarioDAO = new AdministradorDAO(dataSource);
        HotelDAO hotelDAO = new HotelDAO(dataSource);
        SiteDAO siteDAO = new SiteDAO(dataSource);

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String role = request.getParameter("role");


        try {
            if (role.equals ("admin")){
                if(usuarioDAO.recuperarAdministrador(usuario) == null){
                    request.setAttribute("erro", "usuario nao existe");
                    request.getRequestDispatcher("loginForm.jsp").forward(request, response);
                }
                else if (usuarioDAO.recuperarAdministrador(usuario).getSenha().equals(senha)){
                    request.getSession().setAttribute("role","admin");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("erro", "senha invalida");
                    request.getRequestDispatcher("loginForm.jsp").forward(request, response);
                }
            }
            /*else if (role == "site"){
                if (siteDAO.recuperarSite(usuario).getSenha().equals(senha)){
                    request.getSession().setAttribute("role","site");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }*/
            /*else if (role == "hotel"){
                if (hotelDAO.recuperarHotel(usuario).getSenha().equals(senha)){
                    request.getSession().setAttribute("role","hotel");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }*/


        } catch (SQLException | NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       // request.getRequestDispatcher("erro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
