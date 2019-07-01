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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
					当前所在位置：思政队伍-导师其他相关-优秀导师风采
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
									<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/show_excellenceTeacherElegant_list.do?act=yxdsfc')">
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
							<tr onclick="rowOnClick(this)" ondblclick="sxDis('yxdsfc')" style="cursor:hand">
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
       								<a href="#" onclick="location='show_excellenceTeacherElegant_list.do?documentId=<bean:write name="v"/>&act=yxdsfc&part=N170401'">修改</a>/ <a href="#" onclick="if(confirm('确实要删除当前计划吗？'))location='excellenceTeacher_elegant_del.do?type=yxdsfc&documentId=<bean:write name="v"/>'" >删除</a> </td>
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
        		<IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> </TD> 
    		</TR> 
    		<TR> 
      		<TD colspan=4 align=center> <button type="button" class="button2" onclick="sxpub('<bean:write name="type"/>')"> 提 交 </button>
        		<INPUT type=reset name=b2 value="重填" class="button2"> </TD> 
    		</TR> 
  			</TABLE> 
  			</fieldset> 
  			</logic:equal>
		</html:form>
	</body>
</html>
