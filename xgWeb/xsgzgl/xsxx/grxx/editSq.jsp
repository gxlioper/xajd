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
		//��ʾ�ֶ��޸�
		function showEditDiv(zd){
			
			var url="xsxx_grxx.do?method=showZdxgDiv";
			
			//��������
		 	var parameter = {
				"zd":zd
			};
		  
			jQuery("#div_zdxg").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				tipsWindown("ϵͳ��ʾ","id:div_zdxg","350","250","true","","true","id");
			});
		}
		
		//�ı�ʡ
		function changeShen(){
			
			var obj = $("shen");
			
			if(jQuery(obj).val()){
				jQuery.getJSON('njjs_jygl.do?method=loadShi',{shen:obj.value},function(data){
					jQuery("#shi").empty().show();
					jQuery("#span_shi").show();
					
					if(data != null && data.length>0){
						var option = jQuery("<option value=''></option>");
						jQuery("#shi").append(option);
						for(var i=0; i<data.length; i++){
							option = jQuery("<option value='"+ data[i].dm+"'>"+ data[i].mc+"</option>");
							jQuery("#shi").append(option);
						}
						
						jQuery("#xian").hide();
						jQuery("#span_xian").hide();
				
					}else{
						
						changeShi();
						
						jQuery("#shi").hide();
						jQuery("#span_shi").hide();
					}
				});
			}else{
				jQuery("#shi").empty().hide();
				jQuery("#span_shi").hide();
				
				jQuery("#xian").hide();
				jQuery("#span_xian").hide();
			}
		}
		
		//�ı���
		function changeShi(){
			
			var shen = $("shen");
			var shi = $("shi");
			
			if(jQuery(shi).val()){
				jQuery.getJSON('njjs_jygl.do?method=loadXian',{shi:shi.value},function(data){
					jQuery("#xian").empty().show();
					jQuery("#span_xian").show();
					
					if(data != null && data.length>0){
						var option = jQuery("<option value=''></option>");
						jQuery("#xian").append(option);
						for(var i=0; i<data.length; i++){
							option = jQuery("<option value='"+ data[i].dm+"'>"+ data[i].mc+"</option>");
							jQuery("#xian").append(option);
						}
					}else{
						jQuery("#xian").hide();
						jQuery("#span_xian").hide();
					}
				});
			}else if(jQuery(shen).val()){
				jQuery.getJSON('njjs_jygl.do?method=loadXian',{shen:shen.value},function(data){
					jQuery("#xian").empty().show();
					jQuery("#span_xian").show();
					
					if(data != null && data.length>0){
						var option = jQuery("<option value=''></option>");
						jQuery("#xian").append(option);
						for(var i=0; i<data.length; i++){
							option = jQuery("<option value='"+ data[i].dm+"'>"+ data[i].mc+"</option>");
							jQuery("#xian").append(option);
						}
					}else{
						jQuery("#xian").hide();
						jQuery("#span_xian").hide();
					}
				});
			}else{
				jQuery("#xian").hide();
				jQuery("#span_xian").hide();
			}
		}
		
		//�ύ�޸Ľ��
		function saveZdxg(zd,zdm,zdlx){
			
			jQuery.ajaxSetup({async:false});
			
			var xgz = "";
			
			if(zdlx == "text" || zdlx == "select" || zdlx == "calendar"){
				var zd_id = zd+"_id";
				if(jQuery("#"+zd_id).val()){
					xgz=jQuery("#"+zd_id).val();
				}else{
					alertInfo("���ܽ�"+zdm+"�޸�Ϊ�գ���ȷ��");
					return false;
				}
			}else if(zdlx == "ssx"){//ʡ����
				
				var shen = jQuery("#shen").val();
				if(shen == "" || shen == null){
					shen = "";
				}
				var shi = jQuery("#shi").val();
				if(shi == "" || shi == null){
					shi = "";
				}
				var xian = jQuery("#xian").val();
				if(xian == "" || xian == null){
					xian = "";
				}
				var ssx = shen+"/"+shi+"/"+xian;
				
				if(ssx == "//"){
					alertInfo("���ܽ�"+zdm+"�޸�Ϊ�գ���ȷ��");
					return false;
				}else{
					xgz = ssx;
				}
			}else if(zdlx == "szbm"){//���ڲ���
				if(jQuery("#bj").val()){
					xgz=jQuery("#bj").val();
				}else{
					alertInfo("���ܽ����ڰ༶�޸�Ϊ�գ���ȷ��");
					return false;
				}
			}
			
			var xh=jQuery("#xh").val();
			var sqid=jQuery("#sqid").val();
			
			var url="xsxx_grxx.do?method=saveZdxg";

			//����
		 	var parameter = {
				"xh":xh,
				"sqid":sqid,
				"xgzd":zd,
				"xgz":escape(xgz)
			};
		 	
			jQuery.post(url,parameter,function(result){
			
				var html = "";
					html+= "<font color=\"blue\">";
					
					if(zdlx == "text" || zdlx == "calendar"){//�����,����
						html+= $(zd+"_id").value;
					}else if(zdlx == "select"){//�����б�
						var obj=$(zd+"_id");
						html+= obj.options[obj.selectedIndex].text;
					}else if(zdlx == "ssx"){//ʡ����
						var shenObj = "";
						if($("shen")){
							shenObj = $("shen");
						}
						var shiObj = "";
						if($("shi")){
							shiObj = $("shi");
						}
						var xianObj = "";
						if($("xian")){
							xianObj = $("xian");
						}
						
						if($("shen")){
							if(shenObj.selectedIndex != "-1"){
								html+= shenObj.options[shenObj.selectedIndex].text;
								html+= " ";
							}
						}
						
						if($("shi")){
							if(shiObj.selectedIndex != "-1"){
								html+= shiObj.options[shiObj.selectedIndex].text;
								html+= " ";
							}
						}

						if($("xian")){
							if(xianObj.selectedIndex != "-1"){
								html+= xianObj.options[xianObj.selectedIndex].text;
							}
						}
					}else if(zdlx == "szbm"){//���ڲ���
						
						var bjdm = jQuery("#bj").val();
						
						var url="xsxx_grxx.do?method=getSzbm";
				
						//����
					 	var parameter = {
							"bjdm":bjdm
						};
						
						jQuery.post(url,parameter,function(result){
							html+= result;
						});
					}
					
					html+= "</font>";
					
				$("p_"+zd).innerHTML = html;
				
				closeWindown();
				
			});
			
			jQuery.ajaxSetup({async:true});
		}
		
		//�����޸�����
		function saveXgsq(){
		
			var sfsh = jQuery("#sfsh").val();
			var sqqx = jQuery("#sqqx").val();
			
			if(sqqx == "no"){
				alertInfo("���������Ѿ�����ظ�������˹��ˣ��޷�����˽׶��޸������Ϣ");
				return false;
			}
			
			if(sfsh == "��"){
				confirmInfo('������ȷ�������޸���',submitXgsq);
			}else{
				confirmInfo('������ȷ�������޸���',submitGrxx);
			}
		}
		
		//�ύ�޸�
		function submitXgsq(tag){
		
			if(tag == "ok"){
			
				var url="xsxx_grxx.do?method=saveXgsq";
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				var xh = jQuery("#xh").val();
				var sqid = jQuery("#sqid").val();
				
				//����
			 	var parameter = {
					"xh":xh,
					"sqid":sqid
				};
				
				jQuery.post(url,parameter,function(result){
				
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					$("sqid").value = result;
					
					showShInfo();
				});
			}
		}
		
		//����ѧ����Ϣ
		function submitGrxx(tag){
		
			if(tag == "ok"){
			
				var url="xsxx_grxx.do?method=submitGrxx";
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				var xh = jQuery("#xh").val();
				
				//����
			 	var parameter = {
					"xh":xh
				};
				
				jQuery.post(url,parameter,function(result){
				
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					alertInfo(result);
				});
			}
		}
		
		//��ʼ��
		function onShow(){
		
			var xh = jQuery("#xh").val();
			var sqid = jQuery("#sqid").val();
			
			jQuery.ajaxSetup({async:false});
			
			if(xh!="" && sqid!=""){
				
				//��ʾ�����Ϣ
				showShInfo();
				//��ʾ�޸���Ϣ
				showXgInfo();
			}else{
				$("div_shInfo").innerHTML = "��δ�����µ��޸�";
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��ʾ�����Ϣ
		function showShInfo(){
		
			var xh = jQuery("#xh").val();
			var sqid = jQuery("#sqid").val();
			
			var url = "xsxx_grxx.do?method=getShInfo";
	   			url+= "&sqid="+sqid;
	   			url+= "&xh="+xh;

			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					
					var shInfo = "";
					
					if(result != null && result.length>0){
					
						shInfo+="Ŀǰ���״����";
						
						for (var i = 0 ; i < result.length; i++){
							
							if(i!=0){
								shInfo+="����>";
							}
							
							//������
							var shyj = result[i].shyj;
							if(shyj== "" || shyj == null){
								shyj = "��δ���";
							}
							
							shInfo+=result[i].gwmc;
							shInfo+="(";
							shInfo+="<font color=\"blue\" title=\""+shyj+"\">";
							shInfo+=result[i].shzt;
							shInfo+="</font>";
							shInfo+=")"
							
							if(result[i].shzt != "�˻�" && result[i].shzt != "δ���"){
								$("sqqx").value = "no";
							}	
						}
					}else{
						shInfo+="��δ�����µ��޸�";
					}
					
					$("div_shInfo").innerHTML = shInfo;
				}
			});
		}
		
		//��ʼ��
		function showXgInfo(){
		
			var xh = jQuery("#xh").val();
			var sqid = jQuery("#sqid").val();
			
			var url = "xsxx_grxx.do?method=getXgInfo";
	   			url+= "&sqid="+sqid;
	   			url+= "&xh="+xh;

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
							$("p_"+zd).innerHTML = html;
						}
					}
					
				}
			});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
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
				<span id="div_shInfo"></span></br>
				<span id="div_help" style="display: none">
				1.���ĳ��Ϣ�Ҳ���<font color="blue">�޸�</font>������֤�����ֶ�����Ȩ�޽����޸ġ�</br>
				2.<font color="blue">��ɫ����</font>Ϊ����Ϣ��ѧ����Ϣ���е����ݣ�<font color="blue">��ɫ����</font>Ϊ�������޸ĺ����Ϣ��</br>
				3.Ϊ��ҳ������࣬ϵͳ�����˲������ݣ���������Ϣ�ı�ͷ����<font color="blue">��ϵ��ʽ</font>������չ������Ϣ�ľ������ݡ�</br>
				4.�������<font color="blue">�޸�ʱ�䷶Χ</font>�ڣ��޷������޸ĺ��ύ������</br>
				5.������������Ѿ�����ĳһ����<font color="blue">��˹���</font>���򲻿����ٽ����޸ĺ��ύ��</br>
				6.����������뱻��һ�����û�<font color="blue">����˻�</font>����ʹ�����޸�ʱ�䷶Χ�ڣ���Ҳ���Խ����޸ġ�</br>
				7.����ƶ���<font color="blue">���״̬</font>�ϣ����Բ鿴��Ӧ����������
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfsh" id="sfsh" value="${sfsh }"/>
			<input type="hidden" name="lcid" id="lcid" value="${lcid }"/>
			<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
			<input type="hidden" name="sqid" id="sqid" value="${sqid }"/>
			<input type="hidden" name="sqqx" id="sqqx" value="yes"/>
			<input type="text" style="display: none"/>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="saveXgsq();return false;" class="btn_ccg">
									�ύ
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->
			</div>
			
			<div style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;">			
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
								<span style="float:right;">${rs.xm_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ա�
							</th>
							<td width="">
								<span style="float:left;" id="p_xb">${rs.xb }</span>
								<span style="float:right;">${rs.xb_kfxg }</span>
							</td>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_csrq">${rs.csrq }</span>
								<span style="float:right;">${rs.csrq_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����
							</th>
							<td width="">
								<span style="float:left;" id="p_mz">${rs.mz }</span>
								<span style="float:right;">${rs.mz_kfxg }</span>
							</td>
							<th width="">
								������ò
							</th>
							<td width="">
								<span style="float:left;" id="p_zzmm">${rs.zzmm }</span>
								<span style="float:right;">${rs.zzmm_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								���֤��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzh">${rs.sfzh }</span>
								<span style="float:right;">${rs.sfzh_kfxg }</span>
							</td>
							<th width="">
								�������
							</th>
							<td width="">
								<span style="float:left;" id="p_pycc">${rs.pycc }</span>
								<span style="float:right;">${rs.pycc_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�������ڵ�
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_hkszd">${rs.hkszd }</span>
								<span style="float:right;">${rs.hkszd_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jg">${rs.jg }</span>
								<span style="float:right;">${rs.jg_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��Դ����(��Դ��)
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_syd">${rs.syd }</span>
								<span style="float:right;">${rs.syd_kfxg }</span>
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
								<span style="float:right;">${rs.bjdm_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="20%">
								ѧ��״̬
							</th>
							<td width="30%">
								<span style="float:left;" id="p_xjztm">${rs.xjztm }</span>
								<span style="float:right;">${rs.xjztm_kfxg }</span>
							</td>
							<th width="20%">
								ѧ��(��)
							</th>
							<td width="">
								<span style="float:left;" id="p_xz">${rs.xz }</span>
								<span style="float:right;">${rs.xz_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ�ע��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzc">${rs.sfzc }</span>
								<span style="float:right;">${rs.sfzc_kfxg }</span>
							</td>
							<th width="">
								�Ƿ��߶�
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzd">${rs.sfzd }</span>
								<span style="float:right;">${rs.sfzd_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ѧʱ��
							</th>
							<td width="">
								<span style="float:left;" id="p_rxrq">${rs.rxrq }</span>
								<span style="float:right;">${rs.rxrq_kfxg }</span>
							</td>
							<th width="">
								��ҵʱ��
							</th>
							<td width="">
<%--								<span style="float:left;" id="p_bysj">${rs.bysj }</span>--%>
<%--								<span style="float:right;">${rs.bysj_kfxg }</span>--%>
								<span style="float:left;" id="p_byny">${rs.byny }</span>
								<span style="float:right;">${rs.byny_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ��ҵ��
							</th>
							<td width="">
								<span style="float:left;" id="p_sfbys">${rs.sfbys }</span>
								<span style="float:right;">${rs.sfbys_kfxg }</span>
							</td>
							<th width="">
								�Ƿ��ѱ�ҵ
							</th>
							<td width="">
								<span style="float:left;" id="p_sfyby">${rs.sfyby }</span>
								<span style="float:right;">${rs.sfyby_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�Ƿ���У
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzx">${rs.sfzx }</span>
								<span style="float:right;">${rs.sfzx_kfxg }</span>
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
								<span style="float:right;">${rs.lxdh_kfxg }</span>
							</td>
							<th width="20%">
								�ֻ�����
							</th>
							<td width="">
								<span style="float:left;" id="p_sjhm">${rs.sjhm }</span>
								<span style="float:right;">${rs.sjhm_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�ѣѺ���
							</th>
							<td width="">
								<span style="float:left;" id="p_qqhm">${rs.qqhm }</span>
								<span style="float:right;">${rs.qqhm_kfxg }</span>
							</td>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_dzyx">${rs.dzyx }</span>
								<span style="float:right;">${rs.dzyx_kfxg }</span>
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
								<span style="float:right;">${rs.lxdh1_kfxg }</span>
							</td>
							<th width="20%">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_yb">${rs.yb }</span>
								<span style="float:right;">${rs.yb_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ͥ��ַ
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jtszd">${rs.jtszd }</span>
								<span style="float:right;">${rs.jtszd_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ͥ�������
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jjzk">${rs.jjzk }</span>
								<span style="float:right;">${rs.jjzk_kfxg }</span>
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
												<span style="float:right;">${rs.jtcy1_xm_kfxg }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_gx">${rs.jtcy1_gx }</span>
												<span style="float:right;">${rs.jtcy1_gx_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_nl">${rs.jtcy1_nl }</span>
												<span style="float:right;">${rs.jtcy1_nl_kfxg }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_sfzh">${rs.jtcy1_sfzh }</span>
												<span style="float:right;">${rs.jtcy1_sfzh_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_mz">${rs.jtcy1_mz }</span>
												<span style="float:right;">${rs.jtcy1_mz_kfxg }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zzmm">${rs.jtcy1_zzmm }</span>
												<span style="float:right;">${rs.jtcy1_zzmm_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zy">${rs.jtcy1_zy }</span>
												<span style="float:right;">${rs.jtcy1_zy_kfxg }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zw">${rs.jtcy1_zw }</span>
												<span style="float:right;">${rs.jtcy1_zw_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh2">${rs.jtcy1_lxdh2 }</span>
												<span style="float:right;">${rs.jtcy1_lxdh2_kfxg }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh1">${rs.jtcy1_lxdh1 }</span>
												<span style="float:right;">${rs.jtcy1_lxdh1_kfxg }</span>
												
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy1_gzdz">${rs.jtcy1_gzdz }</span>
												<span style="float:right;">${rs.jtcy1_gzdz_kfxg }</span>
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
												<span style="float:right;">${rs.jtcy2_xm_kfxg }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_gx">${rs.jtcy2_gx }</span>
												<span style="float:right;">${rs.jtcy2_gx_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_nl">${rs.jtcy2_nl }</span>
												<span style="float:right;">${rs.jtcy2_nl_kfxg }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_sfzh">${rs.jtcy2_sfzh }</span>
												<span style="float:right;">${rs.jtcy2_sfzh_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_mz">${rs.jtcy2_mz }</span>
												<span style="float:right;">${rs.jtcy2_mz_kfxg }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zzmm">${rs.jtcy2_zzmm }</span>
												<span style="float:right;">${rs.jtcy2_zzmm_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zy">${rs.jtcy2_zy }</span>
												<span style="float:right;">${rs.jtcy2_zy_kfxg }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zw">${rs.jtcy2_zw }</span>
												<span style="float:right;">${rs.jtcy2_zw_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh2">${rs.jtcy2_lxdh2 }</span>
												<span style="float:right;">${rs.jtcy2_lxdh2_kfxg }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh1">${rs.jtcy2_lxdh1 }</span>
												<span style="float:right;">${rs.jtcy2_lxdh1_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy2_gzdz">${rs.jtcy2_gzdz }</span>
												<span style="float:right;">${rs.jtcy2_gzdz_kfxg }</span>
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
												<span style="float:right;">${rs.jtcy3_xm_kfxg }</span>
											</td>
											<th width="20%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_gx">${rs.jtcy3_gx }</span>
												<span style="float:right;">${rs.jtcy3_gx_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_nl">${rs.jtcy3_nl }</span>
												<span style="float:right;">${rs.jtcy3_nl_kfxg }</span>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_sfzh">${rs.jtcy3_sfzh }</span>
												<span style="float:right;">${rs.jtcy3_sfzh_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_mz">${rs.jtcy3_mz }</span>
												<span style="float:right;">${rs.jtcy3_mz_kfxg }</span>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zzmm">${rs.jtcy3_zzmm }</span>
												<span style="float:right;">${rs.jtcy3_zzmm_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zy">${rs.jtcy3_zy }</span>
												<span style="float:right;">${rs.jtcy3_zy_kfxg }</span>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zw">${rs.jtcy3_zw }</span>
												<span style="float:right;">${rs.jtcy3_zw_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh2">${rs.jtcy3_lxdh2 }</span>
												<span style="float:right;">${rs.jtcy3_lxdh2_kfxg }</span>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh1">${rs.jtcy3_lxdh1 }</span>
												<span style="float:right;">${rs.jtcy3_lxdh1_kfxg }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy3_gzdz">${rs.jtcy3_gzdz }</span>
												<span style="float:right;">${rs.jtcy3_gzdz_kfxg }</span>
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
								<span style="float:right;">${rs.xmpy_kfxg }</span>
							</td>
							<th width="20%">
								������
							</th>
							<td width="">
								<span style="float:left;" id="p_cym">${rs.cym }</span>
								<span style="float:right;">${rs.cym_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								���(cm)
							</th>
							<td width="">
								<span style="float:left;" id="p_sg">${rs.sg }</span>
								<span style="float:right;">${rs.sg_kfxg }</span>
							</td>
							<th width="">
								����(kg)
							</th>
							<td width="">
								<span style="float:left;" id="p_tz">${rs.tz }</span>
								<span style="float:right;">${rs.tz_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����Ա����
							</th>
							<td width="">
								<span style="float:left;" id="p_fdyxm">${rs.fdyxm }</span>
								<span style="float:right;">${rs.fdyxm_kfxg }</span>
							</td>
							<th width="">
								һ��ͨ��
							</th>
							<td width="">
								<span style="float:left;" id="p_kh">${rs.kh }</span>
								<span style="float:right;">${rs.kh_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��������
							</th>
							<td width="">
								<span style="float:left;" id="p_yhdm">${rs.yhdm }</span>
								<span style="float:right;">${rs.yhdm_kfxg }</span>
							</td>
							<th width="">
								���п���
							</th>
							<td width="">
								<span style="float:left;" id="p_yhkh">${rs.yhkh }</span>
								<span style="float:right;">${rs.yhkh_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								�س�
							</th>
							<td width="">
								<span style="float:left;" id="p_tc">${rs.tc }</span>
								<span style="float:right;">${rs.tc_kfxg }</span>
							</td>
							<th width="">
								�������
							</th>
							<td width="">
								<span style="float:left;" id="p_kslb">${rs.kslb }</span>
								<span style="float:right;">${rs.kslb_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								��ѧ��ʽ
							</th>
							<td width="">
								<span style="float:left;" id="p_rxfs">${rs.rxfs }</span>
								<span style="float:right;">${rs.rxfs_kfxg }</span>
							</td>
							<th width="">
								������ʽ
							</th>
							<td width="">
								<span style="float:left;" id="p_pyfs">${rs.pyfs }</span>
								<span style="float:right;">${rs.pyfs_kfxg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								����״��
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jkzk">${rs.jkzk }</span>
								<span style="float:right;">${rs.jkzk_kfxg }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ������Ϣ end-->
			</div>
			
			<!-- �ֶ��޸ĵ����� -->
			<div id="div_zdxg" style="display:none">
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>