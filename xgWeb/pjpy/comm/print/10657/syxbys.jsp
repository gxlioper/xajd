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
					${rs.pjnd }届贵州省普通高校优秀大学毕业生审批表 </span> </b>
		</p>
		<br />

		<p style="width:98%;" align="right">
			填报时间： &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="60" >
					<p align="center" >
						姓名
					</p>
				</td>
				<td width="84" align="center">
					${rs.xm }
				</td>
				<td width="60">
					<p align="center">
						性别
					</p>
				</td>
				<td width="48" align="center">
					${rs.xb }
				</td>
				<td width="60">
					<p align="center">
						出生
					</p>
					<p align="center">
						日期
					</p>
				</td>
				<td width="96" align="center">
					${rs.csrq }
				</td>
				<td width="60">
					<p align="center">
						民族
					</p>
				</td>
				<td width="60" align="center">
					${rs.mzmc }
				</td>
				<td width="120" rowspan="3" style="padding:0px 0px 0px 0px">
					<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:120px;height:160px" />
				</td>
			</tr>
			<tr>
				<td width="60">
					<p align="center">
						政治
					</p>
					<p align="center">
						面貌
					</p>
				</td>
				<td width="84" align="center">
					${rs.zzmmmc }
				</td>
				<td width="108" colspan="2">
					<p align="center">
						现任社会
					</p>
					<p align="center">
						工作职务
					</p>
				</td>
				<td width="156" colspan="2" align="center">
					${rs.xszw }
				</td>
				<td width="60">
					<p align="center">
						学历
					</p>
				</td>
				<td width="60" align="center">
					${rs.xlmc }
				</td>
			</tr>
			<tr>
				<td width="60">
					<p align="center">
						毕业
					</p>
					<p align="center">
						院校
					</p>
				</td>
				<td width="192" colspan="3" align="center">
					${xxmc }
				</td>
				<td width="60">
					<p align="center">
						专业
					</p>
					<p align="center">
						名称
					</p>
				</td>
				<td width="216" colspan="3" align="center">
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						个人简历：
					</p>
					<p style="height:175px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.个人简历 }
					</p>
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						获奖情况 :
					</p>
					<p style="height:170px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.获奖情况 }
					</p>
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						主要事迹：（可另附材料）
					</p>
					<p style="height:250px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.主要事迹 }
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="104">
					<p align="center" >
						班<br/>级<br/>推<br/>荐<br/>意<br/>见
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						辅导员或班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="74">
					<p align="center" style="writing-mode: tb-rl">
						院 ( 系 ) 评选意见
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
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
				<td width="104">
					<p align="center">
						学<br/>校<br/>初<br/>审<br/>意<br/>见
					</p>
				</td>
				<td width="544" valign="top">
					<p style="20px">
						&nbsp;
					</p>
					<p style="height:90px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示 个工作日，无异议，现报请批准该同学为 ${rs.pjnd } 届贵州省优秀大学毕业生。
					</p>
					<p style="height:20px">
						&nbsp;
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
				<td width="104">
					<p align="center">
						省<br/>教<br/>育<br/>厅<br/>审<br/>批<br/>意<br/>见
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
						&nbsp;
						&nbsp;
					</p>
					<p align="right">
						（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104">
					<p align="center">
						备 注
					</p>
				</td>
				<td width="544">
					<p style="height:90px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.备注 }
					</p>
				</td>
			</tr>
		</table>
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
