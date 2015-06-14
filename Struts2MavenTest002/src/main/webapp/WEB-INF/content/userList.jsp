<%@page import="struts2maven.Struts2MavenTest002.model.UserList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
	<jsp:include page="header.jsp"/>
	<script src="./js/userlist.js"></script>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>UserList</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9" id="main-contents"><!-- col-sm-9 -->
					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">ﾕｰｻﾞｰﾘｽﾄ</span></h2>
						</div>
					</div>
					<div class="row userlist-main">
						<div class="col-sm-12 form-box form-box-table form-box-table-userlist">
							<table id="userListTable" class="table table-striped header-fixed">
								<thead class="scrollHead">
									<tr>
										<th class="userlist-01">項番</th>
										<th class="userlist-02">ﾕｰｻﾞｰID</th>
										<th class="userlist-03">ﾕｰｻﾞｰ名</th>
										<th class="userlist-04">権限</th>
										<th class="userlist-05">状態</th>
										<th class="userlist-06">操作</th>
									</tr>
								</thead>
								<tbody class="scrollBody">
								<s:if test="#session.UserList != null">
									<s:iterator value="#session.UserList" begin="0" step="1"  status="st">
									<tr>
										<td class="userlist-01" onclick="tableClick(this); return false;"><s:property value="#st.count"/></td>
										<td class="userlist-02" onclick="tableClick(this); return false;"><s:property value="userid"/></td>
										<td class="userlist-03" onclick="tableClick(this); return false;"><s:property value="username"/></td>
										<td class="userlist-04">
											<s:if test='permission == "0"'>
												<span>一般</span>
											</s:if>
											<s:if test='permission == "1"'>
												<span>管理</span>
											</s:if>
											<s:if test='permission == "2"'>
												<span>ｹﾞｽﾄ</span>
											</s:if>
										</td>
										<td class="userlist-05">
											<s:if test='delflg == "0"'>
												<span>使用</span>
											</s:if>
											<s:else>
												<span>削除</span>
											</s:else>
										</td>
										<td class="userlist-06">
											<input
												type="button"
												value="削除"
												class="btn btn-xs btn-danger"
												id="btn_del<s:property value="#st.count"/>"
												onclick="userListDelete(this); return false;"
											>
										</td>
									</tr>
									</s:iterator>
								</s:if>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 権限判定 -->
					<div class="row sousa">
						<div class="row">
							<div class="col-sm-12 form-box listcap">
								<h2><span class="label label-default">ﾕｰｻﾞｰ作成・更新</span></h2>
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

						<s:form name="userListForm">
							<div class="row">
								<div class="col-md-2">
									<s:textfield
										id="inp_userid"
										name="userListBean.editUserId"
										cssClass="form-control userlist-input"
										placeholder="ﾕｰｻﾞｰID"
										maxlength="10"
										tabindex="1"
									/>
								</div>
								<div class="col-md-4">
									<s:textfield
										id="inp_username"
										name="userListBean.editUserName"
										cssClass="form-control userlist-input"
										placeholder="ﾕｰｻﾞｰ名"
										maxlength="20"
										tabindex="2"
									/>
								</div>
								<div class="col-md-2">
									<s:textfield
										id="inp_password"
										name="userListBean.editPassword"
										cssClass="form-control userlist-input"
										placeholder="ﾊﾟｽﾜｰﾄﾞ"
										maxlength="10"
										tabindex="3"
								/>
								</div>
								<div class="col-md-2">
									<s:select
										id="inp_permission"
										name="userListBean.editPermission"
										headerKey="1"
										list="#{'0':'一般','1':'管理','2':'ｹﾞｽﾄ'}"
										cssClass="form-control userlist-select"
										tabindex="4"
									/>
								</div>
								<div class="col-md-2">
									<s:select
										id="inp_delflg"
										name="userListBean.editDelFlg"
										headerKey="1"
										list="#{'0':'使用','1':'削除'}"
										cssClass="form-control userlist-select"
										tabindex="5"
									/>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-9 btn-m-box">
									<div class="btn-s-box">
										<input
											type="button"
											value="作成"
											class="btn btn-lg btn-primary"
											id="insert_button"
											onclick="userListInsert(); return false;"
											tabindex="6"
										/>
									</div>
									<div class="btn-s-box">
										<input
											type="button"
											value="更新"
											class="btn btn-lg btn-success"
											id="update_button"
											onclick="userListUpdate(); return false;"
											tabindex="7"
										/>
									</div>
									<div class="btn-s-box">
										<input
											type="button"
											value="ｸﾘｱ"
											class="btn btn-lg btn-warning"
											id="clear_button"
											onclick="initField(); return false;"
											tabindex="8"
										/>
									</div>
								</div>

							</div>
						</s:form>
					</div>
					<div class="col-sm-12">
					</div>
				</div><!-- col-sm-9 -->
			</div>
		</div>
	</body>
</html>