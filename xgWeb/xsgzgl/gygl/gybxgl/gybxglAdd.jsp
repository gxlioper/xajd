<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxComm.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script	type="text/javascript">
	function getBxlbzxList(){
		var bxlb=jQuery("#bxlb").val();
		jQuery.post('gyglnew_gybxgl.do?method=getBxlbzxList',{bxlb:bxlb},function(data){
				var option = "<option value=''></option>";
				for(var i = 0; i < data.length; i++){
					option +="<option value='"+data[i].bxlbzxdm+"'>"+data[i].bxlbzxmc+"</option>";
				}
				jQuery('#bxlbzxdm').empty().append(option);	
				jQuery("#bxlbzxdm").val(jQuery("#bxlbzxdm_value").val());		
		},'json');
	}	
		jQuery(function(){
			var clzt = jQuery("#clzt").val();
			
			if("已处理" == clzt || "不处理" == clzt){
				jQuery("#buttonSave").attr("disabled", "disabled");
			}
			
			jQuery("#buttonSave").click(function(){
				var bxnr = jQuery("[name=bxnr]").val();
				if(bxnr == null || bxnr ==""){
					alertInfo("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
			    	return false;
				}
				
				var checkids = "xh-jjcd-lxdh";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}
				var url = 'gyglnew_gybxgl.do?method=gybxglAdd&doType=save';
				ajaxSubFormWithFun("gyglnewGybxglForm",url,function(data){
					if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
				});
			});
		});
		jQuery(function(){
			getBxlbzxList();
		})
	</script>
</head>
<body>
	<html:form action="/gyglnew_gybxgl" method="post" styleId="gyglnewGybxglForm">
		<input type="hidden" name="pk" value="${param.pk }"/>
		<input type="hidden" id="clzt" value="${jbxx.clzt }"/>
		<input type="hidden" id="lddm" name="lddm" value="${jbxx.lddm}"/>
		<input type="hidden" id="qsh" name="qsh" value="${jbxx.qsh}"/>
		<input type="hidden" id="cwh" name="cwh" value="${jbxx.cwh}"/>
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
				<%@ include file="/xsgzgl/gygl/comm/selectStudent.jsp" %>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>报修信息</span>
					</th>
				</tr>
			</thead>
			<tbody>		
			<tr>
				<th><font color="red">*</font>紧急程度</th>
				<td>
					<html:select styleId="jjcd" property="jjcd" value="${jbxx.jjcd}" style="width:100px"> 
						<html:option value="一般">一般</html:option>
						<html:option value="紧急">紧急</html:option>
					</html:select>
				</td>
				<th><font color="red">*</font>联系电话</th>
				<td>
					<html:text styleId="lxdh"  property="lxdh" value="${jbxx.lxdh}" onkeyup="checkInputLxfx(this);" maxlength="13"></html:text>
				</td>
			</tr>
			<tr>
			<th>报修类别</th>
				<td>
					<html:select property="bxlb" styleId="bxlb" value="${jbxx.bxlb }" disabled="false" style="width:100px" onchange="getBxlbzxList()">
						<html:option value=""></html:option>
						<html:options collection="bxlbList" property="bxlbdm"
							labelProperty="bxlbmc" />
					</html:select>
				</td>
			<th>报修类别子项</th>
				<td>
				<input type="hidden" id="bxlbzxdm_value" value="${jbxx.bxlbzxdm}"/>
					<html:select property="bxlbzxdm" styleId="bxlbzxdm" value="${jbxx.bxlbzxdm }" disabled="false" style="width:100px">
						<html:option value=""></html:option>
						<html:options collection="bxlbzxList" property="bxlbzxdm"
							labelProperty="bxlbzxmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				
				<th>期望维修时间</th>
				<td colspan="3">
					<html:text property="qwwxsj_ks" styleId="qwwxsj_ks" style="width:100px" value="${jbxx.qwwxsj_ks}"
						readonly="readonly" onclick="return showCalendar(this.id,'yyyy-MM-dd',true,'qwwxsj_js')"></html:text>
					至
					<html:text property="qwwxsj_js" styleId="qwwxsj_js" style="width:100px" value="${jbxx.qwwxsj_js}"
						readonly="readonly" onclick="return showCalendar(this.id,'yyyy-MM-dd',false,'qwwxsj_ks')"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right">
						附件信息
					</th>
					  <td colspan="3">
								<html:hidden property="filepath" styleId="filepath" value="${jbxx.filepath}" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
						   </td>
				
			</tr>
			<tr>
				<th>
					<font color="red">*</font>报修内容
					<br /><font color="red">(限制字数500)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bxnr' name="bxnr" styleId="bxnr" style="width:95%;word-break:break-all;width:97%" rows='4' value="${jbxx.bxnr}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" >保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>
