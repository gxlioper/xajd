<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
				<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
	<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 申请 - 学生走读(外住)申请</a>
			</p>
		</div>
		<!-- 标题 end-->	
		<html:form action="/XsgyglDispatch.do?method=xsZdSq" method="post">			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">							
				<fieldset>	
			<logic:notEqual value="stu" name="userType">					
			   <div align="center"><font color="red">只有学生用户可以申请!</font> </div>
		     </logic:notEqual>
								
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<span>学生走读申请</span>
							</td>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th align="right">
								学号：
							</th>
							<td align="left">							
								<html:text name='rs' property="xh" readonly="true"
									styleId="xh"/>							
							</td>
							<th align="right">
								外住开始学年：
							</th>
							<td align="left">
								<html:select name="rs" property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
						    <th align="right">
								姓名：
							</th>
							<td align="left">
								<bean:write name="rs" property="xm"/>
							</td>						
							<th align="right">
								外住开始学期：
							</th>
							<td align="left">
							  <html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								年级：
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>
							</td>
							 <th align="right">
								外住开始日期：
							</th>
							<td align="left">
                                   <html:text name="rs" property="wzksrq" readonly="true"
									onclick="return showCalendar('wzksrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " property="wzksrq"/>							</td>
						</tr>
						<tr>
							<th align="right">
								性别：
							</th>
							<td align="left">
								<bean:write name="rs" property="xb"/>
							</td>
							<th align="right">
								计划外住时间：
							</th>
							<td align="left">
								<html:text name='rs' property="jhwzsj" styleId="jhwzsj" style="width:80px"/><span style="color: red">如：12个月</span>						
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								专业：
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
							<th align="right">
								外住类型：
							</th>
							<td align="left">
								<html:select name="rs" property="wzlxdm" styleId="wzlxdm">
									<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
								</html:select>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								班级：
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>							
							<th align="right">
								外住地址：
							</th>
							<td align="left">
								<html:text name="rs" property="wzdz" styleId="wzdz"/>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								手机号码：
							</th>
							<td align="left">
								<bean:write name="rs"  property="sjhm"/>
							</td>							
							<th align="right">
								家长是否同意：
							</th>
							<td align="left">
								<html:select name="rs" property="jzsfty" styleId="jzsfty">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
						</tr>	
						<tr>						   
							<th align="right">
								固定电话：
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>	
						<tr>						   
							<th align="right">
								电子邮箱：
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>					
						<tr>
							<th align="right">
								外住原因：<br>(限200字内)
							</th>
							<td align="left" colspan="3">
							    <html:textarea  name="rs" property="wzyy" styleId="wzyy" rows="6"  
									style="width:95% " ></html:textarea>								
							</td>
							
						</tr>
						</tbody>
						<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="wzDataSave()"
										style="width: 80px">
										提 交
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("申请成功!");				
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("申请失败,请重新申请！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function wzDataSave(){
            if(mstFill("xh-xn-xq-wzksrq-jhwzsj-wzlxdm-wzdz-wzyy-lxfs-jzsfty"))
            { 
               if($('wzyy').value.length>200){
                  alert('外住原因字数超出200字！');
                  return false;
               }else{                  
                  refreshForm('/xgxt/XsgyglDispatch.do?method=xsZdSqSave');
                   $("buttonSave").disabled=true;
               }
          }    
       }
       function mstFill(mustFill){
           var eles = mustFill.split("-");
	       for (i = 0; i < eles.length; i++) {
		      if($(eles[i])){
			     if (document.getElementById(eles[i]).value == "") {
				    alert("所有选项不得为空！");
				    return false;
			     }
		      }
	       }
	       return true;
       }
	</script>
</html>
