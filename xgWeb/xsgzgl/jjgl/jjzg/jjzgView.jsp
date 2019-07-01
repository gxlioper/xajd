<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjzgForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjzgForm.splcid}&shid=${data.shid}");
		});
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_jjzg" method="post" styleId="zgshForm">
		
			<div class='tab' style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">学号</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">姓名</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>性别</th>
					    	<td>${jbxx.xb }</td>
					    	<th>年级</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>学院</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>班级</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>擅长科目</th>
					    	<td colspan="4">
					    		${jjzgForm.sckmmcs }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>针对年级</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>联系电话</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教经历</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<table width="100%">
									<thead>
										<tr>
											<td>
												家教编号
											</td>
											<td>
												家教学科
											</td>
											<td>
												开始时间
											</td>
											<td>
												结束时间
											</td>
										</tr>
									</thead>
									<logic:empty name="jjjlList">
					    					<tr>
					    						<td colspan="5" style="text-align:center;">
					    							暂无!
					    						</td>
					    					</tr>
					    			</logic:empty>
					    			<logic:notEmpty name="jjjlList">
											<logic:iterate id="jjjj" name="jjjlList">
											<tr>
												<td>${jjjj.xqid}</td>
					    						<td>[${jjjj.jjxkmc}]-[${jjjj.jjnjmc}]</td>
					    						<td>${jjjj.kssj}</td>
					    						<td>${jjjj.jssj}</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" id="shlccx">
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
