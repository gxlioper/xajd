<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		
	<script language="javascript" src="js/dtjsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	function add(){
		    
		  var xslx = document.getElementById("xslx").value; 
	      var xh = document.getElementById("xh").value; 
	      var sqkm = document.getElementById("sqkm").value; 
	      var cbs = document.getElementById("cbs").value; 
	      var jg = document.getElementById("jg").value;
	      var bz =  document.getElementById("bz").value;

		    if(xh==""){
				alert("序号为必填项！！");
				return false;
		    }
		    if(sqkm==""){
				alert("书、期刊名为必填项！！");
				return false;
		    }
		    if(cbs==""){
				alert("出版社为必填项！！");
				return false;
		    }
		    if(jg==""){
				alert("价格为必填项！！");
				return false;
		    }
		    if(bz!=""){
			    if(bz.length>300){
					alert("备注不能超过300个汉字！！");
					return false;
			    }
		    }
	        BatAlert.showTips('正在提交，请稍侯...');
	        if(xslx=="add"){
				 document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=xxzl&czlx=save&act=save";
	        }
	        if(xslx=="update"){
	        	document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=xxzl&czlx=update&act=update";
	        }
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
	 function sh(){
			BatAlert.showTips('正在提交，请稍侯...');
			document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_sh&xxk=xxzl&act=sh";
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
							<span>学习资料增加</span>
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
						<font color="red">*</font>书、期刊名
					</th>
					<td>  
						<html:text property="sqkm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						<font color="red">*</font>出版社
					</th>
					<td>
						<html:text property="cbs" maxlength="60"></html:text>
					</td>
					<th>
						<font color="red">*</font>价格	
					</th>
					<td>  
						<html:text property="jg" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						出版时间		
					</th>
					<td>
						<html:text property="cbsj" maxlength="20" onclick="return showCalendar('cbsj','y-mm-dd');"></html:text>
					</td>
					<th>
						可否借阅
					</th>
					<td>
						<html:select property="kfjy">
							<html:option value=""></html:option>
							<html:option value="可以借阅">可以借阅</html:option>
							<html:option value="暂不借阅">暂不借阅</html:option>
						</html:select>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						备注
					</th>
					<td colspan="3">
						<html:textarea property="bz" rows="5" style="width:100%"></html:textarea>
					</td>
					
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="update" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>学习资料修改</span>
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
						<html:text name="rs" property="xh" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>书、期刊名
					</th>
					<td>  
						<html:text name="rs" property="sqkm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						出版社
					</th>
					<td>
						<html:text name="rs" property="cbs" maxlength="60"></html:text>
					</td>
					<th>
						价格	
					</th>
					<td>  
						<html:text name="rs" property="jg" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						出版时间		
					</th>
					<td>
						<html:text name="rs" property="cbsj" maxlength="20" onclick="return showCalendar('cbsj','y-mm-dd');"></html:text>
					</td>
					<th>
						可否借阅
					</th>
					<td>
						<html:select property="kfjy" name="rs">
							<html:option value=""></html:option>
							<html:option value="可以借阅">可以借阅</html:option>
							<html:option value="暂不借阅">暂不借阅</html:option>
						</html:select>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						备注
					</th>
					<td colspan="3">
						<html:textarea name="rs" rows="5"  property="bz" style="width:100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="view" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>学习资料查看</span>
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
						<font color="red">*</font>书、期刊名
					</th>
					<td>  
						<html:text name="rs" property="sqkm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						出版社
					</th>
					<td>
						<html:text name="rs" property="cbs" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						价格	
					</th>
					<td>  
						<html:text name="rs" property="jg" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						出版时间		
					</th>
					<td>
						<html:text name="rs" property="cbsj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						可否借阅
					</th>
					<td>
						<html:text name="rs" property="kfjy" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						备注
					</th>
					<td colspan="3">
						<html:textarea name="rs" rows="5"  property="bz" readonly="true" style="width:100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="sh" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>学习资料审核</span>
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
						<html:text name="rs" property="xh" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>书、期刊名
					</th>
					<td>  
						<html:text name="rs" property="sqkm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						出版社
					</th>
					<td>
						<html:text name="rs" property="cbs" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						价格	
					</th>
					<td>  
						<html:text name="rs" property="jg" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						出版时间		
					</th>
					<td>
						<html:text name="rs" property="cbsj" maxlength="30" readonly="true"></html:text>
					</td>
					<th>
						可否借阅
					</th>
					<td>
						<html:text name="rs" property="kfjy" maxlength="30" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						备注
					</th>
					<td colspan="3">
						<html:textarea name="rs" rows="5"  property="bz" readonly="true" style="width:100%"></html:textarea>
					</td>
					
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						学校审核：
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
						审核时间：
					</th>
					<td>  
						<html:text name="rs" property="shsj" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						审核意见：
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

