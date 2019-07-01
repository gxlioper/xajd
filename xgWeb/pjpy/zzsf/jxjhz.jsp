<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<base id="base"  />
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

  </head>
  <link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<script language="javascript"></script>
  <body>
    <html:form action="/pjpy_jxjhz" >
       <div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：
			<bean:write name="tips" scope="request" />
		</div>
	  </div>
	  <fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" styleId="nj" style="width:80px" onchange="refreshForm('pjpy_jxjhz.do')">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>											
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：											
										<html:select property="xn" styleId="xn" style="width:100px" styleId="xn">
											<html:options collection="xnList" property="xn"
														                    labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;&nbsp;学号：
										<html:text property="xh" styleId="xh" style="width:85px"></html:text>						
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
											<button type="button" class="button2" style="height:40px;width:40px"
												onclick="">
												&nbsp;选择&nbsp;
											</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" styleId="xydm" style="width:180px" styleId="xy" onchange="refreshForm('pjpy_jxjhz.do')" disabled="${disable}">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" styleId="zydm" style="width:180px" styleId="zy" onchange="refreshForm('pjpy_jxjhz.do')" >
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" styleId="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
			<fieldset style="width:100%;height: 100%">
				<legend>
				  选择导出字段
				</legend>
				<input type="hidden" id="table">
				<html:select styleId="selectTab" property="selectTab" onchange="refreshForm('pjpy_jxjhz.do')"  value="${tableName}">
				   <html:option value="view_jxjhz_output">奖学金</html:option>
				   <html:option value="view_rychhz_output">荣誉称号</html:option>
				</html:select>
				<table class="tbstyle" style="width:100%;height: 300">
				  <tr>
				     <td width=45%>
					      <html:select property="en" styleId="srctab" multiple="multiple" ondblclick="ondblckEv(this)" style="width:100%;height:300">
					      	<html:options collection="srcTabColsList" property="en" labelProperty="cn"/>
					      </html:select>
				     </td>
				     <td width=10% align=center>
					     <button type="button" class="button2" style="width:90%" onclick="ondblckEv(document.getElementById('srctab'))">&gt;</button><p>
					     <button type="button" class="button2" style="width:90%" onclick="ondblckEv(document.getElementById('srctab'),'right')">选择</button><p>
					     <button type="button" class="button2" style="width:90%" onclick="ondblckEv(document.getElementById('targTabCols'))">&lt;</button><p>
					     <button type="button" class="button2" style="width:90%" onclick="ondblckEv(document.getElementById('targTabCols'),'left')">&lt;&lt;</button><p>
				     </td>
				     <td width=45%>
				     	 <div id="tarSel"><select id="targTabCols" name="targTabCols" ondblclick="ondblckEv(this)"  multiple="multiple"  style="width:100%;height:300"></select></div>
				     </td>
				  </tr>
				</table>
				<div class="buttontool" align=center>
				  <button type="button" class="button2" onclick="jxjhzOutput()">
				    	输出数据
				  </button>
				</div>
			</fieldset>
    </html:form>
  </body>
  <script>
     
  </script>
</html:html>
