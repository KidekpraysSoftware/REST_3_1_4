
const URL = 'http://localhost:8080/api/user'
const userInfoNavbar = document.getElementById("user_info_navbar");
const userInfoTable = document.getElementById("user_info_table");

const getRequest = () => fetch(URL)
    .then(r => r.json())
    .then(userJson => {
        userInfoTable.innerHTML = `
        <td>${userJson.id}</td>
        <td>${userJson.firstname}</td>
        <td>${userJson.lastname}</td>
        <td>${userJson.age}</td>
        <td>${userJson.email}</td>
        <td> ${userJson.roles.map((role) => role.name === "ADMIN" ? "ADMIN" : "USER")} </td>
        `

        userInfoNavbar.innerHTML = `

            <b>
            <span class="align-middle">${userJson.email}</span></b>
            <span class="align-middle">with roles:</span>
            <span class="align-middle">${userJson.roles.map((role) => role.name === "ADMIN" ? "ADMIN" : "USER")}</span>
            `
        }
    )
getRequest()