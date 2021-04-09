function displayUsersByName(users) {
    var listOfUsers = [];
    if (!users.length) {
        $("#container").html("<h2> There is no user with this name... </h2>");
        return;
    }

    users.forEach(user => {
        let userHtml =  "Name: " + user.name + "<br/>" + 
                        "Username: " + user.username + "<br/>" + 
                        "Password: " + user.password + "<br/>" +
                        "Date of birth: " + user.dateOfBirth+ "<br/>" +
                        "Role: " + user.role + "<br/>" +
                        "E-mail: " + user.email + "<br/><hr>";
        listOfUsers.push(userHtml);
    });

    $("#container").html(listOfUsers);
}


function displayUsersByRole(users) {
    var listOfUsers = [];
    if (!users.length) {
        $("#container").html("<h2> There is no user with this role... </h2>");
        return;
    }

    users.forEach(user => {
        let userHtml =  "Name: " + user.name + "<br/>" + 
                        "Username: " + user.username + "<br/>" + 
                        "Password: " + user.password + "<br/>" +
                        "Date of birth: " + user.dateOfBirth+ "<br/>" +
                        "Role: " + user.role + "<br/>" +
                        "E-mail: " + user.email + "<br/><hr>";
        listOfUsers.push(userHtml);
    });

    $("#container").html(listOfUsers);
}


$(document).ready(function() {
    $("#showUsersByName").click(function() {
        console.log(`Beginning the request of users with the name ${$('#name').val()}...`);
        $.getJSON(
            "controller.php",  
            { requestedAction: "getUserByName", name: $('#name').val() }, 
            displayUsersByName
        );
    });
});


$(document).ready(function() {
    $("#showUsersByRole").click(function() {
        $.getJSON(
            "controller.php",  
            { requestedAction: "getUserByRole", role: $('#role').val() }, 
            displayUsersByRole
        );
    });
});


$(document).ready(function() {
    $("#addUser").click(function() {
        $.getJSON(
            "controller.php",  
            {   
                requestedAction: "addUser", 
                name: $('#name').val(),  
                username: $('#username').val(),  
                password: $('#password').val(),  
                dateOfBirth: $('#dateOfBirth').val(),  
                role: $('#role').val(),  
                email: $('#email').val() 
            }, 
            displayUsersByRole
        );
    });
});