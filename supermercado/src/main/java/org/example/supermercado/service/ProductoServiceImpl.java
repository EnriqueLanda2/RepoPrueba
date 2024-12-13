package org.example.supermercado.service;

import org.example.supermercado.model.Producto;
import org.example.supermercado.model.dao.IProductoDao;
import org.example.supermercado.response.ProductoResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);
    @Autowired
    private IProductoDao productoDao;


    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> crear(Producto producto) {

        // public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
//        log.info("Buscando categorias");
        log.info("Buscar por ID");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try {
            Producto productoGuardar = productoDao.save(producto);
            if (productoGuardar != null) {
                list.add(productoGuardar);
                response.getProductoResponse().setProducto(list);
                response.setMetada("Respuesta OK", "00", "Creacion Exitosa");
            } else {
                log.info("No se encontro la categoria");
                response.setMetada("Respuesta no encontrada", "-1", "Categoria no creada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al crear la categoria");
            log.error("Error al guardar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }


}
