package com.breezevillepark.Kitchen.and.Bar.System.receipt;

import com.breezevillepark.Kitchen.and.Bar.System.order.Order;
import com.breezevillepark.Kitchen.and.Bar.System.payment.Payment;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Receipt {

    @Id
    @SequenceGenerator(
            name = "receipt_id_sequence",
            sequenceName = "receipt_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receipt_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private int receiptId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
