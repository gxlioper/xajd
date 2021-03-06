<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
	<script type="text/javascript">
        function saveZjjl() {
            var checkId = 'zjmb-jsh-xm-zcyy';
            if(!checkNotNull(checkId)){
                showAlert("请将带<span class='red'>*</span>的必填项填写完整！");
                return false;
            }
            var url = 'xyfd_yjgy.do?method=yjzj&type=save';
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlert(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        
        function selectJs() {
			var zjmb = jQuery("#zjmb").val();
			var url = "xyfd_yjgy.do?method=selectJs&zjmb=" + zjmb ;
            showDialog("选择教师", 800, 350, url);
        }

        function changeMb() {
			jQuery("#jsh").val("");
			jQuery("#xm").val("");
        }
	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_yjgy" method="post" styleId="demoForm">
	<input type="hidden" id="xh" name="xh" value="${model.xh}"/>
	<div style='width:100%;height:200px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="2">
					<span>预警转介</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="30%"><span class="red">*</span>转介至</th>
				<td width="70%">
					<html:select property="zjmb" styleId="zjmb" name="model" onchange="changeMb()">
						<option value="js">教师</option>
						<option value="xs">学生</option>
						<option value="fdy">辅导员</option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>教师姓名</th>
				<td>
					<input type="hidden" id="jsh" name="jsh" value="${model.xh}"/>
					<input type="text" id="xm" name="xm" readonly="readonly" value="${model.xm}"/>
					<button class="btn_01" type="button" onclick="selectJs()">选择</button>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>转出原因</th>
				<td>
					<html:textarea name="model" property="zcyy" styleId="zcyy" style="width: 100%;resize:none;"></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button type="button" type="button" onclick="saveZjjl();return false;" >
							保存记录
						</button>
						<button type="button" name="关 闭" onclick="iFClose();">
							关 闭
						</button>
					</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
	</div>
</html:form>
</body>
</html>

 