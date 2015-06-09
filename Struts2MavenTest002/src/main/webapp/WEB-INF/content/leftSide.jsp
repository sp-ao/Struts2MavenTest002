<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<!-- /.col-sm3 -->
	<div class="col-sm-3">
		<div id="accordion" class="list-group">
			<div class="list-menu-00 list-group-item left-menu-cap acstop">
				<div>
					<s:if test="#session != null">
						<span>ﾛｸﾞｲﾝﾕｰｻﾞｰ:</span>
						<s:if test="#session != null">
							<span><s:property value="#session.userid"/></span>
						</s:if>
					</s:if>
				</div>
				<div>
				<s:if test="#session != null">
					<span>権限:</span>
					<s:if test="#session.permission == 1">
						<span>管理ﾕｰｻﾞｰ</span>
					</s:if>
					<s:if test="#session.permission == 0">
						<span>一般ﾕｰｻﾞｰ</span>
					</s:if>
					<s:if test="#session.permission != 0">
						<s:if test="#session.permission != 1">
							<span>ｹﾞｽﾄﾕｰｻﾞｰ</span>
						</s:if>
					</s:if>
					<input type="hidden" id="permission" value='<s:property value="#session.permission"/>' />
				</s:if>
				</div>
			</div>
			<div class="list-menu-01 list-group-item left-menu-cap">
				<a href="<s:url action="menu" />">ﾒｲﾝﾒﾆｭｰ</a>
			</div>
			<div class="list-group-item list-contents-dummy"></div>
			<div class="list-menu-02 list-group-item left-menu-cap list-move">ﾃﾞｰﾀ入力</div>
			<div class="list-group-item list-contents">
				<ul>
					<li><a href="<s:url action='userList' />">ﾕｰｻﾞｰﾘｽﾄ</a></li>
					<li><a href="<s:url action='itemList' />">商品ﾘｽﾄ</a></li>
					<li>ｺﾝﾃﾝﾂ3</li>
					<li>ｺﾝﾃﾝﾂ4</li>
				</ul>
			</div>

			<div class="list-menu-03 list-group-item left-menu-cap list-move">ﾌｧｲﾙ操作</div>
			<div class="list-group-item list-contents">
				<ul>
					<li><a href="<s:url action='fileUpload' />">ﾌｧｲﾙｱｯﾌﾟﾛｰﾄﾞ</a></li>
					<li><a href="<s:url action='fileDownload' />">ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞ</a></li>
					<li>ｺﾝﾃﾝﾂ3</li>
					<li>ｺﾝﾃﾝﾂ4</li>
				</ul>
			</div>
			<div class="list-menu-04 list-group-item left-menu-cap acstop">
				<a href="<s:url action="logout" />">ﾛｸﾞｱｳﾄ</a>
			</div>
		</div>
	</div><!-- /.col-sm3 -->