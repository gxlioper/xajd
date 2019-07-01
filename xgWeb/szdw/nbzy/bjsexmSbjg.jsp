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
	function chBzList(jxnd){
		getJxglDAO.getLdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				$(objId).options[0].value = "";
				if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value!= null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
							}
						}
					}
					}
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
		}
	function onShow(){
		var xydm = $("xyV").value;
		if($("userType").value == "xy"){
			for(var i=0;i<$("xy").options.length;i++){
				if($("xy").options[i].value == xydm){
					$("xy").options[i].selected = true;
				}
			}
			$("xy").disabled = true;
		}
	}
	
	jQuery(function(){
		onShow();
	})
	</script>
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyStuInfo" method="post">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" value="<bean:write name="xydm" scope="request"/>"/>
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="lddmV" id="lddmV" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<logic:present name="isBz">
					<p align="center">
						�㲻�ǰ೤����ģ��ֻ���ɰ೤���ʣ���
					</p>
				</logic:present>
				<logic:notPresent name="isBz">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									��Ŀ������ȣ�
									<html:select property="nd" style="width:80px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									<logic:notEqual name="userType" value="stu">
									<logic:notEqual name="userType" value="teacher">
									�꼶��
									<html:select property="nj" style="width:80px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;������
									<html:text property="xm" style="width:85px"></html:text>
									</logic:notEqual>
									</logic:notEqual>
									
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/bjtsxm.do?method=bjtsxmSbjg')">
										��ѯ
									</button>
								</td>
							</tr>
							<logic:notEqual name="userType" value="stu">
							<logic:notEqual name="userType" value="teacher">
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							</logic:notEqual>
							</logic:notEqual>
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
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmSb&type=view&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)">
									<td align="center">
										<bean:write name="s" property="xmmc" />
										<input type="hidden" name="xmdm"
											value="<bean:write name="s" property="xmdm"/>" />
									</td>
									<td align="center">
										<bean:write name="s" property="bzxm" />
									</td>
									<td align="center">
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="xmsqsj" />
									</td>
									<logic:equal name="userType" value="stu">
										<td align="center">
											<bean:write name="s" property="bzrshimg" filter="false" />
										</td>
										<td align="center">
											<bean:write name="s" property="xyshimg" filter="false" />
										</td>
										<td align="center">
											<bean:write name="s" property="xxshimg" filter="false" />
										</td>		
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
										<logic:equal name="userType" value="teacher">
											<td align="center">
												<bean:write name="s" property="bzrshimg" filter="false" />
											</td>
										</logic:equal>
										<logic:equal name="userType" value="xy">
											<td align="center">
												<bean:write name="s" property="xyshimg" filter="false" />
											</td>
										</logic:equal>
										<logic:equal name="userType" value="xx">
											<td align="center">
												<bean:write name="s" property="xxshimg" filter="false" />
											</td>
										</logic:equal>
										<logic:equal name="userType" value="admin">
											<td align="center">
												<bean:write name="s" property="xxshimg" filter="false" />
											</td>
										</logic:equal>
									</logic:notEqual>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:notEqual name="userType" value="stu">
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('��ѡ��Ҫ�޸ĵ���Ϣ!');return false;}showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmSb&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<logic:equal name="userType" value="stu">
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('��ѡ��Ҫ�޸ĵ���Ϣ!');return false;}showOpenWindow('/xgxt/bjtsxm.do?method=bjtsxmSb&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,680,600)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="if(curr_row == null){alert('��ѡ��Ҫɾ������Ϣ!');return false;}refreshForm('/xgxt/bjtsxm.do?method=bjtsxmSb&type=del&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)"
								style="width:80px">
								ɾ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
				</logic:notPresent>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����Ŀ�Ѿ�ͨ����������ˣ��޷�����");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
