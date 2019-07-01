<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/gzdx_scgwxxbPrint">
		<div align="center" style="font-size:22px;font:'黑体' ">
			<b>${xxmc }设立勤工助学岗位申请表</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle" align="center">
				<tr>
					<td width=132 class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>部</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:
  12.0pt;font-family:宋体'>门</span>
							</b>
						</p>
					</td>
					<td width=144 valign=middle class="Normal">
						<div align="center">${rs.yrdwmc }</div>
					</td>
					<td width=151 class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>拟申请设立岗位数</span>
							</b>
						</p>
					</td>
					<td width=120 valign=top class="Normal">
						<div align="center">${rs.xyrs }</div>
					</td>
				</tr>
				<tr>
					<td width=132 class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>本部门勤工助学</span>
							</b>
						</p>
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>管理员姓名</span>
							</b>
						</p>
					</td>
					<td width=144 valign=middle class="Normal">
						<div align="center">${rs.fzr }</div>
					</td>
					<td width=151 class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>联</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>系</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>电</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>话</span>
							</b>
						</p>
					</td>
					<td width=120 valign=middle class="Normal">
						<div align="center">${rs.lxdh }</div>
					</td>
				</tr>
				<tr>
					<td width=132 class="Normal">
						<p align=center style='text-align:center;
  line-height:15.0pt'>
							<b><span style='font-size:12.0pt;
  font-family:宋体'>岗位名称</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 align="center" valign=middle class="Normal">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.gwdm }
					</td>
				</tr>
				<tr>
					<td width=132 height="94" class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>工</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>作</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>内</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>容</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 valign=middle class="Normal">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.gznr }
					</td>
				</tr>
				<tr>
					<td width=132 height="102" class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>工</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>作</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>要</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>求</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 valign=middle class="Normal">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.gzyd }
					</td>
				</tr>
				<tr>
					<td width=132 height="107" class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>工</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>作</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>时</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>间</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 valign=middle class="Normal">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.gzsj }
					</td>
				</tr>
				<tr>
					<td width=132 height="162" class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>部</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>门</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>意</span>
							</b><b><span style='font-size:12.0pt'> </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>见</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 valign=top class="Normal">
						<p align=left style='text-align:center;line-height:15.0pt'>
							<b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</b>
						</p>
						<p align=left style='text-align:left;line-height:15.0pt'>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqdwyj}
						</p>
						<p align=right style='text-align:right;line-height:15.0pt'>
							<b><span lang=EN-US style='font-size:12.0pt'>&nbsp; </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>
									领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</b>
						</p>
						<p align=left style='text-align:center;line-height:15.0pt'>
							<b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（部门公章）</span>
							</b>
						</p>
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>&nbsp;&nbsp;&nbsp;&nbsp;年</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>月</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:
  12.0pt;font-family:宋体'>日</span>
							</b>
						</p>
					</td>
				</tr>
				<tr>
					<td width=132 height="110" class="Normal">
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>学</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp; </span>
							</b><b><span style='font-size:12.0pt;
  font-family:宋体'>生</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp; </span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>处</span>
							</b>
						</p>
						<p align=center style='text-align:center;line-height:15.0pt'>
							<b><span style='font-size:12.0pt;font-family:宋体'>意</span>
							</b><b><span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</b><b><span style='font-size:12.0pt;font-family:宋体'>见</span>
							</b>
						</p>
					</td>
					<td width=415 colspan=3 valign=middle class="Normal">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xgbyj }
					</td>
				</tr>
			</table>

		</div>

	</html:form>
	<br />
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
		<%--    </div>--%>
</body>
</html:html>
