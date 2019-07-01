
	
	/**
     * 入伍学费补偿单个信息--查看
     * @param xh
     * @return
     */
    function viewRwxfbc(guid,xh){
    	//showWindow("入伍学费补偿信息",700,450,"rwgl_rwxfbcgl.do?method=viewRwxfbc&guid="+guid+"&xh="+xh);
		showDialog("入伍学费补偿信息",700,450,"rwgl_rwxfbcgl.do?method=viewRwxfbc&guid="+guid+"&xh="+xh);
    }
	
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='viewRwxfbc(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	jQuery(function(){
		var gridSetting = {
				caption:"入伍学费补偿结果列表",
				pager:"pager",
				url:"rwgl_rwxfbcgl.do?method=getRwxfbcList&type=query",
				colList:[
			       {label:'guid',name:'guid', index: 'guid',width:'5%',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'8%'},
				   {label:'年级',name:'nj', index: 'nj',width:'7%',hidden:true},
				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xydm',width:'10%',hidden:true},
				   {label:'专业',name:'zymc', index: 'zydm',width:'15%',hidden:true},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
				   {label:'入伍学年',name:'rwxn', index: 'rwxn',width:'10%'},
				   {label:'补偿学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'补偿时间',name:'xfbcsj', index: 'xfbcsj',width:'10%'},
				   {label:'补偿金额',name:'xfbcje', index: 'xfbcje',width:'9%'},
				   {label:'是否补偿',name:'sfbc', index: 'sfbc',width:'8%'}
				],
				sortname: "xn,nj,xydm,zydm,bjdm,xh",
			 	sortorder: "desc"
			}
		jQuery("#dataTable").initGrid(gridSetting);
	});
	
	function searchRs(){
		var map = getSuperSearch();

		jQuery("#dataTable").reloadGrid(map);
	}
	
	/**
	 * 入伍学费补偿
	 * @return
	 */
	function rwxfBc(){
		
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length == 0){
			showAlertDivLayer("请选择您要学费补偿的记录！");
		} else if (rows.length == 1){
			if(null==rows[0]["guid"] || "null"==rows[0]["guid"] || ""==rows[0]["guid"]){
				//showWindow("入伍学费补偿",710,560,"rwgl_rwxfbcgl.do?method=addRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);
				showDialog("入伍学费补偿",710,540,"rwgl_rwxfbcgl.do?method=addRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);

			}else{
				showDialog("入伍学费补偿",710,540,"rwgl_rwxfbcgl.do?method=updateRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);

				//showWindow("入伍学费补偿",710,560,"rwgl_rwxfbcgl.do?method=updateRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);
			}
		} else {
			//showDivWindow("批量入伍学费补偿",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc");
			var rows = jQuery("#dataTable").getSeletRow();
			var guid = new Array();
			var xhs = new Array();
			
			jQuery.each(rows,function(i,row){
				guid.push(row["guid"]==null?"null":row["guid"]);
				xhs.push(row["xh"]);
			});
			showDialog("批量入伍学费补偿",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc&id="+guid+"&xhs="+xhs);

			//showWindow("批量入伍学费补偿",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc&id="+guid+"&xhs="+xhs);
		}
	}
	
	/**
     * 撤销入伍学费补偿信息
     * @param xh
     * @return
     */
	function cancelRwxfbc(){
		var ids = jQuery("#dataTable").getSeletIds();
		var rows = jQuery("#dataTable").getSeletRow();
		var boo = true;//判断是否包含未进行学费补偿的记录
		jQuery.each(rows,function(i,row){
			var flag = row["xfbcsj"]==null||"null"==row["xfbcsj"]||""==row["xfbcsj"]?false:true;
			if(flag==false){//包含没有补偿学费的
				boo=false;
			}
		});
		if (ids.length == 0){
			showAlertDivLayer("请选择您要撤销补偿的记录！");
		} else if (ids.length == 1){
			if(boo==false){//包含没有补偿学费的
				showAlertDivLayer("该学生没有进行学费补偿，不能进行撤销操作！");
			}else{//不包含没有补偿学费的
				showConfirmDivLayer("您确定要撤销选择的补偿记录吗？",{"okFun":function(){
					jQuery.post("rwgl_rwxfbcgl.do?method=cancelRwxfbc",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}else{
			if(boo==true){//不包含没有补偿学费的
				showConfirmDivLayer("您确定要撤销选择的补偿记录吗？",{"okFun":function(){
					jQuery.post("rwgl_rwxfbcgl.do?method=cancelRwxfbc",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}else{//包含没有补偿学费的
				showConfirmDivLayer("您选择的记录中包含未进行学费补偿的记录，确定仍要继续执行撤销操作吗？",{"okFun":function(){
					jQuery.post("rwgl_rwxfbcgl.do?method=cancelRwxfbc",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}
	}
	
	function rwxfbcExportConfig() {
		
		customExport("rwgl_rwtwgl_rwxfbcgl.do", rwxfbcExportData);
		
	}
		
	
		
	// 导出方法
	function rwxfbcExportData() {
		setSearchTj();//设置高级查询条件
		var url = "rwgl_rwxfbcgl.do?method=rwxfbcExportData&dcclbh=" + "rwgl_rwtwgl_rwxfbcgl.do";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
