<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/jxgl/xnjxsq/js/xnjxsq.js"></script>
		<script type="text/javascript">
			function jifen(){
				var fen = jQuery("#jcxf").html();
				var jichu = jQuery("#jxid").val();
				jQuery("input[name='jxId']").each(function(i,n){
					if(jQuery(n).val()==jichu){
						jQuery("#zxf").html(Number(fen)+Number((jQuery(n).parent().find("td").eq(1).html())));
						return false;
					}
				})
				return false;
			}
			jQuery(function(){
				jifen();
				jQuery("#xh").attr({readonly:"readonly"});
				jQuery("#selhd").unbind('click').bind('click', function(){
					if(jQuery("#xh").val() == ''){
						showAlert("请先填写基本信息！");
						return false;
					}else{
					  var url = "jxgl_xnjxsq.do?method=getXmSelectList&xh="+jQuery("#xh").val();
						showDialog('请选择项目',600,350,url);				
					}
			   });
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/jxgl_xnjxsq" method="post" styleId="XnjxsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>项目名称</th>
							<td>
								<input type="hidden" name="jgid" id="jgid"/>
								<input type="hidden" id="xmdm" name="xmdm"/>
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >选择</button>
							</td>
							<th>项目级别</th>
							<td id="xmjbmc" >
							  ${rs.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
								${rs.xn}
							</td>
							<th>学期</th>
							<td id="xq" >
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
								${rs.sbbmmc} 
							</td>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
								${rs.sskmmc}
							</td>						
						</tr>
						<tr>
							<th>可参与人数</th>
							<td id="kcyrs" name="kcyrs">
								${rs.kcyrs}
							</td>
							<th>项目开始时间</th>
							<td id="xmkssj" name="xmkssj">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th>申报人</th>
							<td id="sbr" name="sbr">
								${rs.sbrxm}
							</td>
							<th>申报人联系方式</th>
							<td id="lxdh" name="lxdh">
								${rs.lxdh}
							</td>
						</tr>
						<tr>
							<th>
								是否设立奖项
							</th>
							<td id="sfsljxmc">
								${rs.sfsljxmc}
							</td>
							<th>
								基础学分
							</th>
							<td id="jcxf">
								${rs.jcxf}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目奖项信息
							</th>
							<td width="30%"  colspan="3">
								
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
									
								</tbody>
								</table>
								</div>								
							</td>
						</tr>
						<thead>
						<tr>
							<th colspan="4">
								<span>获奖情况</span>
							</th>
						</tr>
					   </thead>
						<tr>
							<div style="overflow-y:auto;">
									<table width="100%" border="0" class="formlist">
										
										<tr>
											<th width="20%"><font color="red">*</font>获得奖项</th>
											<td>
												<select name="jxid" id="jxid" style="width:200px" onchange="jifen()">
												
												</select>
												
											</td>
										</tr>
										<tr>
											<th width="20%">总学分</th>
											<td id="zxf">
												
											</td>
										</tr>									
									</table>
								</div>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('save');">
										保存草稿
									</button>
									<button type="button" id="tjsq" onclick="saveSqjg('submit');">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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