/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ValidateTextNumber(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    var regex = /^[a-zA-Z0-9 ]+$/;
    if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault)
            theEvent.preventDefault();
    }
}

var minLength = 5;
var maxLength = 100;
$(document).ready(function () {
    $('#username').on('keydown keyup change', function () {
        var char = $(this).val();
        var charLength = $(this).val().length;
        if (charLength < minLength) {
//                                   
        } else if (charLength > maxLength) {
            $(this).val(char.substring(0, maxLength));
        } else {
        }
    });
});

function refreshCaptcha() {
    document.getElementById('captcha1').src = 'getCaptcha.do' + "?rand=" + Math.random() * 9876541230;
    return true;
}

function validateLogin() {
    GenerateRandomKey();

    if ($.trim($('#username').val()).length !== 0) {
        var emailidfordelete = $('#username').val();
        var emailvalid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!emailidfordelete.match(emailvalid)) {
            Swal.fire({text: 'Please enter proper Email'});
            return false;
        } else {
            if (emailidfordelete.length > 150) {
                Swal.fire({text: 'Email too long, please enter proper Email'});
                return false;
            } else {
                if (emailidfordelete.trim().length === 0) {
                    Swal.fire({text: 'Please enter proper Email'});
                    return false;
                }
            }
        }
    }

    if ($.trim($('#password').val()).length === 0) {
        Swal.fire({text: 'Please enter proper password'});
        return false;
    }

    if ($.trim($('#password').val()).length < 5) {
        Swal.fire({text: 'Please enter password min length is 5'});
        return false;
    }

    if ($.trim($('#captcha').val()).length === 0) {
        Swal.fire({text: 'Please enter proper captcha'});
        return false;
    }
    $('#username').val(GenerateEncrypted($('#username').val()));
    $('#password').val(GenerateEncrypted($('#password').val()));
    $('#captcha').val(GenerateEncrypted($('#captcha').val()));

    document.loginForm.method = "POST";
    document.loginForm.action = "./LoginSubmitdata.do";
    document.loginForm.submit();
    return true;
}
