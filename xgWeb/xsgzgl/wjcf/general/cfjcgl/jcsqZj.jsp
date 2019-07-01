<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

		//保存 
		function save(){
			var sqly = $("sqly").value;
			 if(""==sqly){
					alertError("请将带*的项目填写完整！");
					return false;
					}
			refreshForm('general_wjcf_cfjc_ajax.do?method=saveJcSq&lx=zj');
		}
		</script>
		
	</head>
	<body >
		<html:form action="/general_wjcf_cfjc_ajax" method="post" enctype='multipart/form-data'>
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="doType" id="doType"  >
					<html:hidden property="cfId" styleId="cfId" value="${cfId}"/>	
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>解除申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="25%">
								<font color="red">*</font>申请理由：<br/>
							<font color="red"><B>(限1000字)</B></font>
							</td>
							<td align="left"  >
								<html:textarea  property='sqly' styleId='sqly' style="width:99%"
								rows='8' onblur="checkLen(this,1000)"/>
							</td>
						</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;
								<button type="button"  onclick="Close();return false;" id="buttonClose">
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
