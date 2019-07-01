<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function add(){
		    
	     
	      var xslx = document.getElementById("xslx").value; 
	      var xh = document.getElementById("xh").value;
	      var sbmc = document.getElementById("sbmc").value;
	      var sbxh = document.getElementById("sbxh").value;
	      var grsj = document.getElementById("grsj").value;  
	
	      if(xh==""){
				alert("序号为必填项！！");
				return false;
		    }
		    if(sbmc==""){
				alert("设备名称为必填项！！");
				return false;
		    }
		    if(sbxh==""){
				alert("设备型号为必填项！！");
				return false;
		    }
		    if(grsj==""){
				alert("购入时间为必填项！！");
				return false;
		    }

	      
	        BatAlert.showTips('正在提交，请稍侯...');
	        if(xslx=="add"){
				 document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zcgl&czlx=save&act=save";
	        }
	        if(xslx=="update"){
	        	document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zcgl&czlx=update&act=update";
	        }
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
	 function sh(){
			BatAlert.showTips('正在提交，请稍侯...');
			document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_sh&xxk=zcgl&act=sh";
			document.forms[0].submit();
		}			
	</script>

	</head>
		<body>
		<html:form action="/zgdzdxXxwh.do" method="post">
			<input id="xslx" type="hidden" name="xslx" value="<bean:write name="xslx"/>"/>
			
			<div class="tab">
			<table width="100%" class="formlist">
			<logic:equal value="add" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>资料管理增加</span>
						</th>
					</tr>
				</thead>
				
				<tbody>		
				<tr>				
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text property="xh" maxlength="30"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>设备名称
					</th>
					<td>  
						<html:text property="sbmc" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						<font color="red">*</font>设备型号
					</th>
					<td>
						<html:text property="sbxh" maxlength="60"></html:text>
					</td>
					<th>
						<font color="red">*</font>购入时间
					</th>
					<td>  
						<html:text property="grsj" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						保修时间	
					</th>
					<td>
						<html:text property="bxsj" maxlength="20"></html:text>
					</td>
					<th>
						价格
					</th>
					<td>
						<html:text property="jg" maxlength="30"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						购买经手人
					</th>
					<td>
						<html:text property="gmjsr" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						设备负责人
					</th>
					<td>
						<html:text property="sbfzr" maxlength="20"></html:text>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="update" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>资料管理修改</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<tr>				
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
					<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="xh" maxlength="30"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>设备名称
					</th>
					<td>  
						<html:text name="rs" property="sbmc" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						设备型号
					</th>
					<td>
						<html:text name="rs" property="sbxh" maxlength="20"></html:text>
					</td>
					<th>
						购入时间
					</th>
					<td>  
						<html:text name="rs" property="grsj" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						保修时间	
					</th>
					<td>
						<html:text name="rs" property="bxsj" maxlength="20"></html:text>
					</td>
					<th>
						价格
					</th>
					<td>
						<html:text name="rs" property="jg" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						购买经手人
					</th>
					<td>
						<html:text name="rs" property="gmjsr" maxlength="30"></html:text>
					</td>
					<th nowrap="nowrap">
						设备负责人
					</th>
					<td>
						<html:text name="rs" property="sbfzr" maxlength="30"></html:text>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="view" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>资料管理查看</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>				
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>设备名称
					</th>
					<td>  
						<html:text name="rs" property="sbmc" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						设备型号
					</th>
					<td>
						<html:text name="rs" property="sbxh" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						购入时间
					</th>
					<td>  
						<html:text name="rs" property="grsj" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						保修时间	
					</th>
					<td>
						<html:text name="rs" property="bxsj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						价格
					</th>
					<td>
						<html:text name="rs" property="jg" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						购买经手人
					</th>
					<td>
						<html:text name="rs" property="gmjsr" maxlength="90" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						设备负责人
					</th>
					<td>
						<html:text name="rs" property="sbfzr" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="sh" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>资料管理审核</span>
						</th>
					</tr>
				</thead>	
				<tbody>	
				<tr>				
					<th>
						<font color="red">*</font>序号
					</th>
					<td>
					<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="xh" maxlength="60" readonly="sh"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>设备名称
					</th>
					<td>  
						<html:text name="rs" property="sbmc" maxlength="60"  readonly="sh"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						设备型号
					</th>
					<td>
						<html:text name="rs" property="sbxh" maxlength="60"  readonly="sh"></html:text>
					</td>
					<th>
						购入时间
					</th>
					<td>  
						<html:text name="rs" property="grsj" maxlength="10" readonly="sh"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						保修时间	
					</th>
					<td>
						<html:text name="rs" property="bxsj" maxlength="90" readonly="sh"></html:text>
					</td>
					<th>
						价格
					</th>
					<td>
						<html:text name="rs" property="jg" maxlength="90" readonly="sh"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						购买经手人
					</th>
					<td>
						<html:text name="rs" property="gmjsr" maxlength="90" readonly="sh"></html:text>
					</td>
					<th nowrap="nowrap">
						设备负责人
					</th>
					<td>
						<html:text name="rs" property="sbfzr" maxlength="90" readonly="sh"></html:text>
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
						<html:text name="rs" property="shsj"
								   styleId="shsj" 
								   onclick="showCalendar(this.id,'y-mm-dd')" 
								   onblur='dateFormatChg(this)' 
								   maxlength="10" readonly="true"></html:text>
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
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         	<logic:equal value="update" name="xslx">
			         	<button type="button" onclick="add();">
							提 交
						</button>
						<button type="button" onclick="Close();return false;">
							关 闭
						</button>
						</logic:equal>
						
						<logic:equal value="add" name="xslx">
						<button type="button" onclick="add();">
							提 交
						</button>
						<button type="button" onclick="Close();return false;">
							关 闭
						</button>
						</logic:equal>
						
						<logic:equal value="sh" name="xslx">
						<button type="button" onclick="sh();">
							提 交
						</button>
						<button type="button" onclick="Close();return false;">
							关 闭
						</button>
						</logic:equal>
						
						<logic:equal value="view" name="xslx">
							<button type="button" onclick="Close();return false;">
								关 闭
							</button>
						</logic:equal>
						
			          </div></td>
			      </tr>
			    </tfoot>
				
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

