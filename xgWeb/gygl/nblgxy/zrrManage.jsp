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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/nblgxy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - ��������ϵ��

				</div>
			</div>
<%--			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />--%>
<%--			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />--%>
			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap >								
								¥��:
								<html:select property="lddm" style="width:120px"
										 styleId="lddm" onchange="getSsbhLb()">										
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;&nbsp; ���Һ�
								<html:select property="ssbh" style="width:120px" styleId="qsh">									
									<html:options collection="ssbhList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;�Ƿ���ְ��
								<html:select  property="sfzz" style="width:90px" styleId="sfzz">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
															
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/nblgxy_gygl.do?method=zrrManage')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" style="width: 120px" styleId="xh" />
								&nbsp;&nbsp;&nbsp;&nbsp;������
								<html:text property="xm" style="width: 120px" styleId="xm" />

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
						��ǰ��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="zrrManageView()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="zrrManageAdd()"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="zrrManageModi()"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="zrrDel()"
							style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
					</div>
				</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
	<script type="text/javascript">
	       function zrrManageAdd(){
	            var url = "/xgxt/nblgxy_gygl.do?method=zrrAdd";
	            showTopWin(url,"700","550");
	       }
	       function zrrManageModi(){
	           if (curr_row == null) {
		          alert("��ѡҪ�޸ĵļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	            var url = "/xgxt/nblgxy_gygl.do?method=zrrModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,"700","550");
	           }
	       }
	       function zrrManageView(){	          
	            var url = "/xgxt/nblgxy_gygl.do?method=zrrModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            url +="&view=true";
	            showTopWin(url,"700","550");	          
	       }
	       function zrrDel(){
	           if (curr_row == null) {
		          alert("��ѡҪɾ���ļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	              if(confirm("ȷ��Ҫɾ���ü�¼��")){
	                 var url = "/xgxt/nblgxy_gygl.do?method=zrrDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	                 refreshForm(url);
	              }else{	 
	                 return false;           	            
	              }
	          }
	       }

	</script>
</html>
