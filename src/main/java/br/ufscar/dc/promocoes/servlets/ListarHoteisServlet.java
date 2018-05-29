package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.dao.HotelDAO;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarHoteisServlet", urlPatterns = {"/ListarHoteisServlet"})
public class ListarHoteisServlet extends HttpServlet {
    @Inject
    HotelDAO hotelDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cidade = request.getParameter("cidade");
        List<Hotel> todosHoteis = null;

        try {
            if (cidade == null) {
                todosHoteis = hotelDAO.listarTodosHoteis();
            }else {
                todosHoteis = hotelDAO.listarTodosHoteisPorCidade(cidade);
            }
            request.setAttribute("listaHoteis", todosHoteis);
            request.getRequestDispatcher("listaHoteis.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
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
