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
		<script type='text/javascript' src="js/qgzx/jfgl/jfgl.js"></script>
		<style>.datelist td {word-break:break-all}</style>
	</head>
	<body>
		<html:form action="/qgzx_jfgl" method="post">
			<input type="hidden" name="qgzq" id="qgzq" value="${qgzq}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="hbsjs" id="hbsjs" value="" />
			<input type="hidden" name="hbjes" id="hbjes" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
			<logic:equal name="doType" value="update">
			<!-- 提示信息 -->
			<div class="prompt" id="promptTs">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					划拨时间不可修改，金额和备注可修改
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<!-- 提示信息 end-->	
			</logic:equal>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
							<logic:equal name="doType" value="update">
								<span>勤工经费修改</span>
							</logic:equal>
							<logic:equal name="doType" value="view">
								<span>勤工经费查看</span>
							</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
						<logic:notEqual name="jfhbfs" value="1">
							<th width="16%">
								年度
							</th>
							<td width="34%">
								<html:hidden name="rs" property="nd" styleId="nd"/>
								${rs.nd }
							</td>
						</logic:notEqual>
						<logic:equal name="jfhbfs" value="1">
							<th width="16%">
								年月
							</th>
							<td width="34%">
								<html:hidden name="rs" property="nd" styleId="nd"/>
								${rs.nd }
							</td>
						</logic:equal>
							<th width="16%">
								用人部门
							</th>
							<td width="34%">
								<html:hidden name="rs" property="bm" styleId="bm"/>
								<bean:write name="rs" property="bmmc"/>
							</td>
						</tr>
					</tbody>
					<logic:equal name="doType" value="view">
					<thead>
						<tr>
							<th colspan="4"><span>划拨经费项</span></th>
						</tr>
					</thead>
					</logic:equal>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<logic:equal name="doType" value="update">
						<tr>
							<td colspan="${col}">
								<button type="button" onclick="delXgTr();return false;">删除经费项</button>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<logic:equal value="xq" name="qgzq">
								<td width="5%">
								<logic:equal name="doType" value="update">
									<input type="checkbox" name="th" onclick="selectAll(this);" />
								</logic:equal>
								<logic:equal name="doType" value="view">
									行号
								</logic:equal>
								</td>
								<td width="10%">
									学年
								</td>
								<td width="10%">
									学期
								</td>
								<td width="15%">
									划拨时间
								</td>
								<td width="15%">
									<logic:equal name="doType" value="update"><font class="red">*</font></logic:equal>划拨金额
								</td>
								<td width="45%">
									<logic:equal name="doType" value="update"></logic:equal>备注
								</td>	
							</logic:equal>
							<logic:equal value="xn" name="qgzq">
								<td width="5%">
								<logic:equal name="doType" value="update">
									<input type="checkbox" name="th" onclick="selectAll(this);" />
								</logic:equal>
								<logic:equal name="doType" value="view">
									行号
								</logic:equal>
								</td>
								<td width="10%">
									学年
								</td>
								<td width="15%">
									划拨时间
								</td>
								<td width="15%">
									<logic:equal name="doType" value="update"><font class="red">*</font></logic:equal>划拨金额
								</td>
								<td width="55%">
									<logic:equal name="doType" value="update"></logic:equal>备注
								</td>	
							</logic:equal>
							
						</tr>
					</thead>
					<tbody id="tbody_jfxx">
						<logic:equal name="doType" value="update" >
							<logic:iterate name="list" id="i">
								<logic:equal value="xn" name="qgzq">
								   <tr>
									<td width="5%"><input type="checkbox"></td>
									<td width="10%"><input type="hidden" name="xn" value="${i.xn}">${i.xn}</td>
									<td width="15%"><input type="hidden" id="hbsj" name="hbsj" value="${i.hbsj}">${i.hbsj}</td>
									<td width="15%"><input type="text" id="hbje" name="hbje" size="10" maxlength="10" onblur="checkInputNum(this)" value="${i.hbje}"></td>
									<td width="55%"><input type="text" id="bz" name="bz" style="width:90%" maxlength="1000" value="${i.bz}"></td>
								   </tr>
								</logic:equal>
								<logic:equal value="xq" name="qgzq">
								   <tr>
									<td width="5%"><input type="checkbox"></td>
									<td width="10%"><input type="hidden" name="xn" value="${i.xn}">${i.xn}</td>
									<td width="10%"><input type="hidden" name="xq" value="${i.xq}">${i.xqmc}</td>
									<td width="15%"><input type="hidden" id="hbsj" name="hbsj" value="${i.hbsj}">${i.hbsj}</td>
									<td width="15%"><input type="text" id="hbje" name="hbje" size="10" maxlength="10" onblur="checkInputNum(this)" value="${i.hbje}"></td>
									<td width="45%"><input type="text" id="bz" name="bz" style="width:90%" maxlength="1000" value="${i.bz}"></td>
								   </tr>
								</logic:equal>
							</logic:iterate>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<logic:iterate name="list" id="i" indexId="t">
								<logic:equal value="xn" name="qgzq">
									<tr>
										<td width="5%">${t+1}</td>
										<td width="10%">${i.xn}</td>
										<td width="15%">${i.hbsj}</td>
										<td width="15%">${i.hbje}</td>
										<td width="55%">${i.bz}</td>
									</tr>
							    </logic:equal>
								<logic:equal value="xq" name="qgzq">
									<tr>
										<td width="5%">${t+1}</td>
										<td width="10%">${i.xn}</td>
										<td width="10%">${i.xqmc}</td>
										<td width="15%">${i.hbsj}</td>
										<td width="15%">${i.hbje}</td>
										<td width="45%">${i.bz}</td>
									</tr>
								</logic:equal>
							</logic:iterate>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<table border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<logic:equal name="doType" value="update">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							</logic:equal>
							<div class="btn">
								<logic:equal name="doType" value="update">
								<button type="button" onclick="bcXgjfxx();return false;">
									保 存
								</button>
								</logic:equal>
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

