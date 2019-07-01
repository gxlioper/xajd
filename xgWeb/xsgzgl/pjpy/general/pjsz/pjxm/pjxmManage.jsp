<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">

		//初始化
		function onShow(){ 
			//
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_pjxm_ajax.do?method=searchPjszPjxm";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			setTimeout("setDivHeight()","1000");

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//显示顺延设置
		function showXmszXmsy(){
			alertInfo("未完成，敬请期待");	
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 项目设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能默认展示的是<font color="blue">本评奖周期</font>的评奖项目，包括了所有的<font color="blue">奖学金与荣誉称号</font>。<br/>
				2.如果您想增加一个新的评奖项目，请点击<font color="blue">增加</font>。<br/>
				3.如果您想增加某项目的信息，请点击<font color="blue">修改</font>，请注意，如果某项目已经有<font color="blue">学生申请</font>，修改内容将受到<font color="blue">限制</font>。<br/>
				4.如果您想删除某项目，请点击<font color="blue">删除</font>，请注意，如果某项目已经有<font color="blue">学生申请</font>，该项目将<font color="blue">不可</font>被删除。<br/>
				5.勾选一个项目，点击<font color="blue">人数设置</font>，可以为该项目设置人数上限，请注意，如果该项目无需人数设置，则<font color="blue">不可进入操作</font>。<br/>
				6.勾选一个项目，点击<font color="blue">兼得设置</font>，可以为该项目设置<font color="blue">不可兼得</font>的项目。<br/>
				7.勾选一个项目，点击<font color="blue">时间设置</font>，可以为该项目设置<font color="blue">申请审核的开始结束时间</font>。<br/>
				8.新增加的项目，系统默认该项目<font color="blue">不可</font>进行<font color="blue">申请与审核</font>，必须进行时间设置。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="str_xmdm" id="xmdm"/>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									返回设置
								</a>
							</li>						
						</logic:equal>
						<!-- 页面来源end -->
						<li>
							<a href="#" onclick="showPjszPjxm();return false;" class="btn_zj">
								增加
							</a>
						</li>
						<li>
							<a href="#" onclick="showPjxmUpdate();return false;" class="btn_xg">
								修改
							</a>
						</li>
						<li>
							<a href="#" onclick="deletePjxm();return false;" class="btn_sc">
								删除
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszPjtj('');return false;" class="btn_csh">
								条件设置
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszRssz('');return false;" class="btn_ck">
								人数设置
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszXmjd('');return false;" class="btn_sz">
								兼得设置
							</a>
						</li>
<%--						<li>--%>
<%--							<a href="#" onclick="showXmszXmsy();return false;" class="btn_sx">--%>
<%--								项目顺延设置--%>
<%--							</a>--%>
<%--						</li>--%>
						<li>
							<a href="#" onclick="showXmszSjsz('');return false;" class="btn_cs">
								时间设置
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmxzDetail();return false;" class="btn_ck">
								项目性质查看
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--					$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 兼得设置弹出层 -->
			<div id="div_xmjd" style="display:none">
				<div class="open_win01">

				</div>
			</div>
			<!-- 兼得设置弹出层 end-->
			
			<!-- 时间设置弹出层 -->
			<div id="div_sjsz" style="display:none">
				<div class="open_win01">
					
				</div>
			</div>
			<!-- 时间设置弹出层 end-->
			
			<!-- 项目性质详细弹出层 begin -->
			<div id="div_xmxz_detail" style="display:none">
				<div class="open_win01" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>项目性质</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									项目性质代码
								</td>
								<td>
									项目性质名称
								</td>
							</tr>
							<logic:notEmpty name="xmxzList">
								<logic:iterate name="xmxzList" id="xmxzMap">
									<tr>
										<td>
											${xmxzMap.dm }
										</td>
										<td>
											${xmxzMap.mc }
										</td>
									</tr>
								</logic:iterate>
								<tr>
									<td colspan="2">
										如需维护其他项目性质，请前往<a href="#" onclick="goPjpjDmwh();return false;"><font color="blue">代码维护</font></a>进行维护
									</td>
								</tr>
							</logic:notEmpty>
							<logic:empty name="xmxzList">
								<tr>
									<td colspan="2">
										项目性质未维护，请前往<a href="#" onclick="goPjpjDmwh();return false;"><font color="blue">代码维护</font></a>进行维护
									</td>
								</tr>
							</logic:empty>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 项目性质详细弹出层 end -->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>