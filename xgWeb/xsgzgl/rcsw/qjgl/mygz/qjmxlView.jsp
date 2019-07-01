<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function viewPic(sqid){
			var url = "<%=request.getContextPath()%>/qjclPic.jsp?id="+sqid; 
			window.open(url);
			//alert(sqid);
		}
		</script>
	</head>
	<body onload="">

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>日常事务 - 请假管理 - 查看请假</a>
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
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。
				</br>
				<span id="div_help" style="display: none"> 
					1.查看请假详细信息及流程图。</br> 
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="sqid" styleId="sqid" />
			<html:hidden name="rs" property="shgw" styleId="shgw" />
			<html:hidden name="rs" property="qjid" styleId="qjid" />
			<input type="hidden" id="shzt" value=""/>
			<!-- 隐藏域 end-->
			
			<div style="width:98%;height:480px;overflow-x:hidden;overflow-y:auto;">
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
							<th width="20%">
								学号
							</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td>
								${rs.lxdh }
							</td>
							<th>
								家庭电话
							</th>
							<td>
								${rs.jtdh }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td colspan="3">
								${rs.jtdz }
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 学生基本信息 end-->
	
				<!-- 请假基本信息 -->
				<table class="formlist" width="">
					<thead onclick="">
						<tr>
							<th colspan="4">
								<span>请假申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								请假开始时间
							</th>
							<td width="30%">
								${rs.kssj }
							</td>
							<th width="20%">
								请假结束时间
							</th>
							<td width="30%">
								${rs.jssj }
							</td>
						</tr>
						<tr>
							<th width="20%">
								申请天数
							</th>
							<td width="30%">
								${rs.sqts }天
							</td>
							<th width="20%">
								请假类型
							</th>
							<td width="30%">
								<logic:equal value="bj" name ="rs" property="kzzd1" >
									病假
								</logic:equal>
								<logic:equal value="sj" name ="rs" property="kzzd1" >
									事假
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								请假流程名称			
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.lxmc }
							</td>
						</tr>
						<tr>
							<th>
								流程图		
							</th>
							<td colspan="3" >
								<table cellpadding="0" cellspacing="0" align="left" border="0">
									<tr>
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<logic:equal value="1" name="qstz" >
												<table style='BORDER: red 2px solid; HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b>提交申请</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														需重新申请
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:equal>
											<logic:notEqual value="1" name="qstz" >
												<table style='HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b>提交申请</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														已提交
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:notEqual>
										</td>
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<img src='xsgzgl/rcsw/qjgl/mygz/arrow_foward.jpg'>
										</td>
										<logic:iterate name="lctLists" id="e" indexId="i">
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<logic:equal value="未审核" name="e" property="shzt">
												<table style='BORDER: red 2px solid; HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b><bean:write name="e" property="mc" />审核</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														<bean:write name="e" property="shzt" />
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:equal>
											
											<logic:notEqual value="未审核" name="e" property="shzt">
												<table style='HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b><bean:write name="e" property="mc" />审核</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														<bean:write name="e" property="shzt" />
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:notEqual>
										</td>
										<logic:notEqual value="${lsszie-1}" name="i" >
											<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
												<img src='xsgzgl/rcsw/qjgl/mygz/arrow_foward.jpg'>
											</td>
										</logic:notEqual>
										</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								申请理由					
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
						<tr>
							<th>
								证明材料
							</th>
							<td colspan="3" style="word-break:break-all;">
								<button type="button" onclick="viewPic('${rs.qjcl }');" id="btn_yl">
									预览
								</button>
							</td>
						</tr>
					</tbody>
				</table>
				
				<!-- 请假基本信息 end-->
				
				<div id="div_shInfo">
				
				</div>
				<!-- 
				<table class="formlist" width="">
				<thead onclick="hiddenMk('mk_qjcl')">
					<tr>
						<th colspan="4">
							<div align="center">证明材料（点击展开详细）</div>
						</th>
					</tr>
				</thead>
				<tbody id="mk_qjcl" style="display:none" width="99%">
					
					<tr>
						<td colspan="4">
						<div id="qjclImg">
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
				</table>
				 -->
			</div>
			
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 关闭-->
								<button onclick="Close();">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>

		</html:form>
	</body>
</html>
