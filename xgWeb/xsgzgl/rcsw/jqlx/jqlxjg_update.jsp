<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxComm.js"></script>
		<script	type="text/javascript">
		function saveForm(obj){
			function check(ids){
				var id=ids.split("-");
				for(var i=0;i<id.length;i++){
					var lddm=jQuery.trim(jQuery("#"+id[i]).val());
					if(lddm==null||""==lddm){
						return false;
					}
				}
				return true;
			}
			// ==========���Ի� begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '10351'){
				clearData('bz', bzMsg);
			}
			// ==========���Ի� end===========
			var sqly = jQuery("#sqly").val();
			if (jQuery.trim(jQuery("#lxkssj").val()) == ""){
				showAlert("��У��ʼʱ�䲻��Ϊ�գ�");
				return false;
			}
			if (jQuery.trim(jQuery("#lxjzsj").val()) == ""){
				showAlert("��У��ֹʱ�䲻��Ϊ�գ�");
				return false;
			}
			// ==========���Ի� begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '12861' || xxdm == '10351'){
				if (jQuery.trim(jQuery("#jzxm").val()) == ""){
					showAlert("�ҳ���������Ϊ�գ�");
					return false;
				}
				if (jQuery.trim(jQuery("#jzlxdh").val()) == ""){
					showAlert("�ҳ���ϵ�绰����Ϊ�գ�");
					return false;
				}
			}
			if(xxdm == '10351') {
				if (jQuery.trim(jQuery("#lxsqlxdm").val()) == ""){
					showAlert("�������Ͳ���Ϊ�գ�");
					return false;
				}
			}
			if(xxdm == '11488'){
				var ids = "lxyy-dwlxr-dwlxdh";
				if(!check(ids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}
				var lxyy = jQuery("#lxyy").val();
				if(lxyy == '3'){
					var lxdw = jQuery.trim(jQuery("#lxdw").val());
					if(lxdw == null || lxdw == ''){
						showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
						return false;
					}
				}
			}
			
			if(xxdm == '10364'){
				if (jQuery.trim(jQuery("#lxyy").val()) == ""){
					showAlert("��Уԭ����Ϊ�գ�");
					return false;
				}
			}
			if(xxdm == '10530'){
				if (jQuery.trim(jQuery("#jzlxdh").val()) == ""){
					showAlert("��ϵ�绰����Ϊ�գ�");
					return false;
				}
			}
			/*if(xxdm == '10511'){
				if (jQuery.trim(jQuery("#rzdz").val()) == ""){
					showAlert("����ס���Ҳ���Ϊ�գ�");
					return false;
				}
			}*/
			// ==========���Ի� end===========
			if(xxdm != '11488'&&(xxdm != '10364')){
				if (jQuery.trim(sqly) == ""){
					showAlert("��Уԭ����Ϊ�գ�");
					return false;
				}
			}

			//�㽭��ҽҩ
            if(xxdm == '10344'){
            	//ȥ��ԭס��԰����ԭ����¥�ţ�ԭ���Һ�����ѡ���
				//var ids = "rzdz-lddm-dwlxdh-qsh-lxdw-bz-lxkssj-lxjzsj-sqly-lxxq-lxld-lxqs-sfgcj-sqlxtj";
				var ids = "dwlxdh-lxdw-bz-lxkssj-lxjzsj-sqly-lxxq-lxld-lxqs-sfgcj-sqlxtj";
				if(!check(ids)){
                    showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
                    return false;
                }
            }

			var url = "rcsw_jqlx.do?method=updateJqlxJg&type=update";
			
		      ajaxSubFormWithFun("jqlxModel",url,function(data){
		    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }
		jQuery(document).ready(function(){ 
			//��ʼ�����ڿؼ������ݲ������ö�����������
			var lxkssjV = '${jcszModel.lxkssj }';
			var lxjssjV = '${jcszModel.lxjssj }';
			jQuery("#lxkssj").bind("focus", function (){
				var lxjssj = jQuery("#lxjzsj").val();
				if(lxjssj == ''){
					lxjssj = lxjssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssj,minDate:lxkssjV}); 
			}); 
			jQuery("#lxjzsj").bind("focus", function (){
				var lxkssj = jQuery("#lxkssj").val();
				if(lxkssj == ''){
					lxkssj = lxkssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssjV,minDate:lxkssj});
			}); 
			jQuery("#lxkssj2").bind("focus", function (){
				var lxjssj2 = jQuery("#lxjzsj2").val();
				if(lxjssj2 == ''){
					lxjssj2 = lxjssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssj2,minDate:lxkssjV}); 
			}); 
			jQuery("#lxjzsj2").bind("focus", function (){
				var lxkssj2 = jQuery("#lxkssj2").val();
				if(lxkssj2 == ''){
					lxkssj2 = lxkssjV;
				}
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:lxjssjV,minDate:lxkssj2});
			}); 
			
			// ======== ���ش�λ begin===============
			var cwxx = jQuery("#cwxx").val();
			if(cwxx!=""){
				if('${xxdm}' != "10344"){
					showCwxx(cwxx, '${rs.sqid}');
				}
			}
			// ======== ���ش�λ end===============
			// =========== ����������ֻ�����޸���У���� begin ==========
			if('${rs.sjlx}' == '1'){
				jQuery("select,textarea,input:not(input:hidden)").attr("disabled","disabled");
			}
			// =========== ����������ֻ�����޸���У���� begin ==========
			// ==========���Ի� begin===========
			var xxdm = '${xxdm}';
			if(xxdm == '10351'){
				initData('bz', bzMsg);
			}
			/*if(xxdm == '10511'){
				jQuery("#rzdz").css("width","320px");
			}*/
			// ==========���Ի� end===========
		});
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_jqlx" method="post" styleId="jqlxModel">
			<input type="hidden" id="xh" value="${rs.xh }"/>
			<input type="hidden" name="sjlx" value="${rs.sjlx }"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<logic:equal value="1" name="rs" property="sjlx">
			<logic:notEqual value="10344" name="xxdm">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�����������ֻ�����޸�<font color="red">��У����</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:notEqual>
			</logic:equal>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:470px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<logic:equal value="10277" name="xxdm">
						<tr>
							<th>�Ƿ�ƶ����</th>
							<td colspan="3">
									${sfkns }
							</td>
						</tr>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%"><span class="red">*</span>ѧ��</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%"><span class="red">*</span>ѧ��</th>
							<td width="30%">
								<html:select  property="xq" styleId="xq" style="width:130px">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<!-- �㽭��ҽҩ��ѧ���Ի�start -->
						<logic:equal value="10344" name="xxdm">
							<%--<tr>
								<th width="20%">
									<span class="red">*</span>ԭס�޵�
								</th>
								<td>
									<html:select  property="rzdz" styleId="rzdz" style="width:155px">
										<html:options collection="yqList" property="yqdm" labelProperty="yqmc"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>ԭ����¥��
								</th>
								<td>
									<html:text property="lddm" styleId="lddm" style="width:155px" maxlength="10">
										
									</html:text>
								</td>
							</tr>
							--%>
							<tr>
								<%--<th width="20%">
									<span class="red">*</span>ԭ���Һ�
								</th>
								<td>
									<html:text property="qsh" styleId="qsh" maxlength="10"></html:text>
								</td>
								--%>
								<th width="20%">
									ԭס����
								</th>
								<td>
									${rs.yzqs}
								</td>
								<th>
									<span class="red">*</span>�ֻ�����
								</th>
								<td>
									<html:text property="dwlxdh" styleId="dwlxdh" maxlength="15"></html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>��ͥ��ַ
								</th>
								<td>
									<html:text property="lxdw" styleId="lxdw" maxlength="50"></html:text>
								</td>
								<th>
									<span class="red">*</span>��ͥ��ϵ�˺��ֻ�
								</th>
								<td>
									<html:text property="bz" styleId="bz" maxlength="40"></html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>����԰��
								</th>
								<td>
									<html:select property="lxxq" styleId="lxxq" style="width:155px">
										<html:options collection="yqList" property="yqdm" labelProperty="yqmc"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>��������¥��
								</th>
								<td>
									<html:text property="lxld" styleId="lxld" style="width:155px" maxlength="20">
										
									</html:text>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>�������Һ�
								</th>
								<td>
									<html:text property="lxqs" styleId="lxqs" style="width:155px" maxlength="20">
										
									</html:text>
								</td>
							</tr>		
						</logic:equal>
					    <tr>
							<th><span class="red">*</span>��У��ʼʱ��</th>
							<td>
								<html:text property="lxkssj" styleId="lxkssj"  />
							</td>
							<th><span class="red">*</span>��У��ֹʱ��</th>
							<td>
								<html:text property="lxjzsj" styleId="lxjzsj" />
							</td>
					    </tr>
					    <logic:notEqual value="12309" name="xxdm">
					    <tr>
							<th>��У��ʼʱ���</th>
							<td>
								<html:text property="lxkssj2" styleId="lxkssj2" />
							</td>
							<th>��У��ֹʱ���</th>
							<td>
								<html:text property="lxjzsj2" styleId="lxjzsj2" />
							</td>
						</tr>
						<logic:equal value="10344" name="xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>�Ƿ���У������
								</th>
								<td>
									<html:select property="sfgcj" styleId="sfgcj">
										<html:option value="">---��ѡ��---</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>������У����
								</th>
								<td>
									<html:select property="sqlxtj" styleId="sqlxtj" style="width:99%">
										<html:options collection="lxtjList" property="lxtjmc" labelProperty="lxtjmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						</logic:notEqual>
						<!-- ����ũҵ��ѧ��Уԭ����Ի�����ѡ��������ѧԺ����һ��ԭ������ -->
						<logic:equal value="10364" name="xxdm">
							<tr>
								<th><span class="red">*</span>��Уԭ��</th>
								<td colspan="3">
									<html:select property="lxyy" styleId="lxyy">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="lxyyList" property="lxyydm" labelProperty="lxyymc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>	
						<!-- ����ѧԺ��Уԭ����Ի� -->
						<logic:equal value="11488" name="xxdm">
					    <tbody id="qzxy">
						<tr>
							<th><span class="red">*</span>��Уԭ��</th>
							<td>
								<html:select property="lxyy" styleId="lxyy" onchange="lxyyChange(this)">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="lxyyList" property="lxyydm" labelProperty="lxyymc" />
								</html:select>
							</td>
							<th id="qzxylxr"><span class="red">*</span>
							<logic:equal value="3" name="rs" property="lxyy">
								��ְ��λ
							</logic:equal>
							<logic:equal value="7" name="rs" property="lxyy">
								��ͥ
							</logic:equal>��ϵ��
							</th>
							<td>
								<html:text property="dwlxr" styleId="dwlxr"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��ϵ�绰</th>
							<td>
								<html:text property="dwlxdh" styleId="dwlxdh" onblur="checkPhone(this)"></html:text>
							</td>
							<th>
								ԭס����
							</th>
							<td>
								${rs.yzqs }
							</td>
						</tr>
						<logic:equal value="3" name="rs" property="lxyy">
							<tr id="qzxyjzdw">
								<th>
									<span class="red">*</span>��ְ��λ
								</th>
								<td colspan="3">
									<html:text property="lxdw" styleId="lxdw" style='width:500px;' maxlength='50'></html:text>
								</td>
							</tr>
						</logic:equal>
						</tbody>						
						</logic:equal>
						<logic:equal value="10530" name="xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>��ϵ�绰
								</th>
								<td>
									<html:text property="jzlxdh" styleId="jzlxdh" onkeyup="checkInput(this)" maxlength="15" style=" ime-mode: disabled;"></html:text>
								</td>
								<logic:equal value="����" name = "jqlxV">
									<th width="20%">
										<span class="red">*</span>�Ƿ���У������
									</th>
									<td>
										<html:select property="sfgcj" styleId="sfgcj">
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</logic:equal>
							</tr>
						</logic:equal>
						<logic:equal value="11458" name="xxdm">
							<logic:equal value="����" name="jqlxV">
								<tr>
									<th>
										<span class="red">*</span>�Ƿ����ҹ��
									</th>
									<td colspan="3">
										<html:select property="sfcnyf" styleId="sfcnyf"
											style="width:150px">
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<logic:equal value="10277" name="xxdm">
							<logic:equal value="����" name="jqlxV">
								<tr>
									<th>
										<span class="red">*</span>�Ƿ����ҹ��
									</th>
									<td colspan="3">
										<html:select property="sfcnyf" styleId="sfcnyf"
											style="width:150px">
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<logic:equal value="10351" name="xxdm">
					    	<logic:equal value="����" name = "jqlxV">
							<tr>
								<th>
									<span class="red">*</span>�Ƿ���У����
								</th>
								<td colspan="3">
									<html:select  property="sflxgn" styleId="sflxgn" style="width:150px">
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
				      		</tr>
							<tr>
								<th>
									��ע
								</th>
								<td colspan="3">
									<html:text property="bz" styleId="bz" size="70" maxlength="100"></html:text>
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<% if("12861".equals(xxdm) || "10351".equals(xxdm)){ %>
							<tr>
								<th>
									<span class="red">*</span>�ҳ�����
								</th>
								<td>
									<html:text property="jzxm" styleId="jzxm" maxlength="10"></html:text>
								</td>
								<th>
									<span class="red">*</span>�ҳ���ϵ�绰
								</th>
								<td>
									<html:text property="jzlxdh" styleId="jzlxdh" onkeyup="checkInput(this)" maxlength="15" style=" ime-mode: disabled;"></html:text>
								</td>
				      		</tr>
						<% } %>
						<logic:equal value="10718" name="xxdm">
						<tr>
								<th>
									<span class="red">*</span>�Ƿ�ʳ������ʳ��
								</th>
								<td colspan="3">
									<html:select  property="sfsyqzsw" styleId="sfsyqzsw" style="width:150px">
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
				      		</tr>
						</logic:equal>
						<logic:equal value="10704" name="xxdm">
						<tr>
								<th>
									<span class="red">*</span>�Ƿ�ʳ������ʳ��
								</th>
								<td colspan="3">
									<html:select  property="sfsyqzsw" styleId="sfsyqzsw" style="width:150px">
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
				      		</tr>
						</logic:equal>
						<!-- �㽭��ýѧԺ���Ի���������У���ֶ�begin -->
						<logic:equal value="11647" name = "xxdm">
							<th width="20%">
								����У��
							</th>
							<td colspan="3">
								<html:select  property="lsxq" styleId="lsxq" style="width:155px">
									<html:options collection="lsxqList" labelProperty="xqmc" property="dm"/>
								</html:select>
							</td>
						</logic:equal>
						<!-- �㽭��ýѧԺ���Ի���������У���ֶ�end -->
						<!-- ������ҽҩ���Ի�begin -->
						<logic:equal value="10026" name = "xxdm">
							<tr>
								<th width="20%">
									<span class="red">*</span>��УУ��
								</th>
								<td>
									<html:select  property="lsxq" styleId="lsxq" style="width:155px">
										<html:options collection="lsxqList" labelProperty="xqmc" property="dm"/>
									</html:select>
								</td>
								<th width="20%">
									<span class="red">*</span>¥��
								</th>
								<td>
									<html:select  property="lddm" styleId="lddm" style="width:155px">
										<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<span class="red">*</span>���Һ�
								</th>
								<td>
									<html:text property="qsh" styleId="qsh" maxlength="10"></html:text>
								</td>
							</tr>
						</logic:equal>
						<!-- ������ҽҩ���Ի�end -->
						<logic:notEqual value="11488" name="xxdm">
						<logic:notEqual value="10026" name="xxdm">
						<logic:notEqual value="10344" name="xxdm">
						<tr>
							<th>
								ԭס����
							</th>
							<td colspan="3">
								${rs.yzqs }
								</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>
			      		</logic:notEqual>
