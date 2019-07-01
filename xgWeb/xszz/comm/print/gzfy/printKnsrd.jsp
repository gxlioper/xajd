<%@ page language="java"  pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title>家庭经济困难学生认定申请表</title>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:黑体">广州番禺职业技术学院家庭经济困难学生认定表</span>
			<br />
			<br />
		</center>
		<table class="printtab">
			<logic:iterate id="k" name="knsList">
			<tr>
				<td width="20px" rowspan="4" valign="center">
					<p valign="center" style="width:12px;word-wrap:break-word;">
						<strong>${k.xn }</strong>
					</p>
				</td>
				<td width="55" rowspan="3">
					<p align="center">
						评民<br/>议主
					</p>
				</td>
				<td width="54" rowspan="3">
					<p align="center">
						档推<br/>次荐
					</p>
				</td>
				<td width="235" height="37">
					<p>
						<logic:equal value="家庭经济一般困难" name="k" property="xmzzjb">
							■
						</logic:equal>
						<logic:notEqual value="家庭经济一般困难" name="k" property="xmzzjb">
							□
						</logic:notEqual>
						 A 、家庭经济一般困难
					</p>
				</td>
				<td width="35" rowspan="3">
					<p>
						陈述理由
					</p>
				</td>
				<td colspan="3" rowspan="3" valign="bottom"> 
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						评议小组组长签字：
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="235" height="39">
					<p>
						<logic:equal value="家庭经济特殊困难" name="k" property="xmzzjb">
							■
						</logic:equal>
						<logic:notEqual value="家庭经济特殊困难" name="k" property="xmzzjb">
							□
						</logic:notEqual>
						 B 、家庭经济特殊困难
					</p>
				</td>
			</tr>
			<tr>
				<td width="235" height="35">
					<p>
						<logic:equal value="家庭经济不困难" name="k" property="xmzzjb">
							■
						</logic:equal>
						<logic:notEqual value="家庭经济不困难" name="k" property="xmzzjb">
							□
						</logic:notEqual>
						 C 、家庭经济不困难
					</p>
				</td>
			</tr>
			<tr>
				<td width="55">
					<p align="center">
						认<br/>定<br/>决<br/>定
					</p>
				</td>
				<td width="54">
					<p align="center">
						系<br/>（院）<br/>意<br/>见
					</p>
				</td>
				<td colspan="3" valign="top" >
					<p>
						经评议小组推荐、本系（院）认真审核后：
					</p>
					<p>
						□ 同意评议小组意见；
					</p>
					<p>
						□ 不同意评议小组意见，调整为：
					</p>
					<p>
						___________________________。
					</p>
					<p>
						助学工作小组组
					</p>
					<p>
						长签字（或盖章）：
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td width="33">
					<p align="center">
						学生处助学办意见
					</p>
				</td>
				<td width="270" valign="top">
					<p>
						经学生所在系（院）审核，本部门认真核实：
					</p>
					<p>
						□同意助学工作小组和评议小组意见；
					</p>
					<p>
						□不同意助学工作小组意见，调整为：
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="center">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						（加盖部门公章）
					</p>
				</td>
			</tr>
			</logic:iterate>
		</table>
		<p>
			&nbsp;
		</p>




		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
