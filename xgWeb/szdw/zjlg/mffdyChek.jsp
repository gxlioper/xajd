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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/jhzy_rych.do?method=bkzxjjsqb";
			document.forms[0].submit();
		}
		function expTabby(the_table, tabTit, titSpan) {
			/*var HKEY_Root="HKEY_CURRENT_USER";
			var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
			var Wsh=new ActiveXObject("WScript.Shell");
			var HKEY_Key="header";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			    HKEY_Key="footer";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */ 
			var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
			var table_content=((the_table != null || the_table != "")&&$(the_table)) ? document.all(the_table).outerHTML:"未找到任何记录！" ;
			var the_content = "<style media='print'>\n";
			the_content += ".noPrin{\n";
			the_content += "display:none;}\n";
			the_content += "</style>\n";
			the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
			the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
			//the_content += "<script language='javascript' src='js/function.js'>";
			the_content += "</sc";
			the_content += "ript>\n";
			the_content += "<center><h3><b>";
			the_content += table_title;
			the_content += "</b></h3>";	
			the_content += table_content;			
			the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
			the_content = the_content.replace(/ mode=\"(false|true)"/g, "");	
			the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
			the_content = the_content.replace("<BUTTON class=button2 id=buttonFindStu>选择</BUTTON>", "");
			the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
			the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
			the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
			the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
			the_content += "<\/div>";	
			var newwin = window.open("about:blank", "_blank", "");
			newwin.document.open();
			newwin.document.write(the_content);
			newwin.document.close();
			newwin = null;
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：思政队伍 -  先进称号 - 模范辅导员
		</div>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				现在不在申请时间内！--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdw_zjlg" method="post">
		<input type="hidden" id="xjchdm" name="xjchdm"
			value="<bean:write name = "xjchdm" />" />	
		<input type="hidden" id="act" name="act"
			value="${act}" />
		<input type="hidden" id="pkValue" name="pkValue"
			value="<bean:write name = "pkValue"/>" />
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("已通过学校审核，不能申请！");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%" id="theTable">
			<tr>
				<td align="center" width="16%">
						学年
				</td>
				<td width="34%" colspan="3">
				        <input type = "hidden" name="xn" value = "<bean:write name = "rs" property="xn" />">
						<bean:write name = "rs" property="xn" />
				</td>
			</tr>
			<tr>
				<td align="center" width="16%">
						部门名称:
				</td>
				<td align="left" width="34%">
						<bean:write name = "rs" property="bmmc"/>
						<input type = "hidden" name = "bmdm" value="<bean:write name = "rs" property="bmdm"/>" /> 
				</td>
				<td align="center" width="16%">
					<font color="red">*</font>职工号
				</td>
					<td width="34%">
						<bean:write name = "rs" property="zgh"/>
						<input type = "hidden" name = "zgh" value="<bean:write name = "rs" property="zgh"/>" /> 
					</td>
			</tr>
			
			<tr>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						出生年月
					</div>
				</td>
				<td>
					<bean:write name="rs" property="csrq"/>
				</td>
				<td>
					<div align="center">
						籍贯
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfmc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						职务
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zwmc"/>
				</td>
				<td>
					<div align="center">
						毕业院校
					</div>
				</td>
				<td>
					<bean:write name="rs" property="byyx"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学历
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xl"/>
				</td>
				<td>
					<div align="center">
						所学专业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sxzy"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						从事辅导员工作时间
					</div>
				</td>
				<td>
					<bean:write name="rs" property="szgzsj"/>
				</td>
				<td align="center">
					审核状态
				</td>
				<td>
					<html:select name = "rs" property="shzt" styleId="shzt">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>	
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						所带年级、专业班级情况
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="sdnjbjqk" rows='5'
						style="width:100%" onblur="chLeng(this,300)"/>
				</td>
			</tr>
			<tr id= "jfqk">
				<td>
					<div align="center">
						主要事迹
						<br>
						<font color="red"><限2000字>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="zysj" rows='10' styleId="zysj" 
						style="width:100%" onblur="chLeng(this,2000)"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						单位总支意见:
						<br>
						<font color="red"><限200字>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="dwzjyj" rows='10' styleId="dwzjyj" 
						style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学工部意见:
						<br>
						<font color="red"><限200字>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="xgbyj" rows='10' styleId="xgbyj" 
						style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学校意见:
						<br>
						<font color="red"><限200字>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="xxyj" rows='10' styleId="xxyj" 
						style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
			<tr id = "bz">
				<td>
					<div align="center">
						备注
						<br>
						<font color="red"><限200字> </font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="chLeng(this,200)"/>
				</td>
			</tr>
		</table>
		<%--			<logic:equal name="sfksq" value="1">--%>
		<%--				<logic:notPresent name="msg">--%>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button type="button" class="button2" onClick="refreshForm('szdw_zjlg.do?method=xjchChek&doType=save');">
				提交申请
			</button>
		</logic:notEqual>
			<button type="button" class="button2" >
				打&nbsp;&nbsp;&nbsp;印
			</button>
		</div>
	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('审核成功！');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('审核失败！');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
