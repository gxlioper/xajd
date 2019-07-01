<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�������-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script language="javascript" defer="defer">

		</script>
	</head>
	<body ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
			
		<html:form action="/customForm" method="post">
			<!-- ������ begin-->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">
								<font color="red">*</font>��ID
							</th>
							<td width="25%">
								<input type="text" style="width:150px" id="form_id" maxlength="20"/>
							</td>
							<th width="25%">
								<font color="red">*</font>������
							</th>
							<td>
								<input type="text" style="width:150px" id="form_name" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ģ��
							</th>
							<td>
								<select style="width:150px" id="ssmk">
									<logic:iterate name="ssmkList" id="ssmkRs">
										<option value="${ssmkRs.dm }">${ssmkRs.mc }</option>
									</logic:iterate>
								</select>
							</td>
							<th>
								��ǰʱ��
							</th>
							<td>
								${cjsj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>���ݱ�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>���ݱ�
							</th>
							<td>
								<input type="text" style="width:150px" id="source_table" maxlength="50"/>
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td colspan="3">
								<input type="hidden" id="source_table_pk"/>
								<button type="button" class="btn_01" onclick="createChoosePkDiv();">ѡ��</button>
								<span id="span_source_table_pk"></span>
							</td>
						</tr>
						<tr>
							<th>
								������1
							</th>
							<td>
								<input type="text" style="width:150px" id="assistant_table_one" maxlength="50"/>
							</td>
							<th>
								�����ֶ�
							</th>
							<td colspan="3">
								<input type="hidden" id="assistant_table_one_zd"/>
								<input type="hidden" id="assistant_table_one_relate"/>
								<button type="button" class="btn_01" onclick="createChooseRelateDiv('one');">ѡ��</button>
								<span id="span_source_relate_one"></span>
							</td>
						</tr>
						<tr>
							<th>
								������2
							</th>
							<td>
								<input type="text" style="width:150px" id="assistant_table_two" maxlength="50"/>
							</td>
							<th>
								�����ֶ�
							</th>
							<td colspan="3">
								<input type="hidden" id="assistant_table_two_zd"/>
								<input type="hidden" id="assistant_table_two_relate"/>
								<button type="button" class="btn_01" onclick="createChooseRelateDiv('two');">ѡ��</button>
								<span id="span_source_relate_two"></span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ͼ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��ѯ��ͼ
							</th>
							<td>
								<input type="text" style="width:150px" id="search_view" maxlength="50"/>
							</td>
							<th>
								<font color="red">*</font>��ϸ��ͼ
							</th>
							<td>
								<input type="text" style="width:150px" id="detail_view" maxlength="50"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="checkSaveCustomForm();">�� ��</button>
									<button type="button" onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
				</table>
			</div>
			
			<!-- ѡ�������� -->
			<div id="div_choose_pk" style="display:none"></div>
			<!-- ����������� -->
			<div id="div_choose_relate" style="display:none"></div>
			
			<!-- ������Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>