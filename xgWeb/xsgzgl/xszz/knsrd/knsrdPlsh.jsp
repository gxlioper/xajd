<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
			function savePlsh(shzt){
				
				var rddc = jQuery("#rddc").val();
				var shyj = jQuery("#shyj").val();
				/**var ylzd3 = jQuery("#ylzd3").val();*/
				//浙江经济批量审核不填推荐档次
				if(jQuery("#xxdm").val()=="12866"){

                    if (shzt == "1"){
                        if (shyj == "") {
                            shyj = "通过";
                        }
                        rddc = jQuery("#sjdcmc").val();
                    }
                }

<%--                if (shzt == "1" && rddc == ""){--%>
<%--                        showAlert("请选择推荐档次！");--%>
<%--                        return false;--%>
<%--				}--%>
				/**浙江大学个性化，现在不需要此字段，注释的原因以防以后老师再需要，如在需要吧0改为10335即可，2016年09月30日-孟威*/
				if("0"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
					showAlertDivLayer("请选择无偿资助金额！");
					return false;
				}
				
				//如有上级认定档次，且认定档次不填的话，就默认为上级认定档次
				if(shzt == "1"){
					if(jQuery("#sjdcmc").val() == null || jQuery("#sjdcmc").val() == "" || jQuery("#sjdcmc").val() == "null"){
						if(rddc == null || rddc == ""){
							showAlert("请选择认定档次！");
							return false;
						}
					}else{
						if(rddc == null || rddc == ""){
							rddc = jQuery("#sjdcmc").val();
						}
					}
				}

				if (shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,rddc,"",shyj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_knsrd" method="post" onsubmit="return false;">
		<input type="hidden" value="${xxdm}" id="xxdm"/>
			<input type="hidden" value="${sjdcmc}" id="sjdcmc"/>
			<div class="tab">
				<table class="formlist">
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
									<logic:equal value="13871" name="xxdm">
										<button type="button" onclick="savePlsh('3');" >
											退回至申请人
										</button>
									</logic:equal>
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
						<logic:notEqual value="12866" name="xxdm">
								<tr>
									<th width="25%">推荐档次</th>
									<td width="75%">
										<html:select property="rddc" styleId="rddc">
											<html:option value=""></html:option>
											<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc"/>
										</html:select>
									</td>
								</tr>																								
						</logic:notEqual>
						<!-- 浙江大学个性化，现在不需要此字段，注释的原因以防以后老师再需要，如在需要吧0改为10335即可，2016年09月30日-孟威-->																						
						<logic:equal value="0" name="xxdm">
							<tr>
								<th width="25%">推荐档次</th>
								<td width="75%">
									<html:select property="rddc" styleId="rddc">
										<html:option value=""></html:option>
										<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc"/>
									</html:select>
								</td>
							</tr>																		
							<tr>
								<th align="right" width="25%"><font color="red">*</font>无偿资助金额</th>
								<td colspan="3">
									<html:select property="ylzd3" styleId="ylzd3">
										<html:option value=""></html:option>
										<html:options collection="wczzjeList" property="zzjedm" labelProperty="zzjemc"/>
									</html:select>
								</td>
								
							</tr>
						</logic:equal>
						
						<tr>
							<th width="25%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrd&id=shyj" />
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
