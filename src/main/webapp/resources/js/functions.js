/**
 * Created by Angel Rene Garcia on 03/26/14.
 */

function showContainer(id){
    //$('[id="'+id+'"]').toggle();
    $('#'+ id +'').modal('show');

}
function hideContainer(id){
    //$('[id="'+id+'"]').toggle();
    $('#'+ id +'').modal('hide');
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
}

function formatSwitch (){
    $(".switch-dlg").switchButton({
        on_label: 'S&iacute;',
        off_label: 'No'
    });
}

function viewSchedule(json){
    var dia = $("#lunes");
    var tr,td;
    var txt = "";
    var obj;

    for (var i = 0; i < json.lunes.length; i++) {
        obj = json.lunes[i];
        tr = $("<tr></tr>");
        td = $("<td></td>");
        txt = "<p>"+ obj.materia +" <br/>" + obj.inicio +" - " + obj.fin +"</p>"
        td.append(txt);
        tr.append(td);
        dia.append(tr);
    }

    dia = $("#martes");

    txt = "";

    for (var i = 0; i < json.martes.length; i++) {
        obj = json.martes[i];
        tr = $("<tr></tr>");
        td = $("<td></td>");
        txt = "<p>"+ obj.materia +" <br/>" + obj.inicio +" - " + obj.fin +"</p>"
        td.append(txt);
        tr.append(td);
        dia.append(tr);
    }

    dia = $("#miercoles");

    txt = "";

    for (var i = 0; i < json.miercoles.length; i++) {
        obj = json.miercoles[i];
        tr = $("<tr></tr>");
        td = $("<td></td>");
        txt = "<p>"+ obj.materia +" <br/>" + obj.inicio +" - " + obj.fin +"</p>"
        td.append(txt);
        tr.append(td);
        dia.append(tr);
    }

    dia = $("#jueves");

    txt = "";

    for (var i = 0; i < json.jueves.length; i++) {
        obj = json.jueves[i];
        tr = $("<tr></tr>");
        td = $("<td></td>");
        txt = "<p>"+ obj.materia +" <br/>" + obj.inicio +" - " + obj.fin +"</p>"
        td.append(txt);
        tr.append(td);
        dia.append(tr);
    }

    dia = $("#viernes");

    txt = "";

    for (var i = 0; i < json.viernes.length; i++) {
        obj = json.viernes[i];
        tr = $("<tr></tr>");
        td = $("<td></td>");
        txt = "<p>"+ obj.materia +" <br/>" + obj.inicio +" - " + obj.fin +"</p>"
        td.append(txt);
        tr.append(td);
        dia.append(tr);
    }
}

$(document).ready(function(){

    var resultados = {
        materias:[
            {
                nombre: "matematicas",
                notas:[
                    {
                        fecha: "YYMMDD",
                        valor: 90
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 70
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 85
                    }
                ]

            },
            {
                nombre: "sociales",
                notas:[
                    {
                        fecha: "YYMMDD",
                        valor: 90
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 80
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 95
                    }
                ]

            },
            {
                nombre: "quimica",
                notas:[
                    {
                        fecha: "YYMMDD",
                        valor: 90
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 70
                    },
                    {
                        fecha: "YYMMDD",
                        valor: 85
                    }
                ]

            }

        ]
    };

    var json = {
        lunes: [
        ],
        martes: [
        ],
        miercoles: [
        ],
        jueves: [
        ],
        viernes: [
        ]
    };

    var data = document.getElementById('containerForm:jsonData').value;
    var jsonData = JSON.parse(data);
    for(var i = 0; i < jsonData.subjectsCourses.length; i++){
        var obj = jsonData.subjectsCourses[i];
        for(var x = 0; x < obj.schedule.length; x++){
            var obj2 = obj.schedule[x];
            if(obj2.day.toLowerCase() == "lunes"){
                json.lunes.push({materia: obj.subject.name, inicio: obj2.startTime, fin: obj2.endTime});
            }else if(obj2.day.toLowerCase() == "martes"){
                json.martes.push({materia: obj.subject.name, inicio: obj2.startTime, fin: obj2.endTime});
            }else if(obj2.day.toLowerCase() == "miercoles"){
                json.miercoles.push({materia: obj.subject.name, inicio: obj2.startTime, fin: obj2.endTime});
            }else if(obj2.day.toLowerCase() == "jueves"){
                json.jueves.push({materia: obj.subject.name, inicio: obj2.startTime, fin: obj2.endTime});
            }else if(obj2.day.toLowerCase() == "viernes"){
                json.viernes.push({materia: obj.subject.name, inicio: obj2.startTime, fin: obj2.endTime});
            }
        }

    }

    viewSchedule(json);
});