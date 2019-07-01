<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="xsgzgl/rcsw/dxsylbx/ylbxjcsz/js/ylbxJcsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
				jQuery(document).ready(function(){ 
					changeSqkg();
				});
		
				//���¸�λ����
				function changeSqkg(){
					var sqkg = jQuery("[name=fxkg]:checked").val();
					if("1"==sqkg){
						jQuery("table select,input:not(input:radio[name=fxkg])").attr("disabled",false);
					}else if("0"==sqkg){
						jQuery("table select,input:not(input:radio[name=fxkg])").attr("disabled","disabled");						
					}
				}

			
				function saveJcsz(){					
					var sqkglength = jQuery("[name=fxkg]:checked").length;
					if(sqkglength==0){
						showAlertDivLayer("���������뿪��!");
						return false;
					}
					var xxdm = jQuery("#xxdm").val();
					if("13011" == jQuery("#xxdm").val()){
						/*ȡ�����ʱ��-��ʼʱ��*/
						var fxtxkssj = jQuery("#fxtxkssj").val();
						if (null == fxtxkssj || "" == fxtxkssj){
							showAlertDivLayer("�����÷�У��ʼʱ��!");
							return false;
						}
						var fxtxzzsj=jQuery("#fxtxzzsj").val();
						if (fxtxzzsj==null || "" == fxtxzzsj){
							showAlertDivLayer("�����÷�У��ֹʱ��!");
							return false;
						}
					}
					var fxdm = jQuery("#fxdm").val();
					var sqkg = jQuery("[name=fxdm]:checked").val();
					
					if ("1"==sqkg && (fxdm == "" || fxdm == null)){
						showAlertDivLayer("��ѡ���������!");
						return false;
					}
					var url = "jlkjxy_jqfxjcsz.do?method=saveJqfxJcsz";
					ajaxSubFormWithFun("jlkjxy_jqfxjcszForm",url,function(data){
						showAlertDivLayer(data["message"]);
					});					
				}
	
		</script>
	</head>
	<body >
		<html:form action="/jlkjxy_jqfxjcsz" method="post" styleId="jlkjxy_jqfxjcszForm" >
		<input name="xxdm" type="hidden" id = "xxdm" value="${xxdm}"/>
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
										<html:radio property="fxkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
							</logic:present>
						</td>
					</tr>
					<tr>
						<logic:notEqual name="xxdm" value="13011">
							<th>���뿪��ʱ��</th>
						</logic:notEqual>
						<logic:equal name="xxdm" value="13011">
							<th><span class="red">*</span>�����ʱ��</th>
						</logic:equal>
						<td>
							<html:text  property="fxtxkssj" styleId="fxtxkssj"   size="10"
									onclick="return showCalendar('fxtxkssj','y-mm-dd',true,'fxtxzzsj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="fxtxzzsj" styleId="fxtxzzsj"   size="10"
									onclick="return showCalendar('fxtxzzsj','y-mm-dd',false,'fxtxkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>															
									
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>��У���</th>
						<td>
							<html:select property="fxdm" styleId="fxdm" disabled="false" >
							<html:options collection="jqfxmcList" property="fxdm"
								labelProperty="fxmc" />
						</html:select>
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
