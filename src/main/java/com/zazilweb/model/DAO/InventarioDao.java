package com.zazilweb.model.DAO;

import com.zazilweb.model.Inventario;
import com.zazilweb.model.Producto;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDao {

    public List<Inventario> findAll() {
        List<Inventario> lista = new ArrayList<>();
        String query = "SELECT * FROM inventario i join producto p on p.id = i.producto join distribuidor d on d.id = p.distribuidor join categoria c on c.id = p.categoria join subcategoria s on s.id = p.subcategoria";
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Producto p = new Producto();
                        p.setId(res.getInt("producto"));



                        Inventario inv = new Inventario();
                        inv.setId(res.getInt("id"));
                        inv.setProducto(p);
                        inv.setCantidad(res.getInt("cantidad"));
                        inv.setOrigen(res.getInt("origen"));
                        lista.add(inv);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean insert(Inventario inv) {
        String query = "INSERT INTO inventario(producto, cantidad, origen) VALUES(?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, inv.getProducto().getId());
                stmt.setInt(2, inv.getCantidad());
                stmt.setInt(3, inv.getOrigen());
                int result = stmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Inventario inv) {
        String query = "UPDATE inventario SET producto = ?, cantidad = ?, origen = ? WHERE id = ?";
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, inv.getProducto().getId());
                stmt.setInt(2, inv.getCantidad());
                stmt.setInt(3, inv.getOrigen());
                stmt.setInt(4, inv.getId());
                int result = stmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM inventario WHERE id = ?";
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, id);
                int result = stmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}