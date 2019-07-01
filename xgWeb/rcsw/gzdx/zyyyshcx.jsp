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
		function chkView(){
   			var url = "/xgxt/zysyyygl.do?method=viewYyxx&pkVStr=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
    		showTopWin(url,"700","580");              		                  
 		}
		function commomOperation(type,url,chkName,sendVal){
			var num = 0;
			var pkVals = "!@!";
			if("query" == type){
				refreshForm(url);
			}else if("add" == type ){
				showTopWin(url,"700","600");
			}else if("update" == type || "view" == type || "delete" == type){
				var pks = document.getElementsByName(chkName);
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
					}
				}
				if(num == 0){
					alert("��ѡ����Ҫ�����ļ�¼��");
					return  false;
				}
				if("update" == type || "view" == type){
					if(num > 1){
						alert("һ��ֻ�ܲ���һ����¼��");
						return  false;
					}else{
						pkVals = replaceChar(pkVals,"!@!","");
						url += "&"+sendVal+"="+pkVals;
					}
					showTopWin(url,"700","600");
				}else{
					if($('pkVStr')){
						$('pkVStr').value = pkVals;
					}else{
						url += "&"+pkVStr+"="+pkVals;
					}
					refreshForm(url);
				}			
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
		function checkNoDisabled(){
			var array = document.getElementsByName("pkV");
     		for(i=0;i<array.length;i++){
     			if(array[i].disabled != true){
     				array[i].checked=document.getElementsByName("all")[0].checked;
     			}
      			
      		}
    	}
	</script>
	<body>
		<center>
			<html:form action="/zysyyygl" method="post">
					<input type="hidden" name="pkVStr" id="pkVStr" value="" />
					<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
					<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
					<div class="title">
						<div class="title_img" id="title_m">
							��ǰ����λ�ã��ճ����� - ��Դʹ��ԤԼ���� - ��ԴԤԼ��˽����ѯ
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
										<logic:equal value="admin" name="userType">
											<html:select property="bm" style="width:200px">
												<html:option value="">--��ѡ��--</html:option>
												<html:options property="bmdm" labelProperty="bmmc"
													collection="bmList" />
											</html:select>
										</logic:equal>
										<logic:notEqual value="admin" name="userType">
											<html:select property="bm" style="width:200px" disabled="true">
												<html:option value="">--��ѡ��--</html:option>
												<html:options property="bmdm" labelProperty="bmmc"
													collection="bmList" />
											</html:select>
										</logic:notEqual>
										
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
									<td width="10"  align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2"  id="search_go"
											onclick="commomOperation('query','/xgxt/zysyyygl.do?method=zyyyshcx&doType=query');">
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
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td align="center">
											<input type="checkbox" name="all" value="all"
												onclick="checkNoDisabled()">
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
										<logic:equal value="admin" name="userType">
											<input type="checkbox" name="pkV"
												value="<bean:write name="s" property="guid"/>">
										</logic:equal>
										<logic:notEqual value="admin" name="userType">
											<logic:equal value="δ���" name="s" property="xxsh">
												<input type="checkbox" name="pkV"
													value="<bean:write name="s" property="guid"/>">
											</logic:equal>
											<logic:notEqual value="δ���" name="s" property="xxsh">
												<input type="checkbox" name="pkV"
													value="<bean:write name="s" property="guid"/>" disabled="true">
											</logic:notEqual>
										</logic:notEqual>
										</td>
										<td align="center">
											<bean:write name="s" property="bmmc" />
										</td>
										<logic:equal name="xxdm" value="11078">
										<td align="center">
											<bean:write name="s" property="sqsj" />
										</td>
										</logic:equal>
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
											<bean:write name="s" property="sqr" />
										</td>
										<logic:equal name="xxdm" value="11078">
										<td align="center">
											<bean:write name="s" property="shpf" />
										</td>
										</logic:equal>
										<td align="center">
											<bean:write name="s" property="xxsh" />
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
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('add','/xgxt/zysyyygl.do?method=zyyysq&act=add')" style="width:60px">
								�� ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('update','/xgxt/zysyyygl.do?method=viewYyxx&act=update','pkV','pkVStr')" style="width:60px">
								�� ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('delete','/xgxt/zysyyygl.do?method=zyyyshcx&doType=delete','pkV','pkVStr')" style="width:60px">
								ɾ ��
							</button>
							<logic:equal value="admin" name="userType">
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="impAndChkData()" style="width:60px">
									�� ��
								</button>
							</logic:equal>		
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:60px">
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
			alert('�����ɹ���');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('����ʧ�ܣ�');
		</script>
	</logic:equal>
</html>
