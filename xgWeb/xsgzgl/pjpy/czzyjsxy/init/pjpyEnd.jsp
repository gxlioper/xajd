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

		}
		
		function theEnd(){
		
			
				var url = "general_pjpy_index_ajax.do?method=dataToHis";
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var parameter={};
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						submitPjlc("ok");
						if(window.dialogArguments.document.getElementById("btn_sx")){
							window.dialogArguments.document.getElementById("btn_sx").click();
						}
					}
				);
		}
		
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" --%>
<%--					onmouseover ="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"--%>
<%--				>--%>
<%--				帮助中心</a><img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>系统提示：</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>--%>
<%--				<span id="div_help" style="display: none">--%>
<%--				1.本功能默认展示的是本评奖学年学期的数据。</br>--%>
<%--				</span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_start" value="no"/>
			
			
				<table width="95%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>
									<logic:equal name="pjzq" value="xn">
										${pjxn }学年评奖结果如下
									</logic:equal>
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td width="">
								<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:200px;width: 100%">
								<table width="99%" class="formlist">
									<tr>
										<th width="24%">
											<div align="left">项目名称</div>
										</th>
										<th width="24%">
											<div align="left">申请/上报 人数</div>
										</th>
										<th width="24%">
											<div align="left">获得人数</div>
										</th>
										<th width="24%">
											<div align="left">总金额</div>
										</th>
									</tr>
									<logic:notEmpty name="bcpjtjInfo">
										<logic:iterate name="bcpjtjInfo" id="bcpjtj">
										<tr>
											<td>
												${bcpjtj.xmmc }
											</td>
											<td>
												${bcpjtj.sqrs }
											</td>
											<td>
												${bcpjtj.hdrs }
											</td>
											<td>
												${bcpjtj.zje }
											</td>
										</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<tbody>
						<tr>
							<td>
							<!--备注 begin-->
							<div class="readme"  id="div_csh_yes" style="height: 200px">
								<h2>您确认后，系统将会执行如下操作</h2>
								<div class="readcon">
									<ul>
										<li id="li_001">将本周期的评奖记录处理到评奖历史库，并清空这些信息</li>
										<li id="li_002">非学生用户可以前往"我的评奖 - 评奖历史结果"进行查看</li>
										<li id="li_004">学生用户可以前往"我的评奖 - 我的评奖结果"进行查看</li>
										<li id="li_008">在学生信息功能也可以进行查看本次评奖的相关结果</li>
										<li id="li_005">非学生用户可以前往"我的评奖 - 评奖历史结果"进行进行相关项目登记表的打印</li>
									</ul>
								</div>
							</div>	
							</td>
						</tr>
					</tbody>
					
			    </table>
		    
			<div>
				<table width="99%" border="0" class="formlist">	
				<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button" onclick="theEnd()">确 认</button>
									<button type="button" onclick="Close();return false;">关 闭</button>
								</div>
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