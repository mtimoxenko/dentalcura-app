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
      selectDentist.innerHTML = ''

      const settings = {
        method: 'GET',
      }
   
      fetch(endpointDentist, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data)
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
        const selectPatient = document.querySelector('#patient-table ul')
        selectPatient.innerHTML = ''

        const settings = {
          method: 'GET',
        }
    
        fetch(endpointPatient, settings)
          .then(response=>response.json())
          .then(data=>{
            console.log(data);
            data.forEach(patient=>{
              selectPatient.innerHTML+=`
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
              name.setAttribute('placeholder', data.name)
              surname.setAttribute('placeholder', data.surname)
              licenseNumber.setAttribute('placeholder', data.licenseNumber)
              dentistDelete()
              updateDentist()
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

      const searchPatientId = document.getElementById('inputPatientId')


      searchButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = searchPatientId.value
        const url = `${endpointPatient}/${id}`

        const settings = {
          method: 'GET',
        }

        fetch(url, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data);
            name.setAttribute('placeholder', data.name)
            surname.setAttribute('placeholder', data.surname)
            niNumber.setAttribute('placeholder', data.niNumber)
            streetName.setAttribute('placeholder', data.streetName)
            streetNumber.setAttribute('placeholder', data.streetNumber)
            floor.setAttribute('placeholder', data.floor)
            department.setAttribute('placeholder', data.department)
        })
      })
    }

    searchPatientById()


    /* ----------------------------------------------------------------------------------- */
    /*                 [4] FUNCTION: Load Dentist & Patient [POST]                         */
    /* ----------------------------------------------------------------------------------- */


    function dentistLoad(){

      const form = document.querySelector('.dentist-load')
      const name = document.querySelector('.dentist-load #inputName')
      const surname = document.querySelector('.dentist-load #inputSurname')
      const licenseNumber = document.querySelector('.dentist-load #inputLicenseNumber')
      const id = document.querySelector('.dentist-load #inputId')

      form.addEventListener('submit', function(e){
        e.preventDefault()

        const payload = {
          id: id.value,
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

      fetch(endpointDentist, config)
          .then((response) => response.json())
          .then(data=>{
              console.log(data);
          renderDentist()  
          }).catch(err => {
              console.log(err);      
          })
        
          form.reset()
      })
    }

    dentistLoad()


    function patientLoad(){

      const form = document.querySelector('.patient-load')
      const name = document.querySelector('.patient-load #inputName')
      const surname = document.querySelector('.patient-load #inputSurname')
      const niNumber = document.querySelector('.patient-load #inputNiNumber')
      const streetName = document.querySelector('.patient-load #inputStreetName')
      const streetNumber = document.querySelector('.patient-load #inputStreetNumber')
      const floor = document.querySelector('.patient-load #inputFloor')
      const department = document.querySelector('.patient-load #inputDepartment')


      form.addEventListener('submit', function(e){
        e.preventDefault()      

        const payload = {
          id: 7,
          name: name.value,
          surname: surname.value,
          niNumber: niNumber.value,
          registrationDate: "Origin of the Universe",
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

        console.log(payload);

        fetch(endpointPatient, config)
          .then((response) => response.json())
          .then(data=>{
            console.log(data);
            renderPatient()  
          }).catch(err => {
            console.log(err);      
          })

        form.reset()
      })
    }

    patientLoad()



    /* ----------------------------------------------------------------------------------- */
    /*                 [5] FUNCTION: Update Dentist & Patient [PUT]                        */
    /* ----------------------------------------------------------------------------------- */

    function updateDentist(){

      const dentistUpdateButton = document.querySelector('.update-button')
      const searchDentistId = document.getElementById('inputDentistId')

      const name = document.querySelector('.dentist-update #inputName')
      const surname = document.querySelector('.dentist-update #inputSurname')
      const licenseNumber = document.querySelector('.dentist-update #inputLicenseNumber')


      dentistUpdateButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = searchDentistId.value
        const url = `${endpointDentist}/${id}`

        const payload = {
          id: id,
          name: name.value,
          surname: surname.value,
          licenseNumber: licenseNumber.value
        }

        const settings = {
          method: 'PUT',
          headers: {
            "Content-type": "application/json"
          },
          body: JSON.stringify(payload)
        }

        fetch(url, settings)
        .then(response => {
          console.log(response.status)
          renderDentist()
        })
      })
    }


    function updatePatient(){

      const form = document.querySelector('.patient-update')
      const searchPatientId = document.getElementById('inputPatientId')

      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const niNumber = document.querySelector('.patient-update #inputNiNumber')
      const streetName = document.querySelector('.patient-update #inputStreetName')
      const streetNumber = document.querySelector('.patient-update #inputStreetNumber')
      const floor = document.querySelector('.patient-update #inputFloor')
      const department = document.querySelector('.patient-update #inputDepartment')


      form.addEventListener('submit', function(e){
        e.preventDefault()

        const id = searchPatientId.value
        const url = `${endpointPatient}/${id}`

        const payload = {
          id: id,
          name: name.value,
          surname: surname.value,
          niNumber: niNumber.value,
          registrationDate: "Origin of the Universe",
          streetName: streetName.value,
          streetNumber: streetNumber.value,
          floor: floor.value,
          department: department.value
        }

        const settings = {
          method: 'PUT',
          headers: {
            "Content-type": "application/json"
          },
          body: JSON.stringify(payload)
        }

        console.log(payload);

        fetch(url, settings)
        .then(response => {
          console.log(response.status);
          renderPatient();
        })
      })
    }

    updatePatient()



    /* ----------------------------------------------------------------------------------- */
    /*                 [6] FUNCTION: Delete Dentist & Patient [DELETE]                     */
    /* ----------------------------------------------------------------------------------- */


    function dentistDelete() {

      const dentistDeleteButton = document.querySelector('.delete-button')
      const searchDentistId = document.getElementById('inputDentistId')

        dentistDeleteButton.addEventListener('click', function (e){
            e.preventDefault()

            const id = searchDentistId.value
            const url = `${endpointDentist}/${id}`
  
            const settings = {
              method: 'DELETE'
            }

            fetch(url, settings)
            .then(response => {
              console.log(response.status);
              renderDentist() 
            })
        })
    }


});
  