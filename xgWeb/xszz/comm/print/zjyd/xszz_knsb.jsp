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
		<div align="center" style="font-size:28px;font:'黑体' "><b>家庭经济困难学生认定申请表</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>学校：  浙江邮电职业技术学院</b> 
		</div>
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<!-- 第一行 -->
			<tr style="height:45px">
				<th width="5%" rowspan="4">
					<div align="center">
						学<br>生<br>
						本<br>人<br>
						基<br>本<br>
						情<br>况
					</div>
				</th>
				<th width="8%">
					<div align="center">
						姓 名
					</div>
				</th>
				<th width="12%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="6%">
					<div align="center">
						性别
					</div>
				</th>
				<th width="7%" colspan="2">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="6%">
					<div align="center">
						出生<br/>
						年月
					</div>
				</th>
				<th width="15%" colspan="2">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						民族
					</div>
				</th>
				<th width="8%">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						身份证<br/>
						号码
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th  colspan="2">
					<div align="center">
						政治<br/>
						面貌
					</div>
				</th>
				<th width="12%" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="9%">
					<div align="center">
						家庭人均<br/>年收入
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						<logic:empty name="rs" property="jtrjsr">
						&nbsp;&nbsp;&nbsp;&nbsp;元
						</logic:empty>
						<logic:notEmpty name="rs" property="jtrjsr">
						${rs.jtrjsr }元
						</logic:notEmpty>
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						系  别
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${xymc }
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						班 级
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.bjmc}
					</div>
				</th>
				<th width="10%">
					<div align="center">
						专 业
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr style="height:45px">
				<th width="8%">
					<div align="center">
						在校联<br/>系电话
					</div>
				</th>
				<th width="10%" colspan="9">
					<div align="center">
						${rs.lxdh } 
					</div>
				</th>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<tr style="height:180px">
				<th width="5%">
					学<br/>生<br/>
					陈<br/>述<br/>
					申<br/>请<br/>
					认<br/>定<br/>
					理<br/>由
				</th>
				<th colspan="10" align="left">
					<p style="height: 190px">
					
					${rs.sqly }
					</p>
					<div align="right">
					学生签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					年<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日
					</div>
					<div align="left">
					（注：可另附详细情况说明。）
					</div>
				</th>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<tr>
				<th width="5%" rowspan="${fjNum }">
					<div align="center">
						民<br><br>
						主<br><br>
						评<br><br>
						议
					</div>
				</th>
				<th width="8%" rowspan="${fjNum }">
					<div align="center">
						推<br>
						荐<br>
						档<br>
						次
					</div>
				</th>
				<th width="12%">
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
				<th width="6%" rowspan="${fjNum }" >
					<div align="center">
						陈<br>
						述<br>
						理<br>
						由
					</div>
				</th>
				<th width="" colspan="7" rowspan="${fjNum }">
					<p style="height: 170px;" align="left">
				
					</p>
					<div align="right">
					评议小组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					年<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					月<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					日
					</div>
				</th>
			</tr>
			<logic:iterate name="xmfjqkList" id="fj" indexId="num">
				<logic:notEqual name="num" value="0">
					<tr>
						<th width="10%" colspan="1">
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
				<th width="8%">
					<div align="center">
						院（系）<br>
						<br>
						意见<br>
					</div>
				</th>
				<th width="" colspan="2">
					<p style="height: 170;" align="left">
					经评议小组推荐、本院（系）认真审核后，<br>
					□  同意评议小组意见。<br>
					□  不同意评议小组意见。调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。 
					<br>
					</p>
					<div align="left">
					工作组组长签字：
					</div>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					年<u>&nbsp;&nbsp;</u>
					月<u>&nbsp;&nbsp;</u>
					日
					</div>					
				</th>
				<th width="5%">
					<div align="center">
						学<br/>
						校<br/>学<br/>
						生<br/>资<br/>
						助<br/>管<br/>
						理<br/>机<br/>
						构<br/>意<br/>
						见<br/>
					</div>
				</th>
				<th width="" colspan="6">
					<p style="height: 100px;vertical-align: top" align="left" >
					经学生所在院（系）提请，本机构认真核实，<br>
					□  同意工作组和评议小组意见。<br>
					□  不同意工作组和评议小组意见。调整为<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。 
					</p>
					<div align="left">
					负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<br/>
					<div align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					年<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					月<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
					日
					</div>		        
					<div align="left">
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
