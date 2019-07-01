<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form action="/jcjy_bcdc" method="post" styleId="jcjyModel">
			<html:hidden property="juid" styleId="juid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    	<tr>
					    		<th>代偿类别</th>
					    		<td>
					    			<span>${model.dclb}</span>
					    		</td>
					    		<th>材料是否齐全</th>
					    		<td>
					    			<span>${model.clsfqq}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>毕业时间</th>
					    		<td>
					    			<span>${model.bysj}</span>
					    		</td>
					    		<th>家人联系方式</th>
					    		<td>
					    			<span>${model.jrlxfs}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>就业单位名称</th>
					    		<td>
					    			<span>${model.jydwmc}</span>
					    		</td>
					    		<th>就业单位详细地址</th>
					    		<td>
					    			<span>${model.jydwdz}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>是否为县政府<br/>所在地</th>
					    		<td>
					    			<span>${model.sfwxzfsfz}</span>
					    		</td>
					    		<th>行业类别</th>
					    		<td>
					    			<span>${hylbmc}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>就业单位人事<br />部门联系电话</th>
					    		<td>
					    			<span>${model.jydwlxdh}</span>
					    		</td>
					    		<th>已签订服务年限</th>
					    		<td>
					    			<span>${model.qdfwnx}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>应缴纳学费<br/>金额（元）</th>
					    		<td>
					    			<span>${model.yjnxf}</span>
					    		</td>
					    		<th>实际缴纳学费<br/>金额（元）</th>
					    		<td>
					    			<span>${model.sjjnxf}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>贷款本金金额（元）</th>
					    		<td>
					    			<span>${model.dkje}</span>
					    		</td>
					    		<th>经办银行名称</th>
					    		<td>
					    			<span>${model.yh}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>贷款合同号</th>
					    		<td>
					    			<span>${model.hth}</span>
					    		</td>
					    		<th>贷款起止时间</th>
					    		<td>
					    			<span>${model.dkkssj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>申请补偿金额（元）</th>
					    		<td>
					    			<span>${model.sqbcje}</span>
					    		</td>
					    		<th>批准补偿代偿<br/>金额（元）</th>
					    		<td>
					    			<span>${model.pzbcdcje}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>第一次代偿金额</th>
					    		<td>
					    			<span>${model.dicdc}</span>
					    		</td>
					    		<th>第一次代偿时间</th>
					    		<td>
					    			<span>${model.dicdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>第二次代偿金额</th>
					    		<td>
					    			<span>${model.decdc}</span>
					    		</td>
					    		<th>第二次代偿时间</th>
					    		<td>
					    			<span>${model.decdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>第三次代偿金额</th>
					    		<td>
					    			<span>${model.dscdc}</span>
					    		</td>
					    		<th>第三次代偿时间</th>
					    		<td>
					    			<span>${model.dscdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>是否在职在岗</th>
					    		<td>
					    			<span>${model.sfzzzg}</span>
					    		</td>
					    		<th>离职离岗是否为正常<br/>调动、提拔、工作<br/>需要换岗</th>
					    		<td>
					    			<span>${model.lzsfzc}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>申请利息回补金额</th>
					    		<td>
					    			<span>${model.ylzd1}</span>
					    		</td>
					    		<th>发放利息回补时间</th>
					    		<td>
					    			<span>${model.ylzd2}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>贷款是否已完全偿还</th>
					    		<td>
					    			<span>${model.dksfwqch}</span>
					    		</td>
					    		<th></th>
					    		<td></td>
					    	</tr>
					    	<tr>
					    		<th>备注</th>
					    		<td colspan="3">
					    			<span>${model.bz}</span>
					    		</td>
					    	</tr>
					</tbody>
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
			</div>
		</html:form>
	</body>
</html>