<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Moderator</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h1>Добро пожаловать, модератор ${user.firstName}!</h1>
				</div>
				<div class="col-md-3">
					<a href="repairthings?command=logout" class="btn btn-default pull-right">Выход</a>
				</div>
			</div>
			
			<h3>Новые заказы, ожидающие проверки:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Описание</th>
						<th>Клиент</th>
						<th>Изменить</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${pendingOrders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><textarea class="form-control" rows="5" readonly><c:out value="${order.description}"/></textarea></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td>
								<a href="repairthings?command=show_order_editor&id=${order.id}" title="Редактировать заказ"><img src="img/edit.png"></a>
								<a href="repairthings?command=check_order&id=${order.id}" title="Принять заказ"><img src="img/accept.png"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			
			<h3>Заказы для удаления:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Клиент</th>
						<th>Мастер</th>
						<th>Статус заказа</th>
						<th>Изменить</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${canceledOrders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td><c:out value="${order.masterName}"/></td>
			    			<td><c:out value="${order.orderStatus.name}"/></td>
			    			<td>
								<a href="repairthings?command=delete_order&id=${order.id}" title="Удалить заказ"><img src="img/delete.png"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			
			<h3>Блокировка пользователей:</h3>
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
								<c:if test="${user.userType!='ADMIN'&&user.userType!='MODERATOR'}"><a href="repairthings?command=change_user_status&id=${user.id}&status=${user.status}" title="Заблокировать/Разблокировать"><img src="img/lock.png"></a></c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</body>
</html>