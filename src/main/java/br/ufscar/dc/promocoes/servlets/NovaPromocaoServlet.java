package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.beans.forms.PromocaoFormBean;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NovaPromocaoServlet", urlPatterns = {"/NovaPromocaoServlet"})
public class NovaPromocaoServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("formEnviado", true);

        PromocaoFormBean promocaoForm = new PromocaoFormBean();

        try {

            BeanUtils.populate(promocaoForm, request.getParameterMap());

            request.setAttribute("novaPromocao", promocaoForm);

            List<String> erros = promocaoForm.validar();

            if (erros != null) {
                request.setAttribute("erros", erros);
            } else {
                PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);

                try {
                    Promocao novaPromocao = new Promocao();

                    novaPromocao.setPreco(Double.parseDouble(promocaoForm.getPreco()));
                    novaPromocao.setDataInicial(promocaoForm.getDataInicial());
                    novaPromocao.setDataFinal(promocaoForm.getDataFinal());
                    novaPromocao.setURLSite(promocaoForm.getURLSite());
                    novaPromocao.setCNPJHotel(promocaoForm.getCNPJHotel());

                    promocaoDAO.gravarPromocao(novaPromocao);

                    request.removeAttribute("novaPromocao");
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

        request.getRequestDispatcher("promocaoForm.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
