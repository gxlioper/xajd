<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">

			function xspjshPlshSave(shzt) {

				var fs = jQuery("#fs").val();
				var shyj = jQuery("#shyj").val();

				if("" == fs || null == fs){
					showAlert("请填写分数！");
					return false;
				}
				
				if("" == shyj || null == shyj){
					showAlert("请填写审核意见！");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.xspjshPlshSave(fs,shyj,shzt);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xspj_xspjsh" method="post" styleId="xspjshForm">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								重要提示：当前审核数据是您通过高级查询条件查询出的数据，共<font color="red" style="font-weight:bold;font-style:italic;">&nbsp;${len}&nbsp;</font>条！
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								评定分数
							</th>
							<td>
								<html:text style="width:80px" property="fs" styleId="fs"  maxlength="5" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbsh&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj">
								</html:textarea>
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
								<button type="button" onclick="xspjshPlshSave('1');">
									通过
								</button>
								<button type="button" onclick="xspjshPlshSave('2');">
									不通过
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