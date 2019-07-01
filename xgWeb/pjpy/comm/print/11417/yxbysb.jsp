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
			<p align=center style='text-align:center'>
				<b><span style='font-size:20.0pt;font-family:宋体'>北京联合大学${rs.xmmc }登记表</span></b>
			</p>
			<table class="printtab" width="90%">
				<tr style="height:45px" >
					<td width="16%" align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>姓名</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.xm }
					</td>
					<td  width="16%" align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>性</span>
							<span style='font-family:宋体;"Times New Roman";"Times New Roman"'>别</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.xb }
					</td>
					<td   width="16%" align="center">
						<p align=center style='text-align:center;'>
							<span style='font-family:宋体;"Times New Roman"'>专业</span>
						</p>
					</td>
					<td  width="16%" align="center">
						&nbsp;${rs.zymc }
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>民族</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.mzmc }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>政治面貌</span>
						</p>
					</td>
					<td align="center">
						&nbsp;${rs.zzmmmc }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>学号</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.xh}
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>评奖年度</span>
						</p>
					</td>
					<td align="center" >
						&nbsp;${rs.pjnd }
					</td>
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>联系方式</span>
						</p>
					</td>
					<td align="center">
						&nbsp;${rs.lxdh }
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>班级</span>
						</p>
					</td>
					<td  align="center">
						&nbsp;${rs.bjmc }
					</td>
					
				</tr>
				<tr style="height:45px">
					<td align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>所在<bean:message key="lable.xb" /></span>
						</p>
					</td>
					<td  align="center" colspan=3  >
						&nbsp;${rs.xymc}
					</td>
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>毕业设计成绩</span>
						</p>
					</td>
					<td align="center" colspan=1>
						&nbsp;
					</td>
				</tr>
				<tr style="height:45px">
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>毕业去向</span>
						</p>
					</td>
					<td  align="center" colspan=5 valign=top >
						&nbsp;
					</td>
					
				</tr>
				<tr style="height:70px">
					<td >
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>荣获称号</span>
						</p>
					</td>
					<td colspan=5 valign=top >
						<p>
							<span style='font-family:宋体;
 							 &quot;Times New Roman&quot;'>
 							 
 							 <logic:iterate name="pjinfo" id="pjpy">
 							 	${pjpy.pjinfo }
 							 </logic:iterate>
 							 
 							 </span>
						</p>
					</td>
					
				</tr>
				<tr style="height:530px">
					<td  align="center">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>申</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>请</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>理</span>
						</p>
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>由</span>
						</p>
					</td>
					<td  align="left" colspan=5 valign=top>
						<p>
							<span style='font-family:宋体;
  							&quot;Times New Roman&quot;'>个人主要事迹：<br/>
  							&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</span>
						</p>
					</td>
					
				</tr>
				 <tr style="height:150px"> 
			      <td colspan=3   align="center" > <p align="left" style="vertical-align: top;height: 80px" >
			      	<span ><bean:message key="lable.xb" />意见<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
			      	</span>
			      </p> 
			      
			      <p align="right" style="vertical-align: bottom;">
			      	<span style='font-family:宋体'>盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
			      </td> 
			      
			     <td colspan=3  align="center"> <p align="left" style="vertical-align: top;height: 80px" ><span >学校意见<br/>
			     	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
			     </span>
			      </p> 
			       <p align="right" style="vertical-align: bottom;">
			      	<span style='font-family:宋体'>盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
			      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
			      </td>  
			    </tr> 
			</table>
			<p>
			<p style="text-align: left;"><span style='font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：此表一式三份，可复制、打印。</span>
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
