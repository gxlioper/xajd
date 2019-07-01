<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">

<head>
	<base target="_self" />
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xydm = document.getElementById('xydm').value;
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((xydm == null) || (xydm == "")){
				alert("请选择<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			}
			if((kssj == null) || (kssj == "")){
				alert("申请开始时间不能为空!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("申请结束时间不能为空!");
				return false;
			}
			if (kssj > jssj){
				alert("申请开始时间不能大于申请结束时间！");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zxdksjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>助学贷款 - 基础数据维护 - 助学贷款时间维护</a>
		</p>
	</div>
	<html:form action="/zgdzdx_xszz.do?method=zxdksjEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	Close();window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>助学贷款时间维护</span></th>
			        </tr>
			    </thead>
			 <tfoot>
			      <tr>
			          <logic:equal name="writeAble" value="yes">
			          <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          		<logic:notEqual name="doType" value="view">
			          			<button type="button" name="保存" onclick="yz();">保 存</button>
			          		</logic:notEqual>
			            <button type="button" name="关闭" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">关闭</button>
			          </div></td>
			         </logic:equal>
			      </tr>
			    </tfoot>
			<tbody>
			<tr>
				<th width="50%">
					项目名称：
				</th>
				<td width="50%">
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />：
				</th>
				<td>
						<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<th>
				 <span class="red">*</span>申请开始时间：
				</th>
				<td >
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<th>
				<span class="red">*</span>申请结束时间：
				</th>
				<td >
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html>
