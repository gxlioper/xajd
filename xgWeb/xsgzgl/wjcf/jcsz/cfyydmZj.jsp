<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		function save(){
			if ($("cfyymc").value.trim()=="") {
				alertInfo("请填写处分原因!");
				return false;
		 	}
			var url="wjcfJcsz_cfyydm.do?method=cfyydmZj&doType=save";
			refreshForm(url);
		}
		//唯一验证
		function checkExists(tableName, pk){
			var pkV = jQuery('#cfyymc').val();
			dwr.engine.setAsync(false);
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_qsh').show('normal')
					jQuery('#btn_bc').attr('disabled', 'disabled');
				}else{
					jQuery('#span_qsh').hide('normal')
					jQuery('#btn_bc').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
		//关闭弹出层
		function divtmpclose() {
			parent.document.getElementById("tmpdiv1").innerHTML = "";
			try{
				parent.hiddenMessage(true,true);
			}catch(e){				
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfJcsz_cfyydm" method="post">
			<%--<div class="tab_cur" >
					<p class="location">
						<em>您的当前位置:</em><a>${title } - 增加</a>
					</p>
			</div>--%>
			<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>提示：</span></h3>
		       <p>该处分原因名称已经存在！<br/></p>
		   	</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>处分代码信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								处分原因名称
							</th>
							<td style="width:60%">
								<input type="text"  onkeypress="if(event.keyCode==13){return false;}"  name="cfyymc" id="cfyymc" maxlength="20" onblur="checkExists('xg_wjcf_cfyydmb','cfyymc');" />
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"的信息必须录入
								</div>
								<div class="btn">
									<button type="button"  class="button2" id="btn_bc"  onclick="save();return false;">
										保 存
									</button>
									<button type="button"  class="button2"  onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					 showAlert("操作失败!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("操作成功!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
