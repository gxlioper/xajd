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
	
	function initClassNameRule(){
		var totalNum=0;
		getRule.getClassNameRule(function(data){		
		for(var i=0;i<data.length;i++){
		if(data[i].zdmc==""){
			
		}else{
			//字段名称
			document.getElementById("zdmc"+i).value=data[i].zdmc;			
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
			//是否全部 
			document.getElementById("sfqb"+i).value=data[i].sfqb;
			//常量
			if(data[i].zdmc=="cl"){
				//起始位
				document.getElementById("qsw"+i).disabled="disabled";
				document.getElementById("qsw"+i).value="";
				//位数 
				document.getElementById("ws"+i).disabled="disabled";
				document.getElementById("ws"+i).value="";
				//是否全部 				
				document.getElementById("sfqb"+i).disabled="disabled";
				//常量 
				document.getElementById("cl"+i).disabled=false;
				document.getElementById("cl"+i).value=data[i].cl;
			}
		}	
		}	
	});	
}
		
		//核查规则
	function checkRule(){
		var num = parseInt(document.getElementById("num").value);	
		for(var i=0;i<num;i++){
			if(document.getElementById("zdmc"+i).value==""){
			}else{
				if(document.getElementById("sfqb"+i).value=="否" && document.getElementById("qsw"+i).value=="" && document.getElementById("zdmc"+i).value!="cl"){
					alert("起始位不能为空!");
					return false;
				}
				if(document.getElementById("sfqb"+i).value=="否" && document.getElementById("ws"+i).value=="" && document.getElementById("zdmc"+i).value!="cl"){
					alert("位数不能为空!");
					return false;
				}
				if(document.getElementById("cl"+i).disabled==false){
					if(document.getElementById("cl"+i).value==""){
						alert("请输入常量!");
						return false;
					}
				}
			}
		}
		alert("核查通过!");
		return true;
	}

	function checkCl(obj){
		var id = obj.id;
		var index = id.substring(parseInt(id.length)-1,id.length);
		if(document.getElementById(id).value=="cl"){
			//起始位
			document.getElementById("qsw"+index).disabled="disabled";
			document.getElementById("qsw"+index).value="";
			//位数 
			document.getElementById("ws"+index).disabled="disabled";
			document.getElementById("ws"+index).value="";
			//是否全部 				
			document.getElementById("sfqb"+index).disabled="disabled";
			//常量 
			document.getElementById("cl"+index).disabled=false;
		}else{
			document.getElementById("cl"+index).disabled="disabled";
			document.getElementById("qsw"+index).disabled=false;
			document.getElementById("ws"+index).disabled=false;
			document.getElementById("sfqb"+index).disabled=false;
			document.getElementById("cl"+index).value="";
		}
	}
	</script>	
	</head>
	<body onload="initClassNameRule()">
			<html:form action="/arrangeClass.do" method="post">
			<input type="hidden" id="num" name="num" value="<bean:write name="zcxNum"/>"/>
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>您的当前位置:</em><a>
						分班编学号 - 基本设置 - 班级名称规则设置
						</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>班级名称规则</span></th>
	        			</tr>
	   				 </thead>
					<tbody>
							<tr>							
								<th>班级名称组成项设置</th>								
								<th>起始位</th>
								<th>位数</th>
								<th>是否全部</th>
								<th>常量</th>
							</tr>
							<logic:iterate id="zcx" name="zcxList" indexId="index">
							<tr><td>	
									<input id="xh${index}" name="xh${index}" type="hidden" value="${index+1}"/>	
									<select id="zdmc${index}" name="zdmc${index}" onchange="checkCl(this)">
										<option value=""></option>
										<logic:iterate id="v" name="zcxList">
											<option value="${v.zcxdm}">${v.zcxmc}</option>
										</logic:iterate>
									</select>
								</td>								
								<td><input id="qsw${index}" name="qsw${index}"/></td>
								<td><input id="ws${index}" name="ws${index}"/></td>
								<td>
									
									<select name="sfqb${index}" id="sfqb${index}">
									<option value="否">否</option>
									<option value="是">是</option>																		
									</select> 
								</td>
								<td><input id="cl${index}" name="cl${index}" disabled="disabled"/></td>
							</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
						<tr>
						<td colspan="5" align="center">
							<div class="btn">
							<button  
								onclick="checkRule()"
								style="width:80px">
								核查规则
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button  
								onclick="if(checkRule()) refreshForm('arrangeClass.do?method=saveClassNameRule');"
								style="width:80px">
								 保存规则
							</button>										
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button  
								onclick="Close();return false;"
								style="width:80px">
								关 闭
							</button>	
							</div>
						</td>
						</tr>
					</table>					
				</div>
						<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
								alert("操作成功！");						
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
