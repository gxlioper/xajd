<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zxdk_xnmz" method="post">
		<table width="100%" id="rsT" class="printstyle">
			<tr height='50px'>
				<td  colspan=8 valign="middle" >
					<p align=center style='font-size:14.0pt;'>
						<b>
							中国银行国家助学贷款申请审批表
						</b>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td  valign=bottom >
					借款人姓名：${rs.xm }
				</td>
				<td colspan=3 valign=bottom >
					性别：${rs.xb }
				</td>
				<td  colspan=4 valign=bottom >
					出生日期：${rs.csrq }
				</td>
			</tr>
			<tr  height='30px'>
				<td  valign=bottom >
					就读学校：${xxmc }
				</td>
				<td  colspan=6 valign=bottom >
					身份证号码：${rs.sfzh }
				</td>
				<td   valign=bottom >
					学号：${rs.xh }
				</td>
			</tr>
			<tr  height='30px'>
				<td colspan=2 valign=bottom >
					院系：${rs.xymc }
				</td>
				<td colspan=4 valign=bottom >
					专业：${rs.zymc }
				</td>
				<td colspan=2 valign=bottom >
					宿舍电话：${rs.qsdh }
				</td>
			</tr>
			<tr  height='30px'>
				<td colspan=2 valign=bottom >
					学制学历：${rs.xz }
				</td>
				<td  colspan=6 valign=bottom >
					&nbsp;
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 rowspan=3 >
					申请贷款金额
				</td>
				<td  colspan=4 rowspan=3 >
					总金额：${rs.dkzje }
				</td>
				<td  colspan=2 valign=bottom >
						<font color='#3366FF'>其中：学杂费贷款</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>元
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 valign=bottom >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>住宿费</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>元</font>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=2 valign=bottom >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>生活费</font>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#3366FF'>元</font>
				</td>
			</tr>
			<tr>
				<td colspan=2 rowspan=3 >
					贷款期限
				</td>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>学杂费贷款：</span>
					<span style='color:fuchsia'>&nbsp;个</span> 
					<span style='color:fuchsia'>&nbsp;月</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >自</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span><span >&nbsp;</span>
					<span >月</span>
					<span >&nbsp;&nbsp;</span>
					<span >日至</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span>
					<span >&nbsp;&nbsp; </span>
					<span >月</span>
					<span>&nbsp;</span>
					<span >日</span>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>生活费贷款：</span>
					<span style='color:fuchsia'>&nbsp;个</span> 
					<span style='color:fuchsia'>&nbsp;月</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >自</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span><span >&nbsp;</span>
					<span >月</span>
					<span >&nbsp;&nbsp;</span>
					<span >日至</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span>
					<span >&nbsp;&nbsp; </span>
					<span >月</span>
					<span>&nbsp;</span>
					<span >日</span>
				</td>
			</tr>
			<tr height='30px'>
				<td  colspan=6 valign=bottom >
					<span style='color:fuchsia'>住宿费贷款：</span>
					<span style='color:fuchsia'>&nbsp;个</span> 
					<span style='color:fuchsia'>&nbsp;月</span>
					<span style='color:fuchsia'>&nbsp;&nbsp;</span>
					<span >自</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span><span >&nbsp;</span>
					<span >月</span>
					<span >&nbsp;&nbsp;</span>
					<span >日至</span>
					<span >&nbsp;&nbsp; </span>
					<span >年</span>
					<span >&nbsp;&nbsp; </span>
					<span >月</span>
					<span>&nbsp;</span>
					<span >日</span>
				</td>
			</tr>
			<tr  height='30px'>
				<td width=187 colspan=2 rowspan=2 valign=top >
					<p>
						家庭详细住址：${rs.jtdz }
					</p>
					<p>
						邮编：${rs.jtyb }
					</p>
					<p>
						电话：${rs.jtdh }
					</p>
					<p>
						父亲姓名：${rs.fqxm }
					</p>
					<p>
						母亲姓名：${rs.mqxm }
					</p>
					<p>
						家庭月收入：${rs.jtysr }
					</p>
				</td>
				<td width=373 colspan=6 valign=top >
					<p>
						<span style='font-family:宋体;
  &quot;Times New Roman&quot;'>本人保证以上填写内容真实无误，并予以认可，借款申请人（签字）：</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>年</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:
  宋体;&quot;Times New Roman&quot;'>月</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:宋体;"Times New Roman"'>日</span>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td width=373 colspan=6 valign=top >
					<p>
						<span style='font-family:宋体;
  &quot;Times New Roman&quot;'>借款申请人系我校就读学生，表内所填内容属实，特此证明。</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>贷款介绍人</span><span
							lang=EN-US>/</span><span style='font-family:宋体;"Times New Roman"'><bean:message key="lable.xb" /></span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>（盖章）：</span>
					</p>
					<p>
						<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
							style='font-family:宋体;"Times New Roman"'>年</span><span lang=EN-US>&nbsp;&nbsp;
						</span><span style='font-family:
  宋体;&quot;Times New Roman&quot;'>月</span><span
							lang=EN-US>&nbsp;&nbsp; </span><span
							style='font-family:宋体;"Times New Roman"'>日</span>
					</p>
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=3 valign=bottom >
					见证人姓名：${rs.jzrxm }
				</td>
				<td  colspan=2 valign=bottom >
					性别：${rs.jzrxb }
				</td>
				<td  colspan=3 valign=bottom >
					出生日期：
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=6 valign=bottom >
					身份证号码：${rs.jzrzjh}
				</td>
				<td  colspan=2 valign=bottom >
					与申请人关系：${rs.jzrgx}
				</td>
			</tr>
			<tr  height='30px'>
				<td  colspan=3 valign=bottom >
					现住所地址：${rs.jzrdz }
				</td>
				<td width=150 colspan=3 valign=bottom >
					邮编：${rs.jzryb }
				</td>
				<td width=187 colspan=2 valign=bottom >
					电话：${rs.jzrdh }
				</td>
			</tr>
			<tr  height='90px'>
				<td colspan=8 >
					见证人意见：
					${rs.jzryj }
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			<tr  height='100px'>
				<td  colspan=8 >
					信贷员意见：
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			<tr  height='100px'>
				<td  colspan=8  >
					有权批准人意见：
					<p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</td>
			</tr>
			
		</table>

		</html:form>
	</body>
</html>
