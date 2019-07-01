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
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}

				var kgzt = radioBox.val();
				var sqkssj = jQuery("#sqkssj").val();
				var sqjssj = jQuery("#sqjssj").val();
				
				if (kgzt == "1" && (sqkssj=="" || sqjssj=="")){
					showAlertDivLayer("请将开放时间填写完整！");
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>时间设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>申请开关</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>开放填写时间</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"
										onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										onblur="dateFormatChg(this)"
										readonly="true"></html:text>
								至
								<html:text  property="sqjssj" styleId="sqjssj"
											onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
											onblur="dateFormatChg(this)"
									 		readonly="true"></html:text>
									
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">		
			          <logic:equal name="writeAble" value="yes">		            
						<button type="button" class="button" onclick="saveForm();" >
							保 存
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
