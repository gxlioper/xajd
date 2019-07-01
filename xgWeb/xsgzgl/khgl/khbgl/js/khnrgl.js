var checkId="khnr-fzzdf-pflx";


jQuery(function(){
var autoSetting1 = {
		dataTable:"xg_khgl_tk_zbx",
		dataField:"yjzb",
		dataFieldKey:"",
		dataFieldKeyId:"",
		scrollHeight:135										
	}
var autoSetting2 = {
		dataTable:"xg_khgl_tk_zbx",
		dataField:"ejzb",
		dataFieldKey:"",
		dataFieldKeyId:"",
		scrollHeight:135										
	}

	// ģ����������ָ������
	jQuery("#yjzb").setAutocomplete(autoSetting1);
	jQuery("#ejzb").setAutocomplete(autoSetting2);
	fzlxChange();
	
	jQuery(".ui_close").bind("click",function(){
		if (parent.window){
			refershParent();
		}
	});
});

//��ֵ�����л��¼�
function fzlxChange(){
	var fzlx = jQuery("[name='fzlx']:checked").val();
	if("1"==fzlx){
		jQuery("#fzzgf").val("");
		jQuery("#fzlxSpan").css("display","none");
	}else{
		jQuery("#fzlxSpan").css("display","");
	}
}
function searchRs() {
	var map = {};
	jQuery("#dataTable").reloadGrid(map);
}
function add() {
	//�Ƿ������֣������ֿ��˱������ӡ�ɾ�����ܲ������޸ģ�ֻ�ܶ�һ��ָ�ꡢ����ָ�ꡢ���������޸ģ�
	var sfypf = jQuery("#sfypf").val(); 
	//1:������
	if("1"==sfypf){
		showAlertDivLayer("�ÿ��˱������û����֣����������ӿ������ݣ�");
		return false;
	}
	var khbid=jQuery("#khbid").val();
	var url = "khglKhnrgl.do?method=addKhnr&khbid="+khbid;
	var title = "���ӿ�������";
	showDialog(title, 700, 350, url);
}

function update() {
	var sfypf = jQuery("#sfypf").val(); 
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	}
	var url = 'khglKhnrgl.do?method=updateKhnr&zbmxid=' + rows[0]["zbmxid"]+"&sfypf="+sfypf;
	var title = "�޸Ŀ�������";
	showDialog(title, 700, 350, url);
	
}

function del() {
	//�Ƿ������֣������ֿ��˱������ӡ�ɾ�����ܲ������޸ģ�ֻ�ܶ�һ��ָ�ꡢ����ָ�ꡢ���������޸ģ�
	var sfypf = jQuery("#sfypf").val(); 
	//1:������
	if("1"==sfypf){
		showAlertDivLayer("�ÿ��˱������û����֣�������ɾ���������ݣ�");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhnrgl.do?method=delKhnr", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function saveForm(type){	  
	var fzlx = jQuery("[name='fzlx']:checked").val();
	var fzzgf=jQuery("#fzzgf").val();
	var fzzdf=jQuery("#fzzdf").val();
	  if(!checkNotNull(checkId)){
		  showAlert("�뽫��������д������");
			return false;
	  }
	  if("1"!=fzlx&&(null==fzzdf||""==fzzdf)){
		  showAlert("�뽫��������д������");
			return false;
	  }
	  if(null!=fzzgf&&""!=fzzgf&&(parseFloat(fzzdf)>parseFloat(fzzgf))){
		  showAlert("��ֵ��ͷֲ��ܴ��ڷ�ֵ��߷֣�");
			return false;
	  }
    var url = "khglKhnrgl.do?method=saveKhnr&type="+type;
     ajaxSubFormWithFun("KhnrglForm",url,function(data){
   	 if(data["message"]=="����ɹ���"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   			var api = frameElement.api;
			var gotoPath="khglKhnrgl.do?method=getKhnrglList&khbid="+jQuery("#khbid").val();
			if (api){
				if (api.get('childDialog')){
					api.reload(api.get('parentDialog') ,gotoPath);
				} else {
					var W = api.opener;
					W.location=gotoPath;			
				}
			} else if (parent.window){
				parent.window.document.location=gotoPath;						
			}
			
			iFClose();
   			}});
   	 }else{
   		 showAlert(data["message"]);
   		 
   	 }
		});
 }
//����
function dr() {
	var sfypf = jQuery("#sfypf").val(); 
	if("1"==sfypf){
		showAlertDivLayer("�ÿ��˱������û����֣��������뿼�����ݣ�");
		return false;
	}
		toImportDataNew("IMPORT_N930101_KHNRDR");
}