<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
	</head>

	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 退学 - 退学审核</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="stu_txsqsh" />

			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单个审核</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="30%">
								<bean:write name="rs" property="xh" />
								<input type="hidden"
									value="<bean:write name="rs" property="xh" />" name="xh"
									id="xh" />
							</td>
							<th width="16%">
								年度
							</th>
							<td width="30%">
								<bean:write name="rs" property="nd" />
								<input type="hidden"
									value="<bean:write name="rs" property="nd" />" name="nd"
									id="nd" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								手机号码
							</th>
							<td align="left">
								<bean:write name="rs" property="sjhm" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								家庭电话
							</th>
							<td align="left">
								<bean:write name="rs" property="qtdh" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								申请日期
							</th>
							<td align="left">
								<bean:write name="rs" property="sqrq" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th rowspan="3">
								申请理由
							</th>
							<td align="left" rowspan="3">
								<bean:write name="rs" property="sqly" />
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>

						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>

						</tr>
						<tr>

							<th>
								审核
							</th>
							<td align="left">
								<logic:equal value="xx" name="userType" scope="request">
									<html:select name="rs" styleId="yesNo" property="xxsh">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:equal>
								<logic:equal value="xy" name="userType" scope="request">
									<html:select name="rs" styleId="yesNo" property="xysh">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:equal>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								意见
							</th>
							<td align="left" colspan="3">
								<logic:equal value="xy" name="userType" scope="request">
									<html:textarea property="xyyj" name="rs"
										style="height:80;width:100%" />
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="request">
									<html:textarea property="xyyj" name="rs"
										style="height:80;width:100%;" disabled="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								经办部门意见
							</th>
							<td align="left" colspan="3">
								<logic:equal value="xx" name="userType" scope="request">
									<html:textarea property="xxyj" name="rs"
										style="height:80;width:100%" />
								</logic:equal>
								<logic:notEqual value="xx" name="userType" scope="request">
									<html:textarea property="xxyj" name="rs"
										style="height:80;width:100%;" disabled="true" />
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button
										onclick="viewAdd('/xgxt/abroad_query.do?act=txsq_sh&doType=save','add');"
										id="buttonSave">
										保 存
									</button>
									<button onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

				</table>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
