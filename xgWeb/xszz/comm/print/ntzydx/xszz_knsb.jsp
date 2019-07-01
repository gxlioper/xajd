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
		<div align="center" style="font-size:28px;font:'黑体' "><b>高等学校家庭经济困难学生认定申请表</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<!-- 第一行 -->
			<tr style="height:22px">
				<th width="5%" rowspan="4">
					<div align="center">
						学<br>
						生<br>
						本<br>
						人<br>
						基<br>
						本<br>
						情<br>
						况
					</div>
				</th>
				<th width="10%">
					<div align="center">
						姓 名
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						性别
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						出生年月日
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						民族
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr>
				<th width="">
					<div align="center">
						身份证号码
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						党团员
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						家庭人均年收入
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.jtrjsr }
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr>
				<th width="">
					<div align="center">
						<bean:message key="lable.xb" />
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${xxmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						系
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.xymc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						专业
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr>
				<th width="">
					<div align="center">
						年级
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.nj }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						班级
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.bjmc }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						在校联系电话
					</div>
				</th>
				<th width="10%">
					<div align="center">
						${rs.lxdh } 
					</div>
				</th>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<tr style="height:200px">
				<th width="5%">
					学生<br>
					陈述<br>
					申请<br>
					认定<br>
					理由
				</th>
				<th colspan="8" align="left">
					<p style="height: 190px">					
					${rs.rdly }
					</p>
					<div align="right">
					学生签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>
					<div align="left">
					注：可另附详细情况说明。
					</div>
				</th>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<tr>
				<th width="5%" rowspan="${fjNum }">
					<div align="center">
						民<br>
						主<br>
						评<br>
						议
					</div>
				</th>
				<th width="" rowspan="${fjNum }">
					<div align="center">
						建<br>
						议<br>
						档<br>
						次
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						<logic:iterate name="xmfjqkList" id="fj" indexId="num">
							<logic:equal name="num" value="0">
								${fj.fjmc }
								<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									□
								</logic:notEqual>
								<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>
								</logic:equal>
							</logic:equal>				
						</logic:iterate>
					</div>
				</th>
				<th width="10%" rowspan="${fjNum }">
					<div align="center">
						陈<br>
						述<br>
						理<br>
						由
					</div>
				</th>
				<th width="" colspan="4" rowspan="${fjNum }">
					<p style="height: 190px;" align="left">
					应参加民主评议人数：____人，实际参加民主评议人数：____人。经民主评议，意见如下：
					</p>
					<div align="right">
					评议小组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日
					</div>
				</th>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<th width="10%" colspan="2">
							<div align="center">
								${fj.fjmc }
								<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									□
								</logic:notEqual>
								<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
									<img src="/xgxt/pictures/xszz/gou.jpg"></img>
								</logic:equal>
							</div>
						</th>
					</tr>
				</logic:notEqual>
			</logic:iterate>
			<!-- 认定决定 -->
			<tr>
				<th width="5%">
					<div align="center">
						认<br>
						定<br>
						决<br>
						定
					</div>
				</th>
				<th width="10%">
					<div align="center">
						院<br>
						（系）<br>
						意<br>
						见<br>
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 190px;" align="left">
					经评议小组推荐、本院（系）认真审核后，<br>
					□  同意评议小组意见。<br>
					□  不同意评议小组意见。调整为:<br>
					</p>
					<div align="left">
					工作组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日
					</div>					
				</th>
				<th width="10%">
					<div align="center">
						校学<br>
						生资<br>
						助管<br>
						理中<br>
						心意<br>
						见
					</div>
				</th>
				<th width="" colspan="3">
					<p style="height: 190px;" align="left">
					经学生所在院（系）提请，本机构认真核实，<br>
					□  同意工作组和评议小组意见。<br>
					□  不同意工作组和评议小组意见。调整为：<br>
					</p>
					<div align="left">
					负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日
					</div>		        
					<div align="right">
						（加盖部门公章）
					</div>
				</th>
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
