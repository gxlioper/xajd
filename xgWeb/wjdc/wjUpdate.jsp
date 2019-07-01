<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	</head>

	<body onload="">
		<html:form action="/wjdc">
			<!-- 隐藏域 -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="oneSs" id="oneSs" value="${oneSs }"/>
			<input type="hidden" name="allSs" id="allSs" value="${allSs }"/>
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
									<logic:notEqual name="doType"value="view">
										<!-- 回答问卷 -->
										<logic:equal name="bclx" value="hd">
											<button id="buttonSave" onclick="saveHd()">
													确	定
											</button> 
										</logic:equal>
										<!-- 非回答问卷 -->
										<logic:notEqual name="bclx" value="hd">
											<button id="buttonSave" onclick="saveWj()">
													保 存
											</button> 
										</logic:notEqual>
									</logic:notEqual>
									<button onclick="window.close();return false;" id="buttonClose">
											关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									基本信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								学年
							</th>
							<td width="35%">
								<html:hidden name="rs" property="xn"/>
								${rs.xn }
							</td>
							<th width="15%">
								学期
							</th>
							<td>
								<html:hidden name="rs" property="xq"/>
								${rs.xqmc }
							</td>
						</tr>
						<tr>
							<th>
								年度
							</th>
							<td>
								<html:hidden name="rs" property="nd"/>
								${rs.nd }
							</td>
							<th>
								建立时间
							</th>
							<td>
								<html:hidden name="rs" property="jlsj"/>
								${rs.jlsjmc }
							</td>
						</tr>
					</tbody>
			<!-- 学生用户 -->
			<logic:equal name="userType" value="stu">
				<!-- 两个开关 -->
				<input type="hidden" name="kyxg" id="kyxg" value="${rs.kyxg }"/>
				<input type="hidden" name="dawk" id="dawk" value="${rs.dawk }"/>
			</logic:equal>
			<!-- 非学生用户 -->
			<logic:notEqual name="userType" value="stu">
				<thead>
						<tr>
							<td colspan="4">
								<span>相关设置</span>
							</td>
						</tr>
					</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>是否开启
						</th>
						<td>
							开
							<html:radio name="rs" property="sfkq" styleId="kgkq" value="是"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							关
							<html:radio name="rs" property="sfkq" styleId="kggb" value="否"/>
						</td>
						<th>
							模块类型										
						</th>
						<td>
							<html:select property="queryequals_mklx" style="" styleId="mklx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="mklxList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							完成后可否修改
						</th>
						<td>
							<html:select name="rs" property="kyxg" style="" styleId="kyxg">
								<html:options collection="sfList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th>
							答案可以为空					
						</th>
						<td>
							<html:select name="rs" property="dawk" style="" styleId="dawk">
								<html:options collection="sfList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</tbody>
				</logic:notEqual>
				<thead>
						<tr>
							<td colspan="4">
								<span>问卷信息</span>
							</td>
						</tr>
					</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>问卷名称<br/>
							<font color="red">(限50字)</font>
						</th>
						<td colspan="3">
							<!-- 回答问卷 -->
							<logic:equal name="bclx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" readonly="true"/>		
							</logic:equal>
							<!-- 非回答问卷 -->
							<logic:notEqual name="bclx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" onblur="getWjInfo()"/>	
							</logic:notEqual>		
						</td>
					</tr>
					<tr>
						<th>
							卷尾语<br/>
							<font color="red">(限50字)</font>
						</th>
						<td colspan="3">
							<html:text name="rs" property="jwy" maxlength="50" style="width: 90%" />	
						</td>
					</tr>
					<tr>
						<th>
							备注<br/>
							<font color="red">(限500字)</font>
						</th>
						<td colspan="3">
							<!-- 回答问卷 -->
							<logic:equal name="bclx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									readonly="true" style="width: 90%"/>
							</logic:equal>
							<!-- 非回答问卷 -->
							<logic:notEqual name="bclx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									onblur="chLeng(this,500)" style="width: 90%"/>
							</logic:notEqual>
						</td>
					</tr>
				</tbody>	
				
			<!-- 组卷信息 -->
			<logic:equal name="isSt" value="true">
					<thead>
						<tr>
							<td colspan="4">
								<span>组卷信息</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<logic:iterate name="zjlxList" id="lx">
										<thead>
											<tr>
												<td align="center" colspan="2">
													${lx.lxmc}
												</td>
											</tr>
										</thead>
										<!-- 单选题 -->
										<logic:equal name="lx" property="lxdm" value="oneChoose">
											<%@ include file="stxx/zjOneChoose.jsp"%>
										</logic:equal>
										<!-- 多选题 -->
										<logic:equal name="lx" property="lxdm" value="allChoose">
											<%@ include file="stxx/zjAllChoose.jsp"%>
										</logic:equal>
										<!-- 问答题 -->
										<logic:equal name="lx" property="lxdm" value="questions">
											<%@ include file="stxx/zjQueChoose.jsp"%>
										</logic:equal>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
			</logic:equal>
		</table>
		</div>
		<!-- 组卷信息 end-->
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- 提示信息 end-->
		</html:form>
	</body>
</html>
