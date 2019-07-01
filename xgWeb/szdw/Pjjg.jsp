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
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript">
	function FdyCjCk(){
	if(document.getElementById("zxm").value == ""){
			alert("��ѡ���ʦ��");
			return false;
		}
	refreshForm('/xgxt/sxjy_jspj.do')
	}
	
	function fdyExp(url){
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
	}	
	function jspjJtDc() {
	document.forms[0].action = "/xgxt/sxjy_jspjJtDc.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
	} 
	function qkywpj() {
		if(confirm('�Ƿ񱸷����ݣ������ȷ�������ݣ������ȡ�������')){
			jspjJtDc();
		}else{
			refreshForm('sxjy_fdypjDel.do')
		}
	}
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/yxglFunction.js"></script>
	<body onload="xyDisabled('xy')">
		<html:form action="/sxjy_jspj" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox" id="btn">
			    <ul>
			    <li> <a href="#" onclick="jspjJtDc()" class="btn_dc"> �������ֵ��� </a> </li>
				<li> <a href="#" onclick="fdyExp('sxjy_fdypjZfDc.do')" class="btn_dc"> �ܷ����򵼳� </a> </li>
				<logic:equal value="11078" name="xxdm" scope="session">
					 <li> <a href="#" onclick="fdyExp('sxjy_xscppTj.do')" class="btn_tj"> ѧ������Ʊͳ�� </a> </li>
	  			</logic:equal>
	  			 <logic:equal name = "writeAble" value="yes">
	  			 	 <li> <a href="#" onclick="qkywpj();" class="btn_hsz"> ����������� </a> </li>
	  			</logic:equal> 
	  			<logic:equal name="xxdm" value="10290" scope="session">
	  				 <li> <a href="#"  onclick="window.open('szdwfzjy.do?method=zwpjb&fs=rep&zgh='+document.getElementById('zxm').value)" class="btn_dy">��ʦ������Ϣ��ӡ </a> </li> 
	  			</logic:equal> 
			    </ul>
			 </div>
			<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				            	<input type="hidden" name="go" value="a" />
				              <button type="button" class="btn_cx" id="search_go" 
				              	onclick="document.forms[0].go.value='go';FdyCjCk()">
				              	�� ѯ
				              </button>
				              &nbsp;&nbsp;&nbsp;&nbsp;
				              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
				              	�� ��
				              </button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					
					<tbody>
					<tr>
						<td align="left">
							����
							<html:select property="xydm" styleId="xy" onchange="refreshForm('sxjy_jspj.do');">
								<html:option value=""></html:option>
								<html:options collection="bmList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							<logic:equal name="xxdm" value="10290" scope="session">
	        					�ֹ��꼶
	        					<html:select property="nj" style="width:80px" styleId="nj" onchange="refreshForm('sxjy_jspj.do');"> 
	          					<html:option value=""></html:option> 
	          					<html:option value="no">δ����</html:option>  
	          					<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        					</html:select> &nbsp;&nbsp;&nbsp;&nbsp; 
	       					</logic:equal>
	       					
	       					<logic:equal value="11078" name ="xxdm" scope="session">
	       					<logic:notEmpty name="zxm" >
	       					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ְ��
	       					<input type="hidden" name="zxm" value="<bean:write name="zxm" />"/>
							<html:select property="zxm" style="width:120px" styleId="zxm" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zgList" property="zgh"
									labelProperty="xm" />
							</html:select>
	       					</logic:notEmpty>
	       					<logic:empty name="zxm" >
	       					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ְ��
							<html:select property="zxm" style="width:120px" styleId="zxm" >
								<html:option value=""></html:option>
								<html:options collection="zgList" property="zgh"
									labelProperty="xm" />
							</html:select>
	       					</logic:empty>
	       					</logic:equal>
	       					
	       					<logic:notEqual value="11078" name ="xxdm" scope="session">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ְ��
							<html:select property="zxm" style="width:120px" styleId="zxm" >
								<html:option value=""></html:option>
								<html:options collection="zgList" property="zgh"
									labelProperty="xm" />
							</html:select>
							</logic:notEqual>
							
						</td>
					<tr>
						<td>
						    �������ֵ�����ѡ�飺
							<html:select property="zdm" style="width:120px" styleId="zdm" >
							<html:option value=""></html:option>
							<html:options collection="cpzList" property="qtdm"
								labelProperty="qtmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<logic:notEmpty name="rs">
		<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ƽ��ֵ���㵽С�������λ
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
									<td>������Ա</td>
									<td>���۷�ֵ</td>
									<td>��������</td>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
							<tr style="cursor:hand">
								<td colspan="3">
										&nbsp;
								</td>
							</tr>
							<tr style="cursor:hand">
									<td nowrap style="color:#ff0033;font-weight:bold" colspan="2" >
										�ܼƷ�ֵ
									</td>
									<td nowrap style="color:#ff0033;font-weight:bold">
										<bean:write name = "count"/>
									</td>	
							</tr>
							</tbody>
					</table>					
			</div>
<%--				<logic:present name="fdyInfo">--%>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
 				<thead>
			    	<tr>
			        	<th colspan="4"><span>ְ��������Ϣ</span></th>
			        </tr>
			    </thead>
			    <tbody>
                  <tr>
                  <th align="center" width="10%" height="30" class="tbstyle">ְ���ţ�</th>
                  <td width="200"><bean:write  name="fdyInfo" property="zgh"/><html:hidden name="fdyInfo" property="zgh" styleId="zgh"/></td>
                  <th width="80" width="20%" align="right" nowrap class="tbstyle">�������ţ�</th>
                  <td><bean:write name="fdyInfo" property="bmmc"/> </td> 
                  </tr>
                  <tr>
                  <th align="center" width="10%" height="30" class="tbstyle">������</th>
                  <td><bean:write name="fdyInfo" property="xm"/> </td> 
                  <th align="center" width="10%"class="tbstyle">ְ��</th>
                  <td><bean:write name="fdyInfo" property="zwmc"/> </td> 
                  </tr>
                  <tr>
                  <th align="center"  width="10%"height="30" class="tbstyle">�Ա�</th>
                  <td><bean:write name="fdyInfo" property="xb"/> </td> 
                  <th align="center" width="10%" class="tbstyle">��ϵ�绰��</th>
                  <td><bean:write name="fdyInfo" property="lxdh"/> </td> 
                  </tr>
                  <tr>
                  <th align="center" width="10%" height="75" valign="top" nowrap class="tbstyle">��Ҫְ��</th>
                  <td colspan="3" valign="top"><bean:write name="fdyInfo" property="zyzz"/></td> 
                  </tr>
                  </tbody>
			      </table>
			      </div>
<%--			    </logic:present>--%>
			  </logic:notEmpty>
			<br />
	        <script type="text/javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
