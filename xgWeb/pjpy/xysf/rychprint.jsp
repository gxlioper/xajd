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
<html:form action="/data_search" method="post">
<div class=Section1 style='layout-grid:15.6pt' align="center"> 
  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:黑体'>信阳师范学院三好学生、模范干部<br/>推 荐 审 批 表
</span></b></p> 
  <table class="printstyle" width="90%"> 
    <tr align="center"> 
      <th width="8%">姓 名</th> 
      <th width="15%">${map.xm } </th> 
      <th width="8%">性 别</th> 
      <th width="15%">${map.xb }</th> 
      <th width="8%">学号</th> 
      <th width="19%">${map.xh }</th> 
      <th width="8%">政治面貌</th> 
      <th width="19%">${zzmmmc }</th> 
    </tr> 
    <tr align="center"> 
      <th>所在<bean:message key="lable.xb" />班级</th> 
      <th colspan="3">${map.xymc} &nbsp;&nbsp;${map.bjmc }</th> 
      <th>申报类别</th> 
      <th colspan="3">${rych }</th>
    </tr> 
    <tr> 
    <th>主<br/>要<br/>事<br/>迹</th>
      <th colspan=7>
    	<p style="height:225px" align="left">&nbsp;&nbsp;&nbsp;&nbsp;${zysj }</p>
    	
  	  </th>
    </tr> 
   <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left">
    	班主任意见：
    	</p>
    	<p align="right">
    		签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr> 
    <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left"><bean:message key="lable.xb" />审核意见：</p>
    	<p align="right">
    		（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
    <tr> 
      <th colspan=8>
    	<p style="height:50px" align="left">学校审核意见：</p>
    	<p align="right">
    		（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>  
  </table> 
  <table width="90%" border="0"> 
  	<tr align="left"><th>说明：“申报类别”一栏填写“三好学生”或“模范干部”</th></tr>
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
	</html:form>
</body>
</html>
