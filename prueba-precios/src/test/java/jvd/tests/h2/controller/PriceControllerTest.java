package jvd.tests.h2.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jvd.tests.h2.model.dao.Price;
import jvd.tests.h2.model.service.PriceMapper;
import jvd.tests.h2.model.service.PriceService;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest extends PriceController {
	
    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;
    
    @Mock
    private PriceMapper priceMapper;

	@Test
	public void testConsultarTarifaWithEmptyResult() {
	    // Configurar el repositorio para devolver una lista vacía
		when(priceService.getTopPriorityPrice(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(null);
	
	    // Llamada al método
	    ResponseEntity<PriceDTO> responseEntity = priceController.consultarTarifa(2L, 1L, LocalDateTime.now());
	
	    // Verificar que se devuelve un HTTP 404
	    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void testConsultarTarifaWithResults() {
	    // Configurar el repositorio para devolver una lista de precios
		Price price = new Price();
		LocalDateTime dateTime = LocalDateTime.now();
	    when(priceService.getTopPriorityPrice(2L, 1L, dateTime)).thenReturn(price);
	    
        // Configurar el mapeador
        PriceDTO simulatedPriceDTO = new PriceDTO();
        when(priceMapper.mapToDTO(price)).thenReturn(simulatedPriceDTO);

	
	    // Llamada al método
	    ResponseEntity<PriceDTO> responseEntity = priceController.consultarTarifa(2L, 1L, dateTime);
	
	    // Verificar que se devuelve un HTTP 200 y la lista de precios
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(simulatedPriceDTO, responseEntity.getBody());
	}

}
