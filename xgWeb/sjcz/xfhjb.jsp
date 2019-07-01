<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function savefa(){
			var bz = document.getElementById("bz").value;
			if(bz.length>60){
				alert("��ע�������ܳ���60��");
				return false;
			}
			if(checkXnNd('xn','nd'))dataDoSave('xh-nd');
		}
		function ffupdate(){
			var bz = document.getElementById("bz").value;
			if(bz.length>60){
				alert("��ע�������ܳ���60��");
				return false;
			}
			
			savefa();
		}
		</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">

			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/xfhjb.jsp" />

				<div class="tab">
					<table width="100%" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" class="button2" onclick="ffupdate();" id="buttonModi">
											�� ��
										</button>
										<button type="button" class="button2" onclick="savefa();"
											id="buttonSave">
											�� ��
										</button>
										<button type="button" class="button2" onclick="Close();return false;" 
											id="buttonClose">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ�ѻ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:text name='rs' property="xh" readonly="readonly"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										ѡ��
									</button>
								</td>
								<th>
									<font color="red">*</font>���
								</th>
								<td>
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td>
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq">
										<html:option value="" />
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name='rs' property="xymc" styleId="xymc" />
								</td>
								<th>
									�Ա�
								</th>
								<td>
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:text name='rs' property="zymc" styleId="zymc" />
								</td>
								<th>
									�������
								</th>
								<td align="left">
									<input type="text" width="100%" id="hjje" name="hjje"
										value="${rs.hjje}" onkeypress="return numInputValue(this,5,event)" />
									(Ԫ)
								</td>
							</tr>
							<tr>
								<th>
									�༶��
								</th>
								<td align="left">
									<html:text name='rs' property="bjmc" styleId="bjmc" />
								</td>
								<th>
									������ֹ����
								</th>
								<td align="left">
									<input type="text" width="100%" id="hjqx" readonly="true"
										name="hjqx" value="${rs.hjqx}"
										onclick="return showCalendar('hjqx','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<th>
									����ԭ��
								</th>
								<td colspan="3">
									<textarea rows="5" cols="100" name="bz" id="bz" type="_moz">${rs.bz}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>

			<logic:empty name="rs">
				<script>
					alert('����ɹ�');
					window.close();
				</script>
			</logic:empty>
		</html:form>
	</body>
</html>
