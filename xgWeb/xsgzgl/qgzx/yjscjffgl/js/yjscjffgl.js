var ids="xh-xm-xn-yrbm-gwmc-ffny-gs-je-gznr";
function getSqlTj(){
	var xn = jQuery("#xn").val();
	var yrdwdm = jQuery("#yrbm").val();
	var sqlTj = " and xn = '"+xn+"' ";
	sqlTj+=" and yrdwdm = '"+yrdwdm+"' ";
	return sqlTj;
}
function changeGwmc(){
	// ����
	jQuery("#gwmc").val("");
	var autoSetting = {
		dataTable:"xg_qgzx_gwxxb",
		dataField:"gwmc",
		sqlTj: getSqlTj,
		scrollHeight:135										
	}
	// ģ��������������Ŀ���ơ�
	jQuery("#gwmc").setAutocomplete(autoSetting);
}
//�����𷢷ű�
function saveForm(method){
	if(checkNull(ids)&&checkTextAreaLength("gznr","��������","500")){
	var ffny=jQuery("#ffny").val();
	var ffje=jQuery("#je").val();
	var yrdwdm=jQuery("#yrbm").val();
	var oldyrbm=jQuery("#oldyrbm").val();
	var oldje=jQuery("#oldje").val();
	//��֤�Ƿ񷢷Ź�����
	var guid=jQuery("#guid").val();
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	var yrdwdm=jQuery("#yrbm").val();
	var gwmc=jQuery("#gwmc").val();
	var ffny=jQuery("#ffny").val();
	if(!checkFfxx(guid,xh,xn,yrdwdm,gwmc,ffny)){
		return false;
	}
	var url = "qgzxJfglYjscjff.do?method="+method+"&type=save";
	ajaxSubFormWithFun("YjsCjffForm",url,function(data){
		alertInfo(data["message"],function(ty){
			if(ty=="ok"){
				//window.parent.searchRs();
				var api = frameElement.api,W = api.opener;
				W.searchRs();
				btn_close();
			}
		});
	});
}
}
function checkFfxx(guid,xh,xn,yrdwdm,gwmc,ffny){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.post("qgzxJfglYjscjff.do?method=checkFfxx",
			{guid:guid,xh:xh,xn:xn,yrdwdm:yrdwdm,gwmc:gwmc,ffny:ffny},
			function(result){
				if(result!="false"){
					var ts="��ѧ����<font color='blue'>"+xn+"</font>ѧ��<font color='blue'>"+ffny+"</font> ���������Ѵ���ͬ��λ������Ϣ�����飡";
					alertInfo(ts,function(tag){
						if(tag=="ok"){
							isok=false;
						}
					});
				}else{
					isok=true;
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	return isok;
}
function add(){
	showDialog('��ӳ�𷢷���Ϣ', 720, 360, 'qgzxJfglYjscjff.do?method=zjyjsCjff');
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function btn_close(){
	frameElement.api.close();	
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸ĳ�𷢷���Ϣ', 720, 360, 'qgzxJfglYjscjff.do?method=xgyjsCjff&guid='+rows[0]["guid"]);
	}
}
//ɾ����¼
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
			confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
		
				if(ty=="ok"){
					jQuery.post("qgzxJfglYjscjff.do?method=scCjff",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
	}
}


function qgjgwhExportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("qgzx_jfgl_yjscjff.do", qgjgwhExportData);
}
	

	
// ��������
function qgjgwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzxJfglYjscjff.do?method=qgjgwhExportData&dcclbh=" + "qgzx_jfgl_yjscjff.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



