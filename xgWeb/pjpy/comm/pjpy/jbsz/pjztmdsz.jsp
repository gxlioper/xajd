<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript">
		//批量保存评奖人员
		function saveRysz(){
			
			if($("bj") && $("bj").value==""){
				alert("当前所在班级不能为空!");
				return false;
			}
			
			if($("pjbj") && $("pjbj").value==""){
				alert("评奖所在班级不能为空!");
				return false;
			}
			refreshForm('/xgxt/pjpyRyqd.do?method=pjztmdsz&doType=save');
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖人员调整
				</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<div class="prompt">
			<h3>
				<span>评奖周期：</span>
			</h3>
			<p>
				评奖学年(${pjxtszModel.pjxn })&nbsp;&nbsp;
				评奖学期(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
				评奖年度(${pjxtszModel.pjnd })&nbsp;&nbsp;
			</p>
		</div>
		
		<html:form action="/pjpyRyqd">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="pjxyV" value="" />
			<input type="hidden" name="pjzyV" value="" />
			<input type="hidden" name="pjbjV" value="" />
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
				<!-- 评奖时间设置 -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>评奖人员整体设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
							<table width="100%">
								<tr>
									<th colspan="2">
										<div align="left">所在部门</div>
									</th>
									<th colspan="2">
										<div align="left">评奖部门</div>
									</th>
								</tr>
								<tr>
									<th>
										年级
									</th>
									<td>	
										<html:select property="nj" styleId="nj" style="width:180px"
											 onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										年级
									</th>
									<td>
										<html:select property="pjnj" styleId="pjnj"
											style="width:180px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjnjList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy"/>
									</th>
									<td>
										<html:select property="xydm" styleId="xy" onmouseover="null"
											style="width:180px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy"/>
									</th>
									<td>
										<html:select property="pjxydm" styleId="pjxy" onmouseover="null"
											style="width:180px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjxyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" styleId="zy" onmouseover="null"
											style="width:180px" onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="pjzydm" styleId="pjzy" onmouseover="null"
											style="width:180px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjzyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<span class="red">*</span>班级
									</th>
									<td>
										<html:select property="bjdm" styleId="bj"
											style="width:180px" onmouseover="null">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th>
										<span class="red">*</span>班级
									</th>
									<td>
										<html:select property="pjbjdm" styleId="pjbj"
											style="width:180px" onmouseover="null">
											<html:option value=""></html:option>
											<html:options collection="pjbjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveRysz()" id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="Close();return false;" id="buttonSave">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>
				</logic:notEqual>
		</logic:present>
		</html:form>
	</body>
</html>
