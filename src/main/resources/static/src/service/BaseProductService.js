import axios from 'axios';

export class BaseProductService{
    baseURL = "http://localhost:8080/api";
    //NO MUESTRA RESULTADOS, POSTMAN SI
    getAll(){
        return axios.get(this.baseURL + "/baseProducts/all").then(res => res.data)
    }

    save(baseProduct) {
        return axios.post(this.baseUrl + "/baseProducts", baseProduct).then(res => res.data);
    }

    delete(id) {
        return axios.get(this.baseUrl + "" + id).then(res => res.data);
    }

}