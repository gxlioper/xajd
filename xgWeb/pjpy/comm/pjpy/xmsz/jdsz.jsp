<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script>
			jQuery(function(){
				var xmdm = jQuery('#xmdm').val();
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getFjddm&xmdm='+xmdm,
					dataType:'json',
					success:function(data){
						if (null != data){
							jQuery.each(data,function(i,n){
								jQuery('input[value="'+n+'"]').attr('checked',true);
							});
						}
					}
				})
			});
			
			function saveCheck(){
				var fjddms=document.getElementsByName("fjddm");
				if(fjddms&&fjddms.length){
					for(var i=0;i<fjddms.length;i++){
						if(fjddms[i].checked){
							return true;
						}
					}
				}
				alertInfo("奖学金或荣誉称号至少选择其中一项！");
				return false;
			}
		</script>
	</head>

	<body>
		<html:form action="/pjpy_ty_jdsz" method="post">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									<span class="red">*勾选设置不可兼得的项目</span>
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="update">
										<button type="button" onclick="saveUpdate('pjpy_ty_jdsz.do?method=xzxm&xmdm=${pjpyJdszForm.xmdm }','')">
											上一步
										</button>
									</logic:notEqual>
									<button type="button" type="reset" onclick="if(saveCheck()){saveUpdate('pjpy_ty_jdsz.do?method=jdsz&doType=save','');}">
										保存
									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								<html:select property="xmdm" style="width:200px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>奖学金</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="02" name="x" property="xmlx">
										<logic:notEqual value="${pjpyJdszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="fjddm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>荣誉称号</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="01" name="x" property="xmlx">
										<logic:notEqual value="${pjpyJdszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="fjddm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<%@ include file="/comm/other/tsxx.jsp"%>
		
		
	</body>
</html>
