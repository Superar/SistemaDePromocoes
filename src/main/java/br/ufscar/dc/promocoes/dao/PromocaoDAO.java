package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Promocao;
import br.ufscar.dc.promocoes.beans.Site;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromocaoDAO {

    private final static String CRIAR_PROMOCAO_SQL = "INSERT INTO PROMOCOES.PROMOCAO"
            + " (URLSITE, CNPJHOTEL, PRECO, DATAINICIAL, DATAFINAL)"
            + " VALUES (?, ?, ?, ?, ?)";

    private final static String LISTAR_TODAS_PROMOCOES_POR_HOTEL_SQL = "SELECT"
            + " P.URLSITE, P.CNPJHOTEL, P.PRECO, P.DATAINICIAL, P.DATAFINAL,"
            + " S.URL, S.NOME AS SITENOME, S.SENHA AS SITESENHA, S.TELEFONE,"
            + " H.CNPJ, H.NOME AS HOTELNOME, H.SENHA AS HOTELSENHA, H.CIDADE"
            + " FROM PROMOCOES.PROMOCAO AS P"
            + " INNER JOIN PROMOCOES.SITE AS S ON S.URL = P.URLSITE"
            + " INNER JOIN PROMOCOES.HOTEL AS H ON H.CNPJ = P.CNPJHOTEL"
            + " WHERE CNPJHOTEL = ?";

    private final static String LISTAR_TODAS_PROMOCOES_POR_SITE_SQL = "SELECT"
            + " P.URLSITE, P.CNPJHOTEL, P.PRECO, P.DATAINICIAL, P.DATAFINAL,"
            + " S.URL, S.NOME AS SITENOME, S.SENHA AS SITESENHA, S.TELEFONE,"
            + " H.CNPJ, H.NOME AS HOTELNOME, H.SENHA AS HOTELSENHA, H.CIDADE"
            + " FROM PROMOCOES.PROMOCAO AS P"
            + " INNER JOIN PROMOCOES.SITE AS S ON S.URL = P.URLSITE"
            + " INNER JOIN PROMOCOES.HOTEL AS H ON H.CNPJ = P.CNPJHOTEL"
            + " WHERE P.URLSITE = ?";
    
    private final static String LISTAR_TODAS_PROMOCOES_FILTRO = LISTAR_TODAS_PROMOCOES_POR_HOTEL_SQL
            + " UNION "
            + LISTAR_TODAS_PROMOCOES_POR_SITE_SQL;

    DataSource dataSource;

    public PromocaoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Promocao gravarPromocao(Promocao promocao) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PROMOCAO_SQL);) {
            ps.setString(1, promocao.getSite().getUrl());
            ps.setString(2, promocao.getHotel().getCNPJ());
            ps.setDouble(3, promocao.getPreco());
            ps.setDate(4, new Date(promocao.getDataInicial().getTime()));
            ps.setDate(5, new Date(promocao.getDataFinal().getTime()));
            ps.execute();
        }
        return promocao;
    }

    public List<Promocao> listarTodasPromocoesFiltro(String hotelCNPJ, String siteURL) throws SQLException, NamingException {
        List<Promocao> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_TODAS_PROMOCOES_FILTRO)) {

            ps.setString(1, hotelCNPJ);
            ps.setString(2, siteURL);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Promocao promocao = new Promocao();
                    Site site = new Site();
                    Hotel hotel = new Hotel();

                    site.setNome(rs.getString("SITENOME"));
                    site.setSenha(rs.getString("SITESENHA"));
                    site.setTelefone(rs.getString("TELEFONE"));
                    site.setUrl(rs.getString("URL"));

                    hotel.setCNPJ(rs.getString("CNPJ"));
                    hotel.setCidade(rs.getString("CIDADE"));
                    hotel.setNome(rs.getString("HOTELNOME"));
                    hotel.setSenha(rs.getString("HOTELSENHA"));

                    promocao.setSite(site);
                    promocao.setHotel(hotel);
                    promocao.setPreco(rs.getDouble("PRECO"));
                    promocao.setDataInicial(rs.getTimestamp("DATAINICIAL"));
                    promocao.setDataFinal(rs.getTimestamp("DATAFINAL"));

                    ret.add(promocao);
                }
            }
        }
        return ret;
    }
}
