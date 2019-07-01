<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			/*ȥ������*/
			jQuery(function(){
					var kgbz = jQuery("#kgbz").val();
					kgbz = kgbz.replaceAll("<br/>","\n");
					jQuery("#kgbz").html(kgbz);
			});
			/*����*/
			function saveForm() {
				var url = "xpjpy_xmwh.do?method=saveFormSjkg";
				ajaxSubFormWithFun("zjdxXmwhForm", url, function(data) {
					showAlert(data["message"],{},{"clkFun":  function(tag) {
						if (tag == "ok") {
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_xmwh" method="post" styleId="zjdxXmwhForm">
			<html:hidden property="xmdm" styleId="xmdm" />
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmmc}&nbsp;</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style='' id ="kgszDiv">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������ʱ��</th>
							<td>
								<html:text  property="sqkssj" styleId="sqkssj"
											onfocus="showCalendar('sqkssj','yyyy-MM-dd HH:mm',true,'sqjssj');" 
											readonly="true" style="width:120px;" >
								</html:text>
								&nbsp;��
								<html:text  property="sqjssj" styleId="sqjssj"
											onfocus="showCalendar('sqjssj','yyyy-MM-dd HH:mm',false,'sqkssj');" 
									 		readonly="true" style="width:120px;">
								</html:text>
							</td>
						</tr>
						<tr>
							<th>���뿪��</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								����������д˵��<br/>
								<font color="red">(����¼��100��)</font>
							</th>
							<td>
								<html:textarea property="kgbz" styleId="kgbz"  style="width:98%" rows="5" onblur="checkLen(this,100);"/>
							</td>
						</tr>
						<tr>
							<th>�������ʱ��</th>
							<td>
								<html:text  property="shkssj" styleId="shkssj"
											onfocus="showCalendar('shkssj','yyyy-MM-dd HH:mm',true,'shjssj');" 
											readonly="true" style="width:120px;" >
								</html:text>
								&nbsp;��
								<html:text  property="shjssj" styleId="shjssj"
											onfocus="showCalendar('shjssj','yyyy-MM-dd HH:mm',false,'shkssj');" 
									 		readonly="true" style="width:120px;">
								</html:text>
							</td>
						</tr>
						<tr>
							<th>��˿���</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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