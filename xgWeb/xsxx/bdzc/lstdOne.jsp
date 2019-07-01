<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script type="text/javascript" src="js/check.js"></script>
</head>
<body>
	<html:form action="/bdzcgl" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<input type="hidden" id="url" name="url" value="/bdzcgl.do?method=lstdOne" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
			<tr>
				<th colspan="4"><span>��ɫͨ��</span></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="16%"><span class="red">*</span>ѧ��</th>
				<td width="34%">
					
					<logic:equal value="" name="doType">
						<html:text  property="save_xh" styleId="xh"  value="${rs.xh}"
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"
						/>
					</logic:equal>
					<logic:notEqual value="" name="doType">
						<html:text  property="save_xh" styleId="xh"  value="${rs.xh}" readonly="true"
						/>
					</logic:notEqual>
					
					<logic:notEqual value="stu" name="userType">
						<logic:notEqual value="view" name="doType">
							<logic:notEqual value="modi" name="doType">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>
				</td>
				<th  width="16%">����</td>
				<td>
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>�꼶</th>
				<td>
					${rs.nj }
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
				</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>
					${rs.zymc }
				</td>
				<th>�༶</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			
			<tr>
				<th>ѧ��</th>
				<td>
					<html:hidden property="save_xn" value="${xn }${rs.xn }"/>
					<html:select property="save_xn" value="${xn }${rs.xn }" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<th>ѧ��</th>
				<td>
					<html:hidden property="save_xq" value="${currXq }${rs.save_xq }"/>
					<html:select property="save_xq" value="${currXq }${rs.save_xq }" disabled="true">
						<html:option value=""></html:option>
						<html:options collection="xq" property="xqdm" labelProperty="xqmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>���</th>
				<td>
					<html:text property="save_je" maxlength="10" style="width:80px" onblur="checkMoney(this);" name="rs"></html:text>
				</td>
				<th>��ѧ����ѧ��</th>
				<td>
					 <script>
					 	if (${rs.xz}-Number(${zxdk.fkxn })+1>4)
						 document.write('���Ļ��ѱ�ҵѧ��û��<br/>��Ӧ����ѧ�����¼!');
						else 
						 document.write(${rs.xz}-Number(${zxdk.fkxn })+1);
					 </script>	
				</td>
			</tr>
			<tr>
				<th>�Ƿ�Ƿ��</th>
				<td>
					<logic:present name="qfqk">
						<logic:equal value=""name="qfqk" property="sfqf">
							��
						</logic:equal>
						<logic:notEqual value=""name="qfqk" property="sfqf">
							${qfqk.sfqf }
						</logic:notEqual>
					</logic:present>
				</td>
				<th>Ƿ�ѽ��</th>
				<td>
					${qfqk.qfje }
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4"><span>��ѧ�������</span></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>��ͬ���</th>
				<td>
					${zxdk.htbh }
				</td>
				<th>������</th>
				<td>
					${zxdk.sqdkje }
				</td>
			</tr>
			<tr>
				<th>��������(��)</th>
				<td>
					${zxdk.dkqxy }
				</td>
				<th>��������</th>
				<td>
					${zxdk.dkqx }
				</td>
			</tr>
			<tr >
				<th>�ſ��ܽ��</th>
				<td>
					${zxdk.fkzje }
				</td>
				<th>�������</th>
				<td>
					${zxdk.dkye }
				</td>
			</tr>
			
			<tr >
				<th>ѧ��</th>
				<th>
					�ſ���
				</th>
				<th>�Ƿ�ſ�</th>
				<th>
					���ʱ��
				</th>
			</tr>
			<tr >
				<td align="right">
					1
				</td>
				<td align="right">
					${zxdk.fk_xn1_je }
				</td>
				<td align="right">
					${zxdk.fk_xn1_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn1_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					2
				</td>
				<td align="right">
					${zxdk.fk_xn2_je }
				</td>
				<td align="right">
					${zxdk.fk_xn2_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn2_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					3
				</td>
				<td align="right">
					${zxdk.fk_xn3_je }
				</td>
				<td align="right">
					${zxdk.fk_xn3_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn3_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					4
				</td>
				<td align="right">
					${zxdk.fk_xn4_je }
				</td>
				<td align="right">
					${zxdk.fk_xn4_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn4_tksj }
				</td>
			</tr>
			<logic:equal value="5" name="rs" property="xz">
				<tr >
					<td align="right">
						5
					</td>
					<td align="right">
						${zxdk.fk_xn5_je }
					</td>
					<td align="right">
						${zxdk.fk_xn5_sffk }
					</td>
					<td align="right">
						${zxdk.fk_xn5_tksj }
					</td>
				</tr>
			</logic:equal>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <logic:equal value="view" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</button>
					</logic:equal>
					<logic:equal value="modi" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=lstdOne&doType=update','save_xh-save_xn-save_xq-save_je');">
							��&nbsp;&nbsp;&nbsp;&nbsp;��
						</button>
					</logic:equal>
					<logic:notEqual value="view" name="doType">
						<logic:notEqual value="modi" name="doType">
							<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=lstdOne&doType=save','save_xh-save_xn-save_xq-save_je');">
								��&nbsp;&nbsp;&nbsp;&nbsp;��
							</button>
						</logic:notEqual>
					</logic:notEqual>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>