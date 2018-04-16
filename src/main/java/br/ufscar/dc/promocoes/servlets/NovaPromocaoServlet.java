package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NovaPromocaoServlet", urlPatterns = {"/NovaPromocaoServlet"})
public class NovaPromocaoServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        Promocao novaPromocao = new Promocao();
        PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);

        try {

            BeanUtils.populate(novaPromocao, request.getParameterMap());

            System.out.println(novaPromocao.getCNPJHotel());

        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(NovoHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            promocaoDAO.gravarPromocao(novaPromocao);
//        } catch (SQLException | NamingException ex) {
//            Logger.getLogger(NovoHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
