<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
			<div id="toolTipLayer"
						style="position: absolute; visibility: hidden" >
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px">
							<logic:equal value="wjcfb" name="realTable">
								<!-- ������������ʾ��У�쿴���� -->
								<logic:notEqual value="11062" name="xxdm">
									<!-- �㽭����У�쿴���� -->
									<logic:equal value="10338" name="xxdm">
										<button type="button" class="button2" id="btn_lxck"
											onclick="showTopWin('wjcf_zjlg_lxckcx.do',750,600)"
											style="width:90px">
											��У�쿴����
										</button>&nbsp;
									</logic:equal>
									<!-- ����ѧУ��У�쿴���� -->
									<!-- �㽭��,���ݴ�ѧ����ʾ��У�쿴���� -->
									<logic:notEqual value="10338" name="xxdm">
										<logic:notEqual value="11078" name="xxdm">
											<logic:notEqual value="12645" name="xxdm">
											<button type="button" class="button2"
												onclick="showTopWin('wjcflxcktj.do',740,600)"
												style="width:90px">
												��У�쿴����
											</button>&nbsp;
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:equal>
							
							<logic:equal value="yes" name="writeAble" scope="request">
								<!-- �γ�ְҵbegin -->
								<logic:equal value="zhszcp" name="realTable">
									<logic:notEqual value="13108" name="xxdm">
										<button type="button" class="button2" id="btn_add" style="width:80px"
											onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">
											���ñ���
										</button>
									</logic:notEqual>
									&nbsp;
									<logic:equal value="13108" name="xxdm">
										<button type="button" class="button2" id="btn_add" style="width:120px"
											onclick="AutoAccountCj('/xgxt/AutoAccount.do?type=drjs')">
											��������¼���
										</button>
									</logic:equal>
									&nbsp;
								</logic:equal>
								<!-- �γ�ְҵend -->
								<logic:equal value="wjcfb" name="realTable">
									<button type="button" class="button2" onclick="wjsjzy('wjcf_shgc_lssjzy.do')"
										style="width:100px">
										��ҵ������ת��
									</button>
								</logic:equal>
								<%--�人����ѧ����<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal value="1049701" name="xxdm">
									<button type="button" class="button2" onclick="kftj('wjcfkftj.do')"
										style="width:60px">
										�۷�ͳ��
									</button>
								</logic:equal>
								<logic:present name="showzdjs">
									<button type="button" class="button2" onclick="jsxxzcCount()"
										style="width: 80px">
										�Զ�����
									</button>
								</logic:present>
								&nbsp;
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="if(curr_row == null){alert('��ѡ��Ҫ��ת��ѧ��!');return false;}else{showTopWin('/xgxt/dtjs_zjcm.do?method=zzgx&rdsj='+curr_row.cells[7].innerText+'&pk='+curr_row.cells[3].innerText,600,480)}"
												style="width: 90px">
												��֯��ϵ��ת
											</button>
										</logic:equal>
										<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=zzYb&go=go',800,600);"
												style="width: 90px">
												��Աת��
											</button>
										</logic:equal>
										<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal name="act" value="active">
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);"
												style="width:90px">
												��չ��������
											</button>
										</logic:equal>
									</logic:equal>
									<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="view_xsrychb" name="tableName">
												<button type="button" class="button2"
													onclick="refreshForm('credit_apply.do')"
													style="width: 80px">
													�� ��
												</button>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('add')"
													style="width: 80px">
													�� ��
												</button>
												</logic:notEqual>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore('add')"
												style="width: 80px">
												�� ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
									&nbsp;
									<logic:present name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
											<button type="button" class="button2" onclick="hzyjxjmodi()"
												style="width: 80px">
												�� ��
											</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore2('modi')"
												style="width: 80px">
												�� ��
											</button>
										</logic:notEqual>
									</logic:present>
									<logic:notPresent name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="xsrychb" name="realTable">
												<button type="button" class="button2" onclick="hzzyrychmodi()"
													style="width: 80px">
													�� ��
												</button>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												
												<button type="button" class="button2" onclick="viewMore('modi')"
													style="width: 80px">
													�� ��
												</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<logic:notEqual name="xxdm" value="10827" scope="session">
												<button type="button" class="button2"
													onclick="<logic:equal value="10827" name="xxdm"><logic:equal value="view_xsrychb" name="tableName">updaterychxx('modi')</logic:equal><logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual></logic:equal><logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>"
													style="width: 80px">
													�� ��
												</button>
											</logic:notEqual>
											<logic:equal value="10827" name="xxdm">
												<button type="button" class="button2" onclick="viewMore('modi')"
													style="width: 80px">
													�� ��
												</button>
											</logic:equal>
										</logic:notEqual>
									</logic:notPresent>
									&nbsp;
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											ɾ ��
										</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										
										<logic:equal value="xsrychb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											ɾ ��
										</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsrychb" name="realTable">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											ɾ ��
										</button>
										</logic:notEqual> 
										
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											ɾ ��
										</button>
										</logic:notEqual>
										
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
									<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
									<%--										ȫ��ɾ��--%>
									<%--									</button>--%>

								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<%--�人����ѧ--%>
									<logic:equal value="10497" name="xxdm">
										<button type="button" class="button2" onclick="impAndChkData()"
											style="width: 80px">
											<!-- impAndChkData(); -->
											��������
										</button>
									</logic:equal>
									<%--end�人����ѧ--%>

									<logic:notEqual value="10497" name="xxdm">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="impAndChkData()"
													style="width: 80px">
													��������
												</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<!-- ��Ͷ����Ϣά�� -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
											<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
												��������
											</button>
											</logic:notEqual>
											<!-- Ͷ����Ϣά�� -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<button type="button" class="button2" onclick="impAndChkData()"
														style="width: 80px">
														��������
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										<!-- "impAndChkData();" -->

									</logic:notEqual>									
								
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												&nbsp;
									0
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										&nbsp;
									<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										��������
									</button>
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										&nbsp;
											<!-- ��Ͷ����Ϣά�� -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
												<button type="button" class="button2" onclick="dataExport()"
													style="width: 80px">
													��������
												</button>
											</logic:notEqual>
											<!-- Ͷ����Ϣά�� -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<button type="button" class="button2" onclick="dataExport()"
													style="width: 80px">
													��������
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
								
									
									<!-- �Ϻ�����begin -->
									<logic:equal value="10856" name="xxdm">
										<logic:equal value="wjcfb" name="realTable">
												&nbsp;
												<button type="button" class="button2" onclick="bgdgl()"
												title="��������Υ�ʹ��ֲ��鵵����ҳ��">
												���ֲ��鵵����
											</button>
										</logic:equal>
									</logic:equal>
									<!-- �Ϻ�����end -->
								</logic:equal>
								<!-- ��дȨ -->
								<!-- ������Ϣ �ۺ����ʲ�����ӡ begin -->
								<logic:present name="showjsxx">
									<logic:equal value="zhszcp" name="realTable">
								&nbsp;
								<button type="button" class="button2" onclick="jsxxzcPrint()"
											style="width: 80px">
											��ӡ
										</button>
									</logic:equal>
								</logic:present>
								<!-- end -->
								<logic:present name="isCSMZ">
									<logic:equal value="yes" name="isCSMZ">
									&nbsp;
							
										
									</logic:equal>
								</logic:present>

								<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
										&nbsp;
									<button type="button" id="btn_print" class="button2" style=""
											onclick="hzyrychprint();">
											�����ӡ
										</button>
									</logic:equal>
									<logic:equal value="view_xsjxjb" name="tableName">
									<logic:equal value="yes" name="writeAble" scope="request">
										<logic:equal value="xy" name="userType">
											<logic:equal value="view_xsrychb" name="tableName">
												<button type="button" class="button2"
													onclick="refreshForm('credit_apply.do')"
													style="width: 80px">
													�� ��
												</button>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
												<button type="button" class="button2" onclick="viewMore('add')"
													style="width: 80px">
													�� ��
												</button>
											</logic:notEqual>									
											&nbsp;
											<button type="button" class="button2" onclick="hzyjxjmodi()"
												style="width: 80px">
												�� ��
										    </button>
