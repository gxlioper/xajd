	var gridSetting;
			var gridSetting2;
			jQuery(function(){
				gridSetting = {
						caption:"�༶ѧ������",
						pager:"pager",
						url:"rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybshManage&type=query",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true},						 
						   {label:'ѧ��',name:'xn', index: 'xn',width:'11%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'9%'},
						   {label:'�·�',name:'ny', index: 'ny',width:'9%',formatter:xhLink},
						   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},
						   {label:'��д��',name:'lrrxm', index: 'txr',width:'10%'},
						   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
						   {label:'���״̬',name:'shztmc', index: 'shzt',width:'15%'}
						],
						params:{shzt:"dsh"},
						sortname: "txsj",
					 	sortorder: "desc",
					 	radioselect:false
				};			
				
				gridSetting2 = {
						caption:"�༶ѧ�������",
						pager:"pager",
						url:"rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybshManage&type=query",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'�༶����',name:'bjdm', index: 'bjdm',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},						 
						   {label:'ѧ��',name:'xn', index: 'xn',width:'11%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'9%'},
						   {label:'�·�',name:'ny', index: 'ny',width:'9%',formatter:xhLink},
						   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},
						   {label:'��д��',name:'lrrxm', index: 'txr',width:'10%'},
						   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},
						   {label:'���״̬',name:'shztmc', index: 'shzt',width:'15%'}
						],
						params:{shzt:"ysh"},
						sortname: "txsj",
					 	sortorder: "desc",
					 	radioselect:true
				};
				var searchJson = getSuperSearch();
				searchJson.shzt = "dsh";
				gridSetting["params"] = searchJson;
				jQuery("#dataTable").initGrid(gridSetting);
			});
				
			function searchRs(){
				var map = getSuperSearch();
				var shzt = jQuery("#shzt").val();
				if (shzt != ""){
					map["shzt"] = shzt;
				}
				jQuery("#dataTable").reloadGrid(map);
			}
				
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);
				var map = getSuperSearch();
				map["shzt"] = shzt;
				if (shzt == "dsh"){
					jQuery("#li_sh").css("display","");
					jQuery("#li_qx").css("display","none");
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				} else {			
					jQuery("#li_sh").css("display","none");
					jQuery("#li_qx").css("display","");
					gridSetting2["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}
			// ���
			function bjxqybSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("�Ѵ����¼�����ٴ����");
					return false;
				}else if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼");
					return false;
				}else if (rows.length == 1){					
					var url = "rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybDgsh&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"] ;
					showDialog("�༶ѧ���±����",700,500,url);
				} else{
					showDialog("�༶ѧ���±��������",500,300,"rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybPlsh");
				} 
			}

	
			// ������˱���
			function savePlsh(shzt,shyj){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var lrr = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					lrr.push(row["lrr"]);
				});
				jQuery.post("rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbPlsh&type=save",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 lrrs:lrr,
					 shzt:shzt,
					 shyj:shyj
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
	
			function bjxqybshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
				} else {		
					showDialog("�༶ѧ���±��������̸���",550,450,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
		
			function cancelShnew(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
				} else {
					var splc = rows[0]["splc"];
					var shid = rows[0]["shid"];
					var sqid = rows[0]["sqid"];
					var shzt = rows[0]["shzt"];
					showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
							// �ж��Ƿ����һ������(1:���һ�������ɹ���
							if("1" == data["cancelFlg"]){
								jQuery.post("rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=cancelBjxqybsh",{sqid:sqid,shzt:shzt},function(result){
									showAlertDivLayer(result["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								},'json');
							}else{
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							}
						
					},'json');
					}});
				}
			}
	
			
			function savePlsh(shzt,shyj){
				
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var gwid = new Array();
				var bjdm = new Array();
				var splc = new Array();
				
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					gwid.push(row["gwid"]);
					bjdm.push(row["bjdm"]);
					splc.push(row["splc"]);
				});

				jQuery.post(
					"rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybPlsh&type=save",
					{
					 shzt:shzt,
					 id:guid,
					 gwids:gwid,
					 bjdms:bjdm,
					 shyj:shyj,
					 splcs:splc
					},function(data){
						
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},
					'json'
				);				
			}
			
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewXqybsq(\""
						+ rowObject["sqid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
						+ "</a>";
			}
			
			function viewXqybsq(sqid,bjdm) {
				showDialog("�༶ѧ���±�����", 720, 520, "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=viewXqybsq&sqid=" + sqid
						+ "&bjdm=" + bjdm);
			}