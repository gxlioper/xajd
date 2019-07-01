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
    <base target="_self">
	<head>
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
	<base target="_self">
	<script language="javascript">
</script>
	<body onload="readOnly();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<html:form action="/sztz_modi_data" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：素质拓展 - 信息维护 - 素质拓展成绩
				</div>
			</div>
			    <logic:present name="isEXIST">
         	    <logic:match value="yes" name="isEXIST">
         		<script language="javascript">
	         		alert("该学年、学期该拓展活动成绩已存在，不能再添加！");
	         	</script>
	            </logic:match>
                </logic:present>
			    <logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			    </logic:empty>
			    <logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("保存成功！");
    				</script>
				</logic:equal>
			    <logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>				
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" 
				    value="/sztz_modi_data.do" />
				<input type="hidden" id="pk" name="pk"
				    value="<bean:write name="pk" scope="request"/>"/> 
                <input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>"/>
                <input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>"/>
				<input type="hidden" id="realTable" name="realTable"
				    value="<bean:write name="realTable" scope="request"/>"/>					
				<input type="hidden" id="tableName" name="tableName"
				    value="<bean:write name="tableName" scope="request"/>"/>    
                <input type="hidden" id="xsnj" name="xsnj"
					value="<bean:write name='rs' property="nj"/>"/>
				<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble" scope="request"/>"/>	
				<fieldset>
					<legend>
						素质拓展成绩信息维护
					</legend>
					<table width="100%" id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b><bean:write name="tips" scope="request" /></b>
								</td>
							</tr>
						</thead>
						<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								
								<td align="left" >								    
								    <html:text name='rs' property="xh" styleId="xh" readonly="true"/>									
								</td>
							<td align="right">
							<font color="red">*</font>年度：
						    </td>
						    <td align="left">
							<html:select name="rs" property="nd" 
							style="width:90px;background-color:#FFFFFF"
								styleId="nd" disabled = "true">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							 </html:select>
						     </td>

						</tr>
						<tr style="height:22px">
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
							<font color="red">*</font>学年：
							</td>
							<td align="left">
								<html:select name="rs" property="xn" 
								style="width:90px;background-color:#FFFFFF"
								styleId="xn" disabled = "true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>							
							<td align="right">
								<font color="red">*</font>学期：
							</td>
							<td align="left">
								<html:select name="rs" property="xq" 
								style="width:90px;background-color:#FFFFFF"
								styleId="xq" disabled = "true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							    </html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								<font color="red">*</font>拓展活动(项目)：
							</td>
							<td align="left">
								<html:select name="rs" property="xmdm"
								style="background-color:#FFFFFF" styleId="xmdm" disabled = "true">
									<html:option value=""></html:option>
									<html:options collection="tzxmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
							    <font color="red">*</font>学分：
						    </td>
						    <td align="left">						
							  <html:text name='rs' property="xf" styleId="pxjssj" readonly="true"
								onkeypress="return sztzNumInputValue(this,4,event)"  maxlength="4" style="width:90px;" />						    							
							</td>
						</tr>
						<tr style="height:22px;">
							<td align="right">
								班级：
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
					           拓展活动获奖类别：
					         </td>
					        <td align="left">
								<html:select name="rs" property="jbdm" style="width:90px;background-color:#FFFFFF"
								styleId="jbdm" onchange="sztzHjjb('/xgxt/sztz_modi_data.do')">
								<html:option value=""></html:option>
								<html:options collection="hjjbList" property="jbdm"
									labelProperty="jbmc" />
							    </html:select>
						    </td>
						</tr>
						<tr align="left" style="height:30px">
							<td align="right">
								备注：
							</td>
							<td colspan="3" align="left">
								<html:textarea name='rs' property='bz' style="width:99%" rows='5'/>
							</td>
						</tr>
						
					</table>
					<div class="buttontool" align="center">
						<button class="button2"
						onclick="if(sztzCheckTj('xn','nd','xq'))sztzDataSave('xn-xq-xh-xmdm-xf');"
						style="width:80px" id="buttonSave">
						保 存
					    </button>
					    &nbsp;&nbsp;&nbsp;&nbsp;
					    <button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					    </button>

					</div>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

