<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" src="/xgxt/dwr/interface/xtwhDAO.js"></script>
<script language="javascript">
//提交首页调查
function submitDc(){

	var xx_num = document.getElementsByName("xxid").length;
	
	var flag = false;
	var xxid = "";
	
	for(var i=0;i<xx_num;i++){
		var obj = document.getElementsByName("xxid")[i];
		
		if(obj.checked == true){
			flag = true;
			xxid = obj.value;
		}
	}
	
	if(!flag){
		alert("请选调查选项");
		return false;
	}else{
	
		var yhm = $("userName").value;	
		var dcid = $("dcid").value;
		
		dwr.engine.setAsync(false);

		xtwhDAO.saveSydc(yhm,dcid,xxid,function(data){
			if(data == true){
				alert("提交成功！");
			}else{
				alert("你已经接受调查，请不要反复提交！");
			}
		});
		
		dwr.engine.setAsync(true);
		
	}
}

//展示调查统计
function showDctj(){
	var dcid = $("dcid").value;
	showTopWin('/xgxt/xtwhSysz.do?method=sydcView&dcid='+dcid,'430','300')
}
</script>
<div class="index_box_01 reportdisplay">
<h3><span>调查问卷</span></h3>
	<div class="con">
		<h4><span title="${dcnrxx }">${dcnr}</span></h4>
		<table width=100%>
			<logic:iterate name="sydcList" id="sydc">	
				<tr>
					<td>
						<input type="radio" name="xxid" value="${sydc.xxid }" />
						<span title="${sydc.xxnrxx }">${sydc.xxnr }</span>
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td>
					<input type="hidden" name="userName" id="userName" value="${userName }"/>
					<input type="hidden" name="dcid" id="dcid" value="${dcid }"/>
					<logic:notEmpty name="dcnr">
					<button class="tj" onclick="submitDc()">提 交</button>
					<button class="ck" onclick="showDctj()">查看投票结果</button>
					</logic:notEmpty>
				</td>
			</tr>
		</table>
	</div>
</div>