<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/Function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<script language="javascript">	     
	     function sqSave(){
	     	if($("xlzt").value==""){
	     		alert("必填字段不能为空!");
	     		return false;
	     	}
	        refreshForm("/xgxt/xljkMjxyXlztbs.do?method=xlztbsWh&doType=save");
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
	     }
	     
	     function modi(){
	     	if($("xlzt").value==""){
	     		alert("必填字段不能为空!");
	     		return false;
	     	}
	        refreshForm('/xgxt/xljkMjxyXlztbs.do?method=xlztbsOne&doType=update')
	        if($("buttonSave")){
	          $("buttonSave").disabled=true;
	        }
	     }
	     
	     function rychSqPrint(){
	        window.open('xljkMjxyXlztbs.do?method=xlztbsb&pkValue=${pkValue}');
	       }	
	</script>
	</head>
	<body>
		   <html:form action="/xljkMjxyXlztbs" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<logic:equal name="doType" value="add">
				<input type="hidden" name="save_bssj" id="save_bssj" value="${nowTime}"/>
			</logic:equal>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:present name="result">
			<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:present>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xb" />
					</th>
					<td>
					<logic:equal name="doType" value="add">
					<logic:equal name="userType" value="xy">
						<html:select property="xydm" styleId="xy" value="${userDep}" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<html:hidden property="save_xydm" value="${userDep }"/>
					</logic:equal>
					
					<logic:notEqual name="userType" value="xy">
						<html:select property="save_xydm" styleId="xy"  style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</logic:notEqual>
					</logic:equal>
					
					<logic:notEqual name="doType" value="add">
						<html:select property="xydm" styleId="xy" value="${rs.xydm}" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<html:hidden property="save_xydm" value="${rs.xydm }"/>
					</logic:notEqual>
					</td>
					<th>
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						报送部门
					</th>
					<td>
						<logic:equal name="doType" value="add">
						<html:select property="save_bsbm"  style="width:150px" value="${rs.bsbm }">
								<html:option value=""></html:option>
								<html:options collection="bsbmList" property="dm"
								labelProperty="mc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:select property="bsbm"  style="width:150px" value="${rs.bsbm }" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bsbmList" property="dm"
								labelProperty="mc" />
						</html:select>
						<html:hidden property="save_bsbm" value="${rs.bsbm }"/>
						</logic:notEqual>
					</td>
				</tr>
				
				<tr style="height:22px">
					<th>
						负责人
					</th>
					<td>
					    <logic:equal name="doType" value="add">
						<html:text property="save_fzr" value="${rs.fzr}"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<input type="text"  name="fzr" value="${rs.fzr}" readonly="true" maxlength="10"/>
							<html:hidden property="save_fzr" value="${rs.fzr}"/>
						</logic:notEqual>
					</td>
					<th>
						报送人
					</th>
					<td>
						 <logic:equal name="doType" value="add">
								<html:text property="save_bsr" value="${rs.bsr}"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text property="bsr" value="${rs.bsr}" readonly="true" maxlength="10"/>
							<html:hidden property="save_bsr" value="${rs.bsr}"/>
						</logic:notEqual>
						
					</td>
				</tr>
				<tr>
				<th>
					<logic:equal name="doType" value="add">
						<font color="red">*</font>
					</logic:equal>
					<logic:equal name="doType" value="modi">
						<font color="red">*</font>
					</logic:equal>
					<logic:equal name="doType" value="update">
						<font color="red">*</font>
					</logic:equal>
					心理状态
				</th>
				<td>
					<html:select property="save_xlzt" styleId="xlzt" style="width:150px" value="${rs.xlzt}">
								<html:option value=""></html:option>
								<html:options collection="xlztList" property="dm"
								labelProperty="mc" />
						</html:select>
				</td>
				<logic:equal name="doType" value="add">
				<td colspan="2">
				</td>
				</logic:equal>
				<logic:notEqual name="doType" value="add">
				<th>
					报送时间
				</th>
				<td>
					<html:text property="bssj" value="${rs.bssj }" readonly="true"/>
					<html:hidden property="save_bssj" value="${rs.bssj}"/>
				</logic:notEqual>
				</td>
				</tr>
				<tr >
					<th align="right">
						具体情况
						<br />
						<span class="style1">(限500字)&nbsp;</span>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" cols="86" style="width:98%"  style="word-break:break-all;"
						 property="save_jtqk" value="${rs.jtqk}"  onblur="chLeng(this,500)"/>
					</td>
				</tr>
				</tbody>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:equal name="writeAble" value="yes">
				          	<logic:equal name="doType" value="add">
								<button  id="buttonSave" onclick="sqSave();" style="width:80px">
									保  存 
								</button>
							</logic:equal>
							<logic:equal name="doType" value="modi">
								<button  id="buttonSave" onclick="modi()" style="width:80px">
										保  存 
								</button>
							</logic:equal>
						</logic:equal>
						<button class="button2" onclick="rychSqPrint()" style="width:80px">
							打  印
						</button>   
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			</table>
			
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

