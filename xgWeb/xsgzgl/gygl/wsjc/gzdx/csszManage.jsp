<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/wsjc.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body onload="onShow_cssz()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commWsjc">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<!-- 参数设置基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>周期设置</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr style="">
							<th align="right" width="15%">
								<font color="red">*</font>周期设置
							</th>
							<td align="left" width="35%">
								<html:hidden name="rs" property="jczq" styleId="zq"/>
								<html:radio name="rs" property="jczq" value="周" onclick="$('zq').value=this.value;showZcTr()"/>周
								&nbsp;&nbsp;
								<html:radio name="rs" property="jczq" value="日" onclick="$('zq').value=this.value;showZcTr()"/>日
							</td>
							<td align="left" width="50%" colspan="2">
								<font color="blue">提示：如周期设置为周，卫生分录入次数，以总周次为准，周期为日的情况，不做限制。</font>
							</td>
						</tr>
						<tr id="zgzcTr" style="display: none">
							<th align="right">
								<font color="red">*</font>总共周次
							</th>
							<td align="left">
								<html:text name="rs" property="zgzc" styleId="zgzc"
									onkeydown="return onlyNum(this,2)"
									onmousedown="return onlyNum(this,2)"
									onblur="chMax(this,52)"
									maxlength="2"
									style="width: 25px;ime-mode:disabled"/>周
							</td>
							<td align="left"colspan="2">
								<font color="blue">提示：此项目关系到卫生分录入的最大次数。</font>
							</td>
						</tr>
						<tr id="qsrqTr" style="display: none">
							<th align="right">
								<font color="red">*</font>起始日期
							</th>
							<td align="left">
								<html:text name="rs" property="qsrq" styleId="qsrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('qsrq','y-mm-dd');"
								/>
							</td>
							<td align="left"colspan="2">
								<font color="blue">提示：此项目关系到卫生分录入时，当前周次的取得。</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>卫生分设置</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr id="jcfTr" style="display: ">
							<th align="right">
								学生基础分
							</th>
							<td align="left">
								<html:text name="rs" property="jcf"/>
							</td>
							<td align="left"colspan="2">
								<font color="blue">提示：此项目关系到每一位学生每一学期的基础卫生分。
								</font>
							</td>
						</tr>	
						<tr style="">
							<th align="right" width="15%">
								录入形式
							</th>
							<td align="left" width="35%">
								<html:hidden name="rs" property="lrxs" styleId="xs"/>
								<html:radio name="rs" property="lrxs" value="分数" onclick="$('xs').value=this.value;showGlTr()"/>分数
								&nbsp;&nbsp;
								<html:radio name="rs" property="lrxs" value="等级" onclick="$('xs').value=this.value;showGlTr()"/>等级
<%--								&nbsp;&nbsp;--%>
<%--								<html:radio name="rs" property="lrxs" value="扣分" onclick="$('xs').value=this.value;"/>扣分--%>
							</td>
							<td align="left" width="50%" colspan="2">
								<font color="blue">提示：此项目关系到卫生分的录入形式，是数字或者是所设置的等级。</font>
							</td>
						</tr>
						
						<tr id="gldjTr" style="display: none">
							<th align="right">
								是否关联等级
							</th>
							<td align="left">
								<html:hidden name="rs" property="gldj" styleId="dj"/>
								<html:radio name="rs" property="gldj" value="是" onclick="$('dj').value=this.value;showGlTb();"/>是
								&nbsp;&nbsp;
								<html:radio name="rs" property="gldj" value="否" onclick="$('dj').value=this.value;showGlTb();"/>否
								&nbsp;&nbsp;
							</td>
							<td align="left"colspan="2">
								<font color="blue">提示：此项目关系到卫生分录入与查看时，是否需要显示分数与等级的关联。
								比如：获得95分的寝室，卫生分等级为A。
								</font>
							</td>
						</tr>
						<tr id="glfsTr" style="display: none">
							<th align="right">
								是否关联分数
							</th>
							<td align="left">
								<html:hidden name="rs" property="glfs" styleId="fs"/>
								<html:radio name="rs" property="glfs" value="是" onclick="$('fs').value=this.value;showGlTb();"/>是
								&nbsp;&nbsp;
								<html:radio name="rs" property="glfs" value="否" onclick="$('fs').value=this.value;showGlTb();"/>否
								&nbsp;&nbsp;
							</td>
							<td align="left"colspan="2">
								<font color="blue">提示：此项目关系到卫生分录入与查看时，是否需要显示等级与分数的关联，便于统计平均分等数据。
								不论是否需要关联，都需要设置具体的等级名称。
								</font>
							</td>
						</tr>
					</tbody>
					<!-- 分数关联等级 -->
					<thead id="gldjTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>等级关联设置</span>
							</th>
						</tr>
					</thead>
					<!-- 等级关联分数 -->
					<thead id="glfsTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>分数关联设置</span>
							</th>
						</tr>
					</thead>
					<!-- 等级不关联分数 -->
					<thead id="bglfsTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>等级设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="nrTb" style="display: none">
						<tr>
							<td colspan="4">
							
							<input  value="+" onclick="addDj('add')" style="width: 20px"/>
							<input  value="-" onclick="trDel('nr','delRow')" style="width: 20px"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
							<input type="text" name="nrAdd" id="nrAdd" style="width: 20px" onblur="addDj('madd')"/>
							&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
							<input type="text" name="nrDel" id="nrDel" style="width: 20px" onblur="trDelAll('nr','nrDel')"/>
							&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<table class="formlist" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div class="mid_box">
											<table align="center" style="width: 100%" id="rsT" class="tbstyle">
												<thead style="height: 23px">
													<!-- 分数关联等级 -->
													<tr id="gldjTopTr" style="display: none">
														<logic:iterate name="gldjTopList" id="gldjNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${gldjNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
													<!-- 等级关联分数 -->
													<tr id="glfsTopTr" style="display: none">
														<logic:iterate name="glfsTopList" id="glfsNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${glfsNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
													<!-- 等级不关联分数 -->
													<tr id="bglfsTopTr" style="display: none">
														<logic:iterate name="bglfsTopList" id="bglfsNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${bglfsNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
												</thead>		
												<tbody width="100%" class="tbstyle" id="nr">
												
												</tbody>	
											</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
					
					
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:equal name="writeAble" value="yes">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveCssz()" id="buttonSave">
										保 存
									</button>
								</div>
								</logic:equal>
							</td>
						</tr>
					</tfoot>
					
					
				</table>
			</div>			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>