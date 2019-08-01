
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

        function savePbzyz(){
            var checkId = 'xh-fdkm-fdsmc-fjid';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "xyfd_pbjg.do?method=save&type=add";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

        function selectFds() {
            showDialog("辅导室列表",700,500,"xyfd_fdjswh.do?method=selectFds");
        }
        function showStudentDialog() {
            var goto = encodeURIComponent('${path}');
            showDialog('请选择一个学生', 800, 500, 'xsxx_xsgl.do?method=showStudents&goto=' + goto);
        }
	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdjswh" method="post" styleId="demoForm">
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="7">
					<span>朋辈志愿者信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="10%">姓名</th>
				<td width="20%" id="xm">
					${xsxxlist.xm}
				</td>
				<th width="10%">性别</th>
				<td width="20%" id="xb">
					${xsxxlist.xbm}
				</td>
				<th width="12%"><span class="red">*</span>学号</th>
				<td width="20%">
					<input type="text" name="xh" id="xh" value="${xsxxlist.xh}"/>
					<button class="btn_01" type="button" onclick="showStudentDialog();">选择</button>
				</td>
				<th rowspan="2">
					<div align="center">
						<img id="zhaopian" src="xsxx_xsgl.do?method=showPhoto&xh=${xsxxlist.xh}" alt="" style="height: 133px;width: 100px;" border="0"/>
					</div>
				</th>
			</tr>
			<tr>
				<th>书院</th>
				<td>
						${xsxxlist.symc}
				</td>
				<th>学院</th>
				<td>
						${xsxxlist.xymc}
				</td>
				<th>班级</th>
				<td>
						${xsxxlist.zybjmc}
				</td>
			</tr>
			<tr>
				<th>专业排名</th>
				<td>
						${zypmlist.pm0}
				</td>
				<th>学生干部任职</th>
				<td>
					<input id="xsgbrz" name="xsgbrz"/>
				</td>
				<th>奖（助）学金及表彰奖励</th>
				<td colspan="2">
						${jlxx}
				</td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td colspan="3">
					<input id="lxdh" name="lxdh" value="${xsxxlist.lxdh}"/>
				</td>
				<th>E-mail</th>
				<td colspan="2">
					<input id="dzyx" name="dzyx" value="${xsxxlist.dzyx}"/>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>辅导科目</th>
				<td colspan="3">
					<input id="fdkm" name="fdkm"/>
				</td>
				<th><span class="red">*</span>辅导室</th>
				<td colspan="2">
					<input id="fds" name="fds" style="display: none"/>
					<input id="fdsmc" name="fdsmc" readonly="readonly"/>
					<button class="btn_01" type="button" onclick="selectFds();">选择</button>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>附件</th>
				<td colspan="6">
					<input type="hidden" id="fjid" name="fjid"  />
					<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
					<script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'fjid'
                            });
                        });
					</script>
				</td>
			</tr>
			<tr>
				<th rowspan="7">答疑时间</th>
				<th colspan="2">周一</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周二</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周三</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周四</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周五</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周六</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周日</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30
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
						<button type="button" type="button" onclick="savePbzyz();return false;" >
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

