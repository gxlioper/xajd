<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//保存 
		function save(){
			confirmInfo("确定要保存吗？",function(tag){
				if(tag=="ok"){
					var xh = $("xh").value;
					var cfyydm = $("cfyydm").value;
					var cflbdm = $("cflbdm").value;
					var wjsj = $("wjsj").value;
					var fjmc = $("fj").value;
					if (fjmc != null && fjmc != "") {
						var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
						if (hz!='doc'&&hz!='rar'&&hz!='pdf'&&hz!='bmp'&&hz!='jpg'&&hz!='gif'&&hz!='png'){
							alertError("上传文件类型只能为：doc,rar,pdf,bmp,jpg,gif,png");
							return false;
						}
					}
					var fjmcleng = fjmc.substr(fjmc.lastIndexOf("\\")+1,(fjmc.length-fjmc.lastIndexOf("\\")));
					if(fjmcleng.length > 50){
						alertError("文件名过长,请修改文件名后再上传");
						return false;
					}
					
					return false;
					jQuery("#fjmc").val(fjmc);
					getSztzData.getInfoEx("xg_wjcf_wjcfsbb","xh||cfyydm||cflbdm||wjsj",xh+cfyydm+cflbdm+wjsj ," 1=1",function(bool){
					       if(bool){
					    	   alertError("已存在该生处分信息，不能保存！");
					           return false;
					       }else{
					    	   if(""==xh||""==cfyydm||""==cflbdm||""==wjsj){
									alertError("请将带*的项目填写完整！");
									return false;
									}
									refreshForm('general_wjcf_cfsb_ajax.do?method=saveCfsb');
								}
						});		
						}
			});
		}

		</script>
		
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post" enctype='multipart/form-data'>
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="message" id="message" value="${message }">		
					<input type="hidden" name="doType" id="doType"  >
					<html:hidden property="fjmc" styleId="fjmc"/>	
					<div  style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="20%">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="30%">
							
							<html:text  property="xh" styleId="xh" value="${rs.xh}" readonly="true"/>
							
							<button type="button"  onclick="showDialog('请选择一个学生',680,500,'xsxx_xsgl.do?method=showStudents&goto=general_wjcf_cfsb_ajax.do?method=zjWjcf');return false;"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
						<td align="right" width="20%">
							姓名：
						</td>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
							<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							年级：
						</td>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />：
						</td>
							<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							专业：
						</td>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
							政治面貌：
						</td>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>处分上报信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="20%">
							处分学年：
						</td>
						<td align="left" width="30%">
							${dqxn}
						</td>
						<td align="right" width="20%">
							处分学期：
						</td>
						<td align="left" width="30%">
							${dqxq}
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>处分原因：
						</td>
						<td align="left">
							<html:select property="cfyydm" styleId="cfyydm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>处分类别：
						</td>
						<td align="left">
							<html:select property="cflbdm" styleId="cflbdm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>违纪时间：
						</td>
						<td align="left" colspan="3">
							<html:text property="wjsj" styleId="wjsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right" >
							处理决定书面材料或附件：
							<br/>(限doc,rar,图片格式)
						</td>
						<td align="left" colspan="3" >
							<html:file  property='fj' styleId ="fj" style="width:99%" />
						</td>
					</tr>
					<tr>
						<td align="right">
							违纪事实经过：<br/>
							<font color="red"><B>(限1000字)</B></font>
						</td>
						<td align="left" colspan="3" >
								<html:textarea  property='wjssjg' style="width:600px"
								rows='5' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
						<tr>
						<td align="right">
							备注：<br/>
							<font color="red"><B>(限1000字)</B></font>
						</td>
						<td align="left" colspan="3" >
								<html:textarea  property='bz' style="width:600px"
								rows='5' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>已受处分情况</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>处分类别</b></td>
										<td ><b>处分原因</b></td>
										<td ><b>处分时间</b></td>
										<td ><b>处分文号</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
						
						<tbody>
					
					</tbody>
		
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
