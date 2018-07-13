import { Component , OnInit , ViewChild }           from "@angular/core";
import { SimpleTableColumn , SimpleTableComponent } from "@delon/abc";
import { _HttpClient , ModalHelper }                from "@delon/theme";
import { StudentSelectionEditComponent }            from "./edit/edit.component";

@Component ( {
  selector : "app-student-selection" , templateUrl : "./selection.component.html" ,
} )
export class StudentSelectionComponent implements OnInit {

  params : any = {};

  dataSet : any[] = [];

  @ViewChild ( "st" ) st : SimpleTableComponent;

  columns : SimpleTableColumn[] = [ { title : "状态" , index : "state" } ,
                                    { title : "课程" , index : "courseName" } ,
                                    { title : "周次" , index : "dayOfWeek" } ,
                                    { title : "教室" , index : "room" } ,
                                    { title : "教师" , index : "teacherName" } ,
                                    { title : "学分" , index : "credit" } ,
                                    { title : "时间" , index : "timeOfDay" } ,
                                    {
                                      title : "" , buttons : [ { text : "退选" , type : "del" , click : ( record ) => this.delete ( record ) } ]
                                    } ];

  msg : string;

  constructor ( private http : _HttpClient , private modal : ModalHelper ) {
  }

  ngOnInit () {
    this.reloadData ();
  }

  reloadData () {
    const url = `transcripts/my`;
    this.http.get ( url ).subscribe ( ( data : any ) => {
      this.dataSet = data;
    } );
  }

  delete ( record ) {
    const url = `transcripts/${record.id}`;
    if ( ! record.grade ) {
      this.http.delete ( url ).subscribe ( () => {
        this.reloadData ();
      } );
    } else {
      this.msg = "不可退选!";
      setTimeout ( () => {
        this.msg = "";
      } , 2000 );
    }
  }

  add () {
    this.modal
    .static ( StudentSelectionEditComponent , { i : {} } )
    .subscribe ( () => this.reloadData () );
  }

}
