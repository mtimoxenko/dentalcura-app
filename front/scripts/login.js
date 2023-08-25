window.addEventListener('load', function () {

    const form = document.querySelector('form')
    const email = document.querySelector('#inputEmail')
    const password = document.querySelector('#inputPassword')
    const endpointLogin = 'http://localhost:8080/user'


    form.addEventListener('submit', function (event) {
        event.preventDefault()

        const payload = {
            email: email.value,
            password: password.value
        }

        const config = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(payload),   
        }

        loginUser(config)
        form.reset()

    })


    function loginUser(config) {

        fetch(endpointLogin, config)
        .then((response) => response.json())
        .then((data) => {
            console.log("Promise OK!");
            console.log(data);

            //if (data.jwt) {
            //    localStorage.setItem('jwt', JSON.stringify(data.jwt));

                if (data.admin) {
                    location.replace('./admin.html')
                }
                location.replace('./tasks.html')
            //}

        }).catch(err => {
            console.log("Promise rejected...");
            console.log(err);      
        })
    }

    

});