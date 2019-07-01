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
	</head>
	<body>

		<p align="center">
			<strong style="font-size:24px;font-family:黑体">成都体育学院优秀学生干部登记表</strong>
		</p>
		<p align="right">
			<strong>（学年：&nbsp;${rs.pjxn }&nbsp; ） </strong>
		</p>
		<table class="printtab" width="100%" style="font-family:宋体;font-weight:bold;font-size:14px;">
			<tr>
				<td width="14%" colspan="3" align="center">
					姓 名 
				</td>
				<td width="14%" colspan="2" align="center">
					${rs.xm }
				</td>
				<td width="9%" align="center">
					性别 
				</td>
				<td width="14%" align="center">
					${rs.xb }
				</td>
				<td width="8%" align="center">
					年龄 
				</td>
				<td width="13%" colspan="2" align="center">
					${rs.年龄 }
				</td>
				<td width="13%" colspan="2" align="center">
					政治 
					<br/>
					面貌 
				</td>
				<td width="15%" align="center">
					${rs.zzmmmc }
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" width="8%">
					系、级、班 
				</td>
				<td colspan="5" align="center">
					${rs.zymc } &nbsp;&nbsp; ${rs.nj } &nbsp;&nbsp; ${rs.bjmc }
				</td>
				<td colspan="2" align="center">
						学号 
				</td>
				<td colspan="2" align="center">
					${rs.xh }
				</td>
			</tr>
			<tr height="120px">
				<td width="6%" align="center">
					主<br/>要<br/>事<br/>迹
				</td>
				<td colspan="12" valign="top">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.主要事迹 }
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					班 级 
					<br/>
					意 见 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }   
					</p>
					<p align="right">
						签字（章）&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 
						&nbsp;&nbsp;&nbsp;&nbsp;日 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					年 级 
					<br/>
					意 见 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }   
					</p>
					<p align="right">
						签字（章）&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 
						&nbsp;&nbsp;&nbsp;&nbsp;日 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					系 部
					<br/>
					意 见 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj3 }   
					</p>
					<p align="right">
						签字（章）&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 
						&nbsp;&nbsp;&nbsp;&nbsp;日 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					学 院
					<br/>
					意 见 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj4 }   
					</p>
					<p align="right">
						签字（章）&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 
						&nbsp;&nbsp;&nbsp;&nbsp;日 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%" colspan="2" align="center">
					备注 
				</td>
				<td colspan="11">
					<p style="height:100px" valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }   
					</p>
					<p align="right">
						签字（章）&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 
						&nbsp;&nbsp;&nbsp;&nbsp;日 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p style="font-family:宋体;font-weight:bold;font-size:14px;" align="right">
			
			此表由组织填写，一式两份。
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			学生处制
		</p>

		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
