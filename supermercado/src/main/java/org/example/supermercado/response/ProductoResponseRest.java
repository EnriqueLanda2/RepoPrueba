package org.example.supermercado.response;

public class ProductoResponseRest extends ResponseRest {

    private ProductoResponse productoResponse = new ProductoResponse();

    public ProductoResponse getProductoResponse() {
        return productoResponse;
    }


    public void setProductoResponse(ProductoResponse productoResponse) {
        this.productoResponse = productoResponse;
    }

}
