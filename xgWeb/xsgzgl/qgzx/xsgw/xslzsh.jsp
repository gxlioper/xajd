<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>

		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click();
				jQuery("#but_save").click();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqbh}&tt="+new Date().getTime());
                jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});
            function save_sh(){
                var shzt=jQuery("#shjg").val();
                jQuery("#shzt").val(shzt);
                if (jQuery("#shyj").val() == ""){
                    showAlertDivLayer("����д��������");
                    return false;
                }
                if (jQuery("#shyj").val().length>200){
                    showAlertDivLayer("���������ܳ���200��");
                    return false;
                }
                //�ύ���
                showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
                    var url = "qgzx_xsgwsh.do?method=xslzSh&type=save&tt="+new Date();
                    ajaxSubFormWithFun("demoForm",url,function(data){
                        showAlertDivLayer(data["message"],{},{"clkFun":function(){
                            //if (parent.window){
                            refershParent();
                            //}
                        }});
                    });
                }});
            }
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_xsgwsh.do?method=xslzSh&type=save" method="post" styleId="demoForm">
			<input type="hidden" name="sqbh" value="${model.sqbh}"/>
			<input type="hidden" name="splc" value="${model.splc}"/>
			<input type="hidden" name="xh" value="${xh}"/>
			<textarea name="sqly" style="display: none;">${model.sqly}</textarea><%--�˸�����--%>
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
							<input type="hidden" id="gwdm" name="gwdm" value="${model.gwdm }">
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
							<th width="16%">
								<font color="red"></font>��ְ����ʱ��
							</th>
							<td colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ְԭ��
							</th>
							<td colspan="3">
								${model.sqly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>������
								<br />
								<font color="red">��200��</font>
							</th>
							<td  colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgw&id=shyj" />
								<textarea rows="5" style="width: 100%;margin-top: 5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
									ȷ��
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
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

