<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>高等学校学生及其家庭情况调查表</title>
<style>
.font_style td{font-size:14px;font-family:宋体; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
<table width="652px" align="center" border="0">
	<tr>
		<td align="center">
		<br/><br/><br/><br/>
			<b>
			<span style='font-size:16.0pt;font-family:黑体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
				高等学校学生及家庭情况调查表
			</span>
			</b>
			<br/><br/><br/>
			<div align="left">
				<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
				<b>学校：</b><u>${xxmc}&nbsp;&nbsp;</u><br/><br/>
				<b>院（系）：</b><u>${jbxx.xymc }&nbsp;&nbsp;</u>
				<b>专业：</b><u>${jbxx.zymc }&nbsp;&nbsp;</u>
				<b>年级：</b><u>${jbxx.nj }</u>
				</span>
			</div>
			<br/>
		</td>
	</tr>
	<tr>
		<td align="center">
		<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
			<!-- 学生本人基本情况 -->
			<tr>
				<td width="7%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="4%"></td>
				<td width="5%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="8%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="7%"></td>
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
						${jbxx.xm }
				</td>
				<td align="center">
						性别
				</td>
				<td align="center">
						${jbxx.xb }
				</td>
				<td align="center" colspan="2">
						出生年月
				</td>
				<td colspan="2" align="center" >
						${jbxx.csrq }
				</td>
				<td colspan="2" align="center" >
						民族
				</td>
				<td align="center">
						${jbxx.mzmc }
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:30px">
				<td width="" align="center">
						身份证<br/>号码
				</td>
				<td colspan="4" align="center">
						${jbxx.sfzh }
				</td>
				<td align="center">
						政治<br/>面貌
				</td>
				<td colspan="2" align="center">
						${jbxx.zzmmmc }
				</td>
				<td align="center" colspan="2">
						入学前<br/>户口	
				</td>
				<td align="left" colspan="3">
					&nbsp;<logic:empty name="jtqk" property="jthk">
							□城镇&nbsp;
							□农村
					</logic:empty>
					<logic:equal name="jtqk" property="jthk" value="城镇">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							城镇&nbsp;
							□农村
					</logic:equal>
					<logic:equal name="jtqk" property="jthk" value="农村">
						□城镇&nbsp;
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
						${jtqk.jtrs}
				</td>
				<td align="center">
						毕业<br/>学校					
				</td>
				<td colspan="2" align="center">
						&nbsp;
				</td>
				<td align="center" colspan="2">
						个人<br/>特长
				</td>
				<td align="center" colspan="3">
						${jbxx.tc }
				</td>
			</tr>
			<!-- 第四行 -->
			<tr style="height:30px">
				<td align="center">
						孤 残
				</td>
				<td align="center" colspan="3">
					<logic:empty name="jtqk" property="sfgc">
						□是&nbsp;&nbsp;
						□否
					</logic:empty>
					<logic:equal name="jtqk" property="sfgc" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						是&nbsp;&nbsp;
						□否
					</logic:equal>
					<logic:equal name="jtqk" property="sfgc" value="0">
						□是&nbsp;&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						否
					</logic:equal>
				</td>
				<td align="center" colspan="1">
						单 亲
				</td>
				<td align="center" colspan="3">
					<logic:empty name="jtqk" property="sfdq">
						□是&nbsp;&nbsp;
						□否
					</logic:empty>
					<logic:equal name="jtqk" property="sfdq" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						是&nbsp;&nbsp;
						□否
					</logic:equal>
					<logic:equal name="jtqk" property="sfdq" value="0">
						□是&nbsp;&nbsp;
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						否
					</logic:equal>
				</td>
				<td align="center" colspan="2">
						烈士子女
				</td>
				<td align="left" colspan="3">
					&nbsp;<logic:empty name="jtqk" property="lszn">
						□是&nbsp;&nbsp;&nbsp;&nbsp;
						□否
					</logic:empty>
					<logic:equal name="jtqk" property="lszn" value="1">
						<img src="/xgxt/pictures/xszz/gou.jpg"></img>
						是&nbsp;&nbsp;&nbsp;&nbsp;
						□否
					</logic:equal>
					<logic:equal name="jtqk" property="lszn" value="0">
						□是&nbsp;&nbsp;&nbsp;&nbsp;
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
				<td colspan="11" align="left">
						&nbsp;${jtqk.jtdz}
				</td>
			</tr>
			<!-- 第二行 -->
			<tr style="height:30px">
				<td align="center" colspan="2">
						邮政编码
				</td>
				<td align="center" colspan="3">
						${jtqk.jtyb}
				</td>
				<td align="center" colspan="2">
						联系电话
				</td>
				<td colspan="6" align="center" >
						${jtqk.jtdh}
				</td>
			</tr>
			<!-- 家庭成员情况 -->
			<tr style="height:30px">
				<td rowspan="${cysize+1 }"  align="center">
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
							${cy.cygxmc }&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							${cy.cyxxdw }&nbsp;&nbsp;
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
			<logic:iterate name="blankList" id="blank">
				<tr style="height:30px">
					<td align="center" >
							&nbsp;&nbsp;
					</td>
					<td align="center">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
					</td>
					<td align="center"colspan="4">
							&nbsp;&nbsp;
					</td>
					<td align="center">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
					</td>
					<td align="center" colspan="2">
							&nbsp;&nbsp;
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
					&nbsp;家庭人均年收入:<logic:notEqual value="" property="jtrjsr" name="jtqk"><u>&nbsp;${jtqk.jtrjsr }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtrjsr" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。学生本学年已获资助情况:
					<logic:notEqual value="" property="yhzzqk" name="jtqk"><u>&nbsp;${jtqk.yhzzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="yhzzqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					&nbsp;家庭遭受自然灾害情况：<logic:notEqual value="" property="jtszqk" name="jtqk"><u>&nbsp;${jtqk.jtszqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtszqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。家庭遭受突发意外事件：<logic:notEqual value="" property="tfsjqk" name="jtqk"><u>&nbsp;${jtqk.tfsjqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="tfsjqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					&nbsp;家庭成员因残疾、年迈而劳动能力弱情况：<logic:notEqual value="" property="cjnmqk" name="jtqk"><u>&nbsp;${jtqk.cjnmqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="cjnmqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					&nbsp;家庭成员失业情况：<logic:notEqual value="" property="jtsyqk" name="jtqk"><u>&nbsp;${jtqk.jtsyqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtsyqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。家庭欠债情况：<logic:notEqual value="" property="jtqzqk" name="jtqk"><u>&nbsp;${jtqk.jtqzqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqzqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。<br><br>
					&nbsp;其他情况：<logic:notEqual value="" property="jtqtqk" name="jtqk"><u>&nbsp;${jtqk.jtqtqk }&nbsp;</u></logic:notEqual><logic:equal value="" property="jtqtqk" name="jtqk"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></logic:equal>。
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
				<td colspan="2">

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
				<td colspan="5">	
					<div align="left">
						<br>
						&nbsp;经办人签字：
						<br>
						<br>
						&nbsp;单位名称：
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
				<td colspan="11" align="left">
					&nbsp;${jtqk.mzbmtxdz}
				</td>
			</tr>
			<tr style="height:30px" align="center">
				<td colspan="2" align="center">
					邮政编码
				</td>
				<td colspan="3" align="center">
					${jtqk.mzbmyzbm}
				</td>
				<td colspan="2" align="center">
					联系电话
				</td>
				<td colspan="6" align="center">
					${jtqk.mzbmlxdh}
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
	</html:form>
	</div>
</body>

</html>