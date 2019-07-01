<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script>
			function print(){
				var url = 'pjpyLsxy.do?method=printRychsqb';
				url += "&xh=" + val("xh");
				url += "&xn=" + val("xn");
				url += "&rychmc=" + selText("rychdm");
				url += "&zysj=" + val("zysj");
				showOpenWindow(url,800,600)
			}
			
			function save(){
				var xxsh= val('xxsh');
				var xysh= val('xysh');
				
				if(xxsh != "δ���" || xysh != "δ���"){
					alert("��Ϣ�Ѿ�������У���ʱ�����޸�");
					return false;
				}
				refreshForm('pjpyLsxy.do?method=rychModi&act=save');
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/pjpyLsxy" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="pkValue" id="pkValue"
				value="${rs.pkValue}" />
			<input type="hidden" name="xysh" id="xysh" value="${rs.xysh}" />
			<input type="hidden" name="xxsh" id="xxsh" value="${rs.xxsh}" />
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�޸�������Ϣ</span>
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
									<button name="�ύ" onclick="save();return false;">
										����
									</button>
									<button class="button2" id="btn_dy" onclick="print()">
										��ӡ�����
									</button>
									<button name="�ر�" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr style="height:23px">
							<th width="15%">
								<font color="red">*</font>ѧ��
							</th>
							<td  width="35%">
								<html:text name='rs' property="save_xh" styleId="xh"
									readonly="true" />
							</td>
							<th width="15%">
								���
							</th>
							<td align="left">
								<bean:write name="rs" property="nd" />
								<input type="hidden" name="save_nd" id="nd" value="${rs.nd}" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
								<input type="hidden" name="save_xn" id="xn" value="${rs.xn}" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xq" />
								<input type="hidden" name="save_xq" id="xq" value="${rs.xq}" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								�ۺϲ����ɼ�
							</th>
							<td align="left">
								<bean:write name="rs" property="zf" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								�ۺϲ�������
							</th>
							<td align="left">
								<bean:write name="rs" property="zfpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								רҵ
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								ͬ�꼶ͬרҵ�ۺϲ�������
							</th>
							<td align="left">
								<bean:write name="rs" property="zyzfpm" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								�༶
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th>
								ѧϰ�ɼ�
							</th>
							<td align="left">
								<bean:write name="rs" property="cj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								�༶����
							</th>
							<td align="left">
								<bean:write name="rs" property="bjrs" />
							</td>
							<th>
								ѧϰ�ɼ�����
							</th>
							<td align="left">
								<bean:write name="rs" property="cjpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								��������
							</th>
							<td align="left">
								<bean:write name="rs" property="csrq" />
							</td>
							<th>
								ͬ�꼶ͬרҵѧϰ�ɼ�����
							</th>
							<td align="left">
								<bean:write name="rs" property="zycjpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								�� ��
							</th>
							<td align="left">
								<bean:write name="rs" property="jg" />
							</td>
							<th>
								���ʽ����ɼ�
							</th>
							<td align="left">
								<bean:write name="rs" property="tzjkcj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								������ò
							</th>
							<td align="left">
								<bean:write name="rs" property="zzmmmc" />
							</td>
							<th>
								�Ƿ���Υ���ܴ���
							</th>
							<td align="left">
								<bean:write name="rs" property="sfwj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								<font color="red">*</font>�����ƺ�
							</th>
							<td align="left" colspan="3">
								<html:select name='rs' property="rychdm" styleId="rychdm"
									styleClass="select" style="width:150px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
								<input type="hidden" id="save_rychdm" name="save_rychdm"
									value="${rs.rychdm}" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								�걨����
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_zysj" styleId="zysj"
									styleClass="inputtext" rows="5" style="width:98%"
									onblur="chLeng(this,'500')"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_bz" styleId="bz"
									style="width: 98%" styleClass="inputtext" rows="5"
									onblur="chLeng(this,'500')" name="rs"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- �������ʾҳ�� -->
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('�����ɹ���');
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<input id="msg" name="" value="${message}" />
					<script>
					alert(document.getElementById("msg").value);
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
