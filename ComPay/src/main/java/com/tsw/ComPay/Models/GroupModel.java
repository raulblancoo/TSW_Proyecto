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
@Table(name="groups")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="groupname")
    private String groupname;

    @Column(name="imgURL")
    private String imageURL;

    @Column(name="amount")
    private Double amount; // Nuevo atributo para almacenar la deuda

    public GroupModel(String groupname, String imageURL, Double amount) {
        this.groupname = groupname;
        this.imageURL = imageURL;
        this.amount = amount;
    }
}
