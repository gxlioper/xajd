

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "xnwxdkjm_jg.do?method=add";
	var title = "У����Ϣ�������������";
	showDialog(title, 770, 552, url);
}

//�����޸����뱣��
function saveDkjg(type){
	var ids = "";
	if(type == "update"){
		 ids = "jmbl"+"-"+"sqly";
	}else{
		 ids = "xh"+"-"+"jmbl"+"-"+"sqly";
	}	
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(!checkHaveValue()){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "xnwxdkjm_jg.do?method=saveDksq&type=" + type;
	ajaxSubFormWithFun("XnwxdkjmJgModel", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='DkjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function DkjgView(jgid, xh) {
	showDialog("У����Ϣ����������鿴", 770, 450, "xnwxdkjm_jg.do?method=DksqView&jgid="
			+ jgid + "&xh=" + xh);
}

//ɾ����������
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xnwxdkjm_jg.do?method=delDksq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'xnwxdkjm_jg.do?method=editDksq&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "У����Ϣ���������޸�";
		showDialog(title, 770, 552, url);
	}
}

var DCCLBH = "zxdk_xnwxdkjm_jg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xnwxdkjm_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//�ֻ��绰��֤ 
function phonecheck(obj){
	  var phone = obj.val();
	  var tel = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/;
	  if(phone != "") {
	   if (!tel.exec(phone)) {
		showAlertDivLayer("�绰�����ʽ���ԣ���ȷ��ʽ���£�\n�������룺����-�绰����(��)\n�ֻ����룺13635456878");
		obj.focus();
		return false;
	   }
	  }
}


//У����Ϣ�������������
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xnwxdkjm_jg.do?method=getXnwxdksq";
		url += "&jgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xnwxdkjm_jg.do?method=gettXnwxdkTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

function printHzb(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xnwxdk_jg.do?method=getHzbexcel";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//����
function importConfig(){
	toImportDataNew("IMPORT_XNWXDKJM");
	return false;
}

function exportWXJMHZ(){
	var njobj = jQuery("a[name='tj_nj'][class='selectedValue']");
	var xyobj = jQuery("a[name='a_xy_mc'][class='selectedValue']");
	var xydms = "";
	var njdms = "";
	var xymcs ="";
	if(njobj.length != 1){
	  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ���꼶��");
	  return false;
	}else{
		njdms = jQuery(njobj).attr("id").replace("tj_nj_","");
		
	}

	if(xyobj.length != 1){
	  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧԺ��");
	  return false;
	}else{
      
	     var str = jQuery(xyobj).attr("id");
	      if(str.indexOf("xy_mc_xs_") != -1){
	    	  xydms =str.replace("xy_mc_xs_","");
	    	  xymcs = jQuery(xyobj).text();
	    	 
	    	}else{
	    		xydms =str.replace("xy_mc_yc_","");
	    		 xymcs = jQuery(xyobj).text();
	      }
	      var url = "xnwxdkjm_jg.do?method=getHzbexcel";
		  url += "&xydm=" + xydms+"&nj="+njdms+"&xymc="+xymcs;
		  window.open(url);  
   }
}



