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
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>

	<body>
		
		<html:form action="/zyfw">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>						
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								起止日期
							</th>
							<td colspan="3">
							<html:text  property="qsrq" styleId="qsrq"
										onfocus="showCalendar('qsrq','y-mm-dd',true,'jsrq');"></html:text>
								至
							<html:text  property="jsrq" styleId="jsrq"
										onfocus="showCalendar('jsrq','y-mm-dd',false,'qsrq');"></html:text>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<div class="main_box">
			<p>&nbsp;</p><p>&nbsp;</p>
			<h1 align="center">
				<font size="4">浙江旅游职业学院学生志愿服务情况汇总表</font>
			</h1>
			<table class="printtab" id="table1" border="1" align="center" style="font-size:12px" width="98%">
				<tr align="center">
					<td width="20%">姓名</td>
					<td colspan="2" width="25%">${rs.xm}</td>
					<td width="25%">性别</td>
					<td colspan="2" width="30%">${rs.xb}</td>
				</tr>
				<tr align="center">
					<td width="20%">系别</td>
					<td colspan="2" width="25%">${rs.xymc}</td>
					<td width="20%">班级</td>
					<td colspan="2" width="25%">${rs.bjmc}</td>
				</tr>
				<tr align="center">	
					<td width="15%" ><font style="font-weight:bold;">日期</font></td>
					<td width="20%" colspan="2"><font style="font-weight:bold;">志愿服务内容</font></td>
					<td width="20%"><font style="font-weight:bold;">工作地点</font></td>
					<td width="15%" colspan="2"><font style="font-weight:bold;">工作时长</font></td>
				</tr>
				<logic:iterate name="zyfwList" id="s" indexId="i">
					<tr align="center">	
						<td width="15%" >${s.rq}</td>
						<td width="20%" colspan="2"><input type="hidden" value="${s.zyfwnr}"></input>${s.zyfwnr}</td>
						<td width="20%" ><input type="hidden" value="${s.gzdd}"></input>${s.gzdd}</td>
						<td width="15%" colspan="2">${s.gzsc}</td>
					</tr>
				</logic:iterate>
				<tr align="center">
					<td colspan="3"><font size="4" style="font-weight:bold;">总工作时长</font></td>
					<td colspan="3">${zsc.xs }小时${zsc.fz }分钟</td>			
				</tr>
			</table>
			<p>&nbsp;</p>
		</div>
	</body>
	<script type="text/javascript"> 
		jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件 
		return this.each(function(){ 
		var that; 
		jQuery('tr', this).each(function(row) { 
			jQuery('td:eq('+colIdx+')', this).filter(':visible').each(function(col) { 
		if (that!=null && jQuery(this).html() == jQuery(that).html()) { 
		rowspan = jQuery(that).attr("rowSpan"); 
		if (rowspan == undefined) { 
			jQuery(that).attr("rowSpan",1); 
		rowspan = jQuery(that).attr("rowSpan"); } 
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
 jQuery("#table1").rowspan(2);
//$(“#table1″).rowspan(2);

function pr() {
	window.print();
}

function query(){
	var qsrq = jQuery("#qsrq").val();
	var jsrq = jQuery("#jsrq").val();
	window.location.href="zyfw.do?method=gjjgList&qsrq="+qsrq+"&jsrq="+jsrq;
}

</script> 
</html>
