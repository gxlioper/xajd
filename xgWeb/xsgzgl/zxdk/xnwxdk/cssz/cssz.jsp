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
	    <script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
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
		//���̿���
		/*
		function setLckz(value) {
			if (value == "") {
				jQuery("#qxfwTd").html("");
				return;
			}
			var url = "gygl_xyzsjcszgl.do?method=xmwhShfw";
			jQuery.post(url, {
				value : value
			}, function(data) {
				var sHtml = "";
				if (data != null) {
					for ( var i = 0; i < data.length; i++) {
						var o = data[i];
						sHtml += "<label><input type='checkbox' name='qxfwView' value='"
								+ o.spgwdm + "'/>";
						sHtml += o.spgwmc;
						sHtml += "</label>";
						if (i != data.length - 1) {
							sHtml += "<br/>";
						}
					}
				}
				jQuery("#qxfwTd").html(sHtml);
				var isH=false;
			    jQuery("input[name=qxfwView]").each(function(){
			    	var V=jQuery(this).val();
			    	if(isHaveV(V)){
			    		jQuery(this).attr("checked","checked");
			    		isH=true;
			    	}
			    });
				
			
			}, 'json');
		}*/
		function isHaveV(V){
			var qxfw=jQuery("#qxfw").val();
			var qxfws=qxfw.split(",");
			for(var i=0;i<qxfws.length;i++){
				if(V==qxfws[i]){
					return true;
				}
			}
			return false;
		}
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  var jesx=jQuery("#jesx").val();
			  if(jQuery.trim(splc)=="" || jQuery.trim(jesx)==""){
				  showAlertDivLayer("�뽫��������д������");
				  return false;
			  }
              if(!checkMoney2(jQuery.trim(jesx))){
            	  showAlertDivLayer("����д��ȷ�Ľ�");
            	  return false;
			  }
			  if(jQuery.trim(jesx).length > 10){
				  showAlertDivLayer("���ܳ���10λ��");
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
				var url = id == "" ? "xnwxdk_cssz.do?method=save" : "xnwxdk_cssz.do?method=update";
		        ajaxSubFormWithFun("XnwxdkCsszModel",url,function(data){
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
				У����Ϣ����������Ҫ�������뿪���Լ��������ֹʱ�䣬�������������Ӧ����ֹʱ������ɣ���Ϣ����<br/>
				�������������λ����ϵͳά���˵��µ���������ά���н������ú�ά����
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/xnwxdk_cssz" method="post" styleId="XnwxdkCsszModel">
		<html:hidden property="id" styleId="id"/>
		<%--<html:hidden property="qxfw" styleId="qxfw"/>
			--%><div style=''>
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
									onfocus="showCalendar('sqkssj','y-mm-dd');" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','y-mm-dd');" />
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
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td colspan="3">
								<html:text property="jesx" onkeyup="checkInput(this)" onblur="ismoney(this)" styleId="jesx" maxlength="10" />
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

