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
			//
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_cpxz_ajax.do?method=searchPjszCpxz";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//�����ʾ������Div
		function checkShowCpzDiv(){
			jQuery("#cpzmc").val("");
			tipsWindown("ϵͳ��ʾ","id:div_cpxz","400","300","true","","true","id");
		}
		
		//��Ᵽ�����С������
		function checkSaveCpxz(){
			var cpzsz = jQuery("#cpzsz_check").val();
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

			if(num == 0){//����
				//�꼶
				var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
				//ѧԺ
				var xy_num = jQuery("a[name=a_name_xy]").length; 		
				//רҵ
				var zy_num = jQuery("a[name=a_name_zy]").length; 
				//�༶
				var bj_num = jQuery("a[name=a_name_bj]").length; 
				
				if(cpzsz == "new"){	
					var cpzmc = jQuery("#cpzmc").val();//����������
					if(cpzmc == ""){
						alertError("���������Ʋ���Ϊ�գ�����ȷ��^_^!!");
						return false;
					}
	
					var obj = $("select_cpzmc");
					for(i=0;i<obj.options.length;i++){
						var mc = obj.options[i].value;
						if(mc == cpzmc){
							alertError("������Ĳ����������Ѵ��ڣ��뻻һ��^_^!!");
							return false;
						}
					}
				}
					
				saveCpxzNoChecked("ok");
				
			}else{//��ѡ
				if(cpzsz == "new"){
					var cpzmc = jQuery("#cpzmc").val();//����������
					if(cpzmc == ""){
						alertError("���������Ʋ���Ϊ�գ�����ȷ��^_^!!");
						return false;
					}
	
					var obj = $("select_cpzmc");
					for(i=0;i<obj.options.length;i++){
						var mc = obj.options[i].value;
						if(mc == cpzmc){
							alertError("������Ĳ����������Ѵ��ڣ��뻻һ��^_^!!");
							return false;
						}
					}
					saveCpxzChecked("ok");
				}else{
					saveCpxzChecked("ok");
				}
			}
		}

		//�������С�����ã�δѡ�У�
		function saveCpxzNoChecked(tag){
			if(tag == "ok"){

				jQuery.ajaxSetup({async:false});

				//�꼶
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//ѧԺ
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});

				//רҵ
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});

				//�༶
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});
				
				var cpzsz = jQuery("#cpzsz_check").val();
				
				var cpzmc = "";//����������

				if(cpzsz == "new"){
					cpzmc = jQuery("#cpzmc").val();
				}else{
					cpzmc = jQuery("#select_cpzmc").val();
				}

				var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzNoChecked";
				
				//����
			 	var parameter = {
			 		"cpzmc":escape(cpzmc),
			 		"nj":nj.join("!!@@!!"),
			 		"xy":xy.join("!!@@!!"),
			 		"zy":zy.join("!!@@!!"),
			 		"bj":bj.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						if(cpzsz == "new"){
							var html = "<option value=\""+cpzmc+"\">"+cpzmc+"</option>";
							jQuery("#select_cpzmc").append(html);
							tempHTML = jQuery("#windown-content").html();
						}
						searchRs();
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//�������С�����ã�ѡ�У�
		function saveCpxzChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var bjdm = new Array();//�༶����
				var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
				var count = 0;
				for(var i=0;i<bjdm_num;i++){
					var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
					bjdm[count] = obj.value;
					count++;
				}

				var cpzsz = jQuery("#cpzsz_check").val();
				
				var cpzmc = "";//����������

				if(cpzsz == "new"){
					cpzmc = jQuery("#cpzmc").val();
				}else{
					cpzmc = jQuery("#select_cpzmc").val();
				}
					
				var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzChecked";
				
				//����
			 	var parameter = {
			 		"cpzmc":escape(cpzmc),
			 		"bjdm":bjdm.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						if(cpzsz == "new"){
							var html = "<option value=\""+cpzmc+"\">"+cpzmc+"</option>";
							jQuery("#select_cpzmc").append(html);
							tempHTML = jQuery("#windown-content").html();
						}
						searchRs();
						closeWindown();	
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}

		//���ɾ������������
		function checkDeleteCpzsz(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

			if(num == 0){//����
				confirmInfo("����ȷ���Ƿ�ȡ��<font color='blue'>����������ָ���༶</font>�Ĳ���������",deleteCpxzNoChecked);
			}else{
				confirmInfo('����ȷ���Ƿ�ȡ������ѡ�༶�Ĳ���������',deleteCpxzChecked);
			}
		}

		//ɾ������С�����ã�ѡ�У�
		function deleteCpxzChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var bjdm = new Array();//�༶����
				var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
				var count = 0;
				for(var i=0;i<bjdm_num;i++){
					var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
					bjdm[count] = obj.value;
					count++;
				}
					
				var url = "general_pjsz_cpxz_ajax.do?method=deleteCpxzChecked";
				
				//����
			 	var parameter = {
			 		"bjdm":bjdm.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						goPjszCpxz();
						closeWindown();	
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}

		//ȡ������С�����ã�δѡ�У�
		function deleteCpxzNoChecked(tag){
			if(tag == "ok"){

				jQuery.ajaxSetup({async:false});

				//�꼶
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//ѧԺ
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});

				//רҵ
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});

				//�༶
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});

				var url = "general_pjsz_cpxz_ajax.do?method=deleteCpxzNoChecked";
				
				//����
			 	var parameter = {
			 		"nj":nj.join("!!@@!!"),
			 		"xy":xy.join("!!@@!!"),
			 		"zy":zy.join("!!@@!!"),
			 		"bj":bj.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						goPjszCpxz();
						closeWindown();	
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
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ����С������</a>
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
				<span> 1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br> </span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									�������� </a>
							</li>
						</logic:equal>
						<!-- ҳ����Դend -->
						<li>
							<a href="#" onclick="showCpxzZdszDiv();return false;"
								class="btn_sz"> �Զ����ò����� </a>
						</li>
						<li>
							<a href="#" onclick="checkShowCpzDiv();return false;"
								class="btn_ccg"> ���ò����� </a>
						</li>
						<li>
							<a href="#" onclick="checkDeleteCpzsz();return false;"
								class="btn_sc"> ȡ�������� </a>
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
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div id="div_rs"
					style="height: 380px; overflow-x: auto; overflow-y: auto;">
				</div>

				<!--��ҳ��ʾ-->
				<div style="clear: both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->

			<!-- ����С�����ã���ѡ��¼�������� -->
			<div id="div_cpxz" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����С������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									����������
								</th>
								<td width="">
									<input type="radio" name="cpzsz" id="cpzsz_new" value="yes"
										onclick="setCheckedValue(this);$('tr_new').style.display='';$('tr_old').style.display='none'"
										checked="checked" />
									�µĲ���С��
									<br />
									<input type="radio" name="cpzsz" id="cpzsz_old" value="no"
										onclick="setCheckedValue(this);$('tr_new').style.display='none';$('tr_old').style.display=''" />
									���еĲ���С��
									<input type="hidden" id="cpzsz_check" value="new" />
								</td>
							</tr>
							<tr id="tr_new">
								<th width="30%">
									����������
								</th>
								<td width="">
									<input type="text" id="cpzmc" maxlength="20" />
								</td>
							</tr>
							<tr id="tr_old" style="display: none">
								<th width="30%">
									����������
								</th>
								<td width="">
									<select id="select_cpzmc">
										<logic:notEmpty name="cpzList">
											<logic:iterate name="cpzList" id="cpz">
												<option value="${cpz.mc }">
													${cpz.mc }
												</option>
											</logic:iterate>
										</logic:notEmpty>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">

									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveCpxz();return false;">
											ȷ ��
										</button>

										<button type="button"  onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ����С�����õ����� end-->

			<!-- �Զ����õ����� -->
			<div id="div_cpxz_zdsz" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�Զ����ù���ѡ��(����޲�����İ༶)</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="nj" />
									���꼶Ϊ��λ���ֲ�����
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="njxy" />
									���꼶+<bean:message key="lable.xb" />Ϊ��λ���ֲ�����
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="xy" />
									��<bean:message key="lable.xb" />Ϊ��λ���ֲ�����
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="njzy" />
									���꼶+רҵΪ��λ���ֲ�����
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="bj" checked="checked" />
									�԰༶Ϊ��λ���ֲ�����
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">

									</div>
									<div class="btn">
										<button type="button"  onclick="saveCpxzZdsz();return false;">
											ȷ ��
										</button>

										<button type="button"  onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �Զ����õ�����  -->

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>