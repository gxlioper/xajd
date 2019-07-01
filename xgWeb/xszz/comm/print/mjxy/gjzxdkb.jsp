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
		<div align="center" style="font-size:28px;font:'黑体' ">
			<b>${xxmc }家庭经济困难学生申请国家助学贷款审核表</b>
		</div>
		<br>
		<div align="left" style="font-size:15px;">
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<!-- 第一行 -->
			<tr style="height:22px">
				<th width="10%">
					<div align="center">
						姓 名
					</div>
				</th>
				<th width="20%">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						院(系)
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.xymc }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr>
				<th width="">
					<div align="center">
						年级
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.nj }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						专业
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.zymc }
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr>
				<th width="">
					<div align="center">
						家庭住址(通讯地址)
					</div>
				</th>
				<th width="" colspan="5">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr>
				<th width="">
					<div align="center">
						家长联系地址(固定电话)
					</div>
				</th>
				<th width="" colspan="5">
					<div align="left">
						<logic:notEmpty name="cyList">
							<logic:iterate name="cyList" id="cy">
								<logic:notEmpty name="cy" property="mc">
								${cy.mc }:${cy.cygzdw }（${cy.cydh }）<br>
								</logic:notEmpty>
							</logic:iterate>
						</logic:notEmpty>
					</div>
				</th>
			</tr>
			<!-- 学生陈述申请认定理由 -->
			<tr style="height:150px">
				<th width="5%">
					学生本<br>
					人申请<br>
					国家助<br>
					学贷款<br>
					并对家<br>
					庭经济<br>
					困难情<br>
					况进行<br>
					说明
				</th>
				<th colspan="5" align="left">
					<p style="height: 120px">
					本人因家庭经济困难。无法交纳上学所需的用费。特向银行申请国家助学贷款。<br>
					家庭经济困难具体情况如下:${rs.sqsm }
					</p>
					<div align="right">
					学生本人签名并盖手印:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>
				</th>
			</tr>
			<!-- 家长是否同意孩子在校申请国家助学贷款 -->
			<tr style="height:150px">
				<th width="5%">
					家长是<br>
					否同意<br>
					孩子在<br>
					校申请<br>
					国家助<br>
					学贷款
				</th>
				<th colspan="5" align="left">
					<p style="height: 100px">
					因家庭经济困难，我同意我的孩子申请国家助学贷款。并督促孩子遵守合同规定，按时还款。
					</p>
					<div align="right">
					家长签名并盖手印:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>
				</th>
			</tr>
			<!-- 认定决定 -->
			<tr>
				<th width="5%">
					<div align="center">
						村民委<br>
						员会或<br>
						街道居<br>
						委会<br>
						意见:
					</div>
				</th>
				<th width="" colspan="3">			
					<p style="height: 100px;" align="left">
					该生家庭困难情况是否属实?
					</p>
					<div align="right">
					盖公章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>		
				</th>
				<th width="10%">
					<div align="center">
						乡(镇)人<br>
						民政府<br>
						或地级<br>
						市街道<br>
						办事处<br>
						意见:
					</div>
				</th>
				<th width="" colspan="">
					<p style="height: 100px;" align="left">
					该生家庭困难情况是否属实?
					</p>
					<div align="right">
					盖公章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>		
				</th>
			</tr>
			<!-- 认定决定 -->
			<tr>
				<th width="5%">
					<div align="center">
						院(系) <br>
						推荐意见
					</div>
				</th>
				<th width="" colspan="5">			
					<p style="height: 120px;" align="left">
					该生于<u>${rs.rxrq }</u>年入学.
					在<u>${rs.xymc }</u>系院(全称)
					<u>${rs.zymc }</u>专业(全称)学习，<br>
					学制<u>${rs.xz }</u>年，
					经审核，同意推荐其申请国家助学贷款，<br>
					总金额为<u>${rs.zje }</u> 元(大写) ，<br>
					其中每学年申请贷款<u>${rs.xnje }</u>元(大写)。<br>
					</p>
					<div align="right">
					院 (系)资助工作组组长签字并盖系(院)公章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
					年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
					</div>		
				</th>
			</tr>		
		</table>
		<div align="left">
		备注:1、以上信息用黑色钢笔或水笔填写。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		2、请附父母身份证正反面复印件（没有身份证附户口本复印件，有户口章的页面以及父母单页都要复印）。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
		3、传真件无效。

		</div>
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
