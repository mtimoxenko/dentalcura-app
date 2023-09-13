window.addEventListener('load', function () {

    const form = document.querySelector('form')
    const endpoint = 'http://localhost:8080/user'

    const name = document.querySelector('#inputName')
    const surname = document.querySelector('#inputSurname')
    const email = document.querySelector('#inputEmail')
    const pass = document.querySelector('#inputPassword')
    const admin = document.querySelector('[name=role]')




    form.addEventListener('submit', function (event) {
        event.preventDefault()

        const payload = {
            name: name.value,
            surname: surname.value,
            email: email.value,
            password: pass.value,
            admin: false
        }

        if(admin.checked){
            payload.admin = true
        }

        console.log(payload)

        const config = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(payload),   
        }


        if (payload.name == '' || payload.name.includes(' ') || !isNaN(payload.name)) {
            errorMessage()
        }
        else if(payload.surname == '' || payload.surname.includes(' ') || !isNaN(payload.surname)){
            errorMessage()
        }
        else if(payload.email == '' || payload.email.includes(' ') || !payload.email.includes('@')){
            errorMessage()
        }
        else if (payload.password == '' || payload.password.includes(' ')) {
            errorMessage()
        }
        else{







            fetch(endpoint, config)
                .then((response) => {
                    if (response.status === 200) {
                        console.log(response);
                        return response.json();
                    } else {
                        return response.text()
                        .then((errorMessage) => {
                            throw new Error(errorMessage);
                        });
                    }
                })
                .then((data) => {
                    // Handle success
                    console.log('Data:', data);
                    sessionStorage.setItem('userName', JSON.stringify(payload.name))

                    if(!response.ok){
                        errorResponse(payload)
                    }
                    else if(payload.admin && response.ok){
                        location.replace('./admin.html')
                    }
                    else if(!payload.admin && response.ok){                  
                        location.replace('./tasks.html')
                    }   
                })
                .catch((error) => {
                    console.error(error.message); // Display the error message
                    const timoTest = document.querySelector('#timoTest')
                    timoTest.innerText = `${error.message}`;
                })










               
            form.reset()            
        }
    })


    function errorMessage(){
        const bugBox = document.querySelector('#errores')
    
        if (bugBox) {
            bugBox.remove()
        }
    
        const divTemplate = document.createElement('div')
        divTemplate.setAttribute('id', 'errores')
        divTemplate.style = "background:rgba(255, 0, 0, 0.3);padding:.5em 1em;color: white;margin-top: 1em;"
        divTemplate.innerHTML += `<p><small>You must complete data correctly, without leaving empty fields or spaces</small></p>`
        form.appendChild(divTemplate)
    }

    function errorResponse(info){
        const bugBox = document.querySelector('#errores')
    
        if (bugBox) {
            bugBox.remove()
        }
    
        const divTemplate = document.createElement('div')
        divTemplate.setAttribute('id', 'errores')
        divTemplate.style = "background:rgba(255, 0, 0, 0.3);padding:.5em 1em;color: white;margin-top: 1em;"
        divTemplate.innerHTML = `<p><small>Email ${info.email} is already in use</small></p>`
        form.appendChild(divTemplate)
    }
    


})
