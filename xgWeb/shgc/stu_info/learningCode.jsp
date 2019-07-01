<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRule.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
function initClassCodeRule(){
	var totalNum=0;
	getRule.getLearningCodeRule(function(data){
		for(var i=0;i<data.length;i++){
			if(data[i].zdmc!="bh"){
				document.getElementById("zdmc"+i).value=data[i].zdmc;
				if(data[i].zdsjcd==null || data[i].zdsjcd==""){
					document.getElementById("zdsjcd"+i).value="";
				}else{
					document.getElementById("zdsjcd"+i).value=data[i].zdsjcd;
				}
				//起始位
				if(data[i].qsw==null ||data[i].qsw==""){
					document.getElementById("qsw"+i).value="";
				}else{
					document.getElementById("qsw"+i).value=data[i].qsw;
				}
				//位数
				if(data[i].ws=="null" || data[i].ws==null || data[i].ws==""){
					document.getElementById("ws"+i).value="";
				}else{
					document.getElementById("ws"+i).value=data[i].ws;
				}
				//起始编号
				if(data[i].qsbh=="" || data[i].qsbh==null){
					document.getElementById("qsbh"+i).value="";
				}else{
					document.getElementById("qsbh"+i).value = data[i].qsbh;
				}
				//是否补零
				document.getElementById("sfbl"+i).value=data[i].sfbl;				
			}else{
				document.getElementById("zdmc"+i).value=data[i].zdmc;
				document.getElementById("zdsjcd"+i).disabled=true;
				document.getElementById("qsw"+i).disabled=true;
				document.getElementById("ws"+i).disabled=false;
				if(data[i].ws=="" || data[i].ws==null){
					document.getElementById("ws"+i).value="";
				}else{
					document.getElementById("ws"+i).value=data[i].ws;
				}
				document.getElementById("sfbl"+i).disabled=false;
				document.getElementById("qsbh"+i).disabled=false;
				document.getElementById("qsbh"+i).value=data[i].qsbh;
			}				
		}		
		getTotalNum();
	});
}

function getStrLength(obj){
	var id = obj.id;
	var index = id.substring(parseInt(id.length)-1,id.length);
	var strname = "";
	var num = document.getElementById("num").value;	
	for(var i=0; i<num;i++){
		if(document.getElementById("zdmc"+i).value=="bh"){
			document.getElementById("qsw"+i).value="";
			document.getElementById("qsw"+i).disabled=true;
			document.getElementById("qsbh"+i).disabled = false;
			document.getElementById("sfbl"+i).disabled = false;
		}else{
			document.getElementById("qsw"+i).disabled=false;
			document.getElementById("sfbl"+i).disabled = true;
		}
	}
	strname = document.getElementById(id).value;
	getRule.getLength(strname,function(data){
		if(data == null || data==""){
			data = "";
		}
		if(strname=="xh"){
			document.getElementById("qsw"+index).value="";
			document.getElementById("qsw"+index).disabled=true;
		}
		document.getElementById("zdsjcd"+index).value=data;
	});
	getTotalNum();
}

function getTotalNum(){
	var num = document.getElementById("num").value;
	var totalNum ="0";
	for(var i=0; i<num;i++){
		if(document.getElementById("zdmc"+i).value!=""){
			if(document.getElementById("ws"+i).value!=""){
				totalNum = parseInt(totalNum)+parseInt(document.getElementById("ws"+i).value);	
			}
		}
	}
	document.getElementById("xhws").value = totalNum;
}

