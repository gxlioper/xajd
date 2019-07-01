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
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpyhbsfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��: �������� - ��Ϣά�� - �ۺ����ʲ���
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/hbsf_zhszcpadd.do" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								�ۺ����ʲ�����Ϣά��
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
								ѡ��
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>��ȣ�
						</td>
						<td align="left">
							<html:select property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<html:select property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
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
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<html:select property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text property="nj" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							˼����������ܷ֣�
						</td>
						<td align="left">
							<html:text property="dcj" styleId="dcj" maxlength="4" onblur="chkData6(this);chksszf()"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text property="xymc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							��ѧ�Ļ������ܷ֣�
						</td>
						<td align="left">
							<html:text property="zcj" styleId="zcj" maxlength="4" onblur="chkData6(this);countkxzf()"></html:text>
						</td>
					</tr><tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text property="zymc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							���Ľ��������ܷ֣�
						</td>
						<td align="left">
							<html:text property="tcj" styleId="tcj" maxlength="4" onblur="chkData6(this);countsxzf()"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text property="bjmc" name="rs" readonly="true"></html:text>
						</td>
						<td align="right">
							�ۺϲ����ܷ֣�
						</td>
						<td align="left">
							<html:text property="zhszcpzf" styleId="zhszcpzf" maxlength="4" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="">
						<td align="right">
							�ۺϲ���������
						</td>
						<td align="left">
							<html:text property="zhcppm" styleId="zhcppm" onkeypress="chkonlynum(this,event);" maxlength="4"></html:text>
						</td>
						<td align="right">
							�����񻺿�������
						</td>
						<td align="left">
							<html:text property="bjghkms" styleId="bjghkms" onkeypress="chkonlynum(this,event);" maxlength="2"></html:text>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>˼����������ܷ���ϸ</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											˼����±��֣�
										</td>
										<td align="left">
											<html:text property="sxddbx" styleId="sxddbx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											��������ѧϰ��
										</td>
										<td align="left">
											<html:text property="zzllxx" style="zzllxx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���Ὠ�������
										</td>
										<td align="left">
											<html:text property="ssjsqk" style="ssjsqk" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											���ʵ�����
										</td>
										<td align="left">
											<html:text property="shsjhd" style="shsjhd" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											�ɲ���ְ���֣�
										</td>
										<td align="left">
											<html:text property="gbrzbx" style="gbrzbx" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td><td align="right">
											����ͻ���¼���
										</td>
										<td align="left">
											<html:text property="qttcsj" style="qttcsj" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											�����֣�
										</td>
										<td align="left">
											<html:text property="zpf" styleId="zpf" maxlength="4" onblur="chkData6(this);countsxddzf()"></html:text>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>��ѧ�Ļ������ܷ���ϸ</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child3" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="height:20px">
										<td align="right">
											רҵ��չ��
										</td>
										<td align="left">
											<html:text property="zytz" styleId="zytz" maxlength="4"  onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											���Լ��ܣ�
										</td>
										<td align="left">
											<html:text property="yyjn" styleId="yyjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											��������ܣ�
										</td>
										<td align="left">
											<html:text property="jsjjn" styleId="jsjjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											ְҵ���ܣ�
										</td>
										<td align="left">
											<html:text property="zyjn" styleId="zyjn" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											ѧ�ƾ�����
										</td>
										<td align="left">
											<html:text property="kxjs" styleId="kxjs" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											����������
										</td>
										<td align="left">
											<html:text property="cxnl" styleId="cxnl" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
									</tr>
									<tr style="height:20px">
										<td align="right">
											�γ̼�Ȩƽ���֣�
										</td>
										<td align="left">
											<html:text property="kcjqpfj" styleId="kcjqpfj" maxlength="4" onblur="chkData6(this);countkxwhszzf()"></html:text>
										</td>
										<td align="right">
											�γ̼�Ȩƽ����������
										</td>
										<td align="left">
											<html:text property="kcjqpfjpm" styleId="kcjqpfjpm" onkeypress="chkonlynum(this,event);" maxlength="4"></html:text>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���Ľ��������ܷ���ϸ</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											������꣺
										</td>
										<td align="left">
											<html:text property="tydb" styleId="tydb" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td align="right">
											�������
										</td>
										<td align="left">
											<html:text property="tyhd" styleId="tyhd" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���������
										</td>
										<td align="left">
											<html:text property="tsqk" styleId="tsqk" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���������
										</td>
										<td align="left">
											<html:text property="xljkhd" styleId="xljkhd" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
										<td align="right">
											��������״����
										</td>
										<td align="left">
											<html:text property="xlszzk" styleId="xlszzk" maxlength="4" onblur="chkData6(this);countsxjkszzf()"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���������ܷ֣�
										</td>
										<td align="left">
											<html:text property="stszzf" styleId="stszzf" readonly="true" ></html:text>
										</td>
										<td align="right">
											���������ܷ֣�
										</td>
										<td align="left">
											<html:text property="xlszzf" styleId="xlszzf" readonly="true"></html:text>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">
							���ּ��أ�
						</td>
						<td align="left" colspan="3">
							<html:textarea property="cfjz" styleId="cfjz" rows="2" style="width:98%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							���������
						</td>
						<td align="left" colspan="3">
							<html:textarea property="cpjg" styleId="cpjg" rows="2" style="width:98%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3" align="left">
							<html:textarea property="bz" styleId="bz" rows="3" style="width:98%"></html:textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<button class="button2"
							onclick="if(checkXnNd('xn','nd'))zhszcpsave('xn-xq-xh','hbsf_zhszcpsave.do');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("����ʧ�ܣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
</html>
