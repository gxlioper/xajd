<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			var trArr = new Array();
		
			//ˢ�º�����ұ��Ѿ�ѡ����ѧ��
			function loadRightTab(){
				var trHtml = jQuery('#trHtml').val();
				trArr = trHtml.split(',');
				
				for (var i = 0 ; i < trArr.length; i++){
					jQuery('#rightTab').append(trArr[i]);
				}
			}
			
			//��ѯ������ѡѧ������ת���ַ���������̨
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
			
			//����
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
					
					//���trHtml����trArr��������Ʋ�����������					
					if (jQuery.inArray(trHtml,trArr) == -1){
						trArr[trArr.length] = trHtml;
						jQuery(rightTab).append(trHtml);
					}
				} 				
			}
			
			//����
			function moveLeft(){
				
				var rightTab = jQuery('#rightTab');
				var rightSelected = jQuery('input:checked',rightTab);
				
				if (rightSelected.length > 0){
					if (confirm('��ȷ��Ҫ�Ƴ�ѡ�е�ѧ����?')){
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
					alert('��ѡ����Ҫ�Ƴ���ѧ��!');
				}
			}
			
			//��������ɾ��Ԫ��
			function remove(trArr,trHtml) {
            	var tempArr = new Array();
            	
            	for (var i = 0 ; i < trArr.length ; i++){
            		
            		if (trArr[i] != trHtml){
            			tempArr[tempArr.length] = trArr[i];
            		}
            	}     
            	return tempArr;
            }
			

			//����
			function tsbjxsbc(){
				var rightTab = jQuery('#rightTab');
				var trArr = jQuery('tr',rightTab);
				var tsbjXh='';
				
				if (trArr.length==0){
					alert('����ѡ����Ҫ��ӵ�ѧ��!');
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
			
			//�л���ɫ��
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
								������Ϊ��ɫ�༶"<b><font id="tsbjText">${tsbjForm.tsbjmc }</font></b>"���ѧ����
								<a style="color:#0f5dc2"
									href='javascript:tipsWindown("ϵͳ��ʾ","id:tsbjDiv","450","200","true","","true","id")'>�л���ɫ��?</a>
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
													ѧ��
												</td>
												<td>
													����
												</td>
												<td>
													�꼶
												</td>
												<td>
													<bean:message key="lable.xb" />
												</td>
												<td>
													�༶
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
													ѧ��
												</td>
												<td>
													����
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
									<input name="ȫѡ" type="checkbox" onclick="selectAllCheckbox(this);"/>
									ȫѡ
									<input name="��ѡ" type="checkbox" onclick="turnSelect(this);"/>
									��ѡ
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
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="reset" onclick="tsbjxsbc();">
										����

									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
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
									<span>��ѡ����ɫ�༶</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									��ɫ�༶
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
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="�ر�" onclick="closeWindown();return false;">
											�� ��
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
