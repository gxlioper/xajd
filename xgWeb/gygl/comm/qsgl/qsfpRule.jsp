<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript" defer="defer">
//点击校区园区
function clickXqYq(lx){

	var xqNum = document.getElementsByName("xiaoqu").length;
	var xqdm = new Array();
	var n = 0;
	
	for(var i=0;i<xqNum;i++){
		var obj = document.getElementsByName("xiaoqu")[i];
		if(obj.checked == true){
			xqdm[n] = obj.value;
			n++;
		}
	}
	
	var yqNum = document.getElementsByName("yuanqu").length;
	var yqdm = new Array();
	var m = 0;
	
	for(var i=0;i<yqNum;i++){
		var obj = document.getElementsByName("yuanqu")[i];
		if(obj.checked == true){
			yqdm[m] = obj.value;
			m++;
		}
	}
	
	//存在园区
	if($("p_yq") && lx == "xq"){
		displayNewYq(xqdm);
	}
	
	//存在楼栋
	if($("p_ld")){
		displayNewLd(xqdm,yqdm,"checkbox");
	}
}

//生成园区
function displayNewYq(xqdm){

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	$("p_yq").innerHTML = "";
	var divHtml = "";
	
	dwr.engine.setAsync(false);
	
	//根据校区取得园区
	searchUtil.getGyglInfo("yq",xqdm,null,null,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<input type='checkbox' name='yuanqu' onclick='clickXqYq()' value='"+data[i].yqdm+"'/>"+data[i].yqmc;
			}
		}
	});
	
	dwr.engine.setAsync(true);
	
	$("p_yq").innerHTML = divHtml;
}

