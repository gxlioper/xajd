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
	<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
	</head>							
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox" id="toolTipLayer">
						<ul>
							<logic:equal value="wjcfb" name="realTable">
							<!-- ������������ʾ��У�쿴���� -->
							<logic:notEqual value="11062" name="xxdm">
								<!-- �㽭����У�쿴���� -->
								<logic:equal value="10338" name="xxdm">
									<li><a href="#" class="btn_ck" id="btn_lxck"
										onclick="showTopWin('wjcf_zjlg_lxckcx.do',750,700)">��У�쿴����</a></li>
								</logic:equal>
								<!-- ����ѧУ��У�쿴���� -->
								<!-- �㽭��,���ݴ�ѧ����ʾ��У�쿴���� -->
								<logic:notEqual value="10338" name="xxdm">
									<logic:notEqual value="11078" name="xxdm">
											<logic:notEqual value="12645" name="xxdm">
											<li><a href="#" class="btn_ck" id="btn_lxck" onclick="showTopWin('wjcflxcktj.do',750,700)">��У�쿴����</a></li>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:equal>
							
							<!-- �γ�ְҵbegin -->
							<logic:equal value="zhszcp" name="realTable">
								<logic:notEqual value="13108" name="xxdm">
									<li><a href="#" class="btn_sz" onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">���ñ���</a></li>
								</logic:notEqual>
								&nbsp;
								<logic:equal value="13108" name="xxdm">
									<li><a href="#" class="btn_zj" onclick="AutoAccountCj('/xgxt/AutoAccount.do?type=drjs')">��������¼���</a></li>
								</logic:equal>
								&nbsp;
							</logic:equal>
							<!-- �γ�ְҵend -->
							
							<logic:equal value="wjcfb" name="realTable">
								<li><a href="#" class="btn_sx" onclick="wjsjzy('wjcf_shgc_lssjzy.do')">��ҵ������ת��</a></li>
								<logic:equal value="11626" name="xxdm">
								<li><a href="#" class="btn_xy" onclick="wjcfjj()">Υ�ʹ��ֽ���</a></li>
								</logic:equal>
								<input type="hidden" name="wjcfflag" id="wjcfflag" value="${wjcfflag }"/>
								<logic:equal name="xxdm" value="10388">
									<li><a href="#" class="btn_tj" onclick="wjcfhz()">����ͳ��</a></li>
								</logic:equal>
							</logic:equal>
							
							<%--�人����ѧ����<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="1049701" name="xxdm">
								<li><a href="#" class="btn_xg" onclick="kftj('wjcfkftj.do')">�۷�ͳ��</a></li>	
							</logic:equal>
							
							<logic:present name="showzdjs">
								<li><a href="#" class="btn_dr" onclick="jsxxzcCount()">�Զ�����</a></li>
							</logic:present>
							
							<logic:notPresent name="showzdjs">
							<logic:equal name="act" value="party">
								
								<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dr" onclick="if(curr_row == null){alert('��ѡ��Ҫ��ת��ѧ��!');return false;}else{showTopWin('/xgxt/dtjs_zjcm.do?method=zzgx&rdsj='+curr_row.cells[7].innerText+'&pk='+curr_row.cells[3].innerText,600,480)}">��֯��ϵ��ת</a></li>
								</logic:equal>
								<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
								<li><a href="#" class="btn_dr" onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');">���µ�ѧ����</a></li>
							</logic:equal>
							
							<logic:equal name="act" value="prepare">
								<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dc" onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=zzYb&go=go',800,600);">��Աת��</a></li>
								</logic:equal>
								<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
								<li><a href="#" class="btn_dc" onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');">���µ�ѧ����</a></li>
							</logic:equal>
							
							<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal name="act" value="active">
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dc" onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);">��չ��������</a></li>
								</logic:equal>
							</logic:equal>
							<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:notEqual name="realTable" value="bks_xsszjbxx">
								<logic:equal value="12872" name="xxdm">
									<logic:equal value="view_xsrychb" name="tableName">
										<li><a href="#" class="btn_zj" onclick="refreshForm('credit_apply.do')">����</a></li>
									</logic:equal>
									<logic:notEqual value="view_xsrychb" name="tableName">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_zj" onclick="viewMore('add')">����</a></li>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="12872" name="xxdm">
									<li><a href="#" class="btn_zj" onclick="viewMore('add')">����</a></li>
								</logic:notEqual>
							</logic:notEqual>
							
							<logic:present name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
											<li><a href="#" class="btn_xg" onclick="hzyjxjmodi()">�޸�1</a></li>
											</logic:notEqual>
										</logic:equal>
										
										<logic:notEqual value="12872" name="xxdm">
											<li><a href="#" class="btn_xg" onclick="viewMore2('modi')">�޸�2</a></li>
										</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="xsjxjb">
							<logic:equal value="12872" name="xxdm">
								<logic:equal value="xsrychb" name="realTable">
									<li><a href="#" class="btn_xg" onclick="hzzyrychmodi()">�޸�3</a></li>
								</logic:equal>
								
								<logic:notEqual value="xsrychb" name="realTable">
									<li><a href="#" class="btn_xg" onclick="viewMore('modi')">�޸�4</a></li>			
								</logic:notEqual>
							</logic:equal>
							
							<logic:notEqual value="12872" name="xxdm">
								<logic:notEqual name="xxdm" value="10827" scope="session">
								<li><a href="#" class="btn_xg" onclick="
										<logic:equal value="10827" name="xxdm"><logic:equal value="view_xsrychb" name="tableName">
										updaterychxx('modi')
										</logic:equal>
										<logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual></logic:equal>
										<logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>">�޸�</a></li>
								</logic:notEqual>
							
							<logic:equal value="10827" name="xxdm">
									<li><a href="#" class="btn_xg" onclick="viewMore('modi')">�޸�6</a></li>
							</logic:equal>
							</logic:notEqual>
							</logic:notPresent>
							
							<logic:notEqual name="realTable" value="bks_xsszjbxx">
								<logic:equal value="12872" name="xxdm">
								<logic:equal value="xsjxjb" name="realTable">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">ɾ��</a></li>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="xsjxjb" name="realTable">
								<logic:equal value="xsrychb" name="realTable">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">ɾ��</a></li>
							</logic:notEqual>
							</logic:equal>
							
							<logic:notEqual value="xsrychb" name="realTable">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">ɾ��</a></li>
										</logic:notEqual> 
										
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">ɾ��</a></li>
										</logic:notEqual>
										
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
							<logic:notEqual value="no" name="xydel">
