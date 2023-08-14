window.addEventListener('load', function () {

    const form = this.document.querySelector('form')
    // https://todo-api.ctd.academy/#/users/loginUser
    const endpointLogin = 'https://todo-api.ctd.academy/v1/users/login';


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

        loginUser(config);
        form.reset();

    });


    /* ------------------------------------------------------------- */
    /*                 [2] FUNCTION: Get form data                   */
    /* ------------------------------------------------------------- */
    function getData() {
        const email = document.querySelector('#inputEmail');
        const pass = document.querySelector('#inputPassword');
        
        let object = {
            email: email.value,
            password: pass.value,
        }

        return object;
    }


    /* ---------------------------------------------------------------------- */
    /*                     [3] FUNCTION: User login [POST]                    */
    /* ---------------------------------------------------------------------- */
    function loginUser(config) {

        fetch(endpointLogin, config)
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