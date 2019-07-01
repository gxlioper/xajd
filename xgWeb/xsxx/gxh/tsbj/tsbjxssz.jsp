<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			var trArr = new Array();
		
			//刷新后加载右边已经选过的学生
			function loadRightTab(){
				var trHtml = jQuery('#trHtml').val();
				trArr = trHtml.split(',');
				
				for (var i = 0 ; i < trArr.length; i++){
					jQuery('#rightTab').append(trArr[i]);
				}
			}
			
			//查询，把已选学生数据转成字符串传到后台
			function searchRs(){
				var trHtml;
				for (var i = 0 ; i < trArr.length ; i++){
					trHtml+=trArr[i];
					
					if (i != trArr.length){
						trHtml+=',';
					}
				}
				
				jQuery('#trHtml').val(trHtml);
				allNotEmpThenGo('tsbj.do?method=tsbjxssz&doType=query');
			}
			
			//右移
			function moveRight(){
				
				var leftTab = jQuery('#leftTab');
				var leftSelected = jQuery('input:checked',leftTab);
				var rightTab = jQuery('#rightTab');
				
				for (var i = 0 ; i < leftSelected.length; i++){
					
					var tr = jQuery(leftSelected[i]).parents("tr");
					var inputTd = jQuery('td',tr).eq(0).attr('innerHTML');
					var xhTd = jQuery('td',tr).eq(1).attr('innerHTML');
					var xmTd = jQuery('td',tr).eq(2).attr('innerHTML');
					var trHtml = "<tr><td>"+inputTd+"</td><td>"+xhTd+"</td><td>"+xmTd+"</td></tr>";
					
					//如果trHtml不在trArr里面就右移并放在数组里					
					if (jQuery.inArray(trHtml,trArr) == -1){
						trArr[trArr.length] = trHtml;
						jQuery(rightTab).append(trHtml);
					}
				} 				
			}
			
			//左移
			function moveLeft(){
				
				var rightTab = jQuery('#rightTab');
				var rightSelected = jQuery('input:checked',rightTab);
				
				if (rightSelected.length > 0){
					if (confirm('您确定要移除选中的学生吗?')){
						for (var i = 0 ; i < rightSelected.length; i++){
							var tr = jQuery(rightSelected[i]).parents("tr");
							var inputTd = jQuery('td',tr).eq(0).attr('innerHTML');
							var xhTd = jQuery('td',tr).eq(1).attr('innerHTML');
							var xmTd = jQuery('td',tr).eq(2).attr('innerHTML');
							var trHtml = "<tr><td>"+inputTd+"</td><td>"+xhTd+"</td><td>"+xmTd+"</td></tr>";
							
							jQuery(tr[0]).remove();
							trArr = remove(trArr, trHtml);
						}
					}
				} else {
					alert('请选择您要移除的学生!');
				}
			}
			
			//从数组中删除元素
			function remove(trArr,trHtml) {
            	var tempArr = new Array();
            	
            	for (var i = 0 ; i < trArr.length ; i++){
            		
            		if (trArr[i] != trHtml){
            			tempArr[tempArr.length] = trArr[i];
            		}
            	}     
            	return tempArr;
            }
			

			//保存
			function tsbjxsbc(){
				var rightTab = jQuery('#rightTab');
				var trArr = jQuery('tr',rightTab);
				var tsbjXh='';
				
				if (trArr.length==0){
					alert('请先选择您要添加的学生!');
				} else {
					var xhArr = jQuery('input[type=checkbox]',rightTab); 
						
					for (var i = 0 ; i < xhArr.length ; i++){
						
						if (xhArr[i].value != ''){
							tsbjXh+=xhArr[i].value;
							
							if (i != xhArr.length-1){
								tsbjXh+=',';
							}
						}
					}	
					
					jQuery('#tsbjXh').val(tsbjXh);
					saveUpdate('tsbj.do?method=tsbjxsbc','')
				}
			}
			
			//切换特色班
			function changeTsbj(obj,text){
			
				if (obj.checked){
					jQuery('#tsbjdm').val(obj.value);
					jQuery('#tsbjmc').val(text);
					jQuery('#tsbjText').attr('innerText',text);
				}
			}
		</script>
	</head>
	<body onload="loadRightTab();">
		<html:form action="/tsbj">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="userName" name="userName"
				value="${userName}" />
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />
			<input type="hidden" id="userDep" name="userDep" value="${userDep}" />
			<input type="hidden" id="path" name="searchModel.path"
				value="tsbjxssz.jsp" />

			<html:hidden property="trHtml" styleId="trHtml" />
			<html:hidden property="tsbjXh" styleId="tsbjXh" />
			<html:hidden property="tsbjdm" value="${tsbjForm.tsbjdm }"
				styleId="tsbjdm" />
			<html:hidden property="tsbjmc" value="${tsbjForm.tsbjmc }"
				styleId="tsbjmc" />

			<div class="toolbox">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>

			<div class="tab">
				<table class="fieldlist" width="100%" style="margin-bottom:0">
					<thead>
						<tr>
							<th colspan="3" style="border-bottom:#b3cee3 1px solid;color:#000;line-height:30px;padding:0;">
								您正在为特色班级"<b><font id="tsbjText">${tsbjForm.tsbjmc }</font></b>"添加学生，
								<a style="color:#0f5dc2"
									href='javascript:tipsWindown("系统提示","id:tsbjDiv","450","200","true","","true","id")'>切换特色班?</a>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="60%">
								<div class="tab_box" style="height:400px" id="leftDiv">
									<table class="dateline" width="100%" id="leftTab">
										<thead>
											<tr>
												<td>
													<input type="checkbox" disabled="true" />
												</td>
												<td>
													学号
												</td>
												<td>
													姓名
												</td>
												<td>
													年级
												</td>
												<td>
													<bean:message key="lable.xb" />
												</td>
												<td>
													班级
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="rs">
												<logic:iterate id="r" name="rs">
													<tr>
														<td>
															<input type="checkbox"
																value='<logic:iterate id="v" name="r" offset="0" length="1">${v }</logic:iterate>' />
														</td>
														<logic:iterate id="v" name="r">
															<td>
																${v }
															</td>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=tsbjForm"></jsp:include>
									<script type="text/javascript">
										$('choose').className="hide";
									</script>
								</div>
							</td>
							<td width="5%">
								<div class="btn_choose">
									<br />
									<button type="button" class="right" onclick="moveRight();"></button>
									<br />
									<button type="button" class="left"
										onclick="moveLeft();"></button>
									<br />
								</div>
							</td>
							<td width="35%">
								<div class="tab_box" style="height:400px;overflow-y:auto;overflow-x:hidden;padding-right:20px;width:96%">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td width="17px">
													<input type="checkbox" disabled="true" />
												</td>
												<td>
													学号
												</td>
												<td>
													姓名
												</td>
											</tr>
										</thead>
										<tbody id="rightTab">

										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="choose">
									<input name="全选" type="checkbox" onclick="selectAllCheckbox(this);"/>
									全选
									<input name="反选" type="checkbox" onclick="turnSelect(this);"/>
									反选
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<table class="formlist" style="margin-top:0;">
					<tfoot>
						<tr>
							<td style="border-top:none;">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="reset" onclick="tsbjxsbc();">
										保存

									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="tsbjDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择特色班级</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									特色班级
								</th>
								<td>
									<logic:iterate id="t" name="tsbjList" indexId="i">
										<%
										if (i % 3 == 0 && i != 0) {
										%>
										<br />
										<%
										}
										%>
										<input type="radio" value="${t.tsbjdm }" name="temp_tsbj"
											onclick="changeTsbj(this,'${t.tsbjmc }')" /> &nbsp;&nbsp;${t.tsbjmc }&nbsp;&nbsp;
									</logic:iterate>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="关闭" onclick="closeWindown();return false;">
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
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
</html>
