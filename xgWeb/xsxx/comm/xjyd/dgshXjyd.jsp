<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function setThjsr(shzt){
				
				if (shzt == '�˻�'){
					jQuery('#thjsrText').attr('style','display:block');
					jQuery('#thjsrZj').attr('style','visibility:');
					jQuery('#thjsr').attr('disabled',false);
				} else {
					jQuery('#thjsrText').attr('style','display:none');
					jQuery('#thjsrZj').attr('style','visibility:hidden');
					jQuery('#thjsr').attr('disabled',true);
				}
			
			}
			
			function submtiShjg(){
				saveUpdate('xsxxXjyd.do?method=xjydDgsh&doType=save&id=${rs.id }&sftj=��','shjg')
			}
			
		</script>
	</head>
	<body>
	
		<html:form action="/xsxxXjyd" method="post">
		
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="url" name="url" value="/xsxxXjyd.do?method=xjydsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
			<input type="hidden" name="pkValue" value="${rs.id }"/>
			
			<html:hidden property="save_shlcid" value="${rs.shlcid }"/>
			<input type="hidden" id="isLast" value="${isLastSpgw }"/>
		
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="�� ��" onclick="submtiShjg();">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ��������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }"/>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th>
								�춯���
							</th>
							<td>
								<html:select property="save_ydlbm" styleId="ydlbm" value="${rs.ydlbm }" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="ydlbAllList" property="ydlbm" labelProperty="ydlbmc"/>
								</html:select>
							</td>
							<th>
								������
							</th>
							<td>
								${rs.sqr }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.ydsm }
							</td>
						</tr>
					</tbody>
					<logic:present name="rs">
					
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ���춯��Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" align="center">��ǰ���ڰ༶��ѧ��״̬</td>
							<td colspan="2" align="center">�����춯��Ϣ��ѧ��״̬</td>
						</tr>
						<tr>
							<th>�꼶</th>
							<td>
								${rs.ydqnj }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqnj }" property="ydhnj" name="rs">
									<font color="red">��</font>
								</logic:notEqual>
								�꼶
							</th>
							<td>
								<html:select property="save_ydhnj" styleId="nj" value="${rs.ydhnj }"
									onchange="initZyList();initBjList()" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								${rs.ydqxymc }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqxydm }" property="ydhxydm" name="rs">
									<font color="red" >��</font>
								</logic:notEqual>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:hidden property="save_ydhxymc" styleId="ydhxymc" value="${rs.ydhxymc }"/><!-- �춯��ѧԺ���� -->
								<html:select property="save_ydhxydm" value="${rs.ydhxydm }"
									onchange="initZyList();initBjList();$('ydhxymc').value=DWRUtil.getText('xy')" styleId="xy"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								${rs.ydqzymc }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqzydm }" property="ydhzydm" name="rs">
									<font color="red" style="">��</font>
								</logic:notEqual>
								רҵ
							</th>
							<td>
								<html:hidden property="save_ydhzymc" styleId="ydhzymc" value="${rs.ydhzymc }"/><!-- �춯��רҵ���� -->
								<html:select property="save_ydhzydm" value="${rs.ydhzydm }"
									onchange="initBjList();$('ydhzymc').value=DWRUtil.getText('zy')" styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<logic:present name="zyList">
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td>
								${rs.ydqbjmc }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqbdm }" property="ydhbdm" name="rs">
									<font color="red" style="">��</font>
								</logic:notEqual>
								�༶
							</th>
							<td>
								<html:hidden property="save_ydhbjmc" styleId="ydhbjmc" value="${rs.ydhbjmc }"/><!-- �춯��༶���� -->
								<html:select property="save_ydhbdm" styleId="bj"
									onchange="$('ydhbjmc').value=DWRUtil.getText('bj')"
									style="width:180px" value="${rs.ydhbdm }">
									<html:option value=""></html:option>
									<logic:present name="bjList">
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��״̬</th>
							<td>
								${rs.ydqxjztm }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqxjztm }" property="ydhxjztm" name="rs">
									<font color="red" style="">��</font>
								</logic:notEqual>
								ѧ��״̬
							</th>
							<td>
								<html:select property="save_ydhxjztm" style="width:180px" value="${rs.ydhxjztm }" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
								${rs.ydqsfzx }
							</td>
							<th>
								<logic:notEqual value="${rs.ydqsfzx }" property="ydhsfzx" name="rs">
									<font color="red" style="">��</font>
								</logic:notEqual>
								�Ƿ���У
							</th>
							<td>
								<html:select property="save_ydhsfzx" style="width:180px" value="${rs.ydhsfzx }" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					</logic:present>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ���ɼ�
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>	
							<td colspan="4">
								<table style="width:100%">
									<tr>
										<td>
											ѧ��
										</td>
										<td>
											ѧ��
										</td>
										<td>
											�γ�����
										</td>
										<td>
											�γ�����
										</td>
										<td>
											�ɼ�
										</td>
										<td>
											�����ɼ�
										</td>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
									</tr>
									<logic:present name="cj">
									<logic:iterate id="c" name="cj">
										<tr>
											<td>${c.xn }</td>
											<td>${c.xq }</td>
											<td>${c.kcmc }</td>
											<td>${c.kcxz }</td>
											<td><font color="<logic:lessThan name="c" property="cj" value="60">red</logic:lessThan>" style="">${c.cj }</font></td>
											<td>${c.bkcj }</td>
											<td>${c.xf }</td>
											<td>${c.jd }</td>
										</tr>
									</logic:iterate>
									</logic:present>
									
								</table>
							</td>
						</tr>
					</tbody>
					
					
					
					<thead>
						<tr>
							<td colspan="4">
								<span>
									�����Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�����</th>
							<td>
								${userNameReal }
								<html:hidden property="shr" value="${userName }"/>
							</td>
							<th>���ʱ��</th>
							<td>
								${now }
								<html:hidden property="shsj" value="${now }"/>
							</td>
						</tr>
						<tr>
							<th>
							<font color="red">*</font>
							���״̬
							</th>
							<td>
								<html:select property="shjg" style="width:120px" styleId="shjg" 
											 value="${rs.shzt }" onchange="setThjsr(this.value)">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<span id="thjsrText" style="display:none;">
									<font color="red">*</font>������
								</span>
							</th>
							<td>
								<span id="thjsrZj" style="visibility:hidden">
									<html:select property="thjsr" styleId="thjsr" disabled="true">
										<html:option value="Applicant">������</html:option>
										<logic:present name="xtgwList">
											<html:options collection="xtgwList" property="id" labelProperty="mc"/>
										</logic:present>
									</html:select>
								</span>
							</td>
						</tr>
						
						<logic:equal value="true" name="isLastSpgw">
							<tr>
								<th>
									�춯ʱ��
								</th>
								<td>
									<html:text property="save_ydsj" styleId="ydsj"
											   onblur="dateFormatChg(this)" value="${now}"
											   onclick="return showCalendar('ydsj','y-mm-dd');"
									></html:text>
								</td>
								<th>
									�����ĺ�
								</th>
								<td>
									<html:text property="save_clwh" maxlength="20" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'');"></html:text>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								������
								<br/>
								<font color="red"><��500��></font>
							</th>
							<td colspan="3"><html:textarea property="shyj" style="width:95%" rows="5" onblur="chLeng(this,500)" value="${rs.shyj }"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<logic:present name="message">
			<script>
				alertInfo("${message}",function(t){
					if (t == 'ok'){
						if(window.dialogArguments){
							dialogArgumentsQueryChick();
							window.close();
						}
					}
				});
			</script>
		</logic:present>
	</body>
</html>
