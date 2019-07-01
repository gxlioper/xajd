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
	function chBzList(){
		var nj =$("nj").value;
		if(nj != ""){
			getJxglDAO.getNextMinJz(nj,function(data){
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
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="chBzList()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/jxglgt.do?method=ArmyStuInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��Ϣά�� - ��ѵ����ά��
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
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-nj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/jxgl/gt/jxglStuInfoOne.jsp" />
				<input type="hidden" name="lddmV" id="lddmV" />
				<fieldset>
					<legend>
						��ѵ������Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<logic:present name="isXs">
								<logic:equal name="isXs" value="no">
									<td align="right">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="xh" readonly="readonly"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<logic:present name = "type">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do?gfs=yes',750,550);"
											class="btn_01" id="buttonFindStu" >
											ѡ��
										</button>
										</logic:present>
									</td>
								</logic:equal>
								<logic:equal name="isXs" value="yes">
									<td align="right">
										<font color="red">*</font>�����ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="ksh" readonly="readonly"
											styleId="ksh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<logic:present name = "type">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu" >
											ѡ��
										</button>
										</logic:present>
									</td>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="isXs">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="xh" readonly="true"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:present name = "type">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" >
										ѡ��
									</button>
									</logic:present>
								</td>
							</logic:notPresent>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
							</td>
							<td align="right">
								
							</td>
							<td align="left">
								
							</td>
						</tr>
						
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�������ӣ�
							</td>
							<td align="left">
								<html:select name='rs' property="lddm" style="width:180px" styleId="lddm" >
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ldList" property="bzdm" labelProperty="bzmc" />
								</html:select>
							</td>
							<td align="right">
								�Ƿ�ѵ
							</td>
							<td align="left">
								<html:select name="rs" property="sfbx" style="width:90px" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ���֣�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxbx' style="width:99%" rows='5' onblur="chLeng(this,200)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ���ã�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxbz' style="width:99%" rows='5' onblur="chLeng(this,200)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ�ͷ���
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxcf' style="width:99%" rows='5' onblur="chLeng(this,200)"/>
							</td>
						</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="Savedata('xh-nd','jxglgt.do?method=ArmyStuInfoOne&type=save')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
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
