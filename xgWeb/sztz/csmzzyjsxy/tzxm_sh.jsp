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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	
		<html:form action="/csmz_sztz.do?method=tzxm_sh" method="post">
	    <input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue" scope="request"/>">
	    <input type="hidden" name="userType" id="userType" value="<bean:write name="userType" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ��չ��Ŀ��� - ��� - �������
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������չ��Ŀ�걨���
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						ѧ�꣺
				    </td>
					<td align="left">
					<bean:write name="rs" property="xn"/>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
					<bean:write name="rs" property="xqmc"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						��Ŀ���ƣ�
				    </td>
					<td align="left">
					<bean:write name="rs" property="xmmc"/>
					</td>
					<td align="right">
						������Ŀ��
					</td>
					<td align="left">
					<bean:write name="rs" property="kmm"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ŀ����
					</td>
					<td align="left">
					<bean:write name="rs" property="xmjb"/>
					</td>
					<td align="right">
						���벿�ţ�
					</td>
					<td align="left">
					<bean:write name="rs" property="bmmc"/>
					</td>
				 </tr>	
				 <tr style="height:22px">
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
					<bean:write name="rs" property="zbsj"/>
					</td>
					<td align="right">
						��֯��λ��
					</td>
					<td align="left">
					<bean:write name="rs" property="zzdw"/>
					</td>
				 </tr>			
			     <tr style="height:22px">
					<td align="right">
					��Ŀ�걨�ˣ�
					</td>
					<td align="left">
					${rs.xmsbr}	
					</td>
					<td align="right">
						<font color="red"><bean:write name="shType" scope="request"/>��ˣ�</font>
					</td>
					<td align="left">
					<logic:equal value="true" name="shLimit">
					   <bean:write name="shClin"/>
					</logic:equal>					
					<logic:equal value="false" name="shLimit">					
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>					
					</logic:equal>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ŀ������
					</td>
					<td align="left"colspan="3">
						<bean:write name="rs" property="xmms"/>
					</td>
				</tr>
     		</table>
     		<table width="100%" align="center"  class="tbstyle">
					<thead>
						<tr>						    
							<td align="center" >
								��Ŀ����
								<div  align="right" ><button id="xxbut" class="button2" onclick="showHide()" >����</button></div>
							</td>
						</tr>
					</thead>
					<tr id="ly" >
						<td>
						<fieldset>
							<logic:empty name="rsJx">
								<br />
								<br />
								<p align="center">
									δ�ҵ��κμ�¼��
								</p>
							</logic:empty>
							<logic:notEmpty name="rsJx">
								<legend>
									��¼����
									<bean:write name="reNum" />
								</legend>

								<table width="99%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center">											
											<td>
												������
											</td>
											<td>
												ѧ��
											</td>
										</tr>
									</thead>
									<logic:iterate name="rsJx" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
						</fieldset>
					 </td>
				  </tr>
				</table>
			<div class="buttontool" align="center">
			   	<logic:equal value="false" name="shLimit">	
				<button class="button2"
					onclick="tzcg_ShSave()"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("�����ɹ���");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="done">
         	<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
	   </logic:equal>
	</body>
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
		function tzcg_ShSave(){
		  var pkV = $("pkValue").value;
		  if($("userType").value=="stu"||$("userType").value=="student"){
		    getSztzData.getInfoEx("csmz_tzxmb "," id ",pkV," (xysh='ͨ��' or xxsh='ͨ��') ",function(str){
		         if(str){		         	
		            alert("����չ��Ŀ��ͨ���ϼ����\n����������У������ٲ�����");		          
			        return false;
		         }else{
		            if(confirm("ȷ��\'"+$("yesNo").value+"\'����չ��Ŀ��")){
		             refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		               document.getElementById("buttonSave").disabled=true;
		            }else{
		               return false;
		            }
		        }
    	     });			    
		  }else if($("userType").value=="xy"){
		     getSztzData.getInfoEx("csmz_tzxmb ","id",pkV," xxsh='ͨ��' ",function(str){
		         if(str){		         	
		            alert("����չ��Ŀ��ͨ���ϼ����\n����������У������ٲ�����");		          
			        return false;
		         }else{
		            if(confirm("ȷ��\'"+$("yesNo").value+"\'����չ��Ŀ��")){
		              refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		               document.getElementById("buttonSave").disabled=true;
		            }else{
		               return false;
		            }
		        }
    	     });
		  }else{
		      if(confirm("ȷ��\'"+$("yesNo").value+"\'����չ��Ŀ��")){
		            refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		            document.getElementById("buttonSave").disabled=true;
		        }else{
		            return false;
		       }
		  }  
		}
		</script>	
</html>
