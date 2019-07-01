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
								<bean:write name="rs" property="num" />
							</td>
							<th width="16%">
								咨询师姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
	
				<tr>
					<th>
						咨询师年龄
					</th>
					<td>
						<bean:write name="rs" property="age" />
					</td>
					<th>
						咨询师性别
					</th>
					<td>
						<bean:write name="rs" property="xb"  />
					</td>
				</tr>
				<tr>
					<th>
						咨询师资格
					</th>
					<td>
						<bean:write name="rs" property="zxszg"  />
					</td>
					<th>
					    电子邮箱
					</th>
					<td>
						<bean:write name="rs" property="email"  />
					</td>
				</tr>
				<tr>
					<th>
						联系电话
					</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
					<th>
					
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<th>
						咨询师简介
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxsjj" rows="6"
							style="width:100%" readonly="true"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
	</body>
</html>

