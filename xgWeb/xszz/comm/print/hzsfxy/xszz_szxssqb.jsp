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
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
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
		<table width="83%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/><br/>
						<logic:equal value="社会爱心助学" property="xmmc" name="rs"><b><span style='font-size:20pt;font-family:黑体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>湖 州 师 范 学 院 “ 社 会 爱 心 助 学 ”<br/>受 助 学 生 申 请 表</span></b></logic:equal>
						<logic:equal value="永兴特钢助学金" property="xmmc" name="rs"><b><span style='font-size:16pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>“永 兴 特 钢 助 学 金 ”受 助 学 生 申 请 表</span></b></logic:equal>
					<br/><br/><br/>
				</td>
			</tr>
			<tr>
				<td align="center">
				<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					<b>初次申请<logic:equal value="初次申请" property="sfzcsq" name="rs"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal><logic:notEqual value="初次申请" property="sfzcsq" name="rs">□</logic:notEqual></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>再次申请<logic:equal value="再次申请" property="sfzcsq" name="rs"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal><logic:notEqual value="再次申请" property="sfzcsq" name="rs">□</logic:notEqual></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>编号<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></b><br/>
				</span>
		<!-- 个人资料 -->
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="8%"></td>
				<td width="16%"></td>
				<td width="6%"></td>
				<td width="4%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
				<td width="2%"></td>
				<td width="5%"></td>
				<td width="16%"></td>
			</tr>
			<!-- 第一行 -->
			<tr style="height:21px">
				<td align="center" colspan="9">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>个&nbsp;&nbsp;人&nbsp;&nbsp;资&nbsp;&nbsp;料</b>
					</span>
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						姓&nbsp;&nbsp;&nbsp;&nbsp;名
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xm }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						性别
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xb }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<bean:message key="lable.xb" />
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xymc }
					</span>
				</td>
			</tr>
			<!-- 第三行 -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						班级学号
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.xh }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						受助情况
					</span>
				</td>
				<td align="center" colspan="5">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.yhzzqk }
					</span>
				</td>
			</tr>
			<!-- 第四行 -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						贫困程度
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.knjb }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						联系电话
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.sjhm }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						民族
					</span>
				</td>
				<td  align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${rs.mzmc }
					</span>
				</td>
			</tr>
		</table>
				</td>
			</tr>
			<tr style="height:21px">
				<td align="center">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
		<!-- 家庭资料 -->
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td width="8%"></td>
				<td width="6%"></td>
				<td width="20%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
				<td width="3%"></td>
				<td width="8%"></td>
				<td width="15%"></td>
			</tr>
			<!-- 家庭资料第一行 -->
			<tr style="height:21px">
				<td align="center" colspan="8">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<b>家&nbsp;&nbsp;庭&nbsp;&nbsp;资&nbsp;&nbsp;料</b>
					</span>
				</td>
			</tr>
			<!-- 家庭资料第二行 -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							原&nbsp;籍
					</span>
				</td>
				<td align="left" colspan="7">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jg }
					</span>
				</td>
			</tr>
			<!-- 家庭资料第三行 -->
			<tr style="height:21px">
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:equal value="社会爱心助学" property="xmmc" name="rs">地&nbsp;址</logic:equal>
						<logic:equal value="永兴特钢助学金" property="xmmc" name="rs">通讯地址</logic:equal>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtdz }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							邮编
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtyb }
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							电话
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtdh }
					</span>
				</td>
			</tr>
			<!-- 家庭资料第四行 -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							是否贫困县
					</span>
				</td>
				<td align="center" colspan="6">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:present name="rs" property="sfpkx">
							<logic:equal value="否" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>否&nbsp;&nbsp;&nbsp;□是&nbsp;&nbsp;&nbsp;（□国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
								</span>
							</logic:equal>
							<logic:equal value="是" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									□否&nbsp;&nbsp;&nbsp;<img src="/xgxt/pictures/xszz/gou.jpg"></img>是&nbsp;&nbsp;&nbsp;
									
										<logic:present name="rs" property="pkxjb">
										<logic:equal value="国家级贫困县" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												（<img src="/xgxt/pictures/xszz/gou.jpg"></img>国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
											</span>
										</logic:equal>
										<logic:equal value="省级贫困县" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												（□国家级贫困县&nbsp;&nbsp;&nbsp;<img src="/xgxt/pictures/xszz/gou.jpg"></img>省级贫困县）
											</span>
										</logic:equal>
										<logic:equal value="" property="pkxjb" name="rs">
											<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
												（□国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
											</span>
										</logic:equal>
									</logic:present>
									<logic:notPresent name="rs" property="pkxjb">
										<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
											（□国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
										</span>
									</logic:notPresent>
									
									
								</span>
							</logic:equal>
							<logic:equal value="" property="sfpkx" name="rs">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									□否&nbsp;&nbsp;&nbsp;□是&nbsp;&nbsp;&nbsp;（□国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
								</span>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="sfpkx">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								□否&nbsp;&nbsp;&nbsp;□是&nbsp;&nbsp;&nbsp;（□国家级贫困县&nbsp;&nbsp;&nbsp;□省级贫困县）
							</span>
						</logic:notPresent>
						
					</span>
				</td>
			</tr>
			<!-- 家庭资料第五行 -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							上年家庭收入
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.snjtsr }
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							家庭成员数
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							${rs.jtrs }
					</span>
				</td>
			</tr>
			<!-- 家庭资料第六行 -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							父&nbsp;亲&nbsp;职&nbsp;业
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:iterate name="cyList" id="cy">
							<logic:equal value="父亲" name="cy" property="mc">
								${cy.cyzy }
							</logic:equal>
						</logic:iterate>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							母亲职业
					</span>
				</td>
				<td align="center" colspan="3">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:iterate name="cyList" id="cy">
								<logic:equal value="母亲" name="cy" property="mc">
									${cy.cyzy }
								</logic:equal>
						</logic:iterate>
					</span>
				</td>
			</tr>
			<!-- 家庭资料第七行 -->
			<tr style="height:45px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							家中是否有欠债
					</span>
				</td>
				<td align="center">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<logic:present name="rs" property="sfqz">
								<logic:equal value="是" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										有<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;无□
									</span>
								</logic:equal>
								<logic:equal value="否" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										有□&nbsp;&nbsp;/&nbsp;&nbsp;无<img src="/xgxt/pictures/xszz/gou.jpg"></img>
									</span>
								</logic:equal>
								<logic:equal value="" property="sfqz" name="rs">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										有□&nbsp;&nbsp;/&nbsp;&nbsp;无□
									</span>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="rs" property="sfqz">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									有□&nbsp;&nbsp;/&nbsp;&nbsp;无□
								</span>
							</logic:notPresent>
					</span>
				</td>
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							父母是否有病<br/>或残疾
					</span>
				</td>
				<td align="center" colspan="3">
					<logic:present name="rs" property="fmjk">
						<logic:equal value="父亲有" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲有<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/母亲有□<br/>/无□
							</span>
						</logic:equal>
						<logic:equal value="母亲有" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲有□&nbsp;&nbsp;/母亲有<img src="/xgxt/pictures/xszz/gou.jpg"></img><br/>/无□
							</span>
						</logic:equal>
						<logic:equal value="父母皆有" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲有<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/母亲有<img src="/xgxt/pictures/xszz/gou.jpg"></img><br/>/无□
							</span>
						</logic:equal>
						<logic:equal value="无" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲有□&nbsp;&nbsp;/母亲有□<br/>/无<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</span>
						</logic:equal>
						<logic:equal value="" property="fmjk" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲有□&nbsp;&nbsp;/母亲有□<br/>/无□
							</span>
						</logic:equal>
					</logic:present>
					<logic:notPresent name="rs" property="fmjk">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							父亲有□&nbsp;&nbsp;/母亲有□<br/>/无□
						</span>
					</logic:notPresent>
					
				</td>
			</tr>
			<!-- 家庭资料第八行 -->
			<tr style="height:21px">
				<td align="center" colspan="2">
					<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							父母是否健在
					</span>
				</td>
				<td align="left" colspan="6">
					<logic:present name="rs" property="fmjz">
						<logic:equal value="父母双全" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父母双全<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡□&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在□&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡□
							</span>
						</logic:equal>
						<logic:equal value="父母双亡" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父母双全□&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在□&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡□
							</span>
						</logic:equal>
						<logic:equal value="父母双亡" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父母双全□&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡□&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在<img src="/xgxt/pictures/xszz/gou.jpg"></img>&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡□
							</span>
						</logic:equal>
						<logic:equal value="父在母亡" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父母双全□&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡□&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在□&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</span>
						</logic:equal>
						<logic:equal value="" property="fmjz" name="rs">
							<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父母双全□&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡□&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在□&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡□
							</span>
						</logic:equal>
					</logic:present>
					<logic:notPresent name="rs" property="fmjz">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							父母双全□&nbsp;&nbsp;/&nbsp;&nbsp;父母双亡□&nbsp;&nbsp;/&nbsp;&nbsp;父亡母在□&nbsp;&nbsp;/&nbsp;&nbsp;父在母亡□
						</span>
					</logic:notPresent>
				</td>
			</tr>
		</table>
				</td>
			</tr>
			<tr style="height:10px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
				<logic:equal value="社会爱心助学" property="xmmc" name="rs">
		<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申&nbsp;&nbsp;请&nbsp;&nbsp;理&nbsp;&nbsp;由</b>
								</span>
							</td>
						</tr>
						<tr height="280px" align="center">
							<td>
								<p style="height:270px" align="left">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									本人签字<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/><br/>
									日&nbsp;&nbsp;&nbsp;&nbsp;期<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					</logic:equal>
					<logic:equal value="永兴特钢助学金" property="xmmc" name="rs">
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申&nbsp;&nbsp;请&nbsp;&nbsp;理&nbsp;&nbsp;由</b>
								</span>
							</td>
						</tr>
						<tr height="200px" align="center">
							<td>
								<p style="height:200px" align="left">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
							</td>
						</tr>
						<tr height="80px" align="left">
							<td>
								<p style="height:70px">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人承诺：<br/>
									1.本人提供的资料准确、真实。<br/>
									2.本人受助后将刻苦钻研、努力学习。<br/>
									3.本人愿意加入爱心奉献团队，并积极参加公益服务和实践活动。<br/>
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									本人签字<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/><br/>
									日&nbsp;&nbsp;&nbsp;&nbsp;期<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					</logic:equal>
				</td>
			</tr>
	</table>
		
		
		<!--分页-->
		<div class="PageNext"><br/></div>
		<br/>
		<table width="83%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学&nbsp;&nbsp;院&nbsp;&nbsp;意&nbsp;&nbsp;见</b>
								</span>
							</td>
						</tr>
						<tr height="220px" align="center">
							<td>
								<p style="height:210px" align="left">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<bean:message key="lable.xb" />（盖章）<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
								<div align="left">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;注：请注明该生的家庭经济困难生种类及在校的品学表现
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height:21px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学&nbsp;&nbsp;校&nbsp;&nbsp;意&nbsp;&nbsp;见</b>
								</span>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td>
								<p style="height:150px" align="left">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									学校（盖章）<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="height:35px">
				<td align="center">
				</td>
			</tr>
			<tr>
				<td align="center">
					<div align="left">
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;&nbsp;&nbsp;以下由资助人填写
						</span>
					</div>
					<table width="100%" class="printstyle">
						<tr height="21px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>审&nbsp;&nbsp;核&nbsp;&nbsp;意&nbsp;&nbsp;见</b>
								</span>
							</td>
						</tr>
						<tr height="170px" align="center">
							<td>
								<p style="height:160px">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									审核人签章<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br/><br/>
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</span>
								</div>
							</td>
						</tr>
					</table>
					<div align="left">
						<br/>
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>备注：</b>此表一式两份，一份学校留存，一份资助人留存（请正反面打印）。
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