<%--											&nbsp;--%>
<%--											<button type="button" class="button2" onclick="viewMore('del')"--%>
<%--												style="width: 80px">--%>
<%--												ɾ ��--%>
<%--											</button>--%>
<%--								&nbsp;										--%>
											<button type="button" class="button2" onclick="dataExport()"
												style="width: 80px">
												��������
											</button>
										</logic:equal>
										
									</logic:equal>
									
											&nbsp;
											<button type="button" class="button2" onclick="hzyprint()"
											style="width: 90px" id="buttonQuery">
											��ӡ����
										</button>
									</logic:equal>
								</logic:present>
								<logic:equal value="10878" name="xxdm">
									<logic:equal value="view_zhszcp" name="tableName">
										<button type="button" class="button2" onclick="impAndChkDataByahjg()"
											style="width: 110px">
											�������ݵ���
										</button>
									&nbsp;
									</logic:equal>
								</logic:equal>
								<logic:notPresent name="showjsxx">
									<logic:notEqual value="12872" name="xxdm">
										<%--�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
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
										<logic:notEqual value="13022" name="xxdm">
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
										<button type="button" class="button2" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzb.do')" style="width:70px">���ֻ���</button>&nbsp;<button type="button" class="button2" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzbByjwc.do')" style="width:95px">���񴦴��ֻ���</button>
									</logic:equal>
									<!-- �㽭��ý <bean:message key="lable.xsgzyxpzxy" />���ֱ���� -->
									<logic:equal value="11647" name="xxdm">
										<button type="button" class="button2" onclick="if (confirm('����ѧ��,ѧ��,<bean:message key="lable.xsgzyxpzxy" />���д������ݵ�ͳ��,ȷ��Ҫ�����?')) {wjcfDataExport('wjcf_zjcm_cfhzb.do?xydm='+document.getElementById('xydm').value)}" style="width:100px">ѧ�����ֻ��ܱ�</button>
									</logic:equal>
								</logic:equal>
							
							<!-- END -->
						</div>
					</center>
				</div>
			</logic:notEqual>