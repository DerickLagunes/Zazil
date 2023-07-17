package com.zazilweb.controller;

import com.google.gson.Gson;
import com.zazilweb.model.DAO.EstadoDao;
import com.zazilweb.model.DAO.PaisDao;
import com.zazilweb.model.Estado;
import com.zazilweb.model.Pais;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EstadoServlet", value = "/Estado")
public class EstadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Estado> lista = new ArrayList<>();
        try{
            int pais = Integer.parseInt(req.getParameter("pais"));
            EstadoDao dao = new EstadoDao();
            lista = dao.findAll(pais);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}
