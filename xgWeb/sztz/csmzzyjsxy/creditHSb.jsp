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
		<meta name="Copyright" content="������� zfsoft" />
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
					��ǰ����λ�ã�������չ - ������չѧ���걨 - �걨 
				</div>
			</div>
			
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td >
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>ѧ��
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
						<button class="button2" onclick="sztzxfsb(this)">�ύ</button>
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
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						ѧ����������<FONT color="red"><bean:write name="rsNum" /></FONT>��
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
								����
								</td>
								<td>
								ѧ��
								</td>
								<td>
								˼���������
								</td>
								<td>
								־Ը����
								</td>
								<td>
								�Ƽ�ѧ��
								</td>
								<td>
								�Ļ�����
								</td>
								<td>
								������Ṥ��
								</td>
								<td>
								������ѵ	
								</td>
								<td>
								�ϼ�ѧ��								
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
	        alert("�����ɹ�");
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
	           alert("��¼������һ��ѧ����������չѧ�֣�");
	           return false;
	        }
	        if($("xn").value==""){
	           alert("��ѡ��ѧ�꣡");
	           return false;
	        }
	        var bjdm = $("bjdm").value;
	        var xn   = $("xn").value;
	        var bjmc = $("bjmc").value; 	       
	        getSztzData.getInfoEx("sztz_xfb","xn||bjdm",xn+bjdm,"xxsh='ͨ��'",function(str){
	            if(str){
		           alert("��ѧ�ꡢ�ð༶ѧ����չѧ�ֻ������걨��\n\n��ͨ���ϼ��������\n\n����������У��������걨��");		          
			       return false;	               
	            }else{
	              if(confirm("ȷ��Ҫ�ύ"+xn+"ѧ��"+bjmc+"������չѧ�ֻ��ܣ�\n\n�ύ����ڽ����ѯ�н����޸ġ���ӡ���ܱ��鿴��˽����")){
	                 refreshForm('/xgxt/csmzzy_sztz.do?method=xfSbSave');
	                 obj.disabled="true";
	              }
	            }	              	            
	         });	
	     }	     
	</script>	
</html>
