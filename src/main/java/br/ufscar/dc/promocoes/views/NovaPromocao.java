package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.dao.HotelDAO;
import br.ufscar.dc.promocoes.dao.PromocaoDAO;
import br.ufscar.dc.promocoes.dao.SiteDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named
@SessionScoped
public class NovaPromocao implements Serializable {

    @Resource(name = "jdbc/PromocoesDBLocal")
    DataSource dataSource;

    @Inject
    Auth hotel;
    @Inject
    HotelDAO hotelDAO;
    @Inject
    SiteDAO siteDAO;
    @Inject
    PromocaoDAO promocaoDAO;
    @Inject
    MensagemHandler msgHandler;

    Promocao dadosPromocao;

    public NovaPromocao() {
        dadosPromocao = new Promocao();
        dadosPromocao.setHotel(new Hotel());
        dadosPromocao.setSite(new Site());
    }

    public String checkPermission() throws IOException {
        if (!hotel.isHotel()) {
            return "index?faces-redirect=true";
        }
        return null;
    }

    public String gravarPromocao() throws SQLException, NamingException {
        dadosPromocao.getHotel().setCNPJ(hotel.getId());

        try {
            promocaoDAO.gravarPromocao(dadosPromocao);
        } catch (SQLIntegrityConstraintViolationException e) {
            String constraint = e.getSQLState();
            if (constraint.equals("23505")) {
                msgHandler.setMensagem(true, "Promoção já cadastrada", MensagemHandler.TipoMensagem.TIPO_ERRO);
            } else {
                msgHandler.setMensagem(true, "Hotel não cadastrado", MensagemHandler.TipoMensagem.TIPO_ERRO);
            }
            return "promocaoForm";
        }

        msgHandler.setMensagem(true, "Promoção cadastrada com sucesso", MensagemHandler.TipoMensagem.TIPO_SUCESSO);
        return "promocaoForm";
    }

    public Promocao getDadosPromocao() {
        return dadosPromocao;
    }

    public void setDadosPromocao(Promocao dadosPromocao) {
        this.dadosPromocao = dadosPromocao;
    }

}
