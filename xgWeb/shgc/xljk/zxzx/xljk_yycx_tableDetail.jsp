<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function zxsyybb(){
				var url="/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=xljk_zxsyy";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		</script>
	</head>
	<body>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��������ԤԼ��ѯ���������Ϣ</a>
			</p>
		</div>
		
		<html:form action="/xljk_xssqyy_zysq" method="post">
			<input type="hidden" id="zxszy_xnid" name="zxszy_xnid" value="${zxszy_xnid }">
			<input type="hidden" id="sfzxg_flag" name="sfzxg_flag" 
			value="<bean:write name="rs" property="sfzxg_flag" scope="request"/>" />
			
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>��д�����</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>ѧ ��
					</th>
					<td align="left">
						<html:text name="rs" property="xh" styleId="xh" readonly="true" />
					</td>
					<th>
						�� �� 
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2" >
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="xs_sex" styleId="xs_sex"
							readonly="true" />
					</td>
					<th>
						�� �� �� ��
					</th>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�� ��
					</th>
					<td align="left">
						<html:text name="rs" property="jg" styleId="jg" />
					</td>
					<th>
						ѧ Ժ
					</th>
					<td align="left">
						<html:text name="rs" property="xy" styleId="xy" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						ר ҵ
					</th>
					<td align="left">
						<html:text name="rs" property="zy" styleId="zy" readonly="true" />
					</td>
					<th>
						�� ϵ �� ��
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�� �� �� ��
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" />
					</td>
					<th>
						�� ͥ �� �� ״ ��
					</th>
					<td align="left">
						<html:text name="rs" property="jtjjzk" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						��ѯʦ����
					</th>
					<td align="left">
						<html:text name="rs" property="zxxxm" readonly="true" />
					</td>
					<th>
						��ѯʦ�Ա�
					</th>
					<td align="left">
						<html:text name="rs" property="zxs_sex" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						��ѯ����
					</th>
					<td align="left">
						<html:text name="rs" property="rq" readonly="true" />
					</td>
					<th>
						��ѯʱ���
					</th>
					<td align="left">
						<html:text name="rs" property="sjd" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						��ѯ�ص�
					</th>
					<td align="left">
						<html:text name="rs" property="dd" />
					</td>
					<!-- ����ʦ�� -->
					<logic:equal value="10477" name="xxdm">
						<th>
							����̶�
						</th>
						<td align="left">
							<html:text name="rs" property="jjcd" />
						</td>
					</logic:equal>
					<logic:notEqual value="10477" name="xxdm">
					<th>
						����
					</th>
					<td align="left">
						<html:text name="rs" property="qt" />
					</td>
					</logic:notEqual>
				</tr>
				<tr>
					<th colspan="2">
						�� ѧ ר ҵ <br/> �� �� �� ��
					</th>
					<td align="left" colspan="1">
						<html:select name="rs" property="zymycd_dm" styleId="en">
							<option value=""></option>
							<html:options collection="mycdList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<logic:present name="xxdm" scope="request">
						<logic:equal value="10355" name="xxdm" scope="request">
							<th>
								<font color="red">*</font>ԤԼ��ʽ
							</th>
							<td align="left">
								<html:select property="yyfs" styleId="yyfs" name="rs">
									<option value=""></option>
									<html:options collection="yyfsList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</logic:equal>
					</logic:present>
				</tr>

				<tr>
					<th colspan="3">
							�Ƿ������������й���ѯ
					</th>
					<td colspan="2" align="center">
						<logic:equal name="rs" property="sfzxg_flag" value="yes">
							<input type="radio" name="sfzxg" id="sfzxg1" value="yes"
								checked="checked"/>
                					&nbsp;&nbsp;��
               	 				</logic:equal>
						<logic:equal name="rs" property="sfzxg_flag" value="no">
							<input type="radio" name="sfzxg" id="sfzxg2" value="no"
								checked="checked"/>
                					&nbsp;&nbsp;��
                				</logic:equal>
					</td>
				</tr>
				<logic:equal property="sfzxg_flag" name="rs" value="yes">
					<tr name="aa" id="a1">
						<th colspan="2">
							��������
						</th>
						<td align="left">
							<html:text name="rs" property="jgmc" />
						</td>
						<th>
							ҽ������
						</th>
						<td align="left">
							<html:text name="rs" property="ysxm" />
						</td>
					</tr>
					<tr name="aa" id="a2">
						<th colspan="2">
							��ʼʱ��
						</th>
						<td align="left">
							<html:text name="rs" property="qzsj" />
						</td>
						<th>
							�Ƿ�����ڷ���ҩ��
						</th>
						<td align="left">
							<html:text name="rs" property="sffyyw" />
						</td>
					</tr>
					<tr name="aa" id="a3">
						<th colspan="2">
							�к����,���������
						</th>
						<td colspan="4" align="left">
							<html:textarea rows="5" name="rs" style="width:98%"
								property="zdjg" styleId="a" />
						</td>
					</tr>
				</logic:equal>



				<tr>
					<th>
						��Ҫ�� <br/> ѯ������
					</th>
					<td colspan="5" align="left" style="word-break:break-all;">
						<html:textarea rows="5" style="width:98%" name="rs"
							property="zxnr" styleId="a" />
					</td>
				</tr>
				<tr>
					<th>
						����ѯ��<br/>������Ŀ��
					</th>
					<td colspan="5" align="left" style="word-break:break-all;">
						<html:textarea rows="5" style="width:98%" name="rs"
							property="qwmb" styleId="a" />
					</td>
				</tr>




				<tr>
					<th width="4%" rowspan="6">
						<div align="center">
							��
							<br/>
							ͥ
							<br/>
							��
							<br/>
							Ա
							<br/>
							��
							<br/>
							��
						</div>
					</th>
					<th width="12%" align="center">
						<center>�뱾�˹�ϵ</center>
					</th>
					<th width="9%" align="center">
						<center>��������</center>
					</th>
					<th width="25%" align="center">
						<center>ѧ��</center>
					</th>
					<th colspan="2" align="center">
						<center>ְҵ�����</center>
					</th>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="JTCY1_gx" name="JTCY1_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY1_gx"/>"/>
					</td>
					<td>
						<input type="text" id="JTCY1_csny" name="JTCY1_csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY1_csny" />"/>
					</td>
					<td>
						<input type="text" id="JTCY1_xl" name="JTCY1_xl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY1_xl" />"/>
					</td>
					<td colspan="2">
						<input type="text" id="JTCY1_zysf" name="JTCY1_zysf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY1_zysf" />"/>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="JTCY2_gx" name="JTCY2_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY2_gx"/>"/>
					</td>
					<td>
						<input type="text" id="JTCY2_csny" name="JTCY2_csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY2_csny" />"/>
					</td>
					<td>
						<input type="text" id="JTCY2_xl" name="JTCY2_xl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY2_xl" />"/>
					</td>
					<td colspan="2">
						<input type="text" id="JTCY2_zysf" name="JTCY2_zysf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY2_zysf" />"/>
					</td>
				<tr>
					<td width="12%">
						<input type="text" id="JTCY3_gx" name="JTCY3_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY3_gx"/>"/>
					</td>
					<td>
						<input type="text" id="JTCY3_csny" name="JTCY3_csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY3_csny" />"/>
					</td>
					<td>
						<input type="text" id="JTCY3_xl" name="JTCY3_xl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY3_xl" />"/>
					</td>
					<td colspan="2">
						<input type="text" id="JTCY3_zysf" name="JTCY3_zysf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY3_zysf" />"/>
					</td>
				<tr>
					<td width="12%">
						<input type="text" id="JTCY4_gx" name="JTCY4_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY4_gx"/>"/>
					</td>
					<td>
						<input type="text" id="JTCY4_csny" name="JTCY4_csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY4_csny" />"/>
					</td>
					<td>
						<input type="text" id="JTCY4_xl" name="JTCY4_xl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY4_xl" />"/>
					</td>
					<td colspan="2">
						<input type="text" id="JTCY4_zysf" name="JTCY4_zysf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY4_zysf" />"/>
					</td>
				<tr>
					<td width="12%">
						<input type="text" id="JTCY5_gx" name="JTCY5_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY5_gx"/>"/>
					</td>
					<td>
						<input type="text" id="JTCY5_csny" name="JTCY5_csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY5_csny" />"/>
					</td>
					<td>
						<input type="text" id="JTCY5_xl" name="JTCY5_xl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY5_xl" />"/>
					</td>
					<td colspan="2">
						<input type="text" id="JTCY5_zysf" name="JTCY5_zysf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="JTCY5_zysf" />"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
						<tr>
							<td colspan="6">
								
								<div class="btn">
									<!-- ����ѧԺ������ѯԤԼ���� -->
									<logic:equal name="xxdm" value="10395">
										<button onclick="zxsyybb()" id="saveButton">
										ԤԼ�����
										</button>
									</logic:equal>
									<button onclick="Close();return false;" id="saveButton">
										��  ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="rec_suc">
					<script>
    					alert("����ɹ���");
    					dialogArgumentsQueryChick();
						window.close();
    				</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nocondi">
					<script>
    alert("����������������,����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>

				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
