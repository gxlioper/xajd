<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/dyzp/js/dyzp.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				
				if (jQuery("#xn").val() == "" || jQuery("#xq").val() == "" || jQuery("#djdm").val() == "" ){
					showAlert("�뽫��������д������");
					return false;
				}
				if(!checklenbyzpnr()){
					return false;
				}
				var _fun = function(){
					ajaxSubFormWithFun("jdqkForm",url,function(data){
						showAlert(data["message"],{},{clkFun:function(){
							refershParent();
						}});
					});
				};
				
				if (jQuery("#yxn").val() != jQuery("#xn").val() && jQuery("#yxq").val() != jQuery("#xq").val()){
					jQuery.post("xsxxDyxjZpjg.do?method=getCount",{xh:jQuery("#xh").val(),xn:jQuery("#xn").val(),xq:jQuery("#xq").val()},function(data){
						if (Number(data) == 0){
							_fun();
						} else {
							showAlert("���ڵ�ǰѧ�ꡢѧ�������������������");
						}
					},"json");
				} else {
					_fun();
				}

			
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxxDyxjZpjg" method="post" styleId="jdqkForm">
			<html:hidden property="id" />
			<input type="hidden" id="yxn" valueu="${dyxjZpjgModel.xn }"/>
			<input type="hidden" id="yxq" valueu="${dyxjZpjgModel.xq }"/>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�����ȼ�</th>
							<td colspan="3">
								<html:select property="djdm" styleId="djdm">
									<html:option value=""></html:option>
									<html:options collection="zpdjList" property="djdm" labelProperty="djmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��������
							<br/><font color="red">(��400-1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="zpnr" styleId="zpnr" 
											   onblur="checklenbyzpnr();"
											   style="width:99%;" rows="8"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm('xsxxDyxjZpjg.do?method=update');">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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

