package com.inobitec.project.data.mapper;

import com.inobitec.project.data.dto.NodeDto;
import com.inobitec.project.data.entity.Node;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NodeMapper{

    NodeDto nodeToNodeDto(Node node);
    List<NodeDto> nodeToNodeDto(List<Node> node);
}
