<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<html:html>
	<head>
		<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
		<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
	</head>
<body style="padding-right: 0mm">	
		<br/><br/><br/>
		<div align="center" style="font-size:22px;font:'黑体' "><b>高等学校学生及家庭情况调查表</b></div>
		<br/><br/>
		<p align="center" style="font-size:15px;">
		<b>
		学校：<u>${rs.xxmc }</u>
		院（系）：<u>${rs.xymc }</u>
		专业：<u>${rs.zymc }</u>
		年级：<u>${rs.nj }</u>
		</b>
		</p>
		<table class="printtab" style="font-family:仿宋_GB2312;font-size:15px;" width="100%">

			<!-- 学生本人基本情况 -->
			<!-- 第一行 -->
			<tr height="22px">
				<th width="10%" rowspan="4" >
					<div align="center">
					<font style="font-size:15px">
						学<br>
						生<br>
						本<br>
						人<br>
						基<br>
						本<br>
						情<br>
						况
					</font>
					</div>
				</th>
				<th width="10%">
					姓 名
				</th>
				<th width="20%" colspan="2">
					${rs.xm }
				</th>
				<th width="10%">
					性 别
				</th>
				<th width="12%">
					${rs.xb }
				</th>
				<th width="10%">
					出生年<br/>月
				</th>
				<th width="10%">
					${rs.csrq }
				</th>
				<th width="10%">
					民 族
				</th>
				<th width="10%">
					${rs.mzmc }
				</th>
			</tr>
			<!-- 第二行 -->
			<tr height="22px">
				<th>
					<div align="center">
						身份证<br/>号码
					</div>
				</th>
				<th colspan="3">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th>
					<div align="center">
						政治面<br/>貌
					</div>
				</th>
				<th>
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th>
					<div align="center">
						入学前<br/>户口	
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<logic:empty name="rs" property="jthk">
							□城镇&nbsp;
							□农村
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="城镇">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							城镇&nbsp;
							□农村
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="农村">
							□城镇&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							农村
						</logic:equal>	
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr height="22px">
				<th>
					<div align="center">
						家庭人<br/>口数		
					</div>
				</th>
				<th colspan="3">
					<div align="center">
						${rs.jtrs }
					</div>
				</th>
				<th>
					<div align="center">
						毕业<br/>学校					
					</div>
				</th>
				<th >
					<div align="center">
						${rs.rxqdw }
					</div>
				</th>
				<th >
					<div align="center">
						个人<br/>特长
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr height="22px">
				<th>
					<div align="center">
						孤 残
					</div>
				</th>
				<th colspan="2">
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
				<th >
					<div align="center">
						单 亲
					</div>
				</th>
				<th colspan="2">
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
				<th>
					<div align="center">
						烈士子女
					</div>
				</th>
				<th colspan="2">
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
			<tr height="22px">
				<th rowspan="2">
					<div align="center">
					<font style="font-size:15px">
						家庭<br>
						通讯<br>
						信息
					</font>
					</div>
				</th>
				<th colspan="2">
					<div align="center"  >
						详细通讯地址
					</div>
				</th>
				<th colspan="7">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr height="22px">
				<th colspan="2">
					<div align="center">
						邮政编码
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th>
					<div align="center">
						联系电话
					</div>
				</th>
				<th colspan="4">
					<div align="center">
						${rs.jtdh }
					</div>
				</th>
			</tr>
			
			<logic:lessEqual value="6" name="${cyNum}">
			<tr style="height:22px">
				<th rowspan="7">
					<div align="center">
					<font style="font-size:15px">
						家<br>
						庭<br>
						成<br>
						员<br>
						情<br>
						况
					</font>
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
				<th colspan="2">
					<div align="center">
						工作（学习）单位
					</div>
				</th>
				<th>
					<div align="center">
						职业
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						年收入（元）
					</div>
				</th>
				<th>
					<div align="center">
						健康<br/>状况
					</div>
				</th>
				</tr>
				
				<logic:iterate name="cyList" id="cy">
				<tr style="height:22px">
					<th>
						<div align="center">
							${cy.cyxm }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cynl }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.mc }
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cygzdw }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyzy }
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cynsr }
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyjkzk }
						</div>
					</th>
				</tr>
			</logic:iterate>
			<logic:iterate name="rs" id="cy" length="${6-cyNum}">
					<tr height="28px">
					<th>
						
					</th>
					<th>
						
					</th>
					<th>
						
					</th>
					<th colspan="2">
						
					</th>
					<th>
						
					</th>
					<th colspan="2">
						
					</th>
					<th>
						
					</th>
				</tr>
					</logic:iterate>
			</logic:lessEqual>
			
			
			<!-- 家庭成员情况 -->
			<logic:greaterThan value="6" name="cyNum">
			<tr style="height:22px">
				<th rowspan="${cyNum+7 }">
					<div align="center">
					<font style="font-size:15px">
						家<br>
						庭<br>
						成<br>
						员<br>
						情<br>
						况
					</font>
					</div>
				</th>
				<th>
					<div align="center">
						姓名
					</div>
				</th>
				<th>
					<div align="center">
						年龄
					</div>
				</th>
				<th>
					<div align="center">
						与学生关系
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						工作（学习）单位
					</div>
				</th>
				<th>
					<div align="center">
						职业
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						年收入（元）
					</div>
				</th>
				<th>
					<div align="center">
						健康状况
					</div>
				</th>
				</tr>
				<logic:iterate name="cyList" id="cy">
				<tr>
					<th>
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyzy }&nbsp;&nbsp;
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							${cy.cynsr }&nbsp;&nbsp;
						</div>
					</th>
					<th>
						<div align="center">
							${cy.cyjkzk }&nbsp;&nbsp;
						</div>
					</th>
				</tr>
			</logic:iterate>
			</logic:greaterThan>
			<!-- 相关信息 -->
			<tr style="height:100px">
				<th width="5%">
					<font style="font-size:15px">
					影响<br>
					家庭<br>
					经济<br>
					状况<br>
					有关<br>
					信息
					</font>
				</th>
				<th colspan="9" align="left">
				家庭人均年收入<u>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtrjsr">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>（元）。
				学生本学年已获资助情况<u>&nbsp;&nbsp;${rs.yhzzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="yhzzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>。<br/>
				家庭遭受自然灾害情况：<u>&nbsp;&nbsp;${rs.jtszqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtszqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>。
				家庭遭受突发意外事件：<u>&nbsp;&nbsp;${rs.tfsjqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="tfsjqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> 。<br>
				家庭成员因残疾、年迈而劳动能力弱情况：<u>&nbsp;&nbsp;${rs.cjnmqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="cjnmqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> 。<br>
				家庭成员失业情况：<u>&nbsp;&nbsp;${rs.jtsyqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtsyqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u> 。
				家庭欠债情况：<u>&nbsp;&nbsp;${rs.jtqzqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqzqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>。<br>
				其他情况：<u>&nbsp;&nbsp;${rs.jtqtqk}&nbsp;&nbsp;<logic:equal value="" name="rs" property="jtqtqk">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:equal></u>。		
				</th>
			</tr>
			<!-- 相关信息 -->
			<tr style="">
				<th width="5%">
					<font style="font-size:15px">
					签<br>
					章<br>
					</font>
				</th>
				<th>
					学生本<br>
					人签字
				</th>
				<th>

				</th>
				<th>
					学生家<br>
					长或监<br>
					护人签<br>
					字
				</th>
				<th>

				</th>
				<th width="10%">
					学生家庭<br>所在地乡<br>镇或街道<br>民政部门<br>意见
				</th>
				<th colspan="4">	
					<div align="left">		
						<br/>
						当地低保标准：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元/人.月<br>
						经办人签字：<br>
						单位名称：<br/>
						（加盖公章）
						<br>
					</div>
					<div align="left">
						 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日
					</div>
				</th>
			</tr>
			<!-- 民政部门信息 -->
			<tr height="22px">
				<th width="5%" rowspan="2">
					<font style="font-size:15px">
					民政<br>
					部门<br>
					信息<br>
					</font>
				</th>
				<th colspan="2" >
					详细通讯地址
				</th>
				<th colspan="7">

				</th>
			</tr>
			<tr height="22px">
				<th colspan="2">
					邮政编码
				</th>
				<th colspan="2">

				</th>
				<th>
					联系电话
				</th>
				<th colspan="4" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（区号）－</th>
			</tr>
		</table>
		<div align="left"  style="font-family:仿宋_GB2312;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<B>注：</B>低保户、烈士家庭、五保户、残疾学生等附证明文件复印件。
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
	</body>
</html:html>
