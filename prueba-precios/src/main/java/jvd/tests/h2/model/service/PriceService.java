package jvd.tests.h2.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jvd.tests.h2.model.dao.Price;
import jvd.tests.h2.model.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;


    /**
	 * Obtiene datos de precio, para un id de marca, id de producto, y fecha de aplicacion.
	 * Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
	 * @param brandId id de la marca
	 * @param productId id de producto	 
	 * @param applicationDate fecha de aplicación
     * @return El precio, o null si no hay definido para los parametros de entrada
     */
    public Price getTopPriorityPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
    	
    	List<Price> prices = priceRepository.findPricesByBrandIdProductIdAndDateOrderByPriorityDesc(brandId, productId, applicationDate);
        Price price = null;	//devolvemos null si no encontramos. Si se esperara que siempre haya precio definido, se podria lanzar una excepcion indicativa de la situacion 
        if(!prices.isEmpty()) {
        	price = prices.get(0); //cogemos el primero, puesto que el repositorio nos lo devuelve ordenador por prioridad descendente	
        }
                
        return price;
    }
    

}