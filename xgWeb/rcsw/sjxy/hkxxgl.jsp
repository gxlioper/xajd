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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script type="text/javascript">
		function addhkxx(){
			showTopWin('/xgxt/xshkgl.do?method=addhkxx',600,470);
		}
		function updatehkxx(){
			var num = 0;
			var pkVStr = '';
			var pks = document.getElementsByName('pkV');
			for(var i=0; i<pks.length; i++){
				if(pks[i].checked == true){
					num++; 
					pkVStr +=pks[i].value;
				}
			}
			if(num == 0){
				alert("��ѡ����Ҫ�޸ĵļ�¼��");
				return  false;
			}else if(num > 1){
				alert("һ��ֻ���޸�һ����¼��");
				return  false;
			}
			showTopWin('/xgxt/xshkgl.do?method=updatehkxx&pkVStr='+pkVStr,600,470);
		}
		function deletehkxx(){
			var num = 0;
			var pkVStr = '!@!';	 
			if(confirm("��ȷ��Ҫɾ����")){
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVStr +=pks[i].value+'!@!'; 
					}
				}
				if(num == 0){
					alert("��ѡ����Ҫɾ���ļ�¼��");
					return  false;
				}else{
					$('pkVStr').value=pkVStr;
				}
			
				$('pkVStr').value=pkVStr;	
				document.forms[0].action = "/xgxt/xshkgl.do?method=deletehkxx";
		   		document.forms[0].submit();
			}
				
		}
		function chkView(){
   			var url = "/xgxt/xshkgl.do?method=updatehkxx&view=yes&pkVStr=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
    		showTopWin(url,"600","500");              		                  
 		}

		function queryData(){
			refreshForm('/xgxt/xshkgl.do?method=hkxxgl&doType=query');
		}
		
	</script>
	<body>
		<center>
			<html:form action="/xshkgl" method="post">
				<input type="hidden" name="pkVStr" id="pkVStr" value="" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
				<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã��ճ����� - ѧ�����ڹ��� - ���ڹ���
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" styleId="xy" disabled="true">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td rowspan="2">
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="queryData();">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left">							
									�༶��
									<html:select property="bjdm" styleId="bj" style="width:240px"> 
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;����״̬��
									<html:select property="hkzt" styleId="hkzt">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��ס�˿�">��ס�˿�</html:option>
										<html:option value="��ס�˿�">��ס�˿�</html:option>
										<html:option value="��ס�˿�">��ס�˿�</html:option>
										<html:option value="��Ǩ��">��Ǩ��</html:option>
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:80px"></html:text>
									&nbsp;&nbsp;������
									<html:text property="xm" style="width:100px"></html:text>
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()">
									</td>
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
									ondblclick="chkView()">
									<td align="center">
										<input type="checkbox" name="pkV"
											value="<bean:write name="s" property="xh"/>">
									</td>
									<td align="center">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="zymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="hkzt" />
									</td>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="99%" id="rsTable1" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=mpglForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="addhkxx()" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="updatehkxx()" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="deletehkxx()"
							style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData()"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('ɾ���ɹ���');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('ɾ��ʧ�ܣ�');
		</script>
	</logic:equal>
</html>
