/**
 * Created by A.A.MAMUN on 8/14/2019.
 */


/*
 @ CPANEL CLASS
 */

/*------FETCH BOOK---------*/

function getBookList(cls)
{
    $("#loader").show();
    $.post("access_class_book", {
            cls : cls
        },
        function(data, status){

            $("#loader").hide(100);
            if(data!=null){

                tblRow = "";
                index = Object.keys(data);
                for(i=0;i<index.length;i++){

                    valueSet = Object.values(data)[i];
                    id = valueSet["id"];
                    name = valueSet["bookName"];
                    pdfLink = valueSet["pdfFileLink"];

                    tblRow+="<tr>";
                    tblRow+="<td>"+(i+1)+"</td>";
                    tblRow+="<td>"+name+"</td>";
                    tblRow+= "<td style='text-align: center;'> <a target='_blank' href="+pdfLink+">" +
                        "<img width='25px;' src='images/pdf.png'></a> </td>";
                    tblRow+= "<td style='text-align: center'> <input type='button' " +
                        "class='button-delete' onclick='deleteBook("+id+")' value='Delete' /> </td>";
                }

                $("#bookRefTblBody").empty();
                $("#bookRefTblBody").append(tblRow);
            }

        });
}


/*
 Save book to database
 */
function saveBook() {

    bookName = $("#bookName").val();
    pdfLink = $("#pdfLink").val();


    if(bookName.length==0 || pdfLink.length==0){
        alert("Please enter Book Name and pdf link.")
    }else{

        cls = $("#selectedClass").val();
        $("#loader").show();
        $.post("save_book",{
            bookName: bookName,
            pdfLink: pdfLink,
            forClass: cls
        }, function(data, status){
            $("#loader").hide(150);
            //alert(data+", "+status);
            if(data){
                $("#bookName").val("");
                $("#pdfLink").val("");
                getBookList(cls);
                //alert("Book successfully saved !");
            }else{
                // alert("Failed to saved book !");
            }
        });
        // alert(bookName+", "+pdfLink);
    }
}


/*
 Delete book from database
 */
function deleteBook(v) {
    // alert("Delete Book Id : "+v);
    $.post("delete_book",{bookId: v},function(data, status){
        if(data==true){
            alert("Book successfully deleted.")
            cls = $("#selectedClass").val();
            getBookList(cls);
        }else{
            alert("Book can't delete");
        }
    });
}


/*
 Fetch class routine from database
 */
function getClassRoutine(cls)
{
    $.post("access_class_routine",{cls: cls},function(data, status){

        //alert("Data : "+data+",  Status : "+status);

        tblRow = "";
        index = Object.keys(data);
        classRoutineData = Object.values(data);

        for(i=0;i<index.length;i++){

            mdata = classRoutineData[i];

            tblRow+="<tr>";
            tblRow+="<td>"+mdata["day"]+"</td>";
            tblRow+="<td>"+mdata["one"]+"</td>";
            tblRow+="<td>"+mdata["two"]+"</td>";
            tblRow+="<td>"+mdata["three"]+"</td>";
            tblRow+="<td>"+mdata["four"]+"</td>";
            tblRow+="<td>"+mdata["five"]+"</td>";
            tblRow+="<td>"+mdata["six"]+"</td>";

            tblRow+="<td style='text-align: center'><button  data-toggle='modal' data-target='#editClsRoutineModal' " +
                "class='button-edit' onclick='editClassRoutine("+i+")'> Edit </button> </td>";

            tblRow+="</tr>";

        }

        $("#clsRoutineTblBody").empty();
        $("#clsRoutineTblBody").append(tblRow);


    });

}

/*
 Update class routine by update subject
 */
