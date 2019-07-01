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
		<style type="text/css">
<!--
.style1 {font-size: 16px}
.style2 {font-size: 14px}
.style3 {
	color: #000000;
	font-size: 15px;
}
.style4 {
	font-size: 15px;
	font-weight: bold;
}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<body onload="initItem();" id="mybody">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�ѧ����ϸ��Ϣ��ѯ
			</div>
		</div>
		<input type="hidden" id="xh" name="xh" value="<bean:write name="xh"/>" />
		<input type="hidden" id="notnulltext" name="notnulltext" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType}" />
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
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
			<tr id="divJtxx" >
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main11" style="color:blue;cursor:hand"
									onclick="document.all.child11.style.display=(document.all.child11.style.display =='none')?'':'none';getJtxxFoKydxInfo();">
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
					<div id="child11" style="display:none">
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
								<div id="jtdz"></div>
							</td>
							<td align="right" width="20%">
								��ͥ�ʱࣺ
							</td>
							<td>
								<div id="jtyb"></div>
							</td>
							</tr>
							
							<tr>
							<td align="right">
								��ͥ���������
							</td>
							<td colspan="3">
								<div id="jtjjqk"></div>
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
												<div id="jtcy1_xm"></div>
											</td>
											<td align="right" width="20%">
												�뱾�˹�ϵ��
											</td>
											<td>
												<div id="jtcy1_gx"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												<div id="jtcy1_csrq"></div>
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												<div id="jtcy1_sfzh"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												<div id="jtcy1_mzmc"></div>
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												<div id="jtcy1_zzmmmc"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												<div id="jtcy1_zy"></div>
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												<div id="jtcy1_zw"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												<div id="jtcy1_lxdh1"></div>
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												<div id="jtcy1_lxdh2"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												<div id="jtcy1_gzdz"></div>
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
												<div id="jtcy2_xm"></div>
											</td>
											<td align="right" width="20%">
												�뱾�˹�ϵ��
											</td>
											<td>
												<div id="jtcy2_gx"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												<div id="jtcy2_csrq"></div>
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												<div id="jtcy2_sfzh"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												<div id="jtcy2_mzmc"></div>
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												<div id="jtcy2_zzmmmc"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												<div id="jtcy2_zy"></div>
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												<div id="jtcy2_zw"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												<div id="jtcy2_lxdh1"></div>
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												<div id="jtcy2_lxdh2"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												<div id="jtcy2_gzdz"></div>
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
												<div id="jtcy3_xm"></div>
											</td>
											<td width="20%" align="right">
												�뱾�˹�ϵ��
											</td>
											<td>
												<div id="jtcy3_gx"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												�������ڣ�
											</td>
											<td>
												<div id="jtcy3_csrq"></div>
											</td>
											<td align="right">
												���֤�ţ�
											</td>
											<td>
												<div id="jtcy3_sfzh"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												���壺
											</td>
											<td>
												<div id="jtcy3_mzmc"></div>
											</td>
											<td align="right">
												������ò��
											</td>
											<td>
												<div id="jtcy3_zzmmmc"></div>
											</td>
										</tr>
										<tr>
											<td align="right">
												ְҵ��
											</td>
											<td>
												<div id="jtcy3_zy"></div>
											</td>
											<td align="right">
												ְ��
											</td>
											<td>
												<div id="jtcy3_zw"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������λ�绰��
											</td>
											<td>
												<div id="jtcy3_lxdh1"></div>
											</td>
											<td align="right">
												���˵绰��
											</td>
											<td>
												<div id="jtcy3_lxdh2"></div>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												������ַ��
											</td>
											<td colspan="3">
												<div id="jtcy3_gzdz"></div>
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
			
			<tr id="divDtjs">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';getSxjyInfo();">
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
					<div id="child2" style="display:none">					
						
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
							<tbody width="100%" class="tbstyle" id="dyxx">
							</tbody>							
						</table>						
					</div>
				</td>
			</tr>
			
			<tr id="divPjpy">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getPjpyInfo();">
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
					<div id="child3" style="display:none">
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
							<tbody width="100%" class="tbstyle" id="jxj">
							</tbody>
						</table>
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
							<tbody width="100%" class="tbstyle" id="rych">
							</tbody>
						</table>						
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
							<tbody width="100%" class="tbstyle" id="zhszcp">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			
			<tr id="divDwjl">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getDwjlInfo();">
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
					<div id="child4" style="display:none">
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
							<tbody width="100%" class="tbstyle" id="dwjljjxj">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			
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
					<div id="child5" style="display:none">
						<table width="100%" align="center" class="tbstyle">
							<logic:notEmpty name="zzrs">
								<input type="hidden" id="maxLength" name="maxLength"
									value="${zzrssize}" />
								<logic:iterate id="rsZz" name="zzrs" indexId="index">
									<tr>
										<td id="td${index}" align="center" class="style2"
											bgcolor="#FFddcc" colspan="1"
											onclick="document.all.xszz${index}.style.display=(document.all.xszz${index}.style.display =='none')?'':'none';getXszzInfo(${index});">
											${rsZz.tabCN}
											<input type="hidden" id="tab${index}" name="tab${index}"
												value="${rsZz.tabEN}" />
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
			<tr id="divQgzx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main6" style="color:blue;cursor:hand"
									onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';getQgzxInfo();">
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
					<div id="child6" style="display:none">
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
							<tbody width="100%" class="tbstyle" id="xsqgzx">
							</tbody>
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
							<tbody width="100%" class="tbstyle" id="cjff">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<logic:notEqual value="stu" name="userType" scope="session">
				<tr id="divXljk">
					<td>
						<table width="100%" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main7" style="color:blue;cursor:hand"
										onclick="document.all.child7.style.display=(document.all.child7.style.display =='none')?'':'none';getXljkInfo();">
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
						<div id="child7" style="display:none">							
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
								<tbody width="100%" class="tbstyle" id="xlcs">
								</tbody>
							</table>
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
								<tbody width="100%" class="tbstyle" id="tsxs">
								</tbody>
							</table>
							
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
								<iframe id="xlytqkxs" style="width:100%;height:380px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxytxx.do?xsxxytxx=xsxxytxx&pkValue=${rs.xh}"></iframe>
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
								<iframe id="zdgzqk" style="width:100%;height:0px" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" src="/xgxt/xsxxzdgz.do?xsxxzdgz=xsxxzdgz&pkValue=${rs.xh}"></iframe>
								</td></tr>
							</table>
						</div>
					</td>
				</tr>
			</logic:notEqual>
			<tr id="divXsjx">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main8" style="color:blue;cursor:hand"
									onclick="document.all.child8.style.display=(document.all.child8.style.display =='none')?'':'none';getXsjxInfo();">
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
					<div id="child8" style="display:none">
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
							<tbody width="100%" class="tbstyle" id="jxhj">
							</tbody>
						</table>
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
							<tbody width="100%" class="tbstyle" id="jxcj">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr id="divWjcf">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main9" style="color:blue;cursor:hand"
									onclick="document.all.child9.style.display=(document.all.child9.style.display =='none')?'':'none';getWjcfInfo();">
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
					<div id="child9" style="display:none">
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td colspan="9">
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
										����ԭ��
									</td>
									<td>
										����ʱ��
									</td>
									<td>
										�����ĺ�
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="tbstyle" id="wjcf">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
					
			<tr id="divQtsj">
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main10" style="color:blue;cursor:hand"
									onclick="document.all.child10.style.display=(document.all.child10.style.display =='none')?'':'none';getQtsjInfo();">
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
					<div id="child10" style="display:none">
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
							<tbody width="100%" class="tbstyle" id="bxxx">
							</tbody>
						</table>
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
								<tbody width="100%" class="tbstyle" id="hsxf">
								</tbody>
							</table>
					</div>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">			
				<button type="button" class="button2" onclick="printOne()" style="width:80px"
					id="buttonSave">
					�� ӡ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;					
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
	</body>
</html>
