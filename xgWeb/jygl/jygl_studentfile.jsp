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
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ���ع��� - ѧ���ļ�����</a>
			</p>
		</div>

		<html:form action="jyglteafiledown.do" method="post"
			enctype='multipart/form-data'>


			<div class="formbox">
				<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="fjList2">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> 
					</h3>
			
				<logic:notEmpty name="fjList2">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td align="center" width="50%">
									�ļ���
								</td>
								<td align="center" width="30%">
									��С(Byte)
								</td>
								<td align="center" width="20%">
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:present name="fjList2">
								<logic:iterate id="s" name="fjList2">
									<tr>
										<td>
											<bean:write name="s" property="wjname" />
										</td>
										<td align="center">
											<bean:write name="s" property="wjdx" />
										</td>
										<td align="center">
											<a
												href="jyglstufiledown.do?dir=<bean:write name="s" property="wjpath"/>&act=down&filename=<bean:write name="s" property="wjname"/>">����</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tbody>
					</table>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>