//生成楼栋
function displayNewLd(xqdm,yqdm,lx){

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	$("p_ld").innerHTML = "";
	var divHtml = "";
	
	dwr.engine.setAsync(false);

	//根据校区园区取得新楼栋
	searchUtil.getGyglInfo("ldqs",xqdm,yqdm,null,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				if(lx == "checkbox"){
					if(data[i].num == 0){
						divHtml += "<input type='checkbox' name='ld' value='"+data[i].lddm+"' disabled='true'/>"
					}else{
						divHtml += "<input type='checkbox' name='ld' value='"+data[i].lddm+"'/>"
					}
					divHtml += data[i].ldmc;
					divHtml += "【";
					divHtml += data[i].xbxd;
					divHtml += "】";
					divHtml += "(";
					divHtml += data[i].num;
					divHtml += ")";
					if((i+1)%3==0){
						divHtml += "</br>";
					}
				}
			}
		}
	});
	
	dwr.engine.setAsync(true);
	
	$("p_ld").innerHTML = divHtml;
}
</script>
<table class="formlist">
	<thead>
		<tr>
			<th colspan="2">
				<span>分配规则选择(楼栋名称括号内为未分配寝室数)</span>
			</th>
		</tr>
	</thead>
	<tbody>					
		<!-- 从属关系1（校区--园区--楼栋） -->
		<logic:equal name="csgx" value="1">
			<!-- 校区 -->
			<tr>
				<th width="20%">
					<bean:message key="lable.xiaoqu" />
				</th>
				<td>
					<logic:notEmpty name="xqdmList">
						<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
							<logic:notEqual name="xqNum" value="0">
								<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
							</logic:notEqual>
							<%if((xqNum.intValue()+1)%5==0){%>
								<% out.print("</br>"); %>
							<%}%> 
						</logic:iterate>
					</logic:notEmpty>
				</td>
			</tr>
			<!-- 园区 -->
			<tr>
				<th width="20%">
					<bean:message key="lable.yuanqu" />
				</th>
				<td>
					<p id="p_yq">
						<logic:notEmpty name="yqList">
							<logic:iterate name="yqList" id="yq" indexId="yqNum">
								<logic:notEqual name="yqNum" value="0">
									<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
								</logic:notEqual>
								<%if((yqNum.intValue()+1)%5==0){%>
									<% out.print("</br>"); %>
								<%}%> 
							</logic:iterate>
						</logic:notEmpty>
					</p>
				</td>
			</tr>
		</logic:equal>
		
		<!-- 从属关系2（校区--楼栋） -->
		<logic:equal name="csgx" value="2">
			<!-- 校区 -->
			<tr>
				<th width="20%">
					<bean:message key="lable.xiaoqu" />
				</th>
				<td>
					<logic:notEmpty name="xqdmList">
						<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
							<logic:notEqual name="xqNum" value="0">
								<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
							</logic:notEqual>
							<%if((xqNum.intValue()+1)%5==0){%>
								<% out.print("</br>"); %>
							<%}%> 
						</logic:iterate>
					</logic:notEmpty>
				</td>
			</tr>
		</logic:equal>
		
		<!-- 从属关系3（园区--楼栋） -->
		<logic:equal name="csgx" value="3">
			<!-- 园区 -->
			<tr>
				<th width="20%">
					<bean:message key="lable.yuanqu" />
				</th>
				<td>
					<logic:notEmpty name="yqList">
						<logic:iterate name="yqList" id="yq" indexId="yqNum">
							<logic:notEqual name="yqNum" value="0">
								<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
							</logic:notEqual>
							<%if((yqNum.intValue()+1)%5==0){%>
								<% out.print("</br>"); %>
							<%}%> 
						</logic:iterate>
					</logic:notEmpty>
				</td>
			</tr>
		</logic:equal>
		
		<!-- 楼栋 -->
		<tr>
			<th width="20%">
				<bean:message key="lable.ld" />（寝室数）
			</th>
			<td>
				<p id="p_ld">
					<logic:notEmpty name="ldQsList">
						<%int count=0;%>
						<logic:iterate name="ldQsList" id="ld" indexId="ldNum">
							
						 	<logic:notEqual name="ld" property="num" value="0">
						 	<%count++;%>
							<input type="checkbox" name="ld" value="${ld.lddm }" />${ld.ldmc }【${ld.xbxd }】(${ld.num})
							<%if(count!=0 && count%3==0){%>
								<% out.print("</br>"); %>
							<%}%> 
							</logic:notEqual>
						</logic:iterate>
						<%if(count==0){%>
							所选择的部门暂时没有可供分配的寝室，如需分配寝室，<br/>
							请联系管理员给该<bean:message key="lable.xb" />分配寝室!
						<%}%>
					</logic:notEmpty>
				</p>
			</td>
		</tr>
		<!-- 楼栋 end-->
		
		<!-- 性别 -->
		<tr>
			<th width="20%">
				寝室性别限制
			</th>
			<td>
				<input type="hidden" id="xb" name="xb" value="不限制"/>
				<input type="radio" name="xb" id="xb_no" value="不限制" onclick="$('xb').value = this.value" checked="checked"/>不限制
				<input type="radio" name="xb" id="xb_man" value="男" onclick="$('xb').value = this.value"/>男
				<input type="radio" name="xb" id="xb_woman" value="女" onclick="$('xb').value = this.value"/>女	
			</td>
		</tr>
		<!-- 性别 end-->
		
		<!-- 可否混住 -->
		<tr>
			<th width="20%">
				部门可否混住
			</th>
			<td>
				<input type="hidden" id="kfhz" name="kfhz" value="不限制"/>
				<input type="radio" name="kfhz" id="kfhz_no" value="不限制" onclick="$('kfhz').value = this.value" checked="checked"/>不限制
				<input type="radio" name="kfhz" id="kfhz_no" value="不可" onclick="$('kfhz').value = this.value"/>不可
				<input type="radio" name="kfhz" id="kfhz_yes" value="可以" onclick="$('kfhz').value = this.value"/>可以	
			</td>
		</tr>
		<!-- 可否混住 end-->
		
	</tbody>
</table>