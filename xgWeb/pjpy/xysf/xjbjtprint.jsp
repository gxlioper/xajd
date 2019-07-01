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
style='font-size:18.0pt;font-family:黑体'>信阳师范学院先进班集体推荐审批表
</span></b></p> 
  <table class="printstyle"> 
    <tr align="center"> 
      <th width="15%">班级名称</th> 
      <th width="25%">${bjmc } </th> 
      <th width="15%">学生总数</th> 
      <th width="15%">${xsrs }</th> 
      <th width="15%">党、团员数量</th> 
      <th width="15%"></th>
    </tr> 
    <tr align="center"> 
      <th>班主任或辅导员姓名</th> 
      <th>${bzr }</th> 
      <th>班长姓名</th> 
      <th colspan="3">${bzxm }</th>
    </tr> 
    <tr> 
    <th>主<br/>要<br/>事<br/>迹</th>
      <th colspan=5>
    	<p style="height:400px" align="left">${zysj }</p>
  	  </th>
    </tr> 
   
    <tr> 
      <th colspan=6>
    	<p style="height:80px" align="left"><bean:message key="lable.xb" />审核意见：</p>
    	<p align="right">
    		（公章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    		年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;
    	</p>
  	  </th>
    </tr>
    <tr> 
      <th colspan=6>
    	<p style="height:80px" align="left">学校审核意见：</p>
    	<p align="right">
    		（公章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
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
