<%@page import="struts2maven.Struts2MavenTest002.model.ItemList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
	<jsp:include page="header.jsp"/>
	<script src="./js/itemlist/itemlist.js"></script>
	<script src="./js/itemlist/chkitemlist.js"></script>
	<body>
		<div id="container" class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>ItemList</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9 main-contents" id="main-contents"><!-- col-sm-9 -->
					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">商品ﾘｽﾄ</span></h2>
						</div>
					</div>

					<div class="row itemlist-main">
						<div class="col-sm-12 form-box form-box-table ">
							<s:hidden id="itemlist-data" value="%{#session.ItemList}" name="itemlist-data"/>
							<s:form name="itemListForm">
								<table id="itemListTable" class="table table-striped">
									<thead>
										<tr>
											<th>項番</th>
											<th>商品ｺｰﾄﾞ</th>
											<th>商品名</th>
											<th>商品区分</th>
											<th>状態</th>
											<th>対象</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>項番</th>
											<th>商品ｺｰﾄﾞ</th>
											<th>商品名</th>
											<th>商品区分</th>
											<th>状態</th>
											<th>対象</th>
										</tr>
									</tfoot>
									<tbody>
									<s:if test="#session.ItemList != null">
										<s:iterator value="#session.ItemList" begin="0" step="1"  status="st">

											<tr>
												<td><s:property value='#st.count'/></td>
												<td>
													<s:textfield
														name="itemList[%{#st.index}].itemCd"
														cssClass="form-control input-sm input-itemcd"
														value="%{itemcd}"
														maxlength="10"
														readonly="true"
													/>
												</td>
												<td>
													<s:textfield
														name="itemList[%{#st.index}].itemName"
														cssClass="form-control input-sm input-itemname"
														value="%{itemname}"
														maxlength="25"
													/>
												</td>
												<td>
													<s:textfield
														name="itemList[%{#st.index}].itemKbn"
														cssClass="form-control input-sm input-itemkbn"
														value="%{itemkbn}"
														maxlength="2"
													/>
												</td>
												<td>
													<s:select
														name="itemList[%{#st.index}].delFlg"
														list="#{'0':'使用','1':'削除'}"
														cssClass="form-control input-sm input-itemdelflg"
														value="%{delflg}"
													/>
												</td>
												<td>
													<s:checkbox
														name="itemList[%{#st.index}].targetRow"
														value="itemList[%{#st.index}].targetRow%{#st.index}"
														cssClass="check"
													/>
												</td>
											</tr>

										</s:iterator>
									</s:if>
									</tbody>
								</table>
							</s:form>

							<div class="col-sm-12">
								<div class="col-sm-10"></div>
								<div class="col-sm-2 itemlist-selall label label-default">
									<label class="">
										<span>全て選択：</span>
										<s:checkbox
											id="check"
											name=""
											cssClass=""
											onchange="checkBoxAll(this); return false;"
										/>
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 msg-box">
							<div class="alert alert-danger" role="alert">
								<s:fielderror theme="simple" />
								<s:actionerror theme="simple" />
							</div>
							<div class="alert alert-success" role="alert" >
								<s:actionmessage theme="simple" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">商品ﾘｽﾄ操作</span></h2>
						</div>
					</div>

					<div class="row itemlist-form-box">
						<!--  対象全選択チェックボックス -->
						<div class="col-sm-12">
							<div class="col-sm-12">
								<h4><span class="label label-default">商品ﾘｽﾄ 更新・作成</span></h4>
							</div>
						</div>

						<!--  商品ﾘｽﾄ更新・作成 -->
						<div class="col-sm-12">
							<div class="col-sm-12">
								<!--  更新 -->
								<div class="col-sm-3">
									<label class="control-label">
										<span>商品ﾘｽﾄ更新(選択済行)：</span>
									</label>
									<s:form>
										<input
											type="button"
											value="更新"
											class="btn btn-success"
											id="update_button"
											onclick="itemListUpdate(); return false;"
										/>
									</s:form>
								</div>
								<!--  作成 -->
								<div class="col-sm-9">
									<s:form action="ItemListUpload" method="post" enctype="multipart/form-data">
										<label class="control-label">商品ﾘｽﾄ新規作成：ﾕｰｻﾞｰﾘｽﾄCSVﾌｧｲﾙを選択してください</label>
										<s:file
											name="fileUploadBean.document"
											cssClass="file"
											label="File"
											accept="text/comma-separated-values"
											onchange="isCsv(document.getElementById('ItemListUpload_fileUploadBean_document').value); return false;"/>
									</s:form>
								</div>
							</div>
						</div>
					</div>
				</div><!-- col-sm-9 -->
			</div>
		</div>
	</body>
</html>