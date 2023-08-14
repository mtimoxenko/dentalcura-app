
// if (!localStorage.jwt) {
//   location.replace('./index.html');
// }


window.addEventListener('load', function () {
  /* ------------------------- AOS lib. init -------------------------------- */
  AOS.init();

  // https://todo-api.ctd.academy/#/tasks/getOneTask
  const endpointTasks = 'https://todo-api.ctd.academy/v1/tasks';
  // https://todo-api.ctd.academy/#/users/getMe
  const endpointGetUser = 'https://todo-api.ctd.academy/v1/users/getMe';
  const token = JSON.parse(localStorage.jwt);

  const formAddTask = document.querySelector('.new-task');
  const newTask = document.querySelector('#newTask');
  const btnCloseApp = document.querySelector('#closeApp');

  getUserName();
  getTasks();


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
  /*                 [3] FUNCTION: Get Tasks list [GET]                */
  /* ----------------------------------------------------------------- */

  function getTasks() {
    const settings = {
      method: 'GET',
      headers: {
        authorization: token
      }
    };

    fetch(endpointTasks, settings)
      .then(response => response.json())
      .then(task => {

        renderTasks(task);
        btnChangeState();
        btnDeleteTask();
      })
      .catch(error => console.log(error));
  };


  /* ------------------------------------------------------------------- */
  /*                    [4] FUNCTION: Add Task [POST]                    */
  /* ------------------------------------------------------------------- */

  formAddTask.addEventListener('submit', function (event) {
    event.preventDefault();

    const payload = {
      description: newTask.value.trim()
    };
    const settings = {
      method: 'POST',
      body: JSON.stringify(payload),
      headers: {
        'Content-Type': 'application/json',
        authorization: token
      }
    }

    fetch(endpointTasks, settings)
      .then(response => response.json())
      .then(response => {
        // console.log(response.status);
        getTasks();
      })
      .catch(error => console.log(error));

    formAddTask.reset();
  })


  /* ----------------------------------------------------------- */
  /*                  [5] FUNCTION: Render Tasks                 */
  /* ----------------------------------------------------------- */
  function renderTasks(listado) {

    // clear all tasks
    const tasksToDo = document.querySelector('.tareas-pendientes');
    const tasksCompleted = document.querySelector('.tareas-terminadas');
    tasksToDo.innerHTML = "";
    tasksCompleted.innerHTML = "";

    const numComleted = document.querySelector('#cantidad-finalizadas');
    let i = 0;
    numComleted.innerText = i;

    listado.forEach(task => {

      let date = new Date(task.createdAt);

      if (task.completed) {
        i++;

        tasksCompleted.innerHTML += `
          <li class="tarea" data-aos="flip-up">
            <div class="hecha">
              <i class="fa-regular fa-circle-check"></i>
            </div>
            <div class="descripcion">
              <p class="nombre">${task.description}</p>
              <div class="cambios-estados">
                <button class="change incompleta" id="${task.id}" ><i class="fa-solid fa-rotate-left"></i></button>
                <button class="borrar" id="${task.id}"><i class="fa-regular fa-trash-can"></i></button>
              </div>
            </div>
          </li>
                        `
      } else {

        tasksToDo.innerHTML += `
          <li class="tarea" data-aos="flip-up">
            <button class="change" id="${task.id}"><i class="fa-regular fa-circle"></i></button>
            <div class="descripcion">
              <p class="nombre">${task.description}</p>
              <p class="timestamp">${date.toLocaleDateString()}</p>
            </div>
          </li>
                        `
      }

      numComleted.innerText = i;
    })
  }

  /* ---------------------------------------------------------------------- */
  /*                  [6] FUNCTION: Change task state [PUT]                 */
  /* ---------------------------------------------------------------------- */
  function btnChangeState() {
    const btnCambioEstado = document.querySelectorAll('.change');

    btnCambioEstado.forEach(btn => {

      btn.addEventListener('click', function (event) {

        const id = event.target.id;
        const url = `${endpointTasks}/${id}`
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
            getTasks();
          })
      })
    });

  }


  /* -------------------------------------------------------------------------- */
  /*                     [7] FUNCTION: Delete task [DELETE]                    */
  /* -------------------------------------------------------------------------- */
  function btnDeleteTask() {
  
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
            const url = `${endpointTasks}/${id}`

            const settings = {
              method: 'DELETE',
              headers: {
                "Authorization": token,
              }
            }
            fetch(url, settings)
              .then(response => {
                console.log(response.status);
                getTasks();
              })

            Swal.fire(
              'Task deleted.',
            );

          }
        });

      })
    });
  }

});
