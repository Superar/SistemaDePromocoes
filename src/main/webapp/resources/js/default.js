window.onload = function () {
    var closeNotification = document.querySelector('.close');

    if (closeNotification) {
        closeNotification.addEventListener('click', function () {
            document.querySelector(".notification").style.display = "none";
        });
    }
};

