package com.zazilweb.model.DAO;

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

public class MunicipioDao {

    private Connection con;
    private List<Municipio> lista;
    private boolean resp;

    public MunicipioDao(){
        this.con = new MysqlConector().connect();
        this.lista = new ArrayList<>();
        this.resp = false;
    }


    public List findAll(int id) {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from municipios where estado = ?"
                    );
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Municipio m = new Municipio();
                m.setId(res.getInt("id"));
                m.setNombre(res.getString("nombre"));

                Estado estado = new Estado();
                estado.setId(res.getInt("estado"));

                Pais pais = new Pais();
                pais.setId(1);
                pais.setNombre("México");
                estado.setPais(pais);

                lista.add(m);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}