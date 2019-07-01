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
				<em>您的当前位置:</em><a>就业管理 - 下载管理 - 教师文件下载</a>
			</p>
		</div>


		<html:form action="jyglteafiledown.do" method="post"
			enctype='multipart/form-data'>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="fjList">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
					</h3>
			
				<logic:notEmpty name="fjList">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td align="center" width="50%">
									文件名
								</td>
								<td align="center" width="30%">
									大小(Byte)
								</td>
								<td align="center" width="20%">
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:present name="fjList">
								<logic:iterate id="s" name="fjList">
									<tr>
										<td>
											<bean:write name="s" property="wjname" />
										</td>
										<td align="center">
											<bean:write name="s" property="wjdx" />
										</td>
										<td align="center">
											<a
												href="jyglteafiledown.do?dir=<bean:write name="s" property="wjpath"/>&act=down&filename=<bean:write name="s" property="wjname"/>">下载</a>
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
