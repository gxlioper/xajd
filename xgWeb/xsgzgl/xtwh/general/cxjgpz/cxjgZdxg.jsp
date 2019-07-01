<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">
		//保存 
		function bcxgZdmc(){
			//var xgzdmc = jQuery("#xgzdmc").val();
			//refreshForm('xtwh_cxjgpz.do?method=bcxgZdmc');

			var zd = jQuery("#zd").val();
			var gnlj = jQuery("#gnlj").val();

			var url = "xtwh_cxjgpz.do?method=bcxgZdmc&zd="+zd+"&gnlj="+gnlj ;
		      ajaxSubFormWithFun("cxjgpzForm",url,function(data){
		    	 if(data["message"]=="修改成功!"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		 
		    	 }
				});
		}
		</script>
	</head>
	<body >

		<html:form styleId="cxjgpzForm" action="/xtwh_cxjgpz" method="post">
			<input type="hidden" name="gnlj" id="gnlj" value="${gnlj}">
			 <input type="hidden" name="zd" id="zd" value="${zd}">
					 <table width="100%" border="0" class="formlist">
						<tbody>
							<tr>
								<td>
											
												<table align="center" class="formlist">
													<thead>
														<tr>
															<th colspan="2">
																<span>字段信息</span>
															</th>
														</tr>
													</thead>
													<tbody>
													<tr id="">
															<th align="right" width="25%">
																字段名称：
															</th>
															<td align="left"  >
																<input name="zdmc" id="zdmc" value="${rs.zdmc}" disabled="disabled"/>
															</td>
														</tr>
														<tr id="">
															<th align="right" width="25%">
																字段别名：
															</th>
															<td align="left"  >
																<input name="xgzdmc"  value="${rs.xgzdmc}"  id="xgzdmc"/>
															</td>
														</tr>
													 </tbody>
													<tfoot>
														<tr>
															<td colspan="2">
																<div class="btn">
																	<button type="button" onclick="bcxgZdmc()">
																		保 存
																	</button>
																	
																	<button type="button" onclick="iFClose();">
																		关 闭
																	</button>
																</div>
															</td>
														</tr>
													</tfoot>
												</table>
										</td>
									</tr>	
								</tbody>
								</table>
							</td>
						</tr>				
					</tbody>
			</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
