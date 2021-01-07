package com.inobitec.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {

    private Long id;
    private String name;
    private String text;
    private Date date;
}
