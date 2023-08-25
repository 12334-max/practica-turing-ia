// Call the dataTables jQuery plugin
$(document).ready(function() {
// al momento de cargar la página
});

async function registerUser(){
    let datos = {};
    datos.name = document.getElementById('name').value;
    datos.lastname = document.getElementById('lastname').value;
    datos.phone = document.getElementById('phone').value;
    datos.email = document.getElementById('email').value;
    datos.password = document.getElementById('password').value;

    repeatPassword = document.getElementById('repeatPassword').value;

    if(repeatPassword != datos.password){
        alert('La contraseña debe ser igual');
        return;
    }

    const request = await fetch('/api/create',{
        method:'POST',
        headers:{
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body: JSON.stringify(datos)
    });

    console.log("Este es la Respuesta de la pregunta: "+request.body)

    if(request == "Ok"){
        window.location.href="user.html";
    }else{
        alert("No se ha registrado el usuario");
    }
}