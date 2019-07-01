<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			//�������е������ӵ�����Ҫ������
			function turnRight(){
				var tjk = jQuery('#tjk');
				var xytj = jQuery('#xytj');
				
				if (null != tjk.val()){
				
					var option = tjk.val();
					//jQuery�ƶ�option����ô��
					xytj.append(jQuery("#tjk option[value="+option+"]"));
				} else {
					alert('������������ѡ��ǰ��Ŀ������Ҫ������!');				
				}
			}
			
			//����Ҫ�������ͷŵ���������
			function turnLeft(){
				var tjk = jQuery('#tjk');
				var xytj = jQuery('#xytj');
				
				if (null != xytj.val()){
					var option = xytj.val();
					
					tjk.append(jQuery("#xytj option[value="+option+"]"));
				} else {
					alert('������Ŀ������ѡ��������Ҫ������!');				
				}
			}
			
			//��һ��
			function turnNext(){
				var option = jQuery("#xytj option:[index=0]");
				var max = jQuery("#xytj option:last").attr("index");
				
				if (jQuery('#xmdm').val()==''){
					alert('��ѡ����Ŀ����!');
					return false;
				}
				
				if (null != option.val()){
					var xmtj = new Array();
					
					for (var i = 0 ; i <= max ; i++){
						xmtj += jQuery("#xytj option:[index="+i+"]").val();
						
						if ( i!=max ){
							xmtj+=',';
						}
					}
					
					jQuery('#xmtj').val(xmtj);
					jQuery('form')[0].action='pjpy_ty_tjsz.do?method=tjsz';
					jQuery('form')[0].submit();
					
				} else {
					alert('��ѡ�����Ŀ��û����Ҫ�����������ܽ�����һ����������ȷ��!');				
				}
			}
			
			function getTjk(text){
				
				if ('' != text){
					jQuery.ajax({
						type:'post',
						url:'pjpy_ty_ajax.do?method=getTjk&xmdm='+text,
						dataType:'json',
						async: false,
						success:function(result){
							jQuery("#tjk").empty();
							
							for (var i = 0 ; i < result.length; i++){
								jQuery("#tjk").append('<option value="'+result[i].tjdm+'">'+result[i].tjmc+'</option>');
							}
						}
					});
					
					jQuery.ajax({
						type:'post',
						url:'pjpy_ty_ajax.do?method=getXmtj&xmdm='+text,
						dataType:'json',
						success:function(data){
							jQuery("#xytj").empty();
							
							for (var i = 0 ; i < data.length; i++){
								jQuery("#xytj").append('<option value="'+data[i].tjdm+'">'+data[i].tjmc+'</option>');
							}
						}
					});
					
				}
				
			}
		</script>

	</head>

	<body>
		<html:form action="/pjpy_ty_tjsz" method="post">
			<input type="hidden" name="xmtj" id="xmtj" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="turnNext()">
										��һ��
									</button>

									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<%--<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td colspan="3">
								<html:select property="pjxn" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td colspan="3">
								<html:select property="pjxq" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�������
							</th>
							<td colspan="3">
								<html:select property="pjnd" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						--%><tr>
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td colspan="3">
								<html:select property="xmdm" style="width:200px" onchange="getTjk(this.value);" styleId="xmdm" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="4">

								<table width="100%" border="0" class="fieldlist">
									<tr>
										<td valign="top" width="47%">
											<table width="100%" style="border:1px solid #B5CEE2;">
												<tr>
													<td
														style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
														<label style="font-weight:bold;color:#0157A2;">
															������
														</label>
													</td>
												</tr>
												<tr>
													<th>
														<html:select property="tjk" style="width:240px" size="10"
															styleClass="selectlist" styleId="tjk">
															<html:options collection="tjkList" property="tjdm"
																labelProperty="tjmc" />
														</html:select>
													</th>
												</tr>
											</table>
										</td>
										<td style="padding:0" align="center">
											<div class="btn_choose">
												<button type="button" onclick="turnRight()" class="right">
												</button>
												<br />
												<button type="button" onclick="turnLeft()" class="left">
												</button>

											</div>
										</td>
										<td valign="top" width="48%">
											<table width="100%" style="border:1px solid #B5CEE2;">
												<tr>
													<td
														style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
														<label style="font-weight:bold;color:#0157A2;">
															��Ŀ������Ҫ������
														</label>
													</td>
												</tr>
												<tr>
													<th>
														<div class="listcon">
															<html:select property="xytj" style="width:240px" size="10"
																styleClass="selectlist" styleId="xytj">
																<html:options collection="xmtjList" property="tjdm"
																	labelProperty="tjmc" />
															</html:select>
														</div>
													</th>
												</tr>
											</table>
										</td>
									</tr>
								</table>

							</td>
						</tr>
					</tbody>
				</table>

			</div>
		</html:form>
	</body>
</html>
