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
		<table width="87%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xxmc }<logic:equal value="求真学院" property="xymc" name="rs">${rs.xymc }</logic:equal>学生及家庭情况调查表
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>学校：</b><u>${rs.xxmc }</u>
						<b><logic:equal value="求真学院" property="xymc" name="rs">系</logic:equal>
						<logic:notEqual value="求真学院" property="xymc" name="rs">院（系）</logic:notEqual>
						：</b><u>${rs.xymc }</u>
						<b>专业：</b><u>${rs.zymc }</u>
						<b>年级：</b><u>${rs.nj }</u>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<tr>
				<td width="7%"></td>
				<td width="7"></td>
				<td width="5"></td>
				<td width="2"></td>
				<td width="3"></td>
				<td width="7"></td>
				<td width="5"></td>
				<td width="5"></td>
				<td width="6"></td>
				<td width="5"></td>
				<td width="5"></td>
				<td width=""></td>
				<td width="5"></td>
				<td width=""></td>
			</tr>
			<!-- 第一行 -->
			<tr style="height:30px">
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
				<td align="center">
						姓 名
				</td>
				<td  align="center" colspan="3">
						${rs.xm }
				</td>
				<td align="center">
						性别
				</td>
				<td align="center">
						${rs.xb }
				</td>
				<td align="center" colspan="2">
						出生年月
				</td>
				<td colspan="2" align="center" >
						${rs.csrq }
				</td>
				<td colspan="2" align="center" >
						民族
				</td>
				<td align="center">
						${rs.mzmc }
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:30px">
				<td width="" align="center">
						身份证<br/>号码
				</td>
				<td colspan="4" align="center">
						${rs.sfzh }
				</td>
				<td align="center">
						政治<br/>面貌
				</td>
				<td colspan="2" align="center">
						${rs.zzmmmc }
				</td>
				<td align="center" colspan="2">
						入学前<br/>户口	
				</td>
				<td align="center" colspan="3">
						<logic:empty name="rs" property="jthk">
							□城镇&nbsp;&nbsp;
							□农村
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="城镇">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							城镇&nbsp;&nbsp;
							□农村
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="农村">
							□城镇&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							农村
						</logic:equal>	
				</td>
			</tr>
			<!-- 第三行 -->
			<tr style="height:30px">
				<td align="center">
						家庭人<br/>口数		
				</td>
				<td colspan="4" align="center">
						${rs.jtrs }
				</td>
				<td align="center">
						毕业<br/>学校					
				</td>
				<td colspan="2" align="center">
						${rs.rxqdw }
				</td>
				<td align="center" colspan="2">
						个人<br/>特长
				</td>
				<td align="center" colspan="3">
						${rs.tc }
				</td>
			</tr>
			<!-- 第四行 -->
			<tr style="height:30px">
				<td align="center">
						孤 残
				</td>
				<td align="center" colspan="2">
						<logic:empty name="rs" property="sfgc">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="sfgc" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="sfgc" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>				
				</td>
				<td align="center" colspan="2">
						单 亲
				</td>
				<td align="center" colspan="3">
						<logic:empty name="rs" property="sfdq">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="sfdq" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="sfdq" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>		
				</td>
				<td align="center" colspan="2">
						烈士子女
				</td>
				<td align="center" colspan="3">
						<logic:empty name="rs" property="lszn">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="lszn" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="lszn" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>			
				</td>
			</tr>
			<!-- 家庭通讯信息 -->
			<!-- 第一行 -->
			<tr style="height:30px">
				<td rowspan="2" align="center">
					<b>
						家庭<br>
						通讯<br>
						信息
					</b>
				</td>
				<td align="center" colspan="2">
						详细通讯地址
				</td>
				<td colspan="11" align="center">
						${rs.jtdz }
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:30px">
				<td align="center" colspan="2">
						邮政编码
				</td>
				<td align="center" colspan="3">
						${rs.jtyb }
				</td>
				<td align="center" colspan="2">
						联系电话
				</td>
				<td colspan="6" align="center" >
						${rs.jtdh }
				</td>
			</tr>
			<!-- 家庭成员情况 -->
			<tr style="height:30px">
				<td rowspan="${cyNum+1 }"  align="center">
					<b>
						家<br>
						庭<br>
						成<br>
						员<br>
						情<br>
						况
					</b>
				</td>
				<td align="center">
						姓名
				</td>
				<td align="center">
						年龄
				</td>
				<td align="center" colspan="2">
						与学生<br>关系
				</td>
				<td align="center" colspan="4">
						工作（学习）单位
				</td>
				<td align="center">
						职业
				</td>
				<td align="center" colspan="2">
						年收入<br>（元）
				</td>
				<td align="center" colspan="2">
						健康状况
				</td>
				</tr>
				<logic:iterate name="cyList" id="cy">
				<tr style="height:30px">
					<td align="center" >
							${cy.cyxm }&nbsp;&nbsp;
					</td>
					<td align="center">
							${cy.cynl }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.mc }&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							${cy.cygzdw }&nbsp;&nbsp;
					</td>
					<td align="center">
							${cy.cyzy }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.cynsr }&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							${cy.cyjkzk }&nbsp;&nbsp;
					</td>
				</tr>
			</logic:iterate>
			<!-- 相关信息 -->
			<tr style="height:150px">
				<td align="center">
					<b>
					影响<br>
					家庭<br>
					经济<br>
					状况<br>
					有关<br>
					信息
					</b>
				</td>
				<td colspan="13" align="left">
					<br/>
					家庭人均年收入:<logic:notEqual value="" property="jtrjsr" name="rs"><u>&nbsp;${rs.jtrjsr }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtrjsr" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>（元）。学生本学年已获资助情况:
					<logic:notEqual value="" property="yhzzqk" name="rs"><u>&nbsp;${rs.yhzzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="yhzzqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					家庭遭受自然灾害情况：<logic:notEqual value="" property="jtszqk" name="rs"><u>&nbsp;${rs.jtszqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtszqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。家庭遭受突发意外事件：<logic:notEqual value="" property="tfsjqk" name="rs"><u>&nbsp;${rs.tfsjqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="tfsjqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					家庭成员因残疾、年迈而劳动能力弱情况：<logic:notEqual value="" property="cjnmqk" name="rs"><u>&nbsp;${rs.cjnmqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="cjnmqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					家庭成员失业情况：<logic:notEqual value="" property="jtsyqk" name="rs"><u>&nbsp;${rs.jtsyqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtsyqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。家庭欠债情况：<logic:notEqual value="" property="jtqzqk" name="rs"><u>&nbsp;${rs.jtqzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqzqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					其他情况：<logic:notEqual value="" property="jtqtqk" name="rs"><u>&nbsp;${rs.jtqtqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqtqk" name="rs"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。
					<br/><br/>	
				</td>
			</tr>
			<!-- 相关信息 -->
			<tr style="height:120px">
				<td align="center">
					<b>
					签<br>
					章<br>
					</b>
				</td>
				<td align="center">
					学<br>
					生<br>
					本<br>
					人<br>
				</td>
				<td >

				</td>
				<td align="center">
					学生<br>
					家长<br>
					或监<br>
					护人
				</td>
				<td colspan="2">

				</td>
				<td align="center" colspan="2">
					学生家庭<br>
					所在地乡<br>
					镇或街道<br>
					民政部门
				</td>
				<td colspan="6">	
					<div align="left">
						<br>
						经办人签字：
						<br>
						<br>
						单位名称：
						<br>
					</div>
					<div align="right">
						（加盖公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
						         <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<!-- 民政部门信息 -->
			<tr style="height:30px">
				<td rowspan="2" align="center">
					<b>
					民政<br>
					部门<br>
					信息<br>
					</b>
				</td>
				<td colspan="2" align="center">
					详细通讯地址
				</td>
				<td colspan="11">
					${rs.mzbmtxdz }
				</td>
			</tr>
			<tr style="height:30px" align="center">
				<td colspan="2" align="center">
					邮政编码
				</td>
				<td colspan="3" align="center">
					${rs.mzbmyzbm }
				</td>
				<td colspan="2" align="center">
					联系电话
				</td>
				<td colspan="6" align="center">
					${rs.mzbmlxdh }
				</td>
			</tr>
		</table>
		<br/>
		<div align="left">
			<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
			备注：此表一式2份，一份<logic:equal value="求真学院" property="xymc" name="rs">各系（部）</logic:equal><logic:notEqual value="求真学院" property="xymc" name="rs"><bean:message key="lable.xb" /></logic:notEqual>留存，一份学校留存（可复印）。
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
