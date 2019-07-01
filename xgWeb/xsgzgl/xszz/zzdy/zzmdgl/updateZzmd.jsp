<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzdy/zzmdgl/js/zzmdgl.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
		jQuery(function(){
			var bgqzt='${ZzdyMdglForm.ffzt}';
			jQuery("#bghzt option[value='"+bgqzt+"']").attr("selected",true);
			changeFfzt();
			});
		
		</script>
	</head>
	<body>
		<html:form action="/xszz_zzdyzzmdgl" method="post"
			styleId="ZzdyMdglForm" onsubmit="return false;">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="bgqje" styleId="bgqje" value='${ZzdyMdglForm.yffje}'/>
			<html:hidden property="bgqzt" styleId="bgqzt" value='${ZzdyMdglForm.ffzt}'/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto'>
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table class="dateline" width="100%">
						<thead>
						<tr>
							<th width="30%">
								��Ŀ����
							</th>
							<th width="30%">
								��Ŀ���
							</th>
							<th width="40%">
								��Ŀ��������
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>
							${rs.xmmc }
							</td>
							<td>
							${rs.zzzje }
							</td>
							<td>
							${rs.pdzq }
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ʷ���ż�¼</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table class="dateline" width="100%">
						<thead>
						<tr>
							<th width="30%">
								��Ŀ����
							</th>
							<th width="30%">
								��������
							</th>
							<th width="40%">
								���Ž��
							</th>
						</thead>
						<tbody>
						<logic:iterate id="i" name="ffjlList" indexId="index01">
							<tr>
							<td>${i.xmmc}</td>
							<td>${i.ffyf}</td>
							<td>${i.ffje}</td>
							</tr>
						</logic:iterate>
						<logic:empty name="ffjlList">
						<tr >
							<td colspan="3" align="center">�޷��ż�¼!</td>
							</tr>
						</logic:empty>
						</tbody>
						</table>
						</div>
						</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th>����״̬</th>
							<td>
							 <html:select property="bghzt" onchange="changeFfzt()" styleId="bghzt" style="width:120px">
								<option value="1">��������</option>
								<option value="0">��ͣ����</option>
								<option value="-1">��ֹ����</option>
								</html:select>
							</td>
						<th><font color="red">*</font>���Ž��</th>
							<td>
							<html:text  style="width:90px" property="bghje" styleId="bghje" maxlength="10"  onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td colspan="3">
								<html:textarea property="bgly" style="width:98%;margin-top:5px;" rows="5"
											   onblur="checkLen(this,200);" styleId="bgly"
								></html:textarea>
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <div style="height:35px"></div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm('update');return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

