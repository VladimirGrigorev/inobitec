package com.inobitec.project.controller;

import com.inobitec.project.data.dto.NodeDto;
import com.inobitec.project.data.mapper.NodeMapper;
import com.inobitec.project.service.NodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nodes")
public class NodeController {

    private final NodeService nodeService;
    private final NodeMapper nodeMapper;

    public NodeController(NodeService nodeService,
                          NodeMapper nodeMapper) {
        this.nodeService = nodeService;
        this.nodeMapper = nodeMapper;
    }

    @GetMapping(path = "")
    public List<NodeDto> getNodes() {
        return nodeMapper.nodeToNodeDto(nodeService.getAllNodes());
    }

    @GetMapping(path = "/{id}")
    public NodeDto getNode(@PathVariable Long id) {
        return nodeMapper.nodeToNodeDto(nodeService.getNode(id));
    }
}
