<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript">
			function sendData() {
				var xh = curr_row.getElementsByTagName('input')[0].value;
				var url = window.dialogArguments.document.getElementById('url').value;
				window.dialogArguments.document.forms[0].action = '/xgxt/jygl.do?method=xysData&xh='+xh+'&url='+url;
				window.dialogArguments.document.forms[0].submit();
				window.close();
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">


		<html:form action="/jygl" method="post">
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />


			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=xysData&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:155px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:155px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
										<logic:equal value="xy" name="userType" scope="session">
											<html:hidden property="queryequals_xydm" value="${userDep }" />
										</logic:equal>
										<html:select property="queryequals_xydm"
											onchange="initZyList();initBjList()" styleId="xy"
											style="width:160px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="blue">提示：双击一行可以选择一个学生</font>
							</logic:empty> </span>
					</h3>


					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)" nowrap>
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" ondblclick="sendData();"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
												<bean:write name="v" />
												<input type="hidden" value="<bean:write name="v" />" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								
									<%
										for (int i = 0; i < (Integer) request
												.getAttribute("maxNum")
												- ((List) request.getAttribute("rs")).size(); i++) {
									%>
									<tr>
										<logic:iterate id="t" name="topTr" offset="0" scope="request">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
							</logic:notEmpty>
							<logic:empty name="rs">
									<%
										for (int i = 0; i < (Integer) request
												.getAttribute("maxNum"); i++) {
									%>
									<tr>
										<logic:iterate id="t" name="topTr" offset="0" scope="request">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
								</logic:empty>
						</tbody>
					</table>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
						</script>
					<!--分页显示-->
				</div>
			</div>
		</html:form>
	</body>
</html>
