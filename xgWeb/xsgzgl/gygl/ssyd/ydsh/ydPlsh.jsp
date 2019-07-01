<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var zsfxs = jQuery("#zsfxs").val();
			if("0" == zsfxs) {
				jQuery("#zsfM").hide();
			}
		});

			function savePlsh(shzt){
				
				var shyj = jQuery("#shyj").val();
				var sfcwcsh = jQuery('input:radio[name="sfcwcsh"]:checked').val();
				var jflx = jQuery("#jflx").val();
				var zsfje = jQuery.trim(jQuery("#zsfje").val());

				if (shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				if('${xxdm}' != '13871'){
					if ((jflx == "" && zsfje != "") || (jflx != "" && zsfje == "")) {
						showAlert("请将住宿费信息填写完整！");
						return false;
					}
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,sfcwcsh,jflx,zsfje);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/ydsh" method="post" onsubmit="return false;">
		<html:hidden property="zsfxs" styleId="zsfxs" value="${zsfxs}"/>
			<div class="tab">
				<table class="formlist">
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
					<logic:notEqual value="13871" name="xxdm">
						<tr>
							<th>
								<font color="red">*</font>原床位是否初始化
							</th>
							<td colspan="3" >
								<input type="radio" name="sfcwcsh" value="0">是
						    	<input type="radio" name="sfcwcsh" value="1" checked>否
							</td>
						</tr>
					</logic:notEqual>
					<logic:equal name="xxdm" value="13871">
						<input type="checkbox" style="display:none" checked="checked" name="sfcwcsh" value="0">
					</logic:equal>
						<logic:notEqual value="13871" name="xxdm">
						<tr id = "zsfM">
							<th align="right" width="10%">
								住宿费
							</th>
							<td align="left" colspan="3">
								<html:select property="jflx" styleId="jflx" >
										<html:option value=""></html:option>
										<html:option value="01">补缴</html:option>
										<html:option value="02">退还</html:option>
								</html:select>
								<html:text size="15" property="zsfje" styleId="zsfje" maxlength="6" onkeyup="checkInputData(this);"></html:text> 元
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th width="25%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ssyd&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
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
