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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	

   
	

	
	function viewMoreinfo(doType){
		var url ="jxglrwbmmorequery.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 1000, 800);
		 }
		}	
	
	function hzjy_xssqbDataExport() {
	       document.forms[0].action = "/xgxt/hzjy_xssqbDataExport.do?realTable=view_hzjysh";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }	
        
	
	</script>



	<body>
		<html:form action="/jxglrwbmxxshresult" method="post">
			<logic:notEmpty name="piliang">
				<input type="hidden" name="whichpk"
					value="<bean:write name="piliang" />" />
				<script>
				   var whichpk = $("whichpk").value;
				   alert(whichpk);
				</script>
			</logic:notEmpty>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 网上征兵 - 入伍报名结果查询
				</div>
			</div>
			<fieldset>
				<legend>
					网上征兵审核结果查询
				</legend>

				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							<font color="blue">提示：双击一行可以查看详细信息</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>

							<logic:notEqual value="11407" name="xxdm">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo('view')">
									<td align="center">
									<input type="hidden" name="pkValue"
										value="<bean:write name="s" property="xh"/>" />
										<bean:write name="s" property="行号" />
									</td>
									<td align="center">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="rxsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="zymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="djsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxshimg" filter="false" />
									</td>
								</tr>
							</logic:iterate>
							</logic:notEqual>
							<logic:equal value="11407" name="xxdm">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewMoreinfo('view')">
									<td align="center">
									<input type="hidden" name="pkValue"
										value="<bean:write name="s" property="xh"/>" />
										<bean:write name="s" property="行号" />
									</td>
									<td align="center">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="rxsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="zymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="djsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxsh" filter="false" />
									</td>
									<td align="center">
										<bean:write name="s" property="zbbgssh" filter="false" />
									</td>
								</tr>
							</logic:iterate>
							</logic:equal>
						</table>
					</fieldset>
				</logic:notEmpty>
			</fieldset>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("删除成功!");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("删除失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>

	</body>
</html>
