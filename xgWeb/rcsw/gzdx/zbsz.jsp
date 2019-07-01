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
	<script type="text/javascript">
		function queryData(){	
			refreshForm('/xgxt/zbrygl.do?method=zbsz&doType=query');
		}
		function setZbry(){
			var num = 0;
			var pk = '';
			var pks = document.getElementsByName('pkV');
			for(var i=0; i<pks.length; i++){
				if(pks[i].checked == true){
					num++;
					pk +=pks[i].value; 
				}
			}
			if(num == 0){
				alert("�빴ѡ��Ҫ�����ļ�¼��");
				return  false;
			}else if(num > 1){
				alert("һ��ֻ�ܲ���һ����¼��");
				return  false;
			}else{
				showTopWin('/xgxt/zbrygl.do?method=zbsz&doType=zbsz&pk='+pk,'850','600');
			}
		}
		
 		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
	</script>
	<body>
		<center>
			<html:form action="/zbrygl" method="post">
				<logic:equal value="no" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">��ҳ��ֻ�����ʦ��ѧ������Ա���ʣ�</font>
					</p>
				</logic:equal>
				<logic:equal value="wqy" name="view">
					<br>
					<br>
					<br>
					<p align="center">
						<font color="red" size="2">
						����ѧ������ԱȨ��û���ã�
						</font>
					</p>
				</logic:equal>
				<logic:notEqual value="no" name="view">
				<logic:notEqual value="wqy" name="view">
					<div class="title">
						<div class="title_img" id="title_m">
							��ǰ����λ�ã��ճ����� - ֵ����Ա���� - ֵ������
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
										ʹ�ò��ţ�
										<html:select property="bm" style="width:200px">
											<html:option value="">--��ѡ��--</html:option>
											<html:options property="bmdm" labelProperty="bmmc"
												collection="bmList" />
										</html:select>
										
										&nbsp;&nbsp;&nbsp; ԤԼ���أ�
										<html:select property="cddm" style="width:180px">
											<html:option value="">--��ѡ��--</html:option>
											<html:options property="dm" labelProperty="mc"
												collection="cdList" />
										</html:select>
										&nbsp;&nbsp;&nbsp;ԤԼ���ڣ�
										<html:text property="yyrq" styleId="yyrq" readonly="true" style="width:100px"
											onclick="this.value='';return showCalendar('yyrq','y-mm-dd');"
											onblur="getRqVal('yyrq')"></html:text>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle" style="40px">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2"  id="search_go"
											onclick="queryData();">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										�Ƿ�������ֵ����Ա��
										<html:select property="zbry">
											<html:option value="">--��ѡ��--</html:option>
											<html:option value="wsz">δ����</html:option>
											<html:option value="ysz">������</html:option>
										</html:select>
										&nbsp;&nbsp;&nbsp;ֵ����������
										<html:text property="zbryxm"></html:text>
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
								<font color="blue">��ʾ��������ͷ��������</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td align="center">
											ѡ��
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
												value="<bean:write name="s" property="guid"/>">
										</td>
										<td align="center">
											<bean:write name="s" property="bmmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="cdmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="yyrq" />
										</td>
										<td align="center">
											<bean:write name="s" property="yysjd" />
										</td>
										<td align="center">
											<bean:write name="s" property="fzr" />
										</td>
										<td align="center">
											<bean:write name="s" property="lxdh" />
										</td>
										<td align="center">
											<bean:write name="s" property="zbry" />
										</td>
										<td align="center">
											<bean:write name="s" property="zbdh" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=rcswgzdxForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
						</TABLE>
						<br />
						<br />
					</logic:notEmpty>
					<center>
						<logic:notEqual name="isFdy" value="true">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="setZbry()" style="width:100px">
								����ֵ����Ա
							</button>
						</div>
						<script language="javascript">
							document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
							document.getElementById("btn").style.width = "98%";
							window.setInterval("initBTNTool('btn')",1);
						</script>
						</logic:notEqual>
					</center>
				</logic:notEqual>
				</logic:notEqual>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('�����ɹ���');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('����ʧ�ܣ�');
		</script>
	</logic:equal>
</html>
