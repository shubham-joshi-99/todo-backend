package com.shubham_joshi.entity;

import com.shubham_joshi.domain.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "todo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(name = "item_order")
    private int order;

}
