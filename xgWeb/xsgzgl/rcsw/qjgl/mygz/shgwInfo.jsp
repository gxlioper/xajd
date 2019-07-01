<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script type='text/javascript' src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
			function save(){
				var gwid=jQuery("#hid_gwid").val();
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					jQuery(W.document).find('#shgw').val(gwid);
					W.searchRs();
					//jQuery(W.document).searchRs();
					closeDialog();
				} else {
					jQuery(parent.window.document).find('#search_go').click();
					iFClose();
				}
				//window.dialogArguments.document.getElementById("shgw").value=gwid;
				//window.parent.document.getElementById("shgw").value=gwid;
				//window.parent.searchRs();
			}
    	</script>
  </head>
  
  <body>
  	<html:form action="/rcsw_zjbb" method="post">
		<!-- 隐藏域 -->
		<%@ include file="/comm/hiddenValue.jsp"%>
	<div id="div_gwxx" >
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>岗位信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									您的岗位
								</th>
								<td>
									<p id="p_gwxx" style="height: 80px">
										<logic:iterate name="gwList" id="gw">
											<input type="radio" name="rad_gwid" value="${gw.gwid}" onclick="jQuery('#hid_gwid').val(this.value)"/>${gw.gwmc}<br/>
										</logic:iterate>
										<input type="hidden" id="hid_gwid" value=""/>
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="save();">
											确 定
										</button>
										
										<button type="button" id="btn_gb" onclick="Close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
	</html:form>
  </body>
</html>
