//����--�����
			function addFj(){
				var index = jQuery("#tbody_wjlx tr").length;
				var openerObj = jQuery("#tbody_wjlx");
				var selectOpHtml = jQuery("#hideselect").html();
				var html = "<tr>" +
								"<td width = '5%'>"+
									"<input type='checkbox' name='selectwjlx' id='' />" +
						        "</td>"+
						        "<td width = '20%'>"+
						    		"<select name = 'wjlxdm' style = 'width:150px'>"+
						    		selectOpHtml+
						    		"</select>"+
						        "</td>"+
						        "<td width = '20%'>"+
						        //ie���˰�ȫ�Ե����ƣ���˲��ܽ�input:file���ص��������޷��ύ��
//						        	"<input type='text' name='wjmc' readonly = 'true' style = 'width:150px;float:left;'>"+
//						        	"<button type='button'  class='btn_01' onclick = 'selectfile(this)'  style = 'float:left'>���" +
//						        	"<input type='file' onchange = 'attachfilename(this)'  name = 'fjid"+index+"' style='position:absolute;left:0;top:0;width:100%;height:100%;z-index:999;opacity:0;'>" +
//						        	"</button>"+
//						        	"<button type='button'  class='btn_01' onclick = 'selectfile(this)'  style = 'float:left'>���</button>"+
//						        	"<div style='display:none'>"+
						        	"<input type='file' onchange = 'attachfilename(this)'  name = 'fjid"+index+"' >"+
//						        	"</div>"+
						        "</td>"+
					        "</tr>";
		        jQuery(openerObj).append(html);
			};
			
			//����--ɾ����
			function delFj(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				if (checkbox.length == 0) {
					showAlertDivLayer("��ѡ����Ҫɾ���ĸ�����");
					return false;
				}
				for ( var i = 0; i < checkbox.length; i++) {
					jQuery(checkbox[i]).parents("tr:eq(0)").remove();
				}
				jQuery('input[type=checkbox][name=th]').attr('checked', false);
			};

			//ȫѡ
			function selectAll(obj) {
				jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
			}

			//���ø��� 
			jQuery(function(){
				
			});	
			
			
			function selectfile(obj){
				jQuery(obj).parent().find('input:file').click();
			}
			
			//���ļ����Ƹ�ֵ��input��
			function attachfilename(obj){
				var filefullpath = getFullPath(obj);
			//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
				checkFileType(obj);
		    }
			
			//��ȡinput file������
			function getFullPath(obj){ 
				if(obj) 
				{ 
					 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
					 { 
						 if(obj.files) 
						 { 
							 return obj.files.item(0).getAsDataURL(); 
						 } 
						 return obj.value; 
					 } 
					 return obj.value; 
				 } 
			} 
			
			//�ļ����Ϳ���
			function checkFileType(obj){
				var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
				var types = ["png","gif","jpg","jpeg","zip","rar","pdf","txt","doc","docx","xls","xlsx"];
				if (jQuery.inArray(type, types) == -1){
					/*����������ϴ�����,���input file��������д��������ie��chrome*/
					var file = jQuery(obj);
					file.after(file.clone().val("")); 
					file.remove(); 
					showAlert("����ѡ����ļ����Ͳ������ϴ���");
					return false;
				}
			}
			
			//ɾ�������ļ�
			function delfj(obj){
				var fileid = jQuery(obj).attr("data-id");;
				 jQuery.ajax( {  
					     url:'rcsw_xsgzzb_xsgzzbsqgl.do?method=deleteFile',// ��ת�� action  
					     data:{  
					         id:fileid
					      },  
					     type:'post',  
					     cache:false,
					     async: false,
					     dataType:'text',  
					     success:function(data) {  
					         if(data =="true" ){  
					             jQuery(obj).parent().parent().remove(); 
					         }else{  
					        	 showAlert("ɾ��ʧ�ܣ�");   
					         }  
					      },  
					      error : function() {  
					           // view("�쳣��");  
					           showAlert("�쳣��");  
					      }  
					 });
			}
			
			//���ص����ļ�
			function downloadfj(obj){
				var fileid = jQuery(obj).attr("data-id");
				window.open('rcsw_xsgzzb_xsgzzbsqgl.do?method= downloadFile&id='+fileid);
			}