<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/syscommon/pagehead_V4.ini"%>
<html>

	<head>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		
			<p style="font-size:22px;font-family:黑体;padding-right: 80px" align="right" >编号:</p><br/>
		<center>	
			<span style="font-size:25px;font-family:黑体">金华职业技术学院家庭经济困难学生认定申请表</span>
		</center>
		<table class="printtab" style="font-size:14px;width:100%">
			<tr height="40px" align="center">
			<td rowspan="4" width="7%"><B>学<br/>生<br/>本<br/>人<br/>基<br/>本<br>情<br/>况</B></td>
				<td  width="12%" colspan="2">
					<p align="center">
						姓&nbsp;&nbsp;名
					</p>
				</td>
				<td  width="9%">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td  width="7%">
					<p align="center">
						性&nbsp;&nbsp;别
					</p>
				</td>
				<td  width="8%">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td  width="8%">
					<p align="center">
						出生<br/>年月
					</p>
				</td>
				<td  width="12%">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td  width="10%">
				    <p align="center">
				        民&nbsp;&nbsp;族
				    </p>
				</td>
				<td  width="10%">
				      <p align="center">
				      ${rs.mzmc }
				      </p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.sfzh }
					</p>
				</td>
				<td  >
					<p align="center">
						政治<br/>面貌
					</p>
				</td>
				<td >
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td>
					<p align="center">
						家庭人均<br/>年收入
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.jtrjsr }<span  style="padding-left: 50px">元</span>
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						学&nbsp;&nbsp;院
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.xymc }
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						专&nbsp;&nbsp;业
					</p>
				</td>
				<td  colspan="3">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td colspan="2">
					<p align="center">
						班级
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
				<td >
					<p align="center">
						在校联<br/>系电话
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						${rs.sjhm }
					</p>
				</td>
			</tr>
			<tr >
				<td  align="center" rowspan="2" >
					<B>学<br>
					生<br/>
					陈<br/>
					述<br/>
					申<br/>
					请<br/>
					认<br/>
					定<br/>
					理<br/>
					由<br></B>
				</td>
				<td align="center" colspan="2" height="45px">申请类别</td>
				<td  colspan="10"  height="45px">
					<logic:present name="knslbList"><logic:iterate name="knslbList" id="knslb">${knslb.num}：${knslb.mc };</logic:iterate></logic:present>
				</td>
			</tr>
			<tr  height="150px">
				<td  colspan="12" valign="top" colspan="2">
					<p style="height: 50px">
						详述理由：${rs.sqly }
					</p>
					<p align="right" >
					         
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
					<p>
						 (注：可另附详细情况说明。)
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr height="200px">
				<td   align="center">
					<B><br/>
					民<br>
					主<br>
					评<br>
					议<br></B>
				</td>
				<td  align="center" width="7%">
				    <br/>
					推<br>
					荐<br>
					档<br>
					次<br>
				</td>
				<td width="25%" colspan="2">
					<p style="letter-spacing:6px">
						A.家庭经济困难<logic:equal name="rs" property="xxtjdc" value="一般困难"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal>
						<logic:notEqual name="rs" property="xxtjdc" value="一般困难"><font style="font-size: 24px">□</font></logic:notEqual>
					</p>
					<p  style="letter-spacing:6px">	
						B.家庭经济特殊困难<br/><logic:equal name="rs" property="xxtjdc" value="特别困难"><img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:equal>
						<logic:notEqual name="rs" property="xxtjdc" value="特别困难"><font style="font-size: 24px">□</font></logic:notEqual>
					</p>
					<p  style="letter-spacing:6px">
						C.家庭经济不困难<br/><font style="font-size: 24px">□</font>
					</p>
					
				</td>
				<td align="center">
				<br/>
				陈<br>
				述<br>
				理<br>
				由<br>
				</td>
				<td colspan="5">
				<p style="height:160px"></p>
				<span  style="align:left">
				评议小组组长签字：
				</span>
				<span  style="padding-left: 40px">	
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>
						 日
					</span>
				</td>
			</tr>
			<tr height="250px">
				<td   align="center">
					<B><br/>
					认<br>
					定<br>
					决<br>
					定<br></B>
				</td>
				<td align="center" valign="center">
				 <br/>
				   <bean:message key="lable.xb" /><br>
				   意见<br>    
				</td>
				<td colspan="3" valign="top">
					经评议小组推荐、本院<br/>
					（系）认真审核后，<br/>
					<font style="font-size: 24px">□</font>同意评议小组意见。<br/>
					<font style="font-size: 24px">□</font>不同意评议小组<br/>意见。调整为<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。<br/><br/>
					工作组组长签字：<br/>
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> 年<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>月<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>日

				</td>
				<td width="5%" align="center" valign="center">
					学<br>校<br>学<br>生<br>资<br>助<br>管<br>理<br>机<br>构<br>意<br>见<br>
				</td>
				<td width="440" colspan="10" valign="top">
					<br/>
					经学生所在院（系）提请，本机构认真核实，<br/>
					<font style="font-size: 24px">□</font>  同意工作组和评议小组意见。<br/>
					<font style="font-size: 24px">□</font> 	不同意工作组和评议小组意见。调整为<br/>
					<font style="font-size: 24px">□</font> 	
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> 。
					<br>
					<br>
					<br>
					负责人签字：        
					<br>
					<br>
					<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> 年<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>月<U>&nbsp;&nbsp;&nbsp;&nbsp;</U>日<br/>
					（加盖部门公章）

				</td>
			</tr>
		</table>
		<p style="padding-left: 60px;font-size: 14px">
			本表格一式二份，学工办、学生处各留存一份
		</p>
		<p>
			&nbsp;
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
