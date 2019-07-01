<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<style type="text/css">
             .note{
                position:absolute;line-height:20px;padding:3px 5px;top:20px;
             }
		</style>
		<script type='text/javascript'>
		function saveFdgl(type) {
			var flag = false;
			var ids = "fdkm-fdsj";
			var kfxydm = "";
			if(check(ids) == false){
				showAlert("请将带<font color='red'>*</font>的项目填写完整");
				return false;
			}

			jQuery("input:checkbox[name=xydm]:checked").each(function(index){
				if(flag){
					kfxydm += ",";
				}else{
					flag = true;
				}
				kfxydm += jQuery(this).val();
			});
			jQuery("#kfxydm").val(kfxydm);
			if(kfxydm==""){
				showAlert("请将带<font color='red'>*</font>的项目填写完整");
				return false;
			}
			var url = "zzyrxmglbfdgl.do?method=updateBfdgl&type="+type;
			ajaxSubFormWithFun("bfdglForm", url, function(data) {
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
		//勾选已选中的项目
		function initSet(){	
			var url = "zzyrxmglbfdgl.do?method=kfxydmCx";
			jQuery.post(url, {
				fdfbid : jQuery("#fdfbid").val()
			}, function(data) {
				for ( var i = 0; i < data.length; i++) {
					var o = data[i];
					jQuery("input:checkbox[name=xydm][value="+o.kfxydm+"]").attr("checked","checked");
				}
			}, 'json');
		}

		jQuery(function() {
			initSet();
		});
		function selectAll(){
			var isCheck=jQuery("#qx").is(':checked');
			jQuery("input:checkbox[name=xydm]").each(function() {
		        this.checked = isCheck;       //循环赋值给每个复选框是否选中
		    });
		}

		jQuery(function(){
			jQuery("#fbbz").focus(function(){
				jQuery("#note").css("display","none");
			})

			jQuery("#fbbz").blur(function(){
				var content = this.value;
				if(content == null || content == ""){
					jQuery("#note").css("display","block");
				}else{
					jQuery("#note").css("display","none");
				}
			})

			jQuery("#fbbz").blur();
		})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglbfdgl" method="post" styleId="bfdglForm" onsubmit="return false;">
			<input type="hidden" name="fbrxh" value="${jbxx.xh }"/>
			<input type="hidden" name="fdfbid" id="fdfbid" value="${rs.fdfbid }"/>
			<input type="hidden" id="kfxydm" name="kfxydm" value=""/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>发布人信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>姓名</th>
						<td>${jbxx.xm }</td>
						<th>学号</th>
						<td>${jbxx.xh }</td>
					</tr>
					<tr>
						<th>所在学院</th>
						<td>${jbxx.xymc }</td>
						<th>专业</th>
						<td>${jbxx.zymc }</td>
					</tr>
					<tr>
						<th>班级</th>
						<td>${jbxx.bjmc }</td>
						<th>联系电话</th>
						<td>${jbxx.sjhm }</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>信息详情</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>需要辅导科目
							</th>
							<td width="30%">
								<html:text property="fdkm" styleId="fdkm" maxlength="10"></html:text>
							</td>
							</td>
							<th>
								<span class="red">*</span>辅导时间
							</th>
							<td>
								<html:textarea property='fdsj' style="width:98%" styleId="fdsj" rows='4' onblur="checkLen(this,250);"/>
							</td>
			      		</tr>
						<tr>
							<th><span class="red">*</span>开放<bean:message key="lable.xb" /></th>
							<td colspan="3">
								<table width="100%" border="0" class="formlist">
									<tr>
										<td style="border:0px;"><input type='checkbox' value="qx" name="qx" id="qx" onclick="selectAll();"/>全选</td>
										<td style="border:0px;"></td>
										<td style="border:0px;"></td>
									</tr>
									<tr>
									<logic:iterate name="xyList" id="l" indexId="index">
										<td style="border:0px;">
										<span style="word-break:break-all;margin-right:15px;">
										<input type='checkbox' value='${l.xydm }' name='xydm'/>${l.xymc }</span>
										</td>
										<%if(index.intValue()%3==2 && index.intValue() != 0){ %>
										</tr><tr>
										<%} %>
									</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								申请说明
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<div style="position:relative;">							
									<html:textarea property='fbbz' style="width:98%" styleId="fbbz" rows='8' onblur="checkLen(this,500);"/>
									<div id="note" class="note">
            							<font color="#777">1、个人薄弱学科、薄弱环节</font></br>
            							<font color="#777">2、预期目标</font></br>
            							<font color="#777">3、辅导周期</font></br>
            							<font color="#777">4、性别要求</font></br>
            							<font color="#777">5、年级要求</font></br>
        							</div>
								</div>
							</td>
			      		</tr>						
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
									<button type="button" onclick="saveFdgl('save');">
										保存
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

