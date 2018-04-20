package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.beans.forms.SiteFormBean;
import br.ufscar.dc.promocoes.dao.HotelDAO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NovoSiteServlet")
public class NovoSiteServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        request.setAttribute("formEnviado", true);

        SiteFormBean siteForm = new SiteFormBean();

        try {
            BeanUtils.populate(siteForm, request.getParameterMap());

            request.setAttribute("novoSite", siteForm);

            List<String> erros = siteForm.validar();

            if (erros != null) {
                request.setAttribute("erros", erros);
            } else {
                SiteDAO siteDAO = new SiteDAO(dataSource);

                try {
                    Site novoSite = new Site();

                    novoSite.setNome(siteForm.getNome());
                    novoSite.setSenha(siteForm.getSenha());
                    novoSite.setUrl(siteForm.getUrl());
                    novoSite.setTelefone(siteForm.getTelefone());

                    siteDAO.gravarSite(novoSite);

                    request.removeAttribute("novoSite");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensagem", e.getLocalizedMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

        request.getRequestDispatcher("siteForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
