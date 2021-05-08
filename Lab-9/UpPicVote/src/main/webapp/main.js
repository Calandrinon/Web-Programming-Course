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
        upvoteImage($(this).attr("id"), function(response) {
            console.log("Response from the server after upvoting: " + response);
            for(var property in response) {
                console.log(property + "=" + response[property]);
            }
        });
    });

    $(".downvoteButton").click(function() {
        console.log("The id of this downvote button: " + $(this).attr("id"));
        downvoteImage($(this).attr("id"), function(response) {
            console.log("Response from the server after downvoting: " + response);
            for(var property in response) {
                console.log(property + "=" + response[property]);
            }
        });
    });
});

