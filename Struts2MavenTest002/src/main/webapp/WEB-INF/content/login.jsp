<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Struts2MavenTest002</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="./css/base.css"/>
		<!-- JS -->
		<script src="./js/jquery-1.11.3.js"></script>
		<script src="./js/jquery-ui.js"></script>
		<script src="./js/jquery.cookie.js"></script>
		<script src="./js/jquery-accordion.js"></script>
		<script src="./js/jquery.tablesorter.min.js"></script>
		<script src="./js/jquery.disableOnSubmit.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script src="./js/base.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>Login</small>
					</span>
				</h1>
			</div>

			<div class="row login-row">
				<s:form action="menu" cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">ﾕｰｻﾞｰID:</label>
						<div class="col-sm-4">
							<s:textfield
								name="loginBean.userId"
								cssClass="form-control"
								tabindex="1"
							/>
						</div>
						<div class="col-sm-4"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">ﾊﾟｽﾜｰﾄﾞ:</label>
						<div class="col-sm-4">
							<s:password
								name="loginBean.password"
								cssClass="form-control"
								tabindex="2"
								value="admin"
							/>
						</div>
						<div class="col-sm-4"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<s:submit
								value="LOGIN"
								cssClass="btn btn-primary"
								id="btn-login"
								tabindex="3">
							</s:submit>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4"></div>
						<div class="col-sm-4"></div>
						<div class="col-sm-4"></div>
					</div>
				</s:form>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<div class="alert alert-danger" role="alert">
						<s:fielderror theme="simple" />
						<s:actionerror theme="simple" />
					</div>
					<div class="alert alert-success" role="alert" >
						<s:actionmessage theme="simple" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
