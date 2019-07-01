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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>	
		<center>
			<html:form action="/n05_xszz.do" method="post">
				<p align="left">附件： </p>
				<p align="center"><h4>${xxmc}</h4></p>
				<p align="center"><h4><u>&nbsp;${rs.xn}&nbsp;</u>学年家庭经济困难学生学费减免审批表</h4></p>
				<div align="center">
				  <table cellspacing="0" cellpadding="0" class="tbstyle">
				    <tr>
				      <td width="31" rowspan="4">
						  <p align="center">本 </p>
				          <p align="center">人 </p>
				          <p align="center">情 </p>
				          <p align="center">况 </p></td>
				      <td width="72"><p align="center">姓名 </p></td>
				      <td width="72" colspan="2"><p align="center">${rs.xm} </p></td>
				      <td width="96"><p align="center">性别 </p></td>
				      <td width="72"><p align="center">${rs.xb} </p></td>
				      <td width="83" colspan="2"><p align="center">出生年月 </p></td>
				      <td width="59" colspan="2"><p align="center">${rs.csrq} </p></td>
				      <td width="48"><p align="center">民族 </p></td>
				      <td width="62"><p align="center">${rs.mzmc}</p></td>
				    </tr>
				    <tr>
				      <td width="72" ><p align="center">学号 </p></td>
				      <td width="72" colspan="2" ><p align="center">${rs.xh} </p></td>
				      <td width="96"><p align="center">入档编号 </p></td>
				      <td width="72"><p align="center">${rs.rdbh}</p></td>
				      <td width="132" colspan="3"><p align="center">专业年级班级 </p></td>
				      <td width="120" colspan="3"><p align="center">${rs.zymc}${rs.nj}${rs.bjmc} </p></td>
				    </tr>
				    <tr>
				      <td width="144" colspan="3"><p align="center">申请何档次学费减免 </p></td>
				      <td width="204" colspan="3" align="center">
						<p> 
						<logic:equal value="全免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">全免
						</logic:equal>
						<logic:notEqual value="全免" name="rs" property="sqdc">
							<input type="checkbox">全免
						</logic:notEqual>
						&nbsp;&nbsp;
						<logic:equal value="半免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">半免
						</logic:equal>
						<logic:notEqual value="半免" name="rs" property="sqdc">
							<input type="checkbox">半免
						</logic:notEqual>
						&nbsp;&nbsp;
						<logic:equal value="部分减免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">部分减免
						</logic:equal>
						<logic:notEqual value="部分减免" name="rs" property="sqdc">
							<input type="checkbox">部分减免
						</logic:notEqual>
						</p></td>
				      <td width="96" colspan="2"><p align="center">品德等第 </p></td>
				      <td width="120" colspan="3"><p align="center">${rs.pddd} </p></td>
				    </tr>
				    <tr>
				      <td width="108" colspan="2" align="center">何学年获何<br/>种资助（写明<br/>受助金额）</td>
				      <td width="240" colspan="4" align="center"><p>${rs.szqk } </p></td>
				      <td width="96" colspan="2" align="center">
				       	           何时参加勤<br/> 
				                                工助学和申<br/>
				          	请助学贷款 </td>
				      <td width="120" colspan="3" align="center">
						<p>&nbsp; <logic:notEqual value="13742" name="xxdm">参加勤工助学时间：</logic:notEqual>
							${rs.cjqgzxsj}<br/>
							<logic:notEqual value="13742" name="xxdm">申请助学贷款时间：</logic:notEqual>
							${rs.sqzxdksj }
                        </p>
                      </td>
				    </tr>
				    <tr>
				      <td width="139" colspan="3"><p align="center">家庭经济<br/> 困难证明 </p></td>
				      <td width="456" colspan="9" valign="top"><p align="left">
						<logic:equal value="孤残证明" name="rs" property="gczm">
							（1）孤残证明<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="孤残证明" name="rs" property="gczm">
							（1）孤残证明<input type="checkbox">
						</logic:notEqual>
				
						<logic:equal value="少数民族证明" name="rs" property="ssmzzm">
							（2）少数民族证明<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="少数民族证明" name="rs" property="ssmzzm">
							（2）少数民族证明<input type="checkbox">
						</logic:notEqual>
						<br/>
						<logic:equal value="烈士子女证明" name="rs" property="lsznzm">
							（3）烈士子女证明<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="烈士子女证明" name="rs" property="lsznzm">
							（3）烈士子女证明<input type="checkbox">
						</logic:notEqual>

						<logic:equal value="优抚家庭证明" name="rs" property="yfjtzm">
							（4）优抚家庭证明<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="优抚家庭证明" name="rs" property="yfjtzm">
							（4）优抚家庭证明<input type="checkbox">
						</logic:notEqual>

						<logic:equal value="其他证明 " name="rs" property="qtzm">
							（5）其他证明<input type="checkbox" checked="checked">
						</logic:equal>
						<logic:notEqual value="其他证明 " name="rs" property="qtzm">
							（5）其他证明<input type="checkbox">
						</logic:notEqual>
					</p> </td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>申请理由： （写明家庭经济收入情况，本人及家庭成员健康状况，目前的学习，生活状态。） </p>
				          <p>${rs.sqyy}</p>
				          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p>本人保证以上内容与事实相符。 </p>
				          <p align="right">申请人签名：　　　　　　　年　　月　　日 </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>班级评议： （写明品行表现、学习、生活现状和核实后的家庭经济困难程度和受校内资助情况。） </p>
				          <p>${rs.bjpy} </p>
				          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p align="right">签名：　　　　　　年　　月　　日 </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />意见及公示结果： </p>
				          <p>${rs.xyshyj}</p>
				          <p>${rs.xygsjg}</p>          
                          <p>&nbsp; </p>
				          <p>&nbsp; </p>
				          <p align="right">签名： 　　　　　　年　　月　　日 </p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p>学生资助管理中心审核意见： </p>
				          <p align="right">情况属实，建议给予学费减免：
				         	(1)
				         <logic:equal value="全免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">全免
						</logic:equal>
						<logic:notEqual value="全免" name="rs" property="sqdc">
							<input type="checkbox">全免
						</logic:notEqual>
						(2)
						<logic:equal value="半免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">半免
						</logic:equal>
						<logic:notEqual value="半免" name="rs" property="sqdc">
							<input type="checkbox">半免
						</logic:notEqual>
						(3)
						<logic:equal value="部分减免" name="rs" property="sqdc">
							<input type="checkbox" checked="checked">部分减免
						</logic:equal>
						<logic:notEqual value="部分减免" name="rs" property="sqdc">
							<input type="checkbox">部分减免
						</logic:notEqual>，减免学费<u>&nbsp;${rs.jmje}&nbsp;</u> 元。 </p>
				          <p align="right">签名： 　　　　　　年　　月　　日</p></td>
				    </tr>
				    <tr>
				      <td width="595" colspan="12" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />资助工作领导小组审核意见： </p>
				          <p>${rs.xxshyj} </p>
				          <p align="right">签名： 　　　　　　年　　月　　日 </p></td>
				    </tr>
				  </table>
				</div>
				<p align="center">&nbsp; </p>
		</html:form>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
