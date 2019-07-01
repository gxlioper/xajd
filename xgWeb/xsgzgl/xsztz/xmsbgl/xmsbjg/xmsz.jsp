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
			var url = "xmsbXmsbjg.do?method=xmrsKzfw";
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
			var rskzjb=jQuery("#rskzjb").val();
			var rskzjbs=rskzjb.split(",");
			for(var i=0;i<rskzjbs.length;i++){
				if(V==rskzjbs[i]){
					return true;
				}
			}
			return false;
		}
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  var qxfwView = jQuery("[name=qxfwView]:checked").val();
			  if(jQuery.trim(splc)=="" || qxfwView == null || qxfwView ==""){
				  showAlert("�뽫��������д������");
					return false;
			  }
			//��ȡȨ�޷�Χ
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#rskzjb").val(qxfwIds);
		     var url = "xmsbXmsbjg.do?method=xmsz&type=save";
		      ajaxSubFormWithFun("XmsbjgForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		 
		    	 }
				});
		  }
		</script>
	</head>
	<body>
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��
					<font color="red">${xmmc} &nbsp; <span id="spztTip"
						style="display: none;"> ��ǰ��Ŀ����ѧ�����룬����������޸� </span> </font>
					ֻ�������뿪�ؿ���״̬����������ʱ����Ч��Χ�ڽ������ú󣬲�Ϊ�������á�״̬�������Ϊ��δ���á�״̬
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<html:hidden property="jgid" styleId="jgid" />
			<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="rskzjb" styleId="rskzjb"/>
			<input type="hidden" name="oldsplc" id="oldsplc" value="${oldsplc}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="120px">
								<font color="red">*</font>���뿪��
							</th>
							<td colspan="3">
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
							<th>
								��Ŀ������ʼʱ��
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','ymmdd',true,'sqjssj');" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','ymmdd',false,'sqkssj');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr id="qxfwTr">
							<th>
								<font color="red">*</font>�������Ƽ���
							</th>
							<td id="qxfwTd" colspan="3"></td>
						</tr>
					</tbody>
				</table>

	<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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

