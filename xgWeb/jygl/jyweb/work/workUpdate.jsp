<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
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


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹ��Ϣ����</span>
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
										<logic:notEqual value="sh" name="doType">
											<html:hidden property="save_xxsh" value="������"/>
										
											<button
												onclick="saveUpdate('jywebUseCheckSession.do?method=workUpdate&doType=save','zpzw-zplx-save_yxqx')">
												����
											</button>
										</logic:notEqual>
										<logic:equal value="sh" name="doType">
											<button
												onclick="saveUpdate('jywebUseCheckSession.do?method=workUpdate&save_xxsh=ͨ��&doType=save','')">
												ͨ��
											</button>
											<button
												onclick="saveUpdate('jywebUseCheckSession.do?method=workUpdate&save_xxsh=��ͨ��&doType=save','')">
												��ͨ��
											</button>
											<button
												onclick="saveUpdate('jywebUseCheckSession.do?method=workUpdate&save_xxsh=�˻�&doType=save','')">
												�˻�
											</button>
										</logic:equal>
									</logic:notEqual>
									<button onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								��˾����
							</th>
							<td>
								${rs.gsmc}
								<html:hidden property="save_gsmc" value="${rs.gsmc}" />
								<logic:equal value="dw" name="jyweb_userType">
									<input type="hidden" name="save_xxsh" value="δ���" />
								</logic:equal>
							</td>
							<th>
								��˾��ַ
							</th>
							<td>
								${rs.dz }
							</td>
						</tr>
						<tr>
							<th>
								��˾����
							</th>
							<td>
								${rs.dwxzmc }
							</td>
							<th>
								��ҵ����
							</th>
							<td>
								${rs.hyflmc }
							</td>
						</tr>
						<tr>
							<th>
								�� ��
							</th>
							<td>
								${rs.cz }
							</td>
							<th>
								�� ַ
							</th>
							<td>
								${rs.wz }
							</td>
						</tr>
						<tr>
							<th>
								��˾���
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.dwjj }
							</td>
						</tr>
					</tbody>

					<thead>
						<tr>
							<td colspan="4">
								<span>��λ��Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��Ƹ����
							</th>
							<td colspan="3">
								<html:radio property="save_zplx" value="zy"
									onclick="changeZpzw('sel',this);" name="rs">רҵ</html:radio>
								<html:radio property="save_zplx" value="gw"
									onclick="changeZpzw('',this);" name="rs">��λ</html:radio>
							</td>
						</tr>
						<tr>
							<th width="70px">
								<font color="red">*</font>רҵ/��λ����
							</th>
							<td>
								<html:hidden property="save_zpzw" styleId="zpzw"
									value="${rs.zpzw}" />

								<logic:equal value="zy" name="rs" property="zplx">
									<html:text property="zpzw" styleId="txtZpzw"
										onchange="$('zpzw').value=this.value" value="${rs.zpzw}"
										style="display:none" />

									<html:select property="zydm" styleId="zydm" style="display:''"
										value="${rs.zpzw}" onchange="$('zpzw').value=this.value">
										<html:options collection="zyList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="zy" name="rs" property="zplx">
									<html:text property="zpzw" styleId="txtZpzw"
										onchange="$('zpzw').value=this.value" value="${rs.zpzw}"
										style="display:''" />

									<html:select property="zydm" styleId="zydm"
										style="display:none" value="${rs.zpzw}"
										onchange="$('zpzw').value=this.value">
										<html:options collection="zyList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:notEqual>


							</td>
							<th>
								��λ����
							</th>
							<td>
								<!-- <html:text property="save_gwxz" styleId="gwxz" /><span class="correct"></span>-->
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
								<html:text property="save_zpdd" styleId="zpdd"
									value="${rs.zpdd }" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th valign="top">
								��λְ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_gwzz" styleId="gwzz" rows="7"
									style="float:left; width: 70%" value="${rs.gwzz}">
								</html:textarea>
								<span style="color: red">������ϸ����ְ��Χ����������<br/>�Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>

						<tr>
							<th valign="top">
								ְλҪ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_zwyq" styleId="zwyq" rows="7"
									style="float:left;width: 70%" value="${rs.zwyq}"></html:textarea>
								<span style="color: red">������ϸ����ְ��Χ����������<br/>�Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType">
						<thead>
							<tr>
								<td colspan="4">
									<span>�����Ϣ</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
									<br />
									<font color="red"><��500��>
									</font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }"
										onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${nowTime }
									<html:hidden property="save_shsj" value="${nowTime }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="view" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${rs.shsj }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				</table>
			</div>

			<logic:present name="message">
				<script>
		 			alert("${message}");
		 			if(window.dialogArguments){
		 				window.close();
		 				dialogArgumentsQueryChick();
		 			}
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
