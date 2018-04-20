package br.ufscar.dc.promocoes.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promocao {

    private Site site;
    private Hotel hotel;
    private double preco;
    private Date dataInicial, dataFinal;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) throws ParseException {
        try {
            this.dataInicial = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
        } catch (ParseException e) {
            throw e;
        }
    }
    
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) throws ParseException {
        try {
            this.dataFinal = new SimpleDateFormat("dd/MM/yyyy").parse(dataFinal);
        } catch (ParseException e) {
            throw e;
        }
    }
    
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
