
		jQuery(function() {
			var gridSetting = {
					caption:"��Уά������б�",
			pager:"pager",
			params:getSuperSearch(),
			url:"rcsw_jqfxFxwh.do?method=jqfxwhManage&type=query",
			colList:[	
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},					   		  
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'10%'},
			   {label:'ѧԺ',name:'xsxymc', index: 'xszymc',width:'10%'},
			   {label:'רҵ',name:'xszymc', index: 'xszymc',width:'10%'},
			   {label:'�༶',name:'xsbjmc', index: 'xsbjmc',width:'10%'},
			   {label:'��У״̬',name:'fxztmc', index: 'fxztmc',width:'5%'},
			   {label:'fxzt',name:'fxzt', index: 'fxzt',hidden:true},
			   {label:'δ����ԭ��',name:'wfxyymc', index: 'wfxyymc',width:'6%'},
			   {label:'Ԥ�Ʒ�Уʱ��',name:'yjfxsj', index: 'yjfxsj',width:'11%'},
			   {label:'wfxyydm',name:'wfxyydm', index: 'wfxyydm',hidden:true},
			   {label:'��Уʱ��',name:'fxsj', index: 'fxsj',width:'11%'}
			   
			],
			sortname: "xh",
		 	sortorder: "desc"
		}
		jQuery("#dataTable").initGrid(gridSetting);
		});

		
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
				
/*		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
*/
		var DCCLBH = "rcsw_jqfx_fxwh.do";// dcclbh,�������ܱ��

		// �Զ��嵼�� ����
		function exportConfig() {
			// DCCLBH�������ܱ��,ִ�е�������
			customExport(DCCLBH, fxglExportData);
		}

		// ��������
		function fxglExportData() {
			setSearchTj();// ���ø߼���ѯ����
			var url = "rcsw_jqfxFxwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
			url = addSuperSearchParams(url);// ���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}				

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXhfxgl(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		function viewXhfxgl(id, xh) {
			showDialog("�鿴��У����", 720, 420,
					"rcsw_jqfxFxwh.do?method=viewxsJqfx&id=" + id + "&xh=" + xh);			
		}				
		
		function viewfxgl(){
			
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
			}else {
				var url = 'rcsw_jqfxFxwh.do?method=viewxsJqfx&id='
						+ rows[0]["id"]+'&xh='+rows[0]["xh"];				
				var title = "�鿴��У����";
				showDialog(title, 720, 400, url);
			}
			
		}
		

	
		

		// ������˱���
		function savePl(fxsj,bz){
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();	
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			//��ֵ����̨�����룬��Ҫ����ת��
			bz = encodeURI(bz);
			bz = encodeURI(bz);
			jQuery.post("rcsw_jqfxFxwh.do?method=pldgxsJqfx&type=save"+'&fxsj='+fxsj+'&bz='+bz,{				
				xhs:xhs
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
		}
		
		function addwfxgl(){
		
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}			
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
				return false;
			}																	
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length == 0){																				
				showAlertDivLayer("��ѡ����Ҫ�޸ĵļ�¼");																
			}
																													
			if (rows.length > 1){				
				var url = 'rcsw_jqfxFxwh.do?method=addplxsJqwfx';																					
				var title = "δ��У��������";
				showDialog(title, 529,350, url);
				return false;
			}else{														
				var fxzt = rows[0]["fxzt"];													
			    if(jQuery.trim(fxzt) == "0" || jQuery.trim(fxzt) == "1"){
			        var url = 'rcsw_jqfxFxwh.do?method=addxsJqwfx&id='
					    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
			    }else{				  
			    	var url = 'rcsw_jqfxFxwh.do?method=addxsJqwfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
			    }					
				var title = "δ��У����";
				showDialog(title, 720,470, url);												
			}	
			
		}


		// �����������δ��У
		function saveWfxplsh(wfxyy,sfqdlx,yjfxsj,bz){	
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();
				
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			jQuery.post("rcsw_jqfxFxwh.do?method=addplxsJqwfx&type=save",{				
				xhs:xhs,
				wfxyy:wfxyy,
				sfqdlx:sfqdlx,
				bz:bz,
				yjfxsj:yjfxsj
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}
		
		
		// ����������ڷ�У
		function savePlfx(fxsj,bz){	
			bz = encodeURI(bz);
			bz = encodeURI(bz);
			var map = getSuperSearch();
			jQuery.post("rcsw_jqfxFxwh.do?method=plxsJqfx&type=save"+'&fxsj='+fxsj+'&bz='+bz,map,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');				
			
			
		}
		
		//ͳ�Ʊ�
		function bdqktj(){
			//var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
			var url="rcsw_jqfxFxwh.do?method=bdqktjPrint";
			
			/*var xnLength=jQuery("a[name=a_name_xn]").length;
			var xmmcLength=jQuery("a[name=a_name_xmmc]").length;
			
			if(xnLength != "1"){
				showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
				return false;
			}
			if(xmmcLength < "1"){
				showAlertDivLayer("������ѡ��һ����Ŀ���Ʋ�ѯ������");
				return false;
			}*/
			setSearchTj();
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();
	}