package org.example.supermercado.service;


import org.example.supermercado.model.Producto;
import org.example.supermercado.response.ProductoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductoService {



    public ResponseEntity<ProductoResponseRest> crear(Producto producto);


}
