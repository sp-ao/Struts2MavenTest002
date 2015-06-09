<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
	<jsp:include page="header.jsp"/>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>FileUpload</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9" id="main-contents"><!-- col-sm-9 -->

					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">ﾌｧｲﾙｱｯﾌﾟﾛｰﾄﾞ</span></h2>
						</div>
					</div>

					<div class="row fileupload-form">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
							<s:form action="fileUpload-Upload" method="post" enctype="multipart/form-data">
								<label class="control-label">ｱｯﾌﾟﾛｰﾄﾞするﾌｧｲﾙを選択してください</label>
								<s:file name="fileUploadBean.document" cssClass="file" label="File" />
							</s:form>
						</div>
						<div class="col-sm-1"></div>
					</div>

					<div class="row msg-box">
						<div class="col-sm-12 fileupload-msg-box">
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
