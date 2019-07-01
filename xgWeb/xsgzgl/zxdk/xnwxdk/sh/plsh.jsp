<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
			function savePlsh(shzt){
				
				var shyj = jQuery("#shyj").val();
				var pzje = jQuery("#zd3").val();
				var zd1 =   jQuery("#zd1").val();
				if (shyj == "" || pzje == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				
				if(parseInt(pzje) > parseInt('${jesx}')){
					showAlert("超出金额上限！");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,pzje,zd1);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xnwxdk_sh" method="post"onsubmit="return false;">
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
							<th><font color="red">*</font>批准金额</th>
							<td>
								 <html:text property="zd3" styleId="zd3" style="width:90px;"></html:text>&nbsp;&nbsp;<font color="blue">上限金额：${jesx}元<font/>
						         <html:hidden property="zd1" styleId="zd1" value="批准金额"/>
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zxdkxnwxdk&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
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
									<button type="button" onclick="savePlsh('1');">
										通过
									</button>
									<button type="button" onclick="savePlsh('2');">
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
