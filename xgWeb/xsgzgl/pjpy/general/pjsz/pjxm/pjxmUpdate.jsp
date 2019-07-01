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
		<script language="javascript" defer="defer">

		//��ʼ��
		function onShow(){
			//��ʼ��������Ŀ����
			defaultPjxmUpdate();
		}

		//��ʼ��������Ŀ����
		function defaultPjxmUpdate(){
			
			jQuery.ajaxSetup({async:false});

			//����
			var xmdm = jQuery("#xmdm").val();
			//·��
			var url = "general_pjsz_pjxm_ajax.do?method=defaultPjxmUpdate";
			//����
		 	var parameter = {
				"xmdm":xmdm
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_pjxm").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);
			
			//defaultXmInfo();
			
			jQuery.ajaxSetup({async:true});
		}

		//����������
		function clickShlc(lcid){

			var rssz = jQuery("[name=rssz]:checked").eq(0).val();
			//·��
			var url = "general_pjsz_pjxm_ajax.do?method=defaultShlcGwxx";
			//����
		 	var parameter = {
				"lcid":lcid,
				"rssz":rssz
			};
			
			if($("div_shgw")){
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery("#div_shgw").load(
					url,
					parameter,
					function(){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";

						//��������
						var rskz = jQuery("#hidden_rskz").val();
						if(rskz == ""){
							rskz = jQuery("input[name=rskz]:checked").val();
							jQuery("#hidden_rskz").val(rskz);
						}else{
							jQuery("input[name=rskz][value="+rskz+"]").attr("checked",true);
						}
						
						//��ÿ���
						var jdkz = jQuery("#hidden_jdkz").val();
						if(jdkz == ""){
							jdkz = jQuery("input[name=jdkz]:checked").val();
							jQuery("#hidden_jdkz").val(jdkz);
						}else{
							jQuery("input[name=jdkz][value="+jdkz+"]").attr("checked",true);
						}
						
						//��Ŀ˳��
						var xmsy = jQuery("#hidden_xmsy").val();
						if(xmsy == ""){
							xmsy = jQuery("input[name=xmsy]:checked").val();
							jQuery("#hidden_xmsy").val(xmsy);
						}else{
							jQuery("input[name=xmsy][value="+xmsy+"]").attr("checked",true);
						}
					}
				);
			}
		}

		//��ʼ����Ŀ��Ϣ
		function defaultXmInfo(){

			jQuery.ajaxSetup({async:false});
			
			//��Ŀ����
			var xmmc =jQuery("#xmmc").val(); 
			jQuery("#hidden_xmmc").val(xmmc);
			//��Ŀ����
			var xmlx =jQuery("input[name=xmlx]:checked").eq(0).val();
			jQuery("#hidden_xmlx").val(xmlx);
			//��Ŀ����
			var xmxz =jQuery("#xmxz").val(); 
			jQuery("#hidden_xmxz").val(xmxz);
			//���뷽ʽ
			var sqfs =jQuery("input[name=sqfs]:checked").eq(0).val();
			jQuery("#hidden_sqfs").val(sqfs);
			//��Ŀ���
			var xmje =jQuery("#xmje").val(); 
			jQuery("#hidden_xmje").val(xmje);
			//��ʾ˳��
			var xssx =jQuery("#xssx").val(); 
			jQuery("#hidden_xssx").val(xssx);
			//��Ŀ˵��
			var xmsm =jQuery("#xmsm").val(); 
			jQuery("#hidden_xmsm").val(xmsm);
			//�Ƿ����
			var sfsh =jQuery("input[name=sfsh]:checked").eq(0).val();
			jQuery("#hidden_sfsh").val(sfsh);
			//��������
			var rssz =jQuery("input[name=rssz]:checked").eq(0).val();
			jQuery("#hidden_rssz").val(rssz);
			//�Ƿ�����
			var sfqy =jQuery("input[name=sfqy]:checked").eq(0).val();
			jQuery("#hidden_sfqy").val(sfqy);
			//����ID
			var lcid =jQuery("input[name=lcid]:checked").eq(0).val();
			jQuery("#hidden_lcid").val(lcid);
			//���Ʒ�Χ
			var kzfw =jQuery("input[name=kzfw]:checked").eq(0).val();
			jQuery("#hidden_kzfw").val(kzfw);
			var rskz =jQuery("input[name=rskz]:checked").eq(0).val();
			jQuery("#hidden_rskz").val(rskz);
			var jdkz =jQuery("input[name=jdkz]:checked").eq(0).val();
			jQuery("#hidden_jdkz").val(jdkz);
			
			jQuery.ajaxSetup({async:true});
			
		}

		//��Ᵽ��������Ŀ
		function checkSavePjxm(){
			confirmInfo('��Ҫִ�б������������ȷ��',savePjxm);
		}
		
		//����������Ŀ
		function savePjxm(tag){

			if(tag == "ok"){
				

				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_pjxm_ajax.do?method=savePjxm";
	
			 	//����һ��json����
				var parameter={};
				
				//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
				var hid_obj=jQuery("input",jQuery("#div_submit_value")).each(function(){
					
					//��ȡ���ؼ�name
					var name=jQuery(this).attr("name");
					//����json����
					parameter[name]=escape(jQuery(this).val());
				});
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//�����Ŀ����
		function checkXmmc(xmmc){
			
			var hid_xmmc=$("hid_xmmc").value;
			if(xmmc!=hid_xmmc){
				if(xmmc!="" ){
					jQuery.ajaxSetup({async:false});
					
					var url = "general_pjsz_pjxm_ajax.do?method=checkXmmc";
		
				 	//����һ��json����
					var parameter={};
					parameter["str_xmmc"]=escape(xmmc);
					
					jQuery.post(url,
						parameter,
						function(result){
							if(result == ""){
								$("btn_bc").disabled=false;
								$("btn_next").disabled=false;
								hideMsgDiv('input_xmmc_msg');
							}else{
								$("btn_bc").disabled=true;
								$("btn_next").disabled=true;
								jQuery("#input_xmmc_msg").attr("class","msg_prompt");
							}
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}else{
					//�Ƿ����
					var sfsh = jQuery("input[name=sfsh]:checked").val();
					//��������
					var rssz = jQuery("input[name=rssz]:checked").val();
	
					if(sfsh == "yes" && rssz == "yes"){
						$("btn_next").disabled=true;
					}
				}
			}
		}
		
		//��ⰴť
		function checkBtn(){
			
			//�Ƿ����
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//��������
			var rssz = jQuery("input[name=rssz]:checked").val();
			
			if(sfsh == "yes" && rssz == "yes"){
				if($("btn_next")){
					$("btn_next").disabled=false;
				}
			}else if(sfsh == "no" && rssz == "no"){
				if($("btn_bc")){
					$("btn_bc").style.display="";
				}
				if($("btn_next")){
					$("btn_next").style.display="none";
				}
			}else{
				if($("btn_bc")){
					$("btn_bc").style.display="none";
				}
				if($("btn_next")){
					$("btn_next").style.display="";
				}
			}
		}
		
		function checkSfsh(){
			
			var sfsh = jQuery("input[name=sfsh]:checked").eq(0).val();
			
			if("yes"==sfsh){
				$("tab_shlc").style.display="";
				$("tab_shgw").style.display="";
			}else{
				$("tab_shlc").style.display="none";
				$("tab_shgw").style.display="none";
			}
		}
		
		function checkRssz(){
			
			var rssz = jQuery("input[name=rssz]:checked").eq(0).val();
			if("yes"==rssz){
				$("tab_rssz").style.display="";
				$("tab_rssz").style.display="";
				jQuery("[name=rskzArr]").each(function(){
					jQuery(this).attr("style","");
				});
				
			}else{
				$("tab_rssz").style.display="none";
				$("tab_rssz").style.display="none";
				jQuery("[name=rskzArr]").each(function(){
					
					jQuery(this).attr("style","display:none");
				})
			}
		}
		
		function updatePjxm(){
     		
     		confirmInfo("�ò���<font color=\"blue\">���ᱣ�����޸ĵļ�¼</font>,�Ƿ�����ò�����",function(tag){
	     		
	     		if(tag=="ok"){
		     		defaultXmInfo();
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					var url = "general_pjsz_pjxm_ajax.do?method=updatePjxm";
		          	
				 	//����һ��json����
					var parameter={};
					
					//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
					var hid_obj=jQuery("input",jQuery("#div_submit_value")).each(function(){
						
						//��ȡ���ؼ�name
						var name=jQuery(this).attr("name");
						//����json����
						parameter[name]=escape(jQuery(this).val());
					});
					
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			})
			
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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ��Ŀ����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		
		<div id="input_xmmc_msg" class="hide">
			<div class="prompcon" style="width: 250px">
				<p id="tsxx_span">����Ŀ����<font color="blue">�Ѵ���</font>����¼���������ƣ�����ϵͳ�޷�ʶ��Ŷ~^_^||~</p>
			</div>
		</div>
			
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					<span id="span_jbsz" style="display: none">
					1.��Ŀ����<font color="blue">����¼��</font>����<font color="blue">�����ظ�</font>��<br/>
					2.<font color="blue">ѧ������</font>ֻ����ѧ���û��������룬<font color="blue">��ʦ�ϱ�</font>��ֻ���ɷ�ѧ���û������ϱ���<br/>
					3.��Ŀ�������Ϊ�գ���ǰ��<font color="blue">����ά�� - ��������</font>����ά����<br/>
					4.���ĳЩ��Ŀ<font color="blue">���漰��Ǯ</font>����Ŀ�����Բ�����ά����<br/>
					5.�������Ŀ����Ϊ<font color="blue">�������</font>����ֻҪ�������룬���Զ���ø���Ŀ��<br/>
					6.�������Ŀ����<font color="blue">��Ҫ��������</font>����δ���������ã���ѧ���޷������ͨ������Ŀ��<br/>
					</span>
					
					<span id="span_shlc" style="display: none">
					1.����������Ϊ�գ���ǰ��<font color="blue">ϵͳά�� - ��������ά�� - ��������</font>����ά����<br/>
					2.�������Ʊ�������Ŀ��<font color="blue">����׶�</font>���п��ƣ�������������ѧ���޷��������롣<br/>
					3.����������ָ���Ƹ���Ŀ�Ļ���������ޣ�ϵͳĬ����<font color="blue">���һ�����</font>���п��ơ�<br/>
					4.��ÿ�����ָ����Ŀ����Щ��Ŀ���ɼ�ã�ϵͳĬ����<font color="blue">���һ�����</font>���п��ơ�<br/>
					5.�������ƺͼ�ÿ�����������õ�<font color="blue">����׶�</font>���ƣ������������<font color="blue">����ƽ</font>������ע�⡣<br/>
					6.����Ŀ�ľ����������޺Ͳ��ɼ����Ŀ����<font color="blue">�����Ŀ���ú�</font>���ڹ���������<font color="blue">��Ӧ�Ĺ���</font>�������á�<br/>
					</span>
					
					<span id="span_rssz" style="display: none">
					1.�������Ʒ�Χ��ָ����Ŀ���������޿��Ƶ�Ϊ�༶��������רҵ�����ȣ�Ĭ��Ϊ<font color="blue">�༶����</font>��<br/>
					2.������Ⱥָ����Ŀ�������õ�<font color="blue">����</font>�Ƿ�������Ҫ�󣬱���<font color="blue">��ҵ����������</font>�ȡ�<br/>
					3.������ȺĬ��Ϊ<font color="blue">��</font>��������������Ҫ������ϵ<font color="blue">����Ա</font>��<br/>
					4.������ɺ����ڹ���ҳ�湴ѡ����Ŀ�����<font color="blue">��������</font>���Ը���Ŀ����<font color="blue">������������</font>��<br/>
					</span>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div id="div_submit_value">
				<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
				<input type="hidden" name="str_xmdm" id="hidden_xmdm" value="${xmdm }"/>
				<input type="hidden" name="str_xmmc" id="hidden_xmmc"/>
				<input type="hidden" name="str_xmlx" id="hidden_xmlx"/>
				<input type="hidden" name="str_xmxz" id="hidden_xmxz"/>
				<input type="hidden" name="str_sqfs" id="hidden_sqfs"/>
				<input type="hidden" name="str_xmje" id="hidden_xmje"/>
				<input type="hidden" name="str_xssx" id="hidden_xssx"/>
				<input type="hidden" name="str_sfsh" id="hidden_sfsh"/>
				<input type="hidden" name="str_rssz" id="hidden_rssz"/>
				<input type="hidden" name="str_sfqy" id="hidden_sfqy"/>
				<input type="hidden" name="str_xmsm" id="hidden_xmsm"/>
				<input type="hidden" name="str_lcid" id="hidden_lcid"/>
				<input type="hidden" name="str_rskz" id="hidden_rskz"/>
				<input type="hidden" name="str_jdkz" id="hidden_jdkz"/>
				<input type="hidden" name="str_xmsy" id="hidden_xmsy"/>
				<input type="hidden" name="str_tsrq" id="hidden_tsrq" value="no"/>
				<input type="hidden" name="str_kzfw" id="hidden_kzfw" value="bj"/>
			</div>
			<input type="hidden" id="step" value=""/>
			
			<!-- ������Ŀ����DIV -->
			<div id="div_pjxm" style="width:99%;height:440px;overflow-x:hidden;overflow-y:auto;">
			
			</div>
			<div >
			<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
			<td colspan="2">
			<div class="btn">
			<button type="button"  name="����" id="btn_bc" onclick="updatePjxm();return false;" >�� ��</button>
			<button type="button"  name="�ر�" onclick="Close();return false;">�� ��</button>
			</div>
			</td>
			</tr>
			</tfoot>
			</table>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>