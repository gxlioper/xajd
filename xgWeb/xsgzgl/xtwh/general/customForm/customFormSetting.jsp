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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function(){
			createCustomForm();
		});
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
			<!-- ��-->
			<input type="hidden" id="ssb" value="${formInfo.source_table }"/>
			<!-- ��ID -->
			<input type="hidden" name="str_form_id" id="hidden_form_id" value="${formInfo.form_id }"/>
			<!-- ��ʾ˳�� -->
			<input type="hidden" name="str_xssx" id="hidden_xssx" value="${xssx }"/>
			<!-- ѡ��TableID -->
			<input type="hidden" id="cliked_table_id" value=""/>
			<!-- ѡ���� -->
			<input type="hidden" id="cliked_row_num" value=""/>
			<!-- ѡ���� -->
			<input type="hidden" id="cliked_column_num" value=""/>
			<!-- ����·�� -->
			<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
			
			<!-- ѡ�ж��� -->
			<div id="div_create_clicked_obj" style="display:none"></div>
			<!-- ������ end -->
			
			<table border="1" bordercolor="#000000" style="width:100%;height: 100%">
				<thead>
					<tr bgcolor="#99CCFF">
						<td colspan="2" height="25px">
							�Զ��x�������
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%" valign="top">
							<table class="formlist" style="width: 100%">
								<!-- ������ begin -->
								<thead onclick="clickTHead('tb_bdcz')" style="cursor:hand">
									<tr><td>������	</td></tr>
								</thead>
								<tbody id="tb_bdcz">
									<tr>
										<td>
											<a href="#" onclick="showTableDiv();return false;">
												<font color="blue">
													���ӱ�
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											ɾ����
										</td>
									</tr>
									<tr>
										<td>
											������
										</td>
									</tr>
									<tr>
										<td>
											ɾ����
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="saveCoalitionCol();return false;">
												<font color="blue">
													�ϲ���
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="cancelCoalitionCol();return false;">
												<font color="blue">
													ȡ���ϲ���
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="showRowDiv();return false;">
												<font color="blue">
													�ϲ���
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="cancelCoalitionRow();return false;">
												<font color="blue">
													ȡ���ϲ���
												</font>
											</a>
										</td>
									</tr>
								</tbody>
								<!-- ������ end -->
								
								<!-- ���ݱ���� begin -->
								<thead onclick="clickTHead('tb_zdcz_${formInfo.source_table}');createCzzdTable('${formInfo.source_table}');" style="cursor:hand">
									<tr><td>${formInfo.source_table}�ֶβ���	</td></tr>
								</thead>
								<tbody id="tb_zdcz_${formInfo.source_table}" style="display:none">
									<tr>
										<td>
											<div id="div_czzd_${formInfo.source_table}"
												style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
											</div>
										</td>
									</tr>
								</tbody>
								<!-- ���ݱ���� end -->
								
								<!-- ������1���� begin -->
								<logic:notEmpty name="formInfo" property="assistant_table_one">
									<thead onclick="clickTHead('tb_zdcz_${formInfo.assistant_table_one}');createCzzdTable('${formInfo.assistant_table_one}');" style="cursor:hand">
										<tr><td>${formInfo.assistant_table_one}�ֶβ���	</td></tr>
									</thead>
									<tbody id="tb_zdcz_${formInfo.assistant_table_one}" style="display:none">
										<tr>
											<td>
												<div id="div_czzd_${formInfo.assistant_table_one}"
													style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
												</div>
											</td>
										</tr>
									</tbody>
								</logic:notEmpty>
								<!-- ������1���� end -->
								
								<!-- ������2���� begin -->
								<logic:notEmpty name="formInfo" property="assistant_table_two">
									<thead onclick="clickTHead('tb_zdcz_${formInfo.assistant_table_two}');createCzzdTable('${formInfo.assistant_table_two}');" style="cursor:hand">
										<tr><td>${formInfo.assistant_table_two}�ֶβ���	</td></tr>
									</thead>
									<tbody id="tb_zdcz_${formInfo.assistant_table_two}" style="display:none">
										<tr>
											<td>
												<div id="div_czzd_${formInfo.assistant_table_two}"
													style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
												</div>
											</td>
										</tr>
									</tbody>
								</logic:notEmpty>
								<!-- ������2���� end -->
								
							</table>
						</td>
						<td  valign="top" rowspan="2">
							<div id="div_customForm">
								
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			
			<!-- ��DIV begin -->
			<div id="div_table" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>TABLE��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									����
								</th>
								<td>
									<input type="text" name="str_title" id="title" style="width:150px"/>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>����
								</th>
								<td>
									<input type="text" name="str_row_num" id="row_num" 
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" 
										style="width:150px;ime-mode:disabled"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="checkSaveTable()">�� ��</button>
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��DIV end -->
			
			<!-- �ϲ�RowDIV begin -->
			<div id="div_RowSpan" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�кϲ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>�ϲ�����
								</th>
								<td>
									<input type="text" name="str_rowspan" id="rowspan" 
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" 
										style="width:150px;ime-mode:disabled"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveCoalitionRow()">�� ��</button>
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �ϲ�RowDIV end -->
			
			<!-- ���ҵ���DIV begin -->
			<div id="div_area" style="display:none">	
			</div>
			<!-- ���ҵ���DIV end -->
			
			<!-- �޸�����DIV begin -->
			<div id="div_configure" style="display:none">	
			</div>
			<!-- �޸�����DIV end -->
			
			<!-- �ϴ�ѧ����Ƭ -->
			<div id="div_stuPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<!-- ������Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>