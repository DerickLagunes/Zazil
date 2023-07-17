package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.Colonia;
import com.zazilweb.model.DAO.ColoniaDao;
import com.zazilweb.model.DAO.DistribuidorDao;
import com.zazilweb.model.DAO.PaisDao;
import com.zazilweb.model.DAO.ProductoDao;
import com.zazilweb.model.Distribuidor;
import com.zazilweb.model.Pais;
import com.zazilweb.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "DistribuidorServlet", value = "/Distribuidor")
@MultipartConfig
public class DistribuidorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre_distribuidor = req.getParameter("nombre_distribuidor");
        String nombre_contacto = req.getParameter("nombre_contacto");
        String telefono = req.getParameter("telefono");
        String telefono2 = req.getParameter("telefono2");
        String correo1 = req.getParameter("correo1");
        String correo2 = req.getParameter("correo2");
        String pais = req.getParameter("pais");
        String estado = req.getParameter("estado");
        String municipio = req.getParameter("municipio");
        String colonia = req.getParameter("colonia");

        ColoniaDao colDao = new ColoniaDao();
        Colonia col = colDao.findOne(Integer.parseInt(colonia));

        Distribuidor dist = new Distribuidor();
        dist.setNombre_distribuidor(nombre_distribuidor);
        dist.setNombre_contacto(nombre_contacto);
        dist.setTelefono(telefono);
        dist.setTelefono2(telefono2);
        dist.setCorreo1(correo1);
        dist.setCorreo2(correo2);
        dist.setPais(col.getMunicipio().getEstado().getPais());
        dist.setEstado(col.getMunicipio().getEstado());
        dist.setMunicipio(col.getMunicipio());
        dist.setColonia(col);

        DistribuidorDao dao = new DistribuidorDao();


        resp.setContentType("text/plain");
        if(dao.insert(dist)){
            resp.getWriter().write("Listo");
        }else{
            resp.getWriter().write("Error");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DistribuidorDao dao = new DistribuidorDao();
        List<Distribuidor> lista = dao.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}
