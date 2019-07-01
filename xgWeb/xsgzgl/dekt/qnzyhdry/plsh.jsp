<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePlsh(){
				var shyj = jQuery("#gsshyj").val().trim();
				var shzt = jQuery("#gsshzt").val();
				var fwjg = jQuery("#fwjg").val();
				if (shzt == ""){
					showAlert("请选择审核结果！");
					return false;
				}
				if(shyj == null || shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				if(fwjg == null || fwjg == ""){
					showAlert("请选择服务结果！");
					return false;
				}				
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,fwjg);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/zyhdry" method="post"onsubmit="return false;">
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
								<span>
									批量审核
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核结果
							</th>
							<td>
								<select id="gsshzt" name="gsshzt">
<%--									<option value="">--请选择--</option>--%>
									<option value="1">通过</option>
<%--									<option value="2">退回</option>--%>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*&nbsp;</font>服务结果
							</th>
							<td colspan="3">
								<select name="fwjg" id="fwjg" style="width: 100px;">
									<option value="满意_1">
										满意
									</option>
									<option value="基本满意_0.75">
										基本满意
									</option>
									<option value="一般满意_0.5">
										一般满意
									</option>
									<option value="不满意_0">
										不满意
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								 <font color="red">*&nbsp;</font>审核意见
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td>
								<textarea rows="5" id="gsshyj" name="gsshyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
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
									<button type="button" onclick="savePlsh();">
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
