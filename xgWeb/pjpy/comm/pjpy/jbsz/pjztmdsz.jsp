<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript">
		//��������������Ա
		function saveRysz(){
			
			if($("bj") && $("bj").value==""){
				alert("��ǰ���ڰ༶����Ϊ��!");
				return false;
			}
			
			if($("pjbj") && $("pjbj").value==""){
				alert("�������ڰ༶����Ϊ��!");
				return false;
			}
			refreshForm('/xgxt/pjpyRyqd.do?method=pjztmdsz&doType=save');
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>������Ա����
				</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<div class="prompt">
			<h3>
				<span>�������ڣ�</span>
			</h3>
			<p>
				����ѧ��(${pjxtszModel.pjxn })&nbsp;&nbsp;
				����ѧ��(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
				�������(${pjxtszModel.pjnd })&nbsp;&nbsp;
			</p>
		</div>
		
		<html:form action="/pjpyRyqd">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="pjxyV" value="" />
			<input type="hidden" name="pjzyV" value="" />
			<input type="hidden" name="pjbjV" value="" />
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
				<!-- ����ʱ������ -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ա��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
							<table width="100%">
								<tr>
									<th colspan="2">
										<div align="left">���ڲ���</div>
									</th>
									<th colspan="2">
										<div align="left">��������</div>
									</th>
								</tr>
								<tr>
									<th>
										�꼶
									</th>
									<td>	
										<html:select property="nj" styleId="nj" style="width:180px"
											 onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										�꼶
									</th>
									<td>
										<html:select property="pjnj" styleId="pjnj"
											style="width:180px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjnjList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy"/>
									</th>
									<td>
										<html:select property="xydm" styleId="xy" onmouseover="null"
											style="width:180px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy"/>
									</th>
									<td>
										<html:select property="pjxydm" styleId="pjxy" onmouseover="null"
											style="width:180px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjxyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="zydm" styleId="zy" onmouseover="null"
											style="width:180px" onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="pjzydm" styleId="pjzy" onmouseover="null"
											style="width:180px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjzyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<span class="red">*</span>�༶
									</th>
									<td>
										<html:select property="bjdm" styleId="bj"
											style="width:180px" onmouseover="null">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th>
										<span class="red">*</span>�༶
									</th>
									<td>
										<html:select property="pjbjdm" styleId="pjbj"
											style="width:180px" onmouseover="null">
											<html:option value=""></html:option>
											<html:options collection="pjbjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveRysz()" id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;" id="buttonSave">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('����ʧ�ܣ�');
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>
				</logic:notEqual>
		</logic:present>
		</html:form>
	</body>
</html>
