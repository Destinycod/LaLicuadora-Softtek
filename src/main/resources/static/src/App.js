import './App.css';
import React, { Component } from 'react';
import { BaseProductService } from './service/BaseProductService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import {Menubar} from 'primereact/menubar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
//import {Growl} from 'primereact/growl';

import 'primereact/resources/themes/nova/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default class App extends Component{

    constructor(){
        super();
        this.state = {
            visible : false,
            baseProduct: {
                id: null,
                name: null,
                price: null,
                description: null,
                manufacturingTime : null,
                active: null
            },
            selectedBaseProduct : { }
        };
        this.items = [
            {
              label : 'New',
              icon  : 'pi pi-fw pi-plus',
              command : () => {this.showSaveDialog()}
            },
            {
              label : 'Edit',
              icon  : 'pi pi-fw pi-pencil',
              command : () => {this.showEditDialog()}
            },
            {
              label : 'Delete',
              icon  : 'pi pi-fw pi-trash',
              command : () => {this.delete()}
            }
        ];
        this.baseProductService = new BaseProductService();
        this.save = this.save.bind(this);
        this.delete = this.delete.bind(this);
        this.footer = (
            <div>
                <Button label="Save" icon="pi pi-check" onClick={this.save} />
            </div>
        );
    }

    componentDidMount(){
        this.baseProductService.getAll().then(data => this.setState({baseProducts: [data]}))
    }

    save() {
        this.BaseProductService.save(this.state.baseProduct).then(data => {
            this.setState({
                visible : false,
                baseProduct: {
                    id: null,
                    name: null,
                    price: null,
                    description: null,
                    manufacturingTime : null,
                    active: null
                }
            });
            //this.growl.show({severity: 'success', summary: 'Atention!', detail: 'Record saved successfully.'});
            this.baseProductService.getAll().then(data => this.setState({baseProducts: data}))
        })
    }
    
    delete() {
        if(window.confirm("Do you want to delete the record?")) {
            this.baseProductService.delete(this.state.selectedBaseProduct.id).then(data => {
                //this.growl.show({severity: 'success', summary: 'Atention!', detail: 'Record deleted successfully.'});
                this.baseProductService.getAll().then(data => this.setState({baseProducts: data}));
            });
        }
    }
    

    render(){
        return(
            <div style={{width:'80%', margin: '0 auto', marginTop: '20px'}}>
            <Menubar model={this.items}/>
            <br/>
            <Panel header="CRUD App">
                <DataTable value={this.state.baseProducts} paginator={true} rows="4" selectionMode="single" selection={this.state.selectedBaseProduct} onSelectionChange={e => this.setState({selectedBaseProduct: e.value})}>
                    <Column field="id" header="Id"></Column>
                    <Column field="name" header="Name"></Column>
                    <Column field="price" header="Price"></Column>
                    <Column field="description" header="Description"></Column>
                    <Column field="manufacturingTime" header="ManufacturingTime"></Column>
                    <Column field="active" header="Active"></Column>
                </DataTable>
            </Panel>

            <Dialog header="Create base product" visible={this.state.visible} style={{width: '400px'}} footer={this.footer} modal={true} onHide={() => this.setState({visible: false})}>
                <form id="base-product-form">
                    <span className="p-float-label">
                        <InputText value={this.state.baseProduct.name} style={{width : '100%'}} id="name" onChange={(e) => {
                            let val = e.target.value;
                            this.setState(prevState => {
                                let baseProduct = Object.assign({}, prevState.baseProduct);
                                baseProduct.name = val;

                                return { baseProduct };
                            })}
                        } />
                        <label htmlFor="name">Name</label>
                    </span>
                    <br/>
                    <span className="p-float-label">
                        <InputText value={this.state.baseProduct.price} style={{width : '100%'}} id="price" onChange={(e) => {
                            let val = e.target.value;
                            this.setState(prevState => {
                                let baseProduct = Object.assign({}, prevState.baseProduct);
                                baseProduct.price = val

                                return { baseProduct };
                            })}
                        } />
                        <label htmlFor="price">Price</label>
                    </span>
                    <br/>
                    <span className="p-float-label">
                        <InputText value={this.state.baseProduct.description} style={{width : '100%'}} id="description" onChange={(e) => {
                            let val = e.target.value;
                            this.setState(prevState => {
                                let baseProduct = Object.assign({}, prevState.baseProduct);
                                baseProduct.description = val

                                return { baseProduct };
                            })}
                        } />
                        <label htmlFor="description">Description</label>
                    </span>
                    <br/>
                    <span className="p-float-label">
                        <InputText value={this.state.baseProduct.manufacturingTime} style={{width : '100%'}} id="manufacturingTime" onChange={(e) => {
                            let val = e.target.value;
                            this.setState(prevState => {
                                let baseProduct = Object.assign({}, prevState.baseProduct);
                                baseProduct.manufacturingTime = val

                                return { baseProduct };
                            })}
                        } />
                        <label htmlFor="manufacturingTime">Manufacturing time</label>
                    </span>
                    <br/>
                    <span className="p-float-label">
                        <InputText value={this.state.baseProduct.active} style={{width : '100%'}} id="active" onChange={(e) => {
                            let val = e.target.value;
                            this.setState(prevState => {
                                let baseProduct = Object.assign({}, prevState.baseProduct);
                                baseProduct.active = val

                                return { baseProduct };
                            })}
                        } />
                        <label htmlFor="active">Active</label>
                    </span>
                </form>
            </Dialog>
        </div>
        );
    }

    showSaveDialog(){
        this.setState({
            visible : true,
            baseProduct: {
                id: null,
                name: null,
                price: null,
                description: null,
                manufacturingTime : null,
                active: null
            }
        });
        document.getElementById('base-product-form').reset();
    }
    
    showEditDialog() {
        this.setState({
            visible : true,
            baseProduct : {
                id: this.state.selectedBaseProduct.id,
                name: this.state.selectedBaseProduct.name,
                price: this.state.selectedBaseProduct.price,
                description: this.state.selectedBaseProduct.description,
                manufacturingTime : this.state.selectedBaseProduct.manufacturingTime,
                active : this.state.selectedBaseProduct.active
            }
        })
    }
}
