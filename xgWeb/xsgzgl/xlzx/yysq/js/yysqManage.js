			var gridSetting = {
				caption:"ԤԼ��ѯ�б�",
				pager:"pager",
				url:"xlzx_yysq.do?method=yysqManage&doType=query",
				colList:[
					{label:'ԤԼ���',name:'id', index: 'id',hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
				   {label:'����',name:'xsxm', index: 'xsxm'},
				   {label:'�Ա�',name:'xsxb', index: 'xsxb'},
				   {label:'��ѯʦ����',name:'zxsxm', index: 'zxsxm'},
				   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
				   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
				   {label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
				   {label:'��ѯ��������',name:'zxrq', index: 'zxrq'},
				   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
				   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc'},
				   {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
				   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
				],
				sortname: "zxrq,yyzxrq",
			 	sortorder: "desc"
			};
			
			function xhLink(cellValue,rowObject){
				return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('�鿴ԤԼ��Ϣ' , 750,440 , 'xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" + rowObject['id'] + "')\" >" + cellValue + "</a>";
			}
			
			function showDetail(id){
				showDialog("ԤԼ��ѯ����",750,500,"xlzx_yysq.do?method=yyzxDetail&doType=view&id="+id);
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
			function getPjColor(cellValue,rowObject){
					if(rowObject["xspjzt"]=="1"){
						return "<font color='blue'>"+cellValue+"</font>";
					}else if(rowObject["xspjzt"]=="2"){
						return "<font color='red'>"+cellValue+"</font>";
					}else{
						return cellValue;
					}
			}
			
			jQuery(function(){
				if(jQuery("#xxdm").val() == "10026"){
					gridSetting = {
							caption:"ԤԼ��ѯ�б�",
							pager:"pager",
							url:"xlzx_yysq.do?method=yysqManage&doType=query",
							colList:[
								{label:'ԤԼ���',name:'id', index: 'id',hidden:true},
							   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
							   {label:'����',name:'xsxm', index: 'xsxm'},
							   {label:'�Ա�',name:'xsxb', index: 'xsxb'},
							   {label:'��ѯʦ����',name:'zxsxm', index: 'zxsxm'},
							   {label:'ԤԼ��ѯ����',name:'yyzxrq', index: 'yyzxrq'},
							   {label:'ԤԼ״̬code',name:'status', index: 'status',hidden:true},
							   {label:'ԤԼ״̬',name:'statusmc', index: 'status',formatter:getYyColor},
							   {label:'��ѯ��������',name:'zxrq', index: 'zxrq'},
							   {label:'zxzt',name:'zxzt', index: 'zxzt',hidden:true},
							   {label:'��ѯʱ���',name:'yysjdmc', index: 'yysjdmc'},
							   {label:'��ѯ״̬',name:'zxztmc', index: 'zxztmc'},
							   {label:'��ѯ����',name:'xspjzt', index: 'xspjzt',hidden:true},
							   {label:'��ѯ����',name:'pjztmc', index: 'pjztmc',formatter:getPjColor}
							],
							sortname: "zxrq,yyzxrq",
						 	sortorder: "desc"
						};
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}

			function addYyzxInfo(){
                showDialog("��ѯ��֪",750,560,"xlzx_yysq.do?method=zxxz");
			}
			function ckYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				}else{
					id = rowsValue[0]["id"];
				}
				 showDialog('�鿴ԤԼ��Ϣ' , 750,440 , "xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" +id);
			}	
			function updateYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["status"]!="1"){
						showAlertDivLayer("ֻ���޸ġ�ԤԼ�С��ļ�¼��");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("�޸�ԤԼ������Ϣ",750,510,"xlzx_yysq.do?method=yyzxDetail&doType=update&id="+id);
			}
			//ȡ���A�s
			function qxYyzxInfo(){
				var id= '';
				var status='';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("��ѡ��һ����Ҫȡ����ԤԼ��");
					return false;
				}else{

					if(rowsValue[0]["status"]=="1"){
						status = "3";
					}else if(rowsValue[0]["status"]=="2" && rowsValue[0]["zxzt"]=="1"){//ԤԼ�ɹ�����δ��ѯ�ſ�ȡ������������ʦ���˷�����ѯ����ѯ״̬����ѯ����Ҳ��ȡ����
						status = "4";
					}else if(rowsValue[0]["status"]=="3" || rowsValue[0]["status"]=="4"){
						return showAlertDivLayer("��ԤԼ������ȡ����");
					}else{
						return showAlertDivLayer("����ȡ������ԤԼ��");
					}
					id = rowsValue[0]["id"];
				}
				 
				var url ="xlzx_yysq.do?method=updateYysqInfo";
				var parameter={id:id,status:status};
				
				showConfirmDivLayer("ȷ��ȡ��ԤԼ��",{"okFun":function(){
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,parameter,function(result){
								if(data = true){
									showAlertDivLayer("ԤԼȡ���ɹ���",{},{"clkFun":function(){
										searchRs();
									}});
								}else{
									showAlertDivLayer("ԤԼȡ��ʧ�ܣ�");
								}
						},"json"
					);
					jQuery.ajaxSetup({async:true});
				}});
			}
			
			function pjYyzxInfo(){
				var id= '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				 if(rowsValue.length != 1){
					 showAlertDivLayer("��ѡ��һ����Ҫ���۵ļ�¼��");
					return false;
				}else{
					if(rowsValue[0]["xspjzt"]!="1"){
						showAlertDivLayer("�������۸���ԤԼ��¼��");
						return false;
					}
					id = rowsValue[0]["id"];
				}
				showDialog("��ѯ����",620,300,"xlzx_yysq.do?method=xspjInfo&doType=update&id="+id);
			}
			

			//���������Ϣ
			function save(type) {
				var ids = null;
				var url = "";
				if(type=='add'){
					ids = "xh-sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
					url = "xlzx_yysq.do?method=addZxJbxx&type=save";
				}
				if(!check(ids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}
				var checkboxs = jQuery("input[name='yzxwts']:checked");
				if(checkboxs.length == 0){
					if(jQuery("#wtbc").val().length == 0){
						showAlert("�빴ѡ��ѯ���������д��ѯ���ⲹ��");
						return false;
					}
				}
				
				ajaxSubFormWithFun("yysqForm", url, function(data) {
					 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    			if(frameElement.api){
			    				var api = frameElement.api,W = api.opener;			    				
			    				//W.location.href="xlzx_yysq.do?method=yysqManage";
			    				W.reload();
			    				closeDialog();
			    			} else {
			    				parent.reload();
			    				//parent.window.location.href = "xlzx_yysq.do?method=yysqManage";
			    				iFClose();
			    			}
						}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});	
			}
			
			/**
			 * ��֤�Ƿ���ڿ���
			 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
			 * @return
			 */
			function check(ids){
				var id=ids.split("-");
				for(var i=0;i<id.length;i++){
					var lddm=jQuery("#"+id[i]).val().trim();
					if(lddm==null||""==lddm){
						return false;
					}
				}
				return true;
			}
			
			//��ѯ��¼ά��
			function ckgrInfo(xh){	
					var url = 'xlzx_yysq.do?method=ckZxzxjl&xh=' + xh;
					var title = "�鿴";
					showDialog(title, 800, 550, url);	
			}
			