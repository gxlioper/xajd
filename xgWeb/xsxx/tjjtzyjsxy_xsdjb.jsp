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
		<script language="javascript" src="js/function.js"></script>
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
			<html:form action="/xsxxdj" method="post">
				<p align="center">&nbsp;&nbsp;</p>
				<p align="center"><h3 align="center">${xxmc}学生登记表 </h3></p>
				<table class="tbstyle" id="rsTable" width="100%" align="center">
			    <tr>
			      <td height="35" colspan="3"><p align="center"><strong>姓 名 </strong></p></td>
			      <td colspan="3"><p align="center"><strong>${rs.xm} </strong></p></td>
			      <td colspan="3"><p align="center"><strong>性 别 </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.xb} </strong></p></td>
			      <td colspan="4"><p align="center"><strong>学 号 </strong></p></td>
			      <td colspan="7" ><p align="center"><strong>${rs.xh}</strong></p></td>
			      <td colspan="" rowspan="4"  style="width:140;height:160">
				  <img src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
						border="0" align="absmiddle" style="width:140;height:160" />
				</td>
			    </tr>
			    <tr>
			      <td height="37" colspan="3"><p align="center"><strong>出生年月 </strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.csrq} </strong></p></td>
			      <td colspan="5"><p align="center"><strong>身份证号 </strong></p></td>
			      <td colspan="14" ><p align="center"><strong>${rs.sfzh}</strong></p></td>
			    </tr>
			    <tr>
			      <td height="50" colspan="3"><p align="center"><strong>政治面目 </strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.zzmmmc} </strong></p></td>
			      <td colspan="3"><p align="center"><strong>民 族 </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.mzmc } </strong></p></td>
			      <td colspan="4"><p align="center"><strong>籍 贯 </strong></p></td>
			      <td colspan="7" ><p align="center"><strong>${rs.jg} </strong></p></td>
			    </tr>
			    <tr>
			      <td height="43" colspan="3"><p align="center"><strong>家庭地址 </strong></p></td>
			      <td colspan="11" ><p align="center"><strong>${rs.jtdz } </strong></p></td>
			      <td colspan="4"><p align="center"><strong>家庭电话 </strong></p></td>
			      <td colspan="7" ><p align="left">${rs.lxdh1} </p></td>
			    </tr>
			    <tr>
			      <td  height="50" colspan="3"><p align="center"><strong>系 别 </strong></p></td>
			      <td colspan="4"><p><strong>${rs.xymc }</strong></p></td>
			      <td colspan="3"><p align="center"><strong>专 业 </strong></p></td>
			      <td colspan="5" ><p align="left"><strong>${rs.zymc }</strong></p></td>
			      <td colspan="3"><p align="center"><strong>年 级 </strong></p></td>
			      <td colspan="3" ><p align="left"><strong>${rs.nj} </strong></p></td>
			      <td colspan="3"><p align="center"><strong>班 级 </strong></p></td>
			      <td width="108" colspan="2"><p align="center"><strong>${rs.bjmc} </p></td>
			    </tr>
			    <tr>
			      <td height="46" colspan="3"><p align="center"><strong>所在公寓 </strong></p></td>
			      <td colspan="4" ><p align="center"><strong>${rs.ssbh} </strong></p></td>
			      <td colspan="3"><p align="center"><strong>寝室号 </strong></p></td>
			      <td colspan="6" ><p align="center"><strong>${rs.qsh} </strong></p></td>
			      <td colspan="4"><p align="center"><strong>手机号码 </strong></p></td>
			      <td colspan="6"><p align="center"><strong>${rs.sjhm}</strong></p></td>
			    </tr>
			    <tr>
			      <td height="44" colspan="3"><p align="center"><strong>有何特长 </strong></p></td>
			      <td colspan="23" ><p align="center"><strong>${rs.tc} </strong></p></td>
			    </tr>
			    <tr>
			      <td height="20" colspan="26" ><p align="center"><strong>本 人 简 历 </strong></p></td>
			    </tr>
			    <tr>
			      <td height="24" colspan="5"><p align="center"><strong>起止年月 </strong></p></td>
			      <td colspan="16"><p align="center"><strong>学习及工作单位（从初中填写） </strong></p></td>
			      <td colspan="5"><p align="center"><strong>学习或任何职 </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl1_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl1_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl1_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl2_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl2_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl2_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl3_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl3_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl3_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl4_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl4_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl4_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl5_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl5_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl5_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="39" colspan="5" ><p align="center"><strong>${rs.jl6_qzny}&nbsp; </strong></p></td>
			      <td colspan="16" ><p align="center"><strong>${rs.jl6_xxjgzdw}&nbsp; </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jl6_xxhrzw}&nbsp; </strong></p></td>
			    </tr>
			    <tr>
			      <td height="24" colspan="26" ><p align="center"><strong>家庭成员及主要社会关系 </strong></p></td>
			    </tr>
			    <tr>
			      <td height="45" colspan="2"><p align="center"><strong>姓 名 </strong></p></td>
			      <td colspan="2"><p align="center"><strong>与本人 </strong></p>
			          <p align="center"><strong>关系 </strong></p></td>
			      <td colspan="3"><p align="center"><strong>年龄 </strong></p></td>
			      <td colspan="5"><p align="center"><strong>民 族 </strong></p></td>
			      <td colspan="3"><p align="center"><strong>政治 </strong></p>
			          <p align="center"><strong>面目 </strong></p></td>
			      <td colspan="9"><p align="center"><strong>工作单位及职务 </strong></p></td>
			      <td colspan="2"><p align="center"><strong>联系电话 </strong></p></td>
			    </tr>
			    <tr>
			      <td height="34" colspan="2" ><p align="center"><strong>${rs.jtcy1_xm} </strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy1_gx}</strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy1_nl} </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jtcy1_mzmc} </strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy1_zzmmmc} </strong></p></td>
			      <td colspan="9" ><p align="center"><strong>${rs.jtcy1_gzdz}</strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy1_lxdh1} </strong></p></td>
			    </tr>
			    <tr>
			      <td height="34" colspan="2" ><p align="center"><strong>${rs.jtcy2_xm} </strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy2_gx}</strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy2_nl} </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jtcy2_mzmc}</strong> </p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy2_zzmmmc} </strong></p></td>
			      <td colspan="9" ><p align="center"><strong>${rs.jtcy2_gzdz}</strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy2_lxdh1} </strong></p></td>
			    </tr>
			    <tr>
			      <td height="34" colspan="2" ><p align="center"><strong>${rs.jtcy3_xm} </strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy3_gx}</strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy3_nl} </strong></p></td>
			      <td colspan="5" ><p align="center"><strong>${rs.jtcy3_mzmc} </strong></p></td>
			      <td colspan="3" ><p align="center"><strong>${rs.jtcy3_zzmmmc} </strong></p></td>
			      <td colspan="9" ><p align="center"><strong>${rs.jtcy3_gzdz}</strong></p></td>
			      <td colspan="2" ><p align="center"><strong>${rs.jtcy3_lxdh1} </strong></p></td>
			    </tr>
			    <tr>
			      <td height="34" colspan="2" ><p align="center"><strong></strong></p></td>
			      <td colspan="2" ><p align="center"><strong></strong></p></td>
			      <td colspan="3" ><p align="center"><strong></strong></p></td>
			      <td colspan="5" ><p align="center"></p></td>
			      <td colspan="3" ><p align="center"><strong></strong></p></td>
			      <td colspan="9" ><p align="center"><strong></strong></p></td>
			      <td colspan="2" ><p align="center"><strong></strong></p></td>
			    </tr>
			  </table>
			<div class="noprint" align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
</html:form>
</body>
</html>
