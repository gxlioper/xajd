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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
		.noPrin{
			display:none;
		}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<body>		
		<table width="100%" align="center" class="tbstyle" id="rsTab">
			<tr>
				<td>
					<table width="100%" class="tbstyle">
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
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									ѧ�ţ�
								</td>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right" width="15%">
									�꼶��
								</td>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="left" width="15%" rowspan="6">
									<img border="0" style="height:133px;width:100px;"
										src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
								</td>
							</tr>
							<tr>
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>

							<tr>
								<td align="right">
									�������ڣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="csrq" />
								</td>
								<td align="right">
									�༶��
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���壺
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<td align="right">
									ѧ�ƣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							<tr>
								<td align="right">
									������ò��
								</td>
								<td align="left">
									<bean:write name="rs" property="zzmmmc" />
								</td>
								<td align="right">
									ѧ��״̬��
								</td>
								<td align="left">
									<bean:write name="rs" property="xjztm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���֤�ţ�
								</td>
								<td align="left">
									<bean:write name="rs" property="sfzh" />
								</td>
								<td align="right">
									�������䣺
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdzxx" />
								</td>
							</tr>
							<tr>
								<td align="right">
									��Դ������
								</td>
								<td align="left">
									<bean:write name="rs" property="lydq" />
								</td>
								<td align="right">
									��ϵ�绰��
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���᣺
								</td>
								<td align="left">
									<bean:write name="rs" property="jg" />
								</td>
								<td align="right">
									�ֻ����룺
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									����ţ�
								</td>
								<td align="left">
									<bean:write name="rs" property="ssbh" />
								</td>
								<td align="right">
									����绰��
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="qsdh" />
								</td>
							</tr>
							<logic:present name="showksh">
								<tr>
									<td align="right">
										�����ţ�
									</td>
									<td align="left">
										<bean:write name="rs" property="ksh" />
									</td>
									<td align="right">
										&nbsp;
									</td>
									<td align="left" colspan="2">
										&nbsp;
									</td>
								</tr>
							</logic:present>

						</table>
					</div>
				</td>
			</tr>
			
			<logic:present name="jtxx">
			<tr id="divJtxx" >
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main11" style="color:blue;cursor:hand" onclick="document.all.child11.style.display=(document.all.child11.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>��ͥ��Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child11">
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											��ͥ��Ϣ��¼
										</div>
									</td>
								</tr>								
							</thead>
							<tr>
							<td align="right" width="20%">
								��ͥ��ַ��
							</td>
							<td>
								${jtxxMap.jtdz}
							</td>
							<td align="right" width="20%">
								��ͥ�ʱࣺ
							</td>
							<td>
								${jtxxMap.jtyb}
							</td>
							</tr>
							
							<tr>
							<td align="right">
								��ͥ���������
							</td>
							<td colspan="3">
								${jtxxMap.jtjjqk}
							</td>
							</tr>
							
							<tr>
								<td colspan="4">
								<fieldset>
									<legend>
										��ͥ��Ա1
									</legend>
									<table class="tbstyle" width="100%">
										<tr>
											<td align="right" width="20%">
												������
											</td>
											<td>
												${jtxxMap.jtcy1_xm}
											</td>
											<td align="right" width="20%">
												�뱾�˹�ϵ��
											</td>
											<td>
												${jtxxMap.jtcy1_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												${jtxxMap.jtcy1_csrq}
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												${jtxxMap.jtcy1_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												${jtxxMap.jtcy1_mzmc}
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												${jtxxMap.jtcy1_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												${jtxxMap.jtcy1_zy}
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												${jtxxMap.jtcy1_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												${jtxxMap.jtcy1_lxdh1}
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												${jtxxMap.jtcy1_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												${jtxxMap.jtcy1_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
								<fieldset>
									<legend>
										��ͥ��Ա2
									</legend>
									<table class="tbstyle" width="100%">
										<tr>
											<td align="right" width="20%">
												������
											</td>
											<td>
												${jtxxMap.jtcy2_xm}
											</td>
											<td align="right" width="20%">
												�뱾�˹�ϵ��
											</td>
											<td>
												${jtxxMap.jtcy2_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												${jtxxMap.jtcy2_csrq}
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												${jtxxMap.jtcy2_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												${jtxxMap.jtcy2_mzmc}
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												${jtxxMap.jtcy2_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												${jtxxMap.jtcy2_zy}
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												${jtxxMap.jtcy2_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												${jtxxMap.jtcy2_lxdh1}
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												${jtxxMap.jtcy2_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												${jtxxMap.jtcy2_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" width="100%">
								<fieldset>
									<legend>
										��ͥ��Ա3
									</legend>
									<table class="tbstyle" style="width:100%">
										<tr>
											<td width="20%" align="right">
												������
											</td>
											<td>
												${jtxxMap.jtcy3_xm}
											</td>
											<td width="20%" align="right">
												�뱾�˹�ϵ��
											</td>
											<td>
												${jtxxMap.jtcy3_gx}
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												${jtxxMap.jtcy3_csrq}
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												${jtxxMap.jtcy3_sfzh}
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												${jtxxMap.jtcy3_mzmc}
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												${jtxxMap.jtcy3_zzmmmc}
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												${jtxxMap.jtcy3_zy}
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												${jtxxMap.jtcy3_zw}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												${jtxxMap.jtcy3_lxdh1}
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												${jtxxMap.jtcy3_lxdh2}
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												${jtxxMap.jtcy3_gzdz}
											</td>											
										</tr>
									</table>
								</fieldset>
								</td>
							</tr>
							
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="dtjs">
			<tr id="divDtjs">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>���Ž���</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<div id="child2">
						<logic:present name="dyxx">
						<table width="100%" align="center" class="tbstyle" id="tbDyxx">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											��Ա��Ϣ
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�����뵳ʱ��
									</td>
									<td>
										��������ʱ��
									</td>
									<td>
										��չ����ʱ��
									</td>
									<td>
										Ԥ����Աʱ��
									</td>
									<td>
										ת��ʱ��
									</td>
									<td>
										Ŀǰ״̬
									</td>
								</tr>
								</thead>
							<logic:iterate id="dyxx" name="dyxxList">
							<tr>
								<logic:iterate id="v" name="dyxx">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>							
						</table>
						</logic:present>
						
                        <logic:present name="ybdy">
						<table width="100%" align="center" class="tbstyle" id="tbYbdyjl">
							<thead>
								<tr>
									<td colspan="9">
										<div align="center" class="style2">
											Ԥ����Ա��¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�꼶
									</td>
									<td>
										�༶
									</td>
									<td>
										��ʼʱ��
									</td>
									<td>
										����ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="ybdy" name="ybdyList">
							<tr>
								<logic:iterate id="v" name="ybdy">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>							
						</table>						
						</logic:present>
						
						<logic:present name="dyjl">
						<table width="100%" align="center" class="tbstyle" id="tbDyjl">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											��Ա��¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�꼶
									</td>
									<td>
										�༶
									</td>
									<td>
										�뵳ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="dyjl" name="dyjlList">
							<tr>
								<logic:iterate id="v" name="dyjl">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						</logic:present>
						
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="pjpy" scope="request">
				<tr id="divPjpy">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
								onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>��������</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child3">
						<logic:present name="jxj">
						<table width="100%" align="center" class="tbstyle" id="tbJxjjl">
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
										��ѧ����
									</td>
									<td>
										<bean:message key="lable.xsgzyxpzxy" />���
									</td>
									<td>
										ѧУ���
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxj" name="jxjRs">
							<tr>
								<logic:iterate id="v" name="jxj">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>						
						</table>
						</logic:present>
						
						<logic:present name="rych">
						<table width="100%" align="center" class="tbstyle" id="tbRychjl">
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
							<logic:iterate id="rych" name="rychList">
							<tr>
								<logic:iterate id="v" name="rych">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>		
						</logic:present>
								
						<logic:present name="zhszcp">
						<table width="100%" align="center" class="tbstyle" id="tbZhszcpjl">
							<thead>
								<tr>
									<td colspan="13">
										<div align="center" class="style2">
											�ۺ����ʲ�����¼
										</div>
									</td>
								</tr>

								<tr>									
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
										Ʒ�²����÷�
									</td>
									<td>
										�γ�ѧϰ�ɼ������÷�
									</td>
									<td>
										����
									</td>
									<td>
										���Ĳ����÷�
									</td>
									<td>
										�������ʲ����÷�
									</td>
									<td>
										����
									</td>
									<td>
										�������ʲ����ȼ�
									</td>									
									<td>
										��չ���ʲ����÷�
									</td>
									<td>
										����
									</td>
								</tr>
							</thead>							
							<logic:iterate id="zhszcp" name="zhszcpList">
							<tr>
								<logic:iterate id="v" name="zhszcp">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						</logic:present>		
					</div>
				</td>
			</tr>
			</logic:present>
			<logic:present name="dwjl">
				<tr id="divDwjl">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>���⽻��</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child4">
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td colspan="11">
										<div align="center" class="style2">
											���⽻����������ѧ���¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										������Ŀ
									</td>
									<td>
										����ʱ��
									</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />���
										</td>
										<td>
											ѧУ���
										</td>
									<td>
										ѧУ����
									</td>
									<td>
										��ѧ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="dwjl" name="dwjlList">
							<tr>
								<logic:iterate id="v" name="dwjl">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="xszz">
				<tr id="divXszz">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>ѧ������</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr id="divXszzxx">
				<td bgcolor="#FFFFFF">
					<div id="child5">
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="7" align="center">������ѧ����</td>
							</tr>
						</thead>
						<logic:iterate id="gjzxdk" name="gjzxdkList">
						<tr>
							<logic:iterate id="v" name="gjzxdk">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="9" align="center">����������Ϣ</td>
							</tr>
						</thead>
						<logic:iterate id="zzsq" name="zzsqList">
						<tr>
							<logic:iterate id="v" name="zzsq">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
						<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
							<td colspan="7" align="center">��������Ϣ</td>
							</tr>
						</thead>
						<logic:iterate id="kns" name="knsList">
						<tr>
							<logic:iterate id="v" name="kns">
								<td>
								${v}
								</td>
							</logic:iterate>
						</tr>
						
						</logic:iterate>
						</table>
						
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="qgzx">
				<tr id="divQgzx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main6" style="color:blue;cursor:hand"
									onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>�ڹ���ѧ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child6">
						<table width="100%" align="center" class="tbstyle" id="tbQgzxjl">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											�ڹ���ѧ��¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�༶����
									</td>
									<td>
										��λ����
									</td>
									<td>
										����ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="xsgw" name="xsgwList">
							<tr>
								<logic:iterate id="v" name="xsgw">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						<table width="100%" align="center" class="tbstyle" id="tbCjffjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											��𷢷ż�¼
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
										�·�
									</td>
									<td>
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�༶����
									</td>
									<td>
										��λ����
									</td>
									<td>
										�����
									</td>
									<td>
										����ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="cjff" name="cjffList">
							<tr>
								<logic:iterate id="v" name="cjff">
								<td>${v}</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:notEqual value="stu" name="userType" scope="session">
				<tr id="divXljk">
					<td>
						<table width="100%" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main7" style="color:blue;cursor:hand"
										onclick="document.all.child7.style.display=(document.all.child7.style.display =='none')?'':'none';">
										<div align="center" class="style1 style3">
											<strong>������</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<div id="child7">
						<logic:present name="xlcs">
							<table width="100%" align="center" class="tbstyle" id="tbXlcsjgjl">
								<thead>
									<tr>
										<td colspan="9">
											<div align="center" class="style2">
												������Խ����¼
											</div>
										</td>
									</tr>
									<tr>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											������Ŀ
										</td>
										<td>
											���Խ��
										</td>
										<td>
											����ʱ��
										</td>
									</tr>
								</thead>	
								<logic:iterate id="xlcs" name="xlcsList">
								<tr>
									<logic:iterate id="v" name="xlcs">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>							
							</table>							
							</logic:present>
							
							<logic:present name="tsxs">
							<table width="100%" align="center" class="tbstyle" id="tbTsxxjl">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												����ѧ����¼
											</div>
										</td>
									</tr>
									<tr>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											��Ҫ�ر�������
										</td>
									</tr>
								</thead>	
								<logic:iterate id="tsxs" name="tsxsList">
								<tr>
									<logic:iterate id="v" name="tsxs">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>
							</table>
							</logic:present>
							
							<table width="100%" align="center">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												����Լ̸���
											</div>
										</td>
									</tr>
								</thead>
								<tr><td>
								<iframe style="width:100%;height:380px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxytxx.do?xsxxytxx=xsxxytxx&pkValue=${rs.xh}"></iframe>
								</td></tr>
							</table>
							<table width="100%" align="center">
								<thead>
									<tr>
										<td colspan="6">
											<div align="center" class="style2">
												�ص��ע���
											</div>
										</td>
									</tr>
								</thead>
								<tr><td>
								<iframe style="width:100%;height:280px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxzdgz.do?xsxxzdgz=xsxxzdgz&pkValue=${rs.xh}"></iframe>
								</td></tr>
							</table>
						</div>
					</td>
				</tr>
			</logic:notEqual>
			
			<logic:present name="xsjx">
				<tr id="divXsjx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main8" style="color:blue;cursor:hand"
									onclick="document.all.child8.style.display=(document.all.child8.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>ѧ����ѵ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child8">
						<logic:present name="jxtdhj">
						<table width="100%" align="center" class="tbstyle" id="tbJxtdhj">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											��ѵ�Ŷӻ񽱼�¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�༶
									</td>
									<td>
										����
									</td>
									<td>
										��ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxtdhj" name="jxtdhjList">
								<tr>
									<logic:iterate id="v" name="jxtdhj">
									<td>${v}</td>
									</logic:iterate>
								</tr>
								</logic:iterate>
						</table>
						</logic:present>
						
						<logic:present name="jxcj">
						<table width="100%" align="center" class="tbstyle" id="tbJxcjjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											��ѵ�ɼ���¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										���
									</td>
									<td>
										�༶
									</td>
									<td>
										����
									</td>
									<td>
										ѵ���ɼ�
									</td>
									<td>
										���Գɼ�
									</td>
									<td>
										�ɼ�
									</td>
									<td>
										¼��ʱ��
									</td>
									<td>
										¼����
									</td>
								</tr>
							</thead>
							<logic:iterate id="jxcj" name="jxcjList">
								<tr>
									<logic:iterate id="v" name="jxcj">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:present>
					</div>
				</td>
			</tr>
			</logic:present>
			
			<logic:present name="wjcf"> 
				<tr id="divWjcf">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main9" style="color:blue;cursor:hand"
									onclick="document.all.child9.style.display=(document.all.child9.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>Υ�ʹ���</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child9">
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											Υ�ʹ��ּ�¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										�������
									</td>
									<td>
										����ʱ��
									</td>
									<td>
										�����ĺ�
									</td>
									<td>
										���߽��
									</td>
									<td>
										���ʱ��
									</td>
								</tr>
							</thead>
							<logic:iterate id="wjcf" name="wjcfList">
								<tr>
									<logic:iterate id="v" name="wjcf">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</div>
				</td>
			</tr>
			</logic:present>
			<logic:present name="qtsj">
				<tr id="divQtsj">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main10" style="color:blue;cursor:hand"
									onclick="document.all.child10.style.display=(document.all.child10.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
											<strong>��������</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child10">
					 <logic:present name="xsbx">
						<table width="100%" align="center" class="tbstyle" id="tbXsbxjl">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">											
											ѧ�����ռ�¼
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
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										���չ�˾
									</td>
									<td>
										Ͷ������
									</td>
									<td>
										Ͷ��ʱ��
									</td>
									<td>
										�˱�ʱ��
									</td>
									<td>
										�˱����
									</td>
								</tr>
							</thead>
							<logic:iterate id="xsbx" name="xsbxList">
								<tr>
									<logic:iterate id="v" name="xsbx">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							
						</table>
						</logic:present>
							<table width="100%" align="center" class="tbstyle" id="tbHsxfjl">
								<thead>
									<tr>
										<td colspan="5">
											<div align="center" class="style2">
												��ʳ���Ѽ�¼
											</div>
										</td>
									</tr>
									<tr>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											���
										</td>
										<td>
											�·�
										</td>
										<td>
											���ѽ��
										</td>
									</tr>
								</thead>
								<logic:iterate id="hsxf" name="hsxfList">
								<tr>
									<logic:iterate id="v" name="hsxf">
									<td>${v}</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</table>
					</div>
				</td>
			</tr>
			</logic:present>
			
			</table>
		<div class='noPrin' align="center">
		<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
		<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
		<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>
