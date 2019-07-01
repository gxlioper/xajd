<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
  
</style>
<style>
*{font-family:隶书!important;}
</style>
<body> 
<html:form action="/stu_info_add.do" method="post">
<center>
  <table width="90%" align="center" border="0" class="printStyle"> 
   <div class=Section1 style='layout-grid:15.6pt'>
  <h3 align="center" style="">成都体育学院新生基本情况登记表</h3>
  <p align="center">
	  <span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
		系(部):<bean:write name="rs" property="xymc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		专业:<bean:write name="rs" property="zymc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		班级:<bean:write name="rs" property="bjmc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		学号:<bean:write name="rs" property="xh"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		生源地:<bean:write name="rs" property="syd"/>
	  </span>
  </p>
  <div align="center">
    <table class="printStyle" border=0 cellspacing=0 cellpadding=0 >
   	 <tr>
		<td width="10%"></td>
		<td width="10%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="7%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="6%"></td>
		<td width="15%"></td>
	 </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>姓名</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="xm"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>性别</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="xb"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>籍贯</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jgs"/><bean:write name="rs" property="jgshi"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>民族</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="mzmc"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>出生年月</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="csrq"/></span></td>
        <td align="center" rowspan="6">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							border="0" style="width:160;height:200"/>
		</td>
      </tr>
      <tr height="28px">
        <td align="center" colspan="2" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>入学前学习<br/>(工作)单位</span></td>
        <td align="center" colspan="4" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="rxqxxdw"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>应届</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="yj"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>城镇</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="cz"/></span></td>
      </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>往届</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="wj"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>农村</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="nc"/></span></td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>入学前<br/>户口所在地</span></td>
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="rxqhkszd"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>家庭详细<br/>通讯地址</span></td>
        <td align="center" colspan="4"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jtszd"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>邮编</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="yb"/></span></td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>家庭<br/>联系人</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="jtlxr"/></span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>联系<br/>电话</span></td>
        <td colspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>固定电话：<bean:write name="rs" property="gddh"/><br/>移动电话：<bean:write name="rs" property="yddh"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>入党(团)时间</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
        	<logic:notEmpty name="rs" property="rdsj"><bean:write name="rs" property="rdsj"/></logic:notEmpty>
        	<logic:empty name="rs" property="rdsj"><bean:write name="rs" property="rtsj"/></logic:empty>
        	</span></td>
      </tr>
      <tr height="28px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>特长</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="tc"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>学校寝室号码</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="ssbh"/></span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>身份证号码</span></td>
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="sfzh"/></span></td>
      </tr>
      <tr height="40px">
        <td align="center" colspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>入学前所取得的运动成绩、等级<br/>称号以及奖惩情况</span></td>
        <td align="center" colspan="10"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:write name="rs" property="qk"/></span></td>
      </tr>
      <tr height="28px">
        <td align="center" rowspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>个人简历</span></td>
        <td align="center" rowspan="3" colspan="4"><bean:write name="rs" property="grjl"/></td>
        <td align="center" rowspan="3"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>高考<br/>成绩</span></td>
        <td align="center" rowspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>文考</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>总分</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>政治</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>语文</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>数学</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>外语</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>综合</span></td>
      </tr>
      <tr height="28px">
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr height="39px">
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>体考</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>总分</span></td>
        <td align="center"></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>专<br/>项</span></td>
        <td align="center" colspan="3"></td>
      </tr>
      <tr height="39px">
        <td align="center" rowspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>家庭主要成<br/>员及社会关<br/>系</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>姓名</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>年龄</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>与本人<br/>关系</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>月收入<br/>(元)</span></td>
        <td align="center"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>政治<br/>面貌</span></td>
        <td align="center" colspan="5"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>现在何地和单位任何职务</span></td>
        <td align="center" colspan="2"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>联系电话</span></td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy1_xm }</td>
        <td align="center">${rs.jtcy1_nlxx }</td>
        <td align="center">${rs.jtcy1_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy1_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy1_gzdz}${rs.jtcy1_zw}</td>
        <td align="center" colspan="2">${rs.jtcy1_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy2_xm }</td>
        <td align="center">${rs.jtcy2_nlxx }</td>
        <td align="center">${rs.jtcy2_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy2_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy2_gzdz}${rs.jtcy2_zw}</td>
        <td align="center" colspan="2">${rs.jtcy2_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">${rs.jtcy3_xm }</td>
        <td align="center">${rs.jtcy3_nlxx }</td>
        <td align="center">${rs.jtcy3_gx}</td>
        <td align="center">&nbsp;</td>
        <td align="center">${rs.jtcy3_zzmmmc}</td>
        <td align="center" colspan="5">${rs.jtcy3_gzdz}${rs.jtcy3_zw}</td>
        <td align="center" colspan="2">${rs.jtcy3_lxdh1}</td>
      </tr>
      <tr height="28px">
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center" colspan="5">&nbsp;</td>
        <td align="center" colspan="2">&nbsp;</td>
      </tr>
    </table>
  </div>
  <p align="left"><span style='font-size:11.5pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
  				注：1、本表由学生本人用钢笔填写；&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				家长签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				填表日期：&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日<br/>
  				&nbsp;&nbsp;&nbsp;&nbsp;2、本表可在我校校园网学生处网页上下载。</span>
</div>  
  </table>  
</center> 
<%--<div class='noPrin' align="center">--%>
<%--	 <input type='button' value='页面设置' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">--%>
<%--	 <input type='button' value='打印预览' onclick="try{window.onresize = doResize;WebBrowser.ExecWB(7,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">--%>
<%--	 <input type='button' value='直接打印' onclick="try{window.onresize = doResize;WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">--%>
<%--</div>--%>
</html:form>

</body>
</html>
