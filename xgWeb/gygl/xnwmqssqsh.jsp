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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function dataExport(){
			url = "XsgyglDispatch.do?method=expYdwmqssqxx";			
			var eleArr = ["xn","lddm","qsh","sqsjks","sqsjjs","fdysh","xxsh","xysh"];
			for(var i=0; i<eleArr.length; i++){
				if(ele(eleArr[i])){
					url += "&";
					url += eleArr[i];
					url += "=";
					url += val(eleArr[i]);
				}
			}
			window.open(url);
		}
		
		function modifyData(w,h){
			if(curr_row == null){
				alert("��ѡ��һ����Ҫ�����ļ�¼��");
				return false;
			}
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			showTopWin('XsgyglDispatch.do?method=audiXnwmqssq&pk=' + pkValue,w,h);
		}
		
		function sumitInfo(url,doType){
			var checkBoxArr = document.getElementsByName("cbv");
			var flag = false;
			
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			
			if(flag){
				url+="&doType="+doType;
				if (confirm("ȷ��Ҫ��������ѡ������?")) {
					showTips('���������У���ȴ�......');
					refreshForm(url);
				}
			}else{
				alert("�빴ѡҪ���������");
				return false;
			}
		}
	</script>	
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/XsgyglDispatch.do" method="post">
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>" />
		    <input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />															
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ��� - ��������������� - ѧ�����������������
				</div>
			</div>			
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" style="width:90px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;¥����:
								<html:select property="lddm" style="width:120px"
									onchange="GetQshList()" styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								&nbsp;&nbsp;���Һţ�
								<html:select property="qsh" style="width:110px" styleId="qsh">
									<html:option value=""></html:option>
									<html:options collection="qshList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=xnwmqssh');">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								����ʱ�䣺
								<html:text property="sqsjks" onblur="dateFormatChg(this)"onclick="return showCalendar('sqsjks','y-mm-dd');" style="cursor:hand;width:80px " />
								��
								<html:text property="sqsjjs" onblur="dateFormatChg(this)"onclick="return showCalendar('sqsjjs','y-mm-dd');" style="cursor:hand;width:80px " />
								<!--����Ա-->
								<logic:equal value="fdy" name="yhType">
									&nbsp;&nbsp;����Ա��ˣ�
									<html:select property="fdysh" styleId="fdysh">
									    <html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp;ѧУ��ˣ�
									<html:select property="xxsh" styleId="xxsh">
									    <html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<!--end����Ա-->
								<!--<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:equal value="xy" name="yhType">
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
									<html:select property="xysh" styleId="xysh">
									    <html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp;ѧУ��ˣ�
									<html:select property="xxsh" styleId="xxsh">
									    <html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<!--end<bean:message key="lable.xsgzyxpzxy" />-->
								<!--ѧУ-->
								<logic:equal value="xx" name="yhType">
									&nbsp;&nbsp;ѧУ��ˣ�
									<html:select property="xxsh" styleId="xxsh">
									    <html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<!--endѧУ-->
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
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="selall" name="selall" onclick="selAll()">
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
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modifyData(700,600)"
								style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="2" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
								<td align=center>
									<input type="checkbox" id="cbv" name="cbv" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> 
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
							    </td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="100%" id="Table" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD style="height:3"></TD>
						</TR>
					</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="tmpdiv"></div>
			<br><br><br><br>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px">
					<button class="button2" onclick="sumitInfo('XsgyglDispatch.do?method=xnwmqssh&shjg=ͨ��','audi')" style="width:80px">
						ͨ ��
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="sumitInfo('XsgyglDispatch.do?method=xnwmqssh&shjg=��ͨ��','audi')" style="width:80px">
						�� ͨ ��
					</button>
				</div>
			</center>
		</html:form>	
		<logic:present name="mes">
			<input id='mes' name='mes' value='${mes}' type='hidden'/>
		</logic:present>	
		<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:equal value="true" name="result">
			<script type="text/javascript">
			    alert('�����ɹ���');
			 </script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script type="text/javascript">
			    if(ele('mes')){
					alert(val('mes'));
				}else{
			    	alert('����ʧ�ܣ�');
			    }
			  </script>
		</logic:equal>
  </body> 
</html>
