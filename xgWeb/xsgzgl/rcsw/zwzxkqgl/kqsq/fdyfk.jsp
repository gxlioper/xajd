<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}
		function fdyfk(type) {
			var flg=true;
			var qqxsTr = jQuery("#tbody_qqryxx tr");
			var objArr= [];
			jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
				var obj = {};
				if (flg){
					var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
					var xh= jQuery(n).find("td").eq(1).text();
					var kkjs = jQuery(n).find("input[name=kkjs]").val();
					var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
					obj.xh=xh;
					obj.kkjs=kkjs;
					obj.qqlxdm=qqlx;
					obj.ylzd1=ylzd1;
					objArr.push(obj);
					flg = (qqlx != "" );
				}
			});
			if (!flg) {
				showAlert("请将必填项填写完整！");
				return false; 
			}
			if (type!="save"&& jQuery("#qqrs").val() != qqxsTr.length) {
				showAlert('缺勤人数为' + jQuery("#qqrs").val() + '人，您添加了' + qqxsTr.length
						+ '条记录！');
				return false;
			}
			var objStr = JSON.stringify(objArr);
			jQuery("#objStr").val(objStr);
			var url = "zwzxKqsq.do?method=saveEditKqsq&type=" + type;
			ajaxSubFormWithFun("ZwzxKqsqForm", url, function(data) {
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
	<body style="width: 100%">
		<html:form action="/zwzxKqsq" method="post" styleId="ZwzxKqsqForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<html:hidden property="bjdm" styleId="bjdm"/>
			<html:hidden property="bjmc" styleId="bjmc"/>
			<html:hidden property="qqrs" styleId="qqrs"/>
			<html:hidden property="sqsj" styleId="sqsj"/>
			<input type="hidden" id="objStr" name="objStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>自习考勤反馈</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${kqsqForm.xn}
							</td>
							<th width="20%">
								抽查日期
							</th>
							<td width="30%">
								${kqsqForm.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${kqsqForm.xqmc}
							</td>
							<th width="20%">
								抽查类型
							</th>
							<td width="30%">
								${kqsqForm.cclxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								班级
							</th>
							<td width="30%">
								${kqsqForm.bjmc}
							</td>
							<th width="20%">
								应到人数
							</th>
							<td width="30%">
								${kqsqForm.ydrs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								实到人数
							</th>
							<td width="30%">
								${kqsqForm.sdrs}
							</td>
							<th width="20%">
								缺勤人数
							</th>
							<td width="30%">
								${kqsqForm.qqrs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								纪律分
							</th>
							<td width="20%" colspan="3">
								${kqsqForm.jlf}
							</td>
						</tr>
						<tr>
						    <th>
								备注
							</th>
							<td colspan="3">
								${kqsqForm.bz}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								除缺勤外的违纪人数
								<br />
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								${kqsqForm.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>缺勤学生信息</span>
							</th>
						</tr>
					</thead>
				 </table>
				 </div>
				<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;"> 
					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcZjRyxx();" style="display: none;"></button>
							<button type="button" onclick="addQqxs();return false;" class="btn_01">增加学生</button>
							<button type="button" onclick="delQqxs();return false;" class="btn_01">删除学生</button>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='15%'><font color="red">*</font>缺勤类型</td>
							<td width='15%'>旷课节数</td>
							<td width='35%'>缺勤备注</td>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:iterate id="i" name="qqxsList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
						<td>
						<html:select property="qqlxdm" styleId="qqlxdm_${index01}" value="${i.qqlxdm}" onchange="changeQqlx(${index01})">
								<option value=""></option>
								<html:options collection="qqlxList" property="qqlxdm" labelProperty="qqlxmc"/>
						</html:select>
						</td>
						<td>
								<html:text property="kkjs" styleId="kkjs_${index01}" maxlength="3" value="${i.kkjs}"
									onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
						</td>
						<td width="30%">
								<html:text property="ylzd1" styleId="ylzd1" value="${i.ylzd1}" onblur="chLeng(this,500)"></html:text>
							</td>
						</tr>
						</logic:iterate>
						</tbody>
				</table>
				</div>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="fdyfk('save');">
										保    存
									</button>
									<button type="button" onclick="fdyfk('submit');">
										提交申请
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

