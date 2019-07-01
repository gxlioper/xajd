<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<div class="tab">
			<table width="80%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="6">
							<span>学生详细信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							学生类别
						</th>
						<td align="left" width="25%">
							<bean:write name="rs" property="xslb" />
						</td>
						<th>
							所属
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<th>
							学号
						</th>
						<td>
							<bean:write name="rs" property="xsxh" />
						</td>
						<th>
							身份证号
						</th>
						<td>
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
						<th>
							性别
						</th>
						<td>
							<bean:write name="rs" property="xbdm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								民族
							</th>
							<td align="left">
								<bean:write name="rs" property="mz" />
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<bean:write name="rs" property="zzmm" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							学校名称
						</th>
						<td>
							<bean:write name="rs" property="xxmc" />
						</td>
						<th>
							学校代码
						</th>
						<td>
							<bean:write name="rs" property="xxdm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								代码
							</th>
							<td align="left" width="25%">
								<bean:write name="rs" property="xydm" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							专业名称
						</th>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<th align="right" width="25%" bgcolor="DOEOEE">
							专业代码
						</th>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								班级代码
							</th>
							<td align="left">
								<bean:write name="rs" property="bjdm" />
							</td>
							<th>
								联系方式
							</th>
							<td align="left">
								<bean:write name="rs" property="lxfs" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							学历
						</th>
						<td align="left">
							<bean:write name="rs" property="xl" />
						</td>
						<th>
							学制
						</th>
						<td align="left">
							<bean:write name="rs" property="xzdm" />
						</td>
					</tr>
					<tr>
						<th>
							生源地区
						</th>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
						<th>
							培养方式
						</th>
						<td align="left">
							<bean:write name="rs" property="pyfs" />
						</td>
					</tr>
					<tr>
						<th>
							入学年度
						</th>
						<td align="left">
							<bean:write name="rs" property="nd" />
						</td>
						<th>
							上报时间
						</th>
						<td align="left">
							<bean:write name="rs" property="sbsj" />
						</td>
					</tr>
					<tr>
						<th>
							毕业年度
						</th>
						<td align="left">
							<bean:write name="rs" property="bynd" />
						</td>
						<logic:equal value="10491" name="xxdm">
							<th>
								协议书编号
							</th>
							<td align="left">
								<bean:write name="rs" property="xysbh" />
							</td>
						</logic:equal>

						<logic:notEqual value="10491" name="xxdm">
							<th>
								备注
							</th>
							<td align="left">
								<bean:write name="rs" property="memo" />
							</td>
						</logic:notEqual>

					</tr>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								邮箱
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdzxx" />
							</td>
							<th>
								QQ
							</th>
							<td align="left">
								<bean:write name="rs" property="qq" />
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>
								备注
							</th>
							<td align="left" colspan="4" width="75%">
								<html:textarea name="rs" property="memo" readonly="true"
									style="word-break:break-all;width:99%" cols="45"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								学校审核
							</th>
							<td>
								<html:text name="rs" property="sfsh" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								审核人
							</th>
							<td>
								<html:text name="rs" property="shperson" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								审核时间
							</th>
							<th>
								<html:text name="rs" property="shsj" readonly="true"
									style="word-break:break-all;width:99%" />
							</th>
						</tr>
						<tr>
							<th>
								不通过原因及修改意见
							</th>
							<td colspan="6" rowspan="2">
								<html:textarea name="rs" property="btgyy" rows="4"
									style="word-break:break-all;width:99%" readonly="true" />
							</td>
						</tr>
					</logic:equal>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<logic:notEqual name="doType" value="view">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							</logic:notEqual>
							<div class="btn">
								<button name="关闭" onclick="window.close();return false;">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
