<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
function tz(){
	$("tjlx").value = "";
	$("xydm").value = "";
	refreshForm('/xgxt/nbzyjsxy_xszz.do?method=data_jkrxx');
}
function getTjlxList(){
	var tjlx = $("tjlx").value;
	if (tjlx=="3") {
		$("xydm").disabled = false;
	} else {
		$("xydm").value = "";
		$("xydm").disabled = true;
	}
}
function tjyz(){
	var tjlx = $("tjlx").value;
	var xydm = $("xydm").value;
	
	if (tjlx=="3") {
		if (xydm == null || xydm == "") {
			alert("��ѡ����Ҫͳ�Ƶ�<bean:message key="lable.xsgzyxpzxy" />!");
			return false;
		}
	}
	refreshForm('/xgxt/nbzyjsxy_xszz.do?method=jkrxxTj&go=go');
}
function dy(){
	if ($("rsTable") != null) {
		expTab('rsTable','','');
	}
}
	</script>
	<body>
		<html:form action="/nbzyjsxy_xszz.do?method=jkrxxTj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��������ά�� - �����ͳ����Ϣ
				</div>
			</div>
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ͳ������
								<html:select property="tjlx" styleId="tjlx"
										onchange="getTjlxList()" >
										<html:option value="1">��<bean:message key="lable.xsgzyxpzxy" /></html:option>
										<html:option value="3">��רҵ</html:option>
										<html:option value="2">����ɫ</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />
								<html:select property="xydm" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:20px" id="search_go"
									onclick="tjyz();">
									ͳ��
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
	                <div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
	                	<button type="button" class="button2" onclick="dy();" style="width:80px">
							��ӡ�б�
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="tz();" style="width:80px">
							�������Ϣ
						</button>
					</div>
					<div id="tmpdiv"></div>
				</div>
			</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
	<script language="javascript">
		getTjlxList();
	</script>
</html>
