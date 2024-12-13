package org.example.supermercado.service;

import org.example.supermercado.model.Carrito;

import org.example.supermercado.model.Cliente;
import org.example.supermercado.model.Producto;
import org.example.supermercado.model.dao.ICarritoDao;

import org.example.supermercado.model.dao.IClienteDao;
import org.example.supermercado.model.dao.IProductoDao;
import org.example.supermercado.response.CarritoResponseRest;

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
import java.util.Stack;

@Service
public class CarritoServiceImpl implements ICarritoService {
    private static final Logger log = LoggerFactory.getLogger(CarritoServiceImpl.class);
    @Autowired
    private ICarritoDao carritoDao;
    @Autowired
    private IClienteDao clienteDao;
    @Autowired
    private IProductoDao productoDao;
    private final Stack<Carrito> ultimaEliminacion = new Stack<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CarritoResponseRest> buscarCarritoPorCliente(Long clienteId) {
        log.info("Buscar carritos por Cliente ID: {}", clienteId);
        CarritoResponseRest response = new CarritoResponseRest();

        try {
            // Validar cliente
            Optional<Cliente> cliente = clienteDao.findById(clienteId);
            if (!cliente.isPresent()) {
                log.warn("Cliente no encontrado con ID: {}", clienteId);
                response.setMetada("Error", "-1", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Buscar carritos asociados al cliente
            List<Carrito> carritos = carritoDao.findByCliente(cliente.get());
            if (!carritos.isEmpty()) {
                response.getCarritoResponse().setCarrito(carritos);
                response.setMetada("Respuesta OK", "00", "Carritos encontrados");
            } else {
                log.info("No se encontraron carritos para el Cliente ID: {}", clienteId);
                response.setMetada("Error", "-1", "Carritos no encontrados");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al buscar carritos por cliente", e);
            response.setMetada("Error", "-1", "Error interno en el servidor");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    @Transactional

    public ResponseEntity<CarritoResponseRest> crearCarrito(Carrito carrito) {

        log.info("Buscar por ID");
        CarritoResponseRest response = new CarritoResponseRest();

        List<Carrito> list = new ArrayList<>();

        try {
            Carrito carritoGuardar = carritoDao.save(carrito);
            if (carritoGuardar != null) {
                list.add(carritoGuardar);
                response.getCarritoResponse().setCarrito(list);
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
            return new ResponseEntity<CarritoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoResponseRest>(response, HttpStatus.OK);
    }


    @Transactional

    public ResponseEntity<CarritoResponseRest> eliminarCarrito(Long productoId) {


        CarritoResponseRest response = new CarritoResponseRest();
        try {

            Optional<Producto> producto = productoDao.findById(productoId);
            List<Carrito> carrito = carritoDao.findByProducto(producto.get());
            for (Carrito carritoAux : carrito) {
                ultimaEliminacion.push(carritoAux);
                carritoDao.deleteById((long) carritoAux.getId());

            }

            response.setMetada("Respuesta Ok", "00", "Eliminacion exitosa");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar el producto");
            log.error("Error al eliminar producto", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoResponseRest>(response, HttpStatus.OK);
    }




    @Override
    @Transactional
    public ResponseEntity<CarritoResponseRest> deshacerCarrito() {
        CarritoResponseRest response = new CarritoResponseRest();

        try {
            if (ultimaEliminacion.isEmpty()) {
                response.setMetada("Error", "404", "No hay eliminaciones recientes para deshacer");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Obtener ID del carrito a restaurar
            Carrito carritoId = ultimaEliminacion.pop();
            log.info("");

            // Guardar la entidad restaurada
            carritoDao.save(carritoId);
            response.setMetada("Respuesta Ok", "00", "Eliminación deshecha exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error al deshacer la eliminación", e);
            response.setMetada("Error", "-1", "Error al deshacer la eliminación");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
