package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.dao.AdministradorDAO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdministradorDAO usuarioDAO = new AdministradorDAO(dataSource);

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if(usuarioDAO.recuperarAdministrador(usuario).getSenha() == senha){
            request.getSession().setAttribute("logado",true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else{
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
