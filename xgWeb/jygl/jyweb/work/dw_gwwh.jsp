<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
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
			<input type="hidden" name="save_fbsj" value="${fbsj }"  id="fbsj"/>
			<input type="hidden" name="save_fblx" value="��λ" />
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
									<logic:notEqual value="view" name="operation">
										<button
											onclick="saveDataShowTips('jywebUseCheckSession.do?method=workAdd&doType=save',
              'zpzw-gwxz-gzdd-zprs-xb-lxr-yddh-yxqx','���ڴ����������Ժ�!')">
											�� ��
										</button>
										<button onclick="Close();return false;">
											�ر�
										</button>
									</logic:notEqual>

								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="60px">
								��˾����
							</th>
							<td>	${companyInfo.dwmc}
									<html:hidden property="save_gsmc" value="${companyInfo.dwmc}" />
							</td>
							<th width="60px">
								��˾��ַ
							</th>
							<td>
								${companyInfo.dz }
							</td>
						</tr>
						<tr>
							<th>
								��˾����
							</th>
							<td>
								${companyInfo.dwxzmc }
							</td>
							<th>
								��ҵ����
							</th>
							<td>
								${companyInfo.hyflmc }
							</td>
						</tr>
						<tr>
							<th>
								�� ��
							</th>
							<td>
								${companyInfo.cz }
							</td>
							<th>
								�� ַ
							</th>
							<td>
								${companyInfo.wz }
							</td>
						</tr>
						<tr>
							<th>
								��˾���
							</th>
							<td colspan="3" style="word-break:break-all;">
								${companyInfo.dwjj }
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
								<input type="radio" name="save_zplx" value="zy"
									onclick="changeZpzw('sel',this);" checked="checked"/>רҵ
								<input type="radio" name="save_zplx" value="gw"
									onclick="changeZpzw('',this);" />��λ
							</td>
						</tr>
						<tr>
							<th width="70px">
								<font color="red">*</font>רҵ/��λ����
							</th>
							<td>
								<html:hidden property="save_zpzw" styleId="zpzw"/>

									<html:text property="zpzw" styleId="txtZpzw"
										onchange="$('zpzw').value=this.value" style="display:none" />

									<html:select property="zydm" styleId="zydm" style="display:''"
										onchange="$('zpzw').value=this.value">
										<html:options collection="zyList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<th>
								<font color="red">*</font>��λ����
							</th>
							<td>
								<!--                  	<html:text property="save_gwxz" styleId="gwxz" /><span class="correct"></span>-->
								<html:select property="save_gwxz" styleId="gwxz">
									<html:option value="ȫְ">ȫְ</html:option>
									<html:option value="��ְ">��ְ</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�����ص�
							</th>
							<td>
								<html:text property="save_gzdd" styleId="gzdd" />
							</td>
							<th>
								<font color="red">*</font>��Ƹ����
							</th>
							<td>
								<html:text property="save_zprs" onblur="checkInputData(this);"
									maxlength="5" styleId="zprs" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ա�Ҫ��
							</th>
							<td>
								<html:select property="save_xb" styleId="xb">
									<html:options collection="xbList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>ѧ��Ҫ��
							</th>
							<td>
								<html:text property="save_xlyq" styleId="xlyq" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����Ҫ��
							</th>
							<td>
								<html:text property="save_wyyq" styleId="wyyq" />
							</td>
							<th>
								<font color="red">*</font>��Ч����
							</th>
							<td>
								<html:text property="save_yxqx" styleId="yxqx"
									 onblur="dateFormatChg(this);if(this.value < document.getElementById('fbsj').value){alert('��Ч�ڲ������ڵ�ǰʱ�䣡'); this.value=''}" 
									 onclick="showCalendar(this.id,'y-mm-dd')"
									/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ϵ��
							</th>
							<td>
								<html:text property="save_lxr" styleId="lxr" />
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text property="save_lxdh" onblur="checkInputData(this);"
									styleId="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								��Ƹʱ��
							</th>
							<td>
								<html:text property="save_zpsj" styleId="zpsj"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur="dateFormatChg(this);" />
							</td>
							<th>
								��Ƹ�ص�
							</th>
							<td>
								<html:text property="save_zpdd" styleId="zpdd" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th valign="top">
								��λְ��
							</th>
							<td colspan="3">
								<html:textarea property="save_gwzz" styleId="gwzz" rows="7"
									style="float:left; width: 70%">
								</html:textarea>
								<span style="color: red">������ϸ����ְ��Χ�����������Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>
						<tr>
							<th valign="top">
								ְλҪ��
							</th>
							<td colspan="3">
								<html:textarea property="save_zwyq" styleId="zwyq" rows="7"
									style="float:left;width: 70%"></html:textarea>
								<span  style="color: red">������ϸ����ְ��Χ�����������Լ�ȡ�õĳɼ�����1500�������֣�</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<logic:present name="message">
				<script>
		 			alert("${message}");
		 			
		 			if(window.dialogArguments){
		 				close();
		 				dialogArgumentsQueryChick();
		 			}
		 			
		 		</script>
			</logic:present>
		</html:form>
	</body>
</html>
