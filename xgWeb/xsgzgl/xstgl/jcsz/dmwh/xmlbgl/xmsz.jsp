<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/jcsz/dmwh/js/dmwh.js"></script>
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
		});
		function saveFormXmSz(){	  
			  var shlc=jQuery("#shlc").val();
			  if(jQuery.trim(shlc)==""){
				  showAlert("�뽫��������д������");
					return false;
			  }
		     var url = "stglXmlbDmwh.do?method=xmsz&type=save";
		      ajaxSubFormWithFun("XmlbglForm",url,function(data){
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
		<html:form action="/stglXmlbDmwh" method="post" styleId="XmlbglForm">
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��
					<font color="red">${xmmc} &nbsp;</font>
					<br/>ֻ�������뿪�ؿ���״̬����������ʱ����Ч��Χ�ڽ������ú󣬲�Ϊ�������á�״̬�������Ϊ��δ���á�״̬
				</p>
				 
					<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
				
			
			</div>
			<html:hidden property="xmlbdm" styleId="xmlbdm" />
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ�������</span>
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
								������ʼʱ��
							</th>
							<td >
								<html:text property="kssj" styleId="kssj"
									onfocus="showCalendar('kssj','ymmdd',true,'jssj');" />
								&nbsp;��
								<html:text property="jssj" styleId="jssj"
									onfocus="showCalendar('jssj','ymmdd',false,'kssj');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:select property="shlc" styleId="shlc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
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
									<button type="button" onclick="saveFormXmSz();">
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