<%--					    <tr>--%>
<%--							<th>--%>
<%--								<% if("10511".equals(xxdm)){ %><span class="red">*</span><% } %>����ס����--%>
<%--							</th>--%>
<%--							<td colspan="3">--%>
<%--								<html:text property="rzdz" styleId="rzdz" size="70"></html:text><% if("10511".equals(xxdm)){ %>����д��ʽ*��*��*�ң����綫��6��101�ң�<% } %>--%>
<%--							</td>--%>
<%--			      		</tr>--%>

					<logic:notEqual value="10026" name="xxdm">
					<logic:notEqual value="10344" name="xxdm">
						<tr>
							<th>��У����</th>
							<td align="left" width="10%" colspan="3">
								<button class="btn_01" type="button"  onclick="selectCw('${path}','${rs.sqid }');return false;" >ѡ��λ</button>
							</td>
						</tr>
						<tr id="yxzcwxx">
							<th>
								��ѡ��λ��Ϣ
							</th>
							<td colspan="3">
								<table id="cwxxTable"></table>
								<input type="hidden" name="cwxx" id="cwxx" value="${rs.cwxx }"/>
							</td>
						</tr>
					</logic:notEqual>
					</logic:notEqual>
						
			      			
						<logic:equal value="10351" name = "xxdm">
							<tr>
								<th>
									<font color="red">* </font>��������
								</th>
								<td colspan="3">
									<html:select property="lxsqlxdm" styleId="lxsqlxdm">
										<html:option value=""></html:option>
										<html:options collection="lxsqlxList" property="lxsqlxdm" labelProperty="lxsqlxmc" />
									</html:select>
								</td>
							</tr>			
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10364">
					    <tr>
							<th>
								<span class="red">*</span>��Уԭ��
								<br /><font color="red">&lt;��320��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,320);"/>
							</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>
			      		<logic:equal value="0" name="rs" property="sjlx">
							<tr>
								<th align="right" style="width: 10%">
									������Ϣ
								</th>
								<td colspan="3">
									<html:hidden property="fjxx" styleId="fjxx"/>
									<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//��׺
												accept : 'doc|docx|xls|xlsx|jpg|rar|zip|pdf|ppt',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'fjxx'
												});
										});
									</script>  
								</td>
							</tr>
							</logic:equal>
							<logic:equal value="1" name="rs" property="sjlx">
							<tr>
								<th align="right" style="width: 10%">
									������Ϣ
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
										<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												var gid = "${rs.fjxx}";
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script>
									</td>
							</tr>
							</logic:equal>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

