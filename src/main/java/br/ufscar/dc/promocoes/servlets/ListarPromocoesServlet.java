package br.ufscar.dc.promocoes.servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "ListarPromocoesServlet", urlPatterns = {"/ListarPromocoesServlet"})
public class ListarPromocoesServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_role = (String) request.getSession().getAttribute("role");

        if (user_role != null) {

            if (user_role.equals("hotel")) {

            } else if (user_role.equals("site")) {

            }
        }

        request.setAttribute("mensagem", "ERRO 401: Permissão negada");
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