function checkRule(){
	var num = parseInt(document.getElementById("num").value);
	var fbzd = document.getElementById("fbzdz").value;
	var count=0;
	for(var i=0;i<num;i++){
		var zdsjcd = parseInt(document.getElementById("zdsjcd"+i).value);
		var qsw = parseInt(document.getElementById("qsw"+i).value);
		var ws = parseInt(document.getElementById("ws"+i).value);
		if(document.getElementById("zdmc"+i).value!="bh" && document.getElementById("zdmc"+i).value!=""){
			if(document.getElementById("zdmc"+i).value==fbzd){
				count++;
			}
			if(document.getElementById("qsw"+i).value=="" || document.getElementById("ws"+i).value=="" ||qsw>zdsjcd ||qsw<0 || ws>zdsjcd || ws<0 ){
				var zdmc ="";
				var str = document.getElementById("zdmc"+i).value;
				if(str=="xydm"){
					zdmc = "<bean:message key="lable.xsgzyxpzxy" />代码";
				}
				if(str=="zydm"){
					zdmc = "专业代码";
				}
				if(str=="xqdm"){
					 zdmc == "校区代码";
				}
				if(str=="nj"){
					zdmc = "年级";
				}
				if(str=="pyccdm"){
					zdmc = "培养层次代码";
				}
				alert(zdmc+"设置有错误!");
				return false;
			}
		}else if(document.getElementById("zdmc"+i).value=="bh"){
			if(document.getElementById("ws"+i).value==""){
				alert("位数不能为空!");
				return false;
			}
		}
		if(i==0){
			for(var j=1;j<num;j++)	{
			if($("zdmc"+j)){
			 if(document.getElementById("zdmc"+i).value== document.getElementById("zdmc"+j).value){	
			 	alert("组成项设置重复！");
			 	return false;
			 }
			 }
			}
		}else if(i==num){
			for(var j=num;j>0;j--){
				if($("zdmc"+j)){
				if(document.getElementById("zdmc"+i).value==document.getElementById("zdmc"+j).value){
					alert("组成项设置重复！");
					return false;
				}
				}
			}
		}else{
			for(var j=i;j<num-1;j++){
			if(j-1!=i){
				if($("zdmc"+parseInt(j-1))){
				if(document.getElementById("zdmc"+i).value=="" || document.getElementById("zdmc"+i).value==null){
				}else{
				if(document.getElementById("zdmc"+i).value==document.getElementById("zdmc"+parseInt(j-1)).value){
					alert("组成项设置重复！");
					return false;
				}
				}
				}
			}
			}
			for(var m=i;m>=0;m--){
				if(m-1!=i){
				if($("zdmc"+parseInt(m-1))){
				if(document.getElementById("zdmc"+i).value=="" || document.getElementById("zdmc"+i).value==null){
				}else{				
				if(document.getElementById("zdmc"+i).value==document.getElementById("zdmc"+parseInt(m-1)).value){
					alert("组成项设置重复！");
					return false;
				}			
				}
				}				
				}				
			}			
		}		
	}
	if(count<1){
		//专业代码作为最小单元
		alert('学号组成项必需包含专业代码！');
		return false;
	}
	alert("核查通过!");
	return true;
}

function putValue(obj){
	var fbzd = obj.value;
	document.getElementById("fbzdz").value = fbzd;
}
	</script>	
	</head>
	<body onload="initClassCodeRule()">
			<html:form action="/arrangeClass.do" method="post">
			<input type="hidden" id="num" name="num" value="<bean:write name="zcxNum"/>"/>
			<input type="hidden" id="fbzdz" name="fbzdz" value="<bean:write name="fbzdz"/>"/>
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>您的当前位置:</em><a>
						分班编学号 - 基本设置 - 学号规则设置
						</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>学号规则</span></th>
	        			</tr>
	   				 </thead>			
						<tbody>
							<tr>							
								<th align="center">学号组成项设置</th>
								<th align="center">代码实际位数</th>
								<th align="center">起始位</th>
								<th align="center">位数</th>
								<th align="center">起始序号</th>
								<th align="center">不足位数是否补零</th>
							</tr>
							<logic:iterate id="zcx" name="zcxList" indexId="index">
							<tr><td>	
									<input id="scxhsx${index}" name="scxhsx${index}" type="hidden" value="${index+1}"/>	
									<select id="zdmc${index}" name="zdmc${index}" onchange="getStrLength(this)">
										<option value=""></option>
										<logic:iterate id="v" name="zcxList">
											<option value="${v.zcxdm}">${v.zcxmc}</option>
										</logic:iterate>
									</select>
								</td>
								<td><input id="zdsjcd${index}" name="zdsjcd${index}" readonly="readonly"/></td>
								<td><input id="qsw${index}" name="qsw${index}"/></td>
								<td><input id="ws${index}" name="ws${index}" onblur="getTotalNum()"/></td>
								<td><input id="qsbh${index}" name="qsbh${index}" disabled="disabled"/></td>
								<td>
									<select disabled="disabled" name="sfbl${index}" id="sfbl${index}">
									<option value="是">是</option>
									<option value="否">否</option>									
									</select> 
								</td>
							</tr>
							</logic:iterate>	
							<tr>
							<td>学号基础位数：</td>
							<td colspan="5"><input id="xhws" value="" disabled="disabled"/></td>						
							</tr>
							</tbody>
						<tfoot>
						<tr>
						<td colspan="6" align="center">
							<div class="btn">
							<button class="button2"
								onclick="checkRule()">
								核查规则
							</button>
							<button class="button2"
								onclick="if(checkRule()) refreshForm('arrangeClass.do?method=saveLearningCodeRule');">
								 保存规则
							</button>														
							<button class="button2"
								onclick="Close();return false;">
								关 闭
							</button>
							</div>	
						</td>
						</tr>
						</tfoot>
					</table>					
						<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
								alert("操作成功！");
								Close();
								document.getElementById('search_go').click();						
							</script>
						</logic:equal>
						<logic:equal name="result" value="false">
							<script>
								alert("操作失败!");
							</script>
						</logic:equal>
					</logic:notEmpty>
					
			</html:form>			
	</body>
</html>
