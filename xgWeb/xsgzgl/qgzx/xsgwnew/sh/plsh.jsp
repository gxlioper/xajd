<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="xsgzgl/qgzx/xsgwnew/sh/js/plsh.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sq/js/xsgwsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#tbody_gwxx").load("xsgwsqnew_sq.do?method=gwxx&gwdm=${gwdms}&tt="+new Date().getTime(),function(){
				jQuery("#tbody_gwxx tr:first").hide();
			});
		});
		//选择申请岗位
		function wdgwxzCx(){
			var xh = jQuery("#xh").val();
			var gwdm=jQuery("#gwdm").val();
			if(xh==null || xh==""){
				showAlert("请选择一个学生");
			}else{
				showDialog("调整岗位",800,500,"xsgwsqnew_sq.do?method=wdgwxzCx&gwdm="+gwdm+"&xh="+xh+"&lx=tz",{
					close:function(){
					}
				});
			}
			
		}

		function savePlsh(shzt){
			if (jQuery("#shyj").val() == ""){
				showAlert("请填写审核意见！");
				return false;
			}
			if (jQuery("#shyj").val().length>200){
				showAlert("审核意见不能超过200字");
				return false;
			}
			var ids=jQuery("#ids").val();
			var shgws=jQuery("#shgws").val();
			var shyj=jQuery("#shyj").val();
			var message=jQuery("#message").val();
			var gwdm=jQuery("#gwdm").val();
			var splc=jQuery("#splc").val();

			var api = frameElement.api,W = api.opener;
			W.savePlsh(shzt,gwdm,shyj,splc);
			closeDialog();
		}
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/xsgwshnew_sh" method="post" styleId="demoForm">
			<input type="hidden" name="ids" id="ids" value="${ids}"/>
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="shgws" id="shgws" value="${shgws}"/>
			<input type="hidden" name="gwdms" id="gwdms" value="${gwdms}"/>
			<input type="hidden" name="splc" id="splc" value="${splc}"/>
			<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<logic:empty name="gwdms">
						<thead>
							<tr>
								<th colspan="4">
									<span>岗位信息</span> 
								</th>
							</tr>
						</thead>
						<tbody id="tbody_gwxxOne">
							<tr>
								<td colspan="4"><button class="btn_01" onclick="wdgwxzCxF();return false;" type="button">选择岗位</button></td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="gwdms">
						<tr>
							<th colspan="4" style="text-align:left">
								<span>岗位信息 <button class="btn_01" onclick="wdgwxzCx();return false;" type="button">调整岗位</button></span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
					</tbody>
					</logic:notEmpty>
						<tr>
							<th width="16%">
								<font color="red">*</font>审核意见
								<br />
								<font color="red">限200字</font>
							</th>
							<td  colspan="3">
							
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgwsh&id=shyj" />
								<textarea rows="10" style="width: 90%;margin-top: 5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<button type="button" id="btqd" onclick="savePlsh('1');">
									通过
								</button>
								<button type="button" id="btqd" onclick="savePlsh('2');">
									不通过
								</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

