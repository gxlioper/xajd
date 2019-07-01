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
				<em>您的当前位置:</em><a>咨询师详细信息</a>
			</p>
		</div>
	
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询师详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								咨询师编号
							</th>
							<td width="34%">
								<html:text name="rs" property="num" style="100%" readonly="true" />
							</td>
							<th width="16%">
								咨询师姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
					<tr>
						<th>
							学生学号
						</th>
						<td>
							<bean:write name="rs" property="xsxh" />
						</td>
						<th>
							学生姓名
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
					</tr>
				<tr>
					<th>
						学生性别
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>
						学生年级
					</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>
						专业名称
					</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<th>
						是否要求见面
					</th>
					<td>
						<bean:write name="rs" property="meet" />
					</td>

					<th>
						期望约见时间
					</th>
					<td>
						<bean:write name="rs" property="qwyjtime" />
					</td>
				</tr>
				<tr>
					<th>
						咨询问题简述
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxwtjs" rows="6"
							readonly="true" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>
						咨询问题回复
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxwthf" rows="11"
							readonly="true" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>
						学生确认
					</th>
					<td>
						<bean:write name="rs" property="xsqr" />
					</td>

					<th>
						咨询师确认
					</th>
					<td>
						<bean:write name="rs" property="zxsqr" />
					</td>
				</tr>
				<tr>
					<th>
						学生确认时间
					</th>
					<td>
						<bean:write name="rs" property="xsqrsj" />
					</td>

					<th>
						咨询师确认时间
					</th>
					<td>
						<bean:write name="rs" property="zxsqrsj" />
					</td>
				</tr>
				<tr>
					<th>
						最终约见地点
					</th>
					<td>
						<bean:write name="rs" property="yjdd" />
					</td>

					<th>
						最终约见时间
					</th>
					<td>
						<bean:write name="rs" property="yjsj" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
	</body>
</html>
