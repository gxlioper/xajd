//����ѧ����Ϣ
function getStuInfo(){
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(jQuery("#xh").val());
	var url="xpj_pjjg.do?method=addPjxmjg";
	jQuery.getJSON(url,parameter,
			function(data){
				if(data!=null){
					jQuery("#xm").html(data.xm);
					jQuery("#nj").html(data.nj);
					jQuery("#xymc").html(data.xymc);
					jQuery("#zymc").html(data.zymc);
					jQuery("#bjmc").html(data.bjmc);
					jQuery("#zzmm").html(data.ldmc);
					jQuery("#mz").html(data.qsh);
					jQuery("#yhmc").html(data.cwh);
					jQuery("#yhkh").html(data.qsdh);
					changeTqs();
				}
			}
		);
	jQuery.ajaxSetup({async:true});
}

//����
function saveForm(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var xxdm=jQuery("#xxdm").val();
	var sqly=jQuery("#sqly").val();
	if("" == sqsj){
		showAlert("����ʱ�䲻��Ϊ��");
		return false;
	}
	if("" == xn){
		showAlert("ѧ�겻��Ϊ��");
		return false;
	}
	if("" == xn||"" == lxdm|| "" == xzdm|| "" == xmmc){
		showAlert("�뽫������Ϣ���д�*�ŵ���д����");
		return false;
	}
	if("12056"==xxdm&&""==sqly){
		showAlert("�뽫������Ϣ���д�*�ŵ���д����");
		return false;
	}
	if (jQuery("#sqly").val().length > 500){
		showAlert("���������������Ϊ500�����Ѿ���������ȷ�ϣ���");
		return false;
	}
	 var url = "xpj_pjjg.do?method=addPjxmjg&type=save";
      ajaxSubFormWithFun("pjxmjgForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  var msg = "��ѧ����"+jQuery.trim(xn)+"ѧ�꣬";
    		  if (jQuery("#xq option:selected").text() != ""){
    			  msg += jQuery("#xq option:selected").text() + "ѧ�ڣ�";
    		  }
    		  msg += "�ѻ��"+jQuery.trim(xmmc)+"��";
    		  showAlert(msg);
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
}


//����
function add(){
	var url = "xpj_pjjg.do?method=addPjxmjg&xzdm="+jQuery("#xzdm").val();
	var title = "�������������Ϣ";
	showDialog(title,800,540,url);
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'xpj_pjjg.do?method=updatePjxmjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
		var title = "�޸�������Ϣ";
		showDialog(title,800,540,url);
	}
}

//ɾ����¼
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpj_pjjg.do?method=delPjxmjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//����
function exportConfig(){
	var DCCLBH='pj_pjjg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='pj_pjjg.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "xpj_pjjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��������鿴

function knsView(id,xh){
	showDialog("���������ѯ",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//�߼���ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function hjwhDr(){
	var pjzq=jQuery("#pjzq").val();
	if(""==pjzq){
	toImportDataNew("IMPORT_HJWHDR_11318_XN");
		
	}else{
	toImportDataNew("IMPORT_HJWHDR_11318_XQ");
	}
	return false;
}
