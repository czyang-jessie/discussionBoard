
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%> 
        <title>Login Page</title>
        <style>
            body
            {
                background: url("http://www.planwallpaper.com/static/images/518079-background-hd.jpg")fixed;
                background-size: cover;
                padding: 0;
                margin: 0;
            }

            .wrap
            {
                width: 100%;
                height: 100%;
                min-height: 100%;
                position: absolute;
                top: 0;
                left: 0;
                z-index: 99;
                margin-top:100px;
            }

            p.form-title
            {
                font-family: 'Open Sans' , sans-serif;
                font-size: 20px;
                font-weight: 600;
                text-align: center;
                color: #FFFFFF;
                margin-top: 5%;
                text-transform: uppercase;
                letter-spacing: 4px;
            }

            form
            {
                width: 250px;
                margin: 0 auto;
            }

            form.login input[type="text"], form.login input[type="password"]
            {
                width: 100%;
                margin: 0;
                padding: 5px 10px;
                background: rgba(255,255,255,0.8);
                ;
                border-width: 0;
                border-bottom-color: #FFFFFF;
                border-bottom-style: solid;
                /* [disabled]border-bottom-width: 1px; */
                outline: 0;
                font-style: italic;
                font-size: 14px;
                font-weight: 400;
                letter-spacing: 1px;
                margin-bottom: 5px;
                color: #020202;
                outline: 0;
            }

            .submit
            {
                width: 100%;
                font-size: 14px;
                text-transform: uppercase;
                font-weight: 500;
                margin-top: 16px;
                outline: 0;
                cursor: pointer;
                letter-spacing: 1px;
            }

            form.login input[type="submit"]:hover
            {
                transition: background-color 0.5s ease;
            }

            form.login .remember-forgot
            {
                float: left;
                width: 100%;
                margin: 10px 0 0 0;
            }
            form.login .forgot-pass-content
            {
                min-height: 20px;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            form.login label, form.login a
            {
                font-size: 12px;
                font-weight: 400;
                color: #FFFFFF;
            }

            form.login a
            {
                transition: color 0.5s ease;
            }

            form.login a:hover
            {
                color: #2ecc71;
            }

            .pr-wrap
            {
                width: 100%;
                height: 100%;
                min-height: 100%;
                position: absolute;
                top: 0;
                left: 0;
                z-index: 999;
                display: none;
                margin-top:100px;
            }

            .show-pass-reset
            {
                display: block !important;
            }

            .pass-reset
            {
                margin: 0 auto;
                width: 250px;
                position: relative;
                margin-top: 22%;
                z-index: 999;
                background: #FFFFFF;
                padding: 20px 15px;
            }

            .pass-reset label
            {
                font-size: 12px;
                font-weight: 400;
                margin-bottom: 15px;
            }

            .pass-reset input[type="email"]
            {
                width: 100%;
                margin: 5px 0 0 0;
                padding: 5px 10px;
                background: 0;
                border: 0;
                border-bottom: 1px solid #000000;
                outline: 0;
                font-style: italic;
                font-size: 17px;
                font-weight: 400;
                letter-spacing: 1px;
                margin-bottom: 5px;
                color: #000000;
                outline: 0;
            }

            .pass-reset input[type="submit"]
            {
                width: 100%;
                border: 0;
                font-size: 14px;
                text-transform: uppercase;
                font-weight: 500;
                margin-top: 10px;
                outline: 0;
                cursor: pointer;
                letter-spacing: 1px;
            }

            .pass-reset input[type="submit"]:hover
            {
                transition: background-color 0.5s ease;
            }
            .posted-by
            {
                position: absolute;
                bottom: 26px;
                margin: 0 auto;
                color: #FFF;
                background-color: rgba(0, 0, 0, 0.66);
                padding: 10px;
                left: 45%;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div  style="font-size: 38px; color:white; margin-top: 60px">Discussion Board</div>
                    <div class="wrap">
                        <p/>
                        <p>&nbsp;</p>
                        <p class="form-title" style="font-size: 24px">
                            Sign In</p>
                        <p>&nbsp;</p>


                        <form class="login" method="post" action="login">
                            <input type="text"  name ="username" required> 
                            <input type ="password"  name ="password" required>
                            <input type="Submit" class="btn btn-primary btn-sm submit" value="SIGN IN">
                           
                        </form>
                    </div>
                </div>



                <br/>






                </body>
                </html>
