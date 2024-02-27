package com.breezevillepark.Kitchen.and.Bar.System.orderitem;

import com.breezevillepark.Kitchen.and.Bar.System.order.Order;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class OrderItem {
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    @Id
    @SequenceGenerator(
            name = "order_item_id_sequence",
            sequenceName = "order_item_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private Integer orderItemId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private Double subTotal;

    public OrderItem() {
    }

    public OrderItem(String comment, Order order,
                      Integer orderItemId,
                     int quantity, Recipe recipe,
                     Double subTotal) {
        this.comment = comment;
        this.order = order;
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.recipe = recipe;
        this.subTotal = subTotal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity &&  Objects.equals(comment, orderItem.comment) && Objects.equals(order, orderItem.order)  && Objects.equals(orderItemId, orderItem.orderItemId) && Objects.equals(recipe, orderItem.recipe) && Objects.equals(subTotal, orderItem.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, order, orderItemId, quantity, recipe,  subTotal);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "comment='" + comment + '\'' +
                ", order=" + order +
                ", orderItemId=" + orderItemId +
                ", quantity=" + quantity +
                ", recipe=" + recipe +

                ", subTotal=" + subTotal +
                '}';
    }
}
