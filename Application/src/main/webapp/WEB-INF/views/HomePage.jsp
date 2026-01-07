

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <%@include file="./header.jsp" %>
    <body>
        <div class="main-content" id="blockdiv" name="blockdiv">
            <div class="container-fluid">
                <div class="row flex-nowrap col-sm-10">
                    <%@include file="./menu.jsp" %>
                    <form name="loginForm" id="loginForm">
                        <input type="hidden" class="form-control" name="renew" id="renew" value=''>
                        <input type="hidden" class="form-control" name="lcid" id="lcid" value=''>
                        <input type="hidden" class="form-control" name="today" id="today" value='${sessionScope.today}'>
                        <div class="col py-3">
                            <div class="card">
                                <div class="card-body" style="min-height: 600px">
                                    <div class="row" style="color: blue; font-weight: bold" >
                                        <center >Welcome</center>
                                        <hr/>
                                    </div>
                                    Welcome to, Application
                                    <br/>
                                    <br/>
                                </div>

                            </div>


                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
