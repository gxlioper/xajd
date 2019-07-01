<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function addTr(isMore){
				var trs = jQuery('tr',jQuery('#xmtjTab')).length;
				var tr = "<tr><td><input type='checkbox'/></td>";
					tr+= "<td><input type='text' name='tjmc' maxlength='50' style='width:80%'/></td></tr>";
					
				if (isMore){
					var num = jQuery('#numAdd').val();
					if ('' != num){
						confirmInfo('您确定要生成'+num+'行吗?',function(t){
							if ('ok'==t){
								trs=Number(num)+trs;
								
								if (trs> 20){
									alertInfo('生成的行过多,项目奖项限20个以内!');
									return false;
								}
								for (var i = 0 ; i < num ; i++){
									jQuery('#xmtjTab').append(tr);
								}
							}
						})
					}
				} else {
					if (trs> 20){
						alertInfo('生成的行过多,项目奖项限20个以内!');
						return false;
					}
					jQuery('#xmtjTab').append(tr);
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

			function bcxm(){
				var xmmc = jQuery('#xmmc').val();

				if (xmmc.trim() == ''){
					alertError("带\"*\"项目不能为空，请确认");
				} else {
					confirmInfo('您确定要保存吗？',function(t){if(t == 'ok'){saveUpdate('tjgy_xfjm.do?method=saveXmsz','');}})
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/tjgy_xfjm" method="post">
			<html:hidden property="xmid" value="${rs.xmid }"/>
		
			<div class="tab" />
				<table class="formlist" width="93%">
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>学费减免项目维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>项目名称
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="50" style="width:90%" value="${rs.xmmc }"></html:text>
							</td>
							<th>
								是否启用
							</th>
							<td>
								<html:select property="sfqy" value="${rs.sfqy }">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请开始时间
							</th>
							<td width="34%">
								<html:text property="sqkssj" readonly="true" styleId="sqkssj"
										   onclick="showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" 
										   value="${rs.sqkssj }"
								></html:text>
							</td>
							<th width="16%">
								申请结束时间
							</th>
							<td width="34%">
								<html:text property="sqjssj" readonly="true" styleId="sqjssj"
										   onclick="showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"
										   value="${rs.sqjssj }" 
								></html:text>
							</td>
						</tr>
					</tbody>
					
					
					<tbody>
						<tr>
							<logic:present name="rs">
								<logic:notEqual value="0" property="ysqrs" name="rs">
									<td colspan="4">
										<input type="hidden" name="xgtj" value="no"/>
										<span style="color:blue">该学费减免项目已经有<span style="color:red">"${rs.ysqrs }"</span>个学生申请，项目条件不可修改。</span>
									</td>
									<script defer>
										jQuery('#button_cz').css('display','none');
										jQuery('input[name=tjmc]').attr('readonly',true);
										jQuery('input[type=checkbox]').attr('disabled',true);
									</script>
								</logic:notEqual>
							</logic:present>
							<td colspan="4" id="button_cz">
							
								<!-- 查询得到的数据量显示区域 -->
								<button type="button" onclick="addTr();" class="btn_01">
									+
								</button>
								<button type="button" onclick="delTr();" class="btn_01">
									-
								</button>
								&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
								<input type="text" name="numAdd" id="numAdd"
									style="width: 20px" onblur="addTr(true)" />
								&nbsp;行 
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									<table width="100%">
										<thead >
											<tr>
												<td  width="17.5px">
													<input type="checkbox" name="th" onclick="selectAll(this)"/>
												</td>
												<td>
													条件名称
												</td>
											</tr>
										</thead>
										<tbody id="xmtjTab">
											<logic:present name="tjList">
												<logic:iterate id="t" name="tjList">
													<tr>
														<td><input type='checkbox' value=""/></td>
														<td style="word-break:break-all;">
															<input type='text' name='tjmc' maxlength='50' style='width:80%' value="${t.tjmc }"/> 
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
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" id="buttonSave"
										onclick="bcxm();">
										保&nbsp;&nbsp;存
									</button>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关&nbsp;&nbsp;闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if (t=="ok") {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
