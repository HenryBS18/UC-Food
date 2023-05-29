package com.ucfood.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction_items")
@Getter
@Setter
@NoArgsConstructor
public class TransactionItems {

    @Id
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "item_quantity")
    private int ItemQuantity;

    public TransactionItems(Transaction transaction, Menu menu, int itemQuantity) {
        this.transaction = transaction;
        this.menu = menu;
        ItemQuantity = itemQuantity;
    }

}
