<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjlxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zxzx_zxzxjl" method="post" styleId="zxzxjlForm" onsubmit="return false;">
		<html:hidden property="xh" styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="qtxx">
						<tr>
							<th width="20%">
								是否独生子女
							</th>
							<td width="30%">
								${rs.sfdszn}
							</td>							
							<th>家庭所在地</th>
							<td>
								${rs.jtszd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								家庭经济状况
							</th>
							<td width="30%">
								${rs.jtjjzk}
							</td>							
							<th>父亲文化程度</th>
							<td>
								${rs.fqwhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								母亲文化程度
							</th>
							<td width="30%">
								${rs.mqwhcd}
							</td>
							<th>父母的婚姻状况</th>
							<td>
								${rs.fmhyzk}
							</td>
						</tr>
						<tr>
							<th width="20%">
								家庭成员有无精神病史
							</th>
							<td width="30%">
								${rs.jtjsbs}
							</td>					
							<th>对家庭的喜欢程度</th>
							<td>
								${rs.jtxhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								是否接受过心理咨询或心理治疗
							</th>
							<td width="30%">
								${rs.sfzl}
							</td>							
							<th>登记日期</th>
							<td>
								${rs.djrq}
							</td>
						</tr>
						<tr>
							<th>
								欲咨询问题
							</th>
							<td colspan="3">
								${rs.yzxwt}
							</td>
			      		</tr>
			      		<tr>
						<th>
							咨询问题补充
						</th>
						<td colspan="3">
							${rs.wtbc}					
						</td>
					</tr>
					<tr>
						<th>
							咨询的期望和预期结果
						</th>
						<td colspan="3">
							${rs.zxqw}					
						</td>
					</tr>						
					</tbody>
				 </table>
				 <logic:present name="jlList">
					 <table id="zxjl" width="100%" border="0" class="formlist">				 
						 <thead>
								<tr>
									<th colspan="4">
										<span>咨询记录<a onclick="showjlxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a></span>
									</th>
								</tr>
						</thead>
							<tbody id="xgTbody" style="display: none">											
							<logic:iterate name="jlList" id="jl" indexId="s">								
									<tr>
										<th width="20%">编号</th>
										<td width="30%">
											${jl.bh}
										</td>
										<th width="20%">咨询师姓名</th>
										<td width="25%">
											${jl.txrxm}					
										</td>
									</tr>
									<tr>
										<th>咨询时间</th>
										<td>
											${jl.zxsj}
										</td>
										<th>咨询地点</th>
										<td>
											${jl.zxdd}								
										</td>
									</tr>
									<tr>
										<th>来访者对问题的陈述、咨询目的及咨询师的评估印象</th>
										<td colspan="3">${jl.zxpg}</td>
									</tr>
									<tr>
										<th>咨询过程</th>											
										<td colspan="3">${jl.zxgc}</td>				
									</tr>
									<tr>
										<th>咨询反馈</th>
										<td colspan="3">${jl.zxfk}</td>
									</tr>
									<tr>
										<th>咨询体会</th>
										<td colspan="3">${jl.zxth}</td>
									</tr>								
								</logic:iterate>
							</tbody>					
					</logic:present>								
				 </table>
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

