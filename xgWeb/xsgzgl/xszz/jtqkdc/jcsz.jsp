<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var radioBox = jQuery("input:radio:checked");

				if (radioBox.length == 0){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}

				var kgzt = radioBox.val();
				var sqkssj = jQuery("#sqkssj").val();
				var sqjssj = jQuery("#sqjssj").val();
				
				if (kgzt == "1" && (sqkssj=="" || sqjssj=="")){
					showAlertDivLayer("�뽫����ʱ����д������");
					return false;
				}
				
				var url = "xszz_jtqkdc_jcsz.do?method=saveJcsz";
				ajaxSubFormWithFun("jcszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}

			
		</script>
	</head>
  	<body >
	<html:form action="/xszz_jtqkdc_jcsz" method="post" styleId="jcszForm">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>ʱ������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>���뿪��</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>������дʱ��</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"
										onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										onblur="dateFormatChg(this)"
										readonly="true"></html:text>
								��
								<html:text  property="sqjssj" styleId="sqjssj"
											onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
											onblur="dateFormatChg(this)"
									 		readonly="true"></html:text>
									
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">		
			          <logic:equal name="writeAble" value="yes">		            
						<button type="button" class="button" onclick="saveForm();" >
							�� ��
						</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
