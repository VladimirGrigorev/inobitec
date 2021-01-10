package com.inobitec.project.controller;

import com.inobitec.project.data.dto.NodeDto;
import com.inobitec.project.data.dto.NodeWithItemsDto;
import com.inobitec.project.data.entity.Node;
import com.inobitec.project.data.mapper.NodeMapper;
import com.inobitec.project.service.NodeService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/nodes")
public class NodeController {

    private final NodeService nodeService;
    private final NodeMapper mapper = Mappers.getMapper(NodeMapper.class);

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping(path = "/{parentId}")
    public List<NodeWithItemsDto> getNodes(@PathVariable Long parentId) {
        var result = new ArrayList<NodeWithItemsDto>();
        nodeService.getAllNodesWithParentId(parentId).forEach(node -> {
            var dto = mapper.nodeToNodeWithItemsDto(node);

            if (node.getChildren().size() != 0) {
                node.getChildren().forEach(children -> {
                    if (children.getChildren().size() == 0)
                        dto.addItem(mapper.nodeToNodeDto(children));
                });
                result.add(dto);
            }
        });
        return result;
    }

    @GetMapping(path = "/root")
    public List<NodeWithItemsDto> getRootNodes() {
        var result = new ArrayList<NodeWithItemsDto>();
        nodeService.getAllRootNodes().forEach(node -> {
            var dto = mapper.nodeToNodeWithItemsDto(node);

            node.getChildren().forEach(children -> {
                if (children.getChildren().size() == 0)
                    dto.addItem(mapper.nodeToNodeDto(children));
            });
            result.add(dto);
        });
        return result;
    }

//    @GetMapping(path = "/{id}")
//    public NodeDto getNode(@PathVariable Long id) {
//        return mapper.nodeToNodeDto(nodeService.getNode(id));
//    }
}
