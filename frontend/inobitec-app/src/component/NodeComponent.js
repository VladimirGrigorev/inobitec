import React, { Component } from "react";
import NodeService from '../service/NodeService';

class NodeComponent extends Component {

    constructor(props) {
        super(props);
        this.getNode = this.getNode.bind(this);

        this.state = {
            node: {}
        };
    }

    componentDidMount() {
        this.getNode(this.props.match.params.id);
    }

    getNode(id){
        NodeService.getNode(id).then((response) => {
            this.setState({node: response.data});
        });
    }

    render() {
        return (
            <div>
                <h1>{this.state.node.label}</h1>
                <h4>{this.state.node.text}</h4>
            </div>
        )
    }
}

export default NodeComponent