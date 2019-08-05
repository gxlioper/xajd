
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
	<script type="text/javascript">

        function saveFdkc(){
            var checkId = 'kcmc-kkdw-fdjs-sqr-sqyy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "xyfd_fdkcjg.do?method=save&type=add";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
        function selectFdjs() {
            var xsjs = jQuery('input:radio[name="xsjs"]:checked').val();
            if(xsjs=="学生"){
                showDialog("朋辈志愿者列表",700,500,"xyfd_fdkcsq.do?method=selectPb");
            }else {
                showDialog("辅导教师列表",700,500,"xyfd_fdkcsq.do?method=selectFdjs");
            }

        }
        jQuery(function () {
            jQuery('input:radio[name="xsjs"]').change(function () {
                jQuery("#fdjs").val("");
                jQuery("#xm").val("");
                jQuery.each(jQuery("input:checkbox"),function (element,index) {
                    jQuery(this).attr("disabled",false);
                    jQuery(this).prop("checked","");
                })
            })

        })

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdkcjg" method="post" styleId="demoForm">
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>辅导课程信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%"><span class="red">*</span>课程名称</th>
				<td width="20%">
					<input type="text" id="kcmc" name="kcmc"/>
				</td>
				<th width="20%"><span class="red">*</span>开课单位</th>
				<td width="40%">
					<input type="text" id="kkdw" name="kkdw"/>
				</td>
			</tr>
			<tr>
				<th>学生/教师</th>
				<td>
					<logic:equal value="true" name="isAdmin">
						<input type="radio" name="xsjs" value="学生" checked="checked"/>学生
					</logic:equal>
					<logic:equal value="stu" name="userType">
						<input type="radio" name="xsjs" value="学生" checked="checked"/>学生
					</logic:equal>
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="true" name="isAdmin">
							<input type="radio" name="xsjs" value="教师"/>教师
						</logic:equal>
						<logic:notEqual value="true" name="isAdmin">
							<input type="radio" name="xsjs" value="教师" checked="checked"/>教师
						</logic:notEqual>
					</logic:notEqual>
				</td>
				<th><span class="red">*</span>辅导教师</th>
				<td>
					<input type="hidden" name="fdjs" id="fdjs" />
					<input type="text" id="xm" name="xm" readonly="readonly"/>
					<button class="btn_01" type="button" onclick="selectFdjs();">选择</button>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>申请人</th>
				<td colspan="3">
					<input type="text" id="sqr" name="sqr"/>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>申请原因</th>
				<td colspan="3">
					<textarea name='sqyy' id='sqyy' style='width: 500px;height: 100px;'></textarea>
				</td>

			</tr>
			<tr>
				<th rowspan="7">答疑时间</th>
				<th>周一</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周二</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周三</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周四</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周五</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周六</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周日</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="sund" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>

			</tbody>
		</table>
	</div>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button type="button" type="button" onclick="saveFdkc();return false;" >
							保存
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
</html:form>
</body>
</html>

