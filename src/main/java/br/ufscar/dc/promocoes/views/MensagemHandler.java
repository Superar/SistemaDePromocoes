package br.ufscar.dc.promocoes.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class MensagemHandler implements Serializable {
    public enum TipoMensagem {
        TIPO_SUCESSO("is-success"),
        TIPO_INFO("is-info"),
        TIPO_AVISO("is-warning"),
        TIPO_ERRO("is-danger");

        private final String classeCSS;

        private TipoMensagem(String classeCSS) {
            this.classeCSS = classeCSS;
        }

        @Override
        public String toString() {
            return classeCSS;
        }
    }

    private boolean mostrar;
    private String texto;
    private TipoMensagem tipoMensagem;

    public MensagemHandler() {
        mostrar = false;
        texto = "";
        tipoMensagem = TipoMensagem.TIPO_SUCESSO;
    }

    public void setMensagem(boolean mostrar, String texto, TipoMensagem tipoMensagem) {
        this.mostrar = mostrar;
        this.texto = texto;
        this.tipoMensagem = tipoMensagem;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public String getTexto() {
        return texto;
    }

    public String getTipoMensagem() {
        return tipoMensagem.toString();
    }
}
