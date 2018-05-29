package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.dao.SiteDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class NovoSite implements Serializable {
    Site dadoSite;
    SiteDAO siteDAO;
    @Inject Auth auth;

    public NovoSite(){
        dadoSite = new Site();
    }

    public Site getDadoSite() {
        return dadoSite;
    }
    public void setDadoSite(Site dadoSite) {
        this.dadoSite = dadoSite;
    }


    public void cadastrar() throws SQLException, NamingException {
        if(auth.isAdmin()){
            try {
                siteDAO.gravarSite(dadoSite);
                //setar mensagem site cadastrado com sucesso
            }catch (SQLIntegrityConstraintViolationException e) {
                    //setar mensagem site ja cadastrado
                }

        }

    }

}
