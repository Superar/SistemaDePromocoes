package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListarPromocoesServlet", urlPatterns = {"/ListarPromocoesServlet"})
public class ListarPromocoesServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_role = (String) request.getSession().getAttribute("role");

        PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);

//        try{
//            List<Promocao> promocoes = promocaoDAO.listarPromocoes();
//        }

        List<Promocao> promocoes = new ArrayList<>();
        Promocao p1 = new Promocao();
//        p1.setURLSite("site 1");
//        p1.setCNPJHotel("1111111111111");
//        p1.setPreco(Double.parseDouble("7.50"));
//        p1.setDataInicial("10/02/2018");
//        p1.setDataFinal("25/11/2018");
        promocoes.add(p1);
        promocoes.add(p1);


        request.setAttribute("listaPromocoes", promocoes);


        if (user_role != null) {

            if (user_role.equals("hotel")) {

            } else if (user_role.equals("site")) {

            }
        }

        request.setAttribute("mensagem", "<strong>ERRO 401</strong>: Permissão negada");
        request.getRequestDispatcher("erro.jsp").forward(request, response);
//        request.getRequestDispatcher("listaPromocoes.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
