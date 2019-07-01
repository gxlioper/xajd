<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePlsh(shzt){
				var shyj = jQuery("#shyj").val();
				var zd3 = jQuery("#zd3").val();
				var zd1 = jQuery("#zd1").val();
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}
				var xxdm = jQuery("#xxdm").val();
				if(xxdm == "10511"){
					if(shzt == "1"){
						if(zd3 == ""){
							showAlert("����д��׼��");
							return false;
						}
						if(parseInt(zd3) == 0){
							showAlert("��׼����Ϊ0��");
							return false;
						}
						if(parseInt(zd3) > 1000){
							showAlert("��׼�������Ϊ1000��");
							return false;
						}
					}
				}
				
				
				var api = frameElement.api,W = api.opener;
				if(xxdm == "10511"){
					W.savePlsh(shzt,shyj,zd1,zd3);
				}else{
					W.savePlsh(shzt,shyj,"","");
				}
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/ypzl_sh" method="post"onsubmit="return false;">
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
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
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th width="20%">
					          <font color="red">*</font>��׼���(Ԫ)
				            </th>
				           <td>
								<html:text property="zd3" styleId="zd3" style="width:90px;" onblur="replaceAboveZero(this)" maxlength="4" onkeyup="checkInput(this)"></html:text>
								<html:hidden property="zd1" styleId="zd1" value="��׼���(Ԫ)"/>
				            </td>
				         </tr>
						</logic:equal>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ypzlsh&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>							
						</tr>
					</tbody>
				</table>
			</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
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
									<button type="button" name="�� ��" onclick="closeDialog();">
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
