<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript">
jQuery(function(){
	stzdlsInit();
	//jQuery("#stzdlsxm").val('${zgh}');
    hideNdzzztTd();
});
		//删除行
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("请先选择记录，再进行删除操作！");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
}

//老师身份增加学生
function addRowDialog(appandTableId){
    var url = "ttgl_stgl.do?method=selectStu&appandTableId="+appandTableId;
    var title = "负责人选择";
	showDialog(title, 770, 550, url);
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}		
function stzdlsInit(){
	var autoSetting = {
		dataTable:"fdyxxb",
		dataField:"xm",
		dataFieldKey:"zgh",
		dataFieldKeyId:"zgh",
		scrollHeight:180										
	}
	// 模糊搜索下拉【项目名称】
	jQuery("#stzdlsxm").setAutocomplete(autoSetting);
}
function saveForm(url){	
	 var checkids ="stlx-stqc-stjc-styx-ywzddw-zgh-stjs";
	 if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	 }
	 var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	 if(!reg.test(jQuery("#styx").val())){
		 showAlert("邮箱格式不正确!");
		 return false;
	 }
	if(jQuery("[name='xh']").length == 0){
			showAlert("请至少填写一位社团负责人!");
			return false;
	}else{
		if(jQuery("[name='xh']").length >5){
				showAlert("社团负责人不超过5人!");
				return false;
		}
	}
	
	var url = "ttgl_stgl.do?method=updatest&type=update";
	ajaxSubFormWithFun("stglForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	}
/**
 * 年度组织状态td隐藏与显示
 */
function hideNdzzztTd(){
    jQuery("#stlx").change(function(e){
        var b = jQuery("#stlx").val();
        var ndzzzt = jQuery("#ndzzzt");
        if(b === "学生社团"){
            ndzzzt.removeAttr('style')
        } else {
            ndzzzt.attr('style','display:none');
        }
    })
    jQuery("#stlx").trigger('change');
}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ttgl_stgl" method="post" styleId="stglForm" onsubmit="return false;">
		<html:hidden property="jgid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>学生组织信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%"><font color="red">*</font>学生组织全称</th>
						<td width="30%">
							<html:text styleId="stqc" property="stqc"  maxlength="50"/>
						</td>
						<th width="20%"><font color="red">*</font>学生组织简称</th>
						<td width="30%">
							<html:text styleId="stjc" property="stjc"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><font color="red">*</font>成立时间</th>
						<td width="30%">
							<html:text  property="clsj" styleId="clsj"   size="10"
										onclick="return showCalendar('clsj','y-mm-dd',true,'clsj');"
										onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
						<th width="20%"><font color="red">*</font>组织人数</th>
						<td width="30%">
							<html:text styleId="strs" property="strs"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>学生组织邮箱</th>
						<td>
							<html:text styleId="styx" property="styx"  maxlength="50"/>
						</td>
						<th>学生组织公众号</th>
						<td>
							<html:text styleId="gzh" property="gzh"  maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>指导老师</th>
						<td>
							<input type='hidden' id="zgh" name="stzdls" value="${zgh}" />
							<input type="text" class="form-control" id="stzdlsxm" value="${zdls}">
						</td>
						<th><font color="red">*</font>指导单位</th>
						<td>
							<html:select property="ywzddw" style="width:150px" styleId="ywzddw">
								<html:option value="">--请选择--</html:option>
								<html:options collection="bmList" property="bmdm" labelProperty="bmqc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>办公室地址</th>
						<td colspan="3">
							<html:text styleId="bgsdz" property="bgsdz" style="width:200px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>学生组织经费来源</th>
						<td colspan="3">
							<logic:iterate id="item" collection="${xszzjflyList}">
								<html:checkbox property="jflyArray" value="${item.dm}">${item.mc}</html:checkbox>
							</logic:iterate>
								<%--后台传过来的checkbox选中--%>
							<script type="text/javascript">
                                jQuery(function(){
                                    var r = '${stglForm.jfly}';
                                    var result = r.split(",");
                                    for(var i=0;i<result.length;i++){
                                        jQuery("input[value='"+result[i]+"'").attr("checked","checked");
                                    }
                                })
							</script>
						</td>
					</tr>
					<tr>
						<th>组织性质</th>
						<td colspan="3">
							<html:select property="stlx" style="width:150px" styleId="stlx">
								<html:option value="校级学生会组织"></html:option>
								<html:option value="院级学生会组织"></html:option>
								<html:option value="学生社团"></html:option>
							</html:select>
						</td>
					</tr>
					<tr id="ndzzzt" style="display:none">
						<th>年度组织状态</th>
						<td colspan="3">
							<html:select property="xn" style="width:150px" styleId="xn">
								<html:option value="${currXn}"></html:option>
							</html:select>
							<html:select property="ndzzzt" style="width:150px" styleId="ndzzzt">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ndzztList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>组织类别</th>
						<td colspan="3">
							<html:select property="zzlb" style="width:150px" styleId="zzlb">
								<html:options collection="zzlbList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>学生组织宗旨<br><font color="red">限制字数（200）</font><br/></th>
						<td colspan="3">
							<html:textarea property="stjs" styleId="stjs" onblur="chLengs(this,200);"
										   style="width:90%;" rows="5"></html:textarea>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span><font color="red">*</font>学生组织负责人</span>
							<div class="btn">
								<button type="button" onclick="addRowDialog('tablebody');return false;" style="float:left">增加</button>
								<button type="button" onclick="delRow();return false;" style="float:left">删除</button>
							</div>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr class="h">
							<td colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='10%' style="text-align:center">学号</th>
											<th width='10%' style="text-align:center">姓名</th>
											<th width='10%' style="text-align:center">书院</th>
											<th width='10%' style="text-align:center">学院</th>
											<th width='10%' style="text-align:center">专业</th>
											<th width='10%' style="text-align:center">班级</th>
											<th width='10%' style="text-align:center">分组</th>
											<th width='10%' style="text-align:center">电话</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="fzrxxInfo" indexId="index">
											<tr name='deltr'>
												<td style='text-align:center'><input type='checkbox' name='chk'></td>
												<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
												<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
												<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
												<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
												<td style='text-align:center'>
													<select name="fzrfz">
														<logic:equal name="i" property="fzrfz" value="第一负责人">
															<option value="第一负责人" selected="selected">第一负责人</option>
															<option value="第二负责人">第二负责人</option>
															<option value="第三负责人">第三负责人</option>
															<option value="第四负责人">第四负责人</option>
															<option value="第五负责人">第五负责人</option>
														</logic:equal>
														<logic:equal name="i" property="fzrfz" value="第二负责人">
															<option value="第一负责人">第一负责人</option>
															<option value="第二负责人" selected="selected">第二负责人</option>
															<option value="第三负责人">第三负责人</option>
															<option value="第四负责人">第四负责人</option>
															<option value="第五负责人">第五负责人</option>
														</logic:equal>
														<logic:equal name="i" property="fzrfz" value="第三负责人">
															<option value="第一负责人">第一负责人</option>
															<option value="第二负责人">第二负责人</option>
															<option value="第三负责人" selected="selected">第三负责人</option>
															<option value="第四负责人">第四负责人</option>
															<option value="第五负责人">第五负责人</option>
														</logic:equal>
														<logic:equal name="i" property="fzrfz" value="第四负责人">
															<option value="第一负责人">第一负责人</option>
															<option value="第二负责人">第二负责人</option>
															<option value="第三负责人">第三负责人</option>
															<option value="第四负责人" selected="selected">第四负责人</option>
															<option value="第五负责人">第五负责人</option>
														</logic:equal>
														<logic:equal name="i" property="fzrfz" value="第五负责人">
															<option value="第一负责人">第一负责人</option>
															<option value="第二负责人">第二负责人</option>
															<option value="第三负责人">第三负责人</option>
															<option value="第四负责人">第四负责人</option>
															<option value="第五负责人" selected="selected">第五负责人</option>
														</logic:equal>
														<logic:equal name="i" property="fzrfz" value="">
															<option value="第一负责人">第一负责人</option>
															<option value="第二负责人">第二负责人</option>
															<option value="第三负责人">第三负责人</option>
															<option value="第四负责人">第四负责人</option>
															<option value="第五负责人">第五负责人</option>
														</logic:equal>
													</select>
												</td>
												<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span><font color="red">*</font>团支部书记信息</span>
							<div class="btn">
								<button type="button" onclick="addRowDialog('tablebody2');return false;" style="float:left">增加</button>
								<button type="button" onclick="delRow();return false;" style="float:left">删除</button>
							</div>
						</th>
					</tr>
					</thead>
                    <tbody>
                    <tr class="h">
                        <td colspan="7">
                            <table width="100%" >
                                <thead>
                                <tr>
                                    <th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
                                    <th width='10%' style="text-align:center">学号</th>
                                    <th width='10%' style="text-align:center">姓名</th>
                                    <th width='10%' style="text-align:center">书院</th>
                                    <th width='10%' style="text-align:center">学院</th>
                                    <th width='10%' style="text-align:center">专业</th>
                                    <th width='10%' style="text-align:center">班级</th>
                                    <th width='10%' style="text-align:center">分组</th>
                                    <th width='10%' style="text-align:center">电话</th>
                                </tr>
                                </thead>
                                <tbody id="tablebody2">
                                <logic:iterate id="i" name="tzsxxInfo" indexId="index">
                                    <tr name='deltr'>
                                        <td style='text-align:center'><input type='checkbox' name='chk'></td>
                                        <td style='text-align:center'><input name='tzsxh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
                                        <td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
                                        <td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
                                        <td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
                                        <td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
                                        <td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
                                        <td style='text-align:center'><label name = 'fz'>团支书</label></td>
                                        <td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
                                    </tr>
                                </logic:iterate>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th align="right" width="20%">
                            上传附件
                        </th>
                        <td colspan="3">
                            <html:hidden property="filepath" styleId="filepath" />
                            <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                            <script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 4,
                                        //后缀
                                        accept : 'png|gif|jpg|zip|rar|doc|docx',
                                        //最大文件大小 单位M
                                        maxsize: 10,
                                        //存放附件的隐藏域的id
                                        elementid : 'filepath'
                                    });
                                });
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <th>说明</th>
                        <td colspan="3">
                            <span>附件1 ：业务指导单位确认函（负责人签字 单位盖章）<br/></span>
                            <span>附件2 ：指导老师确认函（指导老师签字 含指导老师基本情况）<br/></span>
                            <span>附件3 ：学生组织拟定品牌活动介绍<br/></span>
                            <span>附件4 ：学生组织章程</span>
                        </td>
                    </tr>
                    </tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
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