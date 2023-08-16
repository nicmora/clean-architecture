package com.nicmora.cleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "stocks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_generator")
    @SequenceGenerator(name = "stock_generator", sequenceName = "stock_seq", initialValue = 10)
    private Long id;

    private String code;
    private Integer count;

}
