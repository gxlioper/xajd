<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/mmcsh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery(function(){
					jQuery("[name='chk']").change(function(){
						if(this.value == "sfz"){
							jQuery("#mm1").val("");
							jQuery("#mm1").attr("readonly","readonly");
						}else{
							jQuery("#mm1").removeAttr("readonly");
						}
					})
				})
			})
		</script>
	</head>

	<body>
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>����ѡ��</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr height='30'>
					<th>
						<input type="radio" name="chk" value="sfz" checked="checked"/>
					</th>
					<td>
					        <logic:equal name="xxdm" value="11458">
									����sdju��+���֤����λ�������֤����sdju��+6��0
							</logic:equal>
							<logic:notEqual name="xxdm" value="11458">
									�����֤����λ�������֤��6��0
							</logic:notEqual>
					<%-- 
						<input type="password" name="mm1" id="mm1" class="text_nor"
							maxlength="20" />--%>
					</td>
				</tr>
				<tr height='30'>
					<th>
						<input type="radio" name="chk" value="sdsr"/>
					</th>
					<td>
					�ֶ�����<input type="password" name="mm1" value="" readonly="readonly" id="mm1" class="text_nor"
							maxlength="20" />
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<span class="red">�����볤��Ϊ6~20λ�Ҳ���Ϊ�������ֻ���ͬ�ַ�</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn">
							<button type="button" onclick="cshMm();return false;">
								ȷ��
							</button>
							&nbsp;&nbsp;
							<button type="button"
								onclick="closeDialog();return false;">
								�ر�
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
