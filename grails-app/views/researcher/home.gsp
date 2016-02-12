<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home</title>

    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="bootstrap.js"/>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav">
                <li>
                    <a href="#">Log Out</a>
                </li>
                <li>
                    <g:link controller="Researcher" action="editLearners">Learners</g:link>
                </li>
                <li>
                    <a href="#">Modules</a>
                </li>
                <li>
                    <a href="#">My Account</a>
                </li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">App For Literacy
                <small>Welcome, ${fname}!</small>
            </h1>
        </div>
    </div>
    <!-- /.row -->


    <g:each in="${learners}" var="p">
        <div class="row" id="learners">
            <div class="col-md-5">
                <h3>${p}</h3>
                <p>Put information about ${p} here.</p>
                <a class="btn btn-primary" href="#">View Progress <span class="glyphicon glyphicon-chevron-right"></span></a>
                <a class="btn btn-primary" href="#">Assign Modules <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </g:each>
    <hr>

</div>
<!-- /.container -->

</body>

</html>
