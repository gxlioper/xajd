<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	function add(){
		    
	      var sydw = document.getElementById("sydw").value; 
	      var sqrxm = document.getElementById("sqrxm").value; 
	      var rs = document.getElementById("rs").value; 
	      var xslx = document.getElementById("xslx").value; 
	      var hdnr = document.getElementById("hdnr").value; 
	      var bz = document.getElementById("bz").value; 
	
	     
	      if(sydw==""){
	      alert("使用单位不能为空！");
	      return false;
	      }
	      if(sqrxm==""){
	      alert("申请人姓名不能为空！");
	      return false;
	      }
	     
	      if(rs != ""){
	      if(!isNumber(rs)){
		      alert("人数只能是数字！");
		      return false;
		      }
	      }

	      if(hdnr!=""){
		      if(hdnr.length>600){
		    	  alert("活动内容不能超过600个汉字");
			      return false;
		      }
	      }
	      if(bz!=""){
		      if(bz.length>300){
		    	  alert("备注不能超过300个汉字");
			      return false;
		      }
	      }
	        BatAlert.showTips('正在提交，请稍侯...');
	        if(xslx=="add"){
				 document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=save&act=save";
	        }
	        if(xslx=="update"){
	        	document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=update&act=update";
	        }
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
	function sh(){
		BatAlert.showTips('正在提交，请稍侯...');
		document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_sh&xxk=cdsbsy&act=sh";
		document.forms[0].submit();
	}	
	</script>
	
	</head>
	<body>
		<html:form action="/zgdzdxXxwh.do" method="post">
			<input id="xslx" type="hidden" name="xslx" value="<bean:write name="xslx"/>"/>

			<div class="tab">
			<table width="100%" class="formlist">
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		      <logic:equal value="update" name="xslx">
				<div align="center">
				<button type="button" onclick="add();" >
					提 交
				</button>
				<button type="button" onclick="Close();return false;">
					关 闭
				</button>
			</div>
			</logic:equal>
			<logic:equal value="add" name="xslx">
			<div align="center">
				<button type="button" onclick="add();">
					提 交
				</button>
				<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关 闭
				</button>
			</div>
			</logic:equal>
			<logic:equal value="sh" name="xslx">
			<div align="center">
				<button type="button" onclick="sh();">
					提 交
				</button>
				<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关 闭
				</button>
			</div>
			</logic:equal>
		          </div></td>
		      </tr>
		    </tfoot>
			
			<logic:equal value="add" name="xslx">
				<thead>
					<tr>
						<th colspan="4" height="">
							<span>场地、设备使用申请</span>
						</th>
					</tr>
				</thead>
				
				<tbody>	
				<tr>
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>	
				<tr>
					<th>
						<font color="red">*</font>使用单位
					</th>
					<td>
						<html:text property="sydw" maxlength="60"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>申请人姓名
					</th>
					<td>  
						<html:text property="sqrxm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						申请人班级
					</th>
					<td>
						<html:text property="sqrbj" maxlength="60"></html:text>
					</td>
					<th>
						人数
					</th>
					<td>  
						<html:text property="rs" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						使用时间	
					</th>
					<td>
						<html:text property="sysj" maxlength="20" onclick="return showCalendar('sysj','y-mm-dd');"></html:text>
					</td>
					<th>
						是否审批	
					</th>
					<td>
						<html:select property="sfsp">
							<html:option value=""></html:option>
							<html:option value="已审批">已审批</html:option>
							<html:option value="未审批">未审批</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td colspan="3">  
						<html:textarea property="bz" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						活动内容
					</th>
					<td colspan="3">  
						<html:textarea property="hdnr" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="update" name="xslx">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>场地、设备使用修改</b>
						</td>
					</tr>
				</thead>
				<tbody>	
					<tr>
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>使用单位
					</th>
					<td>
						<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="sydw" maxlength="60"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>申请人姓名
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					<th nowrap="nowrap">
						申请人班级
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60"></html:text>
					</td>
					<th>
						人数
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					<th>
						使用时间	
					</th>
					<td>
						<html:text name="rs" property="sysj" onclick="return showCalendar('sysj','y-mm-dd');" maxlength="20"></html:text>
					</td>
					<th>
						是否审批	
					</th>
					<td>
						<html:select name="rs" property="sfsp">
							<html:option value=""></html:option>
							<html:option value="已审批">已审批</html:option>
							<html:option value="未审批">未审批</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						活动内容
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="view" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>场地、设备使用查看</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>使用单位
					</th>
					<td>
						<html:text name="rs" property="sydw" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>申请人姓名
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						申请人班级
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						人数
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						使用时间	
					</th>
					<td>
						<html:text name="rs" property="sysj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						是否审批	
					</th>
					<td>
						<html:text name="rs" property="sfsp" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						活动内容
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="sh" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>场地、设备使用审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>使用单位
					</th>
					<td>
						<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="sydw" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>申请人姓名
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						申请人班级
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						人数
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						使用时间	
					</th>
					<td>
						<html:text name="rs" property="sysj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						是否审批	
					</th>
					<td>
						<html:text name="rs" property="sfsp" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						活动内容
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>		
					<th nowrap="nowrap">
						学校审核
					</th>
					<td>
						<html:select name="rs" property="xxsh">
							<html:option value=""></html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
							<html:option value="未审核">未审核</html:option>
						</html:select>
					</td>
					<th>
						审核时间
					</th>
					<td>  
						<html:text name="rs" property="shsj" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						审核意见
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="shyj" rows="5" style="width :100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
			</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("提交成功！");
                      Close();
			 window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                      Close();
			 window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

