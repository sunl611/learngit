$(function(){
     var winWidth = $(document).width();
     mainWidth = winWidth * 0.8;
     $("#content").width(mainWidth);

     $("#btn").on("click",function(){
        $('#demoModal').modal('show');
     });

     function addHref(){
         var tds = document.getElementsByTagName("tds");
         for(var i=0;i < tds.length;i++){
             var index = td[i].getAttribute("index");
             var href = "checking_" + index;
             td[i].setAttribute("href",href);
         }
     }
     addHref();
});
