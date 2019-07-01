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
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("[name=splc]").change(function() {// ��˿��Ƽ���,����������̽�����ʾ
				setLckz(jQuery(this).val());
			});
			jQuery("[name=splc]").change();
		});
		//���̿���
		function setLckz(value) {
			if (value == "") {
				jQuery("#qxfwTd").html("");
				return;
			}
			var url = "mrgzkhJcsz.do?method=xmwhShfw";
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

                jQuery("[name=qxfwView]").bind("click",function(){
                    var selectV=jQuery(this).val();
                    jQuery("[name=qxfwView]:checked").each(function(){
                        var sv=jQuery(this).val();
                        if(sv!=selectV){
                            jQuery(this).removeAttr("checked");
                        }
                    });
                });
			}, 'json');
		}
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
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("�뽫��������д������");
					return false;
			  }
			  
				//��ȡȨ�޷�Χ
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
//			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#qxfw").val(qxfwIds);
			    
			  var id = jQuery("#id").val();
				var url = id == "" ? "mrgzkhJcsz.do?method=save" : "mrgzkhJcsz.do?method=update";
		      ajaxSubFormWithFun("GzkhJcszForm",url,function(data){
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
				���˵�λ��˼���: �趨���˵�λ���ʱ�����ݷ�Χ�����<font color="blue">��ѡ</font>�������Ϊѧ��<font color="blue">�����λ���ڲ���</font>�Ľ�ʦ�����<font color="blue">����ѡ</font>�������Ϊ<font color="blue">��������ѧ��</font>�Ľ�ʦ(���������ݹ��˿����ѧ��)��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/mrgzkhJcsz" method="post" styleId="GzkhJcszForm">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="qxfw" styleId="qxfw"/>
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
								<font color="red">*</font>���������д����
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
								��д��ʼʱ��
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
								<font color="red">*</font>���������˿���
							</th>
							<td width="60%">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th width="35%">
								�����ʼʱ��
							</th>
							<td width="60%">
								<html:text property="shkssj" styleId="shkssj"
									onfocus="showCalendar('shkssj','y-mm-dd',true,'shjssj');" />
								&nbsp;��
								<html:text property="shjssj" styleId="shjssj"
									onfocus="showCalendar('shjssj','y-mm-dd',false,'shkssj');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>������������
							</th>
							<td width="60%">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr id="qxfwTr">
							<th>
								���˵�λ��˿���
							</th>
							<td id="qxfwTd" colspan="3"></td>
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

