import { MockRequest , MockStatusError } from "@delon/mock";
import { Mock }                          from "protractor/built/driverProviders";
// TIPS: mockjs 一些优化细节见：http://ng-alain.com/docs/mock
// import * as Mock from 'mockjs';

export const STUDENTS = {
  // '/user': (req: MockRequest) => genData(req.queryString),
  // '/user/:id': (req: MockRequest) => list.find(w => w.id === +req.params.id),
  // 'POST /user/:id': (req: MockRequest) => saveData(+req.params.id, req.body),
  // 支持值为 Object 和 Array
  "students/studyPlans/mock" : { msg : "这是一条来自mock的消息" } , // GET 可省略
  '/users/1': ({ id: 1, 'rank|3': '★★★' }),
  // POST 请求
  "POST /users/1"                 : { uid : 1 } , // 获取请求参数 queryString、headers、body
  "/qs"                           : ( req : MockRequest ) => req.queryString.pi , // 路由参数
  "/users/:id"                    : ( req : MockRequest ) => req.params , // /users/100, output: { id: 100 }
  // 发送 Status 错误
  "/404"                          : () => {
    throw new MockStatusError ( 404 );
  } ,
};
