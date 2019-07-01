<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
<%--		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjl.js"></script>--%>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjlxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zxzx_zxzxjl" method="post" styleId="zxzxjlForm" onsubmit="return false;">
		<html:hidden property="xh" styleId="xh"/>
		<input type="hidden" name="userNameReal" id="userNameReal" value="${userNameReal }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
						<tr>
								<th>
									学号
								</th>
								<td>
									${jbxx.xh}
								</td>													
								<th>
									姓名
								</th>
								<td>
									${jbxx.xm}
								</td>
							</tr>						
					<thead>
						<tr>
							<th colspan="4">
								<span>其他信息
								<a onclick="showqtxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="qtxx" style="display: none">
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
				 <table id="zxjl" width="100%" border="0" class="formlist">				 
					 <thead>
							<tr>
								<th colspan="5">
									<span>咨询记录<a onclick="addRow();" href="javascript:void(0);">
											<img src="images/knsrd/jiahao.gif" />
										</a></span>
								</th>
							</tr>
					</thead>
					<logic:present name="jlList">						
							<logic:iterate name="jlList" id="jl" indexId="s">
								<tbody id="t_${s}" name='xgTbody'>
									<input type="hidden" value="${jl.id}" />
									<tr>
										<th width="25%"><span><font color='red'>*</font></span>编号</th>
										<td width="20%">
											<input type="text" id="bh_${s}" value="${jl.bh}" maxlength="10"/>
										</td>
										<th width="25%"><span><font color='red'>*</font></span>咨询师姓名</th>
										<td width="20%">
											${jl.txrxm}
											<input type="hidden" id="zxsxm_${s}" value="${jl.zxsxm}" maxlength="10"/>
										</td>
										<th width="10%" style="text-align: center;">操作</th>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>咨询时间</th>
										<td>
											<input type="text" id="zxsj_${s}" value="${jl.zxsj}" onfocus='showCalendar("zxsj_${s}","yyyy-MM-dd HH:mm");' maxlength="20"/>
										</td>
										<th><span><font color='red'>*</font></span>咨询地点</th>
										<td>
											<input type="text" id="zxdd_${s}" value="${jl.zxdd}" maxlength="20"/>
										</td>
										<td rowspan="5" style="text-align: center"><a href='javascript:void(0)' onclick='scXg("t_${s}")'>删除</a></td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>来访者对问题的陈述、咨询目的及咨询师的评估印象<br /><font color="red">&lt;限500字&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxpg_${s}" onblur="checkLen(this,500);">${jl.zxpg}</textarea>
										</td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>咨询过程<br /><font color="red">&lt;限2000字&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxgc_${s}" onblur="checkLen(this,2000);">${jl.zxgc}</textarea>										
										</td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>咨询反馈<br /><font color="red">&lt;限500字&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxfk_${s}" onblur="checkLen(this,500);">${jl.zxfk}</textarea>							
										</td>
									</tr>
									<tr>
										<th>咨询体会<br /><font color="red">&lt;限500字&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxth_${s}" onblur="checkLen(this,500);">${jl.zxth}</textarea>										
										</td>
									</tr>
								</tbody>
							</logic:iterate>					
					</logic:present>								
				 </table>
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveJlxx();">
										保存
									</button>
									<button type="button" onclick="reSearch();">
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

