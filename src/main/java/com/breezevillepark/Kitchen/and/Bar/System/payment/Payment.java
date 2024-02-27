package com.breezevillepark.Kitchen.and.Bar.System.payment;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.order.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_id_sequence",
            sequenceName = "payment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private int paymentId;
    private BigDecimal amount;
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private Double changes;
    private LocalDateTime datePaid;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private double payableAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private BigDecimal received;
    private Double serviceAmount;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private double taxAmount;


}

