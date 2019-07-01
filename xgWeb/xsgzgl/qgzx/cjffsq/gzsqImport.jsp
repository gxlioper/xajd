<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
            /**
             * 下载导入模版
             * @params drmkdm 导入模块代码
             */
            function downloadTemplate(drmkdm){
                var jqFrom=jQuery("#importForm");
                var url = "qgzx_cjffsq_ajax.do?method=downloadTemplate";
                jqFrom.attr("action",url);
                jqFrom.submit();
            }

            /**
             * 验证是否选择导入文件及文件格式
             */
            function notNullImportFile(){
                var importFile=jQuery("#importFile").val();
                if(importFile == null || importFile == ""){
                    showAlert("请选择导入文件!");
                    return false;
                }
                var fileSuffix=importFile.substring(importFile.lastIndexOf(".")+1,importFile.length);
                fileSuffix = fileSuffix.toLowerCase();
                if(fileSuffix != "xls"){
                    showAlert("导入文件格式不合法，请确认!")
                    return false;
                }
                return true;
            }

            /**
             * 保存导入（这里省略了下一步的导入配置）
             */
            function selectImport(){
                if(!notNullImportFile()){
                    return false;
                }
                var url = "qgzx_cjffsq_ajax.do?method=saveImport";
                ajaxSubFormWithFun("importForm",url,function(data){
                    var file = jQuery("#importFile")
                    file.after(file.clone().val(""));
                    file.remove();

                    if(data["result"]){
                        jQuery("#cwsj").html("<font>无错误数据</font> ");
                        showImportResult(data["totalCount"],data["totalCount"],0,"成功导入");
                    }else{
                        if(data["error"]=="01"){
                            showAlert(data["message"]);
                        }else{
                            var filename = data["errorTipsExcelName"];
                            jQuery("#cwsj").html("<a href='javascript:void(0);' class='name' onclick=\"downloadError('"+filename+"')\">点击下载异常数据</a> ");
                            showImportResult(data["totalCount"],data["totalCount"]-data["errorCount"],data["errorCount"],"可导入");
                        }
                    }
                });
            }

            /**
             * 显示导入结果提示信息
             */
            function showImportResult(drzs,cgs,cws,tf){
                var reslutMsg="<font color='blue'>总计["+drzs+"]条</font>，<font color='green'>"+tf+"["
                    +cgs+"]条</font>，<font color='red'>错误["+cws+"]条</font>。";
                jQuery("#importResult").html(reslutMsg);
                jQuery("#dr_result").show();
            }

            /**
             * 下载错误数据
             * @params drmkdm 导入模块代码
             */
            function downloadError(filename){
                var jqFrom=jQuery("#importForm");
                var url = "qgzx_cjffsq_ajax.do?method=downloadImportError&filename="+filename;
                jqFrom.attr("action",url);
                jqFrom.submit();
            }
		</script>
	</head>
	<body>
		<form id="importForm" name="importForm" action="zfxg/drdcsj/import_importData.html" method="post" enctype="multipart/form-data">
			<input type="hidden" id="drmkdm" name="drmkdm" value="${drmbxx.drmkdm}">
			<div class="tab">
			<table width="100%" border="0" class=" formlist" cellpadding="0"
					cellspacing="0">
					<thead>
						<tr>
							<td colspan="4">
								<span>导入数据</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								模板名称
							</th>
							<td colspan="3">
								${drmbxx.drmkmc}
							</td>
						</tr>
						<tr>
							<th>
								模板下载
							</th>
							<td colspan="3">
								<a href="javascript:void(0);" class="name" onclick="downloadTemplate();">下载EXCEL模板</a> 
							</td>
						</tr>
						<tr>
							<th>
								上传数据
							</th>
							<td colspan="3">
								<input type="file" name="importFile" id="importFile"
									style="width: 200px;" />
								<span style="color:red;font-style:normal"><span>提示：</span><span>允许导入Excel文件</span></span>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="drBtn" onclick="selectImport();" type="button" class="ui_state_highlight">
										导入
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<table width="100%" border="0" class=" formlist" cellpadding="0" id="dr_result"
					style="margin-top: 2px; display: none;" cellspacing="0" >
					<tbody>
						<tr>
							<th width="20%">
								导入结果
							</th>
							<td colspan="3" id="importResult" style="font-weight: bold">
								&nbsp;&nbsp;无
							</td>
						</tr>
						<tr>
							<th>
								异常数据
							</th>
							<td colspan="3" id="cwsj">
								<font>无错误数据</font>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="drts">
					<div id="rule_header">
						<table width="100%" border="0" class=" formlist" cellpadding="0" style="margin-top: 2px;" 
						cellspacing="0" align="center">
						<thead>
							<tr>
								<td colspan="6">
									<span>导入规则</span>
								</td>
							</tr>
							<tr style="background-color: #FFFACD;font-weight: bold">
								<td align='right'  style="width: 15%" id="rule_header_1">
									列名称
								</td>
								<td align='center' style="width:10%"  id="rule_header_2">
									主键
								</td>
								<td align='center'  style="width:10%"  id="rule_header_3">
									是否唯一
								</td>
								<td align='center'  style="width:10%"  id="rule_header_4">
									不可为空
								</td>
								<td align='center'  style="width:10%"  id="rule_header_5">
									最大长度
								</td>
								<td align='center'  style="width:55%"  id="rule_header_6">
									数据格式
								</td>
							</tr>
						</thead>
					</table>
					</div>
					<div id="rule_div" style="height: 220px; overflow-x: hidden; overflow-y: scroll;">
						<table width="100%" border="0" class=" formlist" cellpadding="0"  frame="vsides"
						cellspacing="0" align="center">
							<tbody>
								<logic:iterate id="drgzxx" name="drgzxxList" indexId="rowid">
				                     <tr>
										<td align='right' style="width: 15%">${drgzxx.drlmc }</td>
										<td align='center' style="width:10%">
											${drgzxx.sfzj==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">
											${drgzxx.sfwy==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">
											${drgzxx.sfbt==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">${drgzxx.zdcd}</td>
										<td align='center' style="width:55%">${drgzxx.gshxx}</td>
									 </tr>
								 </logic:iterate>
							</tbody>
						</table>
					</div>
					<div id="drts-rule-table-bottom" style="border-bottom: 1px solid #B0CBE0;background-color: #eee;"></div>
				</div>
			</div>
			<div style="height: 30px;"></div>
		</form>
	</body>
</html>
	