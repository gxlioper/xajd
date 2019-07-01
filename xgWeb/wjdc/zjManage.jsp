<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript" src="js/wjdc/wjdc.js"></script>
		<script language="javascript" src="js/wjdc/wjdcMk.js"></script>
	</head>
	<body onload="setWjstList();setTkList();getWjList();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wjdc">
			<!-- 隐藏域 -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->


			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveZjInfo()">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span> 基本信息 </span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								学年
							</th>
							<td width="35%">
								<html:select property="xn" style="" styleId="xn"
									onchange="getWjList()">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th width="15%">
								<font color="red">*</font>问卷
							</th>
							<td>
								<html:select property="id" style="" styleId="id"
									onchange="setTkList();setWjstList();">
									<html:options collection="wjList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年度
							</th>
							<td>
								<html:select property="nd" style="" styleId="nd"
									onchange="getWjList()">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
							<th>
								试题所属
							</th>
							<td>
								<html:select property="stss" style="width:120px" styleId="stss"
									onchange="setTkList();">
									<html:options collection="stssList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学期
							</th>
							<td>
								<html:select property="xq" style="" styleId="xq"
									onchange="getWjList()">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
								试题类型
							</th>
							<td>
								<html:select property="stlx" style="width:120px" styleId="stlx"
									onchange="setTkList();">
									<html:options collection="stlxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								试题建立时间
							</th>
							<td colspan="3">
								<html:text property="querygreaterequal_jlsj" styleId="kssj"
									onblur="dateFormatChg(this);setTkList();" styleClass="jssj"
									onclick="return showCalendar('querygreaterequal_jlsj','y-mm-dd');" />
								—
								<html:text property="querylessequal_jlsj" styleId="jssj"
									onblur="dateFormatChg(this);setTkList();" styleClass="jssj"
									onclick="return showCalendar('querylessequal_jlsj','y-mm-dd');" />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span> 组卷信息<font color="blue">提示：双击试题题目可以查看详细信息;按Shift或者Ctrl可以进行批量选择.</font>
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100% ">
									<tr>
										<td align="right" style="width:7%">
											题
											<br />
											<br />
											<br />
											库
										</td>
										<td colspan="2" style="width:42%">
											<html:select property="xydm" style="width:100% " onmouseover=""
												multiple="multiple" styleId="xyLeft" size="12"
												ondblclick="showStInfo(this.value)">
												<html:options collection="tkList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
										<td width="2%" algin="center">
											<button id="addBtn" class="btn_01"
												onclick="addAllRightFrame('xyLeft','xyRight')">
												&gt;&nbsp;&gt;
											</button>
											<br />
											<br />
											<br />
											<button  id="delBtn" class="btn_01"
												onclick="addAllLeftFrame('xyLeft','xyRight')">
												&lt;&nbsp;&lt;
											</button>
										</td>
										<td width="7%" align="right">
											试
											<br />
											卷
											<br />
											题
											<br />
											目
										</td>
										<td width="42%">
											<html:select property="xh" style="width:100%" styleId="xyRight"
												multiple="multiple" size="12" onmouseover=""
												ondblclick="showStInfo(this.value)">
											</html:select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tmpdiv1"></div>
			<!-- 提示信息 -->
			<%@ include file="other/tsxx.jsp"%>
			<!-- 提示信息 end-->
		</html:form>
	</body>
</html>
