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
			  var kssj=jQuery("#kssj").val();
			  var jssj=jQuery("#jssj").val();
			  if(jQuery.trim(kssj)==""||jQuery.trim(jssj)==""){
				  showAlertDivLayer("�뽫��������д������");
					return false;
			  }
			  var id = jQuery("#id").val();
				var url = id == "" ? "xsxwkhCssz.do?method=save" : "xsxwkhCssz.do?method=update";
		      ajaxSubFormWithFun("xsxwCsszForm",url,function(data){
		    	  showAlertDivLayer(data["message"]);
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
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		</div>
		<html:form action="/xsxwkhCssz" method="post" styleId="xsxwCsszForm">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="xn" styleId="xn"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody >
					<tr>
							<th width="35%">
								��������ѧ��
							</th>
							<td>
							${xsxwCsszForm.xn}
						</td>
						</tr>
					<tr>
							<th width="35%">
								<font color="red">* </font>����������ʼʱ��
							</th>
							<td>
							<html:text  property="kssj" styleId="kssj"   size="10"
									onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" 
									readonly="true"></html:text>
								-
								<html:text  property="jssj" styleId="jssj"   size="10"
									onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" 
									readonly="true"></html:text>
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

