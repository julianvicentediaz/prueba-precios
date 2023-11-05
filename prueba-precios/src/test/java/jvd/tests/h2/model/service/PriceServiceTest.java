package jvd.tests.h2.model.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jvd.tests.h2.model.dao.Price;
import jvd.tests.h2.model.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;


    @Test
    public void testGetPriceWithPricesAvailable() {
        Long brandId = 1L;
        Long productId = 2L;
        LocalDateTime applicationDate = LocalDateTime.now();

        // Mock del repositorio
        List<Price> mockPrices = new ArrayList<>();
        Price price = new Price();
        mockPrices.add(price);
        Mockito.when(priceRepository.findPricesByBrandIdProductIdAndDateOrderByPriorityDesc(brandId, productId, applicationDate))
                .thenReturn(mockPrices);

        // Ejecutamos
        Price result = priceService.getTopPriorityPrice(brandId, productId, applicationDate);

        // Vvrificamos
        assertEquals(price, result);
    }

    @Test
    public void testGetPriceWithNoPricesAvailable() {
        Long brandId = 1L;
        Long productId = 2L;
        LocalDateTime applicationDate = LocalDateTime.now();

        // Mock del repositorio para devolver vacio
        Mockito.when(priceRepository.findPricesByBrandIdProductIdAndDateOrderByPriorityDesc(brandId, productId, applicationDate))
                .thenReturn(new ArrayList<>());

        // Ejecutamos
        Price result = priceService.getTopPriorityPrice(brandId, productId, applicationDate);

        // verificamos
        assertNull(result);
    }
    
    @Test
    public void testGetPriceWithMultiplePrices() {
        Long brandId = 1L;
        Long productId = 2L;
        LocalDateTime applicationDate = LocalDateTime.now();

        // Mock dos precios con diferente prioridad
        Price price1 = new Price();
        price1.setBrandId(brandId);
        price1.setProductId(productId);
        price1.setPriority(1); 
        price1.setPriceList(1L); // Menor prioridad

        Price price2 = new Price();
        price2.setBrandId(brandId);
        price2.setProductId(productId);
        price2.setPriority(2); 
        price2.setPriceList(2L); // Mayor prioridad

        List<Price> prices = Arrays.asList(price2, price1); //price2 va primero pues es el orden en que debe devolver el metodo findPricesByBrandIdProductIdAndDateOrderByPriorityDesc()

        // Simula el comportamiento del repositorio y el mapeador
        Mockito.when(priceRepository.findPricesByBrandIdProductIdAndDateOrderByPriorityDesc(brandId, productId, applicationDate))
            .thenReturn(prices);

        // probamos
        Price result = priceService.getTopPriorityPrice(brandId, productId, applicationDate);

        // Verifico que el precio con la mayor prioridad se mapee al DTO
        assertEquals(2L, result.getPriceList()); // Verifica que se mapeó el precio con mayor prioridad
    }
}