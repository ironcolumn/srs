import { NgModule }                   from "@angular/core";
import { RouterModule , Routes }      from "@angular/router";
import { StudentSelectionComponent }  from "./selection/selection.component";
import { StudentStudyPlanComponent }  from "./study-plan/study-plan.component";
import { StudentTranscriptComponent } from "./transcript/transcript.component";

const routes : Routes = [ { path : "" , redirectTo : "study-plan" } ,
                          { path : "study-plan" , component : StudentStudyPlanComponent } ,
                          { path : "course-selection" , component : StudentSelectionComponent } ,
                          { path : "score-query" , component : StudentTranscriptComponent }  ];

@NgModule ( {
  imports : [ RouterModule.forChild ( routes ) ] , exports : [ RouterModule ]
} )
export class StudentRoutingModule {
}
