<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	
		<script type="text/javascript">
		//��ѯ
		function searchRs(){
			
			var url = "/xgxt/xsxx_pysjsz.do?method=bzrpysj";
			
			allNotEmpThenGo(url);
		}
		
		//����
		function save(){			
			
			confirmInfo("�ò��������޸İ����������ֹʱ�䣬�Ƿ�ȷ���޸ģ�",function(tag){
				if(tag=="ok"){
					var xn=jQuery("#xn").val();
			var pysj=jQuery("#pysj").val();
			
			var blog=true;;
			if(xn==""){
				alertInfo("ѧ�겻��Ϊ�գ�");
				blog=false;
			}
			
			if(blog && pysj==""){
			
				alertInfo("����ʱ�䲻��Ϊ�գ�");
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
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_pysjsz" method="post">
				<input type="hidden" name="sfbc" id="sfbc" value="${rs.sfbc}"/>
				<div class="tab" id="displayCols" name="displayCols" height="500px" >
					<table width="100%" border="0" class="formlist" >
						<thead>
							<tr>
								<th colspan="4">
									<span>����������ʱ������</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="�ύ" onclick="save();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									<font color="red">*</font>ѧ��
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
									<font color="red">*</font>��ֹʱ��
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
