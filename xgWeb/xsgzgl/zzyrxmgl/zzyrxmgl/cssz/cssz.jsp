<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){ 
				changeSqkg();
			});

function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
		
	}else if("0"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");
		
	}
}

function saveJcsz(){
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	if(sqkglength==0){
		showAlertDivLayer("���������뿪��!");
		return false;
	}
	var sqkg = jQuery("[name=sqkg]:checked").val();
	var url = "zzyrxmglcssz.do?method=saveCssz";
	ajaxSubFormWithFun("jcsszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
}
		</script>
	</head>
	<body >
		<html:form action="/zzyrxmglcssz" method="post" styleId="jcsszForm" >
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
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="sqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>���뿪��ʱ��</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"   size="10"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
					</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">		            
						<button type="button" class="button2" onclick="saveJcsz();return false;" id="btn_save">
							�� ��
						</button>
						</logic:equal>
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				</tbody>
			</table>
		
				
		
			</div>
		</html:form>
	</body>
</html>
