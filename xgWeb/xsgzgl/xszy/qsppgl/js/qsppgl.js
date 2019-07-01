function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����֮���ֹ�ƥ��
function sgpp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if(0==rows.length){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
		return false;
	}
	if(1!=rows.length){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}
	var lddm = rows[0]["lddm"];
	var qsh = rows[0]["qsh"];
	var nj = rows[0]["nj"];
	var ldmc = rows[0]["ldmc"];
	var qsxb = rows[0]["qsxb"];
	var rzrs = rows[0]["rzrs"];
		showDialog("�ֹ�ƥ��", 800, 500, "qsppgl.do?method=getSgppXszyList&lddm="
			+ lddm + "&qsh=" + qsh+"&nj="+nj+"&ldmc="+ldmc+"&qsxb="+qsxb+"&rzrs="+rzrs);
}

function zdpp() {
	var qsNum = jQuery("#dataTable tbody tr").length;
	if(0==qsNum){
		showAlertDivLayer("ѧԺδ�������ң����ȷ���������ƥ������֮�ѣ�");
		return false;
	}
	showDialog("�Զ�ƥ��", 550, 200, "qsppgl.do?method=zdpp");
}


//����֮��ƥ�䱣��
function saveSdpp(zgh,nj){ 
	var rows = jQuery("#dataTable").getSeletRow();
	jQuery.post("qsppgl.do?method=saveSdpp", { 
		lddm:rows[0]["lddm"],
		qsh:rows[0]["qsh"],
		zgh : zgh,
		nj : nj
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//�Զ�ƥ�䱣��
function saveZdpp() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	jQuery.post("qsppgl.do?method=saveZdpp", {
		
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//���ƥ����
function qkppjg(){ 
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	if(0==rows.length){
		showAlertDivLayer("��ѡ����Ҫ��յļ�¼��");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		if(null!=rows[i]["qsgxid"]&&""!=rows[i]["qsgxid"]){
			ids.push(rows[i]["qsgxid"]);
		}
	}
	if(null==ids||""==ids){
		showAlertDivLayer("��ѡ����ƥ������֮�ѵ����ң�");
		return false;
	}
	showConfirmDivLayer("�Ƿ�ȷ����չ�ѡ�ļ�¼��", {
		"okFun" : function() {
	jQuery.post("qsppgl.do?method=qkppjg", { 
		id:ids.join(",")
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
	}});
}

//�˻���ѧ԰
function qsppTh(){ 
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	var lddms = new Array();
	var qshs= new Array();
	var ssyxdms = new Array();
	var ppshs="";
	if(0==rows.length){
		showAlertDivLayer("��ѡ����Ҫ�˻صļ�¼��");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		if(null!=rows[i]["qsfpid"]&&""!=rows[i]["qsfpid"]){
			ids.push(rows[i]["qsfpid"]);
			lddms.push(rows[i]["lddm"]);
			qshs.push(rows[i]["qsh"]);
			ssyxdms.push(rows[i]["ssyxdm"]);
		}
		if(null!=rows[i]["qsgxid"]&&""!=rows[i]["qsgxid"]){
			if(i!=0){
				ppshs+=",";
			}
			ppshs+=rows[i]["qsh"];
		}
	}
   
   if(""!=ppshs){
	   showAlertDivLayer("����"+ppshs+"��ƥ������֮�ѣ��޷��˻أ������˻أ��������ƥ������");
		return false;   
   }
	showConfirmDivLayer("ȷ��Ҫ���������˻ص�ѧ԰���˻ص�ѧ԰�󣬽���ѧ԰���·��䵽Ժϵ",{"okFun":function(){
		jQuery.post("qsppgl.do?method=qsppTh",{values:ids.toString(),lddms:lddms.toString(),qshs:qshs.toString(),ssyxdms:ssyxdms.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}