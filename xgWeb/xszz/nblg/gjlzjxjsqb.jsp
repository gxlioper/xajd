<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjlzjxjsq";
			document.forms[0].submit();
		}
		
function expTabT(the_table, the_table2, tabTit, titSpan) {	
	/*var HKEY_Root="HKEY_CURRENT_USER";
	var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	var Wsh=new ActiveXObject("WScript.Shell");
	var HKEY_Key="header";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	    HKEY_Key="footer";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */  
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
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table2).outerHTML;	
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
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
<body>
	<html:form action="nblg_xszz.do?method=gjlzjxjsqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								国家励志奖学金申请表
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									出生年月
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									分院
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									专业班级
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="zymc" />
									&nbsp;
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="17%">
								<div align="center">
									上学年智育成绩
									<br />
									专业排名
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="sxnzycjzypm" />
								</div>
							</td>
							<td width="17%">
								<div align="center">
									上学年德育测评
									<br />
									班级排名
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="sxndycpbjpm" />
								</div>
							</td>
							<td width="17%">
								<div align="center">
									上学年有无
									<br />
									违纪处分
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="sxnywwjcf" />
								</div>
							</td>
							<td width="17%">
								<div align="center">
									上学年不及
									<br />
									格课程数
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="sxnbjgkcs" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									曾获何种奖励
									<br />
									和资助情况
								</div>
							</td>
							<td colspan="7">
								<bean:write name="rs" property="chhzjlhzzqk" />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="3" width="4%">
								<div align="center">
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="20%">
								<div align="center">
									家庭户口和
									<br />
									家庭类型
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<logic:empty name="rs" property="xh">
										城镇&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;农村&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;双亲&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;单亲&nbsp;□
										<br />
										孤儿&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;离异&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;烈属&nbsp;□&nbsp;&nbsp;&nbsp;(打"√")
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="jthk" value="城镇">
											城镇&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;农村&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jthk" value="农村">
											城镇&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;农村&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="双亲">
											双亲&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;单亲&nbsp;□
											<br />
											孤儿&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="单亲">
											双亲&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;单亲&nbsp;√
											<br />
											孤儿&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="jtlx" value="孤儿">
											双亲&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;单亲&nbsp;□
											<br />
											孤儿&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfly" value="是">
											离异&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfly" value="否">
											离异&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfls" value="是">
											烈属&nbsp;√&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										<logic:equal name="rs" property="sfls" value="否">
											烈属&nbsp;□&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:equal>
										(打"√")
									</logic:notEmpty>
								</div>
							</td>
							<td width="14%">
								<div align="center">
									家庭总人口数
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="jtrkzs" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭经济
									<br />
									月总收入
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="jtyzsr" />
								</div>
							</td>
							<td width="14%">
								<div align="center">
									家庭人均
									<br />
									月收入
								</div>
							</td>
							<td width="22%">
								<div align="center">
									<bean:write name="rs" property="jtrjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									收入来源
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtzz" />
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
								<div align="center">
									家
									<br />
									庭
									<br />
									成
									<br />
									员
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									称谓
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									职业和工作（学习）单位
								</div>
							</td>
							<td>
								<div align="center">
									年收入
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy1_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy2_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy3_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy4_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy5_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								&nbsp;申请理由(附页)：
								<br />
								&nbsp;
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								&nbsp;&nbsp;&nbsp;&nbsp;
								国家励志奖学金是由中央和地方政府共同出资设立的，奖励资助品学兼优的家庭经济困难学生。本人承诺申请国家励志奖学金只用于学习、生活必需支出方面，不用做其他用途，保证做到自立自强、勤奋学习、生活俭朴。
								<br />
								&nbsp;
								<div align="right">
									承诺人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									班导师鉴
									<br />
									定意见
								</div>
							</td>
							<td colspan="5">
								&nbsp;学生家庭经济、学生学习和日常表现等情况：
								<br />
								<logic:empty name="rs" property="bdsyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="bdsyj">
									<bean:write name="rs" property="bdsyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									班导师签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									分院审
									<br />
									核意见
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name="rs" property="xyshyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校审
									<br />
									核意见
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<br />
								&nbsp;
								<div align="right">
									负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div style='page-break-before:always;'>&nbsp;</div>
		<table width="100%" border="0" id="theTable2">
			<tr>
				<td>
					&nbsp;申请理由（附页）：
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<bean:write name="rs" property="sqly" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTabT('theTable','theTable2','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
