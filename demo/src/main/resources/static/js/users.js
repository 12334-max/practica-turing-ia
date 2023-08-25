// Call the dataTables jQuery plugin
$(document).ready(function() {
    getUsers();
  $('#users').DataTable();
  viewEmail();
});

function viewEmail(){
    document.getElementById('email-avatar').outerHTML = localStorage.email;
}

function getHeaders() {
    return {
        'Accept':'application/json',
        'Content-Type':'application/json',
        'Authorization':localStorage.token
    }
}

async function getUsers(){
const request = await fetch('/api/users',{
    method:'GET',
    headers:getHeaders(),
});

const response = await request.json();

console.log('er :::> '+response)

let listHtml = '';

for(let t of response){
    btnDelete = '<a href="#" onclick="deleteUser('+t.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let html = `<tr>
            <td>`+t.id+`</td>
            <td>`+t.name+` `+t.lastname+`</td>
            <td>`+t.phone+`</td>
            <td>`+t.email+`</td>
            <td>
                `+btnDelete+`
            </td>
        </tr>
    `;
    listHtml += html;
}
document.querySelector('#users tbody').outerHTML = listHtml;

console.log(response);

}

async function deleteUser(id){
    if(!confirm('¿Deseas borrar este Usuario?')){return;}

    const request = await fetch('/api/delete/'+id,{
        method:'DELETE',
        headers: getHeaders(),
    });
    console.log(request.body);
    location.reload();
}
