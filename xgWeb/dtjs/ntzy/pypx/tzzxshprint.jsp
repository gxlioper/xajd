<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align="center"> 
  <p align=center>
  	<span style='font-size:18.0pt;font-family:华文中宋;color:black;'>
  		<logic:equal value="tzzgb" name="mk">
  			优秀团总支干部（标兵）推荐表
  		</logic:equal>
  		<logic:equal value="xshgb" name="mk">
  			优秀学生会干部（标兵）推荐表
  		</logic:equal>
  	</span>
  </p> 
  <table class="tbstyle" width="95%" height="800px"> 
    <tr> 
      <td width="14%"><p align=center>姓&nbsp;&nbsp;&nbsp;名</p></td> 
      <td width="26%">${rs.xm }</td> 
      <td width="13%" colspan=2> <p align=center>性&nbsp;&nbsp;&nbsp;别</p></td> 
      <td width="14%" colspan=2>${rs.xb }</td> 
      <td width="14%"> <p align=center>民&nbsp;&nbsp;&nbsp;族</p></td> 
      <td width="17%">${rs.mzmc } </td> 
    </tr> 
    <tr> 
      <td width="14%"><p align=center>出生年月</p></td> 
      <td width="26%">${rs.csrq }</td> 
      <td width="13%" colspan=2> <p align=center>箱&nbsp;&nbsp;&nbsp;贯</p></td> 
      <td width="16%" colspan=2>${rs.jg } </td> 
      <td width="16%"> <p align=center>政治面貌</p></td> 
      <td width="13%">${rs.zzmmmc }</td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>院&nbsp;&nbsp;&nbsp; 系</p></td> 
      <td width="26%">${rs.xymc } </td> 
      <td width="13%" colspan=2> <p align=center>所在班级</p></td> 
      <td width="45%" colspan=4>${rs.bjmc } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>手&nbsp;&nbsp;&nbsp; 机</p></td> 
      <td width="26%">${rs.sjhm } </td> 
      <td width="13%" colspan=2> <p align=center>入团时间</p></td> 
      <td width="16%" colspan=2>${rs.rtrq } </td> 
      <td width="16%"> <p align=center>入党时间</p></td> 
      <td width="13%">${rs.rdsj } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>担任职务</p></td> 
      <td width="85%" colspan=7>${zw } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>简<br/>历</p></td> 
      <td width="85%" colspan=7>${rs.grjl } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>主<br/>要<br/>表<br/>现</p></td> 
      <td width="85%" colspan=7>
      <p style="height:80px">
      	${rs.sqsm }
      </p></td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>奖惩<br/>情况</p></td> 
      <td width="85%" colspan=7>${rs.hjqk} </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>所<br/>在<br/>院<br/>系<br/>意<br/>见</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>团<br/>委<br/>意<br/>见</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>备注</p></td> 
      <td width="85%" colspan=7>
      	<p style="height: 60px">${rs.bz }</p>
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
</body>
</html>
