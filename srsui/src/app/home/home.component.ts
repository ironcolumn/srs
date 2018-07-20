import {AfterViewInit, Component, OnInit} from "@angular/core";
import {FormControl} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {CookieService} from "ngx-cookie";
import {MyHttpService} from "../interceptor/my-http.service";
import {NetServerConf} from "../shared/net-server-conf";
import {AuthenticationService} from "../shared/service/authentication.service";
import {Notice, NoticeAttachment} from "../shared/service/notice.service";

@Component({
  selector: "app-home", templateUrl: "./home.component.html", styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit, AfterViewInit {
  visible: boolean = false;

  displayDownload: boolean = false;

  displayNotice: boolean = false;

  username: string;

  qCloudDomain: string = NetServerConf.QCloudDomain;

  notices: Notice[] = [];

  noticeDisplay: Notice;

  noticeCount: number;

  checkTaskCount: number = 0;

  correctCount: number;

  reviewCount: number;

  approveCount: number;

  checkTableCount: number;

  alertWarningCount: number;

  correctOutOfDateCount: number;

  items: MenuItem[] = [];

  itemsDisplay: MenuItem[] = [];

  uploadUrl: string;

  searchContext: FormControl = new FormControl;

  selectedIndex = 0;
  tabs = [];

  constructor(private cookieService: CookieService,
              private authenticationService: AuthenticationService,
              private router: Router,
              private http: MyHttpService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.items = this.authenticationService.getAuthorizationInfo().menuItems;
    this.itemsDisplay = this.items;
    this.username = this.authenticationService.getAuthorizationInfo().usernameDisplay;
    this.approveCount = this.authenticationService.getAuthorizationInfo().task.approveCount;
    this.checkTableCount = this.authenticationService.getAuthorizationInfo().task.checkTableCount;
    this.checkTaskCount = this.authenticationService.getAuthorizationInfo().task.checkTaskCount;
    // this.checkTaskCount= this.authenticationService.getAuthorizationInfo().task.checkTaskCount;
    this.reviewCount = this.authenticationService.getAuthorizationInfo().task.reviewCount;
    this.correctCount = this.authenticationService.getAuthorizationInfo().task.correctCount;
    this.alertWarningCount = this.authenticationService.getAuthorizationInfo().task.alertWarningCount;
    this.correctOutOfDateCount = this.authenticationService.getAuthorizationInfo().task.correctOutOfDateCount;
    this.noticeCount = this.authenticationService.getAuthorizationInfo().notice.length;
    this.notices = this.authenticationService.getAuthorizationInfo().notice;
    // this.searchContext.valueChanges.debounceTime ( 500 )
    //   .subscribe ( searchContext => {
    //     this.itemsDisplay = this.items.filter ( item => this.filter ( item , searchContext ) );
    //   } );
    // this.router.events.filter ( event => event instanceof NavigationStart )
    //   .subscribe ( event => {
    //     if ( event instanceof NavigationStart && event.url.substring ( 0 , event.url.length - 2 ) == "/home/check" ) {
    //       console.log(event.url.substring ( event.url.length - 1 , event.url.length ) )
    //       this.router.navigate ( [ "/home/check/dynamicCheck" ,
    //                                event.url.substring ( event.url.length - 1 , event.url.length ) ] ).then ();
    //     }
    //     // console.log(event)
    //   } );
  }


  onTabClose(panel) {
    this.tabs = this.tabs.filter(p => p.title != panel.title);
  }

  getTabIndex(title) {
    for (let i = 0; i < this.tabs.length; i++) {
      if (this.tabs[i].title == title) {
        return i;
      }
    }
    return -1;
  }

  addTab(tab) {
    let index = this.getTabIndex(tab.title);
    if (index == -1) {
      this.tabs.push(tab);
      setTimeout(() => this.selectedIndex = this.tabs.length);
    } else {
      this.selectedIndex = index + 1;
    }
  }


  navigateToDangerQuery() {
    const url: string = `/home/dangerQuery`;
    this.router.navigate([url], {queryParams: {isAlertWarning: true}})
      .then();
  }


  logOut() {
    this.authenticationService.setAuthorizationInfo(null);
    this.cookieService.removeAll();
    this.router.navigate(["login"])
      .catch(status => console.log(status));
  }

  ngAfterViewInit() {
    // $ ( "#0" ).trigger ( "click" );
  }

  public getTaskCount() {
    const url = `api/task`;
    this.http.get(url).map(res => res.json()).subscribe(task => {
      this.approveCount = task.approveCount;
      this.checkTableCount = task.checkTableCount;
      this.checkTaskCount = task.checkTaskCount;
      this.reviewCount = task.reviewCount;
      this.correctCount = task.correctCount;
    });
  }

  downloadList(rowData) {
    // this.selectedDanger = rowData;
    // this.attachments = this.selectedDanger.inconformityAttachmentList;
    // this.displayDownload = true;
    // this.uploadUrl = `api/inconformityItems/file/${this.selectedDanger.inconformityItemSn}`;
  }

  noticeClick(e) {
    this.noticeDisplay = e;
    this.displayNotice = true;
  }

  public download(attachment: NoticeAttachment) {
    const url = `${this.qCloudDomain}/fxykDownload/NoticeAttachment/${attachment.noticeId}/${attachment.logicalFilename}`;
    window.open(url);
  }

  private filter(item: MenuItem, searchContext) {
    if (item.label.indexOf(searchContext) > -1) {
      return true;
    }
    if (item.items) {
      return item.items.some(item => {
        if (item.label.indexOf(searchContext) > -1) {
          return true;
        }
        if (item.items) {
          return item.items.some(item => {
            if (item.label.indexOf(searchContext) > -1) {
              return true;
            }
            if (item.items) {
              return item.items.some(item => {
                if (item.label.indexOf(searchContext) > -1) {
                  return true;
                }
              });
            }
          });
        }
      });
    }
    return false;
  }
}

export interface MenuItem {
  label?: string;
  icon?: string;
  command?: (event?: any) => void;
  url?: string;
  routerLink?: any;
  items?: MenuItem[];
  expanded?: boolean;
  disabled?: boolean;
  visible?: boolean;
  target?: string;
  routerLinkActiveOptions?: any;
  separator?: boolean;
  badge?: string;
  badgeStyleClass?: string;
  style?: any;
  styleClass?: string;
  title?: string;
}
