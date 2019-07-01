<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var bjdm = jQuery("#bjdm").val();
				if (jQuery.trim(bjdm) == ""){
					showAlert("��ѡ��һ���༶��");
					return false;
				}
				var sdrs = jQuery("#sdrs").val();
				if (jQuery.trim(sdrs) == ""){
					showAlert("ʵ����������Ϊ�գ�");
					return false;
				}	
				var qjrs = jQuery("#qjrs").val();
				if (jQuery.trim(qjrs) == ""){
					showAlert("�����������Ϊ�գ�");
					return false;
				}	
				var wdrs = jQuery("#wdrs").val();
				if (jQuery.trim(wdrs) == ""){
					showAlert("�޹�δ��ѧ����������Ϊ�գ�");
					return false;
				}	
				var ydrs = jQuery("#ydrs").val();
				if(parseInt(ydrs) != (parseInt(sdrs) + parseInt(qjrs) + parseInt(wdrs))){
					showAlert("Ӧ������=ʵ������+�������+�޹�δ��ѧ��������");
					return false;
				}
				var zynr = jQuery("#zynr").val();
				if (jQuery.trim(zynr) == ""){
					showAlert("���ܶ�ѧ�����н�������Ҫ���ݲ���Ϊ�գ�");
					return false;
				}	
				if (zynr.length > 500){
					showAlert("���ܶ�ѧ�����н�������Ҫ�������500�֣�");
					return false;
				}	
				var zywt = jQuery("#zywt").val();
				if (jQuery.trim(zywt) == ""){
					showAlert("����ѧ�����ڵ���Ҫ���ⲻ��Ϊ�գ�");
					return false;
				}	
				if (zywt.length > 500){
					showAlert("����ѧ�����ڵ���Ҫ�������500�֣�");
					return false;
				}	
				var jjdc = jQuery("#jjdc").val();
				if (jQuery.trim(jjdc) == ""){
					showAlert("����Ϊ�Ϻ���Ľ���Բ߲���Ϊ�գ�");
					return false;
				}	
				if (jjdc.length > 500){
					showAlert("����Ϊ�Ϻ���Ľ���Բ����500�֣�");
					return false;
				}	
				var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=addXsgzzbsq&gzzblx=bj&type="+type;
		      	ajaxSubFormWithFun("xsgzzbsqForm",url,function(data){
		    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	// �����ܴ���ʾ�ܴ���ֹ����
			function changeZcksjsrq(){
				var zcObj = jQuery("#zc").find("option:selected");
				var html = zcObj.attr("ksrq") + " �� " + zcObj.attr("jsrq");
				jQuery("#zcksjsrq_td").html(html);
				jQuery("#zcksjsrq_hidden").val(html);
			}
			function checkRs(){
				var ydrs = jQuery("#ydrs").val();
				if (jQuery.trim(ydrs) == ""){
					return false;
				}
				var sdrs = jQuery("#sdrs").val();
				if (jQuery.trim(sdrs) == ""){
					return false;
				}	
				var qjrs = jQuery("#qjrs").val();
				if (jQuery.trim(qjrs) == ""){
					return false;
				}	
				if(parseInt(ydrs) < (parseInt(sdrs) + parseInt(qjrs))){
					showAlert("ʵ������+����������ܴ���Ӧ��������");
					return false;
				}
				var wdrs = jQuery("#wdrs").val();
				if (jQuery.trim(wdrs) == ""){
					jQuery("#wdrs").val(parseInt(ydrs) - parseInt(sdrs) - parseInt(qjrs));
				}else{
					if(parseInt(ydrs) != (parseInt(sdrs) + parseInt(qjrs) + parseInt(wdrs))){
						showAlert("Ӧ������=ʵ������+�������+�޹�δ��ѧ��������");
						return false;
					}
				}
			}
			jQuery(function(){
				changeZcksjsrq();
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbsqgl" method="post" styleId="xsgzzbsqForm">
			<html:hidden property="xn"  styleId="xn" />
			<html:hidden property="xq"  styleId="xq" />
			<input type="hidden" name="zcksjsrq" id="zcksjsrq_hidden" value="${xsgzzbsqMap.zcksjsrq}"/>
			<% String userStatus = (String)request.getAttribute("userStatus"); %>
			<div style='tab;width:100%;height:418px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ܱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%"><span class="red">*</span>�༶</th>
							<td width="32%" colspan="3">
								<input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ���༶',800,500,'rcsw_xsgzzb_xsgzzbsqgl.do?method=bjManage');return false;">ѡ��</button>
								<span id="bj_span"></span>
							</td>
					    </tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									���ศ��Ա
								</th>
								<td id="dbfdy" colspan="3">
								</td>
							</tr>
						</logic:equal>

						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xsgzzbsqMap.xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xsgzzbsqMap.xqmc}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�ܴ�</th>
							<td>
								<select name="zc" id="zc" value="${xsgzzbsqMap.zc}" onchange="changeZcksjsrq();">
									<logic:iterate id="zc" name="zcList">
									<option value="${zc.dm}" ksrq="${zc.ksrq}" jsrq="${zc.jsrq}" <logic:equal name="zc" property="dm" value="${xsgzzbsqMap.zc}">selected="selected"</logic:equal>>${zc.mc}</option>
									</logic:iterate>
								</select>
							</td>
							<th>�ܴ���ֹ����</th>
							<td id="zcksjsrq_td">
								
							</td>
					    </tr>
					    <tr>
							<th>
								Ӧ������
								<input type="hidden" name="ydrs" id="ydrs" value=""/>
							</th>
							<td id="ydrs_td">
							</td>
							<th>
								<span class="red">*</span>ʵ������
							</th>
							<td>
								<html:text property="sdrs" styleId="sdrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>�������
							</th>
							<td>
								<html:text property="qjrs" styleId="qjrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
							<th>
								<span class="red">*</span>�޹�δ��ѧ������
							</th>
							<td>
								<html:text property="wdrs" styleId="wdrs" maxlength="5" style="" onkeyup="checkInputData(this);" onblur="checkRs();"></html:text>
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>���ܶ�ѧ������<br />��������Ҫ����
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='zynr' style="width:98%" styleId="zynr" rows='5' />
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>����ѧ�����ڵ�<br />��Ҫ����
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='zywt' style="width:98%" styleId="zywt" rows='5' />
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>����Ϊ�Ϻ����<br />����Բ�
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='jjdc' style="width:98%" styleId="jjdc" rows='5' />
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								����
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',
	
											eid : 'filepath_f'
											});
									});
								</script>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										����ݸ�
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										�ύ����
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

