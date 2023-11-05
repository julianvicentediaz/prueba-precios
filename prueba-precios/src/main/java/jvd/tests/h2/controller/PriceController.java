package jvd.tests.h2.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jvd.tests.h2.model.dao.Price;
import jvd.tests.h2.model.service.PriceMapper;
import jvd.tests.h2.model.service.PriceService;


@RestController
public class PriceController {
	
	@Autowired
    private PriceService priceService;
	
    @Autowired
    private PriceMapper priceMapper;
    
	/**
	 * Obtiene datos de precio, para un id de marca, id de producto, y fecha de aplicacion.
	 * Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
	 * @param brandId id de la marca
	 * @param productId id de producto	 
	 * @param applicationDate fecha de aplicación
	 * @return
	 */
    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> consultarTarifa(
        @RequestParam("brandId") @Min(1) Long brandId,			// Por incluir una valdiacion, el id debe ser mayor que cero
        @RequestParam("productId") @Min(1) Long productId,  	// Por incluir una valdiacion, el id debe ser mayor que cero
        @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime applicationDate
    ) {
    	Price price = priceService.getTopPriorityPrice(brandId, productId, applicationDate);

        if (price == null) {
        	// podríamos definir un objeto de respuesta mas complejo para manejar codigos 
        	// y/o mensajes de error, pero mantengo la respuesta simple y estandarizada
            return ResponseEntity.notFound().build(); 
        } else {
            return ResponseEntity.ok(priceMapper.mapToDTO(price));
        }
    }
}
