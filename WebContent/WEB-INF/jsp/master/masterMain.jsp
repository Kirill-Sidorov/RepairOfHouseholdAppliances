<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Master</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h1>Добро пожаловать, мастер ${user.firstName}!</h1>
				</div>
				<div class="col-md-3">
					<a href="repairthings?command=logout" class="btn btn-default pull-right">Выход</a>
				</div>
			</div>
			<h3>Доступные для выполнения заказы:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Описание</th>
						<th>Клиент</th>
						<th>Выполнить заказ</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${availableOrders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><textarea class="form-control" rows="5" readonly><c:out value="${order.description}"/></textarea></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td>
								<a href="repairthings?command=take_order&id=${order.id}" title="Выполнить заказ"><img src="img/accept.png"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			<h3>Незавершенные заказы:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Клиент</th>
						<th>Статус заказа</th>
						<th>Стоимость работы</th>
						<th>Изменить</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${uncompletedOrders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td><c:out value="${order.orderStatus.name}"/></td>
			    			<td><c:out value="${order.costWork}"/></td>
			    			<td>
								<a href="repairthings?command=show_master_order_editor&id=${order.id}" title="Редактировать заказ"><img src="img/edit.png"></a>
								<c:if test="${order.orderStatus=='REPAIR_PROCESS'}"><a href="repairthings?command=confirm_order_execution&id=${order.id}" title="Подтвердить выполнение заказа"><img src="img/accept.png"></a></c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			<h3>Завершенные заказы:</h3>
				<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Клиент</th>
						<th>Статус заказа</th>
						<th>Стоимость работы</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${completedOrders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td><c:out value="${order.orderStatus.name}"/></td>
			    			<td><c:out value="${order.costWork}"/></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</body>
</html>