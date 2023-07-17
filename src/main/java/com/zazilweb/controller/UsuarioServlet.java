package com.zazilweb.controller;

import com.zazilweb.model.Operacion;
import com.zazilweb.model.Rol;
import com.zazilweb.model.Usuario;
import com.zazilweb.model.DAO.UsuarioDao;
import com.zazilweb.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UserDao", value = "/login")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass  = req.getParameter("pass");

        UsuarioDao dao = new UsuarioDao();

        req.getSession().removeAttribute("mensaje");
        Usuario usr = (Usuario) dao.findOne(email, pass);

        if(usr.getId()!=0){ //Que si existe un usuario en la BD
            req.getSession().setAttribute("sesion",usr);

            Rol rol = new Rol();
            rol.setNombre_rol(usr.getRol().getNombre_rol());

            req.getSession().setAttribute("tipoSesion", rol.getNombre_rol());

            List<Operacion> operaciones = new ArrayList<>();
            switch (rol.getNombre_rol()){
                case "admin":
                    operaciones.add(
                            new Operacion(
                                    "Usuarios",
                                    "assets/img/userAdmin.png",
                                    "Ver, agregar, actualizar y eliminar <strong>usuarios</strong> del sistema"
                            ));
                    operaciones.add(
                            new Operacion(
                                    "Clientes",
                                    "assets/img/clientAdmin.png",
                                    "Ver, agregar, actualizar y eliminar <strong>clientes</strong> del sistema"
                            ));
                    operaciones.add(
                            new Operacion(
                                    "Distribuidores",
                                    "assets/img/distAdmin.png",
                                    "Ver, agregar, actualizar y eliminar <strong>distribuidores</strong> del sistema"
                            ));
                    operaciones.add(
                            new Operacion(
                                    "Productos",
                                    "assets/img/productAdmin.png",
                                    "Ver, agregar, actualizar y eliminar <strong>productos</strong> del sistema"
                            ));
                    operaciones.add(
                            new Operacion(
                                    "Inventario",
                                    "assets/img/inventoryAdmin.png",
                                    "Administrar el <strong>inventario</strong> del sistema"
                            ));
                    operaciones.add(
                            new Operacion(
                                    "Ventas",
                                    "assets/img/salesAdmin.png",
                                    "Ver, agregar, actualizar y eliminar <strong>ventas</strong> del sistema"
                            ));
                    break;
                default:
                    break;
            }
            req.getSession().setAttribute("operaciones", operaciones);
        }else{
            req.getSession().setAttribute("mensaje","El usuario o la contraseña son incorrectos");
        }

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("sesion");
        resp.sendRedirect("index.jsp");
    }
}
