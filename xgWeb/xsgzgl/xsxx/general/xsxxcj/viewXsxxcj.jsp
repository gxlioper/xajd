<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<%--<script type='text/javascript' src='js/calendar/calendar-zh.js'></script>--%>
		<%--<script type='text/javascript' src='js/calendar/calendar.js'></script>--%>
		<script type="text/javascript" src="xsgzgl/xsxx/general/xsxxcj/js/xsxxcj.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>

	</head>
	<body>
		<html:form action="/xsxx_gygl_xsxxcj" method="post" styleId="xsxxcjForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xh" styleId="xh"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">学号</th>
							<td style="width:35%">
								<a class="name" href="#" onclick="showDialog('学生详细信息',700,500,'stu_info_details.do?xh=${xsxxcjForm.xh }')" style="margin-left: 1px;">${xsxxcjForm.xh }</a>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<bean:write name="jbxx" property="xm"/>
							</td>
						</tr>
						
						<tr>
							<th>年级</th>
							<td>
								<bean:write name="jbxx" property="nj"/>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<bean:write name="jbxx" property="xymc"/>
							</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>
								<bean:write name="jbxx" property="zymc"/>
							</td>
							<th>班级</th>
							<td>
								<bean:write name="jbxx" property="bjmc"/>
							</td>
						</tr>
						<tr>
							<th>家庭联系方式</th>
							<td colspan="3">${xsxxcjForm.jtlxfs }</td>
						</tr>
						<tr>
							<th style="width:15%"> 户口所在地</th>
							<td colspan="3">
								${xsxxcjForm.hkszdmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">家庭住址</th>
							<td colspan="3">
								${xsxxcjForm.jtdzmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">生源地</th>
							<td colspan="3">
								${xsxxcjForm.sydmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">籍贯</th>
							<td colspan="3">
								${xsxxcjForm.jgmc }
							</td>							
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生采集信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >户口是否迁入学校</th>
							<td >
								${xsxxcjForm.hksfjrxx }
							</td>
							<th >是否住校</th>
							<td >
								${xsxxcjForm.sfzx }
							</td>
						</tr>
						<tr>
							<th >是否申请入党</th>
							<td >
								${xsxxcjForm.sfsqrd }
							</td>
							<th>是否顶岗实习</th>
							<td >
								${xsxxcjForm.sfdgsx }
							</td>
						</tr>
						<tr id="sfsqrdcs" <logic:equal value="否" name="xsxxcjForm" property="sfsqrd"> style="display: none;" </logic:equal>bgcolor="#f1f1f1">
							<th>递交申请书时间</th>
							<td>
								${xsxxcjForm.djsqssj }
							</td>
							<th>入党时间</th>
							<td>
								${xsxxcjForm.rdsj }
							</td>
						</tr>
						<tr id="sfsqrdcs2" <logic:equal value="否" name="xsxxcjForm" property="sfsqrd"> style="display: none;" </logic:equal> bgcolor="#f1f1f1">
							<th>转正时间</th>
							<td colspan="3" >
								${xsxxcjForm.zzsj }
							</td>
						</tr>
						<tr>
							<th >是否少数民族</th>
							<td >
								${xsxxcjForm.sfssmz }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfssmzcs" <logic:equal value="否" name="xsxxcjForm" property="sfssmz">style="display: none;"</logic:equal> >
							<th>少数民族名称</th>
							<td colspan="3" bgcolor="#f1f1f1">
								${xsxxcjForm.mzmc }
							</td>
						</tr>
						<tr>
							<th >是否临时培训</th>
							<td >
								${xsxxcjForm.sflspx }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sflspxcs" <logic:equal value="否" name="xsxxcjForm" property="sflspx">style="display: none;"</logic:equal>>
							<th>培训名称</th>
							<td colspan="3" bgcolor="#f1f1f1">
								${xsxxcjForm.pxmc }
							</td>
						</tr>
						<tr>
							<th >是否宗教信仰</th>
							<td >
								${xsxxcjForm.sfzjxy }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfzjxycs" <logic:equal value="否" name="xsxxcjForm" property="sfzjxy">style="display: none;"</logic:equal>>
							<th>信仰宗教名称</th>
							<td  bgcolor="#f1f1f1">
								${xsxxcjForm.xyzjmc }
							</td>
							<th>参加宗教时间</th>
							<td  bgcolor="#f1f1f1">
								${xsxxcjForm.cjzjsj }
							</td>
						</tr>
						<tr>
							<th >是否经济困难</th>
							<td >
								${xsxxcjForm.sfjjkn }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjjkncs" <logic:equal value="否" name="xsxxcjForm" property="sfjjkn">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>经济困难原因</th>
							<td colspan="3">
								${xsxxcjForm.jjknyy }
							</td>
						</tr>
						<tr>
							<th >身体是否疾病</th>
							<td >
								${xsxxcjForm.stsfcj }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="stsfcjcs" <logic:equal value="否" name="xsxxcjForm" property="stsfcj">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>身体疾病原因</th>
							<td colspan="3">
								${xsxxcjForm.stcjyy }
							</td>
						</tr>
						
						<tr>
							<th >是否学习困难</th>
							<td >
								${xsxxcjForm.sfxxkn }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxxkncs" <logic:equal value="否" name="xsxxcjForm" property="sfxxkn">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>学习困难原因</th>
							<td colspan="3">
								${xsxxcjForm.xxknyy }
							</td>
						</tr>
						
						<tr>
							<th >是否心理困难</th>
							<td >
								${xsxxcjForm.sfxlkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxlkrcs" <logic:equal value="否" name="xsxxcjForm" property="sfxlkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>心理困扰原因</th>
							<td colspan="3">
								${xsxxcjForm.xlkryy }
							</td>
						</tr>
						
						<tr>
							<th >是否家庭困扰</th>
							<td >
								${xsxxcjForm.sfjtkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjtkrcs" <logic:equal value="否" name="xsxxcjForm" property="sfjtkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>家庭困扰原因</th>
							<td colspan="3">
								${xsxxcjForm.jtkryy }
							</td>
						</tr>
						
						<tr>
							<th >是否有其他困扰</th>
							<td >
								${xsxxcjForm.sfyqtkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfyqtkrcs" <logic:equal value="否" name="xsxxcjForm" property="sfyqtkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>其他困扰原因</th>
							<td colspan="3">
								${xsxxcjForm.qtkryy }
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
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