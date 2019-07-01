<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function getXyValue(){
   			var getSelectText = $("xydm").options[$("xydm").selectedIndex].text;
   			var realValue = document.getElementById("format").value;
   			if(realValue==""){
   				document.getElementById("format").value = getSelectText; 
   			}else{
   				document.getElementById("format").value = realValue+","+getSelectText;
   			}
   			var getSelectValue =document.getElementById("xydm").value;
   			var realValue = document.getElementById("sxsfdm").value;
   			if(realValue==""){
   				document.getElementById("sxsfdm").value = getSelectValue; 
   			}else{
   				document.getElementById("sxsfdm").value = realValue+","+getSelectValue;
   			}                
		}
</script>
</head>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>发展教育-发展教育相关-优秀大学生论坛</a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="sxsfdm" name="sxsfdm" value=""/>
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name = "tableName" />"/>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write  name="pk"  scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<div class="tab">
			 	 <table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="4"><span>优秀大学生论坛</span></th>
				        </tr>
				    </thead>
					 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          <logic:notEqual name="doType" value="view">
				          		<button type="button" name="保存" id="buttonSave" onclick="szsxDataDoSave('szdwfzjy.do?method=saveJyltxtOne','fssj-zjr');">保 存</button>
				          </logic:notEqual>
				            <button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
				          </div></td>
				      </tr>
				    </tfoot>
					
					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>发生时间:
							</th>
							<td align="left">
								<html:text name= "rs"  property="fssj" styleId="fssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fssj','y-mm-dd');" />
							</td>
							<th align="right">
								<font color="red">*</font>主讲人:
							</th>
							<td align="left">
								<html:text name = "rs" property="zjr" />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								主讲人简介
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zjrjj' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr>
							<th align="right">
								所属<bean:message key="lable.xsgzyxpzxy" />:
							</th>
							<td align="left">
	        						<html:select name = "rs" property="xydm" styleId="xydm">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
							</td>
							<th align="right">
								期界:
							</th>
							<td align="left">
								<html:text name = "rs" property="qj" />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								主题
							</th>
							<td colspan="3">
								<html:text name = "rs" property="zt" style="width:99%"  />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								纪要
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="jy" style="width:99%" rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								参会人员
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='chry' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
