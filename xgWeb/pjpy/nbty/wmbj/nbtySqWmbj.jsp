
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
	<script language="javascript" src="js/xsgyglFunction.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/nbtyWmqssq.js"></script>
	<script language="javascript">	
     function rychSqSave(){
        var xn = "";
        var xydm = "";
        var bjdm = "";
        var zydm = "";
        var bz = "";
        var jljl = "";
        var zysj = "";
        if($("xn")){
        	xn=$("xn").value
        }
        if($("xy")){
           xydm = $("xy").value;
        }
        if($("bj")){
          bjdm = $("bj").value;
        }
        if($("zy")){
          zydm=$("zy").value
        }
        if($("bz")){
          bz=$("bz").value
        }
        if($("jljl")){
          jljl=$("jljl").value
        }
        if($("zysj")){
          zysj=$("zysj").value
        } 
        if(zysj.length>800){
          alert("主要事迹字数过大，限400字内！");
          return false;
        }
        if(bz.length>800){
          alert("主要事迹字数过大，限400字内！");
          return false;
        }
 
        if(xydm==""){
          alert("<bean:message key="lable.xsgzyxpzxy" />不能为空！");
          return false;
        }
        if(bjdm==""){
          alert("班级不能为空！");
          return false;
        }
         if(zydm==""){
          alert("专业不能为空！");
          return false;
        }
        if(jljl.length>800){
          alert("备注字数过大，限400字内！");
          return false;
        }      
        refreshForm("/xgxt/nbtyWmbj.do?method=nbtySqWmbj&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
    function rychSqPrint(){
        window.open('nbtyWmbj.do?method=nbtyPrintWmbj&pkValue=${pkValue}');
        }	
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/nbtyWmbj" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="realTable" id="realTable" value="nbty_wmqsdjb" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=jtjjknsSq&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${sqsj}"/>
	
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号申请 - 先进班级申请
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>文明班级申请</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>学年：
					</td>
					<td>
					<bean:write name="xn"/>
					<html:hidden property="save_xn" value="${xn}"/>
					</td>
					
					<td align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" style="width: 40%">
					
					<!-- 非学生 -->
					<logic:notEqual name="userOnLine" value="student">
						<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
						<logic:equal name="userType" value="xy">
							<!-- 辅导员 -->
							<logic:equal name="isFdy" value="true">
					   		 <html:select property="save_xydm" styleId="xy" style="width:180px"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							</html:select>
							</logic:equal>
							<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
							<logic:notEqual name="isFdy" value="true">
					   			 <html:select disabled="true" property="save_xydm" styleId="xy"  style="width:180px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
								</html:select>
								<html:hidden property="save_xydm" value="${userDep}"/>
							</logic:notEqual>
						</logic:equal>
						<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 -->
						<logic:notEqual name="userType" value="xy">
							<html:select property="save_xydm" styleId="xy" style="width:180px"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</logic:notEqual>
					<!-- 学生 -->
					<logic:equal name="userOnLine" value="student">
						<bean:write name="map" property="xymc"/>
						<html:hidden property="save_xydm" styleId="xy" value="${map.xydm}"/>
					</logic:equal>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left" style="width: 40%">
					<logic:notEqual name="userOnLine" value="student" >
						<html:select property="save_zydm" styleId="zy" style="width:180px"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>	
					</logic:notEqual>
					<logic:equal name="userOnLine" value="student">
						<bean:write name="map" property="zymc"/>
						<html:hidden property="save_zydm" styleId="zy" value="${map.zydm}"/>
					</logic:equal>
					</td>
					<td align="right">
						<font color="red">*</font>班级：
					</td>
					<td align="left">
					<logic:notEqual name="userOnLine" value="student">
						<html:select property="save_bjdm" styleId="bj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
						</html:select>		
					</logic:notEqual>
					<logic:equal name="userOnLine" value="student">
						<bean:write name="map" property="bjmc"/>
						<html:hidden property="save_bjdm" styleId="bj" value="${map.bjdm}"/>
					</logic:equal>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						奖励记录：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj" property="save_jljl" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
			<logic:equal name="writeAbled" value="yes">
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						保  存 
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:equal>
					<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px">
						打  印
					</button>
			</div>
			<logic:notEqual name="isBgb" value="yes">
				<script>
			          alert("对不起，非班干部成员不能申请！");
			        </script>
			</logic:notEqual>
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
			
			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>
			
		</html:form>
		
	</body>


</html>

