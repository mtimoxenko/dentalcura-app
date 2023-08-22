window.addEventListener('load', function () {

    const form = this.document.querySelector('form')
    // https://todo-api.ctd.academy/#/users/registerUser
    const endpointSingUp = 'https://todo-api.ctd.academy/v1/users';


    /* ----------------------------------------------------------------------- */
    /*           [1] FUNCTION: Listen to submit & prepare to send data         */
    /* ----------------------------------------------------------------------- */
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const payload = getData();
        const config = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(payload),   
        }

        registerNewUser(config);
        form.reset();
    });


    /* ------------------------------------------------------------- */
    /*                 [2] FUNCTION: Get form data                   */
    /* ------------------------------------------------------------- */
    function getData() {
        const name = document.querySelector('#inputName');
        const surname = document.querySelector('#inputSurname');
        const email = document.querySelector('#inputEmail');
        const pass = document.querySelector('#inputPassword');
        const passrepeat = document.querySelector('#inputPasswordRepeat');

        let object = {
            firstName: name.value,
            lastName: surname.value,
            email: email.value,
            password: pass.value
        }

        return object;
    }

    /* --------------------------------------------------------------- */
    /*                   [3] FUNCTION: Signup [POST]                   */
    /* --------------------------------------------------------------- */
    function registerNewUser(config) {
        
        fetch(endpointSingUp, config)
            .then((response) => response.json())
            .then((data) => {
                console.log("Promise OK!");
                console.log(data);

                if (data.jwt) {
                    // saving jwt to localstorage
                    localStorage.setItem('jwt', JSON.stringify(data.jwt));

                    // redirecting to the page
                    location.replace('./tasks.html');
                };
                
            }).catch(err => {
                console.log("Promise rejected...");
                console.log(err);      
            });

    };

});
