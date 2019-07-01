		jQuery(function(){
			var gridSetting = {
					caption:"�༶ѧ���±��б�",
					pager:"pager",
					url:"rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjxqybsqManage&type=query",
					params:getSuperSearch(),
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},					   
					   {label:'��������',name:'splc', index: 'splc',hidden:true},
					   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
					   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'10%'},
					   {label:'�·�',name:'ny', index: 'ny',width:'10%',formatter:xhLink},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},					   
					   {label:'��д��',name:'lrrxm', index: 'txr',width:'10%'},
					   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'8%'}
					],
					sortname: "txsj",
				 	sortorder: "desc"
				}
			jQuery("#dataTable").initGrid(gridSetting);
			
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("false" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
			
		}


		
		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1){
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='3'&& "true"!= isopen){
						showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
						return false;
					}
					if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
						showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
						return false;
					}
					if(rows[i]['shzt']!='3'){
						url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=submitBjxqybsq";
					}else{
						url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=submitBjxqybsq";
					}
				}

				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{
						 values:ids.toString(),
						 bjdm : rows[0]['bjdm'],
						 splc : rows[0]['splc'],
						 shzt : rows[0]['shzt']
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function cancel(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
				return false;
			}
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
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=cancelBjxqybsq",
						{
						 values:ids.toString(),
						 splcid : rows[0]['splc'] 
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function xqybsqLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {	
				var shzt = rows[0]["shzt"];
				if ("0" == shzt){
					showAlertDivLayer(jQuery("#lable_wxglcxx").val());
					return false;
				}	
				showDialog("�༶ѧ���±������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		function add(){
			
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
				return false;
			}
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=addBjxqybsq";
			var title = "�༶�±�������д";
			showDialog(title,790,450,url);
			
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {				
				var shzt = rows[0]["shzt"];
				if (shzt=='0'||shzt=='3'){
					var url = 'rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=updateBjxqybsq&sqid=' + rows[0]["sqid"];
					showDialog("�༶�±������޸�", 720, 450, url);
				}else{
					showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
					return false;
				}
			}
		}

		//ɾ��
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=delBjxqybsq", {
							values : ids.toString()
						}, function(data) {
							var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="�������Ѿ��ύ����ɾ��!";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		function viewXqybsq(sqid,bjdm) {
			showDialog("�༶ѧ���±�����", 720, 520, "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=viewXqybsq&sqid=" + sqid
					+ "&bjdm=" + bjdm);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXqybsq(\""
					+ rowObject["sqid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_xqybgl_bjxqybgl_bjxqybsq.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, bjybsqExportData);
		}

		// ��������
		function bjybsqExportData() {			
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();			
		}