var action="ydsq.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//�������޻��ǵ�����Ϣ��ʾ
function setTstz(){
	var nowSelect=jQuery("#ssydlx").val();
	change(nowSelect);
	jQuery("#ssydlx").bind("click",function(){
		var select=jQuery(this).val();
		change(select);
	});
}
//�鿴ҳ�����õ�ǰ��ʾ
function setTstzFock(){
	var nowSelect=jQuery("#ssydlx").val();
	if(nowSelect=="00"){
		jQuery("#yy").text("����ԭ��");
		jQuery("#sj").text("����ʱ��");
		jQuery("#yxzcwxx").hide();
	}else{
		jQuery("#yy").text("�������ԭ��");
		jQuery("#sj").text("�������ʱ��");	
		alert(jQuery("#cwxx").val());
		if(jQuery("#cwxx").val()!=""){
			showCwxx(jQuery("#cwxx").val());
		}
	}
}
function change(select){
	if(select=="00"){
		jQuery("#ts").show();
		jQuery("#tz").hide();
		jQuery("#rz").hide();
		
		jQuery("#tssave").show();
		jQuery("#tssub").show();
		jQuery("#tzsave").hide();
		jQuery("#tzsub").hide();
		jQuery("#rzsave").hide();
		jQuery("#rzsub").hide();
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
	}else if(select=="03"){
		jQuery("#ts").hide();
		jQuery("#tz").hide();
		jQuery("#rz").show();
		
		jQuery("#tssave").hide();
		jQuery("#tssub").hide();
		jQuery("#tzsave").hide();
		jQuery("#tzsub").hide();
		jQuery("#rzsave").show();
		jQuery("#rzsub").show();
		
		var rzcwxx = jQuery("#rzcwxx").val();
		if(rzcwxx!=""){
			showRzcwxx(rzcwxx);
		}
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
	}else{
		jQuery("#ts").hide();
		jQuery("#rz").hide();
		jQuery("#tz").show();
		
		jQuery("#tssave").hide();
		jQuery("#tssub").hide();
		jQuery("#rzsave").hide();
		jQuery("#rzsub").hide();
		jQuery("#tzsave").show();
		jQuery("#tzsub").show();
		
		var cwxx = jQuery("#cwxx").val();
		if(cwxx!=""){
			showCwxx(cwxx);
		}
		
		jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
		jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
	}
}
//ѡ��λ��������
function selectCw(){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlertDivLayer('����ѡ��һ��ѧ����');
	}else{
		showDialog('��ѡ��һ����λ',800,500,'ydsq.do?method=selectCwxx&goto=path&xh='+xh);
	}
}
//ѡ��λ����ס��
function selectRzcw(){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlertDivLayer('����ѡ��һ��ѧ����');
	}else{
		showDialog('��ѡ��һ����λ',800,500,'ydsq.do?method=selectRzcwxx&goto=path&xh='+xh);
	}
}
//��ʾѡ�д�λ��Ϣ��������
function showCwxx(cwxx){
	var cwxxSetting = {
			caption:"��ѡ��λ��Ϣ",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
			colList:[
			   {label:'��λ��Ϣid',name:'cwxx', index: 'cwxx',key:true,hidden:true},
			   {label:'¥������',name:'ldmc', index: 'ldmc'},
			   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
			   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
			   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
			   {label:'�����꼶',name:'nj', index: 'nj'},
			   {label:'����'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'ѧ��',name:'xh', index: 'xh'},
			   {label:'����',name:'xm', index: 'xm'}
			],
			sortname: "sfrz",
		 	sortorder: "desc"
		}
 	jQuery("#cwxxTable").initGrid(cwxxSetting);
	jQuery("#yxzcwxx").show();
	jQuery("#cwxx").val(cwxx);
}
//��ʾѡ�д�λ��Ϣ����ס��
function showRzcwxx(rzcwxx){
	var rzcwxxSetting = {
			caption:"��ѡ��λ��Ϣ",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectRzcwxx&type=query&rzcwxx="+rzcwxx,
			colList:[
			         {label:'��λ��Ϣid',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
			         {label:'¥������',name:'ldmc', index: 'ldmc'},
			         {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
			         {label:'�����꼶',name:'nj', index: 'nj'},
			         {label:'����'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
			         //{label:'ѧ��',name:'xh', index: 'xh'},
			         //{label:'����',name:'xm', index: 'xm'}
			         ],
			         sortname: "sfrz",
			         sortorder: "desc"
	}
	jQuery("#rzcwxxTable").initGrid(rzcwxxSetting);
	jQuery("#yxzrzcwxx").show();
	jQuery("#rzcwxx").val(rzcwxx);
}

//��������ת
function dcmcLink(cellValue, rowObject) {
	var ssydsqid = rowObject["ssydsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + ssydsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(id) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&ssydsqid=" + id;
	var title = "�����춯������Ϣ";
	showDialog(title, 800, 500, url);
}
//����
function add() {
	/*var userType = jQuery("#userType").val();
	if("stu" == userType){
		jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
			var lddm = data.lddm;
			
			if(!lddm){
				showAlertDivLayer("����δ��ס��Ԣ���ң��޷��ύ�����춯���룡");
			}else{
				var url =action+"?method=add";
				var title = "�����춯����";
				showDialog(title, 800, 500, url);
			}					
		},'json');
	}else{*/
		var url =action+"?method=add";
		var title = "�����춯����";
		showDialog(title, 800, 500, url);		
	//}
		
}

function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					ydlx : rows[0]['ssydlx'],
					xh : rows[0]['xh']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}


function save(url,checkId){
	var ssydlx = jQuery("#ssydlx").val();
	var qsmcCk = jQuery("#qsmcCk").val();
	var sfysq = jQuery("#sfysq").val();
	var zsfje = jQuery.trim(jQuery("#zsfje").val());
	var jflx = jQuery("#jflx").val();
	var xxdm = jQuery("#xxdm").val();
	var rzcwxx = jQuery("#rzcwxx").val();
	
	if(sfysq=="0"){
		return showAlertDivLayer("��ѧ�����������춯��Ϣ��������У����������롣");
	}

	if(ssydlx == "03" && url == "ydsq.do?method=add&type=save" && qsmcCk == "0") {
		return showAlertDivLayer("�������Ѿ���ѧ���ύ�����룬��������У����������롣");
	}
	if(xxdm == 10466 && ssydlx == "03" && rzcwxx == ''){
		showAlertDivLayer("��ѡ��λ��");
		return false;
	}
	
	if("12303"==xxdm){
		checkId+="-bz";
	}
	
	if(!check(checkId)){
		return showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkother()){
		return false;
	}

	if (jflx != undefined && ((jflx == "" && zsfje != "" ) || (jflx != "" && zsfje == ""))) {
		showAlertDivLayer("�뽫ס�޷���Ϣ��д������");
		return false;
	}
	
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
	    		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }
	 		 else{
	    		 showAlertDivLayer(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}

function checkother(){
	var ssydlx = jQuery("#ssydlx").val();
	var oldSsydlx = jQuery("#oldSsydlx").val(); // �޸�ǰ���������
	// ����ǰѧ�������Һţ���������ѡ����ǡ���ס�����򲻷���
	var td_cwh = jQuery.trim(jQuery("#td_cwh").html());
	if((!oldSsydlx && ssydlx != "03" && td_cwh == "") || (oldSsydlx && oldSsydlx == "03" && ssydlx != "03")){
		showAlertDivLayer("��ѧ����δ��ס��");
		return false;
	}
	if((!oldSsydlx && ssydlx == "03" && td_cwh != "") || (oldSsydlx && oldSsydlx != "03" && ssydlx == "03")){
		showAlertDivLayer("��ѧ������ס��");
		return false;
	}
	return true;
}
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("ֻ���޸�δ��˻������˻صļ�¼��");
			return false;
		}
		var url = action+'?method=update&xh='+rows[0]["xh"]+'&ssydsqid=' + rows[0]["ssydsqid"];
		var title = "�޸������춯����";
		showDialog(title, 800, 490, url);
		reload();
	}
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {			
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="�����춯</br>";
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" �������ѽ������ⲻ��ɾ��!";
					}
					showAlertDivLayer(mes);
					reload();
				}, 'json');
				
			}
		});
	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {	
		var shlc=rows[0]["splcid"];
		if(shlc==""){
			showAlertDivLayer("������������ˣ������������Ϣ��");
			return false;
		}
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�����춯�������̸���",600,400,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssydsqid']+"&splc="+rows[0]['splcid']);
	}
}

function exportConfig() {
	customExport("ydsqbase.do", exportData,690,500);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ydsq.do?method=exportData&dcclbh=ydsqbase.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//��ӡ����
function printTstzd() {
	var ssydsqid = jQuery("#dataTable").getSeletIds();
	if (ssydsqid.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydsqid.length; i++){
		if("����"==rows[i]['ydlxmc'] || "��ס"==rows[i]['ydlxmc']){
			showAlertDivLayer('"��ס"/"����"���ܴ�ӡ"����֪ͨ��"');
			return false;
		}
	}
	
	var url = "ydsq.do?method=printTstzd&ssydsqid=" +ssydsqid.toString();
	
	window.open(url);
}
