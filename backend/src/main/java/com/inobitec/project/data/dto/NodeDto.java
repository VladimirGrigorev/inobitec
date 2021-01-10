package com.inobitec.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {

    private Long id;
    private Long parentId;
    private String label;
    private String text;
}
