var addBtn = null;
var backBtn = null;


$(function(){
	addBtn = $("#add");
    backBtn = $("#back");
    
    addBtn.bind("click",function(){      
       if(confirm("是否确认提交照片")){
          $("#userForm").submit();
       }      
    });
    
    backBtn.on("click",function(){
        if(referer != undefined 
            && null != referer 
            && "" != referer
            && "null" != referer
            && referer.length > 4){
         window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});