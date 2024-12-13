package org.example.supermercado.model.dao;

import org.example.supermercado.model.Carrito;
import org.example.supermercado.model.Cliente;
import org.example.supermercado.model.Producto;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICarritoDao extends JpaRepository<Carrito, Long> {
    List<Carrito> findByCliente(Cliente cliente);
    List<Carrito> findByProducto(Producto producto);

}
