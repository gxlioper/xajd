<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"  />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function cfqr(obj) {
				if (confirm('确定要对该处分信息进行确认吗,确认后不能进行撤消操作，请仔细核对！')) {
					var pk = $("pkValue").value;
					refreshForm("grwjcfxxview.do?act=save&pk="+pk);
				}
			}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/grwjcfxxview.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 数据维护 - 个人违纪信息</a>
			</p>
		</div>
				<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			      	<table border="0" id="rsTable" width="100%" class="formlist">
<%--			      			<div class="tab">--%>
<%--				<table width="100%" border="0" class="formlist">--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="4">--%>
<%--								<span>处分决定书或附件</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr style="23px" onmouseover="rowOnClick(this)">--%>
<%--							<logic:empty name="fjList">--%>
<%--								<td colspan="5" align="center">暂无决定书或附件</td>--%>
<%--							</logic:empty>--%>
<%--							<logic:notEmpty name="fjList">--%>
<%--								<logic:iterate id="s" name="fjList">--%>
<%--									<td align="center"><a href="downloadfilewj.do?len=&wjsclj=${s.fjsclj }" target=_black>下载</a> </td>--%>
<%--									<td align="center">${s.cfwh }</td>--%>
<%--									<td align="center">${s.cflbmc }</td>--%>
<%--									<td align="center">${s.cfyymc }</td>--%>
<%--									<td align="center">${s.cfsj }</td>--%>
<%--								</logic:iterate>--%>
<%--							</logic:notEmpty>--%>
<%--						</tr>--%>
<%--						</tbody>--%>
<%--			      	</table>--%>
<%--			   </div>--%>
			     
			     
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>个人违纪信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="userType" value="stu">
										<logic:equal name="rs" property="xsqr" value="未确认">
										<button type="button" class="" onclick="cfqr()" 
											id="buttonClose">
											学生确认
										</button>
										</logic:equal>
									</logic:equal>
									<button type="button" class="" onclick="Close();return false;" 
										id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr style="height:22px">
					<th align="right">
						学号
					</th>
					<td align="left">
						${rs.xh}
					</td>
					<th align="right">
						姓名
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						性别
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xb"/></logic:present>
					</td>
					<th align="right">
						年级
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="nj"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						学年
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xn"/></logic:present>
					</td>
					<th align="right">
						学期
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xq"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xymc"/></logic:present>
					</td>
					<th align="right">
						专业名称
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="zymc"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						班级名称
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="bjmc"/></logic:present>
					</td>
					<th align="right">
						违纪时间
					</th>
					<td align="left">
						${rs.wjsj }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						处分文号
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfwh"/></logic:present>
					</td>
					<th align="right">
						处分时间
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfrq"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						处分类别
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cflbmc"/></logic:present>
					</td>
					<th align="right">
						处分原因
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfyymc"/></logic:present>
					</td>
				</tr>
				<tr>
					<th align="right">
						违纪<logic:equal value="10290" name="xxdm">事实</logic:equal><logic:notEqual value="10290" name="xxdm">内容</logic:notEqual>
					</th>
					<td align="left" colspan="3">
						<div style="word-break:break-all;width:95%">
							<logic:present name="rs"><bean:write name="rs" property="bz"/></logic:present>
						</div>
					</td>
				</tr>
				<!-- 广州大学有学生处分确认时间 -->
				<logic:equal value="11078" name="xxdm">
				
				
				<tr style="height:22px">
					<th align="right">
						确认时间
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="qrsj"/></logic:present>
					</td>
					<th align="right">
						学生处分是否确认
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xsqr"/></logic:present>
					</td>
				</tr>
		</logic:equal>
			</table>
			</div>
	</body>
		<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
	</html:form>
</html>
