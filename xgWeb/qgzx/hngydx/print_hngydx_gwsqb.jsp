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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	
	<body>
	<html:form action="/xsqgzx.do" method="post">		
		<h3 align="center">${xxmc}勤工助学申请表</h3>		
		<p>　
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		填表时间：${rs.sj}
<%--		${rs.year}年${rs.month}月${rs.day}日   --%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		顺序号______ </p>
		<table cellspacing="0" cellpadding="0" align="center" class="tbstyle" >
  <tr>
    <td width="72" colspan="2"><p align="center">姓　名 </p></td>
    <td width="173" colspan="6"><p align="center">${rs.xm} </p></td>
    <td width="140" colspan="7"><p align="center">政治面貌 </p></td>
    <td width="65" colspan="4"><p>${rs.zzmmmc} </p></td>
    <td width="48" colspan="4"><p>性 别 </p></td>
    <td width="50" colspan="3"><p>${rs.xb} </p></td>
    <td width="106" colspan="2" rowspan="7"><p align="center">贴 </p>
        <p align="center">相 </p>
        <p align="center">片 </p>
        <p align="center">处 </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">学　号 </p></td>
    <td width="173" colspan="6"><p align="center">${rs.xh} </p></td>
    <td width="140" colspan="7"><p align="center">专业班级 </p></td>
    <td width="163" colspan="11"><p>${rs.zymc}${rs.bjmc} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">宿舍号 </p></td>
    <td width="173" colspan="6"><p align="center">${rs.ssbh}</p></td>
    <td width="140" colspan="7"><p align="center">联系电话 </p></td>
    <td width="163" colspan="11"><p align="center">${rs.lxdh} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">籍　贯 </p></td>
    <td width="477" colspan="24"><p align="center">${rs.jg} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">申请意向 </p></td>
    <td width="477" colspan="24"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="151" colspan="5" rowspan="2"><p>是否愿意参加校外勤工助学岗位（在项目上打勾） </p></td>
    <td width="288" colspan="13"><p align="center">家教（年级／课程） </p></td>
    <td width="32" colspan="3" rowspan="2"><p align="center">家政 </p></td>
    <td width="26" rowspan="2"><p align="center">餐饮 </p></td>
    <td width="26" colspan="3" rowspan="2"><p align="center">促销 </p></td>
    <td width="26" rowspan="2"><p align="center">推销 </p></td>
  </tr>
  <tr>
    <td width="288" colspan="13"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p>有何特长 </p></td>
    <td width="552" colspan="25"><p align="center">${rs.yhtc} </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p>岗位记录 </p></td>
    <td width="552" colspan="25"><p align="center">${rs.gwjl} </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p align="center">家庭经济情况 </p>
        <p align="center">（此栏为贷款中心填写） </p></td>
    <td width="41"><p align="center">不困难 </p></td>
    <td width="43" colspan="2"><p align="center">一般困难 </p></td>
    <td width="39"><p align="center">困难 </p></td>
    <td width="41" colspan="3"><p align="center">特殊困难 </p></td>
    <td width="51" colspan="2"><p align="center">学习成绩情况 </p></td>
    <td width="45" colspan="2"><p align="center">优秀 </p></td>
    <td width="45" colspan="2"><p align="center">良好 </p></td>
    <td width="45" colspan="4"><p align="center">合格 </p></td>
    <td width="45" colspan="3"><p align="center">不及格 </p></td>
    <td width="156" colspan="5" valign="top"><p>不及格科目备注：</p></td>
  </tr>
  <tr>
    <td width="187" colspan="6"><p>在校期间获奖情况：需填写获奖名称、时间、金额。 </p></td>
    <td width="468" colspan="22"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="187" colspan="6"><p>在校期间受助情况：需填写贷款时间、金额或助学金名称、时间、金额。</p></td>
    <td width="468" colspan="22"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="55" rowspan="6"><p align="center">可用勤工助学的时间 </p></td>
    <td width="48" colspan="2"><p>&nbsp; </p></td>
    <td width="84" colspan="3"><p align="center">周一 </p></td>
    <td width="74" colspan="3"><p align="center">周二 </p></td>
    <td width="82" colspan="4"><p align="center">周三 </p></td>
    <td width="77" colspan="4"><p align="center">周四 </p></td>
    <td width="79" colspan="7"><p align="center">周五 </p></td>
    <td width="79" colspan="3"><p align="center">周六 </p></td>
    <td width="76"><p align="center">周日 </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">1-2 节 </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">3-4 节 </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">5-6 节 </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">7-8 节 </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">晚 上 </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr height="40px">
    <td width="55">
       <p>辅导员</p>
       <p>意见</p></td>
    <td width="240" colspan="10">
        <p>&nbsp; </p>
        <p>&nbsp; </p>
        <p align="right">签名盖章 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </p>
        <p align="right">年　月　日 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </p></td>
    <td width="48" colspan="2"><p align="center">贷款中心 </p>
        <p align="center">意见 </p></td>
    <td width="312" colspan="15"><p>&nbsp; </p>
        <p>拟推荐至_________________ 部门_________________________________勤工助学岗位工作 . </p>
        <p align="right">签名盖章 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
        <p align="right">年　月　日 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p></td>
  </tr>
</table>
<p><strong>1 </strong><strong>、在“申请意向”栏注明愿意从事或擅长从事哪些项目勤工助学活动。 </strong><strong></strong></p>
<p><strong>2 </strong><strong>、在“有何特长”栏除注明自己的特长外，还可注明所获英语和计算机等级证书，及所担任的社会职务。 </strong><strong></strong></p>
<p><strong>3 </strong><strong>、在“岗位记录”栏注明以前所干过的岗位，如无则不写。 </strong><strong></strong></p>
<p><strong>4 </strong><strong>、附上上学期成绩表及家庭贫困证明 </strong><strong></strong></p>
<p><strong>5 </strong><strong>、除顺序号本人不需填写外，其它都需详细地填写。 </strong><strong></strong></p>
<p><strong>6 </strong><strong>、需要特别说明的请在背面附上文字说明。 </strong><strong></strong></p>
		</html:form>
	</body>
</html>
