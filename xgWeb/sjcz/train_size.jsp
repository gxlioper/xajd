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
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<style type="text/css">
<!--
.style1 {
	font-size: 18px;
	font-weight: bold;
}
.style2 {font-size: 14px}
-->
</style>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js">
</script>
	<body>
		<div class="title">
			<div class="title_img" id="title_m">
				<span id="tipFollow">学生迷彩作训服/鞋帽/文化衫尺寸表</span>
			</div>
		</div>
		<p class="style1" align="center">
			学生迷彩作训服/鞋帽/文化衫尺寸表
		</p>
		<table width="100%" align="center" class="tbstyle">
			<tr align="center">
				<td colspan="2" rowspan="2">
					&nbsp;
				</td>
				<td align="center">
					号
				</td>
				<td colspan="3" align="center">
					1号
				</td>
				<td colspan="3" align="center">
					2号
				</td>
				<td colspan="3" align="center">
					3号
				</td>
				<td colspan="3" align="center">
					4号
				</td>
				<td rowspan="2" align="center">
					特号
				</td>
				<td rowspan="2" align="center">
					公差+.-
				</td>
			</tr>
			<tr>
				<td align="center">
					型
				</td>
				<td align="center">
					1
				</td>
				<td align="center">
					2
				</td>
				<td align="center">
					3
				</td>
				<td align="center">
					1
				</td>
				<td align="center">
					2
				</td>
				<td align="center">
					3
				</td>
				<td align="center">
					1
				</td>
				<td align="center">
					2
				</td>
				<td align="center">
					3
				</td>
				<td align="center">
					1
				</td>
				<td align="center">
					2
				</td>
				<td align="center">
					3
				</td>
			</tr>
			<tr align="center">
				<td width="93" rowspan="8">
					迷
					<br>
					彩
					<br>
					作
					<br>
					训
					<br>
					服
					<br>
					(套)
				</td>
				<td width="13" rowspan="4">
					男
				</td>
				<td width="54" align="left" nowrap>
					前身长
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					67
				</td>
				<td align="center">
					67
				</td>
				<td align="center">
					67
				</td>
				<td align="center">
					69
				</td>
				<td align="center">
					69
				</td>
				<td align="center">
					69
				</td>
				<td align="center">
					71
				</td>
				<td align="center">
					71
				</td>
				<td align="center">
					71
				</td>
				<td align="center">
					73
				</td>
				<td align="center">
					1.5
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					胸围
				</td>
				<td align="center">
					112
				</td>
				<td align="center">
					118
				</td>
				<td align="center">
					124
				</td>
				<td align="center">
					114
				</td>
				<td align="center">
					120
				</td>
				<td align="center">
					126
				</td>
				<td align="center">
					116
				</td>
				<td align="center">
					122
				</td>
				<td align="center">
					128
				</td>
				<td align="center">
					118
				</td>
				<td align="center">
					124
				</td>
				<td align="center">
					130
				</td>
				<td align="center">
					132
				</td>
				<td align="center">
					2
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					裤长
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					110
				</td>
				<td align="center">
					110
				</td>
				<td align="center">
					110
				</td>
				<td align="center">
					114
				</td>
				<td align="center">
					1.5
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					裤腰围
				</td>
				<td align="center">
					78
				</td>
				<td align="center">
					86
				</td>
				<td align="center">
					94
				</td>
				<td align="center">
					80
				</td>
				<td align="center">
					88
				</td>
				<td align="center">
					96
				</td>
				<td align="center">
					82
				</td>
				<td align="center">
					90
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					84
				</td>
				<td align="center">
					92
				</td>
				<td align="center">
					100
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					2
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					女
				</td>
				<td align="left" nowrap>
					前身长
				</td>
				<td align="center">
					59
				</td>
				<td align="center">
					59
				</td>
				<td align="center">
					59
				</td>
				<td align="center">
					61
				</td>
				<td align="center">
					61
				</td>
				<td align="center">
					61
				</td>
				<td align="center">
					63
				</td>
				<td align="center">
					63
				</td>
				<td align="center">
					63
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					65
				</td>
				<td align="center">
					67
				</td>
				<td align="center">
					1.5
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					胸围
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					108
				</td>
				<td align="center">
					114
				</td>
				<td align="center">
					104
				</td>
				<td align="center">
					110
				</td>
				<td align="center">
					116
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					112
				</td>
				<td align="center">
					118
				</td>
				<td align="center">
					108
				</td>
				<td align="center">
					114
				</td>
				<td align="center">
					120
				</td>
				<td align="center">
					122
				</td>
				<td align="center">
					2
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					裤长
				</td>
				<td align="center">
					94
				</td>
				<td align="center">
					94
				</td>
				<td align="center">
					94
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					98
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					102
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					106
				</td>
				<td align="center">
					110
				</td>
				<td align="center">
					1.5
				</td>
			</tr>
			<tr>
				<td align="left" nowrap>
					裤腰围
				</td>
				<td align="center">
					69
				</td>
				<td align="center">
					77
				</td>
				<td align="center">
					85
				</td>
				<td align="center">
					71
				</td>
				<td align="center">
					79
				</td>
				<td align="center">
					87
				</td>
				<td align="center">
					73
				</td>
				<td align="center">
					81
				</td>
				<td align="center">
					89
				</td>
				<td align="center">
					75
				</td>
				<td align="center">
					83
				</td>
				<td align="center">
					91
				</td>
				<td align="center">
					93
				</td>
				<td align="center">
					2
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					迷彩作训帽(顶)
				</td>
				<td align="left" nowrap>
					头围
				</td>
				<td colspan="3">
					59.5
				</td>
				<td colspan="3">
					58.5
				</td>
				<td colspan="3">
					57.5
				</td>
				<td colspan="3">
					56.5
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					0.5
				</td>
			</tr>
			<tr align="center">
				<td colspan="17">
					&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td colspan="2" rowspan="2">
					解放鞋(双)
				</td>
				<td rowspan="2" align="left" nowrap>
					尺码
				</td>
				<td>
					34码
				</td>
				<td>
					35码
				</td>
				<td>
					36码
				</td>
				<td>
					37码
				</td>
				<td>
					38码
				</td>
				<td>
					39码
				</td>
				<td>
					40码
				</td>
				<td>
					41码
				</td>
				<td>
					42码
				</td>
				<td>
					43码
				</td>
				<td>
					44码
				</td>
				<td>
					45码
				</td>
				<td>
					46码
				</td>
				<td>
					47码
				</td>
			</tr>
			<tr>
				<td>
					22
				</td>
				<td>
					22.5
				</td>
				<td>
					23
				</td>
				<td>
					23.5
				</td>
				<td>
					24
				</td>
				<td>
					24.5
				</td>
				<td>
					25
				</td>
				<td>
					25.5
				</td>
				<td>
					26
				</td>
				<td>
					26.5
				</td>
				<td>
					27
				</td>
				<td>
					27.5
				</td>
				<td>
					28
				</td>
				<td>
					28.5
				</td>
			</tr>
			<tr align="center">
				<td colspan="17">
					&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					迷彩文化衫
				</td>
				<td align="left" nowrap>
					尺寸
				</td>
				<td>
					85
				</td>
				<td>
					90
				</td>
				<td>
					95
				</td>
				<td>
					100
				</td>
				<td>
					105
				</td>
				<td>
					110
				</td>
				<td>
					115
				</td>
				<td>
					120
				</td>
				<td>
					125
				</td>
				<td>
					130
				</td>
				<td>
					135
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					备注
				</td>
				<td colspan="15" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服装型号中的&quot;号&quot;是长短(1号小,4号大),&quot;型&quot;是肥瘦(1型瘦,3型肥).在一般情况下,在选号时不同身高适合穿着的参考标准是:男式1号165厘米以下,2号165厘米至170厘米,3号170厘米至175厘米,4号175至180厘米,特号180厘米至185厘米;女式1号155厘米以下,2号155厘米至160厘米,3号160厘米至165厘米,4号165至170厘米,特号170厘米至175厘米,特殊体型可提供(前身长/胸围/裤腰围/裤长)尺寸定做.
				</td>
			</tr>
		</table>
	</body>
</html>
