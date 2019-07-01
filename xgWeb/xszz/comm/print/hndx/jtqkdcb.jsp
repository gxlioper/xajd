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
		<div align="center" style="font-size:28px;font:'黑体' "><b>高等学校学生及家庭情况调查表</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>
		学校：<u>海南大学</u>
		院（系）：<u>${rs.xymc }</u>
		专业：<u>${rs.zymc }</u>
		年级：<u>${rs.nj}</u>
		</b>
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
						出生年月
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
			<tr style="height:22px">
				<th width="">
					<div align="center">
						身份证号码
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th width="20%">
					<div align="center">
						政治面貌
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="">
					<div align="center">
						入学前户口	
					</div>
				</th>
				<th width="">
					<div align="center">
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
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr style="height:22px">
				<th width="">
					<div align="center">
						家庭人口数		
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.jtrs }
					</div>
				</th>
				<th width="">
					<div align="center">
						毕业学校					
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.rxqdw }
					</div>
				</th>
				<th width="">
					<div align="center">
						个人特长
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr style="height:22px">
				<th width="">
					<div align="center">
						孤 残
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">					
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
					</div>
				</th>
				<th width="">
					<div align="center">
						单 亲
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
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
					</div>
				</th>
				<th width="">
					<div align="center">
						烈士子女
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
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
					</div>
				</th>				
			</tr>
			<!-- 家庭通讯信息 -->
			<!-- 第一行 -->
			<tr style="height:22px">
				<th width="5%" rowspan="2">
					<div align="center">
						家庭<br>
						通讯<br>
						信息
					</div>
				</th>
				<th width="10%" colspan="2">
					<div align="center">
						详细通讯地址
					</div>
				</th>
				<th width="" colspan="6">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr style="height:22px">
				<th width="10%" colspan="2">
					<div align="center">
						邮政编码
					</div>
				</th>
				<th width="">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th width="10%">
					<div align="center">
						联系电话
					</div>
				</th>
				<th width="" colspan="4">
					<div align="center">
						${rs.jtdh }
					</div>
				</th>				
			</tr>
			<!-- 家庭成员情况 -->
			<tr style="height:22px">
				<th width="5%" rowspan="${cyNum+1 }">
					<div align="center">
						家<br>
						庭<br>
						成<br>
						员<br>
						情<br>
						况
					</div>
				</th>
				<th width="10%">
					<div align="center">
						姓名
					</div>
				</th>
				<th width="10%">
					<div align="center">
						年龄
					</div>
				</th>
				<th width="10%">
					<div align="center">
						与学生<br/>关系
					</div>
				</th>
				<th width="10%">
					<div align="center">
						工作（学习）单位
					</div>
				</th>
				<th width="10%">
					<div align="center">
						职业
					</div>
				</th>
				<th width="10%">
					<div align="center">
						年收入（元）
					</div>
				</th>
				<th width="10%"  colspan="2">
					<div align="center">
						健康状况
					</div>
				</th>				
				</tr>
				<logic:iterate name="cyList" id="cy">
				</tr>
					<th width="10%">
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cyzy }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${cy.cynsr }&nbsp;&nbsp;
						</div>
					</th>
					<th width="10%" colspan="2">
						<div align="center">
							${cy.cyjkzk }&nbsp;&nbsp;
						</div>
					</th>
				</tr>
			</logic:iterate>
			<!-- 相关信息 -->
			<tr style="height:100px">
				<th width="5%">
					影响<br>
					家庭<br>
					经济<br>
					状况<br>
					有关<br>
					信息
				</th>
				<th colspan="8" align="left">
				家庭人均年收入:<u>${rs.jtrjsr }</u>（元）。学生本学年已获资助情况:<u>${rs.yhzzqk}</u>。<br>
				家庭遭受自然灾害情况：<u>${rs.jtszqk}</u>。家庭遭受突发意外事件：<u>${rs.tfsjqk}</u> 。<br>
				家庭成员因残疾、年迈而劳动能力弱情况：<u>${rs.cjnmqk}</u> 。<br>
				家庭成员失业情况：<u>${rs.jtsyqk}</u> 。家庭欠债情况：<u>${rs.jtqzqk}</u>。<br>
				其他情况：<u>${rs.jtqtqk}</u>。		
				</th>
			</tr>
			<!-- 相关信息 -->
			<tr style="height:100px">
				<th width="5%">
					签<br>
					章<br>
				</th>
				<th width="">
					学<br>
					生<br>
					本<br>
					人<br>
				</th>
				<th width="">

				</th>
				<th width="">
					学生<br>
					家长<br>
					或监<br>
					护人
				</th>
				<th width="">

				</th>
				<th width="" nowrap="nowrap">
					学生家庭所在地<br>
					乡镇政府、县级<br>
					以上民政部门、<br>
					国营农场总厂、<br>
					生产建设兵团团<br>
					部（含）以上单位
				</th>
				<th width="" colspan="3">	
					<div align="left">		
						经办人签字：
						<br>
						<br>
						单位名称：
						<br>
					</div>
					<div align="right">
						（加盖公章）<br>
						 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
					</div>
				</th>
			</tr>
			<!-- 民政部门信息 -->
			<tr style="height:22px">
				<th width="5%" rowspan="2">
					意见<br>
					部门<br>
					信息<br>
				</th>
				<th width="" colspan="2">
					详细通讯地址
				</th>
				<th width="" colspan="6">

				</th>
			</tr>
			<tr style="height:22px">
				<th width="" colspan="2">
					邮政编码
				</th>
				<th width="" colspan="2">

				</th>
				<th width="" colspan="2">
					联系电话
				</th>
				<th width="" colspan="2">

				</th>
			</tr>
		</table>
		<div align="left" style="font-size:14px;font:'黑体' ">
		<b>注：本表可复印使用。请如实填写，并到家庭所在地乡镇政府或县级以上民政部门、国营农场总厂、生产建设兵团团部（含）以上单位盖章后，交到学校。</b>
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
