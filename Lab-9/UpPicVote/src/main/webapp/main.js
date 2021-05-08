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

function sendRequestsForTheTopNPosts(n, callback) {
    console.log("The input n is: " + n);
    $.post("/filter", {action: "getTopN", topN: n}, callback);
}

function attachEventHandlers() {
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
}

$(document).ready(function(){
    attachEventHandlers();

    $("#topNButton").click(function() {
        let topNString = document.getElementById("numberTopN").value;
        let topNInteger = parseInt(topNString);
        console.log("The topNString is: " + topNString);
        console.log("The topNInteger is: " + topNInteger);
        sendRequestsForTheTopNPosts(topNInteger, function(response) {
            /**
            console.log("This is the response with the top N images from the server: " + response);
            console.log("images=", response["images"]);
            console.log("image[0]=", response["images"][0]['filename']);
             **/

            let postContainer = document.getElementById("postContainer");
            postContainer.innerHTML = '';

            response['images'].forEach(function (value, index, array) {
                console.log("value=", value, "; index=", index);
                let filename = value['filename'];
                let description = value['description'];
                let numberOfVotes = value['numberOfVotes'];
                let poster = filename.split('_')[0];
                 let post = document.createElement('div');
                 post.setAttribute('id', 'post');
                 let imageTag = document.createElement('img');
                 imageTag.setAttribute('src', "Files/" + filename);
                 let descriptionTag = document.createElement('h4');
                 descriptionTag.textContent = "Description: " + description;
                 let ratingTag = document.createElement('h4');
                 ratingTag.textContent = "Rating:";
                 let ratingNumberTag = document.createElement('h4');
                 ratingNumberTag.textContent = numberOfVotes;
                 ratingNumberTag.setAttribute('id', 'rating_'+filename);
                 let posterTag = document.createElement('h4');
                 posterTag.textContent = "Posted by: " + poster;
                 let upvoteButtonTag = document.createElement('button');
                 upvoteButtonTag.setAttribute('id', 'upvote_'+filename);
                 upvoteButtonTag.setAttribute('class', 'upvoteButton');
                 upvoteButtonTag.setAttribute('type', 'button');
                 upvoteButtonTag.textContent = "^";
                let downvoteButtonTag = document.createElement('button');
                downvoteButtonTag.setAttribute('id', 'downvote_'+filename);
                downvoteButtonTag.setAttribute('class', 'downvoteButton');
                downvoteButtonTag.setAttribute('type', 'button');
                downvoteButtonTag.textContent = "v";
                let responseTag = document.createElement('h4');
                responseTag.setAttribute('id', 'response_'+filename);

                post.appendChild(imageTag);
                 post.appendChild(descriptionTag);
                 post.appendChild(ratingTag);
                 post.appendChild(ratingNumberTag);
                 post.appendChild(posterTag);
                post.appendChild(upvoteButtonTag);
                post.appendChild(downvoteButtonTag);
                post.appendChild(responseTag);
                postContainer.appendChild(post);
            });
            attachEventHandlers();
        });
    });
});

