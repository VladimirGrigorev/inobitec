package com.inobitec.project.controller;

import com.inobitec.project.data.dto.NodeDto;
import com.inobitec.project.data.mapper.NodeMapper;
import com.inobitec.project.service.NodeService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nodes")
public class NodeController {

    private final NodeService nodeService;
    private final NodeMapper mapper = Mappers.getMapper(NodeMapper.class);

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping(path = "")
    public List<NodeDto> getNodes() {
        return mapper.nodeToNodeDto(nodeService.getAllNodes());
    }

    @GetMapping(path = "/{id}")
    public NodeDto getNode(@PathVariable Long id) {
        return mapper.nodeToNodeDto(nodeService.getNode(id));
    }
}
