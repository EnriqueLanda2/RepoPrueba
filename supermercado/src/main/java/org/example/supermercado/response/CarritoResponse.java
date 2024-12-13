package org.example.supermercado.response;

import org.example.supermercado.model.Carrito;
import org.example.supermercado.model.Cliente;

import java.util.List;

public class CarritoResponse extends ResponseRest {
    private List<Carrito> carrito;

    public List<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Carrito> carrito) {
        this.carrito = carrito;
    }

}
