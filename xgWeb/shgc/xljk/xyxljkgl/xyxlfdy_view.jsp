<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" >
			function jd(){
				if($("jd")){
					$("jd").focus();
				}
			}
		</script>
	</head>
	<body onload="jd()">
		
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title } - �鿴</a>
			</p>
		</div>
		<html:form action="/xljk_xljkfdy" method="post">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th width="16%">
						<font color="red">*</font>
						<logic:equal value="10827" name="xxdm">
								����ר�ɱ��
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
								����Ա���
						</logic:notEqual>
					</th>
					<td align="left" width="34%">
						<html:text property="fdybh" styleId="fdybh" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>�� ��
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr>
				<logic:equal value="10827" name="xxdm">
					<th>
						<font color="red">*</font>�� ҵ Ժ У
					</th>
					<td align="left">
						<html:text property="byyx" styleId="byyx" readonly="true" />
					</td>
				</logic:equal>
				<logic:notEqual value="10827" name="xxdm">
					<th>
						<font color="red">*</font>�� ��
					</th>
					<td>
						<html:text property="xb"  styleId="xb"
							readonly="true" />
					</td>
				</logic:notEqual>
					<th>
						�� �� �� ��
					</th>
					<td align="left">
						<html:text property="csny" styleId="csrq" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>ѧ Ժ
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<logic:notEqual value="10827" name="xxdm">
					<th>
						<font color="red">*</font>�� ҵ Ժ У
					</th>
					<td align="left">
						<html:text property="byyx" styleId="byyx" readonly="true" />
					</td>
					</logic:notEqual>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>�� ְ ʱ ��
					</th>
					<td align="left">
						<html:text property="rzsj" styleId="rzsj" readonly="true" />
					</td>
					<th>
						ְ ��
					</th>
					<td align="left">
						<html:text property="zw" styleId="zw" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						ְ ��
					</th>
					<td align="left">
						<html:text property="zc" styleId="zc" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>ѧ ��
					</th>
					<td align="left">
						<html:text property="xl" styleId="xl" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>ר ҵ
					</th>
					<td align="left">
						<html:text property="zy" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>�� �� �� ��
					</th>
					<td align="left">
						<html:text property="sjhm" styleId="sjhm" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>�� ϵ �� ��
					</th>
					<td align="left">
						<html:text property="lxdh" readonly="true" />
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>��������������
						<br/>
						��������
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="gzjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>�μ�����������
						<br/>
						��ѵ���
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="pxqk"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>����������
						<br/>
						��������ļ�
						<br/>
						���гɹ�
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="lwcg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<th>
						�� ע
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="5" style="width:98%" property="bz"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
