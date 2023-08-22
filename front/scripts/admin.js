// if (!localStorage.jwt) {
//   location.replace('./index.html');
// }


window.addEventListener('load', function () {
    /* ------------------------- AOS lib. init -------------------------------- */
    AOS.init();
  

    // const token = JSON.parse(localStorage.jwt);
    
    const endpointDentist = 'http://localhost:8080/dentist'
    const endpointPatient = 'http://localhost:8080/patient'

  
    const btnCloseApp = document.querySelector('#closeApp');
  
    // getUserName();
    // getDentistList();
    // getAppointment();
    


    /* ------------------------------------------------------------------------------- */
    /*                 [0] FUNCTION: Render Dentist & Patient [GET]                    */
    /* ------------------------------------------------------------------------------- */
  
  
    function renderDentist(){
      const selectDentist = document.querySelector('#dentist-table ul')

      const settings = {
        method: 'GET',
      }
  
      fetch(endpointDentist, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data);
          data.forEach(dentist=>{
            selectDentist.innerHTML+=`
            <li>${dentist.name} ${dentist.surname} Lic. ${dentist.licenseNumber}
            <div></div>
            </li>`
          })
        })
    }
  
    renderDentist()

  
    function renderPatient(){
        const selectDentist = document.querySelector('#patient-table ul')

        const settings = {
          method: 'GET',
        }
    
        fetch(endpointPatient, settings)
          .then(response=>response.json())
          .then(data=>{
            console.log(data);
            data.forEach(patient=>{
              selectDentist.innerHTML+=`
              <li>${patient.name} ${patient.surname} NI Number ${patient.niNumber}
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
    /*                 [2] FUNCTION: Get user name [GET]                  */
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
  


    /* ----------------------------------------------------------------------------------- */
    /*                 [3] FUNCTION: Search By Id Dentist & Patient [GET]                  */
    /* ----------------------------------------------------------------------------------- */


    function searchDentistById(){

      const searchButton = document.querySelector('.dentist-search button')
      const name = document.querySelector('.dentist-update #inputName')
      const surname = document.querySelector('.dentist-update #inputSurname')
      const licenseNumber = document.querySelector('.dentist-update #inputLicenseNumber')

      const searchDentistId = document.getElementById('inputDentistId')


      searchButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = searchDentistId.value
        const url = `${endpointDentist}/${id}`

        const settings = {
          method: 'GET',
        }

        fetch(url, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data);
          data.forEach(dentist=>{

            if(id == dentist.id){
              name.setAttribute('placeholder', dentist.name)
              surname.setAttribute('placeholder', dentist.surname)
              licenseNumber.setAttribute('placeholder', dentist.licenseNumber)
            }
          })
        })
      })
    }

    searchDentistById()


    function searchPatientById(){

      const searchButton = document.querySelector('.patient-search button')
      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const niNumber = document.querySelector('.patient-update #inputNiNumber')
      const streetName = document.querySelector('.patient-update, #inputStreetName')
      const streetNumber = document.querySelector('.patient-update, #inputStreetNumber')
      const floor = document.querySelector('.patient-update, #inputFloor')
      const department = document.querySelector('.patient-update, #inputDepartment')

      const searchPatienttId = document.getElementById('inputPatienttId')


      searchButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = searchPatienttId.value
        const url = `${endpointPatient}/${id}`

        const settings = {
          method: 'GET',
        }

        fetch(url, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data);
          data.forEach(patient=>{

            if(id == patient.id){
              name.setAttribute('placeholder', patient.name)
              surname.setAttribute('placeholder', patient.surname)
              niNumber.setAttribute('placeholder', patient.niNumber)
              streetName.setAttribute('placeholder', patient.streetName)
              streetNumber.setAttribute('placeholder', patient.streetNumber)
              floor.setAttribute('placeholder', patient.floor)
              department.setAttribute('placeholder', patient.department)
            }
          })
        })
      })
    }

    searchPatientById()


    /* ----------------------------------------------------------------------------------- */
    /*                 [4] FUNCTION: Load Dentist & Patient [POST]                         */
    /* ----------------------------------------------------------------------------------- */


    function registerNewDentist(config) {
    
      fetch(endpointDentist, config)
          .then((response) => response.json())
          .then((data) => {
              console.log(data);
              renderDentist()
          }).catch(err => {
              console.log(err);      
          });
    }


    function dentistLoad(){

      const dentistLoadButton = document.querySelector('.dentist-load button')
      const name = document.querySelector('.dentist-load, #inputName')
      const surname = document.querySelector('.dentist-load, #inputSurname')
      const licenseNumber = document.querySelector('.dentist-load, #inputLicenseNumber')

      const payload = {
        name: name.value,
        surname: surname.value,
        licenseNumber: licenseNumber.value
      }

      const config = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify(payload),
      }

      dentistLoadButton.addEventListener('click', function(e){
        e.preventDefault()

        registerNewDentist(config)
      })
    }


    dentistLoad()


    function registerNewPatient(config) {

      fetch(endpointPatient, config)
          .then((response) => response.json())
          .then((data) => {
              console.log(data);
              renderPatient()
          }).catch(err => {
              console.log(err);      
          });
    }

    function patientLoad(){

      const patientLoadButton = document.querySelector('.patient-load button')
      const name = document.querySelector('.patient-load, #inputName')
      const surname = document.querySelector('.patient-load, #inputSurname')
      const niNumber = document.querySelector('.patient-load, #inputNiNumber')
      const streetName = document.querySelector('.patient-load, #inputStreetName')
      const streetNumber = document.querySelector('.patient-load, #inputStreetNumber')
      const floor = document.querySelector('.patient-load, #inputFloor')
      const department = document.querySelector('.patient-load, #inputDepartment')


      const payload = {
        name: name.value,
        surname: surname.value,
        niNumber: niNumber.value,
        streetName: streetName.value,
        streetNumber: streetNumber.value,
        floor: floor.value,
        department: department.value
      }


      const config = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify(payload),
      }


      patientLoadButton.addEventListener('click', function(e){
        e.preventDefault()

        registerNewPatient(config)
      })
    }

    patientLoad()


});
  