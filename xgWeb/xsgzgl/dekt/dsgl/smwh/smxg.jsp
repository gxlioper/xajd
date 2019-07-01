<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
			<script type="text/javascript">
/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


//修改--保存
function saveForm(){
	var checkId ="nj-ssm-cbs-author-lx";
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var url = "dekt_smwhgl.do?method=update&type=update";
	ajaxSubFormWithFun("smwhForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="smwhForm" action="/dekt_smwhgl"
			enctype="multipart/form-data">
			<html:hidden property="smid"  styleId="smid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>书本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								<span class="red">*</span>年级
							</th>
							<td align="left" >
									<select name="nj" id="nj" style="width:150px;">
									<option value="">--请选择--</option>	
									<option value="大一" <logic:equal value="大一" name="nj">selected = "selected" </logic:equal>>大一</option>
									<option value="大二" <logic:equal value="大二" name="nj">selected = "selected" </logic:equal>>大二</option>
									<option value="大三" <logic:equal value="大三" name="nj">selected = "selected" </logic:equal>>大三</option>
									<option value="大四" <logic:equal value="大四" name="nj">selected = "selected" </logic:equal>>大四</option>
								</select>
							</td>
							<th align="right">
								<span class="red">*</span>书名
							</th>
							<td align="left" >
								<html:text styleId="ssm" property="ssm"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>出版社
							</th>
							<td align="left" >
								<html:text styleId="cbs" property="cbs"  maxlength="50"/>
							</td>
							<th align="right">
								<span class="red">*</span>作者
							</th>
							<td align="left" >
								<html:text styleId="author" property="author"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>类型
							</th>
							<td align="left" >
								<select name="lx" id="lx" style="width:150px;">
									<option value="">--请选择--</option>	
									<option value="选读" <logic:equal value="选读" name="lx">selected = "selected" </logic:equal>>选读</option>
									<option value="必读" <logic:equal value="必读" name="lx">selected = "selected" </logic:equal>>必读</option>
								</select>
							</td>
							<th align="right">
								电子书链接
							</th>
							<td align="left" >
								<html:text styleId="ebook" property="ebook"  maxlength="500"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								是否推荐
							</th>
							<td align="left" >
								<html:radio property="sftj" value="1">是</html:radio>
								<html:radio property="sftj" value="0">否</html:radio>
							</td>
						</tr>
					<tr>
						<th align="right">
							封面上传
						</th>
						<td colspan="3">
							<html:hidden property="stp" styleId="stp" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 1,
                                        //后缀
                                        accept : 'png|gif|jpg',
                                        //最大文件大小 单位M
                                        maxsize: 4,
                                        //存放附件的隐藏域的id
                                        elementid : 'stp'
                                    });
                                });
							</script>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
