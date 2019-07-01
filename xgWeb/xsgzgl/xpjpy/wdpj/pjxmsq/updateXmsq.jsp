<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveJxsb(){
				if (jQuery("#sqly").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				
				var url = "xpj_sqsh.do?method=saveUpdateSqb&type=save";
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							var api = frameElement.api;
							W = api.opener;
							var grid = W.jQuery("#dataTable").reloadGrid();
							iFClose();
						}
					}});
				});
			}

			//增加获奖信息
			function addHjxx(){
				var xh = jQuery("#xh").val();
				var url = "xpj_sqsh.do?method=getHjjgAdd&xh="+xh;
				showDialog('选择申请奖项',800,550,url);
			}

			jQuery(function(){
                //ie9及以下无此事件
                jQuery("#sqly").bind("input",function(){
                    chCount($(this),180,200);
                });
			});
		</script>
	</head>
	<body>
		<html:form action="/xpj_pjxmsq" method="post" styleId="sqshForm">
			<html:hidden property="sqid" styleId="sqid" />
			<html:hidden property="xh" styleId="xh" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<logic:notEqual name="UserType" value="stu">
						<thead>
							<tr>
								<th colspan="4">
									<span>基本信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="20%">
									学号
								</th>
								<td width="30%">
									<a href="javascript:void(0);" class="name" 
									   onclick="showDialog('学生基本信息',700,500,'xsxx_tygl.do?method=ckZxsxx&xh=${jbxx.xh }')"
									   style="margin-left: 1px;"
									 >
									 ${jbxx.xh }
									</a>
								</td>
								<th width="20%">
									姓名
								</th>
								<td width="30%">
									${jbxx.xm }
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									${jbxx.xb }
								</td>
								<th>
									身份证号
								</th>
								<td>
									${jbxx.sfzh }
								</td>
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									${cpbjxx.nj }
								</td>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									${cpbjxx.xymc }
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									${cpbjxx.zymc }
								</td>
								<th>
									班级
								</th>
								<td>
									${cpbjxx.bjmc }
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项&nbsp;&nbsp;
									<logic:equal value="1" name="hjjgxskg">
								<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue"  id="hjbutton" ><u>选择获奖信息</u></font>	
									</a>
								</logic:equal>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">评奖周期</th>
							<td colspan="3">${pjzqmc}</td>
						</tr>
						<tr>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${xmwhModel.xmmc }
							</td>
							<th width="20%">
								项目金额
							</th>
							<td width="30%">
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="10355" name="xxdm">
						<logic:equal value="1" name="hjjgxskg">
						<tr id="hjtr">
								<th>获奖信息</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">获奖名称</td>
											<td width="20%">获奖时间</td>
											<td width="50%">颁奖单位</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										<logic:iterate id="i" name="hjjgList">
											<tr>
												<td>${i.hjmc}</td>
												<td>${i.hjsj}</td>
												<td>${i.fjdw}</td>
											</tr>									
										</logic:iterate>
									</tbody>
								</table>
								</td>
							</tr>
						</logic:equal>
						</logic:equal>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : ${xxdm=='12713'?10:3},
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//最大文件大小 单位M
											maxsize: ${xxdm=='12713'?30:10},
											//存放附件的隐藏域的id
											elementid : 'ylzd5'
											});
										
										//浙江同济科技职业技术学院，追加2条附件上传提示
										if(${xxdm=='12647'}){
											var tipsPrepend = "★ 文件请根据获奖情况命名，如：优秀毕业生、积极分子佐证材料照片。</br>"+
															  "★ 如需上传多个文件，请成功选择一个后，再次点击选择文件继续。</br>";
											jQuery('#tips').prepend(tipsPrepend);
										}
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>申请理由填写说明</th>
							<td colspan="3"><p>${xmwhModel.kgbz }</p></td>
						</tr>
						<tr>
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由</br>(思想政治、社会实践、综合素质得分、学习排名等)</br><font color="red" >(限180-200字)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,180,200);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由</br>(思想政治、社会实践、综合素质得分、学习排名等)</br><font color="red">(限150-250字)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
				</div>
				<div>
					<table  width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveJxsb();">
											保 存
										</button>
										
										<button type="button" type="button" onclick="iFClose();">
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

