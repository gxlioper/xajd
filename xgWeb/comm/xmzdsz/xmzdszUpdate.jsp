<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xszz/xszzComm.js"></script>
		<script type="text/javascript">
			function plbczd(){
			
				refreshForm('/xgxt/xmzdsz.do?method=xmzdszUpdate&doType=save');
			}
			
			function setSjy(obj){
				
				var sjy = $('sjy').value;
				
				if (obj.checked){
					$('sjy').value+=","+obj.value;
				} else {
					$('sjy').value = $('sjy').value.replace(obj.value,"");
				}
				
				$('search_go').click();
				
			}
		</script>
	</head>

	<body>
		<!-- 标题 -->
		<!-- 标题 end-->
		<html:form action="/xmzdsz">
			<!-- 隐藏域 -->
			<input type="hidden" name="mkmc" value="${mkmc }"/>
			<input type="hidden" name="xmdm" value="${xmdm }"/>
			<input type="hidden" name="sjy" value="${sjyArr }" id="sjy"/>
			<html:hidden property="queryequals_xmdm" styleId="xmdm"/>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				 <div class="searchtab">
				<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xmzdsz.do?method=xmzdszUpdate')">
											查 询
										</button>
										 <button type="button" class="btn_cz" id="btn_cz" onclick="plbczd();">
											 保 存
										 </button>
										 <button type="button" class="btn_cz"  onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();">
											 关 闭
										 </button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td colspan="4">
									 <logic:match value="view_xsjbxx" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="view_xsjbxx" checked="checked" onclick="setSjy(this)"/>学生基本信息
									 </logic:match>
									 <logic:notMatch value="view_xsjbxx" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="view_xsjbxx" onclick="setSjy(this)"/>学生基本信息
									 </logic:notMatch>
									 <logic:match value="jtqkdcb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="jtqkdcb" checked="checked" onclick="setSjy(this)"/>学生家庭情况
									 </logic:match>
									 <logic:notMatch value="jtqkdcb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="jtqkdcb" onclick="setSjy(this)"/>学生家庭情况
									 </logic:notMatch>
									 
									 <logic:match value="xszz_knsb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xszz_knsb" checked="checked" onclick="setSjy(this)"/>困难生信息
									 </logic:match>
									 <logic:notMatch value="xszz_knsb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xszz_knsb" onclick="setSjy(this)"/>困难生信息
									 </logic:notMatch>
									 
									  <logic:match value="cjb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="cjb" checked="checked" onclick="setSjy(this)"/>学生成绩
									  </logic:match>
									  <logic:notMatch value="cjb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="cjb" onclick="setSjy(this)"/>学生成绩
									 </logic:notMatch>
									 
									 
									 
									 <logic:match value="xsqtxxb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xsqtxxb" checked="checked" onclick="setSjy(this)"/>学生其它信息
									 </logic:match>
									 <logic:notMatch value="xsqtxxb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xsqtxxb" onclick="setSjy(this)"/>学生其它信息
									 </logic:notMatch>
									 
									 <logic:match value="${xmmc }" name="sjyArr">
										<input type="checkbox" name="sjly" value="${xmmc }" checked="checked" onclick="setSjy(this)"/>本项目相关字段
									 </logic:match>
									  <logic:notMatch value="${xmmc }" name="sjyArr">
										<input type="checkbox" name="sjly" value="${xmmc }" onclick="setSjy(this)"/>本项目相关字段
									 </logic:notMatch>
									 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->								
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
						</span>
					</h3>
					
					<table summary="" class="dateline"  width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" style="height:17.5px" />
								</td>
								<td onclick="tableSort(this)">
									数据来源	
								</td>
								<td onclick="tableSort(this)">
									字段名称
								</td>
								<td onclick="tableSort(this)">
									启用名称	
								</td>
								<td onclick="tableSort(this)">
									是否必填	
								</td>
								<td onclick="tableSort(this)">
									字段类型	
								</td>
								<td onclick="tableSort(this)">
									录入限制	
								</td>
								<td onclick="tableSort(this)">
									显示顺序	
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" value="${s.pkValue }" name="primarykey_cbv" ${s.checked } />
											<input type="hidden" value="${s.pkValue }" name="flg"/>
										</td>
										<td>
											${s.lybmc }
											<input type="hidden" value="${s.lyb }" name="lyb"/>
										</td>
										<td>
											${s.zdmc }
											<input type="hidden" value="${s.zd }" name="zd"/>
										</td>
										<td>
											<input type="text" value="${s.zdm }" name="zdm" style="width:100px" maxlength="25"/>
										</td>
										<td>
											<html:select property="bxlr" name="s">
												<html:option value="是">是</html:option>
												<html:option value="否">否</html:option>
											</html:select>
										</td>
										<td>
											<html:select property="zdlx" name="s">
												<logic:equal value="VIEW_XSJBXX" property="lyb" name="s">
													<html:option value="short">短文本</html:option>
													<html:option value="long">长文本</html:option>
												</logic:equal>
												
												<logic:equal value="view_xsjbxx" property="lyb" name="s">
													<html:option value="short">短文本</html:option>
													<html:option value="long">长文本</html:option>
												</logic:equal>
												
												<logic:notEqual value="view_xsjbxx" property="lyb" name="s">
												<logic:notEqual value="VIEW_XSJBXX" property="lyb" name="s">
													<html:option value="text">短文本框</html:option>
													<html:option value="long">长文本框</html:option>
													<html:option value="select">下拉框</html:option>
													<html:option value="textarea">文本域</html:option>
												</logic:notEqual>
												</logic:notEqual>
											</html:select>
										</td>
										
										<td>
											<html:select property="lrxz" name="s">
												<html:option value="无限制">无限制</html:option>
												<html:option value="日期">日期</html:option>
												<html:option value="金额">金额</html:option>
												<html:option value="数字">数字</html:option>
											</html:select>
										</td>
										
										<td>
											<input type="text" name="zdsx" 
												   onkeyup="value=value.replace(/[^\d]/g,'')"
												   maxlength="5"
												   value="${s.zdsx }" style="width:50px"/>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					<!--分页显示-->
					 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xmzdszForm"></jsp:include>
					<!--分页显示-->
				
				</div>
				<!-- 查询结果 end-->
		</html:form>
		
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>