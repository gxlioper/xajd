<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>		
	<html:form action="/qgzxLogic" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					<bean:write name="title"/>
				</a>
			</p>
		</div>
		
		<logic:empty name="xsList">
			<br />
			<br />
			<p align="center">
				�д�������
			</p>
		</logic:empty>
		<logic:notEmpty name="xsList">
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th>
							<span>ѧ����Ϣ</span>
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
											ѧ��
										</th>
										<th>
											����
										</th>
										<th>
											�Ա�	
										</th>
										<th>
											�༶
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
