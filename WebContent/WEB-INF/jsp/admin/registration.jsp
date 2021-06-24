<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Registration</title></head>
<style><%@include file="/style/main.css"%></style>
<body>
<div class="container">
	<div class="row">
 	<div class="col-md-6 col-md-offset-3 well">
	<form class="form-horizontal" name="registrationForm" method="POST" action="repairthings">
	<fieldset>
	<div class="text-center">
		<h2>Регистрация</h2>
	</div>
	<input type="hidden" name="command" value="register_user" />
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Имя</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" name="firstName" value="${createdUser.firstName}">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Фамилия</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" name="lastName" value="${createdUser.lastName}">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Телефон</label>
		<div class="col-lg-8">
			<input type="tel" class="form-control" name="phoneNumber" placeholder="8-xxx-xxx-xx-xx" value="${createdUser.phone}"  maxlength="10">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Пользователь</label>
		<div class="col-lg-8">
			<select class="form-control" name="userType">
				<option value="customer">Клиент</option>
				<option value="master">Мастер</option>
				<option value="moderator">Модератор</option>
				<option value="warehouser">Работник склада</option>
				<option value="warehouser">Администратор</option>
			</select>
			<br>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Логин</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" name="login" value="${createdUser.login}">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Пароль</label>
		<div class="col-lg-8">
			<input type="password" class="form-control" name="password">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-2 control-label">Повторите пароль</label>
		<div class="col-lg-8">
			<input type="password" class="form-control" name="passwordRepeat">
		</div>
	</div>
	
	<br/>
	<h5 class="text-danger text-center">${errorRegistrationMessage}</h5>
	
	<div class="form-group">
		<div class="col-lg-8 col-lg-offset-2">
		<button type="submit" class="btn btn-primary center-block">Зарегистрироваться</button>
		</div>
	</div>
	
	</fieldset>
	</form>
	</div>
	</div>
</div>
</body></html>