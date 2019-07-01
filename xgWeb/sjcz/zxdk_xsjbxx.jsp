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
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
		function dataDoSave(mustFill) {
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
			var url = "/xgxt/modiData.do?realTable=";
			url += window.dialogArguments.document.forms[0].realTable.value;
			url += "&doType=save";
			url += "&tableName=";
			url += window.dialogArguments.document.forms[0].tableName.value;
			url += "&pk=";
			url += window.dialogArguments.document.forms[0].pk.value;
			url += "&pkValue=";
			url += document.forms[0].pkValue.value;
			url += "&from=";
			url += window.dialogArguments.document.forms[0].act.value;
			document.forms[0].action = url;
			document.forms[0].submit();
			alert("����ɹ���");
			dialogArgumentsQueryChick();
			Close();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
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
				<input type="hidden" id="disableEle" name="disableEle" value="xm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm" />
				<input type="hidden" id="url" name="url" value="/sjcz/zxdk_htxx.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="center">
								ѧ����Ϣ
							</td>
						</tr>
					</thead>
						<tr>
							<td>
								<table width="100%" border="1" class="tbstyle">
									<tr>
										<td bgcolor="#CCCCCC">
											<div id="main1" style="color:blue;cursor:hand"
												onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
												<div align="center" class="style1 style3">
													<strong>ѧ��������Ϣ</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="child1">
									<table class="tbstyle" width="100%">
										<tr>
											<td align="right" width="16%" scope="col">
												<font color="red">*</font>ѧ�ţ�
											</td>
											<td align="left" width="34%" scope="col">
												<html:text name='rs' property="xh" readonly="readonly"   style="width:100%"
													styleId="xh" />
											</td>
											<td width="16%" scope="col">
												<div align="right">
													�����ţ�
												</div>
											</td>
											<td width="34%" scope="col">
												<div align="left">
													<html:text name='rs' property="ksh"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xm" styleId="xm"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													�Ա�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xb"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���֤�ţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="sfzh"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													���壺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="mzmc"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ѧУ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xxmc"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													<bean:message key="lable.xsgzyxpzxy" />��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xymc"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													רҵ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xmc"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													���ţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="kh"  style="width:100%" readonly="true"/>
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�������ڣ�
												</div>
											</td>
											<td>
												<div align="left">
												<html:text name='rs' property="csrq" styleId="csrq"
													style="cursor:hand;width:100%"
													onclick="return showCalendar('csrq','y-mm-dd');" />
												</div>
											</td>
											<td>
												<div align="right">
													ѧ�ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xz"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ѧ���£�
												</div>
											</td>
											<td>
												<div align="left">
												<html:text name='rs' property="rxny" styleId="rxny"
													 style="cursor:hand;width:100%"
													onclick="return showCalendar('rxny','y-mm');" />
												</div>
											</td>
											<td>
												<div align="right">
													��ҵ���£�
												</div>
											</td>
											<td>
												<div align="left">
												<html:text name='rs' property="byny" styleId="byny"
													style="cursor:hand;width:100%"
													onclick="return showCalendar('byny','y-mm');" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ѧ�����ͣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xslx"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													�������룺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="yzbm"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����绰��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="sedh"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													�����ܶ
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="dkze" readonly="true"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ѧ�Ѵ�������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="xfdks"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													����Ѵ�������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="shfdks"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ס�޷Ѵ�������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="zsfdks"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													�ѻ�����
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="yhdkje" readonly="true"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�״α�ҵȥ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="scbyqx"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													��Ч��ϵ��ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="yxlxfs"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǰ������λ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="dqgzdw"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													��ǰ������λ��ַ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="dqgzdwdz"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǰ������λ�ʱࣺ
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="dqgzdwyb"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													��ǰ������λ�绰��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="dqgzdwdh"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��Уԭ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="lxyy"  style="width:100%" />
												</div>
											</td>
											<td scope="row">
												<div align="right">
												</div>
											</td>
											<td>
												<div align="left">
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ͥסַ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="jtzz"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													��ͥ���룺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="jtsr"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="fqxm"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													������ϵ�绰��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="fqlxdh"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���׹�����λ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="fqgzdw"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													�������֤�ţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="fqsfzh"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ĸ��������
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="mqxm"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													ĸ����ϵ�绰��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="mqlxdh"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													ĸ�׹�����λ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="mqgzdw"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													ĸ�����֤�ţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="mqsfzh"  style="width:100%" />
												</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="1" class="tbstyle">
									<tr>
										<td bgcolor="#CCCCCC">
											<div id="main2" style="color:blue;cursor:hand"
												onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
												<div align="center" class="style1 style3">
													<strong>��ͬ1</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="child2" style="display:none">
									<table width="100%" border="1" align="center" class="tbstyle">
										<tr>
											<td scope="row" width="16%">
												<div align="right">
													��ͬ�ţ�
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="hth1" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td width="16%">
												<div align="right">
													������ڻ�����
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="ht1_jbjrjg"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��֧�������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_fzjgmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��׼���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_pzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�ܽ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_zje" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													����Ա��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_jby" readonly="true"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��׼�г���
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_pzhz"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʼ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_hkksrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�����ֹ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_hkjzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													չ��ʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_zqsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													չ�����ɣ�
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht1_zqly" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_dkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_hkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_hkjzmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��������ʺţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_hkjzzh"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǷӦ����Ϣ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_tqyhbxje"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��Ƿ��ֹʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht1_tqjzsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��Ƿ��ע��
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht1_tqbz" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>

									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="1" class="tbstyle">
									<tr>
										<td bgcolor="#CCCCCC">
											<div id="main3" style="color:blue;cursor:hand"
												onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
												<div align="center" class="style1 style3">
													<strong>��ͬ2</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="child3" style="display:none">
									<table width="100%" border="1" align="center" class="tbstyle">


										<tr>
											<td scope="row" width="16%">
												<div align="right">
													��ͬ�ţ�
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="hth2" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td width="16%">
												<div align="right">
													������ڻ�����
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="ht2_jbjrjg"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��֧�������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_fzjgmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��׼���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_pzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�ܽ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_zje" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													����Ա��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_jby" readonly="true"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��׼�г���
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_pzhz"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʼ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_hkksrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�����ֹ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_hkjzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													չ��ʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_zqsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													չ�����ɣ�
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht2_zqly" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_dkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_hkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_hkjzmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��������ʺţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_hkjzzh"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǷӦ����Ϣ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_tqyhbxje"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��Ƿ��ֹʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht2_tqjzsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��Ƿ��ע��
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht2_tqbz" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>

									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="1" class="tbstyle">
									<tr>
										<td bgcolor="#CCCCCC">
											<div id="main4" style="color:blue;cursor:hand"
												onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
												<div align="center" class="style1 style3">
													<strong>��ͬ3</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="child4" style="display:none">
									<table width="100%" border="1" align="center" class="tbstyle">

										<tr>
											<td scope="row" width="16%">
												<div align="right">
													��ͬ�ţ�
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="hth3"  style="width:100%" readonly="true" />
												</div>
											</td>
											<td width="16%">
												<div align="right">
													������ڻ�����
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="ht3_jbjrjg"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��֧�������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_fzjgmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��׼���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_pzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�ܽ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_zje" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													����Ա��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_jby"  style="width:100%" readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��׼�г���
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_pzhz"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʼ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_hkksrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�����ֹ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_hkjzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													չ��ʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_zqsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													չ�����ɣ�
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht3_zqly" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_dkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_hkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_hkjzmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��������ʺţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_hkjzzh"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǷӦ����Ϣ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_tqyhbxje"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��Ƿ��ֹʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht3_tqjzsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��Ƿ��ע��
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht3_tqbz" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>

									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="1" class="tbstyle">
									<tr>
										<td bgcolor="#CCCCCC">
											<div id="main5" style="color:blue;cursor:hand"
												onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
												<div align="center" class="style1 style3">
													<strong>��ͬ4</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								<div id="child5" style="display:none">
									<table width="100%" border="1" align="center" class="tbstyle">

										<tr>
											<td scope="row" width="16%">
												<div align="right">
													��ͬ�ţ�
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="hth4" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td width="16%">
												<div align="right">
													������ڻ�����
												</div>
											</td>
											<td width="34%">
												<div align="left">
													<html:text name='rs' property="ht4_jbjrjg"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��֧�������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_fzjgmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��׼���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_pzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�ܽ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_zje" readonly="true"  style="width:100%" />
												</div>
											</td>
											<td>
												<div align="right">
													����Ա��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_jby" readonly="true"  style="width:100%" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��׼�г���
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_pzhz"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʼ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_hkksrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													�����ֹ���ڣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_hkjzrq"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													չ��ʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_zqsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													չ�����ɣ�
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht4_zqly" style="width:100%" 
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_dkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													���ʽ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_hkfs"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													����������ƣ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_hkjzmc"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��������ʺţ�
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_hkjzzh"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��ǷӦ����Ϣ��
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_tqyhbxje"  style="width:100%"
														readonly="true" />
												</div>
											</td>
											<td>
												<div align="right">
													��Ƿ��ֹʱ�䣺
												</div>
											</td>
											<td>
												<div align="left">
													<html:text name='rs' property="ht4_tqjzsj"  style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
										<tr>
											<td scope="row">
												<div align="right">
													��Ƿ��ע��
												</div>
											</td>
											<td colspan="3">
												<div align="left">
													<html:text name='rs' property="ht4_tqbz" style="width:100%"
														readonly="true" />
												</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr>
										<td scope="row" width="16%">
											<div align="right">
												��ע��
											</div>
										</td>
										<td colspan="3">
											<div align="left">
												<html:text name='rs' style="width:100%" property="bz" />
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width:80px" id="buttonModi">
							�� ��
						</button>
						<button type="button" class="button2"
							onclick="dataDoSave('xh');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="window.dialogArguments.document.getElementById('search_go').click();Close();"
							style="width:80px" id="buttonClose">
							�� ��
						</button>
					</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
