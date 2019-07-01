<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">
		<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="求真学院" property="xymc" name="rs">${rs.xymc }</logic:equal>家庭经济困难生认定申请表
					</span>
					</b>
					<br/><br/><br/>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<tr>
				<td width="5%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="7%"></td>
				<td width="3%"></td>
				<td width="3%"></td>
				<td width="6%"></td>
				<td width="8%"></td>
				<td width="3%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
			</tr>
			<!-- 第一行 -->
			<tr style="height:20px">
				<td rowspan="4" align="center">
						<b>
						学<br>
						生<br>
						本<br>
						人<br>
						基<br>
						本<br>
						情<br>
						况
						</b>
				</td>
				<td align="center"  colspan="2">
						姓&nbsp;&nbsp;名
				</td>
				<td  align="center" colspan="3">
						${rs.xm }
				</td>
				<td align="center">
						性&nbsp;&nbsp;别
				</td>
				<td align="center" colspan="2">
						${rs.xb }
				</td>
				<td align="center" colspan="2">
						出生年月
				</td>
				<td colspan="2" align="center" >
						${rs.csrq }
				</td>
				<td align="center" >
						民&nbsp;&nbsp;族
				</td>
				<td align="center" >
						${rs.mzmc }
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						身份证号码
				</td>
				<td colspan="4" align="center">
						${rs.sfzh }
				</td>
				<td align="center"colspan="2">
						政治面貌
				</td>
				<td colspan="2" align="center">
						${rs.zzmmmc }
				</td>
				<td align="center" colspan="2">
						家庭人均<br/>年收入	
				</td>
				<td align="center" colspan="2">
					${rs.jtrjsr }元
				</td>
			</tr>
			<!-- 第三行 -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						学&nbsp;&nbsp;院	
				</td>
				<td colspan="3" align="center">
						${rs.xymc }
				</td>
				<td align="center">
						系				
				</td>
				<td colspan="4" align="center">
						${rs.xymc }
				</td>
				<td align="center">
						专&nbsp;&nbsp;业
				</td>
				<td align="center" colspan="3">
						${rs.zymc }
				</td>
			</tr>
			<!-- 第四行 -->
			<tr style="height:20px">
				<td align="center" colspan="2">
						年&nbsp;&nbsp;级
				</td>
				<td align="center">
						${rs.nj }		
				</td>
				<td align="center">
						班
				</td>
				<td align="center" colspan="2">
						${rs.bjmc }
				</td>
				<td align="center" colspan="3">
						在校联系电话
				</td>
				<td align="center" colspan="5">
						${rs.sjhm }
				</td>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<!-- 第一行 -->
			<tr style="height:130px">
				<td align="center">
					<b>
						
						学<br/>
						生<br/>
						陈<br/>
						述<br/>
						申<br/>
						请<br/>
						认<br/>
						定<br/>
						理<br/>
						由
					</b>
				</td>
				<td colspan="14" align="left">
					<p style="height:120px">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
						</span>
					</p>
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							&nbsp;本人保证以上所填情况真实有效。
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							学生签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							年<u>&nbsp;&nbsp;&nbsp;</u>
							月<u>&nbsp;&nbsp;&nbsp;</u>
							日&nbsp;&nbsp;
						</span>
					</div>
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>注：可另附详细情况说明。</b>
						</span>
					</div>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="center" rowspan="3">
					<b>
						民<br/>
						主<br/>
						评<br/>
						议
					</b>
				</td>
				<td align="center" rowspan="3">
						推<br/>
						荐<br/>
						档<br/>
						次
				</td>
				<td align="left" colspan="4">
						A. 家庭经济特殊困难□
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="一般困难" property="knjb" name="rs">
								□
							</logic:equal>
							<logic:equal value="特殊困难" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								□
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							□
						</logic:notPresent>
						 --%>
				</td>
				<td align="center" rowspan="3">
						陈<br/>
						述<br/>
						理<br/>
						由
				</td>
				<td align="left" rowspan="3" colspan="8">
					<p style="height:110px">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</p>
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							评议小组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							年<u>&nbsp;&nbsp;&nbsp;</u>
							月<u>&nbsp;&nbsp;&nbsp;</u>
							日&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="left" colspan="4">
						B. 家庭经济一般困难□
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="一般困难" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
							<logic:equal value="特殊困难" property="knjb" name="rs">
								□
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								□
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							□
						</logic:notPresent>
						 --%>
				</td>
			</tr>
			<tr style="height:40px">
				<td align="left" colspan="4">
						C.家庭经济不困难□
						<%--  
						<logic:present name="rs" property="knjb">
							<logic:equal value="一般困难" property="knjb" name="rs">
								□
							</logic:equal>
							<logic:equal value="特殊困难" property="knjb" name="rs">
								□
							</logic:equal>
							<logic:equal value="" property="knjb" name="rs">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="knjb">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						</logic:notPresent>
						 --%>
				</td>
			</tr>
			<tr style="height:120px">
				<td align="center">
					<b>
						认<br/>
						定<br/>
						决<br/>
						定
					</b>
				</td>
				<td align="center">
						<logic:equal value="求真学院" property="xymc" name="rs">系（部）</logic:equal>
						<logic:notEqual value="求真学院" property="xymc" name="rs">院（系）</logic:notEqual>
						<br/>
						意见
				</td>
				<td align="left" colspan="5">
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							经评议小组推荐、本<logic:equal value="求真学院" property="xymc" name="rs">系（部）</logic:equal>
							<logic:notEqual value="求真学院" property="xymc" name="rs">院（系）</logic:notEqual>认真审核后，<br/>
							□&nbsp;&nbsp;同意评议小组意见。<br/>
							□&nbsp;&nbsp;不同意评议小组意见。调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。<br/><br/>
							工作组组长签字：
						</span>
					</div>
					<br/><br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							年<u>&nbsp;&nbsp;&nbsp;</u>
							月<u>&nbsp;&nbsp;&nbsp;</u>
							日&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/><br/><br/>
				</td>
				<td align="center">
						学校学<br/>
						生资助<br/>
						管理机<br/>
						构意见
				</td>
				<td align="left" colspan="7">
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						经学生所在院（系）提请，本机构认真核实，<br/>
						□  同意工作组和评议小组意见。<br/>
						□  不同意工作组和评议小组意见。调整为：<br/><br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
						<br/><br/>
						负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							年<u>&nbsp;&nbsp;&nbsp;</u>
							月<u>&nbsp;&nbsp;&nbsp;</u>
							日&nbsp;&nbsp;
						</span>
					</div>
					<br/><br/>
					<div align="right">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							（加盖部门公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<div align="left">
			<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
			备注：此表一式2份，一份<logic:equal value="求真学院" property="xymc" name="rs">系（部）</logic:equal><logic:notEqual value="求真学院" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>留存，一份学校留存（可复印）。
			</span>
		</div>
		</td>
		</tr>
		</table>
		<br>
		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
		</html:form>
	</body>
</html>
