<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript">
function plsh(){
	var userType = document.getElementById('userType').value;
	var shType = document.getElementById('shType').value;
	var shjg = document.getElementById('shjg').value;
	
	if (shType == null || shType == ""){
		alert("请选择审核类型!");
		return false;
	}
	if (shType == null || shType == ""){
		alert("请选择审核结果!");
		return false;
	}
	
	
	if (userType != "xx" && userType != "admin"){
		if (!confirm("下级用户不能修改已通过上级审核的数据，确定要修改所选记录？")){
			return false;
		}
	}
	refreshForm('/xgxt/zgdzdx_xszz.do?method=knsrdplsh&doType=save');
}

</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/zgdzdx_xszz.do?method=knsrdplsh" method="post">
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>困难生 - 审核 - 困难生认定批量审核</a>
				</p>
			</div>
			<!-- 标题 end-->
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="cbVal" name="cbVal"
				value="<bean:write name="rs" property="cbVal"/>" />
			<logic:present name="over">
				<logic:match value="over" name="over">
					<script language="javascript">
	         			alert("批量审核完成！");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>批量审核情况</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>
					<th align="right" width="50%">
						审核类型
					</th>
					<td align="left" width="50%">
						<logic:equal name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">推荐档次</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />审核</html:option>
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">推荐档次</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />审核</html:option>
								<html:option value="3">学校审核</html:option>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th align="right" width="50%">
						审核结果
					</th>
					<td align="left" width="50%">
						<html:select name="rs" property="shjg">
							<html:option value="一般困难">一般困难</html:option>
							<html:option value="困难">困难</html:option>
							<html:option value="特殊困难">特殊困难</html:option>
							<html:option value="不困难">不困难</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" onclick="plsh();">
										保 存
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button type="button" id="buttonClose" 
									onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
