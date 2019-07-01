<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	
		<script type="text/javascript">
		//查询
		function searchRs(){
			
			var url = "/xgxt/xsxx_pysjsz.do?method=bzrpysj";
			
			allNotEmpThenGo(url);
		}
		
		//保存
		function save(){			
			
			confirmInfo("该操作将会修改班主任评语截止时间，是否确定修改？",function(tag){
				if(tag=="ok"){
					var xn=jQuery("#xn").val();
			var pysj=jQuery("#pysj").val();
			
			var blog=true;;
			if(xn==""){
				alertInfo("学年不能为空！");
				blog=false;
			}
			
			if(blog && pysj==""){
			
				alertInfo("评议时间不能为空！");
				blog=false;
			}
			
			if(blog){
				var sfbc=jQuery("#sfbc").val();
				
				var parameter={}
				
				parameter["xn"]=xn;
				
				parameter["pysj"]=escape(pysj);
				
				var url="";
				if(sfbc=="false"){
					url = "xsxx_pysjsz_ajax.do?method=save";
				}else{
					url = "xsxx_pysjsz_ajax.do?method=update";
				}
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
					
						alertInfo(result,function(tag){
							if(tag=="ok"){
								
								searchRs();
						
							}
						});
		
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
				}
			})
		}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_pysjsz" method="post">
				<input type="hidden" name="sfbc" id="sfbc" value="${rs.sfbc}"/>
				<div class="tab" id="displayCols" name="displayCols" height="500px" >
					<table width="100%" border="0" class="formlist" >
						<thead>
							<tr>
								<th colspan="4">
									<span>班主任评议时间设置</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="提交" onclick="save();return false;">
											保 存
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									<font color="red">*</font>学年
								</th>
								<td width="80%">
									<html:select property="xn" styleId="xn"  style="width:150px" value="${rs.xn}"
										>
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>截止时间
								</th>
								<td width="80%">
									<html:text property="pysj" styleId="pysj" 
										onclick="return showCalendar('pysj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" value="${rs.pysj}"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
		</html:form>
	</body>
</html>
