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

				var splc = jQuery("#splc").val();
				if (splc == ""){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				
				var id = jQuery("#id").val();
				var url = id == "" ? "zbglCssz.do?method=save" : "zbglCssz.do?method=update";
				ajaxSubFormWithFun("csszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
		</script>
	</head>
  	<body >
	<html:form action="/zbglCssz" method="post" styleId="csszForm">
			<html:hidden property="id" styleId="id"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>��������</span></th>
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
						<th>
							��ʼʱ��
						</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"
										onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										readonly="true"></html:text>
								��
								<html:text  property="sqjssj" styleId="sqjssj"
											onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									 		readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>�������
						</th>
						<td>
							<html:select property="splc" styleId="splc">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
							</html:select>
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
