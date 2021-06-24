<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Admin</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h1>Добро пожаловать, администратор ${user.firstName}!</h1>
				</div>
				<div class="col-md-3">
					<a href="repairthings?command=logout" class="btn btn-default pull-right">Выход</a>
				</div>
			</div>
			<a href="repairthings?command=show_registration" class="btn btn-primary btn-lg">Регистрация нового пользователя</a>
			<hr>
			<h3>Пользователи:</h3>
			<div class="bs-component">
			<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>№</th>
					<th>Логин</th>
					<th>Группа</th>
					<th>Имя</th>
					<th>Фамилия</th>
					<th>Номер телефона</th>
					<th>Статус</th>
					<th>Авторизован</th>
					<th>Изменить</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${unlockedUsers}"  varStatus="status">
		    		<tr>
		    			<td><c:out value="${status.count}"/></td>
		    			<td><c:out value="${user.login}"/></td>
		    			<td><c:out value="${user.userType}"/></td>
		    			<td><c:out value="${user.firstName}"/></td>
		    			<td><c:out value="${user.lastName}"/></td>
		    			<td><c:out value="${user.phone}"/></td>
		    			<td><c:out value="${user.status}"/></td>
		    			<td><c:out value="${user.authorized}"/></td>
		    			<td>
							<a href="repairthings?command=show_user_editor&id=${user.id}" title="Редактировать"><img src="img/edit.png"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
			</div>
			<h3>Заблокированные пользователи:</h3>
			<div class="bs-component">
			<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>№</th>
					<th>Логин</th>
					<th>Группа</th>
					<th>Имя</th>
					<th>Фамилия</th>
					<th>Номер телефона</th>
					<th>Статус</th>
					<th>Авторизован</th>
					<th>Изменить</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${lockedUsers}"  varStatus="status">
		    		<tr>
		    			<td><c:out value="${status.count}"/></td>
		    			<td><c:out value="${user.login}"/></td>
		    			<td><c:out value="${user.userType}"/></td>
		    			<td><c:out value="${user.firstName}"/></td>
		    			<td><c:out value="${user.lastName}"/></td>
		    			<td><c:out value="${user.phone}"/></td>
		    			<td><c:out value="${user.status}"/></td>
		    			<td><c:out value="${user.authorized}"/></td>
		    			<td>
							<a href="repairthings?command=show_user_editor&id=${user.id}" title="Редактировать"><img src="img/edit.png"></a>
							<a href="repairthings?command=delete_user&id=${user.id}" title="Удалить"><img src="img/delete.png"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
		</div>
	</body>
</html>