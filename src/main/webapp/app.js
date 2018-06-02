function handleErrors(response) {
    if (!response.ok) {
        var error = new Error(response.statusText);
        error = response;
        throw error;
    }
    return response.json();
}

function errorMessage(errorJson) {
    if (errorJson !== undefined) {
        message = errorJson.message;
        //alert(message);
        alert("The username doesn't exist")
    }
}
function getUserWithuserName() {
    var userName = document.getElementById("userNameId").value;
    if (userName === "") {
        alert("You need to type in a username");
    } else {
        var myHeaders = new Headers;
        myHeaders.set("Content-Type", "application/json");
        var promise = fetch("api/users/getUser/" + userName,
                {
                    method: "GET",
                    headers: myHeaders
                });
        promise.then(handleErrors).then(function (user) {
            document.getElementById("tId").innerHTML = genSingleUserTable(user);
        }).catch(function (error) {
            return error.json();
        }).then(errorMessage);
    }
}





function genSingleUserTable(user) {
    var htmlStr = "<thead><th>Address</th><th>Latitude</th><th>Longitude</th><th>Password</th><th>Role</th><th>Title</th><th>Username</th></thead><tbody></tbody>";
    return htmlStr += ""
            + "<tr><td>" + user.address
            + "</td><td>" + user.latitude
            + "</td><td>" + user.longitude
            + "</td><td>" + user.password
            + "</td><td>" + user.role
            + "</td><td>" + user.title
            + "</td><td>" + user.username
            + "</td></tr>";
}


function addNewUser() {
    var userObject = {
        "address": document.getElementById("address").value,
        "latitude": document.getElementById("latitude").value,
        "longitude": document.getElementById("longitude").value,
        "password": document.getElementById("password").value,
        "role": document.getElementById("role").value,
        "title": document.getElementById("title").value,
        "username": document.getElementById("username").value
    };


    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/users/addUser", {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(userObject)
    });

    promise

            .then(function () {
                document.getElementById("tId").innerHTML = genSingleUserTable(userObject);
                alert(userObject.username + " " + "is been added!");
            })
            .catch(function (error) {
                return error.json();
            }).then(function () {

    });
}



function deleteUserwithuserName() {
    var userName = document.getElementById("userNameId").value;
    if (userName === "") {
        alert("You need to type in a username");
    } else {
        var myHeaders = new Headers;
        myHeaders.set("Content-Type", "application/json");
        var promise = fetch("api/users/delete/" + userName,
                {
                    method: "DELETE",
                    headers: myHeaders
                });
        promise.then(handleErrors).then(function (user) {
            alert(userName+" "+"is been deleted");
            document.getElementById("tId").innerHTML = genSingleUserTable(user);
        }).catch(function (error) {
            return error.json();
        }).then(errorMessage);
    }
}

function getAllUsers() {
    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/users/getAllUsers",
            {
                method: "GET",
                headers: myHeaders
            });
    promise.then(handleErrors).then(function (user) {
        document.getElementById("tId").innerHTML = mapAllUsersList(user);
        console.log(user);
    }).catch(function (error) {
        return error.json();
    }).then(errorMessage);
}
function mapAllUsersList(users) {

    var htmlStr = "<thead><th>Address</th><th>Latitude</th><th>Longitude</th><th>Password</th><th>Role</th><th>Title</th><th>Username</th></thead>";
    htmlStr += users.map(user => {
        return  ""
                + "<tr><td>" + user.address
                + "</td><td>" + user.latitude
                + "</td><td>" + user.longitude
                + "</td><td>" + user.password
                + "</td><td>" + user.role
                + "</td><td>" + user.title
                + "</td><td>" + user.username
                + "</td></tr>";
    }).join("");
    console.log(htmlStr);
    //htmlStr += ;
    return htmlStr;
}

document.getElementById("getAllUsersBtn").addEventListener("click", getAllUsers);
document.getElementById("addUserBtn").addEventListener("click", addNewUser);
document.getElementById("findByUsernameBtn").addEventListener("click", getUserWithuserName);
document.getElementById("deleteByUsernameBtn").addEventListener("click", deleteUserwithuserName);