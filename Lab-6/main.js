$(document).ready(function() {
    var currentSlide = 1;
    var duration = 1000;
    var slider = $('#slider');
    var slideContainer = slider.find('.slides');
    var slides = slideContainer.find('.slide');
    console.log(slides);
    slideContainer.css('margin-left', '-400vw');

    slider.click(function () {
        slideContainer.animate({'margin-left': '+=100vw'}, duration, function() {
            currentSlide++;
            if (currentSlide == slides.length) {
                currentSlide = 1;
                slideContainer.css('margin-left', '-400vw');
            }
        });
    });
});