package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.dao.HotelDAO;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

public class NovoHotelServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hotel novoHotel = new Hotel();
        HotelDAO hotelDAO = new HotelDAO(dataSource);

        try {
            BeanUtils.populate(novoHotel, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(NovoHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            hotelDAO.gravarHotel(novoHotel);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovoHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
