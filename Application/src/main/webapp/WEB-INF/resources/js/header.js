/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('input[type=number]').on('mousewheel', function (e) {
    $(this).blur();
});
// Disable keyboard scrolling
$('input[type=number]').on('keydown', function (e) {
    var key = e.charCode || e.keyCode;
    // Disable Up and Down Arrows on Keyboard
    if (key === 38 || key === 40) {
        e.preventDefault();
    } else {
        return;
    }
});

$(document).keydown(function (event) {
    if (event.keyCode === 123) { // Prevent F12
        return false;
    } else if (event.ctrlKey && event.shiftKey && event.keyCode === 73) { // Prevent Ctrl+Shift+I        
        return false;
    }
});

document.onkeydown = function () {
    switch (event.keyCode) {
        case 116: //F5 button
            event.returnValue = false;
            event.keyCode = 0;
            return false;
    }
};

window.onload = function () {
    document.body.oncontextmenu = function () {
        var message = "Right-click not allowed";
        //            swal(message);
        return false;
    };
};


window.onload = function () {

    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    if (isIE) {
        alert('Internet explorer is not supported for this application,\nplease use Chrome, Firefox, Edge etc.');
    }
};

window.onload = function () {

    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    if (isIE) {
        alert('Internet explorer is not supported for this application,\nplease use Chrome, Firefox, Edge etc.');
    }
};

function getEmailForEcNo(id) {
    var ecno = $("#" + id + "").val();
    var jsonData = {};
    jsonData = JSON.stringify({"id": ecno});
    $.ajax({
        type: "POST",
        url: '/GNFC_BG/getEmailForEcNo.do',
        dataType: 'json',
        contentType: 'application/json',
        data: jsonData,
        async: false,
        success: function (data)
        {
            if (data.status === "1") {
                $("#" + id + "email").val(data.email);
                $("#" + id + "email").prop("readonly", true);
            } else {
                Swal.fire({text: '' + data.msg});
                $("#" + id + "email").val("");
                $("#" + id + "email").prop("readonly", true);
            }

        },
        error: function (jqXHR, status, err) {

            if (jqXHR.status === 401) {
                Swal.fire({text: 'Session Expired.'});
                window.location = 'login';
            }

        }
    });

}


function getFileExtension3(filename) {
    return filename.slice((filename.lastIndexOf(".") - 1 >>> 0) + 2);
}