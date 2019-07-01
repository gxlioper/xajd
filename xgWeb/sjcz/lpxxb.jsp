<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getInsureInfo.js'></script>
		<script type="text/javascript">
		function checkRule(){
				var num = parseInt(document.getElementById("bxxzNum").value);	
				for(var i=0;i<num;i++){		
					if(i==0){
						for(var j=1;j<num;j++)	{
						if($("bxxzdm"+j)){
						 if(document.getElementById("bxxzdm"+i).value== document.getElementById("bxxzdm"+j).value){	
						 	alert("保险险种重复！");
						 	return false;
						 }
						 }
						}
					}else if(i==num){
						for(var j=num;j>0;j--){
							if($("bxxzdm"+j)){
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+j).value){
								alert("保险险种重复！");
								return false;
							}
							}
						}
					}else{
						for(var j=i;j<num-1;j++){
						if(j-1!=i){
							if($("bxxzdm"+parseInt(j-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(j-1)).value){
								alert("保险险种重复！");
								return false;
							}
							}
							}
						}
						}
						for(var m=i;m>=0;m--){
							if(m-1!=i){
							if($("bxxzdm"+parseInt(m-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{				
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(m-1)).value){
								alert("保险险种重复！");
								return false;
							}			
							}
							}				
							}				
						}//end else			
					}	//end for						
				}//end fucntion
				return true;
			}
			
			function loadBxxz(){
				var bxgsdm = document.getElementById("bxgsdm").value;
					getInsureInfo.getBxxzByBxgs(bxgsdm,function(data){
					var cellMuster=[
					function(data){return data.bxxzdm},
					function(data){return data.bxxzmc}
					];
					if (data != null && typeof data == 'object') {
						if ($("bxxzdm").tagName.toLowerCase() =="select"){
							DWRUtil.removeAllOptions("bxxzdm");			
							DWRUtil.addOptions("bxxzdm",data,"bxxzdm","bxxzmc");	
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
			}
		</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">
		<input type="hidden" id="doType"/>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/lpxxb.jsp" />
				<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="学生"/>
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>理赔信息维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:equal value="10865" name="xxdm">
									<button type="button" class="button2"
										onclick="if(checkXnNd('xn','nd')&&checkRule())dataDoSave('xh-nd-bxgsdm-slrq');"
										id="buttonSave">
										保 存
									</button>
									</logic:equal>
									<logic:notEqual value="10865" name="xxdm">
									<button type="button" class="button2"
										onclick="if(checkXnNd('xn','nd')){dataDoSave('xh-nd-bxgsdm-slrq');}"
										id="buttonSave">
										保 存
									</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="Close();return false;" 
										id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do','800','600')"
									class="btn_01" id="buttonFindStu" style="">
									选择
								</button>
							</td>
							<th>
								<font color="red">*</font>年度
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>学年
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								学期
							</th>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								<font color="red">*</font>保险公司
							</th>
							<td align="left">
								<html:select name="rs" property="bxgsdm" style="width:150px"
									styleId="bxgsdm" onchange="loadBxxz();">
									<html:option value=""></html:option>
									<html:options collection="bxgsList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学制
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" />
							</td>
							<th>
								<font color="red">*</font>保险险种
							</th>
							<td align="left">
								<html:select name="rs" property="bxxzdm" style="width:150px"
									styleId="bxxzdm">
									<html:option value=""></html:option>
									<html:options collection="bxxzList" property="bxxzdm"
										labelProperty="bxxzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								理赔金额
							</th>
							<td align="left">
								<html:text name='rs' property='lpje' onkeyup="value=value.replace(/[^\d]/g,'') " style="width:90px" maxlength="8"/>(元)
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								<font color="red">*</font>受理日期
							</th>
							<td align="left">
								<html:text name='rs' property="slrq" styleId="slrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('slrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
								到帐时间
							</th>
							<td align="left">
							<%--上海工程技术大学--%>
							<logic:equal value="10856" name="xxdm">
								<html:select property="dzsj" styleId="dzsj" name="rs">
									<html:option value=""></html:option>
									<html:option value="保险公司受理中">保险公司受理中</html:option>
									<html:option value="已到帐，请尽快到学生处领取">已到帐，请尽快到学生处领取</html:option>
									<html:option value="理赔结束">理赔结束</html:option>
									</html:select>
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								<html:text name='rs' property="dzsj" styleId="dzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('dzsj','y-mm-dd');" />
								</logic:notEqual>	
							</td>
						</tr>
						
						<%--上海工程技术大学--%>
						<logic:equal value="10856" name="xxdm">
						<input type= "hidden" id="bxxzNum" name="bxxzNum" value="<bean:write name="bxxzNum"/>"/>						
							
							<td colspan="4">
							<table class="tbstyle" align="center" width="100%">	
							
							<logic:empty name="bxfyList">
							<logic:iterate id="bxxz" indexId="in" name="bxxzList">
							<tr>
							<th>	
								保险险种							
							</th>
							<td align="left" width="120px">
								<select name="bxxzdm${in}" style="width:120px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>
								 </logic:iterate>			
								</select>
							</td>
							<th>
								花费费用
							</th>
							<td align="left">
<%--								<html:text name='rs' property="hffy" styleId="hffy${index}" />(元)--%>
								<input type="text" name="hffy${in}" value=""/>(元)
							</td>
							<th>
								理赔金额
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value=""/>(元)
							</td>							
							</tr>
							</logic:iterate>
							</logic:empty>
							
							<logic:notEmpty name="bxfyList">
							<input type="hidden" id="bxfyNum" value="${bxfyNum}"/>
							<logic:iterate id="info" name="bxfyList" offset="0" length="${bxfyNum}" indexId="in">
							<tr>
							<th>	
								保险险种						
							</th>
							<td align="left" width="160px">
								<select id="bxxzdm${in}" name="bxxzdm${in}" style="width:160px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>								 	
								 </logic:iterate>			
								</select>
								<input type="hidden" id="bxxzdmV${in}" name="bxxzdmV${in}" value="${info.bxxzdm}"/>					
							</td>
							<th>
								花费费用
							</th>
							<td align="left">
								<input type="text" name="hffy${in}" value=""/>(元)
								<input type="hidden" id="hffyV${in}" value="${info.hffy}" />	
							</td>	
							<th>
								理赔金额
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value="" />(元)
								<input type="hidden" id="lpjeV${in}" value="${info.lpje}" />	
							</td>						
							</tr>
							</logic:iterate>
							<script>
							   var num = document.getElementById("bxfyNum").value;
							   for(var i=0;i<parseInt(num); i++){
							    document.getElementById("bxxzdm" + i).value = document.getElementById("bxxzdmV" + i).value;
							    document.getElementById("hffy" + i).value = document.getElementById("hffyV" + i).value;
							    document.getElementById("lpje" + i).value = document.getElementById("lpjeV" + i).value;
							   }								
							</script>
							<logic:iterate id="info" name="bxxzList" length="${bxxzNum-bxfyNum}" indexId="in">
							<tr>
							<th>	
								保险险种					
							</th>
							<td align="left" width="160px">		
								<select name="bxxzdm${in+bxfyNum}" style="width:160px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>
								 </logic:iterate>			
								</select>						
							</td>
							<th>
								花费费用
							</th>
							<td align="left">
								<input type="text" name="hffy${in+bxfyNum}"/>(元)
							</td>
							<th>
								理赔金额
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value="" onkeyup="value=value.replace(/[^\d]/g,'') "/>(元)
							</td>							
							</tr>
							</logic:iterate>
							</logic:notEmpty>												
							</table>
							</td>							
							<tr align="left">
							<th>
								学校审核意见
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='xxshyj' style="width:99%" rows='4' styleId="xxshyj"/>
							</td>
						</tr>
						</logic:equal>		
						<%--长沙民政--%>
						<logic:equal value="10827" name="xxdm">
							<tr>
							<th>
								花费费用
							</th>
							<td align="left" colspan="3"><html:text property="hffy" name="rs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>(元)
							</td>
							</tr>
							</logic:equal>					
						<tr>
							<th>
								理赔原因
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%" rows='4' />
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='4' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert('操作成功');
				if (window.dialogArguments) {
					close();
					if(window.dialogArguments.document.getElementById("isPage")){
							window.dialogArguments.document.getElementById("isPage").value="yes";
					}
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
