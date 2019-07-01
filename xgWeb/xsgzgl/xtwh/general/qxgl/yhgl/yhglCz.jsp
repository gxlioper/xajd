<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" defer="defer">
function addYhxx(){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxAdd";
			var eles = new Array("yhm_add","xm_add","kl_add","select_zdm_add","select_szbm_add","select_dwdm_add","select_qx_add");
			for (i = 0; i < eles.length; i++) {
			    if($(eles[i])!=null||$(eles[i])){
				    if ($(eles[i]).value == "") {
				    	alertInfo("�뽫��\"*\"�ŵ���Ŀ����������");
				    	return false;
				   }
				}
			}	

			if(jQuery("#kl_add").val() != jQuery("#kl_add_rep").val()){
				alertInfo("ȷ�Ͽ��һ�£�");
		    	return false;
			}

			if(!checkPsw(jQuery("#kl_add").val()))
				return false;
			
			var parameter = {};
			for (i = 0; i < eles.length; i++) {
				parameter[eles[i].replace("select_","").replace("_add","")] =escape(jQuery("#"+eles[i]).val());
			}
			jQuery.post(url,parameter,function(result){
				alertInfo(result+"��");
				hiddenMessage(true,true);
				searchRs();					
			});
		}

		//�û���Ϣ�޸�
		function updateYhxx(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxUpdate";
			var eles = new Array("yhm_update","xm_update","select_zdm_update","select_szbm_update","select_dwdm_update","select_qx_update");
		
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
		
			if(len==1){
				var parameter = {};
				if(act=="show"){
					jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
						jQuery("#yhm_v").val(data.yhm);
						jQuery("#yhm_update").val(data.yhm);
						jQuery("#xm_update").val(data.xm);
						jQuery("#select_zdm_update").val(data.zdm);
						jQuery("#select_szbm_update").val(data.szbm);
						jQuery("#select_dwdm_update").val(data.dwdm);
						jQuery("#select_qx_update").val(data.qx);
						
						viewTempDiv('�û���Ϣ�޸�','updateyhxx',350,380);			
					},'json');
				}else{	
					for (i = 0; i < eles.length; i++) {
					    if($(eles[i])!=null||$(eles[i])){
						    if ($(eles[i]).value == "") {
						    	alertInfo("�뽫��\"*\"�ŵ���Ŀ����������");
						    	return false;
						   }
						}
					}			
					for (i = 0; i < eles.length; i++) {
						parameter[eles[i].replace("select_","").replace("_update","")] =escape(jQuery("#"+eles[i]).val());
					}
					jQuery.post(url,parameter,function(result){
						alertInfo(result+"��");
						hiddenMessage(true,true);
						searchRs();		
					});
				}
			}else {				
				alertInfo("�빴ѡһ����Ҫ�޸ĵ����ݣ�");
			}
		}

		//ɾ���û�
		function deleteYhxx(){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxDelete";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					array[i] = escape(jQuery(this).val());
				});
				parameter["array_primarykey_checkVal"]=array.join('!!array!!');
				
				confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
					if(ok=="ok"){		
						jQuery.post(url,parameter,function(result){
							alertInfo(result+"��");
							searchRs();		
						});					
					}
				});
			}else{				
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�");
			}
		}


		//�����ʼ��
		function mmcsh(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhmmCsh";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					array[i] = escape(jQuery(this).val());
				});
				var selectId=array.join('!!array!!');
				if(act == "show"){
					//viewTempDiv('�����ʼ��','cshYhmm',350, 230);
					showDialog('�����ʼ��',400,200,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectId+'&pathUrl=mmcsh');
						
				}else{
					if(jQuery("#mm1").val() != jQuery("#mm2").val()){
						alertInfo("ȷ�Ͽ��һ�£�");
				    	return false;
					}
					
					if(!checkPsw(jQuery("#mm1").val()))
						return false;
						
					var i = 0;
					var parameter = {};
					parameter["array_primarykey_checkVal"]=selectId;
					parameter["str_kl"]=escape(jQuery("#mm1").val());
					confirmInfo("ȷ��Ҫ��ѡ�е��û��������ʼ����?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"��");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else{				
				// ����
				jQuery("#pksPlHidden").val("");
				// ���ݲ�ѯ���������������
				var ie = "ie";
				var parameter = {"ie":ie};
				jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
					parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
				});
				jQuery("#pksPlHidden").val(JSON.stringify(parameter));
				showDialog('�����ʼ��',360,200,'xtwh_qxgl_yhgl.do?method=yhglManage&pathUrl=mmcshPl&selectId='+jQuery("#max_record").html());			
			}
		}

		function setFdyxx(){
			jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getFdyxx",{yhm:jQuery("#yhm_add").val()},function(data){
				jQuery("#xm_add").val(data.xm);
				jQuery("#select_szbm_add").val(data.bmdm);		
			},'json');
		}

		//�����޸��û������飬����״̬
		function yhfz(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhfz";			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			
			if(n>0){
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						array[i] = escape(jQuery(this).val());
					}
				});

				if(ifGly == "yes"){
					alertError("zf01ΪϵͳĬ���û��������޸������飡");
				}else{				
					if(n == 1 && act == "show"){
						jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
							//viewTempDiv(' ','yhfz',360,230);
							var selectid=array.join('!!array!!');
							showDialog('�û��������',450,385,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectid+'&pathUrl=fz');			
							jQuery("#select_zdm_fz").val(data.zdm);
						},'json');									
					}else if(act == "show"){
						//viewTempDiv(' ','yhfz',360, 230);
						var selectid=array.join('!!array!!');
							showDialog('�û��������',450,385,'xtwh_qxgl_yhgl.do?method=yhglManage&selectId='+selectid+'&pathUrl=fz');
						jQuery("#select_zdm_fz").val("");
					}else{
						if(jQuery("#select_zdm_fz").val() == ""){
							alertError("��ѡ�������飡");
							return false;
						}
						var i = 0;					
						parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						parameter["str_zdm"]=jQuery("#select_zdm_fz").val();
						parameter["str_qx"]=jQuery("[name=qx]:checked").val();
						confirmInfo("ȷ��Ҫ�޸�ѡ�е��û�����������?",function(ok){
							if(ok=="ok"){
								jQuery.post(url,parameter,function(result){
									alertInfo(result+"��");
									hiddenMessage(true,true);
									searchRs();		
								});					
							}else{
								return false;
							}
						});
					}
				}
			}else{				
				alertInfo("�빴ѡ��Ҫ�޸ķ�����û���");
			}
		}
