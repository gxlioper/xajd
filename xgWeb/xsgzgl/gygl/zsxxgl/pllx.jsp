<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/calendar/calendar.js"></script>
			<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
			<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script language="javascript">
	function saveTsxx(){
		var hjrs=jQuery("#hjrs").val();
		if(hjrs ==0){

			showAlertDivLayer("暂无已离校待退宿学生!",{},{"clkFun":function(){
					Close();
				}
			});
			return false;
		}
		if(!check("tsyy-tssj-xn-xq")){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}
		
		var ts="确定对【离校且仍占床位】的<font style='font-size: 15px;font-weight: 600;color:red;'>"+hjrs+"</font>个学生进行<font style='font-size: 15px;font-weight: 600;color:red;'>退宿</font>操作？";
		showConfirmDivLayer(ts, {
			"okFun" : function() {
			var url="gyglnew_zsxxgl.do?method=plLx&doType=pllx";
			 	jQuery("#form").ajaxSubmit({
					url:url,
					type:"post",
					dataType:"json",
					success:function(data){
						 var message=data["message"];
						 if(message=="保存成功"){
							 message="退宿成功!";
						 }else{
							 message="退宿失败!";
						 }	
			    		 showAlert(message,{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    		}});
					},
					contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});	
			}
		});
	}
	
	/**
	 * 验证是否存在空项
	 * @param ids 要验证的控件id "-"分隔
	 * @return
	 */
	function check(ids){
		var id=ids.split("-");
		for(var i=0;i<id.length;i++){
			var lddm=jQuery("#"+id[i]).val();
			if(lddm==null||""==lddm){
				//alert(id[i]);
				return false;
			}
		}
		return true;
	}
	jQuery(function(){
	     var gridSetting = {
					caption:"已离校待退宿信息",
					pager:"pager",
					url:"gyglnew_zsxxgl.do?method=plLx&doType=query",
					colList:[
					   {label:'njxyzy',name:'njxyzy',index:'njxyzy',key:true,hidden:true},			
					   {label:'年级',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
					   {label:'专业',name:'zymc', index: 'zymc'},
					   {label:'zydm',name:'zydm', index: 'zydm',hidden:true},
					   {label:'xydm',name:'xydm', index: 'xydm',hidden:true},
					   {label:'离校未退宿学生数',name:'dlxrs', index: 'dlxrs',formatter:rsLink}
					],
					sortname: "nj",
				 	sortorder: "desc",
				 	multiselect:false,
					rowNum:5,
					pageList:[5,10,50,100,200]					 	
				}
			jQuery("#dataTable").initGrid(gridSetting);
	});
	function rsLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='pllxclj(\""+rowObject["nj"]+"\",\""+rowObject["xydm"]+"\",\""+rowObject["zydm"]+"\");'>"+rowObject["dlxrs"]+"</a>";
	}
	function pllxclj(nj,xydm,zydm){
		showDialog("离校待退宿学生信息",550,400,"gyglnew_zsxxgl.do?method=pllxview&nj="+nj+"&xydm="+xydm+"&zydm="+zydm);
   	}
	</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl" styleId="form"
			method="post">
			<input type="hidden" id="hjrs" value="${hjrs}"/>
			<div class="prompt" id="span_qsh">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					对【离校且仍占床位】的学生进行批量退宿操作
					</br>
					退宿时，若“是否初始化床位所属”选择“是”，则系统自动初始化床位所属，床位分配状态变更为未分配。
				</p>
				<a class="close" onclick="this.parentNode.style.display='none';" title="隐藏"></a>
			</div>

			<div class="tab">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>已离校待退宿信息</span>
							</th>
						</tr>
					</thead>
				</table>
				
				<table id="dataTable"></table>
				<div id="pager"></div>
				<table class="dateline" width="100%">
					<tbody>
						<tr style="font-size: 15px;font-weight: 600;" align="right">
							<td colspan="4">
								<span style="color: red;margin-right: 30px;">合计:&nbsp;&nbsp;${hjrs}人</span>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>退宿信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>退宿原因
							</th>
							<td>
								<html:select property="tsyy" styleId="tsyy" >						
									<html:options collection="tsyyList" labelProperty="tsyymc" property="tsyydm"/>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>退宿时间
							</th>
							<td>
								<html:text property="tssj" styleId="tssj"
									onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')" style="width:100px;"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>学年/学期
							</th>
							<td align="left">
								<html:select property="xn" styleId="xn" disabled="false">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq" disabled="false" >
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>是否初始化床位所属
							</th>
							<td>
								<logic:equal value="xx" name="userStatus" scope="session">
									<input type="radio" name="sfqccwss" value="是" checked="checked" />是
									&nbsp;
									<input type="radio" name="sfqccwss" value="否" />否
								</logic:equal>
								<logic:notEqual value="xx" name="userStatus" scope="session">
									<input type="radio" name="sfqccwss" value="是"
										disabled="disabled" />是
										&nbsp;
									<input type="radio" name="sfqccwss" value="否" checked="checked"
										disabled="disabled" />否
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br />
								<font color="red">(限1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:95%" styleId="bz"  onblur="checkLen(this,1000);"
									rows='3' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="提交" id="buttonSave" onclick="saveTsxx();">
										提交
									</button>
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
										关闭
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
