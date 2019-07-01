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
	<script language="javascript">
	function onShow(){
		if($("xh") && $("jxnd")){
			if($("xh").value != "" && $("jxnd").value != ""){
				var xh = $("xh").value;
				var nd = $("jxnd").value;
				chBzList(nd);
			}
		}
	}
	function chBzList(jxnd){
		var xh = $("xh").value;
		getJxglDAO.getLdListMc(xh,jxnd,function(data){
			if(data!=null && data !=""){
				$("ldmc").value=data[0].bzmc;
				$("lddm").value=data[0].bzdm;
			}else{
				$("ldmc").value="";
				$("lddm").value="";
			}
		});
	}
	function saveBz(){
		if($("ldmc").value == ""){
			alert("请确认军训所在连队");
			return false;
		}
		SaveArmy('/xgxt/modiArmy.do?doType=save&tableName=view_xsbz&realTable=xsjxbzb&act=xsjxbz&pk=bzh','xsjxbzb')
	}
	
	jQuery(function(){
		onShow();
	})
	
	</script>
	<base target="_self">
	<body >
		<html:form action="/Army">
			<input type="hidden" name="url" id="url"
				value="/showArmy.do?realTable=<bean:write name = "realTable"/>">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />	
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="oper" id="oper" value="<bean:write name="doType"/>">		
			<input type="hidden" name="notnull" id="notnull" value="xh-hjyy"/>
			<input type="hidden" name="bzh" id="bzh" value="<bean:write name="rs" property="bzh"/>">	
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:军训管理-学生军训表彰-信息维护
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<div class="buttontool" id="btn" width="100%">
					<b>学生军训表彰</b>
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
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="right">
							年级：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="xymc" />
					</td>
					<td height="22">
						<div align="right">
							专业名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							班级名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td height="22">
						<div align="right">
							
						</div>
					</td>
					<td height="22">
						
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							军训年份：
						</div>
					</td>
					<td>
						<html:select name="rs" property="jxnd" styleId="jxnd" onchange="chBzList(this.value)">
						<html:options collection="xnList" labelProperty="nd" property="nd"/>
						</html:select>
					</td>
					<td height="22">
						<div align="right">
							连队：
						</div>
					</td>
					<td>
						<html:text property="ldmc" style="width:180px" styleId="ldmc" readonly="true"/>
						<html:hidden property="lddm" style="width:180px" styleId="lddm"/>
					</td>
				</tr>			
				<%--浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />--%>
				<logic:equal value="13022" name="xxdm">
					<tr>
					<td height="22">
						<div align="right">
							获奖类别：
						</div>
					</td>
					<td height="22" colspan="3">
						<html:select property="jxhjlbdm" name="rs">
						<html:option value=""></html:option>
						<html:options collection="jxhjlbList" property="jxdm" labelProperty="jxmc"/>
						</html:select>
					</td>					
				</tr>
				</logic:equal>
				
				<tr style="height:30px">
					<td height="22">
						<div align="right">
							<font color="red">*</font>获奖原因：
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="hjyy" style="height:100px;width:100%" onblur="chLeng(this,150)"/>				
					</td>
				</tr>
			</table>
			<center>
				<div class="buttontool" id="btn" width="100%">
					<button type="button" class="button2" onclick="saveBz()" style="width:80px">
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
