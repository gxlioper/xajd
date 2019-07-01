<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
		<script>
		function changeList(){
			var yhjs = document.getElementById("yhjs").value;
			document.getElementById("btn_add").disabled = false;
			//加载学生信息字段
			xsxxZgkd.getWhzdByYh(yhjs,"xsxxb",function(data){
				DWRUtil.removeAllOptions("xsxxzd");			
				DWRUtil.addOptions("xsxxzd",data,"en","cn");
			});
			
			//加载学生家庭信息字段	
			xsxxZgkd.getWhzdByYh(yhjs,"xsfzxxb",function(data){
				DWRUtil.removeAllOptions("jtxxzd");			
				DWRUtil.addOptions("jtxxzd",data,"en","cn");
			});
			
			xsxxZgkd.getBtzdByYh(yhjs,"xsxxb",function(data){
				DWRUtil.removeAllOptions("mappingList");			
				DWRUtil.addOptions("mappingList",data,"en","cn");
			});
			
			xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
				DWRUtil.removeAllOptions("jtxxMappingList");			
				DWRUtil.addOptions("jtxxMappingList",data,"en","cn");
			});
		}
		
		
		//必填字段分配保存		
		function commit(){
			var xsxxzdLen = document.getElementById("mappingList").options.length;
			var jtxxzdLen = document.getElementById("jtxxMappingList").options.length;
		 	var xsxxzd = "";
		 	var jtxxzd = "";
		 	var yhjs = document.getElementById("yhjs").value;
		 	document.getElementById('btn_add').disabled=true;
		 	if(yhjs==null || yhjs==""){
		 		alert('请选择用户！');
		 		return false;
		 	}
		 	for(var i=0; i<xsxxzdLen; i++){
		 		xsxxzd += document.getElementById("mappingList").options[i].value + "!!";
		 	}
		 	for(var i=0; i<jtxxzdLen; i++){
		 		jtxxzd += document.getElementById("jtxxMappingList").options[i].value + "!!";
		 	}
		 	refreshForm("xsxxZgdzdx.do?method=fpbtzd&type=save&xsxxzd="+xsxxzd+"&jtxxzd="+jtxxzd);
		}
		</script>
</head>
	<body onload="changeList()">
		<html:form action="/xsxxZgdzdx.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			<div class="tab">
			<fieldset>
				<legend>
					<b>字段分配</b>	
				</legend>	
					<table width="100%" class="formlist">
					
					</table>
					<table width="100%" class="formlist" id="rsTable">
						<thead>
							<tr>
								<th colspan="3">
									用户
									<html:select property="yhjs" style="width:80px" onchange="changeList();" styleId="yhjs">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="en" labelProperty="cn"/>
									</html:select>	
								</th>
							</tr>
							<tr>					
								<th>学生信息字段</th>
								<th></th>
								<th>必填字段</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td>
								<html:select property="xsxxzd" style="width:300px;" size="10"
										styleId="xsxxzd" ondblclick="addOneItemList()">																		
								</html:select>
							</td>
							<td>
								<button type="button" id="btn_add"
									onclick="defaultAllItemList()"
									style="width:80px">
									 >> 
								</button>
								<br/><br/>
								<button type="button" id="btn_add"
									onclick="addOneItemList()"
									style="width:80px">
									 > 
								</button>
								<br/><br/>
								<button type="button" id="btn_add"
									onclick="deleteItemList()"
									style="width:80px">
									 < 
								</button>
								<br/><br/>
								<button type="button" id="btn_add"
									onclick="clearList()"
									style="width:80px">
									 << 
								</button>
							</td>
							<td>
								<html:select property="xsxxzdV" style="width:300px;" size="10"
										styleId="mappingList" ondblclick="deleteItemList()">		
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>		
				<table width="100%" class="formlist" id="rsT">
					<thead>
						<tr>					
							<th>学生家庭信息字段</th>
							<th>
							
							</th>
							<th>
							
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td>
							<html:select property="jtxxzd" style="width:300px;" size="10"
									styleId="jtxxzd" ondblclick="addOneJtxxList()">
																	
							</html:select>
						</td>
						<td>
							<button type="button" id="btn_add"
								onclick="defaultAllJtxxList()"
								style="width:80px">
								 >> 
							</button>
							<br/><br/>
							<button type="button" id="btn_add"
								onclick="addOneJtxxList()"
								style="width:80px">
								 > 
							</button>
							<br/><br/>
							<button type="button" id="btn_add"
								onclick="deleteJtxxList()"
								style="width:80px">
								 < 
							</button>
							<br/><br/>
							<button type="button" id="btn_add"
								onclick="clearJtxxList()"
								style="width:80px">
								 << 
							</button>
						</td>
						<td>
							<html:select property="jtxxzdV" style="width:300px;" size="10"
									styleId="jtxxMappingList" ondblclick="deleteJtxxList()">		
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" id="btn_add"
								onclick="commit()"
								style="width:80px">
								保 存
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>		
			</fieldset>
		</div>

		<logic:present name="result">
			<script>
					alert('操作成功!');
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
