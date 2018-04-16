import br.ufscar.dc.promocoes.beans.Site;
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

@WebServlet(name = "NovoSiteServlet")
public class NovoSiteServlet extends HttpServlet {
    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Site novoSite = new Site();
        SiteDAO siteDAO = new SiteDAO(dataSource);

        try {
            BeanUtils.populate(novoSite, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(NovoSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            siteDAO.gravarSite(novoSite);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovoSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
