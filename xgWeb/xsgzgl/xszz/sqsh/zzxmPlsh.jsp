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
				var shxmje = jQuery("#shxmje").val();
				
				if (shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,shxmje);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post">
			<div class="tab">
				<table class="formlist" width="100%">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<logic:equal name="xxdm" value="13871">
											<button type="button" onclick="savePlsh('3');">
												退回至申请人
											</button>
									</logic:equal>
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
						<logic:equal value="10335" name="xxdm">
							<tr>
								<th width="20%">
									调整金额
								</th>
								<td>
									<html:text onkeyup="checkInputData(this)" maxlength="5" property="shxmje" styleId="shxmje" style="width:100px"></html:text>
									<br />
									<font color="red">1、谨慎填写调整金额<br/>
									2、批量选择的记录可能存在不同的项目<br/>
									3、不填写则不调整金额</font>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xszz&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
