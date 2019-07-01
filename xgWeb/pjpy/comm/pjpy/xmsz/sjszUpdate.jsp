<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>
        function onLoad(){
        	var xmdm = $("xmdm").value;
			jQuery.ajax({
				type:'post',
				url:'pjpy_ty_ajax.do?method=isRsszSet&xmdm='+xmdm,
				dataType:'json',
				success:function(data){
				   if(!data){
					   jQuery("#nextStep").hide();
				   }else{
					   jQuery("#nextStep").show();
				   }
				}
			});
        }
		
		function confim(){
			var xmdm = $("xmdm").value;
			jQuery.ajax({
				type:'post',
				url:'pjpy_ty_ajax.do?method=isRsszSet&xmdm='+xmdm,
				dataType:'json',
				success:function(data){
				   if(!data){
					     saveUpdate('pjpy_ty_sjsz.do?method=sjszUpdate&doType=save','')
						window.close();
						window.dialogArguments.window.alertInfo("保存成功");
				   }else{
					   confirmInfo("项目时间设置已完成,此项设置不可返回,如果需要修改请到项目时间设置中直接修改,是否要继续下一步的设置?",mbDownLoad);
				   }
				}
			});
		}
		function mbDownLoad(tag){
			if(tag=='ok'){
				saveUpdate('pjpy_ty_sjsz.do?method=sjszFlow&doType=save','');
			}
		}
		
		
		</script>
	</head>

	<body onload="onLoad()">
	<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优-项目设置-评奖时间设置</a>
				</p>
	  </div>
	
	 <div class="flow-steps">
			  <ol class="num5">
			    <li class="done" style = "width:19%"><span class="first">1. 项目兼得设置</span></li>
			    <li class="done" style = "width:19%"><span>2. 项目兼得范围设置</span></li>
			    <li class="done current-prev"><span>3. 项目条件设置</span></li>
			    <li class="current" style = "width:19%"><span>4. 项目时间设置</span></li>
			    <li class="last" style = "width:19%"><span>5. 项目人数设置</span></li>
			  </ol>
        </div>
	
		<html:form action="/pjpy_ty_sjsz" method="post">
			<input type="hidden" id="pjxn" value="${pjxtszModel.pjxn }" name="save_pjxn"/>
			<input type="hidden" id="pjxq" value="${pjxtszModel.pjxq }" name="save_pjxq"/>
			<input type="hidden" id="pjnd" value="${pjxtszModel.pjnd }" name="save_pjnd"/>
			<input type="hidden" id="xmdm" value="${pjpySjszForm.xmdm }" name="save_xmdm"/>
		    <html:hidden property="xmdm" styleId="xmdm"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								    <button type="button" name="下一步"  id="nextStep" onclick="saveUpdate('pjpy_comm_rssz.do?method=rsszManageFlow&rssz=${xmszModel.rssz}','')" >
										  跳过
									</button>
									<button type="button" onclick="confim()">
										保存
									</button>

									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>评奖项目时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="2">
					<div class="prompt" id="div_help">
				            <p>
								项目名称：${xmmc }&nbsp;&nbsp;
								学年：${pjxtszModel.pjxn }&nbsp;&nbsp;
								年度：${pjxtszModel.pjnd }&nbsp;&nbsp;
								学期：${pjxtszModel.pjxqmc }&nbsp;&nbsp;
							</p>
      	            </div>
      	           </td>
					</tr>
						<tr>
							<th width="32%">
								申请开始时间
							</th>
							<td width="68%">
								<input type="text" id="save_sqkssj" name="save_sqkssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								申请结束时间
							</th>
							<td>
								<input type="text" id="save_sqjssj" name="save_sqjssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								申请控制开关
							</th>
							<td>
								<input type="radio"  name="save_sqkzkg"  value="0"/>开
								<input type="radio"  name="save_sqkzkg"  value="1"  checked="checked" />关
							</td>
						</tr>
						<tr>
							<th>
								审核开始时间
							</th>
							<td>
								<input type="text" id="save_shkssj" name="save_shkssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								审核结束时间
							</th>
							<td>
								<input type="text" id="save_shjssj" name="save_shjssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								审核控制开关
							</th>
							<td>
								<input type="radio"  name="save_shkzkg"  value="0"/>开
								<input type="radio"  name="save_shkzkg"  value="1"  checked="checked"/>关
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
