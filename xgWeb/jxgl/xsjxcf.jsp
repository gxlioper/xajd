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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript" src="js/jxgl.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript">
	function onShow(){
		if($("nj")){
			if($("nj").value != ""){
				var nj = $("nj").value;
				chBzList(nj);
			}
		}
	}
	function chBzList(nj){
		var xh = $("xh").value;
		getJxglDAO.getLdListMc(xh,nj,function(data){
			if(data!=null && data !=""){
				$("ldmc").value=data[0].bzmc;
				//$("lddm").value=data[0].bzdm;
			}else{
				//$("ldmc").value="";
				//$("lddm").value="";
			}
		});
	}
	</script>
	<base target="_self">
	<body onload="onShow()">
		<html:form action="/Army">
			<input type="hidden" name="url" id="url"
				value="/showArmy.do?realTable=<bean:write name = "realTable"/>">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />	
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="oper" id="oper" value="<bean:write name="doType"/>">		
			<input type="hidden" name="notnull" id="notnull" value="xh-sclqk-sclyy"/>	
			<input type="hidden" name="cfh" id="cfh" value="<bean:write name="rs" property="cfh"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:军训管理-学生军训惩罚-信息维护
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<div class="buttontool" id="btn" width="100%">
					<b>学生军训惩罚</b>
				</div>
				<tr>
					<td height="22" align="right">
						<div align="right">
							<font color="red">*</font>学号：
						</div>
					</td>
					<td height="22" width="33%">
						<logic:notEqual value="add" name="doType">
							<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:text name='rs' property="xh" styleId="xh"								
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
					</td>
					<td width="15%">
						<div align="right">
							姓名：
						</div>
					</td>
					<td width="33%">
						<html:text name="rs" property="xm" readonly="true"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<html:text name="rs" property="xb" readonly="true"/>
					</td>
					<td>
						<div align="right">
							年级：
						</div>
					</td>
					<td>
						<html:text name="rs" property="nj" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td height="22">
						<html:text name="rs" property="xymc" readonly="true"/>
					</td>
					<td height="22">
						<div align="right">
							专业名称：
						</div>
					</td>
					<td height="22">
						<html:text name="rs" property="zymc" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							班级名称：
						</div>
					</td>
					<td height="22">
						<html:text name="rs" property="bjmc" readonly="true"/>
					</td>
					<td height="22">
						<div align="right">
							连队名称：
						</div>
					</td>
					<td height="22">
						<input type="text" name="ldmc" id="ldmc" value="" readonly="readonly"/>
					</td>
				</tr>
				<tr style="height:30px">
					<td height="22">
						<div align="right">
							<font color="red">*</font>受处理情况：
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sclqk" style="height:100px;width:300px" onblur="chLeng(this,150);"/>				
					</td>
				</tr>
				<tr style="height:30px">
					<td height="22">
						<div align="right">
							<font color="red">*</font>受处理原因：
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sclyy" style="height:100px;width:300px" onblur="chLeng(this,150);"/>				
					</td>
				</tr>
			</table>
			<center>
				<div class="buttontool" id="btn" width="100%">
					<button type="button" class="button2" onclick="SaveArmy('/xgxt/modiArmy.do?doType=save&tableName=view_xsbx&realTable=xsjxcfb&act=xsjxrcbx&pk=cfh','xsjxcfb')" style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.close();return false;"
						style="width:80px">
						关 闭
					</button>
				</div>
			</center>
			<logic:present name = "sfjxs">
					<script>
    				alert("该学生不在军训学生名单内！");
   			</script>
   			</logic:present>
		</html:form>
	</body>
</html>
