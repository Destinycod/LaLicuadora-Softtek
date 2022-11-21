$(document).ready(function() {
    loadBaseProducts();
    $('#baseProductsTable').DataTable();
});

async function loadBaseProducts(){
    const request = await fetch('api/baseProducts', {
        method: 'GET',
        headers: getHeaders()
    });
    const baseProducts = await request.json();

     let HtmlList = '';
     for (let baseProduct of baseProducts) {

        let deleteButton = '<a href="#" onclick="deleteBaseProduct(' + baseProducts.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        //let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
        let HtmlBaseProduct = '<tr><td>'+ baseProduct.name +'</td><td>'+
            baseProduct.price +' '+
            baseProduct.description + '</td><td>'+
            baseProduct.manufacturingTime +'</td><td>'+
            baseProduct.active + '</td><td>' +
            deleteButton + '</td></tr>';

        HtmlList += HtmlBaseProduct;
     }

    document.querySelector('#baseProductsTable tbody').outerHTML = HtmlList;

}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     //'Authorization': localStorage.token
   };
}

async function deleteBaseProduct(id) {
    if (!confirm('¿Desea eliminar este usuario?')) {
        return;
    }
    const request = await fetch('api/baseProducts/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload();
}