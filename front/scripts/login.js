window.addEventListener('load', function () {

<<<<<<< HEAD
    const form = document.querySelector('form')
    const email = document.querySelector('#inputEmail')
    const password = document.querySelector('#inputPassword')
    const endpointLogin = 'http://localhost:8080/user'
=======
    const form = this.document.querySelector('form')
    // https://todo-api.ctd.academy/#/users/loginUser
    
>>>>>>> f7e63896c5b2a422431db4f1c1822cad8abfe16a


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


<<<<<<< HEAD
=======
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

>>>>>>> f7e63896c5b2a422431db4f1c1822cad8abfe16a
    function loginUser(config) {

        fetch(endpointLogin, config)
        .then((response) => response.json())
        .then((data) => {
            console.log("Promise OK!");
            console.log(data);

<<<<<<< HEAD
            //if (data.jwt) {
            //    localStorage.setItem('jwt', JSON.stringify(data.jwt));

                if (data.admin) {
                    location.replace('./admin.html')
                }
                location.replace('./tasks.html')
            //}
=======
            if (data == 1) {
                // redirecting to the page
                location.replace('./tasks.html');
            }else if(data == 33) {
                // redirecting to the page
                location.replace('./root.html');
            }else{
                console.log("Datos incorrectos");
            }
>>>>>>> f7e63896c5b2a422431db4f1c1822cad8abfe16a

        }).catch(err => {
            console.log("Promise rejected...");
            console.log(err);      
<<<<<<< HEAD
        })
    }
=======
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







            

>>>>>>> f7e63896c5b2a422431db4f1c1822cad8abfe16a

    

});