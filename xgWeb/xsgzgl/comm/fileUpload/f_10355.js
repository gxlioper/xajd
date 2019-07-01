jQuery.extend( {
			MultiUploader : function(options) {
				var _default = {
					list : '#commonfileupload-list',
					max : -1
				};
				var commonFileuploadOptions = jQuery.extend(_default, options
						|| {});
				if (!commonFileuploadOptions['maxcount']) {
					commonFileuploadOptions['maxcount'] = 3
				}
				if (!commonFileuploadOptions['accept']) {
					commonFileuploadOptions['accept'] = 'png|gif|jpg|zip|rar|doc|docx|pdf' 
				}
				if (!commonFileuploadOptions['maxsize']) {
					commonFileuploadOptions['maxsize'] = 5
				}
				if (!commonFileuploadOptions['gid']) {
					if (commonFileuploadOptions['elementid']) {
						var _gid_val = jQuery(
								'#' + commonFileuploadOptions['elementid'])
								.val();
						commonFileuploadOptions['gid'] = _gid_val
					} else {
						jQuery('#commonfileupload').attr('disabled', true);
						showAlert('附件上传配置不正确,请联系管理员!');
						return false
					}
				}
				if (commonFileuploadOptions['accept']) {
					var accepts = commonFileuploadOptions['accept'].split('|');
					jQuery('#tips').text('提示：1.高等学校学生及家庭情况调查表；');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp2.孤、残证明；');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp3.烈士或优抚对象子女证明；');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp4.低保证明；');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp5.建档立卡贫困户相关证明；');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp6.其他证明');
					
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp7.请选择 ' + accepts.toString() + ' 格式的文件！');
					
					var maxsize = commonFileuploadOptions['maxsize'];
					maxsize = (maxsize == null || maxsize == '') ? "20" : maxsize;
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp8.请上传不超过 ' + maxsize + ' M的文件！');
					
					var maxcount = commonFileuploadOptions['maxcount'];
					maxcount = (maxcount == null || maxcount == '') ? "3" : maxcount;
					jQuery('#tips').append('</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp9.上传文件数不超过 ' + maxcount + ' 个！')
				}
				if (commonFileuploadOptions['gid']) {
					jQuery
							.getJSON(
									"common_upload.do?method=asyncQuery&gid="
											+ commonFileuploadOptions['gid']
											+ "&t=" + new Date().getTime(),
									{},
									function(data) {
										jQuery
												.each(
														data,
														function(i, item) {
															var p="", r = jQuery('<div class="MultiFile-label"></div>'), v = item['originalname'], a = jQuery('<span class="MultiFile-title">' + v + '</span>'), b = jQuery('<a class="MultiFile-remove" style="color:blue" href="#">删除</a>');
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

															b
																	.click(function() {
																		jQuery(
																				'#loading')
																				.show();
																		var _this = this;
																		jQuery
																				.getJSON(
																						"common_upload.do?method=asyncDelete&fid="
																								+ item['fid']
																								+ "&t="
																								+ new Date()
																										.getTime(),
																						{},
																						function() {
																							jQuery(
																									_this)
																									.parent()
																									.remove()
																						});
																		jQuery(
																				'#loading')
																				.hide()
																	});
															c
																	.click(function() {
																		var _this = this;
																		var fid = item['fid'];
																		var url = "common_upload.do?method=asyncDownload&t="
																				+ new Date()
																						.getTime()
																				+ '&fid='
																				+ fid;
																		var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
																		jqFrom
																				.attr(
																						"action",
																						url);
																		jQuery(
																				'body')
																				.append(
																						jqFrom);
																		jQuery(
																				'#_j_downloadform')
																				.submit();
																		jQuery(
																				'#_j_downloadform')
																				.remove()
																	});
															jQuery('#commonfileupload-list').append(r.append(b, ' ', c, ' ',p,' ', a))
														})
									})
				}
				jQuery
						.extend(
								commonFileuploadOptions,
								{
									onFileRemove : function(element, value,
											master_element) {
										var fid = jQuery(element).attr('fid');
										var _element = element;
										jQuery('#loading').show();
										jQuery.getJSON(
												"common_upload.do?method=asyncDelete&fid="
														+ fid + "&t="
														+ new Date().getTime(),
												{}, function() {
												});
										jQuery('#loading').hide()
									},
									onFileAppend : function(element, value,
											master_element, del, div, download,preview) {
										jQuery('#loading').show();
										var _local_element = element;
										var elementid = _local_element.id;
										var url = "common_upload.do?method=asyncUpload&t="
												+ new Date().getTime();
												/*+ '&gid='
												+ commonFileuploadOptions['gid']
												+ '&max='
												+ commonFileuploadOptions['max']
												+ '&maxsize='
												+ commonFileuploadOptions['maxsize']
												+ '&accept='
												+ commonFileuploadOptions['accept']
												+ '&maxcount='
												+ commonFileuploadOptions['maxcount'];*/
										jQuery
												.ajaxFileUpload( {
													url : url,
													type: 'post',
													data: { gid: commonFileuploadOptions['gid'], 
															max: commonFileuploadOptions['max'],
															maxsize: commonFileuploadOptions['maxsize'], 
															accept: commonFileuploadOptions['accept'],
															maxcount: commonFileuploadOptions['maxcount']},
													secureuri : false,
													fileElementId : elementid,
													dataType : "json",
													success : function(data,
															status) {
														if (data['result'] == '-1') {
															div.css('display',
																	'none');
															jQuery('#loading')
																	.hide();
															showAlert('提示: 文件大小超过指定范围' + commonFileuploadOptions['maxsize'] + 'M!')
														} else if (data['result'] == '-2') {
															jQuery('#loading')
																	.hide();
															div.css('display',
																	'none');
															showAlert('提示: 上传文件数超过最大值' + commonFileuploadOptions['maxcount'] + '个!')
														} else if (data['result'] == '-99') {
															jQuery('#loading')
																	.hide();
															div.css('display',
																	'none');
															showAlert('提示: 附件上传配置不正确,请联系系统管理员!')
														} else {
															jQuery(element)
																	.attr(
																			'fid',
																			data['fid']);
															jQuery(
																	'#' + elementid)
																	.attr(
																			'fid',
																			data['fid']);
															jQuery(
																	'#' + commonFileuploadOptions['elementid'])
																	.val(
																			data['gid']);
															commonFileuploadOptions['gid'] = data['gid'];
															jQuery('#loading').hide();
                                                            if(data["canPreview"]){
                                                                preview.css(
                                                                    'display',
                                                                    'inline');
                                                            }
														}
													}
												})
									},
                                    onFilePreview:function (element,
                                                            value, master_element) {
                                        var fid = jQuery(element).attr(
                                            'fid');
                                        var url = "common_upload.do?method=preview&t="
                                            + new Date().getTime()
                                            + '&fid=' + fid;
                                        window.open(url);
                                    },
									onFileDownload : function(element, value,
											master_element) {
										var fid = jQuery(element).attr('fid');
										var url = "common_upload.do?method=asyncDownload&t="
												+ new Date().getTime()
												+ '&fid=' + fid;
										var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
										jqFrom.attr("action", url);
										jQuery('body').append(jqFrom);
										jQuery('#_j_downloadform').submit();
										jQuery('#_j_downloadform').remove()
									}
								});
				jQuery('#commonfileupload').MultiFile(commonFileuploadOptions)
			}
		});
