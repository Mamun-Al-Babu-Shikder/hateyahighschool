/**
 * Created by A.A.MAMUN on 8/21/2019.
 */

$(document).ready(function () {

    getTeacher();


});

function getTeacher() {

    $.post("access_teacher_info", function (data, status) {
        teacherList = Object.values(data);
        // alert(data+", "+status);
        i = 0;
        tblRow = "";
        for(teacher of teacherList)
        {
            tblRow+= "<tr>";
            tblRow+= "<td> "+teacher["name"]+" </td>";
            tblRow+= "<td> "+teacher["designation"]+" </td>";
            tblRow+= "<td> "+teacher["address"]+" </td>";
            tblRow+= "<td> "+teacher["phone"]+" </td>";
            tblRow+= "<td style='text-align: center'> <button onclick='updateTeacher("+i+")' data-toggle='modal'  class='button-edit' > Edit </button> </td>";
            tblRow+= "<td style='text-align: center'> <button onclick='deleteTeacher("+teacher["id"]+")' class='button-delete' > Delete </button> </td>";
            tblRow+= "</tr>";
            i++;
        }

        $("#teacherTblBody").empty();
        $("#teacherTblBody").append(tblRow);

    });
}



function addTeacher(){


    $("#tImage").val('');
    $("#tName").val('');
    $("#tDesignation").val('');
    $("#tAddress").val('');
    $("#tDob").val('');
    $("#tPhone").val('');
    $("#tEmail").val('');
    $("#tDegree").val('');
    $("#tSubject").val('');
    $("#tJoinDate").val('');
    //$("#tType").val('');
    $("#tFb").val('');
    $("#tTwit").val('');
    $("#tDescription").val('');

    t = 201;

    $("#addNewTeacherModal").modal("show");
}


function updateTeacher(v){

    mdata = teacherList[v];
    $("#modalHeader").text("Update Teacher Info");

    $("#tImage").val('');
    $("#tName").val('');
    $("#tDesignation").val('');
    $("#tAddress").val('');
    $("#tDob").val('');
    $("#tPhone").val('');
    $("#tEmail").val('');
    $("#tDegree").val('');
    $("#tSubject").val('');
    $("#tJoinDate").val('');
    //$("#tType").val('');
    $("#tFb").val('');
    $("#tTwit").val('');
    $("#tDescription").val('');


    $("#tImage").val(mdata["img"]);
    $("#tName").val(mdata["name"]);
    $("#tDesignation").val(mdata["designation"]);
    $("#tAddress").val(mdata["address"]);
    $("#tDob").val(mdata["dob"]);
    $("#tPhone").val(mdata["phone"]);
    $("#tEmail").val(mdata["email"]);
    $("#tDegree").val(mdata["degree"]);
    $("#tSubject").val(mdata["subject"]);
    $("#tJoinDate").val(mdata["joinDate"]);
    $("#tType").val(mdata["type"]);
    $("#tFb").val(mdata["facebookLink"]);
    $("#tTwit").val(mdata["twitterLink"]);
    $("#tDescription").val(mdata["description"]);

    t = 202;

    $("#addNewTeacherModal").modal("show");
}

function deleteTeacher(v) {

    $.post("delete_teacher",{id: v},
        function (data, status) {
            if(data){
                getTeacher();
            }else{
                alert("Can't delete. Please try again later.")
            }
        }
    );
}

function addOrUpdateTeacher() {

    $("#tImage").removeClass("efw");
    $("#tName").removeClass("efw");
    $("#tDesignation").removeClass("efw");
    $("#tAddress").removeClass("efw");
    $("#tDob").removeClass("efw");
    $("#tPhone").removeClass("efw");
    $("#tEmail").removeClass("efw");
    $("#tDegree").removeClass("efw");
    $("#tSubject").removeClass("efw");
    $("#tJoinDate").removeClass("efw");
    $("#tType").removeClass("efw");
    $("#tFb").removeClass("efw");
    $("#tTwit").removeClass("efw");
    $("#tDescription").removeClass("efw");


    tImg = $("#tImage").val();
    tName = $("#tName").val();
    tDesignation = $("#tDesignation").val();
    tAddress = $("#tAddress").val();
    tDob = $("#tDob").val();
    tPhone = $("#tPhone").val();
    tEmail = $("#tEmail").val();
    tDegree = $("#tDegree").val();
    tSubject = $("#tSubject").val();
    tJoinDate = $("#tJoinDate").val();
    tType = $("#tType").val();
    tFb = $("#tFb").val();
    tTwit = $("#tTwit").val();
    tDescription = $("#tDescription").val();

    if (tImg == '') {
        $("#tImage").addClass("efw");
    } else if (tName == '') {
        $("#tName").addClass("efw");
    } else if (tDesignation == '') {
        $("#tDesignation").addClass("efw");
    } else {

        $("#addNewTeacherModal").modal("hide");

        if (t == 201) {


            var teacher = {
                'type': tType,
                'name': tName,
                'img': tImg,
                'designation': tDesignation,
                'address': tAddress,
                'email': tEmail,
                'phone': tPhone,
                'subject': tSubject,
                'degree': tDegree,
                'dob': tDob,
                'joinDate': tJoinDate,
                'description': tDescription,
                'facebookLink': tFb,
                'twitterLink': tTwit
            };



            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "save_teacher",
                data: JSON.stringify(teacher), // Note : it is important
                success: function (result) {
                    if (result) {
                        getTeacher();
                    } else {
                        alert("Can't save! Please try again.");
                    }
                },
                error: function (e) {
                    alert("Internal error occurred.");
                }
            });


        } else if (t == 202) {

            var teacher = {
                'id':mdata["id"],
                'type': tType,
                'name': tName,
                'img': tImg,
                'designation': tDesignation,
                'address': tAddress,
                'email': tEmail,
                'phone': tPhone,
                'subject': tSubject,
                'degree': tDegree,
                'dob': tDob,
                'joinDate': tJoinDate,
                'description': tDescription,
                'facebookLink': tFb,
                'twitterLink': tTwit
            };


            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "update_teacher",
                data: JSON.stringify(teacher), // Note : it is important
                success: function (result) {
                    if (result) {
                        getTeacher();
                    } else {
                        alert("Can't update! Please try again.");
                    }
                },
                error: function (e) {
                    alert("Internal error occurred.");
                }
            });

        }

    }

}

