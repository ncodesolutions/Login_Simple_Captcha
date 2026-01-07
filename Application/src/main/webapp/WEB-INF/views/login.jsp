<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="header.jsp" %>
    <script src="./resources/js/login.js"></script>
    <body>
        <div class="loginbox">
            <div class="d-flex flex-center flex-column flex-column-fluid">

                <h3 class="mb-4" style="align-self: center">Application</h3>
                <div class="card mx-auto">
                    <h3 class="mb-4">Login</h3>
                    <form name="loginForm" id="loginForm">
                        <input type="hidden" name="_csrf" value="${cookie['XSRF-TOKEN'].getValue()}" />
                        <sec:csrfInput />
                        <c:if test="${Error ne ''}">
                            <div class="mb-3">
                                <center> <label class="form-label" name="error" id="error" style="color: red">${Error}</label>   </center>
                            </div>
                        </c:if>
                        <input type="hidden" id="salt" name="salt"/>
                        <input type="hidden" id="iv" name="iv"/>
                        <input type="hidden" id="key" name="key"/>
                        <div class="mb-3">
                            <label class="form-label">Email *</label>
                            <input type="text" name="username" id="username" class="form-control"  minlength="6" maxlength="100" autocomplete="off" >

                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password *</label>
                            <input type="password" name="password" id="password" class="form-control" minlength="1" maxlength="100" autocomplete="off">
                        </div>
                        <div class="mb-3">
                            <label class="form-label d-block">Enter Captcha *</label>

                            <img src="getCaptcha.do" class="ms-2" name="captcha1" id="captcha1">
                            <!--<input type="number" name="captcha" id="captcha" class="form-control d-inline w-25" onkeypress="return ValidateTextNumber(event);" minlength="0"  maxlength="4" max="9999" min="0000" autocomplete="off" >-->
                            <input type="text" name="captcha" id="captcha" class="form-control d-inline w-25" onkeypress="return ValidateTextNumber(event);" minlength="0"  maxlength="100" autocomplete="off" >
                            <i class="fa fa-refresh fa-lg" onclick="return refreshCaptcha()" id="refreshbtn" name="refreshbtn"></i>
                        </div>
                        <div class="mb-3">
                            <button type="button" class="btn btn-primary w-100" onclick="return  validateLogin()">Sign In</button>
                        </div>

                    </form> 

                    <script>


//                        function ValidateTextNumber(evt) {
//                            var theEvent = evt || window.event;
//                            var key = theEvent.keyCode || theEvent.which;
//                            key = String.fromCharCode(key);
//                            var regex = /^[a-zA-Z0-9 ]+$/;
//                            if (!regex.test(key)) {
//                                theEvent.returnValue = false;
//                                if (theEvent.preventDefault)
//                                    theEvent.preventDefault();
//                            }
//                        }
//
//                        var minLength = 5;
//                        var maxLength = 100;
//                        $(document).ready(function () {
//                            $('#username').on('keydown keyup change', function () {
//                                var char = $(this).val();
//                                var charLength = $(this).val().length;
//                                if (charLength < minLength) {
////                                   
//                                } else if (charLength > maxLength) {
//                                    $(this).val(char.substring(0, maxLength));
//                                } else {
//                                }
//                            });
//                        });
//
//                        function refreshCaptcha() {
//                            document.getElementById('captcha1').src = 'getCaptcha.do' + "?rand=" + Math.random() * 9876541230;
//                            return true;
//                        }
//
//                        function validateLogin() {
//                            GenerateRandomKey();
//
//                            if ($.trim($('#username').val()).length !== 0) {
//                                var emailidfordelete = $('#username').val();
//                                var emailvalid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//                                if (!emailidfordelete.match(emailvalid)) {
//                                    Swal.fire({text: 'Please enter proper Email'});
//                                    return false;
//                                } else {
//                                    if (emailidfordelete.length > 150) {
//                                        Swal.fire({text: 'Email too long, please enter proper Email'});
//                                        return false;
//                                    } else {
//                                        if (emailidfordelete.trim().length === 0) {
//                                            Swal.fire({text: 'Please enter proper Email'});
//                                            return false;
//                                        }
//                                    }
//                                }
//                            }
//
//                            if ($.trim($('#password').val()).length === 0) {
//                                Swal.fire({text: 'Please enter proper password'});
//                                return false;
//                            }
//
//                            if ($.trim($('#password').val()).length < 5) {
//                                Swal.fire({text: 'Please enter password min length is 5'});
//                                return false;
//                            }
//
//                            if ($.trim($('#captcha').val()).length === 0) {
//                                Swal.fire({text: 'Please enter proper captcha'});
//                                return false;
//                            }
//                            $('#username').val(GenerateEncrypted($('#username').val()));
//                            $('#password').val(GenerateEncrypted($('#password').val()));
//                            $('#captcha').val(GenerateEncrypted($('#captcha').val()));
//
//                            document.loginForm.method = "POST";
//                            document.loginForm.action = "./LoginSubmitdata.do";
//                            document.loginForm.submit();
//                            return true;
//                        }

                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
