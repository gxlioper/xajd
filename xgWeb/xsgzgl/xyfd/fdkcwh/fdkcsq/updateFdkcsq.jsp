
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
		jQuery(function () {
            var mon = '${fdjsxx.mon}';
            var tues = '${fdjsxx.tues}';
            var wed = '${fdjsxx.wed}';
            var thur = '${fdjsxx.thur}';
            var fri = '${fdjsxx.fri}';
            var sat = '${fdjsxx.sat}';
            var sun = '${fdjsxx.sun}';
            var mond = mon.split(",");
            var tuesd = tues.split(",");
            var wedd = wed.split(",");
            var thurd = thur.split(",");
            var frid = fri.split(",");
            var satd = sat.split(",");
            var sund = sun.split(",");
            if(mond!=null){
                for(var i=0;i<mond.length;i++){
                    jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                }
            }
            if(tuesd!=null){
                for(var i=0;i<tuesd.length;i++){
                    jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                }
            }
            if(wedd!=null){
                for(var i=0;i<wedd.length;i++){
                    jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                }
            }
            if(thurd!=null){
                for(var i=0;i<thurd.length;i++){
                    jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                }
            }
            if(frid!=null){
                for(var i=0;i<frid.length;i++){
                    jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                }
            }
            if(satd!=null){
                for(var i=0;i<satd.length;i++){
                    jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                }
            }
            if(sund!=null){
                for(var i=0;i<sund.length;i++){
                    jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                }
            }
            jQuery.each(jQuery("input:checkbox"),function (element,index) {
                if(!jQuery(this).is(':checked')){
                    jQuery(this).attr("disabled","disabled");
                }
				jQuery(this).prop("checked","");
            })

            var mon = '${model.mon}';
            var tues = '${model.tues}';
            var wed = '${model.wed}';
            var thur = '${model.thur}';
            var fri = '${model.fri}';
            var sat = '${model.sat}';
            var sun = '${model.sun}';
            var mond = mon.split(",");
            var tuesd = tues.split(",");
            var wedd = wed.split(",");
            var thurd = thur.split(",");
            var frid = fri.split(",");
            var satd = sat.split(",");
            var sund = sun.split(",");
            if(mond!=null){
                for(var i=0;i<mond.length;i++){
                    jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                }
            }
            if(tuesd!=null){
                for(var i=0;i<tuesd.length;i++){
                    jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                }
            }
            if(wedd!=null){
                for(var i=0;i<wedd.length;i++){
                    jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                }
            }
            if(thurd!=null){
                for(var i=0;i<thurd.length;i++){
                    jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                }
            }
            if(frid!=null){
                for(var i=0;i<frid.length;i++){
                    jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                }
            }
            if(satd!=null){
                for(var i=0;i<satd.length;i++){
                    jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                }
            }
            if(sund!=null){
                for(var i=0;i<sund.length;i++){
                    jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                }
            }

        })
        function saveFdkc(type){
            var checkId = 'kcmc-kkdw-sqyy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            save(type);
        }
        function save(type){
            var url = "xyfd_fdkcsq.do?method=saveUpdate&type="+type;
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

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdkcsq" method="post" styleId="demoForm">
	<html:hidden name="model" property="sqid" styleId="sqid"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
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
					<input type="text" id="kcmc" name="kcmc" value="${model.kcmc}"/>
				</td>
				<th width="20%"><span class="red">*</span>开课单位</th>
				<td width="40%">
					<input type="text" id="kkdw" name="kkdw" value="${model.kkdw}"/>
				</td>
			</tr>
			<tr>
				<th>学生/教师</th>
				<td>
					${model.xsjs}
				</td>
				<th>辅导教师</th>
				<td>
					<input type="hidden" name="fdjs" id="fdjs" value="${model.fdjs}" />
					${fdjsxm}
				</td>
			</tr>
			<tr>
				<th>申请人</th>
				<td colspan="3">
					${model.sqr}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>申请原因</th>
				<td colspan="3">
					<html:textarea name="model" property="sqyy" styleId="sqyy" style="width: 500px;height: 100px;"></html:textarea>
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
	<div style="width: 100%;position:fixed;bottom:0;">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button type="button" type="button" onclick="saveFdkc('save');return false;" >
							保存草稿
						</button>
						<button type="button" type="button" onclick="saveFdkc('submit');return false;" >
							提交申请
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

     