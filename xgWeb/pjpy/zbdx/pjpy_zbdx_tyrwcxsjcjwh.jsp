<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  </head>
  <%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
  %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<script language="javascript" src="js/sharedFunction.js"></script>	
  <body onload="onLoadfun()">
    <html:form action="/pjpy_zbdx_tyrwcxsjcjwh" method="post">
    <input type="hidden" id="tableName" name="tableName"
		value="<bean:write name="tableName" scope="request"/>" />
	<input type="hidden" id="realTable" name="realTable"
		value="<bean:write name="realTable" scope="request"/>" />
    <div class="title">
    	<div class="title_img" id="title_m">
    		<bean:message bundle="pjpy" key="pjpy_zbdm_tyrwcxsjwh"/>
    	</div>
    </div>
    <fieldset>
		<legend>
			查询
		</legend>
    	<table width="100%" class="tbstyle">
							<thead>
							  <tr>
									<td align="left">
										学年：
										<html:select property="xn" style="width:100px" styleId="xn" style="">
												<html:options collection="xnList" property="xn"
														labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
										<html:select property="xqdm">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择成绩维护表：
										<select name="dataTable" id="dataTable" onchange="refreshForm('pjpy_zbdx_tyrwcxsjcjwh.do')"> 
											<option></option>
											<option value="view_tycjb">身体素质信息维护</option>
											<option value="view_rwszcjb">人文素质信息维护</option>
											<option value="view_cxysjcjb">创新与实践素质信息维护</option>
										</select>										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级：
										<html:select property="nj" onchange="refreshForm('pjpy_zbdx_tyrwcxsjcjwh.do')">
											<html:option value=""/>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<button class="button2" style="height:40px" id="search_go"
											onclick="checkchoice();refreshForm('pjpy_zbdx_tyrwcxsjcjwh.do?go=go')">
											查询
										</button>
									</td>
							  	</tr>
							  	<tr>
							  		<td>
							  			<bean:message key="lable.xsgzyxpzxy" />：
							  			<html:select property="xydm" onchange="refreshForm('pjpy_zbdx_tyrwcxsjcjwh.do')" style="width:150px">
							  				<html:option value=""/>
							  				<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							  			</html:select>
							  			&nbsp;&nbsp;&nbsp;&nbsp;专业：
							  			<html:select property="zydm" onchange="refreshForm('pjpy_zbdx_tyrwcxsjcjwh.do')" style="width:185px">
							  				<html:option value=""></html:option>
							  				<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							  			</html:select>
							  			&nbsp;&nbsp;&nbsp;&nbsp;班级：
							  			<html:select property="bjdm" style="width:150px">
							  				<html:option value=""></html:option>
							  				<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							  			</html:select>
							  		</td>
								</tr>
									  		
					</thead>
		</table>
	</fieldset>
	<logic:present name="rs">
	<fieldset>
	<legend>
		记录数：
		<bean:write name="rsNum" />
	</legend>
		<table class="tbstyle" width="100%">
			<thead>
				<logic:iterate name="title" id="tit">
					<td>${tit}</td>
				</logic:iterate>
			</thead>
				<logic:iterate id="a" name="rs" >
					<tr>
					<logic:equal value="tycjb" name="realTable">
						<td>${a.xn}<input type="hidden" name="xn" id="xn" value="${a.xn}"></td>
						<td>${a.xq}<input type="hidden" name="xq" id="xq" value="${a.xq}"></td>
						<td>${a.xh}<input type="hidden" name="xh" id="xh" value="${a.xh}"></td>
						<td>${a.xm}</td>
						<td>${a.bjmc}</td>
						<td>${a.kcmc}</td>
						<td>${a.cj}</td>
						<td><input type="text" id="stszcpcj" name="stszcpcj" value="${a.stszcpcj}" onkeyup="onlyNum(this);" onblur="checkCj(this);"/></td>
					</logic:equal>
					<logic:equal value="rwszcjb" name="realTable">
						<td>${a.xn}<input type="hidden" name="xn" id="xn" value="${a.xn}"></td>
						<td>${a.xq}<input type="hidden" name="xq" id="xq" value="${a.xq}"></td>
						<td>${a.xh}<input type="hidden" name="xh" id="xh" value="${a.xh}"></td>
						<td>${a.xm}</td>
						<td>${a.bjmc}</td>
						<td><input type="text" id="rwcj" name="rwcj" value="${a.rwcj}" onkeyup="onlyNum(this);" onblur="checkCj(this);"/></td>
					</logic:equal>
					<logic:equal value="cxysjcjb" name="realTable">
						<td>${a.xn}<input type="hidden" name="xn" id="xn" value="${a.xn}"></td>
						<td>${a.xq}<input type="hidden" name="xq" id="xq" value="${a.xq}"></td>
						<td>${a.xh}<input type="hidden" name="xh" id="xh" value="${a.xh}"></td>
						<td>${a.xm}</td>
						<td>${a.bjmc}</td>
						<td><input type="text" id="cxrjcj" name="cxrjcj" value="${a.cxrjcj}" onkeyup="onlyNum(this);" onblur="checkCj(this);"/></td>
					</logic:equal>
					</tr>
				</logic:iterate>
		</table>
		
					<br/>
					<br/>
	</fieldset>	
	</logic:present>
	<logic:notPresent name="rs">
		<br />
		<div align="center" ><font style="font-size:16px" >请点击查询获得数据!</font></div>
	</logic:notPresent>
		<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
			<button class="button2" onclick="resultSubmit()">
				保    存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="whResetResult()">
				重    置
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="impAndChkData();">
				导入数据
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="dataZbdxExport()">
				导出数据
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>							
    </html:form>
    <script type="text/javascript" src="js/bottomButton.js"></script>
    <logic:present name="doresult">
    <logic:equal value="true" name="doresult">
    <script type="text/javascript">
    	alert("保存成功！");
    </script>
    </logic:equal>
    <logic:equal value="false" name="doresult">
    <script type="text/javascript">
    	alert("保存失败！");
    </script>
    </logic:equal>
    </logic:present>
  </body>
</html:html>
