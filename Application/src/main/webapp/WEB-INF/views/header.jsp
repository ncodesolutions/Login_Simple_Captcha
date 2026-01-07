<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <title>EPP</title>
    <meta charset="utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="EPP" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" type="image/png" href="resources/img/favicon.png"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!--<link rel="stylesheet" href="./resources/css/all.min.css"  />-->
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link href="./resources/css/sweetalert.min.css" rel="stylesheet">
    <link href="./resources/css/dataTables.bootstrap.css" rel="stylesheet"/>
    <link href="./resources/css/buttons.bootstrap.css" rel="stylesheet"/>
    <link href="./resources/css/responsive.bootstrap.css" rel="stylesheet"/>
    <link href="./resources/css/searchBuilder.bootstrap.css" rel="stylesheet"/>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
    <link rel="stylesheet" href="./resources/css/fixedColumns.dataTables.min.css">

    <script src="./resources/js/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/js/all.min.js" integrity="sha512-1JkMy1LR9bTo3psH+H4SV5bO2dFylgOy+UJhMus1zF4VEFuZVu5lsi4I6iIndE4N9p01z1554ZDcvMSjMaqCBQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="./resources/js/sweetalert.all.min.js"></script>
    <script src="./resources/js/jszip.js"></script>
    <script src="./resources/js/vfs_fonts.js"></script>
    <script src="./resources/js/jquery.dataTables.js"></script>
    <script src="./resources/js/dataTables.bootstrap.js"></script>
    <script src="./resources/js/dataTables.buttons.js"></script>
    <script src="./resources/js/buttons.bootstrap.js"></script>
    <script src="./resources/js/buttons.html.js"></script>
    <script src="./resources/js/dataTables.responsive.js"></script>
    <script src="./resources/js/responsive.bootstrap.js"></script>
    <script src="./resources/js/dataTables.searchBuilder.js"></script>
    <script src="./resources/js/searchBuilder.bootstrap.js"></script>
    <script src="./resources/js/bootstrap.bundle.min.js"></script>
    <script src="./resources/js/jquery.blockUI.min.js"></script>
    <script src="./resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="./resources/js/crypto-js.js"></script>
    <script src="./resources/js/AesUtil.js" type="text/javascript"></script>
    <script src="./resources/js/header.js"></script>
    <script type="text/javascript">

//        $('input[type=number]').on('mousewheel', function (e) {
//            $(this).blur();
//        });
//        // Disable keyboard scrolling
//        $('input[type=number]').on('keydown', function (e) {
//            var key = e.charCode || e.keyCode;
//            // Disable Up and Down Arrows on Keyboard
//            if (key === 38 || key === 40) {
//                e.preventDefault();
//            } else {
//                return;
//            }
//        });
//
//        $(document).keydown(function (event) {
//            if (event.keyCode === 123) { // Prevent F12
//                return false;
//            } else if (event.ctrlKey && event.shiftKey && event.keyCode === 73) { // Prevent Ctrl+Shift+I        
//                return false;
//            }
//        });
//
//        document.onkeydown = function () {
//            switch (event.keyCode) {
//                case 116: //F5 button
//                    event.returnValue = false;
//                    event.keyCode = 0;
//                    return false;
//            }
//        };
//
//        window.onload = function () {
//            document.body.oncontextmenu = function () {
//                var message = "Right-click not allowed";
//                //            swal(message);
//                return false;
//            };
//        };
//
//
//        window.onload = function () {
//
//            var isIE = /*@cc_on!@*/false || !!document.documentMode;
//            if (isIE) {
//                alert('Internet explorer is not supported for this application,\nplease use Chrome, Firefox, Edge etc.');
//            }
//        };
    </script>
    <noscript>Your browser does not support JavaScript! please enable java script to use website </noscript>
    <style>
        input[type=number]::-webkit-inner-spin-button,
        input[type=number]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        .btn-secondary {
            color: #fff;
            background-color: #0d6efd;
            border-color: #6c757d;
        }

        .nav .btn-toggle-nav a{
            color: #fff !important;
            padding: 5px 10px;
            display: block;
        }
        .nav .btn-toggle-nav li{
            list-style: disc;
            margin-left: 50px;
        }
        a[aria-expanded="true"] .fa-solid{
            transform: rotate(180deg);
        }
    </style>
    <script>
//        function getEmailForEcNo(id) {
//            var ecno = $("#" + id + "").val();
//            var jsonData = {};
//            jsonData = JSON.stringify({"id": ecno});
//            $.ajax({
//                type: "POST",
//                url: '/GNFC_BG/getEmailForEcNo.do',
//                dataType: 'json',
//                contentType: 'application/json',
//                data: jsonData,
//                async: false,
//                success: function (data)
//                {
//                    if (data.status === "1") {
//                        $("#" + id + "email").val(data.email);
//                        $("#" + id + "email").prop("readonly", true);
//                    } else {
//                        Swal.fire({text: '' + data.msg});
//                        $("#" + id + "email").val("");
//                        $("#" + id + "email").prop("readonly", true);
//                    }
//
//                },
//                error: function (jqXHR, status, err) {
//
//                    if (jqXHR.status === 401) {
//                        Swal.fire({text: 'Session Expired.'});
//                        window.location = 'login';
//                    }
//
//                }
//            });
//
//        }
//
//        function getFileExtension3(filename) {
//            return filename.slice((filename.lastIndexOf(".") - 1 >>> 0) + 2);
//        }

    </script>
</head>
