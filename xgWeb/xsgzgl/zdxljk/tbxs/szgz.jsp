<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zdxljk/tbxs/thjl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#saveBtn").bind("click",function(){
					if(("取消关注"==jQuery("[name='gzlx']:checked").val()&&""==jQuery("#qxyy").val().trim())||"undefined"==typeof(jQuery("[name='gzlx']:checked").val())){
						showAlert("请将必填项填写完整。");
						return false;
						}
					var url = "zdxljkTbxs.do?method=saveSzgz";
					ajaxSubFormWithFun("tbxsForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
						
					});
					
				});
			});
		</script>
  	</head>
  
  	<body>
  		<html:form action="/zdxljkTbxs" method="post" styleId="tbxsForm">
  			<input type="hidden" name="ids" value="${ids }"/>
  			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>设置关注</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="gzlxTr">
							<th style="width:20%;"><font color="red">*</font>关注类型</th>
							<td colspan="3" style="text-align:left;">
								<logic:present name="gzlxList">
									<logic:iterate id="o" name="gzlxList">
										<label>
											<html:radio property="gzlx" onclick="gzlxChange(this)" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						
						<tr style="display:none">
							<th width="16%"><font color="red">*</font>取消关注原因</th>
							<td colspan="3" style="text-align:left;">
								<textarea name="qxyy"  id="qxyy" onblur="checkLen(this,500);"
							   	 style="width:99%;" rows="4"></textarea>
							</td>
						</tr>
										
										
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button" id="saveBtn">
									保存
								</button>
								<button type="button" onclick="iFClose();">
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
