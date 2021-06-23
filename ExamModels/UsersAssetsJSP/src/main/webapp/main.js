var assets = [];

function getAssets() {
    $.get("/assets", function(response) {
        console.log(response);
        assets = response;
        let container = document.getElementById("assetsList");

        for (assetIt in response) {
            let idEl = document.createElement("p");
            idEl.innerHTML = "Id: " + response[assetIt]["id"];

            let userIdEl = document.createElement("p");
            userIdEl.innerHTML = "UserId: " + response[assetIt]["userId"];

            let nameEl = document.createElement("p");
            nameEl.innerHTML = "Name: " + response[assetIt]["name"];

            let descriptionEl = document.createElement("p");
            descriptionEl.innerHTML = "Description: " + response[assetIt]["description"];

            let valueEl = document.createElement("p");
            valueEl.innerHTML = "Value: " + response[assetIt]["value"];

            let line = document.createElement("hr");

            let assetEl = document.createElement("div");

            if (response[assetIt]["value"] > 10)
                assetEl.style.backgroundColor = "red";

            assetEl.appendChild(idEl);
            assetEl.appendChild(userIdEl);
            assetEl.appendChild(nameEl);
            assetEl.appendChild(descriptionEl);
            assetEl.appendChild(valueEl);
            assetEl.appendChild(line);

            container.appendChild(assetEl);
        }
    });
}

window.onload = function() {
    $("#assetAdditionButton").click(function() {
        let name = document.getElementById("nameInput").value;
        let description = document.getElementById("descriptionInput").value;
        let value = document.getElementById("valueInput").value;
        $.post("/assets", {name: name, description: description, value: value}, function(response) {
            console.log("Response after adding asset: ");
            console.log(response);

            let container = document.getElementById("assetsList");

            let idEl = document.createElement("p");
            idEl.innerHTML = "Id: " + response["id"];
            let userIdEl = document.createElement("p");
            userIdEl.innerHTML = "UserId: " + response["userId"];
            let nameEl = document.createElement("p");
            nameEl.innerHTML = "Name: " + response["name"];
            let descriptionEl = document.createElement("p");
            descriptionEl.innerHTML = "Description: " + response["description"];
            let valueEl = document.createElement("p");
            valueEl.innerHTML = "Value: " + response["value"];
            let line = document.createElement("hr");
            let assetEl = document.createElement("div");

            if (response["value"] > 10)
                assetEl.style.backgroundColor = "red";

            assetEl.appendChild(idEl);
            assetEl.appendChild(userIdEl);
            assetEl.appendChild(nameEl);
            assetEl.appendChild(descriptionEl);
            assetEl.appendChild(valueEl);
            assetEl.appendChild(line);

            container.appendChild(assetEl);
        });
    });

    getAssets();
};