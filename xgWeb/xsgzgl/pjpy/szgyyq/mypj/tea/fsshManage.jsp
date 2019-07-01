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
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//ǰ���ҵ�����
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//��ʼ��
		function onShow(){
		
			var czxm = $("shxm").value;

			if(czxm == ""){
				var id = "left_a_0";
				if($(id)){
					$(id).click();
				}
			}else{
				searchRs();
			}
		}
		
		//��ѯ�����
		function searchRs(){
			var url = "szgyyq_mypj_tea.do?method=getFsshInfoList";

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			//��Ŀ����
			var xmdm = $("shxm").value;
			//��ѯ����
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}
			
			//ģ����ѯ
			var input_mhcx = "";
			
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
				if(input_mhcx == ""){
					input_mhcx = " ";
				}else{
					input_mhcx=escape(input_mhcx);
				}
			}

			//ѧ��
			var xn = new Array();
			var xn_num = jQuery("a[name=a_name_xn]").length;
		
			var num = 0;
			
			if(xn_num != 0){
				for(var i=0;i<xn_num;i++){
					var id = jQuery("a[name=a_name_xn]")[i].id;
					xn[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xn = [" "];
			}
			
			//ѧ��
			var xq = new Array();
			var xq_num = jQuery("a[name=a_name_xq]").length;
		
			num = 0;
			
			if(xq_num != 0){
				for(var i=0;i<xq_num;i++){
					var id = jQuery("a[name=a_name_xq]")[i].id;
					xq[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xq = [" "];
			}
			
			var otherValue = [
				mhcx_lx,
				input_mhcx,
				xmdm,
				xn.join("!!##!!"),
				xq.join("!!##!!")	
			];
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				if($("is_default")){
					$("is_default").value="no";
				}
			}
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;

				if(xn_num != 1 && flag){
					alertError("ѧ����������Ϊ�գ���ֻ�ܲ�ѯ��ǰѧ�꣡");
					flag = false;
				}
				
				if( xq_num != 1 && flag){
					alertError("ѧ����������Ϊ�գ���ֻ�ܲ�ѯ��ǰѧ�ڣ�");
					flag = false;
				}
				
				if(flag){
					var xnid = jQuery("a[name=a_name_xn]")[0].id;
					var xqid = jQuery("a[name=a_name_xq]")[0].id;
					
					var xn_value =xnid.replace("a_id_","");
					var xq_value =xqid.replace("a_id_","");
					var dqxn=jQuery("#xn").val();
					var dqxq=jQuery("#xq").val();
					
					if(xn_value!=dqxn){
						alertError("����ѡ��ǰѧ�꣡");
						flag = false;
					}
					
					if(xq_value!=dqxq && flag){
						alertError("����ѡ��ǰѧ�ڣ�");
						flag = false;
					}
				}
				
			}
			return flag;
		}
		
		//��ʾ�����ϸ
		function showFsshDetail(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 1){
				var xh = jQuery("input[name=checkVal]:checked")[0].value;
				var xmdm = $("shxm").value;
				
				var url = "/xgxt/szgyyq_mypj_tea.do?method=fsshDetail";
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;

				showTopWin(url,'800','600');	
			}else if(num >1){
				var xmdm = $("shxm").value;
				if($("rad_th") && $("span_th") && xmdm=="szyc_5sb"){
					$("rad_th").style.display="none";
					$("span_th").style.display="none";
				}else if($("rad_th") && $("span_th")){
					$("rad_th").style.display="";
					$("span_th").style.display="";
				}
				tipsWindown("ϵͳ��ʾ","id:div_plsh","400","180","true","","true","id");
			}else if(num == 0){
				alertError("�빴ѡ����Ҫ��˵ļ�¼");
				return false;
			}
		}
		
		//�������״̬
		function saveShzt(){
		
			var flag = true;
				
			//�û�����
			var yhlx = $("yhlx").value;
			//��Ŀ����
			var xmdm = $("shxm").value;
			//���״̬
			var shzt = $("hid_shzt").value;
			
			var url="szgyyq_mypj_tea.do?method=savePlShzt";
			
			var i = 0;
			//ID
			var xh = new Array(); 						  
			jQuery("input[name=checkVal]:checked").each(
			function(){
				xh[i] = jQuery(this).val();
				i++;
			});
			
			//����
		 	var parameter = {
		 		"xmdm":xmdm,
		 		"yhlx":yhlx,
		 		"shzt":shzt,
				"xh":xh.join("!!@@!!")
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery.post(url,parameter,function(result){doSuccess(result)});
		}
		
		//ִ�гɹ�
		function doSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){				
				if(tag == "ok"){
					closeWindown();
					$("search_go").click();}});
		}
		
		//��ʾѧ��Ͷ��Div
		function showXstsDiv(tsr,btsr,tsnr,tssj){
			$("tsr").value = tsr;
			$("btsr").value = btsr;
			$("tsnr").value = tsnr;
			$("p_tssj").innerHTML = tssj;
			tipsWindown("ϵͳ��ʾ","id:div_xsts","350","380","true","","true","id");
		}
		
		//����Ͷ�ߴ���
		function saveTscl(){
		
			var clyj = $("clyj").value;
			
			if(clyj == ""){
				alertError("�����������Ϊ�գ���ȷ�ϣ�");
				return false;
			}
			
			var url="szgyyq_mypj_tea.do?method=saveTscl";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			var xn = $("xn").value;
			var xq = $("xq").value;
			var btsr = $("btsr").value;
			var tsr = $("tsr").value;
			var xmlx = $("shxm").value;
			
			//����
		 	var parameter = {
		 		"xn":xn,
		 		"xq":xq,
				"btsr":btsr,
				"tsr":tsr,
				"xmlx":xmlx,
				"clyj":escape(clyj)
			};
			
			jQuery.post(url,parameter,function(result){doSuccess(result);});
		}
		
		// ------------2011.11.21 qlj-------------
		jQuery(function(){
			if(jQuery("#yhlx").val()!="bz"){
				$("div_superSh").style.display=""; 
				//$("div_advanced").style.display="none"; 
			}
		});
		</script>
	</head>
	<body onload="onShow()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - �������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.��ͨ�����<font color="blue">���</font>��ť��һ������ѧ����ˡ�</br>
				3.����ѡһ��ѧ��ʱ��ִ�е���ѧ����ˣ�����ѡ���ѧ��ʱ��ִ������ѧ����ˡ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������Ŀ  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<input type="hidden" name="tsr" id="tsr" value=""/>
			<input type="hidden" name="btsr" id="btsr" value=""/>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goMypj();return false;" class="btn_fh">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="showFsshDetail();return false;" class="btn_sh">
								���
							</a>
						</li>	
					</ul>
				</div>
				<!-- ��ť end-->
		
				<logic:equal name="yhlx" value="bz">
				<!-- �������� -->
				<!-- ģ����ѯ -->
				<div class="search_advanced" >
					<div class="adv_filter">
						<table border="0" width="100%">
							<tbody>
								<tr>
									<td style="padding-left:68px;">
										��ѯ������
								        <input type="text" name="searchModel.input_mhcx"
											id="input_mhcx" value="${searchTj.input_mhcx }" size="50"
											onkeypress="if(pressEnter(event)){searchRs();return false;}"
											onfocus="setTsMsg();displayMsgDiv('input_mhcx_msg');"
											onblur="hideMsgDiv('input_mhcx_msg')" />
			
										<div id="input_mhcx_msg" class="hide"
											style="left: 140px;top: 122px;">
											<div class="prompcon" style="width: 250px">
												<p id="tsxx_span"></p>
											</div>
										</div>
										
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											�� ѯ
										</button>
			
										<button type="button" class="btn_cz" onclick="czSuperSearch()">
											�� ��
										</button>
										<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:radio property="searchModel.mhcx_lx" value="all" onclick="$('mhcx_lx').value = this.value"/>
										ȫ��
										<!-- ģ��ѭ�� -->
										<html:radio property="searchModel.mhcx_lx" value="xh" onclick="$('mhcx_lx').value = this.value"/>ѧ��
										<html:radio property="searchModel.mhcx_lx" value="xm" onclick="$('mhcx_lx').value = this.value"/>����
										<input type="hidden" id="mhcx_lx" value="all"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<!-- ��ѡ����(���Ի�) -->
					<div class="selected-attr" id="yxtj_gxh_div" style="display: none">
						<h3>
							��ѡ������
						</h3>
						<dl id="yxtj_gxh_dl">
				
						</dl>
					</div>
					<!-- ��ѡ���� end-->
					
					<!-- �߼���ѯ(���Ի�)-->
					<div class="prop-item" id="gjcx_gxh_div" style="display: none">
						<!-- ѧ�� -->
						<dl>
							<dt>ѧ�꣺</dt>
							<dd>
								<ul>
									<logic:iterate name="xnTjList" id="xnMap" indexId="list_num">
										<logic:notEqual name="list_num" value="0">
											<li>
												<a href="#" class=""
													onclick="clickTj('xn','${xnMap.xn }');creatGxhClickedTj('xn','ѧ��','${xnMap.xn }','${xnMap.xn }',this);return false;" 
													id="tj_xn_${xnMap.xn }" name="tj_xn">
													${xnMap.xn }
												</a>
											</li>
										</logic:notEqual>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ѧ�� end-->
						
						<!-- ѧ�� -->
						<dl>
							<dt>ѧ�ڣ�</dt>
							<dd>
								<ul>
									<logic:iterate name="xqTjList" id="xqMap" indexId="list_num">
										<li>
											<a href="#" class=""
												onclick="clickTj('xq','${xqMap.xqdm }');creatGxhClickedTj('xq','ѧ��','${xqMap.xqdm }','${xqMap.xqmc }',this);return false;" 
												id="tj_xq_${xqMap.xqdm }" name="tj_xq">
												${xqMap.xqmc }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- ѧ�� end-->			
					</div>
					<!-- �߼���ѯ end-->
				</div>

				<!-- ������ť -->
				<div class="more--item_bottom">
					<p>
						<a href="#" class="down"
							onclick="showGxhTbody(this,'up','down','�� ��','�� ��');return false">
							�� ��
						</a>
					</p>
				</div>
				<!-- ������ť end-->
				
				<!-- �������� -->
				<div id="searchGxhTjDiv" style="display:none">
					<logic:present name="searchTj">
						<!-- ������� -->
						<logic:notEmpty name="searchTj" property="search_tj_xn">
							<!-- ѧ�� -->
							<logic:iterate name="searchTj" property="search_tj_xn" id="queryValue">
								<input type="hidden" title="xn" name="search_tj_xn" value="${queryValue }" />
							</logic:iterate>
							<!-- ѧ�� -->
							<logic:iterate name="searchTj" property="search_tj_xq" id="queryValue">
								<input type="hidden" title="xq" name="searchModel.search_tj_xq" value="${queryValue }" />
							</logic:iterate>
						</logic:notEmpty>	

						<!-- ������ѡ���� -->
						<script language="javascript" defer="defer">
							setTimeout("creatClickedTjByGxhSearch()",500);
						</script>
					</logic:present>
				</div>
				<!-- ��������end-->
				</logic:equal>
				
				<div id="div_superSh" style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 370px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:650px;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqTeaForm"></jsp:include>
				 <script type="text/javascript">
				     $('choose').className="hide";
				 </script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ������˵����� -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ȷ���������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									���״̬
								</th>
								<td>
									<!-- �೤ -->
									<logic:equal name="yhlx" value="bz">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">ͨ��</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">��ͨ��</html:radio>
									</logic:equal>
									<!-- ������ -->
									<logic:equal name="yhlx" value="bzr">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">ͨ��</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">��ͨ��</html:radio>
									</logic:equal>
									<!-- ѧԺ -->
									<logic:equal name="yhlx" value="xy">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">ͨ��</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">��ͨ��</html:radio>
										<html:radio property="shzt" value="th" styleId="rad_th" onclick="$('hid_shzt').value=this.value"><span id="span_th">�˻�</span></html:radio>
									</logic:equal>
									<!-- ѧУ -->
									<logic:equal name="yhlx" value="xx">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">ͨ��</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">��ͨ��</html:radio>
										<html:radio property="shzt" value="th" onclick="$('hid_shzt').value=this.value">�˻�</html:radio>
									</logic:equal>
									<input type="hidden" id="hid_shzt" value="tg"/>
								</td>
							</tr>
							<tr>
								<th width="30%">
									˵��
								</th>
								<td>
									���ѡ��ͨ���Ļ�����ô��ѡ��¼�ķ�����<br/>����ǰһ����ķ���Ϊ׼������ѡ������<br/>¼������ˣ��鿴��ϸ������								
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="saveShzt();return false;">
											ȷ ��
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ѧ�����ߵ����� -->
			<div id="div_xsts" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									Ͷ������
								</th>
								<td>
									<textarea id="tsnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									Ͷ��ʱ��
								</th>
								<td>
									<p id="p_tssj"></p>
								</td>
							</tr>
							<tr id="tr_clyj">
								<th width="30%">
									�������<br/>
									<font color="red"><��500��></font>
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" 
										style="word-break:break-all;width:100%"  
										onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="saveTscl()">
											�� ��
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>