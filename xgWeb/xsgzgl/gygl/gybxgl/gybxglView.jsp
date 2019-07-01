<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
			// 保存按钮
			jQuery('#buttonSave').click(function(){
				var clzt = jQuery("#clzt").val();
				var mustfill = ""
					
				if("已处理"==clzt){
					mustfill = "clzt-wxsj";
				}else if("不处理"==clzt){
				}
				saveData('gyglnew_gybxgl.do?method=gybxglUpdate&doType=save',mustfill);
			});
			
			// 关闭按钮
			jQuery('#buttonClose').click(function(){
				Close();
			});	
			
			changeClzt();
		});
		
		function changeClzt(){
			var clzt = jQuery("#clzt").val();
			
			if("已处理"==clzt){
				jQuery("#bcl_body").css("display","none");
				jQuery("#ycl_body").css("display","");
				jQuery("#zbcl_body").css("display","none");
			}else if("不处理"==clzt){
				jQuery("#bcl_body").css("display","");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
			}else if ("暂不处理"==clzt){
				jQuery("#zbcl_body").css("display","");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#bcl_body").css("display","none");
			}else{
				jQuery("#bcl_body").css("display","none");	
				jQuery("#ycl_body").css("display","none");
				jQuery("#zbcl_body").css("display","none");
			}
		}
		
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		
		#jydw{
			width:88%;
		}
		
		textarea {
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/gyglnew_gybxgl" method="post">
		<input type="hidden" name="xh" value="${param.xh }"/>
		<input type="hidden" name="pk" value="${param.pk }"/>
		
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>学号</th>
					<td>
						${rs.xh }
					</td>
					<th>姓名</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc }
					</td>
					<th>班级</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
				<th>住宿寝室</th>
				<td>
					<logic:notEqual value="" name="rs" property="ldmc">
						${rs.ldmc}，
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="qsh">
						${rs.qsh}，
					</logic:notEqual>
					<logic:notEqual value="" name="rs" property="cwh">
						${rs.cwh}号床
					</logic:notEqual>
				</td>
				<th>寝室电话</th>
				<td>${rs.qsdh}</td>
			</tr>	
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>报修信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>紧急程度</th>
					<td>
						${rs.jjcd }
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh }
					</td>
				</tr>
				<tr>
				<th>报修类别</th>
					<td>
						${rs.bxlbmc }
					</td>
				<th>报修类别子项</th>
					<td>
						${rs.bxlbzxmc }
					</td>
				</tr>
				<tr>
					
					<th>期望维修时间</th>
					<td colspan="3">
						<logic:notEqual name="rs" property="qwwxsj_ks" value="">
						${rs.qwwxsj_ks } 至 ${rs.qwwxsj_js }
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>报修内容</th>
					<td colspan="3">
<%--						<html:textarea property="bxnr" value="${rs.bxnr }" cols="4" rows="3" --%>
<%--							readonly="true" style="width: 95%"></html:textarea>--%>
						${rs.bxnr }
					</td>
				</tr>
				<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${rs.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>处理信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>处理状态</th>
					<td colspan="3">
						${rs.clzt}
						<input type="hidden" id="clzt" value="${rs.clzt }"/>
					</td>
				</tr>
			</tbody>
			<tbody id="ycl_body" style="display: none">
				<tr>
					<th>实际维修时间</th>
					<td>
						${rs.wxsj}
					</td>
					<th>维修人员</th>
					<td>
						${rs.wxry}
					</td>
				</tr>
				<tr>
					<th>维修费用</th>
					<td>
						${rs.wxfy}
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>维修内容<br/></th>
					<td colspan="3">
<%--						<html:textarea property="wxnr" value="${rs.wxnr }" cols="4" rows="3" --%>
<%--							readonly="true" style="width: 95%"></html:textarea>--%>
						${rs.wxnr }
					</td>
				</tr>
			</tbody>
			<tbody id="bcl_body" style="display: none">
				<tr>
					<th>不处理原因<br/></th>
					<td colspan="3">
<%--						<html:textarea property="bclyy" cols="4" rows="3" style="width: 95%" --%>
<%--							readonly="true"	value="${rs.bclyy}"></html:textarea>--%>
						${rs.bclyy}
					</td>
				</tr>
			</tbody>
			<tbody id="zbcl_body" style="display: none">
				<tr>
					<th>暂不处理原因<br/></th>
					<td colspan="3">
<%--						<html:textarea property="zbclyy" cols="4" rows="3" style="width: 95%" --%>
<%--							readonly="true"	value="${rs.zbclyy}"></html:textarea>--%>
						${rs.zbclyy}
					</td>
				</tr>
			</tbody>
			<logic:notEqual name="xxdm" value="1103202">
				<thead>
				<tr>
					<th colspan="4">
						<span>服务评价</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th>满意程度</th>
					<td>${rs.mycd }</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>评价</th>
					<td colspan="3">
							<%--						<html:textarea property="pj" rows="3" cols="4" style="width: 95%"--%>
							<%--							value="${rs.pj}" readonly="true"></html:textarea>--%>
							${rs.pj}
					</td>
				</tr>
				</tbody>
			</logic:notEqual>
		</table>
		</div>
		<table class="formlist">
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			           <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
</body>
</html>
