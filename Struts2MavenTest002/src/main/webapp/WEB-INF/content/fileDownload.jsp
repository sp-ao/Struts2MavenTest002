<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
	<jsp:include page="header.jsp"/>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>FileDownload</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9" id="main-contents"><!-- col-sm-9 -->

					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞ</span></h2>
						</div>
					</div>

					<div class="row fileupload-form">
						<s:form action="downloadStart" method="post" enctype="multipart/form-data">

						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<label class="control-label">ﾀﾞｳﾝﾛｰﾄﾞするﾃｰﾌﾞﾙを選択してください</label>
								<s:select
									id="download_table"
									name="fileDownloadBean.selectTable"
									headerKey="1"
									list="#session.selectMap"
									cssClass="form-control filedownload-select"
									tabindex="4"
								/>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10 fileupload-submit">
								<s:submit
									value="作成"
									cssClass="btn btn-lg btn-success"
								/>
							</div>
						</div>
						</s:form>
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
