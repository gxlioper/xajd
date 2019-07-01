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
	
		<center>
			<html:form action="/czxxDtjsDyxx" method="post">
			<p align="center"><strong>中共常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />委员会 </strong><strong></strong></p>
			<p align="center"><strong>学生入党积极分子基本情况登记表 </strong></p>
			<p><div align="right" style="width:70%">组织部制</div></p>
			<div align="center">
			  <table cellspacing="0" cellpadding="0" class="tbstyle">
			    <tr>
			      <td width="72"><p align="center">姓名 </p></td>
			      <td width="148" colspan="2"><p align="center">${rs.xm}</p></td>
			      <td width="73"><p align="center">性别 </p></td>
			      <td width="112" colspan="2"><p align="center">${rs.xb} </p></td>
			      <td width="105" colspan="2"><p align="center">出生时间 </p></td>
			      <td width="141" colspan="2"><p align="center">${rs.csrq} </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">系别 </p></td>
			      <td width="148" colspan="2"><p align="center">${rs.xymc} </p></td>
			      <td width="73"><p align="center">班级 </p></td>
			      <td width="112" colspan="2"><p align="center">${rs.bjmc} </p></td>
			      <td width="105" colspan="2"><p align="center">申请入党时间 </p></td>
			      <td width="141" colspan="2"><p align="center">${rs.sqrdsj} </p></td>
			    </tr>
			    <tr>
			      <td width="146" colspan="2"><p align="center">党校培训时间及成绩</p></td>
			      <td width="505" colspan="8"><p align="center">${rs.pxsjjcj}</p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">&nbsp; </p></td>
			      <td width="73"><p align="center">第一学期 </p></td>
			      <td width="74"><p align="center">第二学期 </p></td>
			      <td width="73"><p align="center">第三学期 </p></td>
			      <td width="73"><p align="center">第四学期 </p></td>
			      <td width="73" colspan="2"><p align="center">第五学期 </p></td>
			      <td width="72"><p align="center">第六学期 </p></td>
			      <td width="71"><p align="center">第七学期 </p></td>
			      <td width="70"><p align="center">第八学期 </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">班级人数 </p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${rs.bjrs}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${rs.bjrs}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${rs.bjrs}</logic:notEmpty> </p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${rs.bjrs}</logic:notEmpty> </p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${rs.bjrs}</logic:notEmpty> </p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${rs.bjrs}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${rs.bjrs}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${rs.bjrs}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">学习成绩班级排名 </p>
			          </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.xxcjbjpm}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.xxcjbjpm}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.xxcjbjpm}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.xxcjbjpm}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.xxcjbjpm}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.xxcjbjpm}</logic:notEmpty> </p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.xxcjbjpm}</logic:notEmpty> </p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.xxcjbjpm}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">综合测评班级排名</p>
			          </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.zhcpbjpm}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.zhcpbjpm}</logic:notEmpty> </p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.zhcpbjpm}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.zhcpbjpm}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.zhcpbjpm}</logic:notEmpty> </p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.zhcpbjpm}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.zhcpbjpm}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.zhcpbjpm}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">思想汇报份数 </p>
			      </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.sxhbfs}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.sxhbfs}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.sxhbfs}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.sxhbfs}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.sxhbfs}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.sxhbfs}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.sxhbfs}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.sxhbfs}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">有无不及格科目 </p>
			      </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.bjgkm}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.bjgkm}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.bjgkm}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.bjgkm}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.bjgkm}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.bjgkm}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.bjgkm}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.bjgkm}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">考级 </p>
			          <p align="center">考证 </p>
			          <p align="center">情况 </p>
			      </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.kjkz}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.kjkz}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.kjkz}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.kjkz}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.kjkz}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.kjkz}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.kjkz}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.kjkz}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">获奖 </p>
			          <p align="center">情况 </p>
			          </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.hjxj}</logic:notEmpty> </p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.hjxj}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.hjxj}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.hjxj}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.hjxj}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.hjxj}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.hjxj}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.hjxj}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">有无违纪情况 </p>
			      </td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq1">${xq1.wjcf}</logic:notEmpty></p></td>
			      <td width="74"><p align="center"><logic:notEmpty name="xq2">${xq2.wjcf}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq3">${xq3.wjcf}</logic:notEmpty></p></td>
			      <td width="73"><p align="center"><logic:notEmpty name="xq4">${xq4.wjcf}</logic:notEmpty></p></td>
			      <td width="73" colspan="2"><p align="center"><logic:notEmpty name="xq5">${xq5.wjcf}</logic:notEmpty></p></td>
			      <td width="72"><p align="center"><logic:notEmpty name="xq6">${xq6.wjcf}</logic:notEmpty></p></td>
			      <td width="71"><p align="center"><logic:notEmpty name="xq7">${xq7.wjcf}</logic:notEmpty></p></td>
			      <td width="70"><p align="center"><logic:notEmpty name="xq8">${xq8.wjcf}</logic:notEmpty></p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">承担社会工</p>
			        <p align="center">作情况</p>        </td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="74"><p align="center">&nbsp; </p></td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="73" colspan="2"><p align="center">&nbsp; </p></td>
			      <td width="72"><p align="center">&nbsp; </p></td>
			      <td width="71"><p align="center">&nbsp; </p></td>
			      <td width="70"><p align="center">&nbsp; </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">所在宿舍星</p>
			        <p align="center">级情况 </p>        </td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="74"><p align="center">&nbsp; </p></td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="73"><p align="center">&nbsp; </p></td>
			      <td width="73" colspan="2"><p align="center">&nbsp; </p></td>
			      <td width="72"><p align="center">&nbsp; </p></td>
			      <td width="71"><p align="center">&nbsp; </p></td>
			      <td width="70"><p align="center">&nbsp; </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">公寓辅导员意见 </p></td>
			      <td width="579" colspan="9" valign="bottom"><p align="center">（签章）： </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">班主任 </p>
			          <p align="center">意见 </p></td>
			      <td width="579" colspan="9" valign="bottom"><p align="center">（签章）： </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">公寓活动支部意见 </p></td>
			      <td width="579" colspan="9" valign="bottom"><p align="center">（签章）： </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">系党总支意见 </p></td>
			      <td width="579" colspan="9" valign="bottom"><p align="center">（签章）： </p></td>
			    </tr>
			    <tr>
			      <td width="72"><p align="center">学工部 </p>
			          <p align="center">意见 </p></td>
			      <td width="579" colspan="9" valign="bottom"><p align="center">（签章）： </p></td>
			    </tr>
			  </table>
			</div>
			</html:form>
		</center>
		<br>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>
