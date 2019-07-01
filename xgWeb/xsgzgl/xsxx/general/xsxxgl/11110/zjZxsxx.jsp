<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			jQuery('#buttonSave').focus();	
		}
		//保存在校生申请信息
		function saveZxsSq(){
			var str = "xh-xm-nj-xydm-zydm-bjdm-zzmm";
			var flag = checkNotNull(str);
			if (!flag) {
				alertError("带\'*\'号字段必须填写！");
				return false;
			}
		
			var xh = $("xh").value;
			if(xh.length < 6){
				alertError("学号请至少维护6位！");
			    return false;
			}
			var xbn = $("xbn");
			var xbnv = $("xbnv");
			if(!xbn.checked && !xbnv.checked){
				alertError("请选择性别！");
			    return false;
			}
			
			//检查学号是否存在
			var flag = checkXhisExists(xh);
			if (flag) {
				refreshForm('xsxx_tygl.do?method=bcZxsxx');
			}
		}
		//显示TBODY内容 
		function displayTbody(tbodyId) {
			if (document.getElementById(tbodyId)) {
				document.getElementById(tbodyId).style.display=(document.getElementById(tbodyId).style.display==''?'none':'');
			}
		}
		//显示上传相片
		function showZpscDiv(){
	
			var xh = jQuery("#xh").val();
			
			if(xh == ""){
				alertError("请先填写学号！");
				return false;
			}else{
				tipsWindown("系统提示","id:addPic","380","130","true","","true","id");
			}
		}
		//照片上传
		function uploadStuPic(){
		
			jQuery.ajaxSetup({async:false,dataType:'text'});
			
			var xh = jQuery("#xh").val();
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh='+xh,//服务器端程序
			  secureuri:false,
			  fileElementId:'stuPic',//input框的ID
			  success:function(data,type){
				if (type=='success'){
					alertInfo(data);
				}
			  }
			});
			
			jQuery('#stuImg').empty();
			jQuery("#stuImg").html('<img id="xszp" name="xszp" src="stuPic.jsp?xh='+xh+'&flg=true" border="0" align="absmiddle" style="width:100px;height:140px" />').show();
		
			jQuery.ajaxSetup({async:true});
		}
		//检查学号是否存在
		function checkXhisExists(xh) {
			var flag = true;
			jQuery.ajaxSetup({async:false});
			jQuery.ajax({
				 type: "POST",
			     url: "xsxx_tygl.do?method=jcXhsfcz",
			     dataType:"json",
			     data: {xh:xh},
			     success: function(data){
			     	if (data=='1') {
			     		flag = false;
			     		alertError("学号：'" + xh + "'已经存在，请重新输入！");
			     		//jQuery('#xh').focus();
			     	} 
			   	 }
			});
			jQuery.ajaxSetup({async:true});
			return flag;
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body>
		<html:form action="/xsxx_tygl"  method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" style="display: none;"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<!-- 学生基本信息 begin-->
					<thead>
						<tr>
							<th colspan="5">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jbxx">
						<tr>
							<th width="14%">
								<span class="red">*</span>学号
							</th>
							<td width="30%">
									<html:text  property="xh" styleId="xh"
										styleClass="text_nor"  maxlength="20" />
							</td>
							<th>
								<span class="red">*</span>姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" maxlength="16"
									styleClass="text_nor" />
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:top; text-align:center;width:100px">
								<div id="stuImg" name="stuImg" style="margin-left:0px;margin-top: 0px;height: 130px;">
										<img style="width:100px;height:130px" id="xszp" name="xszp" 
										src="stuPic.jsp?xh=${rs.xh}"
										border="0">
								</div>
								<br/>
								<button type="button" onclick="showZpscDiv();" style="width:100px">上传照片</button>
							</td>
						</tr>
						<tr>
						<th width="14%">
								学警号
							</th>
							<td width="30%">
									<html:text  property="zd1" styleId="zd1"
										styleClass="text_nor"  maxlength="20" />
							</td>
							<th>
								<span class="red">*</span>性别
							</th>
							<td>
								<html:radio  property="xb" styleId="xbn" value="男" >男</html:radio>
								<html:radio property="xb" styleId="xbnv" value="女">女</html:radio>
							</td>
						
						</tr>
						
						<tr>
							<th>
								出生日期
							</th>
							<td>
								<html:text  property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" readonly="true" />
							</td>
							<th>
								民族
							</th>
							<td>
								<html:select  property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								学制(年)
							</th>
							<td colspan="">
								<html:text property="xz" styleId="xz"
									onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="1"
									styleClass="text_nor" />
							</td>
							<th>
								考生类别
							</th>
							<td>
					 			<html:select property="kslb"  styleId="kslb">
										<html:options collection="kslbList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>政治面貌
							</th>
							<td >
								<html:select  property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td> 
								<th width="10%">
								<span class="red">*</span>年级
							</th>
							<td width="30%" >
								<html:select  property="nj" styleId="nj"
									style="width:90px" onchange="initXyzybj();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>	
						
						<tr>
								<th>
								<span class="red">*</span>班级
							</th>
							<td colspan="4">
								
								<input type="text" id="bjmc"
									onkeydown="" value="${rs.bjmc }" readonly="readonly"/>
								<button type="button" class="btn_01" id="button_bj" style="display: "
									onclick="getBj();">
									选择
								</button>
								<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
						
							
						</tr>
						<tr>
							<th>
								系部
							</th>
							<td>
								<input type="text" id="xymc"
									onkeydown="" value="${rs.xymc }"  readonly="readonly"/>
								<!--  <button class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									选择
								</button>-->
								<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							<th>
								<span class="red">*</span>专业
							</th>
							<td colspan="2">
								
								<input type="text" id="zymc"
									onkeydown="" value="${rs.zymc }" readonly="readonly"/>
							<!-- 	<button class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									选择
								</button> -->
								<input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
						</tr>
						<tr>
							<th>
								生源地(高考时户籍所在地)
							</th>
							<td colspan="4">
									<!--地址信息取标准码-->
									<logic:present name="ssList">
										<html:select  property="syds" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select  property="sydshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select property="sydx" styleId="jgxian" style="width:120px">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end地址信息取标准码-->
							</td>
							
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button"  name="保存" onclick="saveZxsSq()" id="buttonSave">
										保 存
									</button>
									<button type="button"  name="关闭" onclick="Close()" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<!-- 上传照片 -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>