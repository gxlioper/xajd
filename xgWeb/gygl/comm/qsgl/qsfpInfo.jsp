<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script language="javascript" defer="defer">
//���ù�ѡ����ͳ�����
function setBmtjInfo(){
	var num = document.getElementsByName("primarykey_checkVal").length;
	var pk = new Array();
	var n = 0;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("primarykey_checkVal")[i];
		if(obj.checked == true){
			pk[n] = document.getElementsByName("primarykey_checkVal")[i].value;
			n++;
		}
	}
	
	dwr.engine.setAsync(false);
	
	var fpdx = $("fpdx").value;

	var count = 0;
	//���ҷ���ͳ��
	getCommGygl.getBmtjInfoForQsfp(pk,fpdx,function(data){
		if(data !=null && data.length >0){
		
			count = data.length;
			
			var divHtml = "<table class=\"dateline\" align=\"\" width=\"100%\">";
				divHtml+= "<thead>";
				divHtml+= "<tr>";
				if(fpdx == "xy"){
					divHtml+= "<td><bean:message key="lable.xsgzyxpzxy" /></td>";
				}else if(fpdx == "njxy"){
					divHtml+= "<td>�꼶</td>";
					divHtml+= "<td><bean:message key="lable.xb" /></td>";
				}else if(fpdx == "njzy"){
					divHtml+= "<td>�꼶</td>";
					divHtml+= "<td>רҵ</td>";
				}else if(fpdx == "bj"){
					divHtml+= "<td>�༶</td>";
				}
				divHtml+= "<td>������</td>";
				divHtml+= "<td>�ѷ�����������(��λ��)</td>";
				divHtml+= "<td>Ů����</td>";
				divHtml+= "<td>�ѷ���Ů������(��λ��)</td>";
				divHtml+= "</tr>";
				divHtml+= "</thead>";
				
				for(var i=0;i<data.length;i++){
					divHtml+= "<tr>";
					if(fpdx == "xy"){
						divHtml+= "<td>"+data[i].xymc+"</td>";
					}else if(fpdx == "njxy"){
						divHtml+= "<td>"+data[i].nj+"</td>";
						divHtml+= "<td>"+data[i].xymc+"</td>";
					}else if(fpdx == "njzy"){
						divHtml+= "<td>"+data[i].nj+"</td>";
						divHtml+= "<td>"+data[i].zymc+"</td>";
					}else if(fpdx == "bj"){
						divHtml+= "<td>"+data[i].bjmc+"</td>";
					}
					divHtml+= "<td>"+data[i].man+"</td>";
					divHtml+= "<td>"+data[i].manqs+"</td>";
					divHtml+= "<td>"+data[i].woman+"</td>";
					divHtml+= "<td>"+data[i].womanqs+"</td>";
					divHtml+= "</tr>";
				}
			
				divHtml+= "</table>";
			
			$("bmtjDiv").innerHTML = divHtml;
		}
	});
	
	if(count >=5){
		$("bmtjDiv").style.height = "200px";
	}else{
		$("bmtjDiv").style.height = "";
	}
	
	dwr.engine.setAsync(true);
}
</script>
<table class="formlist">
	<thead>
		<tr>
			<th colspan="2">
				<span>�ѷ������</span>
			</th>
		</tr>
	</thead>
	<tbody>					
		
		<!-- ���ҷ������ͳ�� -->
		<tr>
			<th width="20%">
				�������
			</th>
			<td>
				<div id="bmtjDiv" style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
				
				</div>
			</td>
		</tr>
		<!-- ���ҷ������ͳ�� end-->
		
	</tbody>
</table>