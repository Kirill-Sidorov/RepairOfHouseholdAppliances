<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Warehouser</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<h1>Добро пожаловать, работник склада ${user.firstName}!</h1>
				</div>
				<div class="col-md-3">
					<a href="repairthings?command=logout" class="btn btn-default pull-right">Выход</a>
				</div>
			</div>
			<hr>
			<a href="repairthings?command=show_replacement_part_creation" class="btn btn-primary btn-lg" >Добавить новую деталь</a>
			<h3>Запасные части:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название детали</th>
						<th>Стоимость</th>
						<th>Количество на складе</th>
						<th>Изменить</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="part" items="${replacementParst}" varStatus="status">
			    		<tr <c:if test="${part.inStock <= 1}">class="danger"</c:if>>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${part.name}"/></td>
			    			<td><c:out value="${part.cost}"/></td>
			    			<td><c:out value="${part.inStock}"/></td>
			    			<td>
								<a href="repairthings?command=show_replacement_part_editor&id=${part.id}" title="Редактировать"><img src="img/edit.png"></a>
								<a href="repairthings?command=delete_replacement_part&id=${part.id}" title="Удалить"><img src="img/delete.png"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			<h3>Заказы, ожидающие поступления запасных частей:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название вещи</th>
						<th>Мастер</th>
						<th>Статус заказа</th>
						<th>Стоимость работы</th>
						<th>Проверить наличие з/п</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderWaitParts}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${order.thingName}"/></td>
			    			<td><c:out value="${order.masterName}"/></td>
			    			<td><c:out value="${order.orderStatus.name}"/></td>
			    			<td><c:out value="${order.costWork}"/></td>
			    			<td>
								<a href="repairthings?command=show_order_parts&id=${order.id}" title="Проверить наличие з/п"><img src="img/search.png"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</body>
</html>