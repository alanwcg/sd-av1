package com.example.av1.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "purchase")
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 60)
    private String customer;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String creditCardNumber;

    @Column(nullable = false)
    private LocalDate creditCardDueDate;

    @Column(nullable = false)
    private String creditCardCVN;

    @Column(nullable = false)
    private Integer installments;

}
