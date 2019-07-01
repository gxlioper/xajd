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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<meta http-equiv="Pragma" http-equiv="no-cache"/>
		<meta http-equiv="Cache-Control" http-equiv="no-cache"/>
		<meta http-equiv="Expires" http-equiv="0"/>
		<meta name="Copyright" content="������� zfsoft"/>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon"/>
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all"/>
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all"/>
	
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>
			<html:form action="/csmz_sztz" method="post">
			<input type="hidden" name="xh" id="xh" value="<bean:write name="xh" scope="request"/>">
			<input type="hidden" name="nj" id="nj" value="<bean:write name="nj" scope="request"/>">
			<input type="hidden" name="xy" id="xy" value="<bean:write name="xy" scope="request"/>">
			<input type="hidden" name="zy" id="zy" value="<bean:write name="zy" scope="request"/>">
			<input type="hidden" name="bj" id="bj" value="<bean:write name="bj" scope="request"/>">
			<input type="hidden" name="xm" id="xm" value="<bean:write name="xm" scope="request"/>">
			<input type="hidden" name="userType" id="userType" value="<bean:write name="userType" scope="request"/>">				
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�������չ - ��չ�ɹ���֤ - ��ӡ�ɹ����� 
					</div>
				</div>
				<table width="100%" align="center" class="tbstyle" >
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								��ӡ�ɹ�����
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="left">
							<bean:write name="xh" />
						</td>
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="xm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="nj" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="xy" />
						</td>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name="zy" />
						</td>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name="bj" />
						</td>								
				</table>
				<table width="100%" align="center" class="tbstyle" name="xmtable" id="xmtable">
					<thead>
						<tr>
							<td align="center"  >
								����(��Ŀ)��ϸ
						</td>
						</tr>
					</thead>
					<tr id="ly" >
						<td>
							<fieldset>
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
											������Ŀ(�)������<bean:write name="xmNum" />&nbsp;&nbsp;&nbsp;&nbsp;
											<font color="blue">��ʾ��ÿһѧ�ꡢ��Ŀ���ֻ�ܴ�ӡ8���ɹ�</font>
										</legend>
										<table width="100%" id="rsTable" class="tbstyle" >
											<thead>
												<tr>												
													<td width="20%" align="center">
														��Ŀ
													</td>
													<td align="center" width="5%">
														ѧ��
													</td>
													<td align="center">
														������Ŀ(�)
													</td>
													<td align="center" width="8%">
														��Ŀ����
													</td>
													<td width="8%" align="center">
														��������
													</td>																																							
													<td width="8%" align="center">
														������
													</td>
													<td width="5%" align="center">
														ѧ��
													</td>
													<td width="5%" align="center">
														���
													</td>	
													<td width="5%" align="center">
														�Ƿ��ӡ
													</td>													
												</tr>
											</thead>
<%--											<tbody >--%>
											<logic:iterate name="rs" id="s" indexId="index">
												<bean:size id="tSize" name="s" property="xmList" />
												<logic:iterate id="v" name="s" property="kmList">
													<tr >													
														<td rowspan="<%=tSize%>" id="cell${index}">
														    <bean:write name="v" property="xn" />ѧ��,<br>
														    <br>
															<bean:write name="v" property="kmm" />
														</td>
														<logic:notEqual name="tSize" value="0">
															<logic:iterate id="b" name="s" property="xmList">
															<td >
																	&nbsp;
																	<bean:write name="b" property="xq" />
																</td>
																<td>
																	&nbsp;
																	<bean:write name="b" property="xmmc" />
																</td>
																<td>
																	&nbsp;
																	<bean:write name="b" property="xmjb" />
																</td>
																<td>
																	&nbsp;
																	<bean:write name="b" property="cyjs" />
																</td>																																
																<td>
																	&nbsp;
																	<bean:write name="b" property="jxm" />
																</td>
																<td>
																	&nbsp;
																	<bean:write name="b" property="xf" />																	
																</td>
																<td nowrap>
																	&nbsp;
																	<bean:write name="b" property="xxsh" />
																</td>
																<td>
																    <input type="hidden" name="pksAll" value="<bean:write name='b' property='pk'/>">
																	&nbsp;
																	<html:select name="b" property="sfdy" styleId="sfdy_<bean:write name='b' property='pk'/>">
																	<html:option value=""></html:option>
																	<html:option value="��">��</html:option>
																	<html:option value="��">��</html:option>
																	</html:select>
																</td>																
																<% out.print("</tr>"); %>
															</logic:iterate>															
														</logic:notEqual>														
														<logic:equal name="tSize" value="0">
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																  <input type="hidden" value="">
																	&nbsp;
																	<select name="sfdy" >
																	<option value=""></option>
																	<option value="��">��</option>
																	<option value="��">��</option>
																	</select>
															</td>															
														</logic:equal>
													</tr>
												</logic:iterate>
											</logic:iterate>
