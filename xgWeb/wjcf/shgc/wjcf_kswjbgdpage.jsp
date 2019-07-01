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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<html:form action="/shgcwjcfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�Υ�ʹ��� - ����ά�� - ����Υ�ʹ��ֲ��鵵
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }"/>
			<input type="hidden"  id="realTable" name="realTable" value="${realTable }"/>
			<input type="hidden" id="failinfo" name="failinfo" vlaue="${failinfo }"/>
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�꼶��
								<html:select property="nj" style="width:90px;padding-left:80px" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" styleId="xh" style="width:90px;inputtext"></html:text>
								&nbsp;&nbsp;������
								<html:text property="xm" styleId="xm" style="width:90px;inputtext"></html:text>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('wjcf_shgc_kswjbgdqry.do')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:220px" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
					
								����ԭ��:
								<html:select property="cfyy" styleId="cfyy">
									<html:option value=""></html:option>
									<html:options collection="kscfyyList" property="cfyydm" labelProperty="cfyymc"/>
								</html:select>
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
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modiAndDel('wjcf_shgc_bgdmodi.do?act=view&pkValue=','modi','600','500');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
			<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
				<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="showTopWin('wjcf_shgc_bgdadd.do',600,500)">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('wjcf_shgc_bgdmodi.do?pkValue=','modi','600','500');">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="deldata('wjcf_shgc_bgddel.do')">
								ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
										��������
									</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
									��������
								</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="wjcfysbprint('kswjbgdprint.do?pkValue=')" style="width:80px">
									��ӡԤ��
								</button>
								&nbsp;&nbsp;&nbsp;
								<a href="xlsDown/shgc_kswjbgdb.xls"
										target="_blank">ģ������</a>
					</div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
			<logic:equal value="yes" name="deleted">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert('' + document.getElementById('failinfo').value);
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<script language="javascript">
		function wjcfysbprint(url){
			if (curr_row == null){
				window.open(url);
			}else {
				var pkVal = curr_row.cells[0].getElementsByTagName("input")[0].value;
				window.open(url+pkVal);
			}
			}
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</html:form>
		</body>
</html>
