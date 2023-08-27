<<<<<<< HEAD
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

    

=======
window.addEventListener('load', function () {

    const form = this.document.querySelector('form')
    // https://todo-api.ctd.academy/#/users/loginUser
    




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
    const endpointLogin = 'http://localhost:8080/user/login';

    function loginUser(config) {

        fetch(endpointLogin, config)
        .then((response) => response.json())
        .then((data) => {
            console.log("Promise OK!");
            console.log(data);

            if (data == 1) {
                // redirecting to the page
                location.replace('./tasks.html');
            }else if(data == 33) {
                // redirecting to the page
                location.replace('./root.html');
            }else{
                console.log("Datos incorrectos");
            }

        }).catch(err => {
            console.log("Promise rejected...");
            console.log(err);      
        });
        
    };




    /* TEST API CUSTOM */
    renderPacienteDentista();

    function renderPacienteDentista() {
        const pacienteNombre = document.querySelector('#pacienteNombre');
        const pacienteApellido = document.querySelector('#pacienteApellido');

        const endpointJson = './json/paciente.json';
        const endpointPacienteJava = 'http://localhost:8080/patient';



        fetch(endpointPacienteJava)
          .then(response => response.json())
          .then(data => {
            console.log(data);
            pacienteNombre.innerText = "Paciente nombre: " + `${data[0].name}`;
            pacienteApellido.innerText = "Paciente apellido: " + `${data[0].surname}`;
          })
          .catch(error => console.log(error));
      }







            


    

          

>>>>>>> c8b40bcb99acdaa4ee9d42c0fe2d820099888d0e
});