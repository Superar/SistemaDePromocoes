package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.beans.forms.SiteFormBean;
import br.ufscar.dc.promocoes.dao.SiteDAO;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@WebServlet(name = "NovoSiteServlet")
public class NovoSiteServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user_role = (String) request.getSession().getAttribute("role");

        if(user_role != null && user_role.equals("admin")){
            request.setCharacterEncoding("UTF-8");

            request.setAttribute("formEnviado", true);

            SiteFormBean siteForm = new SiteFormBean();

            try {
                BeanUtils.populate(siteForm, request.getParameterMap());

                request.setAttribute("novoSite", siteForm);

                List<String> erros = siteForm.validar();
                request.setAttribute("erros", erros);

                if (erros.isEmpty()) {
                    SiteDAO siteDAO = new SiteDAO(dataSource);

                    try {
                        Site novoSite = new Site();

                        novoSite.setNome(siteForm.getNome());
                        novoSite.setSenha(siteForm.getSenha());
                        novoSite.setUrl(siteForm.getUrl());
                        novoSite.setTelefone(siteForm.getTelefone());

                        siteDAO.gravarSite(novoSite);

                        request.removeAttribute("novoSite");
                    } catch (SQLIntegrityConstraintViolationException e) {
                        erros.add("Já existe um site com essa url");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensagem", e.getLocalizedMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

            request.getRequestDispatcher("siteForm.jsp").forward(request, response);
        } else {
            request.setAttribute("mensagem", "<strong>ERRO 401</strong>: Permissão negada.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
