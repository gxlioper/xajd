<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function showXgInfo(){
		
			var xh = jQuery("#xh").val();
			var sqid = jQuery("#sqid").val();
			
			var url = "xsxx_grxx.do?method=getXgInfo";
	   			url+= "&sqid="+sqid;
	   			url+= "&xh="+xh;

			jQuery.ajaxSetup({async:false});
			
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					if(result != null && result.length>0){
						for (var i = 0 ; i < result.length; i++){
							var zd = result[i].zd;
							var zdlx = result[i].zdlx;
							var source_table = result[i].source_table;
							var select_dm = result[i].select_dm;
							var select_mc = result[i].select_mc;
							var xgz = result[i].xgz;
							var ysz = result[i].ysz;
							
							if(zdlx=="select"){
								
								if(source_table != "" && source_table != null){
								
									url="customTable.do?method=getListBySource";
									url+= "&tablename="+source_table;
						   			url+= "&dm="+select_dm;
						   			url+= "&mc="+select_mc;
						   			
									jQuery.ajax({
										type:'post',
										url:url,
										dataType:'json',
										async: false,
										success:function(source){
											if(source == false){
												
											}else{
												if(source!=null && source.length!=0){	
													for(var j=0;j<source.length;j++){
														var dm = source[j].dm;
														var mc = source[j].mc;
														
														if(dm == xgz){
															xgz = mc;
															break;
														}
													}
												}
											}
										}
									});
								}else{
									var dm = select_dm.split("!!luojw!!");
									var mc = select_mc.split("!!luojw!!");
									
									for(var j=0;j<dm.length;j++){
										if(dm[j] == xgz){
											xgz = mc[j];
											break;
										}
									}
								}
							}else if(zdlx=="ssx"){
							
								url="xsxx_grxx.do?method=getSsx";
						
								//����
							 	var parameter = {
									"ssx":xgz
								};
								
								jQuery.post(url,parameter,function(ssx){
									xgz = ssx;
								});
								
							}else if(zdlx=="szbm"){
							
								url="xsxx_grxx.do?method=getSzbm";
						
								//����
							 	var parameter = {
									"bjdm":xgz
								};
								
								jQuery.post(url,parameter,function(bjdm){
									xgz = bjdm;
								});
							}
							
							var html = "";
								html+= "<font color=\"blue\">";
								html+= xgz;
								html+= "</font>";
<%--								if(ysz !=""){--%>
<%--									html+= "<br/>";--%>
<%--									html+= "(";--%>
<%--									html+= ysz;--%>
<%--									html+= ")";--%>
<%--								}--%>
								
							$("p_"+zd).innerHTML = html;
						}
					}
					
				}
			});
			
			jQuery.ajaxSetup({async:true});
		}
		
		//�������״̬
		function saveShzt(shzt){
			$("shzt").value = shzt;
			confirmInfo('��ȷ��Ҫ'+shzt+"��ѧ�����޸�������",submitShzt);
		}
		
		//�ύ�޸����
		function submitShzt(tag){
			
			var xh = $("xh").value;
			var sqid = $("sqid").value;
			var shzt = $("shzt").value;
			var shgw = $("shgw").value;
			var shyj = $("shyj").value;
			
			if(tag == "ok"){
			
				var url="xsxx_grxx.do?method=saveShzt";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//����
			 	var parameter = {
			 		"xh":xh,
					"sqid":sqid,
					"shzt":escape(shzt),
					"shyj":escape(shyj),
					"shgw":shgw
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
				});
			}
		}
		</script>
	</head>
	<body onload="showXgInfo()" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span id="div_help" style="display: none">
				1.<font color="blue">��ɫ����</font>Ϊ����Ϣ��ѧ����Ϣ���е����ݣ�<font color="blue">��ɫ����</font>Ϊ��ѧ�������޸ĺ����Ϣ��</br>
				2.Ϊ��ҳ������࣬ϵͳ�����˲������ݣ���������Ϣ�ı�ͷ����<font color="blue">��ϵ��ʽ</font>������չ������Ϣ�ľ������ݡ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
			<input type="hidden" name="sqid" id="sqid" value="${sqid }"/>
			<input type="hidden" name="shgw" id="shgw" value="${shgw }"/>
			<input type="hidden" name="shzt" id="shzt" value=""/>
			<!-- ������ end-->
			
			<div style="width:100%;height:350px;overflow-x:hidden;overflow-y:auto;">			
				<!-- ������Ϣ -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_jbxx')" style="cursor:hand">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								<p id="p_xh">${rs.xh }</p>
							</td>
							<th width="20%">
								����
							</th>
							<td width="">
								<span style="float:left;" id="p_xm">${rs.xm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ա�
							</th>
							<td width="">
								<span style="float:left;" id="p_xb">${rs.xb }</span>
							</td>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_csrq">${rs.csrq }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����
							</th>
							<td width="">
								<span style="float:left;" id="p_mz">${rs.mz }</span>
							</td>
							<th width="">
								������ò
							</th>
							<td width="">
								<span style="float:left;" id="p_zzmm">${rs.zzmm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								���֤��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzh">${rs.sfzh }</span>
							</td>
							<th width="">
								�������
							</th>
							<td width="">
								<span style="float:left;" id="p_pycc">${rs.pycc }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�������ڵ�
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_hkszd">${rs.hkszd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jg">${rs.jg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��Դ����(��Դ��)
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_syd">${rs.syd }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ������Ϣ end-->
				
				<!-- ѧ����Ϣ -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_xjxx')" style="cursor:hand">
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody  id="tb_xjxx" style="">
						<tr>
							<th width="20%">
								���ڲ���
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_bjdm">${rs.bjdm }</span>
							</td>
						</tr>
						<tr>
							<th width="20%">
								ѧ��״̬
							</th>
							<td width="30%">
								<span style="float:left;" id="p_xjztm">${rs.xjztm }</span>
							</td>
							<th width="20%">
								ѧ��(��)
							</th>
							<td width="">
								<span style="float:left;" id="p_xz">${rs.xz }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ�ע��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzc">${rs.sfzc }</span>
							</td>
							<th width="">
								�Ƿ��߶�
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzd">${rs.sfzd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ѧʱ��
							</th>
							<td width="">
								<span style="float:left;" id="p_rxrq">${rs.rxrq }</span>
							</td>
							<th width="">
								��ҵʱ��
							</th>
							<td width="">
<%--								<span style="float:left;" id="p_bysj">${rs.bysj }</span>--%>
								<span style="float:left;" id="p_byny">${rs.byny }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ��ҵ��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfbys">${rs.sfbys }</span>
							</td>
							<th width="">
								�Ƿ��ѱ�ҵ
							</th>
							<td width="">
								<span style="float:left;" id="p_sfyby">${rs.sfyby }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ���У
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzx">${rs.sfzx }</span>
							</td>
							<th width="">
								
							</th>
							<td width="">
	
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ѧ����Ϣ end-->
	
				<!-- ��ϵ��ʽ -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_lxfs')" style="cursor:hand">
							<th colspan="4">
								<span>��ϵ��ʽ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_lxfs" style="display: none">
						<tr>
							<th width="20%">
								�̶��绰
							</th>
							<td width="30%">
								<span style="float:left;" id="p_lxdh">${rs.lxdh }</span>
							</td>
							<th width="20%">
								�ֻ�����
							</th>
							<td width="">
								<span style="float:left;" id="p_sjhm">${rs.sjhm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�ѣѺ���
							</th>
							<td width="">
								<span style="float:left;" id="p_qqhm">${rs.qqhm }</span>
							</td>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_dzyx">${rs.dzyx }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ��ϵ��ʽ end-->
				
				<!-- ��ͥ��Ϣ -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_jtxx')" style="cursor:hand">
							<th colspan="4">
								<span>��ͥ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jtxx" style="display: none">
						<tr>
							<th width="20%">
								��ͥ�绰
							</th>
							<td width="30%">
								<span style="float:left;" id="p_lxdh1">${rs.lxdh1 }</span>
							</td>
							<th width="20%">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_yb">${rs.yb }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ͥ��ַ
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jtszd">${rs.jtszd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ͥ�������
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jjzk">${rs.jjzk }</span>
							</td>
						</tr>
						<tr>
							<td width="" colspan="4">
								<table width="100%">
									<!-- ��ͥ��Ա1 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>��ͥ��Ա1</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												����
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy1_xm">${rs.jtcy1_xm }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_gx">${rs.jtcy1_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_nl">${rs.jtcy1_nl }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_sfzh">${rs.jtcy1_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_mz">${rs.jtcy1_mz }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zzmm">${rs.jtcy1_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zy">${rs.jtcy1_zy }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zw">${rs.jtcy1_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh1">${rs.jtcy1_lxdh1 }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh2">${rs.jtcy1_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy1_gzdz">${rs.jtcy1_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- ��ͥ��Ա1 end-->
									
									<!-- ��ͥ��Ա2 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>��ͥ��Ա2</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												����
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy2_xm">${rs.jtcy2_xm }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_gx">${rs.jtcy2_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_nl">${rs.jtcy2_nl }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_sfzh">${rs.jtcy2_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_mz">${rs.jtcy2_mz }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zzmm">${rs.jtcy2_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zy">${rs.jtcy2_zy }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zw">${rs.jtcy2_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh1">${rs.jtcy2_lxdh1 }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh2">${rs.jtcy2_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy2_gzdz">${rs.jtcy2_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- ��ͥ��Ա2 end-->
									
									<!-- ��ͥ��Ա3 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>��ͥ��Ա3</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												����
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy3_xm">${rs.jtcy3_xm }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_gx">${rs.jtcy3_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_nl">${rs.jtcy3_nl }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_sfzh">${rs.jtcy3_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_mz">${rs.jtcy3_mz }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zzmm">${rs.jtcy3_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zy">${rs.jtcy3_zy }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zw">${rs.jtcy3_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh1">${rs.jtcy3_lxdh1 }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh2">${rs.jtcy3_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy3_gzdz">${rs.jtcy3_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- ��ͥ��Ա3 end-->
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ��ͥ��Ϣ end-->
				
				<!-- ������Ϣ -->
				<table class="formlist" style="width:95%">
					<thead>
						<tr onclick="hiddenMk('tb_qtxx')" style="cursor:hand">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_qtxx" style="display: none">
						<tr>
							<th width="20%">
								����ƴ��
							</th>
							<td width="30%">
								<span style="float:left;" id="p_xmpy">${rs.xmpy }</span>
							</td>
							<th width="20%">
								������
							</th>
							<td width="">
								<span style="float:left;" id="p_cym">${rs.cym }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								���(cm)
							</th>
							<td width="">
								<span style="float:left;" id="p_sg">${rs.sg }</span>
							</td>
							<th width="">
								����(kg)
							</th>
							<td width="">
								<span style="float:left;" id="p_tz">${rs.tz }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����Ա����
							</th>
							<td width="">
								<span style="float:left;" id="p_fdyxm">${rs.fdyxm }</span>
							</td>
							<th width="">
								һ��ͨ��
							</th>
							<td width="">
								<span style="float:left;" id="p_kh">${rs.kh }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_yhdm">${rs.yhdm }</span>
							</td>
							<th width="">
								���п���
							</th>
							<td width="">
								<span style="float:left;" id="p_yhkh">${rs.yhkh }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�س�
							</th>
							<td width="">
								<span style="float:left;" id="p_tc">${rs.tc }</span>
							</td>
							<th width="">
								�������
							</th>
							<td width="">
								<span style="float:left;" id="p_kslb">${rs.kslb }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ѧ��ʽ
							</th>
							<td width="">
								<span style="float:left;" id="p_rxfs">${rs.rxfs }</span>
							</td>
							<th width="">
								������ʽ
							</th>
							<td width="">
								<span style="float:left;" id="p_pyfs">${rs.pyfs }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����״��
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jkzk">${rs.jkzk }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ������Ϣ end-->
			</div>
			
			<div style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;">	
				<logic:present name="rsList">
					<table class="formlist" style="width:95%">
						<thead onclick="">
							<tr>
								<th colspan="4">
									<span>������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="shInfo" name="rsList">
								<tr>
									<th width="20%">
										${shInfo.gwmc }�����
									</th>
									<td width="30%">
										${shInfo.xm }
									</td>
									<th width="20%">
										${shInfo.gwmc }���ʱ��
									</th>
									<td>
										${shInfo.shsj }
									</td>
								</tr>
								<tr>
									<th>
										${shInfo.gwmc }���״̬
									</th>
									<td colspan="3">
										${shInfo.shzt }
									</td>
								</tr>
								<tr>
									<th>
										${shInfo.gwmc }������
									</th>
									<td style="word-break:break-all;" colspan="3">
										${shInfo.shyj }
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:present>
			</div>	
			<table class="formlist" style="width:95%">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">		
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>