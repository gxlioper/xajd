<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<FORM method="POST" name="myform" action="eWebEditor/submit.jsp">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ļ����� - ���� - �ļ�����</a>
				</p>
			</div>

			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	�� �� �� ��&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">�����޿ɽ����ļ���</font> 
			 		 </logic:empty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" bgcolor="#D0E0EE">
								<td>
									�ļ���
								</td>
								<td>
									�ļ���
								</td>
								<td>
									���Ų���
								</td>
								<td>
									�ļ�����ʱ��
								</td>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="list" name="rs">
							<tr onmouseover="rowOnClick(this)">
								<td>
									<a
										href="fileView.do?wjh=<bean:write name="list" property="wjh"/>&yd=yes"
										target="_blank" style="color:blue"> <bean:write name="list" property="wjh" />
									</a>
									<logic:equal name="list" property="newmark" value="new">
										<img style="width:30px" src="images/newmark.gif" />
									</logic:equal>
								</td>
								<td>
									<bean:write name="list" property="wjm" />
								</td>
								<td>
									<bean:write name="list" property="bmmc" />
								</td>
								<td>
									<bean:write name="list" property="wjffsj" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
				<logic:empty name="rs">
					<center>
						�����ϴ�
					</center>
				</logic:empty>
		</FORM>
	</body>
</html>
