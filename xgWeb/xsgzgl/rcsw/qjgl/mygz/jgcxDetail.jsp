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
		//获得审核信息
		function getShInfo(){
			var sqid = $("sqid").value;
			var shgw = $("shgw").value;
			var url = "rcsw_qjgl.do?method=getShInfo";
			
			//参数
		 	var parameter = {
				"sqid":sqid,
				"shgw":shgw
			};
			
			jQuery("#div_shInfo").load(
				url,
				parameter,
				function(){}
			);
		}
		function viewPic(sqid){
			var url = "<%=request.getContextPath()%>/qjclPic.jsp?id="+sqid; 
			window.open(url);
			//alert(sqid);
		}
		
		//保存审核状态
		function saveShzt(shzt){
		
			var sqid = $("sqid").value;
			var shgw = $("shgw").value;
			var shyj = $("shyj").value;
			
			var url="rcsw_qjgl.do?method=saveShzt";
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			//参数
		 	var parameter = {
				"shzt":shzt,
				"sqid":sqid,
				"shgw":shgw,
				"shyj":escape(shyj)
			};
			
			jQuery.post(url,parameter,function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			});
		}
		</script>
	</head>
	<body onload="getShInfo()">

		<!-- 标题 -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>日常事务 - 请假管理 - 结果查询</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"
					onmouseout="showHelpDiv()"> 帮助中心</a>
				<img src="<%=stylePath%>/images/ico_new02.gif" />
			</p>
		</div>--%>
		<!-- 标题 end-->

		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="sqid" styleId="sqid" />
			<html:hidden name="rs" property="shgw" styleId="shgw" />
			<!-- 隐藏域 end-->
			
			<div style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
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
					<thead >
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="qjshList" id="qjsh">
						<tr>
							<th>
								审核岗位
							</th>
							<td>
								${qjsh.gwmc}
							</td>
							<th>
								审核状态
							</th>
							<td>
								${qjsh.shzt}
							</td>
						</tr>
						<tr>
							<th>
								审核意见
							</th>
							<td colspan="3">
							${qjsh.shyj}
							<%--
								<html:textarea name="qjsh" property="shyj" style="word-break:break-all;width:96%"  rows="4" disabled="true"/>
							--%></td>
						</tr>
						<tr>
						</logic:iterate>
					</tbody>
				</table>
				<!-- 请假基本信息 end-->
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
						<div id="qjclImg" >
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
				</table>
				 -->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								
								<!-- 关闭-->
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
