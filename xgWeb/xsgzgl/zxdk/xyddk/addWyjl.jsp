<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript">
			function saveWyjl(url){
				var xh= jQuery("#xh").val();
				var wyxq = jQuery("#wyxq").val();
				var wysj = jQuery("#wysj").val();

				
				// ���ѧ���Ƿ����
				if (xh==""){
					showAlertDivLayer("��ѡ��ѧ��!");
					return false;
				}

				// ���ΥԼʱ���Ƿ�Ϊ��
				if (wysj==""){
					showAlertDivLayer("ΥԼʱ�䲻����Ϊ��!");
					return false;
				}

				// ���ΥԼ�����Ƿ����
				if (wyxq==""){
					showAlertDivLayer("ΥԼ���鲻����Ϊ��!");
					return false;
				}
				
				jQuery.post("zxdkWyjl.do?method=getCountByXh",{xh:xh},function(data){
					if (Number(data) == 0){
						ajaxSubFormWithFun("wyjlForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("��ѧ���Ѵ���ΥԼ��¼����ȷ�ϣ�");
					}
				},"json");
				
				
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkWyjl" method="post" styleId="wyjlForm">
			<html:hidden property="splcid" value="${cssz.xydshlc }"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
				</table>
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table width="100%" border="0" class="formlist" style="margin-bottom: 35px;">
				<thead>
					<tr>
						<th colspan="4">
							<span>ΥԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tr>
					<th width="15%">�ֻ�����</th>
					<td width="35%">
						<html:text  property="sjhm" styleId="sjhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">QQ����</th>
					<td width="35%">
						<html:text  property="qqhm" styleId="qqhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">΢��</th>
					<td width="35%">
						<html:text  property="wxhm" styleId="wxhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">����</th>
					<td width="35%">
						<html:text  property="dzyx" styleId="dzyx" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">�Ƿ���ϵ��</th>
					<td>
						<html:select property="wyzt" styleId="wyzt" style="width:155px">
						<html:options collection="wyztList" property="wyzt"
							labelProperty="wyzt" />
						</html:select>
					</td>
					<th>
						<font color="red">* </font>ΥԼʱ��
						
					</th>
					<td>
						<html:text property="wysj" styleId="wysj" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">
						<span class="red">*</span>ΥԼ����
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="wyxq" styleId="wyxq" rows="5" cols="75" onblur="checkLen(this,200);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">
						��ע
						<br />
						<font color="red">(��500��)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="bz" styleId="bz" rows="5" cols="75" onblur="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</table>
			<div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveWyjl('zxdkWyjl.do?method=save');">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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