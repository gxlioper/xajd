<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 头文件 -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body onload="">	
		<html:form action="/XsgyglDispatch.do?method=ssYdSqSh" method="post">
		<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - 申请 - 假期留校审核 - 单个审核</a>
				</p>
			</div>
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div class="tab">
				  <table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="4"><span>单个审核</span></th>
				        </tr>
				    </thead>	
				
					 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          <logic:equal value="yes" name="writeAble">
				          <button  onclick="toSave()" id="buttonSave">
							保 存
							</button>			
							</logic:equal>
						<logic:equal value="no" name="writeAble">
							<script>
					         alert("该用户只有读权限!");
					        </script>
						</logic:equal>
						<button  onclick="Close();return false;"  id="buttonClose">
							关 闭
						</button>
				          </div></td>
				      </tr>
				    </tfoot>
					
					<tbody>			
					<tr>
						<th>
							学号：
						</th>
						<td>
						    <bean:write name="rs" property="xh"/>												
						</td>
						<th>
							姓名：
						</th>
						<td>
						 <bean:write name="rs" property="xm"/>	
						</td>						
					</tr>					
					<tr>
						<th>
							性别：
						</th>
						<td>
							 <bean:write name="rs" property="xb"/>	
						</td>
						<th>
							学年：
						</th>
						<td>
							 <bean:write name="rs" property="xn"/>	
						</td>											
					</tr>
					<tr>
						<th>
							年级：
						</th>
						<td>
							 <bean:write name="rs" property="nj"/>	
						</td>
						<th>
							学期：
						</th>
						<td>
							 <bean:write name="rs" property="xq"/>	
						</td>				
					</tr>	
					<logic:notEqual name="xxdm" value="11641">				
						<logic:equal name="xxdm" value="11407">
								 <tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td>
								<bean:write name="rs" property="xymc"/>
							</td>
							<th>
								专业：
							</th>
							<td">
								<bean:write name="rs" property="zymc"/>
							</td>				
						</tr>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11407">
								<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td>
								<bean:write name="rs" property="xymc"/>
							</td>
							<th>
								<font color="red">*</font>楼栋名称：
							</th>
							<td >
									<bean:write name="rs" property="ldmc"/>
							</td>				
						</tr>
						<tr>
							<th>
								专业：
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
							<th>
								<font color="red">*</font>寝室号：
							</th>
							<td align="left">
								<bean:write name="rs" property="qsh"/>
							</td>									
						</tr>
							</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11641">	
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</th>
						<td align="left">
							 <bean:write name="rs" property="xymc"/>	
						</td>
						<th align="right">
							专业：
						</th>
						<td align="left">
							<bean:write name="rs" property="zymc"/>						
						</td>				
					</tr>
					</logic:equal>
					<tr>
						<th align="right">
							班级：
						</th>
						<td align="left">
							 <bean:write name="rs" property="bjmc"/>	
						</td>
						<th align="right">
							联系电话：
						</th>
						<td align="left">
							 <bean:write name="rs" property="lxdh"/>	
						</td>												
					</tr>
					<tr>
						<th align="right">
							住宿开始时间：
						</th>
						<td align="left">
							 <bean:write name="rs" property="zskssj"/>	
						</td>
						<th align="right">
							住宿结束时间：									
						</th>
						<td align="left">
						 <bean:write name="rs" property="zsjssj"/>	
						</td>							
					</tr>
					<tr>
						<th align="right">
							审核： 
						</th>
						<td colspan="3">
							<html:select name="rs"  property="yesNo" >
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="11641">	
						
						<logic:equal value="11407" name="xxdm">
							<tr>
								<th>
									留校原因：							
								</th>
								<td align="left" colspan="3">
									<bean:write name="rs" property="lxyy"/>
								</td>					
							</tr>
						</logic:equal>
						<logic:notEqual value="11407" name="xxdm">
							<logic:notEqual name="xxdm" value="10690">							
							<tr>
								<th>
									留校原因：							
								</th>
								<td align="left" colspan="3">
									<bean:write name="rs" property="ydly"/>
								</td>					
							</tr>
							</logic:notEqual>
							<logic:equal name="xxdm" value="10690">							
							<tr>
								<th>
									留校原因：							
								</th>
								<td align="left" colspan="3">
									<bean:write name="rs" property="lxyy"/>
								</td>					
							</tr>
							</logic:equal>
						</logic:notEqual>
					
					</logic:notEqual>	
					<logic:equal name="xxdm" value="11641">	
					<tr>
						<th>
							留校原因：							
						</th>
						<td align="left" colspan="3">
							<bean:write name="rs" property="lxyy"/>
						</td>					
					</tr>
					</logic:equal>
					<logic:notEqual name="xxdm" value="10690">
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />审核说明：<BR/>
							(小于300字)
						</th>
						<td align="left" colspan="3" >							
							<logic:equal name="userType" value="xx">	
							<html:textarea name="rs"  property="xyshyy" styleId="xyshyy" rows="6" cols="66"  disabled="true">
							</html:textarea>
							</logic:equal>	
							<logic:equal name="userType" value="xy">	
							<html:textarea name="rs"  property="xyshyy" styleId="xyshyy" rows="6" cols="66"  >
							</html:textarea>
							</logic:equal>								
						</td>
					</tr>
					</logic:notEqual>
					<tr>
						<th>
							学校审核说明：<BR/>
							(小于300字) 							                   
						</th>
						<td align="left" colspan="3">
						<logic:equal name="userType" value="xy">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="66"  disabled="true"></html:textarea>
						</logic:equal>
						<logic:equal name="userType" value="xx">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="66" ></html:textarea>
						</logic:equal>						
						</td>
					</tr>
				</table>
			</logic:notEmpty>		
		</html:form>		
		<logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("操作失败!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>	
  </body>
  <script type="text/javascript">
    function toSave(){
       if($("xyshyy")){
          if($("xyshyy").value.length>300){
             alert("<bean:message key="lable.xsgzyxpzxy" />审核说明字数大于300字！");
             return false;
          }
       }
       if($("xxshyy")){
           if($("xxshyy").value.length>300){
             alert("学校审核说明字数大于300字！");
             return false;
          }         
       }
       refreshForm('/xgxt/XsgyglDispatch.do?method=jqLxSqSh&doType=save');
       $("buttonSave").disabled='true';
    }
  </script>
</html>
