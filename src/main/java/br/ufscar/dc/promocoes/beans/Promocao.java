package br.ufscar.dc.promocoes.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promocao {

    private int id;
    private String URLSite, CNPJHotel;
    private double preco;
    private Date dataInicial, dataFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        try {
            this.dataInicial = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
        } catch (ParseException e){
            e.printStackTrace();
//            TODO: lidar com o erro
        }
    }


    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        try {
            this.dataFinal = new SimpleDateFormat("dd/MM/yyyy").parse(dataFinal);
        } catch (ParseException e){
            e.printStackTrace();
//            TODO: lidar com o erro
        }
    }

}
