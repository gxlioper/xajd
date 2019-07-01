<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=qjDate";
			document.forms[0].submit();
		}
		function toPrintOut(){//输出相应的打印页面
			showTopWin("/xgxt/zzlgdx_rcsw.do?method=qjDy&pkVal="+document.getElementById("guid").value,650,450);
		}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 申请 - 请假申请详细信息</a>
			</p>
		</div>

		<html:form action="zzlgdx_rcsw.do?method=qjQuery" method="post">
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>请假申请详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="rs" property="shjg" value="通过">
										<button type="button" onclick="toPrintOut();">
											打印
										</button>
									</logic:equal>
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<button type="button" c onclick="Close();return false;">
											关闭
										</button>
									</logic:equal>
									<logic:notEqual name="userOnLine" value="teacher"
										scope="session">
										<button type="button" onclick="back();">
											返回
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								身份证号
							</th>
							<td>
								<bean:write name="rs" property="sfzh" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								班级名称
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<th>
								申请时间
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
							<th>
								请假类型
							</th>
							<td>
								<bean:write name='rs' property="qjlx" />
							</td>
						</tr>
						<tr>
							<th>
								请假开始日期
							</th>
							<td>
								<bean:write name='rs' property="qjksrq" />
							</td>
							<th>
								请假截止日期
							</th>
							<td>
								<bean:write name='rs' property="qjjzrq" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<bean:write name="rs" property="qjkc1" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<bean:write name="rs" property="qjkcs1" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<bean:write name="rs" property="qjkc2" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<bean:write name="rs" property="qjkcs2" />
							</td>
						</tr>
						<tr>
							<th>
								请假课程
							</th>
							<td>
								<bean:write name="rs" property="qjkc3" />
							</td>
							<th>
								课程数
							</th>
							<td>
								<bean:write name="rs" property="qjkcs3" />
							</td>
						</tr>
						<tr>
							<th>
								审核结果
							</th>
							<td>
								<bean:write name='rs' property="shjg" />
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								审核意见
							</th>
							<td colspan="3">
								<bean:write name='rs' property="shyj" />
							</td>
						</tr>
						<tr>
							<th>
								请假原因
							</th>
							<td colspan="3">
								<html:textarea property="qjyy" readonly="true" cols="4" rows="5" style="word-break:break-all;width:90%" value="${rs.qjyy}"></html:textarea>
							</td>
						</tr>
				</table>
		</html:form>
	</body>
	</html>