package com.zazilweb.model.DAO;

import com.zazilweb.model.Colonia;
import com.zazilweb.model.Estado;
import com.zazilweb.model.Municipio;
import com.zazilweb.model.Pais;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColoniaDao {

    private Connection con;
    private List<Colonia> lista;
    private boolean resp;

    public ColoniaDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }


    public List findAll(int id) {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from colonias where municipio = ?"
                    );
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Colonia c = new Colonia();
                c.setId(res.getInt("id"));
                c.setNombre(res.getString("nombre"));
                lista.add(c);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Colonia findOne(int id) {
        Colonia c = new Colonia();
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select c.id, m.id, e.id, p.id " +
                                    "from colonias as c " +
                                    "join municipios as m " +
                                    "on c.municipio = m.id " +
                                    "join estados as e " +
                                    "on m.estado = e.id " +
                                    "join paises as p " +
                                    "on e.pais = p.id " +
                                    "where c.id = ?"
                    );
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                Municipio m = new Municipio();
                Estado e = new Estado();
                Pais p = new Pais();

                c.setId(res.getInt(1));
                m.setId(res.getInt(2));
                e.setId(res.getInt(3));
                p.setId(res.getInt(4));

                e.setPais(p);
                m.setEstado(e);
                c.setMunicipio(m);

            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
}