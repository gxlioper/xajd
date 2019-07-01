var sysDate = new Date().getTime();
		
			var gridSetting = {
				caption:"ԤԼ��ѯ�б�",
				pager:"pager",
				url:"xlzx_yysq.do?method=yysqManage&doType=query",
				colList:[
					//{label:'���',name:'r', index: 'r',width:'4%'},
					{label:'ԤԼ���',name:'id', index: 'id',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'����',name:'xsxm', index: 'xsxm'},
				  // {label:'�Ա�',name:'xsxb', index: 'xsxb',width:'4%'},
				   // {label:'ְ����',name:'zgh', index: 'zgh',width:'6%'},
				  /* {label:'��ѯʦ',name:'zxsxm', index: 'zxsxm'},
				   {label:'���ڲ���',name:'bmmc', index: 'bmmc'},*/
				   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
				   {label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'��ѯ��������',name:'zxrq', index: 'zxrq'},
				    {label:'��ѯ״̬',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc',formatter:getZxColor},
				    {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',width:'6%',formatter:getPjColor},
				   {label:'ѧ������',name:'yyzxzt', index: 'yyzxzt',align:'center',formatter:getSfxssq}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function getSfxssq(cellValue,rowObject){
				if(!cellValue){
					return "��";
				}else{
					return "��";
				}
			}
			
			function xhLink(cellValue,rowObject){
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"+rowObject["id"]+"');\">"+cellValue+"</a>";
			}
			
			function showDetail(id){
				showDialog("ԤԼ��ѯ����",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
			}
			
			
			function getYyColor(cellValue,rowObject){
					if(rowObject["status"]=="1"){
						return "<font color='blue'>"+cellValue+"</font>";
					}else if(rowObject["status"]=="2"){
						return "<font color='red'>"+cellValue+"</font>";
					}else{
						return cellValue;
					}
			}
			
			function getZxColor(cellValue,rowObject){
				if(rowObject["zxzt"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["zxzt"]=="2" || rowObject["zxzt"]=="3"){
					return "<font color='red'>"+cellValue+"</font>";
				}else{
					return cellValue;
				}
			}
			
			function getPjColor(cellValue,rowObject){
				if(rowObject["xspjzt"]=="1"){
					return "<font color='blue'>"+cellValue+"</font>";
				}else if(rowObject["xspjzt"]=="2"){
					return "<font color='red'>"+cellValue+"</font>";
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}

			function addYyzxInfo(){
				showDialog("������ѯ",750,520,"xlzx_yysq.do?method=addYyzxInfo");
			}
			
			// ɾ��ԤԼ��ѯ
			function delYyzxInfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlert("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					
					for(var i=0;i<ids.length;i++){
						if(rows[i]['yyzxzt'] || rows[i]['zxzt']=='2'){
							showAlert("����ѯ����ѧ������ļ�¼����ɾ����");
							return false;
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {"okFun" : function() {
							jQuery.post("xlzx_yysq.do?method=delYyzxInfo", {
								values : ids.toString()
							}, function(data) {
								showAlert(data["message"],{},{"clkFun":function(){
									searchRs();
								}});
							}, 'json');
							
					}});
				}
			}
			function ckYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				}else{
					id = rowsValue[0]["id"];
				}
				showDialog("ԤԼ��ѯ����",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
			}	
			
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�����ļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["status"]=="3" ||rowsValue[0]["status"]=="4" || (rowsValue[0]["status"]=="2"&& rowsValue[0]["zxzt"]!="1" )){
						showAlert("������¼���ܷ�����");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("ԤԼ����",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=yyfk&id="+id);
			}
			
			function zxfkInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					showAlert("��ѡ��һ����Ҫ�����ļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["status"]!="2"){
						showAlert("���ܷ�������ԤԼ��");
						return false;
					}
					if(rowsValue[0]["xspjzt"]=="2"){
						return showAlert("����ѯѧ���Ѿ����ۡ��޷��޸���ѯ������");
					}
					id = rowsValue[0]["id"];
				}
				showDialog("��ѯ����",750,560,"xlzx_yysq.do?method=yyzxDetail&doType=zxfk&id="+id);
			}
			

			function exportConfig() {
				customExport("xlzx_zxyycl_zxyycl.do", exportData,750,500);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xlzx_yysq.do?method=exportData&dcclbh=xlzx_zxyycl_zxyycl.do";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
