function upvoteImage(filename) {
    var parts = filename.split('_');
    var actualFilename = parts.slice(1).join('_');
    $.post("/vote", {action: "upvote", filename: actualFilename});
    console.log("Upvote request has been sent. Filename is: " + actualFilename);
}

function downvoteImage(filename) {
    var parts = filename.split('_');
    var actualFilename = parts.slice(1).join('_');
    $.post("/vote", {action: "downvote", filename: actualFilename});
    console.log("Downvote request has been sent. Filename is: " + actualFilename);
}

$(document).ready(function(){
    $(".upvoteButton").click(function() {
        console.log("The id of this upvote button: " + $(this).attr("id"));
        upvoteImage($(this).attr("id"));
    });

    $(".downvoteButton").click(function() {
        console.log("The id of this downvote button: " + $(this).attr("id"));
        downvoteImage($(this).attr("id"));
    });
});

