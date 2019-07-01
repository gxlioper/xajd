<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			$("bc").style.display = "none";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			setTimeout('searchRs()','2000');

		}

		//����༭
		function changeEdit(){
			$("edit").value="yes";
			$("bj").style.display = "none";
			$("wh").style.display = "none";
			$("dc").style.display = "none";
			$("dr").style.display = "none";
			$("bc").style.display = "";
			searchRs();
			}

		function changeIsEdit(){
			$("had_edit").value="yes";
		}
		
		function addYjtf(obj){
			var id = obj.id;
			var arr = id.split("_");
			var sjtfId = arr[0]+'_sjtf';
			var sjtf = $(sjtfId).value;
			var yjtf = obj.value;
			var qfId = arr[0]+'_qf';
			if(null==yjtf||yjtf==""){
				obj.value=0;
				yjtf=0;
				}
			$(qfId).value=parseFloat(yjtf)-parseFloat(sjtf);
		}

		function addSjtf(obj){
			var id = obj.id;
			var arr = id.split("_");
			var yjtfId = arr[0]+'_yjtf';
			var yjtf = $(yjtfId).value;
			var sjtf = obj.value;
			var qfId = arr[0]+'_qf';
			if(null==sjtf||sjtf==""){
				obj.value=0;
				sjtf=0;
				}
			$(qfId).value=parseFloat(yjtf)-parseFloat(sjtf);
		}

		//ִ�в�ѯ����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var flag = true;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var first= $("first").value;
			
			var url = "general_dtjs_tyjf_ajax.do?method=searchTyjfResult";
			var ie = "10.0";

			var edit=$("edit").value;
			var otherValue = [ie,edit];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			if(xn_num==1&&flag){
				searchRsByAjax(url,otherValue);
				$("first").value="yes";
				$("had_edit").value="no";
			}else if(first=="yes"&&flag){
				alertInfo("������ֻ��ѡ��һ��ѧ�꣡");
			}
			
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//��ʾ��Ա�ɷѱ���Div
		function showTyjfDiv(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(num == "0"){
				alertError("�빴ѡϣ���������Ա��¼");
				flag = false;
			}

			if(flag){
				tipsWindown("ϵͳ��ʾ","id:div_tyjf","400","250","true","","true","id");
			}		
		}

		//��֤������Ա�ɷ�
		function checkSaveTyjf(){
			//Ӧ���ŷ�
			var yjtf = jQuery("#yjtf").val();		  		
			//ʵ���ŷ�
			var sjtf = jQuery("#sjtf").val();
			
			var flag = true;

			if(parseFloat(sjtf)>parseFloat(yjtf)){
				alertError("ʵ���ŷѲ��ܴ���Ӧ���ŷѣ���ȷ��");
				flag = false;
				}

			if(yjtf == ""){
				alertError("Ӧ���ŷѲ���Ϊ�գ���ȷ��^_^");
				flag = false;
			}

			if(flag){
				confirmInfo("��Ҫ����������ѡ��¼��Ӧ�ɽ����ʵ�ɽ���ȷ��",saveTyjf);
			}
		}

		//������Ա�ɷ�
		function saveTyjf(tag){
			if(tag == "ok"){
				var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
				var RowsStr="";
				if(objs.length>0){
					for (i=0; i<objs.length; i++){
				     RowsStr+=objs[i].value+",";
					}
				}
				//Ӧ���ŷ�
				var yjtf = jQuery("#yjtf").val();		  		
				//ʵ���ŷ�
				var sjtf = jQuery("#sjtf").val();
				//ѧ��
				var xh = new Array();
				//ѧ��
				var xn = new Array();
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_dtjs_tyjf_ajax.do?method=saveTyjf";
				
				//����
			 	var parameter = {
			 		"str_yjtf":yjtf,
			 		"str_sjtf":sjtf,
			 		"str_xh":RowsStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						closeWindown();
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//��֤�༭��Ա�ɷ�
		function CheckSaveBjTyjf(){
			var qf = document.getElementsByName("qf");
			var flag = true;
			var xhs ="";
			for(var i = 0;i<qf.length;i++){
				var qfId="";
				var fy= parseFloat(qf[i].value);
				var xh ="";
				if(fy<0){
					qfId =qf[i].id;
					var arr = qfId.split("_");
					xh = arr[0];
					xhs+=xh+",";
					}
				}if(xhs!=null&&xhs!=""){
					alertError("ѧ��Ϊ"+xhs.substring(0,xhs.length-1)+"Ƿ�Ѳ���Ϊ������");
					$("had_edit").value="no";
					flag = false;
					return false;
					}

			if(flag){
				confirmInfo("��Ҫ���浱ǰҳ���Ӧ�ɽ����ʵ�ɽ���ȷ��",SaveBjTyjf);
			}
		}

		function saveMethod(){
			confirmInfo("���Ѿ��޸�����ط������Ƿ���Ҫ���棿",CheckSaveBj);
		}

		//��֤�༭��Ա�ɷ�
		function CheckSaveBj(tag){
			var qf = document.getElementsByName("qf");
			var flag = true;
			var xhs ="";
			for(var i = 0;i<qf.length;i++){
				var qfId="";
				var fy= parseFloat(qf[i].value);
				var xh ="";
				if(fy<0){
					qfId =qf[i].id;
					var arr = qfId.split("_");
					xh = arr[0];
					xhs+=xh+",";
					}
				}if(xhs!=null&&xhs!=""){
					alertError("ѧ��Ϊ"+xhs.substring(0,xhs.length-1)+"Ƿ�Ѳ���Ϊ������");
					$("had_edit").value="no";
					flag = false;
					return false;
					}

			if(flag&&tag=="ok"){
				SaveBj("ok");
			}else{
				if($("had_edit")){
					$("had_edit").value = "";
				}
				var fanye = $("fanye").value;
				if(fanye=="pre"){
					submitPrePage();
					$("fanye").value="";
					}
				if(fanye=="next"){
					submitNextPage();
					$("fanye").value="";
					}
				if(fanye=="first"){
					submitFirstPage();
					$("fanye").value="";
					}
				if(fanye=="last"){
					submitLastPage();
					$("fanye").value="";
					}
				
			}
		}

		//����
		function SaveBj(tag){
			//Ӧ���ŷ�
			var yjtf = document.getElementsByName("yjtf");
			var yjtfStr="";
			if(yjtf.length>0){
				for (i=0; i<yjtf.length; i++){
					yjtfStr+=yjtf[i].value+",";
				}
			}
			
			//ʵ���ŷ�
			var sjtf = document.getElementsByName("sjtf");
			var sjtfStr="";
			if(sjtf.length>0){
				for (i=0; i<sjtf.length; i++){
					sjtfStr+=sjtf[i].value+",";
				}
			}
			
			//ѧ��
			var xh = document.getElementsByName("primarykey_checkVal");
			var xhStr="";
			if(xh.length>0){
				for (i=0; i<xh.length; i++){
					xhStr+=xh[i].value+",";
				}
			}
			//ѧ��
			var xn = new Array();
			
		if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				var url = "general_dtjs_tyjf_ajax.do?method=saveBjTyjf";
				//����
			 	var parameter = {
			 		"str_yjtf":yjtfStr,
			 		"str_sjtf":sjtfStr,
			 		"str_xh":xhStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						$("edit").value="yes";
						$("had_edit").value="no";
						$("bj").style.display = "none";
						$("wh").style.display = "none";
						$("dc").style.display = "none";
						$("dr").style.display = "none";
						$("bc").style.display = "";
						var fanye = $("fanye").value;
						if(fanye=="pre"){
							submitPrePage();
							$("fanye").value="";
							}
						if(fanye=="next"){
							submitNextPage();
							$("fanye").value="";
							}
						if(fanye=="first"){
							submitFirstPage();
							$("fanye").value="";
							}
						if(fanye=="last"){
							submitLastPage();
							$("fanye").value="";
							}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//����
		function SaveBjTyjf(tag){
			//Ӧ���ŷ�
			var yjtf = document.getElementsByName("yjtf");
			var yjtfStr="";
			if(yjtf.length>0){
				for (i=0; i<yjtf.length; i++){
					yjtfStr+=yjtf[i].value+",";
				}
			}
			
			//ʵ���ŷ�
			var sjtf = document.getElementsByName("sjtf");
			var sjtfStr="";
			if(sjtf.length>0){
				for (i=0; i<sjtf.length; i++){
					sjtfStr+=sjtf[i].value+",";
				}
			}
			
			//ѧ��
			var xh = document.getElementsByName("primarykey_checkVal");
			var xhStr="";
			if(xh.length>0){
				for (i=0; i<xh.length; i++){
					xhStr+=xh[i].value+",";
				}
			}
			//ѧ��
			var xn = new Array();
			
		if(tag == "ok"){
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_dtjs_tyjf_ajax.do?method=saveBjTyjf";
				
				//����
			 	var parameter = {
			 		"str_yjtf":yjtfStr,
			 		"str_sjtf":sjtfStr,
			 		"str_xh":xhStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						$("edit").value="no";
						$("had_edit").value="no";
						$("bj").style.display = "";
						$("wh").style.display = "";
						$("dc").style.display = "";
						$("dr").style.display = "";
						$("bc").style.display = "none";
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
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
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
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
				������Ĭ��չʾ���Ǳ�ѧ����Ա�ɷ����ݡ�</br>
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_dtjs" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="first" id="first" value="no">
			<input type="hidden" name="edit" id="edit" value="no">
			<input type="hidden" name="had_edit" id="had_edit" value="no">
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ begin-->
						<logic:equal name="writeAble" value="yes">
						<li id="bj">
							<a href="#" onclick="changeEdit();return false;" class="btn_xg">
								�༭
							</a>
						</li>
						<li id="wh">
							<a href="#" onclick="showTyjfDiv();return false;" class="btn_ccg">
								ά��
							</a>
						</li>
						<li id="dr">
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
								����
							</a>
						</li>
						</logic:equal>
						<!-- ��дȨ end-->
						<li id="dc" >
							<a href="#" onclick="expData('general_dtjs_tyjf_ajax.do?method=expTyjf');return false;" class="btn_dc">
								����
							</a>
						</li>
						<li id="bc">
							<a href="#" onclick="CheckSaveBjTyjf();return false;" class="btn_zj">
								����
							</a>
						</li>
				
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtjsGeneralForm"></jsp:include>		
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ��Ա�ɷѱ��浯���� begin-->
			<div id="div_tyjf" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ŷ�ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									Ӧ���ŷ�
								</th>
								<td>
									<input type="text" name="str_yjtf" id="yjtf" onblur="checkInputNum(this)"/>
								</td>
							</tr>
							<tr>
								<th>
									ʵ���ŷ�
								</th>
								<td>
									<input type="text" name="str_sjtf" id="sjtf" onblur="checkInputNum(this)"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" onclick="checkSaveTyjf()">
											�� ��
										</button>
										
										<button type="button" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��Ա�ɷѱ��浯���� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>