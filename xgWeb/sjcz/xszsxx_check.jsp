<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xyDisabled('xy');bjLimit('bj')">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em>
			</p>
		</div>
		<!-- 标题 end-->
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div class="toolbox">
			<div class="comp_title"><ul><li><a href="#" onclick="refreshForm('xszsxx_check.do');" id="dm_ydlb"><span>性别和床位号验证</span> </a></li>
			<li><a href="#" onclick="refreshForm('xszsxx_check.do?act=sshf');" id="dm_ydlb"><span>宿舍划分验证</span> </a></li></ul></div>
<%--				<button type="button"  onclick="refreshForm('xszsxx_check.do')"></button>--%>
<%--				<button type="button"  onclick="refreshForm('xszsxx_check.do?act=sshf')"></button>--%>
				<!-- 查询结果-->
				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
							</span>
						</h3>
						<logic:equal value="sshf" name="act">
							<table summary="" class="dateline" align="" width="100%">
								<!-- 表头 -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>年级</td>
										<td><bean:message key="lable.xb" /></td>
										<td>楼栋名称</td>
										<td>寝室号</td>
										<td>已入住人数</td>
										<td>划分床位数</td>
										<td>异常类型</td>
									</tr>
								</thead>
								<!-- 表头 end-->
								<!--内容 -->
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xy"/></td>
											<td><bean:write name="s" property="ldmc"/></td>
											<td><bean:write name="s" property="qsh"/></td>
											<td><bean:write name="s" property="rzrs"/></td>
											<td><bean:write name="s" property="cws"/></td>
											<td><bean:write name="s" property="exc_type"/></td>
										</tr>
									</logic:iterate>
									<!--内容 -->
								</tbody>
							</table>
						</logic:equal>
						<logic:notEqual value="sshf" name="act">
							<table summary="" class="dateline" align="" width="100%">
								<!-- 表头 -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>年级</td>
										<td>学号</td>
										<td>姓名</td>
										<td>性别</td>
										<td><bean:message key="lable.xb" /></td>
										<td>专业</td>
										<td>楼栋名称</td>
										<td>寝室号</td>
										<td> 床位号</td>
										<td>入住日期</td>
										<td>收费标准</td>
										<td>异常类型</td>
									</tr>
								</thead>
								<!-- 表头 end-->
								<!--内容 -->
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
											<td><bean:write name="s" property="nj"/></td>
											<td><bean:write name="s" property="xh"/></td>
											<td><bean:write name="s" property="xm"/></td>
											<td><bean:write name="s" property="xb"/></td>
											<td><bean:write name="s" property="xy"/></td>
											<td><bean:write name="s" property="zymc"/></td>
											<td><bean:write name="s" property="ldmc"/></td>
											<td><bean:write name="s" property="qsh"/></td>
											<td><bean:write name="s" property="cwh"/></td>
											<td><bean:write name="s" property="rzrq"/></td>
											<td><bean:write name="s" property="sfbz"/></td>
											<td><bean:write name="s" property="exc_type"/></td>
										</tr>
									</logic:iterate>
									<!--内容 -->
								</tbody>
							</table>
						</logic:notEqual>
<%--						<!--分页-->--%>
<%--						<jsp:include flush="true"--%>
<%--							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>--%>
<%--						<!--分页end-->--%>
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="msg" value="${msg }" />

			<script type="text/javascript">
				alert($('msg').value);
			</script>

		</logic:present>
	</body>
</html>
