<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gzwpcmdjgl/js/gzwpcmdj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gzwpcmdj" method="post" styleId="gzwpcmdjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:370px;margin-bottom: 0px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贵重物品出门登记</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">* </font>学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" maxlength="20" styleClass="text_nor" readonly="true" />
								<button class="btn_01" type="button"  
											onclick="showDialog('请选择一个学生',680,480,'gygl_gzwpcmdj.do?method=showStudents&goto=${path}');return false;">选择</button>
							</td>
							<th>
								姓名
							</th>
							<td>
								<span id="xm">${jbxx.xm}</span>
							</td>
						</tr>
						
						<tr>
							<th >性别</th><td>	<span id="xb">${jbxx.xb}</span></td>
							<th >学院名称</th><td>	<span id="xymc">${jbxx.xymc}</span></td>
						</tr>
						
						<tr>
							<th >专业名称</th><td>	<span id="zymc">${jbxx.zymc}</span></td>
							<th >班级名称</th><td>	<span id="bjmc">${jbxx.bjmc}</span></td>
						</tr>
						
						<tr>
							<th >楼栋名称</th><td>	<span id="ldmc">${gzwpxx.ldmc}</span></td>	
							<th >寝室号</th><td>	<span id="qsh">${gzwpxx.qsh}</span></td>			
						</tr>
						<tr>
							<th >身份证号</th><td>	<span id="sfzh">${jbxx.sfzh}</span></td>	
							<th >
								<font color="red">* </font>物品名称
							</th>
							<td>
								<html:text property="wpmc" styleId="wpmc"  maxlength="30"></html:text>
							</td>			
						</tr>
						<tr>
							<th >
								<font color="red">* </font>出门时间
							</th>
							<td>
								<html:text property="cmsj" styleId="cmsj" style="width:145px;" onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm','','',250,10);"></html:text>
							</td>						
							<th >
								<font color="red">* </font>值班人员
							</th>
							<td>
								<html:text property="zbry" styleId="zbry"  maxlength="30"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								备注
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="8"></html:textarea>
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
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="save_button" type="button"
										onclick="addGzwpcmdjxx();">
										保 存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

