<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("#buttonSave").click(function(){
				if(!checkNotNull("splc")){
					showAlertDivLayer("�뽫��<font class='red'>*</font>����Ŀ��д������");
					return false;
				}
				saveForm();
			
			})
		});
		function saveForm(){	  
			  var id = jQuery("#id").val();
				var url = "gypynew_cssz.do?method=saveCssz";
		      ajaxSubFormWithFun("CsszForm",url,function(data){
		    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
			    	  document.location.href = "gygl_gypynew_cssz.do";
					}});;
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
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				�������ù�Ԣ����������������̣�
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/jskp_cssz" method="post" styleId="CsszForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>�������
							</th>
							<td width="65%">
								<logic:notEmpty name="splcMap" property="id">
									<input type="hidden" name="id" value="${splcMap.id}" />
								</logic:notEmpty>
								<html:select property="splc" styleId="splc" value="${splcMap.splc}">
									<option value=""></option>
									<html:options collection="splcList" property="splc"
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
									<button type="button" onclick=";" id="buttonSave">
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

