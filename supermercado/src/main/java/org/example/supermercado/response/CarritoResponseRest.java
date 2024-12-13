package org.example.supermercado.response;

public class CarritoResponseRest extends CarritoResponse {
    private CarritoResponse carritoResponse = new CarritoResponse();

    public CarritoResponse getCarritoResponse() {
        return carritoResponse;
    }

    //5:37
    public void setCarritoResponse(CarritoResponse carritoResponse) {
        this.carritoResponse = carritoResponse;
    }

}
