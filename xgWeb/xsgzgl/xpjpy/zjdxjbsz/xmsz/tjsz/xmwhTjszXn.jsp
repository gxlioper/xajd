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
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/xmsz/tjsz/js/xmwhTjszXn.js"></script>
		<style>
			#xmList tr{ height:40px; }
			#xmList td{ width:100px; }
		</style>
	</head>
	<body>
		<html:form action="/xpj_xmwh_tjsz" method="post" styleId="form1">
			<input type="hidden" id="xnVal" name="xnVal" value="${xnVal}"/>
			<input type="hidden" id="zqlx" name="zqlx" value="${zqlx}"/>
			<input type="hidden" id="pjzq" name="pjzq" value="${pjzq }" />
			<input type="hidden" id="tjdm" name="tjdm" value="${tjdm }" />
			<input type="hidden" id="knszq" name="knszq" value="${knszq }" />
				<table width="100%" border="0" class="dateline">
					<tbody id="xmList">
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">							
							<div class="bz" align="left" id="qxDiv" style="display:none;">
								<input type="checkbox"  name="grid_selectAll">ȫѡ
							</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
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
