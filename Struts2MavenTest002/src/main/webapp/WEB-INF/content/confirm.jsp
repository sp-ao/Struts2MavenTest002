<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
	<jsp:include page="header.jsp"/>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>
					<a href="/Struts2MavenTest002/menu.action">Struts2MavenTest002</a>
					<span class="page-sub-header">
						<small>Confirm</small>
					</span>
				</h1>
			</div>
			<div class="row">
				<jsp:include page="leftSide.jsp"/>
				<div class="col-sm-9" id="main-contents"><!-- col-sm-9 -->
					<div class="row">
						<div class="col-sm-12 page-cap">
							<h2><span class="label label-default">確認画面</span></h2>
						</div>
						<div class="col-sm-12">
							<table class="table table-striped">
							<s:iterator value="#session.OUTPUT_LIST" begin="0" step="1"  status="st">
								<!--  画面と処理名 -->
								<s:if test="#st.Index == 0">
									<caption>
										<s:property value='#session.OUTPUT_LIST[#st.Index]'/>
									</caption>
								</s:if>
								<s:elseif test="#st.Index > 0">
									<tr>
										<s:iterator value="#session.OUTPUT_LIST[#st.Index]" begin="0" step="1"  status="st2">
											<!--  ヘッダ項目 -->
											<s:if test="#st.Index == 1">
												<th>
													<s:property value='#session.OUTPUT_LIST[#st.Index][#st2.Index]'/>
												</th>
											</s:if>
											<!--  値 -->
											<s:else>
												<td>
													<s:property value='#session.OUTPUT_LIST[#st.Index][#st2.Index]'/>
												</td>
											</s:else>
										</s:iterator>
									</tr>
								</s:elseif>
							</s:iterator>
							</table>
						</div>

						<div class="col-sm-1">
							<s:form action="%{#session.SUBMIT_ACTION}">
								<input
									type="submit"
									value="実行"
									class="btn btn-success"
									id="btn-exec"
								/>
							</s:form>
						</div>
						<div class="col-sm-1">
							<form action="javascript:history.back();">
								<input
									type="submit"
									value="戻る"
									class="btn btn-warning"
									id="btn-back"
								/>
							</form>
						</div>
						<div class="col-sm-10"></div>
					</div>
					<div class="row">
					</div>
					<div class="col-sm-12">
					</div>
				</div><!-- col-sm-9 -->
			</div>
		</div>
	</body>
</html>