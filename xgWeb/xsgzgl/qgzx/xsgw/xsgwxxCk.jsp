<%@ page language="java"
	import="java.util.*,xgxt.utils.String.StringUtils"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
				/*var isopen = jQuery("#xssqkg").val();
				var shzt = jQuery("#shzt1").val();
				if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
					jQuery("#tssub").hide();
				}*/
			});
		</script>
	</head>
	<body style="width: 99%">
		<html:form action="qgzx_wdgwsq.do?method=wdgwxg" method="post"
			styleId="demoForm">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
			       					  ѧ��
								<input type="hidden" id="gwdm" name="gwdm"
									value="${model.gwdm }">
							</th>
							<td width="34%">
										${model.xn }
							</td>
							<th width="16%">
								���˵�λ
							</th>
							<td width="34%">
								${model.yrdwmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
									${model.gwmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<logic:equal name="model" property="gwxzdm" value="0"> ��ʱ</logic:equal>
								<logic:equal name="model" property="gwxzdm" value="1">��ʽ</logic:equal>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��Ƹ����
							</th>
							<td width="34%">
									${model.xqrs}��
							</td>

							<th width="16%">
								��λ����
							</th>
							<td>
								<logic:equal name="model" property="gwlx" value="0">��ʱ</logic:equal>
								<logic:equal name="model" property="gwlx" value="1">����</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								��λ���
							</th>
							<td >
									${model.gwxzmc}
							</td>
							<th>
								��λн������
							</th>
							<td>
									${model.gwcjsx}Ԫ
							</td>
						</tr>
						<tr>
							<th>
								��ʱ����
							</th>
							<td colspan="3">
									${model.gssx}Сʱ
								<span id="label"></span>
							</td>
						</tr>
						<tr>
							<th>
								��Ƹ��ʼʱ��
							</th>
							<td>
									${model.zpkssj}
							</td>
							<th>
								��Ƹ����ʱ��
							</th>
							<td>
								<logic:equal name="model" property="cq" value="1">����</logic:equal>
								<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								��ƸҪ��
							</th>
							<td colspan="3">
									${model.gwryyq}
							</td>
						</tr>
						<tr>
							<th>
								��λ��Ϣ
							</th>
							<td colspan="3">
								<style>

									#shlccx_table th{text-align: center;}
									#shlccx_table tr{text-align: center;}
								</style>
								<div class="con_overlfow">
									<table id="shlccx_table" width="100%" class="formlist" >
										<tr>
											<th>��Ƹ����</th>
											<th>��¼������</th>
											<th>����������</th>
										</tr>
										<tr>
											<td>${model.xqrs}</td>
											<td>${model.ylyrs}</td>
											<td>${model.ysqrs}</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" id="but_close">
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

