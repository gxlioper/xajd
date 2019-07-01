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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function jsghSearch(){
	var url = "/xgxt/jxglgt.do?method=jxghSearch";
	var doType = document.getElementById('doType').value;
	var pkValue = document.getElementById('pkValue').value;
	
	url += "&doType=";
	url += doType;
	url += "&pkValue=";
	url += pkValue;
	url += "&gb=yes";
	showTopWin(url,700,500);
	}
	
	function saveGbxx(){
	if($("xxdm").value == "13022"){
		var gblx = document.getElementById("gblx").value;
		var dwlx = document.getElementById("dwlx").value;
		var xh = document.getElementById("xh").value;
		var jsdm = document.getElementById("jsdm").value;
		var bz = document.getElementById("bz").value;
		var cxgb = document.getElementById("cxgb").value;
		var jxzw = document.getElementById("jxzw").value;
		
		if(gblx == "xs"){
			if(xh == ""){
				alert("ѧ�Ų���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}else if(gblx == "js"){
			if(jsdm == ""){
				alert("ְ���Ų���Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
		if(dwlx == "jg"){
			if(jxzw == "0000"){
				alert("��ѵְλ����Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}else if(dwlx == "cx"){
			if(cxgb == "0000"){
				alert("��ѵ�ɲ�����Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
		if(bz.length > 200){
			alert("��ע��������200�֣���ȷ�ϣ���");
			return false;
		}
	}else{
		var xh = document.getElementById("xh").value;
		var bz = document.getElementById("bz").value;
		if(xh == ""){
				alert("ѧ�Ų���Ϊ�գ���ȷ�ϣ���");
				return false;
		}
		if(bz.length > 200){
			alert("��ע��������200�֣���ȷ�ϣ���");
			return false;
		}
	}
	
	return true;
	}
	function editGbxx(){
		if($("xxdm").value == "13022"){
			var dwlx = document.getElementById("dwlx").value;
			var jxzw = document.getElementById("jxzw").value;	
		}
		var bz = document.getElementById("bz").value;
		var cxgb = document.getElementById("cxgb").value;
		
		if($("xxdm").value == "13022"){
			if(dwlx == "jg"){
				if(jxzw == "0000"){
					alert("��ѵְλ����Ϊ�գ���ȷ�ϣ���");
					return false;
				}
			}else if(dwlx == "cx"){
				if(cxgb == "0000"){
					alert("��ѵ�ɲ�����Ϊ�գ���ȷ�ϣ���");
					return false;
				}
			}
		}else{
			if(cxgb == "0000"){
				alert("��ѵ�ɲ�����Ϊ�գ���ȷ�ϣ���");
				return false;
			}
		}
		if(bz.length > 200){
			alert("��ע��������200�֣���ȷ�ϣ���");
			return false;
		}
		Savedata('','jxglgt.do?method=jxcxgbOne&type=save');
	}
	function chlx(value){
		if(value == "js"){
			$("dwlxV").value="jg";
			$("dwlx").value="jg";
			$("dwlxV").disabled = true;
			$("buttonFindJsgh").style.display = "";
			$("zgh").style.display = "";
			$("jsdm").style.display = "";
			$("xh").style.display = "none";
			$("buttonFindXh").style.display = "none";
			$("xhB").style.display = "none";
			
			$("xh").value = "";
			$("xm").value = "";
			$("bmmc").value = "";
			$("xb").value = "";
			$("mzdm").value = "";
			
		}else if (value == "xs"){
			$("dwlxV").disabled = false;
			$("buttonFindJsgh").style.display = "none";
			$("zgh").style.display = "none";
			$("jsdm").style.display = "none";
			$("xh").style.display = "";
			$("buttonFindXh").style.display = "";
			$("xhB").style.display = "";
			
			$("jsdm").value = "";
			$("xm").value = "";
			$("bmmc").value = "";
			$("xb").value = "";
			$("mzdm").value = "";
		}
	
	}
	function chgb(value){
		if(value == "jg"){
			$("dwlx").value = "jg"
			$("cx").style.display = "none";
			$("jg").style.display = "";
			$("lddm").value = "";
			$("cxgb").value = "";	
		}else if(value == "cx"){
			$("dwlx").value = "cx"
			$("cx").style.display = "";
			$("jg").style.display = "none";
			$("jxdw").value = "";
			$("jxzw").value = "";
		}
	}
	function onShow(){
		if($("xxdm").value == "13022"){
			if($("isXh")){
				if($("isXh").value == "yes"){
					$("dwlxV").disabled = false;
					$("buttonFindJsgh").style.display = "none";
					$("zgh").style.display = "none";
					$("jsdm").style.display = "none";
					$("xh").style.display = "";
					$("buttonFindXh").style.display = "";
					$("xhB").style.display = "";
					$("gblx").value = "xs";
				}
			}
		}
	}
	function chDwList(dwdm){
		getJxglDAO.getzwList(dwdm,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "jxzw";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"zwdm","zwmc");		
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
			}
		});
	}
	
	function chBzList(jxnd){
		getJxglDAO.getLdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
	}
	
	function chZwList(bzdm){
		getJxglDAO.getBzzwList(bzdm,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "cxgb";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"zwdm","zwmc");		
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
	}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="onShow()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<html:form action="/jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ѵ���� - ��Ϣά�� - ��ѵ�ɲ���Ϣά��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�޼�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="isXh" name="isXh"
					value="<bean:write name="rs" property="isXh"/>" />
				<input type="hidden" id="url" name="url"
					value="/jxgl/gt/jxcxgbOne.jsp" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<fieldset>
					<legend>
						��ѵ�ɲ���Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<logic:equal value="13022" name="xxdm">
								<p id="zgh">
									<font color="red">*</font>ְ���ţ�
								</p>
								<p id="xhB" style="display:none">
									<font color="red">*</font>ѧ�ţ�
								</p>
								</logic:equal>
								<logic:notEqual value="13022" name="xxdm">
									<p id="xhB">
										<font color="red">*</font>ѧ�ţ�
									</p>
								</logic:notEqual>
							</td>
							<td align="left">
								<logic:equal value="13022" name="xxdm">
									<logic:equal value="edit" name="doType">
										<html:text name="rs" property="jsdm" styleId="jsdm"
											readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="edit" name="doType">
										<html:text name="rs" property="jsdm" styleId="jsdm"
											onchange="searchLs(this)"></html:text>
										<html:text name="rs" property="xh" styleId="xh"
											style="display:none"></html:text>
										<button type="button" onclick="jsghSearch()" class="button2"
											id="buttonFindJsgh">
											ѡ��
										</button>
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="button2" id="buttonFindXh" style="display:none">
											ѡ��
										</button>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="13022" name="xxdm">
									<logic:notEqual value="add" name="doType">
										<html:text name="rs" property="jsdm" styleId="jsdm"
											readonly="true"></html:text>
									</logic:notEqual>
									<logic:equal value="add" name="doType">
									<html:text name="rs" property="xh" styleId="xh"></html:text>
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="button2" id="buttonFindXh">
										ѡ��
									</button>
									</logic:equal>
								</logic:notEqual>
							</td>
							<logic:equal value="13022" name="xxdm">
								<td align="right">
									�ɲ����ͣ�
								</td>
								<td align="left">
									<logic:notEqual value="edit" name="doType">
										<html:select name="rs" property="gblx" style="width:150px"
											onchange="chlx(this.value)">
											<html:option value="js">��ʦ</html:option>
											<html:option value="xs">ѧ��</html:option>
										</html:select>
									</logic:notEqual>
									<logic:equal value="edit" name="doType">
										<html:select name="rs" property="gblx" style="width:150px"
											onchange="chlx(this.value)" disabled="true">
											<html:option value="js">��ʦ</html:option>
											<html:option value="xs">ѧ��</html:option>
										</html:select>
									<html:hidden name='rs' property="gblx" styleId="gblx" />
									</logic:equal>
								</td>
							</logic:equal>	
							<logic:notEqual value="13022" name="xxdm">	
								<td align="right">
									�����꼶��
								</td>
								<td align="left">
									<html:select property="jxnd" name="rs" styleId="jxnd" onchange="chBzList(this.value)">
									<html:options collection="njList" labelProperty="njmc" property="njdm"/>
									</html:select>
								</td>
							</logic:notEqual>				
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
							<td align="right">
								�������ţ�
							</td>
							<td align="left">
								<input type="text" name="bmmc"
									value="<bean:write name="rs" property="bmmc"/>" readonly="true" />
								<input type="hidden" name="xydm"
									value="<bean:write name="rs" property="xydm"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<input type="text" name="xb"
									value="<bean:write name="rs" property="xb"/>" readonly="true" />
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<html:select name="rs" property="mzdm" styleId="mz"
									disabled="true">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
								<input type="hidden" name="mzdmV"
									value="<bean:write name="rs" property="mzdm"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�칫�绰��
							</td>
							<td align="left">
								<html:text name="rs" property="bgdh" styleId="bgdh"  maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
							<td align="right">
								סլ�绰��
							</td>
							<td align="left">
								<html:text name="rs" property="zzdh" styleId="zzdh"  maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ���
							</td>
							<td align="left">
								<html:text name="rs" property="sjdh" styleId="sjdh" maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
							<td align="right">
								����ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="xnh" styleId="xnh" maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
						</tr>
						<logic:equal value="13022" name="xxdm">
						<tr>
							<td align="right">
								��ѵ��ȣ�
							</td>
							<td align="left">
								<html:select property="jxnd" name="rs" styleId="jxnd" onchange="chBzList(this.value)">
								<html:options collection="xnList" labelProperty="nd" property="nd"/>
								</html:select>
							</td>
							<td align="right">
								�ɲ����ͣ�
							</td>
							<td align="left">
								 <html:select name="rs" property="dwlxV" style="width:100px"
									onchange="chgb(this.value)" disabled="true">
									<html:option value="jg">������Ա</html:option>
									<html:option value="cx">��ѵ��Ա</html:option>
								</html:select>	
								<html:hidden name="rs" property="dwlx" value="jg"/>							
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual value="13022" name="xxdm">
							<tr>
								<td align="right">
									��ѵ���ƣ�
								</td>
								<td align="left">
									<html:select property="lddm" name="rs" styleId="lddm" onchange="chZwList(this.value)">
										<html:options collection="ldList" labelProperty="bzmc"
											property="bzdm" />
									</html:select>
								</td>
								<td align="right">
									��ѵ�ɲ���
								</td>
								<td align="left">
									<html:select property="cxgb" name="rs" styleId="cxgb">
										<html:options collection="zwList" labelProperty="zwmc"
											property="zwdm" />
									</html:select>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="13022" name="xxdm">
							<logic:equal value="add" name="doType">
							<tr id="jg">
								<td align="right">
									��ѵ��λ��
								</td>
								<td align="left">
									<html:select property="jxdw" name="rs" styleId="jxdw" onchange="chDwList(this.value)">
										<html:options collection="dwList" labelProperty="bzmc"
											property="bzdm" />
									</html:select>
								</td>
								<td align="right">
									��ѵְλ��
								</td>
								<td align="left">
									<html:select property="jxzw" name="rs" styleId="jxzw">
										<html:options collection="zwList" labelProperty="zwmc"
											property="zwdm" />
									</html:select>
								</td>
							</tr>
							<tr id="cx" style="display:none">
								<td align="right">
									��ѵ���ƣ�
								</td>
								<td align="left">
									<html:select property="lddm" name="rs" styleId="lddm" onchange="chZwList(this.value)">
										<html:options collection="ldList" labelProperty="bzmc"
											property="bzdm" />
									</html:select>
								</td>
								<td align="right">
									��ѵ�ɲ���
								</td>
								<td align="left">
									<html:select property="cxgb" name="rs" styleId="cxgb">
										<html:option value="0000">----��ѡ��----</html:option>
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<logic:notEqual value="add" name="doType">
								<logic:equal value="jg" name="dw">
								<tr id="jg">
									<td align="right">
										��ѵ��λ��
									</td>
									<td align="left">
										<html:select property="jxdw" name="rs" styleId="jxdw" onchange="chDwList(this.value)">
											<html:options collection="dwList" labelProperty="bzmc"
												property="bzdm" />
										</html:select>
									</td>
									<td align="right">
										��ѵְλ��
									</td>
									<td align="left">
										<html:select property="jxzw" name="rs" styleId="jxzw">
											<html:options collection="zwList" labelProperty="zwmc"
												property="zwdm" />
										</html:select>
									</td>
								</tr>
								<tr id="cx" style="display:none">
									<td align="right">
										��ѵ���ƣ�
									</td>
									<td align="left">
										<html:select property="lddm" name="rs" styleId="lddm" onchange="chZwList(this.value)">
											<html:options collection="ldList" labelProperty="bzmc"
												property="bzdm" />
										</html:select>
									</td>
									<td align="right">
										��ѵ�ɲ���
									</td>
									<td align="left">
										<html:select property="cxgb" name="rs" styleId="cxgb">
											<html:option value="0000">----��ѡ��----</html:option>
										</html:select>
									</td>
								</tr>
								</logic:equal>
								<logic:equal value="cx" name="dw">
								<tr id="jg" style="display:none">
									<td align="right">
										��ѵ��λ��
									</td>
									<td align="left">
										<html:select property="jxdw" name="rs" styleId="jxdw" onchange="chDwList(this.value)">
											<html:options collection="dwList" labelProperty="bzmc"
												property="bzdm" />
										</html:select>
									</td>
									<td align="right">
										��ѵְλ��
									</td>
									<td align="left">
										<html:select property="jxzw" name="rs" styleId="jxzw">
											<html:options collection="zwList" labelProperty="zwmc"
												property="zwdm" />
										</html:select>
									</td>
								</tr>
								<tr id="cx">
									<td align="right">
										��ѵ���ƣ�
									</td>
									<td align="left">
										<html:select property="lddm" name="rs" styleId="lddm" onchange="chZwList(this.value)">
											<html:options collection="ldList" labelProperty="bzmc"
												property="bzdm" />
										</html:select>
									</td>
									<td align="right">
										��ѵ�ɲ���
									</td>
									<td align="left">
										<html:select property="cxgb" name="rs" styleId="cxgb">
											<html:options collection="zwList" labelProperty="zwmc"
												property="zwdm" />
										</html:select>
									</td>
								</tr>
								</logic:equal>
							</logic:notEqual>
						</logic:equal>
						<tr>
							<td align="right">
								��ע��
							</td>
							<td colspan="3" align="left">
								<html:textarea name="rs" property="bz" style="width:90%"
									rows="3"></html:textarea>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<logic:equal name="doType" value="add">
						<button type="button" class="button2"
							onclick="if(saveGbxx()){Savedata('','jxglgt.do?method=jxcxgbOne&type=save')};"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					<logic:equal name="doType" value="edit">
						<button type="button" class="button2"
							onclick="editGbxx();"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
