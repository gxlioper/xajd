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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/jhzyzphcs" method="post">
			<center><h3>厦门理工<bean:message key="lable.xsgzyxpzxy" /><bean:write name="rs" property="xn"/>届毕业生档案留校申请表</h3></center>
			<fieldset>
				<table class="tbstyle" border=1 cellspacing=0 cellpadding=0
					width='100%'>
					<tr>
						<td width=63 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>姓名</span>
							</p>
						</td>
						<td width=135 colspan=3 class="Normal">
						<p align=center style='text-align: center; line-height: 21.0pt;'>
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="xm"/></span>
							</p>
						</td>
						<td width=55 colspan=2 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>专业</span>
							</p>
						</td>
						<td width=161 colspan=2 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="zydm"/></span>
						</td>
						<td width=55 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>学号</span>
							</p>
						</td>
						<td width=129 colspan=2 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="xh"/></span>
						</td>
					</tr>
					<tr>
						<td width=109 colspan=2 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>生源所在地</span>
							</p>
						</td>
						<td width=291 colspan=5 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><bean:write name="rs" property="sysheng"/></span>
							</p>
						</td>
						<td width=81 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span class=GramE><span
									style='font-size: 12.0pt; font-family: 宋体'>邮</span>
								</span><span lang=EN-US style='font-size: 12.0pt; font-family: 宋体'>&nbsp;
								</span><span style='font-size: 12.0pt; font-family: 宋体'>编</span>
							</p>
						</td>
						<td width=116 class="Normal">
						<span style='font-size: 12.0pt; font-family: 宋体'>
						<bean:write name="rs" property="yzbm"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span class=GramE><span
									style='font-size: 12.0pt; font-family: 宋体'>现家庭</span>
								</span><span style='font-size: 12.0pt; font-family: 宋体'>详细地址</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="jtdz"/></span>
						</td>
					</tr>
					<tr>
						<td width=211 colspan=5 class="Normal">
							<p align=center style='text-align: center; line-height: 21.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>联系方式（手机、<span
									lang=EN-US>E-mail</span>）</span>
							</p>
						</td>
						<td width=386 colspan=6 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="lxfs"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>申 请 理 由</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly1" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>单位不能接收档案</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly2" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>暂不就业</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly3" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>正在办理出国手续</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly4" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>欲留在厦门找工作</span><span style='font-family: 宋体'>（针对非厦门生源）</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly5" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>可能升入其他院校</span><span style='font-family: 宋体'>（专升本或考研）</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><html:checkbox name="rs" property="sqly" value="1" disabled="true"></html:checkbox><span
									lang=EN-US>&nbsp; </span>其他情况请注明<span lang=EN-US>
									<bean:write name="rs" property="sqly6"/>
									</span>
								</span>
							</p>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p align=center style='text-align: center; line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>拟申请留校期限</span>
							</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="lxqx"/>   </span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
						<p >
								<span style='font-size: 12.0pt; font-family: 宋体'>本人签名：</span>
						</p>
						</td>
						<td width=445 colspan=8 class="Normal">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="brqm"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'><bean:message key="lable.xsgzyxpzxy" />审批意见:</span>
							</p>
							<p style='line-height: 16.0pt;'></p>
						</td>
						<td width=800 colspan=8 class="Normal" height="150">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="xyyj"/></span>
						</td>
					</tr>
					<tr>
						<td width=152 colspan=3 class="Normal">
							<p style='line-height: 16.0pt;'>
								<span style='font-size: 12.0pt; font-family: 宋体'>学生处意见:</span>
							</p>
							<p style='line-height: 16.0pt;'></p>
						</td>
						<td width=800 colspan=8 class="Normal" height="150">
							<span style='font-size: 12.0pt; font-family: 宋体'>
							<bean:write name="rs" property="xxyj"/></span>
						</td>
					</tr>
					<tr height=0>
						<td width=63 height="2" class="Normal"></td>
						<td width=46 class="Normal"></td>
						<td width=43 class="Normal"></td>
						<td width=46 class="Normal"></td>
						<td width=13 class="Normal"></td>
						<td width=42 class="Normal"></td>
						<td width=147 class="Normal"></td>
						<td width=14 class="Normal"></td>
						<td width=55 class="Normal"></td>
						<td width=13 class="Normal"></td>
						<td width=116 class="Normal"></td>
					</tr>
					<tr>
						<td colspan="12">
							<p>
								<span style='font-family: 宋体'>备注：</span>
							</p>
							<p>
								<span lang=EN-US style='font-family: 宋体'>1.</span><span
									style='font-family: 宋体'>本表由毕业生本人填写，一式两份，学生个人和学生处就业指导中心各保留一份，作为有关手续办理依据；</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: 宋体'>2.</span><span
									style='font-family: 宋体'>档案留校期间<span lang=EN-US>,</span>校方只提供就业有关问题的处理<span
									lang=EN-US>,</span>毕业生如在这两年期间因出国、结婚、考研等需要办理手续的，按规定，不予办理。如需办理，应转出关系到接受单位办理。毕业生档案等留校期间，学生没有学籍，不享受在校生待遇；</span>
							</p>
							<p>
								<span lang=EN-US style='font-family: 宋体'>3.</span><span
									style='font-family: 宋体'>毕业生档案留校期间，毕业生仍可继续落实签约就业单位，办理派遣；也可随时申请转回原籍或到有关城市人才市场办理人事代理（先落户，后就业）。所签就业协议或有关材料，随时可以邮寄（最好以特快专递邮件）或本人、委托他人（须持身份证明和委托书）来校就业指导中心交件办理；</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: 宋体'>4.</span><span
									style='font-family: 宋体'>档案留校超过两年仍未落实单位的毕业生，其在校档案<span
									class=GramE>迁回</span>其生源所在地县市人事局；</span>
							</p>
							<p style='line-height: 16.0pt;'>
								<span lang=EN-US style='font-family: 宋体'>5.</span><span
									style='font-family: 宋体;'>如有其他未尽事宜，厦门理工<bean:message key="lable.xsgzyxpzxy" />就业指导服务中心具有最终解释权。</span>
							</p>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class='noPrin' align="center">
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
