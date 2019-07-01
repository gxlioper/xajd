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
		//��ѯ�����
		function searchRs(){
	
			var url = "general_wdpj_lssb_ajax.do?method=getXmsbXsList";
			var xmdm = $("xmdm").value;
			var bjdm = $("bjdm").value;
			
			var search_condition = jQuery("#search_condition").val();
			var arrange = jQuery("#arrange").val();
			var fashion = jQuery("#fashion").val();
			
			if(arrange == ""){
				arrange = "no";
			}
			
			var otherValue = [xmdm,bjdm,arrange,fashion,search_condition];

			jQuery("#div_rs").html("");
			searchRsByAjax(url,otherValue);
		}		

		//������ط���
		function saveXmsb(tag){

			if(tag=='ok'){
				var xmdm = $("xmdm").value;
				var num =  document.getElementsByName("checkVal").length;
				
				var xh = new Array();
				var n=0;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("checkVal")[i];
					if(obj.checked && obj.disabled != true){
						xh[n] = obj.value;
						n++;
					}
				}
				
				if(checkPjtj(xh,xmdm)){
				
					var url="general_wdpj_lssb_ajax.do?method=saveXmsb";
	
					//����
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"xh":xh.join(" !!@@!! ")
					};
		
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
						
					jQuery.post(url,parameter,function(result){
						ymPrompt.alert({message:result,winPos:"t"});
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					});
				}
			}
		}
		
		//ִ���ϱ�
		function doSave(){
		
			var xh = new Array();
			var num =  document.getElementsByName("checkVal").length;
			
			var n=0;
				
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("checkVal")[i];
				if(obj.checked && obj.disabled != true){
					xh[n] = obj.value;
					n++;
				}
			}
				
			if(n==0){
				ymPrompt.errorInfo({message:"�����ٹ�ѡһ��ϣ���ϱ���ѧ��",winPos:"t"});
				return false;
			}
			
			ymPrompt.confirmInfo({message:"��ȷ���Ƿ��ϱ�������ѡ��ѧ��?",winPos:"t",title:"ȷ������",handler:saveXmsb});
		}

		//��ʾ�ɼ���Ϣ
		function showCjInfo(xh){
			jQuery("#hidden_xh").val(xh);
			defaultXscj();
			tipsWindown("�ɼ��鿴","id:div_xscj","350","350","true","","true","id");
		}

		//��ʼ��ѧ���ɼ�
		function defaultXscj(){
			
			jQuery.ajaxSetup({async:false});

			//ѧ��
			var xh = jQuery("#hidden_xh").val();
			//·��
			var url = "pjpyXmsb.do?method=defaultXscj";
			//����
		 	var parameter = {
				"xh":xh
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery("#div_xscj_info").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);

			jQuery.ajaxSetup({async:true});
		}

		//����ѧ��������������
		function searchXsByTj(){
			var search_condition = jQuery("#search_condition").val();
			if(search_condition == "yes"){
				jQuery("#search_condition").val("no");
				jQuery("#btn_cx").html("����ѧ��(��������)");
				searchRs();
			}else{
				confirmInfo("��Ҫ���������������˳�����������ѧ�������ܻ�ִ�нϳ�ʱ�䣬�����Ƿ����?��",doSearchXsByTj);	
			}	
		}

		//ִ�й���
		function doSearchXsByTj(tag){
			if(tag == "ok"){
				jQuery("#search_condition").val("yes");
				jQuery("#btn_cx").html("ȡ������");
				searchRs();
			}
		}
		
		function checkPjtj(xhArr,xmdm){
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join(" !!@@!! ");
			
			parameter["str_xmdm"]=xmdm;
			
			//����URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xmsz_pjtj_ajax.do?method=checkPjtj";
			
			var flag=false;
			
			//------------�����ж� begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!=""){
						alertInfo(result);
						flag=false;
					}else{
						flag=true;
					}
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  flag;
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ��ʦ�ϱ�</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
			
		</div>
		<!-- ���� end-->
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk"
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none;">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>�ҵ�����</p>
						</a>   	
					</div>
					
				    <div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
							<p>ѧ������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>�����ѯ</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.ѧ����ʾ˳������ۺ�����<font color="blue">�༶����</font>��˳�����С�</br>
				2.����ִ��<font color="blue">����ѧ��</font>�������˵�������������ѧ����</br>
				3.��������������ù��࣬���ܵ��¹����ٶȽ����������ĵȴ���
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/pjpyXmsb">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value="${bjdm }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="hidden_xh" id="hidden_xh"/>
			<input type="hidden" name="search_condition" id="search_condition" value="no"/>
			<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;" style="display: none"> �� ѯ </button>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="doSave();return false;"
								class="btn_ccg">
								ȷ���ϱ�
							</a>
						</li>
						<li>
							<a href="#" id="btn_down"
								onclick="showTopWin('/xgxt/general_pjpy.do?method=lssbFwChoose',600,550);return false;"
								class="btn_down">
								�����ϱ���Ŀ
							</a>
						</li>
						<li>
							<a href="#" id="btn_cx"
								onclick="searchXsByTj();return false;"
								class="btn_cx">
								����ѧ��(��������)
							</a>
						</li>	
					</ul>
				</div>
				<!-- ��ť end-->
							
				<!-- �������� -->
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<table align="center" width="100%">
					<tr style="">
						<td width="100%" valign="top" style="">
							<div id="div_rs" style="">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- ������ʾ����ʼ end-->	
			
			<!-- �ɼ���ʾ������ -->
			<div id="div_xscj" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>${pjxn }ѧ��ɼ�</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2" width="">
									<div id="div_xscj_info" style="width:100%;height:230px;overflow-x:hidden;overflow-y:auto;">
									
									</div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">								
										<button type="button" onclick="closeWindown();return false;">
											��  ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �ɼ���ʾ������ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>