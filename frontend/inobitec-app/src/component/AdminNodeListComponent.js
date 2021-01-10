import React, { Component } from "react";
import NodeService from '../service/NodeService';
import Tree from '@naisutech/react-tree'

class AdminNodeListComponent extends Component {

    constructor(props) {
        super(props)
        this.onSelectNode = this.onSelectNode.bind(this);
        this.clear = this.clear.bind(this);
        this.onChangeLabel = this.onChangeLabel.bind(this);
        this.onChangeText = this.onChangeText.bind(this);
        this.onChangeParentId = this.onChangeParentId.bind(this);
        this.saveNode = this.saveNode.bind(this);
        this.deleteNode = this.deleteNode.bind(this);

        this.state = {
            nodes: [],
            id: null,
            label: "",
            text: "",
            parentId: null
        }
    }

    combiningLabelAndId(nodes) {
        nodes.map(node => {
            node.name = node.label;
            node.label = "id:" + node.id + " " + node.label;
            node.items.map(item => {
                item.name = item.label;
                item.label = "id:" + item.id + " " + item.label;
            })
        });
    }

    componentDidMount() {
        NodeService.getRootNodes().then((response) => {
            this.combiningLabelAndId(response.data);
            this.setState({nodes: response.data});
        });
    }

    onSelectNode(node) {
        this.setState({
            id: node.id,
            label: node.name,
            text: node.text,
            parentId: node.parentId
        });
        if(node.text == null)
            this.setState({text: ""});
        if(node.parentId == null)
            this.setState({parentId: -1});

        NodeService.getNodesWithParentId(node.id).then((response) => {
            this.combiningLabelAndId(response.data);
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

    clear() {
        this.setState({
            id: null,
            label: "",
            text: "",
            parentId: -1
        });
    }

    onChangeLabel(e) {
        this.setState({
            label: e.target.value
        });
    }

    onChangeText(e) {
        this.setState({
            text: e.target.value
        });
    }

    onChangeParentId(e) {
        this.setState({
            parentId: e.target.value
        });
    }

    saveNode() {
        var data = {
            id: this.state.id,
            label: this.state.label,
            text: this.state.text,
            parentId: this.state.parentId
        };
        console.log(data);

        NodeService.saveNode(data);

        window.location.reload();
    }

    deleteNode() {
        NodeService.deleteNode(this.state.id);
        window.location.reload();
    }

    render() {
        return (
            <div>
                <div className="admin-node-container">
                <div className="form-group">
                    <label htmlFor="id">Id: {this.state.id}</label><br/>
                    <label htmlFor="label">Имя</label>
                    <input
                        type="text"
                        className="form-control"
                        id="label"
                        required
                        value={this.state.label}
                        onChange={this.onChangeLabel}
                        name="label"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="text">Описание</label>
                    <input
                        type="text"
                        className="form-control"
                        id="text"
                        required
                        value={this.state.text}
                        onChange={this.onChangeText}
                        name="text"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="parentId">Id родителя</label>
                    <input
                        type="number"
                        className="form-control"
                        id="parentId"
                        required
                        value={this.state.parentId}
                        onChange={this.onChangeParentId}
                        name="parentId"
                    />
                    <p>Для указания начального узла (без родителя) укажите в "Id родителя" любое не положительное целое число(например -1)</p>
                </div>

                <button onClick={this.saveNode} className="btn btn-success">
                    Сохранить
                </button>
                <button onClick={this.clear} className="btn btn-warning">
                    Очистить поля
                </button>
                <button onClick={this.deleteNode} className="btn btn-danger">
                    Удалить
                </button>
                </div>
            <Tree nodes={this.state.nodes} onSelect={this.onSelectNode} theme={'light'} grow/>
            </div>
        )
    }
}

export default AdminNodeListComponent