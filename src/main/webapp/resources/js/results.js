/**
 * Created by Angel Rene Garcia on 04/11/14.
 */

function viewResults(json){
    //alert(JSON.stringify(json));
    var container = $("#results");
    var obj, div, title, table, tr, td, thead, tbody;
    for (var i = 0; i < json.materias.length; i++) {
        obj = json.materias[i];
        div = $("<div></div>").addClass("row-fluid");
        title = $("<h4></h4>").addClass("text-primary");
        title.append(obj.nombre);
        div.append(title).append("<br/>");
        table = $("<table></table>").addClass("table table-responsive table-bordered");
        thead = $("<thead></thead>");
        tr = $("<tr></tr>");
        td = $("<th></th>").append("Fecha");
        tr.append(td);
        td = $("<th></th>").append("Nota");
        tr.append(td);
        thead.append(tr);
        table.append(thead);

        tbody = $("<tbody></tbody>");

        for (var j = 0; j < obj.notas.length; j++) {
            var nota = obj.notas[j];
            tr = $("<tr></tr>");
            td = $("<td></td>").append(nota.fecha);
            tr.append(td);
            td = $("<td></td>").append(nota.valor);
            tr.append(td);
            tbody.append(tr);
        }
        table.append(tbody);
        div.append(table);
        container.append(div);
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


    viewResults(resultados);
});