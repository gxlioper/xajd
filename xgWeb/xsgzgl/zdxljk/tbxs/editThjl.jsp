<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zdxljk/tbxs/thjl.js"></script>
		<script type="text/javascript">

		</script>
	</head>
	<body>
		<html:form action="/zdxljkTbxs" method="post" styleId="tbxsForm">
			<input type="hidden" name="xh" value="${jbxx.xh }" id="xh"/>
		
			<div style='overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>谈话记录
									<a onclick="addRow();" href="javascript:void(0);">
										<img src="images/knsrd/jiahao.gif" />
									</a>
									<font color="red">只允许对24小时以内的信息进行修改操作</font>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="dataTbody">
					<tr><td colspan="4"><table class="dateline" width="100%" ><tbody>
						<tr class="gzlxTr">
											<th width="16%"><font color="red">*</font>关注类型</th>
											<td colspan="3" style="text-align:left;">
												<logic:present name="gzlxList">
													<logic:iterate id="o" name="gzlxList">
														<label>
															<html:radio property="gzlx" onclick="gzlxChange(this)" value="${o.dm}">${o.mc}</html:radio>
														</label>
													</logic:iterate>
												</logic:present>
											</td>
										</tr>
										<logic:empty name="model" property="qxyy">
									
										<tr style="display:none">
											<th width="16%"><font color="red">*</font>取消关注原因</th>
											<td colspan="3" style="text-align:left;">
												<textarea name="qxyy"  id="qxyy" onblur="checkLen(this,500);"
											   	 style="width:99%;" rows="4"></textarea>
											</td>
										</tr>
										
										</logic:empty>
										<logic:notEmpty name="model" property="qxyy">
											<tr>
											<th width="16%"><font color="red">*</font>取消关注原因</th>
											<td colspan="3" style="text-align:left;">
												<textarea name="qxyy"  id="qxyy" onblur="checkLen(this,500);"
											   	 style="width:99%;" rows="4">${model.qxyy}</textarea>
											</td>
											</tr>
										</logic:notEmpty>
										</tbody></table></td></tr>
						<tr style="display:none;">
							<td colspan="4" align="right">
								<img src="images/knsrd/jianhao.gif" onclick="jQuery(this).parents('tr').remove();"/>
								<table class="dateline" width="100%" >
									<input type="hidden" value="Y" name="sfkxg"/>
									<tbody>
										
										<input type="hidden" value="" name="gxlxArr"/>
										<tr>
											<input type="hidden" name="canUpdateArr" value="Y"/>
											<th width="16%"><font color="red">*</font>访谈人</th>
											<td style="text-align:left;">
												<input type="text" name="ftrArr" id="ftr" value="${ftr}"/>
											</td>
											<th width="16%"><font color="red">*</font>访谈时间</th>
											<td style="text-align:left;">
												<input type="text" name="thsjArr" id="thsj0"
												onfocus="showCalendar(this.id,'y-mm-dd',true);" 
												readonly="true"/>
											</td>
										</tr>
										<tr>
											<th width="16%"><font color="red">*</font>学生类型</th>
											<td colspan="3" style="text-align:left;">
											<input type="checkbox" name="gxlx" value="经济困难"/>经济困难
											<input type="checkbox" name="gxlx" value="学习困难"/>学习困难
											<input type="checkbox" name="gxlx" value="心理困难"/>心理困难
											<input type="checkbox" name="gxlx" value="其它"/>其它
											</td>
										</tr>
										
										<tr>
											<th width="16%"><font color="red">*</font>谈话内容</th>
											<td colspan="3">
												<textarea name="thnrArr"  onblur="checkLen(this,400);"
											   	 style="width:99%;" rows="4"></textarea>
											</td>
										</tr>
										<tr>
											<th width="16%"><font color="red">*</font>帮扶措施</th>
											<td colspan="3">
												<textarea name="cljgArr"  onblur="checkLen(this,500);"
											   	 style="width:99%;" rows="4"></textarea>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						
						<logic:empty name="thjlList">
						<tr>
							<td colspan="4">
								<table class="dateline" width="100%" >
									<tbody>
										<input type="hidden" name="gxlxArr"/>
										<tr>
											<input type="hidden" name="canUpdateArr" value="Y"/>
											<th width="16%"><font color="red">*</font>访谈人</th>
											<td style="text-align:left;">
												<input type="text" name="ftrArr" id="ftr" value="${ftr}"/>
											</td>
											<th width="16%"><font color="red">*</font>谈话时间</th>
											<td style="text-align:left;">
												<input type="text" name="thsjArr" id="thsj1"
												onfocus="showCalendar(this.id,'y-mm-dd',true);" 
												readonly="true"/>
											</td>
										</tr>
										<tr>
											<th width="16%"><font color="red">*</font>学生类型</th>
											<td colspan="3" style="text-align:left;">
											<input type="checkbox" name="gxlx" value="经济困难"/>经济困难
											<input type="checkbox" name="gxlx" value="学习困难"/>学习困难
											<input type="checkbox" name="gxlx" value="心理困难"/>心理困难
											<input type="checkbox" name="gxlx" value="其它"/>其它
											</td>
										</tr>
										
										<tr>
											<th width="16%"><font color="red">*</font>谈话内容</th>
											<td colspan="3">
												<textarea name="thnrArr"  onblur="checkLen(this,500);"
											   	 style="width:99%;" rows="4"></textarea>
											</td>
										</tr>
										<tr>
											<th width="16%"><font color="red">*</font>帮扶措施</th>
											<td colspan="3">
												<textarea name="cljgArr"  onblur="checkLen(this,500);"
											   	 style="width:99%;" rows="4"></textarea>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						</logic:empty>
						<logic:notEmpty name="thjlList">
							<logic:iterate id="t" name="thjlList" indexId="index01">
							<logic:equal value="Y" property="sfkxg" name="t">
							<tr>
								<td colspan="4" align="right">
								<img src="images/knsrd/jianhao.gif" onclick="jQuery(this).parents('tr').remove();"/>
									<table class="dateline" width="100%" >
										<input type="hidden" value="${t.sfkxg}" name="sfkxg"/>
										<input type="hidden" value="${t.gxlx}" name="gxlxArr"/>
										<tbody>
											<tr>
												<th width="16%"><font color="red">*</font>操作时间</th>
												<td colspan="3" style="text-align:left;">
													${t.czsj}
												</td>
											</tr>
											<tr>
												<input type="hidden" name="canUpdateArr" value="Y"/>
												<th width="16%"><font color="red">*</font>访谈人</th>
												<td style="text-align:left;">
													<input type="text" name="ftrArr" id="ftr" value="${t.ftr}"/>
												</td>
												<th width="16%"><font color="red">*</font>谈话时间</th>
												<td colspan="3">
													<input type="text" name="thsjArr" id="thsj${index01+1}" value="${t.thsj }"
													onfocus="showCalendar(this.id,'y-mm-dd',true);" 
													readonly="true"/>
												</td>
											</tr>
											<tr>
												<th width="16%"><font color="red">*</font>学生类型</th>
												<td colspan="3">
													<input type="checkbox" name="gxlx" value="经济困难"/>经济困难
													<input type="checkbox" name="gxlx" value="学习困难"/>学习困难
													<input type="checkbox" name="gxlx" value="心理困难"/>心理困难
													<input type="checkbox" name="gxlx" value="其它"/>其它
													</td>
											</tr>
											
											<tr>
												<th width="16%"><font color="red">*</font>谈话内容</th>
												<td colspan="3">
													<textarea name="thnrArr"  onblur="checkLen(this,500);"
												   	 style="width:99%;" rows="4">${t.thnr}</textarea>
												</td>
											</tr>
											<tr>
												<th width="16%"><font color="red">*</font>帮扶措施</th>
												<td colspan="3">
													<textarea name="cljgArr"  onblur="checkLen(this,500);"
												   	 style="width:99%;" rows="4" >${t.cljg}</textarea>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							</logic:equal>
							<logic:equal value="N" property="sfkxg" name="t">
								<tr>
								<td colspan="4">
									<table class="dateline" width="100%" >
									<input type="hidden" value="${t.sfkxg}" name="sfkxg"/>
										<tbody>
											<tr>
												<th width="16%">操作时间</th>
												<td colspan="3" style="text-align:left;">
													${t.czsj}
												</td>
											</tr>
											<tr>
												<input type="hidden" name="canUpdateArr" value="N"/>
												<th width="16%">访谈人</th>
												<input type="hidden" value="${t.ftr}" name="ftrArr"/>
												<td style="text-align:left;">
													${t.ftr}
												</td>
												<th width="16%">谈话时间</th>
												<input type="hidden" value="${t.thsj}" name="thsjArr"/>
												<td>
													${t.thsj}
												</td>
											</tr>
											<tr>
												<th width="16%">学生类型</th>
												<input type="hidden" value="${t.gxlx}" name="gxlxArr"/>
												<td colspan="3">
													${t.gxlx}
												</td>
											</tr>
											<tr>
												<th width="16%">谈话内容</th>
												<input type="hidden" value="${t.thnr}" name="thnrArr"/>
												<td colspan="3">
													${t.thnr}
												</td>
											</tr>
											<tr>
												<th width="16%">帮扶措施</th>
												<input type="hidden" value="${t.cljg}" name="cljgArr"/>
												<td colspan="3">
													${t.cljg}
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							</logic:equal>
							</logic:iterate>
						</logic:notEmpty>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

