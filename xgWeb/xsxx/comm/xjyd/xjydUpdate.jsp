<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
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
			<html:hidden property="save_id" value="${rs.id }"/>
			<html:hidden property="save_sqr" value="${userNameReal }"/>
		
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
									<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
										<%--
										<button type="button" name="�� ��" onclick="saveUpdate('xsxxXjyd.do?method=xjydUpdate&doType=save','xh-ydlbm')">
											�� ��
										</button>
										--%>
										<button type="button" name="�� ��" onclick="saveUpdate('xsxxXjyd.do?method=xjydUpdate&doType=save&sftj=��','xh-ydlbm')">
											�� ��
										</button>
									</logic:notEqual>
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
								ѧ��
							</th>
							<td>
								<html:select property="save_xn" style="width:180px" 
									styleId="xn" styleClass="select" value="${rs.xn }">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>	
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="save_xq" style="width:180px" 
									styleId="xq" styleClass="select" value="${rs.xq }">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
								<th>
									�춯ʱ��
								</th>
								<td>
									<html:text property="ydsj" styleId="ydsj"
											   onblur="dateFormatChg(this)" value="${rs.ydsj}"
											   onclick="return showCalendar('ydsj','y-mm-dd');"
									></html:text>
								</td>
								<th>
									�����ĺ�
								</th>
								<td>
									<html:text property="clwh" value="${rs.clwh}" maxlength="20" 
											   onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'');"></html:text>
								</td>
							</tr>
						<tr>
							<th>
								��������
								<br/>
								<font color="red"><��400��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_ydsm" style="width:90%" rows="5" onblur="checkLen(this,400)" value="${rs.ydsm }"></html:textarea>
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
								�꼶
							</th>
							<td>
								<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhnj" styleId="nj" value="${rs.ydhnj }"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</logic:notEqual>
								<logic:equal value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhnj" styleId="nj" value="${rs.ydhnj }" disabled="true"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								${rs.ydqxymc }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:hidden property="save_ydhxymc" styleId="ydhxymc" value="${rs.ydhxymc }"/><!-- �춯��ѧԺ���� -->
								<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhxydm" value="${rs.ydhxydm }"
										onchange="initZyList();initBjList();$('ydhxymc').value=DWRUtil.getText('xy')" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
								<logic:equal value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhxydm" value="${rs.ydhxydm }" disabled="true"
										onchange="initZyList();initBjList();$('ydhxymc').value=DWRUtil.getText('xy')" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								${rs.ydqzymc }
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:hidden property="save_ydhzymc" styleId="ydhzymc" value="${rs.ydhzymc }"/><!-- �춯��רҵ���� -->
								<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhzydm" value="${rs.ydhzydm }"
										onchange="initBjList();$('ydhzymc').value=DWRUtil.getText('zy')" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</logic:notEqual>
								<logic:equal value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhzydm" value="${rs.ydhzydm }" disabled="true"
										onchange="initBjList();$('ydhzymc').value=DWRUtil.getText('zy')" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td>
								${rs.ydqbjmc }
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:hidden property="save_ydhbjmc" styleId="ydhbjmc" value="${rs.ydhbjmc }"/><!-- �춯��༶���� -->
								<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhbdm" styleId="bj"
										onchange="$('ydhbjmc').value=DWRUtil.getText('bj')"
										style="width:180px" value="${rs.ydhbdm }">
										<html:option value=""></html:option>
										<logic:present name="bjList">
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</logic:notEqual>
								<logic:equal value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhbdm" styleId="bj"
										onchange="$('ydhbjmc').value=DWRUtil.getText('bj')"
										style="width:180px" value="${rs.ydhbdm }" disabled="true">
										<html:option value=""></html:option>
										<logic:present name="bjList">
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>ѧ��״̬</th>
							<td>
								${rs.ydqxjztm }
							</td>
							<th>
								ѧ��״̬
							</th>
							<td>
								<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhxjztm" style="width:180px" value="${rs.ydhxjztm }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
									</html:select>
								</logic:notEqual>
								<logic:equal value="view" property="doType" name="xsxxXjydForm">
									<html:select property="save_ydhxjztm" style="width:180px" value="${rs.ydhxjztm }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
								${rs.ydqsfzx }
							</td>
							<th>
								�Ƿ���У
							</th>
							<td>
							<logic:notEqual value="view" property="doType" name="xsxxXjydForm">
								<html:select property="save_ydhsfzx" style="width:180px" value="${rs.ydhsfzx }" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
								</html:select>
							</logic:notEqual>
							<logic:equal value="view" property="doType" name="xsxxXjydForm">
								<html:select property="save_ydhsfzx" style="width:180px" value="${rs.ydhsfzx }" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
								</html:select>
							</logic:equal>
							</td>
						</tr>
					</tbody>
					</logic:present>
				</table>
			</div>
		</html:form>
		
		<logic:present name="message">
			<script>
				alert("${message}");
				if(window.dialogArguments){
					dialogArgumentsQueryChick();
					window.close();
				}
			</script>
		</logic:present>
	</body>
</html>
