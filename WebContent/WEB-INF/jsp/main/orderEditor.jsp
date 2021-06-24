<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Edit order</title></head>
<style><%@include file="/style/main.css"%></style>
<body>
<div class="container">
	<div class="row">
 	<div class="col-md-6 col-md-offset-3 well">
	<form class="form-horizontal" name="orderEditorForm" method="POST" action="repairthings">
	<fieldset>
		<input type="hidden" name="command" value="edit_order" />
		<input type="hidden" name="orderId" value="${order.id}"/>
		<h2>Редактирование заказа</h2>
		<hr>
		<div class="form-group">
			<label class="col-lg-2 control-label">Название вещи</label>
			<div class="col-lg-8">
				<input type="text" class="form-control" name="thingName" value="${order.thingName}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-2 control-label">Описание</label>
			<div class="col-lg-8">
				<textarea class="form-control" rows="7" name="description" placeholder="Описание поломки"><c:out value="${order.description}"/></textarea>
			</div>
		</div>
		
		<br/>
		<h5 class="text-danger text-center">${errorOrderEditMessage}</h5>
		
		<div class="form-group">
			<div class="col-lg-8 col-lg-offset-2">
			<button type="submit" class="btn btn-primary center-block">
				<c:if test="${user.userType == 'CUSTOMER'}">
					Редактировать
				</c:if>
				<c:if test="${user.userType == 'MODERATOR'}">
				    Принять заказ
				</c:if>
			</button>
			</div>
		</div>
	
	</fieldset>
	</form>
	</div>
	</div>
</div>
</body></html>