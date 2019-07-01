<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			var id = "left_a_0";
			
			var xmdm=$("xmdm").value;
			
			var first=$("first").value;
			// �ж��Ƿ��Ǵ��ҵ����ģ����ת����
			if(first!=""){
				// ����־λ�ƿ�
				$("first").value="";
				
				// �ж���Ҫѡ�е���Ŀ
				var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
					var xmdmVal=jQuery(this).val();
					
					if(xmdmVal==xmdm){
						
						// ѡ�к�������ɫ
						setLiClick(i);
					}
				});
				showSpgwHidden(xmdm);
				// ��ѯ
				searchRs();
				
			}else{// ����ת���ˣ������ģ�����л���Ŀ��
				// δѡ��
				if($(id) && xmdm==""){
					$(id).click();
				}else if(xmdm!=""){// ��ѡ����Ŀ
					
					var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
						var xmdmVal=jQuery(this).val();
						if(xmdmVal==xmdm){
							$("left_a_"+i).click();
						}
					});
					
					searchRs();
				}
			}
			
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xmsh_ajax.do?method=searchWdpjXmsh";
			var ie = "ie";// ie�汾
			var v4Path = stylePath;//v4��ʽ·��
			var xmdm = $("xmdm").value;//��Ŀ����
			var spgw = $("spgw").value;//������λ
			// ��Ҫ�����̨����������
			var otherValue = [ie,v4Path,xmdm,spgw];

			// loding
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// ��ѯ����
			searchRsByAjax(url,otherValue);

			// ���� loding
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			// �ж��Ƿ��ǵ�һ����˸�λ(�����˻ذ�ť��дȨ)
			if($("hid_firstSpgw")){
			
				var div_firstSpgw=$("hid_firstSpgw").value;
				// ��һ����˲������˻�
				if(div_firstSpgw=="true"){
					$("btn_th").style.display="none";
				}else if(div_firstSpgw=="false"){
					$("btn_th").style.display="";
				}
			}
			
			// ���ʱ�䡢���ؿ���
			if($("shkz")){
				
				if($("shkz").value=="true"){
					// ȡ��������˰�ť
					$("btn_sh").disabled=false;
					
				}else{
					// ���ð�ť
					$("btn_sh").disabled=true;
				
				}
			}
			
			jQuery.ajaxSetup({async:true});
			
			if($("span_note")){
				$("span_note").innerHTML = "��ѯ���&nbsp;&nbsp;��ע�����ѧ�ſɲ鿴��ѧ������ϸ��Ϣ��";
			}
		}

		//������Ŀ���(������ˡ��������)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			if(len==1){// �������
				showWdpjXmsh(pk);
			}else if(len>1){// �������
				tipsWindown("ϵͳ��ʾ","id:div_002","500","300","true","","true","id");
			}else{
				alertError("��<font color='blue'>��ѡ</font>��Ҫ��˵ļ�¼��");
				return false;
			}
		}

		//��ʾ��λ��ϢDiv
		function showGwxxDiv(){
			
			var xmdm=$("xmdm").value;
			// ������Ŀ���뵯��������λ
			showSpgw(xmdm);
			
		}
		
		

		//ǰ����Ŀ���
		function showSpgw(xmdm){
		
			var url="general_wdpj_xmsh_ajax.do?method=showShgwDiv";
			
			//��������
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgw][type=radio]").length;
		
				if(len==1){
					$("btn_sx").disabled=true;
					var spgw=jQuery("[name=spgw]:checked").val();
					jQuery("#xmdm").val(xmdm);
					searchRs();
					
				}else if(len>0 ){
				    $("btn_sx").disabled=false;
					tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
				}
			});
		}
		
		//ǰ����Ŀ���
		function showSpgwHidden(xmdm){
		
			var url="general_wdpj_xmsh_ajax.do?method=showShgwDiv";
			
			//��������
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgw][type=radio]").length;
		
				if(len==1){
					$("btn_sx").disabled=true;
					var spgw=jQuery("[name=spgw]:checked").val();
					jQuery("#xmdm").val(xmdm);
					searchRs();
					
				}else if(len>0 ){
				    $("btn_sx").disabled=false;
					
				}
			});
		}
		
		function checkSpgw(){
		
			$("xmdm").value=jQuery("#text_xmdm").val();
			$("spgw").value=jQuery("[name=spgw]:checked").val();
			
			czSearch();
			
			jQuery("#tj_shzt_������").click();
			jQuery("#tj_shzt_δ���").click();
			
			closeWindown();
			
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var xmdm = $("xmdm").value;
			var spgw = $("spgw").value;
		
			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",function(tag){
				
				if(tag=="ok"){
					
					//����
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						xh[i]=jQuery(this).val();
					});
					
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["str_xmdm"]=xmdm;
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_xh"]=xh.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#textarea_shyj").val());
					var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							
							alertInfo(result,function(tag){
								
								if(tag=="ok"){								
									searchRs();
									closeWindown();
								}
								
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
				
			});
			
		}
		
		
		//���ù�������
		function czSearch(){

		
			//����ģ����ѯ
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//���ø߼���ѯ
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;
	
			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//��ʼʱ��
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//����ʱ��
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ��Ŀ���</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.���չʾ����Ŀ��Ϊ��<font color="blue">���ʸ����</font>��������Ŀ������<font color="blue">��ɫ</font>����ĿΪ�ر���˻����ǵ�ǰʱ�䲻�����ʱ���ڵ���Ŀ��<br/>
				2.�������һ�˶�ڵ����������<font color="blue">�л���λ��ť</font>��������л���<br/>
				3.ϵͳĬ�ϲ�ѯ����<font color="blue">δ���</font>��<font color="blue">������</font>����ѯ�����ļ�¼����Ҫ���ҵ������е����������һ�¡�<br/>
				4.��ѡ<font color="blue">һ��</font>��¼�����<font color="blue">���</font>��ť���û��ڵ���ҳ������������ˡ�<br/>
				5.��ѡ<font color="blue">����</font>��¼�����<font color="blue">���</font>��ť��ִ��������ˡ�<br/>
				6.ǰһ���û����<font color="blue">ͨ��</font>�󣬺�һ����ſ��Խ�����ˡ�<br/>
				7.��һ��������Ѿ���˹��ˣ�ǰһ����<font color="blue">����</font>���޸ġ�<br/>
				8.����󼶱����<font color="blue">�˻�</font>�Ļ���ǰһ������Ҫ<font color="blue">�������</font>��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${xmdm}"/>
			<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
			<input type="hidden" name="first" id="first" value="${first }"/>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ�����
							</a>
						</li>
						<li>
							<a href="#" id="btn_sh" disabled="true"
								onclick="if(checkItsDis(this)){showShxxDiv();};return false;" 
								class="btn_sh">
								<span>���</span>
							</a>
						</li>
						<li>
							<a href="#" id="btn_sx" disabled="true"
								onclick="if(checkItsDis(this)){showGwxxDiv();};return false;"
								class="btn_sx">
								<span>�л���˸�λ</span>
							</a>
						</li>
					</ul>
					<button type="button"  class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
<!--					<tr>-->
<!--						<td colspan="2">-->
<!--							<h3 class="datetitle_01">-->
<!--								<span>-->
<!--									-->
<!--								</span>-->
<!--							</h3>-->
<!--						</td>-->
<!--					</tr>-->
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 372px;" style="overflow-y: auto;overflow-x:hidden">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<logic:equal name="xmnr" property="shkz" value="yes">
													<a href="#" name="left_a" id="left_a_${index}"  title="${xmnr.xmmcxx}"
														onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													</logic:equal>
													<logic:equal name="xmnr" property="shkz" value="no">
													<a href="#" name="left_a" id="left_a_${index}" title="${xmnr.xmmcxx}"
														onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">
														<span class="ico"></span><font style="color: gray;">${xmnr.xmmc}</font>
													</a>
													</logic:equal>
													<input type="hidden" name="xmdmArr" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													<input type="hidden" id="xmmc_${xmnr.xmdm}" value="${xmnr.xmmc}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
				
			<!-- 002 begin -->
			<div id="div_002" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��Ŀ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									������
								</th>
								<td>
									<textarea rows="5" name="shyj" id="textarea_shyj" cols="" style="width:400px"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="saveShzt('tg');return false;">ͨ��</button>
										<button type="button"  onclick="saveShzt('btg');return false;">��ͨ��</button>
										
										<button type="button"  id="btn_th" onclick="saveShzt('th')">�˻�</button>
										
										<button type="button"  onclick="closeWindown();return false;">�ر�</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 002 end-->
			
			<div id="div_spgw" style="display:none">

			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>