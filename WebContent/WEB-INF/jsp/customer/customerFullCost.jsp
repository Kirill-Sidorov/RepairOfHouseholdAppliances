<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Full cost</title></head>
<style><%@include file="/style/main.css"%></style>
	<body>
		<div class="container">
			<h3>Ремонт: ${order.thingName}</h3>
			<h3>Мастер: ${order.masterName}</h3>
			<h3>Стоимость работы: ${order.costWork}</h3>
			<hr>
			<h3>Стоимость запасных частей:</h3>
			<div class="bs-component">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>№</th>
						<th>Название</th>
						<th>Количество</th>
						<th>Стоимость</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="part" items="${parts}" varStatus="status">
			    		<tr>
			    			<td><c:out value="${status.count}"/></td>
			    			<td><c:out value="${part.partName}"/></td>
			    			<td><c:out value="${part.numberParts}"/></td>
			    			<td><c:out value="${part.cost}"/></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<hr>
			<h3>Общая стоимость: ${fullCost}</h3>
		</div>
	</body>
</html>