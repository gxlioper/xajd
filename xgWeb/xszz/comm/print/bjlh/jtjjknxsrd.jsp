<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
			<span style="font-size:22px;font-family:黑体">北京联合大学家庭经济困难学生认定申请表</span>
			<br />
			<br />
			<br />
		</center>
		<table class="printtab">
			<tr height="40px">
			<td rowspan="4">   学生<br/>本人<br/>基本<br>情况  </td>
				<td width="90">
					<p align="center">
						姓 名
					</p>
				</td>
				<td width="73">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td>
					<p align="center">
						性 别
					</p>
				</td>
				<td>
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td>
					<p align="center">
						出生年月
					</p>
				</td>
				<td>
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td width="87px">
				    <p align="center">
				                        民族
				    </p>
				</td>
				<td width="130">
				      <p align="center">
				      ${rs.mzmc }
				      </p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85">
					<p align="center">
						身份证号
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.sfzh }
					</p>
				</td>
				<td width="87" >
					<p align="center">
						政治面貌
					</p>
				</td>
				<td width="113">
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td width="100">
					<p align="center">
						家庭人均月收入
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.jtrjysr }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85">
					<p align="center">
						<bean:message key="lable.xb" />
					</p>
				</td>
				<td>
					<p align="center">
						${rs.xymc }
					</p>
				</td>
				<td>
					<p align="center">
						学号
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.xh }
					</p>
				</td>
				<td width="100">
					<p align="center">
						专业
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="87">
					<p align="center">
						班级
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						在校联系电话
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						${rs.sjhm }
					</p>
				</td>
			</tr>
			<tr height="300px">
				<td width="38" align="center">
					学<br>
					生<br>
					陈<br>
					述<br>
					申<br>
					请<br>
					认<br>
					定<br>
					理<br>
					由<br>
				</td>
				<td width="840" colspan="11" valign="top">
					<p style="height:290px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
					          注：可另附详细情况说明。
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						学生签字：
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 日
					</p>
				</td>
			</tr>
			<tr height="200">
				<td width="38" align="center">
					<br/>
					民<br>
					主<br>
					评<br>
					议<br>
				</td>
				<td  align="center">
				    <br/>
					推<br>
					荐<br>
					档<br>
					次<br>
				</td>
				<td width="300" colspan="2">
					<p>
						A.家庭经济一般困难     <input type="checkbox" />
					</p>
					<p>	<br/>
						B.家庭经济特殊困难  <input type="checkbox"/>
					</p>
					<p><br/>
						C.家庭经济不困难&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"/>
					</p>
				</td>
				<td align="center">
				<br/>
				陈<br>
				述<br>
				理<br>
				由<br>
				</td>
				<td colspan="4">
				<p style="height:160px"></p>
				<p  align="left">
				评议小组组长签字：
				</p>
				<p align="right">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 日
					</p>
				</td>
			</tr>
			<tr height="200">
				<td width="38" align="center">
					<br/>
					认<br>
					定<br>
					审<br>
					核<br>
				</td>
				<td align="center">
				 <br/>
				   学<br>
				   院<br>
				   意<br>
				   见<br>    
				</td>
				<td colspan="3">
				经评议小组推荐、本<bean:message key="lable.xb" />认真审核后，<br/>                 
				 <input type="checkbox"/>   同意评议小组意见。   <br/>
				 <input type="checkbox"/>  不同意评议小组意见。调整为 
				 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 </u>。
				
				</td>
				<td width="440" colspan="11" valign="top">
					<p style="height:160px" align="left">
					<br/>
						工作组组长签字：
					</p>
					<p align="right">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 日
					</p>
					<p align="center">
					（加盖<bean:message key="lable.xb" />公章）
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
