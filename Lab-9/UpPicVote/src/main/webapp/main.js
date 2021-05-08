function upvoteImage(filename, callback) {
    var parts = filename.split('_');
    var actualFilename = parts.slice(1).join('_');
    $.post("/vote", {action: "upvote", filename: actualFilename}, callback);
    console.log("Upvote request has been sent. Filename is: " + actualFilename);
}

function downvoteImage(filename, callback) {
    var parts = filename.split('_');
    var actualFilename = parts.slice(1).join('_');
    $.post("/vote", {action: "downvote", filename: actualFilename}, callback);
    console.log("Downvote request has been sent. Filename is: " + actualFilename);
}

$(document).ready(function(){
    $(".upvoteButton").click(function() {
        console.log("The id of this upvote button: " + $(this).attr("id"));
        let id = $(this).attr("id");
        upvoteImage($(this).attr("id"), function(response) {
            let responseCode = response["responseCode"];
            let message = response["message"];
            console.log("Response from the server after upvoting: {[" + responseCode + "], " + message + "}");

            let parts = id.split('_');
            let actualFilename = parts.slice(1).join('_');
            let idOfTheResponseMessage = "response_" + actualFilename;
            if (message == "Image was successfully upvoted.") {
                let oldRating = parseInt(document.getElementById("rating_"+actualFilename).innerHTML);
                document.getElementById("rating_"+actualFilename).innerHTML = parseInt(oldRating) + 1;
            }

            document.getElementById(idOfTheResponseMessage).innerHTML = message;
        });
    });

    $(".downvoteButton").click(function() {
        console.log("The id of this downvote button: " + $(this).attr("id"));
        let id = $(this).attr("id");
        downvoteImage($(this).attr("id"), function(response) {
            let responseCode = response["responseCode"];
            let message = response["message"];
            console.log("Response from the server after downvoting: {[" + responseCode + "], " + message + "}");

            let parts = id.split('_');
            let actualFilename = parts.slice(1).join('_');
            let idOfTheResponseMessage = "response_" + actualFilename;
            if (message == "Image was successfully downvoted.") {
                let oldRating = parseInt(document.getElementById("rating_"+actualFilename).innerHTML);
                document.getElementById("rating_"+actualFilename).innerHTML = oldRating - 1;
            }
            document.getElementById(idOfTheResponseMessage).innerHTML = message;
        });
    });
});

