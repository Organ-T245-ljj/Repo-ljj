function delfiless(id){
	alert("del");
	$.ajax({
		type:"GET",//请求类型
		url:"/appinfo/appVersion/delfile.json",//请求的url
		data:{id:id},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			alert(data.result);
			if(data.result == "success"){
				alert("删除成功！");
				$("#uploadfile").show();
				$("#apkFile").html('');
			}else if(data.result == "failed"){
				alert("删除失败！");
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("请求错误！");
		}
	});  
}
$(function(){  
	$("#back").on("click",function(){
		window.location.href = "/appinfo/appinfo/list";
	});
	
	//上传APK文件---------------------
	var downloadLink = $("#downloadLink").val();
	var id = $("#id").val();
	var apkFileName = $("#apkFileName").val();
	if(downloadLink == null || downloadLink == "" ){
		$("#uploadfile").show();
	}else{
		$("#apkFile").append("<p>"+apkFileName+
							"&nbsp;&nbsp;<a href='/appinfo/appVersion/download?filename="+downloadLink+"' >下载</a> &nbsp;&nbsp;" +
							"<a href=\"javascript:;\"  onclick=\"delfiless('"+id+"');\">删除</a></p>");
	}

});
      
      
      