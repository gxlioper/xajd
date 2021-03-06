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
		<base target = "_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="showLzRq();loadView()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 责任区联系人
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								责任区负责人
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right" style="width: 10%">
							<font color="red">*</font>责任人：
						</td>
						<td height="22" align="left" style="width: 30%">
							<html:text name="rs" property="xh" styleId="xh" readonly="true" />

						</td>
						<td height="22" align="right" style="width: 15%">
							联系方式：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lxfs" maxlength="25"></html:text>
						</td>
					</tr>

					<tr>
						<td height="22" align="right">
							姓名：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td height="22" align="right">
							邮箱：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="dzyx" maxlength="25"></html:text>
						</td>

					</tr>
					<tr>
						<td height="22" align="right">
							性别：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" name="rs" property="xb" />
						</td>
						<td height="22" align="right">
							任职日期：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="rq" styleId="rq"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rq','y-mm-dd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							年级：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							专业：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<td height="22" align="right">
							是否在职：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="sfzz" style="width:120px" styleId="sfzz" onchange="showLzRq()">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>								
							</html:select>
							
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							班级：
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td height="22" align="right">							
						</td>
						<td height="22" align="left">							
						</td>
					</tr>
					<tr style="display: none" id="showlz">
						<td height="22" align="right">

						</td>
						<td height="22" align="left">

						</td>
						<td height="22" align="right">
							离职日期：
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="lzrq" styleId="lzrq"
								style="cursor:hand;" onblur="dateFormatChg(this)"
								style="cursor:hand;"
								onclick="if($('rq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right" rowspan="2">
							<font color="red">*</font>负责区：
						</td>
						<td colspan="3">
							楼栋：
							<html:select property="lddm" style="width:120px" styleId="lddm"
								onchange="getSsbhLb()">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>
							寝室号：
							<html:select property="ssbh" style="width:120px" styleId="ssbh"
								onchange="selectPoint(this,'fzssbh','ssbhcn','lddm')">
								<html:options collection="ssbhList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr align="left">
						<td colspan="4">
							寝室编号：
							<br>
							<html:textarea name="rs" property="fzssbh" styleId="fzssbh"
								rows="5" style="width:95%" readonly="true" />
							<br>
							寝室注释：
							<br>
							<html:textarea name="rs" property="ssbhcn" styleId="ssbhcn"
								rows="5" style="width:95%" readonly="true" />
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							备注：
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="bz" style="width:60%"
								style="width:95%" rows="4" />
						</td>
					</tr>
				</table>			
				<div class="buttontool" id="button" align="center">
					<button class="button2" onclick="zrrModiSave('xh-fzssbh')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="buttonClear" onclick="clearFzq()" style="width:80px"
						id="buttonSave">
						清空负责区
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function zrrModiSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("请将带\"*\"号的项目输入完整！");
			       return false;
		           }		
	           }
	                        var dzyx = document.getElementById('dzyx').value;
	          if(!isEmail(dzyx) && dzyx!=""){
		          alert("请输入正确的电子邮箱!");
		          return false;
	          }	           	                    
 
	           if(confirm("确定要提交当前责任人信息？")){  	           
                refreshForm("/xgxt/nblgxy_gygl.do?method=zrrModi&doType=Save");
                $("buttonSave").disabled=true; 
               }
	     }
	     function clearFzq(){
	         if($("fzssbh").value!==""||$("ssbhcn").value!==""){
	            if(confirm("确定要清空\"宿舍编号、注释\"中的当前内容？")){
	                $("fzssbh").value="";
	                $("ssbhcn").value="";
	            }
	         }
	     }
	     function showLzRq(){
	          var sfzz = $("sfzz").value;
	          if(sfzz=="否"){
	             $("showlz").style.display="";
	          }else{
	             $("showlz").style.display="none";
	          }
	     }
	</script>
</html>
