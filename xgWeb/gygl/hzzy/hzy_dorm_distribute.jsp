<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
      function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
      function setTBGbed(){
          totalBed.innerText="0";
          boyBed.innerText="0";
          girlBed.innerText="0";
      }
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript">
    function fzLd(ele){ 
       var userType = document.getElementById("userType").value;
	   var xxdm = document.getElementById("xxdm").value;
	   if(xxdm=="13429"){//�ϲ���ѧ��ѧ����<bean:message key="lable.xsgzyxpzxy" />      
	      if (userType!="admin") {
		     var tmp = ele.split("-");
		     for (i = 0; i < tmp.length; i++) {
			   document.getElementById(tmp[i]).disabled = true;
		   }
		  }
	  }   
    }
    </script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="fzLd('xq-xb-ld')">
		<html:form action="/dorm_distribute" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldMappingItems" scope="request"/>"/>	
			xbmc		
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ������� - ���Ữ��
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="bjV" id="bjV">	
			<div class="rightcontent">
				<fieldset>
					<legend>
						���Ữ��
					</legend>
					<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE">
						<tr align="center">
							<td width="30%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;У������
								<html:select property="xqdm"  styleId="xq" onfocus="beforSSFPSubmit()"
									onchange="ssFp_Xq()">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;�Ա��޶���
								<html:select property="xb" styleId="xb"  onfocus="beforSSFPSubmit()"
									onchange="if($('xq').value==''){}else{initSsFpLdList();initSsFpSsXxList();}">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
									<html:option value="���">���</html:option>
								</html:select>
								<br>
								&nbsp;&nbsp;<font color="red">*</font>¥������
								<html:select property="lddm" styleId="ld"  onfocus="beforSSFPSubmit()"
									onchange="initSsFpSsXxList();initSsFpFpCsList();initSsFpFpSxXxList();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>							
								<br>
						          &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;�ţ�
						           	<html:select property="cs" styleId="cs"
										onchange="initSsFpSsXxList();initSsFpFpSxXxList();">
										<html:options collection="lcList" property="dm"
										labelProperty="mc" />
									</html:select> 
								<br>    							
							</td>
							<td width="70%" align="left">								
								   �꼶��
									<html:select property="nj" styleId="nj"  onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initBjList();initSsFpFpSxXxList();">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>		
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm"  styleId="xy" onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initBjList();initSsFpFpSxXxList();">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />																						
									</html:select>
									&nbsp;<font color="red">*</font>�༶��
									<html:select property="bjdm"  styleId="bj" onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initSsFpFpSxXxList();">										
										<html:options collection="bjList" property="dm"
											labelProperty="mc" />
									</html:select>							
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;δ����ѧ������(��):
								<span id="allbody" style="width: 70px"><bean:write name="total"scope="request" /></span> (��):
								<span id="allboy" style="width: 70px"><bean:write name="boy" scope="request" /></span>(Ů):
								<span id="allgirl" style="width: 100px"><bean:write name="girl"scope="request" />
								</span> 
								<br>
								 �ѻ�����(��)λ��(��):
								<span id="totalBed" style="width: 70px"><bean:write name="totalBed"scope="request" /></span>(��):
								<span id="boyBed" style="width: 70px"><bean:write name="boyBed"scope="request" /></span> (Ů):
								<span id="girlBed" style="width: 70px"><bean:write name="girlBed"scope="request" /></span>��ϣ�
								<span id="bgBed" style="width: 70px"><bean:write name="bgBed"scope="request" /></span>
							</td>
						</tr>

						<tr align="center">
							<td rowspan="2" valign="top" colspan="2">
								<table width="97%" align="center" class="tbstyle">
									<tr align="center" bgcolor="#D0E0EE">
										<td align="center">
											δ��������
										</td>
										<td></td>

										<td align="center">
											�ѻ������
										</td>
									</tr>

									<tr align="center">
										<td rowspan="2" valign="top">
										<font color="red">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
										<br>											
							¥������/���/�����/�ܴ�λ��/ʣ�ലλ��/��λ��
												<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleList" title=""
												ondblclick="" multiple="multiple">												
												<html:options collection="ssxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td valign="top">
                                            <div title="������ʽ">
											 ������ʽ��
											 <br>
											 <html:select property="fpfs" styleId="fpfs"onchange="initSsFpSsXxList()" onfocus="beforSSFPSubmit()" >
										     <html:option value="ass">������</html:option>
										    <html:option value="acw">����λ</html:option>
									        </html:select>
											</div>	
											<br>
											<br>
											<br>
											<br>
											<br>											
											<font color="blue">����</font>
											<br>
											<button class="button2" onclick="addBatchColum()"
												style="width:50px;height: 20px" title="���Ữ��">
												&rarr;

											</button>
											<br>
											<br>
											<br>
											<br>
										    <font color="blue">�ͷ�</font>
										    <br>
											<button class="button2" onclick="delBatchColum()"
												style="width:50px;height: 20px" title="�����ͷ�">
												&larr;
											</button>
										</td>
										<td valign="top">
										<font color="red">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
										<br>
							                  �༶/¥������/���/�����/�ܴ�λ��/ʣ�ലλ��/���ִ�λ��											
											<html:select property="sql" style="width:100%;" size="26"
												styleId="sql" ondblclick=""multiple="multiple">
												<html:options collection="ssfpList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<input class="button2" type="button" name="button1"
												style="width:100px" value="ȷ ��" 
												<logic:equal value="no" name="writeAble">
												disabled="true"
												</logic:equal>
												onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѻ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){dormDistSave()}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="button2" name="button2"
												style="width:100px" value="�� ��"
												<logic:equal value="no" name="writeAble">
												disabled="true"
												</logic:equal>
												onclick="refreshForm('/xgxt/dorm_distribute.do')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</fieldset>
			</div>
			<div id="tmpdivone"></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>						
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
<%--				    document.forms[0].action = "/xgxt/dorm_distribute.do";--%>
<%--					document.forms[0].submit();--%>
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
<%--				    document.forms[0].action = "/xgxt/dorm_distribute.do";--%>
<%--					document.forms[0].submit();								--%>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
