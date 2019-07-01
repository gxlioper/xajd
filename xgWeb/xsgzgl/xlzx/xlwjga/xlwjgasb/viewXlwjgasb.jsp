<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			function deploy(id){
				var d = (document.getElementById("xswjbx_"+id).style.display == 'none') ? '' : 'none';
				document.getElementById("xswjbx_"+id).style.display = d; 
				document.getElementById("zxfk_"+id).style.display = d; 
			}
			jQuery(function(){
				if("1"=="${rs.ywzyls}"){
					jQuery("#zyqk_tr").show();
				}else{
					jQuery("#zyqk_tr").hide();
				}
			})
		</script>
	</head>
	<body >
		<html:form action="/xlzx_xlwjga_xlwjgasbgl" method="post" styleId="xlwjgasbForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:465px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<logic:notEmpty name="rs" property="zxfk">
						<thead>
							<tr>
								<th colspan="4">
									<span>中心反馈意见</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									反馈学院参考建议
								</th>
								<td colspan="3">
									${rs.zxfk }
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr>
							<th colspan="4">
								<span>案例详细信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								上报人
							</th>
							<td>
								${userNameReal }
							</td>
							<th>
								联系电话
							</th>
							<td>
								${rs.sbrlxfs }
							</td>
						</tr>
						<tr>
							<th>
								危机程度
							</th>
							<td colspan="3">
								${rs.wjcdmc }
							</td>
						</tr>
						<tr>
						    <th>
								学生危机表现
							</th>
							<td colspan="3">
								${rs.xswjbx }
							</td>
						</tr>
						<tr>
						    <th>
								学院处理过程
							</th>
							<td colspan="3">
								${rs.xyclgc }
							</td>
						</tr>
						<tr>
							<th>
								有无住院历史
							</th>
							<td colspan="3">
								${rs.ywzylsmc }
							</td>
						</tr>
						<tr id="zyqk_tr">
							
						    <th>
								住院情况
							</th>
							<td colspan="3">
								${rs.zyqk }
							</td>
						</tr>
						<tr>
						    <th>
								学生目前状况
							</th>
							<td colspan="3">
								${rs.xszk }
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%"  border="0" class="formlist">
				    <thead>
				      <tr>
				      	<th colspan="2">
				      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">历史记录</a>
				   	    </th>
				      </tr>
				    </thead>
				</table>
				<div class="regform" style="padding-top:0px;">
					<div id="myTbody" style="padding-bottom:10px;">
						<logic:notEmpty name="rsList">
							<table width="100%" border="0" class="formlist">
							  <tbody>
								<logic:iterate name="rsList" id="rsMap" indexId="index">
									<tr onclick="deploy('${index}');return false" title="[展开/隐藏]">
										<th width="12%">危机程度</th>
										<td width="17%">${rsMap.wjcdmc}</td>
										<th width="12%">危机个案状态</th>
										<td width="17%">${rsMap.wjgabzmc}</td>
										<th width="12%">上报时间</th>
										<td width="30%">${rsMap.sbsj}<span style="float: right;"><a href="#" class="up">展开/隐藏</a></span></td>
									</tr>
									<tr id="xswjbx_${index}" style="display:none" height="50">
										<th width="12%" align="right" >学生危机表现</th>
										<td colspan="5" align="left">${rsMap.xswjbx}</td>
									</tr>
									<tr id="zxfk_${index}" style="display:none" height="50">
										<th width="12%" align="right" >中心反馈意见</th>
										<td colspan="5" align="left">${rsMap.zxfk}</td>
									</tr>
									<tr style="height:5px"></tr>
								</logic:iterate>
							  </tbody>
							</table>
						</logic:notEmpty>
						<logic:empty name="rsList">
							<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;">没有更多记录</span>			
						</logic:empty>
					</div>
				</div>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();return false;">
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

