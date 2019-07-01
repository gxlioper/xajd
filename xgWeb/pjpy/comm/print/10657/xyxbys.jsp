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
	<script type="text/javascript">
		function dyConfirm(){
			var ie8 = jQuery.browser.msie && jQuery.browser.version=='8.0';
			if (ie8){
				return true;
			} else {
				return confirm('您使用的浏览器非IE8，可能会影响到打印效果。确定要继续吗?');
			}
		}
	</script>	
</head>
<body>
	<center>
		<br />
		<br />
		<p align=center>
			<b><span
				style='font-size:21px;font-family:黑体;font-weight:normal;'>
					${rs.pjnd }届贵州大学优秀大学毕业生审批表 </span> </b>
		</p>
		<br />

		<p class="nowrap">
			学号&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			填报时间： &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="8%">
					<p align="center" class="nowrap">
						姓名
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.xm }
				</td>
				<td width="9%">
					<p align="center">
						性别
					</p>
				</td>
				<td width="9%" colspan="2" align="center">
					${rs.xb }
				</td>
				<td width="9%" colspan="3">
					<p align="center">
						出生年月
					</p>
				</td>
				<td width="11%" align="center">
					${rs.csrq }
				</td>
				<td width="8%" colspan="2">
					<p align="center" class="nowrap">
						民族
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.mzmc }
				</td>
				<td width="17%" colspan="2" rowspan="3" style="padding:0px 0px 0px 0px">
					<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:110px;height:140px" />
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						政治面貌
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.zzmmmc }
				</td>
				<td width="9%">
					<p align="center">
						担任
					</p>
					<p align="center">
						职务
					</p>
				</td>
				<td width="30%" colspan="6" align="center">
					${rs.xszw }
				</td>
				<td width="8%" colspan="2">
					<p align="center">
						学历
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.xlmc }
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						<bean:message key="lable.xb" />
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.xymc }
				</td>
				<td width="9%">
					<p align="center">
						专业
					</p>
				</td>
				<td width="30%" colspan="6" align="center">
					${rs.zymc }
				</td>
				<td width="8%" colspan="2">
					<p align="center">
						学制
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.xz }
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						个
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						人
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						简
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						历
					</p>
				</td>
				<td width="91%" colspan="17" valign="top">
					<p align="left" style="height:220px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.个人简历 }
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						主
					</p>
					<p align="center">
						要
					</p>
					<p align="center">
						先
					</p>
					<p align="center">
						进
					</p>
					<p align="center">
						事
					</p>
					<p align="center">
						迹
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						获
					</p>
					<p align="center">
						奖
					</p>
					<p align="center">
						情
					</p>
					<p align="center">
						况
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="91%" colspan="17" valign="top">
					<p align="left" style="height:210px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.主要事迹 }
					</p>
					<br/>
					<p align="left" style="height:210px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.获奖情况 }
					</p>
				</td>
			</tr>
			</table>
			<p style="height:70px">&nbsp;</p>
			<table class="printtab" style="width:98%">
			<tr>
				<td width="13%" colspan="2">
					<p align="center">
						班级人数
					</p>
				</td>
				<td width="6%" align="center">
					${rs.bjrs }
				</td>
				<td width="15%" colspan="2">
					<p align="center">
						在校期间是否受过处分
					</p>
				</td>
				<td width="9%" colspan="3" align="center">
					${rs.sfwj }
				</td>
				<td width="22%" colspan="4">
					<p align="center">
						综测名次
					</p>
				</td>
				<td width="7%" colspan="2" align="center">
					${rs.zd1 }
				</td>
				<td width="14%" colspan="3">
					<p align="center">
						综测成绩
					</p>
				</td>
				<td width="9%" align="center">
					${rs.zcfbjpm }
				</td>
			</tr>
			<tr>
				<td width="37%" colspan="6" >
					<p align="center">
						在校期间补考科目数
					</p>
				</td>
				<td width="15%" colspan="3" align="center">
					${rs.bkkms }
				</td>
				<td width="30%" colspan="6" align="center">
						未取得学分的科目数
				</td>
				<td width="17%" colspan="3" align="center">
					${rs.wdxfkms }
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2" valign="top">
					<p>
						&nbsp;
					</p>
					<p align="center">
						班
					</p>
					<p align="center">
						级
					</p>
					<p align="center">
						推
					</p>
					<p align="center">
						荐
					</p>
					<p align="center">
						意
					</p>
					<p align="center">
						见
					</p>
				</td>
				<td width="87%" colspan="16" valign="bottom">
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2" valign="top">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						院
					</p>
					<p align="center">
						审
					</p>
					<p align="center">
						批
					</p>
					<p align="center">
						意
					</p>
					<p align="center">
						见
					</p>
				</td>
				<td width="87%" colspan="16">
					<p align="center" style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
					</p>
					<p align="right">
						（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;${rs.xynian}&nbsp;&nbsp; 年 &nbsp;&nbsp;${rs.xyyue}&nbsp;&nbsp;月 &nbsp;&nbsp;${rs.xyri}&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2" valign="top">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						校
					</p>
					<p align="center">
						审
					</p>
					<p align="center">
						批
					</p>
					<p align="center">
						意
					</p>
					<p align="center">
						见
					</p>
				</td>
				<td width="87%" colspan="16" valign="top">
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
					</p>
					<p align="right">
						（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;${rs.xxnian}&nbsp;&nbsp; 年 &nbsp;&nbsp;${rs.xxyue}&nbsp;&nbsp;月 &nbsp;&nbsp;${rs.xxri}&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2">
					<p align="center">
						备
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						注
					</p>
				</td>
				<td width="87%" colspan="16" valign="top">
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.备注 }
					</p>
				</td>
			</tr>
		</table>
		<p width="98%" align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;注 : 1. 附本人进校以来的自我鉴定和成绩单复印件（须院盖章）一式一份 <br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 此表填一式一份，存入毕业生档案。
		</p>
	</center>
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(8,1)}">
			页面设置
		</button>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(7,1)}">
			打印预览
		</button>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(6,6)}">
			直接打印
		</button>
	</div>
</body>
</html:html>
