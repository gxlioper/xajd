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
				<span>单位信息</span>
			</h3>
			<div class="tabcon">
				<h4>
					${rs.dwmc }
				</h4>
				<div class="module">
					<h5>
						招聘单位基本情况
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="400px">
								单位性质： ${rs.dwxzmc }
							</td>
							<td>
								行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：${rs.hyflmc }
							</td>
						</tr>
						<tr>
							<td>
								联&nbsp;系&nbsp;人&nbsp;：${rs.lxr }
							</td>
							<td>
								联系电话：${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td>
								传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真&nbsp;：${rs.cz }
							</td>
							<td>
								电子邮箱：${email }
							</td>
						</tr>
						<tr>
							<td>
								网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;：${rs.wz }
							</td>
							<td>
								邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;：${rs.yb }
							</td>
						</tr>
						<tr>
							<td colspan="2">
								联系地址：${rs.dz }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						招聘单位简介
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