</script>

<!-- ������û��� -->
<div class="open_win01" style="display:none;" id="addYhxx" >
	<table width='80%' class='formlist'>				
		<thead>
			<tr>
				<th colspan="2">
					<span>�û���Ϣ����</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width='25%'>
					<font color=red>*</font>�û���
				</th>
				<td>
					<input type='text' name='yhm_add' class='text_nor' id="yhm_add"  maxlength="20"
						onblur="checkInputIshz(this);checkExists('yhb','yhm',this,'button_user','span_user');setFdyxx();"/>
					<span id="span_user" style="display: none;color:red">�û����Ѵ��ڣ�</span>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>����
				</th>
				<td>
					<input type='text' name='xm_add' id="xm_add" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>����
				</th>
				<td>								
					<input type='password' name='kl_add' id='kl_add' class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>ȷ������
				</th>
				<td>
					<input type='password' name='kl_add_rep' id="kl_add_rep" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>������
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_add">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<logic:notEqual value="10338" name="xxdm" scope="session">
				<tr>
					<th>
						<font color=red>*</font>������λ
					</th>
					<td>
						<html:select property="dwdm" style="width:200px" styleId="select_dwdm_add">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="dwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<th>
					<font color=red>*</font>��������
				</th>
				<td>
					<html:select property="szbm" style="width:200px" styleId="select_szbm_add">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					����״̬
				</th>
				<td>
					<select id='select_qx_add' name='qx' style='width:152px;'>
						<option value='1'>
							����
						</option>
						<option value='0'>
							ͣ��
						</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"Ϊ������<br/>
					<span class="red">���Ӹ���Ա�û�ʱ��ȷ��˼�������еĸ���Ա��Ϣ�뵱ǰ<br/>�û���Ϣһ��</span><br/>
					<span class="red">���볤�Ȳ���С��6λ�Ҳ���Ϊ�������ֻ���ͬ�ַ�</span>							
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div class='btn'>
						<button type="button"  id="button_user" onclick='addYhxx()'>
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
<!-- �޸��û���Ϣ�� -->
<div class="open_win01" style="display:none;" id="updateYhxx" >
	<table width='80%' class='formlist'>
		<thead>
			<tr>
				<th colspan="2">
					<span>�û���Ϣ�޸�</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width='25%'>
					<font color=red>*</font>�û���
				</th>
				<td>
					<input type='text' name='yhm_v' class='text_nor' id="yhm_v" disabled="disabled"/>
					<input type='hidden' name='yhm_update' class='text_nor' id="yhm_update" value='' maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>����
				</th>
				<td>
					<input type='text' name='xm_update' id="xm_update" class='text_nor' maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color=red>*</font>������
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_update">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<logic:notEqual value="10338" name="xxdm" scope="session">
				<tr>
					<th>
						<font color=red>*</font>������λ
					</th>
					<td>
						<html:select property="dwdm" style="width:200px" styleId="select_dwdm_update">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="dwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
			</logic:notEqual>
			<tr>
				<th>
					<font color=red>*</font>��������
				</th>
				<td>
					<html:select property="szbm" style="width:200px" styleId="select_szbm_update">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					����״̬
				</th>
				<td>
					<select id='select_qx_update' name='qx' style='width:152px;'>
						<option value='1'>
							����
						</option>
						<option value='0'>
							ͣ��
						</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"Ϊ������<br/><span class="red">���Ӹ���Ա�û�ʱ��ȷ��˼�������еĸ���Ա��Ϣ�뵱ǰ<br/>�û���Ϣһ��</span>
				</td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<div class='btn'>
						<button type="button"  id="button_user" onclick='updateYhxx()'>
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
<!-- �����ʼ�������� -->
<div class="open_win01" style="display:none;" id="cshYhmm">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>�����ʼ��</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>������
				</th>
				<td>
					<input type="password" name="mm1" id="mm1" class="text_nor"  maxlength="10"/>
				</td>
			</tr>
			<tr height='30'>
				<th>
					<font color="red">*</font>ȷ������
				</th>
				<td>
					<input type="password" name="mm2" id="mm2" class="text_nor" maxlength="10"/>
				</td>
			</tr>
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"Ϊ������<br/><span class="red">���볤�Ȳ���С��6λ�Ҳ���Ϊ�������ֻ���ͬ�ַ�</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="mmcsh()">
							ȷ��
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
<!-- �û��������޸ĵ����� -->
<div class="open_win01" style="display:none;" id="yhfz">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>�û�����</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>������
				</th>
				<td>
					<html:select property="zdm" style="width:200px" styleId="select_zdm_fz">
						<html:option value=""></html:option>
						<html:options collection="yhzList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>			
			<tr height='30'>
				<th>
					����״̬
				</th>
				<td>
					<input type="radio" id="qx_qy" name="qx" value="1" checked="checked"/>����
					<input type="radio" id="qx_bqy" name="qx" value="0" />ͣ��
				</td>
			</tr>
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"Ϊ������
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="yhfz()">
							ȷ��
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
