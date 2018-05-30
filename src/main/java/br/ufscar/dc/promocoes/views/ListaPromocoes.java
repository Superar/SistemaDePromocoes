package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

@Named
@RequestScoped
public class ListaPromocoes {

    @Inject
    Auth auth;
    @Inject
    PromocaoDAO promocaoDAO;
    @Inject
    MensagemHandler msgHandler;

    List<Promocao> promocoes;

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    @PostConstruct
    public void init() {
        try {
            if (auth.isHotel()) {
                promocoes = promocaoDAO.listarTodasPromocoesFiltro(auth.getId(), "");
            } else if (auth.isSite()) {
                promocoes = promocaoDAO.listarTodasPromocoesFiltro("", auth.getId());
            }
        } catch (SQLException | NamingException e) {
            msgHandler.setMensagem(true, e.getLocalizedMessage(), MensagemHandler.TipoMensagem.TIPO_ERRO);
        }
    }

    public String checkPermission() throws IOException {
        if (!auth.isHotel() & !auth.isSite()) {
            return "index";
        }
        return null;
    }

}
