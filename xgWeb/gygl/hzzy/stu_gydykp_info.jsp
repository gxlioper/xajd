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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<base target="_self"/>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>	
		<html:form action="/stu_gydykp_info" method="post">
		<input type="hidden" id="pk" name="pk" value="xh||xn||xq||sj" />
		<input type="hidden" id="Pkxh" name="Pkxh" value="<bean:write name="xh" scope="request"/>" />
		<input type="hidden" id="search_go" onclick="refreshForm('/xgxt/stu_gydykp_info.do')"/>
		   <div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��ǰ����λ�ã���Ԣ���� - ��Ԣ�������� - ��ϸ��Ϣ�鿴</span>
				</div>
			</div>
			<fieldset>
					<legend>
						�� ѯ
					</legend>
			<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="2">
								<input type="hidden" name="xh" id="xh" value="<bean:write name="xh" scope="request" />">
								ѧ��:<bean:write name="xh" scope="request" />
								&nbsp;&nbsp;
								������<bean:write name="xm" scope="request" />
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="xn" style="width:100px" styleId="xn" onchange="refreshForm('/xgxt/stu_gydykp_info.do?Pkxh='+$('xh').value)">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:80px" styleId="xq" onchange="refreshForm('/xgxt/stu_gydykp_info.do?Pkxh='+$('xh').value)">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
			</thead>
			</table>
			</fieldset>		
			<br/>
				<br/>
				<fieldset>
					<legend>
						����	&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						
					</legend>
			    <logic:empty name="rsa">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
                <logic:notEmpty name="rsa">   
				<table width="100%" class="tbstyle">
					<thead>						
						<tr>
						    <td align="center" onclick="tableSort(this)" >�к�</td>
						    <td align="center" onclick="tableSort(this)" >ѧ��</td>
							<td align="center" onclick="tableSort(this)" >ѧ��</td>	
						    <td align="center" onclick="tableSort(this)" >����ʱ��</td>
							<td align="center" onclick="tableSort(this)" >������</td>								
							<td align="center" onclick="tableSort(this)" >�������</td>
							<td align="center" onclick="tableSort(this)" >�ӷ����</td>
						</tr>
					</thead>
					<tbody id="rsTables">
					<logic:iterate name="rsa" id="s">
					<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="gykpViewData('view')">
						<td>
						<input type="hidden" value="<bean:write name="s" property="pk"/>" />
						<input type="hidden" value="<bean:write name="s" property="l"/>" />
						<bean:write name="s" property="r"/>
						</td>
						<td><bean:write name="s" property="xn"/></td>
						<td><bean:write name="s" property="xq"/></td>
						<td><bean:write name="s" property="sj"/></td>
						<td><bean:write name="s" property="ssbh"/></td>
						<td><bean:write name="s" property="jlnr"/></td>
						<td><bean:write name="s" property="ryjf"/></td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
				</logic:notEmpty>
			</fieldset>	
				<br/>
				<br/>
				<fieldset>
					<legend>
						����	&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					 <logic:empty name="rsb">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
                <logic:notEmpty name="rsb">   
				<table width="100%" class="tbstyle">
					<thead>						
						<tr>
						    <td align="center" onclick="tableSort(this)" >�к�</td>
						    <td align="center" onclick="tableSort(this)" >����ʱ��</td>
							<td align="center" onclick="tableSort(this)" >������</td>								
								<td align="center" onclick="tableSort(this)" >�������</td>
								<td align="center" onclick="tableSort(this)" >�������</td>
						</tr>
					</thead>
					<tbody id="rsTables">
					<logic:iterate name="rsb" id="s">
					<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="gykpViewData('view')">
						<td>
						<input type="hidden" value="<bean:write name="s" property="pk"/>" />
						<input type="hidden" value="<bean:write name="s" property="l"/>" />
						<bean:write name="s" property="r"/>
						</td>
						<td><bean:write name="s" property="sj"/></td>
						<td><bean:write name="s" property="ssbh"/></td>
						<td><bean:write name="s" property="cfnr"/></td>
						<td><bean:write name="s" property="rykf"/></td>
					</tr>
					</logic:iterate>
					</tbody>
				</table>
				</logic:notEmpty>
			</fieldset>	
			<logic:equal value="yes" name="writeAble" scope="request">
			<div class="buttontool" id="btn" align="center" >			
			<button class="button2" onclick="gykpViewData('add')" style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="gykpViewData('modi')" style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="gykpViewData('del')" style="width:80px">
				ɾ ��
			</button>
		</div>	
		</logic:equal>	
		</html:form>	
  </body>
 <script type="text/javascript">
 function gykpViewData(doType){
	var xh = document.forms[0].xh.value;
	var pkValueA = "";
	var pkValueB = "";
	var pk = document.forms[0].pk.value;
	var url = "/xgxt/detailData.do?act=";
	url += doType;
	url += "&xh=";
	url += xh;
	url += "&pk=";
	url += pk;
	url += "&pkValueA=";
    
	if (doType == "modi" && curr_row == null) {
		alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
		return false;
	}
	
	if (doType == "del"){
		if(curr_row == null){
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
		}else{	
		    pkValueA = curr_row.getElementsByTagName("input")[0].value;
		    pkValueB = curr_row.getElementsByTagName("input")[1].value;		
			url += pkValueA;
			url += "&pkValueB=";
			url += pkValueB;
			if(confirm("ȷ��Ҫɾѡ�м�¼��")){
				refreshForm(url);
				return true;
			}else{
				return false;
			}
		}
	}
	if(doType != "add"){	     
		pkValueA = curr_row.getElementsByTagName("input")[0].value;
		pkValueB = curr_row.getElementsByTagName("input")[1].value;
	}
	url += pkValueA;
	url += "&pkValueB=";
	url += pkValueB;
	if(doType=="add"){
	     getSztzData.getInfoEx("xszsxxb","xh",xh," 1=1 ",function(data){
	        if(data){	        
	          showTopWin(url, 700, 500);
	        }else{
	          alert("����Ŀǰδ��ס���ң����ܽ��е�������");
	          return false;	          
	        }
	    });	    	    	  
	}else{
	     showTopWin(url, 700, 500);
	}		
}
 </script>
</html>
