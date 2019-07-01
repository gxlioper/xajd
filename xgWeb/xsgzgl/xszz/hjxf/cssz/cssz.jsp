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
				
				saveForm();
			
			})
			/*
			jQuery("[name=splc]").change(function() {// ��˿��Ƽ���,����������̽�����ʾ
				setLckz(jQuery(this).val());
			});
			jQuery("[name=splc]").change();*/
		});
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("�뽫��������д������");
				  return false;
			  }
				//��ȡȨ�޷�Χ
				/*
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#qxfw").val(qxfwIds);*/
			    
			  var id = jQuery("#id").val();
				var url = id == "" ? "hjxf_cssz.do?method=save" : "hjxf_cssz.do?method=update";
		        ajaxSubFormWithFun("CsszhjxfForm",url,function(data){
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
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ѧ��������Ҫ�������뿪���Լ��������ֹʱ�䣬�������������Ӧ����ֹʱ������ɣ�����ѧ��<br/>
				�������������λ����ϵͳά���˵��µ���������ά���н������ú�ά����
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/hjxf_cssz" method="post" styleId="CsszhjxfForm">
		<html:hidden property="id" styleId="id"/>
		<%--<html:hidden property="qxfw" styleId="qxfw"/>
			--%><div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���������</span>
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
								������дʱ��
							</th>
							<td width="60%">
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" onblur="dateFormatChg(this);" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');"  onblur="dateFormatChg(this);"/>
							</td>
						</tr>
						<tr>
							<th width="35%">
								�����ֹʱ��
							</th>
							<td width="60%">
								<html:text property="jqjzsj" styleId="jqjzsj"
									onfocus="showCalendar('jqjzsj','y-mm-dd');"  onblur="dateFormatChg(this);"/>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>�������
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

