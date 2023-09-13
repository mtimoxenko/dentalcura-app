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

        
 
        const tessssst = document.querySelector('#test')
        
        fetch(endpoint, config)
        .then((response) => {
          if (response.status === 200) {
            return response.json();
          } else {
            return response.text().then((errorMessage) => {
              throw new Error(errorMessage);
            });
          }
        })
        .then((data) => {
          // Handle success
          console.log('Data:', data);
        })
        .catch((error) => {
          // Handle errors
          console.error(error.message); // Display the error message
          console.log(error.message); // Display the log message
          tessssst.innerText = `${error.message}`;
        });
      




        form.reset()
    })


})
