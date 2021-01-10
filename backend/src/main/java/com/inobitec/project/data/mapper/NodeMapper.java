package com.inobitec.project.data.mapper;

import com.inobitec.project.data.dto.NodeDto;
import com.inobitec.project.data.dto.NodeWithItemsDto;
import com.inobitec.project.data.entity.Node;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NodeMapper {

    @Mappings({
            @Mapping(target = "parentId", source = "node.parent.id"),
            @Mapping(target = "label", source = "node.name")
    })
    NodeDto nodeToNodeDto(Node node);

    @Mappings({
            @Mapping(target = "parentId", source = "node.parent.id"),
            @Mapping(target = "label", source = "node.name")
    })
    NodeWithItemsDto nodeToNodeWithItemsDto(Node node);

    @Mappings({
            @Mapping(target = "name", source = "dto.label")
    })
    Node nodeDtoToNode(NodeDto dto);
}
