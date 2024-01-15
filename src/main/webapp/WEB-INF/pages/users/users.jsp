<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <div class="container text-center">
        <form method="POST" action="${pageContext.request.contextPath}/Users">
            <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-success btn-lg">Add User</a>
                <button class="btn btn-success btn-lg" type="submit">Invoice</button>
            </c:if>
            <c:forEach var="user" items="${users}">
                <div class="row">
                    <div class="col">
                        <input type="checkbox" name="user_ids" value="${user.id}"/>
                    </div>
                    <div class="col">
                            ${user.username}
                    </div>
                    <div class="col">
                            ${user.email}
                    </div>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                        <div class="col">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditUser?id=${user.id}">Edit User</a>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </form>
    </div>
    <c:if test="${not empty invoices}">
        <h2>Invoices</h2>
        <c:forEach var="username" items="${invoices}" varStatus="status">
            ${status.index + 1}. ${username}
            <br>
        </c:forEach>
    </c:if>
</t:pageTemplate>