<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function save_plsh(shjg) {
				if (jQuery("#shyj").val().length > 200) {
					showAlert("���������ܳ���200��");
					return false;
				}
				jQuery("#shjg").val(shjg);
				// �ύ���
	
				jQuery("button").attr("disabled","disabled");
				var url = "szdw_fdyxx.do?method=savePlsh";
				ajaxSubFormWithFun("form1", url, function(data) {
					showAlert(data["message"], {}, {
						"clkFun" : function() {
							refershParent();
						}
					});
				});
			}
		</script>
	</head>
	<body >	
	<html:form action="/szdw_fdyxx" method="post" styleId="form1">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" name="dataJson" id="dataJson" value='${dataJson}'/>
			<input type="hidden" name="shjg" id="shjg" value=''/>
			<table width="100%" border="0" style="margin-bottom: 5px" class="formlist" >
					<tbody>			
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=fdyxx&id=shyj" />
								
								<textarea rows="5" style="width: 90%;margin-top: 5px;" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<button type="button"  onclick="save_plsh(1);">
											ͨ��
										</button>
										<button type="button"  onclick="save_plsh(2);">
											��ͨ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</html:form>
	</body>
</html>

