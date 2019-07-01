/*
 *jqeruy MultiUploader 
 *
 *
 * 依赖包 1.jquery ; 2.ajaxfileupload ;
 *
 * author : zxb
 */

jQuery.extend({
		
		MultiUploader_q : function(options){
			
			//默认参数
			var _default = {};

			//合并参数
			jQuery.extend(_default,options || {});
			
			//是否有gid,有就查询文件列表 
			if(options['gid']){
				
				
					jQuery.getJSON(
							/**/
							"common_upload.do?method=asyncQuery&gid="+ options['gid'] +"&t=" + new Date().getTime(),
							{},
							function(data){
								if(data==undefined || data.length==0){
									if(_default['targetEl'] != undefined){
										
							        	 jQuery('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
							         }else{
							        	 jQuery('#commonfileupload-list-q').append(jQuery('<span style="color:blue">无</span>'));
							         }
									return false;
								}
								 jQuery.each(data, function(i,item){
								        var
										 p = "",
								         r = jQuery('<div class="MultiFile-label"></div>'),
								         v = item['originalname'],
								         a = jQuery('<span class="MultiFile-title" style="color:black">'+v+'</span>'),
								         c = jQuery('<a class="MultiFile-download" style="color:blue" href="#">下载</a>');

								         if(item['canpreview']){
								         	p = jQuery('<a class="MultiFile-preview" style="color:blue" href="#">预览</a>');
								         	p.click(function () {
                                                var fid = item['fid'];
                                                var url = "common_upload.do?method=preview&t="
                                                    + new Date().getTime()
                                                    + '&fid=' + fid;
                                                window.open(url);
                                            });
										 }

										 c.click(function(){
											 	var _this = this;
											 	var fid = item['fid'];
												//showAlert(jQuery(element).attr('fid'));
												var url = "common_upload.do?method=asyncDownload&t=" 
													+ new Date().getTime()
													+ '&fid=' + fid;
												var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
												jqFrom.attr("action",url);
												jQuery('body').append(jqFrom);
												jQuery('#_j_downloadform').submit();
												jQuery('#_j_downloadform').remove();	
											 });
								         if(_default['targetEl'] != undefined){
								        	 jQuery('#'+_default['targetEl']).append(r.append( c ,' ',p, ' ', a));
								         }else{
								        	 jQuery('#commonfileupload-list-q').append(r.append( c ,' ',p, ' ', a));
								         }
									  });							
							}
				);
			}else{
				if(_default['targetEl'] != undefined){
					
		        	 jQuery('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
		         }else{
		        	 jQuery('#commonfileupload-list-q').append(jQuery('<span style="color:blue">无</span>'));
		         }
				return false;
			}
			
		}

	});


(function($){
	
	function _createFileDivEL(el,args){
		var fileListDiv = $('<div style="padding: 5px" id="' + args['_commonfileuploadlistid'] + '" ></div>');
		
		return el.before(fileListDiv);
	};
	
	$.fn.extend( {
		multiUploader_q: function(options){
		//默认参数
		var _default = {};

		//合并参数
		jQuery.extend(_default,options || {});
		
		_default['_commonfileuploadlistid'] = 'commonfileupload-list-q-' + (options['uid'] || '1');
		
		var $this = $(this);
		
		_createFileDivEL($this, _default);
		
		//是否有gid,有就查询文件列表 
		if(options['gid']){
				jQuery.getJSON(
						/**/
						"common_upload.do?method=asyncQuery&gid="+ options['gid'] +"&t=" + new Date().getTime(),
						{},
						function(data){
							if(data==undefined || data.length==0){
								if(_default['targetEl'] != undefined){
									
						        	 jQuery('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
						         }else{
						        	 jQuery('#' + _default['_commonfileuploadlistid']).append(jQuery('<span style="color:blue">无</span>'));
						         }
								return false;
							}
							 jQuery.each(data, function(i,item){
							        var
									 p = "",
							         r = jQuery('<div class="MultiFile-label"></div>'),
							         v = item['originalname'],
							         a = jQuery('<span class="MultiFile-title" style="color:black">'+v+'</span>'),
							         c = jQuery('<a class="MultiFile-download" style="color:blue" href="#">下载</a>');

									 if(item['canpreview']){
										 p = jQuery('<a class="MultiFile-preview" style="color:blue" href="#">预览</a>');
										 p.click(function () {
											 var fid = item['fid'];
											 var url = "common_upload.do?method=preview&t="
												 + new Date().getTime()
												 + '&fid=' + fid;
											 window.open(url);
										 });
									 }

									 c.click(function(){
										 	var _this = this;
										 	var fid = item['fid'];
											//showAlert(jQuery(element).attr('fid'));
											var url = "common_upload.do?method=asyncDownload&t=" 
												+ new Date().getTime()
												+ '&fid=' + fid;
											var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
											jqFrom.attr("action",url);
											jQuery('body').append(jqFrom);
											jQuery('#_j_downloadform').submit();
											jQuery('#_j_downloadform').remove();	
										 });
							         if(_default['targetEl'] != undefined){
							        	 jQuery('#'+_default['targetEl']).append(r.append( c ,' ',p, ' ', a));
							         }else{
							        	 jQuery('#'+_default['_commonfileuploadlistid']).append(r.append( c ,' ',p, ' ', a));
							         }
								  });							
						}
			);
		}else{
			if(_default['targetEl'] != undefined){
				
	        	 jQuery('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
	         }else{
	        	 jQuery('#'+_default['_commonfileuploadlistid']).append(jQuery('<span style="color:blue">无</span>'));
	         }
			return false;
			}
		}
	});
	
})(jQuery);
