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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	    <script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	    <script type='text/javascript' src='/xgxt/dwr/interface/hngyStuCj.js'></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��� - ѧ��������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="gwdm"
				value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="rs" property="xh"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							����ѧ���������
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" value="<bean:write name="rs" property="xh" />" />
						<input type="hidden" id="xxdm" value="<bean:write name="xxdm"/>" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						��λ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						�Ƿ���������
					</td>
					<td align="left">
						<bean:write name="rs" property="sfpks" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						������ò��
					</td>
					<td>
						<bean:write name='map' property="zzmmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���᣺
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="jg" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�к��س���
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="yhtc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��λ��¼��
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="gzjl" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�������ɣ�
					</td>
					<td align="left" colspan="3">
						<bean:write name="map" property="sqly" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�ɲμ��ڹ���ѧʱ�䣺
					</td>
					<td colspan="3">
						<logic:notEmpty name="kxList">
							<table border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td align="center">
										ʱ��
									</td>
									<td>
										����һ
									</td>
									<td>
										���ڶ�
									</td>
									<td>
										������
									</td>
									<td>
										������
									</td>
									<td>
										������
									</td>
									<td>
										������
									</td>
									<td>
										������
									</td>
								</tr>
								<logic:iterate id="kxsj" name="kxList">
									<tr>
										<td>
											${kxsj.sj}
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}1"
												value="${kxsj.mon}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}2"
												value="${kxsj.tue}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}3"
												value="${kxsj.wed}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}4"
												value="${kxsj.thu}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}5"
												value="${kxsj.fri}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}6"
												value="${kxsj.sat}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											<input type="hidden" name="index${kxsj.sjIndex}7"
												value="${kxsj.sun}" />
										</td>

									</tr>
								</logic:iterate>
							</table>
								<script>
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main3" style="color:blue;cursor:hand"
										onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getPjpyOfQgzx();">
										<div align="center" class="style1 style3">
											<strong>�����</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="child3" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												��ѧ���¼
											</div>
										</td>
									</tr>
									<tr>
										<td>
											���
										</td>
										<td>
											ѧ��
										</td>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											�༶
										</td>
										<td>
											��ѧ������
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />���
										</td>
										<td>
											ѧУ���
										</td>
										<td>
											�Ƿ���
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="jxj">
								</tbody>
							</table>
							<br></br>
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="8">
											<div align="center" class="style2">
												�����ƺż�¼
											</div>
										</td>
									</tr>
									<tr>
										<td>
											���
										</td>
										<td>
											ѧ��
										</td>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											�༶
										</td>
										<td>
											�����ƺ�����
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />���
										</td>
										<td>
											ѧУ���
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="rych">
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main5" style="color:blue;cursor:hand"
										onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
										<div align="center" class="style1 style3">
											<strong>�������</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" colspan="4">
						<div id="child5" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<logic:notEmpty name="zzrs">
									<input type="hidden" id="maxLength" name="maxLength"
										value="${zzrssize}" />
									<logic:iterate id="rsInfo" name="zzrs" indexId="index">
										<tr>
											<td id="td${index}" align="center" class="style2"
												bgcolor="#FFddcc" colspan="1"
												onclick="document.all.xszz${index}.style.display=(document.all.xszz${index}.style.display =='none')?'':'none';getXszzInfo(${index});">
												${rsInfo.tabCN}
												<input type="hidden" id="tab${index}" name="tab${index}"
													value="${rsInfo.tabEN}" />
											</td>
										</tr>

										<tbody style="display:none" width="100%" class="tbstyle"
											id="xszz${index}">
										</tbody>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="tbstyle">
							<tr>
								<td>
									<div id="main6" style="color:blue;cursor:hand"
										onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';getHngydxBxkcj();">
										<div align="center" class="style1 style3">
											<strong>���޿β�������Ϣ</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="child6" style="display:none">
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												�ɼ���Ϣ
											</div>
										</td>
									</tr>
									<tr>
										<td>
											���
										</td>
										<td>
											ѧ��
										</td>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											�༶
										</td>
										<td>
											�γ�����
										</td>
										<td>
											�����ɼ�
										</td>
										<td>
											���³ɼ�
										</td>
										<td>
											�ɼ�
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="bxkcj">
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ˣ�
					</td>
					<td align="left" colspan="3">						
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postStuChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<script language="javascript">
				alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:notEqual value="true" name="flag">
				<script language="javascript">
					alert("����ʧ�ܣ�");
				</script>
			</logic:notEqual>
		</logic:notEmpty>
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
	         	alert("��ѧ����������ͨ����ˣ�");
	         	Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
	         	alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<logic:equal value="full" name="result">
			<script>
				alert("����������");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("����������������");
			</script>
		</logic:equal>
	</body>
</html>
