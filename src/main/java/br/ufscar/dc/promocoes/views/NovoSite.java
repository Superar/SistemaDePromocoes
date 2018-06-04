package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.dao.SiteDAO;
import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Named
@SessionScoped
public class NovoSite implements Serializable {

    Site dadoSite;

    @Inject
    SiteDAO siteDAO;

    @Inject
    Auth auth;

    @Inject
    MensagemHandler handler;

    public NovoSite() {
        dadoSite = new Site();
    }

    public Site getDadoSite() {
        return dadoSite;
    }

    public void setDadoSite(Site dadoSite) {
        this.dadoSite = dadoSite;
    }

    public String cadastrar() throws SQLException, NamingException {
        if (auth.isAdmin()) {
            try {
                siteDAO.gravarSite(dadoSite);
                handler.setMensagem(true, "Site cadastrado com sucesso", MensagemHandler.TipoMensagem.TIPO_SUCESSO);
            } catch (SQLIntegrityConstraintViolationException e) {
                handler.setMensagem(true, "URL já cadastrada", MensagemHandler.TipoMensagem.TIPO_ERRO);
            }
            return "siteForm";
        } else {
            handler.setMensagem(true, "<strong>ERRO 401</strong>: Permissão negada", MensagemHandler.TipoMensagem.TIPO_ERRO);
            return "erro";
        }
    }

    public String checkPermission() throws IOException {
        if (!auth.isAdmin()) {
            return "index?faces-redirect=true";
        }
        return null;
    }
}
