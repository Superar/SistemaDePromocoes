package br.ufscar.dc.promocoes.beans.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PromocaoFormBean {
    String URLSite, CNPJHotel, preco, dataInicial, dataFinal;

    public String getURLSite() {
        return URLSite;
    }

    public void setURLSite(String URLSite) {
        this.URLSite = URLSite;
    }

    public String getCNPJHotel() {
        return CNPJHotel;
    }

    public void setCNPJHotel(String CNPJHotel) {
        this.CNPJHotel = CNPJHotel;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();

        if(this.URLSite.isEmpty()){
            erros.add("Informe a url do site");
        }
        if (this.CNPJHotel.isEmpty()){
            erros.add("Informe o CNPJ do hotel");
        }

        if(dataInicial.isEmpty()){
            erros.add("Informe a data inicial");
        } else {
            try {
                new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
            }catch (ParseException e){
                erros.add("Data Inicial: Formato de data incompatível (dd/mm/aaaa)");
            }
        }

        if(dataFinal.isEmpty()){
            erros.add("Informe a data final");
        } else {
            try {
                new SimpleDateFormat("dd/MM/yyyy").parse(dataFinal);
            }catch (ParseException e){
                erros.add("Data Final: Formato de data incompatível (dd/mm/aaaa)");
            }
        }

        try {
            Double.parseDouble(this.preco);
        } catch (NumberFormatException e){
            erros.add("O preço deve ser um número válido");
        }

        return erros;
    }
}
