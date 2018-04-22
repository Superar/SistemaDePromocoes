package br.ufscar.dc.promocoes.servlets;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.forms.HotelFormBean;
import br.ufscar.dc.promocoes.dao.HotelDAO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "NovoHotelServlet", urlPatterns = {"/NovoHotelServlet"})
public class NovoHotelServlet extends HttpServlet {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user_role = (String) request.getSession().getAttribute("role");

        if(user_role != null && user_role.equals("admin")){
            request.setCharacterEncoding("UTF-8");

            request.setAttribute("formEnviado", true);

            HotelFormBean hotelForm = new HotelFormBean();

            try {
                BeanUtils.populate(hotelForm, request.getParameterMap());

                request.setAttribute("novoHotel", hotelForm);

                List<String> erros = hotelForm.validar();
                request.setAttribute("erros", erros);

                if (erros.isEmpty()) {
                    HotelDAO hotelDAO = new HotelDAO(dataSource);

                    try {
                        Hotel novoHotel = new Hotel();

                        novoHotel.setNome(hotelForm.getNome());
                        novoHotel.setCNPJ(hotelForm.getCnpj());
                        novoHotel.setCidade(hotelForm.getCidade());
                        novoHotel.setSenha(hotelForm.getSenha());

                        hotelDAO.gravarHotel(novoHotel);

                        request.removeAttribute("novoHotel");
                    } catch (SQLIntegrityConstraintViolationException e) {
                        erros.add("Já exite um hotel com o mesmo CNPJ");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensagem", e.getLocalizedMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

            request.getRequestDispatcher("hotelForm.jsp").forward(request, response);
        } else {
            request.setAttribute("mensagem", "<strong>ERRO 401</strong>: Permissão negada. Você deve se autenticar como administrador.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
