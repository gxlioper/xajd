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
				tipsWindown("ϵͳ��ʾ","id:zpdcDiv","500","200","true","","true","id");
			});
			
		})
		
		//��Ƭ����
		function zpdc(){
			var photoNameType=$("photoNameType").value;
			
			document.forms[0].action = 'xtwhZpgl.do?method=xszpdc&photoNameType='+photoNameType;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		</script>
	</head>
			<html:form action="/xtwhZpgl" method="post">
<div id="zpdcDiv">
					
					<div class="open_win01">
						<table align="center" class="formlist">
							<div id="xxPrompt" class="prompt">
								<h3>
									<span>��ʾ��</span>
								</h3>
								<p >
									��ѧ����Ϣ����ѡ�ֶ�����Ϊ�գ�����Ƭ�ĵ���������ʽĬ��Ϊ<br/>
									������+ѧ�š��� 
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
											<html:option value="xh">����+ѧ��</html:option>
										 	<html:option value="sfzh">����+���֤��</html:option>
										 	<html:option value="ksh">����+������</html:option>
										</html:select>
									</td>
								</tr>
<%--								<tr>--%>
<%--									<th>--%>
<%--										��������--%>
<%--									</th>--%>
<%--									<td>--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="xy"/>ѧԺ--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="zy"/>רҵ--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="bj"/>�༶--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="wxz"/>������--%>
<%--									</td>--%>
<%--								</tr>--%>
								<tr>
									<th>
										�������
									</th>
									<td>
										<input type="radio" name="zpType" value="xs_zs" checked="checked"/>����&������Ƭ
										<input type="radio" name="zpType" value="xszp"/>������Ƭ
										<input type="radio" name="zpType" value="zszp"/>������Ƭ
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
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