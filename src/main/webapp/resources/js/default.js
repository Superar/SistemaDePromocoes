window.onload = function () {
    var closeNotification = document.querySelector('.close');

    if (closeNotification) {
        closeNotification.addEventListener('click', function () {
            document.querySelector(".notification").style.display = "none";
        });
    }
};

function monitorarAjax(data) {
    var ajaxStatus = data.status;
    var ajaxLoader = document.getElementById("ajaxLoader");

    console.log(ajaxLoader);

    switch (ajaxStatus) {
        case "begin":
            ajaxLoader.classList.add('is-active');
            break;


        case "complete":
            ajaxLoader.classList.remove('is-active');
            break;
    }
}

