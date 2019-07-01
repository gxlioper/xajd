<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function displayQscw(obj){
				
				jQuery(":input[name=cwBtn]").not(obj).text("展开");
				jQuery("tr[name=cwTr]").not(jQuery(obj).parents("tr").next()).css("display","none");
				
				var text = jQuery.trim(jQuery(obj).text());
				jQuery(obj).parents("tr").next().toggle();
				jQuery(obj).text(text == "展开" ? "隐藏" : "展开");
			}
			
			function displayLcxx(obj){
				
				jQuery(":input[name=lcBtn]").not(obj).text("展开");
				jQuery("tbody[name=lcTbody]").not(jQuery(obj).parents("thead").next()).css("display","none");
				
				var text = jQuery.trim(jQuery(obj).text());
				jQuery(obj).parents("thead").next().toggle();
				jQuery(obj).text(text == "展开" ? "隐藏" : "展开");
			}
			
			function selectXhBox(obj){
				jQuery(":input[name=xhBox]").attr("checked",jQuery(obj).prop("checked"));
			}
			
			function selectCwBox(obj){
				
				var checked = jQuery(obj).prop("checked");
				
				if (checked){
					var xhArr = jQuery(":input[name=xhBox]:checked");
					var rzcw = jQuery(obj).parent().next().find(":input[name=cwBox]:not(:checked):lt("+xhArr.size()+")");
					szRz(xhArr,rzcw);
				} else {
					var xhArr = jQuery(obj).parent().next().find(":input[name=cwBox]:checked");
					
					jQuery.each(xhArr,function(i,n){
						var xh = jQuery(n).parent().find(":input[name=xh]");
						var xm = jQuery(n).parent().find(":input[name=xm]");
						
						var html = "<tr>";
							html+= "<td><input type='checkbox' name='xhBox' checked='true'/></td>";
							html+= "<td>"+xh.val()+"</td>";
							html+= "<td>"+xm.val()+"</td>";
							html+= "</tr>";
							
						jQuery("#table_xsxx tbody").append(html);
						
						var content = jQuery(n).parent().text();
						var newContext = content.replace(xm.val()+"["+xh.val()+"]","");
						var cwBox = jQuery(n).parent().find(":input[name=cwBox]");
						
						jQuery(n).parent().html(cwBox).append(newContext);
						
						xh.remove();
						xm.remove();
						jQuery(n).attr("checked",false);
					});
				}
				//jQuery(obj).parent().next().find(":input[name=cwBox]").attr("checked",jQuery(obj).prop("checked"));
			}
			
			function selectQsBox(obj){
				
				var xhArr = jQuery(":input[name=xhBox]:checked");
				
				var rzcw = jQuery(obj).parents("tr").next().find(":input[name=cwBox]:not(:checked):lt("+xhArr.size()+")");
				szRz(xhArr,rzcw);
				//jQuery(obj).parents("tr").next().find(":input").attr("checked",jQuery(obj).prop("checked"));
			}
			
			function selectLcBox(obj){
				var xhArr = jQuery(":input[name=xhBox]:checked");
				var rzcw = jQuery(obj).parents("thead").next().find(":input[name=cwBox]:not(:checked):lt("+xhArr.size()+")");
				szRz(xhArr,rzcw);
				//jQuery(obj).parents("thead").next().find(":input").attr("checked",jQuery(obj).prop("checked"));
			}
			
			function szRz(xhArr,rzcw){
				
				jQuery.each(rzcw,function(i,n){
					
					var xh = jQuery(xhArr[i]).parent().next().text();
					var xm = jQuery(xhArr[i]).parent().next().next().text();
					
					jQuery(xhArr[i]).parent().parent().remove();
					jQuery(n).parent().append(xm+"["+xh+"]");
					jQuery(n).attr("checked","checked");
					
					jQuery(n).parent().append("<input name='xh' type='hidden' value='"+xh+"'/>");
					jQuery(n).parent().append("<input name='xm' type='hidden' value='"+xm+"'/>");
					
					jQuery(n).parents("table").parent().prev().find(":input[name=qsBox]").attr("checked",true);
					jQuery(n).parents("table").parents("table").parents("tr").prev().find(":input").attr("checked",true);
				});
			}
			
				function checkCwBox(obj){
					
					var checked = jQuery(obj).prop("checked");
					var xhArr = jQuery(":input[name=xhBox]:checked");
					if (xhArr.size() == 0 && checked){
						showAlert("请选择入住学生");
						jQuery(obj).attr("checked",false);
						return false;
					}
					
					if (checked){
						if (xhArr.size() > 0){
							var xh = jQuery(xhArr[0]).parent().next().text();
							var xm = jQuery(xhArr[0]).parent().next().next().text();
							jQuery(xhArr[0]).parent().parent().remove();
							jQuery(obj).parent().append(xm+"["+xh+"]");
							
							jQuery(obj).parent().append("<input name='xh' type='hidden' value='"+xh+"'/>");
							jQuery(obj).parent().append("<input name='xm' type='hidden' value='"+xm+"'/>");
							
							jQuery(obj).parents("table").parent().prev().find(":input[name=qsBox]").attr("checked",true);
							jQuery(obj).parents("table").parents("table").parents("tr").prev().find(":input").attr("checked",true);
						}
						
					} else {
						
						var xh = jQuery(obj).parent().find(":input[name=xh]");
						var xm = jQuery(obj).parent().find(":input[name=xm]");
						
						var html = "<tr>";
							html+= "<td><input type='checkbox' name='xhBox' checked='true'/></td>";
							html+= "<td>"+xh.val()+"</td>";
							html+= "<td>"+xm.val()+"</td>";
							html+= "</tr>";
							
						jQuery("#table_xsxx tbody").append(html);
						
						var content = jQuery(obj).parent().text();
						var newContext = content.replace(xm.val()+"["+xh.val()+"]","");
						var cwBox = jQuery(obj).parent().find(":input[name=cwBox]");
						
						jQuery(obj).parent().html(cwBox).append(newContext);
						
						xh.remove();
						xm.remove();
					}
				}
			
			
			
			function saveXsrz(){
				var cwBox = jQuery(":input[name=cwBox]:checked");
				var jsonArr = [];
				
				jQuery.each(cwBox,function(i,n){
					var cwxx = jQuery(n).val();
					var xh = jQuery(n).parent().find(":input[name=xh]").val();
					var json = {cwxx:cwxx,xh:xh};
					jsonArr.push(json);
				});
				
				jQuery.post("gy_plyd.do?method=saveXsrz",{rzxx:JSON.stringify(jsonArr)},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				},"json");
			}
			
			
		</script>
	</head>

	<body>
		<div class="formbox">
			<div class="con_overlfow">
				<!-- 布局用table start -->
				<table style="width:100%;">
					<tbody>
						<tr>
							<td width="30%" style="vertical-align: top;">
								<table class="dateline" width="100%">
									<tbody>
										<tr>
											<td colspan="2">
												<div style="overflow-x: hidden; overflow-y: auto;">
													<table id="table_xsxx" width="100%">
														<thead>
															<tr>
																<th>
																	<input type="checkbox" onclick="selectXhBox(this);"/>
																</th>
																<th>
																	学号
																</th>
																<th>
																	姓名
																</th>
															</tr>
														</thead>
														<tbody>
															<logic:present name="xhList">
																<logic:iterate id="x" name="xhList">
																	<tr>
																		<td><input type="checkbox" name="xhBox"/></td>
																		<td>${x.xh }</td>
																		<td>${x.xm }</td>
																	</tr>
																</logic:iterate>
															</logic:present>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td width="70%" style="vertical-align: top;">
								<!-- 布局用table end1 -->
								<div id="anniu" style="overflow-x: auto; overflow-y: auto;width:100%;">
									<logic:present name="fyxx" property="ldxxList">
										<logic:iterate id="l" name="fyxx" property="ldxxList" indexId="dIndex">
											<table class="dateline" name="ldTable" border="1px;" width="100%">
												<thead>
													<tr align="center" style="cursor: hand">
														<td>
															<input type="checkbox" onclick="selectLcBox(this);"/>
															楼栋名称:${l.ldmc }
															
															<logic:equal value="0" name="dIndex">
																<button type="button" name="lcBtn" onclick="displayLcxx(this);">
																	隐藏
																</button>
															</logic:equal>
															<logic:notEqual value="0" name="dIndex">
																<button type="button" name="lcBtn" onclick="displayLcxx(this);">
																	展开
																</button>
															</logic:notEqual>
														</td>
													</tr>
												</thead>
												<tbody name="lcTbody"
													<logic:notEqual value="0" name="dIndex">
														style="display:none;"
													</logic:notEqual>
												>
													<logic:iterate id="c" name="fyxx" property="lcxxList" indexId="cIndex">
														<logic:equal value="${l.lddm }" name="c" property="lddm">
															<tr >
																<td>
																	<input type="checkbox" value="${c.ch }" onclick="selectQsBox(this);"/>${c.ch } 层
																	
																	<logic:equal value="0" name="cIndex">
																		<button type="button" name="cwBtn" onclick="displayQscw(this);">
																			隐藏
																		</button>
																	</logic:equal>
																	<logic:notEqual value="0" name="cIndex">
																		<button type="button" name="cwBtn" onclick="displayQscw(this);">
																			展开
																		</button>
																	</logic:notEqual>
																</td>
															</tr>
															<tr name="cwTr"
																<logic:notEqual value="0" name="cIndex">
																	style="display:none;"
																</logic:notEqual>
															>
																<td>
																	<table width="98%" style="border: 0px;">
																		<tbody >
																			<logic:iterate id="q" property="qscwList" name="fyxx">
																				<logic:equal value="${l.lddm }" name="q" property="lddm">
																					<logic:equal value="${c.ch }" name="q" property="ch">
																						<tr >
																							<td width="15%">
																								<input type="checkbox" name="qsBox" onclick="selectCwBox(this);"/>
																										${q.qsh }
																							</td>
																							<td>
																								<table>
																									<tbody>
																										<tr>
																											<logic:iterate id="cw" property="cwList" name="q">
																												<td style="width:70px;">
																													<input type="checkbox" name="cwBox" onclick="checkCwBox(this);" value="${l.lddm}-${q.qsh}-${cw}"/>${cw}
																												</td>
																											</logic:iterate>
																										</tr>
																									</tbody>
																								</table>
																							</td>
																						</tr>
																					</logic:equal>
																				</logic:equal>
																			</logic:iterate>
																		</tbody>
																	</table>
																</td>
															</tr>
														</logic:equal>
													</logic:iterate>
												</tbody>
											</table>
										</logic:iterate>
									</logic:present>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
