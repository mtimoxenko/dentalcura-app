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
                    console.log(response);
                    response.json()})
                .then(data => {
                    console.log(data)
                    sessionStorage.setItem('userName', JSON.stringify(payload.name))

                    if(payload.admin){
                        location.replace('./admin.html')
                    }
                    else if(!payload.admin){                  
                        location.replace('./tasks.html')
                    }
                }).catch(err => {
                    console.log(err)
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
    


})
