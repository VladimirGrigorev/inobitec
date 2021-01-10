import http from "../http-common";
import CurrentUserService from "../service/CurrentUserService";

class NodeService {

    getNodesWithParentId(parentId){
        return http.get(`/v1/nodes/${parentId}`, this.buildOpts());
    }

    getRootNodes(){
        return http.get(`/v1/nodes/root`, this.buildOpts());
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