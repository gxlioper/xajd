<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
<title>学生违纪处分呈报审批表</title>
<style>
<!--
.Section1
	{page:Section1;}
-->
</style>
<style media='print'>
		.noPrin{display:none;}
	</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<body bgcolor="#FFFFFF" class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center; font-weight: bold; font-size: 18px;'><span style='font-family:宋体'>学生违纪处分呈报审批表</span></p> 
  <div align=center> 
    <table class="printstyle" align=center width="100%" > 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>姓名</span></p></th> 
        <td width=140 class="Normal" align="center">&nbsp;${rs.xm } </td> 
        <th width=58 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>性别</span></p></th> 
        <td width=109 class="Normal" align="center">&nbsp;${rs.xb } </td> 
        <th width=73 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>出生年月</span></p></th> 
        <td width=128 class="Normal" align="center">&nbsp; ${rs.csrq }</td> 
      </tr> 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>籍贯</span></p></th> 
        <td width=140 class="Normal" align="center">&nbsp; ${rs.jg }</td> 
        <th width=58 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>班级</span></p></th> 
        <td width=109 class="Normal" align="center">&nbsp; ${rs.bjmc }</td> 
        <th width=73 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>学号</span></p></th> 
        <td width=128 class="Normal" align="center">&nbsp; ${rs.xh }</td> 
      </tr> 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>违纪事实</span></p></th> 
        <td width=507 colspan=5 class="Normal"> <p>&nbsp;</p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p></td> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align="left"><span
  style='font-size:9.0pt;line-height:150%;font-family:宋体'><bean:message key="lable.xsgzyxpzxy" />处理依据及处理意见：</span></p> 
          <p align=center style='text-align:center;
  word-break:break-all'>&nbsp;${rs.xyclyj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'>&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:宋体'><bean:message key="lable.xsgzyxpzxy" />领导签名：${rs.fsjname }<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'><bean:message key="lable.xsgzyxpzxy" />已做思想工作，学生于<span lang=EN-US>&nbsp;&nbsp;${rs.year }&nbsp; </span>年<span lang=EN-US>&nbsp;&nbsp;${rs.month }&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;${rs.date }&nbsp; </span>日<span lang=EN-US>&nbsp;&nbsp; </span>确认<span lang=EN-US>&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align=left style='text-align:left;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>学生处处理意见：</span></p>
          <p align=center style='text-align:center;
  '>&nbsp;${rs.xxclyj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:宋体'>学生处领导签名：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>学生处盖章：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p> 
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>日<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align=left style='text-align:left;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  宋体'>学校审批意见：</span></p> 
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:宋体'>主管校领导签名：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>日<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
    </table> 
  </div> 
</div> 
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
    </div>
</body>

</html>