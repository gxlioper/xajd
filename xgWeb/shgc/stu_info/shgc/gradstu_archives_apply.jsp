<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script>
			function checkExist(){
				var xh = document.getElementById("xh").value;
				getStuDetails.checkGradArchiveApply(xh,function(data){
					if(data){
						if(confirm("已经存在了转档申请的记录，是否要重新申请？")){
							commSave('/xgxt/business.do?method=gradArchivesApply&doType=save',
							'xh-sqly-hkssqx-hkssjd-hkxxdz-rxsj-bysj');
						}else{
							return false;
						}
					}else{
						commSave('/xgxt/business.do?method=gradArchivesApply&doType=save',
							'xh-sqly-hkssqx-hkssjd-hkxxdz-rxsj-bysj');
					}						
				});
			}
			
			function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			//var xm = document.getElementById("xm").value;
			//var xydm = document.getElementById("xydm").value;
			//var zydm = document.getElementById("zydm").value;
			//var bjdm = document.getElementById("bjdm").value;
			var rxsj = document.getElementById("rxsj").value;
			var bysj = document.getElementById("bysj").value;
			var hkssqx = document.getElementById("hkssqx").value;
			var hkssjd = document.getElementById("hkssjd").value;
			var hkxxdz = document.getElementById("hkxxdz").value;
			var lxfs = document.getElementById("lxfs").value;
			var sqly = document.getElementById("sqly").value;
			//var nj = document.getElementById("nj").value;
			url += "&xh=" + xh;
			//url += "&xm=" + xm;
			//url += "&xydm=" + xydm;
			//url += "&zydm=" + zydm;
			//url += "&bjdm=" + bjdm;
			url += "&rxsj=" + rxsj;
			url += "&bysj=" + bysj;
			url += "&hkssqx=" + hkssqx;
			url += "&hkssjd=" + hkssjd;
			url += "&hkxxdz=" + hkxxdz;
			url += "&lxfs=" + lxfs;
			url += "&sqly=" + sqly;
			//url += "&nj=" + nj;
			
			window.open(url);
			}
		</script>
	</head>
	<body>
		<html:form action="/business.do" method="post">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/shgc/gradstu_archives_apply.jsp"/>
		<input type="hidden" value="xh-xm-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 毕业学生转档 - 填写转档申请表</a>
				</p>
			</div>
				<logic:present name="isBys">
					<logic:equal value="no" name="isBys">
						<br/>
						<br/>
						<center>
							只有毕业两年内的学生可以申请，你不是毕业两年内的学生！
						</center>
					</logic:equal>
				</logic:present>	
				<logic:notPresent name="isBys">
				<table width="98%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="4"><span>毕业学生转档申请</span></th>
	        			</tr>
	   				 </thead>
	   				 <tbody>
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th align="right">
									<font color="red">*</font>学号
								</th>
								<td align="left">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button align="left" class="button2"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									 id="buttonFindStu">
									选择
								</button>								
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th align="right">
									<font color="red">*</font>学号
								</th>
								<td align="left">
									<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
								</td>
							</logic:equal>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">	
								<bean:write name="rs" property="xymc"/>							
<%--								<html:select name="rs" property="xydm"  style="width:150px" styleId="xydm"--%>
<%--								onchange="refreshForm('business.do?method=gradArchivesApply')">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="xyList" labelProperty="xymc" property="xydm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right" >
								姓名
							</th>
							<td align="left">
							<bean:write name="rs" property="xm"/>							
<%--								<html:text name="rs"  property="xm" styleId="xh" />--%>
							</td>							
							<th align="right">
								专业
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>							
<%--								<html:select name="rs"  property="zydm"  style="width:150px"--%>
<%--								onchange="refreshForm('business.do?method=gradArchivesApply')" styleId="zydm">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="zyList" labelProperty="zymc" property="zydm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">	
							<th align="right">
								年级
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>	
<%--								<html:select name="rs"  property="nj"  style="width:150px"--%>
<%--								onchange="" styleId="nj">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="njList" labelProperty="nj" property="nj"/>							--%>
<%--								</html:select>--%>
							</td>
							<th align="right">
								班级
							</th>
							<td align="left">
							<bean:write name="rs" property="zymc"/>	
<%--								<html:select name="rs"  property="bjdm"  style="width:150px"--%>
<%--								onchange="" styleId="bjdm">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:options collection="bjList" labelProperty="bjmc" property="bjdm"/>							--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr style="height:22px">	
							<th align="right">
								<font color="red">*</font>入校时间
							</th>
							<td align="left">
								<html:text name="rs" property="rxsj" styleId="rxsj" onclick="return showCalendar('rxsj','y-mm-dd');"/>
							</td>	
							<th align="right">
								<font color="red">*</font>毕业时间
							</th>
							<td align="left">
								<html:text name="rs"   property="bysj" styleId="bysj" onclick="return showCalendar('bysj','y-mm-dd');"/>
							</td>				
						</tr>								
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>户口所属区县
							</th>
							<td align="left">
								<html:text name="rs"  property="hkssqx" styleId="hkssqx"/>
							</td>
							<th align="right">
								<font color="red">*</font>户口所属街道
							</th>
							<td align="left">
								<html:text name="rs"  property="hkssjd" styleId="hkssjd"/>
							</td>
						</tr>	
						<tr align="left">
							<th align="right">
								<font color="red">*</font>户口详细地址
							</th>
							<td align="left" >
								<html:text name="rs"   property="hkxxdz" styleId="hkxxdz"/>
							</td>
							<th align="right">
								电话或其他联系方式
							</th>
							<td align="left" >
								<html:text name="rs"  property="lxfs" styleId="lxfs"/>
							</td>
						</tr>						
						<tr align="left">
							<th align="right" width="150px">
								<font color="red">*</font>申请理由
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs"  property="sqly" style="height:100px;width:700px" styleId="sqly"/>
							</td>
						</tr>	
						</tbody>
						<tfoot>
						 <tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
						    <div class="btn">
							<button  
								onclick="checkExist()">
								提 交 申 请
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button   onclick="print('business.do?method=printBysArchivesApp')">
								打 印 报 表
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							</td>
						</tr>
					</tfoot>					
					</table>
				</logic:notPresent>	
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("申请成功！");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("申请失败！");
				</script>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
