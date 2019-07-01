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
//增加--保存草稿或提交
function save(url){
	var checkId ="pjjszgh-pj-ylzd1";
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var pj = jQuery("#pj").val();
	if(pj.length < 200||pj.length > 1000){
		return showAlert("评价输入项字数范围为200-1000！");
	}
	ajaxSubFormWithFun("jspjsqForm", url, function(data) {
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
		<html:form method="post" styleId="jspjsqForm" action="/dekt_jspjglsq"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>评价信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="15%">
								学年
							</th>
							<td align="left" width="30%">
								${dqxn}
							</td>
							<th align="right" width="20%">
								学期
							</th>
							<td align="left" width="30%">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right">
								<logic:equal name="lx" value="0"><span class="red">*</span></logic:equal>评价老师
							</th>
							<td align="left">
							<logic:equal name="lx" value="0">
							<!--<html:text styleId="pjjszgh" property="pjjszgh" />-->
									<html:text property="pjjszgh" styleId="pjjszgh" style="width:100px" value="${jsList.zgh}" readonly="true"/>
									<button type="button" onclick="showDialog('教师选择',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
							 </logic:equal >
							<logic:notEqual name="lx" value="0">
							<html:hidden property="pjjszgh" styleId="pjjszgh" value ="${jszgh}"/>
									${jsxm}
							</logic:notEqual>
							</td>
							<th align="right">
								<span class="red">*</span>认识途径&nbsp;
							</th>
							<td>
								<html:select property="ylzd1" styleId="ylzd1">
											<html:option value="">--请选择--</html:option>
											<html:options collection="rstjList" property="rstjdm" labelProperty="rstjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>评价&nbsp;
								<br />
								<font color="red">(不少于200字,<br />限1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="7" property="pj" styleId="pj" 
									style="width:97%" ></html:textarea>
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
									<button type="button"
										onclick="save('dekt_jspjglsq.do?method=add&type=save');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="save('dekt_jspjglsq.do?method=add&type=submit');return false;"
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
