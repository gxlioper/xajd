<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
	function tj() {
		var tjlx = document.forms[0].tjlx.value;

//		var jddmcs=document.forms[0].jddmcs.value;
//		var jddmcss=jddmcs.split(",");
//		var chejdmc="";
//	    for(var i=0;i<jddmcss.length;i++){
//	        if(document.getElementById(jddmcss[i]).checked){
//	        	chejdmc+= jddmcss[i]+",";
//	        }
//	     }
		//alert(${tjlx});
		jQuery("#tjlx").val(tjlx);
		document.forms[0].action = "dtjs_dtxxgl_dtxxtj.do?";
		document.forms[0].submit();
	}
	function expdate(){
		var exportdate="dc"
		jQuery("#exportdate").val(exportdate);
		document.forms[0].action = "dtjs_dtxxgl_dtxxtj.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
		}
</script>

	</head>

	<body>
		<form action="/dtjs_dtxxgl_dtxxtj">
		<input type="hidden" name="jddmcs" value=<%=request.getAttribute("jddmcs").toString() %>/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
			<div class="buttonbox">
					<ul>
						<li><a  onclick="expdate()" class="btn_dc"> 导出结果 </a></li>
					</ul>
			</div>
			<div class="buttonbox">
			统计类型：&nbsp;<html:select name="dtjsDtxxglForm" property="tjlx" style="width:170px" >
				<html:option value="1"><bean:message key="lable.xb" /></html:option>
			</html:select>
			<input type="hidden" name="tjlx" id="tjlx" value="${tjlx}" />
			<input type="hidden" name="exportdate" id="exportdate" value="" />


			<button type="button" class="" style="height: 23px" id="" onclick="tj();return false;" >
				统计查询
			</button>
			</div>
			</div>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							统计结果&nbsp;&nbsp;
						</span>
					</h3>
				<table width="99%" id="rsTable" class="dateline">
					<logic:equal name="tjlx" value="1">
						<thead>
							<tr align="center" style="coursor: hand">
								<td rowspan="2" width="10%" align="center">
									<strong><bean:message key="lable.xb" />名称</strong>
								</td>
								<td rowspan="2" width="5%" align="center">
									<strong>学生总数</strong>
								</td>
								<logic:iterate id="jd" name="jdArray" scope="request">
									<td colspan="2" width="10%" align="center"><strong><bean:write  name="jd" /></strong></td>
								</logic:iterate>
							</tr>
							<tr align="center" style="coursor: hand">
								<logic:iterate id="jd" name="jdArray" scope="request">
								<logic:iterate id="a" name="rsbl" scope="request">
									<td width="5%" align="center" ><strong><bean:write name="a"/></strong></td>
								</logic:iterate>
								</logic:iterate>
							</tr>
						</thead>
							<logic:iterate name="tjList" id="tjlist">
								<tr>
									<logic:iterate id="v" name="tjlist">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
					</logic:equal>

				</table>
			</div>
		</form>
	</body>
</html>