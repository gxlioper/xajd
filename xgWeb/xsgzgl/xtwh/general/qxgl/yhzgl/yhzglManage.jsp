<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 		
			searchRs();
		}
		function teshuzifu(str)
		{
			//����������ַ�����
			var str_ne =jQuery(str).val();
			var pat="[`~!@#\$%\^&\-\*\(\)_\+<>\?:\"{},\.\/;'\[\\]]";
		    var pattern = new RegExp(pat);
		    //  
		    if(pattern.test(str_ne)||str_ne=='-'){  
		    	jQuery(str).val(str_ne.replaceAll(pat,'').replaceAll('-','')); 
		        alert("�������������ַ�");   
		    }     
		    
		}
		//��ѯ�����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function addYhzxx(){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxAdd";
			var zmc = jQuery("#zmc_add").val();
			if (trim(zmc) == "") {
				showAlert("�����Ʋ���Ϊ�գ�");
				jQuery("#zmc_add").focus();
				return false;
			}
			var parameter={"str_zmc":escape(zmc)};
			jQuery.post(url,parameter,function(result){
				if(result == "exist"){
					showAlert("�û��������Ѵ��ڣ�");
					jQuery("#zmc_add").focus();
					return false;
				}else{
					alertInfo(result+"��");
					hiddenMessage(true,true);
					searchRs();
				}
					
			});
		}
		function copyYhzxx(act){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxCopy";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
				var zmc_old = jQuery("[name=primarykey_checkVal]:checked").parents().children("td").eq(1).html();
				
				if(act=="show"){
					//viewTempDiv('�û��鸴��','copyYhzxx',320, 110)
					showDialog("�û��鸴��", 420, 240, "xtwh_qxgl_yhzgl.do?method=ymcl&type=copy&zdm="+zdm+"&zmc_old="+zmc_old);
					jQuery("#zmc_copy").val("���� "+zmc_old);
				}else{					
					var zmc_new = jQuery("#zmc_copy").val();
					if(trim(zmc_new) == trim(zmc_old)){
						alertInfo("����ɹ���");
						hiddenMessage(true,true);
						return false;
					}else{
						var parameter={"zdm":zdm,"zmc":encodeURI(zmc_new)};
						
						jQuery.post(url,parameter,function(result){
							if(result == "exist"){
								alertInfo("�û��������Ѵ��ڣ�");
								document.getElementById('zmc_copy').focus();
								return false;
							}else{
								alertInfo(result+"��");
								hiddenMessage(true,true);
								searchRs();
							}						
						});
					}
				}
			}else {				
				alertInfo("�빴ѡһ����Ҫ���Ƶ��û��飡");
			}
		}

		function xsxxts(){
			alertInfo('�뵽"ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ "�²鿴������Ϣ ');			
		}
		
		function updateYhzxx(act){

			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxUpdate";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(len==1){
				var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
				var zmc_old = jQuery("[name=primarykey_checkVal]:checked").parents().children("td").eq(1).html();
				
				if(act=="show"){
					showDialog('�û����޸�',  360, 180, "xtwh_qxgl_yhzgl.do?method=ymcl&type=update&zdm="+zdm+"&zmc_old="+zmc_old);
					//viewTempDiv('�û����޸�','updateYhzxx',320, 110)
					jQuery("#zmc_update").val(zmc_old);
				}else{					
					var zmc_new = jQuery("#zmc_update").val();
					if(trim(zmc_new) == trim(zmc_old)){
						alertInfo("����ɹ���");
						hiddenMessage(true,true);
						return false;
					}else{
						var parameter={"str_zdm":zdm,"str_zmc":escape(zmc_new)};
						
						jQuery.post(url,parameter,function(result){
							if(result == "exist"){
								alertInfo("�û��������Ѵ��ڣ�");
								document.getElementById('zmc_update').focus();
								return false;
							}else{
								alertInfo(result+"��");
								hiddenMessage(true,true);
								searchRs();
							}						
						});
					}
				}
			}else {				
				alertInfo("�빴ѡһ����Ҫ�޸ĵ����ݣ�");
			}
		}
		
		function deleteYhzxx(){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxDelete";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var i = 0;
				var ifXsz = "";
				var ifHyh = "";
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					if(jQuery(this).val() == "6727"){//ѧ���鲻��ɾ��
						ifXsz = "yes";
						return false;
					}else if(jQuery(this).parents().children("td").eq(2).children("a").children("b").html() != "0"){//�����û����û��鲻��ɾ��
						ifHyh = "yes";
						return false;
					}else{		
						array[i] = escape(jQuery(this).val());
					}
				});
				if(ifXsz == "yes"){ 
					alertInfo("��ѧ������ΪϵͳĬ���û���<br>����ɾ����");
				}else if(ifHyh == "yes"){
					alertInfo("��ά���û����û��鲻��ɾ����");
				}else{		
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					
					confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
						if(ok=="ok"){		
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"��");
								searchRs();		
							});					
						}
					});
				}
			}else{				
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�");
			}
		}

		//������Ȩ
		function gnsq(){

			var url = "xtwh_qxgl_yhzgl.do?method=yhzglGnsq";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(len==1){
				jQuery("#pkValue").val(jQuery("[name=primarykey_checkVal]:checked").val());
				document.forms[0].action=url;
				document.forms[0].submit();
			}else {				
				alertInfo("�빴ѡһ����Ҫ�޸ĵ����ݣ�");
			}
		}
		//�����û�
		function fpyh(){

			var url = "xtwh_qxgl_yhzgl.do?method=yhzglFpyh";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
			
			if(len==1){
				if("6727"==zdm){
					alertInfo("ѧ���鲻�ɷ����û���");
					return false;
				}
				jQuery("#pkValue").val(zdm);
				document.forms[0].action=url;
				document.forms[0].submit();
			}else {
				alertInfo("�빴ѡһ����Ҫ�����û����飡");
				return false;
			}
		}

		function viewYhxx(zdm){
			showDialog('', 800, 500, "xtwh_qxgl_yhzgl.do?method=yhzglViewYhxx&zdm="+zdm);

		}
		function add(){
			showDialog('�û�������', 360, 180, "xtwh_qxgl_yhzgl.do?method=ymcl&type=add");
		}
		jQuery(function(){
			onShow();
			var btndr=jQuery("#btn_dr");
			//����
			if(btndr!=null){
				btndr.click(function () {
					//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
					toImportData("IMPORT_N700201_YHZGL");
					return false;
				});
			}
		});
		
		
		</script>
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
			<p class="help">
