<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

	</head>
	<body>

		<html:form action="/rcsw_xszbb_bbshgl" method="post" styleId="jqlxModel">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<html:hidden property="sqid" styleId="sqid" name="model" />

			<div style="tab;overflow-x:hidden;overflow-y:auto;height:347px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<logic:equal value="10277" name="xxdm">
						<tr>
							<th>是否贫困生</th>
							<td colspan="3">
									${sfkns }
							</td>
						</tr>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学年</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">学期</th>
							<td width="30%">
								${rs.xqmc}
							</td>
					    </tr>
					    <logic:equal value="10344" name="xxdm">
					    	<tr>
					    	<%--
					    		<th width="20%">
									原住宿园区
								</th>
								<td>
									${rs.yqmc}
								</td>
								<th width="20%">
									原宿舍楼号
								</th>
								<td>
									${rs.ldmc}
								</td>
					    	</tr>
					    	<tr>
					    		<th width="20%">
									原寝室号
								</th>
								<td>
									${rs.qsh}
								</td>
							--%>
								<th width="20%">
									原住寝室
								</th>
								<td>
									${rs.yzqs}
								</td>
								<th>
									手机号码
								</th>
								<td>
									${rs.dwlxdh}
								</td>
					    	</tr>
					    	<tr>
								<th>
									家庭地址
								</th>
								<td>
									${rs.lxdw}
								</td>
								<th>
									家庭联系人和手机
								</th>
								<td>
									${rs.bz}
								</td>
							</tr>
							<tr>
								<th>
									留宿园区
								</th>
								<td>
									${rs.lxxqmc}
								</td>
								<th>
									留宿宿舍楼号
								</th>
								<td>
									${rs.lxldmc}
								</td>
							</tr>
							<tr>
								<th>
									留宿寝室号
								</th>
								<td>
									${rs.lxqs}
								</td>
							</tr>
					    </logic:equal>
					    <tr>
							<th>留校开始时间</th>
							<td>
								${rs.lxkssj}
							</td>
							<th>留校截止时间</th>
							<td>
								${rs.lxjzsj}
							</td>
					    </tr>
					    <logic:notEqual value="12309" name="xxdm">
					    <tr>
					    	<th>留校开始时间二</th>
							<td>
								${rs.lxkssj2}
							</td>
							<th>留校截止时间二</th>
							<td>
								${rs.lxjzsj2}
							</td>
						</tr>
						<logic:equal value="10344" name="xxdm">
							<tr>
						    	<th>是否在校过春节</th>
								<td>
									${rs.sfgcj}
								</td>
								<th>申请留校条件</th>
								<td>
									${rs.sqlxtj}
								</td>
							</tr>
						</logic:equal>
						</logic:notEqual>
						<!-- 安徽农业大学留校原因个性化-->
						<logic:equal value="10364" name="xxdm">
							<tr>
								<th>留校原因</th>
								<td colspan="3">
									${rs.lxyymc}
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="11488" name="xxdm">
						<tr>
							<th>留校原因</th>
							<td>
								${rs.lxyymc}
							</td>
							<th><logic:equal value="3" name="rs" property="lxyy">
								兼职单位
							</logic:equal>
							<logic:equal value="7" name="rs" property="lxyy">
								家庭
							</logic:equal>联系人</th>
							<td>
								${rs.dwlxr}
							</td>
						</tr>
						<tr>
							<th>联系电话</th>
							<td>
								${rs.dwlxdh}
							</td>
							<th>原住寝室</th>
							<td>
								${rs.yzqs}
							</td>
						</tr>
					    </logic:equal>
					    <logic:equal value="10530" name="xxdm">
							<tr>
								<th width="20%">
									联系电话
								</th>
								<td>
									${rs.jzlxdh}	
								</td>
								<logic:equal value="寒假" name = "jqlxV">
									<th width="20%">
										是否留校过春节
									</th>
									<td>
										${rs.sfgcj }
									</td>
								</logic:equal>
							</tr>
						</logic:equal>
					     <logic:equal value="11458" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									是否吃年夜饭
								</th>
								<td colspan="3">
									${rs.sfcnyf }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<logic:equal value="10277" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									是否吃年夜饭
								</th>
								<td colspan="3">
									${rs.sfcnyf }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
					    <logic:equal value="10351" name="xxdm">
					    	<logic:equal value="寒假" name = "jqlxV">
							<tr>
								<th>
									是否留校过年
								</th>
								<td colspan="3">
									${rs.sflxgn }
								</td>
				      		</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="3">
									${rs.bz }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<% if("12861".equals(xxdm) || "10351".equals(xxdm)){ %>
							<tr>
								<th>
									家长姓名
								</th>
								<td>
									${rs.jzxm }
								</td>
								<th>
									家长联系电话
								</th>
								<td>
									${rs.jzlxdh }
								</td>
				      		</tr>
						<% } %>
						<logic:equal value="10718" name="xxdm">
						<tr>
								<th>
									是否食用清真食物
								</th>
								<td colspan="3">
									${rs.sfsyqzsw }
								</td>
				      		</tr>
						</logic:equal>
						<logic:equal value="10704" name="xxdm">
						<tr>
								<th>
									是否食用清真食物
								</th>
								<td colspan="3">
									${rs.sfsyqzsw }
								</td>
				      		</tr>
						</logic:equal>
						<!-- 浙江传媒学院个性化增加留宿校区字段begin -->
						<logic:equal value="11647" name = "xxdm">
							<tr>
							<th>
								留宿校区
							</th>
							<td colspan="3">
								${rs.xxxqmc }
							</td>
			      		</tr>
						</logic:equal>
						<!-- 浙江传媒学院个性化增加留宿校区字段end -->
						<!-- 北京中医药个性化begin -->
						<logic:equal value="10026" name = "xxdm">
							<tr>
								<th>
									留校校区
								</th>
								<td>
										${rs.xxxqmc }
								</td>
								<th>
									楼栋
								</th>
								<td>
										${rs.ldmc }
								</td>
							</tr>
							<tr>
								<th>
									寝室号
								</th>
								<td>
										${rs.qsh }
								</td>
							</tr>
						</logic:equal>
						<!-- 北京中医药个性化end -->
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10026">
						<logic:notEqual name="xxdm" value="10344">
						<tr>
							<th>
								原住寝室
							</th>
							<td colspan="3">
								${rs.yzqs }
							</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>
						</logic:notEqual>
			      		<logic:equal value="3" name="rs" property="lxyy">
			      		<tr>
							<th>
								兼职单位
							</th>
							<td colspan="3">
								${rs.lxdw }
							</td>
			      		</tr>
			      		</logic:equal>
						<logic:notEqual name="xxdm" value="10026">
						<logic:notEqual name="xxdm" value="10344">
					    <tr>
							<th>留校寝室</th>
							</th>
							<td colspan="3">
								${rs.rzdz}
							</td>
					    </tr>
					    </logic:notEqual>
						</logic:notEqual>
							
						<logic:equal value="10351" name = "xxdm">
							<tr>
								<th>申请类型</th>
								<td colspan="3">
									${rs.lxsqlxmc}
								</td>
							</tr>		
						</logic:equal>
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10364">
					    <tr>
							<th>留校原因</th>
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
					    </tr>
					    </logic:notEqual>
					    </logic:notEqual>
					    <tr>
							<th align="right" style="width: 10%">
								附件信息
							</th>
							<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = "${rs.fjxx}";
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

