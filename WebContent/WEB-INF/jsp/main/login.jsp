<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title></head>
<style><%@include file="/style/main.css"%></style>
<body>
<div class="container">
	<div class="row">
 	<div class="col-md-6 col-md-offset-3 well">
	<form class="form-horizontal" name="loginForm" method="POST" action="repairthings">
	<fieldset>
		<div class="text-center">
			<h2>Авторизация</h2>
		</div>
		<input type="hidden" name="command" value="login" />

		<div class="form-group">
		<label class="col-lg-2 control-label">Логин</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" name="login" value="${login}">
		</div>
		</div>
		
		<div class="form-group">
		<label class="col-lg-2 control-label">Пароль</label>
		<div class="col-lg-8">
			<input type="password" class="form-control" name="password">
		</div>
		</div>
			<br/>
			<h5 class="text-danger text-center">${errorLoginPassMessage}</h5>
		<div class="form-group">
			<div class="col-lg-8 col-lg-offset-2">
			<button type="submit" class="btn btn-primary center-block">Войти</button>
			</div>
		</div>
	</fieldset>
	</form>
	</div>
	</div>
</div>
</body></html>