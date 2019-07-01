
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="dwr/interface/nbtyxszz.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='dwr/interface/getTyYdLx.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	function initYdlx(){
		var ydzt="";
		var ydlx=""
		if($("ydzt")){
			ydzt=$("ydzt").value;
		}
		if(ydzt==""){
			DWRUtil.removeAllOptions($("ydlx"));	
		}
		if(ydzt!=""){
			getTyYdLx.initTyYdLx(ydzt,getData);
		}
	}
	function getData(data){
		DWRUtil.removeAllOptions($("ydlx"));		
		DWRUtil.addOptions($("ydlx"),data,"dm","mc");
	}
     function rychSqSave(){
     	var ydlx="";
     	var ydzt="";
     	if($("ydlx")){
     		ydlx=$("ydlx").value;
     	}
     	if($("ydzt")){
     		ydzt=$("ydzt").value;
     	}
     	if(ydlx==""){
     		alert("异动类型不能为空！");
     		return false;
     	}
     	if(ydzt==""){
     		alert("异动状态不能为空！");
     		return false;
     	}
     	showTips("保存中，请稍等...");
        refreshForm("/xgxt/gdbyTyxx.do?method=tyxxDgYd&doType=modi");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/gdbyTyxx" method="post">
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj-rtrq-rtdd"/>
			<input type="hidden" id="url" name="url" value="gdbyTyxx.do?method=tyxxDgYd&doType=save" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" id="tableName" name="tableName" value="view_gdby_dtjs_tyxxb"/>
			<input type="hidden" id="lx" name="lx" value="团员异动"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：党团信息 - 团员信息 - 团员信息异动
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>团员信息异动</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<html:hidden property="save_xh" value="${rs.xh}"/>
					</logic:notEmpty>
					
					<logic:empty name="rs">
					<html:text property="xh" styleId="xh" />
					</logic:empty>
							<button type="button" onclick="sendXx();return false;"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
							
					</td>
					<td align="right" style="width: 10%">
						姓名：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="xm" readonly="true"  styleId="xh" value="${rs.xm}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text  property="xm" styleId="xm" />
					</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font>性别：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="xb" readonly="true"  styleId="xb" value="${rs.xb}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text  property="xb" styleId="xb" />
					</logic:empty>
					</td>
					<td align="right" style="width: 10%">
						年级：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="nj" readonly="true"  styleId="nj" value="${rs.nj}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text  property="nj" styleId="nj" />
					</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="xymc" readonly="true"  styleId="xy" value="${rs.xymc}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text property="xymc" styleId="xymc" />
					</logic:empty>
					</td>
					<td align="right" style="width: 10%">
						专业：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="zymc" readonly="true"  styleId="nj" value="${rs.zymc}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text  property="zymc" styleId="zymc" />
					</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font> 班级：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					<html:text name="rs" property="bjmc" readonly="true"  styleId="xy" value="${rs.bjmc}"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text property="bjmc" styleId="bjmc" />
					</logic:empty>
					</td>
					<td align="right" style="width: 10%">
						入团日期：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEmpty name="rs">
						 <html:text name="rs" property="rtrq" styleId="rtrq" readonly="true" />	
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text  property="rtrq" styleId="rtrq" />
					</logic:empty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						入团地点：
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<html:text name="rs" property="rtdd" readonly="true" />
						</logic:notEmpty>
						<logic:empty name="rs">
					<html:text property="rtdd" styleId="rtdd" />
					</logic:empty>
					</td>
					<td>
						是否在籍：
					</td>
					<td>
					<logic:notEmpty name="rs">
						<html:text name="rs" property="sfzj"/>
					</logic:notEmpty>
					<logic:empty name="rs">
					<html:text property="sfzj" styleId="sfzj" />
					</logic:empty>
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>异动状态：
					</td>
					<td align="left">
						<html:select property="save_ydzt" onchange="initYdlx()" styleId="ydzt" style="width:80px">
							<html:option value=""></html:option>
							<html:options collection="ydztList" property="mc"
										labelProperty="mc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>异动类型：
					</td>
					<td align="left">
						<html:select property="save_ydlx" styleId="ydlx"  style="width:80px">
							<html:option value=""></html:option>
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						异动时间：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEmpty name="rs">
					
					<html:text  property="save_ydsj" styleId="ydsj" 
					onclick="return showCalendar('ydsj','y-mm-dd');" 
					onblur="dateFormatChg(this)" readonly="true" />	
					</logic:notEmpty>
					<logic:empty name="rs">
						
						 <html:text  property="save_ydsj"  readonly="true" />	
					</logic:empty>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						何时担任何职务:
						<br />
						<span class="style1">(限100字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:notEmpty name="rs">
						<html:textarea rows="8" styleId="hsrhzw" style="width:98%" name="rs" property="hsrhzw" onblur="chLeng(this,100)"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="hsrhzw" property="hsrhzw" onblur="chLeng(this,200)"/>
					</logic:empty>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						参加社团:
						<br />
						<span class="style1">(限100字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:notEmpty name="rs">
						<html:textarea rows="8" styleId="cjst" style="width:98%" name="rs" property="cjst" onblur="chLeng(this,100)"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="cjst" property="cjst" onblur="chLeng(this,200)"/>
					</logic:empty>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						参加社会实践：
						<br />
						<span class="style1">(限100字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:notEmpty name="rs">
						<html:textarea rows="8" styleId="cjshsj" style="width:98%" name="rs" property="cjshsj" onblur="chLeng(this,100)"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="cjshsj" property="cjshsj" onblur="chLeng(this,200)"/>
					</logic:empty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						志愿者的情况记录：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:notEmpty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="zyzqk" name="rs" property="zyzqk" onblur="chLeng(this,200)"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="zyzqk" property="zyzqk" onblur="chLeng(this,200)"/>
					</logic:empty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						参加培训情况：
						<br />
						<span class="style1">(限200字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:notEmpty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="cjpxqk" name="rs" property="cjpxqk" onblur="chLeng(this,200)"/>
					</logic:notEmpty>
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" styleId="cjpxqk" property="cjpxqk" onblur="chLeng(this,200)"/>
					</logic:empty>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
			</div>
			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			    </script>
			</logic:equal>
		</html:form>
	</body>


</html>

