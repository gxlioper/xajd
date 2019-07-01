//附件--添加行
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
						        //ie做了安全性的限制，因此不能将input:file隐藏掉，否则无法提交表单
//						        	"<input type='text' name='wjmc' readonly = 'true' style = 'width:150px;float:left;'>"+
//						        	"<button type='button'  class='btn_01' onclick = 'selectfile(this)'  style = 'float:left'>浏览" +
//						        	"<input type='file' onchange = 'attachfilename(this)'  name = 'fjid"+index+"' style='position:absolute;left:0;top:0;width:100%;height:100%;z-index:999;opacity:0;'>" +
//						        	"</button>"+
//						        	"<button type='button'  class='btn_01' onclick = 'selectfile(this)'  style = 'float:left'>浏览</button>"+
//						        	"<div style='display:none'>"+
						        	"<input type='file' onchange = 'attachfilename(this)'  name = 'fjid"+index+"' >"+
//						        	"</div>"+
						        "</td>"+
					        "</tr>";
		        jQuery(openerObj).append(html);
			};
			
			//附件--删除行
			function delFj(){
				var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
				if (checkbox.length == 0) {
					showAlertDivLayer("请选择需要删除的附件！");
					return false;
				}
				for ( var i = 0; i < checkbox.length; i++) {
					jQuery(checkbox[i]).parents("tr:eq(0)").remove();
				}
				jQuery('input[type=checkbox][name=th]').attr('checked', false);
			};

			//全选
			function selectAll(obj) {
				jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
			}

			//调用附件 
			jQuery(function(){
				
			});	
			
			
			function selectfile(obj){
				jQuery(obj).parent().find('input:file').click();
			}
			
			//将文件名称赋值到input框
			function attachfilename(obj){
				var filefullpath = getFullPath(obj);
			//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
				checkFileType(obj);
		    }
			
			//获取input file的名称
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
			
			//文件类型控制
			function checkFileType(obj){
				var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
				var types = ["png","gif","jpg","jpeg","zip","rar","pdf","txt","doc","docx","xls","xlsx"];
				if (jQuery.inArray(type, types) == -1){
					/*如果不符合上传类型,清空input file，兼容性写法，兼容ie和chrome*/
					var file = jQuery(obj);
					file.after(file.clone().val("")); 
					file.remove(); 
					showAlert("您所选择的文件类型不允许上传。");
					return false;
				}
			}
			
			//删除单个文件
			function delfj(obj){
				var fileid = jQuery(obj).attr("data-id");;
				 jQuery.ajax( {  
					     url:'rcsw_xsgzzb_xsgzzbsqgl.do?method=deleteFile',// 跳转到 action  
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
					        	 showAlert("删除失败！");   
					         }  
					      },  
					      error : function() {  
					           // view("异常！");  
					           showAlert("异常！");  
					      }  
					 });
			}
			
			//下载单个文件
			function downloadfj(obj){
				var fileid = jQuery(obj).attr("data-id");
				window.open('rcsw_xsgzzb_xsgzzbsqgl.do?method= downloadFile&id='+fileid);
			}