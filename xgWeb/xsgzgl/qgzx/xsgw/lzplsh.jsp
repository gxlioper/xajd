<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
            function plsh(shzt) {

                var shyj = jQuery("#shyj").val();
                if (shyj == "") {
                    showAlert("����д��������");
                    return false;
                }
                if (shyj.length > 200) {
                    showAlert("���������ܳ���200��");
                    return false;
                }

                var api = frameElement.api,W = api.opener;
                W.savePlsh(shzt,shyj);
                closeDialog();
            }
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_xsgwsh.do" method="post" styleId="demoForm">
			<div >
				<table width="100%" border="0" class="formlist">
						<tr>
							<th width="16%">
								<font color="red">*</font>������
								<br />
								<font color="red">��200��</font>
							</th>
							<td  colspan="3">
							
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgw&id=shyj" />
								<textarea rows="10" style="width: 90%;margin-top: 5px" id="shyj" name="shyj"></textarea>
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
								<button type="button" id="btqd" onclick="plsh('1');">
									ͨ��
								</button>
								<logic:notEqual name="xxdm"  value="10351">
								<button type="button" id="btqd" onclick="plsh('2');">
									��ͨ��
								</button>
								</logic:notEqual>
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

