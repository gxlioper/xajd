/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


// �鿴ѧ���춯��Ϣ
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydXsInfoCk(\""+row["xjydsqid"]+"\")'>"+cellValue+"</a>";
}


//�鿴ѧ���춯��Ϣ
function xjydXsInfoCk(xjydsqid){
	showDialog("�鿴ѧ��ѧ���춯��Ϣ",800, 500,"xjydsq.do?method=xjydck&xjydsqid="+xjydsqid,null);
}

//����
function add() {
		var url ="xjydsq.do?method=xjydsqAdd";
		var title = "ѧ���춯����";
		showDialog(title, 830, 500, url);
}

//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}  else{
		var shzt = rows[0]["shzt"];
		if(shzt!="0" && shzt!="3"){
			showAlert("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		}
		showDialog("�޸�ѧ���춯��Ϣ",830, 500,'xjydsq.do?method=xjydsqUpd&xjydsqid='+rows[0]["xjydsqid"]);
	}
}

// ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();

		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		
		confirmInfo("��ȷ��Ҫɾ��<font color='red'>"+ids.length +"</font>����¼��?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydsq.do?method=xjydsqDel",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}

/**
 * �ύ
 * @return
 */
function pltj(){

	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlert("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var shzt = rows[0]["shzt"];
		if(shzt!="0" && shzt!="3"){
			showAlertDivLayer("��ѡ��δ�ύ���˻صļ�¼��");
			return false;
		}
		confirmInfo("��ȷ��Ҫ�ύ<font color='red'>"+ids.length +"</font>����¼��?",function(ty){
			if(ty=="ok"){
				
				if(shzt!="3"){
					// ��֤�Ƿ���ύ
					jQuery.post("xjydsq.do?method=checkSfktj", {
						ydlbdm:rows[0]["ydlbdm"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("��������춯�ѹر����룬�޷��ύ����������ϵ����Ա��");
							return false;
						}else{
							// 
							jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=5",{values:ids.toString()},function(data){
								showAlert(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});
					
				}else{

					// �ύ
					jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=5",{values:ids.toString()},function(data){
						showAlert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
		});
	}
}

/**
 * ����
 * @return
 */
function plqxtj(){

	var ids = jQuery("#dataTable").getSeletIds();
	var shlcidnew = new Array();
	
	if (ids.length == 0){
		showAlert("��ѡ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			var shzt = rows[i]["shzt"];
			if(shzt!="5"){
				showAlert("��ѡ������еļ�¼��");
				return false;
			}
		}
		
		jQuery.each(rows,function(i,row){
			if(row["shzt"]=="3"){
				shlcidnew.push(row["shlcidnew"]);
			}else{
				shlcidnew.push(row["splcid"]);
			}
		})
		
		
		confirmInfo("��ȷ��Ҫ����<font color='red'>"+ids.length +"</font>����¼��?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydsq.do?method=xjydsqPltj&shzt=0",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}


//�������鿴
 function rcxwshLcinfo(){
  	var rows = jQuery("#dataTable").getSeletRow();
  	if (rows.length != 1){
  		showAlert("��ѡ��һ����¼��");
  	} else {

		var rows = jQuery("#dataTable").getSeletRow();
		var shzt = rows[0]["shzt"];
		if(shzt=="0"){
			showAlert("��ѡ�����ύ�ļ�¼��");
			return false;
		}
  		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['xjydsqid']+"&splc="+rows[0]['splcid']);
  	}
 }
	  

//��ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("xjyd_xjydsq.do", exportData,690,500);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xjydsq.do?method=exportData&dcclbh=xjyd_xjydsq.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��ʾѧ���Ͱ༶��Ϣ
 * @return
 */
function initShow(){
	var xjlbdm = jQuery("#ydlbdm").val();
	if(xjlbdm == ""){
		jQuery("#xjlbmc").html("");
		jQuery("#sfyxj").html("");
		jQuery("#sfzx").html("");
		jQuery("#tzbj").hide();
		
	}else{
		jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
			if(data != null){
				jQuery("#xjlbmc").html(data["xjlbmc"]);
				jQuery("#sfyxj").html(data["sfzxmc"]);
				jQuery("#sfzx").html(data["sfzxmc"]);
				if(data["sftjbj"]=='0'){
					jQuery("#tzbj").show();
				}else{
					jQuery("#tzbj").hide();
				}
			}
		},'json');
	}
}
