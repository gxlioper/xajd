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
		
		jQuery(function(){
		
			var operation='${operation}';
			
			if(operation=="no"){
				jQuery(".buttonbox").children().find("li").each(function(){
					jQuery(this).attr("disabled",true);
				});
			}
			

			onShow();

		});
		
		//��ʼ��
		function onShow(){ 
		
			
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}else{
				$("czxm").value = 'zd1';
				$("czxm").value = 'zd1';
				searchRs();
			}
			
			
		}

		function initBtn(){
		
			if($("li_bc") && $("li_tb")){
				
				if($("hid_lrf")){
					if($("hid_lrf").value=="no"){
						$("a_btn_bc").disabled=true;
					}else{
						$("a_btn_bc").disabled="";
					}
				}
				
				if($("hid_lyf")){
					if($("hid_lyf").value=="no"){
						$("a_btn_tb").disabled=true;
					}else{
						$("a_btn_tb").disabled="";
					}
				}
				
			}
		}


		//��ѯ�����
		function searchRs(){

			//��ť����
			if(checkHadEdit()){
				//controlBtn();
				var url = "general_zhcp_ajax.do?method=searchZhcpZcxx";
				var xmdm = $("shxm").value;
				var xmmc = escape($("xmmc_"+xmdm).value);
				var lyb = escape($("lyb_"+xmdm).value);
				var xmjb = escape($("xmjb_"+xmdm).value);
				var ie = "ie";
	
				var otherValue = [ie,xmdm,xmmc,lyb,xmjb];
	
				//showWaitingDiv("1000");
				
	<%--			if(checkSearch()){--%>
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
				
				setTimeout("initBtn()","1500");
	<%--			}--%>
			}
		}
		
		//����Ƿ��޸�
		function saveMethod(){
			confirmInfo('���Ѿ��޸�����ط����������Ƿ���Ҫ���棿',save);
		}


		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				
				if(xn_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}
			}
			return flag;
		}
		
		//��ʾ�ֶ��޸�
		function showEditDiv(zd,zdid){
			
			var url="general_zhcp_ajax.do?method=showZdxgDiv";
			
			//��������
		 	var parameter = {
				"zd":zd,
				"zdid":zdid
			};
		  	
			jQuery("#div_zdxg").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				jQuery("#"+zd+"_id").val(jQuery("#"+zdid).val());
				
				tipsWindown("ϵͳ��ʾ","id:div_zdxg","350","250","true","","true","id");
			});
		}
		
		
		function affirmValue(zd,zdid){
			jQuery("#"+zdid).val(jQuery("#"+zd+"_id").val());
			closeWindown();
		}
		
		//�����ۺϲ�����Ϣ
		function saveZhcpInfo(){
			
			confirmInfo("�ò���������������Ϣ���Ƿ�ȷ������������",save);
			
		}
		
		function save(tag){
			if(tag=="ok"){
				var xmdm=jQuery("#shxm").val();
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				//������չ�ֶ�����
				jQuery("input[name=kzzdArr]").each(function(i){
						// ��ȡ��չ�ֶ������ֵ
						var kzzd=jQuery(this).val();
						
						// ����һ�����������Ϣ������;
						var array=new Array();
						
						jQuery("input[name="+kzzd+"],select[name="+kzzd+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//������չ�ֶδ���JSON����
						parameter[kzzd]=array.join(" !!@@!! ");
						
				});
				
				//�����۲�����
				jQuery("input[name=bczdArr]").each(function(i){
				
						// ��ȡ��չ�ֶ������ֵ
						var zczx=jQuery(this).val();
						
						// ����һ�����������Ϣ������;
						var array=new Array();
						
						jQuery("input[name="+zczx+"],select[name="+zczx+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//�����۲��������JSON����
						parameter[zczx]=array.join(" !!@@!! ");
						
				});
					
					
					
				jQuery("[name=div_pkValue]").each(function(i){
							
					pkValue[i] =escape(jQuery(this).val());
							
				});
				
				
				var url = "general_zhcp_ajax.do?method=saveZhcpInfo";
	          	 
			 	 parameter["xmdm"]=xmdm;
				 parameter["pkValue"]=pkValue.join("!!@@!!");
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(tag){
						
							if(tag=="ok"){
						
								closeWindown();	
								searchRs();
								
							}
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
			$("had_edit").value="no";
		}
		
		//ͬ����������
		function updateLybInfo(){
			
			confirmInfo("�ò�������ͬ��������Դ���ݣ��Ƿ�ȷ������������",function(tag){
				if(tag=="ok"){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
				
					setTimeout("tbcz()","1000")
				}
			});
			
		}
		
		function tbcz(){
		
			var xmdm=jQuery("#shxm").val();
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					var url = "general_zhcp_ajax.do?method=updateLybInfo";
		          	 
				 	 parameter["xmdm"]=xmdm;
				
		
				 	
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result,function(tag){
							
								if(tag=="ok"){
							
									closeWindown();	
									searchRs();
									
								}
							});
							
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		</script>
	</head>
	<body >

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

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1.������Ĭ��չʾ����<font color="blue">����������</font>����Ϣ�� <br/>
					2.<font color="blue">����</font>���ܣ����޸ĵ��۲����Ϣ���б�������� <br/>
					3.<font color="blue">ͬ��</font>���ܣ�ͬ��������Դ���ݵ��۲��С�<br/>
					4.�����ѯ���Ϊ<font color="blue">��</font>��������<font color="blue">������Ա</font>δ���ã�����ϵ����Ա��<br/>
					5.����۲���ĿΪ<font color="blue">��</font>��������<font color="blue">�۲���Ŀ</font>δ���ã�����ϵ����Ա��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/general_pjpy" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �Ƿ��ʼ��  -->
			<input type="hidden" name="had_edit" id="had_edit" value="no" />
			<input type="hidden" name="is_default" id="is_default" value="" />
			<input type="hidden" name="shxm" id="shxm" value="${czxm }" />
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }" />
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }" />
			<input type="hidden" name="operation" id="operation" value="${operation }" />
			<logic:empty name="cshXmList">
				<input type="hidden" name="xmmc_zd1" id="xmmc_zd1" value="${xmmc}" />
				<input type="hidden" name="lyb_zd1" id="lyb_zd1"  value="${lyb}" />
				<input type="hidden" name="xmjb_zd1" id="xmjb_zd1"   value="${xmjb}"/>
			</logic:empty>
			<!-- ������ end-->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									��������
								</a>
							</li>
						</logic:equal>
						<!-- ҳ����Դ end-->
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<!-- �ɷ�ִ�� -->
							<li id="li_bc" >
								<a href="#" id="a_btn_bc" disabled="true" onclick="if(checkItsDis(this)){saveZhcpInfo();}return false;" class="btn_yl">
									�� �� </a>
							</li>
							<li id="li_tb"  >
								<a href="#" id="a_btn_tb" disabled="true" onclick="if(checkItsDis(this)){updateLybInfo();}return false;" class="btn_sx">
									ͬ �� </a>
							</li>
							<!-- �ɷ�ִ�� end-->
						</logic:equal>
						<!-- ��дȨ end-->
					</ul>
				</div>
				</logic:equal>
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
				<logic:notEmpty name="cshXmList">
					<tr>
						<table align="center" width="100%">
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}"
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc} </a>
													<input type="hidden" id="xmdm_${index }"
														value="${xmnr.xmdm}" />
													<input type="hidden" id="xmmc_${xmnr.xmdm}"
														value="${xmnr.xmmc}" />
													<input type="hidden" id="xmjb_${xmnr.xmdm}"
														value="${xmnr.xmjb}" />
													<input type="hidden" id="lyb_${xmnr.xmdm}"
														value="${xmnr.lyb}" />
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						</logic:notEmpty>
						<td width="85%" valign="top" style="position: relative;">
							<logic:empty name="cshXmList">	
								<script language="javascript" defer="defer">			
								jQuery(function(){
									$("div_rs").style.width="100%";
								});
								</script>
							</logic:empty>
							<div id="div_rs"
								style="width:680px;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->

			<!-- �ֶ��޸ĵ����� -->
			<div id="div_zdxg" style="display:none">
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
