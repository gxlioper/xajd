<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>		
	<html:form action="/qgzxLogic" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					<bean:write name="title"/>
				</a>
			</p>
		</div>
		
		<logic:empty name="xsList">
			<br />
			<br />
			<p align="center">
				有错误发生！
			</p>
		</logic:empty>
		<logic:notEmpty name="xsList">
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th>
							<span>学生信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<table width="100%" class="formlist">
								<thead>
									<tr>
										<th>
											学号
										</th>
										<th>
											姓名
										</th>
										<th>
											性别	
										</th>
										<th>
											班级
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:iterate id="v" name="xsList">			
										<tr>
											<td>
												<bean:write name="v" property="xh"/>
											</td>
											<td>
												<bean:write name="v" property="xm"/>	
											</td>
											<td>
												<bean:write name="v" property="xb"/>	
											</td>
											<td>
												<bean:write name="v" property="bjmc"/>	
											</td>
										</tr>
									</logic:iterate>	
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</logic:notEmpty>
	</html:form>
</body>
</html>
