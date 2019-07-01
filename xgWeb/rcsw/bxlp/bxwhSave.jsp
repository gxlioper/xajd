<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/checkUtils.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript">
		
			function loadBxgs(text){
				if ("" != text){
					getInsureInfo.getBxgsdm(text,function(data){
						$('tbgsdm').value = data;
					});
				} else {
					$('tbgsdm').value = "";
				}
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ�����-���ա�����-������Ϣά��</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_bxlp.do?method=bxwhSave" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ա�����-������Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<span> "<font color="red">*</font>"Ϊ������ </span>
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button type="button" id="buttonSave"
												onclick="saveUpdate('/xgxt/rcsw_bxlp.do?method=bxwhSave&doType=save',$('sfbtString').value+'xh-je-nx-bxgsdm-tbxzdm');">
												ȷ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="view" name="doType">
										<button type="button" id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:equal>
									<logic:equal value="update" name="doType">
										<button type="button" id="buttonSave"
											onclick="saveUpdate('/xgxt/rcsw_bxlp.do?method=bxwhSave&doType=modi',$('sfbtString').value+'xh-je-nx-bxgsdm-tbxzdm');">
											�޸�
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:notEqual value="stu" name="userType" scope="session">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
												class="btn_01" id="buttonFindStu">
												ѡ��
											</button>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<html:text property="xm" name="rs" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:text property="xb" name="rs" readonly="true"></html:text>
							</td>
							<th>
								���֤��
							</th>
							<td>
								<html:text property="sfzh" readonly="true" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" name="rs" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td>
								<html:select property="xydm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								<html:select property="zydm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶����
							</th>
							<td>
								<html:select property="bjdm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���չ�˾
							</th>
							<td>
								<html:select property="bxgsdm" name="rs"
									onchange="loadBxxzList()" styleId="tbgsdm">
									<html:options collection="bxgsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>Ͷ������
							</th>
							<td>
								<html:select property="tbxzdm" name="rs"
									onchange="loadBxgs(this.value)" styleId="tbxzdm">
									<html:options collection="bxxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>


						<tr>
							<th>
								<font color="red">*</font>���
							</th>
							<td>
								<html:text property="je" name="rs" onblur="checkMoney(this);"
									maxlength="10" styleId="bf"></html:text>
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:text property="nx" name="rs" styleId="bxnx" maxlength="2"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr> 
						<customTag:customTable rsname="rs" nodeslist="bxlpForm"
							doType="updata" scope="request" />
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("����ɹ���");
	         	if(window.dialogArguments){
					 window.close();
					 window.dialogArguments.document.getElementById('search_go').click();
				}
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
