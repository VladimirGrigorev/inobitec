package com.inobitec.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {

    private Long id;
    private Long parent_id;
    private String name;
    private String text;
}
