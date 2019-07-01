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
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
		<script type="text/javascript">
			function selectDw(){
                var dwlx = jQuery("input[type='radio']:checked").val();
				if(typeof dwlx == "undefined"){
					showAlert("请选择单位类型");
					return false;
				}
                showDialog('请选择一个单位',800,500,'qgzxhmdgl.do?method=selectDw&dwlx='+dwlx);
			}
            function saveXsHmd() {

                if(jQuery("#xh").val() == ""){
                    return showAlert("请选择学生！");
                }
                var url = "qgzxhmdgl.do?method=addxshmd&type=save";
                ajaxSubFormWithFun("hmdglForm", url, function(data) {
                    if(data["message"]=="保存成功！"){
                        showAlert(data["message"],{},{"clkFun":function(){
                            if (parent.window){
                                refershParent();
                            }
                        }});
                    }else{
                        showAlert(data["message"]);
                    }
                });
            }
		</script>
	</head>
	<body>
		<html:form method="post" styleId="hmdglForm" action="/qgzxhmdgl"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>企业信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:empty name="yrdwxx">
						<tr>
							<th align="right">
								<span class="red">*</span>单位类型
							</th>
							<td align="left" colspan="3">
								<html:radio property="dwlx" value="01"></html:radio>校内单位
								<html:radio property="dwlx" value="02"></html:radio>校外单位
								<html:radio property="dwlx" value="03"></html:radio>家教家长
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>单位/家长
							</th>
							<td align="left" colspan="3">
								<input type="hidden" name="dwid" id="dwid">
								<span id="mc"></span>
								<button class="btn_01" type="button" onclick="selectDw();">选择</button>
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="yrdwxx" >
						<tr>
							<th align="right">
								<span class="red">*</span>单位/家教家长
							</th>
							<td align="left" colspan="3">
								<input type="hidden" name="dwlx" value="${yrdwxx.dwlb}">
								<input type="hidden" name="dwid" value="${yrdwxx.id}">
								<span>${yrdwxx.yrdwmc}</span>
							</td>
						</tr>
					</logic:notEmpty>
						<tr>
							<th align="right">
								原因&nbsp;
								<br />
								<font color="red">(限1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="6" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,1000);"></html:textarea>
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
										onclick="saveXsHmd();return false;"
										id="buttonSave">
										保存
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
