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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/cjgl/cjxxFf.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/qgzx_cjgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="sxzd" id="sxzd" value="${rs.sxzd }" />
			<input type="hidden" name="sxsz" id="sxsz" value="${rs.sxsz}" />
			<input type="hidden" name="cjbz" id="cjbz" value="${rs.cjbz}" />
			<input type="hidden" name="yf" id="yf" value="${rs.ffny }" />
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="gwdms" id="gwdms" value="" />
			<input type="hidden" name="gwxzs" id="gwxzs" value="" />

			<input type="hidden" name="gwxns" id="gwxns" value="" />
			<input type="hidden" name="gwxqs" id="gwxqs" value="" />

			<input type="hidden" name="gss" id="gss" value="" />
			<input type="hidden" name="jes" id="jes" value="" />
			<input type="hidden" name="jfhbs" id="jfhbs" value="" />
			<input type="hidden" name="zcs" id="zcs" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
			<input type="hidden" name="jcdlgss" id="jcdlgss" value="" />
			<input type="hidden" name="zhdlgss" id="zhdlgss" value="" />
			<input type="hidden" name="khdjs" id="khdjs" value="" />
			<input type="hidden" name="cjsx" id="cjsx" value="" />
			<input type="hidden" id="yffxh" value=""/>
			<input type="hidden" id="yffgw" value="" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="cjffr" id="cjffr" value="${rs.cjffr }" />
			<logic:equal value="10351" name="xxdm">
				<input type="hidden" name="pkscjbz"  id="pkscjbz" value="${cs.pkscjbz}" />
				<input type="hidden" name="pkscjzgsx" id="pkscjzgsx" value="${cs.pkscjzgsx}" />
                <input type="hidden" name="yxrs" id="yxrs" value="" />
                <input type="hidden" name="yxzrs" id="yxzrs" value="" />
			</logic:equal>
			<logic:equal value="10704" name="xxdm">
				<input type="hidden" name="gdgcjbz"  id="gdgcjbz" value="${cs.gdgcjbz}" />
			</logic:equal>
			<!-- 提示信息 end-->
			<div style="overflow-x:hidden;overflow-y:auto;">
				<div class="prompt" id="div_help">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						<span>
						1.输入的金额为空时，不保存和提交该记录。<br/>
						2.到达发放最后日期将自动提交。<br/>
						<logic:equal name="xxdm" value="10351">
							3.贫困生岗位上限金额${cs.pkscjzgsx}元。<br/>
							4.当前优秀人数：<span id="yxrsSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  可评为优秀的总人数：<span id="yxzrsSpan"></span>。<br/>
						</logic:equal>

						</span>
					</p>
					<%--
					<a class="close" title="隐藏"
					   onclick="this.parentNode.style.display='none'"></a>
					--%>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								<logic:notEqual value="10052" name="xxdm"><font class="red">*</font></logic:notEqual>
									学年
								<logic:equal value="xq" name="cs" property="qgzq">
									学期
								</logic:equal>
							</th>
							<td width="34%">

								<html:select name="rs" property="xn" styleId="xn" onchange="getGwdm();getFFny();onShow()" disabled="${rs.dis}" style="width:100px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>

								<logic:equal value="xq" name="cs" property="qgzq" >
									<html:select property="xq" styleId="xq"  onchange="getGwdm();onShow()" disabled="${rs.dis}">
										<html:options collection="xqList" labelProperty="xqmc" property="xqdm" />
									</html:select>
								</logic:equal>
								<logic:equal value="xn" name="cs" property="qgzq" >
									<input type="hidden" id="xq" name="xq" value="" />
								</logic:equal>
							</td>
							<th width="16%">
								<font class="red">*</font>用人部门
							</th>
							<td width="34%">
								<logic:empty name="rs" property="disQg">
									<html:select name="rs" property="yrbm" styleId="yrbm" onchange="getGwdm();getFFny();onShow()" disabled="${rs.dis}" style="width:200px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs" property="disQg">
									<html:select name="rs" property="yrbm" styleId="yrbm" onchange="getGwdm();getFFny();onShow()" disabled="true" style="width:200px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								在岗状态
							</th>
							<td>
								<html:select property="zgzt" styleId="zgzt" onchange="onShow()" style="width:200px">
									<option value=''>全部</option>
									<option value='zg' selected="selected">在岗</option>
								</html:select>
							</td>
							<th>
								<font class="red">*</font>发放年月
							</th>
							<td>
								<html:select name="rs" property="ffny" styleId="ffny" onchange="onShow()" disabled="${rs.dis}" style="width:200px">
									<html:option value="${rs.ffny}">${rs.ffny}</html:option>
									<html:options collection="ffnyList" property="ffny" labelProperty="ffny" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								岗位名称
							</th>
							<td>
								<html:select name="rs" property="gwdm" styleId="gwdm" onchange="onShow()" style="width:200px">
									<option value="">----全部岗位----</option>
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<%--浙江传媒 --%>
							<logic:equal name="xxdm" value="11647">
							<th>
							</th>
							<td>
							</td>
							</logic:equal>
							<%--浙江传媒 end--%>
							<%--非浙江传媒 --%>
							<logic:notEqual name="xxdm" value="11647">
								<logic:notEqual name="xxdm" value="12309">
							<th>
								酬金标准
							</th>
							<td>
								<font class="red">${rs.cjbz}</font>元/时
							</td>
								</logic:notEqual>
							</logic:notEqual>
							<%--非浙江传媒end --%>
						</tr>
						<tr>
							<th>
								是否已录
							</th>
							<td>
								<html:select property="sfyl" styleId="sfyl" onchange="onShow()" style="width:200px">
									<option value=''></option>
									<option value='1'>已录入</option>
									<option value='0'>未录入</option>
								</html:select>
							</td>
							<logic:equal name="xxdm" value="10351">
								<th>学生姓名</th>
								<td><input type="text" id="xsxm" name="xsxm" onblur="onShow()"/></td>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10351">
								<th></th>
								<td></td>
							</logic:notEqual>

						</tr>
					</tbody>
				</table>
				<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>

				<!-- 内容显示区开始 -->
				<div class="main_box">
					<div class="mid_box">
						<div class="title">
							<p>
								<!-- 查询得到的数据量显示区域 -->
							</p>
						</div>
					</div>
					<h3 class="datetitle_01">
<!--						<span style="float: left;"> 查询结果&nbsp;&nbsp;[只能发放&nbsp;&nbsp;<font color="red" id="tsny"></font>  本月以及之前的]</span>-->
					</h3>
					<div id="div_rs"
						style="width:100%;overflow-x:auto;overflow-y:hidden;">
					</div>
				</div>
				<div id="div_detail" style="display:none">
				</div>		
			</div>
			
			<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="saveCjxxff();return false;">
									保 存
								</button>
								<button type="button" onclick="iFClose();">
									关 闭
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

