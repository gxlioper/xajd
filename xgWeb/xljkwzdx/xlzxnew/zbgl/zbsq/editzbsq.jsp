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
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/comm/js/comm.js"></script>
		<script type="text/javascript" defer="defer">

			function saveAction(type){
					
				var checkids = "ztqk";
				
				if(!checkNotNull(checkids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
					return false;
				}
				
				if(jQuery("[name='xh']").length == 0){
					showAlert("��������дһλѧ��������״��!");
					return false;
				}
				var message = null;
				jQuery("[name='zbwtms']").each(function(){
					if(jQuery.trim(this.value).length == 0){
						message = "��Ҫ������������Ϊ�գ�";
						return false;
					}else if(this.value.length > 500){
						message = "��Ҫ����������500�֣�";
						return false;
					}	
				})
				if(message){
					showAlert(message);
					return false;
				}
				var url = "xlzxnew_zbsb.do?method=saveSbsq&saveFlag="+type;
				ajaxSubFormWithFun("ZbsbForm",url,function(data){
					if(data["message"] == "����ɹ���"){
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
		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_zbsb" method="post" styleId="ZbsbForm">
			<html:hidden property="sbzbid"  value="${zbrc.zbid}"/>
			<html:hidden property="sbsqid" />
			<%--<html:hidden property="splcid" value="${sbxx.splcid}"/>
			<html:hidden property="splcidpz" value="${sbxx.splcidpz}"/>
			--%><div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${zbrc.xn}<input type="hidden" name="xn" value="${zbrc.xn}" />
								</td>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xqmc}<input type="hidden" name="xq" value="${zbrc.xq}"/>
								</td>	
							</tr>
							<tr>
								<th>
									�ܴ�
								</th>
								<td>
									${zbrc.zbzc}
								</td>
								<th>
									��ֹ����
								</th>
								<td>
									${zbrc.zbksrq} ��  ${zbrc.zbjsrq}
								</td>
							</tr>
							<tr>
								<th>�༶����</th>
								<td>${bjxx.bjmc}
									<input type="hidden" name="bjdm" id="bjdm" value="${bjxx.bjdm}"/>
								</td>
								<th>������</th>
								<td>${bjxx.xm}</td>
							</tr>
						<tr>
							<th>
								<span class="red">*</span>
								�����ڰ༶������<br />
								�ش��¼�(1000��)<br />
								<font color="red">(��1000��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="4"> <!-- style="margin-top:2px;margin-left:30px"  -->
								<button type="button" onclick="addRowDialog();return false;" style="float:left">����</button>
									<button type="button" onclick="delRow();return false;" style="float:left">ɾ��</button>
							</th>
						</tr>
						<tr>
							<th colspan="5">
								<table width="100%" >
									<thead>
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='20%' style="text-align:center">ѧ��</th>
											<th width='20%' style="text-align:center">����</th>
											<th width='15%' style="text-align:center">�Ա�</th>
											<th width='40%' style="text-align:center"><font class="red">*</font>��Ҫ��������(��500��)</th>
										</tr>
									</thead>
									<tbody id="tablebody">
										<logic:iterate id="i" name="wtryInfo">
											<tr>
												<th style="text-align:center" ><input name='chk' type="checkbox" /></th>
												<td style="text-align:center"><input name='xh' type='hidden' value='${i.xh}' style='width:90%'/><label name = 'xhname'>${i.xh}</label></td>
												<td style="text-align:center">${i.xm}</td>
												<td style="text-align:center">${i.xb}</td>
												<td style='text-align:center'><textarea name = 'zbwtms' style='width:90%' rows='2'>${i.zbwtms}</textarea></td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveAction('save');">
										����ݸ�
									</button>
									<button id="submit_button" type="button"  onclick="saveAction('submit');">
										�ύ�ϱ�
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

