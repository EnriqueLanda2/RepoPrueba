package org.example.supermercado.controller;

import org.example.supermercado.model.Producto;
import org.example.supermercado.response.ProductoResponseRest;
import org.example.supermercado.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class ProductoRestController {

    @Autowired
    private IProductoService productoService;


    @PostMapping("/producto")
    public ResponseEntity<ProductoResponseRest> crear(@RequestBody Producto request) {
        ResponseEntity<ProductoResponseRest> response = productoService.crear(request);
        return response;
    }


}