(function($) {
	function _createFileEL(el, args) {
		var fileIpt = $('<input type="file" name="uploadFile" id="' + args['_fileid'] + '" />');
		var span = $('<span  style="color: red" id="' + args['_tipid'] + '" />');
		var loading = $('<span class="loading" id=" ' + args['_loadingid'] + '" style="font-size: 10px; margin-left: 0; display: none;" >处理中...</span>');
		var fileListDiv = $(
				'<div style="padding: 5px" id="' + args['_commonfileuploadlistid'] + '" ></div>')
				.append(loading);
		return el.before(fileIpt).before(span).before(fileListDiv)
	}
	;
	$.fn.extend( {
				multiUploader : function(options) {
					var $this = $(this);
					var _default = {
						list : '#commonfileupload-list',
						max : -1
					};
					var args = jQuery.extend(_default, options || {});
					var unique_id = args['eid'] || '';
					args['_eid'] = unique_id;
					if (args['eid']) {
						args['list'] = '#commonfileupload-list' + unique_id
					}
					var _fileid = 'commonfileupload' + unique_id;
					var _tipid = 'tips' + unique_id;
					var _loadingid = 'loading' + unique_id;
					var _commonfileuploadlistid = 'commonfileupload-list' + unique_id;
					args['_fileid'] = _fileid;
					args['_tipid'] = _tipid;
					args['_loadingid'] = _loadingid;
					args['_commonfileuploadlistid'] = _commonfileuploadlistid;
					_createFileEL($this, args);
					$this.remove();
					if (!args['maxcount']) {
						args['maxcount'] = 3
					}
					if (!args['accept']) {
						args['accept'] = 'png|gif|jpg|zip|rar|doc|docx|pdf'
					}
					if (!args['maxsize']) {
						args['maxsize'] = 5
					}
					//隐藏提示
					if(args['hideTS']){
						jQuery('#' + args['_tipid']).hide();
					}
					if (!args['gid']) {
						if (args['elementid']) {
							var _gid_val = jQuery('#' + args['elementid'])
									.val();
							args['gid'] = _gid_val
						} else {
							jQuery('#' + args['_commonfileuploadlistid']).attr(
									'disabled', true);
							showAlert('附件上传配置不正确,请联系管理员!');
							return false
						}
					}
					if (args['accept']) {
						var accepts = args['accept'].split('|');
						jQuery('#' + args['_tipid']).text(
								'提示：1.请选择 ' + accepts.toString() + ' 格式的文件！');
						var maxsize = args['maxsize'];
						maxsize = (maxsize == null || maxsize == '') ? "20"
								: maxsize;
						jQuery('#' + args['_tipid'])
								.append(
										'</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.请上传不超过 ' + maxsize + ' M的文件！');
						var maxcount = args['maxcount'];
						maxcount = (maxcount == null || maxcount == '') ? "3"
								: maxcount;
						jQuery('#' + args['_tipid'])
								.append(
										'</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.上传文件数不超过 ' + maxcount + ' 个！')
					}
					if (args['gid']) {
						jQuery
								.getJSON(
										"common_upload.do?method=asyncQuery&gid="
												+ args['gid'] + "&t="
												+ new Date().getTime(),
										{},
										function(data) {
											jQuery
													.each(
															data,
															function(i, item) {
																var p="",r = jQuery('<div class="MultiFile-label"></div>'), v = item['originalname'], a = jQuery('<span class="MultiFile-title">' + v + '</span>'), b = jQuery('<a class="MultiFile-remove" style="color:blue" href="#">删除</a>');
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

																b
																		.click(function() {
																			jQuery(
																					'#loading')
																					.show();
																			var _this = this;
																			jQuery
																					.getJSON(
																							"common_upload.do?method=asyncDelete&fid="
																									+ item['fid']
																									+ "&t="
																									+ new Date()
																											.getTime(),
																							{},
																							function() {
																								jQuery(
																										_this)
																										.parent()
																										.remove()
																							});
																			jQuery(
																					'#' + args['_loadingid'])
																					.hide()
																		});
																c
																		.click(function() {
																			var _this = this;
																			var fid = item['fid'];
																			var url = "common_upload.do?method=asyncDownload&t="
																					+ new Date()
																							.getTime()
																					+ '&fid='
																					+ fid;
																			var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
																			jqFrom
																					.attr(
																							"action",
																							url);
																			jQuery(
																					'body')
																					.append(
																							jqFrom);
																			jQuery(
																					'#_j_downloadform')
																					.submit();
																			jQuery(
																					'#_j_downloadform')
																					.remove()
																		});
																jQuery('#' + args['_commonfileuploadlistid']).append(r.append(b, ' ', c, ' ',p,' ', a))
															})
										})
					}
					jQuery
							.extend(
									args,
									{
										onFileRemove : function(element, value,
												master_element) {
											var fid = jQuery(element).attr(
													'fid');
											var _element = element;
											jQuery('#' + args['_loadingid'])
													.show();
											jQuery.getJSON(
													"common_upload.do?method=asyncDelete&fid="
															+ fid
															+ "&t="
															+ new Date()
																	.getTime(),
													{}, function() {
													});
											jQuery('#' + args['_loadingid'])
													.hide()
										},
										onFileAppend : function(element, value,
												master_element, del, div,
												download,preview) {
											jQuery('#' + args['_loadingid'])
													.show();
											var _local_element = element;
											var elementid = _local_element.id;
											var url = "common_upload.do?method=asyncUpload&t="
													+ new Date().getTime();
													/*+ '&gid='
													+ args['gid']
													+ '&max='
													+ args['max']
													+ '&maxsize='
													+ args['maxsize']
													+ '&accept='
													+ args['accept']
													+ '&maxcount='
													+ args['maxcount'];*/
											jQuery
													.ajaxFileUpload( {
														url : url,
														type: 'post',
														data: { gid: args['gid'], 
																max: args['max'],
																maxsize: args['maxsize'], 
																accept: args['accept'],
																maxcount: args['maxcount']},
														secureuri : false,
														fileElementId : elementid,
														dataType : "json",
														success : function(
																data, status) {
															if (data['result'] == '-1') {
																div
																		.css(
																				'display',
																				'none');
																jQuery(
																		'#' + args['_loadingid'])
																		.hide();
																showAlert('提示: 文件大小超过指定范围' + args['maxsize'] + 'M!')
															} else if (data['result'] == '-2') {
																jQuery(
																		'#' + args['_loadingid'])
																		.hide();
																div
																		.css(
																				'display',
																				'none');
																showAlert('提示: 上传文件数超过最大值' + args['maxcount'] + '个!')
															} else if (data['result'] == '-99') {
																jQuery(
																		'#' + args['_loadingid'])
																		.hide();
																div
																		.css(
																				'display',
																				'none');
																showAlert('提示: 附件上传配置不正确,请联系开发人员!')
															} else {
																jQuery(element)
																		.attr(
																				'fid',
																				data['fid']);
																jQuery(
																		'#' + elementid)
																		.attr(
																				'fid',
																				data['fid']);
																jQuery(
																		'#' + args['elementid'])
																		.val(
																				data['gid']);
																args['gid'] = data['gid'];
																jQuery('#' + args['_loadingid']).hide();
                                                                if(data["canPreview"]){
                                                                    preview.css(
                                                                        'display',
                                                                        'inline');
                                                                }
															}
														}
													})
										},
										onFilePreview:function (element,
                                                                  value, master_element) {
											var fid = jQuery(element).attr(
												'fid');
											var url = "common_upload.do?method=preview&t="
												+ new Date().getTime()
												+ '&fid=' + fid;
											window.open(url);
                                    },
										onFileDownload : function(element,
												value, master_element) {
											var fid = jQuery(element).attr(
													'fid');
											var url = "common_upload.do?method=asyncDownload&t="
													+ new Date().getTime()
													+ '&fid=' + fid;
											var jqFrom = jQuery('<form  action="" method="POST" name="_j_downloadform"' + '" id="_j_downloadform"' + '" enctype="multipart/form-data"></form>');
											jqFrom.attr("action", url);
											jQuery('body').append(jqFrom);
											jQuery('#_j_downloadform').submit();
											jQuery('#_j_downloadform').remove()
										}
									});
					jQuery('#' + args['_fileid']).MultiFile(args)
				}
			})
})(jQuery);