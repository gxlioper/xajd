<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
						
		</script>
	</head>
	<body>
		<html:form action="/zhf_sh" method="post" styleId="form">
		<input type="hidden" name="xh" value="${xh}"/>
		<input type="hidden" name="shzt" value="${shzt}"/> 	 		
			<div style='overflow-x:hidden;overflow-y:auto;height:550px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tr style="display:none;">
											<td id="xmmkdm">
												<html:select property="cxzd" styleId="cxzd" onchange="getJfxmList(this);" style="width:200px">
													<html:option value="">---请选择---</html:option>
													<html:options collection="xmmkList" property="xmmkdm" labelProperty="xmmkmc"/>
												</html:select>
											</td>
					</tr>
					<logic:notEmpty name="ysdList">
						<thead>										
							<tr>
								<th colspan="4">
									<span>已审核计分项目信息</span>
								</th>
							</tr>					
						</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
										<!--<td style="text-align: center;width:3%;"><input type="checkbox" name="selectall" onclick="DoCheck()"/></td>-->
											<td style="text-align: center;width:10%;">所属模块 </td>
											<td style="text-align: center;width:20%;"">计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%">事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%">参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
											<!--<td style="text-align: center;width:10%">操作</td>-->
										</tr>
									</thead>
									<tbody>
									<logic:iterate id="y" name="ysdList">
										<tr>
										<!--<td><input type="checkbox" name="check" value="${y.id}"/></td>-->
										<input type="hidden" value="${y.id}"/>
											<td>
												${y.xmmkmc}
											</td>
											<td>
												${y.jfxmmc}
											</td>
											<td>
												${y.fs}
											</td>
											<td>
												${y.sxsm}
											</td>
											<td>
												${y.lb}
											</td>
											<td>
												${y.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${y.id}');return false;" class="name">${y.fjmc}</a> 
											</td>
											<!--<td>
												<a href='#'onclick='del(this);' class='name' style='width:100px'>删除</a>
											</td>
										--></tr>
									</logic:iterate>							
									</tbody>
								</table>
							</td>
						</tr>				
					</tbody>
					</logic:notEmpty>
					<logic:notEmpty name="thList">
					<thead>
						<tr>
							<th colspan="4">
								<span>退回计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<!--<td style="text-align: center;width:3%;"><input type="checkbox" name="selectall" onclick="DoCheck()"/></td>-->
											<td style="text-align: center;width:10%;">所属模块 </td>
											<td style="text-align: center;width:20%;"">计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%">事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%">参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
											<td style="text-align: center;width:10%">操作</td>
										</tr>
									</thead>
										<tbody id="ycz">			
										<logic:iterate id="t" name="thList">
										<tr>
										<!--<td><input type="checkbox" name="check" value="${y.id}"/></td>-->
											<input type="hidden" value="${t.id}"/>
											<td>
												${t.xmmkmc}
											</td>
											<td>
												${t.jfxmmc}
											</td>
											<td>
												${t.fs}
											</td>
											<td>
												${t.sxsm}
											</td>
											<td>
												${t.lb}
											</td>
											<td>
												${t.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${w.id }');return false;" class="name">${w.fjmc}</a> 
											</td>
												<td>
												<a href='#'onclick='del(this);' class='name' style='width:100px'>删除</a>
											</td>											
										</tr>
									</logic:iterate>
									</tbody>	
								</table>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
					<thead>
						<tr>
							<th colspan="4">
								<span>未审核计分项目信息</span><!--<a onclick="addRow();" href="javascript:void(0);"><img src="images/knsrd/jiahao.gif" /></a>
							--></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;width:3%;"><input type="checkbox" name="selectall" onclick="DoCheck()"/></td>
											<td style="text-align: center;width:10%;"><font color="red">*</font>所属模块 </td>
											<td style="text-align: center;width:20%;""><font color="red">*</font>计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%"><font color="red">*</font>事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%"><font color="red">*</font>参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
											<td style="text-align: center;width:10%">操作</td>
										</tr>
									</thead>
										<tbody id="ycz">			
										<logic:iterate id="w" name="wsdList">
										<tr>
										<td><input type="checkbox" name="check" value="${w.id}"/></td>
											<td>
												${w.xmmkmc}
											</td>
											<td>
												${w.jfxmmc}
											</td>
											<td>
												${w.fs}
											</td>
											<td>
												${w.sxsm}
											</td>
											<td>
												${w.lb}
											</td>
											<td>
												${w.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${w.id }');return false;" class="name">${w.fjmc}</a> 
											</td>
											<td>
												<a href='#'onclick='del(this);' class='name' style='width:100px'>删除</a>
											</td>
										</tr>
									</logic:iterate>
									</tbody>	
									<tbody id="dataList">
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px">
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz"><span class="red">*</span>为必填项</div>
								<div class="btn">
								<logic:notEqual value="0" name="shzt">
									<button type="button" type="button" onclick="back();">
										退回
									</button>
									<button type="button" type="button" onclick="saveFormForSd();">
										审定
									</button>
								</logic:notEqual>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>退回意见</span>
								</th>
							</tr>
						</thead>
						<tbody>
								<th colspan="1">
									退回意见
									<br><font color="red">限制字数（100）</font><br/>
								</th>
								<td>
									<html:textarea property="thyj" onblur="chLengs(this,100);" styleId="thyj" style="width:234px;height:73px" ></html:textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="保存"  onclick="backform();">
											保存 
										</button>
										<button type="button" name="取消"  onclick="iFClose();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>