function editClassRoutine(v){

    mdata = classRoutineData[v];

    id = mdata["id"];
    forClass = mdata["forClass"];
    day = mdata["day"];
    one = mdata["one"];
    two = mdata["two"];
    three = mdata["three"];
    four = mdata["four"];
    five = mdata["five"];
    six = mdata["six"];

    // alert("called : "+id+", "+day+", "+one);

    $("#one").val(one);
    $("#two").val(two);
    $("#three").val(three);
    $("#four").val(four);
    $("#five").val(five);
    $("#six").val(six);

    $("#one").removeClass("efw");
    $("#two").removeClass("efw");
    $("#three").removeClass("efw");
    $("#four").removeClass("efw");
    $("#five").removeClass("efw");
    $("#six").removeClass("efw");

    $("#clsRoutineModalHeader").text("CLASS : "+mdata["forClass"]+", DAY : "+day);

    $("#updateRoutine").click(function(){

        //  $("#editClsRoutineModal").attr("data-dismiss").val("modal");

        isBlank = false;

        $("#one").removeClass("efw");
        $("#two").removeClass("efw");
        $("#three").removeClass("efw");
        $("#four").removeClass("efw");
        $("#five").removeClass("efw");
        $("#six").removeClass("efw");

        if($("#one").val()==''){
            $("#one").addClass("efw");
            isBlank = true;
        }
        if($("#two").val()==''){
            $("#two").addClass("efw");
            isBlank = true;
        }
        if($("#three").val()==''){
            $("#three").addClass("efw");
            isBlank = true;
        }
        if($("#four").val()==''){
            $("#four").addClass("efw");
            isBlank = true;
        }
        if($("#five").val()==''){
            $("#five").addClass("efw");
            isBlank = true;
        }
        if($("#six").val()==''){
            $("#six").addClass("efw");
            isBlank = true;
        }

        if(!isBlank){

            $('#editClsRoutineModal').modal('hide');

            _one = $("#one").val();
            _two = $("#two").val();
            _three = $("#three").val();
            _four = $("#four").val();
            _five =  $("#five").val();
            _six = $("#six").val();

            // $("#loader").show();

            $.post("update_class_routine",{

                    id: id,
                    forClass: forClass,
                    day: day,
                    one: _one,
                    two: _two,
                    three: _three,
                    four: _four,
                    five: _five,
                    six: _six

                },
                function(data, status){
                    //alert(data+", "+status);
                    if(data==true){
                        cls = $("#selectedClass").val();
                        getClassRoutine(cls)
                    }
                });
        }

    });

}


/*
 Fetch exam routine from database
 */
function getExamRoutine(cls) {

    $.post("access_exam_routine",{cls: cls},function(data, status){

        tblRow = "";
        index = Object.keys(data);
        examRoutineData = Object.values(data);

        for(i=0;i<index.length;i++)
        {
            mdata = examRoutineData[i];
            tblRow+="<tr>";
            tblRow+="<td>"+mdata["date"]+"</td>";
            tblRow+="<td>"+mdata["subject"]+"</td>";
            tblRow+="<td>"+mdata["startTime"]+"</td>";
            tblRow+="<td>"+mdata["endTime"]+"</td>";
            tblRow+="<td style='text-align: center'><button  data-toggle='modal' data-target='#editExmRoutineModal' " +
                "class='button-edit'  onclick='editExamRoutine("+i+")'> Edit </button> </td>";
            tblRow+="<td style='text-align: center'><button class='button-delete'" +
                " onclick='deleteExamRoutine("+mdata["id"]+")' > Delete </button> </td>";
            tblRow+="</tr>";

        }



        $("#exmRoutineTblBody").empty();
        //$('#exmRoutineTblBody > tr').remove();
        $("#exmRoutineTblBody").append(tblRow);


    });

}



/*
 Save exam routine to database
 */
function saveExamRoutine()
{
    $("#exmDate").removeClass("efw");
    $("#exmSubject").removeClass("efw");
    $("#exmStartTim").removeClass("efw");
    $("#exmEndTim").removeClass("efw");

    date = $("#exmDate").val();
    subject =  $("#exmSubject").val();
    startTime = $("#exmStartTim").val();
    endTime = $("#exmEndTim").val();

    isBlank = false;

    if(date==''){
        $("#exmDate").addClass("efw");
        isBlank = true;
    }
    if(subject==''){
        $("#exmSubject").addClass("efw");
        isBlank = true;
    }
    if(startTime==''){
        $("#exmStartTim").addClass("efw");
        isBlank = true;
    }
    if(endTime==''){
        $("#exmEndTim").addClass("efw");
        isBlank = true;
    }

    if(!isBlank)
    {
        cls = $("#selectedClass").val();

        $.post("save_exam_routine",{
            date: date,
            subject: subject,
            startTime: startTime,
            endTime: endTime,
            forClass: cls
        },function (data, status) {
            //alert(data+", "+status);
            if(data){
                $("#exmDate").val('');
                $("#exmSubject").val('');
                $("#exmStartTim").val('');
                $("#exmEndTim").val('');

                getExamRoutine(cls);
            }else{
                alert("Can't save, Please try again.")
            }
        });
    }


}

