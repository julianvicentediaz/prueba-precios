package jvd.tests.h2.model.dao;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Price {
	//añado el campo ID por simplicidad, puesto que no se explicita cual es la clave primaria y se permite añadir campos nuevos
    @Id
    private long id;		
    //mantengo el resto de campos con la numenclatura dada
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Long priceList;
    private Double price;
    private String curr;
}