<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
<%--										ȫ��ɾ��--%>
<%--									</button>--%>
							</logic:notEqual>
							
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<%--�人����ѧ--%>
									<logic:equal value="10497" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="impAndChkData()">��������</a></li>
									</logic:equal>
									<%--end�人����ѧ--%>
									<logic:notEqual value="10497" name="xxdm">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
												<li><a href="#" class="btn_dr" onclick="impAndChkData()">��������</a></li>
											</logic:notEqual>
										</logic:equal>
										
										<logic:notEqual value="12872" name="xxdm">
											<!-- ��Ͷ����Ϣά�� -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
											<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
											</logic:notEqual>
											<!-- Ͷ����Ϣά�� -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<li><a href="#" class="btn_dr" onclick="impAndChkData()">��������</a></li>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										<!-- "impAndChkData();" -->
									</logic:notEqual>
									
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												&nbsp;
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										&nbsp;
										<li><a href="#" class="btn_dc" onclick="dataExport()">��������</a></li>
										</logic:notEqual>
									</logic:equal>	
									<logic:notEqual value="12872" name="xxdm">
										&nbsp;
											<!-- ��Ͷ����Ϣά�� -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
												<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>
												<logic:equal value="11641" name="xxdm">
													<logic:equal name="realTable" value="xsjxjb">
													<li><a href="#" class="btn_tj" onclick="hhgxyTj()"> ͳ�� </a></li>
													</logic:equal>
												</logic:equal>
											</logic:notEqual>
											<logic:equal name="xxdm" value="1024701">
												<li><a href="#" onclick="bbdcExport();return false;" class="btn_dc">������</a></li>
											</logic:equal>
											<!-- Ͷ����Ϣά�� -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<li><a href="#" class="btn_dc" onclick="dataExport()">��������</a></li>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										
										<!-- �Ϻ�����begin -->
										<logic:equal value="10856" name="xxdm">
											<logic:equal value="wjcfb" name="realTable">
												<li><a href="#" class="btn_dr" onclick="bgdgl()" title="��������Υ�ʹ��ֲ��鵵����ҳ��">���ֲ��鵵����</a></li>	
											</logic:equal>
										</logic:equal>
											<!-- �Ϻ�����end -->
										</logic:equal>
										<!-- ��дȨ -->
										<!-- ������Ϣ �ۺ����ʲ�����ӡ begin -->
										<logic:present name="showjsxx">
										<logic:equal value="zhszcp" name="realTable">
										<li><a href="#" class="btn_dc" onclick="jsxxzcPrint()">����</a></li>
										</logic:equal>
										</logic:present>
										<!-- end -->
										<logic:present name="isCSMZ">
											<logic:equal value="yes" name="isCSMZ">
											</logic:equal>
										</logic:present>
										
									<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
										<li><a href="#" class="btn_dy" onclick="hzyrychprint();">�����ӡ</a></li>
									</logic:equal>
									
									<logic:equal value="view_xsjxjb" name="tableName">
									<logic:equal value="yes" name="writeAble" scope="request">
										<logic:equal value="xy" name="userType">
											<logic:equal value="view_xsrychb" name="tableName">
												<li><a href="#" class="btn_dy" onclick="refreshForm('credit_apply.do')">����</a></li>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
												<li><a href="#" class="btn_dy" onclick="viewMore('add')">����</a></li>
											</logic:notEqual>
											<li><a href="#" class="btn_dy" onclick="hzyjxjmodi()">�޸�</a></li>
