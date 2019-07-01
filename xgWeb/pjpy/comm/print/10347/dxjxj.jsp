<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=GBK">
		<title>湖州师范学院单项奖学金申请表</title>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext></object>
		<style media='print'>
			.noPrin {display: none;}
		</style>
	</head>
	<body>
		<center>
			<p>
				<span style="font-size: 26px; font-family: 宋体">湖州师范学院单项奖学金申请表</span>
			</p>
		</center>
			<table border="0" width="652px" class="printtab">
				<tr>
					<td width=80>
						<p><b><span>姓名</span></b></p>
					</td>
					<td width=91 colspan=2>
						${rs.xm }
					</td>
					<td width=49 colspan=2>
						<p><b><span>性别</span></b></p>
					</td>
					<td width=63>
						${rs.xb }
					</td>
					<td width=53>
						<p><b><span><bean:message key="lable.xb" /></span></b></p>
					</td>
					<td width=87 colspan=2>
						${rs.xymc }
					</td>
					<td width=67>
						<p><b><span>班级</span></b></p>
					</td>
					<td width=87 valign=top>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td width=80>
						<p><b><span>政治面貌</span></b></p>
					</td>
					<td width=91 colspan=2>
						${rs.zzmmmc }
					</td>
					<td width=49 colspan=2>
						<p><b><span>职务</span></b></p>
					</td>
					<td width=357 colspan=6>
						${rs.xszw }
					</td>
				</tr>
				<tr>
        			<td width=74 colspan=2 >
        				<p><b><span>申请类别</span></b></p>
        			</td>
        			<td width=489 colspan=10>
        				<p><b>
        					<span>
        						<logic:equal value="学习特别奖" name="xmmc">
        							<input type="checkbox" disabled="disabled" checked="checked"/>学习特别奖
        							<input type="checkbox" disabled="disabled"/>技能奖
	        						<input type="checkbox" disabled="disabled"/>科技奖
	        						<input type="checkbox" disabled="disabled"/>创新奖
	        						<input type="checkbox" disabled="disabled"/>文体活动奖
        						</logic:equal>
        						<logic:equal value="技能奖" name="xmmc">
        							<input type="checkbox" disabled="disabled"/>学习特别奖
        							<input type="checkbox" disabled="disabled" checked="checked"/>技能奖
	        						<input type="checkbox" disabled="disabled"/>科技奖
	        						<input type="checkbox" disabled="disabled"/>创新奖
	        						<input type="checkbox" disabled="disabled"/>文体活动奖
        						</logic:equal>
        						<logic:equal value="科技奖" name="xmmc">
        							<input type="checkbox" disabled="disabled"/>学习特别奖
        							<input type="checkbox" disabled="disabled"/>技能奖
	        						<input type="checkbox" disabled="disabled" checked="checked"/>科技奖
	        						<input type="checkbox" disabled="disabled"/>创新奖
	        						<input type="checkbox" disabled="disabled"/>文体活动奖
        						</logic:equal>
        						<logic:equal value="创新奖" name="xmmc">
        							<input type="checkbox" disabled="disabled"/>学习特别奖
        							<input type="checkbox" disabled="disabled"/>技能奖
	        						<input type="checkbox" disabled="disabled"/>科技奖
	        						<input type="checkbox" disabled="disabled" checked="checked"/>创新奖
	        						<input type="checkbox" disabled="disabled"/>文体活动奖
        						</logic:equal>
        						<logic:equal value="文体活动奖" name="xmmc">
        							<input type="checkbox" disabled="disabled"/>学习特别奖
        							<input type="checkbox" disabled="disabled"/>技能奖
	        						<input type="checkbox" disabled="disabled"/>科技奖
	        						<input type="checkbox" disabled="disabled"/>创新奖
	        						<input type="checkbox" disabled="disabled" checked="checked"/>文体活动奖
        						</logic:equal>
        					</span>
        				</b></p>
        			</td>
      			</tr>
     			<tr > 
			      <td align=center >
				      <b> 申</b><br/>
				      <b> 请</b><br/>
				      <b> 理</b><br/>
				      <b> 由</b><br/>
			      </td> 
      				<td valign=center  colspan=10 >
      					<p style="height:320px"><b>注：（如是进步奖，请注明进步名次，其他需附证书复印件）：</b></p>
      					<br/>${rs.sqly }<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
						<p align="right">
							<b><span>签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
      				</td> 
    			</tr> 
				<tr>
					<td width=182 colspan=4 valign=top>
						<p>
							<b><span>班委、团支部意见：</span>
							</b>
						</p>
					</td>
					<td width=221 colspan=4 valign=top>
						<p>
							<b><span>下属<bean:message key="lable.xb" />评比小组意见：</span></b>
						</p>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<p align="right">
							<b><span>（章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </b>
						</p>
						<br/>
						<p align="right">
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
					</td>
					<td width=175 colspan=3 valign=top>
						<p>
							<b><span>学校审核意见：</span></b>
						</p>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<p align="right">
							<b><span>（章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </b>
						</p>
						<br/>
						<p align="right">
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
					</td>
				</tr>
				<tr>
					<td width=92 colspan=2>
						<p style='text-align: center'>
							<b><span>备注</span></b>
						</p>
					</td>
					<td width=485 colspan=9>
						&nbsp;
					</td>
				</tr>
			</table>
			<div style="width:652px; margin-top: 5px; margin:auto;" align="center">
				<p align="left">
					<b><span>注</span></b><b><span>：1、此表一式两份，一份存入学生档案，一份学校留存，
                        <br/>2、此表打印或用钢笔填写，字迹清楚，经院（系）、学校盖章方有效</span></b>
				</p>
				<p align="right">
					<b><span>湖州师范学院学生处制表</span> </b>
				</p>
			</div>
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