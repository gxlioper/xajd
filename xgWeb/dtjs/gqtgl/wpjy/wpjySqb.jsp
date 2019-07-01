<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
<center>
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;'>借用物品申请单</span></b></p> 
  <table  class="printstyle" width="85%"> 
    <tr> 
      <td width=91 colspan=2 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>申请部门</p></td> 
      <td width=133 colspan=2 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.sqbm }</p></td> 
      <td width=77 colspan=3 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>经办人</p></td> 
      <td width=126 colspan=4 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.jbr }</p> </td> 
      <td width=64 colspan=2 class="Normal"><p align='center' style='font-size:12.0pt'>电话</p></td> 
      <td width=139 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.lxdh }</p></td> 
    </tr> 
    <tr> 
      <td width=91 colspan=2 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>申请事由</p></td> 
      <td width=189 colspan=4 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.sqsy }</p></td> 
      <td width=91 colspan=3 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>借用日期</p></td> 
      <td width=259 colspan=5 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.jyrq }</p></td> 
    </tr> 
    <tr> 
      <td width=630 colspan=14 valign=top class="Normal">
      <p align='center' style='font-size:12.0pt'>
      借用设备清单
      </p>
      </td> 
    </tr> 
    <tr> 
      <td width=57 class="Normal"><p align='center' style='font-size:12.0pt'>序号</p></td> 
      <td width=216 colspan=4 class="Normal"><p align='center' style='font-size:12.0pt'>设备名称</p></td> 
      <td width=82 colspan=3 class="Normal"><p align='center' style='font-size:12.0pt'>单位</p></td> 
      <td width=98 colspan=4 class="Normal"><p align='center' style='font-size:12.0pt'>数量</p></td> 
      <td width=177 colspan=2 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>归还情况标注</p></td> 
    </tr> 
				<fieldset>
						<logic:iterate name="wpbxx" id="s" indexId="index"  >
							<tr style="cursor:hand">
									<logic:iterate id="v" name="s" offset="0" length='1'>
									<td >
										<p align='center' style='font-size:12.0pt'>&nbsp;${v }</p>
									</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length='1'>
									<td colspan='4'>
										<p align='center' style='font-size:12.0pt'>&nbsp;${v }</p>
									</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length='1'>
									<td colspan='3'>
										<p align='center' style='font-size:12.0pt'>&nbsp;${v }</p>
									</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length='1'>
									<td colspan='4'>
										<p align='center' style='font-size:12.0pt'>&nbsp;${v }</p>
									</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="4" length='1'>
									<td colspan='2'>
										<p align='center' style='font-size:12.0pt'>&nbsp;${v }</p>
									</td>
									</logic:iterate>
							</tr>
						</logic:iterate>
				</fieldset>
    <tr> 
      <td width=630 colspan=14 valign=top class="Normal"><p align='left' style='font-size:12.0pt'>申请部门意见：</p> 
      	<br/>
      	<br/>
      	<br/>
      	<br/>
        <p align='right' style='font-size:12.0pt'>负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td width=630 colspan=14 valign=top class="Normal"><p align='left' style='font-size:12.0pt'>物品所有部门意见：</p> 
       	<br/>
      	<br/>
      	<br/>
      	<br/>
        <p align='right' style='font-size:12.0pt'>负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td width=630 colspan=14 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>设备归还情况登记</p></td> 
    </tr> 
    <tr> 
      <td width=105 colspan=3 class="Normal"><p align='center' style='font-size:12.0pt'>归还日期</p></td> 
      <td width=196 colspan=4 class="Normal"><p align='center' style='font-size:12.0pt'>${rs.ghrq }</p></td> 
      <td width=98 colspan=3 class="Normal"><p align='center' style='font-size:12.0pt'>验收人</p></td> 
      <td width=231 colspan=4 valign=top class="Normal"><p align='center' style='font-size:12.0pt'>${rs.ysr }</p></td> 
    </tr> 
    <tr height=0> 
      <td width=57 class="Normal"></td> 
      <td width=34 class="Normal"></td> 
      <td width=14 class="Normal"></td> 
      <td width=119 class="Normal"></td> 
      <td width=49 class="Normal"></td> 
      <td width=7 class="Normal"></td> 
      <td width=21 class="Normal"></td> 
      <td width=54 class="Normal"></td> 
      <td width=16 class="Normal"></td> 
      <td width=28 class="Normal"></td> 
      <td width=28 class="Normal"></td> 
      <td width=26 class="Normal"></td> 
      <td width=37 class="Normal"></td> 
      <td width=139 class="Normal"></td> 
    </tr> 
  </table> 
</div> 
</body>
</center>
<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</html>

