<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/xgxt/skin1/style/main1.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>	
	<body>		
		<html:form action="/qgzxZgdzdx" method="post">
		<p align="center"><strong>	广州大学学生勤工助学岗位申请表 </strong></p>
		<p align="center"><strong>学 院： </strong>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong>填 表 时 间</strong></p>
	<div align="center">
	  <table class="tbstyle">
	    <tr>
	      <td width="72"><p align="center"><strong>姓 </strong><strong></strong><strong>名 </strong><strong></strong></p></td>
	      <td width="72"><p><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>性别 </strong><strong></strong></p></td>
	      <td width="48"><p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>班级 </strong><strong></strong></p></td>
	      <td width="84"><p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="96"><p align="center"><strong>学号 </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>家 </strong><strong></strong><strong>庭 </strong><strong></strong><strong>地 </strong><strong></strong><strong>址 </strong><strong></strong></p></td>
	      <td width="96" colspan="2"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>宿舍 </strong><strong></strong></p></td>
	      <td width="84"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="96"><p align="center"><strong>联系电话 </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>上学期不及格科目 </strong><strong></strong></p></td>
	      <td width="144" colspan="3"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="180" colspan="2"><p align="center"><strong>有无受过处分 </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>是否欠交学费、金额 </strong></p></td>
	      <td width="144" colspan="3"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="180" colspan="2"><p align="center"><strong>是否获得奖学金、金额 </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p><strong>本 </strong><strong></strong><strong>人 </strong><strong></strong><strong>家 </strong><strong></strong><strong>庭 </strong><strong></strong></p>
	          <p><strong>经 </strong><strong></strong><strong>济 </strong><strong></strong><strong>状 </strong><strong></strong><strong>况 </strong><strong></strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>学 </strong><strong></strong><strong>习 </strong><strong></strong><strong>情 </strong><strong></strong><strong>况 </strong><strong></strong></p>
	          <p align="center">（上学期各科成绩） </p></td>
	      <td width="480" colspan="6"><p align="center"><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>有何特长 </strong><strong></strong></p></td>
	      <td width="480" colspan="6"><p align="center">1. 书法 2. 绘画 3. 电脑打字 4. 网页制作 5. 写作 6. 其它 __________ </p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>可从事何种工作 </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2" valign="top"><p><strong>&nbsp; </strong></p>
	          <p><strong>学 </strong><strong></strong><strong>院 </strong><strong></strong><strong>意 </strong><strong></strong><strong>见 </strong>（请对学生学习、经济情况审核） <strong></strong></p></td>
	      <td width="480" colspan="6" valign="bottom"><p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>领导签名： </strong></p>
	          <p align="right"><strong>（<bean:message key="lable.xsgzyxpzxy" />公章） </strong></p>
	          <p align="right"><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>学 </strong><strong></strong><strong>生 </strong><strong></strong><strong>处 </strong><strong></strong><strong>意 </strong><strong></strong><strong>见 </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="bottom"><p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>备注 </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p></td>
	    </tr>
	  </table>
	</div>
<p align="center"><strong>&nbsp; </strong></p>
<p align="left">
	<strong>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	附：每周可工作时间 
	</strong></p>
<div align="center">
  <table class="tbstyle">
    <tr>
      <td width="120"><p align="center">&nbsp; </p></td>
      <td width="96"><p align="center">星期一 </p></td>
      <td width="98"><p align="center">星期二 </p></td>
      <td width="108"><p align="center">星期三 </p></td>
      <td width="106"><p align="center">星期四 </p></td>
      <td width="98"><p align="center">星期五 </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">第一大节 </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">第二大节 </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">第三大节 </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">第四大节 </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
  </table>
</div>

<p align="center">注： 只需在你的可工作时间段处打“√”，个别时段有特殊情况的请注释说明。 </p>		
<div class='noPrin' align="center">
	<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:form>
</body>
</html>
