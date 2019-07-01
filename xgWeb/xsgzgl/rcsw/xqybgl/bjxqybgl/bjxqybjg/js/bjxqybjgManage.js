jQuery(function(){
				var gridSetting = {
						caption:"�༶ѧ�����б�",
						pager:"pager",
						url:"rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=bjxqybjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},												 						 
							{label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
							{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'10%'},
							{label:'�·�',name:'ny', index: 'ny',width:'10%',formatter:xhLink},
							{label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
							{label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},
							{label:'��д��',name:'lrrxm', index: 'txr',width:'10%'},
							{label:'��дʱ��',name:'txsj', index: 'txsj',width:'15%'},
							{label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
						],
						sortname: "txsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=addBjxqybjg";
				var title = "�༶ѧ���±������д";
				showDialog(title,790,460,url);
			}

			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				var sjly = rows[0]["sjly"];			
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				}else if(sjly == '1'){
					showAlertDivLayer("��������ݣ������޸ģ�");
				}else {
					var url = 'rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=updateBjxqybjg&jgid='+ rows[0]["jgid"]
					+ '&bjdm=' + rows[0]["bjdm"];
					var title = "�༶ѧ���±�����޸�";
					showDialog(title, 720, 460, url);
				}

			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				}else {
					var rows = jQuery("#dataTable").getSeletRow();
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
						"okFun" : function() {
							jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=delBjxqybjg", {
								values : ids.toString()
							}, function(data) {
								var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
								mes+="</br>";
								if(data["nodel"]!="-1"){
									mes+="<font color='red'>"+data["nodel"]+"</font>";
									mes+="���������������ɾ��!";
								}
								showAlertDivLayer(mes);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});		
				}
			}

	
			var DCCLBH = "rcsw_xqybgl_bjxqybgl_bjxqybjg.do";//dcclbh,�������ܱ��
			
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, bjxqybjgExportData);
			}
			
			// ��������
			function bjxqybjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//�ϲ�����
			function hbdc() {
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=hbdc";
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//�鿴
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewXqybjg(\""
						+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
						+ "</a>";
			}
			
			function viewXqybjg(jgid,bjdm) {
				showDialog("�༶ѧ���±�����", 720, 520, "rcsw_xqybgl_bjxqybgl_bjxqybjggl.do?method=viewXqybjg&jgid=" + jgid
						+ "&bjdm=" + bjdm);
			}