<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
});
		
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


//删除行
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("请先选择成员信息，再进行删除操作！");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
}
//得到已添加的学号字符串
function getYtjxhs(obj){
	var xhs = "";
	var xhobj = jQuery("[name='xh']").not(obj);
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
	});
	return xhs;
}
//学生身份添加成员时blur事件触发：再验证一遍值给出提示信息
function inputBlur(obj){
	var xhs = getYtjxhs(obj);//取到已经添加的学号，避免重复添加，验证用
    var xh = obj.value;
	var jsonPara = {xh:xh,xhs:xhs};
	var url = "ttgl_stglsq.do?method=getxsInfo";
	var jsonResult = null;
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:jsonPara,
	async: false,
	success:function(result){
			jsonResult = result;
	}
   });
	var parentObj = jQuery(obj).parent().parent();
	if(jsonResult['xh']){
		jQuery(parentObj).find("[name='xm']").text(jsonResult['xm']);
		jQuery(parentObj).find("[name='symc']").text(jsonResult['symc']==null?'':jsonResult['symc']);
		jQuery(parentObj).find("[name='xymc']").text(jsonResult['xymc']==null?'':jsonResult['xymc']);
		jQuery(parentObj).find("[name='zymc']").text(jsonResult['zymc']==null?'':jsonResult['zymc']);
		jQuery(parentObj).find("[name='bjmc']").text(jsonResult['bjmc']==null?'':jsonResult['bjmc']);
		jQuery(parentObj).find("[name='sjhm']").text(jsonResult['sjhm']==null?'':jsonResult['sjhm']);
	}else{
		jQuery(parentObj).find("[name='xm']").text("");
		jQuery(parentObj).find("[name='symc']").text("");
		jQuery(parentObj).find("[name='xymc']").text("");
		jQuery(parentObj).find("[name='zymc']").text("");
		jQuery(parentObj).find("[name='bjmc']").text("");
		jQuery(parentObj).find("[name='sjhm']").text("");
		showAlert("学号不存在或学号重复!");
	    return false;
	}
}
//增加行
function addRow(tableId){
	var html = "";
	html += "<tr name='deltr'>";
	html += "<td><input type='checkbox' name='chk'></td>"
    if(tableId == "tablebody1"){
        html += "<td><input name='xh' style='width:90%' onblur='inputBlur(this)'/></td>";
    } else{
        html += "<td><input name='tzsxh' style='width:90%' onblur='inputBlur(this)'/></td>";
    }
	html += "<td style='text-align:center'><label name = 'xm'></label></td>";
	html += "<td style='text-align:center'><label name = 'symc'></label></td>";
	html += "<td style='text-align:center'><label name = 'xymc'></label></td>";
	html += "<td style='text-align:center'><label name = 'zymc'></label></td>";
	html += "<td style='text-align:center'><label name = 'bjmc'></label></td>";
	if(tableId == "tablebody1"){
        html += "<td style='text-align:center'><label name = 'fz'>负责人</label></td>";
    } else {
        html += "<td style='text-align:center'><label name = 'fz'>团支书</label></td>";
    }
	html += "<td style='text-align:center'><label name = 'sjhm'></label></td>";
	html += "</tr>";
	jQuery("#"+tableId).append(html);
}

//选择所有行 
function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}
function fzrIsNull(){
    var flag = true;
	jQuery("[name='xm']").each(function(){
		if(jQuery(this).text() == ""){
			flag = false;
			return false;
		}
	});
	return flag;
}

function save(url){	
	 var checkids ="stlx-stqc-stjc-styx-ywzddw-zgh-stjs";
	 if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	 }
	 if(!fzrIsNull()){
		showAlert("请将负责人信息填写完整！");
		return false;
	}
    if(!validateNUM(jQuery("#strs").val())){
        showAlert("组织人数输入不正确!");
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
	
	ajaxSubFormWithFun("stglsqForm", url, function(data) {
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

/**
 * 年度组织状态td隐藏与显示
 */
function hideNdzzztTd(e){
    var b = jQuery(e).val();
    var ndzzzt = jQuery("#ndzzzt")
    if(b === "学生社团"){
        ndzzzt.removeAttr('style')
    } else {
        ndzzzt.attr('style','display:none');
    }
}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ttgl_stglsq" method="post" styleId="stglsqForm" onsubmit="return false;">
		<input type="hidden" name="flag" id="flag" />
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
                                <html:text  property="sqsj" styleId="sqsj"   size="10"
                                            onclick="return showCalendar('sqsj','y-mm-dd',true,'sqsj');"
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
								<input type='hidden' id="zgh" name="stzdls" />
								<input type="text" class="form-control" id="stzdlsxm">
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
                            </td>
                        </tr>
                        <tr>
                            <th>组织性质</th>
                            <td colspan="3">
                                <select name="stlx" id="stlx" style="width:150px;" onchange="hideNdzzztTd(this)">
                                    <option value="校级学生会组织">校级学生会组织</option>
                                    <option value="院级学生会组织">院级学生会组织</option>
                                    <option value="学生社团">学生社团</option>
                                </select>
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
								<span><font color="red">*</font>学生组织第一负责人</span>
                                <div class="btn">
                                    <button type="button" onclick="addRow('tablebody1');return false;" style="float:left">增加</button>
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
											<th width='12%' style="text-align:center">学号</th>
											<th width='10%' style="text-align:center">姓名</th>
											<th width='10%' style="text-align:center">书院</th>
											<th width='10%' style="text-align:center">学院</th>
											<th width='10%' style="text-align:center">专业</th>
											<th width='10%' style="text-align:center">班级</th>
											<th width='10%' style="text-align:center">分组</th>
											<th width='10%' style="text-align:center">电话</th>
										</tr>
									</thead>
									<tbody id="tablebody1">
										<tr>
										<td></td>
										<td style='text-align:center'><input type="hidden" name='xh' value="${jbxx.xh }"/><label>${jbxx.xh }</label></td>
										<td style='text-align:center'><label name = 'xm'>${jbxx.xm }</label></td>
										<td style='text-align:center'><label name = 'symc'>${jbxx.symc }</label></td>
										<td style='text-align:center'><label name = 'xymc'>${jbxx.xymc }</label></td>
										<td style='text-align:center'><label name = 'zymc'>${jbxx.zymc }</label></td>
										<td style='text-align:center'><label name = 'bjmc'>${jbxx.bjmc }</label></td>
										<td style='text-align:center'><label name = 'fz'>负责人</label></td>
										<td style='text-align:center'><label name = 'sjhm'>${jbxx.sjhm }</label></td>
										</tr>
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
                                <button type="button" onclick="addRow('tablebody2');return false;" style="float:left">增加</button>
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
				<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('ttgl_stglsq.do?method=add&type=save');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="save('ttgl_stglsq.do?method=add&type=submit');return false;"
										id="buttonSave">
										提交申请
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
										关 闭
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