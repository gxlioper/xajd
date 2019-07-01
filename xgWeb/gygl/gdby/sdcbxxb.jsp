<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript">
		    function yueCout(){
		        if($("yjje").value!=""&&$("yijje").value!=""){
		           $("yue").value=parseFloat(parseFloat($("yjje").value)*10000-parseFloat($("yijje").value)*10000)/10000;
		        }
		    }
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>公寓管理 - 信息维护 - 公寓水电费管理 - 公寓水电费信息维护</a>
			</p>
		</div>

		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>公寓水电费信息维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="sdfsave()" id="buttonSave">
										保存
									</button>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>年度
							</th>
							<td>
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="nd" readonly="true"></html:text>
								</logic:equal>
								<logic:equal value="add" name="doType">
									<html:select property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
							</td>
							<th width="25%">
								<font color="red">*</font>月份
							</th>
							<td align="left">
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="yf" readonly="true"></html:text>
								</logic:equal>
								<logic:equal value="add" name="doType">
									<html:select property="yf" style="width:100px" styleId="yf">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th width="25%">
								楼栋名称
							</th>
							<td align="left">
								<logic:equal value="modi" name="doType">
									<html:hidden name='rs' property="lddm" />
									<html:text name='rs' property="ldmc" readonly="true"></html:text>
								</logic:equal>
								<logic:equal value="add" name="doType">
									<html:select property="lddm" style="width:90px" styleId="lddm"
										onchange="GetQshList()">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</logic:equal>
							</td>
							<th width="25%">
								<font color="red">*</font>寝室号
							</th>
							<td align="left">
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="qsh" readonly="true"></html:text>
								</logic:equal>
								<logic:equal value="add" name="doType">
									<input type="hidden" name="qshV" id="qshV" />
									<html:select property="qsh" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="ssList" property="qsh"
											labelProperty="qsh" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								超标水量
							</th>
							<td align="left">
								<html:text name='rs' property="cbsl"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event)"></html:text>
							</td>
							<th>
								超标水费
							</th>
							<td align="left">
								<html:text name='rs' property="cbsf"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								超标电量
							</th>
							<td align="left">
								<html:text name='rs' property="cbdl"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event)"></html:text>
							</td>
							<th>
								超标电费
							</th>
							<td align="left">
								<html:text name='rs' property="cbdf"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								应交金额
							</th>
							<td align="left">
								<html:text name='rs' property="yjje" styleId="yjje"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event)"></html:text>
							</td>
							<th>
								已交金额
							</th>
							<td align="left">
								<html:text name='rs' property="yijje" styleId="yijje"
									onkeypress='return sztzNumInputValue(this,6,event)'
									onblur="chkInput(this,event);yueCout()"></html:text>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								余额
							</th>
							<td align="left">
								<html:text name='rs' property="yue" styleId="yue"
									onfocus="yueCout()" readonly="true"
									onkeypress='return sztzNumInputValue(this,6,event)'></html:text>
							</td>
							<th>

							</th>
							<td align="left">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
alert("操作成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
  alert("操作失败！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
		      function sdfsave(){		      
		           if(IsNoEmpty('nd-yf-lddm-qsh')){                      
                    var nd = $("nd").value;
                    var yf = $("yf").value;
                    var lddm = $("lddm").value;
                    var qsh = $("qsh").value;
                    pkV = nd+yf+lddm+qsh;                
                      getSztzData.getInfoEx("gdby_gysdcbxxb","nd||yf||lddm||qsh",pkV,"1=1",function(str){
		               if(str){		         	
		                  if(confirm("该寝室,该年度、月份水电信息已存在！\n\n确定要保存？\n\n点击\"确定\"，保存数据并更改已存在信息；\n点击\"取消\"，将放弃更改！")){
		                    refreshForm('/xgxt/XsGyGlLogic.do?method=sdCbXx_Modi&doType=save');
                            $("buttonSave").disabled=true;
		                  }else{
		                     return false;
		                  }	          			              
		               }else{
		                  if(confirm("确定要保存？\n\n点击\"确定\"，保存信息；\n点击\"取消\"，将放弃保存！")){
		                    refreshForm('/xgxt/XsGyGlLogic.do?method=sdCbXx_Modi&doType=save');
                            $("buttonSave").disabled=true;
		                  }else{
		                     return false;
		                  }	
 		               }
                      }); 
                 } 
             }  		
		</script>

</html>



