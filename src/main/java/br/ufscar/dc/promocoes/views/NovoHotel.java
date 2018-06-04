package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.dao.HotelDAO;
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
public class NovoHotel implements Serializable {
    Hotel dadoHotel;

    @Inject
    HotelDAO hotelDAO;

    @Inject
    Auth auth;

    @Inject
    MensagemHandler handler;

    public NovoHotel() {
        dadoHotel = new Hotel();
    }

    public Hotel getDadoHotel() {
        return dadoHotel;
    }

    public void setDadoHotel(Hotel dadoHotel) {
        this.dadoHotel = dadoHotel;
    }

    public Boolean validar_CNPJ(Hotel dadoHotel){
        if(dadoHotel.getCNPJ().length() != 14){
            handler.setMensagem(true, "CNPJ não compatível", MensagemHandler.TipoMensagem.TIPO_ERRO);
            return false;
        } else if(!dadoHotel.getCNPJ().matches("[0-9]+") ){
            handler.setMensagem(true, "O CNPJ só pode conter números", MensagemHandler.TipoMensagem.TIPO_ERRO);
            return false;
        }
        return true;
    }

    public String cadastrar() throws SQLException, NamingException {
        if (auth.isAdmin()) {
            if(validar_CNPJ(dadoHotel)) {
                try {
                    hotelDAO.gravarHotel(dadoHotel);
                    handler.setMensagem(true, "Hotel cadastrado com sucesso", MensagemHandler.TipoMensagem.TIPO_SUCESSO);
                    dadoHotel.limparHotel();
                } catch (SQLIntegrityConstraintViolationException e) {
                    handler.setMensagem(true, "Hotel já cadastrado", MensagemHandler.TipoMensagem.TIPO_ERRO);
                    dadoHotel.limparHotel();
                }

            }
            return "hotelForm";

        } else {
            handler.setMensagem(true, "ERRO 401: Permissão negada", MensagemHandler.TipoMensagem.TIPO_ERRO);
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
