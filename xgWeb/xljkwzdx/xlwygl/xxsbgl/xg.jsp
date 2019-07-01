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
					
				var checkids = "ztqk";
				
				if(!checkNotNull(checkids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
					return false;
				}
				
				var url = "xljk_xlwygl_xxsbglwh.do?method=saveAction&type="+type  ;
					ajaxSubFormWithFun("xlwyglxxsbglForm",url,function(data){

						if((data||{})['code'] == '-1'){
							showAlertDivLayer("��������δ����,����ϵ����Ա!");
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
		</script>
	</head>
	<body>
		<html:form action="/xljk_xlwygl_xxsbglwh" method="post" styleId="xlwyglxxsbglForm">
			<html:hidden property="sblx" value="${sbxx.sblx}"/>
			<html:hidden property="sbzbid"  value="${sbxx.zbid}"/>
			<html:hidden property="xh"/>
			<html:hidden property="sbsj"/>
			<html:hidden property="shzt"/>
			<html:hidden property="splcid"/>
			<html:hidden property="sbsqid"/>
			<%--<html:hidden property="splcid" value="${sbxx.splcid}"/>
			<html:hidden property="splcidpz" value="${sbxx.splcidpz}"/>
			--%><div style='tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
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
									ѧ��
								</th>
								<td>
									${sbxx.xn}
								</td>
								<th>
									ѧ��
								</th>
								<td>
									${sbxx.xqmc}
								</td>	
							</tr>
							<tr>
								<th>
									�ܴ�
								</th>
								<td>
									${sbxx.zbzc}
								</td>
								<th>
									��ֹ����
								</th>
								<td>
									${sbxx.zbqzrq}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="2" name="sbxx" property="sblx">
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xn}
								</td>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xq}
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
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��<br />
								��ϸ���<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="xlxsxxqk" styleId="xlxsxxqk" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
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
									<button id="save_button" type="button"  onclick="saveAction('update');">
										����
									</button>
									<button id="submit_button" type="button"  onclick="saveAction('saveSubmit');">
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

