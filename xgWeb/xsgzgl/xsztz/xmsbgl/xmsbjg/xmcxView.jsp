<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/jxbf.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm" onsubmit="return false;">
		<html:hidden property="xmdm" styleId="xmdm" value="${rs.xmdm}"/>
		<html:hidden property="jcxf" styleId="jcxf" value="${rs.jcxf}"/>
		<html:hidden property="xmjbdm" styleId="xmjbdm" value="${rs.xmjbdm}"/>
		<html:hidden property="sskmdm" styleId="sskmdm" value="${rs.sskmdm}"/>
		<html:hidden property="xn" styleId="xn" value="${rs.xn}"/>
		<html:hidden property="xq" styleId="xq" value="${rs.xq}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目申报</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%">
								项目级别
							</th>
							<td width="30%">
								${rs.xmjbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申报部门
							</th>
							<td width="30%">
								${rs.sbbmmc}
							</td>
							<th width="20%">
								所属科目
							</th>
							<td width="30%">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								可参与团/人数
							</th>
							<td width="30%">
								${rs.kcyrs}
							</td>
							<th width="20%">
								项目开始时间
							</th>
							<td width="30%">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申报人
							</th>
							<td width="30%" >
								${rs.sbr}
							</td>
							<th width="20%">
								申报人联系方式
							</th>
							<td width="30%">
								${rs.lxdh}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								是否设立奖项
							</th>
							<td width="30%" >
								${rs.sfsljxmc}
							</td>
							<th width="20%">
								基础学分
							</th>
							<td width="30%" >
								${rs.jcxf}
							</td>
						</tr>
						<logic:equal value="13627" name="xxdm">
						<tr>
							<th width="20%">
								项目场地
							</th>
							<td width="30%" >
								${rs.xmcd}
							</td>
							<th width="20%">
								板块归属
							</th>
							<td width="30%" >
								${rs.bkgsmc}
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="20%">
								项目奖项信息
							</th>
							<td width="30%"  colspan="3">
								<logic:notEmpty name="xmjxList">
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>奖项名称</td>
											<td width='15%'>附加学分</td>
											<td width='15%'>奖项顺序</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									<logic:iterate id="i" name="xmjxList" indexId="index01">
										<tr>
										<input type="hidden" name="jxid" value='${i.jgid}'/>
										<td name="jxArr">${i.jxmc}</td>
										<td>${i.fjxf}</td>
										<td>${i.xssx}</td>
										</tr>
										</logic:iterate>
								</tbody>
								</table>
								</div>
								</logic:notEmpty>
								<logic:empty name="xmjxList">
								无
								</logic:empty>
							</td>
						</tr>
						<tr><th width="20%">项目描述
								</th>
							<td colspan="3">
								${rs.xmms}
							</td>
						</tr>
						<tr><th width="20%">得/扣分依据
									</th>
								<td colspan="3">
									${rs.dkfyj}
								</td>
						</tr>
						<tr><th width="20%">参与要求
									</th>
								<td colspan="3">
									${rs.cyyq}
								</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>参与学生信息&nbsp;<font style="color:red">${xsrs}</font>人</span> 
							</th>
						</tr>
					</thead>
					<tbody>
					<tr><td colspan="4">
					<div class="con_overlfow" style="width:100%;overflow-y:auto;vertical-align: top;">
					<table class="dateline" width="100%">
					<thead>
					<tr>
							<td>学号</td>
							<td>姓名</td>
							<td>班级</td>
							<td>是否缺勤</td>
							<td>获得奖项</td>
							<td>总学分</td>
							<logic:equal value="13627" name="xxdm">
							<td>备注1</td>
							<td>备注2</td>
							<td>备注3</td>
							<td>备注4</td>
							<td>备注5</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_xmsqxx">
					<logic:present name="sqxsList">
										<logic:iterate id="sqxs" name="sqxsList" indexId="i">
											<tr>
												<td>
												${sqxs.xh }
												</td>
												<td>
												${sqxs.xm }
												</td>
												<td>
												${sqxs.bjmc }
												</td>
												<td>
												${sqxs.sfqqmc }
												</td>
												<td>
												${sqxs.jxmc }
												</td>
												<td>
												${sqxs.zxf }
												</td>
												<logic:equal value="13627" name="xxdm">
												<td>${sqxs.bz1}</td>
												<td>${sqxs.bz1}</td>
												<td>${sqxs.bz1}</td>
												<td>${sqxs.bz1}</td>
												<td>${sqxs.bz1}</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:present>
						
						<logic:empty name="sqxsList">
											<tr>
												<td colspan="6" align="center">无学生信息！</td>
											</tr>
										</logic:empty>
										</tbody></table></div></td></tr>
						</tbody>
				</table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
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