/*
 Update exam routine
 */
function editExamRoutine(v) {

    mdata = examRoutineData[v];
    //alert(Object.values(mdata));

    id = mdata["id"];
    date = mdata["date"];
    subject = mdata["subject"];
    startTime = mdata["startTime"];
    endTime = mdata["endTime"];
    forClass = mdata["forClass"];

    $("#exmRoutineModalHeader").text("CLASS : "+forClass);

    $("#date").val(date);
    $("#subject").val(subject);
    $("#startTim").val(startTime);
    $("#endTim").val(endTime);

    $("#date").removeClass("efw");
    $("#subject").removeClass("efw");
    $("#startTim").removeClass("efw");
    $("#endTim").removeClass("efw");


    $("#updateExmRoutine").click(function(){

        isBlank = false;

        $("#date").removeClass("efw");
        $("#subject").removeClass("efw");
        $("#startTim").removeClass("efw");
        $("#endTim").removeClass("efw");

        _date = $("#date").val();
        _subject = $("#subject").val();
        _startTime = $("#startTim").val();
        _endTime = $("#endTim").val();

        if(_date==''){
            $("#date").addClass("efw");
            isBlank = true;
        }
        if(_subject==''){
            $("#subject").addClass("efw");
            isBlank = true;
        }
        if(_startTime==''){
            $("#startTim").addClass("efw");
            isBlank = true;
        }
        if(_endTime==''){
            $("#endTim").addClass("efw");
            isBlank = true;
        }

        if(!isBlank)
        {
            $("#editExmRoutineModal").modal("hide");

            //alert(_date+", "+_subject+", "+_startTime+", "+_endTime);

            var  examRoutine= {
                'id': id,
                'date': _date,
                'subject': _subject,
                'startTime': _startTime,
                'endTime': _endTime,
                'forClass': forClass
            }

            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "update_exam_routine",
                data: JSON.stringify(examRoutine), // Note : it is important
                success: function (result) {
                    if(result){
                        cls = $("#selectedClass").val();
                        getExamRoutine(cls);
                    }else{
                        alert("Can't update! Please try again.");
                    }
                },
                error: function (e) {
                    alert("Internal error occurred.");
                }
            });

        }

    });

}


/*
 Delete exam routine from database
 */
function deleteExamRoutine(v)
{
    $.post("delete_exam_routine",{id: v},function(data, status){
        if(data){
            alert("Successfully deleted !");
            cls = $("#selectedClass").val();
            getExamRoutine(cls);
        }
    });
}


/*
 Fetch class lecture from database
 */
function getClassLecture(cls)
{
    $.post("access_class_lecture",{cls: cls},function(data, status){

        index = Object.keys(data);
        classLecturedata = Object.values(data);

        tblRow = "";
        for(i=0; i<index.length; i++)
        {
            mdata = classLecturedata[i];
            id = mdata["id"];
            title = mdata["title"];
            link = mdata["link"];

            tblRow+= "<tr>";
            tblRow+= "<td>"+(1+i)+"</td>";
            tblRow+= "<td>"+link+"</td>";
            tblRow+= "<td>"+title+"</td>";
            tblRow+= "<td><button class='button-delete' onclick='deleteClassLecture("+id+")'>Delete</button></td>";
            tblRow+= "</tr>";
        }

        $("#clsLectureTblBody").empty();
        $("#clsLectureTblBody").append(tblRow);

    });



}


/*
 Save class lecture to database
 */
