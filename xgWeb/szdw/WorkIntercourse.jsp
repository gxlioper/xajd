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
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<script type="text/javascript">
		function sxpubx(type){
	if(document.getElementById("title").value == ""){
		alert("请填写标题！");
			document.getElementById("title").focus();
		return false;
	}
	if(FCKeditorAPI.GetInstance("content1").EditorDocument.body.innerText == ""){
		alert("请填写新闻正文！");
		return false;
	}

	if(type=="xy"){
	if(document.getElementById("saveXxplandm").value == ""){
		alert("请选择所属学校计划！");
			document.getElementById("saveXxplandm").focus();
		return false;
	}
	refreshForm('xy_plan_Save.do?type=xy');
	}else if(type=="xx"){
	refreshForm('xx_plan_Save.do?type=xx');
	}else if(type=="xyzj"){
	refreshForm('xyzj_Save.do?type=xyzj');
	}else if(type=="jyzt"){
	refreshForm('jyzt_Save.do?type=jyzt');
	}else if(type=="hdch"){
	refreshForm('hdch_Save.do?type=hdch');
	}else if(type=="hdzj"){
	refreshForm('hdzj_Save.do?type=hdzj');
	}else if(type=="zzxxcl"){
	refreshForm('zzxxcl_Save.do?type=zzxxcl');
	}else if(type=="szkhwj"){
	refreshForm('szkhwj_Save.do?type=szkhwj');
	}else if(type=="khtz"){
	refreshForm('assess_Inform_save.do?type=khtz');
	}else if(type=="ysjyxx"){
	refreshForm('artEducate_info_save.do?type=ysjyxx');
	}else if(type=="dyjh"){
	refreshForm('research_plan_Save.do?type=dyjh');
	}else if(type=="dyzj"){
	refreshForm('research_summarize_Save.do?type=dyzj');
	}else if(type=="aqjytz"){
	refreshForm('safetyEducation_Inform_save.do?type=aqjytz');
	}else if(type=="xsjyhdtz"){
	refreshForm('sfreshman_educationPloy_save.do?type=xsjyhdtz');
	}else if(type=="fdypx"){
	refreshForm('counsellor_fosterInfo_save.do?type=fdypx');
	}else if(type=="fdykhpy"){
	refreshForm('counsellor_AssessInfo_save.do?type=fdykhpy');
	}else if(type=="fdyzbaptz"){
	refreshForm('counsellor_onDuty_save.do?type=fdyzbaptz');
	}else if(type=="fdyzdxx"){
	refreshForm('counsellor_system_save.do?type=fdyzdxx');
	}else if(type=="bjfc"){
	refreshForm('class_elegant_save.do?type=bjfc');
	}else if(type=="fdygzjl"){
	refreshForm('class_workIntercourse_save.do?type=fdygzjl');
	}else if(type=="yxdsfc"){
	refreshForm('excellenceTeacher_elegant_save.do?type=yxdsfc');
	}else if(type=="001"||type=="002"||type=="003"){
		var jhtype = document.getElementById("type").value;
	refreshForm('xsgbzjfb.do?type=xsgbzjfb&jhtype='+jhtype);	
	}
}
		</script>
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
	<script language="javascript">

</script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<body>
		<html:form action="/show_xyjh_list" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：思想教育-学生工作队伍建设-辅导员工作交流
				</div>
			</div>
			<input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="type" name="type" value="xy" />
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								<html:select property="year" style="width:60px" styleId="month">
									<html:option value=""></html:option>
									<html:options collection="yearList" property="en" labelProperty="cn" />
								</html:select>
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select property="month" style="width:40px" styleId="month">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn" />
								</html:select>
								月
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />名称：
								<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/show_workIntercourse_list.do?act=fdygzjl')">
									查 询
								</button>
							</td>
							<td width="10" align="center" valign="middle">
								<button type="button" class="button2" onclick="">
									&nbsp;&gt;&nbsp;&gt;&nbsp;
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:notEqual name="isModi" value="yes" scope="request">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			</logic:notEqual>
			<logic:equal name="isModi" value="yes" scope="request">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					请修改文档
				</p>
			</logic:empty>
			</logic:equal>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:notEqual name="updataOFF" value="true"> 
								<logic:equal name="writeAble" value="yes"> 
								<td id="work">
										操作
								</td>
								</logic:equal>
								</logic:notEqual> 
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="sxDis('fdygzjl')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="0" length="1">
								<logic:equal name="writeAble" value="yes"> 
								<logic:notEqual name="updataOFF" value="true"> 
       							<td align=right>
       								<a href="#" onclick="location='show_workIntercourse_list.do?documentId=<bean:write name="v"/>&act=fdygzjl&part=N021113'">修改</a>/ <a href="#" onclick="if(confirm('确实要删除当前计划吗？'))location='class_workIntercourse_del.do?type=fdygzjl&documentId=<bean:write name="v"/>'" >删除</a> </td>
      							</logic:notEqual> 
      							</logic:equal>
      							</logic:iterate>
      							
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			
			</logic:notEmpty>
			
			<logic:equal name="writeAble" value="yes"> 
  			<fieldset> 
 			<legend> 添加文档 </legend> 
    		<input type="hidden" name="isModi" id="isModi" value="<bean:write name="isModi" />" /> 
    		<input type="hidden" name="documentId" id="documentId" value="<bean:write name="documentId" />" />  	
  			<TABLE class="tbstyle" width="100%"> 
    		<TR>
      			<TD align=right width="100"> 文档标题： </TD> 
      			<TD align=left colspan="3"> <input type="text" name="title" id="title" style="width:100%" value="<bean:write name="documenttit"/>"> </TD> 
    		</TR> 
    		<TR> 
      		<TD align=right width="100"> 文档内容： </TD> 
      		<TD align=center colspan="3"> <INPUT type="hidden" name="content1" value="<bean:write name="documentcont"/>"> 
<%--        		<IFRAME ID="eWebEditor1"--%>
<%--								src="BatEditor" frameborder="0"--%>
<%--								scrolling="no" width="100%" height="350"></IFRAME> </TD> --%>
					<FCK:editor instanceName="content1" toolbarSet="Default" inputName="content1"
									width="100%" height="400px" >
									<jsp:attribute name="value">
										<logic:equal name="update" value="update">
										<bean:write name="content1" filter="false"/>
										</logic:equal>
									</jsp:attribute>
								</FCK:editor>
    		</TR> 
    		<TR> 
      		<TD colspan=4 align=center> <button type="button" class="button2" onclick="sxpubx('<bean:write name="type"/>')"> 提 交 </button>
        		<INPUT type=reset name=b2 value="重填" class="button2"> </TD> 
    		</TR> 
  			</TABLE> 
  			</fieldset> 
  			</logic:equal>
		</html:form>
	</body>
</html>
