var gridSetting = {
    caption : "",
    pager : "pager",
    url : "dycjgl_dmwh.do?method=dmwhQuery",
    colList : [
        { label : '�����ɼ����ʹ���', name : 'xmdm', index : 'xmdm', width : '10%' },
        { label : '�����ɼ���������', name : 'xmmc', index : 'xmmc', width : '11%' },
        { label : '�ɼ��ϸ�����ж�', name : 'cjhgfsx', index : 'cjhgfsx', width : '6%' },
        { label : '����˵��', name : 'xmsm', index : 'xmsm', width : '15%' },
        { label : '��ʾ���', name : 'xsxh', index : 'xsxh',width : '7%' }
	],
    sortname : "xsxh",
    sortorder : "asc" };



//�޸ı���
function updSaveForm(){
	var ffxmmc=jQuery("#ffxmmc").val();
	if(ffxmmc==""){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "rcsw_fyff_ffxm.do?method=updateFfxm&type=update";
	ajaxSubFormWithFun("FyffxmForm",url,function(data){
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