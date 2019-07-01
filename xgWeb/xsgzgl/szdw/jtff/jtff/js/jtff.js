function searchRs(){
	var map = getSuperSearch();
	var jtlb = jQuery("#jtlb").val();
	if (null != jtlb && jtlb != "") {
		map["jtlb"] = jtlb;
	}else{
		map["jtlb"] = "zc";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function scjt(){
	var url = "jtff_jtff.do?method=jtsc";
	var title = "��������";
	showDialog(title, 770, 450, url);
}

function exportConfig(){
	
}

function savejtsc(){
	if(jQuery.trim(jQuery("#ffny").val()) == ""){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������")
		return false;
	}
	if(!checkIsNotNull()){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������")
		return false;
	}
	var url = "jtff_jtff.do?method=savejtsc";
	ajaxSubFormWithFun("JtffForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
  		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
  	 }else{
  		 showAlert(data["message"]);
  		}
		});
}

function getData(){
	var ffny = jQuery.trim(jQuery("#ffny").val());
	var exist = "";
	if(ffny != ""){
		var rs = null;
		var url = "jtff_jtff.do?method=getData";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'ffny='+ffny,
		async: false,
		success:function(result){
			if(result['zrs']=='0'){
				showAlert("�޽�������ά����Ϣ!",{},{"clkFun":function(){
		    		jQuery("#jtsc").hide();
		    		jQuery("#zrs").text(result['zrs']);
		    		jQuery("#zje").text(result['zje']);
					return false;
				}});
				
			}else{
				jQuery("#jtsc").text("���ɽ���");
				exist = result['exist'];
				var datalist = result['datalist'];
				var html = "";
				html +=	"<tr>";
				html +=	"<th>���</th>";
			    html +=	"<th>ְ����</th>";
			    html +=	"<th>����</th>";
			    html +=	"<th>�Ա�</th>";
			    html +=	"<th>��������</th>";
			    html +=	"<th>��λ</th>";
			    html +=	"<th>����ѧ����</th>";
			    html +=	"<th>�����·�</th>";
			    html +=	"<th><font color='red'>*</font>���(Ԫ)</th>";
			    html +=	"</tr>";
				for(var i=0;i<datalist.length;i++){
					html +="<tr>";
					html +="<td>"+datalist[i]['rownum']+"</td>";
					html +="<td>"+datalist[i]['zgh'];
					html +=" <input type='hidden' name='zgh' value='"+datalist[i]['zgh']+"'/>";
					html +="</td>";
					html +="<td>"+datalist[i]['xm']+"</td>";
					html +="<td>"+datalist[i]['xb']+"</td>";
					html +="<td>"+datalist[i]['bmmc']+"</td>";
					html +="<td>"+datalist[i]['gw']+"</td>";
					html +="<td>"+datalist[i]['dbrs']+"<input name='dbrs' type='hidden' value ='"+datalist[i]['dbrs']+"' />"+"</td>";
					html +="<td>"+datalist[i]['ffny']+"</td>";
					html +="<td>";
					if(datalist[i]['jtlb'] == 'zc'){
						html +="<input type='text' style='width:80px;' name='ffje' value='"+datalist[i]['xsje']+"' onkeyup='checkMoneyBykeyUp(this);' maxlength='10'/>";
					}else{
						html +=datalist[i]['xsje'];
						html +="<input type='hidden' style='width:80px;' name='ffje' value='"+datalist[i]['xsje']+"'/>";
					}
					
					html +="<input name='gw' type='hidden' value ='"+datalist[i]['gw']+"' />";
					html +="<input name='jsje' type='hidden' value ='"+datalist[i]['jsje']+"' />";
					html +="</td>";
				    html +="</tr>";
				    jQuery("#thnr").html(html);
				    jQuery("#zrs").text(result['zrs']);
		    		jQuery("#zje").text(result['zje']);
				}
			}
		 }
	    });
		  
	}
	if(exist == '1'){
		showAlert("���·��ż�¼�����ɣ���ע��!",{},{"clkFun":function(){
			jQuery("#jtsc").text("��������");
			return false;
		}});
	}
}

function checkIsNotNull(){
    var objs = jQuery("input[name='ffje']");
    var flag = true;
    for(var i=0;i<objs.length;i++){
    if(jQuery.trim(objs[i].value) == ""){
      flag = false;
     break;
     }
   }
   return flag;

}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jtscck(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//���������鿴
function jtscck(id,zgh){
	showDialog("��ʦ������Ϣ�鿴", 650, 450, "jtff_jtff.do?method=jtscCk&id="
			+ id + "&zgh=" + zgh);
}


var DCCLBH = "szdw_jtff_jtff.do";
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH,ExportData);
}

//��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var jtlb = "";
	var url = "jtff_jtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



