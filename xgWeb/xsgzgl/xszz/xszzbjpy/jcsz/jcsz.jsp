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
		<script type="text/javascript">
			jQuery(document).ready(function(){ 
				changeBjpykg();
			});
			
			function changeBjpykg(){
				var bjpykg = jQuery("[name=bjpykg]:checked").val();
				if("1"==bjpykg){
					jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled",false);
					
				}else if("0"==bjpykg){
					jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled","disabled");
					
				}
			}
			
			function saveJcsz(){
				var bjpykglength = jQuery("[name=bjpykg]:checked").length;
				if(bjpykglength==0){
					showAlertDivLayer("�����ð༶���鿪��!");
					return false;
				}
				var bjpykg = jQuery("[name=bjpykg]:checked").val();
				var xzrsxx = jQuery.trim(jQuery("#xzrsxx").val());
				if("1"==bjpykg && xzrsxx == ''){
					showAlertDivLayer("С���������޲���Ϊ��!");
					return false;
				}
				var url = "xszz_xszzbjpy_jcszgl.do?method=saveJcsz";
				ajaxSubFormWithFun("jcszForm",url,function(data){
					showAlertDivLayer(data["message"]);
				});
			}
		</script>
	</head>
  <body >
	<html:form action="/xszz_xszzbjpy_jcszgl" method="post" styleId="jcszForm">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist" id="bjpy_table">
			<thead>
				<tr>
					<th colspan="2"><span>�༶��������</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="40%"><span class="red">*</span>����</th>
					<td>
					   
					   <logic:present name="onoffList">
								<logic:iterate id="o" name="onoffList" >
									<html:radio property="bjpykg" onclick="changeBjpykg();" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>								
							</logic:present>
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<td>
						<html:text  property="bjpykssj" styleId="bjpykssj"   size="10"
								onclick="return showCalendar('bjpykssj','y-mm-dd',true,'bjpyjssj');" 
								onblur="dateFormatChg(this)" readonly="true"></html:text>
							-
							<html:text  property="bjpyjssj" styleId="bjpyjssj"   size="10"
								onclick="return showCalendar('bjpyjssj','y-mm-dd',false,'bjpykssj');" 
								onblur="dateFormatChg(this)" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>С����������</th>
					<td>
						<html:text property="xzrsxx" styleId="xzrsxx" maxlength="3" style="width: 25px;" onkeyup="checkInputData(this);"></html:text>%���༶�����ٷֱȣ�"0"��ʾ�����ƣ�
					</td>
				</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">			            
					<logic:equal name="writeAble" value="yes">	
					<button type="button" class="button2" onclick="saveJcsz();return false;" style="width:80px"
						id="btn_save">
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
