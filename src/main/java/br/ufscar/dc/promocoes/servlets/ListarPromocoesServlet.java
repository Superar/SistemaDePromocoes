package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListarPromocoesServlet", urlPatterns = {"/ListarPromocoesServlet"})
public class ListarPromocoesServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_role = (String) request.getSession().getAttribute("role");

        PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);

        try {
            List<Promocao> promocoes = promocaoDAO.listarTodasPromocoesFiltro("11111111111111", "");

            request.setAttribute("listaPromocoes", promocoes);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();

        }

        if (user_role != null) {

            if (user_role.equals("hotel")) {
                request.getRequestDispatcher("listaPromocoes.jsp").forward(request, response);
            } else if (user_role.equals("site")) {
                request.getRequestDispatcher("listaPromocoes.jsp").forward(request, response);
            }
        }

        request.setAttribute("mensagem", "<strong>ERRO 401</strong>: Permissão negada.");
        request.getRequestDispatcher("erro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
