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
  <link rel="icon" href="images/icon.ico" type="image/x-icon" />
  <link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
  <script type="text/javascript" src="js/function.js"></script>
  <script type="text/javascript" src="js/jsFunction.js"></script>
  <body>
  <html:form action="/hzjy_azb">
  			
    <div class="title" style="width:100%">
    	<div class="title_img" id="title_m">
			<bean:message bundle="shgc" key="hzjy_azb" />
		</div>
    </div>
    
    <input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>" />
    <input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable"/>" />    
    
    <fieldset>
      <legend>
      	查 询
      </legend>
      <table class="tbstyle" width="100%">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级：
									<html:select property="nj" style="width:100px" styleId="nj"
										onchange="refreshForm('/xgxt/hzjy_azb.do')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<logic:notPresent name="xyuser">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('/xgxt/hzjy_azb.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notPresent>
									<logic:present name="xyuser">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy" disabled="true"
										onchange="refreshForm('/xgxt/hzjy_azb.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:present>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('/xgxt/hzjy_azb.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/hzjy_azb.do')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:120px"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:120px"></html:text>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
	<logic:empty name="rs">
		<p><p><p>
		<center>
			未找到任何信息！
		</center>
	</logic:empty>		
	<logic:notEmpty name="rs">
	<fieldset>
	  <legend>
	      记录数：&nbsp;&nbsp;<bean:write name="rsNum" />
	  </legend></fieldset>
    <table style="width: 100%" class="tbstyle">
     <thead>
	  <tr>
	  	<logic:iterate name="title" id="titTxt">
	  		<td><bean:write name="titTxt"/></td>
	  	</logic:iterate>
	  </tr>
	  </thead>
  	  <logic:iterate id="v" name="rs">
  	  	<tr style="cursor: hand" onclick="rowOnClick(this)">
  	  		<logic:iterate id="s" name="v">
  	  			<td><bean:write name="s"/></td>
  	  		</logic:iterate>
  	  	</tr>
  	  </logic:iterate>	
    </table>
    
   </logic:notEmpty> 
    <logic:equal value="yes" name="write">
    	<center>
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" onclick="mark('hzjyazb');"
						style="width:90px">
						<logic:present name="xyuser">
							安排协调员
						</logic:present>
						<logic:notPresent name="xyuser">
							修改安置信息
						</logic:notPresent>
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('hzjy_bb_xsazb.do?xydm='+ document.getElementById('xy').value + '&bjdm=' + document.getElementById('bj').value);"
						style="width:80px">
						生成安置表
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
				</div>
		</center>
    </logic:equal>
    </html:form>
    
  </body>
  <script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
</html:html>
