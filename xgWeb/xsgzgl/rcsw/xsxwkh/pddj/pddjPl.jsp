<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePddjPl(){
				var pddj =jQuery("#pddj").val();
				var api = frameElement.api,W = api.opener;
				W.savePddjPl(pddj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxwkhDjpd" method="post"onsubmit="return false;">
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="4">
								<span>
									批量等级评定
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td colspan="4" align="center" > <span style="font-size:14px">当前选择生成考核等级的学生有<font color="red">${num}</font>个</span></td>
						   </tr>
						   <tr>
						<th><font color="red">* </font>终评等级</th>
							<td colspan="3">
								<html:select  property="pddj" styleId="pddj"  style="width:130px">
								<option value='优秀'>优秀</option>
								<option value='良好'>良好</option>
								<option value='合格'>合格</option>
								<option value='不合格'>不合格</option>
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
								<div class="btn">
									<button type="button" onclick="savePddjPl();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="closeDialog();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>
