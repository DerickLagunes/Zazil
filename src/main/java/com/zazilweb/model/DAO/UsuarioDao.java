package com.zazilweb.model.DAO;

import com.zazilweb.model.Rol;
import com.zazilweb.model.Usuario;
import com.zazilweb.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao{

    public List<Usuario> findAll() {
        List<Usuario> listaUsuario = new ArrayList<>();
        String query = "select * from usuario";
        try(Connection con = DatabaseConnectionManager.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(query)){
                try (ResultSet res = stmt.executeQuery()){
                    while(res.next()){
                        Usuario usr = new Usuario();
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
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }

    public Object findOne(String correo, String contra) {
        Usuario usr = new Usuario();
        String query = "select usuario.*, roles.nombre_rol from usuario join roles on usuario.rol = roles.id " +
                "where correo = ? AND contra = sha2(?,224)";
        try(Connection con = DatabaseConnectionManager.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(query)){
                stmt.setString(1,correo);
                stmt.setString(2,contra);
                try (ResultSet res = stmt.executeQuery()){
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
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }

}
