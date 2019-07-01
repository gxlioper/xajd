<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/xsyysq.js"></script>
	  <script type="text/javascript">
	  	jQuery(function(){
	  		var wtlxarr = new Array(<%=(String)request.getAttribute("wtlxarr") %>);
	  		jQuery.each( wtlxarr, function(index, value){
	  		   jQuery("input[name='wtlxarray'][value='"+value+"']").attr("checked",true);
	  		});	
	  		jQuery("#yyzxsTd").find("input=[type='radio'][checked]").click();
	  	});
	  </script>
  </head>
  
  <body>
    <html:form action="/xljk_xsyyzx" method="post" styleId="xsyysqForm">
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="2">
						<span>心理咨询预约信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%">
						咨询预约说明
					</th>
					<td>
						${zxyysm }
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span>预约咨询时间
					</th>
					<td>
						<html:text property="yyzxsj" styleId="yyzxsj" styleClass="text_nor" style="width:500px;" maxlength="15"/>
						<html:hidden property="sqid" styleId="sqid" />
						<html:hidden property="xh" styleId="xh" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span>联系号码
					</th>
					<td>
						<html:text property="xslxdh" styleId="xslxdh" styleClass="text_nor"/>
					</td>
				</tr>
				<tr>
					<th>
						问题类型
					</th>
					<td>
						<logic:iterate name="xlwtList" id="xlwtxx" > 
							<html:checkbox property="wtlxarray" value="${xlwtxx.lxdm}">${xlwtxx.lxmc}</html:checkbox>
						</logic:iterate> 
					</td>
				</tr>
				<tr>
					<th>
						预约咨询师
					</th>
					<td colspan="3" id="yyzxsTd">
						<div  style="overflow-y: scroll; height: 120px;">
							<logic:iterate name="zxslist" id="zxs" > 
								<div style="margin: 5px 0px 5px 0px;">
									<html:radio property="zxs" value="${zxs.zgh }" onclick="changeZxsjj(this)" >
										<span onclick="jQuery(this).parent().find(':radio').attr('checked',true);changeZxsjj(this);" style="cursor: pointer;">
										${zxs.xm }&nbsp;[${zxs.xb }][${zxs.bmmc }][${zxs.zgh }]
										</span>
										<span class="lxdh" style="display: none;">${zxs.lxdh }</span>
										<span class="address" style="display: none;">${zxs.address }</span>
										<span class="kjdrs" style="display: none;">${zxs.kjdrs }</span>
										<span class="yaprs" style="display: none;">${zxs.yaprs }</span>
										<span class="zxsjj" style="display: none;">${zxs.zxsjj }</span>
									</html:radio>
								</div>
							</logic:iterate> 
						</div>
					</td>
				</tr>
				<tr>
					<th>
						简介
					</th>
					<td colspan="3" id="zxsjjTd" height="40px">
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span>问题简要描述<br/>
						<span class="red">(限500字)</span>
					</th>
					<td>
						<html:textarea property="yyzxzt" styleId="yyzxzt" style="width:99%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						其他备注<br/>
						<span class="red">(限500字)</span>
					</th>
					<td>
						<html:textarea property="yyzxxq" styleId="yyzxxq" style="width:99%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateXsyysqAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
								关 闭
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</html:form>
  </body>
</html>
