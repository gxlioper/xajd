<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/jfhbzjdx.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_jfgl" method="post">
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="bms" id="bms" value="" />
			<input type="hidden" name="hbsjs" id="hbsjs" value="" />
			<input type="hidden" name="hbjes" id="hbjes" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
			<div style="">
				<!-- 提示信息 -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						同一个用人单位不能存在两条相同划拨时间的经费项目
					</p>
					<a class="close" title="隐藏"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- 提示信息 end-->	
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>勤工经费增加</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								<font class="red">*</font>年度
							</th>
							<td>
								<html:select name="rs" property="nd" styleId="nd" style="width:150px" >
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td colspan="5">
								<button type="button" onclick="addTr();return false;" class="btn_common">增加经费项</button>
								<button type="button" onclick="delTr();return false;" class="btn_common">删除经费项</button>
							</td>
						</tr>
					</thead>
				</table>
				</div>
				<div style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td width="3%">
									<input type="checkbox" name="th" onclick="selectAll(this);" />
								</td>
								<td>
									<font class="red">*</font>用人单位
								</td>
								<td>
									<font class="red">*</font>划拨时间
								</td>
								<td>
									<font class="red">*</font>划拨金额<元>
								</td>
								<td>
									备注
								</td>
							</tr>
							<tbody id="tbody_jfxx">
							</tbody>
						</thead>
					</table>
				</div>
				<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveJfhb();return false;">
										保 存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<div style="display: none;">
				<table >
					<tbody id="hidden_jfxx">
						<tr>
							<td><input type='checkbox'/></td>
							<td>
							   	<html:select name="rs" property="bm" styleId="bm">
									<html:option value="">--请选择--</html:option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							<td ><input type='text' id='hbsj!!@@!!id' name='hbsj' style='width:90px' onclick='return showCalendar("hbsj!!@@!!onclick","y-mm-dd");' onblur='dateFormatChg(this)' value="${nowTime}"  readonly='true'/></td>
							<td ><input type='text' id='hbje' name='hbje' size='10' maxlength='10' onblur='checkInputNum(this)'/></td>
							<td><input type='text' id='bz' name='bz' style='width:100px' maxlength='1000'/></td>
						</tr>
					</tbody>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>