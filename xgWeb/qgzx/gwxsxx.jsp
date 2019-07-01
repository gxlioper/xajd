<%@ page language="java" contentType="text/html; charset=GBK"%>
		<!--显示参加岗位的学生列表-->
		<thead>
		<tr>
			<td colspan="4" onclick="document.getElementById('tbodyXs').style.display=document.getElementById('tbodyXs').style.display=='none' ? '' : 'none'" align="center"><a href="#"><b>参加岗位的学生</b></a></td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="4">
			<table id="tbodyXs" width="100%" class="formlist">
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
		<!--end显示参加岗位的学生列表-->			
