package com.inobitec.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeWithItemsDto {

    private Long id;
    private Long parentId;
    private String label;
    private List<NodeDto> items = new ArrayList<>();
    private String text;

    public void addItem(NodeDto dto){
        items.add(dto);
    }
}
