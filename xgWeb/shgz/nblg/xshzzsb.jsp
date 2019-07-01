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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
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
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/xsgbForNblg" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="xshzzxxsb" />
			<input type="hidden" id="title" name="title"
				value="<bean:write name="title"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�<bean:write name = "title" />
					</div>
				</div>                                                        
				<table width="100%" class="tbstyle"> 
	  			<thead> 
	   			<tr> 
	   			<td align="left" nowrap>
	      		�������ţ� 
	        	<html:select property="bmdm" style="width:230px" styleId="bmdm"> 
	          	<html:option value=""></html:option> 
	          	<html:options collection="bmList" property="bmdm" labelProperty="bmmc" /> 
	        	</html:select> 
	        	<td width="10"  align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xsgbForNblg.do');">
										��ѯ
									</button>
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
									ondblclick="showTopWin('/xgxt/xsgbForNblg.do?method=xshzzxxOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,480)">	
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
				</logic:notEmpty>
				<logic:notEmpty name="result">
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal name = 'isXy'value="yes">
							<logic:equal name = "ksb" value="no">
										
							<div 
								style="width:280px;color:red">
								�����ϱ�ʱ���ڣ�û���ϱ��޸ĵ�Ȩ��	
							</div>					
							</logic:equal>
							</logic:equal>
							<logic:equal name = 'isXy'value="yes">
							<logic:equal name = "ksb" value="yes">
							<button class="button2"
								onclick="showTopWin('/xgxt/xsgbForNblg.do?method=xshzzxxOne',680,480)"
								style="width:80px">
								�� ��
							</button>
							</logic:equal>
							</logic:equal>
							<logic:equal name = 'isXy'value="no">
								<button class="button2"
									onclick="showTopWin('/xgxt/xsgbForNblg.do?method=xshzzxxOne',680,480)"
									style="width:80px">
									�� ��
								</button>
							</logic:equal>
							<logic:equal name = 'isXy'value="yes">
							<logic:equal name = "ksb" value="yes">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2" onclick="if(curr_row == null){alert('��ѡ��Ҫ��˵���!');return false;}else{showTopWin('/xgxt/xsgbForNblg.do?method=xshzzxxOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,300)}"
									style="width:80px">
									�� ��
								</button>
							</logic:equal>
							</logic:equal>
							<logic:equal name = 'isXy'value="no">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="if(curr_row == null){alert('��ѡ��Ҫ��˵���!');return false;}else{showTopWin('/xgxt/xsgbForNblg.do?method=xshzzxxOne&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,300)}"
									style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="if(curr_row == null){alert('��ѡ��Ҫɾ������!');return false;}else{refreshForm('/xgxt/xsgbForNblg.do?method=delXshzzxxOne&go=go&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)}"
									style="width:80px">
								ɾ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
									onclick="impAndChkData();"
									style="width:80px">
										��������
							</button>					
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataStencilExport()" style="width:80px">
								����ģ��
							</button>
							</logic:equal>		
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