<%--											</tbody>--%>
										</table>										
									</fieldset>																		
									
									
								</logic:notEmpty>

							</fieldset>
						</td>
					</tr>
				</table>
				
				<div class="buttontool" align="center">
					<logic:notPresent name="isBJXM">
						<button class="button2"
							onclick="cgAjs_Save()"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button class="button2" onclick="Close();return false;"
						id="buttonClose">
						�� ��
					</button>
				</div>
				
				<logic:equal value="true" name="done">
			<script language="javascript">
	         	alert("�����ɹ���");
<%--	         	Close();--%>
<%--	         	window.dialogArguments.document.getElementById('search_go').click();--%>
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="false">
         	<script language="javascript">
	         	alert("����ʧ�ܣ�");
<%--	         	Close();--%>
<%--	         	window.dialogArguments.document.getElementById('search_go').click();--%>
	        </script>
	   </logic:equal>
			</html:form>
		</center>
			<script type="text/javascript" src="js/bottomButton.js"></script>	
		<script type="text/javascript" >
		function showHide(){		
		   if(document.getElementById('ly').style.display==''){
		      document.getElementById('ly').style.display='none';
		      document.getElementById('xxbut').value='��ʾ'
		   }else{
		      document.getElementById('ly').style.display='';
		      document.getElementById('xxbut').value='����'
		   }		
		}
		function cgAjs_Save(){
		var pksArr = document.getElementsByName('pksAll');
		var sfdyArr = document.getElementsByName('sfdy');
		/*alert(document.getElementById('xmtable').innerHTML);
		for(i=0;i<pksArr.length;i++){
			alert(pksArr[i].value);
		}
		for(i=0;i<sfdyArr.length;i++){
			alert(sfdyArr[i].value);
		}*/
		//return false;
		   var url="/xgxt/csmz_sztz.do?method=tzcgAjs&act=save";
           var pkStr = "";
           var pkVStr="";
		   var len = document.getElementById("rsTable").rows.length;
		   var cont = 0;
		   var coutChkTem = 0;
		   var coutChk = 0;
		   var collId = "";
		   
		   var clinStr = "";
		   for(i=1;i<len;i++){		   	   
               var pk="";
               var pkV="";        
               var currCellId="";     
		       if(document.getElementById("rsTable").rows[i].cells[0].rowSpan>1){
		           pk  = document.getElementById("rsTable").rows[i].cells[8].getElementsByTagName('input')[0].value;		      
		           pkV  =document.getElementById("rsTable").rows[i].cells[8].getElementsByTagName('select')[0].value;
		           cont = document.getElementById("rsTable").rows[i].cells[0].rowSpan;
		           coutChk = document.getElementById("rsTable").rows[i].cells[0].rowSpan;
		           clinStr = document.getElementById("rsTable").rows[i].cells[0].innerText;
		           currCellId = document.getElementById("rsTable").rows[i].cells[0].id;
		           if(collId == ''){
		           	collId = currCellId; 
		           }
		          // alert(document.getElementById("rsTable").rows[i].cells[0].rowSpan);
		       }else{
		        // alert(document.getElementById("rsTable").rows[i].cells[7].getElementsByTagName('input')[0].name);
		         if(cont>1){
		           pk   = document.getElementById("rsTable").rows[i].cells[7].getElementsByTagName('input')[0].value;		      
		           pkV  =document.getElementById("rsTable").rows[i].cells[7].getElementsByTagName('select')[0].value;	
		           cont--;	         
		         }else{
		           pk  = document.getElementById("rsTable").rows[i].cells[8].getElementsByTagName('input')[0].value;		      
		           pkV  =document.getElementById("rsTable").rows[i].cells[8].getElementsByTagName('select')[0].value;
		           clinStr = document.getElementById("rsTable").rows[i].cells[0].innerText;		         
		         }
		       }
		       if(collId != currCellId){
		       	coutChkTem = 0;
		       	collId = currCellId;
		       }
		       
		       if(coutChk>8){
		          if(pkV=='��'){
		           coutChkTem++;		          		          
		          }
		          
		       }
		       if(coutChkTem>7){
		          alert("ÿѧ�ꡢ��Ŀ����ܴ�ӡ8���ɹ�\n\n"+clinStr+" ��Ŀ\n\n�����ӡ������8����");		          
		          return false;		          
		       }
		       
		       pkStr  +=pk+"!!";
		       pkVStr +=pkV+"!!";
		   }
		   url +="&pkStr="+pkStr;
		   url +="&pkVStr="+pkVStr;
		   //alert(url);
		   document.getElementById('buttonSave').disabled = true;
		   document.forms[0].action="/xgxt/csmz_sztz.do?method=tzcgAjs&act=save";
		   document.forms[0].submit();
//	       refreshForm(url);	  
		}
		</script>				
	</body>
</html>
