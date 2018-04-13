package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelDAO {

    private final static String CRIAR_HOTEL_SQL = "INSERT INTO PROMOCOES.HOTEL"
            + " (CNPJ, NOME, SENHA, CIDADE)"
            + " VALUES (?, ?, ?, ?)";

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

}
