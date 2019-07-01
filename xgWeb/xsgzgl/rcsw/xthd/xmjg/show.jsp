<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/rcsw_txhd_xmjg">
		 <% String xxdm = (String) request.getAttribute("xxdm"); %>
		 <html:hidden property="guid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>

		<div style='tab;width:100%;height:395px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							学期
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							活动名称
						</th>
						<td >
							${data.xmmc}
						</td>
						
						<th align="right" width="10%">
							活动时间
						</th>
						<td align="left">
							${data.hdkssj}&nbsp;至&nbsp;${data.hdjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							活动类别
						</th>
						<td align="left">
							${data.lbmc }
						</td>
						<th align="right">
							活动地点
						</th>
						<td align="left">
							${data.hddd}
						</td>
					</tr>
					<tr>
					   <th align="right">
							负责人姓名
						</th>
						<td width="34%">
							${data.fzrxm}
						</td>
						<th align="right" width="10%">
						          联系电话
						</th>
						<td align="left">
							${data.lxdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						承办单位
						</th>
						<td align="left" colspan="3" >
							${data.cbdw}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动目的及意义
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动方案
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							授予学分
						</th>
						<td width="34%" >
							${data.syxf}
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th align="right">
							活动说明
						</th>
						<td align="left" colspan="3">
							${data.hdsm }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							申请理由
						</th>
						<td colspan="3">
							${data.sqly}
						</td>
					</tr>
				</tbody>
				<logic:equal value="12309" name="xxdm">
				<thead>
						<tr>
							<th colspan="4">
								<span>物资申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">申请物资</th>
						<td align="left" colspan="3" >
							${data.sqwz}
						</td>
				</tr>
					<tr>
					   <th align="right">
							使用时间
						</th>
						<td width="34%">
							${data.wzsysj}
						</td>
						<th align="right" width="10%">
						          归还时间
						</th>
						<td align="left">
							${data.wzghsj}
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>宣传申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">宣传方式</th>
						<td align="left">
							${data.xcfs}
						</td>
				<th align="right" width="10%">张贴（悬挂地点）</th>
						<td align="left" colspan="3" >
							${data.xgdd}
						</td>
				</tr>
					<tr>
					<th align="right" width="10%">起止时间</th>
					<td>
					   ${data.xckssj}&nbsp;至&nbsp;${data.xcjssj }
					</td>
					<th align="right" width="10%">海报（横幅）数量</th>
					<td>
					  ${data.hbsl }
					</td>
					</tr>
					<tr>
					<th align="right" width="10%">宣传内容</th>
					<td colspan="3">
					   ${data.xcnr }
					</td>
					
					</tr>
					
				</tbody>
				</logic:equal>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
