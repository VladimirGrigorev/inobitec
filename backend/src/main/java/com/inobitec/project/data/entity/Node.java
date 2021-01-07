package com.inobitec.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "node", schema = "public")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @NonNull Long id;
    private Long parent_id;
    private @NonNull String name;
    private String text;
}
