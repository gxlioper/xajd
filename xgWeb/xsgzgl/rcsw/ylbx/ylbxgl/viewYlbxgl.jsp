<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){
				onShow("rcsw_ylbx_query");
				jQuery("#myTbody").css("display","none");
			});
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			function deploy(id){
				var v = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
				jQuery("."+id).css("display", v);
			}

			//医疗保险结果信息
			function onShow(gndm) {
				var url = "rcsw_ylbx_ylbxglgl.do?method=ylbxXx";
				jQuery.ajax( {
					type : "post",
					async : false,
					url : url,
					data : {
						xh : jQuery("#xh").val(),
						jgid : jQuery("#jgid").val()
					},
					dataType : "json",
					success : function(data) {
						zdybdInit(gndm, data);
					}
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/rcsw_ylbx_ylbxglgl" method="post" styleId="ylbxglForm">
			<html:hidden property="jgid"  styleId="jgid"/>
			<html:hidden property="xh" styleId="xh" />
			<div style='width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/ylbx/comm/viewStudent.jsp" %>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>医疗保险信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
							                    <td width="5%"  style="text-align:center;">
							                    	序 号
						                        </td>
							                    <td width="10%"  style="text-align:center;">
							                    	保 费
						                        </td>
												<td width="25%"  style="text-align:center;">
													保险开始时间
												</td>
												<td width="25%"  style="text-align:center;">
													保险截止时间
												</td>
												<td width="35%"  style="text-align:center;">
													备 注
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="lsList">
												<logic:notEmpty name="lsList">
													<logic:iterate id="l" name="lsList" indexId="index">
														<tr style="text-align:center;">
															<td>${index+1}</td>
															<td>${l.zd23}</td>
															<td>${l.zd21}</td>
															<td>${l.zd22}</td>
															<td title=${l.zd8}>${l.str}</td>
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="lsList">
													<tr>
														<td colspan="5" style="text-align:center;">未找到任何记录！</td>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="lsList">
												<tr>
													<td colspan="5" style="text-align:center;">未找到任何记录！</td>
												</tr>
											</logic:notPresent>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>		
				
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</table>
		</html:form>
	</body>
</html>

