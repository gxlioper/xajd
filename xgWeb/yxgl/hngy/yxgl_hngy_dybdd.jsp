<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<base target="_self"/>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
			type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
function expTab_se(the_table, tabTit, titSpan) {	
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";		
	the_content += document.all(the_table).outerHTML;		
	//the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	//the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	//the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
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
	<center>
		<html:form action="/yxgl_init_time" method="post">
			<table id="expTb" width="99%">
			<tbody>
				<tr>
					<td width="100%" colspan="2">
						<div align="center" style="font-size: 35px;font-family:黑体;font-weight:bold;">
						河南工业大学</div><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="center" style="font-size: 20px;font-family:黑体;font-weight:bold;">
						2008年新生入学手续办理单</div><p>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr align="center" width="100%" size="2" color="#666666"/>
					<table id="table1" border="0" width="100%" bgcolor="#666666">
					<tr style="font-size: 18px;font-family:黑体;font-weight:bold;color:white">
						<td align="center">新生类别：</td>
						<td align="center">理工&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">文史&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">艺术类&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">专升本&nbsp;&nbsp;<input type="checkbox"/></td>
					</tr>
				</table>
				<hr align="center" width="100%" size="2"  color="#666666"/>	
				</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td style="width:75%">
						<p>姓名：___<bean:write name="rs" property="xm"/>______ 
						   性别：__<bean:write name="rs" property="xb"/>__ 
						   准考证号：______ <bean:write name="rs" property="ksh"/>_______
						 
					</td>
					<td rowspan="4" style="width:25%">
						<img height="100" width="75"  border="1" align="right"
							src="<bean:write name="rs" property="picture"/>"/>
					</td>
				</tr>
				<tr><td>录取通知书：_________________
						学号：_______<bean:write name="rs" property="xh"/>________</td></tr>
				<tr><td><bean:message key="lable.xsgzyxpzxy" />：_______<bean:write name="rs" property="xymc"/>________
						专业：_______<bean:write name="rs" property="zymc"/>________</td></tr>
				<tr><td>班级：_______<bean:write name="rs" property="bjmc"/>________
						报到时间：_______月________日</td></tr>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="2">
			<table id="table3" width="100%">
					<tr><td>&nbsp;</td></tr>
				<tr>
				<td align="left" style="font-weight:bold;font-family:黑体;font-size: 17px;" colspan="5">
					一、新生报到时需办理一下手续：
				</td>
				</tr>
				<tr>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;"><bean:message key="lable.xsgzyxpzxy" />资格审查：</font>
							<div>
							 1.交验并进行准考证、身份证、<br>&nbsp;&nbsp;录取通知书、电子档案、（<br> &nbsp;&nbsp;片）与本人五对照<br>
							 2.填写新生登记表并核对学号<br>
							 3.领取已通过农村卡交通的收据<br>
							 4.领取住宿通知单<p>
							 <div align="right">（学  院）</div>
							</div>
					</td>
					<td style="width:8%" align="center">
						<font style="height:4px;width:30px;font-size: 13px;">&rarr;</font>
					</td>
					<td style="width:25%;height:150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;">收费：</font><br>
							 1.已通过农村卡交费并在<bean:message key="lable.xsgzyxpzxy" /><br>&nbsp;&nbsp;领取收据的同学无须办理<br>
							 2.到新生所在的小区校园卡中<br>&nbsp;&nbsp;心领取临时校园周转卡<p><br><br>
							 <div align="right">（财务处）</div>	
					</td>
					<td style="width:8%"  align="center">&rarr;</td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;">住宿：</font><br>
							 	凭住宿通知单到所住楼座登记<br>领取钥匙入住<br><br>
								____<bean:write name="rs" property="ldh"/>_____楼<br>
								____<bean:write name="rs" property="qsh"/>_____房号<p><br>
							 <div align="right">（宿教管理中心）</div>
					</td>
				</tr>
				</table>
				</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
				 <td colspan="2">
				<table id="table3" width="100%">
				<tr>
						<td>
							&nbsp;
						</td>
						</tr>
				<tr>
				<td align="left" style="font-weight:bold;font-family:黑体;font-size: 17px;" colspan="5">
					二、入校后，军训期间由<bean:message key="lable.xsgzyxpzxy" />通知办理的手续：
				</td>
				</tr>
				<tr>
					<td width="25%" height="150px" style="border:1px #000000 solid">
						<div>
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;">体检：</font>
							<div>
							 时间安排在军训期间<br>体检不合格者，交学校招生办<br>公室处理<br>
							<p><br>
							医师：<p>
							 <div align="right">（校医院）</div>
							</div>
						</div>
					</td>
					<td style="width:8%"  align="center"><font style="height:4px;width:30px;font-size:10.5pt;">&rarr;</font></td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;">户籍关系：</font>
							<p>
							 经办人：<p><br><br><br><br>
							 <div align="right">（保卫处）</div>
					</td>
					<td style="width:8%"  align="center">&rarr;</td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:黑体;font-size: 13px;">注册：</font><br>
							 	此单和准考证、录取通知书附<br>联交所在<bean:message key="lable.xsgzyxpzxy" /><br>
							<p></p><br><br><br><br>
							 <div align="right">（学 院）</div>
					</td>
				</tr>
				</table>
				 </td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="2">
						<table  id="table4" width="100%" > 
						<tr>
						<td>
							&nbsp;
						</td>
						</tr>
					<tr>
					<td align="left" style="font-weight:bold;font-family:黑体;font-size: 17px;" colspan="5">
						三、注意事项：
					</td>
					</tr>
					<tr>
						<td align="left">
						<div style="width:100%">
							1.<bean:message key="lable.xsgzyxpzxy" />在发本单时，应注明新生类别、姓名、性别、专业、班级、准考证号、学号及报道时间。<br>
							2.新生凭此单、准考证号及入学通知书严格按照规定的顺序办理入学手续。有关单位在办理此单时，经办人
							应签名并加盖专用章。<br>
							3.申请办理"绿色通道"的新生须持家庭所在地乡以上民政部门出具的特困证明，学生本人身份证、户口
							迁移证（户口本）、准考证原件、有学校审查批复后到学生处办理绿色通道，再到财务处办理相关手续。<br>
							4.新生凭交费收据以班级为单位到教务处领书。<br>
							5.新生须将各项手续办完，将此单、准考证及通知书第二栏交新生所在<bean:message key="lable.xsgzyxpzxy" />，凭财务处缴费收据在<bean:message key="lable.xsgzyxpzxy" />注册后，
							方能取得学籍。<br>
							6.新生报到后，学校将统一组织入学资格审查，入发现有违反高校规定者，将取消其入校资格。<br>
							7.领取校园卡地址：嵩山路校区学8楼对面；中原路校区1号楼113房间；莲花街校区钟楼广场。<br>
						</div>	
						</td>
					</tr>
				</table>	
					</td>
				</tr>
			</tbody>
		</table>		
		<div class="buttontool" align="center">	
			<button class="button2" onclick="expTab_se('expTb','','')">
						打印报表
			</button>
		</div>					
			</html:form>
		</center>
	</body>
</html>
