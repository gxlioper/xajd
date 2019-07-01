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
		<meta name="Copyright" content="正方软件 zfsoft"/>
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
						当前所在位置：素质拓展 - 拓展成果认证 - 打印成果调整 
					</div>
				</div>
				<table width="100%" align="center" class="tbstyle" >
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								打印成果调整
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							学号：
						</td>
						<td align="left">
							<bean:write name="xh" />
						</td>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="xm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="nj" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="xy" />
						</td>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name="zy" />
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name="bj" />
						</td>								
				</table>
				<table width="100%" align="center" class="tbstyle" name="xmtable" id="xmtable">
					<thead>
						<tr>
							<td align="center"  >
								参与活动(项目)详细
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
										未找到任何记录！
									</p>
								</logic:empty>
								<logic:notEmpty name="rs">									

									<fieldset>
										<legend>
											参与项目(活动)总数：<bean:write name="xmNum" />&nbsp;&nbsp;&nbsp;&nbsp;
											<font color="blue">提示：每一学年、科目最多只能打印8条成果</font>
										</legend>
										<table width="100%" id="rsTable" class="tbstyle" >
											<thead>
												<tr>												
													<td width="20%" align="center">
														科目
													</td>
													<td align="center" width="5%">
														学期
													</td>
													<td align="center">
														参与项目(活动)
													</td>
													<td align="center" width="8%">
														项目级别
													</td>
													<td width="8%" align="center">
														参与性质
													</td>																																							
													<td width="8%" align="center">
														奖项名
													</td>
													<td width="5%" align="center">
														学分
													</td>
													<td width="5%" align="center">
														审核
													</td>	
													<td width="5%" align="center">
														是否打印
													</td>													
												</tr>
											</thead>
<%--											<tbody >--%>
											<logic:iterate name="rs" id="s" indexId="index">
												<bean:size id="tSize" name="s" property="xmList" />
												<logic:iterate id="v" name="s" property="kmList">
													<tr >													
														<td rowspan="<%=tSize%>" id="cell${index}">
														    <bean:write name="v" property="xn" />学年,<br>
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
																	<html:option value="是">是</html:option>
																	<html:option value="否">否</html:option>
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
																	<option value="是">是</option>
																	<option value="否">否</option>
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
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button class="button2" onclick="Close();return false;"
						id="buttonClose">
						关 闭
					</button>
				</div>
				
				<logic:equal value="true" name="done">
			<script language="javascript">
	         	alert("操作成功！");
<%--	         	Close();--%>
<%--	         	window.dialogArguments.document.getElementById('search_go').click();--%>
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="false">
         	<script language="javascript">
	         	alert("操作失败！");
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
		      document.getElementById('xxbut').value='显示'
		   }else{
		      document.getElementById('ly').style.display='';
		      document.getElementById('xxbut').value='隐藏'
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
		          if(pkV=='是'){
		           coutChkTem++;		          		          
		          }
		          
		       }
		       if(coutChkTem>7){
		          alert("每学年、科目最多能打印8条成果\n\n"+clinStr+" 科目\n\n允许打印数大于8条！");		          
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
