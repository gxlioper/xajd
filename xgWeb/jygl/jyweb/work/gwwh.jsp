<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveGwxx(){
				$('gsmc').value = window.dialogArguments.document.getElementById('gsmc').value;
				saveDataShowTips('jywebUseCheckSession.do?method=gwwh&doType=save','zpzw-zplx-save_yxqx','���ڴ����������Ժ�!')
			}

			function changeZpzw(flg,obj){
				if ('sel'==flg){
					if(obj.checked){$('zydm').style.display='';}{$('txtZpzw').style.display='none'}
				}else {
					if(obj.checked){$('zydm').style.display='none';}{$('txtZpzw').style.display=''}
				}
			}
			
		</script>
	</head>
	<body>
		<html:form action="/jyweb.do" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="hidden" name="save_fblx" value="ѧУ" />
			<input type="hidden" name="save_xxsh" value="ͨ��" />
			<html:hidden property="save_gsmc" styleId="gsmc"/>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹ��λ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button
											onclick="saveGwxx();">
											�� ��
										</button>
									</logic:notEqual>
									<button onclick="Close();return false;">
										�ر�
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th><font color="red">*</font>��Ƹ����</th>
							<td colspan="3">
								<html:radio property="save_zplx" value="zy" onclick="changeZpzw('sel',this);">רҵ</html:radio>
								<html:radio property="save_zplx" value="gw" onclick="changeZpzw('',this);">��λ</html:radio>
							</td>
						</tr>
						<tr>
							<th width="70px">
								<font color="red">*</font>רҵ/��λ����
							</th>
							<td>
								<html:hidden property="save_zpzw" styleId="zpzw" value="${rs.zpzw}"/>
								
								<html:text property="zpzw" styleId="txtZpzw"
										   onchange="$('zpzw').value=this.value"
									       value="${rs.zpzw}"  style="display:none" />
									
								<html:select property="zydm" styleId="zydm" 
											 style="display:''" value="${rs.zpzw}" 
											 onchange="$('zpzw').value=this.value">
									<html:options collection="zyList" property="dm" labelProperty="mc"/>
								</html:select>
									
							</td>
							<th>
								��λ����
							</th>
							<td>
								<html:select property="save_gwxz" styleId="gwxz"
									value="${rs.gwxz}">
									<html:option value="ȫְ">ȫְ</html:option>
									<html:option value="��ְ">��ְ</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td>
								<html:text property="save_gzdd" styleId="gzdd"
									value="${rs.gzdd}" />
							</td>
							<th>
								��Ƹ����
							</th>
							<td>
								<html:text property="save_zprs" styleId="zprs"
									onblur="checkInputData(this);" maxlength="5" value="${rs.zprs}" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�Ҫ��
							</th>
							<td>
								<html:select property="save_xb" styleId="xb" value="${rs.xb }">
									<html:options collection="xbList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								ѧ��Ҫ��
							</th>
							<td>
								<html:text property="save_xlyq" styleId="xlyq"
									value="${rs.xlyq }" />
							</td>
						</tr>
						<tr>
							<th>
								����Ҫ��
							</th>
							<td>
								<html:text property="save_wyyq" styleId="wyyq"
									value="${rs.wyyq}" />
							</td>
							<th>
								<font color="red">*</font>��Ч����
							</th>
							<td>
								<html:text property="save_yxqx" styleId="yxqx"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur="dateFormatChg(this);" value="${rs.yxqx }" />
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��
							</th>
							<td>
								<html:text property="save_lxr" styleId="lxr" value="${rs.lxr }" />
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								<html:text property="save_lxdh" styleId="lxdh"
									onblur="checkInputData(this);" value="${rs.lxdh }" />
							</td>
						</tr>
						<tr>
							<th>
								��Ƹʱ��
							</th>
							<td>
								<html:text property="save_zpsj" styleId="zpsj"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur="dateFormatChg(this);" value="${rs.zpsj }" />
							</td>
							<th>
								��Ƹ�ص�
							</th>
							<td>
								<html:text property="save_zpdd" styleId="zpdd" value="${rs.zpdd }" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th valign="top">
								��λְ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_gwzz" styleId="gwzz"
									rows="7" style="float:left; width: 70%" value="${rs.gwzz}">
								</html:textarea>
								<span style="color: red">������ϸ����ְ��Χ�����������Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>
						
						<tr>
							<th valign="top">
								ְλҪ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_zwyq" styleId="zwyq" 
									rows="7" style="float:left;width: 70%" value="${rs.zwyq}"></html:textarea>
								<span style="color: red">������ϸ����ְ��Χ�����������Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>
						<logic:equal value="sh" name="doType">
							<tr>
								<th>
									<font color="red">*</font>ѧУ���
								</th>
								<td>
									<html:select property="save_xxsh" value="${rs.xxsh }">
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>

			<logic:present name="message">
				<script>
			 		alert("${message}");
			 		if(window.dialogArguments){
			 			window.close();
			 			window.dialogArguments.refresh(window.dialogArguments.document.URL);
			 		}
 				</script>
			</logic:present>
		</html:form>
	</body>
</html>
