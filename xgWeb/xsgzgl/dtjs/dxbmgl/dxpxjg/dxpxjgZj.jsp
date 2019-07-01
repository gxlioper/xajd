<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" href="xsgzgl/dtjs/dtxxglnew/color/dtxxglnew.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoChange();
			});
			function tzXzpx(){
				var xh = jQuery('#xh').val();
				if(xh==undefined||xh==''){
					showAlert("请选择学生!");
				}else{
					showDialog('请选择培训信息',800,500,'dtjs_dxbmgl_dxpxxzCx.do?xh='+xh);
				}
				return false;
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="dxbmgl_dxpxjg.do">
		 <!-- 提示信息 end-->
			<div class="prompt" id="div_help">
				<h3>
					<span>提示：</span>
				</h3>
				<p></p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
		 <!-- 提示信息 end-->			
			<div style="tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsxx/comm/selectStudent/selectStudentAll.jsp"%>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">培训期次</th>
							<td width="34%"><html:text property="pxqc" styleId="pxqc" readonly="true" value="${dxpxjgForm.pxqc }"/>
								<button class="btn_01" type="button" onclick="tzXzpx();">选择</button>
							</td>
							<th width="16%">培训时间</th>
							<td id="pxsj" width="34%"><input type="hidden" id="pxid" name="pxid" value="${dxpxjgForm.pxid}"/>
							${dxpxjgForm.pxsj }</td>
						</tr>
						<tr>
							<th>考勤成绩</th>
							<td><input type="hidden" id="kqcjbfb" value="${dxpxjgForm.kqcjbfb }"/>
								<html:text maxlength="3" property="kqcj" styleId="kqcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>实践成绩</th>
							<td><input type="hidden" id="sjcjbfb" value="${dxpxjgForm.sjcjbfb}"/>
								<html:text maxlength="3" property="sjcj" styleId="sjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
						<tr>
							<th>笔记成绩</th>
							<td><input type="hidden" id="bjcjbfb" value="${dxpxjgForm.bjcjbfb }"/>
								<html:text maxlength="3" property="bjcj" styleId="bjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
					<logic:equal value="xx" name="userStatus" scope="session">
						<tr>
							<th>考试成绩</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>总评成绩</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="admin" name="userStatus" scope="session">
						<tr>
							<th>考试成绩</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>总评成绩</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th>评价结果</th>
							<td>
								<html:select property="pjjg">
									<html:option value=""></html:option>
									<html:option value="优">优</html:option>
									<html:option value="良">良</html:option>
									<html:option value="及格">及格</html:option>
									<html:option value="不及格">不及格</html:option>
								</html:select>
							</td>
						</tr>
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
									<button type="button" onclick="save('xh');return false;" id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
