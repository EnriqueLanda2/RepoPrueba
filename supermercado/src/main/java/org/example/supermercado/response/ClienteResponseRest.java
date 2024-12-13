package org.example.supermercado.response;

public class ClienteResponseRest extends ResponseRest {

    private ClienteResponse clienteResponse = new ClienteResponse();

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }

    //5:37
    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }
}
