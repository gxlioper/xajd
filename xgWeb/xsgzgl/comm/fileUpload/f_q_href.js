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

                                    jQuery(frameElement.api.get('parentDialog').document).find('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
						         }else{
                                    jQuery(frameElement.api.get('parentDialog').document).find('#' + _default['_commonfileuploadlistid']).append(jQuery('<span style="color:blue">无</span>'));
						         }
								return false;
							}
							 jQuery.each(data, function(i,item){
							        var
							         r = jQuery('<div class="MultiFile-label"></div>'),
							         v = item['originalname'],
							         a = jQuery('<span class="MultiFile-title" style="color:black">'+v+'</span>'),
							         c = jQuery("<a class='MultiFile-download' style='color:blue' href='"
										 +"common_upload.do?method=asyncDownload&t="
                                         + new Date().getTime()
                                         + '&fid=' + item['fid']
                                         +"'>下载</a>");
									 
							         if(_default['targetEl'] != undefined){
                                         jQuery(frameElement.api.get('parentDialog').document).find('#'+_default['targetEl']).append(r.append( c , ' ', a));
							         }else{
							        	 // jQuery('#'+_default['_commonfileuploadlistid'],_default['_docu']).append(r.append( c , ' ', a));
                                         jQuery(frameElement.api.get('parentDialog').document).find('#'+_default['_commonfileuploadlistid']).append(r.append( c , ' ', a));;
							         }
								  });							
						}
			);
		}else{
			if(_default['targetEl'] != undefined){

                jQuery(frameElement.api.get('parentDialog').document).find('#'+_default['targetEl']).append(jQuery('<span style="color:blue">无</span>'));
	         }else{
                jQuery(frameElement.api.get('parentDialog').document).find('#'+_default['_commonfileuploadlistid']).append(jQuery('<span style="color:blue">无</span>'));
	         }
			return false;
			}
		}
	});
	
})(jQuery);
