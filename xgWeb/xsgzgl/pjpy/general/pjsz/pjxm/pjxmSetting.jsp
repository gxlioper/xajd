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
			defaultPjxmSetting();
			
			if($("xmmc")){
				$("xmmc").focus();
			}
		}

		//��ʼ��������Ŀ����
		function defaultPjxmSetting(){
			
			jQuery.ajaxSetup({async:false});

			//����
			var step = jQuery("#step").val();
			//·��
			var url = "general_pjsz_pjxm_ajax.do?method=defaultPjxmSetting";
			//����
		 	var parameter = {
				"step":step
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
			
			jQuery.ajaxSetup({async:true});

			defaultHelpMessage();
		}

		//��ʼ��������Ϣ
		function defaultHelpMessage(){
			var step = jQuery("#step").val();
			
			if(step == "shlcsz"){//���
				
				$("span_jbsz").style.display="none";
				$("span_shlc").style.display="";
				$("span_rssz").style.display="none";
				
			}else if(step == "rskzsz"){//����
				$("span_jbsz").style.display="none";
				$("span_shlc").style.display="none";
				$("span_rssz").style.display="";
			}else{
				$("span_jbsz").style.display="";
				$("span_shlc").style.display="none";
				$("span_rssz").style.display="none";
			}
		}
		
		//��һ��
		function previousStep(previousStep){
		
			if(previousStep == "shlcsz"){//������������ö���
				jQuery("#step").val("");
				defaultPjxmSetting();
				defaultXmInfo(); 
			}else if(previousStep == "rskzsz"){//���������ö���

				//�ж��Ƿ���Ҫ���
				var sfsh = jQuery("#hidden_sfsh").val();
				if(sfsh == "yes"){
					jQuery("#step").val("shlcsz");
				}else{
					jQuery("#step").val("");
				}
				
				defaultPjxmSetting();
				defaultXmInfo();
			}
			checkBtn();
		}
		
		//��һ��
		function nextStep(nextStep){
			
			var step = jQuery("#step").val();
			//�Ƿ����
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//��������
			var rssz = jQuery("input[name=rssz]:checked").val();
			//�Ƿ�����
			var sfqy = jQuery("input[name=sfqy]:checked").val();

			if(step == ""){//�������������ö���
				//��Ŀ����
				var xmmc = jQuery("#xmmc").val();
				jQuery("#hidden_xmmc").val(xmmc);
				//��Ŀ����
				var xmlx = jQuery("input[name=xmlx]:checked").val();
				jQuery("#hidden_xmlx").val(xmlx);
				//��Ŀ����
				var xmxz = jQuery("#xmxz").val();
				jQuery("#hidden_xmxz").val(xmxz);
				//���뷽ʽ
				var sqfs = jQuery("input[name=sqfs]:checked").val();
				jQuery("#hidden_sqfs").val(sqfs);
				//��Ŀ���
				var xmje = jQuery("#xmje").val();
				jQuery("#hidden_xmje").val(xmje);
				//��ʾ˳��
				var xssx = jQuery("#xssx").val();
				jQuery("#hidden_xssx").val(xssx);
				//��Ŀ˵��
				var xmsm = jQuery("#xmsm").val();
				jQuery("#hidden_xmsm").val(xmsm);
				//�Ƿ����
				sfsh = jQuery("input[name=sfsh]:checked").val();
				jQuery("#hidden_sfsh").val(sfsh);
				//��������
				rssz = jQuery("input[name=rssz]:checked").val();
				jQuery("#hidden_rssz").val(rssz);
				//�Ƿ�����
				sfqy = jQuery("input[name=sfqy]:checked").val();
				jQuery("#hidden_sfqy").val(sfqy);
				
				jQuery("#step").val("pjjbsz");
			}

			if(nextStep == ""){
		
				if(sfsh == "yes" && rssz =="yes"){
					//�����������
					jQuery("#step").val("shlcsz");
					defaultPjxmSetting();
					if($("div_shgw")){
						var lc_num = jQuery("input[type=radio][name=lcid]").length;
						if(lc_num !=0){
							var obj = jQuery("input[type=radio][name=lcid]")[0];
							obj.checked = true;
							clickShlc(obj.value);
						}
					}
				}else if(sfsh == "yes" && rssz == "no"){
					//�����������
					jQuery("#step").val("shlcsz");
					defaultPjxmSetting();
					if($("div_shgw")){
						var lc_num = jQuery("input[type=radio][name=lcid]").length;
						if(lc_num !=0){
							var obj = jQuery("input[type=radio][name=lcid]")[0];
							obj.checked = true;
							clickShlc(obj.value);
						}
					}
					$("btn_bc").style.display="";
					$("btn_next").style.display="none";
				}else{
					jQuery("#step").val("rskzsz");
					jQuery("#hidden_rskz").val("sqsb");
					jQuery("#hidden_jdkz").val("sqsb");
					jQuery("#hidden_xmsy").val("sqsb");
					defaultPjxmSetting();
				}
			}else if (nextStep == "shlcsz"){
				//�����������
				jQuery("#step").val("rskzsz");
				//����ID
				var lcid = jQuery("input[name=lcid]:checked").val();
				var	rskz = jQuery("input[name=rskz]:checked").val();
				var	jdkz = jQuery("input[name=jdkz]:checked").val();
								
				if(lcid !="" && lcid!=null){
					jQuery("#hidden_lcid").val(lcid);
					jQuery("#hidden_jdkz").val(jdkz);
					jQuery("#hidden_rskz").val(rskz);
				}
				
				defaultPjxmSetting();
				defaultXmInfo();

				//������Ⱥ
				var tsrq = jQuery("#hidden_tsrq").val();
				var i=0;
				jQuery("input[name=tsrq]").each(function(){
					if(jQuery(this).val() == tsrq){
						jQuery(this).attr("checked",true);
					}
					i++;
				});	
			}		
		}

		//����������
		function clickShlc(lcid){

			var rssz = jQuery("#hidden_rssz").val();
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
			var xmmc = jQuery("#hidden_xmmc").val();
			jQuery("#xmmc").val(xmmc);
			//��Ŀ����
			var xmlx = jQuery("#hidden_xmlx").val();
			jQuery("input[name=xmlx][value="+xmlx+"]").attr("checked",true);
			//��Ŀ����
			var xmxz = jQuery("#hidden_xmxz").val();
			jQuery("#xmxz").val(xmxz);
			//���뷽ʽ
			var sqfs = jQuery("#hidden_sqfs").val();
			jQuery("input[name=sqfs][value="+sqfs+"]").attr("checked",true);
			//��Ŀ���
			var xmje = jQuery("#hidden_xmje").val();
			jQuery("#xmje").val(xmje);
			//��ʾ˳��
			var xssx = jQuery("#hidden_xssx").val();
			jQuery("#xssx").val(xssx);
			//��Ŀ˵��
			var xmsm = jQuery("#hidden_xmsm").val();
			jQuery("#xmsm").val(xmsm);
			//�Ƿ����
			var sfsh = jQuery("#hidden_sfsh").val();
			jQuery("input[name=sfsh][value="+sfsh+"]").attr("checked",true);
			//��������
			var rssz = jQuery("#hidden_rssz").val();
			jQuery("input[name=rssz][value="+rssz+"]").attr("checked",true);
			//�Ƿ�����
			var sfqy = jQuery("#hidden_sfqy").val();
			jQuery("input[name=sfqy][value="+sfqy+"]").attr("checked",true);
			//����ID
			var lcid = jQuery("#hidden_lcid").val();
			jQuery("input[name=lcid][value="+lcid+"]").attr("checked",true);
			clickShlc(lcid);
			//���Ʒ�Χ
			var kzfw = jQuery("#hidden_kzfw").val();
			jQuery("input[name=kzfw][value="+kzfw+"]").attr("checked",true);

			jQuery.ajaxSetup({async:true});
		}

		//��Ᵽ��������Ŀ
		function checkSavePjxm(){
			
			//����ID
			var lcid = jQuery("input[name=lcid]:checked").val();
			var	rskz = jQuery("input[name=rskz]:checked").val();
			var	jdkz = jQuery("input[name=jdkz]:checked").val();
							
			if(lcid !="" && lcid!=null){
				jQuery("#hidden_lcid").val(lcid);
				jQuery("#hidden_jdkz").val(jdkz);
				jQuery("#hidden_rskz").val(rskz);
			}
			
			confirmInfo('��Ҫִ�б������������ȷ��',savePjxm);
		}
		
		//����������Ŀ
		function savePjxm(tag){
			
			copyValue();
			
			if(tag == "ok"){
				//��Ŀ����
				var xmmc = jQuery("#hidden_xmmc").val().trim();
				//��Ŀ����
				var xmlx = jQuery("#hidden_xmlx").val();
				//��Ŀ����
				var xmxz = jQuery("#hidden_xmxz").val();
				//���뷽ʽ
				var sqfs = jQuery("#hidden_sqfs").val();
				//��Ŀ���
				var xmje = jQuery("#hidden_xmje").val();
				//��ʾ˳��
				var xssx = jQuery("#hidden_xssx").val();
				//��Ŀ˵��
				var xmsm = jQuery("#hidden_xmsm").val();
				//�Ƿ����
				var sfsh = jQuery("#hidden_sfsh").val();
				//��������
				var rssz = jQuery("#hidden_rssz").val();
				//�Ƿ�����
				var sfqy = jQuery("#hidden_sfqy").val();
				//����ID
				var lcid = jQuery("#hidden_lcid").val();
				//���Ʒ�Χ
				var kzfw = jQuery("#hidden_kzfw").val();
				//��������
				var rskz = jQuery("#hidden_rskz").val();
				//��ÿ���
				var jdkz = jQuery("#hidden_jdkz").val();
				//��Ŀ˳��
				var xmsy = jQuery("#hidden_xmsy").val();

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
		function checkXmmc(xmmcTwo){
			var xmmc = xmmcTwo.trim();
			
			if(xmmc!=""){
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
							hideMsgDiv('input_mhcx_msg');
						}else{
							$("btn_bc").disabled=true;
							$("btn_next").disabled=true;
							displayMsgDiv('input_mhcx_msg');
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
					$("btn_bc").disabled=false;
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
					$("btn_next").disabled=false;
				}
			}
		}
		
		function copyValue(){
			
			var step = jQuery("#step").val();
			//�Ƿ����
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//��������
			var rssz = jQuery("input[name=rssz]:checked").val();
			//�Ƿ�����
			var sfqy = jQuery("input[name=sfqy]:checked").val();

			if(step == ""){//�������������ö���
				//��Ŀ����
				var xmmc = jQuery("#xmmc").val();
				jQuery("#hidden_xmmc").val(xmmc);
				//��Ŀ����
				var xmlx = jQuery("input[name=xmlx]:checked").val();
				jQuery("#hidden_xmlx").val(xmlx);
				//��Ŀ����
				var xmxz = jQuery("#xmxz").val();
				jQuery("#hidden_xmxz").val(xmxz);
				//���뷽ʽ
				var sqfs = jQuery("input[name=sqfs]:checked").val();
				jQuery("#hidden_sqfs").val(sqfs);
				//��Ŀ���
				var xmje = jQuery("#xmje").val();
				jQuery("#hidden_xmje").val(xmje);
				//��ʾ˳��
				var xssx = jQuery("#xssx").val();
				jQuery("#hidden_xssx").val(xssx);
				//��Ŀ˵��
				var xmsm = jQuery("#xmsm").val();
				jQuery("#hidden_xmsm").val(xmsm);
				//�Ƿ����
				sfsh = jQuery("input[name=sfsh]:checked").val();
				jQuery("#hidden_sfsh").val(sfsh);
				//��������
				rssz = jQuery("input[name=rssz]:checked").val();
				jQuery("#hidden_rssz").val(rssz);
				//�Ƿ�����
				sfqy = jQuery("input[name=sfqy]:checked").val();
				jQuery("#hidden_sfqy").val(sfqy);
				
				jQuery("#step").val("pjjbsz");
			}
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
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
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
				<input type="hidden" name="str_xmmc" id="hidden_xmmc"/>
				<input type="hidden" name="str_xmlx" id="hidden_xmlx"/>
				<input type="hidden" name="str_xmxz" id="hidden_xmxz"/>
				<input type="hidden" name="str_sqfs" id="hidden_sqfs"/>
				<input type="hidden" name="str_xmje" id="hidden_xmje"/>
				<input type="hidden" name="str_xssx" id="hidden_xssx"/>
				<input type="hidden" name="str_sfsh" id="hidden_sfsh"/>
				<input type="hidden" name="str_rssz" id="hidden_rssz"/>
				<input type="hidden" name="str_sfqy" id="hidden_sfqy" value="yes"/>
				<input type="hidden" name="str_xmsm" id="hidden_xmsm"/>
				<input type="hidden" name="str_lcid" id="hidden_lcid"/>
				<input type="hidden" name="str_rskz" id="hidden_rskz"/>
				<input type="hidden" name="str_jdkz" id="hidden_jdkz"/>
				<input type="hidden" name="str_xmsy" id="hidden_xmsy"/>
				<input type="hidden" name="str_tsrq" id="hidden_tsrq" value="no"/>
			
				<logic:equal name="useCpz" value="yes">
					<input type="hidden" name="str_kzfw" id="hidden_kzfw" value="cpz"/>
				</logic:equal>
				<logic:equal name="useCpz" value="no">
					<input type="hidden" name="str_kzfw" id="hidden_kzfw" value="bj"/>
				</logic:equal>
			</div>
			<input type="hidden" id="step" value=""/>
			
			<!-- ������Ŀ����DIV -->
			<div id="div_pjxm" style="width:100%;height:370px;overflow-x:hidden;overflow-y:auto;">
			
			</div>
			
			<div id="input_mhcx_msg" class="hide"
				style="left: 160px;top: 85px;">
				<div class="prompcon" style="width: 250px">
					<p id="tsxx_span">����Ŀ����<font color="blue">�Ѵ���</font>����¼���������ƣ�����ϵͳ�޷�ʶ��Ŷ~^_^||~</p>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>