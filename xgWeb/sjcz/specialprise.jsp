<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <base target="_self" />
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
  
  <body>
  <logic:equal name="ok" value="yes">
  <script type="text/javascript">
    alert("����ɹ���");
  </script>
  </logic:equal>
  <logic:equal value="no" name="ok">
    <script type="text/javascript">
       alert("����ʧ�ܣ�");
    </script>
  </logic:equal>
    <html:form action="specialprise.do" method="post" >
						
		   <div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
    <fieldset style="width:100%">
	       <legend>
	         ��ѯ
	       </legend>
            <input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
	       <table class="tbstyle" width="100%">
          	<tr>
          	    <td align="left"><bean:message key="lable.xsgzyxpzxy" />��
          	       <html:select property="xydm" styleId="xy">
          	           <html:option value=" " />
          	           <html:options collection="xyList" property="xydm" labelProperty="xymc"/>
          	       </html:select>
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	     </td>
          	     <td>
          	       ���轱ѧ�����ƣ�
          	       <html:select property="zxjxjdm" >
          	           <html:option value=" " />
          	           <html:options collection="zxjxjList" property="zxjxjdm" labelProperty="zxjxjmc"/>
          	       </html:select>
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	      </td>
         	       <td width="10" rowspan="2" align="center" valign="middle">
					<input type="hidden" name="go" value="a" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" style="height:40px;width:80px" id="search_go"
						onclick="allNotEmpThenGo('/xgxt/specialprise.do')">
						��&nbsp;&nbsp;ѯ
					</button>
			     </td>
          	      <tr>
          	      <td>
          	       ��ȣ�
          	       <html:select property="ndfw" styleId="ndfw">
          	           <html:option value=" " />          	           
          	           <html:options collection="ndfwList" property="ndfw" labelProperty="ndfw"/>          	           
          	       </html:select>
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	      </td>
          	      <td>
          	       ѧ�ţ�
          	       <input id="xh" name="xh" value="">
          	      </td>
          	</tr>
           </table>
   	</fieldset> 
   	<fieldset>
   	        <div align="center" ><strong>�㽭��ѧ
   	        
   	             ��� 
   	       
   	        ���轱ѧ����ܱ�</strong></div>
				<table class="tbstyle" width="100%">
				  <tr>
				    <td rowspan="3"  width="21"><div align="center">��<br>�� </div></td>
				    <td rowspan="3"  width="21"><div align="center">ѧ��</div></td>
				    <td rowspan="3"  width="21"><div align="center">���轱<br>����</div></td>
				    <td rowspan="3"  width="23"><div align="center">ѧ<br>Ժ </div></td>
				    <td rowspan="3"  width="38"><div align="center">רҵ��<br>�༶ </div></td>
				    <td rowspan="3"  width="51"><div align="center">ѧ��</div></td>
				    <td rowspan="3"  width="37"><div align="center">����</div></td>
				    <td rowspan="3"  width="17"><div align="center">��<br>��</div></td>
				    <td rowspan="3"  width="17"><div align="center">��<br>��</div></td>
				    <td rowspan="3"  width="13"><div align="center">����<br>��Ա </div></td>
				    <td colspan="5" ><div align="center">�������<br>�����ƺ���� </div></td>
				    <td rowspan="3"  width="27"><div align="center">ƽ��<br>ѧ��<br>���� </div></td>
				    <td rowspan="3"  width="30"><div align="center">ѧ��<br>����<br>�ܺ� </div></td>
				    <td rowspan="3"  width="32"><div align="center">��ѧ��<br>���ѧ�� </div></td>
				    <td rowspan="3"  width="26"><div align="center">Ӣ��<br>�ɼ� </div></td>
				    <td rowspan="3"  width="20"><div align="center">��<br>��<br>��</div></td>
				    <td rowspan="3"  width="24"><div align="center">רҵ<br>�ɼ�<br>���� </div></td>
				    <td rowspan="3"  width="25"><div align="center">רҵ<br>������ </div></td>
				    <td rowspan="3"  width="49"><div align="center">����<br>ְ�� </div></td>
				    <%--
				    <td rowspan="3" class="xl31" width="98"><div align="center">�μӿ������<br>��������� </div></td>
				    <td rowspan="3" class="xl28" width="26"><div align="center">��ע</div></td>
				    <td rowspan="3" class="xl26" width="42"><div align="center">��ί���</div></td> 
				    <td rowspan="3"  width="21"><div align="center">���轱<br>����</div></td>
				      --%>
				  </tr>
				  <tr>
				    <td colspan="3" class="xl24"><div align="center">���㽱ѧ�� </div></td>
				    <td rowspan="2" class="xl24" width="17"><div align="center">����</div></td>
				    <td rowspan="2" class="xl24" width="48"><div align="center">�����ƺż��������� </div></td>
				  </tr>
				  <tr>
				    <td class="xl24" width="17"><div align="center">һ</div></td>
				    <td class="xl24" width="17"><div align="center">��</div></td>
				    <td class="xl24" width="17"><div align="center">��</div></td>
				  </tr>
					  <logic:empty name="rs">
						  <tr>
						  	<td height="31"  width="21">&nbsp;</td>
				    		<td height="31"  width="21">&nbsp;</td>
				    		<td height="31"  width="21">&nbsp;</td>
						    <td width="21" height="31" >&nbsp;</td>
						    <td width="23" height="31" >&nbsp;</td>
						    <td width="38" height="31" >&nbsp;</td>
						    <td width="51" height="31" >&nbsp;</td>
						    <td width="37" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="13" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="17" height="31" >&nbsp;</td>
						    <td width="48" height="31" >&nbsp;</td>
						    <td width="27" height="31" >&nbsp;</td>
						    <td width="30" height="31" >&nbsp;</td>
						    <td width="32" height="31" >&nbsp;</td>
						    <td width="26" height="31" >&nbsp;</td>
						    <td width="20" height="31" >&nbsp;</td>
						    <td width="24" height="31" >&nbsp;</td>
						    <td width="27" height="31" >&nbsp;</td>
						    <%-- 
						    <td width="47" height="31" >&nbsp;</td>
						    <td width="98" height="31" class="xl25">&nbsp;</td>
						    <td width="26" height="31" class="xl24">&nbsp;</td>
						    <td width="42" height="31" class="xl24">&nbsp;</td>
						    --%>
						</tr> 
					  </logic:empty>
					   <logic:notEmpty name="rs">
							  <logic:iterate id="commanForm" name="rs" scope="request">
							           <tr onclick="rowOnClick(this);" style="cursor:hand"
							                 ondblclick="">
										    <td width="21" height="31"  align="left"><bean:write name="commanForm" property="num"/></td>
										    <td height="31"  width="21" align="left"><bean:write name="commanForm" property="ndfw"/></td>
										    <td height="31"  width="21" align="left"><bean:write name="commanForm" property="zxjmc"/></td>
										    <td width="23" height="31"  align="left"><bean:write name="commanForm" property="xy"/></td>
										    <td width="38" height="31"  align="left"><bean:write name="commanForm" property="zybj"/></td>
										    <td width="51" height="31"  align="left"><bean:write name="commanForm" property="xh"/></td>
										    <td width="37" height="31"  align="left"><bean:write name="commanForm" property="xm"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="xb"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="mzmc"/></td>
										    <td width="42" height="31"  align="left"><bean:write name="commanForm" property="dty"/></td>
										    <td width="13" height="31"  align="left"><bean:write name="commanForm" property="ydcs"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="edcs"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="sdcs"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="dxcs"/></td>
										    <td width="17" height="31"  align="left"><bean:write name="commanForm" property="rychother"/></td>
										    <td width="48" height="31"  align="left"><bean:write name="commanForm" property="pjxfjd"/></td>
										    <td width="27" height="31"  align="left"><bean:write name="commanForm" property="xfjdzh"/></td>
											<td width="30" height="31"  align="left"><bean:write name="commanForm" property="bxnxf"/></td>
									    	<td width="32" height="31"  align="left"><bean:write name="commanForm" property="yycj"/></td>	
									    	<td width="26" height="31"  align="left"><bean:write name="commanForm" property="dcj"/></td>	
									    	<td width="20" height="31"  align="left"><bean:write name="commanForm" property="zycjpm"/></td>	
									    	<td width="24" height="31"  align="left"><bean:write name="commanForm" property="zyzrs" /></td>	
									    	<td width="25" height="31"  align="left"><bean:write name="commanForm" property="drzw"/></td>	
									    	<%--
										    <td height="31"  width="21" align="left"><bean:write name="commanForm" property="zxjdm"/></td>
									    	<td width="49" height="31" class="xl24" align="left"><bean:write name="commanForm" property="kyqkother"/></td>	
									    	<td width="98" height="31" class="xl25" align="left"><bean:write name="commanForm" property="bz"/></td>
									    	<td width="26" height="31" class="xl24" align="left"><bean:write name="commanForm" property="pwyj"/></td>	
									    	 --%>
						  	  		   </tr>	
						 	  </logic:iterate>	
				       </logic:notEmpty>
			    </table>
		</fieldset>
		<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="hzbMake('newpage')"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="hzbMake('modipage')"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="hzbMake('del');"
								style="width:80px">
								ɾ ��
							</button>
							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
						</div>
					</center>
				</logic:equal>
    </html:form>
    <script src="js/bottomButton.js" ></script>
  </body>
</html:html>
