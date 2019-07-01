<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var zxlxdh = document.getElementById('zxlxdh').value;
			var jtrks = document.getElementById('jtrks').value;
			var jtrjysr = document.getElementById('jtrjysr').value;
			var kjzmsj = document.getElementById('kjzmsj').value;
			var yxjtjjzyys = document.getElementById('yxjtjjzyys').value;
			var yxjtjjzkqtxx = document.getElementById('yxjtjjzkqtxx').value;
			var bz = document.getElementById('bz').value;
			
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			if(zxlxdh == null || zxlxdh == ""){
				alert("在校联系电话不能为空!");
				return false;
			}
			if(jtrks == null || jtrks == ""){
				alert("家庭人口数不能为空!");
				return false;
			}
			if(jtrjysr == null || jtrjysr == ""){
				alert("家庭人均月收入不能为空!");
				return false;
			}
			if(kjzmsj == null || kjzmsj == ""){
				alert("开据证明时间不能为空!");
				return false;
			}
	       	if(yxjtjjzyys != null){
	         	if(yxjtjjzyys.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("影响家庭经济重要因素不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(yxjtjjzkqtxx != null){
	         	if(yxjtjjzkqtxx.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("影响家庭经济状况其他原因不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("备注不能大于500个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=knssq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 困难生申请
		</div>
	</div>

	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内，不能申请！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="bjlhdx_xszz.do?method=knssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/bjlhdx_xszz.do?method=knssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />

			<logic:equal name="sfksq" value="1">

				<logic:present name="ok">
					<logic:match value="ok" name="ok">
						<script language="javascript">
	         	alert("保存成功！");
	         	</script>
					</logic:match>
					<logic:match value="no" name="ok">
						<script language="javascript">
	         	alert("保存失败！");
	         	</script>
					</logic:match>
				</logic:present>

				<table class="tbstyle" width="90%">
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="center" width="16%">
								<font color="red">*</font>学号
							</td>
							<td align="left" width="34%">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="center" width="16%">
								<font color="red">*</font>学号
							</td>
							<td align="left" width="34%">
								<input type="text" id="xh" name="xh"
									style="width:100%;heigh:100%"
									value="<bean:write name='rs' property="xh" />" readonly="true">
							</td>
						</logic:equal>
						<td width="16%" scope="col">
							<div align="center">
								姓名
							</div>
						</td>
						<td width="34%" scope="col">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								性别
							</div>
						</td>
						<td>
							<input type="text" id="xb" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xb" />" readonly="true">
						</td>
						<td>
							<div align="center">
								身份证号
							</div>
						</td>
						<td>
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="sfzh" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								民族
							</div>
						</td>
						<td>
							<input type="text" id="mzmc" name="mzmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="mzmc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								政治面貌
							</div>
						</td>
						<td>
							<input type="text" id="zzmmmc" name="zzmmmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zzmmmc" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								出生日期
							</div>
						</td>
						<td>
							<input type="text" id="csrq" name="csrq"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="csrq" />" readonly="true">
						</td>
						<td>
							<div align="center">
								年级
							</div>
						</td>
						<td>
							<input type="text" id="nj" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="nj" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />
							</div>
						</td>
						<td>
							<input type="text" id="xymc" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xymc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								专业
							</div>
						</td>
						<td>
							<input type="text" id="zymc" name="zymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zymc" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								班级
							</div>
						</td>
						<td>
							<input type="text" id="bjmc" name="bjmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="bjmc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								入学前户口
							</div>
						</td>
						<td>
							<div align="center">
								<logic:present name="rs" property="rxqhk">
									<logic:match value="城镇" name="rs" property="rxqhk">
										<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
							    		<input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
									</logic:match>
									<logic:match value="农村" name="rs" property="rxqhk">
										<input type="radio" name="rxqhk" value="城镇">&nbsp;&nbsp;城镇
							    		<input type="radio" name="rxqhk" value="农村" checked>&nbsp;&nbsp;农村
									</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="rxqhk">
									<input type="radio" name="rxqhk" value="城镇" checked>&nbsp;&nbsp;城镇
									<input type="radio" name="rxqhk" value="农村">&nbsp;&nbsp;农村
								</logic:notPresent>
							</div>
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								入学年月
							</div>
						</td>
						<td>
							<input type="text" id="rxny" name="rxny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="rxny" />" readonly="true">
						</td>
						<td>
							<div align="center">
								毕业年月
							</div>
						</td>
						<td>
							<input type="text" id="byny" name="byny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="byny" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								学历
							</div>
						</td>
						<td>
							<div align="center">
								<logic:present name="rs" property="xl">
									<logic:match value="本科" name="rs" property="xl">
										<input type="radio" name="xl" value="本科" checked>&nbsp;&nbsp;本科
							    		<input type="radio" name="xl" value="专科">&nbsp;&nbsp;专科
									</logic:match>
									<logic:match value="专科" name="rs" property="xl">
										<input type="radio" name="xl" value="本科">&nbsp;&nbsp;本科
							    		<input type="radio" name="xl" value="专科" checked>&nbsp;&nbsp;专科
									</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="xl">
									<input type="radio" name="xl" value="本科" checked>&nbsp;&nbsp;本科
									<input type="radio" name="xl" value="专科">&nbsp;&nbsp;专科
								</logic:notPresent>
							</div>
						</td>
						<td>
							<div align="center">
								<font color="red">*</font>在校联系电话
							</div>
						</td>
						<td>
							<input type="text" id="zxlxdh" name="zxlxdh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zxlxdh" />" maxlength="15"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								父亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="fqxm" />">
						</td>
						<td>
							<div align="center">
								母亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="mqxm" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								父亲年龄
							</div>
						</td>
						<td>
							<input type="text" id="fqnl" name="fqnl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="fqnl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								母亲年龄
							</div>
						</td>
						<td>
							<input type="text" id="mqnl" name="mqnl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="mqnl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								父亲年收入
							</div>
						</td>
						<td>
							<input type="text" id="fqnsr" name="fqnsr"
								style="width:100%;heigh:100%" maxlength="10"
								value="<bean:write name='rs' property="fqnsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								母亲年收入
							</div>
						</td>
						<td>
							<input type="text" id="mqnsr" name="mqnsr"
								style="width:100%;heigh:100%" maxlength="10"
								value="<bean:write name='rs' property="mqnsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								<font color="red">*</font>家庭人口数
							</div>
						</td>
						<td>
							<input type="text" id="jtrks" name="jtrks"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="jtrks" />" maxlength="3"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								<font color="red">*</font>家庭人均月收入
							</div>
						</td>
						<td>
							<input type="text" id="jtrjysr" name="jtrjysr"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="jtrjysr" />" maxlength="5"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								提供证明类型
							</div>
						</td>
						<td>
							<html:select name="rs" property="tgzmlx">
								<html:option value="调查表">调查表</html:option>
								<html:option value="低保证明">低保证明</html:option>
								<html:option value="调查表和低保证明">调查表和低保证明</html:option>
							</html:select>
						</td>
						<td>
							<div align="center">
								<font color="red">*</font>开据证明时间
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('kjzmsj','y-mm-dd');"
								value="<bean:write name='rs' property="kjzmsj" />" name="kjzmsj"
								id="kjzmsj" />
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								影响家庭经济<br />的重要因素
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="yxjtjjzyys" rows='10'
							style="width:100%" onblur="yzdx(this,'yxjtjjzyys')" />
							<input type="hidden" id="yxjtjjzyys" name="yxjtjjzyys" value="">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								影响家庭经济<br />状况其他原因
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="yxjtjjzkqtxx" rows='10'
							style="width:100%" onblur="yzdx(this,'yxjtjjzkqtxx')" />
							<input type="hidden" id="yxjtjjzkqtxx" name="yxjtjjzkqtxx" value="">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								备注
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows='3'
							style="width:100%" onblur="yzdx(this,'bz')" />
							<input type="hidden" id="bz" name="bz" value="">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								其他字段
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="qtzd" name="qtzd"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="qtzd" />" maxlength="100">
						</td>
					</tr>
				</table>
			</logic:equal>
	<logic:notEqual name="sfksq" value="-1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					提交申请
				</button>
			</div>
	</logic:notEqual>
		</html:form>
</body>
</html:html>
