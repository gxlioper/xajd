<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function st_modi(){
		var stlxdm=document.all["stlxdm"].value;
		if(stlxdm==""){
			alert ("��ѡ���������ʹ���");
			document.all["stlxdm"].focus();
			return false;
		}
		var stjffs=document.all["stjffs"].value;
		if(stjffs==""){
			alert ("��ѡ������Ƿַ�ʽ");
			document.all["stjffs"].focus();
			return false;
		}else if(stjffs=="1"){
			var stfz=document.all["stfz"].value;
			if(stfz==""){
				alert ("��ѡ�������ֵ");
				document.all["stfz"].focus();
				return false;
			}	
		}
		var stnr=document.all["stnr"].value;
		if(stnr==""){
			alert ("����д��������");
			document.all["stnr"].focus();
			return false;
		}
		
		var stda=document.all["stda"].value;
		if(stda==""){
			alert ("����д�����");
			document.all["stda"].focus();
			return false;
		}
		var stlsh=document.all["stlsh"].value;
		document.getElementById("modi_flag").value="yes";
		underDealWith();
		refreshForm('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_modi');
	}
	</script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ������� - ���ά��</a>
				</p>
			</div>
	
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="stlsh" name="stlsh"
					value="<bean:write name="rs" property="stlsh" />" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>���ά��</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button class="" onclick="st_modi()" 
											id="buttonSave">
											����
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="" onclick="Close();return false;" 
											id="buttonClose">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>		
					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>��������
							</th>
							<td align="left">
								<html:select name="rs" property="stlxdm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select>
							</td>
							<th align="right">
								�����Ѷȼ���
							</th>
							<td align="left">
								<html:select name="rs" property="stndjbdm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="STNDJBDM"
										labelProperty="STNDJBMC" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>����Ʒַ�ʽ
							</th>
							<td align="left">
								<html:select name="rs" property="stjffs" style="width:120px"
									styleId="stjffs"
									onchange="if(document.forms[0].stjffs.value==1) document.forms[0].stfz.disabled=false;else{ document.forms[0].stfz.value='';document.forms[0].stfz.disabled=true;}">
									<html:option value=""></html:option>
									<html:option value="1">����</html:option>
									<html:option value="0">��ѡ��</html:option>
								</html:select>
							</td>
							<th align="right">
								�����ֵ
							</th>
							<td align="left">
								<html:text name='rs' property="stfz" style="width: 120px"
									styleId="stfz" onblur="numFormatChk(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>�����
							</th>
							<td align="left">
								<html:text name='rs' property="stda" style="width: 120px"
									styleId="stda" />
							</td>
							<th align="right">
								�Ƿ���ʾ
							</th>
							<td align="left">
								<html:select name="rs" property="stxsbj" style="width:120px"
									styleId="stxsbj">
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								<font color="red">*</font>��������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stnr' style="width:95%"
									rows='6'/>
							</td>
						</tr>
						<tr>
							<th align="right">
								����𰸽���
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stdajs' style="width:95%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
	
			</logic:notEmpty>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update success">
					<script>
						alert("�޸ĳɹ�!")
						dialogArgumentsQueryChick();
						Close();
					</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>
						alert("�޸�ʧ��!");
						document.getElementById("tmpdiv").innerTTML = "";
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
