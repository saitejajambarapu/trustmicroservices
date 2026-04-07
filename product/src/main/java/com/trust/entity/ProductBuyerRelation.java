package com.trust.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "ProductBuyerRelation")
@Data
public class ProductBuyerRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long buyerId;
    @CreatedDate
    private Timestamp createdOn;
    private boolean isActive;
}
