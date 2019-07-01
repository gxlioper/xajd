<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function checkHxnlDm(obj){
				var value = jQuery(obj).val();
				if(value!=""){
					var hxnldm = jQuery('input[name=hxnldm][value='+value+']').length;
					
					if (hxnldm > 1){
						ymPrompt.alert({message:'重复的核心能力代码,请确认!',handler:function(m){
								if ('ok' == m){
									jQuery(obj).focus();
								}
							},maskAlpha:0.01
						});
					}
				}	
			}
		
		
			function addTr(isMore){
				var trs = jQuery('tr',jQuery('#hxnlTab')).length;
				
				var tr = "<tr><td><input type='checkbox'/></td>";
					tr+="<td> <input type='text' name='hxnldm' maxlength='10' onblur='checkHxnlDm(this)' style='width:100px'/> </td>";
					tr+="<td> <input type='text' name='hxnlmc' maxlength='30' style='width:100px'/> </td>"
					tr+="<td> <input type='text' name='minfs' onkeyup='checkInputNum(this)' style='width:80px' maxlength='10'> ~ "
					tr+="<input type='text' name='maxfs' onkeyup='checkInputNum(this)' style='width:80px' maxlength='10'></td>"
					tr+="</tr>";
					
				if (isMore){
					var num = jQuery('#numAdd').val();
					if ('' != num){
						confirmInfo('您确定要生成'+num+'行吗?',function(t){
							if ('ok'==t){
								trs+=Number(num);
								if (trs> 100){
									alertInfo('生成的行过多,核心能力项限100个以内!');
									return false;
								}
								
								for (var i = 0 ; i < num ; i++){
									jQuery('#hxnlTab').append(tr);
								}
							}
						})
					}
				} else {
					if (trs > 100){
						alertInfo('生成的行过多,核心能力项限100个以内!');
						return false;
					}
					jQuery('#hxnlTab').append(tr);
				}
			}
			
			function delTr(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				if (checkbox.length > 0){
				
					for (var i = 0 ; i < checkbox.length ; i++){
						jQuery(checkbox[i]).parents("tr").eq(0).remove();
					}
				} else {
					alertInfo('请选择您要删除的行!');
				}
			}
			
			function selectAll(obj){
				jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
			}
			
			function checkKmdmExists(obj){
				
				jQuery.post('sztz.do?method=checkKmdmExists',{kmdm:jQuery(obj).val()},function(data){
					if ("false" == data){
						jQuery(obj).focus();
						jQuery('#msg_div').attr('class','msg_error');
					}else {
						jQuery('#msg_div').attr('class','hide');
					}					
				})
			}
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('sztz.do?method=kmslUpdate&doType=save','kmdm-kmmc')">
										保 存
									</button>

									<button id="buttonSave" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>
									科目信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>科目代码
							</th>
							<td>
								<logic:notPresent name="rs">
									<div class="pos" style="z-index:1" >
									  <html:text property="kmdm" maxlength="10" styleId="kmdm" onblur="checkKmdmExists(this)"></html:text>
									  <div id="msg_div" class="hide">
							             <div class="prompcon">
							               <p>科目代码已存在</p>
							             </div>
							          </div>
						            </div>
								</logic:notPresent>
								
								<logic:present name="rs">
									<html:text property="kmdm" styleId="kmdm" value="${rs.kmdm }" readonly="true"></html:text>
								</logic:present>
			            	</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>科目名称
							</th>
							<td>
								<html:text property="kmmc" maxlength="30" styleId="kmmc" value="${rs.kmmc }"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							
							<td colspan="2">
								<span>
									核心能力
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
								<td colspan="2" >
									<!-- 查询得到的数据量显示区域 -->
									<button onclick="addTr();" class="btn_01">
										+
									</button>
									<button onclick="delTr();" class="btn_01">
										-
									</button>
									&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd"
										style="width: 20px" onblur="addTr(true)" />
									&nbsp;行 
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									<table width="100%">
										<thead >
											<tr>
												<td>
													<input type="checkbox" name="th" onclick="selectAll(this)"/>
												</td>
												<td>
													核心能力代码
												</td>
												<td>
													核心能力名称
												</td>
												<td>
													分数区间
												</td>
											</tr>
										</thead>
										<tbody id="hxnlTab">
											<logic:present name="hxnlList">
												<logic:iterate id="h" name="hxnlList">
													<tr>
														<td>
															<input type="checkbox"/>
														</td>
														<td>
															<input type="text" name="hxnldm" value="${h.hxnldm }" maxlength="10" style="width:100px"/>
														</td>
														<td>
															<input type="text" name="hxnlmc" value="${h.hxnlmc }" maxlength="30" style="width:100px"/>
														</td>
														<td>
															<input type='text' name='minfs' value="${h.minfs }" onkeyup='checkInputNum(this)' style='width:80px'  maxlength="10"/> ~
															<input type='text' name='maxfs' value="${h.maxfs }" onkeyup='checkInputNum(this)' style='width:80px'  maxlength="10"/>
														</td>
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
			</div>
			
			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
