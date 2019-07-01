<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.HashMap"%>
<%@page import="xgxt.utils.String.StringUtils"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>“真维斯大学生助学基金”</title>
<!-- 打印控件begin -->
		<object id="WebBrowser" height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
</head>
<body> 
<p><span style='font-size:14.0pt;font-family:楷体_GB2312;color:black'>附1：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p> 
<div align="center"> 
  <p><b>“真维斯基金”申请表</b></p> 
  <table class="tbstyle" width="85%" height="800px"> 
    <tr align="center"> 
      <td colspan=2 width="15%"> 姓&nbsp; 名</td> 
      <td colspan=3 width="35%">${rs.xm } </td> 
      <td colspan=3 width="10%">性&nbsp; 别</td> 
      <td colspan=3 width="20%">${rs.xb }</td> 
      <td colspan=2 rowspan=3 width="30%"> 
      	<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }" border="0" align="absmiddle"  />
  		</td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>姓名汉语拼音</td> 
      <td colspan=9>${rs.zwpy } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>出生日期</td> 
      <td colspan=3>
     
		<!-- 获得年月日    -->
      <%
     		HashMap<String,String> map = (HashMap<String, String>)request.getAttribute("rs");
     		String csrq = map.get("csrq");
     		if(StringUtils.isNotNull(csrq) && csrq.length() == 8 ){
 				String year = csrq.substring(0,4);
 				String month = csrq.substring(4,6);
 				String day = csrq.substring(6);
 				pageContext.setAttribute("year",year);
 				pageContext.setAttribute("month",month);
 				pageContext.setAttribute("day",day);    		
     		}
       %>
      <u>&nbsp;&nbsp;${year }&nbsp;&nbsp; </u>年
      <u>&nbsp;&nbsp;${month }&nbsp;&nbsp; </u>月
      <u>&nbsp;&nbsp;${day }&nbsp;&nbsp; </u>日
      </td> 
      <td colspan=3><p align=center>民&nbsp; 族</p></td> 
      <td colspan=3>${rs.mzmc } </td>
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>家庭地址</p></td> 
      <td colspan=6>${rs.jtdz } </td> 
      <td colspan=3> <p align=center>邮政编码</p></td> 
      <td colspan=2>${rs.jtyb } </td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=4> 
	  	<p align=center>就<br/>读<br/>学<br/>校</p></td> 
      <td colspan=2> <p align=center>院校名称</p></td> 
      <td colspan=5>${xxmc } </td> 
      <td colspan=2> <p align=center>系</p></td> 
      <td colspan=3>${rs.xymc } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>专&nbsp;&nbsp;&nbsp; 业</p></td> 
      <td colspan=5>${rs.zymc } </td> 
      <td colspan=2> <p align=center>班&nbsp;&nbsp; 级</p></td> 
      <td colspan=3>${rs.bjmc } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2> <p align=center>学校通讯地址</p></td> 
      <td colspan=6>${rs.xxdz } </td> 
      <td colspan=3> <p align=center>邮政编码</p></td> 
      <td>${rs.xxyb } </td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3> <p align=center>联系电话（区号）</p></td> 
      <td colspan=3>${rs.xxlxdh } </td> 
      <td colspan=3> <p align=center>学生宿舍电话</p></td> 
      <td colspan=3>${rs.qsdh } </td> 
    </tr> 
    <tr align="center" height="150px"> 
      <td> <p align=center>申<br/>请<br/>理<br/>由</p></td> 
      <td colspan=12 valign=top> <p class=MsoBodyText2 style='height: 100px'><b>（请详细说明父母职业、家庭人口、收入来源情况；另请注明学费来源，包括学费减免及获得奖学金、助学金状况。——可另附页填写）</b></p> 
        <p align=center>申请人签名：
        <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>&nbsp;&nbsp; 时间：&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</p></td> 
    </tr> 
    <tr align="center" height="150px"> 
      <td> <p align=center>申<br/>请<br/>人<br/>承<br/>诺</p></td> 
      <td colspan=12 valign=top align="left" >
      	<p style="height: 100px">1、参加真维斯店铺“定点”实习工作，每次不少于8个小时；大一学生每学年不少于40个小时，大二、大三学生每学年不少于80个小时;<br/>
      		原则上安排在“五一”、“十一”、寒假和暑假等节假日集中完成，特殊情况可由受助生与真维斯店协商确定；勤工俭学期间遵守真维斯店各项规章制度，同时享受普通计时工各项待遇。
      		2、学习期间积极参加各种社会公益活动；毕业工作后，自愿向基金办公室提供联络方式,在有能力时自愿向希望工程捐资，帮助其他需要帮助的学生。</p> 
        <p align=center>承诺人签名：
		 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>
		&nbsp;&nbsp; 时间：&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日</p>  
        </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td> <p align=center>初<br/>评<br/>意<br/>见</p></td> 
      <td colspan=12> 
      <p align=center style="height: 80px"></p>
      <p> 院系评估人：
       <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </u>
     	&nbsp;&nbsp; 时间：&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p></td> 
    </tr> 
    <tr align="center" height="100px"> 
      <td> <p align=center>复<br/>审<br/>意<br/>见</p></td> 
      <td colspan=6> 
      <p style="height: 60px">&nbsp;&nbsp;</p>
      <p align="left">复审人：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>（大学团委和学工部盖章）<br/>
      	 时间：&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日
      </p>
     </td> 
      <td colspan=6 valign=top> 
      <p style="height: 60px">&nbsp;&nbsp;</p>
      <p align="left">终审人：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>（省级青基会盖章）<br/>
      	 时间：&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日
      </p></td> 
    </tr> 
  
  </table> 
  
  	<table border="0" width="85%">
  	<tr>
  	<td align="left">
  	<p><font size="3">注：此表可以复制，一式三份，大学、真维斯店铺和中国青基会各存一份原件，省级青基会留复印件。</font></p> 
  	</td>
  	</tr>
  	</table>
  	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</div> 
</body>
</html>
