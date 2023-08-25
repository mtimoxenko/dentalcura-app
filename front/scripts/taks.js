// if (!localStorage.jwt) {
//   location.replace('./index.html');
// }


window.addEventListener('load', function () {
  /* ------------------------- AOS lib. init -------------------------------- */
  AOS.init();

  // const endpointGetUser = 'https://todo-api.ctd.academy/v1/users/getMe';
  // const token = JSON.parse(localStorage.jwt);

  const endpointDentist = 'http://localhost:8080/dentist'
  const endpointTurno = './json/turno.json'
  const endpointPatient = 'http://localhost:8080/patient'
  const endpointAppointment = 'http://localhost:8080/appointment'


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
          <div><input type="radio" name="patient" id="${patient.niNumber}">
          <label for="${patient.niNumber}">${patient.name} ${patient.surname}</label>
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
          <div><input type="radio" name="dentist" id="${dentist.licenceNumber}">
          <label for="${dentist.licenceNumber}">${dentist.name} ${dentist.surname}</label>
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

    fetch(endpointAppointment, settings)
      .then(response => response.json())
      .then(response => {
        // console.log(response.status);
        renderAppointment();
      })
      .catch(error => console.log(error));

    formAddAppointment.reset();
  })


  /* ----------------------------------------------------------- */
  /*                  [5] FUNCTION: Render Appointment           */
  /* ----------------------------------------------------------- */
  function renderAppointment() {

    const appointmentToDo = document.querySelector('#appointment-list');
    appointmentToDo.innerHTML = "";

    fetch(endpointAppointment)
    .then(response=>response.json())
    .then(data=>{
      console.log(data)
      data.forEach(appointment=>{
        appointmentToDo.innerHTML+=`
        <li>Date: ${appointment.date} Dentist: ${appointment.dentistFullName}, Patient: ${appointment.patientFullName}
        </li>`
        btnDeleteAppointment()
      })
    })
  }

renderAppointment()

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
            const url = `${endpointAppointment}/${id}`

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
