package org.example.supermercado.response;

import org.example.supermercado.model.Cliente;

import java.util.List;

public class ClienteResponse {
    private List<Cliente> cliente;

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

}
