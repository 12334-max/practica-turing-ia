async function login(){
    let datos = {};
    datos.email = document.getElementById('email').value;
    datos.password = document.getElementById('password').value;

    if(datos.email && datos.password != ""){
        const request = await fetch('/api/login',{
                method:'POST',
                headers:{
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                },
                body: JSON.stringify(datos)
            });
            const response = await request.text();
                console.log(response == "Failed" ? "Error: "+response : "Success: "+response)

                if(response != "Failed"){
                    localStorage.token = response;
                    localStorage.email = datos.email;
                    window.location.href='user.html';
                }else{
                    alert("Datos incorrectos");
                }
    }else{
    alert("Los campos deben estar llenados")
    }

}