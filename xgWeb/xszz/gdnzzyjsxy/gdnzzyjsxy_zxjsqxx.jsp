<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />
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
		function yz(titName){
			var zxjdm1 = document.getElementById('zxjdm1').value;
			var xh = document.getElementById('xh').value;
			
			if(zxjdm1 == null){
				 alert("请选择要申请的助学金项目！");
	          	 return false;
			}
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/gdnzzyjsxy_zxjsqxx.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/gdnzzyjsxy_zxjsqxxb.do";
			document.forms[0].submit();
		}
		
		function je(){
			var jtrks = document.getElementById('jtrks').value;
			var jtrjysr = document.getElementById('jtrjysr').value;
			if((jtrks == null) || (jtrks == "")){
				jtrks = "0";
			}
			if((jtrjysr == null) || (jtrjysr == "")){
				jtrjysr = "0";
			}
			var t;
			if(Math.round(jtrjysr)<100){
				t="★★★";
			}else if(Math.round(jtrjysr) <150){
				t="★★";
			}else if(Math.round(jtrjysr) <200){
				t="★";
			}
			document.getElementById('bz').value=t;
			var je = Math.round(jtrks)*(Math.round(jtrjysr)*12);
			document.getElementById('jtnzsr').value=je;
		}	
		
		function getZxj(){
			var temp = document.getElementById('zxjdm1').value;
			msgArray = new Array();
			msgArray = temp.split('!!splitOne!!');
			var zzgrje = "";
			if (msgArray.length > 1) {
				zzgrje = msgArray[4];
			}
			document.getElementById('zzgrje').value=zzgrje;
		}	
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 助学金申请
		</div>
	</div>
		<html:form action="gdnzzyjsxy_zxjsqxx.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/gdnzzyjsxy_zxjsqxx.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd"/>">

			<logic:present name="notKNS">
				<logic:match value="is" name="notKNS">
					<script language="javascript">
	         			alert("必须是贫困生才能申请！");
	         		</script>
				</logic:match>
			</logic:present>
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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<td scope="row" width="16%">
						<div align="center">
							<font color="red">*</font>助学金项目
						</div>
					</td>
					<td width="34%">
						<html:select name="rs" property="zxjdm1" styleId="zxjdm1" onchange="getZxj();">
							<html:option value="">------请选择------</html:option>
							<html:options collection="zxjList" property="zxjdm1"
								labelProperty="zzmc" />
						</html:select>
					</td>
					<td width="16%">
						<div align="center">
							资助个人金额
						</div>
					</td>
					<td width="34%">
						<input type="text" id="zzgrje" name="zzgrje"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzgrje" />" readonly="true">
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
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
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							生源地
						</div>
					</td>
					<td>
						<input type="text" id="syd" name="syd"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="syd" />" readonly="true">
					</td>
					<td>
						<div align="center">
							户口性质
						</div>
					</td>
					<td>
						<input type="text" id="hkxz" name="hkxz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hkxz" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭居住地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtjzdz" name="jtjzdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtjzdz" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="yzbm" />" readonly="true">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="lxdh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td>
						<input type="text" id="jtrks" name="jtrks"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name='rs' property="jtrks" />" readonly="true">
					</td>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td>
						<input type="text" id="jtrjysr" name="jtrjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name='rs' property="jtrjysr" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭年总收入
						</div>
					</td>
					<td>
						<input type="text" id="jtnzsr" name="jtnzsr"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="jtnzsr" />">
					</td>
					<td>
						<div align="center">
							学生本人月实际消费额度
						</div>
					</td>
					<td>
						<input type="text" id="xsbrysjxfed" name="xsbrysjxfed"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xsbrysjxfed" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭收入来源
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="srly" name="srly"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="srly" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭情况
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="jtqk" name="jtqk"
							value="<bean:write name='rs' property="jtqk" />">
						<bean:write name="rs" property="jtqk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭经济情况说明
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="jtjjqksm" name="jtjjqksm"
							value="<bean:write name='rs' property="jtjjqksm" />">
						<bean:write name="rs" property="jtjjqksm" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						在校期间历年获得奖、助学金及困难补助和贷款记录：
						<div align="left">
						<%ArrayList list = (ArrayList) request
									.getAttribute("zzjl"); 
								  for(Iterator it = list.iterator();it.hasNext();){
								  	String temp = (String)it.next();
								%>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=temp %><br />
						<%} %>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							是否已参加勤工助学
						</div>
					</td>
					<td align="center">
						<input type="text" id="sfycjqgzx" name="sfycjqgzx"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfycjqgzx" />" readonly="true">
					</td>
					<td>
						<div align="center">
							欠学费金额
						</div>
					</td>
					<td>
						<input type="text" id="qxfje" name="qxfje"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="qxfje" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							上学期成绩排名
						</div>
					</td>
					<td>
						<input type="text" id="sxqcjpm" name="sxqcjpm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sxqcjpm" />" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'') " 
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							综合测评排名
						</div>
					</td>
					<td>
						<input type="text" id="zhcppm" name="zhcppm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zhcppm" />" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'') " 
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							困难程度排名
						</div>
					</td>
					<td>
						<input type="text" id="kncdpm" name="kncdpm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="kncdpm" />" readonly="true">
					</td>
					<td scope="row">
						<div align="center">
							备注
						</div>
					</td>
					<td>
						<input type="text" id="bz" name="bz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bz" />" readonly="readonly">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
		</html:form>
</body>
</html:html>
