<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>招聘详细信息</a>
			</p>
		</div>

		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>招聘详细信息</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="reset" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th width="20%">
							招聘职位
						</th>
						<td>
							<font color="blue" size="2"><bean:write name="rs"
									property="zpzw" /> </font>
						</td>
						<th width="20%">
							公司名称
						</th>
						<td>
							<bean:write name="rs" property="gsmc" />
						</td>
					</tr>
					<tr>
						<th>
							电子邮箱
						</th>
						<td>
							<bean:write name="rs" property="email" />
						</td>
						<th>
							联系电话
						</th>
						<td>
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th>
							工作地点
						</th>
						<td>
							<bean:write name="rs" property="gzdd" />
						</td>
						<th>
							招聘人数
						</th>
						<td>
							<bean:write name="rs" property="zprs" />
						</td>
					</tr>
					<tr>
						<th>
							行业分类
						</th>
						<td>
							<bean:write name="rs" property="hyfl" />
						</td>
						<th>
							外语要求
						</th>
						<td>
							<bean:write name="rs" property="wyyq" />
						</td>
					</tr>
					<tr>
						<th>
							试用期薪水
						</th>
						<td>
							<bean:write name="rs" property="syxs" />
						</td>
						<th>
							转正后薪水
						</th>
						<td>
							<bean:write name="rs" property="zzxs" />
						</td>
					</tr>
					<tr>
						<th>
							学历要求
						</th>
						<td>
							<bean:write name="rs" property="xlyq" />
						</td>
						<th>
							面试时间
						</th>
						<td>
							<bean:write name="rs" property="mssj" />
						</td>
					</tr>
					<tr>
						<th>
							面试携带
						</th>
						<td>
							<bean:write name="rs" property="msxd" />
						</td>
						<th>
							面试地点
						</th>
						<td>
							<bean:write name="rs" property="msdd" />
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="10690" scope="session">
						<tr>
							<th>
								岗位职责
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
					</logic:notEqual>
					<tr>
						<th>
							职位要求
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zwyq" rows="8" cols="75%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							公司简介
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="gsjj" rows="8" cols="75%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							发布时间
						</th>
						<td>
							<bean:write name="rs" property="fbsj" />
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					</tbody>
			</table>
	</body>
</html>

