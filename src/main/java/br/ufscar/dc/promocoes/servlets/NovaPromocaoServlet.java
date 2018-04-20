package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.beans.forms.PromocaoFormBean;
import br.ufscar.dc.promocoes.dao.HotelDAO;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;
import br.ufscar.dc.promocoes.dao.SiteDAO;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NovaPromocaoServlet", urlPatterns = {"/NovaPromocaoServlet"})
public class NovaPromocaoServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("formEnviado", true);

        PromocaoFormBean promocaoForm = new PromocaoFormBean();

        try {

            BeanUtils.populate(promocaoForm, request.getParameterMap());

            request.setAttribute("novaPromocao", promocaoForm);

            List<String> erros = promocaoForm.validar();
            request.setAttribute("erros", erros);

            if (erros.isEmpty()) {
                PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);

                try {

                    Promocao novaPromocao = new Promocao();


                    Site site = new SiteDAO(dataSource).recuperarSite(promocaoForm.getURLSite());
                    if (site == null) {
                        erros.add("O site informado não existe");
                    }

                    Hotel hotel = new HotelDAO(dataSource).recuperarHotel(promocaoForm.getCNPJHotel());
                    if (hotel == null) {
                        erros.add("O CNPJ do hotel informado não existe");
                    }

                    if (erros.isEmpty()) {
                        novaPromocao.setSite(site);
                        novaPromocao.setHotel(hotel);
                        novaPromocao.setPreco(Double.parseDouble(promocaoForm.getPreco()));
                        novaPromocao.setDataInicial(promocaoForm.getDataInicial());
                        novaPromocao.setDataFinal(promocaoForm.getDataFinal());

                        promocaoDAO.gravarPromocao(novaPromocao);

                        request.removeAttribute("novaPromocao");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    erros.add("Já existe uma promoção desse hotel no site informado");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

        request.getRequestDispatcher("promocaoForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
