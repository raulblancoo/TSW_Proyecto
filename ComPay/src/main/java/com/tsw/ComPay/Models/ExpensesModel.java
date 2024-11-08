package com.tsw.ComPay.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="expenses")
public class ExpensesModel {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="amount")
    private double amount;

    @Column(name="expenses_name")
    private String expenses_name;

    @ManyToOne
    @JoinColumn(name = "originUser_id")
    private UserModel originUser;

    @ManyToOne
    @JoinColumn(name = "group_id")

    private GroupModel group;

}
