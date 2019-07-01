<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="dwr/interface/gyglGywh.js"></script>	
		<script type='text/javascript' src='dwr/interface/getCommGygl.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		function checkExists(tableName, pk){
			var lddm = jQuery('#lddm').val();
			var pkV = lddm;
			
			dwr.engine.setAsync(false);
			
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_qsh').show('normal')
					jQuery('#btn_bc').attr('disabled', 'disabled');
				}else{
					jQuery('#span_qsh').hide('normal')
					jQuery('#btn_bc').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
		
		function save(){
			if($("xqdm")&&$("xqdm").value==""){
				alertInfo("��ѡ��У����");
				return false;
			}
			if($("yqdm")&&$("yqdm").value==""){
				alertInfo("��ѡ��"+jQuery("#yqmc").val()+"��");
				return false;
			}
			if($("lddm").value==""){
				alertInfo("¥�����벻��Ϊ��!");
				return false;
			}
			if ($("lddm").value.match(/[\u4e00-\u9fa5]/g)) {
				alertInfo("¥������,������������!");
				return false;
		 	}
			if($("ldmc").value==""){
				alertInfo("¥�����Ʋ���Ϊ��!");
				return false;
			}
			//��֤¥���Ա�
			var ldxb;
			var b=false;//¥���Ա�
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					b=true;
					break;
				}
			}
			if(!b){
				alertInfo("��ѡ��¥���Ա�!");
				return false;
			}
			//��֤����������
			b=false;
			var re_num = /^[\d]+$/;
			var scqss=document.getElementsByName("scqss");
			for(var i=0;i<scqss.length;i++){
				if(scqss[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("¥������������Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("¥��������������Ϊ��!");
				return false;
			}
			//��֤����������
			b=false;
			var cws=document.getElementsByName("cws");
			for(var i=0;i<cws.length;i++){
				if(cws[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("��λ������Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("��λ��������Ϊ��!");
				return false;
			}
			//��֤�շѱ�׼
			b=false;
			var sfbz=document.getElementsByName("sfbz");
			for(var i=0;i<sfbz.length;i++){
				if(sfbz[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("�շѱ�׼����Ϊ����!");
					return false;
				}
			}
			if(b){
				alertInfo("�շѱ�׼������Ϊ��!");
				return false;
			}
			//��֤�����Ա�
			if("��ס"==ldxb){
				var ldcs=$("ldcs").value;//¥������
				var qsxb;
				for(var i=0;i<parseInt(ldcs);i++){
					qsxb=document.getElementsByName("qsxb"+i);
					if(!qsxb[0].checked&&!qsxb[1].checked){
						alertInfo("��ѡ�������Ա�");
						return false;	
					}
				}
			}
			
			
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=save";
			refreshForm(url);
		}
		function modi(){
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=modi";
			refreshForm(url);
		}
		//����¥��
		function createLd(){
			var ldxb="��";//¥���Ա�
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					break;
				}
			}
			var ldcs=$("ldcs").value;//¥������
			var qsch=parseInt($("qsch").value);//��ʼ���
			var sfhlc=$("sfhlc").value;//�Ƿ����
			if(ldxb==""||ldcs==""){
				return false;
			}
			
			var ld_table=$("ld_table");//¥��table
			var ld_trs=ld_table.getElementsByTagName("TR");
			for(var i=ld_trs.length-1;i>1;i--){//�Ƴ�ԭ�ȴ�������Ϣ
				ld_table.deleteRow(i);
			}
			var b_mark;//����B���
			var ch_value;//���ֵ
			var ch_name;//�������
			var sfhlc_mark=(qsch>0||sfhlc=="��"?0:1);//�Ƿ������//������������㣬���������Ҫ����
			var top_mark=true;//������
			for(var i=parseInt(ldcs)-1;i>-1;i--){//�������õĲ�������¥��var i=0;i<parseInt(ldcs);i++
				var r=ld_table.insertRow(parseInt(ldcs)-1-i+2);//ld_table.insertRow(i+2);
				if(i+qsch<0){
					b_mark="B";
					ch_value=i+qsch;
					ch_name=-(i+qsch);
				}else{
					b_mark="";
					ch_value=i+qsch+sfhlc_mark;
					ch_name=i+qsch+sfhlc_mark;
				}
				
				r.insertCell(0).innerHTML='<input type="hidden" name="ch" value="'+ch_value+'"/>'+b_mark+ch_name+"��/0";
				
				r.insertCell(1).innerHTML='<input type="text" style="width:100px" name="scqss" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(2).innerHTML='<input type="text" style="width:100px" name="cws" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(3).innerHTML='<input type="text" style="width:100px" name="sfbz" maxlength="4"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				if(ldxb=="��"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" checked="checked" value="��"/>��<input type="radio" disabled="disabled" value="Ů"/>Ů';
				}else if(ldxb=="Ů"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" value="��"/>��<input type="radio" disabled="disabled" checked="checked" value="Ů"/>Ů';
				}else{
					r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+i+'" checked="checked" value="��"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>��<input type="radio" name="qsxb'+i+'" value="Ů"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>Ů';
				}
				top_mark=false;
			}
		}
		//��������
		function plsr(obj){
			if($("plsr_checkbox").checked){
				var objs=document.getElementsByName(obj.name);
				for(var i=1;i<objs.length;i++){
					objs[i].value=obj.value;
				}
			}
		}
		//������������Ա�
		function click_qsxb(obj){
			if($("plsr_checkbox").checked){
				var ldcs=$("ldcs").value;//¥������
				for(var i=1;i<ldcs;i++){
					if(obj.value=="��"){
						document.getElementsByName("qsxb"+i)[0].checked="checked";
					}else{
						document.getElementsByName("qsxb"+i)[1].checked="checked";
					}
				}
			}
		}

		function show_sfhlc(qsch){
			if(qsch>=0){
				if(qsch==0){
					$("sfhlc").value="��";
				}else{
					$("sfhlc").value="��";
				}
				$("sfhlc_lable").style.display="none";
				$("sfhlc_obj").style.display="none";
			}else{
				$("sfhlc_lable").style.display="";
				$("sfhlc_obj").style.display="";
			}
		}
		//����У������԰�������Ϣ
		function loadYqxx(){
			if(!$("yqdm")){
				return false;
			}
			jQuery.post('gyglnew_ldgl.do?method=loadYqxx',{xqdm:jQuery('#xqdm').val()},function(data){
				jQuery('#yqdm').empty();
				//jQuery('#yqdm').append("<option value=''>--��ѡ��--</option>");
				jQuery('#yqdm').append(data);
			});
		}

		function showSzm(){
			if(jQuery("[name='qsh_sfhszm']").prop("checked")){
				jQuery("#qsh_szm").css("display","");
			}else{
				jQuery("#qsh_szm").css("display","none");
				//$("qsh_szm").style.display = "none";
			}
		}
		/**
		 * ֻ���������ַ�
		 * @param obj
		 * @return
		 */
		function onlyCaseChar(obj) {
			var str = obj.value;
			var result = "";
			for ( var i = 0; i < str.length; i++) {
				if (str.charCodeAt(i) == 12288) {
					result += String.fromCharCode(str.charCodeAt(i) - 12256);
					continue;
				}
				if (str.charCodeAt(i) > 65280 && str.charCodeAt(i) < 65375)
					result += String.fromCharCode(str.charCodeAt(i) - 65248);
				else
					result += String.fromCharCode(str.charCodeAt(i));
			}
			obj.value = result;
		}
		
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_ldgl" method="post">
			<logic:present name="rs">
			<script type="text/javascript">
			jQuery(function(){
				createLd();
			})
			</script>
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" id="yqmc" value="<bean:message key="lable.yuanqu" />"/>
			<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>��ʾ��</span></h3>
		       <p>��¥���Ѵ��ڣ�<br/></p>
		   	</div>
			<div class="tab" style="tab;height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.ld" />��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:notEqual value="0" name="xqbj">
							<th style="width:10%">
								<font color="red">*</font>
								У��
							</th>
							<td  style="width:40%">
								<html:select property="xqdm" name="rs" styleId="xqdm" onchange="loadYqxx()">
									<html:option value=""></html:option>
									<html:optionsCollection name="xqlist" label="xqmc" value="dm"/>
								</html:select>
							</td>
							</logic:notEqual>
							<logic:notEqual value="0" name="yqbj">
							<th>
								<font color="red">*</font>
								<bean:message key="lable.yuanqu" />
							</th>
							<td>
								<html:select property="yqdm" name="rs" styleId="yqdm">
									<html:option value=""></html:option>
									<html:optionsCollection name="yqlist" label="yqmc" value="yqdm"/>
								</html:select>
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th style="width:14%">
								<font color="red">*</font>
								¥������
							</th>
							<td style="width:36%">
								<html:text property="lddm" styleId="lddm" name="rs" onblur="checkExists('xg_gygl_new_ldxxb','lddm');onlyCaseChar(this)" maxlength="20"></html:text>
							</td>
							<th style="width:14%">
								<font color="red">*</font>
								¥������
							</th>
							<td style="width:36%">
								<html:text property="ldmc" styleId="ldmc" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />�Ա�
							</th>
							<td>
								<html:radio property="ldxb" value="��" name="rs" onclick="createLd();">��</html:radio>
								<html:radio property="ldxb" value="Ů" name="rs" onclick="createLd();">Ů</html:radio>
								<html:radio property="ldxb" value="��ס" name="rs" onclick="createLd();">��ס</html:radio>
							</td>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />����
							</th>
							<td>
								<html:select property="ldcs" name="rs" styleId="ldcs" onchange="createLd();">
									<html:optionsCollection name="ldcsList" label="mc" value="dm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />��ʼ��
							</th>
							<td>
								<html:select property="qsch" name="rs" styleId="qsch" onchange="createLd();show_sfhlc(this.value);">
									<html:option value="5">5</html:option>
									<html:option value="4">4</html:option>
									<html:option value="3">3</html:option>
									<html:option value="2">2</html:option>
									<html:option value="1">1</html:option>
									<html:option value="0">0</html:option>
									<html:option value="-1">-1</html:option>
									<html:option value="-2">-2</html:option>
									<html:option value="-3">-3</html:option>
									<html:option value="-4">-4</html:option>
								</html:select>
							</td>
							<th>
								<span id="sfhlc_lable" style="display: none;">�Ƿ�0��</span>
							</th>
							<td>
								<span id="sfhlc_obj" style="display: none;">
								<html:select property="sfhlc" name="rs" styleId="sfhlc" onchange="createLd();">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
								</span>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.ld" />��ע<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" name="rs" rows="3" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>	
				<div class="border_01 formbox">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>ά����Ҫ���ɵ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								���Һ����ɹ���
								<input type="checkbox" name="qsh_sfhszm" value="��" onclick="showSzm();"/>����ĸ 
								<input type="text" name="qsh_szm" id="qsh_szm" maxlength="4" style="width: 50px;display: none"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<strong>+</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="qsh_ws" value="3" checked="checked"/>��λ
								<input type="radio" name="qsh_ws" value="4"/>��λ
							</td>
						</tr>
					</tbody>
					</table>
					<!--���й������� ����end-->
					<div style="height: 250px;overflow: auto;width: 100%">
						<table width="100%" border="0" class="datelist tablenowrap" id="ld_table">
							<thead>
								<tr>
									<td colspan="5">
										<input type="checkbox" id="plsr_checkbox" checked="checked" />Ĭ�ϰ���ʼ��������������������λ�����շѱ�׼
									</td>
								</tr>
								<tr>
									<th style="width:20%">
										���/����������
									</th>
									<th style="width:20%">
										����������
									</th>
									<th style="width:20%">
										�������Ҵ�λ��
									</th>
									<th style="width:20%">
										�շѱ�׼��Ԫ��
									</th>
									<th style="width:20%">
										�����Ա�
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
				<div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										ע����"<font color="red">*</font>"����Ϣ����¼��
									</font>
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" class="button2" id="btn_bc"  onclick="modi()">
											�� ��
										</button>
									</logic:equal>
									<button type="button" class="button2"  onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>

			</logic:present>
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					
					 showAlert("����ʧ��",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("�����ɹ�",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
