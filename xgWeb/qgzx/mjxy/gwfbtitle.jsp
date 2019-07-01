<%@ page language="java" contentType="text/html; charset=GBK"%>
<!--闽江学院-->
<logic:equal value="10395" name="xxdm">
	<!--非修改操作-->
	<logic:notEqual value="modi" name="doType">
		<div class="compTab" id="card" style="width: 100%">
			<div class="comp_title" id="div1">
				<ul id="ul1">
					<li id="xngw">
						<a href="#" onclick="refreshForm('qgzxgwfb.do')">
							<span>校内勤工岗位信息发布</span>
						</a>
					</li>
					<li id="xwgw">
						<a href="#" onclick="refreshForm('gwfb.do?method=xwgwfb')">
							<span>校外勤工兼职岗位信息发布</span>
						</a>
					</li>						
				</ul>				
			</div>
		</div>
		<script defer="defer">
			if(ele('path') && val('path') == 'qgzxgwfb.do'){
				ele('xngw').className = 'ha';
				ele('xwgw').className = '';	
			}else{
				ele('xngw').className = '';
				ele('xwgw').className = 'ha';	
			}
		</script>
	</logic:notEqual>
</logic:equal>