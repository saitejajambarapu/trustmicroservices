package com.trust.repository;

import com.trust.entity.Product;
import com.trust.entity.ProductBuyerRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductBuyerRelationRepository extends JpaRepository<ProductBuyerRelation, Long> {

    Optional<List<ProductBuyerRelation>> findByProductId(Long productId);
}
