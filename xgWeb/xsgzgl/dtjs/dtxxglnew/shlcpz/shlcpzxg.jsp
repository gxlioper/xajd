<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript"
			src="xsgzgl/dtjs/dtxxglnew/js/shlcpz.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				var checked=jQuery("[name=ksqkg]")[1].checked;
				if(checked){
					jQuery("#qzsj").hide();
				}
				jQuery("[name=ksqkg]").bind("click",function(){
					var val=jQuery(this).val();
					if(val=="1"){
						jQuery("#qzsj").show();
					}else{
						jQuery("#qzsj").hide();
					}
				});
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/shlcpz">
		<html:hidden property="jddm"/>
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�޸������������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								�׶�����
							</th>
							<td>
								<html:select property="jddm" styleId="jddm" disabled="true" >
									<html:options collection="jdList" property="jddm"
										labelProperty="jdmc" />
								</html:select>							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>�������
							</th>
							<td>
								<html:select property="splc" styleId="splc" disabled="false"
									style="width:280px;">
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>					
							</td>
						</tr>
						<tr>
							<th>
								�����뿪��
							</th>
							<td>
								<html:radio property="ksqkg" value="1">����</html:radio>		
								<html:radio property="ksqkg" value="0">�ر�</html:radio>					
							</td>
						</tr>
						<tr id="qzsj">
							<th>
								��ֹʱ��
							</th>
							<td>
							<html:text property="ksqkssj" styleId="ksqkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'ksqjssj');" />
							~
							<html:text property="ksqjssj" styleId="ksqjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'ksqkssj');" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('shlcpz.do?method=update&type=save','jddm-splc');return false;"
										id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
