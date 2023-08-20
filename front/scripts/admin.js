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
  
  
    function renderDentist(){
      const selectDentist = document.querySelector('#dentist-table ul')
  
      fetch(endpointDentist)
        .then(response=>response.json())
        .then(data=>{
          console.log(data);
          data.forEach(dentist=>{
            selectDentist.innerHTML+=`
            <li>${dentist.nombre} ${dentist.apellido} Lic. ${dentist.licencia}
            <div></div>
            </li>`
          })
        })
    }
  
    renderDentist()

  
    function renderPatient(){
        const selectDentist = document.querySelector('#patient-table ul')
    
        fetch(endpointPatient)
          .then(response=>response.json())
          .then(data=>{
            console.log(data);
            data.forEach(patient=>{
              selectDentist.innerHTML+=`
              <li>${patient.nombre} ${patient.apellido} NI Number ${patient.documento}
              <div></div>
              </li>`
            })
          })
      }
    
      renderPatient()




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
  
    function getUserName() {
      const settings = {
        method: 'GET',
        headers: {
          authorization: token
        }
      };
  
      fetch(endpointGetUser, settings)
        .then(response => response.json())
        .then(data => {
          const usrName = document.querySelector('.user-info p');
          usrName.innerText = data.firstName;
        })
        .catch(error => console.log(error));
    }
  
  
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
          btnChangeState();
          btnDeleteAppointment();
        })
        .catch(error => console.log(error));
    };
  
  

  
  

  
    /* ---------------------------------------------------------------------- */
    /*                  [6] FUNCTION: Change appointment state [PUT]                 */
    /* ---------------------------------------------------------------------- */
    function btnChangeState() {
      const btnCambioEstado = document.querySelectorAll('.change');
  
      btnCambioEstado.forEach(btn => {
  
        btn.addEventListener('click', function (event) {
  
          const id = event.target.id;
          const url = `${enpointAppointment}/${id}`
          const payload = {};
  
          if (event.target.classList.contains('incompleta')) {
            payload.completed = false;
          } else {
            payload.completed = true;
          }
  
          const settings = {
            method: 'PUT',
            headers: {
              "Authorization": token,
              "Content-type": "application/json"
            },
            body: JSON.stringify(payload)
          }
          fetch(url, settings)
            .then(response => {
              console.log(response.status);
              getAppointment();
            })
        })
      });
  
    }
  
  
    /* -------------------------------------------------------------------------- */
    /*                     [7] FUNCTION: Delete appointment [DELETE]                    */
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
  