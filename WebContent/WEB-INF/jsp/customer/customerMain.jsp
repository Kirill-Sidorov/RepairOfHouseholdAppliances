<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Customer</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>Добро пожаловать, ${user.firstName}!</h1>
				</div>
				<div class="col-md-6">
					<a href="repairthings?command=logout" class="btn btn-default pull-right">Выход</a>
				</div>
			</div>
			<hr>
			<a href="repairthings?command=show_order_creation" class="btn btn-primary btn-lg" >Создать новый заказ</a>	
			<hr>
			<h3>Заказы:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Клиент</th>
						<th>Мастер</th>
						<th>Статус заказа</th>
						<th>Общая стоимость</th>
						<th>Изменить</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><c:out value="${order.customerName}"/></td>
			    			<td><c:out value="${order.masterName}"/></td>
			    			<td><c:out value="${order.orderStatus.name}"/></td>
			    			<td><a href="repairthings?command=show_full_cost&id=${order.id}" title="Общая стоимость"><img src="img/cost.png"></a></td>
			    			<td>
								<a href="repairthings?command=show_order_editor&id=${order.id}" title="Редактировать заказ"><img src="img/edit.png"></a>
								<c:if test="${order.orderStatus!='REPAIR_PROCESS'&&order.orderStatus!='COMPLETED'}"><a href="repairthings?command=cancel_order&id=${order.id}" title="Отменить заказ"><img src="img/cancel.png"></a></c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
		</div>
	</body>
</html>