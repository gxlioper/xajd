<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript" defer="defer">
//���У��԰��
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
	
	//����԰��
	if($("p_yq") && lx == "xq"){
		displayNewYq(xqdm);
	}
	
	//����¥��
	if($("p_ld")){
		displayNewLd(xqdm,yqdm,"checkbox");
	}
}

//����԰��
function displayNewYq(xqdm){

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	$("p_yq").innerHTML = "";
	var divHtml = "";
	
	dwr.engine.setAsync(false);
	
	//����У��ȡ��԰��
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

//����¥��
function displayNewLd(xqdm,yqdm,lx){

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	$("p_ld").innerHTML = "";
	var divHtml = "";
	
	dwr.engine.setAsync(false);

	//����У��԰��ȡ����¥��
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
					divHtml += "��";
					divHtml += data[i].xbxd;
					divHtml += "��";
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
				<span>�������ѡ��(¥������������Ϊδ����������)</span>
			</th>
		</tr>
	</thead>
	<tbody>					
		<!-- ������ϵ1��У��--԰��--¥���� -->
		<logic:equal name="csgx" value="1">
			<!-- У�� -->
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
			<!-- ԰�� -->
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
		
		<!-- ������ϵ2��У��--¥���� -->
		<logic:equal name="csgx" value="2">
			<!-- У�� -->
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
		
		<!-- ������ϵ3��԰��--¥���� -->
		<logic:equal name="csgx" value="3">
			<!-- ԰�� -->
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
		
		<!-- ¥�� -->
		<tr>
			<th width="20%">
				<bean:message key="lable.ld" />����������
			</th>
			<td>
				<p id="p_ld">
					<logic:notEmpty name="ldQsList">
						<%int count=0;%>
						<logic:iterate name="ldQsList" id="ld" indexId="ldNum">
							
						 	<logic:notEqual name="ld" property="num" value="0">
						 	<%count++;%>
							<input type="checkbox" name="ld" value="${ld.lddm }" />${ld.ldmc }��${ld.xbxd }��(${ld.num})
							<%if(count!=0 && count%3==0){%>
								<% out.print("</br>"); %>
							<%}%> 
							</logic:notEqual>
						</logic:iterate>
						<%if(count==0){%>
							��ѡ��Ĳ�����ʱû�пɹ���������ң�����������ң�<br/>
							����ϵ����Ա����<bean:message key="lable.xb" />��������!
						<%}%>
					</logic:notEmpty>
				</p>
			</td>
		</tr>
		<!-- ¥�� end-->
		
		<!-- �Ա� -->
		<tr>
			<th width="20%">
				�����Ա�����
			</th>
			<td>
				<input type="hidden" id="xb" name="xb" value="������"/>
				<input type="radio" name="xb" id="xb_no" value="������" onclick="$('xb').value = this.value" checked="checked"/>������
				<input type="radio" name="xb" id="xb_man" value="��" onclick="$('xb').value = this.value"/>��
				<input type="radio" name="xb" id="xb_woman" value="Ů" onclick="$('xb').value = this.value"/>Ů	
			</td>
		</tr>
		<!-- �Ա� end-->
		
		<!-- �ɷ��ס -->
		<tr>
			<th width="20%">
				���ſɷ��ס
			</th>
			<td>
				<input type="hidden" id="kfhz" name="kfhz" value="������"/>
				<input type="radio" name="kfhz" id="kfhz_no" value="������" onclick="$('kfhz').value = this.value" checked="checked"/>������
				<input type="radio" name="kfhz" id="kfhz_no" value="����" onclick="$('kfhz').value = this.value"/>����
				<input type="radio" name="kfhz" id="kfhz_yes" value="����" onclick="$('kfhz').value = this.value"/>����	
			</td>
		</tr>
		<!-- �ɷ��ס end-->
		
	</tbody>
</table>