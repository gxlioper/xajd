<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：偉大的駱-->
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
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
			
		<html:form action="/customForm" method="post">
			<!-- 隐藏域 begin-->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 表-->
			<input type="hidden" id="ssb" value="${formInfo.source_table }"/>
			<!-- 表单ID -->
			<input type="hidden" name="str_form_id" id="hidden_form_id" value="${formInfo.form_id }"/>
			<!-- 显示顺序 -->
			<input type="hidden" name="str_xssx" id="hidden_xssx" value="${xssx }"/>
			<!-- 选中TableID -->
			<input type="hidden" id="cliked_table_id" value=""/>
			<!-- 选中行 -->
			<input type="hidden" id="cliked_row_num" value=""/>
			<!-- 选中列 -->
			<input type="hidden" id="cliked_column_num" value=""/>
			<!-- 工程路径 -->
			<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
			
			<!-- 选中对象 -->
			<div id="div_create_clicked_obj" style="display:none"></div>
			<!-- 隐藏域 end -->
			
			<table border="1" bordercolor="#000000" style="width:100%;height: 100%">
				<thead>
					<tr bgcolor="#99CCFF">
						<td colspan="2" height="25px">
							自定義表單配置
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%" valign="top">
							<table class="formlist" style="width: 100%">
								<!-- 表单操作 begin -->
								<thead onclick="clickTHead('tb_bdcz')" style="cursor:hand">
									<tr><td>表单操作	</td></tr>
								</thead>
								<tbody id="tb_bdcz">
									<tr>
										<td>
											<a href="#" onclick="showTableDiv();return false;">
												<font color="blue">
													增加表单
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											删除表单
										</td>
									</tr>
									<tr>
										<td>
											增加行
										</td>
									</tr>
									<tr>
										<td>
											删除行
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="saveCoalitionCol();return false;">
												<font color="blue">
													合并列
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="cancelCoalitionCol();return false;">
												<font color="blue">
													取消合并列
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="showRowDiv();return false;">
												<font color="blue">
													合并行
												</font>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="#" onclick="cancelCoalitionRow();return false;">
												<font color="blue">
													取消合并行
												</font>
											</a>
										</td>
									</tr>
								</tbody>
								<!-- 表单操作 end -->
								
								<!-- 数据表操作 begin -->
								<thead onclick="clickTHead('tb_zdcz_${formInfo.source_table}');createCzzdTable('${formInfo.source_table}');" style="cursor:hand">
									<tr><td>${formInfo.source_table}字段操作	</td></tr>
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
								<!-- 数据表操作 end -->
								
								<!-- 辅助表1操作 begin -->
								<logic:notEmpty name="formInfo" property="assistant_table_one">
									<thead onclick="clickTHead('tb_zdcz_${formInfo.assistant_table_one}');createCzzdTable('${formInfo.assistant_table_one}');" style="cursor:hand">
										<tr><td>${formInfo.assistant_table_one}字段操作	</td></tr>
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
								<!-- 辅助表1操作 end -->
								
								<!-- 辅助表2操作 begin -->
								<logic:notEmpty name="formInfo" property="assistant_table_two">
									<thead onclick="clickTHead('tb_zdcz_${formInfo.assistant_table_two}');createCzzdTable('${formInfo.assistant_table_two}');" style="cursor:hand">
										<tr><td>${formInfo.assistant_table_two}字段操作	</td></tr>
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
								<!-- 辅助表2操作 end -->
								
							</table>
						</td>
						<td  valign="top" rowspan="2">
							<div id="div_customForm">
								
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			
			<!-- 表DIV begin -->
			<div id="div_table" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>TABLE信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									标题
								</th>
								<td>
									<input type="text" name="str_title" id="title" style="width:150px"/>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>行数
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
										<button type="button" onclick="checkSaveTable()">保 存</button>
										<button type="button" onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 表DIV end -->
			
			<!-- 合并RowDIV begin -->
			<div id="div_RowSpan" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>行合并信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>合并行数
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
										<button type="button" onclick="saveCoalitionRow()">保 存</button>
										<button type="button" onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 合并RowDIV end -->
			
			<!-- 国家地区DIV begin -->
			<div id="div_area" style="display:none">	
			</div>
			<!-- 国家地区DIV end -->
			
			<!-- 修改配置DIV begin -->
			<div id="div_configure" style="display:none">	
			</div>
			<!-- 修改配置DIV end -->
			
			<!-- 上传学生照片 -->
			<div id="div_stuPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
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
			
			<!-- 其他信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>