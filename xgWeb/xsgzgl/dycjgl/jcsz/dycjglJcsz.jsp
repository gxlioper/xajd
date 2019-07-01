<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		function saveForm(){
			  var id = jQuery("#id").val();
				var checkId = 'xn-xqdm';
				if(!checkNotNull(checkId)){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				var url = id == "" ? "dycjgl_jcsz.do?method=save" : "dycjgl_jcsz.do?method=update";
		      	ajaxSubFormWithFun("JcszForm",url,function(data){
		      		showAlertDivLayer(data["message"],{},{"clkFun":function(){
		      			location.href=location.href;
					}});
				});
		  }
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
		   </p>
		</div>
		<!-- ���� end-->
		</div>
		<html:form action="/dycjgl_jcsz" method="post" styleId="JcszForm">
		<html:hidden property="id" styleId="id"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="35%">
								<font color="red">*</font>�����ɼ�¼�뿪��
							</th>
							<td width="60%">
								<logic:present name="onOff">
									<logic:iterate id="o" name="onOff">
										<label>
											<html:radio property="kqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>�����ɼ�¼������</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
								<html:select property="xqdm" styleId="xqdm">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
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

