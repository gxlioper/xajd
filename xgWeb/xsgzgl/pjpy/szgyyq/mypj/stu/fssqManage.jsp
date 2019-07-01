<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//前往我的评奖
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//执行成功
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			$("had_edit").value = "no";
			alertInfo(result,function(tag){if(tag=="ok"){defalutSqxm();}});
		}
		
		//检查是否修改
		function saveMethod(){
			confirmInfo('您已经为该项目添加了申请记录，请问是否需要保存？',saveFssq);
		}
		</script>
	</head>
	<body onload="createFssqDiv()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 分数申请</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc}</font>学期 展开的。</br>
				2.您可以通过点击<font color="blue">增加</font>或<font color="blue">删除</font>来对选择的项目的申请项进行增删。</br>
				3.若查看已申请的记录请点“<font color="blue">已申请分(XX分)</font>”中蓝色的XX(分值)进行查看。</br>
				4.申请分如果超过上限的话，最后即便通过审核，也会以最高分进行计算。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 操作项目  -->
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<!-- 是否修改 -->
			<input type="hidden" id="had_edit" value="no"/>
			<!-- 初始化 -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- 刷新  -->
			<button type="button" id="btn_sx" onclick="defalutSqxm()" style="display:none">
				刷新
			</button>
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showWaitingDiv('30000');refreshForm('pjpy_szgyyq_mypj.do');return false;" class="btn_fh">
								返回
							</a>
						</li>
						<li>
							<a href="#" onclick="confirmInfo('您确认保存所录入的信息吗？',saveFssq);return false;" class="btn_ccg">
								保存
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->			
				<!-- 过滤条件 -->
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">学号</th>
						<td width="30%">
							<input type="hidden" name="xh" value="${stuInfo.xh }" />
							${stuInfo.xh }
						</td>
						<th width="20%">姓名</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${stuInfo.xb }
						</td>
						<th>班级</th>
						<td>
							${stuInfo.bjmc }
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="4">
							<div id="div_sqxm_info">
								
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');createFssqDiv();$('is_default').value='no';};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');createFssqDiv();$('is_default').value='no';};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td colspan="3" valign="top">
							<div>
								<button type="button" onclick="addFssq()" id="btn_add">增加</button>
								<button type="button" onclick="confirmInfo('您确认要删除所勾选的记录吗？',delFssq);">删除</button>	
							</div>
							<br />
							<table class="tbstyle" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div id="div_fssq" style="height:330px;">

										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
<%--							<div class="btn">--%>
<%--								<!-- 保存 -->--%>
<%--								<button type="button" onclick="saveFssq();">--%>
<%--									<bean:message key="lable.btn_bc_space" />--%>
<%--								</button>--%>
<%--								<!-- 返回 -->--%>
<%--								<button type="button" onclick="goMypj();">--%>
<%--									<bean:message key="lable.btn_fh_space" />--%>
<%--								</button>--%>
<%--							</div>--%>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>