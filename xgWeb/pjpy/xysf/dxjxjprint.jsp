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
style='font-size:18.0pt;font-family:黑体'>信阳师范学院${map.jxjmc }审批表</span></b></p> 
  <table class="printstyle" width="90%"> 
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;项目名称：${map.jxjmc }</th>
    </tr>
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;项&nbsp;&nbsp;目：&nbsp;&nbsp;团体□ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人□</th>
    </tr>
    <tr align="left">
    	<th colspan="8">&nbsp;&nbsp;获奖级别：&nbsp;&nbsp;国家□ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省（部）□
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市厅□&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校级□</th>
    </tr>
    
    <tr align="center">
    	<th colspan="8">申请人基本情况</th>
    </tr>
    <tr align="center"> 
      <th width="12%">申请人</th> 
      <th width="13%"></th> 
      <th width="12%"><bean:message key="lable.xb" /></th> 
      <th width="13%"></th> 
      <th width="13%">专业</th> 
      <th width="13%"></th> 
      <th width="12%">班级</th> 
      <th width="13%"></th> 
    </tr>
     <tr align="center"> 
      <th>参与者</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>专业</th> 
      <th></th> 
      <th>班级</th> 
      <th></th> 
    </tr> 
     <tr align="center"> 
      <th>参与者</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>专业</th> 
      <th></th> 
      <th>班级</th> 
      <th></th> 
    </tr> 
     <tr align="center"> 
      <th>参与者</th> 
      <th></th> 
      <th><bean:message key="lable.xb" /></th> 
      <th></th> 
      <th>专业</th> 
      <th></th> 
      <th>班级</th> 
      <th></th> 
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
    <th><bean:message key="lable.xb" /><br/>评审<br/>意见</th>
      <th colspan=7>
    	<p style="height:50px"></p>
    	<p align="right">
    		（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
       <tr> 
    <th>学校<br/>评审<br/>意见</th>
      <th colspan=7>
    	<p style="height:50px"></p>
    	<p align="right">
    		（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
  </table>
  <table width="90%" class="" border="0">
  	<tr align="left"><th>说明：团体项目申报，由项目负责人申报，鉴定、审批意见由负责人所在<bean:message key="lable.xb" />填写，所得奖金由负责人
负责分发给参与者。
  	</th></tr>
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
