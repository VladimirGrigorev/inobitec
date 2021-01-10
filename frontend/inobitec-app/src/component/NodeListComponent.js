import React, { Component } from "react";
import NodeService from '../service/NodeService';
import Tree from '@naisutech/react-tree'

class NodeListComponent extends Component {

    constructor(props) {
        super(props)
        this.onSelectNode = this.onSelectNode.bind(this);

        this.state = {
            nodes: []
        }
    }

    componentDidMount() {
        NodeService.getRootNodes().then((response) => {
            this.setState({nodes: response.data});
        });
    }

    onSelectNode(node) {
        console.log(node);
        if (node.items != null) {
            NodeService.getNodesWithParentId(node.id).then((response) => {
                response.data.map(
                    node => {
                        if (!this.state.nodes.some(item => node.id === item.id))
                            this.setState(prevState => ({
                                nodes: [...prevState.nodes, node]
                            }));
                    }
                )
            });
        }
        else {
            this.props.history.push(`/nodes/${node.id}`);
        }
    }

    render() {
        return (
            <Tree nodes={this.state.nodes} onSelect={this.onSelectNode} grow/>
        )
    }
}

export default NodeListComponent