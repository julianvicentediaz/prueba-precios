package jvd.tests.h2.controller;

import java.time.LocalDateTime;

import lombok.Data;


/**
 * Informacion de precio a utilizar en el interfaz del servicio de precios
 */
@Data
public class PriceDTO {
	
    private Long brandId;		// cadena del grupo
    private Long productId;		// identificador código de producto
    private Long priceList;		// identificador de la tarifa de precios aplicable
    private LocalDateTime startDate;	// fecha de inicio de aplicación del precio tarifa indicado
    private LocalDateTime endDate;		// fecha de fin de aplicación del precio tarifa indicado
    private double price;		// precio final de venta
}
