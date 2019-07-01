<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">

			function saveAction(type){

				if(jQuery("#sblx").val() == '2' ) {
					var checkids = "xh-ztqk";
				}else {
					var checkids = "xh-xn-xq-sbzbid-ztqk";
				}	
						
				if(!checkNotNull(checkids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
					return false;
				}
				
				var url = "xljk_xlwygl_xxsbjgglwh.do?method=saveAction&type="+type  ;
					ajaxSubFormWithFun("xlwyglxxsbjgglForm",url,function(data){

						if((data||{})['code'] == '-1'){
							showAlertDivLayer("��������δ����,����ϵ����Ա!");
							return false;
						}else if((data||{})['code'] == '-2'){
							showAlertDivLayer("��ѧ���Ѵ�����ͬ����(ͬ�ܴ�ͬ���������Ѵ���),��ȷ��!");
							return false;
						}else if((data||{})['code'] == '0'){
							showAlertDivLayer(data["message"]);
							return false;
						}else if((data||{})['code'] == '1'){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
						}else{
							showAlertDivLayer("δ֪ϵͳ����!");
							return false;
						}
					});
			}
			function changeZbqzrq(obj){
				jQuery("#zbqzrq_td").html(jQuery(obj).find('option:selected').attr("zbqzrq"));
			}
			jQuery(function(){
				jQuery("#sbzbid").change();
				jQuery("#xn").val('${xn}');
				jQuery("#xq").val('${xq}');
				jQuery("#xn").change();
			});

			function on_change(){
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				var sblx = jQuery("#sblx").val();
				jQuery.post("xljk_xlwygl_xxsbjgglwh.do?method=getZcList",{xn:xn,xq:xq,sblx:sblx},function(data){
					var html = "<option value=''></option>";
					  if(data && data.length > 0){
						  for(var i = 0; i < data.length; i++){
							html += '<option value='+data[i].zbid+'>'+data[i].zbzc+'</option>';
						  }
					  }
					jQuery("#sbzbid").html(html);
				  },'json');
			}

			function on_changeZc(){
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				var sbzbid = jQuery("#sbzbid").val();
				jQuery.post("xljk_xlwygl_xxsbjgglwh.do?method=getQzrq",{xn:xn,xq:xq,sbzbid:sbzbid},function(data){
					var html = "";
					  if(data && data.length > 0){	 
							html += data[0].zbqzrq;		  
					  }
					jQuery("#zbqzrq").html(html);
				  },'json');
			}
			
		</script>
	</head>
	<body>
		<html:form action="/xljk_xlwygl_xxsbjgglwh" method="post" styleId="xlwyglxxsbjgglForm">
			<html:hidden styleId="sblx" property="sblx" value="${sbxx.sblx}"/>
			<div style='tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xljkwzdx/xlwygl/xxsbjggl/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span><font color="blue" style="font-weight: bold;">${sbxx.sblxmc}</font>-�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="2" name="sbxx" property="sblx">
							<tr>
								<th>
									<font color="red">* </font>ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" onchange="on_change()" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<html:select property="xq" styleId="xq" onchange="on_change()" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�ܴ�
								</th>
								<td>
									<select name="sbzbid" id="sbzbid" style="width: 150px;" onchange="on_changeZc()">
									</select>
								</td>
								<th>
									��ֹ����
								</th>
								<td>
									<span name="zbqzrq" id="zbqzrq">
									</span>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="2" name="sbxx" property="sblx">
							<tr>
								<th align="right">
									<span class="red">*</span>ѧ��
								</th>
								<td align="left">
									<html:select property="xn" styleId="xn" disabled="false" 
											style="width:125px;">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
									</html:select>
								</td>
								<th align="right">
									<span class="red">*</span>ѧ��
								</th>
								<td align="left">
									<html:select property="xq" styleId="xq" disabled="false" 
											style="width:125px;">
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								<span class="red">*</span>�������
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��<br />
								��ϸ���<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="xlxsxxqk" styleId="xlxsxxqk" style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveAction('save');">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>