function saveClassLecture() {

    isBlank = false;

    $("#clsLecId").removeClass("efw");
    $("#clsLecTitle").removeClass("efw");

    clsLecId = $("#clsLecId").val();
    clsLecTitle = $("#clsLecTitle").val();

    if(clsLecId==''){
        $("#clsLecId").addClass("efw");
        isBlank = true;
    }

    if(clsLecTitle==''){
        $("#clsLecTitle").addClass("efw");
        isBlank = true;
    }

    if(!isBlank){

        cls = $("#selectedClass").val();

        var classLecture = {
            'title': clsLecTitle,
            'link': clsLecId,
            'forClass': cls
        };

        $.ajax({
            type:"POST",
            contentType:'application/json;  charset=utf-8',
            dataType:'json',
            url:"save_class_lecture",
            data:JSON.stringify(classLecture),
            success: function (result) {
                if(!result){
                    alert("Can't save class lecture. Please try again.");
                }else{
                    $("#clsLecId").val('');
                    $("#clsLecTitle").val('');
                    getClassLecture(cls);
                }
            },
            error:function (e) {
                alert("Internal error occurred.");
            }

        });

    }
}



/*
 Delete class lecture from database
 */
function deleteClassLecture(v) {

    $.post("delete_class_lecture",{id: v}, function (data, status) {
        if(data){
            cls = $("#selectedClass").val();
            getClassLecture(cls);
        }else{
            alert("Can't delete class lecture. Please try again.")
        }
    });
}




/*
 Fetch exam result from database
 */
function getExamResult(cls)
{
    $.post("access_exam_result",{cls: cls},function(data, status){

        index = Object.keys(data);
        examResultData = Object.values(data);

        tblRow = "";
        for(i=0; i<index.length; i++)
        {
            mdata = examResultData[i];
            id = mdata["id"];
            title = mdata["title"];
            link = mdata["link"];

            tblRow+= "<tr>";
            tblRow+= "<td>"+(1+i)+"</td>";
            tblRow+= "<td>"+title+"</td>";
            tblRow+= "<td style='text-align: center;'><a target='_blank' href="+link+"><img width='25px;' src='images/pdf.png'></a></td>";
            tblRow+= "<td><button class='button-delete' onclick='deleteExamResult("+id+")'>Delete</button></td>";
            tblRow+= "</tr>";
        }

        $("#exmResultTblBody").empty();
        $("#exmResultTblBody").append(tblRow);

    });



}


/*
 Save class lecture to database
 */
function saveExamResult() {

    isBlank = false;

    $("#exmResultId").removeClass("efw");
    $("#exmResultTitle").removeClass("efw");

    exmResultId = $("#exmResultId").val();
    exmResultTitle = $("#exmResultTitle").val();

    if(exmResultId==''){
        $("#exmResultId").addClass("efw");
        isBlank = true;
    }

    if(exmResultTitle==''){
        $("#exmResultTitle").addClass("efw");
        isBlank = true;
    }

    if(!isBlank){

        cls = $("#selectedClass").val();

        var examResult = {
            'title': exmResultTitle,
            'link': exmResultId,
            'forClass': cls
        };

        $.ajax({
            type:"POST",
            contentType:'application/json;  charset=utf-8',
            dataType:'json',
            url:"save_exam_result",
            data:JSON.stringify(examResult),
            success: function (result) {
                if(!result){
                    alert("Can't save exam result. Please try again.");
                }else{
                    $("#exmResultId").val('');
                    $("#exmResultTitle").val('');
                    getExamResult(cls);
                }
            },
            error:function (e) {
                alert("Internal error occurred.");
            }

        });

    }
}



/*
 Delete class lecture from database
 */
function deleteExamResult(v) {

    $.post("delete_exam_result",{id: v}, function (data, status) {
        if(data){
            cls = $("#selectedClass").val();
            getExamResult(cls);
        }else{
            alert("Can't delete exam result file. Please try again.")
        }
    });
}



function getSyllabus() {

    $.post("access_class_syllabus",{cls:cls}, function (data, status) {
        $("#syllabusLink").val(data["link"]);
    });
}



function searchClass(){

    cls = $("#selectedClass").val();
    getSyllabus();
    getBookList(cls);
    getClassRoutine(cls);
    getExamRoutine(cls);
    getClassLecture(cls);
    getExamResult(cls);
}



 $(document).ready(function(){

  searchClass();

  $("#updateSyllabus").click(function(){

      $("#syllabusLink").removeClass("efw");
      link = $("#syllabusLink").val();
      if(link==''){
          $("#syllabusLink").addClass("efw");
      }else{
          cls = $("#selectedClass").val();
          $.post("update_syllabus",{cls: cls, link: link}, function (data, status) {
              if(!data){
                  alert("Can't update please try again.")
              }else{
                  alert("Successfully updated.")
              }
          });
      }

  });


 });


