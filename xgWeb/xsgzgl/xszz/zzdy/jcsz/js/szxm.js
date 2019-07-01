var checkId="xn-xmdm-ffys-ffksyf-ffjsyf";
var gridSetting = {
	caption : "������Ŀ�б�",
	pager : "pager",
	url : "xszz_zzdyjcsz.do?method=getSzxmList&type=query",
	colList : [ 
	    	   {label : 'szid',name : 'szid',index : 'szid',key : true,hidden:true},
	    	   {label : 'xmdm',name : 'xmdm',index : 'xmdm',hidden:true},
	    	   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '30%'},
	    	   {label : 'ѧ��',name : 'xn',index : 'xn',width : '20%'},
	    	   {label : 'ѧ��',name : 'xqmc',index : 'xqmc',width : '10%'},
	    	   {label : '��������',name : 'ffys',index : 'ffys',width : '20%'},
	    	   {label : '���ſ�ʼ����',name : 'ffksyf',index : 'ffksyf',width : '10%'},
	    	   {label : '���Ž�������',name : 'ffjsyf',index : 'ffjsyf',width : '10%'},
	    	   {label : '��������',name : 'isopen',index : 'isopen',width : '10%',formatter : setXmsz}
	    	   ],
	sortname : "xn",
	sortorder : "desc"
}

function dcmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name'>" + cellValue + "</a>";
}
function setXmsz(cellValue, rowObject) {
	var szid = rowObject.szid;
	var value = "δ����";
	var status = '0';
	var color;
	if ("1"==cellValue){
		value = "�ѿ���";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + szid
			+ "\");' >" + value + "</a>";
	return value;
}
/*
 * ��Ŀ��������
 */
function xmsz(szid) {
	if (szid == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		szid = rows[0]["szid"];
	}
	var url = 'xszz_zzdyjcsz.do?method=xmsz&szid=' + szid;
	var title = "��Ŀ����";
	showDialog(title, 520, 350, url);
}
jQuery(function() {
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

function add() {
	var url = "xszz_zzdyjcsz.do?method=addSzxm";
	var title = "������Ŀ����";
	showDialog(title, 470, 350, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xszz_zzdyjcsz.do?method=updateSzxm&szid=' + rows[0]["szid"];
		var title = "�޸���Ŀ����";
		showDialog(title, 470, 350, url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_zzdyjcsz.do?method=delSzxm", {
					values : ids.toString()
				}, function(data) {
					if("true"==data["message"]){
						showAlertDivLayer("����Ŀ��ͬ������������������ɾ����");
						return;
					}
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

function changeZq(){
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var url="xszz_zzdyjcsz.do?method=getXmList&xn="+xn+"&xq="+xq;
	jQuery.post(url, {}, function(data) {
		var pffw="<select name='xmdm' id='xmdm' style='width:120px'>";
		for(var i = 0; i < data.length; i++){
			pffw +="<option value='"+data[i].xmdm+"'>"+data[i].xmmc+"</option>";
		}
		pffw += "</select>"
		jQuery(".formlist").find("tr[name='xmmcTr']").find("td").html(pffw);
		
	}, 'json');
}
function saveForm(type){	  
	 if(!checkNotNull(checkId)){
		  showAlert("�뽫��������д������");
			return false;
	  }
	 var ffjsyf = jQuery("#ffjsyf").val();
	 var ffksyf = jQuery("#ffksyf").val();
	 var ffys = jQuery("#ffys").val();
	 var len=getMonthNumber(ffksyf,ffjsyf);
	 if(ffys!=len+1){
		 showAlert("����������ʵ�ʷ������²�����");
			return false; 
	 }
     var url = "xszz_zzdyjcsz.do?method=saveSzxm&type="+type;
      ajaxSubFormWithFun("ZzdyJcszForm",url,function(data){
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
function getMonthNumber(date1,date2){
	  var year1 =  date1.substr(0,4);
	  var year2 =  date2.substr(0,4); 
	  var month1 = date1.substr(4,2);
	  var month2 = date2.substr(4,2);
	  
	  var len=(year2-year1)*12+(month2-month1);
	  
	  return len;

	 }
