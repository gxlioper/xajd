<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
		<script type="text/javascript" defer="defer">


		jQuery(function(){
				jQuery('#save_button').click(function(){
					
					var jlsj = jQuery('#_jlsj').val();
					var jlnr = jQuery('#_jlnr').val();
					
					if (jlsj == "" || jlnr == ""){
						showAlertDivLayer("�뽫��������д������");
						return false;
					}
					if(jlnr.length > 500){
						showAlertDivLayer("��¼�����������������"+500+",��ȷ�ϣ�");
						return false;
					}
					var url = "szdw_bjxwjlwh.do?method=updateAction";
						ajaxSubFormWithFun("bjxwjlwhForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
						});
			});
		});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		
		<html:form action="/szdw_bjxwjlwh" method="post" styleId="bjxwjlwhForm">
		<input name="xn" value="${bjxwjlxx.xn}" type="hidden"/>
		<input name="guid" value="${bjxwjlxx.guid}" type="hidden"/>
		<input name="xqdm" value="${bjxwjlxx.xqdm}" type="hidden"/>
		<input name="jlr" value="${bjxwjlxx.jlr}" type="hidden"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td style="font-weight: bold">${bjxwjlxx.xn}</td>
							<th>ѧ��</th>
							<td style="font-weight: bold">${bjxwjlxx.xqmc}</td>
						</tr>
						<tr>
							<th>ְ����</th>
							<td>${bjxwjlxx.jlr}</td>
							<th>����  </th>
							<td>${bjxwjlxx.jlrmc}</td>
						</tr>
						<tr>
							<th>����  </th>
							<td>${bjxwjlxx.jlrbmmc}</td>
							<th>��λ</th>
							<td>${bjxwjlxx.gwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶��Ϊ��¼</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�༶</th>
							<td>
								<html:select property="bjdm" value="${bjxwjlxx.bjdm}" >
									<html:options collection="fdybjxxList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>���</th>
							<td>
								<html:select property="xndsdm" value="${bjxwjlxx.xndsdm}">
									<html:options collection="lbxxList" property="xndsdm" labelProperty="xndsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��¼ʱ��
							</th>
							<td colspan="3">
								<html:text styleId="_jlsj" property="jlsj" value="${bjxwjlxx.jlsj}" onclick="return showCalendar(this.id,'yyyyMMdd');"></html:text>
							</td>
							
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��¼����
							</th>
							<td colspan="3">
								<html:textarea styleId="_jlnr" property="jlnr" rows="3" style="width:97%" onblur="checkLen(this,500);" value="${bjxwjlxx.jlnr}"></html:textarea>
							</td>
						</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button" >
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

