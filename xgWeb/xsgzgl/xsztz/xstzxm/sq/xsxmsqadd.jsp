<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#xh").attr({readonly:"readonly"});
				jQuery("#selhd").unbind('click').bind('click', function(){
					if(jQuery("#xh").val() == ''){
						showAlert("请先填写基本信息！");
						return false;
					}else{
					  var url = "xmsqgl_xmsq.do?method=getXmSelectList&xh="+jQuery("#xh").val()+"&flag=sq";
					  showDialog('请选择项目',600,400,url);
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
		<html:form action="/xmsqgl_xmsq" method="post" styleId="XsXmSqForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" name="splc" id="splc"/>
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
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >选择</button>
							</td>
							<th>项目级别</th>
							<td id="xmjbmc" >
							  
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
							</td>
							<th>学期</th>
							<td id="xq" >
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
							</td>
						</tr>
						<tr>
							<th>可参与人数</th>
							<td id="kcyrs" name="kcyrs">
							</td>
							<th>已申请人数</th>
							<td id="sqrs" name="sqrs">
							</td>
						</tr>
						<tr>
							<th>已通过人数</th>
							<td id="tgrs" name="tgrs">
							</td>
							<th>项目开始时间</th>
							<td id="xmkssj" name="xmkssj">
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup="checkzs();" 
								   style="width:99%;" rows="3"></html:textarea>
							</td>
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