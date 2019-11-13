           var gridSetting = {
				caption:"困难档次列表",
				pager:"pager",
				url:"xszz_knsdc.do?method=dcwhList&type=query",
				colList:[
				   {label:'档次代码',name:'dcdm', index: 'dcdm',key:true},
				   {label:'档次名称',name:'dcmc', index: 'dcdm',width:'20%'},
				   {label:'项目说明',name:'xmsm', index: 'xmsm',width:'60%',formatter:xmsmSubstring},
                    {label:'是否启用',name:'sfqy', index: 'sfqy',formatter:function (cellValue,rowObject) {
						if(rowObject["sfqy"]!="否"){
							return "是"
						}else {
							return rowObject["sfqy"];
						}
                    }}
				],
				sortname: "dcdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["dcmc"] = jQuery("#dcmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "xszz_knsdc.do?method=addKnsdc";
				var title = "增加困难档次";
				showDialog(title,400,250,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = 'xszz_knsdc.do?method=updateKnsdc&dcdm='+rows[0]["dcdm"];
					var title = "修改困难档次";
					showDialog(title,400,250,url);
				}
			}

			function updateQyzt() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条您要修改的记录！");
                } else {
                    var url = 'xszz_knsdc.do?method=updateQyzt&dcdm='+rows[0]["dcdm"];
                    jQuery.post(url,{sfqy:rows[0]["sfqy"]},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            }

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("xszz_knsdc.do?method=delKnsdc",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
			
			//项目说明过长截取
			function xmsmSubstring(cellValue,row){
				if(cellValue==null){
					cellValue="";
				}
				var strValue = cellValue;
				if(strValue.length > 60){
					strValue = strValue.substring(0, 60)+"...";
				}
				return "<span title='"+cellValue+"'>"+strValue+"</span>";;
			}
			
			
		