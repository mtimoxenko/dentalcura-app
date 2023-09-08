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


    getUserName()
    getDentistAll()
    getPatientAll()
    patientLoad()
    dentistLoad()
    searchDentistById()
    searchPatientById()


    /* ------------------------------------------------------------------------------- */
    /*                 [0] FUNCTION: Render Dentist & Patient [GET]                    */
    /* ------------------------------------------------------------------------------- */


    function getDentistAll(){
      fetch(endpointDentist)
      .then(response=>response.json())
      .then(data=>{
        console.log(data)
        renderDentist(data)
      })
      .catch(err => {
        console.log(err)
      })
    }

    function renderDentist(list){
      const selectDentist = document.querySelector('#dentist-table ul')
      selectDentist.innerHTML = ''

      list.forEach(dentist=>{
        selectDentist.innerHTML+=`
        <li>${dentist.id} - ${dentist.surname}, ${dentist.name}. Lic: ${dentist.licenseNumber}
        <div></div>
        </li>`
      })
    }
  


    function getPatientAll(){

      const settings = {
        method: 'GET'}

      fetch(endpointPatient, settings)
      .then(response=>response.json())
      .then(data=>{
        console.log(data)
        renderPatient(data)
      })
    }
  
    function renderPatient(list){
      const selectPatient = document.querySelector('#patient-table ul')
      selectPatient.innerHTML = ''

      list.forEach(patient=>{
        selectPatient.innerHTML+=`
        <li>${patient.id} - ${patient.surname}, ${patient.name}. Nin: ${patient.niNumber}
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
        iconColor: '#545454',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        background: 'rgba(255, 255, 255, .9)'
      }).then((result) => {
        if (result.isConfirmed) {
          localStorage.clear()
          location.replace('./index.html')
        }
      })
  
    })
  
    /* ------------------------------------------------------------------ */
    /*                 [2] FUNCTION: Get user name [GET]                  */
    /* ------------------------------------------------------------------ */
  
    function getUserName() {

      const name = sessionStorage.getItem('userName')

      const userName = document.querySelector('.user-info p')

      userName.innerText = JSON.parse(name)

      // const settings = {
      //   method: 'GET',
      //   headers: {
      //     authorization: token
      //   }
      // }
  
      // fetch(endpointGetUser, settings)
      //   .then(response => response.json())
      //   .then(data => {
      //     const usrName = document.querySelector('.user-info p');
      //     usrName.innerText = data.firstName;
      //   })
      //   .catch(error => console.log(error));
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


        if (payload.name == '' || payload.name.includes(' ') || !isNaN(payload.name)) {
          name.style.border = '2px solid red'
        }
        else if(payload.surname == '' || payload.surname.includes(' ') || !isNaN(payload.surname)){
          alert('You must complete Surname correctly, without leaving empty fields or spaces')
        }
        else if(payload.licenseNumber == '' || payload.licenseNumber.includes(' ') || isNaN(payload.licenseNumber)){
          alert('You must complete License Number field correctly')
        }
        else{
            fetch(endpointDentist, config)
            .then((response) => {response.json()
              getDentistAll()             
            })
            .catch(err => {
                console.log(err)
            })
            form.reset()
        }
      })
    }



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
          address:{
            streetName: streetName.value,
            streetNumber: streetNumber.value,
            floor: floor.value,
            department: department.value
          }
        }

        const config = {
          method: 'POST',
          headers: {
              'Content-type': 'application/json; charset=UTF-8',
          },
          body: JSON.stringify(payload),
        }

        console.log(payload)


        if (payload.name == '' || payload.name.includes(' ') || !isNaN(payload.name)) {
          alert('You must complete Name correctly, without leaving empty fields or spaces')
        }
        else if(payload.surname == '' || payload.surname.includes(' ') || !isNaN(payload.surname)){
          alert('You must complete Surname correctly, without leaving empty fields or spaces')
        }
        else if(payload.niNumber == '' || payload.niNumber.includes(' ') || isNaN(payload.niNumber)){
          alert('You must complete NI Number field correctly')
        }
        else{
          fetch(endpointPatient, config)
            .then((response) => {response.json()
              getPatientAll()             
            })
            .catch(err => {
              console.log(err)
            })
          form.reset()          
        }
      })
    }




    /* ----------------------------------------------------------------------------------- */
    /*                 [4] FUNCTION: Search By Id Dentist & Patient [GET]                  */
    /* ----------------------------------------------------------------------------------- */


    function searchDentistById(){

      const searchForm = document.querySelector('.dentist-search')
      const name = document.querySelector('.dentist-update #inputName')
      const surname = document.querySelector('.dentist-update #inputSurname')

      const searchDentistId = document.getElementById('inputDentistId')


      searchForm.addEventListener('submit', function(e){
        e.preventDefault()

        const id = searchDentistId.value
        const url = `${endpointDentist}/${id}`
        const dentistDeleteButton = document.querySelector('.dentist-update #delete-button')

        const settings = {
          method: 'GET',
        }

        fetch(url, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data)
          name.value = data.name
          surname.value = data.surname
          updateDentist()
          dentistDeleteButton.disabled = false
          dentistDelete()
        })
        .catch(err=>{
          console.log(err);
          name.value = ""
          surname.value = ""
          dentistDeleteButton.disabled = true
        })
      })
    }



    function searchPatientById(){

      const searchButton = document.querySelector('.patient-search button')
      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const streetName = document.querySelector('.patient-update #inputStreetName')
      const streetNumber = document.querySelector('.patient-update #inputStreetNumber')
      const floor = document.querySelector('.patient-update #inputFloor')
      const department = document.querySelector('.patient-update #inputDepartment')

      const searchPatientId = document.getElementById('inputPatientId')


      searchButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = searchPatientId.value
        const url = `${endpointPatient}/${id}`
        const patientDeleteButton = document.querySelector('.patient-update #delete-button')

        const settings = {
          method: 'GET',
        }

        fetch(url, settings)
        .then(response=>response.json())
        .then(data=>{
          console.log(data)
            name.value = data.name
            surname.value = data.surname
            streetName.value = data.address.streetName
            streetNumber.value = data.address.streetNumber
            floor.value = data.address.floor
            department.value = data.address.department
            updatePatient()
            patientDeleteButton.disabled = false
            patientDelete()
        })
        .catch(err=>{
          name.value = ""
          surname.value = ""
          streetName.value = ""
          streetNumber.value = ""
          floor.value = ""
          department.value = ""
          patientDeleteButton.disabled = true
        })
      })
    }



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


      updateButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = dentistId.value
        const url = `${endpointDentist}/${id}`

        console.log(id)

        const payload = {
          name: name.value,
          surname: surname.value,
        }

        const settings = {
          method: 'PUT',
          headers: {
            "Content-type": "application/json"
          },
          body: JSON.stringify(payload)
        }


        if (payload.name == '' || payload.name.includes(' ') || !isNaN(payload.name)) {
          alert('You must complete Name correctly, without leaving empty fields or spaces')
        }
        else if(payload.surname == '' || payload.surname.includes(' ') || !isNaN(payload.surname)){
          alert('You must complete Surname correctly, without leaving empty fields or spaces')
        }
        else{
          fetch(url, settings)
          .then(response => {
          console.log(response.status)
          getDentistAll()
        })
        updateform.reset()
        searchForm.reset()
        }
      })
    }


    function updatePatient(){

      const updateform = document.querySelector('.patient-update')
      const searchForm = document.querySelector('.patient-search')

      const updateButton = document.querySelector('.patient-update .update-button')
      const patientId = document.getElementById('inputPatientId')

      const name = document.querySelector('.patient-update #inputName')
      const surname = document.querySelector('.patient-update #inputSurname')
      const streetName = document.querySelector('.patient-update #inputStreetName')
      const streetNumber = document.querySelector('.patient-update #inputStreetNumber')
      const floor = document.querySelector('.patient-update #inputFloor')
      const department = document.querySelector('.patient-update #inputDepartment')


      updateButton.addEventListener('click', function(e){
        e.preventDefault()

        const id = patientId.value
        const url = `${endpointPatient}/${id}`

        console.log(id)

        const payload = {
          name: name.value,
          surname: surname.value,
          registrationDate: "Origin of the Universe",
          address: {
            streetName: streetName.value,
            streetNumber: streetNumber.value,
            floor: floor.value,
            department: department.value            
          }
        }

        const settings = {
          method: 'PUT',
          headers: {
            "Content-type": "application/json"
          },
          body: JSON.stringify(payload)
        }

        console.log(payload)


        if (payload.name == '' || payload.name.includes(' ') || !isNaN(payload.name)) {
          alert('You must complete Name correctly, without leaving empty fields or spaces')
        }
        else if(payload.surname == '' || payload.surname.includes(' ') || !isNaN(payload.surname)){
          alert('You must complete Surname correctly, without leaving empty fields or spaces')
        }
        else{
          fetch(url, settings)
          .then(response => {
            console.log(response.status)
            getPatientAll()
          })
          searchForm.reset()
          updateform.reset()          
        }
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

            console.log(id)
  
            const settings = {
              method: 'DELETE'
            }

            Swal.fire({
              title: `Confirm delete dentist Id: ${id}`,
              icon: 'question',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Confirm',
              cancelButtonText: 'Cancel',
              background: 'rgba(0, 0, 0, .8)'
            })
            .then((result) => {
              if (result.isConfirmed) {
                fetch(url, settings)
                .then(response => {
                  console.log(response.status)
                  getDentistAll()
                })
              updateform.reset()
              searchForm.reset()                
              }
            })

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

            console.log(id)

            const settings = {
              method: 'DELETE'
            }

            Swal.fire({
              title: `Confirm delete patient Id: ${id}`,
              icon: 'question',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Confirm',
              cancelButtonText: 'Cancel',
              background: 'rgba(0, 0, 0, .8)'
            })
            .then((result) => {
              if (result.isConfirmed) {
                fetch(url, settings)
                .then(response => {
                  console.log(response.status)
                  getPatientAll()
                })
              updateform.reset()
              searchForm.reset()                
              }
            })

        })
    }



})
  