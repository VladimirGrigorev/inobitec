package com.inobitec.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "node", schema = "public")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NonNull String name;
    private String text;

    @ManyToOne()
    @JoinColumn(name = "parentId")
    private Node parent;

    @OneToMany(mappedBy = "parent")
    private List<Node> children;
}
