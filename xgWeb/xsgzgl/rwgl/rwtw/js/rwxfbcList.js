
	
	/**
     * ����ѧ�Ѳ���������Ϣ--�鿴
     * @param xh
     * @return
     */
    function viewRwxfbc(guid,xh){
    	//showWindow("����ѧ�Ѳ�����Ϣ",700,450,"rwgl_rwxfbcgl.do?method=viewRwxfbc&guid="+guid+"&xh="+xh);
		showDialog("����ѧ�Ѳ�����Ϣ",700,450,"rwgl_rwxfbcgl.do?method=viewRwxfbc&guid="+guid+"&xh="+xh);
    }
	
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='viewRwxfbc(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	jQuery(function(){
		var gridSetting = {
				caption:"����ѧ�Ѳ�������б�",
				pager:"pager",
				url:"rwgl_rwxfbcgl.do?method=getRwxfbcList&type=query",
				colList:[
			       {label:'guid',name:'guid', index: 'guid',width:'5%',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'8%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'7%',hidden:true},
				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xydm',width:'10%',hidden:true},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'15%',hidden:true},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
				   {label:'����ѧ��',name:'rwxn', index: 'rwxn',width:'10%'},
				   {label:'����ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'����ʱ��',name:'xfbcsj', index: 'xfbcsj',width:'10%'},
				   {label:'�������',name:'xfbcje', index: 'xfbcje',width:'9%'},
				   {label:'�Ƿ񲹳�',name:'sfbc', index: 'sfbc',width:'8%'}
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
	 * ����ѧ�Ѳ���
	 * @return
	 */
	function rwxfBc(){
		
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length == 0){
			showAlertDivLayer("��ѡ����Ҫѧ�Ѳ����ļ�¼��");
		} else if (rows.length == 1){
			if(null==rows[0]["guid"] || "null"==rows[0]["guid"] || ""==rows[0]["guid"]){
				//showWindow("����ѧ�Ѳ���",710,560,"rwgl_rwxfbcgl.do?method=addRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);
				showDialog("����ѧ�Ѳ���",710,540,"rwgl_rwxfbcgl.do?method=addRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);

			}else{
				showDialog("����ѧ�Ѳ���",710,540,"rwgl_rwxfbcgl.do?method=updateRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);

				//showWindow("����ѧ�Ѳ���",710,560,"rwgl_rwxfbcgl.do?method=updateRwxfDgbc&guid="+rows[0]["guid"]+'&xh='+rows[0]["xh"]);
			}
		} else {
			//showDivWindow("��������ѧ�Ѳ���",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc");
			var rows = jQuery("#dataTable").getSeletRow();
			var guid = new Array();
			var xhs = new Array();
			
			jQuery.each(rows,function(i,row){
				guid.push(row["guid"]==null?"null":row["guid"]);
				xhs.push(row["xh"]);
			});
			showDialog("��������ѧ�Ѳ���",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc&id="+guid+"&xhs="+xhs);

			//showWindow("��������ѧ�Ѳ���",700,350,"rwgl_rwxfbcgl.do?method=rwxfPlbc&id="+guid+"&xhs="+xhs);
		}
	}
	
	/**
     * ��������ѧ�Ѳ�����Ϣ
     * @param xh
     * @return
     */
	function cancelRwxfbc(){
		var ids = jQuery("#dataTable").getSeletIds();
		var rows = jQuery("#dataTable").getSeletRow();
		var boo = true;//�ж��Ƿ����δ����ѧ�Ѳ����ļ�¼
		jQuery.each(rows,function(i,row){
			var flag = row["xfbcsj"]==null||"null"==row["xfbcsj"]||""==row["xfbcsj"]?false:true;
			if(flag==false){//����û�в���ѧ�ѵ�
				boo=false;
			}
		});
		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫ���������ļ�¼��");
		} else if (ids.length == 1){
			if(boo==false){//����û�в���ѧ�ѵ�
				showAlertDivLayer("��ѧ��û�н���ѧ�Ѳ��������ܽ��г���������");
			}else{//������û�в���ѧ�ѵ�
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��Ĳ�����¼��",{"okFun":function(){
					jQuery.post("rwgl_rwxfbcgl.do?method=cancelRwxfbc",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}else{
			if(boo==true){//������û�в���ѧ�ѵ�
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��Ĳ�����¼��",{"okFun":function(){
					jQuery.post("rwgl_rwxfbcgl.do?method=cancelRwxfbc",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}else{//����û�в���ѧ�ѵ�
				showConfirmDivLayer("��ѡ��ļ�¼�а���δ����ѧ�Ѳ����ļ�¼��ȷ����Ҫ����ִ�г���������",{"okFun":function(){
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
		
	
		
	// ��������
	function rwxfbcExportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "rwgl_rwxfbcgl.do?method=rwxfbcExportData&dcclbh=" + "rwgl_rwtwgl_rwxfbcgl.do";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
