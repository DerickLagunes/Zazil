package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.DAO.PaisDao;
import com.zazilweb.model.DAO.ProductoDao;
import com.zazilweb.model.Pais;
import com.zazilweb.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaisServlet", value = "/Pais")
public class PaisServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PaisDao dao = new PaisDao();
        List<Pais> lista = dao.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}
