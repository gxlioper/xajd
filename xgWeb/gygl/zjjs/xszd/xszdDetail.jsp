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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		//保存审核状态
		function saveShzt(shzt){
			
			$("shzt").value = shzt;
			
			confirmInfo("您是否要审核"+shzt+"该申请记录? 请确认",submitShzt);

		}
		
		function submitShzt(tag){	
			if(tag == "ok"){
				showTips('处理中，请稍后......');
				var url = "gyglZjjs.do?method=xszdUpdate&doType=save";
					url+= "&pk="+$("id_pk").value;
				refreshForm(url);
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/gyglZjjs" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="pk" styleId="id_pk" value="${rs.pk }" />
			<html:hidden property="shzt" styleId="shzt" value="" />
			<!-- 隐藏域 end-->
			
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
							${rs.xh }
						</td>
						<th width="20%">姓名</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${rs.xb }
						</td>
						<th>班级</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>联系电话</th>
						<td>
							${rs.lxdh }
						</td>
						<th>家庭电话</th>
						<td>
							${rs.jtdh }
						</td>
					</tr>
					<tr>
						<th>家庭地址</th>
						<td colspan="3">
							${rs.jtdz }
						</td>
					</tr>
					<tr>
						<th>住宿地址</th>
						<td colspan="3">
							${rs.zsdd }
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>走读开始时间</th>
						<td>
							${rs.zdkssj }
						</td>
						<th><font color="red">*</font>走读结束时间</th>
						<td>
							${rs.zdjssj }
						</td>
					</tr>
					<tr>
						<th>申请理由</th>
						<td colspan="3" style="word-break:break-all;" >
							${rs.sqly }
						</td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3" style="word-break:break-all;" >
							${rs.bz }
						</td>
					</tr>
					<logic:empty name="mklx">
						<logic:equal name="userStatus" value="fdy">
							<tr>
								<td align="right">辅导员审核意见<br><font color="red">(限250字)</font></td>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bjshyj"  
										styleId="bjpyyj" rows="5" 
										style="word-break:break-all;width:100%" 
										onblur="chLeng(this,'250')"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userStatus" value="xy">
							<tr>
								<td align="right">辅导员审核意见</td>
								<td align="left" colspan="3" style="word-break:break-all;" >
									${rs.bjshyj }
								</td>
							</tr>
							<tr>
								<td align="right">院系审核意见<br><font color="red">(限250字)</font></td>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xyshyj"  
										styleId="xyshyj" rows="5" 
										style="word-break:break-all;width:100%" 
										onblur="chLeng(this,'250')"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</logic:empty>
					
					<logic:notEmpty name="mklx">
						<tr>
							<td align="right">辅导员审核意见</td>
							<td align="left" colspan="3" style="word-break:break-all;" >
								${rs.bjshyj }
							</td>
						</tr>
						<tr>
							<td align="right">院系审核意见</td>
							<td align="left" colspan="3" style="word-break:break-all;" >
								${rs.xyshyj }
							</td>
						</tr>
					</logic:notEmpty>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<logic:empty name="mklx">
									<button
										onclick="saveShzt('通过')">
										通 过
									</button>
									
									<button
										onclick="saveShzt('不通过')">
										不通过
									</button>
								</logic:empty>
								<button onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
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