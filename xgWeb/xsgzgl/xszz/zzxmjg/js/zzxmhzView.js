var DCCLBH = "xszz_zzxmjg.do?method=zzxmhzList";//dcclbh,�������ܱ�� 

var gridSetting = {
	caption:"��������б�",
	pager:"pager",
	url:"xszz_zzxmjg.do?method=zzxmhzView&type=query",
	colList:[
       {label:'guid',name:'guid', index: 'guid',width:'2%',key:true,hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'13%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
	   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'19%'},
	   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
	   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
	   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'15%'},
	   {label:'���',name:'je', index: 'je',width:'11%'},
	   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
	 
	],
	multiselect:false,
	sortname: "xh",
 	sortorder: "asc"
	};

   /**
	* ������Ŀ�����������--�鿴
	* @param xh
	* @return
	*/
   function zzjgView(guid,xh){
		showDialog("���������ѯ",550,400,"xszz_zzxmjg.do?method=zzxmjgView&guid="+guid+"&xh="+xh);
   }
   
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='zzjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	jQuery(function(){
		gridSetting["params"]={"lbdm":jQuery("#lbdm").val(),"xn":jQuery("#xn").val(),"xq":jQuery("#xq").val(),"xmmc":jQuery("#xmmc").val()};
		jQuery("#dataTable").initGrid(gridSetting);
	});
	
	
	function reBack(){
		refreshForm("/xgxt/xszz_zzxmjg.do?method=zzxmhzList");
	}
	
	
	// �������� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е����ĺ���
		customExport(DCCLBH, exportData);
	}
	
	// ��������
	function exportData() {
		var lbdm = jQuery("#lbdm").val();
		var xn = jQuery("#xn").val();
		var xq = jQuery("#xq").val();
		var xmmc = jQuery("#xmmc").val();
		var url = "xszz_zzxmjg.do?method=exportDataSx&dcclbh=" + DCCLBH + "&lbdm=" + lbdm + "&xn=" + xn + "&xq="+ xq + "&xmmc=" + xmmc;//dcclbh,�������ܱ��
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
