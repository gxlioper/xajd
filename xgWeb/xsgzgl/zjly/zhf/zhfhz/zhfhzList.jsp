<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" import="org.owasp.encoder.Encode"  pageEncoding="GBK"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String systemPath = request.getContextPath();
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
		<%@ include file="/syscommon/v4_url.ini"%>
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<link rel="stylesheet" href="<%=stylePath %>css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>css/module.css" type="text/css" media="all" />
		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css"  media="all" /> 
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<html:form action="/zhf_hz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		</html:form>
		
		<div class="main_box">
			<p>&nbsp;</p><p>&nbsp;</p>
			<h1 align="center">
				<font size="4">浙江旅游职业学院学生综合素质学分汇总表</font>
			</h1>
			<table class="printtab" id="table2" border="1" align="center" style="font-size:14px" width="98%">
				<tr align="left">
					<td colspan="8" ><font  style="font-weight:bold;">学生基本信息</font></td>
				</tr>
				<tr align="center">
					<td width="10%">学号</td>
					<td colspan="3" width="20%">${rs.xh}</td>
					<td width="15%">班级</td>
					<td colspan="2" width="20%">${rs.bjmc}</td>
					<td rowspan="4" width="20%"><img src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}"
							 width="100" height="120" style="margin:0;float:right;padding-right: 2px;" /></td>
				</tr>
				<tr align="center">
					<td width="15%">姓名</td>
					<td colspan="2" width="15%">${rs.xm}</td>
					<td width="15%">联系方式</td>
					<td colspan="3" width="20%">${rs.sjhm}</td>
				</tr>
				<tr align="center">
					<td width="15%">系部</td>
					<td colspan="2" width="25%">${rs.xymc}</td>
						<td width="20%">性别</td>
					<td colspan="3" width="20%">${rs.xb}</td>
				</tr>
				<tr align="center">
					<td width="15%">学制</td>
					<td colspan="2" width="25%">${rs.xz}</td>
					<td width="20%">专业</td>
					<td colspan="3" width="20%">${rs.zymc}</td>
				</tr>				
				<tr align="center">
					<td colspan="1"><font size = "3px" style="font-weight:bold;">总分</font></td>
					<td colspan="2">${zfrs}</td>		
						<td colspan="1"><font size = "3px" style="font-weight:bold;">学工办意见</font></td>
					<td colspan="4"> <br /> <br /> <br /><br /> <br /> <br /></td>			
					
				</tr>
				</table>
				<table class="printtab" id="table1" border="1" align="center" style="font-size:14px" width="98%">
				${html }
				</table>
			<p>&nbsp;</p>
			<!--<div align="center" class='noPrin' width="98%">
				<input id="btnPrint" class='button2' type="button" value="打印" onclick="pr();" /> 
			</div>
		--></div>
	</body>
	<script type="text/javascript">
		jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件 
		return this.each(function(){ 
			var that; 
			jQuery('tr', this).each(function(row) { 
				jQuery('td:eq('+colIdx+')', this).filter(':visible').each(function(col) { 
				//	if (that!=null && jQuery(this).html() == jQuery(that).html()) { 
					if (that!=null && jQuery(this).attr('value') == jQuery(that).attr('value')) { 
						rowspan = jQuery(that).attr("rowSpan"); 
						if (rowspan == undefined) { 
							jQuery(that).attr("rowSpan",1); 
							rowspan = jQuery(that).attr("rowSpan");
						} 
						rowspan = Number(rowspan)+1; 
						jQuery(that).attr("rowSpan",rowspan); 
						jQuery(this).hide(); 
					} else { 
						that = this; 
					} 
				}); 
			}); 
		}); 
	} 
 jQuery("#table1").rowspan(0);//传入的参数是对应的列数从0开始，哪一列有相同的内容就输入对应的列数值
jQuery("#table1").rowspan(1);
 //jQuery("#table1").rowspan(2);

function pr() {
	window.print();
}
</script> 
</html>
