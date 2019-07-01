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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">		
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						�� ѯ 
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
						<logic:equal value="yes" name="xmsb">
								<tr>
									<td align="left">
										ѧ�꣺
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;ѧ�ڣ�
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;������Ŀ��
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>	
										&nbsp;&nbsp;��Ŀ���ƣ�
										<html:text property="xmmc" styleId="xmmc"></html:text>								
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">										
										���ţ�
										<html:select property="xydm" styleId="xy"
											style="background-color:#FFFFFF;">
											<option value=""></option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc"></html:options>
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ŀ����
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">
											<option value=""></option>
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="no" name="xmsb">
								<tr>
									<td align="left">
										�꼶��
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										<logic:equal value="no" name="ptcx">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣺
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										</logic:equal>
										&nbsp;&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" style="width:85px"></html:text>
										&nbsp;&nbsp;&nbsp;������
										<html:text property="xm" style="width:85px"></html:text>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>						
						<logic:equal value="view_csmz_cgsbxx" name="tableName">
						    <logic:equal value="true" name="isSH">
						    <ul style="color: red;list-style:none;">
						    <li>
						    1���ѷ���Ȩ�޵İ�ɲ��û�ֻ�ܲ鿴����˱���ѧ���걨����Ŀ�ɹ���
						    </li>
						    <li>
						    2���ѷ���Ȩ�޵�<bean:message key="lable.xsgzyxpzxy" />�û�ֻ�ܲ鿴����˱�<bean:message key="lable.xsgzyxpzxy" />��ѧ���걨����Ŀ�ɹ���
						    </li>
						    <li>
						    3���ѷ���Ȩ�޵�ѧУ�û�ֻ�ܲ鿴ѧ���걨��<font color="blue" style="font-family: sans-serif">"�༶����"</font>��Ŀ�ɹ����ܲ鿴�����'�༶��������'����Ŀ�ɹ���
						    </li>
						    <li>
						    4��<font color="blue" style="font-family: sans-serif">"�༶����"</font>��Ŀ�ɹ�������̣����ѷ���Ȩ�޵İ�ɲ��û����ͨ��-&gt;�ѷ���Ȩ�޵�<bean:message key="lable.xsgzyxpzxy" />�û����ͨ���󼴿���Ч 
						    </li>
						    <li>
						    5��<font color="blue" style="font-family: sans-serif">"�༶��������"</font>����Ŀ������̣������ѷ���Ȩ�޵İ�ɲ��û����ͨ��-&gt;�ѷ���Ȩ�޵�<bean:message key="lable.xsgzyxpzxy" />�û����ͨ��-&gt;�ѷ���Ȩ�޵�ѧУ�û����ͨ�������Ч
						    </li>
						    </ul>
						    </logic:equal>
						</logic:equal>	
						<logic:equal value="view_csmz_tzxmxx" name="tableName">
						    <logic:equal value="true" name="isSH">
						    <ul style="color: red;list-style:none;">						    
						    <li>
						    1���ѷ���Ȩ�޵İ�ɲ��û�ֻ�ܲ鿴����˱�����<font color="blue" style="font-family: sans-serif">"�༶����"</font>����Ŀ�걨��
						    </li>
						    <li>
						    2���ѷ���Ȩ�޵�<bean:message key="lable.xsgzyxpzxy" />�û�ֻ�ܲ鿴����˱�<bean:message key="lable.xsgzyxpzxy" />�ڣ���"ͨ��"�༶��˵�<font color="blue" style="font-family: sans-serif">"�༶����"</font>����Ŀ�걨��
						    �鿴����˱�<bean:message key="lable.xsgzyxpzxy" />�ڵ�<font color="blue" style="font-family: sans-serif">"<bean:message key="lable.xsgzyxpzxy" />����"</font>����Ŀ�걨��
						    </li>
						    <li>
						    3���ѷ���Ȩ�޵�ѧУ�û�ֻ�ܲ鿴<font color="blue" style="font-family: sans-serif">"�༶����"</font>����Ŀ�걨���ܲ鿴�������"ͨ��"<bean:message key="lable.xsgzyxpzxy" />��˵�<font color="blue" style="font-family: sans-serif">"<bean:message key="lable.xsgzyxpzxy" />����"</font>����Ŀ�걨,
						    �鿴�����<font color="blue" style="font-family: sans-serif">"ѧУ��������"</font>����Ŀ�걨��
						    </li>						    
						    </ul>
						    </logic:equal>
						</logic:equal>						
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal value="true" name="isSH">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ˣ�������ͷ��������;</font>
							</logic:equal>
							<logic:equal value="false" name="isSH">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;</font>
							</logic:equal>
						</legend>
