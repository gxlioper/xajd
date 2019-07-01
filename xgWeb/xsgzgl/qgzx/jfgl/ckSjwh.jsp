<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/sjwh.js"></script>
		
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
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){
					saveForm('xgCjff');
				});
				jQuery("#but_close").click(btn_close);
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_jfcjgl_cjff.do" method="post" styleId="demoForm">
			<div style='tab'>
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
								<span>�鿴��𷢷���Ϣ</span>
								<html:hidden name="model" property="wbh" styleId="wbh" />
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��<logic:equal value="xq" name="cs" property="qgzq">ѧ��
								</logic:equal>
							</th>
							<td width="34%">
								${model.xn}<logic:equal value="xq" name="cs" property="qgzq">&nbsp;&nbsp;&nbsp;${xqmc}</logic:equal>
							</td>
							<th width="16%">
								���˲���
							</th>
							<td width="34%" >
								<logic:empty name="rs" property="disQg">
									<html:select name="model" property="yrbm" styleId="yrbm"  disabled="true" style="width:150px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs" property="disQg">
									<html:select name="model" property="yrbm" styleId="yrbm" disabled="true" style="width:200px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:notEmpty>
								
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ʱ
							</th>
							<td width="34%">
								${model.gs}
							</td>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
								${model.gwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								���
							</th>
							<td width="34%">
								${model.je}
							</td>
							<th width="16%">
								�ڸ�״̬
							</th>
							<td width="34%">
								<html:select name="model" property="zgzt" styleId="zgzt"  disabled="true" style="width:150px">
									<logic:equal value="�ڸ�" name="model" property="zgzt">
										<option value='zg' selected="selected">�ڸ�</option>
										<option value='tg'>�˸�</option>
									</logic:equal>
									<logic:equal value="�˸�" name="model" property="zgzt">
										<option value='zg'>�ڸ�</option>
										<option value='tg'  selected="selected">�˸�</option>
									</logic:equal>
									<logic:empty name="model" property="zgzt">
										<option value='zg'>�ڸ�</option>
										<option value='tg'>�˸�</option>
									</logic:empty>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%" colspan="3">
								${model.ffsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע
							</th>
							<td width="34%" colspan="3">
								${model.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;">
			</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="Close();return false;">
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

