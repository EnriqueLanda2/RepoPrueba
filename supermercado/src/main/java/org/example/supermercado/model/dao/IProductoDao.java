package org.example.supermercado.model.dao;

import org.example.supermercado.model.Carrito;
import org.example.supermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IProductoDao extends JpaRepository<Producto, Long> {


}
