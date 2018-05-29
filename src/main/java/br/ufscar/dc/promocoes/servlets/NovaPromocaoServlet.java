package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.beans.forms.PromocaoFormBean;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;
import br.ufscar.dc.promocoes.dao.SiteDAO;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@WebServlet(name = "NovaPromocaoServlet", urlPatterns = {"/NovaPromocaoServlet"})
public class NovaPromocaoServlet extends HttpServlet {

    @Inject
    PromocaoDAO promocaoDAO;

    @Inject
    SiteDAO siteDAO;



    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_role = (String) request.getSession().getAttribute("role");

        if (user_role != null && user_role.equals("hotel")) {
            Hotel hotel = (Hotel)request.getSession().getAttribute("usuario");
            request.setCharacterEncoding("UTF-8");

            request.setAttribute("formEnviado", true);

            PromocaoFormBean promocaoForm = new PromocaoFormBean();


            try {

                BeanUtils.populate(promocaoForm, request.getParameterMap());

                request.setAttribute("novaPromocao", promocaoForm);

                List<String> erros = promocaoForm.validar();
                request.setAttribute("erros", erros);

                if (erros.isEmpty()) {


                    try {

                        Promocao novaPromocao = new Promocao();


                        Site site =siteDAO.recuperarSite(promocaoForm.getURLSite());
                        if (site == null) {
                            erros.add("O site informado não existe");
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
                        erros.add("Já existe uma promoção desse hotel no site informado com a mesma data");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensagem", e.getLocalizedMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

            request.getRequestDispatcher("promocaoForm.jsp").forward(request, response);
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
