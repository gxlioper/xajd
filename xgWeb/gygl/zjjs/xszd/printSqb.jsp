<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
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
	<body>
		<br/><br/>
		<p align="center">
			<span style="font-size:21px;font-family:黑体">走读申请表</span>
		</p>
		<br/>
		<table style="font-family:仿宋_GB2312;font-size:14px;width:17.67cm;" class="printtab">
			<tr height="45px" class="nowrap">
				<td width="43" align="center">
						系
				</td>
				<td width="84" align="center">
						${rs.xymc }
				</td>
				<td width="48" align="center">
						班级
				</td>
				<td width="120" align="center">
						${rs.bjmc }
				</td>
				<td width="48" align="center">
						姓名
				</td>
				<td width="120" align="center">
						${rs.xm }
				</td>
				<td width="48" align="center">
						性别
				</td>
				<td width="57" align="center">
						${rs.xb }
				</td>
			</tr>
			<tr height="45px">
				<td width="343" colspan="5" >
						学生联系电话：${rs.lxdh }
				</td>
				<td width="225" colspan="3" >
						住宿地点：${rs.zsdd }
				</td>
			</tr>
			<tr height="45px">
				<td width="343" colspan="5" >
						家庭居住地址：${rs.jtdz }
				</td>
				<td width="225" colspan="3" >
						家庭联系电话：${rs.jtdh }
				</td>
			</tr>
			<tr height="45px">
				<td width="568" colspan="8" >
						走读时间为：
					<script defer>
						var kssj = '${rs.zdkssj }';
						jQuery('#kssj').text(kssj.substring(0,4)+"年"+kssj.substring(5,6)+"月"+kssj.substring(7,8)+"日") ;
					</script>
					<span id="kssj"></span>
					&nbsp;&nbsp; —— &nbsp;&nbsp;
					<span id="jssj"></span>
					<script defer>
						var jssj = '${rs.zdjssj }';
						jQuery('#jssj').text(jssj.substring(0,4)+"年"+jssj.substring(5,6)+"月"+jssj.substring(7,8)+"日") ;
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						申请理由：
					</p>
					<p style="height:110px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<p>
						本人及家长已仔细阅读过《学生手册》中相关的学生走读规定。
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						家长意见：
					</p>
					<p style="height:100px">
						&nbsp;
					</p>
					<p align="right" >
						家长签字 :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						班主任意见：
					</p>
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjshyj }
					</p>
					<p align="right" >
						班主任签字 :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						系领导意见：
					</p>
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="right" >
						系领导签字 :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

		<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
