<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align=center> 
	<p align=center style='text-align: center'>
		<b><span style='font-size: 18.0pt; font-family: 宋体;'>何康农业教育科研基金资助申请表</span>
		</b>
	</p>
  <table class="tbstyle" width="90%"> 
    <tr align="center"> 
      <td width="12.5%">姓名</td> 
      <td colspan=2 width="25%">${rs.xm }</td> 
      <td width="12.5%">性别</td> 
      <td width="12.5%">${rs.xb }</td> 
      <td width="12.5%">民族</td> 
      <td colspan=2 width="25%">${rs.mzmc }　</td> 
    </tr> 
    <tr align="center"> 
      <td>院校</td> 
      <td colspan=4>${xxmc }${rs.xymc }</td> 
      <td>身份证号码</td> 
      <td colspan=2>${rs.sfzh }</td> 
    </tr> 
    <tr align="center"> 
      <td>专业</td> 
      <td colspan=4>${rs.zymc }</td> 
      <td>入学时间</td> 
      <td colspan=2>${rs.rxrq }</td> 
    </tr> 
    <tr align="center"> 
      <td colspan=3>院校通讯地址（含邮编）</td> 
      <td colspan=5></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=2>院校联系人</td> 
      <td colspan=2></td> 
      <td colspan=2>联系电话（含区号）</td> 
      <td colspan=2></td> 
    </tr> 
    <tr align="center"> 
    	<td>
    		<br/>申<br/>请<br/>人<br/>品<br/>学<br/>情<br/>况<br/>简<br/>介<br/>
    	</td>
    	<td colspan="7">
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			本人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
    		</p>
    	</td>
    </tr>
    	
    <tr align="center"> 
    	<td>
    		<p align="center"><br/>院<br/>系<br/>︵<br/>专<br/>业<br/>︶<br/>意<br/>见<br/></p>
    	</td>
    	<td colspan="7">
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
    		</p>
    	</td>
    </tr> 
    
    <tr align="center"> 
 		<td>
 			<br/>学<br/>校<br/>意<br/>见<br/>
 		</td>
 		<td colspan="7">
 		
 			<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			校长（签字）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
    		</p>
 		</td>
    </tr> 
    
    <tr align="center">
    	<td>
    		<br/>基<br/>金<br/>会<br/>意<br/>见<br/>
    	</td>
    	<td colspan="7">
    	
    		<p align="left">
    			&nbsp;&nbsp;
    		</p>
    		
    		<br/><br/>
    		
    		<p align="right">
    			签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
    		</p>
    	</td>
    </tr> 
  </table>
  <br/><br/><br/>
  
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
