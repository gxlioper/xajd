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
						confirmInfo('��ȷ��Ҫ����'+num+'����?',function(t){
							if ('ok'==t){
								trs=Number(num)+trs;
								
								if (trs> 20){
									alertInfo('���ɵ��й���,��Ŀ������20������!');
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
						alertInfo('���ɵ��й���,��Ŀ������20������!');
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
					alertInfo('��ѡ����Ҫɾ������!');
				}
			}
			
			function selectAll(obj){
				jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
			}

			function bcxm(){
				var xmmc = jQuery('#xmmc').val();

				if (xmmc.trim() == ''){
					alertError("��\"*\"��Ŀ����Ϊ�գ���ȷ��");
				} else {
					confirmInfo('��ȷ��Ҫ������',function(t){if(t == 'ok'){saveUpdate('tjgy_xfjm.do?method=saveXmsz','');}})
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
								<span>ѧ�Ѽ�����Ŀά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��Ŀ����
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="50" style="width:90%" value="${rs.xmmc }"></html:text>
							</td>
							<th>
								�Ƿ�����
							</th>
							<td>
								<html:select property="sfqy" value="${rs.sfqy }">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���뿪ʼʱ��
							</th>
							<td width="34%">
								<html:text property="sqkssj" readonly="true" styleId="sqkssj"
										   onclick="showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" 
										   value="${rs.sqkssj }"
								></html:text>
							</td>
							<th width="16%">
								�������ʱ��
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
										<span style="color:blue">��ѧ�Ѽ�����Ŀ�Ѿ���<span style="color:red">"${rs.ysqrs }"</span>��ѧ�����룬��Ŀ���������޸ġ�</span>
									</td>
									<script defer>
										jQuery('#button_cz').css('display','none');
										jQuery('input[name=tjmc]').attr('readonly',true);
										jQuery('input[type=checkbox]').attr('disabled',true);
									</script>
								</logic:notEqual>
							</logic:present>
							<td colspan="4" id="button_cz">
							
								<!-- ��ѯ�õ�����������ʾ���� -->
								<button type="button" onclick="addTr();" class="btn_01">
									+
								</button>
								<button type="button" onclick="delTr();" class="btn_01">
									-
								</button>
								&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
								<input type="text" name="numAdd" id="numAdd"
									style="width: 20px" onblur="addTr(true)" />
								&nbsp;�� 
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
													��������
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
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" id="buttonSave"
										onclick="bcxm();">
										��&nbsp;&nbsp;��
									</button>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										��&nbsp;&nbsp;��
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
