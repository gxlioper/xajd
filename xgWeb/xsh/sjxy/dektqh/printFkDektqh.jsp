<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jtjjknsPrint.jsp' starting page</title>
    	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
  </style>
<center>
<body  class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center'><b><span style='font-size:22.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>第二课堂活动申请与意见反馈书</span></b></p> 
  <table class=tbstyle width=548> 
    <tr> 
      <td width=42 rowspan=4 valign=top class="Normal">
      	<b>
      		申<br>请<br>部<br>分
      	</b>
      </td> 
      <td width=62 valign=top class="Normal"> 	
      	社团联<br>合会主<br>席审批<br>意见
      </td> 
      <td width=444 colspan=4 align="right" valign=bottom class="Normal">
      		签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td> 
    </tr> 
    <tr> 
      <td width=62 valign=top class="Normal"> 
      		学生会<br>主席审<br>批意见
      </td> 
      <td width=444 colspan=4 align="right" valign=bottom class="Normal">
      		签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr> 
    <tr> 
      <td width=506 colspan=5 valign=top class="Normal">
      	管老师是否批准活动执行
      	<logic:empty name="rs" property="xxsh" >
      		<input type=checkbox name="sh" disabled />是
      		<input type=checkbox name="sh" disabled  />否
      	</logic:empty>
      	<logic:equal name="rs" property="xxsh" value="通过">
      		<input type=checkbox name="sh" disabled checked/>是
      		<input type=checkbox name="sh" disabled  />否
      	</logic:equal>
      	<logic:notEqual name="rs" property="xxsh" value="通过">
      		<input type=checkbox name="sh" disabled/>是
      		<input type=checkbox name="sh" disabled  />否
      	</logic:notEqual>
      	<br>
      	<br>
      	<br>
      	<br>
 		<p align=right>签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
 	  </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal"> <p><span style='font-family:宋体'>团委书记<br>审批意见</span></p></td> 
      <td width=433 colspan=3 valign=top class="Normal"> <p align=right>签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
    <tr> 
      <td width=42 rowspan=3 valign=top class="Normal"> 
      	<b>反<br>馈<br>部<br>分</b>
      </td> 
      <td width=74 colspan=2 valign=top class="Normal">
      	实际出<br>席人数
      </td> 
      <td width=144 valign=top class="Normal">&nbsp; </td> 
      <td width=84 valign=top class="Normal">活动效果</td> 
      <td width=205 valign=top class="Normal">
      <logic:empty name="rs" property="hdxg" >
      		优<input type=checkbox name="sh" disabled checked/>
      		良<input type=checkbox name="sh" disabled  />
      		中<input type=checkbox name="sh" disabled  />
      		差<input type=checkbox name="sh" disabled  />
      	</logic:empty>
      <logic:equal name="rs" property="hdxg" value="优">
      		优<input type=checkbox name="sh" disabled checked/>
      		良<input type=checkbox name="sh" disabled  />
      		中<input type=checkbox name="sh" disabled  />
      		差<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="良">
      		优<input type=checkbox name="sh" disabled />
      		良<input type=checkbox name="sh" disabled checked />
      		中<input type=checkbox name="sh" disabled  />
      		差<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="中">
      		优<input type=checkbox name="sh" disabled />
      		良<input type=checkbox name="sh" disabled  />
      		中<input type=checkbox name="sh" disabled  checked/>
      		差<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="差">
      		优<input type=checkbox name="sh" disabled />
      		良<input type=checkbox name="sh" disabled  />
      		中<input type=checkbox name="sh" disabled  />
      		差<input type=checkbox name="sh" disabled  checked/>
      	</logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal">学生会主<br>席事后评<br>价、意见</td> 
      <td width=433 colspan=3 valign=bottom class="Normal">
      	<p align=right>签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal">
      	团委宣<br>传部事<br>后评价、<br>意见
      </td> 
      <td width=433 colspan=3 valign=top class="Normal"> <p><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>图</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:宋体;"Times New Roman"'>片：有（）</span><span lang=EN-US>&nbsp; </span><span
  style='font-family:宋体;
  &quot;Times New Roman&quot;'>否（）</span></p> 
        <p><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>通讯稿：有（）</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:宋体;"Times New Roman"'>否（）</span></p> 
        <p><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>报</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:宋体;"Times New Roman"'>销：同意（）</span><span lang=EN-US>&nbsp; </span><span
  style='font-family:宋体;
  &quot;Times New Roman&quot;'>不同意（）</span></p> 
        <p align=right style='text-align:right'><span
  style='font-family:宋体;
  &quot;Times New Roman&quot;'>签名：＿＿＿＿＿</span></p> 
        <p align=right style='text-align:right'><span
  style='font-family:宋体;
  &quot;Times New Roman&quot;'>日期：＿年＿月＿日</span></p></td> 
    </tr> 
  </table> 
</div> 
  <table width=548>
  <tr>
  <td>
 备注：
 </td></tr>
 <tr>
  <td>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、未经批准的活动不许执行，否则追究直接当事人的责任； </td></tr>
 <tr>
  <td>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、活动结束后，要附“反馈意见单”，方可报销活动经费（必须是发票）</td></tr>
 </table>
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
