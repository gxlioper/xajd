<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function assignSubmit(){
			if(jQuery('input[name="xh"]:checked').length > 1 || jQuery('input[name="xh"]:checked').length == 0){
				showAlert("请选择一个学生,请确认!");
				return false;
			}
			
			//提交
			showConfirmDivLayer("您确定要提交？",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=assignSubmit";
				ajaxSubFormWithFun("jjglXqwhForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<div class='tab' style='tab;width:100%;overflow-x:hidden;overflow-y:scroll;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">发布人</th>
					    	<td width="30%">${xqModelMap.sqr }</td>
					    	<th width="20%">发布时间</th>
					    	<td width="30%">${xqModelMap.sqsj }</td>
					    </tr>
					    <tr>
					    	<th>家教学科</th>
					    	<td>${xqModelMap.jjxk }</td>
					    	<th>学科年级</th>
					    	<td>${xqModelMap.jjnj }</td>
					    </tr>
					    <tr>
					    	<th>家教地点</th>
					    	<td colspan="3">${xqModelMap.jjdd }</td>
					    </tr>
					    <tr>
					    	<th>家教老师要求</th>
					    	<td colspan="3">${xqModelMap.jjlsyq }</td>
					    </tr>
					    <tr>
					    	<th>备注</th>
					    	<td colspan="3">${xqModelMap.bz }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教子女信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">姓名</th>
					    	<td width="30%">${znxxMap.xm }</td>
					    	<th width="20%">性别</th>
					    	<td width="30%">${znxxMap.xb }</td>
					    </tr>
					    <tr>
					    	<th width="20%">出生日期</th>
					    	<td width="30%">${znxxMap.csrq }</td>
					    	<th width="20%">年级</th>
					    	<td width="30%">${znxxMap.nj }</td>
					    </tr>
					    <tr>
					    	<th width="20%">在读学校</th>
					    	<td colspan="3">${znxxMap.zdxx }</td>
					    </tr>
					 </tbody>
					
					
					 <thead>
						<tr>
							<th colspan="5">
								<span>当前申请学生列表  <a style="color: blue;">[请勾选一位学生,然后提交]</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<td colspan="4">
					    		<table width="100%">
					    			<thead>
										<tr>
											<td>
												学号
											</td>
											<td>
												姓名
											</td>
											<td>
												学院
											</td>
											<td>
												专业
											</td>
											<td>
												班级
											</td>
											<td>
												申请时间
											</td>
											<td>
												选择
											</td>
										</tr>
									</thead>
					    			<tbody>
					    				<logic:empty name="xqXsSqList">
					    					<tr>
					    						<td colspan="8" style="text-align:center;">
					    							暂无申请人!
					    						</td>
					    					</tr>
					    				</logic:empty>
										<logic:notEmpty name="xqXsSqList">
											<logic:iterate id="xs" name="xqXsSqList">
											<tr>
					    						<td>${xs.xh}</td>
					    						<td>${xs.xm}</td>
					    						<td>${xs.xymc}</td>
					    						<td>${xs.zymc}</td>
					    						<td>${xs.bjmc}</td>
					    						<td>${xs.sqsj}</td>
					    						<td>
					    							<html:checkbox property="xh" value="${xs.xh}"></html:checkbox>
					    						</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
					    			</tbody>
					    		</table>
					    	</td>
					    </tr>
					 </tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btqd" onclick="assignSubmit();">
										提交
									</button>
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
