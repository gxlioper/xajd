<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
<html:form action="/jyweb" method="post">
	<div id="tmpdiv1"></div>
	<div class="type_right">
		<div class="rightcon">
			<h3>
				<span>��λ��Ϣ</span>
			</h3>
			<div class="tabcon">
				<h4>
					${rs.dwmc }
				</h4>
				<div class="module">
					<h5>
						��Ƹ��λ�������
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="400px">
								��λ���ʣ� ${rs.dwxzmc }
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ&nbsp;��${rs.hyflmc }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;ϵ&nbsp;��&nbsp;��${rs.lxr }
							</td>
							<td>
								��ϵ�绰��${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;��${rs.cz }
							</td>
							<td>
								�������䣺${email }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ַ&nbsp;��${rs.wz }
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;��${rs.yb }
							</td>
						</tr>
						<tr>
							<td colspan="2">
								��ϵ��ַ��${rs.dz }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��Ƹ��λ���
					</h5>
					<p>
						${rs.dwjj}
					</p>
				</div>
			</div>
		</div>
	</div>
</html:form>
</body>
</html>