<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="container">
    <div class="col-8 offset-2">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">FULL_NAME</th>
                <th scope="col">BALANCE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
                <tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.fullName}</td>
                    <td>${item.balance}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="col-6 offset-3">
        <form:form action="/send" method="post" modelAttribute="money">
            <c:if test="${not empty message}">
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </c:if>
            <div class="mb-3">
                <label class="form-label">From</label>
                <form:input type="number" path="fromAccountId" class="form-control"/>
            </div>
            <div class="mb-3">
                <label class="form-label">To</label>
                <form:input type="number" path="toAccountId" class="form-control"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Amount</label>
                <form:input type="number" path="amount" class="form-control"/>
            </div>
            <input type="submit" class="btn btn-primary" value="Send"></input>
        </form:form>
    </div>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
-->
</body>
</html>