<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
			function savePlsh(shzt){
				
				var rddc = jQuery("#rddc").val();
				var shyj = jQuery("#shyj").val();
				var ylzd3 = jQuery("#ylzd3").val();
				if (shzt == "1" && rddc == ""){
					showAlert("��ѡ���Ƽ����Σ�");
					return false;
				}
				if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
					showAlertDivLayer("��ѡ���޳�������");
					return false;
				}
				
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}
				
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,rddc,ylzd3,shyj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_knsrdbjpy" method="post" onsubmit="return false;">
		<input type="hidden" value="${xxdm}" id="xxdm"/>
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" onclick="savePlsh('1');">
										ͨ��
									</button>
									<button type="button" onclick="savePlsh('2');">
										��ͨ��
									</button>
<%--									<button type="button" onclick="savePlsh('3');" >--%>
<%--										�˻�--%>
<%--									</button>--%>
									<button type="button" name="�� ��" onclick="closeDialog();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>
									�������
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">�Ƽ�����</th>
							<td width="75%">
								<html:select property="rddc" styleId="rddc">
									<html:option value=""></html:option>
									<html:options collection="knsdcbjpyList" property="dcdm" labelProperty="dcmc"/>
								</html:select>
							</td>
						</tr>
						<logic:equal value="10335" name="xxdm">
						<tr>
							<th align="right"><font color="red">*</font>�޳��������</th>
							<td colspan="3">
								<html:select property="ylzd3" styleId="ylzd3">
									<html:option value=""></html:option>
									<html:options collection="wczzjeList" property="zzjedm" labelProperty="zzjemc"/>
								</html:select>
							</td>
							
						</tr>
						</logic:equal>
						<tr>
							<th>
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrdbjpy&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
