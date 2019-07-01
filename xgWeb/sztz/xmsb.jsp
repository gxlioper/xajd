<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			function checkJxdm(obj){
				var value = jQuery(obj).val();
				if(value!=""){
					var jxdm = jQuery('input[name=jxdm][value='+value+']').length;
					if (jxdm > 1){
						ymPrompt.alert({message:'�ظ�����Ŀ����,��ȷ��!',handler:function(m){
								if ('ok' == m){
									jQuery(obj).focus();
								}
							},maskAlpha:0.01
						});
					}
				}
			}
		
		
			function addTr(isMore){
				var trs = jQuery('tr',jQuery('#sljxTab')).length;
				var tr = "<tr><td><input type='checkbox'/></td>";
					tr+="<td> <input type='text' name='jxdm' maxlength='10' /> </td>";
					tr+="<td> <input type='text' name='jxmc' maxlength='30'/> </td>";
					tr+="<td> <input type='text' name='jxfs' maxlength='10' onblur='checkInputNum(this)'/> </td></tr>"
					
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
									jQuery('#sljxTab').append(tr);
								}
							}
						})
					}
				} else {
					if (trs> 20){
						alertInfo('���ɵ��й���,��Ŀ������20������!');
						return false;
					}
					jQuery('#sljxTab').append(tr);
				}
			}
			
			function delTr(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				
				if (checkbox.length > 0){
					for (var i = 0 ; i < checkbox.length ; i++){
						jQuery(checkbox[i]).parents("tr").eq(0).remove();
					}
				} else {
					alertError('��ѡ����Ҫɾ������!');
				}
			}
			
			function initHxnl(obj){
				var params = {kmdm:obj.value};
				jQuery.post('sztzAjax.do?method=initHxnl',params,function(data){
					jQuery('#hxnl').empty();
					
					for (var i = 0 ; i < data.length; i++){
						if(data[i].dm==""||data[i].dm==null){
							var option="<option value=''>"+data[i].mc+"</option>";
						}else{
							var option="<option value='"+data[i].dm+"'>"+data[i].mc+"</option>";
							}
						
						jQuery(option).appendTo('#hxnl');
					}
				},'json');
			}
			
			function selectAll(obj){
				jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
			}
			
			function saveXmsb(){
				var jxdms = document.getElementsByName("jxdm");
				for (var i = 0 ; i < jxdms.length; i++){
					if(jxdms[i].value==""){
						alertError("������벻��Ϊ�գ���ȷ�ϣ�");
						return false;
						}
					for (var j = i+1 ; j < jxdms.length; j++){
						if((jxdms[i].value!="")&&(jxdms[j].value!="")&&jxdms[i].value==jxdms[j].value){
							alertError("��������ظ�����ȷ�ϣ�");
							return false;
						}
						
					}
				}
				
				var flg = checkSjTj('jbkssj','�ٰ쿪ʼʱ��','jbjssj','�ٰ����ʱ��');
				var doType = jQuery('#doType').val();
				if (flg){
					if(doType=="update"){
						saveUpdate('sztz.do?method=xmsb&doType=update','xn-xq-kmdm-hxnl-xmlx-xmmc-shlcid-jcf-jbkssj-jbjssj-rssx')
						}else{
							saveUpdate('sztz.do?method=xmsb&doType=save','xn-xq-kmdm-hxnl-xmlx-xmmc-shlcid-jcf-jbkssj-jbjssj-rssx')
							}
					
				}
			}

			function iner(){
				var hiddenXn = jQuery('#hiddenXn').val();
				var hiddenXq = jQuery('#hiddenXq').val();
				if(jQuery('#xn').val()==null||jQuery('#xn').val()==""){
					jQuery('#xn').val(hiddenXn);
					}
				if(jQuery('#xq').val()==null||jQuery('#xq').val()==""){
					jQuery('#xq').val(hiddenXq);
					}
				}
			
		</script>
	</head>
	<body onload="jQuery('#xmxx').focus(); iner()">
		<html:form action="/sztz" method="post">
			<html:hidden property="id" value="${rs.id }" />
			<input type="hidden" id="hiddenXn" value="${xn}" />
			<input type="hidden" id="hiddenXq" value="${xq}"  />
			<input type="hidden" id="doType" value="${doType}"  />
			<logic:present name="rs">
				<logic:equal value="�˻�" property="shzt" name="rs">
					<!-- �Ƿ��˻غ��ٴ��ύ -->
					<input type="hidden" name="flg" value="true"/>
				</logic:equal>
				<logic:notEqual value="�˻�" property="shzt" name="rs">
					<!-- �Ƿ��˻غ��ٴ��ύ -->
					<input type="hidden" name="flg" value="false"/>
				</logic:notEqual>
			</logic:present>
			
			<div  style="width:100%;height:550px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead >
						<tr>
							<td colspan="4" >
								<span id="xmxx">
									��Ŀ��Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xn" value="${rs.xn }" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
			            	</td>
			            	<th width="16%">
			            		<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xq" value="${rs.xq }" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
			            	</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�꼶
							</th>
							<td width="34%">
								<html:select property="nj" styleId="nj" value="${rs.nj }">
									<html:option value="">ȫ��</html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
			            	</td>
			            	<th width="16%">
								<font color="red">*</font>Ժϵ
							</th>
							<td width="34%">
								<logic:empty name="xydm">
									<html:select property="xydm" style="width:180px" styleId="xydm" value="${rs.xydm }" >
										<html:option value="xj">У��</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="xydm">
									<html:select property="xydmXy" style="width:180px" styleId="xydmXy" value="${xydm }" disabled="true">
										<html:option value="xj">У��</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									<input type="hidden" id="xydm" name="xydm" value="${xydm }"/>
								</logic:notEmpty>
			            	</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������Ŀ
							</th>
							<td>
								<html:select property="kmdm" onchange="initHxnl(this)" value="${rs.kmdm }">
									<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:select property="hxnl" styleId="hxnl" value="${rs.hxnl }">
									<html:options collection="hxnlList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:select property="xmlx" value="${rs.xmlx }">
									<html:options collection="xmlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:text property="xmmc" maxlength="50" value="${rs.xmmc }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<logic:notPresent name="rs">
									<html:select property="shlcid" value="${rs.shlcid }">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
								</logic:notPresent>
								
								<logic:present name="rs">
									<html:select property="shlcid" value="${rs.shlcid }" disabled="true">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
									<html:hidden property="shlcid" value="${rs.shlcid }"/>
								</logic:present>
							</td>
							<th>
								<font color="red">*</font>������
							</th>
							<td>
								<html:text property="jcf" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="10" value="${rs.jcf }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�ٰ�ʱ��
							</th>
							<td>
								<html:text property="jbkssj" 
										   readonly="true"
										   styleId="jbkssj"
										   value="${rs.jbkssj }"
										   style="width:90px"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('jbkssj','y-mm-dd');"
								></html:text>
								��
								<html:text property="jbjssj"
										   readonly="true"
										   styleId="jbjssj"
										   style="width:90px"
										   value="${rs.jbjssj }"
										   onblur="dateFormatChg(this);"
										   onclick="return showCalendar('jbjssj','y-mm-dd');"
								></html:text>
							</td>
							<th><font color="red">*</font>��������</th>
							<td>
								<html:text styleId="rssx" property="rssx" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="5" value="${rs.rssx }"></html:text>
							</td>
							
						</tr>
						<tr>
							<th>
								���췽
							</th>
							<td>
								<html:text property="zbf" maxlength="50" style="width:85%" value="${rs.zbf }"></html:text>
							</td>
							<th>
								ѧʱ
							</th>
							<td>
								<html:text property="xs" onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="2" value="${rs.xs }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�걨��
							</th>
							<td>
								<logic:empty name="sbr">
									${userNameReal }
								</logic:empty>
								<logic:notEmpty name="sbr">
									${rs.sbr }
								</logic:notEmpty>
								
								<html:hidden property="sbr" value="${userNameReal }"/>
							</td>
							<th>
								������
							</th>
							<td>
								<html:text property="fzr" maxlength="10" value="${rs.fzr }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע<font color="red">&lt;��400��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" onblur="checkLen(this,400)" style="width:85%" rows="5" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							
							<td colspan="4">
								<span>
									��������
								</span>
							</td>
						</tr>
					</thead>
					
					<tbody>
							<tr>
								<td colspan="4">
									<!-- ��ѯ�õ�����������ʾ���� -->
									<button onclick="addTr();" class="btn_01">
										+
									</button>
									<button onclick="delTr();" class="btn_01">
										-
									</button>
									&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd" onkeyup="value=value.replace(/[^\d|.]/g,'')"
										style="width: 20px" onblur="addTr(true)" />
									&nbsp;�� 
								</td>
							</tr>
							<tr>
								<td colspan="4">
									
									<table width="100%">
										<thead >
											<tr>
												<td  width="17.5px">
													<input type="checkbox" name="th" onclick="selectAll(this)"/>
												</td>
												<td>
													�������
												</td>
												<td>
													��������
												</td>
												<td>
													ѧ��
												</td>
											</tr>
										</thead>
										<tbody id="sljxTab">
											<logic:present name="jxList">
												<logic:iterate id="j" name="jxList">
													<tr>
														<td>
															<input type="checkbox"/>
														</td>
														<td>
															<input type="text" name="jxdm" value="${j.jxdm }" maxlength="10"/>
														</td>
														<td>
															<input type="text" name="jxmc" value="${j.jxmc }" maxlength="30"/>
														</td>
														<td>
															<input type="text" name="jxfs" value="${j.jxfs }" maxlength="10" onblur='checkInputNum(this)'/>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>
									
								</td>
							</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="sztzActionForm" property="doType" >
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
								</logic:notEqual>
								<div class="btn">
									<logic:notEqual value="view" property="doType" name="sztzActionForm">
										<button onclick="saveXmsb()">
											�� ��
										</button>
									</logic:notEqual>

									<button id="buttonSave" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
				</table>
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
