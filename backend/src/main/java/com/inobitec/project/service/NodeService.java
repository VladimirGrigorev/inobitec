package com.inobitec.project.service;

import com.inobitec.project.data.entity.Node;
import com.inobitec.project.data.repository.NodeRepository;
import com.inobitec.project.exeption.NotFoundException;
import com.inobitec.project.exeption.WrongDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getAllNodesWithParentId(Long parentId) {
        var result = new ArrayList<Node>();
        nodeRepository.findAll().forEach(node -> {
            if(node.getParent() != null && node.getParent().getId().equals(parentId))
                result.add(node);
        });
        return result;
    }

    public List<Node> getAllRootNodes() {
        var result = new ArrayList<Node>();
        nodeRepository.findAll().forEach(node -> {
            if(node.getParent() == null)
                result.add(node);
        });
        return result;
    }

    public Node getNode(Long id) {
        return nodeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Node createOrUpdateNode(Node node, Long parentId) {
        if(node.getId() != null && node.getId().equals(parentId)){
            throw new WrongDataException();
        }
        if(parentId <= 0) {
            node.setParent(null);
        }
        else{
            node.setParent(nodeRepository.findById(parentId).orElse(null));
        }
        return nodeRepository.save(node);
    }

    public void deleteNode(Long id) {
        nodeRepository.findById(id).orElseThrow(NotFoundException::new).getChildren().forEach(children -> {
            deleteNode(children.getId());
        });
        nodeRepository.deleteById(id);
    }
}
