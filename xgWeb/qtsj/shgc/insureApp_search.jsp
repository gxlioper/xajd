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
	<script language="javascript">
			function modify(){
				var xh = "";
				var nd = "";
				var url = "shgc_sqjgcx.do?method=modTbsq";
				if(curr_row==null){
					alert('��ѡ��Ҫ�޸ĵļ�¼��');
					return;
				}
				xh = curr_row.cells[0].innerText;
				nd = curr_row.cells[2].innerText;
				url += "&xh=";
				url += xh;
				url += "&nd=";
				url += nd;
				showTopWin(url,800,550);
			}
			
			function del(){
				var xh = "";
				var nd = "";
				var url = "shgc_sqjgcx.do?method=delTbsq";
				if(curr_row==null){
					alert('��ѡ��Ҫɾ���ļ�¼��');
					return;
				}				
				xh = curr_row.cells[0].innerText;
				nd = curr_row.cells[2].innerText;
				url += "&xh=";
				url += xh;
				url += "&nd=";
				url += nd;
				if(confirm('��ȷ��ɾ����ѡ��Ϣ��')){
					refreshForm(url);
				}
			}
			
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<body onload="xyDisabled('xy')">
		<html:form action="/shgc_sqjgcx.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��:���չ��� - ������Ϣ - ��������ѯ			
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
						<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="session"/>" />
						<input type="hidden" id="tableName" name="tableName" value="view_xsbxxx"/>
						<input type="hidden" id="realTable" name="realTable" value="xsbxb"/>
							<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										�꼶��
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;��ȣ�
										<html:select property="nd" style="width:100px" styleId="nd">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>							
										&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" style="width:85px"></html:text>
										&nbsp;&nbsp;������
										<html:text property="xm" style="width:85px"></html:text>
										&nbsp;&nbsp;��˽����
										<html:select property="yesNo" styleId="yesNo">
										<html:option value=""></html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
										<html:option value="δ���">δ���</html:option>
										</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/shgc_sqjgcx.do?method=appQue')">
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
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="width:180px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="width:180px" styleId="bj">
												<logic:notEqual value="yes" name="isBzr">
													<html:option value=""></html:option>
												</logic:notEqual>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��ʾ��¼����
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"> ��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ�������� </font>
							</legend>

							<table id="rsTable" class="tbstyle" width="100%" >
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>

									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="modify()">										
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
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
							<logic:equal value="yes" name="writeAble" scope="request">								
								&nbsp;&nbsp;
								<button class="button2" onclick="showTopWin('shgc_sqjgcx.do?method=addTbsq',800,550)" style="width:80px">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="modify()"
									style="width:80px">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="del()"
									style="width:80px">
									ɾ ��
								</button>								
							</logic:equal>
							<logic:equal value="yes" name="writeAble" scope="request">
								&nbsp;&nbsp;
								<button class="button2"
									onclick="impAndChkData();"
									style="width:80px">
									��������
								</button>
							</logic:equal>
							&nbsp;&nbsp;
							<button class="button2" onclick="document.forms[0].target = '_blank';refreshForm('shgc_sqjgcx.do?method=expData');document.forms[0].target = '_self';" style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="xlsDown/.xls" target="_blank">ģ������</a>
						</div>
					</center>
				</div>
			</logic:notEqual>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("�����ɹ���");
      				document.getElementById('search_go').click();
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>

