<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/jsxm/js/jsxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				bindXmlxEvent();
				
				var xmlx = jQuery(":radio:checked").val();
				
				if (xmlx == "3"){
					jQuery("#mrfTr").show();
					jQuery("#qzbTr").hide();
					jQuery("#zdfTr").hide();
					jQuery("#zxfTr").hide();
				} else if (xmlx == "4"){
					jQuery("#mrfTr").hide();
					jQuery("#qzbTr").hide();
					jQuery("#zdfTr").hide();
					jQuery("#zxfTr").hide();
				}else {
					jQuery("#mrfTr").hide();
					jQuery("#qzbTr").show();
					jQuery("#zdfTr").show();
					jQuery("#zxfTr").show();
				}
				
			});
		</script>
	</head>
	<body>
		<html:form action="/xpjpyBfjsJsxm" method="post" styleId="BfjsJsxmModel">
			<input type="hidden" value="${isUpdate }" id="isUpdate"/>
			<input type="hidden" value="${csszModel.jsxmjb }" id="jsxmjb"/>
			
			<html:hidden property="fjdm" />
			<html:hidden property="xmdm" />
			<html:hidden property="xn" />
			<html:hidden property="xq" />
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="30%">
								<span class="red">*</span>综测项目
							</th>
							<td width="70%">
								<html:text property="xmmc" maxlength="20" styleId="xmmc" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>项目类型
							</th>
							<td>
								<logic:iterate id="x" name="xmlxList">
									<label>
										<html:radio property="xmlx" value="${x.dm }"></html:radio>${x.mc }
									</label>
								</logic:iterate>
							</td>
						</tr>
						<tr id="qzbTr">
							<th>
								<span class="red">*</span>权重比
							</th>
							<td>
								<html:text property="qzbl" maxlength="5" onkeyup='checkInputNum(this)' onblur='checkInputNum(this)' styleId="qzbl"></html:text>%
							</td>
						</tr>
						<tr style="display:none;" id="mrfTr">
							<th>
								<span class="red">*</span>默认分数
							</th>
							<td>
								<html:text property="mrfs" maxlength="5" onkeyup='checkInputNum(this)' onblur='checkInputNum(this)' styleId="mrfs"></html:text>
							</td>
						</tr>
						<tr id="zdfTr">
							<th>
								<span class="red">*</span>最大分
							</th>
							<td>
								<html:text property="zdfz" styleId="zdfz" maxlength="5" onkeyup='checkInputNum(this)' onblur='checkInputNum(this)'></html:text>
							</td>
						</tr>
						<tr id="zxfTr">
							<th>
								<span class="red">*</span>最小分
							</th>
							<td>
								<html:text property="zxfz" styleId="zxfz" maxlength="5" onkeyup='checkInputNum(this)' onblur='checkInputNum(this)' value="0"></html:text>
							</td>
						</tr>
						<tr id="pfsmTr">
							<th>评分说明
								</br><font color="red">(限500字)</font></th>
							<td >
								<html:textarea property="pfsm" styleId="pfsm" 
											    onkeyup="checkzs(this);" 
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="updateBfjsJsxm();">
										保 存
									</button>

									<button type="button" type="button" onclick="iFClose();">
										关 闭
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

