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


    // getUserName()
    getDentistAll()
    getPatientAll()


    /* ------------------------------------------------------------------------------- */
    /*                 [0] FUNCTION: Render Dentist & Patient [GET]                    */
    /* ------------------------------------------------------------------------------- */


    function getDentistAll(){
      fetch(endpointDentist)
      .then(response=>response.json())
      .then(data=>{
        console.log(data)
        renderDentist(data)
        searchDentistById()
      })
    }

    function renderDentist(list){
      const selectDentist = document.querySelector('#dentist-table ul')
      selectDentist.innerHTML = ''

      list.forEach(dentist=>{
        selectDentist.innerHTML+=`
        <li>${dentist.name} ${dentist.surname}, Lic: ${dentist.licenseNumber}
        <div></div>
        </li>`
      })
    }
  


    function getPatientAll(){
      fetch(endpointPatient)
      .then(response=>response.json())
      .then(data=>{
        console.log(data)
        renderPatient(data)
        searchPatientById()
      })
    }
  
    function renderPatient(list){
      const selectPatient = document.querySelector('#patient-table ul')
      selectPatient.innerHTML = ''

      list.forEach(patient=>{
        selectPatient.innerHTML+=`
        <li>${patient.name} ${patient.surname}, NI Number: ${patient.niNumber}
        <div></div>
        </li>`
      })
    }


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
      }
  
      fetch(endpointGetUser, settings)
        .then(response => response.json())
        .then(data => {
          const usrName = document.querySelector('.user-info p');
          usrName.innerText = data.firstName;
        })
        .catch(error => console.log(error));
    }
  

    /* ----------------------------------------------------------------------------------- */
    /*                 [3] FUNCTION: Load Dentist & Patient [POST]                         */
    /* ----------------------------------------------------------------------------------- */


    function dentistLoad(){

      const form = document.querySelector('.dentist-load')
      const name = document.querySelector('.dentist-load #inputName')
      const surname = document.querySelector('.dentist-load #inputSurname')
      const licenseNumber = document.querySelector('.dentist-load #inputLicenseNumber')

      form.addEventListener('submit', function(e){
        e.preventDefault()

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

        console.log(config)

      fetch(endpointDentist, config)
          .then((response) => response.json())
          .then(data=>{
              console.log(data);
              getDentistAll()
          }).catch(err => {
              console.log(err);      
          })
        
//          form.reset()
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
            getPatientAll() 
          }).catch(err => {
            console.log(err);      
          })

//        form.reset()
      })
    }

    patientLoad()



    /* ----------------------------------------------------------------------------------- */
    /*                 [4] FUNCTION: Search By Id Dentist & Patient [GET]                  */
    /* ----------------------------------------------------------------------------------- */


    function searchDentistById(){

      const searchForm = document.querySelector('.dentist-search')
      const name = document.querySelector('.dentist-update #inputName')
      const surname = document.querySelector('.dentist-update #inputSurname')
      const licenseNumber = document.querySelector('.dentist-update #inputLicenseNumber')

      const searchDentistId = document.getElementById('inputDentistId')


      searchForm.addEventListener('submit', function(e){
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
              name.value = data.name
              surname.value = data.surname
              licenseNumber.value = data.licenseNumber
              updateDentist()
              dentistDelete()
        })
      })
    }



    function searchPatientById(){

      const searchButton = document.querySelector('.patient-search button')
      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const niNumber = document.querySelector('.patient-update #inputNiNumber')
      const streetName = document.querySelector('.patient-update #inputStreetName')
      const streetNumber = document.querySelector('.patient-update #inputStreetNumber')
      const floor = document.querySelector('.patient-update #inputFloor')
      const department = document.querySelector('.patient-update #inputDepartment')

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
            name.value = data.name
            surname.value = data.surname
            niNumber.value = data.niNumber
            streetName.value = data.streetName
            streetNumber.value = data.streetNumber
            floor.value = data.floor
            department.value = data.department
            updatePatient()
            patientDelete()
        })
      })
    }

    searchPatientById()


    /* ----------------------------------------------------------------------------------- */
    /*                 [5] FUNCTION: Update Dentist & Patient [PUT]                        */
    /* ----------------------------------------------------------------------------------- */

    function updateDentist(){

      const updateform = document.querySelector('.dentist-update')
      const searchForm = document.querySelector('.dentist-search')

      const updateButton = document.querySelector('.dentist-update .update-button')
      const dentistId = document.getElementById('inputDentistId')

      const name = document.querySelector('.dentist-update #inputName')
      const surname = document.querySelector('.dentist-update #inputSurname')
      const licenseNumber = document.querySelector('.dentist-update #inputLicenseNumber')


      updateButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = dentistId.value
        const url = `${endpointDentist}/${id}`

        const payload = {
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
          getDentistAll()
        })
        updateform.reset()
        searchForm.reset()
      })
    }


    function updatePatient(){

      const updateform = document.querySelector('.patient-update')
      const searchForm = document.querySelector('.patient-search')

      const updateButton = document.querySelector('.patient-update .update-button')
      const patientId = document.getElementById('inputPatientId')

      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const niNumber = document.querySelector('.patient-update #inputNiNumber')
      const streetName = document.querySelector('.patient-update #inputStreetName')
      const streetNumber = document.querySelector('.patient-update #inputStreetNumber')
      const floor = document.querySelector('.patient-update #inputFloor')
      const department = document.querySelector('.patient-update #inputDepartment')


      updateButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = patientId.value
        const url = `${endpointPatient}/${id}`

        const payload = {
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
          getPatientAll()
        })
        searchForm.reset()
        updateform.reset()
      })
    }



    /* ----------------------------------------------------------------------------------- */
    /*                 [6] FUNCTION: Delete Dentist & Patient [DELETE]                     */
    /* ----------------------------------------------------------------------------------- */

    function dentistDelete() {

      const updateform = document.querySelector('.dentist-update')
      const searchForm = document.querySelector('.dentist-search')

      const dentistDeleteButton = document.querySelector('.dentist-update #delete-button')
      const dentistId = document.getElementById('inputDentistId')

        dentistDeleteButton.addEventListener('click', function (e){
            e.preventDefault()

            const id = dentistId.value
            const url = `${endpointDentist}/${id}`
  
            const settings = {
              method: 'DELETE'
            }

            fetch(url, settings)
            .then(response => {
              console.log(response.status);
              getDentistAll()
            })
            updateform.reset()
            searchForm.reset()
        })
    }



    function patientDelete() {

      const updateform = document.querySelector('.patient-update')
      const searchForm = document.querySelector('.patient-search')

      const patientDeleteButton = document.querySelector('.patient-update #delete-button')
      const patientId = document.getElementById('inputPatientId')

        patientDeleteButton.addEventListener('click', function (e){
            e.preventDefault()

            const id = patientId.value
            const url = `${endpointPatient}/${id}`
  
            const settings = {
              method: 'DELETE'
            }

            fetch(url, settings)
            .then(response => {
              console.log(response.status);
              getPatientAll()
            })
            searchForm.reset()
            updateform.reset()
        })
    }



})
  