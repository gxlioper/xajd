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
				var shxmje = jQuery("#shxmje").val();
				
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,shxmje);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post">
			<div class="tab">
				<table class="formlist" width="100%">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<logic:equal name="xxdm" value="13871">
											<button type="button" onclick="savePlsh('3');">
												�˻���������
											</button>
									</logic:equal>
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
						<logic:equal value="10335" name="xxdm">
							<tr>
								<th width="20%">
									�������
								</th>
								<td>
									<html:text onkeyup="checkInputData(this)" maxlength="5" property="shxmje" styleId="shxmje" style="width:100px"></html:text>
									<br />
									<font color="red">1��������д�������<br/>
									2������ѡ��ļ�¼���ܴ��ڲ�ͬ����Ŀ<br/>
									3������д�򲻵������</font>
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xszz&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="6"
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
