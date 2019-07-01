<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
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
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=zxdksjEdit&act=save";
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
	<html:form action="/zgmsxy_xszz.do?method=zxdksjEdit" method="post">

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
		<table class="formlist" width="90%">
			<tr>
				<th width="30%">
					项目名称
				</th>
				<td width="70%">
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							value="<bean:write name="rs" property="xmmc"/>"/>
				</td>
			</tr>
			<tr>
				<th>
						<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
						<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							value="<bean:write name="rs" property="xymc"/>"/>
				</td>
			</tr>
			<tr>
				<th>
						 申请开始时间
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<th>
						申请结束时间
				</th>
				<td>
					<input type="text" readonly="readonly"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
			<logic:equal name="writeAble" value="yes">
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" name="提交" onclick="yz();">保存</button>
			            <button type="button" name="关闭" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    </logic:equal>
		</table>
		</div>
	</html:form>
</body>
</html>
