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
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function save(){
			    var sqly = jQuery("#sqly").val();
			    if(sqly == ""){
                    showAlertDivLayer("����д��ְԭ��");
                    return false;
				}
                showConfirmDivLayer("��ȷ���ύ��������",{"okFun":function(){
                    var url = "qgzx_wdgwsq.do?method=lzsq&type=save";
                    ajaxSubFormWithFun("demoForm",url,function(data){
                        showAlertDivLayer(data["message"],{},{"clkFun":function(){
                            if (parent.window){
                                refershParent();
                            }
                        }});
                    });
                }});
			}
		</script>
	</head>
	<body style="width: 99%">
		<html:form action="qgzx_wdgwsq.do?method=lzsq" method="post"
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
					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>��ְ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<span class="red">*</span>��ְԭ��
							<br />
							<span class="red">(��1500��)</span>
						</th>
						<td  colspan="4">
							<textarea rows="5" style="width: 100%" onblur="checkLen(this,1500)" id="sqly" name="sqly"></textarea>
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
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<button type="button" type="button" onclick="save();return false;">
									�ύ����
								</button>
								<button type="button" type="button" onclick="iFClose();return false;" id="but_close">
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

