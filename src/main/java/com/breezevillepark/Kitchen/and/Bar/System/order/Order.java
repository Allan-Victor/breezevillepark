package com.breezevillepark.Kitchen.and.Bar.System.order;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.orderitem.OrderItem;
import com.breezevillepark.Kitchen.and.Bar.System.payment.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    private LocalDateTime dateModified;
    private LocalDateTime dateSubmitted;

    @ManyToOne()
    @JoinColumn(name = "employee_Id")
    private Employee lastUpdatedEmployee;


    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    @Column(name = "order_id",
            nullable = false,
            updatable = false
    )
    private Integer orderId;

    @OneToMany(mappedBy = "order",
    orphanRemoval = true,
    cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private Employee submittedEmployee;


    private Integer submittedEmployeeId;
    private BigDecimal totalPrice;

    public Order() {
    }

    public Order(LocalDateTime dateModified, LocalDateTime dateSubmitted,
                 Employee lastUpdatedEmployee, Integer lastUpdatedEmployeeId,
                 Integer orderId, List<OrderItem> orderItemList,
                 Payment payment, OrderStatus status,
                 Employee submittedEmployee, Integer submittedEmployeeId,
                 BigDecimal totalPrice) {
        this.dateModified = dateModified;
        this.dateSubmitted = dateSubmitted;
        this.lastUpdatedEmployee = lastUpdatedEmployee;
        this.orderId = orderId;
        this.orderItemList = orderItemList;
        this.payment = payment;
        this.status = status;
        this.submittedEmployee = submittedEmployee;
        this.submittedEmployeeId = submittedEmployeeId;
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Employee getLastUpdatedEmployee() {
        return lastUpdatedEmployee;
    }

    public void setLastUpdatedEmployee(Employee lastUpdatedEmployee) {
        this.lastUpdatedEmployee = lastUpdatedEmployee;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Employee getSubmittedEmployee() {
        return submittedEmployee;
    }

    public void setSubmittedEmployee(Employee submittedEmployee) {
        this.submittedEmployee = submittedEmployee;
    }

    public Integer getSubmittedEmployeeId() {
        return submittedEmployeeId;
    }

    public void setSubmittedEmployeeId(Integer submittedEmployeeId) {
        this.submittedEmployeeId = submittedEmployeeId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(dateModified, order.dateModified) && Objects.equals(dateSubmitted, order.dateSubmitted) && Objects.equals(lastUpdatedEmployee, order.lastUpdatedEmployee)  && Objects.equals(orderId, order.orderId) && Objects.equals(orderItemList, order.orderItemList) && Objects.equals(payment, order.payment) && status == order.status && Objects.equals(submittedEmployee, order.submittedEmployee) && Objects.equals(submittedEmployeeId, order.submittedEmployeeId) && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateModified, dateSubmitted, lastUpdatedEmployee,  orderId, orderItemList, payment, status, submittedEmployee, submittedEmployeeId, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateModified=" + dateModified +
                ", dateSubmitted=" + dateSubmitted +
                ", lastUpdatedEmployee=" + lastUpdatedEmployee +

                ", orderId=" + orderId +
                ", orderItemList=" + orderItemList +
                ", payment=" + payment +
                ", status=" + status +
                ", submittedEmployee=" + submittedEmployee +
                ", submittedEmployeeId=" + submittedEmployeeId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
