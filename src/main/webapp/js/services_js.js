/**
 * Created by 熊纪元 on 2015/12/3.
 */

function toHospitalPage(pageNo){
    htmlobj=$.ajax({url:"hospitalList.html?pageNo=" + pageNo,type:"POST",async:false});
    $("#applist").html(htmlobj.responseText);
}

function toDepartmentPage(pageNo){
    htmlobj=$.ajax({url:"departmentList.html?pageNo=" + pageNo,type:"POST",async:false});
    $("#applist").html(htmlobj.responseText);
}

function toDepartmentPageByhospital(hospitalId,pageNo){
    htmlobj=$.ajax({url:"departmentList.html?hospitalId="+hospitalId+"&pageNo=" + pageNo,type:"POST",async:false});
    $("#applist").html(htmlobj.responseText);
}
