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

/**
 * ���ӻ�������
 * @return
 */
function zjJcsj(){
	var url = "jcsj.do?method=jcsjAdd";
	var title = "���ӻ�������";
	showDialog(title,380,280,url);
}

 /**
  * �޸Ļ�������
  * @return
  */
 function xgJcsj(){
 	var rows = jQuery("#dataTable").getSeletRow();
 	if (rows.length != 1){
 		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
 		return false;
 	} else {

		var xzflg = jQuery("#xzflg").val();
 		var url = "jcsj.do?method=jcsjUpd&xzflg="+xzflg;
		if("" ==xzflg || "0" ==xzflg){
			url += "&xydm="+rows[0]["xydm"];
		}else if("1" ==xzflg){
			url += "&zydm="+rows[0]["zydm"];
		}else if("2" ==xzflg){
			url += "&bjdm="+rows[0]["bjdm"];
		}
 		var title = "�޸Ļ�������";
 		showDialog(title,380,280,url);
 	}
 }

  /**
   * ɾ��
   * @return
   */
  function scJcsj(){
	var rows = jQuery("#dataTable").getSeletRow();
  	if (rows.length == 0){
  		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
  	} else {

		var xzflg = jQuery("#xzflg").val();
			
  		for(var i=0;i<rows.length;i++){
  			
  			if("" ==xzflg || "0" ==xzflg ){
  				
  				if(rows[0]["zys"]>0){
  					showAlert("��"+jQuery("#xbmc").val()+"�ѷ���רҵ������ɾ����");
  	  				return false;
  				}
  			}else if("1" ==xzflg){
  				if(rows[0]["bjs"]>0){
  					showAlert("��רҵ�ѷ���༶������ɾ����");
  	  				return false;
  				}
  			}else if("2" ==xzflg){

  				if(rows[0]["xss"]>0){
  					showAlert("�ð༶�ѷ���ѧ��������ɾ����");
  	  				return false;
  				}
  			}
  		}
  		var ids = jQuery("#dataTable").getSeletIds();
  		
  		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
  				jQuery.post("jcsj.do?method=jcsjDel",{xzflg:xzflg,values:ids.toString()},function(data){
  					showAlertDivLayer(data["message"]);
  					jQuery("#dataTable").reloadGrid();
  				},'json');
  		}});
  	}
  }

  	function exportConfig() {
		var xzflg = jQuery("#xzflg").val();
		var path = "jcsjcl.do?xzflg=0";
		if("1" ==xzflg){					
			path = "jcsjcl.do?xzflg=1";
		}else if("2" ==xzflg){	
			path = "jcsjcl.do?xzflg=2";
		}
		customExport(path, exportData,680,500);
	}
	// ��������
	function exportData() {

		var xzflg = jQuery("#xzflg").val();
		var path = "jcsjcl.do?xzflg=0";
		if("1" ==xzflg){					
			path = "jcsjcl.do?xzflg=1";
		}else if("2" ==xzflg){	
			path = "jcsjcl.do?xzflg=2";
		}
				
		setSearchTj();//���ø߼���ѯ����
		var url = "jcsj.do?method=exportData&dcclbh="+path;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	
