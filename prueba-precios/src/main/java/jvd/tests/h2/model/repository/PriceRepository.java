package jvd.tests.h2.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jvd.tests.h2.model.dao.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	/**
	 * Obtiene la(s) entrada(s) correspondiente(s) al id de marca, id de producto, y para las que 
	 * la fecha indicada este entre la fecha de inicio y finalizacion, ordenado por prioridad 
	 * descendente
	 * @param brandId
	 * @param productId
	 * @param fecha
	 * @return
	 */
    @Query("SELECT p FROM Price p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate <= :fecha " +
            "AND p.endDate >= :fecha " +
            "ORDER BY p.priority DESC")
     List<Price> findPricesByBrandIdProductIdAndDateOrderByPriorityDesc(	
         @Param("brandId") Long brandId,
         @Param("productId") Long productId,
         @Param("fecha") LocalDateTime fecha
     );
}