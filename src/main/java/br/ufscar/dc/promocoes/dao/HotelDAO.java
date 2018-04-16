package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelDAO {

    private final static String CRIAR_HOTEL_SQL = "INSERT INTO PROMOCOES.HOTEL"
            + " (CNPJ, NOME, SENHA, CIDADE)"
            + " VALUES (?, ?, ?, ?)";

    private final static String LISTAR_TODOS_HOTEIS_SQL = "SELECT"
            + " CNPJ, NOME, SENHA, CIDADE"
            + " FROM PROMOCOES.HOTEL";

    private final static String LISTAR_TODOS_HOTEIS_POR_CIDADE_SQL = "SELECT"
            + " CNPJ, NOME, SENHA, CIDADE"
            + " FROM PROMOCOES.HOTEL"
            + " WHERE CIDADE = ?";

    DataSource dataSource;

    public HotelDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Hotel gravarHotel(Hotel hotel) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_HOTEL_SQL);) {
            ps.setString(1, hotel.getCNPJ());
            ps.setString(2, hotel.getNome());
            ps.setString(3, hotel.getSenha());
            ps.setString(4, hotel.getCidade());
            ps.execute();
        }
        return hotel;
    }

    public List<Hotel> listarTodosHoteis() throws SQLException, NamingException {
        List<Hotel> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_TODOS_HOTEIS_SQL)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setCNPJ(rs.getString("CNPJ"));
                    hotel.setNome(rs.getString("NOME"));
                    hotel.setSenha(rs.getString("SENHA"));
                    hotel.setCidade(rs.getString("CIDADE"));

                    ret.add(hotel);
                }
            }
        }
        return ret;
    }

    public List<Hotel> listarTodosHoteisPorCidade(String cidade) throws SQLException, NamingException {
        List<Hotel> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_TODOS_HOTEIS_POR_CIDADE_SQL)) {

            ps.setString(1, cidade);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setCNPJ(rs.getString("CNPJ"));
                    hotel.setNome(rs.getString("NOME"));
                    hotel.setSenha(rs.getString("SENHA"));
                    hotel.setCidade(rs.getString("CIDADE"));

                    ret.add(hotel);
                }
            }
        }
        return ret;
    }
}
