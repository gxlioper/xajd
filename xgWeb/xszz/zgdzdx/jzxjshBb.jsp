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
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>( — )学年国家奖学金申请审批表</title>
<style>
<!--
.Section1
	{page:Section1;}
-->

</style>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<html:form action="/zgdzdxXxwh" method="post">
<div align="center" class=Section1 style='layout-grid:15.6pt'> <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奖学金申请审批表（<bean:write name = "rs" property="xn" />学年）</h2> 
	<b>学校：</b><bean:write name = "rs" property="xxmc" />&nbsp;<b><bean:message key="lable.xsgzyxpzxy" />：</b><bean:write name = "rs" property="xymc" />&nbsp;<b>专业：</b><bean:write name = "rs" property="zymc" />&nbsp;<b>班级：</b><bean:write name = "rs" property="bjmc" />
  <table border=1 cellspacing=0 cellpadding=0 align=center
 width=655> 
    <tr> 
      <td width=67 rowspan=4 class="Normal"> <b>&nbsp; </b><b>基</b> <b>本</b> <b>情</b> <b>况</b></td> 
      <td width=84 class="Normal"> 姓名</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="xm" />&nbsp;</td> 
      <td width=84 colspan=4 class="Normal"> 性别</td> 
      <td width=84 colspan=4 class="Normal"><bean:write name = "rs" property="xb" /> &nbsp;</td> 
      <td width=84 colspan=5 class="Normal"> 出生年月</td> 
      <td width=52 colspan=3 class="Normal"><bean:write name = "rs" property="csrq" /> &nbsp;</td> 
      <td width=52 colspan=4 class="Normal"> 学历</td> 
      <td width=52 colspan=2 class="Normal"> &nbsp;</td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> 学号</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="xh" />&nbsp; </td> 
      <td width=84 colspan=4 class="Normal"> 民族</td> 
      <td width=84 colspan=4 class="Normal"><bean:write name = "rs" property="mzmc" />&nbsp;</td> 
      <td width=84 colspan=5 class="Normal"> 入学时间</td> 
      <td width=52 colspan=3 class="Normal"><bean:write name = "rs" property="rxny" />&nbsp;</td> 
      <td width=52 colspan=4 class="Normal"> 就业去向</td> 
      <td width=52 colspan=2 class="Normal">&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> 身份证号</td> 
      <td width=28 class="Normal" colspan="27"><bean:write name = "rs" property="sfzh" />&nbsp;</td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> 政治面貌</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="zzmmmc" />&nbsp;</td> 
      <td width=168 colspan=8 class="Normal"> 联系电话</td> 
      <td width=80 colspan=4 class="Normal"><bean:write name = "rs" property="lxdh" />&nbsp;&nbsp; </td> 
      <td width=80 colspan=6 class="Normal"> 贫困生认定等级</td> 
      <td width=80 colspan=4 class="Normal">&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>学</b> <b>习</b> <b>等</b> <b>情</b> <b>况</b></td> 
      <td width=588 colspan=27 class="Normal"> 本学年必修课程数<u><bean:write name = "rs" property="bxnbxkms" />&nbsp;&nbsp; </u>门，其中，优秀<u><bean:write name = "rs" property="bxnbxkyxms" />&nbsp;</u>门，良好<u><bean:write name = "rs" property="bxnbxlhxms" />&nbsp;</u>门 学年学分绩点：<u><bean:write name = "rs" property="xnxfjd" />&nbsp; </u>（上学期：<u><bean:write name = "rs" property="sbxqxfjd" />&nbsp; </u>下学期：<u><bean:write name = "rs" property="xbxqxfjd" />&nbsp; </u>）<br>
成绩排名（专业）：<u><bean:write name = "rs" property="cjpm" />&nbsp; </u>（名次/总人数）。&nbsp;综合测评成绩<u><bean:write name = "rs" property="zhcpcj" />&nbsp; </u>分，班级排名<u><bean:write name = "rs" property="zhcpcjpm" />&nbsp;</u>（名次/总人数）（如无此项，填写“无”）外语水平（注明具体分数）：<u><bean:write name = "rs" property="wysp" />&nbsp;</u>，计算机水平：<u><bean:write name = "rs" property="jsjsp" />&nbsp; </u></td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>获奖</b> <b>及科</b> <b>研情</b> <b>况</b></td> 
      <td width=588 colspan=27 class="Normal"> 主要奖项： 其中，院级奖励<u><bean:write name = "rs" property="yxjl" />&nbsp;</u>项；&nbsp;&nbsp; 校级奖励<u><bean:write name = "rs" property="xjjl" />&nbsp;</u>项；&nbsp;&nbsp; 省级以上奖励<u><bean:write name = "rs" property="sjjl" />&nbsp;</u>项 发表论文：</td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>申</b> <b>请</b> <b>理</b> <b>由</b> <b>︵</b> <b>全</b> <b>面</b> <b>反</b> <b>映</b> <b>德</b> <b>智</b> <b>体</b> <b>美</b> <b>情</b> <b>况</b> ︶</td> 
      <td width=588 colspan=27 class="Normal"> <bean:write name = "rs" property="sqly" /><br />申请人：</td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>家</b> <b>庭</b> <b>情</b> <b>况</b></td> 
      <td width=588 class="Normal" colspan=27>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>上一</b> <b>学年</b> <b>社会</b> <b>工作</b> <b>情况</b></td> 
      <td width=588 class="Normal" colspan=27>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>年&nbsp; 级</b> <b>（专业）</b> <b>推&nbsp; 荐</b> <b>意&nbsp; 见</b></td> 
      <td width=588 class="Normal" colspan=27> &nbsp;推荐人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;行政职务： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>学</b> <b>院</b> <b>意</b> <b>见</b></td> 
      <td width=588 class="Normal" colspan=27> （公&nbsp; 章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>学</b> <b>校</b> <b>意</b> <b>见</b></td> 
      <td width=588 class="Normal" colspan=27> 经评审，并在_________________范围内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>天，无异议，现报请同意该同学获本学年度<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>奖学金。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公&nbsp; 章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>设奖</b> <b>单位</b> <b>意见</b></td> 
      <td width=588 class="Normal" colspan=27> （公&nbsp; 章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</td> 
    </tr> 
  </table> 
  </div> 
  <br/>
 <div class="noprint" align="center">
 <input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
</div>

</html:form>
</body>
</html>
