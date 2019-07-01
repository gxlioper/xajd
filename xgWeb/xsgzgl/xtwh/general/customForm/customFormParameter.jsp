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
		<script language="javascript" defer="defer">

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
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>表单信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">
								<font color="red">*</font>表单ID
							</th>
							<td width="25%">
								<input type="text" style="width:150px" id="form_id" maxlength="20"/>
							</td>
							<th width="25%">
								<font color="red">*</font>表单名称
							</th>
							<td>
								<input type="text" style="width:150px" id="form_name" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>所属模块
							</th>
							<td>
								<select style="width:150px" id="ssmk">
									<logic:iterate name="ssmkList" id="ssmkRs">
										<option value="${ssmkRs.dm }">${ssmkRs.mc }</option>
									</logic:iterate>
								</select>
							</td>
							<th>
								当前时间
							</th>
							<td>
								${cjsj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>数据表</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>数据表
							</th>
							<td>
								<input type="text" style="width:150px" id="source_table" maxlength="50"/>
							</td>
							<th>
								<font color="red">*</font>主键
							</th>
							<td colspan="3">
								<input type="hidden" id="source_table_pk"/>
								<button type="button" class="btn_01" onclick="createChoosePkDiv();">选择</button>
								<span id="span_source_table_pk"></span>
							</td>
						</tr>
						<tr>
							<th>
								辅助表1
							</th>
							<td>
								<input type="text" style="width:150px" id="assistant_table_one" maxlength="50"/>
							</td>
							<th>
								关联字段
							</th>
							<td colspan="3">
								<input type="hidden" id="assistant_table_one_zd"/>
								<input type="hidden" id="assistant_table_one_relate"/>
								<button type="button" class="btn_01" onclick="createChooseRelateDiv('one');">选择</button>
								<span id="span_source_relate_one"></span>
							</td>
						</tr>
						<tr>
							<th>
								辅助表2
							</th>
							<td>
								<input type="text" style="width:150px" id="assistant_table_two" maxlength="50"/>
							</td>
							<th>
								关联字段
							</th>
							<td colspan="3">
								<input type="hidden" id="assistant_table_two_zd"/>
								<input type="hidden" id="assistant_table_two_relate"/>
								<button type="button" class="btn_01" onclick="createChooseRelateDiv('two');">选择</button>
								<span id="span_source_relate_two"></span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>相关视图</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>查询视图
							</th>
							<td>
								<input type="text" style="width:150px" id="search_view" maxlength="50"/>
							</td>
							<th>
								<font color="red">*</font>详细视图
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
									<button type="button" onclick="checkSaveCustomForm();">保 存</button>
									<button type="button" onclick="Close();return false;">关 闭</button>
								</div>
							</td>
						</tr>
				    </tfoot>
				</table>
			</div>
			
			<!-- 选择主键层 -->
			<div id="div_choose_pk" style="display:none"></div>
			<!-- 辅助表关联层 -->
			<div id="div_choose_relate" style="display:none"></div>
			
			<!-- 其他信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>