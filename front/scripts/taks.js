// if (!localStorage.jwt) {
//   location.replace('./index.html');
// }


window.addEventListener('load', function () {
  /* ------------------------- AOS lib. init -------------------------------- */
  AOS.init();

  // https://todo-api.ctd.academy/#/tasks/getOneTask
  // const enpointAppointment = 'https://todo-api.ctd.academy/v1/tasks';
  // https://todo-api.ctd.academy/#/users/getMe
  // const endpointGetUser = 'https://todo-api.ctd.academy/v1/users/getMe';
  // const token = JSON.parse(localStorage.jwt);
  const endpointDentist = './json/dentista.json'
  const endpointTurno = './json/turno.json'
  const endpointPatient = './json/paciente.json'

  const formAddAppointment = document.querySelector('.new-appointment');
  const btnCloseApp = document.querySelector('#closeApp');

  // getUserName();
  // getDentistList();
  // getAppointment();
  

  // function getDentistList(){
  //   const settings = {
  //     method: 'GET',
  //     headers: {
  //       authorization: token
  //     }
  //   };

  //   fetch(endpointDentist, settings)
  //     .then(response => response.json())
  //     .then(dentist => {

  //       renderDentist(dentist);
  //     })
  //     .catch(error => console.log(error));
  // }

  function renderPatient(){
    const selectPatient = document.getElementById('select-patient')

    fetch(endpointPatient)
      .then(response=>response.json())
      .then(data=>{
        console.log(data);
        data.forEach(patient=>{
          selectPatient.innerHTML+=`
          <div><input type="radio" name="patient" id="${patient.documennto}">
          <label for="${patient.documento}">${patient.nombre} ${patient.apellido}</label>
          </div>`
        })
      })
  }

  renderPatient()


  function renderDentist(){
    const selectDentist = document.getElementById('select-dentist')


    fetch(endpointDentist)
      .then(response=>response.json())
      .then(data=>{
        console.log(data);
        data.forEach(dentist=>{
          selectDentist.innerHTML+=`
          <div><input type="radio" name="dentist" id="${dentist.licencia}">
          <label for="${dentist.licencia}">${dentist.nombre} ${dentist.apellido}</label>
          </div>`
        })
      })
  }

  renderDentist()

  function renderTurno(){
    const selectTurno = document.getElementById('select-turno')


    fetch(endpointTurno)
      .then(response=>response.json())
      .then(data=>{
        console.log(data);
        data.forEach(turno=>{
          selectTurno.innerHTML+=`
          <div><input type="radio" name="turno" id="${turno.fecha_hora}">
          <label for="${turno.fecha_hora}">${turno.fecha_hora}</label>
          </div>`
        })
      })
  }

  renderTurno()

    /* -------------------------------------- */
    /*           [1] FUNCTION: Logout         */
    /* -------------------------------------- */

  btnCloseApp.addEventListener('click', function () {

    Swal.fire({
      title: 'Are you leaving?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.clear();
        location.replace('./index.html');
      }
    });

  });

  /* ------------------------------------------------------------------ */
  /*                 [2] FUNCTION: Get user name [GET]                */
  /* ------------------------------------------------------------------ */

  // function getUserName() {
  //   const settings = {
  //     method: 'GET',
  //     headers: {
  //       authorization: token
  //     }
  //   };

  //   fetch(endpointGetUser, settings)
  //     .then(response => response.json())
  //     .then(data => {
  //       const usrName = document.querySelector('.user-info p');
  //       usrName.innerText = data.firstName;
  //     })
  //     .catch(error => console.log(error));
  // }


  /* ----------------------------------------------------------------- */
  /*                 [3] FUNCTION: Get Appointment list [GET]                */
  /* ----------------------------------------------------------------- */

  function getAppointment() {
    const settings = {
      method: 'GET',
      headers: {
        authorization: token
      }
    };

    fetch(enpointAppointment, settings)
      .then(response => response.json())
      .then(appointment => {

        renderAppointment(appointment);
        btnDeleteAppointment();
      })
      .catch(error => console.log(error));
  };


  /* ------------------------------------------------------------------- */
  /*                    [4] FUNCTION: Add Appointment [POST]                    */
  /* ------------------------------------------------------------------- */

  formAddAppointment.addEventListener('submit', function (event) {
    event.preventDefault();

    const dentist = document.querySelectorAll('[name=dentist]');
    const turno = document.querySelectorAll('[name=turno]');

    const payload = {
      dentist: {},
      patient: "user",
      turno: ""
    };

    dentist.forEach(dentist=>{
      if(dentist.checked){
        payload.dentist = dentist
      }
    })

    turno.forEach(turno=>{
      if(turno.checked){
        payload.turno = turno
      }
    })

    console.log(payload);


    const settings = {
      method: 'POST',
      body: JSON.stringify(payload),
      headers: {
        'Content-Type': 'application/json',
        authorization: token
      }
    }

    fetch(enpointAppointment, settings)
      .then(response => response.json())
      .then(response => {
        // console.log(response.status);
        getAppointment();
      })
      .catch(error => console.log(error));

    formAddAppointment.reset();
  })


  /* ----------------------------------------------------------- */
  /*                  [5] FUNCTION: Render Appointment           */
  /* ----------------------------------------------------------- */
  function renderAppointment(listado) {

    // clear all Appointment
    const appointmentToDo = document.querySelector('.appointment-pendientes');
    appointmentToDo.innerHTML = "";


    listado.forEach(appointment => {

      // let date = new Date(appointment.createdAt);

        appointmentToDo.innerHTML += `
          <li class="tarea" data-aos="flip-up">
            <button class="borrar" id="${appointment.id}"><i class="fa-regular fa-trash-can"></i></button>
            <div class="descripcion">
              <p class="nombre">${appointment.description}</p>
              <p class="timestamp">${appointment.toLocaleDateString()}</p>
            </div>
          </li>`
    })
  }



  /* -------------------------------------------------------------------------- */
  /*                     [6] FUNCTION: Delete appointment [DELETE]                    */
  /* -------------------------------------------------------------------------- */
  function btnDeleteAppointment() {
  
    const btnDelete = document.querySelectorAll('.borrar');

    btnDelete.forEach(btn => {

      btn.addEventListener('click', function (event) {
        Swal.fire({
          title: 'Delete task?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel'
        }).then((result) => {
          if (result.isConfirmed) {

            const id = event.target.id;
            const url = `${enpointAppointment}/${id}`

            const settings = {
              method: 'DELETE',
              headers: {
                "Authorization": token,
              }
            }
            fetch(url, settings)
              .then(response => {
                console.log(response.status);
                getAppointment();
              })

            Swal.fire(
              'Appointment deleted.',
            );

          }
        });

      })
    });
  }

});
