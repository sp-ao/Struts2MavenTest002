<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib uri="/struts-tags" prefix="s" %>
	<jsp:include page="header.jsp"/>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>Report</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9"><!-- col-sm-9 -->
					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">エラー</span></h2>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-9">
							<s:property value="%{exception.message}" />
							<s:property value="%{exceptionStack}" />
						</div>
					</div>

					<div class="alert alert-danger" role="alert">
						<s:actionerror />
					</div>
				</div><!-- col-sm-9 -->
			</div>
		</div>
	</body>
</html>
