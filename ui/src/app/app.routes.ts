export const appRoutes = [ {
  path      : "" , redirectTo : "home" , // component:AMapComponent,
  pathMatch : "full"
} , {} , {} , //子模块test
                           {
                             path : "test" , loadChildren : "./test/test.module#TestModule"
                           } ];
