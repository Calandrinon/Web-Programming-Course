$(document).ready(function() {
    var currentSlide = 1;
    var speed = 1000;
    var $slider = $('#slider');
    var $slideContainer = $slider.find('.slides');
    var $slides = $slideContainer.find('.slide');

    $slider.click(function () {
        $slideContainer.animate({'margin-left': '-=100vw'}, speed, function() {
            currentSlide++;
            if (currentSlide == $slides.length) {
                currentSlide = 1;
                $slideContainer.css('margin-left', 0);
            }
        });
    });
});