<%--						<div--%>
<%--							style="overflow-x:auto;overflow-y:auto;width:810px;height:330px;">--%>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:equal value="true" name="isSH"><!-- ��˲�ѯʱ -->
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "ondblclick="CheckDo()">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:equal value="false" name="isSH"><!-- ����˲�ѯʱ -->
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="MyMoreDo('view')">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
							</table>
<%--						</div>--%>
				  </fieldset>				 
				  <logic:equal value="false" name="isSH">
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
					</logic:equal>
				</logic:notEmpty>
				 <br><br> <br><br>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				  <center>
				  <logic:equal value="false" name="isSH">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button class="button2" onclick="MyMoreDo('add')"
								style="width:80px">
								�� ��
							</button>								
							&nbsp;&nbsp;&nbsp;&nbsp;							
										<button class="button2" onclick="MyMoreDo('modi')"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="button2" onclick="MyMoreDo('del')"
								style="width:80px">
								ɾ ��
							</button>							
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									<button class="button2"--%>
<%--								onclick="showTopWin('/xgxt/data_import.do',600,480)"--%>
<%--								style="width:80px">--%>
<%--								��������--%>
<%--							</button>--%>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							��������
						</button>
						<br>
						&nbsp;&nbsp;
					</div>
					</logic:equal>
					<logic:equal value="true" name="isSH">
					<logic:equal value="csmz_tzcgb" name="realTable">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">					
					<button class="button2" onclick="allBjSh('/xgxt/csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"
								style="width:80px">
								ȫ��ͨ��
					</button>
					<br>
						&nbsp;&nbsp;						
					</div>
					</logic:equal>
					</logic:equal>
				</center>
			</div>		
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("�Զ������ɣ�");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("�Զ����ʧ�ܣ�");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
<script type="text/javascript">

function CheckDo(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	if(realTable=="csmz_tzxmb"){
		url = "/xgxt/csmz_sztz.do?method=tzxm_sh";
	}else if(realTable=="csmz_tzcgb"){
	    url = "/xgxt/csmz_sztz.do?method=tzcg_sh";
	}
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}


function allBjSh(url){
    confirmTxt = "ȫ��ͨ����˽���ĳѧ�ꡢѧ����Ŀ\n���༶Ϊ��λ��������ͨ�����! \n";
    if(document.forms[0].xn.value==""){
		alert(confirmTxt+"��ѡ��ѧ�꣡");
		return false;
    }
    if(document.forms[0].xq.value==""){
        alert(confirmTxt+"��ѡ��ѧ�ڣ�");
        return false;
    }
    if (document.forms[0].bjdm.value == "") {
		alert("��ѡ��༶��");
		return false;
	}else{
	   confirmTxt = "ȫ��ͨ����˽��ķѽϳ���ʱ�䣬ȷ��Ҫ��ʼ�Զ������";
		if (confirm(confirmTxt)) {		
		    alert("���\"ȷ��\"��ʼ��ˣ�");		    
		    sztzShowTips('���ڽ���������ˣ����Ժ�......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}	
}
function myxyDisabled(ele) {
    var userT = document.getElementById("userType").value;
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
</script>
</html>	
