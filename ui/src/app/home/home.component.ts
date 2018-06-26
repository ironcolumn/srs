import { AfterViewInit , Component , OnInit } from "@angular/core";
import { ActivatedRoute }                     from "@angular/router";

@Component ( {
  selector : "app-home" , templateUrl : "./home.component.html" , styleUrls : [ "./home.component.css" ]
} )
export class HomeComponent implements OnInit , AfterViewInit {
  visible : boolean = false;

  displayNotice : boolean = false;

  username : string;



  noticeCount : number;

  checkTaskCount : number = 0;

  correctCount : number;

  reviewCount : number;

  approveCount : number;

  checkTableCount : number;

  alertWarningCount : number;

  correctOutOfDateCount : number;

  items : MenuItem[] = [];

  itemsDisplay : MenuItem[] = [];

  selectedIndex = 0;

  tabs = [];

  constructor ( private activatedRoute : ActivatedRoute ) {
  }

  ngOnInit () {
  }

  onTabClose ( panel ) {
    this.tabs = this.tabs.filter ( p => p.title != panel.title );
  }

  getTabIndex ( title ) {
    for ( let i = 0 ; i < this.tabs.length ; i ++ ) {
      if ( this.tabs[ i ].title == title ) {
        return i;
      }
    }
    return - 1;
  }



  logOut () {
  }

  ngAfterViewInit () {
    // $ ( "#0" ).trigger ( "click" );
  }


  downloadList ( rowData ) {
    // this.selectedDanger = rowData;
    // this.attachments = this.selectedDanger.inconformityAttachmentList;
    // this.displayDownload = true;
    // this.uploadUrl = `api/inconformityItems/file/${this.selectedDanger.inconformityItemSn}`;
  }

  noticeClick ( e ) {
    this.displayNotice = true;
  }

  private filter ( item : MenuItem , searchContext ) {
    if ( item.label.indexOf ( searchContext ) > - 1 ) {
      return true;
    }
    if ( item.items ) {
      return item.items.some ( item => {
        if ( item.label.indexOf ( searchContext ) > - 1 ) {
          return true;
        }
        if ( item.items ) {
          return item.items.some ( item => {
            if ( item.label.indexOf ( searchContext ) > - 1 ) {
              return true;
            }
            if ( item.items ) {
              return item.items.some ( item => {
                if ( item.label.indexOf ( searchContext ) > - 1 ) {
                  return true;
                }
              } );
            }
          } );
        }
      } );
    }
    return false;
  }
}

export interface MenuItem {
  label? : string;

  icon? : string;

  command? : ( event? : any ) => void;

  url? : string;

  routerLink? : any;

  items? : MenuItem[];

  expanded? : boolean;

  disabled? : boolean;

  visible? : boolean;

  target? : string;

  routerLinkActiveOptions? : any;

  separator? : boolean;

  badge? : string;

  badgeStyleClass? : string;

  style? : any;

  styleClass? : string;

  title? : string;
}
