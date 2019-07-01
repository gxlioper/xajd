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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript"> 
	function expTabdy(the_table, tabTit, titSpan) {
		/*var HKEY_Root="HKEY_CURRENT_USER";
		var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		var Wsh=new ActiveXObject("WScript.Shell");
		var HKEY_Key="header";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		    HKEY_Key="footer";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */
		var table_title = (titSpan == null || titSpan == "") ? tabTit
				: document.getElementById(titSpan).outerHTML;
		var the_content = "<style media='print'>\n";
		the_content += ".noPrin{\n";
		the_content += "display:none;}\n";
		the_content += "</style>\n";
		the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
		the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
		the_content += "<center><h3><b>";
		the_content += table_title;
		the_content += "</b></h3>";
		the_content += document.all(the_table).outerHTML;
    	//the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
		the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
		the_content = the_content.replace(
				/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
		the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
		the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
		the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
		the_content += "<\/div>";
		//confirm(the_content);
		var newwin = window.open("about:blank", "_blank", "");
		newwin.document.open();
		newwin.document.write(the_content);
		newwin.document.close();
		newwin = null;
	}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
<html:form action="/yxjzyjs.do" method="post">
		
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle" id="grjl">
				<thead>
					<tr>
						<td colspan="8" align="center">
							2009届北京地区普通高校毕业生就业推荐表
						</td>
					</tr>
				</thead>
				<tr>
					<td rowspan="7" align="center" >
						<b>个<br>人<br>信<br>息</b>
					</td>
					<td align="center" style="width: 85px" nowrap="nowrap">
						学号
					</td>
					<td>
						<bean:write name="rs1" property="xsxh"/>
					</td>
					<td align="center" nowrap="nowrap" style="width: 150px;">
						姓名
					</td>
					<td style="width: 200px;">
						<bean:write name="rs1" property="name"/>
					</td>
					<td align="center" nowrap="nowrap">
						性别
					</td>
					<td style="width: 150px;">
						<bean:write name="rs1" property="xb" />
					</td>
					<td rowspan="6" align="center">
						<img border="0" style="height:133px;width:100px;"
								src="/xgxt<bean:write name="rs1" property="sczp" />">
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						身份证号
					</td>
					<td>
						<bean:write name="rs1" property="id"/>
					</td>
					<td align="center" nowrap="nowrap">
						出生日期
					</td>
					<td nowrap="nowrap">
						<bean:write name="rs1" property="csrq"/>
					</td>
					<td align="center" nowrap="nowrap">
						政治面目
					</td>
					<td>
						<bean:write name="rs1" property="zzmm"/>
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						生源地区
					</td>
					<td>
						<bean:write name="rs1" property="sydq"/>
					</td>
					<td align="center" nowrap="nowrap">
						电话
					</td>
					<td>
						<bean:write name="rs1" property="lxdh"/>
					</td>
					<td align="center" nowrap="nowrap">
						手机
					</td>
					<td>
						<bean:write name="rs1" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						通讯地址
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="txdz"/>
					</td>
					<td align="center" nowrap="nowrap">
						邮编号码
					</td>
					<td >
						<bean:write name="rs1" property="yzbm"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" nowrap="nowrap">
						毕业学校
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="byxx"/>
					</td>
					<td align="center" nowrap="nowrap">
						学历
					</td>
					<td>
						<bean:write name="rs1" property="xlmc"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						专业
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="zymc"/>
					</td>
					<td align="center">
						学制
					</td>
					<td >
						<bean:write name="rs1" property="xz"/>
					</td>
					
				</tr>
				<tr>
					<td align="center" >
						毕业时间
					</td>
					<td>
						<bean:write name="rs1" property="bysj"/>
					</td>
					<td align="center">
						奖惩<br>情况
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="jlqk"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>社<br>会<br>实<br>践</b>
					</td>
					<td align="right">
						1：
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj1"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						2：
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj2"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						3：
					</td>
					<td colspan="7">
						<bean:write name="rs1" property="shsj3"/>
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">
						<b>特<br>长<br>及<br>能<br>力</b>
					</td>
					<td align="center" colspan="3">
						1：主修外语种类
					</td>
					<td >
						<bean:write name="rs1" property="wyyz"/>
					</td>
					<td align="center">
						级别
					</td>
					<td colspan="3">
						<bean:write name="rs1" property="jb"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						2:计算机水平
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="jsjsp"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						3:特长及能力(包括在校期间担任职务)
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="tcnl"/>
					</td>
				</tr>
				<tr>
					<td align="center" rowspan="5">
						<b>学<br>校<br>推<br>荐<br>意<br>见</b>
					</td>
					<td align="center" rowspan="3" colspan="2">
						系（院）意见：
					</td>
					<td align="center">
						毕业生<br>培养方式
					</td>
					<td colspan="6">
						<bean:write name="rs1" property="pyfs"/>
					</td>
				</tr>
				<tr>
				<td align="center">
						就业范围
					</td>
					<td colspan="6">
						<bean:write name="rs1" property="jyfw"/>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<bean:write name="rs1" property="yxyj"/>
					</td>
				</tr>
				<tr>
					
					<td align="center" rowspan="1" colspan="2">
						学校毕业<br>生就业部<br>门意见
					</td>
					<td colspan="5">
						<bean:write name="rs1" property="jybmyj"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						联系部门
					</td>
					<td >
						<bean:write name="rs1" property="lxbm"/>
					</td>
					<td align="center">
						联系人
					</td>
					<td >
						<bean:write name="rs1" property="bmlxr"/>
					</td>
					<td align="center">
						联系电话
					</td>
					<td colspan="4">
						<bean:write name="rs1" property="bmlxdh"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>备<br>注</b>
					</td>
					<td align="center" colspan="7">
					<bean:write name="rs1" property="bz"/>
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2"  onclick="expTabdy('grjl','','');" style="width: 100px">
					打印列表
				</button>
				&nbsp;&nbsp;
				<button class="button2"  onclick="Close();return false;" style="width: 100px">
					关闭
				</button>
			</div>
		</logic:iterate>
		</html:form>
	</body>
</html>
