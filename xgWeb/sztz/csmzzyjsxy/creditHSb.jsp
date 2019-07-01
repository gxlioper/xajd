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
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
 		<script language="javascript" src="js/sztzFunction.js"></script>
		<html:form action="/csmzzy_sztz" method="post">
			<input type="hidden" name="bjdm" id="bjdm"
			value="<bean:write name="bjInfo" property="bjdm"/>" />
			<input type="hidden" name="bjmc" id="bjmc"
			value="<bean:write name="bjInfo" property="bjmc"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 素质拓展学分申报 - 申报 
				</div>
			</div>
			
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td >
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>学年
						<html:select property="xn" style="width:100px" styleId="xn">
							<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<FONT color="red">
						<bean:write name="bjInfo" property="xymc"/>
						<bean:write name="bjInfo" property="zymc"/>
						<bean:write name="bjInfo" property="bjmc"/>
						</FONT>
						
						<div align="right"> 
						<button class="button2" onclick="sztzxfsb(this)">提交</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
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
								思想道德素养
								</td>
								<td>
								志愿服务
								</td>
								<td>
								科技学术
								</td>
								<td>
								文化艺术
								</td>
								<td>
								社团社会工作
								</td>
								<td>
								技能培训	
								</td>
								<td>
								合计学分								
								</td>							
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<td  >
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" name="bjdmV" value="<bean:write name="v"/>"  />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="xm"  readonly="true" style="width:100px;" value="<bean:write name="v" />">
									</logic:iterate>
								</td>
								<td >
								<logic:iterate id="v" name="s" offset="2" length="1">
								<input  name="xh"  readonly="true" style="width:130px;"  value="<bean:write name="v" />">																					
								</logic:iterate>
								</td>
								<td >																	
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="sxddsy_xf" style="width:60px;"  onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)"   >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="zyfw_xf" style="width:60px;" onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)" >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="kxjs_xf" style="width:60px;" onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)" >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="whys_xf" style="width:60px;" onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)" >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="shstgz_xf" style="width:60px;" onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)" >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="jnpx_xf" style="width:60px;" onkeypress='return sztzNumInputValue(this,6,event)' onchange="sumxf()" onblur="ckinpdata(this)" >															
								</td>
								<td >																
										<input onfocus="rowOnClick(this.parentNode.parentNode)" name="xfhj" style="width:70px;" onkeypress='return sztzNumInputValue(this,6,event)' readonly="true"  >															
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
			<script type="text/javascript">
	        alert("操作成功");
	        Close();
	        window.dialogArguments.document.getElementById('search_go').click();	        	        
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
	     function sztzxfsb(obj){
	        var array = document.getElementById('rsTable').getElementsByTagName('tr');
	        var is = false;
	        for(i=1;i<array.length;i++){
	            var valu= array[i].cells[8].getElementsByTagName('input')[0].value;
                if(valu!=null&&valu!=""){
                     is = true;
                     break;
                }        
	        }
	        if(!is){
	           alert("请录入至少一个学生的素质拓展学分！");
	           return false;
	        }
	        if($("xn").value==""){
	           alert("请选择学年！");
	           return false;
	        }
	        var bjdm = $("bjdm").value;
	        var xn   = $("xn").value;
	        var bjmc = $("bjmc").value; 	       
	        getSztzData.getInfoEx("sztz_xfb","xn||bjdm",xn+bjdm,"xxsh='通过'",function(str){
	            if(str){
		           alert("该学年、该班级学生拓展学分汇总已申报，\n\n并通过上级部门审核\n\n或正在审核中，不能再申报！");		          
			       return false;	               
	            }else{
	              if(confirm("确定要提交"+xn+"学年"+bjmc+"素质拓展学分汇总？\n\n提交后可在结果查询中进行修改、打印汇总表并查看审核结果。")){
	                 refreshForm('/xgxt/csmzzy_sztz.do?method=xfSbSave');
	                 obj.disabled="true";
	              }
	            }	              	            
	         });	
	     }	     
	</script>	
</html>
