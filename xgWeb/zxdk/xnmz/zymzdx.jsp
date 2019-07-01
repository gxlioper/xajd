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
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
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
.noPrin {
	display: none;
}
</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			
			//性别勾选
			var selectXb = jQuery("#selectXb").val();
			jQuery("input[name=xb]").each(function() {
				if (jQuery(this).val() == selectXb) {
					jQuery(this).attr("checked", true);
				}
			});

			//培养层次勾选
			var selectPycc = jQuery("#selectPycc").val();
			jQuery("input[name=pycc]").each(function(){
				if (jQuery(this).val() == selectPycc) {
					jQuery(this).attr("checked", true);
				}
			})
			
			});
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zxdk_xnmz" method="post">
			<table width="100%" id="rsT" class="printstyle">
				<tr height='50px'>
					<td colspan=8 valign="middle">
						<p align=center style='font-size: 14.0pt;'>
							<b> 中国银行国家助学贷款申请审批表 </b>
						</p>
					</td>
				</tr>
				<tr height='30px'>
					<td valign=bottom>
						借款人姓名：${rs.xm }
					</td>
					<td colspan=3 valign=bottom>
						性别：
						<input type="hidden" id="selectXb" value="${rs.xb}" />
						<input type='checkbox' value='男' name="xb" disabled="disabled" />
						男
						<input type='checkbox' value='女' name="xb" disabled="disabled" />
						女
					</td>
					<td colspan=4 valign=bottom>
						出生日期：${rs.csrq }
					</td>
				</tr>
				<tr height='30px'>
					<td valign=bottom>
						就读学校：${xxmc }
					</td>
					<td colspan=6 valign=bottom>
						身份证号码：${rs.sfzh }
					</td>
					<td valign=bottom>
						年级：${rs.nj }
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 valign=bottom>
						院系：${rs.xymc }
					</td>
					<td colspan=3 valign=bottom>
						专业：${rs.zymc }
					</td>
					<td colspan=2 valign=bottom>
						宿舍电话：${rs.qsdh }
					</td>
					<td colspan=2 valign=bottom>
						学号：${rs.xh }
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 valign=bottom>
						学制：${rs.xz } 年
					</td>
					<td colspan=7 valign=bottom>
						学历：
						<input type="hidden" id="selectPycc" value="${rs.pycc}" />
						<logic:iterate id="pycc" name="pyccList" offset="1">
							<input type="checkbox" value="${pycc.dm}" name="pycc" disabled="disabled" />${pycc.mc }"
						</logic:iterate>
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=1 >
						申请贷款金额
					</td>
					<td colspan=7 >
						&nbsp;&nbsp;总金额：${rs.dkzje } 元
						&nbsp;&nbsp;
						<font color='#3366FF'>其中：学杂费贷款______</font>
						<font color='#3366FF'>元 
						
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color='#3366FF'>住宿费______</font>
						<font color='#3366FF'>元</font>
						</p>
						
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color='#3366FF'>生活费______</font>
						<font color='#3366FF'>元</font>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=1 >
						贷款期限
					</td>
					<td colspan=7 valign=bottom>
						<span style='color: fuchsia'>贷款期限：____个月      自_____年___月___日至_____年___月___日</span>
					</td>
				</tr>
				<tr height='30px'>
					<td width=187 colspan=3 rowspan=2 valign=top>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							职业：
						</p>
						<p>
							父亲身份证号码：${rs.fqsfzh }
						</p>
						<p>
							母亲姓名：${rs.mqxm }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							职业：
						</p>
						<p>
							母亲身份证号码：${rs.mqsfzh }
						</p>
						<p>
							家庭月收入：${rs.jtysr }
						</p>
					</td>
					<td colspan=5 valign=top>
						<p>
							<span style='font-family: 宋体;'>本人保证以上填写内容真实无误，并予以认可，借款申请人（签字）：</span>
						</p>
						<p>
							<span lang=EN-US>
							<p>&nbsp;</p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>年</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>月</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>日</span>
						</p>
					</td>
				</tr>
				<tr height='30px'>
					<td colspan=5 valign=top>
						<p>
							<span style='font-family: 宋体;'>借款申请人系我校就读学生，表内所填内容属实，特此证明。</span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>贷款介绍人</span><span lang=EN-US>/</span><span
								style='font-family: 宋体;'><bean:message key="lable.xb" /></span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>（盖章）：</span>
						</p>
						<p>
							<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
								style='font-family: 宋体;'>年</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>月</span><span lang=EN-US>&nbsp;&nbsp;
							</span><span style='font-family: 宋体;'>日</span>
						</p>
					</td>
				</tr>
				<tr height='90px'>
					<td colspan=8>
						备注：
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>

				<tr height='100px'>
					<td colspan=8>
						信贷员意见：
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height='90px'>
					<td colspan=8>
						科长意见：
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height='100px'>
					<td colspan=8>
						有权批准人意见：
						<p align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>

			</table>

		</html:form>
	</body>
</html>
