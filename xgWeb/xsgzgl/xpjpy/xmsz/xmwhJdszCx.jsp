<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhJdszCx.js"></script>
		<style>
			#xmList tr{ height:40px; }	
			#xmList td{ width:100px; }	
		</style>
	</head>
	<body>
		<html:form action="/xpj_xmwh_jdsz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmmc}</font>

					<span id="spztTip" style="display:none;">
					<br/>
					<font color="red">��ǰ��Ŀ����ѧ�����룬�������޸�</font>
					</span>
				</p>
				<p>
					ֻ������ͬ�����֮�����Ŀ�໥����
				</p>
				<p>
					�ѹ�ѡ��ĿΪ���ɼ�õ���Ŀ
				</p>
				
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
				<div style="width:100%;height:120px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="dateline">
						<tbody id="xmList">
						</tbody>
					</table>
				</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="saveBtn" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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

