<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"xsxxDyxjZpjg.do?method=getZpjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm'},
					   {label:'ѧ��',name:'xn', index: 'xn'},
					   {label:'ѧ��',name:'xqmc', index: 'xq'},
					   {label:'�����ȼ�',name:'djmc', index: 'djmc'},
					   {label:'����ʱ��',name:'pysj',index:'pysj'},
					   {label:'������Դ',name:'sjly',index:'sjly',hidden:true}
					],
					sortname:"pysj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("�鿴�����",600,320,"xsxxDyxjZpjg.do?method=zpjgView&id="+id);
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					if (rows[0]["sjly"] == "1"){
						alertInfo("�������ݲ����޸ģ�");
						return false;
					}
					showDialog('�޸�',700,450,'xsxxDyxjZpjg.do?method=zpjgEdit&id='+rows[0]["id"]);
				}
			}

			function delZpjg(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("�������ݲ���ɾ����");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("xsxxDyxjZpjg.do?method=zpjgDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addZpjg(){
				showDialog('���ӵ�������',700,450,'xsxxDyxjZpjg.do?method=zpjgAdd');;
			}
			
			function importZpjg(){
				toImportData("xsxx_dyxj");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='xsxx_dyxj';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='xsxx_dyxj';
				setSearchTj();//���ø߼���ѯ����
				//dcclbh,�������ܱ��
				var url = "xsxxDyxjZpjg.do?method=export&dcclbh=" + DCCLBH;
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			function exporDyxj(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "xsxxDyxjZpjg.do?method=getZpjgxjb";
					url += "&id=" + ids+"&flag=jg";
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "xsxxDyxjZpjg.do?method=getZpjgTy";
					url += "&value=" + ids+"&flag=jg";
					window.open(url);
				}
			}

			function exporDyHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var bjobj = jQuery("a[name='a_bj_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var bjdms = "";
				if(xnobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�꣡");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�ڣ�");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}

				if(bjobj.length == 0){
				  showAlertDivLayer("�ڸ߼���ѯ��������ѡ��һ����һ�����ϵİ༶��");
				  return false;
				}else{
			      if(bjobj.length == 1){
				     var str = jQuery(bjobj).attr("id");
				      if(str.indexOf("bj_mc_xs_") != -1){
				    		bjdms =str.replace("bj_mc_xs_","");
				    	}else{
				    		bjdms =str.replace("bj_mc_yc_","");
				      }
				      var url = "xsxxDyxjZpjg.do?method=getbjzpjghzb";
					  url += "&bjdm=" + encodeURI(encodeURI(bjdms))+"&xn="+xn+"&xq="+xq;
					  window.open(url);
			      }else{
			    	  for(var i=0;i<bjobj.length;i++){
				    	var str = jQuery(bjobj[i]).attr("id");
				    	if(str.indexOf("bj_mc_xs_") != -1){
				    		bjdms +=str.replace("bj_mc_xs_","")+",";
				    	}else{
				    		bjdms +=str.replace("bj_mc_yc_","")+",";
				    	}
					  }
			    		var url = "xsxxDyxjZpjg.do?method=getZpjghzTy";
						url += "&value=" + encodeURI(encodeURI(bjdms))+"&xn="+xn+"&xq="+xq;
						window.open(url);
			      }
				  
				}

			
			}

			function exporDyXyHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var xyobj = jQuery("a[name='a_xy_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var xydms = "";
				var xymcs ="";
				if(xnobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�꣡");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�ڣ�");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}

				if(xyobj.length == 0){
				  showAlertDivLayer("�ڸ߼���ѯ��������ѡ��һ����һ�����ϵ�ѧԺ��");
				  return false;
				}else{
			      if(xyobj.length == 1){
				     var str = jQuery(xyobj).attr("id");
				      if(str.indexOf("xy_mc_xs_") != -1){
				    	  xydms =str.replace("xy_mc_xs_","");
				    	  xymcs = jQuery(xyobj).text();
				    	 
				    	}else{
				    		xydms =str.replace("xy_mc_yc_","");
				    		 xymcs = jQuery(xyobj).text();
				      }
				      var url = "xsxxDyxjZpjg.do?method=getxyzpjghzb";
					  url += "&xydm=" + xydms+"&xn="+xn+"&xq="+xq+"&xymc="+encodeURI(encodeURI(xymcs));
					  window.open(url);
			      }else{
			    	  for(var i=0;i<xyobj.length;i++){
				    	var str = jQuery(xyobj[i]).attr("id");
				    	var str1 = jQuery(xyobj[i]).text();
				    	if(str.indexOf("xy_mc_xs_") != -1){
				    		xydms +=str.replace("xy_mc_xs_","")+",";
				    		xymcs +=str1+","
				    	}else{
				    		xydms +=str.replace("xy_mc_yc_","")+",";
				    		xymcs +=str1+","
				    	}
					  }
			    		var url = "xsxxDyxjZpjg.do?method=getZpjgxyhzTy";
						url += "&value=" + xydms+"&xn="+xn+"&xq="+xq+"&value1="+encodeURI(encodeURI(xymcs));
						window.open(url);
			      }
				  
				}

			
			}

			function exporDyXxHz(){
				var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
				var xqobj = jQuery("a[name='tj_xq'][class='selectedValue']");
				var xyobj = jQuery("a[name='a_xy_mc'][class='selectedValue']");
				var xn = "";
				var xq = "";
				var xydms = "";
				var xymcs ="";
				if(xnobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�꣡");
				  return false;
				}else{
				  xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
				}

				if(xqobj.length != 1){
				  showAlertDivLayer("�ڸ߼���ѯ�����н���ֻ��ѡ��һ��ѧ�ڣ�");
				  return false;
				}else{
				 xq = jQuery(xqobj).attr("id").replace("tj_xq_","");
				}
			      var url = "xsxxDyxjZpjg.do?method=getxxzpjghzb";
				  url += "&xn="+xn+"&xq="+xq
				  window.open(url);
			}

			function exportXsDymx(){
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
			    if (len == 0) {
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return false;
				} else {
					var url = "xsxxDyxjZpjg.do?method=getZpjgMxb";
					url += "&value=" + ids;
					window.open(url);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xsxxDyxjDyzp" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="addZpjg()" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="delZpjg();" class="btn_sc">ɾ��</a></li>						
							<li><a href="javascript:void(0);" onclick="importZpjg();" class="btn_dr">����</a></li>						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>	
							<li><a href="javascript:void(0);" onclick="exporDyxj();" class="btn_dy">����С��</a></li>
							<li><a href="javascript:void(0);" onclick="exportXsDymx();" class="btn_dy">����С����ܱ�</a></li>
							<li><a href="javascript:void(0);" onclick="exporDyHz();" class="btn_dy">�༶���ܱ�</a></li>
							<li><a href="javascript:void(0);" onclick="exporDyXyHz();" class="btn_dy">ѧԺ���ܱ�</a></li>		
							<li><a href="javascript:void(0);" onclick="exporDyXxHz();" class="btn_dy">ѧУ���ܱ�</a></li>		
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>������������б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
