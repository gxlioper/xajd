<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/dmwh/ffxm/js/fyffxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body >
		<html:form action="/rcsw_fyff_ffxm" method="post" styleId="FyffxmForm" onsubmit="return false;">
		   <html:hidden property="ffxmdm"  styleId="ffxmdm"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸ķ�����Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<tr>
								<th width="16%">
									<font color="red">*</font>������Ŀ
								</th>
								<td width="34%">
									<html:text property="ffxmmc" styleId="ffxmmc" maxlength="10" styleClass="text_nor" />
								</td>
							</tr>
							<tr>
								<th width="16%">
									Ĭ�Ϸ��Ž��
								</th>
								<td width="34%">
									<html:text property="mrffje" styleId="mrffje" maxlength="10" onblur="checkInputNum(this)" styleClass="text_nor" />
								</td>
							</tr>
							<tr>
								<th width="16%">
									���ŷ�ʽ
								</th>
								<td width="34%">
									<html:radio  property="fffs" styleId="fffs1" value="1" ><label for="fffs1" style="cursor:pointer">���η���</label></html:radio>
									<html:radio  property="fffs" styleId="fffs0" value="0" ><label for="fffs0" style="cursor:pointer">���·���</label></html:radio>
								</td>
							</tr>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="updSaveForm();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

