package com.zazilweb.model.DAO;

import com.zazilweb.model.Rol;
import com.zazilweb.model.Usuario;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements DaoRepository {

    private Connection con;
    private List<Usuario> listaUsuario;
    private Usuario usr;
    private boolean resp;

    public UsuarioDao(){
        this.con = new MysqlConector().connect();
        this.listaUsuario = new ArrayList<>();
        this.usr = new Usuario();
        this.resp = false;
    }

    @Override
    public List findAll() {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from usuario");
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                usr.setId(res.getInt("id"));
                usr.setNombre(res.getString("nombre"));
                usr.setNombre2(res.getString("nombre2"));
                usr.setApellido(res.getString("apellido"));
                usr.setApellido2(res.getString("apellido2"));
                usr.setSexo(res.getInt("sexo"));
                usr.setFecha_nacimiento(res.getDate("fecha_nacimiento"));
                usr.setCorreo(res.getString("correo"));
                usr.setContra(res.getString("contra"));
                usr.setCodigo(res.getString("codigo"));
                usr.setRol((Rol) (res.getObject("rol")));
                usr.setUltimo_login(res.getDate("ultimo_login"));
                listaUsuario.add(usr);
            }
            res.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    public Object findOne(String correo, String contra) {
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select usuario.*, roles.nombre_rol from usuario join roles on usuario.rol = roles.id " +
                            "where correo = ? AND contra = sha2(?,224)");
            stmt.setString(1,correo);
            stmt.setString(2,contra);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setId(res.getInt("id"));
                usr.setNombre(res.getString("nombre"));
                usr.setNombre2(res.getString("nombre2"));
                usr.setApellido(res.getString("apellido"));
                usr.setApellido2(res.getString("apellido2"));
                usr.setSexo(res.getInt("sexo"));
                usr.setFecha_nacimiento(res.getDate("fecha_nacimiento"));
                usr.setCorreo(res.getString("correo"));
                usr.setContra(res.getString("contra"));
                usr.setCodigo(res.getString("codigo"));
                Rol rol = new Rol();
                rol.setNombre_rol(res.getString("nombre_rol"));
                usr.setRol(rol);
                usr.setUltimo_login(res.getDate("ultimo_login"));
            }
            res.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }

    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }
}
