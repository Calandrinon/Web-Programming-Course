$(document).ready(function() {
    $("#slider").click(function () {
        $('#slider .slides').animate({'margin-left': '-=100vw'}, 1000);
    });
});