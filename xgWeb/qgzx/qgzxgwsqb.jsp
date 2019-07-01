<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.utils.String.StringUtils"/>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="奖学金.files/editdata.mso">
<title>勤工助学岗位申请表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>

<body bgcolor="#FFFFFF" lang=ZH-CN> 
<div align="center"> 
<table width="95%" border="0" id="theTable" align="center">
<tr>
  <td align="center">
  <p align=center style='text-align:center'><b><span
style='font-size:16.0pt;font-family:宋体'>${xxmc}学生勤工助学岗位申请表</span></b></p> 
  <div align=center> 
    <table  class="printstyle" border="0" align="center" style="width: 95%">
      <tr height="40px">
        <td width=96 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>姓名</span></p></td> 
        <td width=113 colspan=4>&nbsp;${rs.xm }</td> 
        <td width=79 colspan=3> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>学号</span></p></td> 
        <td width=116 colspan=2>&nbsp;${rs.xh }</td> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>专业班级</span></p></td> 
        <td width=90>&nbsp;${rs.zymc }&nbsp;${rs.bjmc }</td> 
        <td width=110 rowspan=3> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:125;height:150" />
		</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=96 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>性别</span></p></td> 
        <td width=66 colspan=2>&nbsp;${rs.xb } </td> 
        <td width=47 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>民族</span></p></td> 
        <td width=79 colspan=3>&nbsp; ${rs.mzmc } </td> 
        <td width=116 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>出生日期</span></p></td> 
        <td width=169 colspan=2> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:宋体'>
  			<%HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs");
  			if(StringUtils.isNull(rs.get("csrq"))){
  			 %>
  			 &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
  			<%
  			}else{
  			%>
  			<%=rs.get("csrq").substring(0,4)%>年
  			<%=rs.get("csrq").substring(4,6)%>月
  			<%=rs.get("csrq").substring(6,8)%>日
  			<%} %>
  		</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>政治面貌</span></p></td> 
        <td width=126 colspan=5>&nbsp; ${rs.zzmmmc }</td> 
        <td width=116 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>任何学生干部职务</span></p></td> 
        <td width=169 colspan=2>&nbsp; </td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4 rowspan=2> <p align=center style='text-align:center;line-height:110%;
  layout-grid-mode:char'><span style='font-size:12.0pt;line-height:110%;
  font-family:宋体'>在院内参加过何种勤工助学岗位</span></p></td> 
        <td width=242 colspan=7 rowspan=2>&nbsp; </td> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>联系电话</span></p></td> 
        <td width=199 colspan=2>&nbsp;</td> 
      </tr> 
      <tr height="40px"> 
        <td width=79> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>是否经认定为家庭经济困难学生</span></p></td> 
        <td width=199 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>□是<span lang=EN-US>&nbsp; </span>□否</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>爱好及特长</span></p></td> 
        <td width=521 colspan=10>&nbsp; </td> 
      </tr> 
      <tr height="40px"> 
        <td width=162 colspan=4> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>拟申请勤工助学岗位（院内）</span></p></td> 
        <td width=321 colspan=8> <p><span lang=EN-US style='font-size:12.0pt;font-family:宋体'>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3.</span></p></td> 
        <td width=90> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>是否服从调配</span></p></td> 
        <td width=110> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>□是<span lang=EN-US>&nbsp; </span>□否</span></p></td> 
      </tr> 
      <tr height="40px"> 
        <td width=404 colspan=11> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>请在空闲时间后划“√”</span></p></td> 
        <td width=278 colspan=3 rowspan=6 valign=top> <p><b><span style='font-size:14.0pt;
  font-family:楷体_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;本人在参加勤工助学工作中，严格遵守《广西工学院鹿山学院勤工助学管理办法》及<bean:message key="lable.xb" />的其他相关规定，同时认真履行岗位职责。</span></b></p> 
          <p align="right"><span style='font-size:14.0pt;
  font-family:楷体_GB2312'>学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right" style='word-break:break-all'><span
  style='font-size:14.0pt;font-family:楷体_GB2312'>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp; </span>日&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=44>&nbsp; </td> 
        <td width=58 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周一</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周二</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周三</span></p></td> 
        <td width=60> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周四</span></p></td> 
        <td width=60 colspan=2> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周五</span></p></td> 
        <td width=60> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>周六</span></p></td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:宋体'>第一二节</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:宋体'>第三四节</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:宋体'>第五六节</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=44> <p align=center style='text-align:center'><span
  style='font-family:宋体'>第七八节</span></p></td> 
        <td width=58 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
        <td width=60 colspan=2>&nbsp; </td> 
        <td width=60>&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:宋体'>所在系意见：</span></p> 
          <p  align="right"><span style='font-size:12.0pt;
  font-family:宋体'>签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p style='word-break:break-all'  align="right"><span
  style='font-size:12.0pt;font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span
  lang=EN-US>&nbsp;&nbsp; </span>日&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:宋体'>用工单位意见：</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:宋体'>盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp; </span>日&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr> 
        <td width=683 colspan=14 valign=top> <p><span style='font-size:12.0pt;font-family:宋体'>学生资助管理中心意见</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:宋体'>盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="right"><span style='font-size:12.0pt;
  font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp; </span>日&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
      </tr> 
      <tr height=0> 
        <td width=44></td> 
        <td width=52></td> 
        <td width=6></td> 
        <td width=59></td> 
        <td width=1></td> 
        <td width=46></td> 
        <td width=14></td> 
        <td width=60></td> 
        <td width=5></td> 
        <td width=56></td> 
        <td width=60></td> 
        <td width=79></td> 
        <td width=90></td> 
        <td width=110></td> 
      </tr> 
    </table> 
  </div> 
  </td>
 </tr>
 <tr>
 	<td align="center" >
 		<table width="90%" border="0" id="theTable" align="center">
 		<tr>
 		  <td>
 		  <p align="left"><b><span style='font-family:
			宋体'>备注：1.</span>此表是取得应聘资格同学参加双选会的凭证。2.</span>取得此表的同学需妥善保管，应聘时向用人单位投递<u>此表</u>。<span
			lang=EN-US>3.</span>若在此次双选会中未被录用者，请及时将此表原件交到院学生资助管理中心入库存档，一有岗位空缺将优先推荐。</span></b></p> 
			</td>
		</tr>
		</table>
 	</td>
 </tr>
</table>

	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
   
    </div>
    <p>&nbsp;</p>
</body>
</html>

