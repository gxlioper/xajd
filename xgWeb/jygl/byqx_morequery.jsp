<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 学生信息 - 毕业去向信息</a>
				</p>
		</div>
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<td align="left" colspan="4">
							毕业年度
							<html:text property="bynd" name="rs" style="width:35px"
								readonly="true" />
							<bean:message key="lable.xsgzyxpzxy" />
							<html:text property="xymc" name="rs" style="width:130px"
								readonly="true" />
							提交时间
							<html:text name="rs" property="tjsj" style="width:140px" readonly="true" />
						</td>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th width="15%">
						学号
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xsxh"  />
					</td>
					<th width="15%">
						姓名
					</th>
					<td width="35%">
						<bean:write name="rs" property="name"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						性别
					</th>
					<td align="left">
						<bean:write name="rs" property="xb"  />
					<th>
						身份证号
					</th>
					<td>
						<bean:write name="rs" property="id"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						生源地区
					</th>
					<td >
						<bean:write name="rs" property="sydq"  />
					</td>
					<th>
						<bean:message key="lable.xb" />
					</th>
					<td >
						<bean:write name="rs" property="xymc"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						专业
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc"  />
					<th>
						班级
					</th>
					<td>
						<bean:write name="rs" property="bjmc"  />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						毕业去向
					</th>
					<td>
					    <bean:write name="rs" property="byqx"  />
					</td>
					<th>
						联系地址
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdz" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						联系电话
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
					<th>
						邮政编码
					</th>
					<td align="left">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						移动电话
					</th>
					<td align="left">
						<bean:write name="rs" property="yddh" />
					</td>
					<th>
						电子邮箱
					</th>
					<td align="left">
						<bean:write name="rs" property="email" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						学校审核结果
					</th>
					<td align="left">
						<html:text name="rs" property="xxsh" style="width=100%" readonly="true"  />
					</td>
					<th>
						审核时间
					</th>
					<td align="left">
						<html:text name="rs" property="shsj" style="width=100%" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						就业单位
					</th>
					<td align="left">
						${rs.jydw}
					</td>
					<th>
						
					</th>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:55px">
				    <th>
						修改意见
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="xgyj" rows="3" 
							style="word-break:break-all;width:99%" readonly="true"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th>
						审核人
					</th>
					<td colspan="3">
						<html:text name="rs" property="shperson" style="width=100%" readonly="true" />
					</td>
				</tr>
				</tbody>
				<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button name="关闭" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</fieldset>
	</body>
</html>
