<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		//��Ƭ������
		jQuery(function(){
			jQuery('#btn_dc').click(function(){
				tipsWindown("ϵͳ��ʾ","id:zpdcDiv","500","300","true","","true","id");
			});
			
		})
		
		//��Ƭ����
		function zpdc(){
			var photoNameType=$("photoNameType").value;
			var zpType=jQuery("input[name=zpType]").val();
			var api = frameElement.api,w = api.opener;
			w.exportZpxx(photoNameType,zpType);
		}
		</script>
	</head>
			<html:form action="/xtwhZpgl" method="post">
				<input type="hidden" id="xxdm" value="${xxdm}"/>
				<div id="zpdcDiv">	
					<div class="open_win01">
						<table align="center" class="formlist">
							<div id="xxPrompt" class="prompt">
								<h3>
									<span>��ʾ��</span>
								</h3>
								<p >
									��ѧ����Ϣ����ѡ�ֶ�����Ϊ�գ�����Ƭ�ĵ���������ʽĬ��Ϊ��ѧ�š��� 
								</p>
								<a class="close" title="����"
									onclick="this.parentNode.style.display='none';"></a>
							</div>
							<tbody>
								<tr>
									<th>
										������Ƭ��������ʽ
									</th>
									<td>
										<html:select property="photoNameType" styleId="photoNameType" style="width:120px">
											<logic:equal value="12688" name="xxdm">
												<html:option value="sfzh">���֤��</html:option>
											</logic:equal>
											<logic:notEqual value="12688" name="xxdm">										
											 	<html:option value="zd30-xm">ҽ����+����</html:option>
											</logic:notEqual>
										</html:select>
									</td>
								</tr>
								<input type="hidden" name="zpType" value="xs_zs" />
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="btn">
											<button type="button" name="����"
												onclick="zpdc()">
												�� ��
											</button>
											<button type="button" name="ȡ��" onclick="iFClose();return false;">
												ȡ ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				</html:form>
				</html>