<%--											&nbsp;--%>
<%--											<button type="button" class="button2" onclick="viewMore('del')"--%>
<%--												style="width: 80px">--%>
<%--												ɾ ��--%>
<%--											</button>--%>
<%--								&nbsp;	
								--%>
									<li><a href="#" class="btn_dy" onclick="dataExport()">��������</a></li>
									</logic:equal>
									</logic:equal>
										<li><a href="#" class="btn_dy" onclick="hzyprint()" id="buttonQuery">��ӡ����</a></li>
									</logic:equal>
								</logic:present>
								
								<logic:equal value="10878" name="xxdm">
									<logic:equal value="view_zhszcp" name="tableName">
										<li><a href="#" class="btn_dy" onclick="impAndChkDataByahjg()">�������ݵ���</a></li>
									</logic:equal>
								</logic:equal>
								<logic:notPresent name="showjsxx">
									<logic:notEqual value="12872" name="xxdm">
										<%--�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%><%--
										  <logic:equal value="13022" name="xxdm">
											<logic:equal value="view_zhszcp" name="tableName">
												<a href="xlsDown/nblgddcj.xls" target="_blank">���»���</a>

												<a href="xlsDown/nblgddjfb.xls" target="_blank">��Ϊ����</a>

												<a href="xlsDown/nblgshqb.xls" target="_blank">����������</a>
											</logic:equal>
											<logic:notEqual value="view_zhszcp" name="tableName">
												<!--  <a
													href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"
													target="_blank">ģ������</a>-->
											</logic:notEqual>
										</logic:equal>
										--%><logic:notEqual value="13022" name="xxdm">
											<%--�Ϻ�����ӡˢ�ߵ�ר��ѧУ--%>
											<logic:equal value="11733" name="xxdm">
												<%--��������--%>
												<logic:equal value="view_xsjxjb" name="tableName">
													<a href="xlsDown/shcbysgdzkxx_xsjxjb.xls" target="_blank">ģ������</a>
												</logic:equal>
												<%--����������--%>
												<logic:notEqual value="view_xsjxjb" name="tableName">
													<a
														href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"
														target="_blank">ģ������</a>
												</logic:notEqual>
											</logic:equal>
											<%--���Ϻ�����ӡˢ�ߵ�ר��ѧУ--%>
											<logic:notEqual value="11733" name="xxdm">
												<logic:notEqual value="11417" name="xxdm">
													<%--													<a--%>
													<%--														href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"--%>
													<%--														target="_blank">ģ������</a>--%>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<%--����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="yes" name="writeAble">
											<logic:equal value="view_xsjxjb" name="tableName">
												<a href="xlsDown/hzyjxjsqb.xls" target="_blank">ģ������</a>
											</logic:equal>
											<logic:notEqual value="view_xsjxjb" name="tableName">
												<%--												<a--%>
												<%--													href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"--%>
												<%--													target="_blank">ģ������</a>--%>
											</logic:notEqual>
										</logic:equal>
									</logic:equal>
									<%--end����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
								</logic:notPresent>
							</logic:notEqual>	
							<%--���ս�����ҵ<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="10878" name="xxdm">
								<logic:equal value="view_zhszcp" name="tableName">
									<a href="xlsDown/ahjg_sjdxspfb.xls" target="_blank">����ģ��</a>
								</logic:equal>
							</logic:equal>
							<%--end���ս�����ҵ<bean:message key="lable.xsgzyxpzxy" />--%>

								<logic:equal value="wjcfb" name="realTable">
									<!-- ���ݴ�ѧΥ�ͻ��ܱ� -->
									<logic:equal value="11078" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzb.do')">���ֻ���</a></li>
										<li><a href="#" class="btn_dr" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzbByjwc.do')">���񴦴��ֻ���</a></li>
									</logic:equal>
									<!-- �㽭��ý <bean:message key="lable.xsgzyxpzxy" />���ֱ���� -->
									<logic:equal value="11647" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="if (confirm('����ѧ��,ѧ��,<bean:message key="lable.xsgzyxpzxy" />���д������ݵ�ͳ��,ȷ��Ҫ�����?')) {wjcfDataExport('wjcf_zjcm_cfhzb.do?xydm='+document.getElementById('xydm').value)}">ѧ�����ֻ��ܱ�</a></li>
									</logic:equal>
								</logic:equal>				
										
						</ul>
					 </div>
					</div>
		</logic:equal>
</html>