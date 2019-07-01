<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRcswDAO.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">	
	
	//保存项目维护
	function saveXmwh(){
		if($("rqnum")){
			var rqnum = $("rqnum").value;
			var flag = false;
			
			if( rqnum != ""){
				for(var i=0;i<rqnum;i++){
					var id = "ffrq"+ i;
					if($(id)){
						if($(id).checked==true){
							flag = true;
						}
					}
				}
			}
		}
		if(flag){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else{
			alert("请至少选择一个发放人群，请确认!!")
			return false;
		}
	}
	
	//保存项目维护 之江学院
	function saveXm(){
		if($("xmmc").value==""){
			alert("项目名称不能为空!");
			return false;
		}
		if($("xn").value==""){
			alert("学年不能为空!");
			return false;
		}
		if($("xmlx").value==""){
			alert("项目类型不能为空!");
			return false;
		}
		if($("xq").value==""){
			alert("学期不能为空!");
			return false;
		}
		if($("ffsj").value==""){
			alert("发放开始时间不能为空!");
			return false;
		}
		if($("nd").value==""){
			alert("年度不能为空!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("发放结束时间不能为空!");
			return false;
		}
		if($("ffdd").value==""){
			alert("发放地点不能为空!");
			return false;
		}
		if(checkSjTj("ffsj","办理开始时间","ffjssj","办理结束时间")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//保存项目维护 之江学院
	function updateXm(){
		if($("xmmc").value==""){
			alert("项目名称不能为空!");
			return false;
		}
		if($("xn").value==""){
			alert("学年不能为空!");
			return false;
		}
		if($("xmlx").value==""){
			alert("项目类型不能为空!");
			return false;
		}
		if($("xq").value==""){
			alert("学期不能为空!");
			return false;
		}
		if($("ffsj").value==""){
			alert("发放开始时间不能为空!");
			return false;
		}
		if($("nd").value==""){
			alert("年度不能为空!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("发放结束时间不能为空!");
			return false;
		}
		if($("ffdd").value==""){
			alert("发放地点不能为空!");
			return false;
		}
		if(checkSjTj("ffsj","办理开始时间","ffjssj","办理结束时间")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=modi','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//设置发放人群
	function setFfrq(){
		var yffrq = $("yffrq").value;
		var rqnum = $("rqnum").value;
		if(yffrq != ""){
			var ffrq = yffrq.split("!!@@!!");
			for(var i=0;i<rqnum;i++){
				var id = "ffrq"+ i;
				if($(id)){
					for(var j =0;j<ffrq.length;j++){
						if($(id).value == ffrq[j]){
							$(id).checked = true;
						}
					}
				}
			}
		}
	}
	</script>
	</head>

	<body onload="setFfrq()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/rcswLnny">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<input type="hidden" name="rqnum" id="rqnum" value="${rqnum }" />
			<input type="hidden" name="yffrq" id="yffrq" value="${rs.ffrq }" />
			<!--  暂时写死,只能是学生 -->
			<html:hidden property="save_ffrq" styleId="ffrq" value="班级" />
			
		
				
				<table width="90%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生一日规范分</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">
								学号
							</th>
							<td width="30%">
								${xsxx.xh}
							</td>	
							<th width="15%">
								姓名
							</th>
							<td width="30%">
								${xsxx.xm}
							</td>
						</tr>
						<tr>	
							<th>
								性别
							</th>
							<td >
								${xsxx.xb}
							</td>	
							<th>
								身份证号
							</th>
							<td >
								${xsxx.sfzh}
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${xsxx.nj}
							</td>	
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td >
								${xsxx.xymc}
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td >
								${xsxx.xymc}
							</td>	
							<th>
								班级
							</th>
							<td >
								${xsxx.bjmc}
							</td>
						</tr>
					</tbody>
				</table>
			
			<div id="xsqNr" style="width:98%;height: 250px;overflow:scroll;overflow-x:hidden">
			<logic:iterate name="xmlxList" id="xmlx">
				<table width="80%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>${xmlx.xmlxmc } </span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr><td colspan="4">
				<table summary="" class="dateline" width="96%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="xmxxList" id="xmxx">
						<logic:equal name="xmlx" property="xmlxdm" value="${xmxx.xmlxdm }">
						<tr>
							<td>
								${xmxx.xmmc}
							</td>
							<td>
								${xmxx.xn}
							</td>
							<td>
								${xmxx.xqmc}
							</td>
							<td>
								${xmxx.nd}
							</td>
							<td>
								${xmxx.dfsj}
							</td>
							<td>
								${xmxx.fz}
							</td>
						</tr>	
						</logic:equal>
						</logic:iterate>
				</table>
				</td></tr>
				</tbody>
			</logic:iterate>
			</div>
		</html:form>
	</body>
</html>
