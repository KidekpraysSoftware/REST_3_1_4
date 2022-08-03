
const user_api = 'http://localhost:8080/api/user'
const admin_api = 'http://localhost:8080/api/admin'
const add_user_action = document.querySelector('#add_user')

const userInfoNavbar = document.getElementById("user_info_navbar");
const userInfoTable = document.getElementById("user_info_table");


//заполняем навбар
const getNavbar = () => fetch(user_api)
    .then(r => r.json())
    .then(userJson => {
            userInfoNavbar.innerHTML = `
            <b>
            <span class="align-middle">${userJson.email}</span></b>
            <span class="align-middle">with roles:</span>
            <span class="align-middle">${userJson.roles.map((role) => role.name === "ADMIN" ? "ADMIN" : "USER")}</span>
            `
        }
    )
getNavbar()

// <td> <button type="button" class="btn btn-info" id="btn-edit-modal-call" data-toggle="modal" data-target="modal-edit"
// data-id="${user.id}">Edit</button></td>

//  <td> <button type="button" class="btn btn-danger" id="btn-delete-modal-call"
// data-id="${user.id}">Delete</button></td>

//заполняем таблицу
const getAllUsers = () =>  fetch(admin_api)
    .then(r => r.json())
    .then(usersJson => {
        document.getElementById("user_list").innerHTML=''
        if (usersJson.length > 0) {
            let EditButtonId = '';
            let DeleteButtonId = '';
            let rows = '';

            //генерация строк в таблице
            for (let i = 0; i < usersJson.length; i++) {
                let user = usersJson[i]


                EditButtonId='EditButton_'+user.id
                DeleteButtonId='DeleteButton_'+user.id
                //console.log("ButtonId= "+ButtonId)
                rows = `
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td> ${user.roles.map((role) => role.name === "ADMIN" ? "ADMIN" : "USER")} </td>
                    <td> <button type="button" class="btn btn-info EditBtn" data-toggle="modal" data-target="#EditModal" id="${EditButtonId}">Edit</button> </td>
                    <td> <button type="button" class="btn btn-danger DeleteBtn" data-toggle="modal" data-target="#DeleteModal" id="${DeleteButtonId}">Delete</button> </td>
                </tr>
                `
                document.getElementById("user_list").innerHTML += rows;

            }

            //установка обработчика на кнопки в таблице (без второго обхода в цикле не работает)
            for (let i = 0; i < usersJson.length; i++) {
                let user = usersJson[i]

                EditButtonId = 'EditButton_' + user.id
                DeleteButtonId = 'DeleteButton_' + user.id
                document.getElementById(EditButtonId).addEventListener("click", () => {
                    console.log("Нажата кнопка Edit " + user.id)

                    document.getElementById('edit_id').value = user.id
                    document.getElementById('edit_FirstName').value = user.firstname
                    document.getElementById('edit_LastName').value = user.lastname
                    document.getElementById('edit_Age').value = user.age
                    document.getElementById('edit_Email').value = user.email




                })


                document.getElementById(DeleteButtonId).addEventListener("click", () => {
                    console.log("Нажата кнопка Delete " + user.id)

                    document.getElementById('delete_id').value = user.id
                    document.getElementById('delete_FirstName').value = user.firstname
                    document.getElementById('delete_LastName').value = user.lastname
                    document.getElementById('delete_Age').value = user.age
                    document.getElementById('delete_Email').value = user.email
                    //document.getElementById('delete_id').text = user.id




                })
            }
        }


    })
getAllUsers()

//добавляем пользователя
add_user_action.addEventListener('submit', (e) => {
    e.preventDefault()

    let add_user = $('#add_user')
//СОЗДАЕМ ДЖИСОН, СПРАВА НАШИ ПЕРЕМЕННЫЕ ИЗ ФОРМЫ
    let newUserData = {
        firstname: add_user.find('#add_FirstName').val().trim(),
        lastname: add_user.find('#add_LastName').val().trim(),
        age: add_user.find('#add_Age').val().trim(),
        email: add_user.find('#add_Email').val().trim(),
        password: add_user.find('#add_Password').val().trim(),
        roles:  Array.from(document.getElementById("add_Role"))
        .filter(option => option.selected)
        .map(option => ({name: option.value, id: option.id}))
    }
    console.log(newUserData)

    //отправляем пользователя в базу
    fetch(admin_api, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(newUserData)
    })
         .then(() => {
             $('#usersTableLi').click()
             getAllUsers()
             add_user.find('#add_FirstName').val('')
             add_user.find('#add_LastName').val('')
             add_user.find('#add_Age').val('')
             add_user.find('#add_Email').val('')
             add_user.find('#add_Password').val('')
        });
})


//обработка кнопки Edit в модальном окне
document.getElementById('edit_user_submit_btn').addEventListener('click', (e) => {
    e.preventDefault()
    console.log("Метод Edit выполняется")
    let edit_user = $('#edit_user')
    let roles = edit_user.find('#add_Role').val();

//СОЗДАЕМ ДЖИСОН, СПРАВА НАШИ ПЕРЕМЕННЫЕ ИЗ ФОРМЫ
    let editUserData = {

        id: edit_user.find('#edit_id').val().trim(),
        firstname: edit_user.find('#edit_FirstName').val().trim(),
        lastname: edit_user.find('#edit_LastName').val().trim(),
        age: edit_user.find('#edit_Age').val().trim(),
        email: edit_user.find('#edit_Email').val().trim(),
        password: edit_user.find('#edit_Password').val().trim(),
        roles:  Array.from(document.getElementById("edit_Role"))
            .filter(option => option.selected)
            .map(option => ({name: option.value, id: option.id}))
    }
    console.log(editUserData)

    //отправляем пользователя в базу
    fetch(admin_api, {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(editUserData)
    })
        .then(user => {
            getAllUsers()
        });
})

//обработка кнопки Delete в модальном окне
document.getElementById('delete_user_submit_btn').addEventListener('click',() => {

    console.log("вызван сабмит для delete")
    let delete_id_modal = document.getElementById('delete_id')
    fetch(`${admin_api}/${delete_id_modal.value}`, {
        method: 'DELETE',
    })
        .then(() =>{
            getAllUsers()
        })

})

