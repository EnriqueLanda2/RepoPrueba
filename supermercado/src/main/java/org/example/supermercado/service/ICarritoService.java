package org.example.supermercado.service;

import org.example.supermercado.model.Carrito;
import org.example.supermercado.response.CarritoResponseRest;
import org.springframework.http.ResponseEntity;
import org.example.supermercado.service.ICarritoService;
import org.springframework.transaction.annotation.Transactional;

public interface ICarritoService {



    public ResponseEntity<CarritoResponseRest> buscarCarritoPorCliente(Long clienteId);

    public ResponseEntity<CarritoResponseRest> crearCarrito(Carrito carrito);

    public ResponseEntity<CarritoResponseRest> eliminarCarrito(Long productoId);

        public ResponseEntity<CarritoResponseRest>deshacerCarrito();

}
