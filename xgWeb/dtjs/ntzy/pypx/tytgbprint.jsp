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
  		<logic:equal value="yxty" name="mk">
  			南通职业大学“优秀共青团员”表彰人选申报表
  		</logic:equal>
  		<logic:equal value="yxtgb" name="mk">
  			南通职业大学“优秀共青团干部”申报表
  		</logic:equal>
  	</span>
  </p> 
  <table class="tbstyle" width="95%" height="800px"> 
    <tr> 
      <td width="14%"><p align=center>姓&nbsp;&nbsp;&nbsp;名</p></td> 
      <td width="26%">${rs.xm }</td> 
      <td width="13%" colspan=2> <p align=center>性&nbsp;&nbsp;&nbsp;别</p></td> 
      <td width="14%" colspan=2>${rs.xb }</td> 
      <td width="14%"> <p align=center>出生年月</p></td> 
      <td width="17%">${rs.csrq } </td> 
    </tr> 
    <tr> 
      <td width="14%"><p align=center>政治面貌</p></td> 
      <td width="26%">${rs.zzmmmc }</td> 
      <td width="13%" colspan=2> <p align=center>入团时间</p></td> 
      <td width="16%" colspan=2>${rs.rtrq } </td> 
      <td width="16%"> <p align=center>学历</p></td> 
      <td width="13%">${rs.whcd }</td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>
      <logic:equal value="yxtgb" name="mk">
      	所在院系、班级、职务
      </logic:equal>
       <logic:equal value="yxty" name="mk">
      	所在院系、班级
      </logic:equal>
      </p></td> 
      <td width="26%" colspan=7>${rs.xymc } &nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;
	       <logic:equal value="yxtgb" name="mk">
	      	${zw }
	      </logic:equal>
       </td> 
    </tr> 

    <tr> 
      <td width="14%"> <p align=center>个<br/>人<br/>简<br/>历</p></td> 
      <td width="85%" colspan=7>${rs.grjl } </td> 
    </tr> 
    
     <tr> 
      <td width="14%"> <p align=center>获<br/>奖<br/>情<br/>况</p></td> 
      <td width="85%" colspan=7>${rs.hjqk } </td> 
    </tr> 
    <tr> 
      <td width="14%"> <p align=center>事<br/>迹<br/>简<br/>介</p></td> 
      <td width="85%" colspan=7>
      <p style="height:80px">
      	${rs.sqsm }
      </p>
      <p align="right">
      	（另附1000字事迹材料）
      </p> 
     </td> 
    </tr>
    <tr> 
      <td width="14%"> <p align=center>团支<br/>部意<br/>见</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负责人（签名）</p>
        <p align="right">
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>院系<br/>团总<br/>支意<br/>见</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr>  
    <tr> 
      <td width="14%"> <p align=center>院系<br/>党组<br/>织意<br/>见</p></td> 
      <td width="34%" colspan=2> 
      	<p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      <td width="8%" colspan=2> <p align=center>团委<br/>意见</p></td> 
      <td width="42%" colspan=3>
        <p style="height: 80px"> &nbsp;</p> 
        <p align="right">
        	（盖章）&nbsp;&nbsp;&nbsp;&nbsp;<br/>
       		 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
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
