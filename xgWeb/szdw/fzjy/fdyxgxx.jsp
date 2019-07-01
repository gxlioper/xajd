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
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="/xgxt/dwr/interface/GetBmList.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('xydm').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('xydm').disabled=false;
		}
	}
	
	function yzRadio(url,w,h){
	var tmps = document.getElementsByName("tableName");
	var sfxz = false;
	for(i = 0; i<tmps.length;i++){
		if(tmps[i].checked){
			sfxz = true;
		}
	}
	if(sfxz){
		return true;
	}else{
		alert("��ѡ��Ҫ�����ı�");
		return false;
	}	
	}
	
	function getBmmcValue(){
   			var getSelectValue = document.getElementById("bmmcChoose").value;
   			document.getElementById("bmmc").value = getSelectValue;         
	}
	function getBmmcYzList(){
		var bmmc = document.getElementById("bmmc").value;
		if(bmmc.length!=0){
			getBmmcList();
		}
	}
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="fdyxgxx" />
			<input type="hidden" id="title" name="title"
				value="<bean:write name="title"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
			<input type = "hidden" name = "bmmcChooseV" value = ""/> 
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�<bean:write name = "title" />
					</div>
				</div>
				<table width="100%" class="tbstyle"> 
	  			<thead> 
	   			<tr> 
	      		<td align="left" nowrap>
	      				����Ա����
	        	<html:text  property="xm" />
	        	&nbsp;&nbsp;&nbsp;&nbsp;�������ţ� 
	        
							<html:text  property="bmmc" styleId="bmmc" style="width:220px;height:21px;font-size:10pt;" onmouseout="getBmmcYzList()"/><span style="width:18px;border:0px solid red;">
							<html:select  property="bmmcChoose" styleId="bmmcChoose" style="margin-left:-220px;width:238px;" onchange="getBmmcValue()">
									<html:options collection="bmList" property="bmmc" labelProperty="bmmc" />
							</html:select>
							</span>
	        	</td>
	        	<td width="10"  align="center" rowspan="2" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="if(yzRadio()){allNotEmpThenGo('/xgxt/szdwfzjy.do');}">
										��ѯ
									</button>
								</td>
	    		</tr> 
	    		<tr>
								<td align="left" nowrap>
									<html:radio property="tableName" value="view_fzjyfdymxfw" >����Ա������У������Ϣ</html:radio>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:radio property="tableName" value="view_fzjyfdyjnds" >����Ա���ܴ�����Ϣ</html:radio>
								</td>
				</tr>
	  			</thead> 
				</table> 
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						��δ���κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="fzjyYzRadio('/xgxt/szdwfzjy.do?method=fdyxgxxOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&tableName=',600,480)">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:notEqual value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:notEqual>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="result">
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2"
								onclick="fzjyYzRadio('/xgxt/szdwfzjy.do?method=fdyxgxxOne&tableName=',600,400);"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="if(curr_row == null){alert('��ѡ��Ҫ�޸ĵ���!');return false;}else{fzjyYzRadio('/xgxt/szdwfzjy.do?method=fdyxgxxOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value+'&tableName=',600,480)}"
									style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="if(curr_row == null){alert('��ѡ��Ҫɾ������!');return false;}else{refreshForm('/xgxt/szdwfzjy.do?method=delFdyxgxxOne&go=go&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)}"
									style="width:80px">
								ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="impAndChkData();"
									style="width:80px">
										��������
							</button>					
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataStencilExport()" style="width:80px">
								����ģ��
							</button>		
						</div>
				</logic:equal>
				<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("ɾ���ɹ�!");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("ɾ��ʧ��");
                    </script>
				</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>