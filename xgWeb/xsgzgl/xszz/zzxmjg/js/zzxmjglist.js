var DCCLBH = "xszz_zzxmjg_cx.do";//dcclbh,�������ܱ��        


function gridSetting(){
	
	var colList = [
	   {label:'guid',name:'guid', index: 'guid',width:'2%',key:true,hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'7%'}
	];
	
	var xxdm = jQuery("#xxdm").val();
		
	if(xxdm=="10344"){
		colList.push({label:'���֤��',name:'sfzh', index:'sfzh',width:'20'});
	}else{
		colList.push({label:'�Ա�',name:'xb', index: 'xb',width:'5%'});
		colList.push({label:'�꼶',name:'nj', index: 'nj',width:'5%'});
	}
   if(xxdm=="11483"){
	   colList.push({label:'���ڴ��',name:'ddmc', index: 'ddmc',width:'5%'});
	}
    colList.push({label:'��Ժ',name:'symc', index: 'sydm',width:'15%'});
	colList.push({label:'�����༶',name:'bjmc', index: 'bjdm',width:'15%'});
    colList.push({label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'});
	colList.push({label:'��������',name:'sqzq', index: 'sqzq',width:'12%'});
	if(xxdm=="11799"){
		colList.push({label:'���ͨ��ʱ��',name:'lastshsj', index: 'lastshsj',width:'12%'});
	}else{
		colList.push({label:'��Ŀ��������',name:'pdzq', index: 'pdzq',width:'12%'});
	}
	colList.push({label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'15%'});
	colList.push({label:'���',name:'je', index: 'je',width:'6%'});
	colList.push({label:'sjly',name:'sjly', index: 'sjly',hidden:true});
	
	return {
		caption:"��������б�",
		pager:"pager",
		params:getSuperSearch(),
		url:"xszz_zzxmjg.do?method=getZzxmjgList&type=query",
		colList:colList,
		sortname: "xn,xq,sqsj",
	 	sortorder: "desc"
	};
}

   /**
* ������Ŀ�����������--�鿴
* @param xh
* @return
*/
   function zzjgView(guid,xh){
		showDialog("���������ѯ",550,435,"xszz_zzxmjg.do?method=zzxmjgView&guid="+guid+"&xh="+xh);
   }
   
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='zzjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting());
});

function add(){
	var url = "xszz_zzxmjg.do?method=addZzxmjg";
	var title = "����������Ϣ";
	showDialog(title,800,500,url);
}
function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
	   if(xxdm!="11799"){
		   if(rows[0]["sjly"]=='1'){
			   showAlertDivLayer("��ѡ��ļ�¼Ϊ��˹����ļ�¼�������޸ģ�������ѡ��");
			   return false;
		   }
		}
		
		var url = 'xszz_zzxmjg.do?method=updateZzxmjg&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
		var title = "�޸�������Ϣ";
		showDialog(title,800,500,url);
	}
	
}
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("��ѡ�ļ�¼�а�����˹����ļ�¼������ɾ����������ѡ��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xszz_zzxmjg.do?method=delZzxmjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//��ӡ����
function printXmsq(url){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
	} else {

		var url= url+"&guid="+rows[0]["guid"]+"&xh="+rows[0]["xh"]+"&xmmc="+rows[0]["xmmc"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]
		//�жϸ���Ŀ�Ƿ��б���
        jQuery.post("xszz_xmwh.do?method=getXszzdm",{"xmmc":rows[0]["xmmc"],"xn":rows[0]["xn"],"xq":rows[0]["xq"]},function(data){
			if(data["message"] != null && data["message"]!=""){
				window.open(url);
			}else{
				showAlertDivLayer("����Ŀ��δ���ñ�������ϵ����Ա��");
				return false;
			}
		},'json');
    }
}			


// �������� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е����ĺ���
	customExport(DCCLBH, exportData);
}

//�������� ����
function exportzzHzData() {
	//DCCLBH�������ܱ��,ִ�е����ĺ���
	customExport("xszz_zzxmjg_hzdc.do", exportHzData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzxmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��������
function exportHzData() {
	//setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzxmjg.do?method=exportHzData&dcclbh=xszz_zzxmjg_hzdc";//dcclbh,�������ܱ��
	//url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡword
function getWord(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����¼��");
	 } else if (rows.length > 1){
		 var guid = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=downloadZip";
//		var ids = jQuery("#dataTable").getSeletIds();
//		url += "&value="+ids;
//		window.open(url);
		post(url, {value:guid});
	 } else {
		var url="xszz_zzxmjg.do?method=downloadWord";		
		url += "&guid="+rows[0]["guid"];
		window.open(url);
     }
}

function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)         
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
} 

//���˸���
function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("����������ѧ���������������ܸ��ơ�",{},{"clkFun":function(){
		}});
		return false;
	}

	var url ="xszz_zzxmjg.do?method=zzjgCopy";
	showDialog("���˸���", 400, 200, url);
}

function saveCopy(){
	var lyxn=jQuery("#lyxn").val();
	var mbxn=jQuery("#mbxn").val();
	jQuery.post("xszz_zzxmjg.do?method=saveCopy", {
		lyxn:lyxn,
		mbxn:mbxn
	}, function(data) {
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	}, 'json');
}

//ɽ��������ҽְҵѧԺ�������ѧ����ܱ�
function getshzxjHzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_shzxjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//ɽ��������ҽְҵѧԺ��������־��ѧ����ܱ�
function getgjlzjHzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjlzjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//ɽ��������ҽְҵѧԺ��������ѧ��ѧ����ܱ�
function getSdxm_gjzxjhzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjzxjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//ɽ��������ҽְҵѧԺ��������־������ģ��excel��
function getSdxm_gjlzjhzmbexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjlzjhzmbexcel&value="+ids;
		window.open(url);
	 } 
}

//�㽭��ѧ�������°棬�˷���Ҳ����Ϊͨ�õ���
function zzjgdr(){
	toImportDataNew("IMPORT_N720305_ZZJG");
	return false;
}

//�ൺ�Ƶ����ְҵ����ѧԺ
function qdjddr(){
	showDialog("����", 500, 200, "xszz_zzxmjg.do?method=drForQdjd");
}

//�����ൺ�Ƶ����ְҵ����ѧԺ����ģ��
function downloadDrmb(){
	window.open("xszz_zzxmjg.do?method=downloadTemplate");
}

//���ļ����Ƹ�ֵ��input��
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

//��ȡinput file������
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//�ļ����Ϳ���
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ���");
		return false;
	}
}

//�ൺ�Ƶ����ְҵ����ѧԺ���Ի�����
function Dr() {
	var url = "xszz_zzxmjg.do?method=saveDrForQdjd";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("zzxmjgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
		 }else{  			   
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["message"] == "����ʧ��,����ϸ�˶ԡ������¼.xls����"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#sl").html("��������"+data["cgs"]+"�����������"+data["cws"]+"��");
   				    		  jQuery("#errora").attr("data_file","xszz_zzxmjg.do?method=downloadFileError&filename="+data["gid"]);
   				    	  }
   				      }else{
   				    	jQuery("#errortr").hide();
   				    	jQuery("#errora").attr("data_file","");
   				      }
   				      jQuery("#drmb").val("");
				}});
   		}
	});
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}

//�ر�ˢ��
function gb(){
	if (parent.window){
		refershParent();
	}
}

// ���ҽ�ѧ�𵼳�
function gjjxjdc() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjjxz.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//������ѧ�𵼳�
function gjzxjdc() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjzxz.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//������־�𵼳�
function gjlzjdc() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjlzj.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}