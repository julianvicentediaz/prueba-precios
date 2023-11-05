package jvd.tests.h2.model.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jvd.tests.h2.controller.PriceDTO;
import jvd.tests.h2.model.dao.Price;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

    @InjectMocks
    private PriceMapper priceMapper;

    @Mock
    private Price price;

    @Test
    public void testMapToDTO() {
        // instanciamos un precio
    	Price price = new Price();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setPriceList(3L);
        price.setStartDate(LocalDateTime.now());
        price.setEndDate(LocalDateTime.now());
        price.setPrice(45.67);

        // Llamada al método
        PriceDTO priceDTO = priceMapper.mapToDTO(price);

        // Verificación de resultados
        assertEquals(price.getBrandId(), priceDTO.getBrandId());
        assertEquals(price.getProductId(), priceDTO.getProductId());
        assertEquals(price.getPriceList(), priceDTO.getPriceList());
        assertEquals(price.getStartDate(), priceDTO.getStartDate());
        assertEquals(price.getEndDate(), priceDTO.getEndDate());
        assertEquals(price.getPrice(), priceDTO.getPrice());
    }
    
    @Test
    public void testMapToDTOWithNull() {
    	// Llamada al método
        PriceDTO priceDTO = priceMapper.mapToDTO(null);
        
        // Verificación de resultados
        assertNull(priceDTO);
    }
}
