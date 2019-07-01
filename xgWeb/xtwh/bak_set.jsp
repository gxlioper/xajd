<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function changeType(){
				var sysType = document.getElementById("db_systemType").value;
				if(sysType == "windows"){
					document.getElementById("bak_path").value = "D:/data_bak";
				}else{
					document.getElementById("bak_path").value = "/home/ora_bak";
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 系统备份 - 系统备份</a>
			</p>
		</div>
	
		<html:form action="/data_backup" method="post">
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>系统设置</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存" onclick="refreshForm('/xgxt/bak_set_save.do')">
										保存
									</button>
									<button type="button" name="关闭" onclick="Close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								操作系统类型
							</th>
							<td width="80%">
								<html:select name="rs" property="db_systemType" styleId="db_systemType" onchange="changeType()">
									<html:option value="windows">Windows</html:option>
									<html:option value="linux">Linux</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								数据库用户名
							</th>
							<td>
								<html:text name="rs" property="db_user" styleId="db_user" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								数据库密码
							</th>
							<td>
								<html:password name="rs" property="db_pwd" styleId="db_pwd" style="width:150px"></html:password>
							</td>
						</tr>
						<tr>
							<th>
								网络服务名
							</th>
							<td>
								<html:text name="rs" property="db_sid" styleId="db_sid" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备份路径
							</th>
							<td>
								<html:text name="rs" property="bak_path" styleId="bak_path" style="width:150px"></html:text>
								<font color="red">如: d:/data_bak</font>
							</td>
						</tr>
					</tbody>
					</table>
			</div>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("操作成功!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
				Close();
			</script>
		</logic:equal>
	</body>
</html>
