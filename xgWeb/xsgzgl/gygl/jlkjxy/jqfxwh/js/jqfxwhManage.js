
		jQuery(function() {
			var gridSetting = {
					caption:"��Уά������б�",
			pager:"pager",
			params:getSuperSearch(),
			url:"jlkjxy_jqfxwh.do?method=jqfxwhManage&type=query",
			colList:[	
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},					   		  
			   {label:'¥������',name:'ldmc', index: 'ldmc',width:'10%'},
			   {label:'���Һ�',name:'qsh', index: 'qsh',width:'7%'},
			   {label:'��λ��',name:'cwh', index: 'cwh',width:'5%'},
			   {label:'�Ա�',name:'qsxb', index: 'qsxb',width:'5%'},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'10%'},
			   {label:'רҵ����',name:'xszymc', index: 'xszymc',width:'10%'},
			   {label:'��У״̬',name:'fxztmc', index: 'fxztmc',width:'6%'},
			   {label:'fxzt',name:'fxzt', index: 'fxzt',hidden:true},
			   {label:'��Уʱ��',name:'fxsj', index: 'fxsj',width:'10%'}
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
				
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "jlkjxy_jqfxwh.do";// dcclbh,�������ܱ��

		// �Զ��嵼�� ����
		function exportConfig() {
			// DCCLBH�������ܱ��,ִ�е�������
			customExport(DCCLBH, fxglExportData);
		}

		// ��������
		function fxglExportData() {
			setSearchTj();// ���ø߼���ѯ����
			var url = "jlkjxy_jqfxwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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
			showDialog("�鿴��У����", 720, 498,
					"jlkjxy_jqfxwh.do?method=viewxsJqfx&id=" + id + "&xh=" + xh);			
		}				
		
		function viewfxgl(){
			
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
			}else {
				var url = 'jlkjxy_jqfxwh.do?method=viewxsJqfx&id='
						+ rows[0]["id"]+'&xh='+rows[0]["xh"];				
				var title = "�鿴��У����";
				showDialog(title, 720, 498, url);
			}
			
		}
		

	


		// ������˱���
		function savedgPlsh(fxsj){
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();	
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			jQuery.post("jlkjxy_jqfxwh.do?method=pldgxsJqfx&type=save"+'&fxsj='+fxsj,{				
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
				var url = 'jlkjxy_jqfxwh.do?method=addplxsJqwfx';																					
				var title = "δ��У��������";
				showDialog(title, 529,220, url);
				return false;
			}else{														
				var fxzt = rows[0]["fxzt"];													
			    if(jQuery.trim(fxzt) == "0" || jQuery.trim(fxzt) == "1"){
			        var url = 'jlkjxy_jqfxwh.do?method=addxsJqwfx&id='
					    + rows[0]["id"]+'&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    
			    }else{				  
			    	var url = 'jlkjxy_jqfxwh.do?method=addxsJqwfx&xh='+rows[0]["xh"]+'&fxzt='+rows[0]["fxzt"];				    	
			    }					
				var title = "δ��У����";
				showDialog(title, 720,518, url);												
			}	
			
		}


		// �����������δ��У
		function savedgWfxplsh(wfxyy){	
			
			var rows = jQuery("#dataTable").getSeletRow();
			var xhs = new Array();
				
			jQuery.each(rows,function(i,row){
				xhs.push(row["xh"]);			
			});
			
			jQuery.post("jlkjxy_jqfxwh.do?method=addplxsJqwfx&type=save",{				
				xhs:xhs,
				wfxyy:wfxyy
				},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			
		}
		