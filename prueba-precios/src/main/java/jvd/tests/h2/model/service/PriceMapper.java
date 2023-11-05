package jvd.tests.h2.model.service;


import org.springframework.stereotype.Component;

import jvd.tests.h2.controller.PriceDTO;
import jvd.tests.h2.model.dao.Price;
import lombok.NoArgsConstructor;

/**
 * Transformador de objetos relativos al precio
 */
@Component
@NoArgsConstructor
public class PriceMapper {

	/**
	 * Transforma on objeto de entidad Price en un DTO priceDTO. Devuelve null si el parametro recibido es null
	 * @param price
	 * @return
	 */
    public PriceDTO mapToDTO(Price price) {
    	//TODO se podria implementar con MapStructs
        PriceDTO priceDTO = null;
        
    	if (price != null) {    	
	    	priceDTO = new PriceDTO();
	    	
	        priceDTO.setBrandId(price.getBrandId());
	        priceDTO.setProductId(price.getProductId());
	        priceDTO.setPriceList(price.getPriceList());
	        priceDTO.setStartDate(price.getStartDate());
	        priceDTO.setEndDate(price.getEndDate());
	        priceDTO.setPrice(price.getPrice());
        }
        return priceDTO;
    }


}
