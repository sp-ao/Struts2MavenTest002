<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
	<jsp:include page="header.jsp"/>
	<script src="./js/menu.js"></script>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>MainMenu</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9" id="main-contents"><!-- col-sm-9 -->
					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">情報一覧</span></h2>
						</div>
					</div>

					<div class="col-sm-12">
						<s:hidden id="json-manu" value="%{#session.JSONMENU}" name="json-manu"/>
						<!--  出力ul -->
						<dl id="output" class="list-group"></dl>
						<!--  出力ul -->
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
				</div><!-- col-sm-9 -->
			</div>
		</div>
	</body>
</html>
