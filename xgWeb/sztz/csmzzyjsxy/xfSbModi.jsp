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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>	
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>	
		<html:form action="/csmzzy_sztz" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 素质拓展学分申报 - 结果查询 - 修改 
				</div>
			</div>
			
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td>
						学年
						<html:select name="map"  property="xn" style="width:100px" styleId="xn">
							<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<FONT color="red">
						<bean:write name="map" property="xymc"/>
						<bean:write name="map" property="zymc"/>
						<bean:write name="map" property="bjmc"/>
						</FONT>
						
						<div align="right"> 
						<button class="button2" 
<%--						onclick="refreshForm('/xgxt/csmzzy_sztz.do?method=xfSbModiSave');this.disabled=true"--%>
						onclick="sztzxfModi(this)"
						>
						修改</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;">关闭</button>
																	
						</div>						
						</td>							
						</tr>													
					</thead>
				</table>
							
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						学生总数：共<FONT color="red"><bean:write name="rsNum" /></FONT>人
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
								姓名
								</td>
								<td>
								学号
								</td>
								<td>
								思想<br>
								道德<br>
								素养
								</td>
								<td>
								志愿<br>
								服务
								</td>
								<td>
								科技<br>
								学术
								</td>
								<td>
								文化<br>
								艺术
								</td>
								<td>
								社团<br>
								社会<br>
								工作
								</td>
								<td>
								技能<br>
								培训	
								</td>
								<td>
								合计<br>
								学分								
								</td>							
							</tr>
						</thead>
						<logic:iterate name="rs" id="s" >
							<tr onclick="rowOnClick(this)">
								<td >
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" name="bjdmV"
											value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input  name="xm" onfocus="rowOnClick(this.parentNode.parentNode)" readonly="true" style="width:80px;" value="<bean:write name="v" />">
									</logic:iterate>
								</td>
								<td >
								<logic:iterate id="v" name="s" offset="2" length="1">
								<input  name="xh" onfocus="rowOnClick(this.parentNode.parentNode)"  readonly="true" style="width:100px;"  value="<bean:write name="v" />">																					
								</logic:iterate>
								</td>
								<td >		
								<logic:iterate id="v" name="s" offset="3" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)" name="sxddsy_xf" style="width:50px;cursor:hand"  value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" >															
								</logic:iterate>
								</td>
								
								<td >	
							     <logic:iterate id="v" name="s" offset="4" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="zyfw_xf" style="width:50px;cursor:hand"    value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()">															
								</logic:iterate>
								</td>
								
								<td >	
								<logic:iterate id="v" name="s" offset="5" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="kxjs_xf" style="width:50px;cursor:hand"    value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()">															
								</logic:iterate>
								</td>
								
								<td >	
								<logic:iterate id="v" name="s" offset="6" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="whys_xf" style="width:50px;cursor:hand"   value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()">															
								</logic:iterate>
								</td>
								
								<td >	
								<logic:iterate id="v" name="s" offset="7" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="shstgz_xf" style="width:50px;cursor:hand" value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()">															
								</logic:iterate>
								</td>
								
								<td >	
								<logic:iterate id="v" name="s" offset="8" length="1">															
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="jnpx_xf" style="width:50px;cursor:hand"  value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()">															
								</logic:iterate>
								</td>
								<td >	
								<logic:iterate id="v" name="s" offset="9" length="1">
										<input   onfocus="rowOnClick(this.parentNode.parentNode)"name="xfhj" style="width:70px;"  value="<bean:write name="v" />"
										onkeypress='return sztzNumInputValue(this,6,event)'  >															
								</logic:iterate>
								</td>
								
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>	
			<br><br>			
		</html:form>			
	</body>
	<logic:equal value="ok" name="done">
		<script language="javascript">
         alert("操作成功！");           
        </script>
	 </logic:equal>
	<logic:equal value="no" name="done">
		<script language="javascript">
        alert("操作失败！");          
       </script>
	</logic:equal>
	<script type="text/javascript">
	     function sumxf(){
	        var xfhjV = 0;
	        var sxddsy_xfV = isNaN(parseInt(curr_row.cells[2].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[2].getElementsByTagName('input')[0].value);
            var zyfw_xfV   = isNaN(parseInt(curr_row.cells[3].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[3].getElementsByTagName('input')[0].value);
            var kxjs_xfV   = isNaN(parseInt(curr_row.cells[4].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[4].getElementsByTagName('input')[0].value);
            var whys_xfV   = isNaN(parseInt(curr_row.cells[5].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[5].getElementsByTagName('input')[0].value);
            var shstgz_xfV = isNaN(parseInt(curr_row.cells[6].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[6].getElementsByTagName('input')[0].value);
            var jnpx_xfV   = isNaN(parseInt(curr_row.cells[7].getElementsByTagName('input')[0].value))?0:parseFloat(curr_row.cells[7].getElementsByTagName('input')[0].value);
            xfhjV = parseFloat(parseFloat(sxddsy_xfV)*10000+parseFloat(zyfw_xfV)*10000+parseFloat(kxjs_xfV)*10000+parseFloat(whys_xfV)*10000+parseFloat(shstgz_xfV)*10000+parseFloat(jnpx_xfV)*10000)/10000;           	           
	        curr_row.cells[8].getElementsByTagName('input')[0].value=xfhjV;
	     }		
	     function sztzxfModi(obj){	      
	        if($("xn").value==""){
	           alert("请选择学年！");
	           return false;
	        }	        	       
	        if(confirm("确定要修改该条记录？")){
	          refreshForm('/xgxt/csmzzy_sztz.do?method=xfSbModiSave');
	          obj.disabled="true";	             
	        }	
	     }         
	</script>

</html>
