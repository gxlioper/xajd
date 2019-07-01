<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
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
<div class=Section1 style='layout-grid:15.6pt' align="center"> 
  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:黑体'>信阳师范学院综合奖学金审批表</span></b></p> 
  <table class="printstyle"> 
    <tr align="center"> 
      <th width="8%">姓 名</th> 
      <th width="15%">${map.xm } </th> 
      <th width="8%">性 别</th> 
      <th width="15%">${map.xb }</th> 
      <th width="8%"><bean:message key="lable.xb" /></th> 
      <th width="19%">${map.xymc }</th> 
      <th width="8%">专业</th> 
      <th width="19%">${map.zymc }</th> 
    </tr> 
    <tr align="center"> 
      <th>班级</th> 
      <th>${map.bjmc }</th> 
      <th colspan="2">综合测评名次</th> 
      <th>${pm.zhszcpzfpm }</th>
      <th>学号</th>
      <th colspan="2">${map.xh }</th> 
    </tr> 
    <tr> 
      <th>奖学金等次</th> 
      <th colspan=7>
      	<p style="heght:60px">
      		${map.jxjmc }
      	</p>
      </th> 
    </tr> 
    <tr> 
    <th>班<br/>级<br/>鉴<br/>定</th>
      <th colspan=7>
    	<p style="height:150px"></p>
    	<p align="right">
    		班主任签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
   <tr> 
    <th>学<br/>院<br/>评<br/>审<br/>意<br/>见</th>
      <th colspan=7>
    	<p style="height:100px"></p>
    	<p align="right">
    		（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
    <tr> 
    <th>学<br/>校<br/>评<br/>审<br/>意<br/>见</th>
      <th colspan=7>
    	<p style="height:100px"></p>
    	<p align="right">
    		（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
  </table> 
	</div>
	<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html>
