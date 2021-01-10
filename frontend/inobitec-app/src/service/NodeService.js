import http from "../http-common";
import CurrentUserService from "../service/CurrentUserService";

class NodeService {

    getNodesWithParentId(parentId){
        return http.get(`/v1/nodes/${parentId}`, this.buildOpts());
    }

    getRootNodes(){
        return http.get(`/v1/nodes/root`, this.buildOpts());
    }

    getNode(id){
        return http.get(`/v1/nodes/selected/${id}`, this.buildOpts());
    }

    saveNode(data){
        return http.post(`/v1/nodes`, data, this.buildOpts());
    }

    buildOpts() {
        return {
            headers: ({
                'Authorization': `Bearer ${CurrentUserService.getToken()}`
            })
        };
    }
}

export default new NodeService();