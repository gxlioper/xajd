<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglCx.js"></script>
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
			// ����ģ��
			function szXszCommon(csdm) {
			 	jQuery.post("xsxx_xsxxgl.do?method=cxXsz",{csdm:csdm},function(data){
				 	 var csz = data["csz"];
					 if(csz == null){
						 showAlertDivLayer("ģ��δ��ʼ����");
						 return false;
					 }else{
						// ��ʼ�����
						var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
						LODOP.PRINT_INIT("��ӡ");
						// ����ģ��Ԫ��
						eval(csz); 
						// ���÷���ֵ
						LODOP.SET_PRINT_MODE("PRINT_SETUP_PROGRAM",true);
						
						//var csz = LODOP.PRINT_SETUP(); // ��ͨ�û�ģʽ��ֻ�ܵ���ģ�����ݵ�λ�ã�
						var csz = LODOP.PRINT_DESIGN(); // ������Աģʽ�������޸�ģ�����ݣ�
						
						confirmInfo("�Ƿ񱣴�ģ�壿",function(data){
							if("ok"==data){
								jQuery.post("xsxx_xsxxgl.do?method=bcXsz",{csdm:csdm, csz:csz},function(data){
									showAlertDivLayer(data["message"]);
								},'json');
							}
						});
					 }
				},'json');
			}
			// ��ӡģ��
			function dyXszCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyXsz",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("ѧ����Ϣδ��д����������д���ٴ�ӡ��");
								 return false;
							 }else{
								// ��ʼ�����
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("��ӡ");
								// ����ģ��Ԫ��
								eval(csz);
								// ��ӡԤ��
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			function dyByzsCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyByzs",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("��ҵѧ����Ϣδ��д����������д���ٴ�ӡ��");
								 return false;
							 }else{
								// ��ʼ�����
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("��ӡ");
								// ����ģ��Ԫ��
								eval(csz);
								// ��ӡԤ��
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			// �����໪ѧ��֤��ӡ
			function dyXszXaph(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("xsxx_xsxxgl.do?method=dyXszXaph",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("ѧ����Ϣδ��д����������д���ٴ�ӡ��");
								 return false;
							 }else{
								// ��ʼ�����
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("��ӡ");
								// ����ģ��Ԫ��
								eval(csz);
								// ��ӡԤ��
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

			
	/*ѧ����Ϣ����*/
	function xsxxdc(){
	 var rows = jQuery("#dataTable").getSeletRow();
	 var len = rows.length;
	 if(len == 0){
		 showAlertDivLayer("���ȹ�ѡ���ݣ��ٽ��е���������");
		 return false;
	 }
	 var xhs = "";
	 jQuery(rows).each(function(i,row){
		 var xh = row["xh"];
		 /*Ϊ�˷�ֹ�п�ѧ�ţ��п����в������ݣ����ߴ�������*/
		 if(jQuery.trim(xh).length !=0){
			 xhs +=xh; 
		 }
		 if(i != len -1){
			 xhs +=","; 
		 }
	 })
	 var url = "xsxx_hzdc.do?method=xsxxDcPrepare&xhs="+xhs;
	 showDialog("ѧ����Ϣ����", 690, 500, url);
	}
	//����������ר
	function sxcl(){
		var ids = jQuery("#dataTable").getSeletIds();
		var cnt = ids.length;
		if(cnt == 0){
			return showAlertDivLayer("���ȹ�ѡ���ݣ��ٽ���ʵϰ���������");
		}
		var xh = cnt ==1 ? ids : "";
		var url = "xsxx_xsxxgl.do?method=sxztXg&cnt="+cnt+"&xh="+xh;
		showDialog("ʵϰ����", 300, 210, url);
	}
			
		</script>
	</head>
	<body>
		<input type="hidden" id="pksPlHidden" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_xsxxgl" method="post">
			<input type="hidden" name="method" id="method" value="${method}"/>
	
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<input type="hidden" name="xh" id="xh" />
			<input type="hidden" name="value" id="value" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<!-- ���Ĵ�����ְҵ����ѧԺ  �����Ƽ�ʦ����ѧ  �㽭��ýѧԺ-->
							<logic:notEqual name="xxdm" value="11647">
							  <logic:notEqual name="xxdm" value="12751">
								<logic:notEqual name="xxdm" value="11318">
									<li>
										<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
											class="btn_zj"> ���� </a>
									</li>
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
											class="btn_xg"> �޸� </a>
									</li>
									<!-- �������ѧԺ������������ƾ��ߵ�ְҵ����ѧУ����ɾ����ť -->
									<logic:notEqual value="12309" name="xxdm">
										<logic:notEqual value="2297" name="xxdm" >
											<li>
												<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
													class="btn_sc"> ɾ�� </a>
											</li>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							  </logic:notEqual>
							</logic:notEqual>
							<!-- end ���Ĵ�����ְҵ����ѧԺ  �����Ƽ�ʦ����ѧ  �㽭��ýѧԺ-->
							
							<!-- �Ĵ�����ְҵ����ѧԺ -->
							<logic:equal name="xxdm" value="12751">
								<logic:notEqual value="xx" name="userStatus">
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;"
											id="btn_xg" class="btn_xg"> �޸� </a>
									</li>
								</logic:notEqual>
							</logic:equal>
							<!-- end �Ĵ�����ְҵ����ѧԺ -->
							
							<!-- �����Ƽ�ʦ����ѧ-->
							<logic:equal name="xxdm" value="11318">
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
										class="btn_zj"> ���� </a>
								</li>
								</logic:equal>
								<li>
									<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
										class="btn_xg"> �޸� </a>
								</li>
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
										class="btn_sc"> ɾ�� </a>
								</li>
								</logic:equal>
							</logic:equal>
							<!-- end �����Ƽ�ʦ����ѧ -->
							
							<!-- �㽭��ýѧԺ-->
							<logic:equal name="xxdm" value="11647">
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj"
										class="btn_zj"> ���� </a>
								</li>
								</logic:equal>
								<li>
									<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg"
										class="btn_xg"> �޸� </a>
								</li>
								<logic:equal value="xx" name="userStatus">
								<li>
									<a href="#" onclick="deletezxsxx();return false;" id="btn_sc"
										class="btn_sc"> ɾ�� </a>
								</li>
								</logic:equal>
							</logic:equal>
							<!-- end �㽭��ýѧԺ -->
							<%--���������е�ר��ѧУ ʵϰ����--%>	
							<logic:equal value="1773" name="xxdm">
							<li>
								<a href="#" onclick="sxcl();return false;"
									class="btn_csh">ʵϰ����</a>
							</li>
							</logic:equal>						
						</logic:equal>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck"
								class="btn_ck"> �鿴 </a>
						</li>
						
						<li>
							<a href="#" class="btn_dc" id="btn_dc" onclick="zxsxxExportConfig();return false;">����</a>
						</li>
						
						<!-- ����ʯ�ʹ�ѧ���Ի� start-->
						<!-- ѧ��֤��ӡ start -->
						<logic:equal name="xszFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('xsz_${xxdm }');return false;" class="btn_dy">����ѧ��֤���������ã�</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyXszCommon('xsz_${xxdm }');return false;" class="btn_dy">��ӡѧ��֤</a></li>
						</logic:equal>
						<!-- ѧ��֤��ӡ end -->
						<!-- ��һѧλ֤�� start -->
						<logic:equal name="dyxwzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('dyxwzs_${xxdm }');return false;" class="btn_dy">���õ�һѧλ֤�飨�������ã�</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('dyxwzs_${xxdm }');return false;" class="btn_dy">��һѧλ֤��</a></li>
						</logic:equal>
						<!-- ��һѧλ֤�� end -->
						<!-- �ڶ�ѧλ֤�� start -->
						<logic:equal name="dexwzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('dexwzs_${xxdm }');return false;" class="btn_dy">���õڶ�ѧλ֤�飨�������ã�</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('dexwzs_${xxdm }');return false;" class="btn_dy">�ڶ�ѧλ֤��</a></li>
						</logic:equal>
						<!-- �ڶ�ѧλ֤�� end -->
						<!-- ר����֤�� start -->
						<logic:equal name="zsbzsFlag" value="true">							
<%--						<li><a href="javascript:void(0);" onclick="szXszCommon('zsbzs_${xxdm }');return false;" class="btn_dy">����ר����֤�飨�������ã�</a></li>--%>
							<li><a href="javascript:void(0);" onclick="dyByzsCommon('zsbzs_${xxdm }');return false;" class="btn_dy">ר����֤��</a></li>
						</logic:equal>
						<!-- ר����֤�� end -->
						<!-- ����ʯ�ʹ�ѧ���Ի� end-->
						<logic:equal name="xxdm" value="10220">
						   </ul>
						  </div>
						  <div class="buttonbox" id="buttonbox2">
						   <ul>
						</logic:equal>
						<!--  -->
						<logic:notEqual name="xxdm" value="14008">
							<logic:notEqual name="xxdm" value="90211">
								<logic:notEqual name="xxdm" value="11600">
									<logic:notEqual name="xxdm" value="10834">
										<logic:notEqual name="xxdm" value="9800003">
											<logic:notEqual name="xxdm" value="110501">
												<logic:notEqual name="xxdm" value="13033">
													<logic:notEqual name="xxdm" value="12684">
														<logic:notEqual name="xxdm" value="70002">
															<%--<li>
																<a href="javascript:void(0);"
																   onclick="printTyXjk();return false;" class="btn_dy" id="dyxsxx">
																	<logic:equal name="xxdm" value="12036">
																		��У��֤����ӡ
																	</logic:equal>
																	<logic:notEqual name="xxdm" value="12036">
																		<logic:notEqual name="xxdm" value="13431">
																			ѧ���ǼǱ��ӡ
																		</logic:notEqual>
																	</logic:notEqual>
																	<logic:equal name="xxdm" value="13431">
																			ѧ������
																	</logic:equal>
																</a>
															</li>--%>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>

						<!-- ����ְҵ����ѧУ���Ի� -->
						<logic:equal name="xxdm" value="90211">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">ѧ���ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						
						<!-- �����Ƽ�ʦ����ѧ���Ի� -->
						<logic:equal name="xxdm" value="11318">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">��ѧ�ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						<!-- �㽭��ý��ѧ���Ի� -->
						<logic:equal name="xxdm" value="11647">							
							<li>
								<a href="javascript:void(0);" onclick="printRxdjb();return false;"
									class="btn_dy">�����ǼǱ��ӡ</a>
							</li>
						</logic:equal>
								<%--<!--������ҵ��ѧ -->
						<logic:equal name="xxdm" value="10022">
							<li>
								<a href="javascript:void(0);" onclick="printJbxxdjb();return false;"
									class="btn_dy">������������Ϣ�ǼǱ�</a>
							</li>
						</logic:equal>--%>
						<!-- ���ϻ���ְҵ����ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="13033">
							<li>
								<a href="javascript:void(0);"
									onclick="printTyXjk();return false;" class="btn_dy">�ǼǱ��ӡ</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="printDjb();return false;" class="btn_dy">ѧ������ӡ</a>
							</li>
						</logic:equal>
						<!-- ������·ְҵ����ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="13943">
							<li>
								<a href="javascript:void(0);"
									onclick="printDjb();return false;" class="btn_dy">ѧ������ӡ</a>
							</li>
						</logic:equal>
						
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ���Ի� -->	
						<logic:equal name="xxdm" value="14008">
							<li>
								<a href="javascript:void(0);"
									onclick="printRxdjb();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						
						<!-- ��������ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="11600">
							<li>
								<a href="javascript:void(0);"
									onclick="printZxsxx();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						<!-- �人ְҵ����ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="10834">
							<li>
								<a href="javascript:void(0);"
									onclick="printZxsxx();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a>
							</li>
						</logic:equal>
										
						<!-- �㶫�Ṥ -->
						<logic:equal name="xxdm" value="9800003">
							<li>
								<a href="javascript:void(0);" onclick="printXjk();return false;"
									class="btn_dy">ѧ��ѧ������ӡ</a>
							</li>
						</logic:equal>
						
						<!-- �Ϻ������ѧ -->
						<logic:equal name="xxdm" value="10264">
							<li>
								<a href="javascript:void(0);" onclick="printXsdjb();return false;"
									class="btn_dy">�����ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						
						<logic:equal name="xxdm" value="	12866">
							<li>
								<a href="javascript:void(0);" onclick="printXsdjb();return false;"
									class="btn_dy">�����ǼǱ��ӡ</a>
							</li>
						</logic:equal>
						
						<!--�������� -->
						<logic:equal name="xxdm" value="80152">
							<li>
								<a href="javascript:void(0);" onclick="printCjd();return false;"
									class="btn_dy">�ɼ�����ӡ</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printHzqcxjk();return false;"
									class="btn_dy">ѧ������ӡ</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="70002">
							<li>
								<a href="javascript:void(0);" onclick="printXzyyXjk();return false;"
									class="btn_dy">ѧ��ѧ������ӡ</a>
							</li>
						</logic:equal>
						<!-- ��ͨ�Ƽ�ְҵѧԺ -->
						<logic:equal name="xxdm" value="12684">
							<li>
								<a href="javascript:void(0);" onclick="printHzqcxjk();return false;"
									class="btn_dy" id="btn_ztzyjsxyXjk">ѧ������ӡ</a>
							</li>
							<!--
							<li>
								<a href="javascript:void(0);" onclick="printXscjb();return false;"
									class="btn_dy">�����Ƴɼ���</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printXscjbwu();return false;"
									class="btn_dy">�����Ƴɼ���</a>
							</li>
							 -->
						</logic:equal>
						<!-- �㽭��ѧ -->
						<logic:equal name="xxdm" value="10335">
							<li>
								<a href="javascript:void(0);" onclick="printXsrxdjb();return false;"
									class="btn_dy">������ѧ�ǼǱ�</a>
							</li>
						</logic:equal>
						<!-- ��ͨ�ߵ�ʦ��ѧУ -->
						<logic:equal name="xxdm" value="110501">
							<!-- update �Ų�·[982] -->
							<li>
								<a href="javascript:void(0);" onclick="getWord();return false;"
									class="btn_dy">����ѧ��ѧ����</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;"
									class="btn_dr">���������Ϣ</a>
							</li>
						</logic:equal>
						<!-- ����ʦ����ѧ -->
						<logic:equal name="xxdm" value="10511">
							<li>
								<a href="javascript:void(0);" onclick="getXskpWord();return false;"
									class="btn_dy" id="btn_dyCard">����ѧ����Ƭ</a>
							</li>
						</logic:equal>
	
						<logic:equal name="writeAble" value="yes">	
<%--						<li><a href="javascript:void(0);" onclick="drxx();return false;" id="btn_dr" class="btn_dr">����</a></li>--%>
						<li><a href="javascript:void(0);" onclick="drxxNew();return false;" id="btn_dr" class="btn_dr">����</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						--%>
						<logic:equal name="xxdm" value="10052">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">�����ʼ��</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10335">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">�����ʼ��</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10026">
							<li>
								<a href="#" onclick="mmcsh('show');return false;"
									class="btn_csh">�����ʼ��</a>
							</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10052">
							<logic:notEqual name="xxdm" value="10335">
								<logic:notEqual name="xxdm" value="10026">
									<logic:equal name="writeAble" value="yes">
										<li>
											<a href="#" onclick="mmcsh('show');return false;"
												class="btn_csh">�����ʼ��</a>
										</li>
									</logic:equal>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<!--��������ѧԺ-->
						<logic:equal name="xxdm" value="13871">
							<logic:notEqual name="writeAble" value="yes">
								<logic:equal value="true" name="bzrfdy">
									<li>
										<a href="#" onclick="mmcsh('show');return false;"
											class="btn_csh">�����ʼ��</a>
									</li>
								</logic:equal>
							</logic:notEqual>
						</logic:equal>
						<%--�����໪ѧԺ --%>
						<logic:equal name="xxdm" value="11400">
						<%--  
							<li><a href="javascript:void(0);" onclick="szXszCommon('xsz_${xxdm }');return false;" class="btn_dy">����ѧ��֤���������ã�</a></li>--%>
					        <li><a href="javascript:void(0);" onclick="dyXszXaph('xsz_${xxdm }');return false;" class="btn_dy">��ӡѧ��֤</a></li>
							
						</logic:equal>
						<li>
							<a href="#" onclick="xsxxdc();return false;" id="btn_xsxxdy"
								class="btn_dy">ѧ����Ϣ��ӡ</a>
                        </li>
					</ul>
				</div>
				
				<!-- ���пƼ���ѧ�����У -->
				<logic:equal name="xxdm" value="12309">
				<div class="buttonbox" id="buttonbox2">
					<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="printZzjsx();return false;" class="btn_dy">����֯��ϵ�����Ŵ�ӡ</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="tyqntj();return false;" class="btn_dy">ѧ����Ա�������ͳ��</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				
				<!-- �㽭����ѧԺ -->
				 <logic:equal name="xxdm" value="11483">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="zjjcXshzExportData();return false;"
									class="btn_dc" >��ҵ����У���ֵ���</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="zjjcZcfExportData();return false;"
									class="btn_dc" >ѧ���ۺ����ʵ���</a>
							</li>
														<li>
								<a href="javascript:void(0);" onclick="zjjcZhqkExportData();return false;"
									class="btn_dc" >ѧ�������ۺ��������</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				
				<!-- ����ְҵ����ѧԺ -->
				<logic:equal name="xxdm" value="13265">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="xnzyjsxyhmcExportData();return false;"
									class="btn_dc" >�༶�����ᵼ��</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- ��ť end-->
				<logic:equal name="xxdm" value="12303">
					<div class="buttonbox" id="buttonbox2">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="getPrintTGB();return false;"
									class="btn_dc" >�Ÿɲ���Ϣ��ӡ</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="getPrintTY();return false;"
									class="btn_dc" >��Ա��Ϣ��ӡ</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- ���칤��ְҵ����ѧԺ -->
				<logic:equal name="xxdm" value="12759">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="printSI();return false;"
									class="btn_dy" >ѧ�����֤����ӡ</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- ���칤��ְҵ����ѧԺ: end-->
				<!-- �㽭����ְҵ����ѧԺ -->
				<logic:equal name="xxdm" value="12867">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="ycsjTs();return false;"
									class="btn_dy" >�쳣ѧ��ͬ��</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- ������ְҵѧԺ -->
				<logic:equal name="xxdm" value="14539">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="ycsjTs();return false;"
									class="btn_dy" >�쳣ѧ��ͬ��</a>
							</li>
					    </ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>

		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>ѧ����Ϣ�б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
