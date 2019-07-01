<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body>
    <html:form action="/dxjxjsp">
     
<br/>
<div>

<table width="98%" border="0">

	<tr>
		<th align="left"><p>____________ ：（教学单位） </p></th>
	</tr>
	<tr>
		<th height="22" align="left"> 本人在校学习期间向中国工商银行西湖支行申办了国家助学贷款。毕业离校 </th>
	</tr>
	<tr>
		<th align="left"> 前尚有贷款余额 ______ 元，按照本人与银行签订的助学贷款确认书，将于 ____ </th>
	</tr>
	<tr>
		<th align="left"> 年 ____ 月开始还本付息，至 ____ 年 ____ 月全部还清贷款，我的还贷账号 </th>
	</tr>
	<tr>
		<th align="left"> 是 。特此郑重承诺：本人保证严格履行贷款合同以 </th>
	</tr>
	<tr>
		<th align="left"> 及贷款确认书的约定。认真填写毕业生贷后管理信息采集表，愿意在未还清贷款 </th>
	</tr>
	<tr>
		<th align="left"> 之前与<bean:message key="lable.xsgzyxpzxy" />贷款见证人（或<bean:message key="lable.xsgzyxpzxy" />辅导员） ____________ 保持联系，并保证及时将 </th>
	</tr>
	<tr>
		<th align="left"> 自己变动后的联系方式主动告诉联系人。 </th>
	</tr>
	<tr>
		<th align="left"><p>本承诺书一式 三份 ，贷款毕业生、教学单位、学生处各持一份。 </p></th>
	</tr>
	<tr>
		<th align="left"><p>教学单位联系人及电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 承诺人：（签字） </p>
	    <p align="right">年 月 日 </p></th>
	</tr>
</table>
 <div align="center" style="font-size:18px;font:'黑体' "><b>国家助学贷款毕业生个人信息采集表</b></div>
<table width="98%" class="printstyle">
  <tr>
    <th width="15%"><div align="center">姓名</div></th>
    <th width="20%"><div align="center">${rs.xm }</div></th>
    <th width="15%"><div align="center">性别</div></th>
    <th width="15%"><div align="center">${rs.xb }</div></th>
    <th width="15%"><div align="center">毕业年月</div></th>
    <th width="20%"><div align="center">${rs.byny }</div></th>
  </tr>
  <tr>
    <th><div align="center">身份证号</div></th>
    <th colspan="2"><div align="center">${rs.sfzh }</div>      <div align="center"></div></th>
    <th><div align="center">学号</div></th>
    <th colspan="2"><div align="center">${rs.xh }</div></th>
  </tr>
  <tr>
    <th><div align="center">家庭地址</div></th>
    <th colspan="3"><div align="center">${rs.jtzz }</div>      <div align="center"></div>      
      <div align="center"></div></th>
    <th><div align="center">邮政编码</div></th>
    <th><div align="center">${rs.yzbm }</div></th>
  </tr>
  <tr>
    <th><div align="center">父亲姓名</div></th>
    <th colspan="2"><div align="center">${rs.fqxm }</div></th>
    <th><div align="center">父亲联系电话</div>      <div align="center"></div></th>
    <th colspan="2"><div align="center">${rs.fqdh }</div></th>
  </tr>
  <tr>
    <th><div align="center">父亲工作单位</div></th>
    <th colspan="5"><div align="center">${rs.fqgzdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">母亲姓名</div></th>
    <th colspan="2"><div align="center">${rs.mqxm }</div></th>
    <th><div align="center">母亲联系电话</div></th>
    <th colspan="2"><div align="center">${rs.mqdh }</div></th>
  </tr>
  <tr>
    <th><div align="center">母亲工作单位</div></th>
    <th colspan="5"><div align="center">${rs.mqgzdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">首次毕业去向</div></th>
    <th colspan="5"><div align="center">${rs.brjyqxhdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">当前工作单位及地址</div></th>
    <th colspan="5"><div align="center">${rs.dqgzdwjdz }</div></th>
  </tr>
  <tr>
    <th><div align="center">当前工作单位邮编</div></th>
    <th colspan="2"><div align="center">${rs.dqgzdwyb }</div>      <div align="center"></div></th>
    <th> <div align="center">当前工作单位电话 </div></th>
    <th colspan="2"><div align="center">${rs.dqgzdwdh }</div>      <div align="center"></div></th>
  </tr>
  <tr>
    <th> <div align="center">目前的有效联系方式 </div></th>
    <th colspan="5"><p align="center">家庭电话：${rs.jtgddh } 手机：${rs.lxdh } </p>
      <div align="center">邮箱及QQ 号：${rs.brdzyxjdzlxfs } </div></th>
  </tr>
  <tr>
    <th> <div align="center">联系方式变更情况 </div></th>
    <th colspan="5"><div align="center">${rs.lxfsbgqk }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div></th>
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
<!-- 注：此表一式两份，系（院）、院学生处各一份 -->
    </div>
    </html:form>
  </body>
</html:html>