<%--			<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>--%>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				δ��д
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/xtwh_qxgl_yhzgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<!-- ������ -->
			

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>

						<li>
							<a href="#" onclick="add();" class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="updateYhzxx('show');return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteYhzxx();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="#" onclick="copyYhzxx('show');return false;" class="btn_fz">����</a>
						</li>						
						<li>
							<a href="#" onclick="gnsq();return false;" class="btn_sq">������Ȩ</a>
						</li>
						<li>
							<a href="#" onclick="fpyh();return false;" class="btn_sq">���û�ά��</a>
						</li>
						<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody id="tbody_search_query">
							<tr>
								<th>
									������
								</th>
								<td>
									<input type="text" name="zmc" id="zmc" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>	
								<td >
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>						
							</tr>
						</tbody>
						<%--
						<tfoot>
							<tr>
								<td colspan="2">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						--%>
					</table>
				</div>
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
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhzglNewForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ����鵯���� -->
			<div class="open_win01" style="display:none;" id="addYhzxx">
				<table width='80%' class='formlist'>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>�û�������</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr>
							<th>
								<font color='red'>*</font>������
							</th>
							<td>
								<input type='text' name='zmc_add' id='zmc_add' onkeyup="teshuzifu(this);"  onkeypress="if(pressEnter(event)){return false;}" onblur="teshuzifu(this);"  class='text_nor' maxlength="10"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"  onclick="addYhzxx()">
										���
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- �޸��鵯���� -->
			<div class="open_win01" style="display:none;" id="updateYhzxx">
				<table width="80%" class="formlist">
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>�û����޸�</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr height='30'>
							<th>
								<font color="red">*</font>������
							</th>
							<td>
								<input type="text" name="zmc_update" id="zmc_update" onkeyup="teshuzifu(this);" onkeypress="if(pressEnter(event)){return false;}"  onblur="teshuzifu(this);" class="text_nor" maxlength="10"/>
								<input type="hidden" name="yymc" id="yymc"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"  onclick="updateYhzxx('do')">
										�޸�
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="open_win01" style="display:none;" id="copyYhzxx">
				<table width="80%" class="formlist">
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>�û����޸�</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr height='30'>
							<th>
								<font color="red">*</font>������
							</th>
							<td>
								<input type="text" name="zmc_copy" id="zmc_copy" onkeyup="teshuzifu(this);" onkeypress="if(pressEnter(event)){return false;}"  onblur="teshuzifu(this);" class="text_nor" maxlength="10"/>
								<input type="hidden" name="yymc" id="yymc"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"  onclick="copyYhzxx('do')">
										����
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
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
