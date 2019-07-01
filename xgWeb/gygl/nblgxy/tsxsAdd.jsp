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
		<base target="_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblgxy_gygl.do?method=tsxsAdd" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-xymc-zymc-bjmc" />
			<input type="hidden" id="ssbh" name="ssbh"
				value="<bean:write name="rs" property="ssbh"/>" />	
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 特殊学生信息
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							特殊学生信息
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<td height="22" align="left" style="width: 30%">
						<html:text property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
					<td height="22" align="right" style="width: 15%">
						<font color="red">*</font>填卡日期：
					</td>
					<td height="22" align="left">
						<html:text property="rq" styleId="rq" onblur="dateFormatChg(this)"
							style="cursor:hand;"
							onclick="return showCalendar('rq','y-mm-dd');" readonly="true" />

					</td>
				</tr>

				<tr>
					<td height="22" align="right">
						姓名：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td height="22" align="right" style="width: 15%">
						联系方式：
					</td>
					<td height="22" align="left">
						长号：
						<html:text property="lxfs_ch" style="width:100px" maxlength="20"></html:text>
						短号：
						<html:text property="lxfs_dh" style="width:80px" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						性别：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xb" />
					</td>
					
					<td height="22" align="right">
						政治面貌：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="zzmmmc" />
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
						民族：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="mzmc" />
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
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xymc" />
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
						楼幢寝室：
					</td>
					<td height="22" align="left">
					    <bean:write name="rs" property="ssmc" />						
					</td>			    
			    </tr>
				<tr>
					<td height="22" align="right">
						
					</td>
					<td height="22" align="left">
						
					</td>
					<td height="22" align="right">
						寝室电话：
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td align="right">
						特长
						<br />
						爱好
						<br/>
						(限500字)
					</td>
					<td colspan="4">
						<html:textarea property="ahtc" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						家庭
						<br />
						状况
						<br/>
						(限500字)						
					</td>
					<td colspan="4">
						<html:textarea property="jtzk" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						学习成
						<br />
						绩和学
						<br />
						风检查
						<br />
						情况
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="xfcj" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						获奖
						<br />
						荣誉
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="hjry" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						违纪
						<br />
						情况
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="wjqk" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						性格特
						<br />
						征和行
						<br />
						为习惯
						<br/>
						(限500字)

					</td>
					<td colspan="4">
						<html:textarea property="xgtzxg" style="width:570px" rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						人际交
						<br />
						往和社
						<br />
						会活动
						<br />
						情况
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="rjjwshhd" style="width:570px" rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						关注、谈
						<br />
						心情况
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="gzqk" style="width:570px"
							rows="4" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						备注:
						<br/>
						(限500字)
						
					</td>
					<td colspan="4">
						<html:textarea property="bz" style="width:570px"
							rows="4" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="tsxsAddSave('xh-rq')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset" style="width:80px"
					id="buttonSave">
					清空
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonSave">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="notIn">
			<script type="text/javascript">
				alert("该生目前尚未入住公寓！");
			</script>
		
		</logic:present>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function tsxsAddSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("请将带\"*\"号的项目输入完整！");
			       return false;
		           }		
	           }	
	       if($("ahtc").value.length>500){
	         alert("爱好特长字数不能超过500字！");
	         return false;
	       }
	       if($("jtzk").value.length>500){
	         alert("家庭状况字数不能超过500字！");
	         return false;
	       }
	       if($("xfcj").value.length>500){
	         alert("学习成绩和学风检查情况字数不能超过500字！");
	         return false;
	       }                
	       if($("hjry").value.length>500){
	         alert("获奖荣誉字数不能超过500字！");
	         return false;
	       } 
	       if($("wjqk").value.length>500){
	         alert("违纪情况字数不能超过500字！");
	         return false;
	       } 
	       if($("xgtzxg").value.length>500){
	         alert("性格特征和行为习惯字数不能超过500字！");
	         return false;
	       } 
	       if($("rjjwshhd").value.length>500){
	         alert("人际交往和社会活动情况字数不能超过500字！");
	         return false;
	       } 
	       if($("gzqk").value.length>500){
	         alert("关注、谈心情况字数不能超过500字！");
	         return false;
	       } 
	       if($("bz").value.length>500){
	         alert("备注字数不能超过500字！");
	         return false;
	       } 	        	                       
           refreshForm("/xgxt/nblgxy_gygl.do?method=tsxsAdd&doType=Save");
           $('buttonSave').disabled=true;
	     }
       
	</script>
</html>
