package com.tsw.ComPay.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="groups")
@Getter
@Setter
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="groupName")
    private String groupName;

    @Column(name="currency")
    private String currency;
}
