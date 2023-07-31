package com.zazilweb.model.DAO;

import com.zazilweb.model.*;
import com.zazilweb.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao implements DaoRepository{

    private Connection con;
    private List<Producto> listaProducto;
    private Producto p;
    private boolean resp;

    public ProductoDao(){
        this.con = new MysqlConector().connect();
        this.listaProducto = new ArrayList<>();
        this.p = new Producto();
        this.resp = false;
    }
    @Override
    public List findAll() {
        try {
            PreparedStatement stmt =
                    con.prepareStatement(
                            "select * from producto as p " +
                                "join distribuidor as d on p.distribuidor = d.id " +
                                "join paises as pa on d.pais = pa.id " +
                                "join estados as es on d.estado = es.id " +
                                "join municipios as mu on d.municipio = mu.id " +
                                "join colonias as col on d.colonia = col.id " +
                                "join subcategoria as sub on p.subcategoria = sub.id " +
                                "join categoria as cat on p.categoria = cat.id ; "
                    );
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                p.setId(res.getInt(1));
                p.setNombre(res.getString(2));
                p.setImagen(res.getString("imagen"));
                p.setLote(res.getString("lote"));
                p.setCaducidad(res.getDate("caducidad"));
                p.setPrecio_costo(res.getDouble("precio_costo"));
                p.setPrecio_venta(res.getDouble("precio_venta"));
                p.setMarca(res.getString("marca"));

                Distribuidor d = new Distribuidor();
                d.setId(res.getInt("distribuidor"));
                d.setNombre_distribuidor("nombre_distribuidor");
                d.setNombre_contacto("nombre_contacto");
                d.setTelefono("telefono");
                d.setTelefono2("telefono2");
                d.setCorreo1("correo1");
                d.setCorreo2("correo2");

                Pais pa = new Pais();
                pa.setId(res.getInt(22));
                pa.setNombre(res.getString(27));
                d.setPais(pa);

                Estado es = new Estado();
                es.setId(res.getInt(23));
                es.setNombre(res.getString(29));
                es.setPais(pa);
                d.setEstado(es);

                Municipio mu = new Municipio();
                mu.setId(res.getInt(24));
                mu.setNombre(res.getString(32));
                mu.setEstado(es);
                d.setMunicipio(mu);

                Colonia col = new Colonia();
                col.setId(res.getInt(25));
                col.setNombre(res.getString(35));
                col.setCiudad(res.getString(36));
                col.setMunicipio(mu);
                col.setAsentamiento(res.getString(38));
                col.setCodigo_postal(39);
                d.setColonia(col);

                p.setDistribuidor(d);

                p.setModelo(res.getString("modelo"));
                p.setTipo_contenido(res.getString("tipo_contenido"));
                p.setContenido(res.getString("contenido"));

                Categoria cat = new Categoria();
                cat.setId(res.getInt("categoria"));
                cat.setNombre(res.getString(44));
                p.setCategoria(cat);

                SubCategoria sub = new SubCategoria();
                sub.setId(res.getInt("subcategoria"));
                sub.setCategoria(cat);
                sub.setNombre(res.getString(41));
                p.setSubCategoria(sub);

                listaProducto.add(p);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProducto;
    }

    @Override
    public Object findOne(int id) {
        return null;
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
        try{
            Producto p = (Producto) object;
            PreparedStatement stmt = con.prepareStatement(
                    "insert into producto (nombre, imagen, lote, caducidad, precio_costo, precio_venta, marca," +
                            " distribuidor, modelo, tipo_contenido, contenido, subcategoria, categoria) " +
                            "values (?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            stmt.setString(1,p.getNombre());
            stmt.setString(2,p.getImagen());
            stmt.setString(3,p.getLote());
            stmt.setDate(4,p.getCaducidad());
            stmt.setDouble(5,p.getPrecio_costo());
            stmt.setDouble(6,p.getPrecio_venta());
            stmt.setString(7,p.getMarca());
            stmt.setInt(8,p.getDistribuidor().getId());
            stmt.setString(9,p.getModelo());
            stmt.setString(10,p.getTipo_contenido());
            stmt.setString(11,p.getContenido());
            stmt.setInt(12,p.getSubCategoria().getId());
            stmt.setInt(13,p.getSubCategoria().getCategoria().getId());

            return stmt.executeUpdate() > 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
