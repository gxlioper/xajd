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
				if(!checkNotNull("splc1-splc2")){
					showAlertDivLayer("�뽫��<font class='red'>*</font>����Ŀ��д������");
					return false;
				}
				/*�Ƿ�����ֶ��ж�*/
				var sfsh = jQuery("input[name='sfsh']:checked").val();
				if("" == sfsh || null == sfsh){
					showAlertDivLayer("��ѡ��������Ŀ�Ƿ���ˣ�");
					return false;
				}
				saveForm();
			})
		});
		
		function saveForm(){	  
		  var id = jQuery("#id").val();
		  var url = "jskp_cssz.do?method=saveCssz";
	      ajaxSubFormWithFun("jskpCsszForm",url,function(data){
	    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
		    	  document.location.href = "pjpy_jskp_cssz.do";
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
				��������������걨���������̣�
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/jskp_cssz" method="post" styleId="jskpCsszForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>���������������
							</th>
							<td width="65%">
								<logic:notEmpty name="lxsplc" property="id">
									<input type="hidden" name="id" value="${lxsplc.id}" />
								</logic:notEmpty>
								<input type="hidden" name="lx" value="lx" />
								<html:select property="splc" styleId="splc1" value="${lxsplc.splc}">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>��Ŀ�걨�������
							</th>
							<td width="65%">
								<logic:notEmpty name="sbsplc" property="id">
									<input type="hidden" name="id" value="${sbsplc.id}" />
								</logic:notEmpty>
								
								<input type="hidden" name="lx" value="sb" />
								<html:select property="splc" styleId="splc2" value="${sbsplc.splc}">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>������Ŀ�Ƿ����
							</td>
							<td>
								<input type="radio" name="sfsh" property="sfsh" value="1" ${sbsplc.sfsh == "1" ? "checked" : ""}/>��
								<input type="radio" name="sfsh" property="sfsh" value="0" ${sbsplc.sfsh == "0" ? "checked" : ""}/>��
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

