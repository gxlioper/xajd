  var gridSetting = {
				caption:"������Ա����б�",
				pager:"pager",
				url:"rcsw_dxsylbx_ylbxwhgl.do?method=czqebzlxListManage&type=query",
				colList:[
				   {label:'�������ʹ���',name:'czqebzdm', index: 'czqebzdm',key:true,width:'50%'},
				   {label:'������������',name:'czqebzmc', index: 'czqebzmc',width:'50%'}
				],
				sortname: "czqebzdm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				var czqebzmc = jQuery("#czqebzmc").val();
				map["czqebzmc"] = jQuery.trim(czqebzmc);
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function delCzqebzlx() {
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
						"okFun" : function() {
							jQuery.post("rcsw_dxsylbx_ylbxwhgl.do?method=delCzqebzlx", {
								values : ids.toString()
							}, function(data) {
								var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
								mes+="</br>";
								if(data["nodel"]!="-1"){
									mes+="<font color='red'>"+data["nodel"]+"</font>";
									mes+="�Ĳ�����Ա�����Ѿ�ʹ�ò���ɾ��!";
								}
								showAlertDivLayer(mes);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
				}
			}
		
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}