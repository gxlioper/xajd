<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khbgl/js/khnrgl.js"></script>	
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/khglKhnrgl" method="post"
			styleId="KhnrglForm" onsubmit="return false;">
			<html:hidden property="khbid" styleId="khbid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>��������ά��</span>
							</th>
						</tr>
						</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<html:textarea property='khnr' style="width:98%" styleId="khnr" rows='4' onblur="checkLen(this,500);"/>
							</td>
						</tr>
						<tr>
							<th>
								һ��ָ��
							</th>
							<td>
								<html:text  property="yjzb" styleId="yjzb" maxlength="50"  styleClass="text_nor"></html:text>
							</td>
							<th>
								����ָ��
							</th>
							<td>
								<html:text  property="ejzb" styleId="ejzb" maxlength="50"  styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ֵ����
							</th>
							<td>
								<logic:present name="fzlxList">
									<logic:iterate id="o" name="fzlxList">
										<label>
											<html:radio property="fzlx" styleId="fzlx" onclick="fzlxChange()" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
							<th>
								<span class="red">*</span>��ֵ
							</th>
							<td>
								<html:text  style="width:50px" property="fzzdf" styleId="fzzdf" maxlength="5"  onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>
								<span id='fzlxSpan'>
								��<html:text  style="width:50px" property="fzzgf" styleId="fzzgf" maxlength="5"  onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>
							   </span>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<html:select property="pflx" styleId="pflx" style="width:100px">
									<option value='1'>�ӷ�</option>
									<option value='2'>����</option>
								</html:select>
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm('save');return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

