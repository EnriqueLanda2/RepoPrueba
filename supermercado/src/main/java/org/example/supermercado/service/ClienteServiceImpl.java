package org.example.supermercado.service;

// import jakarta.transaction.Transactional;

import org.example.supermercado.model.Cliente;
import org.example.supermercado.model.dao.IClienteDao;
import org.example.supermercado.response.ClienteResponseRest;
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
public class ClienteServiceImpl implements IClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private IClienteDao clienteDao;



    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> crear(Cliente cliente) {

        // public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
//        log.info("Buscando categorias");
        log.info("Buscar por ID");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();
        try {
            Cliente clienteGuardar = clienteDao.save(cliente);
            if (clienteGuardar != null) {
                list.add(clienteGuardar);
                response.getClienteResponse().setCliente(list);
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
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }



}
