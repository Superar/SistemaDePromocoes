package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.dao.HotelDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ListaHoteis {
    @Inject
    HotelDAO hotelDAO;

    @Inject
    MensagemHandler msg;

    List<Hotel> hoteis;

    String cidadeFiltro = "";

    public String getCidadeFiltro() {
        return cidadeFiltro;
    }

    public void setCidadeFiltro(String cidadeFiltro) {
        this.cidadeFiltro = cidadeFiltro;
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }

    public void setHoteis(List<Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    @PostConstruct
    public void init() {
        try {
            hoteis = hotelDAO.listarTodosHoteis();
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMensagem(true, e.getLocalizedMessage(), MensagemHandler.TipoMensagem.TIPO_ERRO);
        }
    }

    public void aplicarFiltro(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        try {
            hoteis = hotelDAO.listarTodosHoteisPorCidade(cidadeFiltro);
            System.err.println(hoteis.size());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMensagem(true, e.getLocalizedMessage(), MensagemHandler.TipoMensagem.TIPO_ERRO);
        }
    }
}
