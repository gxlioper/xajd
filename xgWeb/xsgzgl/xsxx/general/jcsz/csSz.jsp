<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		
		function save(){

			var sqkglength = jQuery("[name=kgzt]:checked").length;
			if(sqkglength==0){
				showAlertDivLayer("���������뿪��!");
				return false;
			}

			var shkglength = jQuery("[name=shkg]:checked").length;
			if(shkglength==0){
				showAlertDivLayer("��������˿���!");
				return false;
			}
			var shlid = jQuery("#shlid").val();
			var sqkg = jQuery("[name=kgzt]:checked").val();
			
			if ("1"==sqkg && (shlid == "" || shlid == null)){
				showAlertDivLayer("��ѡ���������!");
				return false;
			}
			
			var url="general_xsxx_jcsz.do?method=csSz&doType=save&shlid="+shlid;
			refreshForm(url);
		}
		
		
		function onShow() {
			jQuery("#sqkssj").attr("disabled",false);
			jQuery("#sqjssj").attr("disabled",false);
			jQuery("#shkssj").attr("disabled",false);
			jQuery("#shjssj").attr("disabled",false);
			var sqkg = document.getElementsByName("kgzt");
			for(var i=0;i<sqkg.length;i++){
				if(sqkg[i].checked&&sqkg[i].value=="n"){
					jQuery("#sqkssj").attr("disabled","disabled");
					jQuery("#sqjssj").attr("disabled","disabled");
				}
			}
			var shkg = document.getElementsByName("shkg");
			for(var i=0;i<shkg.length;i++){
				if(shkg[i].checked&&shkg[i].value=="n"){
					jQuery("#shkssj").attr("disabled","disabled");
					jQuery("#shjssj").attr("disabled","disabled");
				}		
			}
		}
		jQuery(function(){
			onShow();
		})

		</script>
	</head>
	<body >
		<html:form action="/general_xsxx_jcsz" method="post">
		<input type="hidden" name="shjg" id="shjg" value="${rs.shjg }"/>
		<input type="hidden" name="sqcs" id="sqcs" value="${xsxxJcszForm.sqcs }"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						����ѧ����Ϣ�޸Ŀ��أ�����ѧ���޸�ѧ����Ϣ������ѧ����Ϣ�޸������������δ����ʱ�������޸��������
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->

			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th style="width:40%"><font color="red">*</font>	���뿪��</th>
							<td  style="width:60%">
							<html:radio property="kgzt" value="y" onclick="onShow()">����</html:radio>
							<html:radio property="kgzt" value="n" onclick="onShow()">�ر�</html:radio>
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
					<tr>
						<th style="width:40%"><font color="red">*</font>	��˿���</th>
							<td  style="width:60%">
							<html:radio property="shkg" value="y" onclick="onShow()">����</html:radio>
							<html:radio property="shkg" value="n" onclick="onShow()">�ر�</html:radio>
							</td>
					</tr>
					<th>��˿���ʱ��</th>
						<td>
							<html:text  property="shkssj" styleId="shkssj"   size="10"
									onclick="return showCalendar('shkssj','y-mm-dd',true,'shjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="shjssj" styleId="shjssj"   size="10"
									onclick="return showCalendar('shjssj','y-mm-dd',false,'shkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
					</tr>
					
					
					<tr id="shlc" >
							<th style="width:40%">
								<font color="red">*</font>
								�������
							</th>
								<logic:present name="rs">
								<logic:equal name="rs" property="shjg" value="0">
									<td  style="width:60%">
										<html:select property="shlid" styleId="shlid"  style="width:240px">
											<html:option value="wxsh">�������</html:option>
											<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
										</html:select>
									</td>
							</logic:equal>
							<logic:notEqual value="0" name="rs" property="shjg">
								<td  style="width:60%" title="����ѧ����Ϣ�޸����������δ���������ܽ����޸ģ�">								
									<html:select property="shlid" styleId="shlid"  style="width:240px" disabled="true">
										<html:option value="wxsh">�������</html:option>
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
								</td>
							</logic:notEqual>
							</logic:present>
							<logic:notPresent name="rs">
								<td  style="width:60%">
									<html:select property="shlid" styleId="shlid" style="width:240px">
										<html:option value="wxsh">�������</html:option>
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
								</td>
							</logic:notPresent>
							
							
							
						</tr>
						
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									��"<font color="red">*</font>"����Ϣ����¼��
								</div>
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
