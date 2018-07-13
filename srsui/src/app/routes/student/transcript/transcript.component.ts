import { Component , OnInit , ViewChild }           from "@angular/core";
import { SimpleTableColumn , SimpleTableComponent } from "@delon/abc";
import { _HttpClient , ModalHelper }                from "@delon/theme";

@Component ( {
  selector : "app-student-transcript" , templateUrl : "./transcript.component.html" ,
} )
export class StudentTranscriptComponent implements OnInit {
  params : any = {};

  dataSet : any[] = [];

  @ViewChild ( "st" ) st : SimpleTableComponent;

  columns : SimpleTableColumn[] = [

    { title : "课程" , index : "courseName" } ,
    { title : "教室" , index : "room" } ,
    { title : "周次" , index : "dayOfWeek" } ,
    { title : "教师" , index : "teacherName" } ,
    { title : "学分" , index : "credit" } ,
    { title : "时间" , index : "timeOfDay" } ,
    { title : "成绩" , index : "grade" } ];

  constructor ( private http : _HttpClient , private modal : ModalHelper ) {
  }

  ngOnInit () {
    this.reloadData ();
  }

  reloadData () {
    const url = `transcripts/myGraded`;
    this.http.get ( url ).subscribe ( ( data : any ) => {
      this.dataSet = data;
    } );
  }

}
