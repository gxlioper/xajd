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
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("�뽫��������д������");
					return false;
			  }
			  var id = jQuery("#id").val();
				var url = id == "" ? "xsxx_zyfwgl_jcsz.do?method=save" : "xsxx_zyfwgl_jcsz.do?method=update";
		      	ajaxSubFormWithFun("zyfwJcszForm",url,function(data){
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
		<html:form action="/xsxx_zyfwgl_jcsz" method="post" styleId="zyfwJcszForm">
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
								<font color="red">*</font>���뿪��
							</th>
							<td width="60%">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th width="35%">
								������ֹʱ��
							</th>
							<td width="60%">
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>���������
							</th>
							<td width="60%">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
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

