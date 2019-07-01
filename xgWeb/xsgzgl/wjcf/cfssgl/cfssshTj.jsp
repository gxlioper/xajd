<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		
		<script type="text/javascript">

		//提交
		function tijiao(){
			confirmInfo("此操作将会提交审核结束的记录，提交后审核不能再修改，是否确定？",function(tag){
				if(tag=="ok"){
					refreshForm('wjcfCfssgl_cfsssh.do?method=cfssshTj&doType=tj');
				}
			});
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
	<body >
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshTj" method="post">
					<table width="100%" border="0" class="formlist">
					<tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>审核统计信息</span>
								</th>
							</tr>
						</thead>

						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>维持原处分</b></td>
										<td ><b>撤销处分</b></td>
										<td ><b>更改处分</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="shtjList">
								<logic:iterate name="shtjList" id="s">
										<tr  style="cursor:hand">
										<td >
											${s.wcycf}
										</td>
										<td >
											${s.cxcf}
										</td>
										<td >
											${s.ggcf}
										</td>
										</tr>
										</logic:iterate>
										</logic:notEmpty>
										</tbody>
								
								</table>
							</td>
						</tr>
							
					</tbody>
						
					<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								<button type="button"  onclick="tijiao();return false;" id="buttonSave">
										提交
								</button>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</table>
				</input>
			</div>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					
					
					showAlert("操作失败！",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					showAlert("操作成功！",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
