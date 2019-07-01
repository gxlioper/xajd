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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/jsfunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	
	if (!confirm("ɾ���Զ����ֶλ�ͬʱɾ���洢���ֶ���Ϣ�ı��и��ֶε����ݣ�ȷ��Ҫɾ��ѡ����Զ����ֶΣ�")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}
	</script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - �������� - �Զ����ֶ�ά��
				</div>
			</div>
			<input type="hidden" id="tabName" name="tabName" value="view_xbemy_qgzx_zdwhxxb"/>
			<input type="hidden" id="realTable" name="realTable" value="xbemy_qgzx_zdwhxxb"/>
			<input type="hidden" id="pkDel" name="pkDel" value=""/>
			<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									������Ŀ��
									<html:select property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
								<input type="hidden" name="go" value="a"/>
									<button type="button" class="button2" id="search_go"
										onclick="refreshForm('xbemyQgzx.do?method=fieldManager&go=go');">
										��ѯ
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
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
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="showTopWin('xbemyQgzx.do?method=showFieldManager&doType=view&act=modify&pkValue='+curr_row.cells[2].innerText,500,400)">
									<td align="center">
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="showTopWin('xbemyQgzx.do?method=showFieldManager&act=add',500,400)"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="javascript:if(curr_row==null){alert('��ѡ����Ӧ���У�')}else{showTopWin('xbemyQgzx.do?method=showFieldManager&doType=view&act=modify&pkValue='+curr_row.cells[2].innerText,500,400)}"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="del('xbemyQgzx.do?method=fieldManager&doType=del');"
							style="width:80px">
							ɾ ��
						</button>
				</div>
			</center>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ���');
					document.getElementById("search_go").onclick();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert('����ʧ�ܣ�');
					document.getElementById("search_go").onclick();
					</script>
				</logic:equal>
			</logic:present>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
