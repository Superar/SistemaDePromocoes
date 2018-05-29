window.onload = function () {
    var closeNotification = document.querySelector('.close');
    console.log("shdfahdsklf");

    if (closeNotification){
        console.log(closeNotification);
        for (var i = 0; i < closeNotification.length; i++) {
            closeNotification[i].addEventListener('click', function () {
                console.log("hey");
                var notifications = document.querySelector(".notification");
                console.log(notifications);
                notifications[0].style.display = "none";
            }, false);
        }
    }
};

