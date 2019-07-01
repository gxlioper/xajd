
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	
	<script language="javascript">	 
	function getInfo(){
		 refreshForm("/xgxt/mjxyJtrych.do?method=jtrychSq&doType=new");
	}    
     function saveJtrych(){
     	var notXsgb=$("notXsgb").value;
     	if(notXsgb=="yes"){
     	  alert("你目前没有申请集体荣誉称号的权限!");
     	  return false;
     	}
     	refreshForm("/xgxt/mjxyJtrych.do?method=jtrychSq&doType=save");
     	showTips("保存中，请等待...");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function modiJtrych(){
     	refreshForm("/xgxt/mjxyJtrych.do?method=jtrychModi");
     	showTips("保存中，请等待...");
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
		   <html:form action="/mjxyJtrych" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-jg-mzmc" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/nbty_rych.do?method=rychSq" />
			<input type="hidden" id="viewName" name="viewName" value="view_nbty_xsrychb" />
			<input type="hidden" id="tabName" name="tabName" value="nbty_xsrychb" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="save_xn" id="save_xn" value="${xn}"/>
			<input type="hidden" name="save_sqrq" id="save_sqrq" value="${nowTime}"/>
			<input type="hidden" name="notXsgb" id="notXsgb" styleId="notXsgb" value="${notXsgb}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:${title }
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
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 15%">
						<font color="red">*</font>院系：
					</td>
					<td align="left">
					
					<logic:equal name="doType" value="view">
						${rs.xymc }
					</logic:equal>
					<logic:equal name="doType" value="modi">
						${rs.xymc }
					</logic:equal>
					<logic:notEqual name="doType" value="view">
						<logic:notEqual name="doType" value="modi">
						<logic:equal name="userOnLine" value="teacher">
							<logic:equal name="userType" value="xy">
								<html:select property="xydm" disabled="true" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
									<html:hidden property="save_xydm" value="${userDep}"/>
							</html:select>
							</logic:equal>						
							<logic:notEqual name="userType" value="xy">
							<html:select property="save_xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="userOnLine" value="student">
							${bjxx.xy}
						</logic:equal>
						</logic:notEqual>
					</logic:notEqual>
					</td>
					<td align="right" style="width: 10%">
						年级：
					</td>
					<td align="left" style="width: 40%">
					<logic:empty name="rs">
						<logic:notEmpty name="nj">
							<bean:write name="nj" />	
						</logic:notEmpty>
					</logic:empty>
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="nj" />	
					</logic:notEmpty>
					<logic:equal name="userOnLine" value="student">
						<bean:write name="bjxx" property="nj" />	
					</logic:equal>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
					<logic:equal name="doType" value="view">
						${rs.zymc }
					</logic:equal>
					<logic:equal name="doType" value="modi">
						${rs.zymc }
					</logic:equal>
					<logic:notEqual name="doType" value="view">
						<logic:notEqual name="doType" value="modi">
						<logic:equal name="userOnLine" value="teacher">
						<html:select property="save_zydm" style="width:180px" styleId="zy"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</logic:equal>
					<logic:equal name="userOnLine" value="student">
							${bjxx.zymc}
						</logic:equal>
						</logic:notEqual>
					</logic:notEqual>
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
					<logic:equal name="doType" value="view">
						${rs.bjmc }
						<html:hidden property="save_bjdm" value="${rs.bjdm}"/>
					</logic:equal>
					<logic:equal name="doType" value="modi">
						${rs.bjmc }
						<html:hidden property="save_bjdm" value="${rs.bjdm}"/>
					</logic:equal>
					
					<logic:notEqual name="doType" value="view">
						<logic:notEqual name="doType" value="modi">
						<logic:equal name="userOnLine" value="teacher">
						<html:select property="save_bjdm" style="width:180px" styleId="bj"
						onchange="getInfo()">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
						</html:select>
						</logic:equal>
						<logic:equal name="userOnLine" value="student">
							${bjxx.bjmc}
							<html:hidden property="save_bjdm" value="${bjxx.bjdm}"/>
						</logic:equal>
						</logic:notEqual>
					</logic:notEqual>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						${xn}
						
					</td>
					<td align="right">
						<font color="red"></font>班级人数：
					</td>
					<td align="left">
						${bjrs}人
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						班长(团支书)：
					</td>
					<td align="left">
						${bjgb}
					</td>
					<td align="right">
						手机号码：
					</td>
					<td align="left">
					<logic:empty name="rs">
						<html:text property="save_sjhm" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="chLeng(this,20)"/>
					</logic:empty>
					<logic:notEmpty name="rs">
						<html:text name="rs" property="save_sjhm" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="chLeng(this,20)"/>
					</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						党员人数<br>（比例）：
					</td>
					<td align="left">
						${dyrsbl}%
					</td>
					<td align="right"><font color=red>*</font>
						荣誉称号：
					</td>
					<td align="left">
					<logic:empty name="rs">
					<html:select property="save_jtrychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="jtrychList" property="dm"
								labelProperty="mc" />
					</html:select>
					</logic:empty>
					<logic:notEmpty name="rs">
						${rs.jtrychmc }
						<html:hidden name="rs" property="save_jtrychdm"/>
					</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						违纪处分人数<br>（比例）：
					</td>
					<td align="left">
						${cfrsbl}%
					</td>
					<td align="right">
						申请日期：
					</td>
					<td align="left">
						${nowTime}
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						获奖情况：
						<br />
						<span class="style1">(限300字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" property="save_hjqk" onblur="chLeng(this,300)"/>
					</logic:empty>
					<logic:notEmpty name="rs">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_hjqk" onblur="chLeng(this,300)"/>
					</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限2000字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:empty name="rs">
						<html:textarea rows="8" style="width:98%" property="save_zysj" onblur="chLeng(this,2000)"/>
					</logic:empty>
					<logic:notEmpty name="rs">
						<html:textarea rows="8" name="rs" style="width:98%" property="save_zysj" onblur="chLeng(this,2000)"/>
					</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
				<logic:empty name="rs">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</logic:empty>
				<logic:notEmpty name="rs" >
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</logic:notEmpty>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAble" value="yes">
				<logic:notEqual name="doType" value="view">
					<logic:notEqual name="doType" value="modi">
					<button type="button" class="button2" id="buttonSave" onclick="saveJtrych()" style="width:80px">
						保  存 
					</button>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="doType" value="modi">
					<button type="button" class="button2" id="buttonSave" onclick="modiJtrych()" style="width:80px">
						保  存 
					</button>
				</logic:equal>			
				</logic:equal>
				<logic:equal name="doType" value="view">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
						</button>
				</logic:equal>
				<logic:equal name="doType" value="modi">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
						</button>
				</logic:equal>
			</div>
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
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

