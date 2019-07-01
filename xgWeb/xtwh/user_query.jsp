<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
		//查询用户
		function searchUser(){
			var yhm = document.getElementById("yhm").value;
			var xm = document.getElementById("xm").value;
			var zdm = document.getElementById("zdm").value;
			var szbm = document.getElementById("xydm").value;
			
			window.dialogArguments.document.getElementById("hidden_yhm").value = yhm;
			window.dialogArguments.document.getElementById("hidden_xm").value = xm;
			window.dialogArguments.document.getElementById("hidden_zdm").value = zdm;
			window.dialogArguments.document.getElementById("hidden_szbm").value = szbm;
			
			window.dialogArguments.document.getElementById("btn_user_query").click();
			window.close();
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 权限维护 - 用户查询</a>
			</p>
		</div>

		<html:form action="/user_query" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>用户查询</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="提交" onclick="searchUser();">
										提 交
									</button>
									<button type="button" name="重置" onclick="Close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								用户名
							</th>
							<td width="80%">
								<input type="text" name="yhm" id="yhm" />
							</td>
						</tr>
						<tr>
							<th>
								用户姓名
							</th>
							<td>
								<input type="text" name="xm" id="xm" />
							</td>
						</tr>
						<tr>
							<th>
								用户组
							</th>
							<td>
								<html:select property="zdm" styleId="zdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zList" property="zdm"
										labelProperty="zmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								用户部门
							</th>
							<td>
								<html:select property="xydm" styleId="xy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
